package mods.defeatedcrow.common.tile;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
public class TileBread extends TileHasDirection {
    public TileBread(net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.state.BlockState state) { super(pos, state); }


    private boolean isTallModel;
    private byte type = 0;

    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);
        this.isTallModel = par1CompoundTag.getBoolean("Tall");
        this.type = par1CompoundTag.getByte("Type");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);
        par1CompoundTag.putBoolean("Tall", this.isTallModel);
        par1CompoundTag.putByte("Type", this.type);
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

    public boolean getTall() {
        return this.isTallModel;
    }

    public void setTall(boolean par1) {
        this.isTallModel = par1;
    }

    public byte getType() {
        return this.type;
    }

    public void setType(byte b) {
        this.type = b;
    }
}
