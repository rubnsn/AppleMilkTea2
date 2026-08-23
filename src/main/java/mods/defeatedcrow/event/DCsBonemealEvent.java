package mods.defeatedcrow.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.block.plants.BlockCassisTree;
import mods.defeatedcrow.common.block.plants.BlockMintCrop;
import mods.defeatedcrow.common.block.plants.BlockSaplingTea;
import mods.defeatedcrow.common.block.plants.BlockTeaTree;
import mods.defeatedcrow.common.block.plants.BlockYuzuSapling;

/**
 * 1.20.1: BonemealEvent now uses Level + BlockPos + BlockState (no int x,y,z)
 * See doc/events/migration-guide.md
 */
public class DCsBonemealEvent {

    @SubscribeEvent
    public void useBoneMeal(BonemealEvent event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        Block block = level.getBlockState(pos).getBlock();
        if (block == DCsAppleMilk.cropMint) {
            if (((BlockMintCrop) DCsAppleMilk.cropMint).isValidBonemealTarget(level, pos, level.getBlockState(pos), false)) {
                event.setResult(Result.ALLOW);
            }
        } else if (block == DCsAppleMilk.cassisTree) {
            if (((BlockCassisTree) DCsAppleMilk.cassisTree).isValidBonemealTarget(level, pos, level.getBlockState(pos), false)) {
                event.setResult(Result.ALLOW);
            }
        } else if (block == DCsAppleMilk.teaTree) {
            if (((BlockTeaTree) DCsAppleMilk.teaTree).isValidBonemealTarget(level, pos, level.getBlockState(pos), false)) {
                event.setResult(Result.ALLOW);
            }
        } else if (block == DCsAppleMilk.saplingTea) {
            if (((BlockSaplingTea) DCsAppleMilk.saplingTea).isValidBonemealTarget(level, pos, level.getBlockState(pos), false)) {
                event.setResult(Result.ALLOW);
            }
        } else if (block == DCsAppleMilk.saplingYuzu) {
            if (((BlockYuzuSapling) DCsAppleMilk.saplingYuzu).isValidBonemealTarget(level, pos, level.getBlockState(pos), false)) {
                event.setResult(Result.ALLOW);
            }
        }
    }

}
