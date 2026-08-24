package mods.defeatedcrow.common.tile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
public class TileHasRemain2 extends BlockEntity {
    public TileHasRemain2(BlockPos pos, BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_HAS_REMAIN2.get(), pos, state); }


    private short remain = 1;

    // NBT
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.remain = tag.getShort("Remaining");
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putShort("Remaining", this.remain);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
        this.saveAdditional(tag);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    public short getRemainShort() {
        return this.remain;
    }

    public void setRemainShort(short remain) {
        this.remain = remain;
    }

    public int getMetadata() { return 0; }
}
