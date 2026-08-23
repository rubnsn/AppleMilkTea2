package mods.defeatedcrow.common.tile;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileDummy extends BlockEntity {
    public TileDummy(BlockEntityType<?> type, BlockPos pos, BlockState state) { super(type, pos, state); }
    public TileDummy(BlockPos pos, BlockState state) {
        super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_DUMMY.get(), pos, state);
    }

}
