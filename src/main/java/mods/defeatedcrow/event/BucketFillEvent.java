package mods.defeatedcrow.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import mods.defeatedcrow.common.registry.ModBlocks;

/**
 * 1.20.1 FillBucketEvent - custom oil buckets.
 * Original 1.7.10 used World+MovingObjectPosition+blockX/Y/Z, now Level+BlockHitResult+BlockPos.
 * See doc/events/migration-guide.md:35
 * TODO: restore custom bucket items (bucketVegiOil etc.) when ModItems adds them; for now uses vanilla bucket placeholder.
 */
public class BucketFillEvent {

    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event) {
        Level level = event.getLevel();
        var hit = event.getTarget();
        if (level == null || hit == null || !(hit instanceof BlockHitResult target)) return;
        BlockPos pos = target.getBlockPos();
        var state = level.getBlockState(pos);
        ItemStack empty = event.getEmptyBucket();
        if (empty.isEmpty() || !empty.is(net.minecraft.world.item.Items.BUCKET)) return;

        if (state.is(ModBlocks.BLOCK_VEGI_OIL.get())) {
            level.setBlock(pos, net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 3);
            ItemStack filled = new ItemStack(net.minecraft.world.item.Items.BUCKET);
            event.setFilledBucket(filled);
            event.setResult(net.minecraftforge.eventbus.api.Event.Result.ALLOW);
        } else if (state.is(ModBlocks.BLOCK_CAMELLIA_OIL.get())) {
            level.setBlock(pos, net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 3);
            ItemStack filled = new ItemStack(net.minecraft.world.item.Items.BUCKET);
            event.setFilledBucket(filled);
            event.setResult(net.minecraftforge.eventbus.api.Event.Result.ALLOW);
        }
    }
}
