# dummyTeppan (`ItemDummyForTeppan`)

> Category: `dummy (NEIダミー)`
> Source: `src/main/java/mods/defeatedcrow/common/item/ItemDummyForTeppan.java:1`
> Registry: `defeatedcrow.dummyPlate` (`DCsAppleMilk.dummyTeppan`)
> Field: `DCsAppleMilk.java:350` `public static Item dummyTeppan;`
> Registration: `MaterialRegister.java:517` `GameRegistry.registerItem(DCsAppleMilk.dummyTeppan, "defeatedcrow.dummyPlate")`
> Class: `ItemDummyForTeppan extends Item`
> CreativeTab: `(none)`
> StackSize: `64` (hasSubtypes)

## 概要
鉄板レシピ表示用ダミー（`ItemDummyForTeppan`）。同上。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:350` `public static Item dummyTeppan;`
- **インスタンス生成**: `MaterialRegister.java` で `new ItemDummyForTeppan()` → `setUnlocalizedName("defeatedcrow.dummyPlate")`  (none)（該当行は `MaterialRegister.java` の `add*()` メソッド内）
- **GameRegistry**: `GameRegistry.registerItem(DCsAppleMilk.dummyTeppan, "defeatedcrow.dummyPlate")` (`MaterialRegister.java:517`)
- **メタデータ管理**: `setHasSubtypes(true)` + `getMetadata(int)` → damageをそのままmetadataへ。`getUnlocalizedName(ItemStack)` で `super.getUnlocalizedName() + "_" + damage` を返却。
- **アイコン**: `registerIcons(IIconRegister)` で `defeatedcrow:*` を登録（NEIダミー、鉄板レシピ表示）

## メタデータ / 亜種一覧
- **管理方式**: `setHasSubtypes(true)` / `setMaxDamage(0)` / `getMetadata` / `getUnlocalizedName` パターン（1.7.10メタ駆動）
- **詳細**: NEIダミー、鉄板レシピ表示
- **参照**: `src/main/java/mods/defeatedcrow/api/ItemAPI.java:54-177` のコメント、`src/main/java/mods/defeatedcrow/common/item/ItemDummyForTeppan.java:1` の `getSubItems` / `registerIcons` / `getUnlocalizedName`

| meta | 説明（dummyTeppan） | 備考 |
|---|---|---|
| NEIダミー、鉄板レシピ表示 | — | |

> 実際のメタ→icon対応は `src/main/java/mods/defeatedcrow/common/item/ItemDummyForTeppan.java:1` の `registerIcons` で `iconItemType[meta] = par1IconRegister.registerIcon("defeatedcrow:...")` を参照。

## 継承・インターフェース
- 継承: `ItemDummyForTeppan extends Item` 
- 実装: `-`
- 親メソッド: `Item` の `onItemUse`, `onItemRightClick`, `addInformation`, `getSubItems`, `registerIcons` 等をオーバーライド

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 / 1.20 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `getIconFromDamage` | `public IIcon getIconFromDamage(int par1) {` | `src/main/java/mods/defeatedcrow/common/item/ItemDummyForTeppan.java:1` (override `Item.getIconFromDamage`) | 鉄板レシピ表示用ダミー（`ItemDum | 削除。JSONモデルへ。`getIconFromDamage` → `BakedModel` |
| `getMetadata` | ` public int getMetadata(int par1) {` | `src/main/java/mods/defeatedcrow/common/item/ItemDummyForTeppan.java:1` (override `Item.getMetadata`) | 鉄板レシピ表示用ダミー（`ItemDum | 維持だが 1.13+ でメタ廃止→分割。`hasSubtypes` 非推奨 |
| `getUnlocalizedName` | ` public String getUnlocalizedName(ItemStack par1ItemStack) {` | `src/main/java/mods/defeatedcrow/common/item/ItemDummyForTeppan.java:1` (override `Item.getUnlocalizedName`) | 鉄板レシピ表示用ダミー（`ItemDum | `getTranslationKey` (1.11) → `getDescriptionId` (1.16) にリネーム |
| `registerIcons` | `public void registerIcons(IIconRegister par1IconRegister) {` | `src/main/java/mods/defeatedcrow/common/item/ItemDummyForTeppan.java:1` (override `Item.registerIcons`) | 鉄板レシピ表示用ダミー（`ItemDum | 削除。`ModelLoader` + `assets/.../models/item/*.json` |

## レシピ / イベント / その他連携
- **レシピ**: `DCsRecipeRegister.java:1`, `RegisterMakerRecipe.java:1`（TeaMaker/IceMaker/Pan/Plate/Processor/Evaporator/Brewing）で素材/生成物として使用。例: `dummyTeppan` は該当レシピの入力または出力。
- **イベント**:
  - `MaterialRegister.load():247` / `addFluid():490` で登録管理
- **API参照**: `ItemAPI.java:54` コメント、`ChargeItemManager.java:1`, `IIncenseEffect.java:1`, `IBattery.java:1` 等

## 1.7.10 → 1.12.2 / 1.16.5 移行チェックリスト
- [ ] `setUnlocalizedName("defeatedcrow.dummyPlate")` → `setTranslationKey` + `setRegistryName("defeatedcrow", "dummyTeppan")`（`Item.Properties` で `setRegistryName` 必須）
- [ ] `GameRegistry.registerItem(item, "defeatedcrow.dummyPlate")` → `RegistryEvent.Register<Item>` / `DeferredRegister<Item>` に移行（`MaterialRegister` → `ModItems` クラスへ分割推奨）
- [ ] `IIcon` / `IIconRegister` / `registerIcons` / `getIconFromDamage` → 削除。`assets/defeatedcrow/models/item/dummyTeppan.json` + `textures/item/*.png` + `ModelLoader` へ。メタ分岐は `overrides` predicate または個別Item化（1.13+）
- [ ] `getUnlocalizedName(ItemStack)` → `getTranslationKey(ItemStack)` (1.11) → `getDescriptionId(ItemStack)` (1.16)
- [ ] `getSubItems(Item, CreativeTabs, List)` → `fillItemCategory(CreativeModeTab, NonNullList<ItemStack>)` / `ItemGroup` 変更 (1.14+)
- [ ] メタ管理 (`setHasSubtypes`, `getMetadata`) → 1.13+でメタ廃止。NBT / 個別Item / `Capability` / `DataComponent` (1.20) へ分割
- [ ] `onItemUse` / `onItemRightClick` → `useOn(UseOnContext)` / `use(Level, Player, InteractionHand)` へ（`BlockPos`/`Level`/`InteractionResult` 化）
- [ ] `addInformation` → `appendHoverText(ItemStack, Level, List<Component>, TooltipFlag)` (`ChatFormatting`, `Component`)
- [ ] `ItemFood` コンストラクタ: `super(heal, saturation, isWolfFood)` → `Item.Properties.food(new FoodProperties.Builder().nutrition().saturationMod().build())` (1.14+)
- [ ] `ItemTool` / `ItemArmor` / `ItemBow` 継承は維持だが `Tier` / `ArmorMaterial` / `Item.Properties` 変更（`EnumHelper.addToolMaterial` → `TierSortingRegistry.registerTier`）
- [ ] `OreDictionary` → `TagKey<Item>` (`forge:*`, `c:*`) に移行
- [ ] 言語ファイル: `*.lang` → `assets/defeatedcrow/lang/*.json` (`item.defeatedcrow.dummyTeppan`)

## 関連ドキュメント
- [Item 一覧](../items.md) - 全体索引
- [Block 一覧](../blocks.md) - ItemBlock はそちらで詳述（`ItemWoodBox` 等）
- [Fluid 一覧](../fluids.md) - 流体コンテナ連携（該当時）
- [移行ガイド](./migration-guide.md) - Item共通の移行手順
- [カテゴリ別一覧](./README.md) - 同カテゴリの他Itemへ

> 自動生成元: `src/main/java/mods/defeatedcrow/common/item/ItemDummyForTeppan.java:1` / `DCsAppleMilk.java:350` / `MaterialRegister.java:517`
> 最終更新: 2026-08-23
