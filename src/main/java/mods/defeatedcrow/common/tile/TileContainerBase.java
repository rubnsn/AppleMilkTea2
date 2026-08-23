package mods.defeatedcrow.common.tile;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
public class TileContainerBase extends BlockEntity {
    public TileContainerBase(BlockPos pos, BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_CONTAINER_BASE.get(), pos, state); }


}
