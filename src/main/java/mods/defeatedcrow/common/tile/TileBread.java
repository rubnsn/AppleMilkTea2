package mods.defeatedcrow.common.tile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
public class TileBread extends BlockEntity {
    public TileBread(BlockPos pos, BlockState state){ super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_BREAD.get(), pos, state); }
}
