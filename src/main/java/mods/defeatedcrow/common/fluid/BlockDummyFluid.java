package mods.defeatedcrow.common.fluid;

import net.minecraftforge.fluids.FluidStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

/**
 * 1.20.1: BlockDummyFluid deprecated - brewing 5 fluids (shothu/whiskey/brandy/rum/vodka) no longer need dummy block.
 * Brewing fluids are stored in TileBrewingBarrel DCsTank as FluidStack (barrel). FlowingFluid via ModFluids used directly.
 * This stub remains for legacy world compat only, renders as invisible.
 */
public class BlockDummyFluid extends Block {

    public BlockDummyFluid() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().strength(0.0F).noLootTable());
    }
}