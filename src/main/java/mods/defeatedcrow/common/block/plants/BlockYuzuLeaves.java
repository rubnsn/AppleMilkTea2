package mods.defeatedcrow.common.block.plants;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

import mods.defeatedcrow.common.registry.ModItems;

/**
 * WT-A 1.20.1: Yuzu leaves - vanilla LeavesBlock parity + old spec YUZU_AGE growth/harvest.
 * Original 1.7.10:
 *  - Meta &3: 0=post-harvest,1=natural,2=flower,3=fruit (right-click harvest when 3 -> reset 0, drops leafTea:3)
 *  - Growth: type0 rand40 ->+1, type1/2 rand15 ->+1; decay via BFS distance 4 + beginLeavesDecay
 *  - Icons: leaves_yuzu_0 for 0/1, _1 for 2, _2 for 3 (plus 4/1 easter egg)
 *  - Drops: saplingYuzu with chance 2 (fruit) /10, plus leafTea3 with 10/50; isShearable true.
 * 1.20.1:
 *  - Extend LeavesBlock (DISTANCE, PERSISTENT, WATERLOGGED) + IntegerProperty YUZU_AGE 0-3
 *  - isRandomlyTicking true if YUZU_AGE<3 (fruit growth) or vanilla decay (DISTANCE 7 && !PERSISTENT)
 *  - randomTick handles fruit growth then delegates to LeavesBlock decay.
 *  - use harvests when YUZU_AGE==3, resets to 0 and gives LEAF_YUZU.
 *  - Flammable, shearable (LeavesBlock already implements IForgeShearable), lightOpacity handled by properties.
 */
public class BlockYuzuLeaves extends LeavesBlock {

    public static final IntegerProperty YUZU_AGE = IntegerProperty.create("yuzu_age", 0, 3);

    public BlockYuzuLeaves(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(DISTANCE, 7)
            .setValue(PERSISTENT, false)
            .setValue(WATERLOGGED, false)
            .setValue(YUZU_AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(YUZU_AGE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState state = super.getStateForPlacement(ctx);
        // Player-placed leaves via BlockItem should be persistent and start at age 0 (will grow)
        // Sheared leaves with age 3 placed will still be reset to 0 - preserve age would need NBT, not required
        return state.setValue(YUZU_AGE, 0);
    }

    // Ensure worldgen leaves with age 1-3 can be placed via feature's foliage_provider weighted state

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(YUZU_AGE) < 3 || super.isRandomlyTicking(state);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Fruit growth before decay (so even persistent leaves can grow)
        int age = state.getValue(YUZU_AGE);
        if (age < 3) {
            if (age == 0) {
                if (random.nextInt(40) == 0) {
                    level.setBlock(pos, state.setValue(YUZU_AGE, age + 1), 3);
                    // after growth, don't return - still allow decay check for same tick if needed
                    state = level.getBlockState(pos);
                }
            } else {
                if (random.nextInt(15) == 0) {
                    level.setBlock(pos, state.setValue(YUZU_AGE, age + 1), 3);
                    state = level.getBlockState(pos);
                }
            }
        }
        // Vanilla decay
        super.randomTick(state, level, pos, random);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighbor, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        // Ensure YUZU_AGE is preserved through distance updates
        return super.updateShape(state, dir, neighbor, level, pos, neighborPos);
    }

    // Harvest: right-click when yuzu_age==3
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        int age = state.getValue(YUZU_AGE);
        if (age != 3) {
            return InteractionResult.PASS;
        }
        if (!level.isClientSide) {
            ItemStack ret = new ItemStack(ModItems.LEAF_YUZU.get(), 1);
            boolean added = false;
            if (player.getInventory().add(ret.copy())) {
                added = true;
                player.inventoryMenu.broadcastChanges();
            } else {
                // spawn as EntityItem
                var entity = new net.minecraft.world.entity.item.ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, ret);
                entity.setDefaultPickUpDelay();
                level.addFreshEntity(entity);
                added = true;
            }
            if (added) {
                level.setBlock(pos, state.setValue(YUZU_AGE, 0), 3);
                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.4F, 1.8F);
                // Original triggered achievement getYuzu - now advancement; ignore
                player.swing(hand, true);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    // Flammability like vanilla leaves
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return 30;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        return 60;
    }

    // Shear already handled by LeavesBlock implements IForgeShearable, drops block itself via loot table.
    // Ensure creative pick retains age? Use p_49855_ style: we keep YUZU_AGE in block state, item has no NBT, so pick will default.
}
