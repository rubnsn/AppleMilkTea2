package mods.defeatedcrow.common.fluid;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.world.level.material.Fluid;
public class BlockOilFluid extends LiquidBlock {
    public BlockOilFluid(Supplier<? extends Fluid> fluid, BlockBehaviour.Properties props){
        super((Supplier<? extends FlowingFluid>)(Supplier) fluid, props);
    }
    @Override public boolean isPathfindable(BlockState s, BlockGetter g, BlockPos p, net.minecraft.world.level.pathfinder.PathComputationType t){ return !getFluid().is(FluidTags.LAVA); }
    @Override public boolean skipRendering(BlockState a, BlockState b, Direction d){ return b.getFluidState().getType().isSame(getFluid()); }
    @Override public void onPlace(BlockState s, Level l, BlockPos p, BlockState o, boolean f){ if(!net.minecraftforge.fluids.FluidInteractionRegistry.canInteract(l,p)) l.scheduleTick(p, s.getFluidState().getType(), getFluid().getTickDelay(l)); }
    @Override public BlockState updateShape(BlockState s, Direction d, BlockState o, LevelAccessor a, BlockPos p, BlockPos q){ if(s.getFluidState().isSource()||o.getFluidState().isSource()) a.scheduleTick(p, s.getFluidState().getType(), getFluid().getTickDelay(a)); return super.updateShape(s,d,o,a,p,q); }
    @Override public void neighborChanged(BlockState s, Level l, BlockPos p, Block b, BlockPos q, boolean f){ if(!net.minecraftforge.fluids.FluidInteractionRegistry.canInteract(l,p)) l.scheduleTick(p, s.getFluidState().getType(), getFluid().getTickDelay(l)); }
    @Override public ItemStack pickupBlock(LevelAccessor a, BlockPos p, BlockState s){ if(s.getValue(LEVEL)==0){ a.setBlock(p, net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 11); return new ItemStack(getFluid().getBucket()); } return ItemStack.EMPTY; }
    @Override public Optional<SoundEvent> getPickupSound(){ return getFluid().getPickupSound(); }
    @Override public Optional<SoundEvent> getPickupSound(BlockState s){ return getFluid().getPickupSound(); }
}
