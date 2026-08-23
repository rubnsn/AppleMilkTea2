package mods.defeatedcrow.common;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.registry.ModBlockEntities;
import mods.defeatedcrow.common.registry.ModBlocks;
import mods.defeatedcrow.common.registry.ModCreativeTabs;
import mods.defeatedcrow.common.registry.ModEntities;
import mods.defeatedcrow.common.registry.ModFluidTypes;
import mods.defeatedcrow.common.registry.ModFluids;
import mods.defeatedcrow.common.registry.ModItems;
import mods.defeatedcrow.common.registry.ModMenuTypes;
import mods.defeatedcrow.common.registry.ModMobEffects;

/**
 * 1.20.1 entry point - DeferredRegister aggregation.
 * 1.7.10 static Block/Item/Fluid/modelXXX fields, SidedProxy, GameRegistry, Tags.VERSION は削除。
 * 各登録は common/registry/Mod*.java の DeferredRegister に委譲 (doc/build.md:22, doc/blocks/migration-guide.md:174)。
 */
@Mod(DCsAppleMilk.MODID)
public class DCsAppleMilk {

    public static final String MODID = "DCsAppleMilk";
    public static final String MOD_NAME = "Apple&Milk&Tea!";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public DCsAppleMilk() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModFluids.FLUIDS.register(modBus);
        ModFluidTypes.FLUID_TYPES.register(modBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modBus);
        ModMenuTypes.MENUS.register(modBus);
        ModEntities.ENTITIES.register(modBus);
        ModMobEffects.MOB_EFFECTS.register(modBus);
        ModCreativeTabs.TABS.register(modBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DCsConfig.COMMON_SPEC, "defeatedcrow-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, DCsConfig.CLIENT_SPEC, "defeatedcrow-client.toml");

        modBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("Apple&Milk&Tea! 1.20.1 bootstrap - DeferredRegister wired (Forge 47.3 / FG6 / mojmap / JDK17)");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            DCsConfig.sync();
            LOGGER.debug("Common setup - config synced");
        });
    }

    // 1.20.1 compat shim - legacy static fields referenced by WT-B leaf code not yet migrated to ModBlocks/ModItems
    // Each field is a placeholder (Blocks.AIR / Items.AIR) so old code compiles; actual block is ModBlocks.* / ModItems.*
    // TODO: remove when all callers are migrated (plan.md:T1/T5/T6). See doc/blocks/migration-guide.md
    public static boolean debugMode = false;
    public static final net.minecraft.world.level.block.Block mushroomBox = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block melonBomb = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.item.Item chopsticks = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item firestarter = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.level.block.Block cropMint = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block cassisTree = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block teaTree = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block saplingTea = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block saplingYuzu = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block incenseBase = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.item.Item bucketCamOil = net.minecraft.world.item.Items.BUCKET;
    public static final net.minecraft.world.level.block.Block blockCamelliaOil = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.item.Item bucketVegiOil = net.minecraft.world.item.Items.BUCKET;
    public static final net.minecraft.world.level.block.Block blockVegitableOil = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.item.Item dustWood = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item moromi = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item itemLargeBottle = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item emptyBottle = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item itemCordial = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item slotPanel = net.minecraft.world.item.Items.AIR;

    public int getMajorVersion() {
        return 2;
    }

    public int getMinorVersion() {
        return 9;
    }

    public String getRivision() {
        return "m";
    }

    public String getModName() {
        return MOD_NAME;
    }

    public String getModID() {
        return MODID;
    }

    // --- additional legacy shim (auto-generated from 1.7.10 fields) ---
    public static final net.minecraft.world.level.block.Block teaMakerNext = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block teaMakerBlack = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block emptyCup = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block iceMaker = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block emptyPanGaiden = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block filledSoupPan = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block teppanII = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block processor = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block evaporator = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block advProcessor = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block batBox = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block redGel = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block yuzuGel = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block yuzuBat = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block gelBat = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block handleEngine = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block teacupBlock = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block teaCup2 = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block blockIcecream = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block cocktail = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block cocktail2 = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block alcoholCup = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block bowlBlock = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block bowlJP = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block foodPlate = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block chocoBlock = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block cocktailSP = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block largeBottle = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block cordial = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block barrel = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block woodBox = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block appleBox = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block vegiBag = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block cardboard = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block charcoalBox = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block gunpowderContainer = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block eggBasket = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block wipeBox = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block wipeBox2 = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block mobBlock = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block silkyMelon = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block flowerPot = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block yuzuFence = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block flowerBase = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block hedge = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block containerWBottle = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block containerSaddle = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block clamSand = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block logYuzu = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block leavesYuzu = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block bowlRack = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block Basket = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block chopsticksBox = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block woodPanel = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block flintBlock = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block chalcedony = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block cLamp = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block rotaryDial = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block chalcenonyPanel = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block cLampOpaque = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block crowDoll = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.item.Item bakedApple = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item appleTart = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item toffyApple = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item icyToffyApple = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item appleSandwich = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item chocolateFruits = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item baseSoupBowl = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item leafTea = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item gratedApple = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item mincedFoods = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item condensedMIlk = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item clam = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item EXItems = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item inkStick = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item foodTea = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item DCgrater = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item icyCrystal = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item itemMintSeed = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item stickCarbon = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item oreDust = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item chalcedonyKnife = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item chalcedonyHammer = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item monocle = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item onixSword = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item pruningShears = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item milkBottle = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item jawPlate = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item yeast = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item youngAlcohol = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item essentialOil = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item strangeSlag = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item fossilScale = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item fossilCannon = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item yuzuGatling = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item containerItemDoorW = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item containerItemDoorI = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item eightEyesArm = net.minecraft.world.item.Items.AIR;
    public static final Object incenseApple = null;
    public static final Object incenseRose = null;
    public static final Object incenseMint = null;
    public static final Object incenseIce = null;
    public static final Object incenseClam = null;
    public static final Object incenseLavender = null;
    public static final Object incenseSandalwood = null;
    public static final Object incenseAgar = null;
    public static final Object incenseFrank = null;
    public static final Object incenseYuzu = null;
    public static final Object incenseVanilla = null;
    public static final net.minecraft.world.level.material.Fluid camelliaOil = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.material.Fluid shothu_young = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.material.Fluid whiskey_young = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.material.Fluid brandy_young = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.material.Fluid rum_young = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.material.Fluid vodka_young = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.material.Fluid shothu = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.material.Fluid whiskey = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.material.Fluid brandy = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.material.Fluid rum = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.material.Fluid vodka = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.material.Fluid sake_young = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.material.Fluid beer_young = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.material.Fluid wine_young = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.material.Fluid sake = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.material.Fluid beer = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.material.Fluid wine = net.minecraft.world.level.material.Fluids.EMPTY;
    public static final net.minecraft.world.level.block.Block blockDummyAlcohol = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.level.block.Block blockDummyAlcohol2 = net.minecraft.world.level.block.Blocks.AIR;
    public static final net.minecraft.world.item.Item bottleVegiOil = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item bottleCamOil = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item bucketYoungAlcohol = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item dummyItem = net.minecraft.world.item.Items.AIR;
    public static final net.minecraft.world.item.Item dummyTeppan = net.minecraft.world.item.Items.AIR;
    public static final Object Immunization = null;
    public static final Object prvExplode = null;
    public static final Object prvProjectile = null;
    public static final Object reflex = null;
    public static final Object absEXP = null;
    public static final Object absHeal = null;
    public static final Object suffocation = null;
    public static final Object prvSuffocation = null;
    public static final Object hallucinations = null;
    public static final Object confinement = null;
    public static final Object villager = null;
    public static final Object villagerYome = null;
    public static final Object enumToolMaterialChalcedony = null;
}
