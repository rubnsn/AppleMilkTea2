package mods.defeatedcrow.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * 1.20.1 CreativeModeTab registry - replaces CreativeTabs (1.19.3+ Registry).
 * See doc/creative-tabs/migration-guide.md:1
 * WT-A owns displayItems. Bootstrap owns registration shell.
 */
public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "defeatedcrow");

    // --- WT-A: TABS (applemilk) ---
    public static final RegistryObject<CreativeModeTab> APPLEMILK = TABS.register("applemilk",
        () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.defeatedcrow.applemilk"))
            .icon(() -> new ItemStack(ModItems.TEA_MAKER_NEXT_ITEM.get())).displayItems((p, out) -> {
                out.accept(ModItems.ADV_PROCESSOR_ITEM.get());
                out.accept(ModItems.BASKET_ITEM.get());
                out.accept(ModItems.BAT_BOX_ITEM.get());
                out.accept(ModItems.BATTERY.get());
                out.accept(ModItems.BOWL_RACK_ITEM.get());
                out.accept(ModItems.CHALCEDONY_ITEM.get());
                out.accept(ModItems.CHALCEDONY_HAMMER.get());
                out.accept(ModItems.CHALCEDONY_KNIFE.get());
                out.accept(ModItems.CHALCEDONY_LAMP_ITEM.get());
                out.accept(ModItems.CHALCEDONY_LAMP_OP_ITEM.get());
                out.accept(ModItems.CHALCEDONY_PANEL_ITEM.get());
                out.accept(ModItems.CHALCEDONY_SHEARS.get());
                out.accept(ModItems.CHOPSTICKS.get());
                out.accept(ModItems.CHOPSTICKS_BOX_ITEM.get());
                out.accept(ModItems.CONTAINER_DOOR_I.get());
                out.accept(ModItems.CONTAINER_DOOR_W.get());
                out.accept(ModItems.CROW_DOLL_ITEM.get());
                out.accept(ModItems.DEBUG_ARM.get());
                out.accept(ModItems.DUMMY_TEPPAN.get());
                out.accept(ModItems.DUMMY_TOOLTIP.get());
                out.accept(ModItems.EMPTY_CUP_ITEM.get());
                out.accept(ModItems.EMPTY_PAN_G_ITEM.get());
                out.accept(ModItems.EVAPORATOR_ITEM.get());
                out.accept(ModItems.FILLED_SOUP_PAN_ITEM.get());
                out.accept(ModItems.FIRE_STARTER.get());
                out.accept(ModItems.FLINT_BLOCK_ITEM.get());
                out.accept(ModItems.GEL_BAT_ITEM.get());
                out.accept(ModItems.GRATER.get());
                out.accept(ModItems.HANDLE_ENGINE_ITEM.get());
                out.accept(ModItems.ICE_MAKER_ITEM.get());
                out.accept(ModItems.INCENSE_BASE_ITEM.get());
                out.accept(ModItems.MONOCLE.get());
                out.accept(ModItems.ONIX_SWORD.get());
                out.accept(ModItems.PROCESSOR_ITEM.get());
                out.accept(ModItems.RED_GEL_ITEM.get());
                out.accept(ModItems.ROTARY_DIAL_ITEM.get());
                out.accept(ModItems.TEA_MAKER_BLACK_ITEM.get());
                out.accept(ModItems.TEA_MAKER_NEXT_ITEM.get());
                out.accept(ModItems.TEPPAN_II_ITEM.get());
                out.accept(ModItems.WOOD_PANEL_ITEM.get());
                out.accept(ModItems.YUZU_BAT_ITEM.get());
                out.accept(ModItems.YUZU_FENCE_ITEM.get());
                out.accept(ModItems.YUZU_LIGHT_ITEM.get());
            }).build());

    // --- WT-A: TABS (applemilk_material) ---
    public static final RegistryObject<CreativeModeTab> APPLEMILK_MATERIAL = TABS.register("applemilk_material",
        () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.defeatedcrow.applemilk_material"))
            .icon(() -> new ItemStack(ModItems.LEAF_TEA.get())).displayItems((p, out) -> {
                out.accept(ModItems.CARBON_STICK.get());
                out.accept(ModItems.DUST_WOOD_LEGACY.get());
                out.accept(ModItems.EMPTY_WALL_MUG.get());
                out.accept(ModItems.EX_ITEMS.get());
                out.accept(ModItems.FOOD_TEA.get());
                out.accept(ModItems.ICY_CRYSTAL.get());
                out.accept(ModItems.INK_STICK.get());
                out.accept(ModItems.JAW_PLATE.get());
                out.accept(ModItems.LEAF_CAMELLIA.get());
                out.accept(ModItems.LEAF_CASSIS.get());
                out.accept(ModItems.LEAF_MINT.get());
                out.accept(ModItems.LEAF_TEA.get());
                out.accept(ModItems.LEAF_YUZU.get());
                out.accept(ModItems.MINT_SEED.get());
                out.accept(ModItems.ORE_DUST.get());
                out.accept(ModItems.SLOT_PANEL.get());
                out.accept(ModItems.WOOD_DUST.get());
            }).build());

    // --- WT-A: TABS (applemilk_food) ---
    public static final RegistryObject<CreativeModeTab> APPLEMILK_FOOD = TABS.register("applemilk_food",
        () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.defeatedcrow.applemilk_food"))
            .icon(() -> new ItemStack(ModItems.BAKED_APPLE.get())).displayItems((p, out) -> {
                out.accept(ModItems.ALCOHOL_CUP_ITEM.get());
                out.accept(ModItems.APPLE_SANDWICH.get());
                out.accept(ModItems.APPLE_TART.get());
                out.accept(ModItems.BAKED_APPLE.get());
                out.accept(ModItems.BASE_SOUP_BOWL.get());
                out.accept(ModItems.BOWL_BLOCK_ITEM.get());
                out.accept(ModItems.BOWL_JP_ITEM.get());
                out.accept(ModItems.CHOCO_BLOCK_ITEM.get());
                out.accept(ModItems.CHOCO_FRUITS.get());
                out.accept(ModItems.CLAM.get());
                out.accept(ModItems.COCKTAIL_ITEM.get());
                out.accept(ModItems.COCKTAIL2_ITEM.get());
                out.accept(ModItems.COCKTAIL_SP_ITEM.get());
                out.accept(ModItems.CONDENSED_MILK.get());
                out.accept(ModItems.FILLED_CUP_ITEM.get());
                out.accept(ModItems.FILLED_CUP2_ITEM.get());
                out.accept(ModItems.FOOD_PLATE_ITEM.get());
                out.accept(ModItems.GRATED_APPLE.get());
                out.accept(ModItems.ICE_CREAM_ITEM.get());
                out.accept(ModItems.ICY_TOFFY_APPLE.get());
                out.accept(ModItems.MINCED_FOODS.get());
                out.accept(ModItems.MOROMI.get());
                out.accept(ModItems.TOFFY_APPLE.get());
                out.accept(ModItems.WALL_MUG.get());
                out.accept(ModItems.YEAST.get());
            }).build());

    // --- WT-A: TABS (applemilk_container) ---
    public static final RegistryObject<CreativeModeTab> APPLEMILK_CONTAINER = TABS.register("applemilk_container",
        () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.defeatedcrow.applemilk_container"))
            .icon(() -> new ItemStack(ModItems.WOOD_BOX_ITEM.get())).displayItems((p, out) -> {
                out.accept(ModItems.APPLE_BOX_ITEM.get());
                out.accept(ModItems.BARREL_ITEM.get());
                out.accept(ModItems.CARDBOARD_ITEM.get());
                out.accept(ModItems.CASSIS_TREE_ITEM.get());
                out.accept(ModItems.CHARCOAL_BOX_ITEM.get());
                out.accept(ModItems.CLAM_SAND_ITEM.get());
                out.accept(ModItems.CONTAINER_SADDLE_ITEM.get());
                out.accept(ModItems.CONTAINER_WATER_BOTTLE_ITEM.get());
                out.accept(ModItems.CORDIAL.get());
                out.accept(ModItems.CROP_MINT_ITEM.get());
                out.accept(ModItems.EGG_BASKET_ITEM.get());
                out.accept(ModItems.EMPTY_BOTTLE_ITEM.get());
                out.accept(ModItems.FLOWER_POT_ITEM.get());
                out.accept(ModItems.FLOWER_VASE_ITEM.get());
                out.accept(ModItems.GUNPOWDER_CONTAINER_ITEM.get());
                out.accept(ModItems.HEDGE_ITEM.get());
                out.accept(ModItems.LARGE_BOTTLE.get());
                out.accept(ModItems.LEAVES_YUZU_ITEM.get());
                out.accept(ModItems.LOG_YUZU_ITEM.get());
                out.accept(ModItems.MELON_BOMB_ITEM.get());
                out.accept(ModItems.MOB_BLOCK_ITEM.get());
                out.accept(ModItems.MUSH_BOX_ITEM.get());
                out.accept(ModItems.SAPLING_TEA_ITEM.get());
                out.accept(ModItems.SAPLING_YUZU_ITEM.get());
                out.accept(ModItems.SILKY_MELON_ITEM.get());
                out.accept(ModItems.TEA_TREE_ITEM.get());
                out.accept(ModItems.VEGI_BAG_ITEM.get());
                out.accept(ModItems.WIPE_BOX_ITEM.get());
                out.accept(ModItems.WIPE_BOX2_ITEM.get());
                out.accept(ModItems.WOOD_BOX_ITEM.get());
            }).build());

    // --- WT-A: TABS (applemilk_magic) ---
    public static final RegistryObject<CreativeModeTab> APPLEMILK_MAGIC = TABS.register("applemilk_magic",
        () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.defeatedcrow.applemilk_magic"))
            .icon(() -> new ItemStack(ModItems.PRINCESS_CLAM.get())).displayItems((p, out) -> {
                out.accept(ModItems.ESSENTIAL_OIL.get());
                out.accept(ModItems.FOSSIL_CANNON.get());
                out.accept(ModItems.FOSSIL_SCALE.get());
                out.accept(ModItems.INCENSE_AGAR.get());
                out.accept(ModItems.INCENSE_APPLE.get());
                out.accept(ModItems.INCENSE_CLAM.get());
                out.accept(ModItems.INCENSE_FRANKINCENSE.get());
                out.accept(ModItems.INCENSE_ICE.get());
                out.accept(ModItems.INCENSE_LAVENDER.get());
                out.accept(ModItems.INCENSE_MINT.get());
                out.accept(ModItems.INCENSE_ROSE.get());
                out.accept(ModItems.INCENSE_SANDALWOOD.get());
                out.accept(ModItems.INCENSE_TEAR.get());
                out.accept(ModItems.INCENSE_VANILLA.get());
                out.accept(ModItems.INCENSE_YUZU.get());
                out.accept(ModItems.PRINCESS_CLAM.get());
                out.accept(ModItems.STRANGE_SLAG.get());
                out.accept(ModItems.YUZU_GATLING.get());
            }).build());

    private ModCreativeTabs() {}
}
