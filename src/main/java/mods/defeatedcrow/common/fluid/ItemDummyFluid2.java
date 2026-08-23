package mods.defeatedcrow.common.fluid;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * 1.20.1: Dummy fluid ItemBlock deprecated - replaced by LiquidBlock via ModFluids
 * This stub remains for compat but is not registered via DeferredRegister.
 */
public class ItemDummyFluid2 extends BlockItem {
    public ItemDummyFluid2(Block block) {
        super(block, new Item.Properties());
    }
}
