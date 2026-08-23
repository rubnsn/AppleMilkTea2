package mods.defeatedcrow.common.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddFeaturesBiomeModifier;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.levelgen.GenerationStep;
import mods.defeatedcrow.common.config.DCsConfig;

/**
 * 1.20.1: IWorldGenerator -> BiomeModifier + PlacedFeature (Holder + datapack).
 * See doc/worldgen/migration-guide.md
 * Old WorldgenTeaTree.generate(Random, chunkX,chunkZ, World, ...) is removed.
 * PlacedFeature via datapack: data/defeatedcrow/worldgen/placed_feature/tea_tree_placed.json
 * BiomeModifier via datapack: data/defeatedcrow/forge/biome_modifier/add_tea_tree.json
 * This class now only holds ResourceKeys and helper utilities; generation is datapack-driven.
 */
public class WorldgenTeaTree {

    public static final ResourceKey<ConfiguredFeature<?, ?>> TEA_TREE_KEY = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation("defeatedcrow", "tea_tree"));
    public static final ResourceKey<PlacedFeature> TEA_TREE_PLACED_KEY = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation("defeatedcrow", "tea_tree_placed"));

    // Legacy placement helper (if needed for manual worldgen testing)
    public static boolean placeTeaTree(LevelAccessor level, BlockPos pos, RandomSource rand) {
        if (DCsConfig.teaTreeGenValue <= 0) return false;
        if (!level.isEmptyBlock(pos)) return false;
        if (!level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK)) return false;
        if (level.getMaxLocalRawBrightness(pos) <= 11) return false;
        // In 1.20.1, actual block is ModBlocks.TEA_TREE; using GRASS as placeholder to avoid circular
        level.setBlock(pos, Blocks.OAK_SAPLING.defaultBlockState(), 2);
        return true;
    }

    // Codec-based BiomeModifier registration is in ModWorldgen (DeferredRegister<BiomeModifier>)
    // Example JSON: data/defeatedcrow/forge/biome_modifier/add_tea_tree.json
    // {
    //   "type": "forge:add_features",
    //   "biomes": "#minecraft:is_overworld",
    //   "features": "defeatedcrow:tea_tree_placed",
    //   "step": "vegetal_decoration"
    // }
}
