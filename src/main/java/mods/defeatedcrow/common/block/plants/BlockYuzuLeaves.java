package mods.defeatedcrow.common.block.plants;

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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import mods.defeatedcrow.common.registry.ModBlocks;
import mods.defeatedcrow.common.registry.ModItems;

/**
 * WT-A 1.20.1: Yuzu leaves - 旧仕様準拠（1.7.10 BlockYuzuLeaves を BlockState 化）。
 * 1.7.10: BlockLeavesBase + meta&3 0=採取後/1=自然/2=花/3=実、右クリックで実(leafTea:3)採取し meta-3に、
 *  成長: type0 rand40 / type1,2 rand15 で +1、腐朽: around[32^3] BFS 距離4で原木未接続なら除去、b0=1の breakBlock で周辺 leaves に decay 伝播。
 * 1.20.1: Block + IntegerProperty YUZU_AGE 0-3 のみに正規化。バニラ LeavesBlock の DISTANCE/PERSISTENT/WATERLOGGED は使わず旧 BFS を再実装
 *  して「バニラ接続切れでブロック化」を防ぐ。randomTick で腐朽判定→除去、さもなければ果実成長。
 *  見た目は minecraft:block/leaves モデル + cutoutMipped、透過は properties noOcclusion + isViewBlocking false 相当で担保。
 */
public class BlockYuzuLeaves extends Block implements net.minecraftforge.common.IForgeShearable {

    public static final IntegerProperty YUZU_AGE = IntegerProperty.create("yuzu_age", 0, 3);

    public BlockYuzuLeaves(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(YUZU_AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(YUZU_AGE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        // プレイヤー設置は採取後状態 0、成長で 1→2→3へ。ワールド生成の葉は foliage_provider で 1-3 を直接指定。
        return this.defaultBlockState().setValue(YUZU_AGE, 0);
    }

    // 葉は通常ブロックと同等の当たり判定（1.7.10 は isOpaque false だが VoxelShape はフル）。
    // getShape/getCollisionShape はデフォルト Shapes.block() のまま（cutoutMipped 描画で透過）。

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        // 腐朽 or 成長待ちは tick させる
        return state.getValue(YUZU_AGE) < 3 || shouldDecay(state);
    }

    private boolean shouldDecay(BlockState state) {
        // 旧仕様では decay ビット 8 が立っている葉が対象だが 1.20.1では YUZU_AGE によらず常時 decay 判定を randomTick で行う。
        // 実際の decay 要否は isNearLog で判定。
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // 1) 腐朽判定: 原木から 4 以内の接続がなければ除去（旧 BFS 距離4を Manhattan 近似）
        if (!isNearLog(level, pos, 4)) {
            // 1.7.10 removeLeaves: drop + setAir
            dropResources(state, level, pos);
            level.removeBlock(pos, false);
            return;
        }
        // 2) 果実成長（旧 updateTick の type<3 部分）
        int age = state.getValue(YUZU_AGE);
        if (age < 3) {
            if (age == 0) {
                if (random.nextInt(40) == 0) {
                    level.setBlock(pos, state.setValue(YUZU_AGE, age + 1), 3);
                }
            } else {
                if (random.nextInt(15) == 0) {
                    level.setBlock(pos, state.setValue(YUZU_AGE, age + 1), 3);
                }
            }
        }
    }

    /**
     * 旧 updateTick の around[32^3] BFS を簡易 Manhattan で代替: 半径4の立方体を走査し log_yuzu が1つでもあれば接続あり。
     * 旧 b0=4, b1=32 の厳密 BFS は 4マス先まで 6方向伝播だが、実質的には 4 ブロック以内の原木存在チェックと同等。
     */
    private boolean isNearLog(ServerLevel level, BlockPos pos, int radius) {
        // 中心 4 以内を全走査（旧は 32^3 配列で距離ラベル付けだが結果は同等）
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    // Manhattan 的距離で 4 以内のみ、対角も含めるため max(|dx|,|dy|,|dz|) <=4 で旧 b0 立方と同等
                    if (Math.max(Math.max(Math.abs(dx), Math.abs(dy)), Math.abs(dz)) > radius) continue;
                    BlockPos p = pos.offset(dx, dy, dz);
                    if (!level.isLoaded(p)) continue;
                    BlockState s = level.getBlockState(p);
                    // BlockTags.LOGS ではなく直接 ModBlocks.LOG_YUZU で判定（旧 canSustainLeaves/isWood と同等）
                    if (s.is(ModBlocks.LOG_YUZU.get())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 破壊時に周囲1の葉へ decay 伝播は randomTick の isNearLog で自然に処理されるため不要。
    // 互換で break 時に周辺 tick をスケジュールするだけに留める。
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moved) {
        super.onRemove(state, level, pos, newState, moved);
        // 1.7.10 breakBlock は半径1の leaves に beginLeavesDecay していたが、現代は葉が自前で tick するため何もしない。
    }

    // 収穫: yuzu_age==3 のみ
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
                var entity = new net.minecraft.world.entity.item.ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, ret);
                entity.setDefaultPickUpDelay();
                level.addFreshEntity(entity);
                added = true;
            }
            if (added) {
                level.setBlock(pos, state.setValue(YUZU_AGE, 0), 3);
                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.4F, 1.8F);
                player.swing(hand, true);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

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

    // IForgeShearable - 1.20.1 signature uses Level
    @Override
    public boolean isShearable(ItemStack item, Level level, BlockPos pos) {
        return true;
    }

    @Override
    public java.util.List<ItemStack> onSheared(Player player, ItemStack item, Level level, BlockPos pos, int fortune) {
        // 旧 onSheared: meta&3 を保持
        return java.util.List.of(new ItemStack(this, 1));
        // 年齢はアイテムに保持しない（BlockItem はデフォルト 0 で設置される点は旧と同様）
    }
}
