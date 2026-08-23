package mods.defeatedcrow.handler;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * WT-B helper: OreDict → TagKey bridge (1.20.1 Forge 47.x).
 * <p>
 * 旧 {@code OreDict.registerOre / getOres / doesOreNameExist / itemMatches / WILDCARD_VALUE}
 * を {@code TagKey<Item>} + datapack JSON ({@code data/forge/tags/items}, {@code data/c/tags/items},
 * {@code data/defeatedcrow/tags/items}) + 単純 {@code ItemStack.is(...)} 比較へ置換する。
 * </p>
 * <p>
 * マッピング規則は {@code doc/oredict-to-tagkey.md} に準拠。
 * {@link #oreNameToTagPath(String)} が prefix → ディレクトリ変換を担う。
 * 実タグの値列挙は datapack JSON 側で行い、取得は {@link BuiltInRegistries#ITEM} 経由。
 * </p>
 * <p>
 * 1.20.1ではメタ/ダメージ値差分は個別 {@code Item} 化が前提のため、WILDCARD 概念は廃止。
 * 旧 {@code WILDCARD_VALUE (=32767)} 判定は「同一 {@code Item} なら一致」と等価。
 * </p>
 */
public final class TagHelper {

    private TagHelper() {}

    // ------------------------------------------------------------ ore → tag path

    /**
     * 旧 OreDict 名 (camelCase) → タグパス ({@code dyes/black}, {@code dusts/wood} 等)。
     * {@code doc/oredict-to-tagkey.md} の「マッピング規則」表を実装。
     */
    public static String oreNameToTagPath(String ore) {
        if (ore == null || ore.isEmpty()) return "";
        // ルールが長いので優先順位: 長いprefixから判定

        // tool / gear / plate / stick 等
        if (ore.startsWith("tool")) return "tools/" + toSnake(ore.substring(4));
        if (ore.startsWith("gear")) return "gears/" + toSnake(ore.substring(4));
        if (ore.startsWith("plate")) return "plates/" + toSnake(ore.substring(5));
        if (ore.startsWith("stick")) return "rods/" + toSnake(ore.substring(5));
        // dust / nugget / gem / ingot / ore
        if (ore.startsWith("dust")) return "dusts/" + toSnake(ore.substring(4));
        if (ore.startsWith("nugget")) return "nuggets/" + toSnake(ore.substring(6));
        if (ore.startsWith("gem")) return "gems/" + toSnake(ore.substring(3));
        if (ore.startsWith("ingot")) return "ingots/" + toSnake(ore.substring(5));
        if (ore.startsWith("ore") && ore.length() > 3 && Character.isUpperCase(ore.charAt(3)))
            return "ores/" + toSnake(ore.substring(3));
        // block -> storage_blocks
        if (ore.startsWith("block")) return "storage_blocks/" + toSnake(ore.substring(5));
        // dye
        if (ore.startsWith("dye")) return "dyes/" + toSnake(ore.substring(3));
        // treeSapling / sapling
        if (ore.equals("treeSapling")) return "saplings";
        if (ore.startsWith("sapling")) {
            String rest = ore.substring(7);
            return rest.isEmpty() ? "saplings" : "saplings/" + toSnake(rest);
        }
        if (ore.equals("logWood")) return "logs";
        if (ore.equals("treeLeaves")) return "leaves";
        if (ore.equals("slimeball")) return "slimeballs";
        // bucket / bottle -> buckets/bottles
        if (ore.startsWith("bucket")) return "buckets/" + toSnake(ore.substring(6));
        if (ore.startsWith("bottle")) return "bottles/" + toSnake(ore.substring(6));
        // foodBlock* -> foods/blocks/* (defeatedcrow固有、forge側はfoods/*にもエイリアス)
        if (ore.startsWith("foodBlock")) return "foods/blocks/" + toSnake(ore.substring(9));
        // food* -> foods/*
        if (ore.startsWith("food")) return "foods/" + toSnake(ore.substring(4));
        // crop* / cooking* -> crops/*
        if (ore.startsWith("crop")) return "crops/" + toSnake(ore.substring(4));
        if (ore.startsWith("cooking")) return "crops/" + toSnake(ore.substring(7));
        // item* -> defeatedcrow:items/*
        if (ore.startsWith("item")) return "items/" + toSnake(ore.substring(4));
        // その他独自 (dropHoney, listAll*, bamboo, etc.) はそのまま小文字スネークへ
        // listAll* は forge 互換の一般名なのでそのまま
        return toSnake(ore);
    }

    private static String toSnake(String camel) {
        if (camel == null || camel.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < camel.length(); i++) {
            char c = camel.charAt(i);
            if (Character.isUpperCase(c)) {
                if (sb.length() > 0) sb.append('_');
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString().toLowerCase(Locale.ROOT);
    }

    // ------------------------------------------------------------ tag queries

    public static TagKey<Item> forgeTag(String oreName) {
        return TagKey.create(Registries.ITEM, new ResourceLocation("forge", oreNameToTagPath(oreName)));
    }

    public static TagKey<Item> cTag(String oreName) {
        return TagKey.create(Registries.ITEM, new ResourceLocation("c", oreNameToTagPath(oreName)));
    }

    public static TagKey<Item> defeatedcrowTag(String oreName) {
        return TagKey.create(Registries.ITEM, new ResourceLocation("defeatedcrow", oreNameToTagPath(oreName)));
    }

    /**
     * 指定 ore 名に対応する {@code TagKey} 群 (forge/c/defeatedcrow) からアイテム一覧を取得。
     * datapack がロードされるまでは空を返すことがある (タグはサーバ起動後に解決)。
     */
    public static List<ItemStack> getTagItems(String oreName) {
        if (oreName == null || oreName.isEmpty()) return Collections.emptyList();
        // forge → c → defeatedcrow の順に試し、最初にヒットしたタグの内容を返す
        // 全タグをマージする方が厳密だが、重複排除が必要なため forge を優先
        for (TagKey<Item> key : new TagKey[]{forgeTag(oreName), cTag(oreName), defeatedcrowTag(oreName)}) {
            List<ItemStack> list = getTagItems(key);
            if (!list.isEmpty()) return list;
        }
        return Collections.emptyList();
    }

    public static List<ItemStack> getTagItems(TagKey<Item> key) {
        return BuiltInRegistries.ITEM.getTag(key)
            .map(set -> set.stream().map(holder -> new ItemStack(holder.value())).collect(Collectors.toList()))
            .orElse(Collections.emptyList());
    }

    public static boolean isInTag(ItemStack stack, String oreName) {
        if (stack == null || stack.isEmpty() || oreName == null) return false;
        return isInTag(stack, forgeTag(oreName)) || isInTag(stack, cTag(oreName)) || isInTag(stack, defeatedcrowTag(oreName));
    }

    public static boolean isInTag(ItemStack stack, TagKey<Item> key) {
        if (stack == null || stack.isEmpty()) return false;
        return stack.is(key);
    }

    // ------------------------------------------------------------ itemMatches replacement

    /**
     * 旧 {@code OreDict.itemMatches(a, b, false)} の置換。
     * 1.20.1ではダメージ/メタ差分は個別Item化のため、同一Itemか否かのみで判定。
     * NBT 厳密一致が必要な場合は {@link ItemStack#isSameItemSameTags} を別途使う。
     */
    public static boolean itemMatches(ItemStack a, ItemStack b, boolean useTags) {
        if (a == null || b == null || a.isEmpty() || b.isEmpty()) return false;
        // useTags=false はオリジナルの厳密一致だが、現行はNBT無視の簡易一致で十分
        return a.is(b.getItem());
    }

    public static boolean itemMatches(ItemStack a, ItemStack b) {
        return itemMatches(a, b, false);
    }

    // ------------------------------------------------------------ WILDCARD helper (obsolete)

    /**
     * 旧 {@code getDamageValue() == OreDict.WILDCARD_VALUE} 判定の置換。
     * 1.20.1では wildcard は不要。呼び出し側は単に {@code a.is(b.getItem())} を使えばよい。
     * 互換のため残すが常に {@code false} (wildcard無し) を返す。
     */
    @Deprecated
    public static boolean isWildcardDamage(int damage) {
        return false;
    }
}

