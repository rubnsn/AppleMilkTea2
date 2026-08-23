package mods.defeatedcrow.common.registry;

import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * 1.20.1 BiomeModifier registry - replaces BiomeLoadingEvent (1.16) and WorldGen_old (1.7.10).
 * Actual additions are datapack-driven: data/defeatedcrow/forge/biome_modifier/*.json
 * (type: forge:add_features, biomes: #minecraft:is_overworld / #minecraft:is_beach, step: vegetal_decoration).
 * This DeferredRegister is required so Forge knows to load the JSON modifiers; no code-registered modifiers needed.
 * See doc/worldgen/migration-guide.md:54 and WorldgenTeaTree.java / WorldgenClam.java / WorldGenYuzuTrees.java.
 * WT-B owns worldgen (plan.md:3.2) - WT0 will wire this in DCsAppleMilk.java: ModBiomeModifiers.MODIFIERS.register(modBus).
 */
public class ModBiomeModifiers {
    public static final DeferredRegister<BiomeModifier> MODIFIERS =
        DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIERS, "defeatedcrow");

    // No programmatic modifiers - all via datapack JSON. Placeholder to keep the DeferredRegister non-empty for Forge.
    // Example JSON-driven modifier: data/defeatedcrow/forge/biome_modifier/add_tea_tree.json
    private ModBiomeModifiers() {}
}
