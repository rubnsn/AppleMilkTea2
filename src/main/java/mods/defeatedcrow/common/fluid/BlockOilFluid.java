package mods.defeatedcrow.common.fluid;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import java.util.function.Supplier;
import net.minecraft.world.level.material.Fluid;
public class BlockOilFluid extends LiquidBlock {
    private final Supplier<? extends Fluid> fluidSupplier;
    public BlockOilFluid(Supplier<? extends Fluid> fluid, BlockBehaviour.Properties props){
        super((FlowingFluid)net.minecraft.world.level.material.Fluids.FLOWING_WATER, props);
        this.fluidSupplier = fluid;
    }
    @Override
    public net.minecraft.world.level.material.FlowingFluid getFluid() {
        return (FlowingFluid) fluidSupplier.get();
    }
}
