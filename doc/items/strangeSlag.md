# strangeSlag (`ItemStrangeSlag`)

> Category: `magic (魔法・お香)`
> Source: `src/main/java/mods/defeatedcrow/common/item/magic/ItemStrangeSlag.java:1`
> Registry: `defeatedcrow.strangeSlag` (`DCsAppleMilk.strangeSlag`)
> Field: `DCsAppleMilk.java:293` `public static Item strangeSlag;`
> Registration: `MaterialRegister.java:310` `GameRegistry.registerItem(DCsAppleMilk.strangeSlag, "defeatedcrow.strangeSlag")`
> Class: `ItemStrangeSlag extends Item`
> CreativeTab: `DCsAppleMilk.applemilkMagic`
> StackSize: `64` 

## 概要
謎のスラグ（インセンス素材）。`onItemUse/onItemRightClick/returnItem`。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:293` `public static Item strangeSlag;`
- **インスタンス生成**: `MaterialRegister.java` で `new ItemStrangeSlag()` → `setUnlocalizedName("defeatedcrow.strangeSlag")`  DCsAppleMilk.applemilkMagic（該当行は `MaterialRegister.java` の `add*()` メソッド内）
- **GameRegistry**: `GameRegistry.registerItem(DCsAppleMilk.strangeSlag, "defeatedcrow.strangeSlag")` (`MaterialRegister.java:310`)
- **メタデータ管理**: `setHasSubtypes(true)` + `getMetadata(int)` → damageをそのままmetadataへ。`getUnlocalizedName(ItemStack)` で `super.getUnlocalizedName() + "_" + damage` を返却。
- **アイコン**: `registerIcons(IIconRegister)` で `defeatedcrow:*` を登録（単一、右クリックで変換？）

## メタデータ / 亜種一覧
- **管理方式**: `setHasSubtypes(true)` / `setMaxDamage(0)` / `getMetadata` / `getUnlocalizedName` パターン（1.7.10メタ駆動）
- **詳細**: 単一、右クリックで変換？
- **参照**: `src/main/java/mods/defeatedcrow/api/ItemAPI.java:54-177` のコメント、`src/main/java/mods/defeatedcrow/common/item/magic/ItemStrangeSlag.java:1` の `getSubItems` / `registerIcons` / `getUnlocalizedName`

| meta | 説明（strangeSlag） | 備考 |
|---|---|---|
| 単一、右クリックで変換？ | — | |

> 実際のメタ→icon対応は `src/main/java/mods/defeatedcrow/common/item/magic/ItemStrangeSlag.java:1` の `registerIcons` で `iconItemType[meta] = par1IconRegister.registerIcon("defeatedcrow:...")` を参照。

## 継承・インターフェース
- 継承: `ItemStrangeSlag extends Item` 
- 実装: `-`
- 親メソッド: `Item` の `onItemUse`, `onItemRightClick`, `addInformation`, `getSubItems`, `registerIcons` 等をオーバーライド

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 / 1.20 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `registerIcons` | `public void registerIcons(IIconRegister par1IconRegister) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemStrangeSlag.java:1` (override `Item.registerIcons`) | 謎のスラグ（インセンス素材）。`onIt | 削除。`ModelLoader` + `assets/.../models/item/*.json` |
| `onItemUse` | ` int par5, int par6, int par7, float par8, float par9, float par10) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemStrangeSlag.java:1` (override `Item.onItemUse`) | 謎のスラグ（インセンス素材）。`onIt | 前述 |
| `onItemRightClick` | ` public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemStrangeSlag.java:1` (override `Item.onItemRightClick`) | 謎のスラグ（インセンス素材）。`onIt | `use(Level, Player, InteractionHand)` → `InteractionResultHolder` |
| `returnItem` | `public ItemStack returnItem(ItemStack item, World world, EntityPlayer player) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemStrangeSlag.java:1` (override `Item.returnItem`) | 謎のスラグ（インセンス素材）。`onIt | 自作API、維持検討 |
| `addInformation` | `public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemStrangeSlag.java:1` (override `Item.addInformation`) | 謎のスラグ（インセンス素材）。`onIt | `appendHoverText` + `Component` へ |

## レシピ / イベント / その他連携
- **レシピ**: `DCsRecipeRegister.java:1`, `RegisterMakerRecipe.java:1`（TeaMaker/IceMaker/Pan/Plate/Processor/Evaporator/Brewing）で素材/生成物として使用。例: `strangeSlag` は該当レシピの入力または出力。
- **イベント**:
  - `MaterialRegister.load():247` / `addFluid():490` で登録管理
- **API参照**: `ItemAPI.java:54` コメント、`ChargeItemManager.java:1`, `IIncenseEffect.java:1`, `IBattery.java:1` 等

## 1.7.10 → 1.12.2 / 1.16.5 移行チェックリスト
- [ ] `setUnlocalizedName("defeatedcrow.strangeSlag")` → `setTranslationKey` + `setRegistryName("defeatedcrow", "strangeSlag")`（`Item.Properties` で `setRegistryName` 必須）
- [ ] `GameRegistry.registerItem(item, "defeatedcrow.strangeSlag")` → `RegistryEvent.Register<Item>` / `DeferredRegister<Item>` に移行（`MaterialRegister` → `ModItems` クラスへ分割推奨）
- [ ] `IIcon` / `IIconRegister` / `registerIcons` / `getIconFromDamage` → 削除。`assets/defeatedcrow/models/item/strangeSlag.json` + `textures/item/*.png` + `ModelLoader` へ。メタ分岐は `overrides` predicate または個別Item化（1.13+）
- [ ] `getUnlocalizedName(ItemStack)` → `getTranslationKey(ItemStack)` (1.11) → `getDescriptionId(ItemStack)` (1.16)
- [ ] `getSubItems(Item, CreativeTabs, List)` → `fillItemCategory(CreativeModeTab, NonNullList<ItemStack>)` / `ItemGroup` 変更 (1.14+)
- [ ] メタ管理 (`setHasSubtypes`, `getMetadata`) → 1.13+でメタ廃止。NBT / 個別Item / `Capability` / `DataComponent` (1.20) へ分割
- [ ] `onItemUse` / `onItemRightClick` → `useOn(UseOnContext)` / `use(Level, Player, InteractionHand)` へ（`BlockPos`/`Level`/`InteractionResult` 化）
- [ ] `addInformation` → `appendHoverText(ItemStack, Level, List<Component>, TooltipFlag)` (`ChatFormatting`, `Component`)
- [ ] `ItemFood` コンストラクタ: `super(heal, saturation, isWolfFood)` → `Item.Properties.food(new FoodProperties.Builder().nutrition().saturationMod().build())` (1.14+)
- [ ] `ItemTool` / `ItemArmor` / `ItemBow` 継承は維持だが `Tier` / `ArmorMaterial` / `Item.Properties` 変更（`EnumHelper.addToolMaterial` → `TierSortingRegistry.registerTier`）
- [ ] `OreDictionary` → `TagKey<Item>` (`forge:*`, `c:*`) に移行
- [ ] 言語ファイル: `*.lang` → `assets/defeatedcrow/lang/*.json` (`item.defeatedcrow.strangeSlag`)

## 関連ドキュメント
- [Item 一覧](../items.md) - 全体索引
- [Block 一覧](../blocks.md) - ItemBlock はそちらで詳述（`ItemWoodBox` 等）
- [Fluid 一覧](../fluids.md) - 流体コンテナ連携（該当時）
- [移行ガイド](./migration-guide.md) - Item共通の移行手順
- [カテゴリ別一覧](./README.md) - 同カテゴリの他Itemへ

> 自動生成元: `src/main/java/mods/defeatedcrow/common/item/magic/ItemStrangeSlag.java:1` / `DCsAppleMilk.java:293` / `MaterialRegister.java:310`
> 最終更新: 2026-08-23
