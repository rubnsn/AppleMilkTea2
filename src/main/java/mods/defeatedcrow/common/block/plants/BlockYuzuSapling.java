package mods.defeatedcrow.common.block.plants;

import javax.annotation.Nullable;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

/**
 * WT-A 1.20.1: Yuzu sapling - now extends SaplingBlock to match vanilla sapling behaviour.
 * - VoxelShape: SaplingBlock.SHAPE = Block.box(2,0,2,14,12,14) (same as vanilla oak_sapling)
 * - Collision: empty via Properties.noCollission() (BlockBehaviour) - entities pass through
 * - Behaviour: BushBlock#canSurvive + SaplingBlock#randomTick/bonemeal (STAGE 0->1 -> growTree)
 * Fix: previously used Shapes.block() for both getShape/getCollisionShape -> full-cube appearance & collision.
 */
public class BlockYuzuSapling extends SaplingBlock {

    public BlockYuzuSapling(BlockBehaviour.Properties properties) {
        super(new YuzuTreeGrower(), properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, java.util.List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
    }

    private static class YuzuTreeGrower extends AbstractTreeGrower {
        @Nullable
        @Override
        protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers) {
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation("defeatedcrow", "yuzu_tree"));
        }
    }
}
