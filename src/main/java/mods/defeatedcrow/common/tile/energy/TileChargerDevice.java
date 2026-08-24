package mods.defeatedcrow.common.tile.energy;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class TileChargerDevice extends TileChargerBase {
    public TileChargerDevice(BlockPos pos, BlockState state){ super(pos, state); }

    public static void tick(Level level, BlockPos pos, BlockState state, TileChargerDevice be){ if(level.isClientSide) return; be.setChanged(); }
}
