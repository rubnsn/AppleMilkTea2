package mods.defeatedcrow.common.tile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
public class TileHasRemain2 extends BlockEntity {
    public TileHasRemain2(BlockPos pos, BlockState state) { super(null, pos, state); }


    private short remain = 1;

    // NBT
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);
        this.remain = par1CompoundTag.getShort("Remaining");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);
        par1CompoundTag.putShort("Remaining", this.remain);
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

    public short getRemainShort() {
        return this.remain;
    }

    public void setRemainShort(short par1) {
        this.remain = par1;
    }

    public int getMetadata() { return 0; }
}
