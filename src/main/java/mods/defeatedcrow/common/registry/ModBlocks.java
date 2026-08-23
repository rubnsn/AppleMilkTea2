package mods.defeatedcrow.common.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.sounds.SoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// WT-A owned block imports
import mods.defeatedcrow.common.block.BlockBasket;
import mods.defeatedcrow.common.block.BlockBowlRack;
import mods.defeatedcrow.common.block.BlockChalcedony;
import mods.defeatedcrow.common.block.BlockChalcedonyLamp;
import mods.defeatedcrow.common.block.BlockChalcedonyLampOp;
import mods.defeatedcrow.common.block.BlockChopsticksBox;
import mods.defeatedcrow.common.block.BlockCPanel;
import mods.defeatedcrow.common.block.BlockCrowDoll;
import mods.defeatedcrow.common.block.BlockFlint;
import mods.defeatedcrow.common.block.BlockIncenseBase;
import mods.defeatedcrow.common.block.BlockRotaryDial;
import mods.defeatedcrow.common.block.BlockWoodPanel;
import mods.defeatedcrow.common.block.BlockYuzuFence;
import mods.defeatedcrow.common.block.appliance.BlockAdvProcessor;
import mods.defeatedcrow.common.block.appliance.BlockEmptyCup;
import mods.defeatedcrow.common.block.appliance.BlockEmptyPanG;
import mods.defeatedcrow.common.block.appliance.BlockEvaporator;
import mods.defeatedcrow.common.block.appliance.BlockFilledSoupPan;
import mods.defeatedcrow.common.block.appliance.BlockIceMaker;
import mods.defeatedcrow.common.block.appliance.BlockProcessor;
import mods.defeatedcrow.common.block.appliance.BlockTeaMakerBlack;
import mods.defeatedcrow.common.block.appliance.BlockTeaMakerNext;
import mods.defeatedcrow.common.block.appliance.BlockTeppanII;
import mods.defeatedcrow.common.block.brewing.BlockBarrel;
import mods.defeatedcrow.common.block.brewing.BlockCordial;
import mods.defeatedcrow.common.block.brewing.BlockEmptyBottle;
import mods.defeatedcrow.common.block.brewing.BlockLargeBottle;
import mods.defeatedcrow.common.block.container.BlockAppleBox;
import mods.defeatedcrow.common.block.container.BlockCardboard;
import mods.defeatedcrow.common.block.container.BlockCharcoalBox;
import mods.defeatedcrow.common.block.container.BlockContainerBase;
import mods.defeatedcrow.common.block.container.BlockContainerSaddle;
import mods.defeatedcrow.common.block.container.BlockContainerWaterBottle;
import mods.defeatedcrow.common.block.container.BlockEggBasket;
import mods.defeatedcrow.common.block.container.BlockFlowerPot;
import mods.defeatedcrow.common.block.container.BlockFlowerVase;
import mods.defeatedcrow.common.block.container.BlockGunpowderContainer;
import mods.defeatedcrow.common.block.container.BlockHedge;
import mods.defeatedcrow.common.block.container.BlockMelonBomb;
import mods.defeatedcrow.common.block.container.BlockMobDrop;
import mods.defeatedcrow.common.block.container.BlockMushBox;
import mods.defeatedcrow.common.block.container.BlockSilkyMelon;
import mods.defeatedcrow.common.block.container.BlockVegiBag;
import mods.defeatedcrow.common.block.container.BlockWipeBox;
import mods.defeatedcrow.common.block.container.BlockWipeBox2;
import mods.defeatedcrow.common.block.container.BlockWoodBox;
import mods.defeatedcrow.common.block.edible.BlockAlcoholCup;
import mods.defeatedcrow.common.block.edible.BlockBowl;
import mods.defeatedcrow.common.block.edible.BlockBowlJP;
import mods.defeatedcrow.common.block.edible.BlockChocoGift;
import mods.defeatedcrow.common.block.edible.BlockCocktail;
import mods.defeatedcrow.common.block.edible.BlockCocktail2;
import mods.defeatedcrow.common.block.edible.BlockCocktailSP;
import mods.defeatedcrow.common.block.edible.BlockFilledCup;
import mods.defeatedcrow.common.block.edible.BlockFilledCup2;
import mods.defeatedcrow.common.block.edible.BlockFoodPlate;
import mods.defeatedcrow.common.block.edible.BlockIceCream;
import mods.defeatedcrow.common.block.energy.BlockBatBox;
import mods.defeatedcrow.common.block.energy.BlockGelBat;
import mods.defeatedcrow.common.block.energy.BlockHandleEngine;
import mods.defeatedcrow.common.block.energy.BlockRedGel;
import mods.defeatedcrow.common.block.energy.BlockYuzuBat;
import mods.defeatedcrow.common.block.energy.BlockYuzuLight;
import mods.defeatedcrow.common.block.plants.BlockCassisTree;
import mods.defeatedcrow.common.block.plants.BlockClamSand;
import mods.defeatedcrow.common.block.plants.BlockMintCrop;
import mods.defeatedcrow.common.block.plants.BlockSaplingTea;
import mods.defeatedcrow.common.block.plants.BlockTeaTree;
import mods.defeatedcrow.common.block.plants.BlockYuzuLeaves;
import mods.defeatedcrow.common.block.plants.BlockYuzuLog;
import mods.defeatedcrow.common.block.plants.BlockYuzuSapling;

/**
 * 1.20.1 Block registry - FG6 + mojmap + DeferredRegister.
 * Bootstrap-owned skeleton. WT-A/B/C append inside their commented sections only.
 * Registry namespace is "defeatedcrow" for legacy world compat (1.7.10 GameRegistry used "defeatedcrow.*").
 * ModID remains "DCsAppleMilk" (mods.toml). DeferredRegister namespace != modId is intentional for save compat.
 * See doc/blocks/migration-guide.md:174
 */
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "defeatedcrow");

    // --- WT-A: APPLIANCE (teaMakerNext, teaMakerBlack, emptyCup, iceMaker, emptyPanGaiden, filledSoupPan, teppanII, processor, evaporator, advProcessor) ---
    public static final RegistryObject<Block> TEA_MAKER_NEXT = BLOCKS.register("tea_maker_next",
        () -> new BlockTeaMakerNext(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(2.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> TEA_MAKER_BLACK = BLOCKS.register("tea_maker_black",
        () -> new BlockTeaMakerBlack(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(2.0F, 6.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> EMPTY_CUP = BLOCKS.register("empty_cup",
        () -> new BlockEmptyCup(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(0.3F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> EMPTY_PAN_G = BLOCKS.register("empty_pan_g",
        () -> new BlockEmptyPanG(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(1.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> FILLED_SOUP_PAN = BLOCKS.register("filled_soup_pan",
        () -> new BlockFilledSoupPan(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(1.0F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> ICE_MAKER = BLOCKS.register("ice_maker",
        () -> new BlockIceMaker(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(1.5F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> TEPPAN_II = BLOCKS.register("teppan_ii",
        () -> new BlockTeppanII(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(1.5F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> PROCESSOR = BLOCKS.register("processor",
        () -> new BlockProcessor(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(1.5F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> ADV_PROCESSOR = BLOCKS.register("adv_processor",
        () -> new BlockAdvProcessor(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(2.0F, 8.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> EVAPORATOR = BLOCKS.register("evaporator",
        () -> new BlockEvaporator(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(1.5F).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Block> INCENSE_BASE = BLOCKS.register("incense_base",
        () -> new BlockIncenseBase(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.5F).sound(SoundType.WOOD).noOcclusion()));

    // --- WT-A: CONTAINER (woodBox, appleBox, vegiBag, cardboard, charcoalBox, gunpowderContainer, eggBasket, mushroomBox, melonBomb, wipeBox, wipeBox2, mobBlock, silkyMelon, flowerPot, flowerVase, hedge, containerWBottle, containerSaddle) ---
    public static final RegistryObject<Block> WOOD_BOX = BLOCKS.register("wood_box",
        () -> new BlockWoodBox(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> APPLE_BOX = BLOCKS.register("apple_box",
        () -> new BlockAppleBox(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.8F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> VEGI_BAG = BLOCKS.register("vegi_bag",
        () -> new BlockVegiBag(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.5F).sound(SoundType.WOOL).noOcclusion()));
    public static final RegistryObject<Block> CARDBOARD = BLOCKS.register("cardboard",
        () -> new BlockCardboard(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.3F).sound(SoundType.WOOL).noOcclusion()));
    public static final RegistryObject<Block> CHARCOAL_BOX = BLOCKS.register("charcoal_box",
        () -> new BlockCharcoalBox(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(1.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> GUNPOWDER_CONTAINER = BLOCKS.register("gunpowder_container",
        () -> new BlockGunpowderContainer(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(0.8F).sound(SoundType.SAND).noOcclusion()));
    public static final RegistryObject<Block> EGG_BASKET = BLOCKS.register("egg_basket",
        () -> new BlockEggBasket(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> MUSH_BOX = BLOCKS.register("mushroom_box",
        () -> new BlockMushBox(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> MELON_BOMB = BLOCKS.register("melon_bomb",
        () -> new BlockMelonBomb(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(1.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> WIPE_BOX = BLOCKS.register("wipe_box",
        () -> new BlockWipeBox(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(0.3F).sound(SoundType.WOOL).noOcclusion()));
    public static final RegistryObject<Block> WIPE_BOX2 = BLOCKS.register("wipe_box2",
        () -> new BlockWipeBox2(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(0.3F).sound(SoundType.WOOL).noOcclusion()));
    public static final RegistryObject<Block> MOB_BLOCK = BLOCKS.register("mob_block",
        () -> new BlockMobDrop(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(1.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> SILKY_MELON = BLOCKS.register("silky_melon",
        () -> new BlockSilkyMelon(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(0.8F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> FLOWER_POT = BLOCKS.register("flower_pot",
        () -> new BlockFlowerPot(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(0.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> FLOWER_VASE = BLOCKS.register("flower_vase",
        () -> new BlockFlowerVase(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.5F).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Block> HEDGE = BLOCKS.register("hedge",
        () -> new BlockHedge(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.8F).sound(SoundType.GRASS).noOcclusion()));
    public static final RegistryObject<Block> CONTAINER_WATER_BOTTLE = BLOCKS.register("container_water_bottle",
        () -> new BlockContainerWaterBottle(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(0.5F).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Block> CONTAINER_SADDLE = BLOCKS.register("container_saddle",
        () -> new BlockContainerSaddle(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.5F).sound(SoundType.WOOL).noOcclusion()));

    // --- WT-A: EDIBLE (teacupBlock, teaCup2, blockIcecream, cocktail, cocktail2, alcoholCup, bowlBlock, bowlJP, foodPlate, chocoBlock, cocktailSP) ---
    public static final RegistryObject<Block> FILLED_CUP = BLOCKS.register("filled_cup",
        () -> new BlockFilledCup(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.2F).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Block> FILLED_CUP2 = BLOCKS.register("filled_cup2",
        () -> new BlockFilledCup2(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.2F).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Block> ICE_CREAM = BLOCKS.register("ice_cream_block",
        () -> new BlockIceCream(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(0.2F).sound(SoundType.SNOW).noOcclusion()));
    public static final RegistryObject<Block> COCKTAIL = BLOCKS.register("cocktail",
        () -> new BlockCocktail(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.2F).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Block> COCKTAIL2 = BLOCKS.register("cocktail2",
        () -> new BlockCocktail2(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.2F).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Block> COCKTAIL_SP = BLOCKS.register("cocktail_sp",
        () -> new BlockCocktailSP(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.2F).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Block> ALCOHOL_CUP = BLOCKS.register("alcohol_cup",
        () -> new BlockAlcoholCup(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.2F).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Block> BOWL_BLOCK = BLOCKS.register("bowl_block",
        () -> new BlockBowl(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.3F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> BOWL_JP = BLOCKS.register("bowl_jp",
        () -> new BlockBowlJP(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.3F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> FOOD_PLATE = BLOCKS.register("food_plate",
        () -> new BlockFoodPlate(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).strength(0.3F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> CHOCO_BLOCK = BLOCKS.register("choco_block",
        () -> new BlockChocoGift(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.3F).sound(SoundType.WOOL).noOcclusion()));

    // --- WT-A: BREWING (emptyBottle, largeBottle, cordial, barrel) ---
    public static final RegistryObject<Block> EMPTY_BOTTLE = BLOCKS.register("empty_bottle",
        () -> new BlockEmptyBottle(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Block> LARGE_BOTTLE = BLOCKS.register("large_bottle",
        () -> new BlockLargeBottle(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Block> CORDIAL = BLOCKS.register("cordial",
        () -> new BlockCordial(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.5F).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Block> BARREL = BLOCKS.register("barrel",
        () -> new BlockBarrel(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.5F).sound(SoundType.WOOD).noOcclusion()));

    // --- WT-A: PLANTS (saplingTea, teaTree, cassisTree, clamSand, cropMint, saplingYuzu, logYuzu, leavesYuzu) ---
    public static final RegistryObject<Block> SAPLING_TEA = BLOCKS.register("sapling_tea",
        () -> new BlockSaplingTea(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.0F).sound(SoundType.GRASS).noOcclusion().noCollission().randomTicks().instabreak()));
    public static final RegistryObject<Block> TEA_TREE = BLOCKS.register("tea_tree",
        () -> new BlockTeaTree(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.8F).sound(SoundType.GRASS).noOcclusion().randomTicks()));
    public static final RegistryObject<Block> CASSIS_TREE = BLOCKS.register("cassis_tree",
        () -> new BlockCassisTree(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.8F).sound(SoundType.GRASS).noOcclusion().randomTicks()));
    public static final RegistryObject<Block> CLAM_SAND = BLOCKS.register("clam_sand",
        () -> new BlockClamSand(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.SAND).randomTicks()));
    public static final RegistryObject<Block> CROP_MINT = BLOCKS.register("crop_mint",
        () -> new BlockMintCrop(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.0F).sound(SoundType.CROP).noOcclusion().noCollission().randomTicks().instabreak()));
    public static final RegistryObject<Block> SAPLING_YUZU = BLOCKS.register("sapling_yuzu",
        () -> new BlockYuzuSapling(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.0F).sound(SoundType.GRASS).noOcclusion().noCollission().randomTicks().instabreak()));
    public static final RegistryObject<Block> LOG_YUZU = BLOCKS.register("log_yuzu",
        () -> new BlockYuzuLog(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> LEAVES_YUZU = BLOCKS.register("leaves_yuzu",
        () -> new BlockYuzuLeaves(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).sound(SoundType.GRASS).noOcclusion()));

    // --- WT-A: DECORATIVE (bowlRack, Basket, chopsticksBox, woodPanel, flintBlock, chalcedony, cLamp, rotaryDial, chalcenonyPanel, cLampOpaque, crowDoll) ---
    public static final RegistryObject<Block> BOWL_RACK = BLOCKS.register("bowl_rack",
        () -> new BlockBowlRack(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> BASKET = BLOCKS.register("basket",
        () -> new BlockBasket(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> CHOPSTICKS_BOX = BLOCKS.register("chopsticks_box",
        () -> new BlockChopsticksBox(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> WOOD_PANEL = BLOCKS.register("wood_panel",
        () -> new BlockWoodPanel(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> YUZU_FENCE = BLOCKS.register("yuzu_fence",
        () -> new BlockYuzuFence(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> FLINT_BLOCK = BLOCKS.register("flint_block",
        () -> new BlockFlint(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(1.5F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHALCEDONY = BLOCKS.register("chalcedony",
        () -> new BlockChalcedony(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F).sound(SoundType.GLASS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHALCEDONY_LAMP = BLOCKS.register("chalcedony_lamp",
        () -> new BlockChalcedonyLamp(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.0F).sound(SoundType.GLASS).lightLevel(s -> 15).noOcclusion()));
    public static final RegistryObject<Block> CHALCEDONY_LAMP_OP = BLOCKS.register("chalcedony_lamp_op",
        () -> new BlockChalcedonyLampOp(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.0F).sound(SoundType.GLASS).lightLevel(s -> 15)));
    public static final RegistryObject<Block> CHALCEDONY_PANEL = BLOCKS.register("chalcedony_panel",
        () -> new BlockCPanel(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.0F).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Block> ROTARY_DIAL = BLOCKS.register("rotary_dial",
        () -> new BlockRotaryDial(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(1.0F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> CROW_DOLL = BLOCKS.register("crow_doll",
        () -> new BlockCrowDoll(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.5F).sound(SoundType.WOOL).noOcclusion()));

    // --- WT-A: ENERGY (batBox, redGel, yuzuGel, yuzuBat, gelBat, handleEngine) ---
    public static final RegistryObject<Block> BAT_BOX = BLOCKS.register("bat_box",
        () -> new BlockBatBox(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(2.0F, 8.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> RED_GEL = BLOCKS.register("red_gel",
        () -> new BlockRedGel(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(0.5F).sound(SoundType.SLIME_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> YUZU_LIGHT = BLOCKS.register("yuzu_light",
        () -> new BlockYuzuLight(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.5F).sound(SoundType.SLIME_BLOCK).lightLevel(s -> 10).noOcclusion()));
    public static final RegistryObject<Block> YUZU_BAT = BLOCKS.register("yuzu_bat",
        () -> new BlockYuzuBat(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(1.5F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> GEL_BAT = BLOCKS.register("gel_bat",
        () -> new BlockGelBat(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(1.5F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> HANDLE_ENGINE = BLOCKS.register("handle_engine",
        () -> new BlockHandleEngine(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(1.5F).sound(SoundType.METAL).noOcclusion()));

    // --- WT-B: FLUID BLOCKS (blockVegitableOil, blockCamelliaOil) - LiquidBlock, see ModFluids ---
    public static final RegistryObject<net.minecraft.world.level.block.LiquidBlock> BLOCK_VEGI_OIL = BLOCKS.register("block_vegi_oil",
        () -> new mods.defeatedcrow.common.fluid.BlockOilFluid(ModFluids.VEGITABLE_OIL_SOURCE, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().strength(100.0F).noLootTable().liquid()));
    public static final RegistryObject<net.minecraft.world.level.block.LiquidBlock> BLOCK_CAMELLIA_OIL = BLOCKS.register("block_camellia_oil",
        () -> new mods.defeatedcrow.common.fluid.BlockCamOilFluid(ModFluids.CAMELLIA_OIL_SOURCE, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

    private ModBlocks() {}
}
