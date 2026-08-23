package mods.defeatedcrow.common.fluid;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import java.util.function.Supplier;
import net.minecraft.world.level.material.Fluid;
import mods.defeatedcrow.common.registry.ModFluids;

/**
 * 1.20.1: ItemBucket -> BucketItem(Supplier<Fluid>, Properties)
 * See doc/fluids/migration-guide.md
 */
public class ItemBucketVegiOil extends BucketItem {

    public ItemBucketVegiOil(Supplier<? extends Fluid> fluid) {
        super(fluid, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
    }

    // Legacy constructor for compat (block param removed)
    public ItemBucketVegiOil(net.minecraft.world.level.block.Block block) {
        this(() -> (Fluid)ModFluids.VEGITABLE_OIL_SOURCE.get());
    }
}
