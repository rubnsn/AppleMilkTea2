package mods.defeatedcrow.common.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 1.20.1 EntityType registry — replaces EntityRegistry.registerModEntity.
 * See doc/entities/migration-guide.md:1
 * WT-B owns all 20 ModEntity + Placeable 13.
 */
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "defeatedcrow");

    // --- WT-B: THROWN (EntityMelonBomb, EntitySilkyMelon, EntityKinoko) ---
    // --- WT-B: PLACEABLE (PlaceableCup1/2, PlaceableBowl/JP, PlaceableSteak, PlaceableCocktail/SP, PlaceableIcecream, etc. 13) ---
    // --- WT-B: MAGIC (EntityAnchorMissile, EntityYuzuBullet, EntityStunEffect, EntityIllusionMobs) ---

    private ModEntities() {}
}
