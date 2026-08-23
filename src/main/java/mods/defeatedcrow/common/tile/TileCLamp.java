package mods.defeatedcrow.common.tile;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
public class TileCLamp extends TileHasDirection {
    public TileCLamp(net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.state.BlockState state) { super(pos, state); }


    private short rad = 0;

    @Override
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);
        this.rad = par1CompoundTag.getShort("Rad");
    }

    @Override
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);
        par1CompoundTag.putShort("Rad", this.rad);
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

    public short getAngle() {
        return this.rad;
    }

    public void setAngle(short par1) {
        this.rad = par1;
    }

    @Override
    public static void tick(Level level, BlockPos pos, BlockState state, TileCLamp be) {
        // 1.20.1 tick (was updateEntity) - see doc/tile-entities/migration-guide.md
        if (level.isClientSide) return;
        be.setChanged();
        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
    }

}
