package mods.defeatedcrow.common.world;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import mods.defeatedcrow.common.config.DCsConfig;

/**
 * 1.20.1: WorldgenClam (beach hamaguri generation) -> Feature + BiomeModifier (Holder)
 * Old IWorldGenerator.generate removed. Now datapack PlacedFeature at beach biomes.
 * See doc/worldgen/migration-guide.md
 */
public class WorldgenClam {

    public static final ResourceKey<ConfiguredFeature<?, ?>> CLAM_KEY = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation("defeatedcrow", "clam"));
    public static final ResourceKey<PlacedFeature> CLAM_PLACED_KEY = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation("defeatedcrow", "clam_placed"));

    public static boolean placeClam(LevelAccessor level, BlockPos pos, RandomSource rand) {
        if (level.getBlockState(pos.above()).getFluidState().isSource() // water above
            && (level.getBlockState(pos).is(Blocks.SAND) || level.getBlockState(pos).is(Blocks.DIRT))) {
            // actual clam block is ModBlocks.CLAM_SAND; placeholder
            level.setBlock(pos, Blocks.SAND.defaultBlockState(), 2);
            return true;
        }
        return false;
    }

    // Datapack:
    // data/defeatedcrow/worldgen/placed_feature/clam_placed.json -> beach placement
    // data/defeatedcrow/forge/biome_modifier/add_clam.json -> {"type":"forge:add_features","biomes":"#minecraft:is_beach","features":"defeatedcrow:clam_placed","step":"vegetal_decoration"}
}
