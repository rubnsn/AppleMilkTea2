package mods.defeatedcrow.common.fluid;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

/**
 * 1.20.1: Bottle item - legacy ItemBucket removed, now simple Item with Fluid capability via FluidContMap
 * See doc/fluids/migration-guide.md
 */
public class ItemBottleCamOil extends Item {
    public ItemBottleCamOil() {
        super(new Item.Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE));
    }
    public ItemBottleCamOil(net.minecraft.world.level.block.Block block) {
        this();
    }
}
