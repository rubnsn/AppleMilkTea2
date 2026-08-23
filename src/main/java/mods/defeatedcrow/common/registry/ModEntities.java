package mods.defeatedcrow.common.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
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
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.EntityMelonBomb>> MELON_BOMB = ENTITIES.register("melon_bomb",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.EntityMelonBomb>of(mods.defeatedcrow.common.entity.EntityMelonBomb::new, MobCategory.MISC).sized(0.6F, 0.6F).clientTrackingRange(8).updateInterval(10).build("melon_bomb"));
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.EntitySilkyMelon>> SILKY_MELON = ENTITIES.register("silky_melon",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.EntitySilkyMelon>of(mods.defeatedcrow.common.entity.EntitySilkyMelon::new, MobCategory.MISC).sized(0.9F, 0.9F).clientTrackingRange(8).updateInterval(10).build("silky_melon"));
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.EntityKinoko>> KINOKO = ENTITIES.register("kinoko",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.EntityKinoko>of(mods.defeatedcrow.common.entity.EntityKinoko::new, MobCategory.MISC).sized(0.6F, 0.6F).clientTrackingRange(8).updateInterval(10).build("kinoko"));

    // --- WT-B: PROJECTILE (YuzuBullet, AnchorMissile) ---
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.EntityYuzuBullet>> YUZU_BULLET = ENTITIES.register("yuzu_bullet",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.EntityYuzuBullet>of(mods.defeatedcrow.common.entity.EntityYuzuBullet::new, MobCategory.MISC).sized(0.3F, 0.3F).clientTrackingRange(4).updateInterval(20).build("yuzu_bullet"));
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.EntityAnchorMissile>> ANCHOR_MISSILE = ENTITIES.register("anchor_missile",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.EntityAnchorMissile>of(mods.defeatedcrow.common.entity.EntityAnchorMissile::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(8).updateInterval(10).build("anchor_missile"));

    // --- WT-B: PLACEABLE (PlaceableCup1/2, PlaceableBowl/JP, PlaceableSteak, PlaceableCocktail/SP, PlaceableIcecream, etc. 13) ---
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.edible.PlaceableCup1>> PLACEABLE_CUP1 = ENTITIES.register("placeable_cup1",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.edible.PlaceableCup1>of(mods.defeatedcrow.common.entity.edible.PlaceableCup1::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(20).build("placeable_cup1"));
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.edible.PlaceableCup2>> PLACEABLE_CUP2 = ENTITIES.register("placeable_cup2",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.edible.PlaceableCup2>of(mods.defeatedcrow.common.entity.edible.PlaceableCup2::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(20).build("placeable_cup2"));
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.edible.PlaceableBowl>> PLACEABLE_BOWL = ENTITIES.register("placeable_bowl",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.edible.PlaceableBowl>of(mods.defeatedcrow.common.entity.edible.PlaceableBowl::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(20).build("placeable_bowl"));
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.edible.PlaceableBowlJP>> PLACEABLE_BOWL_JP = ENTITIES.register("placeable_bowl_jp",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.edible.PlaceableBowlJP>of(mods.defeatedcrow.common.entity.edible.PlaceableBowlJP::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(20).build("placeable_bowl_jp"));
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.edible.PlaceableSteak>> PLACEABLE_STEAK = ENTITIES.register("placeable_steak",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.edible.PlaceableSteak>of(mods.defeatedcrow.common.entity.edible.PlaceableSteak::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(20).build("placeable_steak"));
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.edible.PlaceableSandwich>> PLACEABLE_SANDWICH = ENTITIES.register("placeable_sandwich",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.edible.PlaceableSandwich>of(mods.defeatedcrow.common.entity.edible.PlaceableSandwich::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(20).build("placeable_sandwich"));
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.edible.PlaceableTart>> PLACEABLE_TART = ENTITIES.register("placeable_tart",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.edible.PlaceableTart>of(mods.defeatedcrow.common.entity.edible.PlaceableTart::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(20).build("placeable_tart"));
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.edible.PlaceableCocktail>> PLACEABLE_COCKTAIL = ENTITIES.register("placeable_cocktail",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.edible.PlaceableCocktail>of(mods.defeatedcrow.common.entity.edible.PlaceableCocktail::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(20).build("placeable_cocktail"));
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.edible.PlaceableCocktail2>> PLACEABLE_COCKTAIL2 = ENTITIES.register("placeable_cocktail2",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.edible.PlaceableCocktail2>of(mods.defeatedcrow.common.entity.edible.PlaceableCocktail2::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(20).build("placeable_cocktail2"));
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.edible.PlaceableCocktailSP>> PLACEABLE_COCKTAIL_SP = ENTITIES.register("placeable_cocktail_sp",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.edible.PlaceableCocktailSP>of(mods.defeatedcrow.common.entity.edible.PlaceableCocktailSP::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(20).build("placeable_cocktail_sp"));
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.edible.PlaceableIcecream>> PLACEABLE_ICECREAM = ENTITIES.register("placeable_icecream",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.edible.PlaceableIcecream>of(mods.defeatedcrow.common.entity.edible.PlaceableIcecream::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(20).build("placeable_icecream"));
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.edible.PlaceableAlcoholCup>> PLACEABLE_ALCOHOL = ENTITIES.register("placeable_alcohol",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.edible.PlaceableAlcoholCup>of(mods.defeatedcrow.common.entity.edible.PlaceableAlcoholCup::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(20).build("placeable_alcohol"));
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.edible.PlaceableBaseSoup>> PLACEABLE_BASE_SOUP = ENTITIES.register("placeable_base_soup",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.edible.PlaceableBaseSoup>of(mods.defeatedcrow.common.entity.edible.PlaceableBaseSoup::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(10).updateInterval(20).build("placeable_base_soup"));

    // --- WT-B: MAGIC (EntityAnchorMissile, EntityYuzuBullet, EntityStunEffect, EntityIllusionMobs) ---
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.dummy.EntityStunEffect>> STUN_EFFECT = ENTITIES.register("stun_effect",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.dummy.EntityStunEffect>of(mods.defeatedcrow.common.entity.dummy.EntityStunEffect::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("stun_effect"));
    public static final RegistryObject<EntityType<mods.defeatedcrow.common.entity.dummy.EntityIllusionMobs>> ILLUSION_MOBS = ENTITIES.register("illusion_mobs",
        () -> EntityType.Builder.<mods.defeatedcrow.common.entity.dummy.EntityIllusionMobs>of(mods.defeatedcrow.common.entity.dummy.EntityIllusionMobs::new, MobCategory.MISC).sized(0.6F, 1.8F).clientTrackingRange(8).updateInterval(10).build("illusion_mobs"));

    // --- WT-B: Villager professions handled via DeferredRegister<VillagerProfession> + PoiType in worldgen (see ComponentVillageCafe) ---

    private ModEntities() {}
}
