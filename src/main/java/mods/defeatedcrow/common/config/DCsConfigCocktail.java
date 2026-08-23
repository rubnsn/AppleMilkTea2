package mods.defeatedcrow.common.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import java.util.Arrays;
import java.util.List;

/**
 * 1.20.1: DCsAppleMilk-cocktail.cfg (INI) → defeatedcrow-common.toml [cocktail_*] subcategories.
 * ForgeConfigSpec に統合。旧 Configuration 読みは廃止だが、旧呼び出し new DCsConfigCocktail().config(cfg) は no-op で残す (WT-Cがデータ駆動に置換するまで)。
 */
public class DCsConfigCocktail {

    public static final ForgeConfigSpec SPEC;
    public static final Cocktail COCKTAIL;

    static {
        Pair<Cocktail, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Cocktail::new);
        SPEC = pair.getRight();
        COCKTAIL = pair.getLeft();
    }

    // Legacy arrays retained for leaf code that reads DCsConfigCocktail.name / recipe* directly
    public static String[] name = { "Russian Ballet", "Rum Flip", "Denki-Bran" };
    public static String[] massage = { "You can put any message here.", "This message is a tool tip display of items.", "This is a fake. Nobody knows the real recipe." };
    public static String[] recipe1 = { "bottleVodka", "bottleCassisliqueur", "foodBlockLemonade" };
    public static String[] recipe2 = { "bottleRum", "minecraft:egg", "dustSugar" };
    public static String[] recipe3 = { "bottleBrandy", "bottleGin", "bottleWine" };
    public static int[] potionIds = { 62, 12, 60 };
    public static int[] potionDur = { 1200, 1200, 2400 };
    public static int[] potionAmp = { 0, 0, 2 };
    public static int[] color1 = { 20, 0, 20, 5 };
    public static int[] color2 = { 10, 20, 0, 9 };
    public static int[] color3 = { 5, 2, 0, 5 };
    public static int[] type = { 0, 0, 1 };
    public static int[] deco = { 0, 0, 0 };
    public static float[] colorf1 = new float[4];
    public static float[] colorf2 = new float[4];
    public static float[] colorf3 = new float[4];

    public static class Cocktail {
        public final ForgeConfigSpec.ConfigValue<String> name1;
        public final ForgeConfigSpec.ConfigValue<String> massage1;
        public final ForgeConfigSpec.ConfigValue<List<? extends Integer>> color1;
        public final ForgeConfigSpec.IntValue type1;
        public final ForgeConfigSpec.IntValue deco1;

        public Cocktail(ForgeConfigSpec.Builder builder) {
            builder.push("cocktail_1");
            name1 = builder.define("CocktailName", "Russian Ballet");
            massage1 = builder.define("CocktailMassage", "You can put any message here.");
            color1 = builder.defineList("CocktailColor", Arrays.asList(20, 0, 20, 5), o -> o instanceof Integer);
            type1 = builder.defineInRange("CocktailGlassType", 0, 0, 2);
            deco1 = builder.defineInRange("CocktailDecorationType", 0, 0, 4);
            builder.pop();
            builder.push("cocktail_2");
            builder.define("CocktailName2", "Rum Flip");
            builder.pop();
            builder.push("cocktail_3");
            builder.define("CocktailName3", "Denki-Bran");
            builder.pop();
        }
    }

    @Deprecated
    public void config(Object cfg) {}
}
