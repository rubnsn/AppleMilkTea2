package mods.defeatedcrow.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.registry.ModFluids;
import mods.defeatedcrow.common.registry.ModItems;

/**
 * タグ (旧TagKey) に登録されているアイテムを、使う分だけゲームのロード時にまとめて読み込み、
 * このクラス内で管理する。
 *
 * 1.20.1移行: TagKey.getOres → TagKey + BuiltInRegistries.ITEM のタグ検索に置換。
 * タグパスは forge 標準命名 (crops/almond 等) の仮置きであり、実タグはdatapack側で定義する。
 */
public class LoadOreDicHandler {

    private static ArrayList<ItemStack> listAlmond = new ArrayList<ItemStack>();
    private static ArrayList<ItemStack> listPeanut = new ArrayList<ItemStack>();
    private static ArrayList<ItemStack> listNuts = new ArrayList<ItemStack>();
    private static ArrayList<ItemStack> listCherry = new ArrayList<ItemStack>();
    private static ArrayList<ItemStack> listStraw = new ArrayList<ItemStack>();
    private static ArrayList<ItemStack> listBerry = new ArrayList<ItemStack>();
    private static ArrayList<ItemStack> listBanana = new ArrayList<ItemStack>();
    private static ArrayList<ItemStack> listRice = new ArrayList<ItemStack>();
    private static ArrayList<ItemStack> listHoney = new ArrayList<ItemStack>();
    private static ArrayList<ItemStack> listSoy = new ArrayList<ItemStack>();
    private static ArrayList<ItemStack> listSeaweed = new ArrayList<ItemStack>();

    private static List<ItemStack> getTagItems(String tagPath) {
        TagKey<net.minecraft.world.item.Item> key = TagKey.create(Registries.ITEM, new ResourceLocation("forge", tagPath));
        return BuiltInRegistries.ITEM.getTag(key)
            .map(set -> set.stream()
                .map(holder -> new ItemStack(holder.value()))
                .collect(Collectors.toList()))
            .orElse(Collections.emptyList());
    }

    public void load() {

        listAlmond.addAll(getTagItems("crops/almond"));
        listPeanut.addAll(getTagItems("crops/peanut"));
        listNuts.addAll(getTagItems("crops/walnut"));
        listNuts.addAll(getTagItems("crops/hazelnut"));
        listNuts.addAll(getTagItems("crops/coconut"));
        listCherry.addAll(getTagItems("crops/cherry"));
        listStraw.addAll(getTagItems("crops/strawberry"));
        listBerry.addAll(getTagItems("crops/raspberry"));
        listBerry.addAll(getTagItems("crops/cranberry"));
        listBerry.addAll(getTagItems("crops/blueberry"));
        listBerry.addAll(getTagItems("crops/blackberry"));
        listBerry.addAll(getTagItems("crops/cassis"));
        listBanana.addAll(getTagItems("crops/banana"));
        listRice.addAll(getTagItems("crops/rice"));
        listHoney.addAll(getTagItems("honey"));
        listSoy.addAll(getTagItems("crops/soybeans"));
        listSeaweed.addAll(getTagItems("crops/seaweed"));

        /**
         * 当MOD用の管理Mapへの登録。
         * タグ名とは異なる名前で登録している。
         */
        if (listAlmond != null && listAlmond.isEmpty()) LoadModHandler.registerArray("nuts", listAlmond);
        if (listPeanut != null && listPeanut.isEmpty()) LoadModHandler.registerArray("nuts", listPeanut);
        if (listNuts != null && !listNuts.isEmpty()) LoadModHandler.registerArray("nuts", listNuts);
        if (listCherry != null && !listCherry.isEmpty()) LoadModHandler.registerArray("cherry", listCherry);
        if (listBerry != null && !listBerry.isEmpty()) LoadModHandler.registerArray("berry", listBerry);
        if (listStraw != null && !listStraw.isEmpty()) LoadModHandler.registerArray("strawberry", listStraw);
        if (listBanana != null && !listBanana.isEmpty()) LoadModHandler.registerArray("banana", listBanana);
        if (listRice != null && !listRice.isEmpty()) LoadModHandler.registerArray("rice", listRice);
        if (listHoney != null && !listHoney.isEmpty()) LoadModHandler.registerArray("honey", listHoney);
        if (listSoy != null && !listSoy.isEmpty()) LoadModHandler.registerArray("soy", listSoy);
        if (listSeaweed != null && !listSeaweed.isEmpty()) LoadModHandler.registerArray("seaWeed", listSeaweed);

        /**
         * 以下、登録したリストを使った追加レシピ登録。
         */
        for (ItemStack soy : listSoy) {
            if (!soy.isEmpty()) {

                RecipeRegisterManager.evaporatorRecipe.addRecipe(
                    new ItemStack(ModItems.WOOD_DUST.get(), 3),
                    new net.minecraft.world.level.material.FluidStack(ModFluids.VEGITABLE_OIL_SOURCE.get(), 25),
                    soy);
            }
        }

    }

    public static boolean isAlmond(ItemStack itemstack) {
        boolean flag = false;
        flag = matchItems(listAlmond, itemstack);
        if (flag) AMTLogger.debugInfo("get:almond");
        return flag;
    }

    public static boolean isPeanut(ItemStack itemstack) {
        boolean flag = false;
        flag = matchItems(listPeanut, itemstack);
        if (flag) AMTLogger.debugInfo("get:peanut");
        return flag;
    }

    public static boolean isNuts(ItemStack itemstack) {
        boolean flag = false;
        flag = matchItems(listNuts, itemstack);
        if (flag) AMTLogger.debugInfo("get:nuts");
        return flag;
    }

    public static boolean isCherry(ItemStack itemstack) {
        boolean flag = false;
        flag = matchItems(listCherry, itemstack);
        if (flag) AMTLogger.debugInfo("get:cherry");
        return flag;
    }

    public static boolean isStraw(ItemStack itemstack) {
        boolean flag = false;
        flag = matchItems(listStraw, itemstack);
        if (flag) AMTLogger.debugInfo("get:strawberry");
        return flag;
    }

    public static boolean isBerry(ItemStack itemstack) {
        boolean flag = false;
        flag = matchItems(listBerry, itemstack);
        if (flag) AMTLogger.debugInfo("get:berrys");
        return flag;
    }

    public static boolean isBanana(ItemStack itemstack) {
        boolean flag = false;
        flag = matchItems(listBanana, itemstack);
        if (flag) AMTLogger.debugInfo("get:banana");
        return flag;
    }

    public static boolean isRice(ItemStack itemstack) {
        boolean flag = false;
        flag = matchItems(listRice, itemstack);
        if (flag) AMTLogger.debugInfo("get:rice");
        return flag;
    }

    private static boolean matchItems(ArrayList<ItemStack> list, ItemStack items) {
        for (ItemStack checks : list) {
            if (checks != null && items != null && !items.isEmpty()
                && (items.getItem() == checks.getItem() && items.getDamageValue() == checks.getDamageValue())) {
                return true;
            }
        }
        return false;
    }
}
