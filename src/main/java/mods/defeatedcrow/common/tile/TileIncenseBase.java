package mods.defeatedcrow.common.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import mods.defeatedcrow.common.DCsAppleMilk;

/*
 * 情報の保存と、作動時間のカウントだけ行う。
 */
public class TileIncenseBase extends BlockEntity {
    public TileIncenseBase(BlockPos pos, BlockState state) { super(null, pos, state); }


    private ItemStack[] holdItem = new ItemStack[2];
    private boolean isActive = false;
    private int remainTick = 0;

    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);

        if (par1CompoundTag.contains("HoldItem")) {
            this.setItemstack(ItemStack.loadItemStackFromNBT(par1CompoundTag.getCompound("HoldItem")));
        }

        if (par1CompoundTag.contains("Ash")) {
            this.holdItem[1] = ItemStack.loadItemStackFromNBT(par1CompoundTag.getCompound("Ash"));
        }

        this.remainTick = par1CompoundTag.getShort("RemainTick");
        this.isActive = par1CompoundTag.getBoolean("Active");
    }

    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);

        par1CompoundTag.putShort("RemainTick", (short) this.remainTick);
        par1CompoundTag.putBoolean("Active", this.isActive);

        if (this.getItemstack() != null) {
            par1CompoundTag.put(
                "HoldItem",
                this.getItemstack()
                    .saveAdditional(new CompoundTag()));
        }

        if (this.getAsh() != null) {
            par1CompoundTag.put(
                "Ash",
                this.getAsh()
                    .saveAdditional(new CompoundTag()));
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag nbtTagCompound = new CompoundTag();
        this.saveAdditional(nbtTagCompound);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    public boolean hasItem() {
        boolean flag = false;
        if (holdItem[0] != null) {
            flag = true;
        }
        return flag;
    }

    public ItemStack getItemstack() {
        return this.holdItem[0];
    }

    public ItemStack getAsh() {
        return this.holdItem[1];
    }

    public void setItemstack(ItemStack par1ItemStack) {
        this.holdItem[0] = par1ItemStack;
    }

    public int getRemain() {
        return this.remainTick;
    }

    public void setRemain(int i) {
        this.remainTick = i;
    }

    public boolean getActive() {
        return this.isActive;
    }

    public void setActive() {
        this.isActive = true;
        this.remainTick = 2400;// 2分間
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TileIncenseBase be) {
        // 1.20.1 tick (was updateEntity) - see doc/tile-entities/migration-guide.md
        if (level.isClientSide) return;
        be.setChanged();
        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
    }

    private void addAsh() {
        ItemStack ash = be.getAsh();
        if (ash == null) {
            ash = new ItemStack(DCsAppleMilk.dustWood, 1, 2);
        } else if (ash.stackSize < 64) {
            ++ash.stackSize;
        }
        be.holdItem[1] = ash;
    }

}
