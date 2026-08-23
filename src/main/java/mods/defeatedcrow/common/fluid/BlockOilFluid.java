package mods.defeatedcrow.common.fluid;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;

/**
 * 1.20.1: ClassicFluidBlock -> LiquidBlock
 * Vegitable oil fluid block. No Icon, textures via FluidType stillTexture.
 * See doc/fluids/migration-guide.md
 */
public class BlockOilFluid extends LiquidBlock {

    public BlockOilFluid(Supplier<? extends Fluid> fluid, BlockBehaviour.Properties props) {
        super(fluid, props);
    }

    @Override
    public boolean canBeReplaced(BlockState state, net.minecraft.world.level.material.Fluid fluid) {
        return false;
    }
}
