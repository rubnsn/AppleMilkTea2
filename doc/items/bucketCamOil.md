# bucketCamOil (`ItemBucketCamOil`)

> Category: `fluid-container (流体コンテナ)`
> Source: `src/main/java/mods/defeatedcrow/common/fluid/ItemBucketCamOil.java:1`
> Registry: `defeatedcrow.bucketCamOil` (`DCsAppleMilk.bucketCamOil`)
> Field: `DCsAppleMilk.java:344` `public static Item bucketCamOil;`
> Registration: `MaterialRegister.java:540` `GameRegistry.registerItem(DCsAppleMilk.bucketCamOil, "defeatedcrow.bucketCamOil")`
> Class: `ItemBucketCamOil extends ItemBucket`
> CreativeTab: `DCsAppleMilk.applemilk`
> StackSize: `64` 

## 概要
椿油バケツ（1000mB）。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:344` `public static Item bucketCamOil;`
- **インスタンス生成**: `MaterialRegister.java` で `new ItemBucketCamOil()` → `setUnlocalizedName("defeatedcrow.bucketCamOil")`  DCsAppleMilk.applemilk（該当行は `MaterialRegister.java` の `add*()` メソッド内）
- **GameRegistry**: `GameRegistry.registerItem(DCsAppleMilk.bucketCamOil, "defeatedcrow.bucketCamOil")` (`MaterialRegister.java:540`)
- **Fluid連携**: `MaterialRegister.addFluid():1` で `FluidContainerRegistry.registerFluidContainer(FluidStack(...), ItemStack(bucketCamOil), ItemStack(empty))`
- **メタデータ管理**: `setHasSubtypes(true)` + `getMetadata(int)` → damageをそのままmetadataへ。`getUnlocalizedName(ItemStack)` で `super.getUnlocalizedName() + "_" + damage` を返却。
- **アイコン**: `registerIcons(IIconRegister)` で `defeatedcrow:*` を登録（単一（1000mB））

## メタデータ / 亜種一覧
- **管理方式**: `setHasSubtypes(true)` / `setMaxDamage(0)` / `getMetadata` / `getUnlocalizedName` パターン（1.7.10メタ駆動）
- **詳細**: 単一（1000mB）
- **参照**: `src/main/java/mods/defeatedcrow/api/ItemAPI.java:54-177` のコメント、`src/main/java/mods/defeatedcrow/common/fluid/ItemBucketCamOil.java:1` の `getSubItems` / `registerIcons` / `getUnlocalizedName`

| meta | 説明（bucketCamOil） | 備考 |
|---|---|---|
| 単一（1000mB） | — | |

> 実際のメタ→icon対応は `src/main/java/mods/defeatedcrow/common/fluid/ItemBucketCamOil.java:1` の `registerIcons` で `iconItemType[meta] = par1IconRegister.registerIcon("defeatedcrow:...")` を参照。

## 継承・インターフェース
- 継承: `ItemBucketCamOil extends ItemBucket` 
- 実装: `-`
- 親メソッド: `Item` の `onItemUse`, `onItemRightClick`, `addInformation`, `getSubItems`, `registerIcons` 等をオーバーライド

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 / 1.20 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `registerIcons` | `public void registerIcons(IIconRegister par1IconRegister) {` | `src/main/java/mods/defeatedcrow/common/fluid/ItemBucketCamOil.java:1` (override `ItemBucket.registerIcons`) | 椿油バケツ（1000mB）。 | 削除。`ModelLoader` + `assets/.../models/item/*.json` |

## レシピ / イベント / その他連携
- **レシピ**: `DCsRecipeRegister.java:1`, `RegisterMakerRecipe.java:1`（TeaMaker/IceMaker/Pan/Plate/Processor/Evaporator/Brewing）で素材/生成物として使用。例: `bucketCamOil` は該当レシピの入力または出力。
- **イベント**:
  - `BucketFillEvent.java:1` / `FluidDispenser.java:1` / `DispenserEvent.java:1` でディスペンサー・バケツ充填対応
  - `MaterialRegister.load():247` / `addFluid():490` で登録管理
- **API参照**: `ItemAPI.java:54` コメント、`ChargeItemManager.java:1`, `IIncenseEffect.java:1`, `IBattery.java:1` 等

## 1.7.10 → 1.12.2 / 1.16.5 移行チェックリスト
- [ ] `setUnlocalizedName("defeatedcrow.bucketCamOil")` → `setTranslationKey` + `setRegistryName("defeatedcrow", "bucketCamOil")`（`Item.Properties` で `setRegistryName` 必須）
- [ ] `GameRegistry.registerItem(item, "defeatedcrow.bucketCamOil")` → `RegistryEvent.Register<Item>` / `DeferredRegister<Item>` に移行（`MaterialRegister` → `ModItems` クラスへ分割推奨）
- [ ] `IIcon` / `IIconRegister` / `registerIcons` / `getIconFromDamage` → 削除。`assets/defeatedcrow/models/item/bucketCamOil.json` + `textures/item/*.png` + `ModelLoader` へ。メタ分岐は `overrides` predicate または個別Item化（1.13+）
- [ ] `getUnlocalizedName(ItemStack)` → `getTranslationKey(ItemStack)` (1.11) → `getDescriptionId(ItemStack)` (1.16)
- [ ] `getSubItems(Item, CreativeTabs, List)` → `fillItemCategory(CreativeModeTab, NonNullList<ItemStack>)` / `ItemGroup` 変更 (1.14+)
- [ ] メタ管理 (`setHasSubtypes`, `getMetadata`) → 1.13+でメタ廃止。NBT / 個別Item / `Capability` / `DataComponent` (1.20) へ分割
- [ ] `onItemUse` / `onItemRightClick` → `useOn(UseOnContext)` / `use(Level, Player, InteractionHand)` へ（`BlockPos`/`Level`/`InteractionResult` 化）
- [ ] `addInformation` → `appendHoverText(ItemStack, Level, List<Component>, TooltipFlag)` (`ChatFormatting`, `Component`)
- [ ] `ItemFood` コンストラクタ: `super(heal, saturation, isWolfFood)` → `Item.Properties.food(new FoodProperties.Builder().nutrition().saturationMod().build())` (1.14+)
- [ ] `ItemTool` / `ItemArmor` / `ItemBow` 継承は維持だが `Tier` / `ArmorMaterial` / `Item.Properties` 変更（`EnumHelper.addToolMaterial` → `TierSortingRegistry.registerTier`）
- [ ] `OreDictionary` → `TagKey<Item>` (`forge:*`, `c:*`) に移行
- [ ] 言語ファイル: `*.lang` → `assets/defeatedcrow/lang/*.json` (`item.defeatedcrow.bucketCamOil`)

## 関連ドキュメント
- [Item 一覧](../items.md) - 全体索引
- [Block 一覧](../blocks.md) - ItemBlock はそちらで詳述（`ItemWoodBox` 等）
- [Fluid 一覧](../fluids.md) - 流体コンテナ連携（該当時）
- [移行ガイド](./migration-guide.md) - Item共通の移行手順
- [カテゴリ別一覧](./README.md) - 同カテゴリの他Itemへ

> 自動生成元: `src/main/java/mods/defeatedcrow/common/fluid/ItemBucketCamOil.java:1` / `DCsAppleMilk.java:344` / `MaterialRegister.java:540`
> 最終更新: 2026-08-23
