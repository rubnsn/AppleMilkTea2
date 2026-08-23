package mods.defeatedcrow.event;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import mods.defeatedcrow.common.AMTLogger;

/**
 * 1.20.1: MovingObjectPosition -> BlockHitResult, World,int x,y,z -> Level, BlockPos
 * FillBucketEvent handling for custom oil buckets.
 */
public class BucketFillEvent {

    public static Map<Block, Item> buckets = new HashMap<Block, Item>();

    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event) {
        // FillBucketEvent in 1.20.1 has getLevel() and getTarget()
        ItemStack result = fillCustomBucket(event.getLevel(), event.getTarget());
        if (result == null) {
            return;
        }
        event.setFilledBucket(result);
        event.setResult(Event.Result.ALLOW);
    }

    private ItemStack fillCustomBucket(Level level, BlockHitResult hit) {
        if (hit == null) return null;
        BlockPos pos = hit.getBlockPos();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        Item bucket = buckets.get(block);
        if (bucket != null) {
            AMTLogger.debugInfo("bucket event : " + bucket.getDescriptionId());
        }

        if (bucket != null && state.getFluidState().isSource()) {
            level.removeBlock(pos, false);
            return new ItemStack(bucket);
        } else {
            return null;
        }
    }

    public void register() {
        // 1.20.1: Defer until ModBlocks/Fluids registered; use RegistryObject lazy
        // buckets.put(ModBlocks.BLOCK_VEGI_OIL.get(), ModItems.BUCKET_VEGI_OIL.get());
        // buckets.put(ModBlocks.BLOCK_CAMELLIA_OIL.get(), ModItems.BUCKET_CAM_OIL.get());
    }
}
