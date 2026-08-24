package mods.defeatedcrow.common.tile;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
public class TileLargeBottle extends TileHasRemain2 {
    public TileLargeBottle(net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.state.BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_LARGE_BOTTLE.get(), pos, state); }


    private boolean side = false;

    @Override
    public void load(CompoundTag tag) {
        if (tag == null) return;
        super.load(tag);
        this.side = tag.getBoolean("Side");
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("Side", this.side);
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
