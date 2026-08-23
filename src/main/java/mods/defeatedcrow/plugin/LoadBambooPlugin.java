package mods.defeatedcrow.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.world.item.ItemStack;

import net.minecraftforge.fml.ModList;

/**
 * BambooMod ("bamboo") 連携プラグイン。
 *
 * 1.20.1では Bamboo の対応版が存在するため破棄せず保留 (deferred) としている。
 * ruby.bamboo.api.* への直接依存 (CookingRegistory / GrindRegistory) を削除し、
 * bamboo jar が存在しなくてもコンパイル・ロード可能な no-op シェルに縮退した。
 * API差分の検証が完了し依存座標が判明した後、compileOnly + ModList ガードで再統合すること。
 *
 * getBasket() / isBasketItem(ItemStack) / addJapaneseBowlContainer(ItemStack) は
 * 本MOD内の公開APIとして維持する (BlockEmptyPanG / BlockBowlJP / EntityItemBowlJP が使用)。
 */
public class LoadBambooPlugin {

    public static final String BAMBOO_MODID = "bamboo";

    private static ArrayList<ItemStack> baskets = new ArrayList<ItemStack>();

    public static List<ItemStack> getBasket() {
        return Collections.unmodifiableList(baskets);
    }

    public static int isBasketItem(ItemStack item) {
        if (item == null || item.isEmpty() || baskets.isEmpty()) return -1;
        for (int i = 0; i < baskets.size(); i++) {
            ItemStack basket = baskets.get(i);
            if (basket.getItem() == item.getItem() && basket.getDamageValue() == item.getDamageValue()) {
                return i;
            }
        }
        return -1;
    }

    public static void addJapaneseBowlContainer(ItemStack item) {
        if (item != null && !item.isEmpty()) {
            baskets.add(item);
        }
    }

    public static boolean isBambooLoaded() {
        return ModList.get().isLoaded(BAMBOO_MODID);
    }

    /**
     * BambooModのアイテム取り込み。
     * 1.20.1再統合まで no-op。rawrice/bamboobasket/campfire 等の取得と
     * タグ登録・熱源登録は再統合時に復帰させる。
     */
    public void loadBambooItems() {
        // deferred: pending Bamboo 1.20.1 API re-integration
    }

    /**
     * BambooMod様の石臼、囲炉裏にレシピを追加。
     * 1.20.1再統合まで no-op。GrindRegistory/CookingRegistory への登録は再統合時に復帰させる。
     */
    public static void loadBambooRecipes(boolean flag) {
        // deferred: pending Bamboo 1.20.1 API re-integration
    }

}
