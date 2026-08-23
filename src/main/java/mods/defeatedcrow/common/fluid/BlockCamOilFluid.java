package mods.defeatedcrow.common.fluid;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;

/**
 * 1.20.1: BlockCamOilFluid -> LiquidBlock with slipperiness.
 * FluidType handles still/flow textures; BlockBehaviour handles properties.
 */
public class BlockCamOilFluid extends LiquidBlock {

    public BlockCamOilFluid(Supplier<? extends Fluid> fluid, BlockBehaviour.Properties props) {
        super(fluid, props);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity != null) {
            // very slippery
            if (entity.getDeltaMovement().horizontalDistanceSqr() < 400) {
                entity.setDeltaMovement(entity.getDeltaMovement().multiply(1.1D, 1.0D, 1.1D));
            }
        }
    }
}
