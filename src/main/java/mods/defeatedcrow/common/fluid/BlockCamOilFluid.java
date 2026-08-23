package mods.defeatedcrow.common.fluid;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import java.util.function.Supplier;
import net.minecraft.world.level.material.Fluid;
public class BlockCamOilFluid extends LiquidBlock {
    public BlockCamOilFluid(Supplier<? extends Fluid> fluid, BlockBehaviour.Properties props){ super((FlowingFluid)fluid.get(), props); }
}
