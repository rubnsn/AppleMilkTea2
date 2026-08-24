package mods.defeatedcrow.event;

import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.core.BlockPos;

/**
 * 1.20.1 Dispenser behavior for firestarter / tea maker.
 * Original 1.7.10 used BlockDispenser.dispenseBehaviorRegistry, now DispenserBlock.registerBehavior.
 * See doc/events/migration-guide.md
 */
public class DispenserEvent {

    public static DispenserEvent instance = new DispenserEvent();
    private DispenserEvent() {}

    public void init() {
        // TODO: restore original dispenser behaviors when ModItems.FIRE_STARTER etc. are ready
        // Example 1.20.1:
        // DispenserBlock.registerBehavior(ModItems.FIRE_STARTER.get(), new DefaultDispenseItemBehavior() {
        //   @Override protected ItemStack execute(BlockSource source, ItemStack stack) {
        //     Level level = source.getLevel();
        //     BlockPos pos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
        //     if (level.isEmptyBlock(pos)) { level.setBlock(pos, net.minecraft.world.level.block.Blocks.FIRE.defaultBlockState(), 3); stack.hurtAndBreak(1, level.random, null); }
        //     return stack;
        //   }
        // });
    }

    public void registerTeaMakerEvent(ItemStack stack) {
        // TODO: tea maker dispenser behavior (was BlockTeaMakerNext + RecipeRegisterManager.teaRecipe)
    }
}
