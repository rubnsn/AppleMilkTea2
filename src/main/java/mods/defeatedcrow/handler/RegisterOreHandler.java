package mods.defeatedcrow.handler;

/**
 * 1.20.1: OreDict.registerOre は廃止。
 * 登録は datapack のタグJSON ({@code data/forge/tags/items}, {@code data/c/tags/items},
 * {@code data/defeatedcrow/tags/items}) + {@link TagHelper} へ移管。
 * <p>
 * 旧 {@code RegisterOreHandler.register()} は 1.7.10 の {@code DCsAppleMilk.*} static フィールド
 * (例 {@code inkStick}, {@code EXItems}, {@code leafTea} 等) を直接参照し、
 * 1.20.1の {@link mods.defeatedcrow.common.registry.ModItems}/{@link mods.defeatedcrow.common.registry.ModBlocks}
 * DeferredRegister では該当フィールドが存在しないため完全デッドコード。
 * 呼出元は0件 (grep: RegisterOreHandler) のため、スタブ化して削除予定とする。
 * </p>
 * 詳細は {@code doc/oredict-to-tagkey.md} の「マッピング規則」「タグJSONの構成」を参照。
 */
public class RegisterOreHandler {

    /**
     * 1.20.1 no-op stub.
     * 実体は datapack タグJSONで代替:
     * <ul>
     *   <li>{@code src/main/resources/data/forge/tags/items/dyes/black.json} etc.</li>
     *   <li>{@code src/main/resources/data/c/tags/items/dyes/black.json} etc.</li>
     *   <li>{@code src/main/resources/data/defeatedcrow/tags/items/...} (独自タグ)</li>
     * </ul>
     * 参照側は {@link TagHelper#getTagItems(String)} / {@link TagHelper#forgeTag(String)}
     * + {@link net.minecraft.world.item.crafting.Ingredient#of(net.minecraft.tags.TagKey)} を使う。
     */
    public void register() {
        // no-op: tags are data-driven in 1.20.1
    }
}

