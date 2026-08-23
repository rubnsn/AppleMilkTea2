# OreDictionary → TagKey 移行規約（1.20.1）

> 最終更新: 2026-08-24
> 方針: 登録側 `OreDictionary.registerOre` を廃止し、datapack のタグJSON + `TagKey<Item>` 参照へ置換。
> 規約: `forge:*` と `c:*` の両方へ登録（他MOD互換のため）。

## マッピング規則（旧OreDict名 → タグパス）

旧 Oredict 名は情報量（prefix と素材カテゴリ）を持つ。以下で `forge:` / `c:` タグへ写像する。

| 旧OreDict prefix | カテゴリ | forge: タグパス例 | c: タグパス例 |
|---|---|---|---|
| `crop*` / `cooking*` / `food*` / `foodBlock*` | 作物・食品 | `forge:crops/*`, `forge:foods/*` | `c:crops/*`, `c:foods/*` |
| `dust*` | 粉 | `forge:dusts/*` | `c:dusts/*` |
| `nugget*` | ナゲット | `forge:nuggets/*` | `c:nuggets/*` |
| `gem*` | 宝石 | `forge:gems/*` | `c:gems/*` |
| `ingot*` | インゴット | `forge:ingots/*` | `c:ingots/*` |
| `ore*` | 鉱石ブロック | `forge:ores/*` | `c:ores/*` |
| `block*` | ブロック | `forge:storage_blocks/*` | `c:storage_blocks/*` |
| `gear*` | 歯車 | `forge:gears/*` | `c:gears/*` |
| `plate*` | 板材 | `forge:plates/*` | `c:plates/*` |
| `stick*` | 棒 | `forge:rods/*` | `c:rods/*` |
| `dye*` | 染料 | `forge:dyes/*` | `c:dyes/*` |
| `tool*` | 道具 | `forge:tools/*` | `c:tools/*` |
| `treeSapling` / `sapling*` | 苗木 | `forge:saplings/*` | `c:saplings/*` |
| `logWood` | 原木 | `forge:logs/*` | `c:logs/*` |
| `treeLeaves` | 葉 | `forge:leaves/*` | `c:leaves/*` |
| `bucket*` | バケツ | `forge:buckets/*` | `c:buckets/*` |
| `bottle*` | 瓶 | `forge:bottles/*` | `c:bottles/*` |
| `slimeball` | スライム | `forge:slimeballs` | `c:slimeballs` |
| `item*` / その他独自 | 独自成分 | `defeatedcrow:*` | — |

## 旧OreDict名の camelCase → タグパス(lowercase + `/` + snake/multipart)

1. prefix (頭のカテゴリ語) をスラッシュ区切りのディレクトリにする。
2. 残りは小文字化。固有名詞境界 (`Camellia`, `EarlGray`) はそのまま小文字連結。
3. `defeatedcrow:` 独自タグは旧名をそのまま小文字化して使う（例 `foodBlockMilkTea` → `defeatedcrow:foods/blocks/milk_tea` 等、実体は対象アイテムの registryName を `values` に列挙）。

## 実装状態 (2026-08-24 WT-B)

- `handler/RegisterOreHandler.java` は完全デッドコード（1.7.10 `DCsAppleMilk.*` 参照 + 呼出元0件）のため **スタブ化**（`register()` no-op）。登録は datapack タグJSONへ移管。`handler/TagHelper.java` が `oreName → TagKey` 変換 + `getTagItems` / `itemMatches` / `WILDCARD` 置換を集約。
- `handler/Util.java:189` の `getOreStack` / `doesOreNameExist` / `getOreTag` を TagHelper 委譲に置換。`getModItem/getModBlock` を `BuiltInRegistries` 化、`checkCurrentBiome` / `addPotionEffectDC` を 1.20.1 Holder/MobEffectInstance 化、`notEmptyItem` を `!isEmpty()` 化、TEX_PASS 系をスタブ化。`handler/FluidContMap.java:68,107` の `WILDCARD_VALUE` 分岐を `is(Item)` 単純一致へ置換。
- `common/tile/appliance/TileProcessor.java:181,187` の `OreDictionary.itemMatches` を `TagHelper.itemMatches` に置換（`stackSize→getCount` 同時修正）。
- `event/ShowOreNameEvent.java:43` の `OreDictionary.getOreIDs/getOreName` を `ItemStack.getTags()` に、`FluidContainerRegistry.getFluidForFilledItem` を `ForgeCapabilities.FLUID_HANDLER_ITEM` に置換。表示は `TagKey#location` 文字列に。
- `data/forge/tags/items/` (156件) と `data/c/tags/items/` (156件)、`data/defeatedcrow/tags/items/` (42件) を生成。旧 `RegisterOreHandler` の全 130+ ore 名を `oreNameToTagPath` 規則で tagJSON 化（`scripts/generate_tags.py` が生成元）。例:
  - `forge:dyes/black` + `c:dyes/black` ← `defeatedcrow:ink_stick`（旧 `dyeBlack`）
  - `forge:crops/tea` / `c:crops/tea` ← `defeatedcrow:leaf_tea`（旧 `cropTea`）
  - `forge:dusts/wood` / `c:dusts/wood` ← `defeatedcrow:wood_dust`（旧 `dustWood`）
  - `forge:storage_blocks/chalcedony` / `c:storage_blocks/chalcedony` ← `defeatedcrow:chalcedony`（旧 `blockChalcedony`）
  - `forge:items/incense` / `c:items/incense` / `defeatedcrow:items/incense` ← 11種 incense（旧 `itemIncense`）
- **WT-B検証**: `grep -r "OreDictionary"` が `handler/` + `common/tile` + `event/` で 0件（WT-B所有内）。残存は `recipe/` 5件 + `plugin/` 2件 + `common/DCsRecipeRegister.java` 1件で、WT-C/WT-A所有のため WT-Bでは触らない（doc/handler/migration-guide.md の禁止編集に準拠）。
- **残TODO（variant個別化 + BlockItem登録後、WT-A連携）**:
  - 現行 tagJSON の `values` は variant 未個別化のため単一 `defeatedcrow:*` に近似（例 `ex_items` は 8 nugget/dust/gear を包含）。`ore_dust` 8種, `grated_apple` 10種, `choco_fruits` 14種, `large_bottle` 24種, `cordial` 5種, `condensed_milk` 4種 等の個別Item化後に再生成が必要。
  - BlockItem 未登録 `log_yuzu` / `leaves_yuzu` 等は `forge:logs`, `forge:leaves` で登録済みだが Bucket/Fluid のタグ (`buckets/*`, `bottles/*`) は placeholder (`camellia_oil_bucket` 等)。Fluid の bucket アイテム登録後に再生成。

## タグJSON の構成

`src/main/resources/data/{namespace}/tags/items/<path>.json`

```json
{
  "replace": false,
  "values": [
    "defeatedcrow:ink_stick"
  ]
}
```

- `values` は `SchemaLocation` 表記（`namespace:path`）。アイテムの registryName で記述する。
- メタ差分（damage値）は 1.20.1 では**アイテム単位が基本**。サブアイテム（variant）は各レジストリ上の別アイテムとして定義済みと仮定し、registryName を1つずつ列挙する。

## Java 側での参照方法（参考）

```java
// 参照側: タグに属する全アイテムを取得
TagKey<Item> key = TagKey.create(Registries.ITEM, new ResourceLocation("forge", "crops/tea"));
ItemStack stack = new ItemStack(BuiltInRegistries.ITEM.getOrCreateTag(key).iterator().next());
// レシピ入力: Ingredient.of(key)
```

※ この規約は登録側の機械置換用のロードマップ。実装は tag 定義の生成と `RegisterOreHandler` 等の削減から開始。