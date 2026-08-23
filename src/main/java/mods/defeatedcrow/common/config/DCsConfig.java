package mods.defeatedcrow.common.config;

import java.util.Arrays;
import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * 1.20.1 ForgeConfigSpec — replaces 1.7.10 Configuration (INI) → TOML.
 * COMMON: world/difficulty/setting/entity, CLIENT: render.
 * PotionID / EntityID 削除 (1.13+ Registry化で整数ID不要, doc/config/migration-guide.md 1.20.1追補)。
 * 手動クランプは defineInRange で自動。
 * 本クラスは ModLoadingContext.registerConfig で登録 (DCsAppleMilk.java)。
 */
public class DCsConfig {

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Common COMMON;
    public static final Client CLIENT;

    static {
        Pair<Common, ForgeConfigSpec> commonPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonPair.getRight();
        COMMON = commonPair.getLeft();
        Pair<Client, ForgeConfigSpec> clientPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = clientPair.getRight();
        CLIENT = clientPair.getLeft();
    }

    // --- Legacy static fields retained for leaf code that still reads DCsConfig.xxx ---
    // They are synced from COMMON/CLIENT via sync() on FMLCommonSetupEvent.
    // PotionID / EntityID は 1.20.1で削除されたが、旧 leaf のコンパイル shim として 0 を保持 (WT-B/Cが削除するまで)。
    @Deprecated public static int potionIDImmunity = 60;
    @Deprecated public static int potionIDPrvExplode = 61;
    @Deprecated public static int potionIDPrvProjectile = 62;
    @Deprecated public static int potionIDReflex = 63;
    @Deprecated public static int potionIDAbsEXP = 64;
    @Deprecated public static int potionIDAbsHeal = 65;
    @Deprecated public static int potionIDSuffocation = 66;
    @Deprecated public static int potionIDPrvSuffocation = 67;
    @Deprecated public static int potionIDHallucinations = 68;
    @Deprecated public static int potionIDConfinement = 69;

    @Deprecated public static int entityIdMelon = 160;
    @Deprecated public static int entityIdSilkMelon = 161;
    @Deprecated public static int entityIdKinoko = 162;
    @Deprecated public static int entityIdStun = 164;
    @Deprecated public static int entityIdIllusion = 165;
    @Deprecated public static int entityIdMissile = 166;
    @Deprecated public static int entityIdBullet = 167;
    @Deprecated public static int entityIdIce = 150;
    @Deprecated public static int entityIdCup = 151;
    @Deprecated public static int entityIdCup2 = 152;
    @Deprecated public static int entityIdBowl = 153;
    @Deprecated public static int entityIdBowlJP = 154;
    @Deprecated public static int entityIdSteak = 155;
    @Deprecated public static int entityIdCocktail = 156;
    @Deprecated public static int entityIdAlcohol = 157;
    @Deprecated public static int entityIdSandwich = 158;
    @Deprecated public static int entityIdTart = 159;
    @Deprecated public static int entityIdCocktail2 = 163;
    @Deprecated public static int entityIdCocktailSP = 168;
    @Deprecated public static int entityIdBaseSoup = 169;
    @Deprecated public static int villagerRecipeID = 15;
    @Deprecated public static int villagerRecipe2ID = 16;

    public static int teaTreeGenValue = 5;
    public static int clamChanceValue = 5;
    public static int princessChanceValue = 5;
    public static int setCupTexture = 1;
    public static int setAltTexturePass = 2;
    public static int teppannReadyTime = 30;
    public static int cupStackSize = 1;
    public static int charmRemain = 0;
    public static int batteryUpdate = 4;
    public static double setCupScale = 1.0D;
    public static int dustDif = 1;
    public static int chargeDif = 1;
    public static int exchangeDif = 2;
    public static boolean altModRecipe = true;
    public static int procDif = 1;
    public static int charmWarpKey = 0x2D;
    public static boolean useEXRecipe = false;
    public static boolean notGenTeaTree = false;
    public static boolean allowSlimeBallDic = true;
    public static boolean noRenderFoodsSteam = false;
    public static boolean disableFireSteater = false;
    public static boolean disableClam = false;
    public static boolean noWetGContainer = false;
    public static boolean teppannHardMode = false;
    public static boolean useSummerRender = false;
    public static boolean teppannRandomCookTime = false;
    public static boolean canExplodeMelon = true;
    public static boolean melonBreakBlock = false;
    public static boolean safetyChocolate = false;
    public static boolean allowInfinityWipes = true;
    public static boolean bonemealClam = true;
    public static boolean allowEdibleEntities = true;
    public static boolean fearMelon = false;
    public static boolean completeFearMelon = false;
    public static boolean[] enableMobBlock = { true, true, true, true, true };
    public static boolean hardLeatherRecipe = true;
    public static boolean disableMissileExplosion = false;
    public static boolean PvPProhibitionMode = false;
    public static boolean yuzuCropBurn = true;
    public static boolean useAltTeppanTex = false;
    public static String debugPass = "Input the password here";

    public static void sync() {
        // COMMON
        try { teaTreeGenValue = COMMON.teaTreeGenValue.get(); } catch (Exception ignored) {}
        try { clamChanceValue = COMMON.clamChanceValue.get(); } catch (Exception ignored) {}
        try { princessChanceValue = COMMON.princessChanceValue.get(); } catch (Exception ignored) {}
        try { teppannReadyTime = COMMON.teppannReadyTime.get(); } catch (Exception ignored) {}
        try { cupStackSize = COMMON.cupStackSize.get(); } catch (Exception ignored) {}
        try { charmRemain = COMMON.charmRemain.get(); } catch (Exception ignored) {}
        try { batteryUpdate = COMMON.batteryUpdate.get(); } catch (Exception ignored) {}
        try { dustDif = COMMON.dustDif.get(); } catch (Exception ignored) {}
        try { chargeDif = COMMON.chargeDif.get(); } catch (Exception ignored) {}
        try { exchangeDif = COMMON.exchangeDif.get(); } catch (Exception ignored) {}
        try { altModRecipe = COMMON.altModRecipe.get(); } catch (Exception ignored) {}
        try { procDif = COMMON.procDif.get(); } catch (Exception ignored) {}
        try { charmWarpKey = COMMON.charmWarpKey.get(); } catch (Exception ignored) {}
        try { useEXRecipe = COMMON.useEXRecipe.get(); } catch (Exception ignored) {}
        try { notGenTeaTree = COMMON.notGenTeaTree.get(); } catch (Exception ignored) {}
        try { allowSlimeBallDic = COMMON.allowSlimeBallDic.get(); } catch (Exception ignored) {}
        try { disableFireSteater = COMMON.disableFireSteater.get(); } catch (Exception ignored) {}
        try { disableClam = COMMON.disableClam.get(); } catch (Exception ignored) {}
        try { noWetGContainer = COMMON.noWetGContainer.get(); } catch (Exception ignored) {}
        try { teppannHardMode = COMMON.teppannHardMode.get(); } catch (Exception ignored) {}
        try { teppannRandomCookTime = COMMON.teppannRandomCookTime.get(); } catch (Exception ignored) {}
        try { canExplodeMelon = COMMON.canExplodeMelon.get(); } catch (Exception ignored) {}
        try { melonBreakBlock = COMMON.melonBreakBlock.get(); } catch (Exception ignored) {}
        try { safetyChocolate = COMMON.safetyChocolate.get(); } catch (Exception ignored) {}
        try { allowInfinityWipes = COMMON.allowInfinityWipes.get(); } catch (Exception ignored) {}
        try { bonemealClam = COMMON.bonemealClam.get(); } catch (Exception ignored) {}
        try { hardLeatherRecipe = COMMON.hardLeatherRecipe.get(); } catch (Exception ignored) {}
        try { disableMissileExplosion = COMMON.disableMissileExplosion.get(); } catch (Exception ignored) {}
        try { PvPProhibitionMode = COMMON.pvpProhibition.get(); } catch (Exception ignored) {}
        try { yuzuCropBurn = COMMON.yuzuCropBurn.get(); } catch (Exception ignored) {}
        try {
            List<? extends Boolean> l = COMMON.enableMobBlock.get();
            for (int i = 0; i < Math.min(l.size(), enableMobBlock.length); i++) enableMobBlock[i] = l.get(i);
        } catch (Exception ignored) {}
        try { debugPass = COMMON.debugPass.get(); } catch (Exception ignored) {}
        // CLIENT
        try { setCupTexture = CLIENT.setCupTexture.get(); } catch (Exception ignored) {}
        try { setAltTexturePass = CLIENT.setAltTexturePass.get(); } catch (Exception ignored) {}
        try { setCupScale = CLIENT.setCupScale.get(); } catch (Exception ignored) {}
        try { noRenderFoodsSteam = CLIENT.noRenderFoodsSteam.get(); } catch (Exception ignored) {}
        try { useSummerRender = CLIENT.useSummerRender.get(); } catch (Exception ignored) {}
        try { allowEdibleEntities = CLIENT.allowEdibleEntities.get(); } catch (Exception ignored) {}
        try { useAltTeppanTex = CLIENT.useAltTeppanTex.get(); } catch (Exception ignored) {}
        try { fearMelon = CLIENT.fearMelon.get(); } catch (Exception ignored) {}
        try { completeFearMelon = CLIENT.completeFearMelon.get(); } catch (Exception ignored) {}
        PropertyHandler.loadConfig();
    }

    public static class Common {
        public final ForgeConfigSpec.IntValue teaTreeGenValue;
        public final ForgeConfigSpec.IntValue clamChanceValue;
        public final ForgeConfigSpec.IntValue princessChanceValue;
        public final ForgeConfigSpec.IntValue teppannReadyTime;
        public final ForgeConfigSpec.IntValue cupStackSize;
        public final ForgeConfigSpec.IntValue charmRemain;
        public final ForgeConfigSpec.IntValue batteryUpdate;
        public final ForgeConfigSpec.IntValue dustDif;
        public final ForgeConfigSpec.IntValue chargeDif;
        public final ForgeConfigSpec.IntValue exchangeDif;
        public final ForgeConfigSpec.BooleanValue altModRecipe;
        public final ForgeConfigSpec.IntValue procDif;
        public final ForgeConfigSpec.IntValue charmWarpKey;
        public final ForgeConfigSpec.BooleanValue useEXRecipe;
        public final ForgeConfigSpec.BooleanValue notGenTeaTree;
        public final ForgeConfigSpec.BooleanValue allowSlimeBallDic;
        public final ForgeConfigSpec.BooleanValue disableFireSteater;
        public final ForgeConfigSpec.BooleanValue disableClam;
        public final ForgeConfigSpec.BooleanValue noWetGContainer;
        public final ForgeConfigSpec.BooleanValue teppannHardMode;
        public final ForgeConfigSpec.BooleanValue teppannRandomCookTime;
        public final ForgeConfigSpec.BooleanValue canExplodeMelon;
        public final ForgeConfigSpec.BooleanValue melonBreakBlock;
        public final ForgeConfigSpec.BooleanValue safetyChocolate;
        public final ForgeConfigSpec.BooleanValue allowInfinityWipes;
        public final ForgeConfigSpec.BooleanValue bonemealClam;
        public final ForgeConfigSpec.BooleanValue hardLeatherRecipe;
        public final ForgeConfigSpec.BooleanValue disableMissileExplosion;
        public final ForgeConfigSpec.BooleanValue pvpProhibition;
        public final ForgeConfigSpec.BooleanValue yuzuCropBurn;
        public final ForgeConfigSpec.ConfigValue<List<? extends Boolean>> enableMobBlock;
        public final ForgeConfigSpec.ConfigValue<String> debugPass;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("world setting");
            teaTreeGenValue = builder.comment("Set the generation probability of tea tree.(1-20) Default is 5.").defineInRange("TeaTreeGenProbability", 5, 1, 20);
            clamChanceValue = builder.comment("Set the generation probability of clam.(1-12) Default is 5.").defineInRange("ClamGenProbability", 5, 1, 12);
            princessChanceValue = builder.comment("Set the generation probability of princess clam. Default is 5.").defineInRange("PrincessChanceValue", 5, 1, 100);
            notGenTeaTree = builder.comment("Not generating tea tree on overworld.").define("NoGenTeaTree", false);
            disableClam = builder.comment("Not generating clams on overworld.").define("DisableClams", false);
            builder.pop();

            builder.push("setting");
            useEXRecipe = builder.comment("Add recipe for crafting tea tree sapling.").define("UseExtraRecipe", false);
            allowSlimeBallDic = builder.comment("Allow to add SlimeBall and Animalglue to Oredictionary.").define("AllowSlimeballOreDic", true);
            disableFireSteater = builder.comment("Disable recipe for crafting firestarter.").define("DisableFirestarter", false);
            noWetGContainer = builder.comment("Not weathering Gunpowder container.").define("NoWeatheringContainer", false);
            teppannHardMode = builder.comment("Enable time limit to get the food from the iron plate.").define("TeppannHardMode", false);
            teppannReadyTime = builder.comment("Set the length of time limit to get the food from the iron plate.(1-60)").defineInRange("TeppannReadyTime", 30, 1, 60);
            cupStackSize = builder.comment("Set stack size of filled cups. Choose from 1/3/8.").defineInRange("CupsStackSize", 1, 1, 8);
            teppannRandomCookTime = builder.comment("Enable randomly cooking time of iron plate.").define("RandomlyTeppannCookingTime", false);
            safetyChocolate = builder.comment("Disable explosion of the heartfelt chocolate gift.").define("SafetyChocolateGift", false);
            allowInfinityWipes = builder.comment("Allow the WipeBox generate a paper infinitely.").define("AllowInfinityWipes", true);
            bonemealClam = builder.comment("Allow to use the clam and the clam container as fertilizer.").define("AllowClamFertilizer", true);
            hardLeatherRecipe = builder.comment("Enable hard mode that make a leather from a rotten flesh.").define("HardModeLeatherRecipe", true);
            yuzuCropBurn = builder.comment("Yuzu crops can be burned in the chargeable appliance, and generate 80 charge.").define("YuzuCropBurnInDevice", true);
            enableMobBlock = builder.comment("Enable to add some compression recipes of mob drop items. Rotten flesh, Bone, Spider eye, Ender pearl, and Slime ball.").defineList("EnableMobDropContainer", Arrays.asList(true, true, true, true, true), o -> o instanceof Boolean);
            charmRemain = builder.comment("Set a limit on the number of times to use the Raden Charm (Wind). If 0, disable Hard Mode.").defineInRange("HardModeWindCharm", 0, 0, 100);
            charmWarpKey = builder.comment("Set key number for rapid warp by charm effect. Default key is X(45). If 0, disable.").defineInRange("CharmWarpKeyNumber", 0x2D, 0, 256);
            batteryUpdate = builder.comment("Set the update cycle tick of the device using the battery.").defineInRange("BatteryUpdateCycle", 4, 1, 100);
            debugPass = builder.comment("Input the password for starting in debug mode. This is only for developer.").define("DebugModePass", "Input the password here");
            builder.pop();

            builder.push("entity setting");
            canExplodeMelon = builder.comment("Allow the Compressed Melon explode.").define("EnableExplodeMelon", true);
            melonBreakBlock = builder.comment("Disable destruction by explosion of melon.").define("MelonNotBreakBlock", false);
            disableMissileExplosion = builder.comment("Disable explosion of missiles generated from the Fossil Scale.").define("DisableMissileExplosion", false);
            pvpProhibition = builder.comment("Disable the damage caused by items of this MOD against player.").define("PvPProhibitionMode", false);
            builder.pop();

            builder.push("difficulty setting");
            dustDif = builder.comment("Change difficulty of the JawCrusher recipe. 0:sweet 1:normal 2:bitter 3:hard").defineInRange("JawCrusherDustGen", 1, 0, 3);
            chargeDif = builder.comment("Change difficulty of the battery charge amount. 0:sweet 1:normal 2:bitter 3:hard").defineInRange("BatteryChargeGen", 1, 0, 3);
            exchangeDif = builder.comment("Change Rate of the charge exchange to the another energy. 0-4. smaller are fewer conversion, and bigger are need more energy to exchange to AMT-Charge.").defineInRange("ExchangeRateOfCharge", 2, 0, 4);
            altModRecipe = builder.comment("Enable Recipes added the another mod machines.").define("AnotherModRecipe", true);
            procDif = builder.comment("Change difficulty of the JawCrusher recipe tier. 0:sweet 1:normal 2:bitter").defineInRange("JawCrusherRecipeDifficulty", 1, 0, 2);
            builder.pop();
        }
    }

    public static class Client {
        public final ForgeConfigSpec.IntValue setCupTexture;
        public final ForgeConfigSpec.IntValue setAltTexturePass;
        public final ForgeConfigSpec.DoubleValue setCupScale;
        public final ForgeConfigSpec.BooleanValue noRenderFoodsSteam;
        public final ForgeConfigSpec.BooleanValue useSummerRender;
        public final ForgeConfigSpec.BooleanValue allowEdibleEntities;
        public final ForgeConfigSpec.BooleanValue useAltTeppanTex;
        public final ForgeConfigSpec.BooleanValue fearMelon;
        public final ForgeConfigSpec.BooleanValue completeFearMelon;

        public Client(ForgeConfigSpec.Builder builder) {
            builder.push("render setting");
            setCupTexture = builder.comment("Select the texture of the JP bowls. 1:cherry flowers, 2:blue pattern, 3:white porcelain").defineInRange("SetJPBowlTexture", 1, 1, 3);
            setAltTexturePass = builder.comment("Select the texture type number. 1:default(x16 tex), 2:use x32 tex").defineInRange("SetTextureTypeNumber", 2, 1, 3);
            setCupScale = builder.comment("Select the scale of drink entity (like a cup). 0.01-10.0").defineInRange("SetDrinkEntityScale", 1.0D, 0.01D, 10.0D);
            noRenderFoodsSteam = builder.comment("Not Render Steam on foods of this mod.").define("NotRenderFoodsSteam", false);
            useSummerRender = builder.comment("Use the Summer Rendering to the Cups.").define("UseSummerRendering", false);
            allowEdibleEntities = builder.comment("Some food blocks are placed as a entity. If false, these are placed as a block.").define("EnableEdibleEntity", true);
            useAltTeppanTex = builder.comment("Enable Alternate Teppan Texture like a mesh grill.").define("EnableAltTeppanTexture", false);
            fearMelon = builder.comment("Silky Melon can destroy all blocks except blocks that coordinates Y = 1.").define("SilkyMelonOfFear", false);
            completeFearMelon = builder.comment("Silky Melon can destroy all blocks include bedrock, and force it to drop.").define("SilkyMelonOfTheCompleteFear", false);
            builder.pop();
        }
    }

    // Legacy Configuration helper — retained only for call sites that expect config(Configuration) before sync,
    // but now no-ops. Leaf code that still calls new DCsConfig().config(cfg) will compile until WT migrates.
    @Deprecated
    public void config(Object cfg) {}
}
