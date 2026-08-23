# monocle (`ItemChalcedonyMonocle`)

> Category: `tool (ツール・武器)`
> Source: `src/main/java/mods/defeatedcrow/common/item/ItemChalcedonyMonocle.java:1`
> Registry: `defeatedcrow.monocle` (`DCsAppleMilk.monocle`)
> Field: `DCsAppleMilk.java:269` `public static Item monocle;`
> Registration: `MaterialRegister.java:254` `GameRegistry.registerItem(DCsAppleMilk.monocle, "defeatedcrow.monocle")`
> Class: `ItemChalcedonyMonocle extends ItemArmor`
> CreativeTab: `DCsAppleMilk.applemilk`
> StackSize: `1` 

## 概要
モノクル（鉄ヘルメット防具）。`ItemArmor` 継承、`ShowOreNameEvent` でShift時鉱石辞書名表示。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:269` `public static Item monocle;`
- **インスタンス生成**: `MaterialRegister.java` で `new ItemChalcedonyMonocle()` → `setUnlocalizedName("defeatedcrow.monocle")`  DCsAppleMilk.applemilk（該当行は `MaterialRegister.java` の `add*()` メソッド内）
- **GameRegistry**: `GameRegistry.registerItem(DCsAppleMilk.monocle, "defeatedcrow.monocle")` (`MaterialRegister.java:254`)
- **メタデータ管理**: `setHasSubtypes(true)` + `getMetadata(int)` → damageをそのままmetadataへ。`getUnlocalizedName(ItemStack)` で `super.getUnlocalizedName() + "_" + damage` を返却。
- **アイコン**: `registerIcons(IIconRegister)` で `defeatedcrow:*` を登録（単一防具（頭、ArmorMaterial.IRON））

## メタデータ / 亜種一覧
- **管理方式**: `setHasSubtypes(true)` / `setMaxDamage(0)` / `getMetadata` / `getUnlocalizedName` パターン（1.7.10メタ駆動）
- **詳細**: 単一防具（頭、ArmorMaterial.IRON）
- **参照**: `src/main/java/mods/defeatedcrow/api/ItemAPI.java:54-177` のコメント、`src/main/java/mods/defeatedcrow/common/item/ItemChalcedonyMonocle.java:1` の `getSubItems` / `registerIcons` / `getUnlocalizedName`

| meta | 説明（monocle） | 備考 |
|---|---|---|
| 単一防具（頭、ArmorMaterial.IRON） | — | |

> 実際のメタ→icon対応は `src/main/java/mods/defeatedcrow/common/item/ItemChalcedonyMonocle.java:1` の `registerIcons` で `iconItemType[meta] = par1IconRegister.registerIcon("defeatedcrow:...")` を参照。

## 継承・インターフェース
- 継承: `ItemChalcedonyMonocle extends ItemArmor` 
- 実装: `-`
- 親メソッド: `Item` の `onItemUse`, `onItemRightClick`, `addInformation`, `getSubItems`, `registerIcons` 等をオーバーライド

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 / 1.20 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `getArmorTexture` | ` public String getArmorTexture(ItemStack item, Entity entity, int slot, String type) {` | `src/main/java/mods/defeatedcrow/common/item/ItemChalcedonyMonocle.java:1` (override `ItemArmor.getArmorTexture`) | モノクル（鉄ヘルメット防具）。`Item | 削除。`getArmorTexture` → `ArmorMaterial` / レイヤーJSON |
| `registerIcons` | `public void registerIcons(IIconRegister par1IconRegister) {` | `src/main/java/mods/defeatedcrow/common/item/ItemChalcedonyMonocle.java:1` (override `ItemArmor.registerIcons`) | モノクル（鉄ヘルメット防具）。`Item | 削除。`ModelLoader` + `assets/.../models/item/*.json` |
| `addInformation` | `public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {` | `src/main/java/mods/defeatedcrow/common/item/ItemChalcedonyMonocle.java:1` (override `ItemArmor.addInformation`) | モノクル（鉄ヘルメット防具）。`Item | `appendHoverText` + `Component` へ |

## レシピ / イベント / その他連携
- **レシピ**: `DCsRecipeRegister.java:1`, `RegisterMakerRecipe.java:1`（TeaMaker/IceMaker/Pan/Plate/Processor/Evaporator/Brewing）で素材/生成物として使用。例: `monocle` は該当レシピの入力または出力。
- **イベント**:
  - `ShowOreNameEvent.java:1` で Shift押下時に鉱石辞書名を `addInformation` 相当で表示
  - `MaterialRegister.load():247` / `addFluid():490` で登録管理
- **API参照**: `ItemAPI.java:54` コメント、`ChargeItemManager.java:1`, `IIncenseEffect.java:1`, `IBattery.java:1` 等

## 1.7.10 → 1.12.2 / 1.16.5 移行チェックリスト
- [ ] `setUnlocalizedName("defeatedcrow.monocle")` → `setTranslationKey` + `setRegistryName("defeatedcrow", "monocle")`（`Item.Properties` で `setRegistryName` 必須）
- [ ] `GameRegistry.registerItem(item, "defeatedcrow.monocle")` → `RegistryEvent.Register<Item>` / `DeferredRegister<Item>` に移行（`MaterialRegister` → `ModItems` クラスへ分割推奨）
- [ ] `IIcon` / `IIconRegister` / `registerIcons` / `getIconFromDamage` → 削除。`assets/defeatedcrow/models/item/monocle.json` + `textures/item/*.png` + `ModelLoader` へ。メタ分岐は `overrides` predicate または個別Item化（1.13+）
- [ ] `getUnlocalizedName(ItemStack)` → `getTranslationKey(ItemStack)` (1.11) → `getDescriptionId(ItemStack)` (1.16)
- [ ] `getSubItems(Item, CreativeTabs, List)` → `fillItemCategory(CreativeModeTab, NonNullList<ItemStack>)` / `ItemGroup` 変更 (1.14+)
- [ ] メタ管理 (`setHasSubtypes`, `getMetadata`) → 1.13+でメタ廃止。NBT / 個別Item / `Capability` / `DataComponent` (1.20) へ分割
- [ ] `onItemUse` / `onItemRightClick` → `useOn(UseOnContext)` / `use(Level, Player, InteractionHand)` へ（`BlockPos`/`Level`/`InteractionResult` 化）
- [ ] `addInformation` → `appendHoverText(ItemStack, Level, List<Component>, TooltipFlag)` (`ChatFormatting`, `Component`)
- [ ] `ItemFood` コンストラクタ: `super(heal, saturation, isWolfFood)` → `Item.Properties.food(new FoodProperties.Builder().nutrition().saturationMod().build())` (1.14+)
- [ ] `ItemTool` / `ItemArmor` / `ItemBow` 継承は維持だが `Tier` / `ArmorMaterial` / `Item.Properties` 変更（`EnumHelper.addToolMaterial` → `TierSortingRegistry.registerTier`）
- [ ] `OreDictionary` → `TagKey<Item>` (`forge:*`, `c:*`) に移行
- [ ] 言語ファイル: `*.lang` → `assets/defeatedcrow/lang/*.json` (`item.defeatedcrow.monocle`)

## 関連ドキュメント
- [Item 一覧](../items.md) - 全体索引
- [Block 一覧](../blocks.md) - ItemBlock はそちらで詳述（`ItemWoodBox` 等）
- [Fluid 一覧](../fluids.md) - 流体コンテナ連携（該当時）
- [移行ガイド](./migration-guide.md) - Item共通の移行手順
- [カテゴリ別一覧](./README.md) - 同カテゴリの他Itemへ

> 自動生成元: `src/main/java/mods/defeatedcrow/common/item/ItemChalcedonyMonocle.java:1` / `DCsAppleMilk.java:269` / `MaterialRegister.java:254`
> 最終更新: 2026-08-23
