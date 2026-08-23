package mods.defeatedcrow.common.tile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
public class TileCPanel extends BlockEntity {
    public TileCPanel(BlockPos pos, BlockState state){ super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_C_PANEL.get(), pos, state); }
}
