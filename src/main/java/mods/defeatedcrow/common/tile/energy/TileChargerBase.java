package mods.defeatedcrow.common.tile.energy;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

/**
 * 1.20.1 BatBox - ForgeEnergy (IEnergyStorage) migration from 1.7.10 RF/Charge.
 * Holds 128000 FE, charges batteries in slots 2-9 from fuel in slot 0.
 * Provides ENERGY capability on all sides.
 */
public class TileChargerBase extends BlockEntity implements WorldlyContainer {

    public static final int MAX_CHARGE = 128000;
    public ItemStack[] items = new ItemStack[10];
    private int chargeAmount = 0;
    private int coolTime = 0;

    private final EnergyStorage energyStorage = new EnergyStorage(MAX_CHARGE, 1000, 1000, 0) {
        @Override
        public int getEnergyStored() { return TileChargerBase.this.chargeAmount; }
        @Override
        public int getMaxEnergyStored() { return MAX_CHARGE; }
        @Override
        public boolean canExtract() { return true; }
        @Override
        public boolean canReceive() { return true; }
        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            int ret = Math.min(MAX_CHARGE - chargeAmount, Math.min(1000, maxReceive));
            if (!simulate && ret > 0) { chargeAmount += ret; setChanged(); }
            return ret;
        }
        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            int ret = Math.min(chargeAmount, Math.min(1000, maxExtract));
            if (!simulate && ret > 0) { chargeAmount -= ret; setChanged(); }
            return ret;
        }
    };
    private LazyOptional<IEnergyStorage> energyCap = LazyOptional.of(() -> energyStorage);

    public TileChargerBase(BlockPos pos, BlockState state){
        super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_CHARGER_DEVICE.get(), pos, state);
        java.util.Arrays.fill(items, ItemStack.EMPTY);
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag == null) return;
        super.load(tag);
        NonNullList<ItemStack> list = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, list);
        for (int i=0;i<items.length;i++) items[i]=list.get(i);
        chargeAmount = tag.getInt("ChargeAmount");
        coolTime = tag.getInt("CoolTime");
    }
    @Override
    public void saveAdditional(CompoundTag tag){
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, NonNullList.of(ItemStack.EMPTY, items));
        tag.putInt("ChargeAmount", chargeAmount);
        tag.putInt("CoolTime", coolTime);
    }
    @Override public CompoundTag getUpdateTag(){ CompoundTag t=super.getUpdateTag(); saveAdditional(t); return t; }
    @Override public ClientboundBlockEntityDataPacket getUpdatePacket(){ return ClientboundBlockEntityDataPacket.create(this); }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY) return energyCap.cast();
        return super.getCapability(cap, side);
    }
    @Override public void invalidateCaps() { super.invalidateCaps(); energyCap.invalidate(); }
    @Override public void reviveCaps() { super.reviveCaps(); energyCap = LazyOptional.of(() -> energyStorage); }

    public int getChargeAmount(){ return chargeAmount; }
    public void setChargeAmount(int v){ chargeAmount = Math.min(v, MAX_CHARGE); setChanged(); }
    public int getMaxChargeAmount(){ return MAX_CHARGE; }
    public boolean isFullCharged(){ return chargeAmount >= MAX_CHARGE; }

    public int getBurnTimeRemainingScaled(int scale){ return chargeAmount * scale / MAX_CHARGE; }

    public static void tick(Level level, BlockPos pos, BlockState state, TileChargerBase be){
        if(level.isClientSide) return;
        if(be.coolTime>0) be.coolTime--;
        if(be.coolTime==0){
            ItemStack fuel = be.items[0];
            if(!fuel.isEmpty() && !be.isFullCharged()){
                int burn = getItemBurnTime(fuel);
                if(burn>0 && be.chargeAmount + burn <= be.getMaxChargeAmount()){
                    var cap = fuel.getCapability(ForgeCapabilities.ENERGY).orElse(null);
                    if(cap != null){
                        int ext = cap.extractEnergy(16, false);
                        if(ext>0){ be.chargeAmount+=ext; }
                        if(cap.getEnergyStored()==0 && be.items[1].isEmpty()){
                            be.items[1]=fuel.copy(); be.items[1].setCount(1);
                            fuel.shrink(1); if(fuel.isEmpty()) be.items[0]=ItemStack.EMPTY;
                        }
                    } else {
                        be.chargeAmount+=burn;
                        ItemStack container = fuel.getCraftingRemainingItem();
                        if(!container.isEmpty()){
                            if(be.items[1].isEmpty()) be.items[1]=container.copy();
                            else if(be.items[1].is(container.getItem())) be.items[1].grow(1);
                        }
                        fuel.shrink(1); if(fuel.isEmpty()) be.items[0]=ItemStack.EMPTY;
                    }
                }
            }
            for(int i=2;i<be.getContainerSize();i++){
                ItemStack batt = be.items[i];
                if(batt.isEmpty() || be.chargeAmount <=0) continue;
                var cap = batt.getCapability(ForgeCapabilities.ENERGY).orElse(null);
                if(cap != null && cap.canReceive()){
                    int toSend = Math.min(16, be.chargeAmount);
                    int received = cap.receiveEnergy(toSend, false);
                    be.chargeAmount-=received;
                    if(received>0) be.setChanged();
                }
            }
            be.coolTime=4;
            be.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
        }
    }

    public static int getItemBurnTime(ItemStack stack){
        if(stack.isEmpty()) return 0;
        var cap = stack.getCapability(ForgeCapabilities.ENERGY).orElse(null);
        if(cap != null){
            int ext = cap.extractEnergy(16, true);
            return ext;
        }
        if(stack.is(net.minecraft.world.item.Items.COAL) || stack.is(net.minecraft.world.item.Items.CHARCOAL)) return 400;
        return 0;
    }
    public static boolean isItemFuel(ItemStack s){ return getItemBurnTime(s)>0; }

    @Override public int getContainerSize(){ return items.length; }
    @Override public boolean isEmpty(){ for(ItemStack s:items) if(!s.isEmpty()) return false; return true; }
    @Override public ItemStack getItem(int i){ return items[i]; }
    @Override public ItemStack removeItem(int i,int count){
        if (items[i].isEmpty()) return ItemStack.EMPTY;
        if (items[i].getCount() <= count) { ItemStack s=items[i]; items[i]=ItemStack.EMPTY; setChanged(); return s; }
        else { ItemStack s=items[i].split(count); setChanged(); return s; }
    }
    @Override public ItemStack removeItemNoUpdate(int i){ ItemStack s=items[i]; items[i]=ItemStack.EMPTY; return s; }
    @Override public void setItem(int i, ItemStack s){ items[i]=s; setChanged(); }
    @Override public boolean stillValid(Player p){ return true; }
    @Override public void clearContent(){ for(int i=0;i<items.length;i++) items[i]=ItemStack.EMPTY; }
    @Override public int[] getSlotsForFace(Direction d){ return new int[]{0,1,2,3,4,5,6,7,8,9}; }
    @Override public boolean canPlaceItemThroughFace(int i, ItemStack s, Direction d){ return i==0 || (i>=2 && i<=9); }
    @Override public boolean canTakeItemThroughFace(int i, ItemStack s, Direction d){ return i==1 || (i>=2); }
}
