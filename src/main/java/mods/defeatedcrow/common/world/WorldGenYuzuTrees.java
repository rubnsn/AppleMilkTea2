package mods.defeatedcrow.common.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

/**
 * 1.20.1: WorldGenYuzuTrees - legacy WorldGenAbstractTree -> Feature + BiomeModifier datapack.
 * Old 1.7.10 WorldGenAbstractTree / WorldGen_old / int x,y,z / getBlock(int) is removed.
 * Now ResourceKey<ConfiguredFeature> + PlacedFeature + BiomeModifier (Holder + datapack).
 * Actual generation is datapack-driven: data/defeatedcrow/worldgen/configured_feature/yuzu_tree.json
 * and data/defeatedcrow/worldgen/placed_feature/yuzu_tree_placed.json and
 * data/defeatedcrow/forge/biome_modifier/add_yuzu_tree.json (type: forge:add_features, step: vegetal_decoration).
 * See doc/worldgen/migration-guide.md and WorldgenTeaTree.java:17 for pattern.
 */
public class WorldGenYuzuTrees {

    public static final ResourceKey<ConfiguredFeature<?, ?>> YUZU_TREE_KEY =
        ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation("defeatedcrow", "yuzu_tree"));
    public static final ResourceKey<PlacedFeature> YUZU_TREE_PLACED_KEY =
        ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation("defeatedcrow", "yuzu_tree_placed"));

    // Legacy placement helper for manual testing / sapling grow (called from BlockYuzuSapling when restored)
    public static boolean placeYuzuTree(LevelAccessor level, BlockPos pos, RandomSource rand) {
        if (!level.isEmptyBlock(pos)) return false;
        if (!level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK) && !level.getBlockState(pos.below()).is(Blocks.DIRT)) return false;
        // In 1.20.1, actual yuzu log/leaves are ModBlocks.YUZU_LOG / YUZU_LEAVES - use oak as placeholder to avoid circular dep
        // Real feature is configured via datapack; this helper just places a sapling for quick verification
        level.setBlock(pos, Blocks.OAK_SAPLING.defaultBlockState(), 2);
        return true;
    }

    // Deprecated legacy constructor retained for compile compat if WT-A stub references it (commented out in 1.20.1)
    @Deprecated
    public WorldGenYuzuTrees(boolean flag) {}
    @Deprecated
    public WorldGenYuzuTrees(boolean flag, int minHeight, int logMeta, boolean vine) {}
}
