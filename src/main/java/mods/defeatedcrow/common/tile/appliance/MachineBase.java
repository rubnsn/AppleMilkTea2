package mods.defeatedcrow.common.tile.appliance;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import mods.defeatedcrow.api.charge.IChargeGenerator;
import mods.defeatedcrow.api.charge.IChargeableMachine;
import mods.defeatedcrow.api.charge.ChargeItemManager;
import mods.defeatedcrow.api.energy.IBattery;
import mods.defeatedcrow.common.config.DCsConfig;

/**
 * 1.20.1: TileEntity -> BlockEntity, S35 -> ClientboundBlockEntityDataPacket, level->level, xCoord->getBlockPos(), updateEntity->tick
 * RF/IEnergyHandler -> Forge Energy IEnergyStorage capability (MachineBase now uses ForgeCapabilities.ENERGY via IChargeableMachine bridge)
 * See doc/tile-entities/migration-guide.md
 */
public abstract class MachineBase extends BlockEntity implements net.minecraft.world.Container, IChargeableMachine {

    // current charge
    private int chargeAmount = 0;
    private int coolTime = 4;
    public int cookTime = 0;
    public ItemStack[] itemstacks = new ItemStack[getContainerSize()];

    public MachineBase(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }



    private static int exchangeRateRF() { return mods.defeatedcrow.handler.PropertyHandler.rateRF(); }
    private static int exchangeRateEU() { return mods.defeatedcrow.handler.PropertyHandler.rateEU(); }
    private static int exchangeRateGF() { return mods.defeatedcrow.handler.PropertyHandler.rateGF(); }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        ListTag list = tag.getList("Items", 10);
        this.itemstacks = new ItemStack[this.getContainerSize()];
        for (int i = 0; i < list.size(); ++i) {
            CompoundTag c = list.getCompound(i);
            byte b0 = c.getByte("Slot");
            if (b0 >= 0 && b0 < this.itemstacks.length) {
                this.itemstacks[b0] = ItemStack.of(c);
            }
        }
        this.chargeAmount = tag.getShort("ChargeAmount");
        this.cookTime = tag.getShort("CookTime");
        this.coolTime = tag.getByte("CoolTime");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ListTag list = new ListTag();
        for (int i = 0; i < this.itemstacks.length; ++i) {
            if (this.itemstacks[i] != null && !this.itemstacks[i].isEmpty()) {
                CompoundTag c = new CompoundTag();
                c.putByte("Slot", (byte) i);
                this.itemstacks[i].save(c);
                list.add(c);
            }
        }
        tag.put("Items", list);
        tag.putShort("ChargeAmount", (short) this.chargeAmount);
        tag.putShort("CookTime", (short) this.cookTime);
        tag.putByte("CoolTime", (byte) this.coolTime);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        load(pkt.getTag());
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    // 1.20.1 ticker: Block.getTicker supplies this
    public static void tick(Level level, BlockPos pos, BlockState state, MachineBase be) {
        // 1.20.1 tick (was updateEntity) - see doc/tile-entities/migration-guide.md
        if (level.isClientSide) return;
        be.setChanged();
        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
    }

    public abstract boolean canSmelt();
    public abstract void onProgress();

    public static int getItemBurnTime(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return 0;
        int inc = 16;
        if (ChargeItemManager.chargeItem.getChargeAmount(stack) > 0) return ChargeItemManager.chargeItem.getChargeAmount(stack);
        if (stack.getItem() instanceof IBattery bat) return bat.discharge(stack, 16, false);
        return 0;
    }

    public static boolean isItemFuel(ItemStack stack) { return getItemBurnTime(stack) > 0; }

    public int discharge(ItemStack item, int amount, int slot) {
        if (item == null) return 0;
        removeItem(slot, 1);
        return amount;
    }

    public int acceptChargeFromDir(Level level, BlockPos pos, Direction dir) {
        BlockEntity tile = level.getBlockEntity(pos.relative(dir));
        if (tile instanceof IChargeGenerator gen) {
            int g = gen.generateCharge(dir.getOpposite(), true);
            if (g > 0) { gen.generateCharge(dir.getOpposite(), false); return g; }
        }
        return 0;
    }

    public int getDecrementChargePerTick() { return 1; }
    public boolean isFullCharged() { return chargeAmount == getMaxChargeAmount(); }
    public boolean isActive() { return cookTime > 0; }
    public int getChargeAmount() { return chargeAmount; }
    public void setChargeAmount(int v) { this.chargeAmount = v; }
    @Override public int getMaxChargeAmount() { return 25600; }
    @Override public boolean canReceiveChargeItem(ItemStack item) {
        if (item == null || item.isEmpty()) return false;
        int need = getItemBurnTime(item);
        return need > 0 && chargeAmount + need <= getMaxChargeAmount() && (getItem(0).isEmpty() || (getItem(0).is(item.getItem()) && getItem(0).getCount() + item.getCount() < getItem(0).getMaxStackSize()));
    }
    @Override public int addCharge(int amount, boolean sim) {
        if (isFullCharged()) return 0;
        int ret = Math.min(getMaxChargeAmount() - chargeAmount, amount);
        if (!sim) setChargeAmount(chargeAmount + ret);
        return ret;
    }
    @Override public int extractCharge(int amount, boolean sim) {
        int ret = Math.min(chargeAmount, amount);
        if (!sim) setChargeAmount(chargeAmount - ret);
        return ret;
    }

    // Container impl (WorldlyContainer -> Container + WorldlyContainer in 1.20)
    protected abstract int[] slotsTop();
    protected abstract int[] slotsBottom();
    protected abstract int[] slotsSides();
    @Override public int getContainerSize() { return 2; }
    @Override public boolean isEmpty() { for (ItemStack s : itemstacks) if (s != null && !s.isEmpty()) return false; return true; }
    @Override public ItemStack getItem(int i) { return i < getContainerSize() ? itemstacks[i] : ItemStack.EMPTY; }
    @Override public ItemStack removeItem(int i, int count) {
        if (itemstacks[i] != null && !itemstacks[i].isEmpty()) {
            ItemStack ret;
            if (itemstacks[i].getCount() <= count) { ret = itemstacks[i]; itemstacks[i] = ItemStack.EMPTY; return ret; }
            else { ret = itemstacks[i].split(count); if (itemstacks[i].getCount() == 0) itemstacks[i] = ItemStack.EMPTY; return ret; }
        }
        return ItemStack.EMPTY;
    }
    @Override public ItemStack removeItemNoUpdate(int i) { ItemStack s = itemstacks[i]; itemstacks[i] = ItemStack.EMPTY; return s; }
    @Override public void setItem(int i, ItemStack stack) {
        if (i >= getContainerSize()) i = 0;
        itemstacks[i] = stack;
        if (stack != null && !stack.isEmpty() && stack.getCount() > getMaxStackSize()) stack.setCount(getMaxStackSize());
        setChanged();
    }
    @Override public void setChanged() { super.setChanged(); }
    @Override public boolean stillValid(Player player) {
        if (level.getBlockEntity(getBlockPos()) != this) return false;
        return player.distanceToSqr(getBlockPos().getX() + 0.5, getBlockPos().getY() + 0.5, getBlockPos().getZ() + 0.5) <= 64;
    }
    @Override public void clearContent() { for (int i=0;i<itemstacks.length;i++) itemstacks[i]=ItemStack.EMPTY; }
    @Override public int getMaxStackSize() { return 64; }
    public boolean hasCustomName() { return true; }
    public String getContainerName() { return "MachineBase"; }
    public boolean canPlaceItem(int slot, ItemStack stack) { return (slot==0 ? isItemFuel(stack) : !isItemFuel(stack)); }
    public int[] getSlotsForFace(Direction dir) { return dir == Direction.DOWN ? slotsBottom() : dir == Direction.UP ? slotsTop() : slotsSides(); }
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, Direction dir) { return canPlaceItem(slot, stack); }
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction dir) { return true; }
}
