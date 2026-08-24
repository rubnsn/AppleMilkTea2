package mods.defeatedcrow.common.block.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import mods.defeatedcrow.common.registry.ModBlocks;

/**
 * WT-A 1.20.1: Tea sapling - old spec single-block growth.
 * Original 1.7.10: meta 0=tea,1=cassis,2=camellia -> grew directly into BlockTeaTree/BlockCassisTree (single block, not tree feature).
 * 1.20.1: Extend BushBlock + BonemealableBlock, keep vanilla sapling VoxelShape (2,0,2,14,12,14), no collision.
 * randomTick / bonemeal both set block to TEA_TREE (meta統合: 当面 tea_tree 一本。cassis/camellia 分離は将来 Property 追加で対応可能)。
 * canSurvive via BushBlock/mayPlaceOn (DIRT/FARMLAND tag) + updateShape.
 */
public class BlockSaplingTea extends BushBlock implements BonemealableBlock {

    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

    public BlockSaplingTea(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return SHAPE;
    }

    // BushBlock already returns empty collision via Properties.noCollission(), but ensure explicit
    // (vanilla SaplingBlock inherits same - no override of getCollisionShape, relies on noCollission).
    // No override needed - rely on Block#hasCollision = false from Properties.noCollission()

    // --- Bush survival ---
    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        // 1.7.10 original: Blocks.grass/dirt/farmland or Material.grass; modern via #minecraft:dirt + farmland
        return super.mayPlaceOn(state, level, pos);
    }

    // --- Growth: vanilla sapling parity light>=9 && nextInt(7)==0 -> grow, but tea is single block not tree ---
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) return;
        if (level.getMaxLocalRawBrightness(pos.above()) >= 9 && random.nextInt(7) == 0) {
            this.advanceSingleBlock(level, pos, state, random);
        }
        // BushBlock#updateShape will handle canSurvive -> AIR if soil removed (via tick of bush? handled in super.randomTick? no, via updateShape)
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    private void advanceSingleBlock(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        // Direct replacement with TEA_TREE (original meta 0 case). Preserve original behavior: fertilize with light>11 earlier, but use sapling parity 9.
        // No TerrainGen event here (1.7.10 tea sapling didn't fire it; only yuzu did).
        level.setBlock(pos, ModBlocks.TEA_TREE.get().defaultBlockState(), 3);
    }

    // --- BonemealableBlock ---
    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true; // always succeeds (original fertilize always true for tea)
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        this.advanceSingleBlock(level, pos, state, random);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, java.util.List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
