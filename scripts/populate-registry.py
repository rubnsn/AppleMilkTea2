#!/usr/bin/env python3
import pathlib

base = pathlib.Path(r"E:\AMT2-WT-A\src\main\java\mods\defeatedcrow\common\registry")

mod_blocks_content = """package mods.defeatedcrow.common.registry;

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
 * 1.20.1 Block registry — FG6 + mojmap + DeferredRegister.
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

    // --- WT-B: FLUID BLOCKS (blockVegitableOil, blockCamelliaOil) — LiquidBlock, see ModFluids ---
    // public static final RegistryObject<net.minecraft.world.level.block.LiquidBlock> BLOCK_VEGI_OIL = BLOCKS.register("block_vegi_oil",
    //     () -> new net.minecraft.world.level.block.LiquidBlock(ModFluids.VEG_OIL_SOURCE, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

    private ModBlocks() {}
}
"""

mod_items_content = """package mods.defeatedcrow.common.registry;

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
"""

mod_tabs_content = """package mods.defeatedcrow.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * 1.20.1 CreativeModeTab registry — replaces CreativeTabs (1.19.3+ Registry化).
 * See doc/creative-tabs/migration-guide.md:1
 * WT-A owns displayItems. Bootstrap owns registration shell.
 */
public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "defeatedcrow");

    // --- WT-A: TABS (applemilk, applemilkMaterial, applemilkFood, applemilkContainer, applemilkMagic) ---
    public static final RegistryObject<CreativeModeTab> APPLEMILK = TABS.register("applemilk",
        () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.defeatedcrow.applemilk"))
            .icon(() -> new ItemStack(ModItems.LEAF_TEA.get())).displayItems((p, out) -> {
                out.accept(ModBlocks.TEA_MAKER_NEXT.get());
                out.accept(ModBlocks.BASKET.get());
                out.accept(ModBlocks.BOWL_RACK.get());
            }).build());

    public static final RegistryObject<CreativeModeTab> APPLEMILK_MATERIAL = TABS.register("applemilk_material",
        () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.defeatedcrow.applemilkMaterial"))
            .icon(() -> new ItemStack(ModItems.LEAF_TEA.get())).displayItems((p, out) -> {
                out.accept(ModItems.LEAF_TEA.get());
                out.accept(ModItems.LEAF_MINT.get());
                out.accept(ModItems.FOOD_TEA.get());
                out.accept(ModItems.ORE_DUST.get());
            }).build());

    public static final RegistryObject<CreativeModeTab> APPLEMILK_FOOD = TABS.register("applemilk_food",
        () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.defeatedcrow.applemilkFood"))
            .icon(() -> new ItemStack(ModItems.BAKED_APPLE.get())).displayItems((p, out) -> {
                out.accept(ModItems.BAKED_APPLE.get());
                out.accept(ModItems.APPLE_TART.get());
                out.accept(ModItems.CLAM.get());
                out.accept(ModBlocks.FILLED_CUP.get());
            }).build());

    public static final RegistryObject<CreativeModeTab> APPLEMILK_CONTAINER = TABS.register("applemilk_container",
        () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.defeatedcrow.applemilkContainer"))
            .icon(() -> new ItemStack(ModBlocks.WOOD_BOX.get())).displayItems((p, out) -> {
                out.accept(ModBlocks.WOOD_BOX.get());
                out.accept(ModBlocks.APPLE_BOX.get());
                out.accept(ModBlocks.VEGI_BAG.get());
                out.accept(ModBlocks.BASKET.get());
            }).build());

    public static final RegistryObject<CreativeModeTab> APPLEMILK_MAGIC = TABS.register("applemilk_magic",
        () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.defeatedcrow.applemilkMagic"))
            .icon(() -> new ItemStack(ModItems.PRINCESS_CLAM.get())).displayItems((p, out) -> {
                out.accept(ModItems.INCENSE_APPLE.get());
                out.accept(ModItems.PRINCESS_CLAM.get());
                out.accept(ModItems.YUZU_GATLING.get());
            }).build());

    private ModCreativeTabs() {}
}
"""

(base/"ModBlocks.java").write_text(mod_blocks_content, encoding='utf-8')
(base/"ModItems.java").write_text(mod_items_content, encoding='utf-8')
(base/"ModCreativeTabs.java").write_text(mod_tabs_content, encoding='utf-8')
print("populated ModBlocks, ModItems, ModCreativeTabs")
