package mods.defeatedcrow.common.tile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
public class TileHasRemaining extends BlockEntity {
    public TileHasRemaining(BlockPos pos, BlockState state) { super(null, pos, state); }


    private byte remain = 1;

    // NBT
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);
        this.remain = par1CompoundTag.getByte("Remaining");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);
        par1CompoundTag.putByte("Remaining", this.remain);
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

    public byte getRemainByte() {
        return this.remain;
    }

    public void setRemainByte(byte par1) {
        this.remain = par1;
    }

    public int getMetadata() { return 0; }
}
