package mods.defeatedcrow.common.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
 * 1.20.1 Item registry — DeferredRegister + Item.Properties.
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

    // leafTea splits — 1.13+個別Item化 (doc/items/migration-guide.md:60)
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

    // --- WT-A: BLOCK-ITEMS (teaMakerNext, woodBox, etc. — BlockItem wrappers) ---
    public static final RegistryObject<Item> TEA_MAKER_NEXT_ITEM = ITEMS.register("tea_maker_next",
        () -> new BlockItem(ModBlocks.TEA_MAKER_NEXT.get(), new Item.Properties()));
    public static final RegistryObject<Item> TEA_MAKER_BLACK_ITEM = ITEMS.register("tea_maker_black",
        () -> new BlockItem(ModBlocks.TEA_MAKER_BLACK.get(), new Item.Properties()));
    public static final RegistryObject<Item> EMPTY_CUP_ITEM = ITEMS.register("empty_cup",
        () -> new BlockItem(ModBlocks.EMPTY_CUP.get(), new Item.Properties()));
    public static final RegistryObject<Item> WOOD_BOX_ITEM = ITEMS.register("wood_box",
        () -> new BlockItem(ModBlocks.WOOD_BOX.get(), new Item.Properties()));
    public static final RegistryObject<Item> BASKET_ITEM = ITEMS.register("basket",
        () -> new BlockItem(ModBlocks.BASKET.get(), new Item.Properties()));
    public static final RegistryObject<Item> CROW_DOLL_ITEM = ITEMS.register("crow_doll",
        () -> new BlockItem(ModBlocks.CROW_DOLL.get(), new Item.Properties()));
    // Additional BlockItems can be added per need; generic fallback is ModBlocks.* used via displayItems

    // --- WT-A: BREWING ITEMS (itemLargeBottle, itemCordial, etc.) ---
    public static final RegistryObject<Item> LARGE_BOTTLE = ITEMS.register("large_bottle",
        () -> new ItemLargeBottle(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CORDIAL = ITEMS.register("cordial",
        () -> new ItemCordial(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> EMPTY_BOTTLE_ITEM = ITEMS.register("empty_bottle",
        () -> new ItemEmptyBottle(new Item.Properties().stacksTo(16)));

    // --- WT-C: INCENSE (incenseApple..incenseVanilla 11種) — may stay in ModItems or move to WT-C section ---
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
