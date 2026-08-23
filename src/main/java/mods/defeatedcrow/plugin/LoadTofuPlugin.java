package mods.defeatedcrow.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.fml.ModList;

/**
 * TofuCraft 連携。
 *
 * 1.20.1対応版なし (Omit確定)。外部MOD APIへの直接依存はないためクラス自体は維持し、
 * ModList ガードで自動スキップされる縮退版とする。
 * タグ (旧鉱石辞書名 tofuKinu / bucketSoymilk / leek 相当) 経由での取得のみ残し、
 * タグがdatapack側で定義されない限りリストは空となり何も登録されない。
 */
public class LoadTofuPlugin {

    // TODO: 実modID要確認
    public static final String TOFU_MODID = "tofucraft";

    public static ItemStack tofuKinu;
    public static ItemStack bucketTounyu;
    public static ItemStack negi;

    private static List<ItemStack> getTagItems(String tagPath) {
        TagKey<Item> key = TagKey.create(Registries.ITEM, new ResourceLocation("forge", tagPath));
        return BuiltInRegistries.ITEM.getTag(key)
            .map(set -> set.stream()
                .map(holder -> new ItemStack(holder.value()))
                .collect(Collectors.toList()))
            .orElse(Collections.emptyList());
    }

    public void load() {
        // 1.20.1では TofuCraft 非対応のため基本ここで抜ける
        if (!ModList.get().isLoaded(TOFU_MODID)) {
            return;
        }

        List<ItemStack> kinu = getTagItems("tofu_kinu");
        List<ItemStack> tounyu = getTagItems("bucket_soymilk");
        List<ItemStack> naganegi = getTagItems("crops/leek");

        if (!kinu.isEmpty()) {
            tofuKinu = kinu.get(0).copy();
        }
        if (!tounyu.isEmpty()) {
            bucketTounyu = tounyu.get(0).copy();
            LoadModHandler.registerModItems("bucketSoy", bucketTounyu);
        }
        if (!naganegi.isEmpty()) {
            negi = naganegi.get(0).copy();
        }
    }

}
