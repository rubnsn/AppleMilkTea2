package mods.defeatedcrow.common.tile;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
public class TileLargeBottle extends TileHasRemain2 {
    public TileLargeBottle(net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.state.BlockState state) { super(pos, state); }


    private boolean side = false;

    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);
        this.side = par1CompoundTag.getBoolean("Side");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);
        par1CompoundTag.putBoolean("Side", this.side);
    }

    public boolean getSide() {
        return this.side;
    }

    public void setSide(boolean flag) {
        this.side = flag;
    }

    
    public short getRemainClient() {
        int r = this.getRemainShort();
        r = (r & 7);
        return (short) r;
    }

}
