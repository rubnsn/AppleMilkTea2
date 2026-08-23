package mods.defeatedcrow.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import net.minecraft.world.item.ItemStack;

/**
 * 他MOD様のアイテムの収集・管理用クラス。
 * 当MOD専用の登録名 (独自名) で ItemStack を紐付け、一括管理する。
 *
 * 1.20.1移行により、以下を削除した:
 * - Omit確定連携 (Gummi / GrowthCraft / MapleTree / SugiForest / DartCraft / ExtraTrees / Wa /
 *   EnchantChanger) の個別取得メソッド。該当MODは1.20.1非対応のため削除
 *   (doc/plugins/migration-guide.md「Omit確定」参照)
 * - GameRegistry.addRecipe / ShapedOreRecipe / ShapelessOreRecipe によるレシピ登録
 *   (レシピは recipe/ 担当、datapack/RecipeSerializer 駆動へ移行)
 * - OreDictionary.registerOre による辞書登録 (TagKey/datapack 側へ移管)
 * - 旧FMLレジストリ依存の除去
 *
 * 登録名 "seaWeed" / "containerWater" / "DCsBakedApple" 等は
 * recipe/RegisterMakerRecipe, common/DCsRecipeRegister, event/EatFoodEvent,
 * common/block/container/ItemWoodBox から参照されているため、アクセサは維持する。
 */
public class LoadModHandler {

    private static HashMap<String, ArrayList<ItemStack>> modItems = new HashMap<String, ArrayList<ItemStack>>();

    private static Random rand = new Random();

    /**
     * Stringを引数にしてアイテムを取得。
     * Stringは他MOD様とは無関係な当MOD専用の登録名。
     * 失敗時にはnullを返す。
     */
    public static ItemStack getItem(String name) {
        ArrayList<ItemStack> ret = modItems.get(name);
        if (ret != null && !ret.isEmpty()) return ret.get(0);
        else return null;
    }

    /**
     * Stringを引数にしてアイテムを取得。
     * Stringは他MOD様とは無関係な当MOD専用の登録名。
     * 失敗時にはnullを返す。
     */
    public static ArrayList<ItemStack> getArray(String name) {
        ArrayList<ItemStack> ret = modItems.get(name);
        if (ret != null && !ret.isEmpty()) return ret;
        else return null;
    }

    /**
     * Stringを引数にしてアイテムを取得。
     * こちらは登録されたItemStackのうち一つをランダムに返す。
     * 失敗時にはnullを返す。
     */
    public static ItemStack getRandomItem(String name) {
        ArrayList<ItemStack> ret = modItems.get(name);
        if (ret != null && !ret.isEmpty()) {
            int random = rand.nextInt(ret.size());
            return ret.get(random);
        } else return null;
    }

    /**
     * Stringを引数にしてアイテムを取得し、
     * targetのアイテムと同一かどうかを返すメソッド。
     * 登録済みアイテムのいずれかと一致すればtrueを返す。
     */
    public static boolean matchItem(String name, ItemStack target) {
        ArrayList<ItemStack> ret = modItems.get(name);

        if (ret == null || ret.isEmpty() || target == null || target.isEmpty()) return false;

        for (ItemStack items : ret) {
            if (items.getItem() == target.getItem() && items.getDamageValue() == target.getDamageValue()) {
                return true;
            }
        }

        return false;
    }

    /**
     * このクラスのHashMapを使って他MOD様のアイテムを独自名に紐付け、一括管理する。
     * 
     * @param name
     *             独自名
     * @param item
     *             登録対象
     */
    public static boolean registerModItems(String name, ItemStack item) {
        if (name != null && item != null && !item.isEmpty()) {
            ArrayList<ItemStack> list = modItems.get(name);
            if (list != null) {
                list.add(item.copy());
                modItems.put(name, list);
            } else {
                ArrayList<ItemStack> val = new ArrayList<ItemStack>();
                val.add(item.copy());
                modItems.put(name, val);
            }
            return true;
        }
        return false;
    }

    /**
     * このクラスのHashMapを使って他MOD様のアイテムを独自名に紐付け、一括管理する。
     * ArrayList型で登録する場合に使用するメソッド。
     * 
     * @param name
     *                独自名
     * @param newList
     *                登録対象
     */
    public static boolean registerArray(String name, ArrayList<ItemStack> newList) {
        if (name != null && newList != null && !newList.isEmpty()) {
            ArrayList<ItemStack> list = modItems.get(name);
            if (list != null) {
                list.addAll(newList);
                modItems.put(name, list);
            } else {
                ArrayList<ItemStack> val = new ArrayList<ItemStack>();
                val.addAll(newList);
                modItems.put(name, val);
            }
            return true;
        }
        return false;
    }

}
