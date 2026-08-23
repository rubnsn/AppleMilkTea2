package mods.defeatedcrow.common.tile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
public class TileCrowDoll extends BlockEntity {
    public TileCrowDoll(BlockPos pos, BlockState state){ super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_CROW_DOLL.get(), pos, state); }
}
