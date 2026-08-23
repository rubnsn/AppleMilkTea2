package mods.defeatedcrow.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

/**
 * 1.20.1: BlockDispenser.dispenseBehaviorRegistry -> DispenserBlock.registerBehavior
 * IBlockSource -> BlockSource, func_149937_b -> getValue(FACING), BlockPos
 */
public class FluidDispenser {

    private FluidDispenser() {}

    public static void load() {
        // Bucket pickup / place is now handled via DispenserBlock.registerBehavior for oils
        // Combined handler for bucket -> check for oil blocks at dispense pos
        DispenserBlock.registerBehavior(Items.BUCKET, new DefaultDispenseItemBehavior() {
            @Override
            protected ItemStack execute(BlockSource source, ItemStack stack) {
                Level level = source.getLevel();
                Direction dir = source.getBlockState().getValue(DispenserBlock.FACING);
                BlockPos pos = source.getPos().relative(dir);
                if (!level.isClientSide) {
                    if (level.getBlockState(pos).is(mods.defeatedcrow.common.registry.ModBlocks.BLOCK_CAMELLIA_OIL.get())) {
                        level.removeBlock(pos, false);
                        level.levelEvent(1009, pos, 0);
                        return new ItemStack(mods.defeatedcrow.common.DCsAppleMilk.bucketCamOil);
                    }
                    if (level.getBlockState(pos).is(mods.defeatedcrow.common.registry.ModBlocks.BLOCK_VEGI_OIL.get())) {
                        level.removeBlock(pos, false);
                        level.levelEvent(1009, pos, 0);
                        return new ItemStack(mods.defeatedcrow.common.DCsAppleMilk.bucketVegiOil);
                    }
                }
                return super.execute(source, stack);
            }
        });
    }
}
