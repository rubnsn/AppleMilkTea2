package mods.defeatedcrow.event;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.Event;

/**
 * 1.20.1 BonemealEvent - tea/mint/cassis/yuzu fertilize.
 * Original 1.7.10 used Block+World+int x,y,z, now Level+BlockPos+BlockState+ItemStack.
 * See doc/events/migration-guide.md:45
 */
public class DCsBonemealEvent {

    @SubscribeEvent
    public void useBoneMeal(BonemealEvent event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        if (level == null || pos == null) return;
        BlockState state = level.getBlockState(pos);
        // TODO: restore original fertilize logic for mint/cassis/tea/yuzu when those blocks implement BonemealableBlock
        // Example (1.20.1):
        // if (state.is(ModBlocks.CROP_MINT.get()) && level instanceof ServerLevel sl) {
        //   if (((BonemealableBlock)state.getBlock()).isValidBonemealTarget(level, pos, state)) {
        //     ((BonemealableBlock)state.getBlock()).performBonemeal(sl, level.random, pos, state);
        //     event.setResult(Event.Result.ALLOW);
        //   }
        // }
    }
}
