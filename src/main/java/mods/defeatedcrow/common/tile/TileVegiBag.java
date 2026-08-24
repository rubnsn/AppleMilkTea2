package mods.defeatedcrow.common.tile;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
public class TileVegiBag extends TileHasDirection {
    public TileVegiBag(net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.state.BlockState state) { super(pos, state); }


    private boolean sneak = false;

    // NBT
    @Override
    public void load(CompoundTag tag) {
        if (tag == null) return;
        super.load(tag);
        this.sneak = tag.getBoolean("Sneaking");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("Sneaking", this.sneak);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
        this.saveAdditional(tag);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
        if (tag != null) this.load(tag);
    }

    public boolean getSneaking() {
        return this.sneak;
    }

    public void setSneaking(boolean b) {
        this.sneak = b;
    }

}
