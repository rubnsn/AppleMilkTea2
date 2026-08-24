package mods.defeatedcrow.common.block.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

/**
 * WT-A 1.20.1: Yuzu log -> vanilla RotatedPillarBlock parity.
 * Original 1.7.10 extended BlockRotatedPillar with canSustainLeaves/isWood and breakBlock decay scan (4 radius).
 * 1.20.1: RotatedPillarBlock handles AXIS property, blockstates axis=x/y/z, vanllia leaves decay via DISTANCE.
 * breakBlock scan is no longer needed - LeavesBlock#updateDistance handles via getOptionalDistanceAt(BlockTags.LOGS).
 * Properties: use vanilla log helper -> instrument BASS, strength 2.0, sound WOOD, ignitedByLava (see ModBlocks).
 * This class only adds flammability + wood-type hooks for Forge tag compatibility.
 */
public class BlockYuzuLog extends RotatedPillarBlock {

    public BlockYuzuLog(BlockBehaviour.Properties properties) {
        super(properties);
    }

    // Forge wood hooks
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return 5;
    }

    // For stripping via axe: if we add a stripped variant later, override getToolModifiedState here.
    // Current single log has no stripped form, so default behaviour.
}
