# chocolateFruits (`ItemChocoFruits`)

> Category: `food (食べ物（完成品）)`
> Source: `src/main/java/mods/defeatedcrow/common/item/edible/ItemChocoFruits.java:1`
> Registry: `defeatedcrow.chocolateFruits` (`DCsAppleMilk.chocolateFruits`)
> Field: `DCsAppleMilk.java:245` `public static Item chocolateFruits;`
> Registration: `MaterialRegister.java:269` `GameRegistry.registerItem(DCsAppleMilk.chocolateFruits, "defeatedcrow.chocolateFruits")`
> Class: `ItemChocoFruits extends ItemFood`
> CreativeTab: `DCsAppleMilk.applemilkFood`
> StackSize: `64` (hasSubtypes)

## 概要
フルーツチョコ 14種（almond:0〜apple:12）。`ItemFood(6,6,false)`。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:245` `public static Item chocolateFruits;`
- **インスタンス生成**: `MaterialRegister.java` で `new ItemChocoFruits()` → `setUnlocalizedName("defeatedcrow.chocolateFruits")`  DCsAppleMilk.applemilkFood（該当行は `MaterialRegister.java` の `add*()` メソッド内）
- **GameRegistry**: `GameRegistry.registerItem(DCsAppleMilk.chocolateFruits, "defeatedcrow.chocolateFruits")` (`MaterialRegister.java:269`)
- **メタデータ管理**: `setHasSubtypes(true)` + `getMetadata(int)` → damageをそのままmetadataへ。`getUnlocalizedName(ItemStack)` で `super.getUnlocalizedName() + "_" + damage` を返却。
- **アイコン**: `registerIcons(IIconRegister)` で `defeatedcrow:*` を登録（0:almond,1:peanut,2:crushedNuts,3:strawberry,4:cherry,5:berry,6:banana,7:cereal,8:bread,9:cookie,10:truffles,11:candy,12:apple（+2空））

## メタデータ / 亜種一覧
- **管理方式**: `setHasSubtypes(true)` / `setMaxDamage(0)` / `getMetadata` / `getUnlocalizedName` パターン（1.7.10メタ駆動）
- **詳細**: 0:almond,1:peanut,2:crushedNuts,3:strawberry,4:cherry,5:berry,6:banana,7:cereal,8:bread,9:cookie,10:truffles,11:candy,12:apple（+2空）
- **参照**: `src/main/java/mods/defeatedcrow/api/ItemAPI.java:54-177` のコメント、`src/main/java/mods/defeatedcrow/common/item/edible/ItemChocoFruits.java:1` の `getSubItems` / `registerIcons` / `getUnlocalizedName`

| meta | 説明（chocolateFruits） | 備考 |
|---|---|---|
| 0:almond,1:peanut,2:crushedNuts,3:strawberry,4:cherry,5:berry,6:banana,7:cereal,8:bread,9:cookie,10:truffles,11:candy,12:apple（+2空） | — | |

> 実際のメタ→icon対応は `src/main/java/mods/defeatedcrow/common/item/edible/ItemChocoFruits.java:1` の `registerIcons` で `iconItemType[meta] = par1IconRegister.registerIcon("defeatedcrow:...")` を参照。

## 継承・インターフェース
- 継承: `ItemChocoFruits extends ItemFood` 
- 実装: `-`
- 親メソッド: `Item` の `onItemUse`, `onItemRightClick`, `addInformation`, `getSubItems`, `registerIcons` 等をオーバーライド

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 / 1.20 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `getIconFromDamage` | `public IIcon getIconFromDamage(int par1) {` | `src/main/java/mods/defeatedcrow/common/item/edible/ItemChocoFruits.java:1` (override `ItemFood.getIconFromDamage`) | フルーツチョコ 14種（almond:0 | 削除。JSONモデルへ。`getIconFromDamage` → `BakedModel` |
| `getMetadata` | ` public int getMetadata(int par1) {` | `src/main/java/mods/defeatedcrow/common/item/edible/ItemChocoFruits.java:1` (override `ItemFood.getMetadata`) | フルーツチョコ 14種（almond:0 | 維持だが 1.13+ でメタ廃止→分割。`hasSubtypes` 非推奨 |
| `getUnlocalizedName` | ` public String getUnlocalizedName(ItemStack par1ItemStack) {` | `src/main/java/mods/defeatedcrow/common/item/edible/ItemChocoFruits.java:1` (override `ItemFood.getUnlocalizedName`) | フルーツチョコ 14種（almond:0 | `getTranslationKey` (1.11) → `getDescriptionId` (1.16) にリネーム |
| `getSubItems` | `public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {` | `src/main/java/mods/defeatedcrow/common/item/edible/ItemChocoFruits.java:1` (override `ItemFood.getSubItems`) | フルーツチョコ 14種（almond:0 | 維持だが `CreativeTabs` → `CreativeModeTab` / `fillItemCategory` に変更 |
| `registerIcons` | `public void registerIcons(IIconRegister par1IconRegister) {` | `src/main/java/mods/defeatedcrow/common/item/edible/ItemChocoFruits.java:1` (override `ItemFood.registerIcons`) | フルーツチョコ 14種（almond:0 | 削除。`ModelLoader` + `assets/.../models/item/*.json` |

## レシピ / イベント / その他連携
- **レシピ**: `DCsRecipeRegister.java:1`, `RegisterMakerRecipe.java:1`（TeaMaker/IceMaker/Pan/Plate/Processor/Evaporator/Brewing）で素材/生成物として使用。例: `chocolateFruits` は該当レシピの入力または出力。
- **イベント**:
  - `MaterialRegister.load():247` / `addFluid():490` で登録管理
- **API参照**: `ItemAPI.java:54` コメント、`ChargeItemManager.java:1`, `IIncenseEffect.java:1`, `IBattery.java:1` 等

## 1.7.10 → 1.12.2 / 1.16.5 移行チェックリスト
- [ ] `setUnlocalizedName("defeatedcrow.chocolateFruits")` → `setTranslationKey` + `setRegistryName("defeatedcrow", "chocolateFruits")`（`Item.Properties` で `setRegistryName` 必須）
- [ ] `GameRegistry.registerItem(item, "defeatedcrow.chocolateFruits")` → `RegistryEvent.Register<Item>` / `DeferredRegister<Item>` に移行（`MaterialRegister` → `ModItems` クラスへ分割推奨）
- [ ] `IIcon` / `IIconRegister` / `registerIcons` / `getIconFromDamage` → 削除。`assets/defeatedcrow/models/item/chocolateFruits.json` + `textures/item/*.png` + `ModelLoader` へ。メタ分岐は `overrides` predicate または個別Item化（1.13+）
- [ ] `getUnlocalizedName(ItemStack)` → `getTranslationKey(ItemStack)` (1.11) → `getDescriptionId(ItemStack)` (1.16)
- [ ] `getSubItems(Item, CreativeTabs, List)` → `fillItemCategory(CreativeModeTab, NonNullList<ItemStack>)` / `ItemGroup` 変更 (1.14+)
- [ ] メタ管理 (`setHasSubtypes`, `getMetadata`) → 1.13+でメタ廃止。NBT / 個別Item / `Capability` / `DataComponent` (1.20) へ分割
- [ ] `onItemUse` / `onItemRightClick` → `useOn(UseOnContext)` / `use(Level, Player, InteractionHand)` へ（`BlockPos`/`Level`/`InteractionResult` 化）
- [ ] `addInformation` → `appendHoverText(ItemStack, Level, List<Component>, TooltipFlag)` (`ChatFormatting`, `Component`)
- [ ] `ItemFood` コンストラクタ: `super(heal, saturation, isWolfFood)` → `Item.Properties.food(new FoodProperties.Builder().nutrition().saturationMod().build())` (1.14+)
- [ ] `ItemTool` / `ItemArmor` / `ItemBow` 継承は維持だが `Tier` / `ArmorMaterial` / `Item.Properties` 変更（`EnumHelper.addToolMaterial` → `TierSortingRegistry.registerTier`）
- [ ] `OreDictionary` → `TagKey<Item>` (`forge:*`, `c:*`) に移行
- [ ] 言語ファイル: `*.lang` → `assets/defeatedcrow/lang/*.json` (`item.defeatedcrow.chocolateFruits`)

## 関連ドキュメント
- [Item 一覧](../items.md) - 全体索引
- [Block 一覧](../blocks.md) - ItemBlock はそちらで詳述（`ItemWoodBox` 等）
- [Fluid 一覧](../fluids.md) - 流体コンテナ連携（該当時）
- [移行ガイド](./migration-guide.md) - Item共通の移行手順
- [カテゴリ別一覧](./README.md) - 同カテゴリの他Itemへ

> 自動生成元: `src/main/java/mods/defeatedcrow/common/item/edible/ItemChocoFruits.java:1` / `DCsAppleMilk.java:245` / `MaterialRegister.java:269`
> 最終更新: 2026-08-23
