package mods.defeatedcrow.common.fluid;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.BucketItem;
import java.util.function.Supplier;
import net.minecraft.world.level.material.Fluid;

/**
 * 1.20.1: Young alcohol bucket - now BucketItem with Fluid supplier
 * Legacy metadata (5 types) now handled via separate FluidTypes (SAKE_YOUNG etc.) in ModFluids
 */
public class ItemBucketYoungAlcohol extends BucketItem {
    public ItemBucketYoungAlcohol(Supplier<? extends Fluid> fluid) {
        super(fluid, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
    }
    public ItemBucketYoungAlcohol() {
        super(() -> net.minecraft.world.level.material.Fluids.WATER, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
    }
}
