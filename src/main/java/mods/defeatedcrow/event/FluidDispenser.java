package mods.defeatedcrow.event;

import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;

/**
 * 1.20.1 Fluid dispenser for oil buckets.
 * Original 1.7.10 used BlockDispenser.dispenseBehaviorRegistry + ObfuscationReflectionHelper, now DispenserBlock.registerBehavior.
 * See doc/events/migration-guide.md
 */
public class FluidDispenser {

    private FluidDispenser() {}

    public static void load() {
        // TODO: restore oil bucket dispenser when ModFluids buckets are custom BucketItem
        // Example:
        // DispenserBlock.registerBehavior(ModItems.BUCKET_VEGI_OIL.get(), new DefaultDispenseItemBehavior() {
        //   @Override protected ItemStack execute(BlockSource source, ItemStack stack) { ... place oil block ... }
        // });
    }
}
