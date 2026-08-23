package mods.defeatedcrow.common.fluid;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

/**
 * 1.20.1: BlockDummyFluid2 deprecated - brewing fluids sake/beer/wine (young/aged). See BlockDummyFluid.
 */
public class BlockDummyFluid2 extends Block {

    public BlockDummyFluid2() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().strength(0.0F).noLootTable());
    }
}
