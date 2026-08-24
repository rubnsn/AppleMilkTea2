package mods.defeatedcrow.common.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import mods.defeatedcrow.client.model.item.TESRBlockItem;

// WT-A item imports
import mods.defeatedcrow.common.item.ItemChalcedonyHammer;
import mods.defeatedcrow.common.item.ItemChalcedonyKnife;
import mods.defeatedcrow.common.item.ItemChalcedonyMonocle;
import mods.defeatedcrow.common.item.ItemChalcedonyShears;
import mods.defeatedcrow.common.item.ItemChopsticks;
import mods.defeatedcrow.common.item.ItemContainerDoor;
import mods.defeatedcrow.common.item.ItemDummyForTeppan;
import mods.defeatedcrow.common.item.ItemDummyForTooltip;
import mods.defeatedcrow.common.item.ItemEmptyWallMug;
import mods.defeatedcrow.common.item.ItemEXItem;
import mods.defeatedcrow.common.item.ItemFireStarter;
import mods.defeatedcrow.common.item.ItemFoodTea;
import mods.defeatedcrow.common.item.ItemInkStick;
import mods.defeatedcrow.common.item.ItemLeafTea;
import mods.defeatedcrow.common.item.ItemMintSeed;
import mods.defeatedcrow.common.item.ItemOnixSword;
import mods.defeatedcrow.common.item.ItemCarbonStick;
import mods.defeatedcrow.common.item.ItemOreDust;
import mods.defeatedcrow.common.item.appliance.ItemBattery;
import mods.defeatedcrow.common.item.appliance.ItemGrater;
import mods.defeatedcrow.common.item.appliance.ItemIcyCrystal;
import mods.defeatedcrow.common.item.appliance.ItemJawplate;
import mods.defeatedcrow.common.item.appliance.ItemSlotPanel;
import mods.defeatedcrow.common.item.appliance.ItemYuzuGatling;
import mods.defeatedcrow.common.item.edible.EdibleEntityItem;
import mods.defeatedcrow.common.item.edible.EdibleEntityItem2;
import mods.defeatedcrow.common.item.edible.ItemAppleSandwich;
import mods.defeatedcrow.common.item.edible.ItemAppleTart;
import mods.defeatedcrow.common.item.edible.ItemBakedApple;
import mods.defeatedcrow.common.item.edible.ItemBaseSoupBowl;
import mods.defeatedcrow.common.item.edible.ItemChocoFruits;
import mods.defeatedcrow.common.item.edible.ItemClam;
import mods.defeatedcrow.common.item.edible.ItemCondensedMilk;
import mods.defeatedcrow.common.item.edible.ItemGratedApple;
import mods.defeatedcrow.common.item.edible.ItemIcyToffyApple;
import mods.defeatedcrow.common.item.edible.ItemMincedFoods;
import mods.defeatedcrow.common.item.edible.ItemMoromi;
import mods.defeatedcrow.common.item.edible.ItemToffyApple;
import mods.defeatedcrow.common.item.edible.ItemWallMug;
import mods.defeatedcrow.common.item.edible.ItemYeast;
import mods.defeatedcrow.common.item.magic.ItemDebugArm;
import mods.defeatedcrow.common.item.magic.ItemEssentialOil;
import mods.defeatedcrow.common.item.magic.ItemFossilCannon;
import mods.defeatedcrow.common.item.magic.ItemFossilScale;
import mods.defeatedcrow.common.item.magic.ItemIncenseAgar;
import mods.defeatedcrow.common.item.magic.ItemIncenseApple;
import mods.defeatedcrow.common.item.magic.ItemIncenseClam;
import mods.defeatedcrow.common.item.magic.ItemIncenseFrankincense;
import mods.defeatedcrow.common.item.magic.ItemIncenseIce;
import mods.defeatedcrow.common.item.magic.ItemIncenseLavender;
import mods.defeatedcrow.common.item.magic.ItemIncenseMint;
import mods.defeatedcrow.common.item.magic.ItemIncenseRose;
import mods.defeatedcrow.common.item.magic.ItemIncenseSandalwood;
import mods.defeatedcrow.common.item.magic.ItemIncenseTear;
import mods.defeatedcrow.common.item.magic.ItemIncenseVanilla;
import mods.defeatedcrow.common.item.magic.ItemIncenseYuzu;
import mods.defeatedcrow.common.item.magic.ItemPrincessClam;
import mods.defeatedcrow.common.item.magic.ItemStrangeSlag;
import mods.defeatedcrow.common.item.magic.ItemWoodDust;
import mods.defeatedcrow.common.block.brewing.ItemCordial;
import mods.defeatedcrow.common.block.brewing.ItemEmptyBottle;
import mods.defeatedcrow.common.block.brewing.ItemLargeBottle;

/**
 * 1.20.1 Item registry - DeferredRegister + Item.Properties.
 * Bootstrap-owned skeleton. See doc/items/migration-guide.md:33
 * NBT維持: DataComponentsは1.20.5+なので1.20.1では導入しない (doc/items/migration-guide.md:220).
 */
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "defeatedcrow");

    // --- WT-A: FOOD/INGREDIENT (bakedApple, appleTart, toffyApple, leafTea splits, gratedApple, mincedFoods, yeast, moromi, etc.) ---
    public static final RegistryObject<Item> BAKED_APPLE = ITEMS.register("baked_apple",
        () -> new ItemBakedApple(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(0.6F).build())));
    public static final RegistryObject<Item> APPLE_TART = ITEMS.register("apple_tart",
        () -> new ItemAppleTart(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(0.7F).build())));
    public static final RegistryObject<Item> TOFFY_APPLE = ITEMS.register("toffy_apple",
        () -> new ItemToffyApple(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(0.5F).build())));
    public static final RegistryObject<Item> ICY_TOFFY_APPLE = ITEMS.register("icy_toffy_apple",
        () -> new ItemIcyToffyApple(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(0.7F).build())));
    public static final RegistryObject<Item> APPLE_SANDWICH = ITEMS.register("apple_sandwich",
        () -> new ItemAppleSandwich(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(0.6F).build())));
    public static final RegistryObject<Item> CHOCO_FRUITS = ITEMS.register("choco_fruits",
        () -> new ItemChocoFruits(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(0.5F).build())));
    public static final RegistryObject<Item> GRATED_APPLE = ITEMS.register("grated_apple",
        () -> new ItemGratedApple(new Item.Properties()));
    public static final RegistryObject<Item> MINCED_FOODS = ITEMS.register("minced_foods",
        () -> new ItemMincedFoods(new Item.Properties()));
    public static final RegistryObject<Item> FOOD_TEA = ITEMS.register("food_tea",
        () -> new ItemFoodTea(new Item.Properties()));
    public static final RegistryObject<Item> CONDENSED_MILK = ITEMS.register("condensed_milk",
        () -> new ItemCondensedMilk(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build())));
    public static final RegistryObject<Item> CLAM = ITEMS.register("clam",
        () -> new ItemClam(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).build())));
    public static final RegistryObject<Item> YEAST = ITEMS.register("yeast",
        () -> new ItemYeast(new Item.Properties()));
    public static final RegistryObject<Item> MOROMI = ITEMS.register("moromi",
        () -> new ItemMoromi(new Item.Properties()));
    public static final RegistryObject<Item> BASE_SOUP_BOWL = ITEMS.register("base_soup_bowl",
        () -> new ItemBaseSoupBowl(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> WALL_MUG = ITEMS.register("wall_mug",
        () -> new ItemWallMug(new Item.Properties().stacksTo(1)));

    // leafTea splits - 1.13+個別Item化 (doc/items/migration-guide.md:60)
    public static final RegistryObject<Item> LEAF_TEA = ITEMS.register("leaf_tea",
        () -> new ItemLeafTea(new Item.Properties()));
    public static final RegistryObject<Item> LEAF_MINT = ITEMS.register("leaf_mint",
        () -> new ItemLeafTea(new Item.Properties()));
    public static final RegistryObject<Item> LEAF_CASSIS = ITEMS.register("leaf_cassis",
        () -> new ItemLeafTea(new Item.Properties()));
    public static final RegistryObject<Item> LEAF_YUZU = ITEMS.register("leaf_yuzu",
        () -> new ItemLeafTea(new Item.Properties()));
    public static final RegistryObject<Item> LEAF_CAMELLIA = ITEMS.register("leaf_camellia",
        () -> new ItemLeafTea(new Item.Properties()));

    public static final RegistryObject<Item> MINT_SEED = ITEMS.register("mint_seed",
        () -> new ItemMintSeed(new Item.Properties()));

    // --- WT-A: MATERIALS (EXItems, inkStick, dustWood, etc.) ---
    public static final RegistryObject<Item> EX_ITEMS = ITEMS.register("ex_items",
        () -> new ItemEXItem(new Item.Properties()));
    public static final RegistryObject<Item> INK_STICK = ITEMS.register("ink_stick",
        () -> new ItemInkStick(new Item.Properties()));
    public static final RegistryObject<Item> ORE_DUST = ITEMS.register("ore_dust",
        () -> new ItemOreDust(new Item.Properties()));
    public static final RegistryObject<Item> WOOD_DUST = ITEMS.register("wood_dust",
        () -> new ItemWoodDust(new Item.Properties()));
    public static final RegistryObject<Item> ESSENTIAL_OIL = ITEMS.register("essential_oil",
        () -> new ItemEssentialOil(new Item.Properties()));
    public static final RegistryObject<Item> STRANGE_SLAG = ITEMS.register("strange_slag",
        () -> new ItemStrangeSlag(new Item.Properties()));
    public static final RegistryObject<Item> FOSSIL_SCALE = ITEMS.register("fossil_scale",
        () -> new ItemFossilScale(new Item.Properties()));
    public static final RegistryObject<Item> EMPTY_WALL_MUG = ITEMS.register("empty_wall_mug",
        () -> new ItemEmptyWallMug(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> CARBON_STICK = ITEMS.register("carbon_stick",
        () -> new ItemCarbonStick(new Item.Properties()));
    public static final RegistryObject<Item> ICY_CRYSTAL = ITEMS.register("icy_crystal",
        () -> new ItemIcyCrystal(new Item.Properties()));
    public static final RegistryObject<Item> SLOT_PANEL = ITEMS.register("slot_panel",
        () -> new ItemSlotPanel(new Item.Properties()));
    public static final RegistryObject<Item> JAW_PLATE = ITEMS.register("jaw_plate",
        () -> new ItemJawplate(new Item.Properties()));
    public static final RegistryObject<Item> DUST_WOOD_LEGACY = ITEMS.register("dust_wood",
        () -> new ItemWoodDust(new Item.Properties()));

    // --- WT-A: TOOLS (chalcedonyKnife, firestarter, chalcedonyHammer, monocle, onixSword, pruningShears, chopsticks, yuzuGatling, fossilCannon, eightEyesArm) ---
    public static final RegistryObject<Item> CHALCEDONY_KNIFE = ITEMS.register("chalcedony_knife",
        () -> new ItemChalcedonyKnife(new Item.Properties().durability(128)));
    public static final RegistryObject<Item> CHALCEDONY_HAMMER = ITEMS.register("chalcedony_hammer",
        () -> new ItemChalcedonyHammer(new Item.Properties().durability(128)));
    public static final RegistryObject<Item> CHALCEDONY_SHEARS = ITEMS.register("chalcedony_shears",
        () -> new ItemChalcedonyShears(new Item.Properties().durability(128)));
    public static final RegistryObject<Item> MONOCLE = ITEMS.register("monocle",
        () -> new ItemChalcedonyMonocle(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ONIX_SWORD = ITEMS.register("onix_sword",
        () -> new ItemOnixSword(new Item.Properties().durability(128)));
    public static final RegistryObject<Item> FIRE_STARTER = ITEMS.register("fire_starter",
        () -> new ItemFireStarter(new Item.Properties().durability(64)));
    public static final RegistryObject<Item> CHOPSTICKS = ITEMS.register("chopsticks",
        () -> new ItemChopsticks(new Item.Properties()));
    public static final RegistryObject<Item> GRATER = ITEMS.register("grater",
        () -> new ItemGrater(new Item.Properties().durability(128)));
    public static final RegistryObject<Item> YUZU_GATLING = ITEMS.register("yuzu_gatling",
        () -> new ItemYuzuGatling(new Item.Properties().durability(256)));
    public static final RegistryObject<Item> FOSSIL_CANNON = ITEMS.register("fossil_cannon",
        () -> new ItemFossilCannon(new Item.Properties().durability(256)));
    public static final RegistryObject<Item> DEBUG_ARM = ITEMS.register("debug_arm",
        () -> new ItemDebugArm(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BATTERY = ITEMS.register("battery",
        () -> new ItemBattery(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CONTAINER_DOOR_W = ITEMS.register("container_door_w",
        () -> new ItemContainerDoor(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CONTAINER_DOOR_I = ITEMS.register("container_door_i",
        () -> new ItemContainerDoor(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> DUMMY_TOOLTIP = ITEMS.register("dummy_tooltip",
        () -> new ItemDummyForTooltip(new Item.Properties()));
    public static final RegistryObject<Item> DUMMY_TEPPAN = ITEMS.register("dummy_teppan",
        () -> new ItemDummyForTeppan(new Item.Properties()));

    // --- WT-A: BLOCK-ITEMS (teaMakerNext, woodBox, etc. - BlockItem wrappers) ---
    // Fix inventory broken: tea_maker world uses ISBRH JSON (frame+glass) + BER contents; inventory should use block JSON (ISBRH-faithful) not BEWLR contents-only (was tiny 6x7x6 box via ModelMakerNext)
    public static final RegistryObject<Item> TEA_MAKER_NEXT_ITEM = ITEMS.register("tea_maker_next",
        () -> new BlockItem(ModBlocks.TEA_MAKER_NEXT.get(), new Item.Properties()));
    public static final RegistryObject<Item> TEA_MAKER_BLACK_ITEM = ITEMS.register("tea_maker_black",
        () -> new BlockItem(ModBlocks.TEA_MAKER_BLACK.get(), new Item.Properties()));
    public static final RegistryObject<Item> EMPTY_CUP_ITEM = ITEMS.register("empty_cup",
        () -> new BlockItem(ModBlocks.EMPTY_CUP.get(), new Item.Properties()));
    public static final RegistryObject<Item> EMPTY_PAN_G_ITEM = ITEMS.register("empty_pan_g",
        () -> new BlockItem(ModBlocks.EMPTY_PAN_G.get(), new Item.Properties()));
    public static final RegistryObject<Item> FILLED_SOUP_PAN_ITEM = ITEMS.register("filled_soup_pan",
        () -> new BlockItem(ModBlocks.FILLED_SOUP_PAN.get(), new Item.Properties()));
    public static final RegistryObject<Item> ICE_MAKER_ITEM = ITEMS.register("ice_maker",
        () -> new TESRBlockItem(ModBlocks.ICE_MAKER.get(), new Item.Properties()));
    public static final RegistryObject<Item> TEPPAN_II_ITEM = ITEMS.register("teppan_ii",
        () -> new TESRBlockItem(ModBlocks.TEPPAN_II.get(), new Item.Properties()));
    public static final RegistryObject<Item> PROCESSOR_ITEM = ITEMS.register("processor",
        () -> new TESRBlockItem(ModBlocks.PROCESSOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> ADV_PROCESSOR_ITEM = ITEMS.register("adv_processor",
        () -> new TESRBlockItem(ModBlocks.ADV_PROCESSOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> EVAPORATOR_ITEM = ITEMS.register("evaporator",
        () -> new TESRBlockItem(ModBlocks.EVAPORATOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> INCENSE_BASE_ITEM = ITEMS.register("incense_base",
        () -> new TESRBlockItem(ModBlocks.INCENSE_BASE.get(), new Item.Properties()));
    public static final RegistryObject<Item> WOOD_BOX_ITEM = ITEMS.register("wood_box",
        () -> new BlockItem(ModBlocks.WOOD_BOX.get(), new Item.Properties()));
    public static final RegistryObject<Item> APPLE_BOX_ITEM = ITEMS.register("apple_box",
        () -> new BlockItem(ModBlocks.APPLE_BOX.get(), new Item.Properties()));
    public static final RegistryObject<Item> VEGI_BAG_ITEM = ITEMS.register("vegi_bag",
        () -> new TESRBlockItem(ModBlocks.VEGI_BAG.get(), new Item.Properties()));
    public static final RegistryObject<Item> CARDBOARD_ITEM = ITEMS.register("cardboard",
        () -> new TESRBlockItem(ModBlocks.CARDBOARD.get(), new Item.Properties()));
    public static final RegistryObject<Item> CHARCOAL_BOX_ITEM = ITEMS.register("charcoal_box",
        () -> new BlockItem(ModBlocks.CHARCOAL_BOX.get(), new Item.Properties()));
    public static final RegistryObject<Item> GUNPOWDER_CONTAINER_ITEM = ITEMS.register("gunpowder_container",
        () -> new BlockItem(ModBlocks.GUNPOWDER_CONTAINER.get(), new Item.Properties()));
    public static final RegistryObject<Item> EGG_BASKET_ITEM = ITEMS.register("egg_basket",
        () -> new TESRBlockItem(ModBlocks.EGG_BASKET.get(), new Item.Properties()));
    public static final RegistryObject<Item> MUSH_BOX_ITEM = ITEMS.register("mushroom_box",
        () -> new BlockItem(ModBlocks.MUSH_BOX.get(), new Item.Properties()));
    public static final RegistryObject<Item> MELON_BOMB_ITEM = ITEMS.register("melon_bomb",
        () -> new BlockItem(ModBlocks.MELON_BOMB.get(), new Item.Properties()));
    public static final RegistryObject<Item> WIPE_BOX_ITEM = ITEMS.register("wipe_box",
        () -> new TESRBlockItem(ModBlocks.WIPE_BOX.get(), new Item.Properties()));
    public static final RegistryObject<Item> WIPE_BOX2_ITEM = ITEMS.register("wipe_box2",
        () -> new TESRBlockItem(ModBlocks.WIPE_BOX2.get(), new Item.Properties()));
    public static final RegistryObject<Item> MOB_BLOCK_ITEM = ITEMS.register("mob_block",
        () -> new BlockItem(ModBlocks.MOB_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> SILKY_MELON_ITEM = ITEMS.register("silky_melon",
        () -> new BlockItem(ModBlocks.SILKY_MELON.get(), new Item.Properties()));
    public static final RegistryObject<Item> FLOWER_POT_ITEM = ITEMS.register("flower_pot",
        () -> new TESRBlockItem(ModBlocks.FLOWER_POT.get(), new Item.Properties()));
    public static final RegistryObject<Item> FLOWER_VASE_ITEM = ITEMS.register("flower_vase",
        () -> new BlockItem(ModBlocks.FLOWER_VASE.get(), new Item.Properties()));
    public static final RegistryObject<Item> HEDGE_ITEM = ITEMS.register("hedge",
        () -> new BlockItem(ModBlocks.HEDGE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CONTAINER_WATER_BOTTLE_ITEM = ITEMS.register("container_water_bottle",
        () -> new BlockItem(ModBlocks.CONTAINER_WATER_BOTTLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CONTAINER_SADDLE_ITEM = ITEMS.register("container_saddle",
        () -> new BlockItem(ModBlocks.CONTAINER_SADDLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> FILLED_CUP_ITEM = ITEMS.register("filled_cup",
        () -> new BlockItem(ModBlocks.FILLED_CUP.get(), new Item.Properties()));
    public static final RegistryObject<Item> FILLED_CUP2_ITEM = ITEMS.register("filled_cup2",
        () -> new BlockItem(ModBlocks.FILLED_CUP2.get(), new Item.Properties()));
    public static final RegistryObject<Item> ICE_CREAM_ITEM = ITEMS.register("ice_cream_block",
        () -> new TESRBlockItem(ModBlocks.ICE_CREAM.get(), new Item.Properties()));
    public static final RegistryObject<Item> COCKTAIL_ITEM = ITEMS.register("cocktail",
        () -> new TESRBlockItem(ModBlocks.COCKTAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> COCKTAIL2_ITEM = ITEMS.register("cocktail2",
        () -> new TESRBlockItem(ModBlocks.COCKTAIL2.get(), new Item.Properties()));
    public static final RegistryObject<Item> COCKTAIL_SP_ITEM = ITEMS.register("cocktail_sp",
        () -> new TESRBlockItem(ModBlocks.COCKTAIL_SP.get(), new Item.Properties()));
    public static final RegistryObject<Item> ALCOHOL_CUP_ITEM = ITEMS.register("alcohol_cup",
        () -> new TESRBlockItem(ModBlocks.ALCOHOL_CUP.get(), new Item.Properties()));
    public static final RegistryObject<Item> BOWL_BLOCK_ITEM = ITEMS.register("bowl_block",
        () -> new TESRBlockItem(ModBlocks.BOWL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> BOWL_JP_ITEM = ITEMS.register("bowl_jp",
        () -> new TESRBlockItem(ModBlocks.BOWL_JP.get(), new Item.Properties()));
    public static final RegistryObject<Item> FOOD_PLATE_ITEM = ITEMS.register("food_plate",
        () -> new TESRBlockItem(ModBlocks.FOOD_PLATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CHOCO_BLOCK_ITEM = ITEMS.register("choco_block",
        () -> new BlockItem(ModBlocks.CHOCO_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> BARREL_ITEM = ITEMS.register("barrel",
        () -> new TESRBlockItem(ModBlocks.BARREL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SAPLING_TEA_ITEM = ITEMS.register("sapling_tea",
        () -> new BlockItem(ModBlocks.SAPLING_TEA.get(), new Item.Properties()));
    public static final RegistryObject<Item> TEA_TREE_ITEM = ITEMS.register("tea_tree",
        () -> new BlockItem(ModBlocks.TEA_TREE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CASSIS_TREE_ITEM = ITEMS.register("cassis_tree",
        () -> new BlockItem(ModBlocks.CASSIS_TREE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CLAM_SAND_ITEM = ITEMS.register("clam_sand",
        () -> new BlockItem(ModBlocks.CLAM_SAND.get(), new Item.Properties()));
    public static final RegistryObject<Item> CROP_MINT_ITEM = ITEMS.register("crop_mint",
        () -> new BlockItem(ModBlocks.CROP_MINT.get(), new Item.Properties()));
    public static final RegistryObject<Item> SAPLING_YUZU_ITEM = ITEMS.register("sapling_yuzu",
        () -> new BlockItem(ModBlocks.SAPLING_YUZU.get(), new Item.Properties()));
    public static final RegistryObject<Item> LOG_YUZU_ITEM = ITEMS.register("log_yuzu",
        () -> new BlockItem(ModBlocks.LOG_YUZU.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEAVES_YUZU_ITEM = ITEMS.register("leaves_yuzu",
        () -> new BlockItem(ModBlocks.LEAVES_YUZU.get(), new Item.Properties()));
    public static final RegistryObject<Item> BOWL_RACK_ITEM = ITEMS.register("bowl_rack",
        () -> new TESRBlockItem(ModBlocks.BOWL_RACK.get(), new Item.Properties()));
    public static final RegistryObject<Item> BASKET_ITEM = ITEMS.register("basket",
        () -> new TESRBlockItem(ModBlocks.BASKET.get(), new Item.Properties()));
    public static final RegistryObject<Item> CHOPSTICKS_BOX_ITEM = ITEMS.register("chopsticks_box",
        () -> new TESRBlockItem(ModBlocks.CHOPSTICKS_BOX.get(), new Item.Properties()));
    public static final RegistryObject<Item> WOOD_PANEL_ITEM = ITEMS.register("wood_panel",
        () -> new BlockItem(ModBlocks.WOOD_PANEL.get(), new Item.Properties()));
    public static final RegistryObject<Item> YUZU_FENCE_ITEM = ITEMS.register("yuzu_fence",
        () -> new BlockItem(ModBlocks.YUZU_FENCE.get(), new Item.Properties()));
    public static final RegistryObject<Item> FLINT_BLOCK_ITEM = ITEMS.register("flint_block",
        () -> new BlockItem(ModBlocks.FLINT_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> CHALCEDONY_ITEM = ITEMS.register("chalcedony",
        () -> new BlockItem(ModBlocks.CHALCEDONY.get(), new Item.Properties()));
    public static final RegistryObject<Item> CHALCEDONY_LAMP_ITEM = ITEMS.register("chalcedony_lamp",
        () -> new TESRBlockItem(ModBlocks.CHALCEDONY_LAMP.get(), new Item.Properties()));
    public static final RegistryObject<Item> CHALCEDONY_LAMP_OP_ITEM = ITEMS.register("chalcedony_lamp_op",
        () -> new BlockItem(ModBlocks.CHALCEDONY_LAMP_OP.get(), new Item.Properties()));
    public static final RegistryObject<Item> CHALCEDONY_PANEL_ITEM = ITEMS.register("chalcedony_panel",
        () -> new BlockItem(ModBlocks.CHALCEDONY_PANEL.get(), new Item.Properties()));
    public static final RegistryObject<Item> ROTARY_DIAL_ITEM = ITEMS.register("rotary_dial",
        () -> new TESRBlockItem(ModBlocks.ROTARY_DIAL.get(), new Item.Properties()));
    public static final RegistryObject<Item> CROW_DOLL_ITEM = ITEMS.register("crow_doll",
        () -> new TESRBlockItem(ModBlocks.CROW_DOLL.get(), new Item.Properties()));
    public static final RegistryObject<Item> BAT_BOX_ITEM = ITEMS.register("bat_box",
        () -> new TESRBlockItem(ModBlocks.BAT_BOX.get(), new Item.Properties()));
    public static final RegistryObject<Item> RED_GEL_ITEM = ITEMS.register("red_gel",
        () -> new BlockItem(ModBlocks.RED_GEL.get(), new Item.Properties()));
    public static final RegistryObject<Item> YUZU_LIGHT_ITEM = ITEMS.register("yuzu_light",
        () -> new BlockItem(ModBlocks.YUZU_LIGHT.get(), new Item.Properties()));
    public static final RegistryObject<Item> YUZU_BAT_ITEM = ITEMS.register("yuzu_bat",
        () -> new BlockItem(ModBlocks.YUZU_BAT.get(), new Item.Properties()));
    public static final RegistryObject<Item> GEL_BAT_ITEM = ITEMS.register("gel_bat",
        () -> new TESRBlockItem(ModBlocks.GEL_BAT.get(), new Item.Properties()));
    public static final RegistryObject<Item> HANDLE_ENGINE_ITEM = ITEMS.register("handle_engine",
        () -> new TESRBlockItem(ModBlocks.HANDLE_ENGINE.get(), new Item.Properties()));

    // --- WT-B: FLUID BUCKETS (vegi_oil, camellia_oil) ---
    public static final RegistryObject<Item> BUCKET_VEGIOIL = ITEMS.register("bucket_vegioil",
        () -> new mods.defeatedcrow.common.fluid.ItemBucketVegiOil(() -> (net.minecraft.world.level.material.Fluid) net.minecraftforge.registries.ForgeRegistries.FLUIDS.getValue(new net.minecraft.resources.ResourceLocation("defeatedcrow", "vegitable_oil"))));
    public static final RegistryObject<Item> BUCKET_CAMOIL = ITEMS.register("bucket_camoil",
        () -> new mods.defeatedcrow.common.fluid.ItemBucketCamOil(() -> (net.minecraft.world.level.material.Fluid) net.minecraftforge.registries.ForgeRegistries.FLUIDS.getValue(new net.minecraft.resources.ResourceLocation("defeatedcrow", "camellia_oil"))));
    public static final RegistryObject<Item> BUCKET_YOUNGALCOHOL = ITEMS.register("bucket_youngalcohol",
        () -> new mods.defeatedcrow.common.fluid.ItemBucketYoungAlcohol(() -> net.minecraft.world.level.material.Fluids.WATER));

    // --- WT-A: BREWING ITEMS (itemLargeBottle, itemCordial, etc.) ---
    public static final RegistryObject<Item> LARGE_BOTTLE = ITEMS.register("large_bottle",
        () -> new TESRBlockItem(ModBlocks.LARGE_BOTTLE.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CORDIAL = ITEMS.register("cordial",
        () -> new TESRBlockItem(ModBlocks.CORDIAL.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EMPTY_BOTTLE_ITEM = ITEMS.register("empty_bottle",
        () -> new TESRBlockItem(ModBlocks.EMPTY_BOTTLE.get(), new Item.Properties().stacksTo(16)));

    // --- WT-C: INCENSE (incenseApple..incenseVanilla 11種) - may stay in ModItems or move to WT-C section ---
    public static final RegistryObject<Item> INCENSE_APPLE = ITEMS.register("incense_apple",
        () -> new ItemIncenseApple(new Item.Properties()));
    public static final RegistryObject<Item> INCENSE_ROSE = ITEMS.register("incense_rose",
        () -> new ItemIncenseRose(new Item.Properties()));
    public static final RegistryObject<Item> INCENSE_MINT = ITEMS.register("incense_mint",
        () -> new ItemIncenseMint(new Item.Properties()));
    public static final RegistryObject<Item> INCENSE_CLAM = ITEMS.register("incense_clam",
        () -> new ItemIncenseClam(new Item.Properties()));
    public static final RegistryObject<Item> INCENSE_ICE = ITEMS.register("incense_ice",
        () -> new ItemIncenseIce(new Item.Properties()));
    public static final RegistryObject<Item> INCENSE_LAVENDER = ITEMS.register("incense_lavender",
        () -> new ItemIncenseLavender(new Item.Properties()));
    public static final RegistryObject<Item> INCENSE_SANDALWOOD = ITEMS.register("incense_sandalwood",
        () -> new ItemIncenseSandalwood(new Item.Properties()));
    public static final RegistryObject<Item> INCENSE_AGAR = ITEMS.register("incense_agar",
        () -> new ItemIncenseAgar(new Item.Properties()));
    public static final RegistryObject<Item> INCENSE_FRANKINCENSE = ITEMS.register("incense_frankincense",
        () -> new ItemIncenseFrankincense(new Item.Properties()));
    public static final RegistryObject<Item> INCENSE_YUZU = ITEMS.register("incense_yuzu",
        () -> new ItemIncenseYuzu(new Item.Properties()));
    public static final RegistryObject<Item> INCENSE_VANILLA = ITEMS.register("incense_vanilla",
        () -> new ItemIncenseVanilla(new Item.Properties()));
    public static final RegistryObject<Item> INCENSE_TEAR = ITEMS.register("incense_tear",
        () -> new ItemIncenseTear(new Item.Properties()));
    public static final RegistryObject<Item> PRINCESS_CLAM = ITEMS.register("princess_clam",
        () -> new ItemPrincessClam(new Item.Properties()));

    private ModItems() {}
}
