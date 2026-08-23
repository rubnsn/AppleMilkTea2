package mods.defeatedcrow.plugin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.fml.ModList;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.handler.Util;

/**
 * ExBucket ("AndanteMod_ExBucket") 連携。
 *
 * 1.20.1対応版なし (Omit確定) だが、外部MOD APIへの直接依存がなく
 * 文字列lookupのみのため ModList ガード付きの発見処理として維持する。
 *
 * 1.20.1移行により、GameRegistry.addRecipe / ShapedOreRecipe / ShapelessOreRecipe
 * によるレシピ登録は全て削除した (レシピは recipe/ 担当、datapack駆動へ移行)。
 */
public class LoadExBucketPlugin {

    // TODO: 実modID要確認 (旧 Util.getModItem の第1引数はmod名)
    private static final String EXBUCKET_MODID = "andexbucket";

    public static ItemStack woodenBucketMilk;
    public static ItemStack goldenBucketMilk;

    public void load() {
        if (!ModList.get().isLoaded(EXBUCKET_MODID)) {
            return;
        }
        try {
            Item item = Util.getModItem("AndanteMod_ExBucket", "WoodenBucketMilk");
            if (item != null) {
                woodenBucketMilk = new ItemStack(item);
                LoadModHandler.registerModItems("containerMilk", woodenBucketMilk);
                AMTLogger.debugInfo("Succeeded to get WoodenBucketMIlk");
            }

            Item item2 = Util.getModItem("AndanteMod_ExBucket", "GoldenBucketMilk");
            if (item2 != null) {
                goldenBucketMilk = new ItemStack(item2);
                LoadModHandler.registerModItems("containerMilk", goldenBucketMilk);
                AMTLogger.debugInfo("Succeeded to get GoldenBucketMilk");
            }

            // 別のレシピ用の水バケツ
            Item item3 = Util.getModItem("AndanteMod_ExBucket", "WoodenBucketWater");
            if (item3 != null) {
                if (LoadModHandler.registerModItems("containerWater", new ItemStack(item3))) {
                    AMTLogger.debugInfo("Succeeded to get WoodenBucketWater");
                }
            }
            Item item4 = Util.getModItem("AndanteMod_ExBucket", "GoldenBucketWater");
            if (item4 != null) {
                if (LoadModHandler.registerModItems("containerWater", new ItemStack(item4))) {
                    AMTLogger.debugInfo("Succeeded to get GoldenBucketWater");
                }
            }
        } catch (Exception e) {
            AMTLogger.debugInfo("Failed to register ModItems");
            e.printStackTrace(System.err);
        }
    }

}
