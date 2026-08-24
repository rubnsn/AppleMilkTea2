package mods.defeatedcrow.common.tile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
public class TileHasRemaining extends BlockEntity {
    public TileHasRemaining(BlockPos pos, BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_HAS_REMAINING.get(), pos, state); }


    private byte remain = 1;

    // NBT
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.remain = tag.getByte("Remaining");
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putByte("Remaining", this.remain);
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

    public byte getRemainByte() {
        return this.remain;
    }

    public void setRemainByte(byte remain) {
        this.remain = remain;
    }

    public int getMetadata() { return 0; }
}
