package mods.defeatedcrow.common.block.plants;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.ItemLike;

import mods.defeatedcrow.common.registry.ModItems;

/**
 * WT-A: BlockMintCrop — 1.7.10 → mojmap 1.20.1 移行 (CropBlock 系)。
 * 4段階(0-3)成長のミント作物。
 */
public class BlockMintCrop extends CropBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);

    public BlockMintCrop(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    /**
     * 真下のブロックが耕地(バニラ or 他Modの耕地)である場合のみ植えられる。
     */
    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(Blocks.FARMLAND) || state.getBlock() instanceof FarmBlock;
    }

    /**
     * 対応するタネアイテム。ModItems.MINT_SEED (mojmap RegistryObject) を返す。
     */
    @Override
    protected @Nullable ItemLike getBaseSeedId() {
        return ModItems.MINT_SEED.get();
    }

    /*
     * 周囲のブロックから、成長しやすさを判定しているところ。
     * ミントの場合は隣接するミントが多いほど成長しやすい（バニラの逆）。
     */
    private float getGrowthRate(Level level, BlockPos pos) {
        float f = 1.0F;

        boolean flag = level.getBlockState(pos.offset(-1, 0, 0)).is(this)
            || level.getBlockState(pos.offset(1, 0, 0)).is(this);
        boolean flag1 = level.getBlockState(pos.offset(0, 0, -1)).is(this)
            || level.getBlockState(pos.offset(0, 0, 1)).is(this);
        boolean flag2 = level.getBlockState(pos.offset(-1, 0, -1)).is(this)
            || level.getBlockState(pos.offset(1, 0, -1)).is(this)
            || level.getBlockState(pos.offset(1, 0, 1)).is(this)
            || level.getBlockState(pos.offset(-1, 0, 1)).is(this);

        for (int dx = -1; dx <= 1; ++dx) {
            for (int dz = -1; dz <= 1; ++dz) {
                BlockState below = level.getBlockState(pos.offset(dx, -1, dz));
                float f1 = 0.0F;

                // 各ブロックの下のブロックが、この作物を支えられるか
                if (below.canSustainPlant(level, pos.offset(dx, -1, dz), Direction.UP, this)) {
                    f1 = 1.0F;
                }

                if (dx != 0 || dz != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        if (flag2 || flag && flag1) {
            f *= 2.0F;
        }

        return f;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) return;
        if (level.getRawBrightness(pos.above(), 0) >= 9) {
            int age = this.getAge(level.getBlockState(pos));
            if (age < this.getMaxAge()) {
                float f = this.getGrowthRate(level, pos);
                if (random.nextInt((int) (25.0F / f) + 1) == 0) {
                    level.setBlock(pos, this.getStateForAge(age + 1), 2);
                }
            }
        }
    }

    @Override
    protected int getBonemealAgeIncrease(Level level) {
        return 1;
    }
}
