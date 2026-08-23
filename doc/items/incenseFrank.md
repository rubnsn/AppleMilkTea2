# incenseFrank (`ItemIncenseFrankincense`)

> Category: `magic (魔法・お香)`
> Source: `src/main/java/mods/defeatedcrow/common/item/magic/ItemIncenseFrankincense.java:1`
> Registry: `defeatedcrow.incense_frankincense` (`DCsAppleMilk.incenseFrank`)
> Field: `DCsAppleMilk.java:313` `public static Item incenseFrank;`
> Registration: `MaterialRegister.java:300` `GameRegistry.registerItem(DCsAppleMilk.incenseFrank, "defeatedcrow.incense_frankincense")`
> Class: `ItemIncenseFrankincense extends Item` implements IIncenseEffect
> CreativeTab: `DCsAppleMilk.applemilkMagic`
> StackSize: `64` 

## 概要
乳香。`IIncenseEffect` で浄化/保護。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:313` `public static Item incenseFrank;`
- **インスタンス生成**: `MaterialRegister.java` で `new ItemIncenseFrankincense()` → `setUnlocalizedName("defeatedcrow.incense_frankincense")`  DCsAppleMilk.applemilkMagic（該当行は `MaterialRegister.java` の `add*()` メソッド内）
- **GameRegistry**: `GameRegistry.registerItem(DCsAppleMilk.incenseFrank, "defeatedcrow.incense_frankincense")` (`MaterialRegister.java:300`)
- **メタデータ管理**: `setHasSubtypes(true)` + `getMetadata(int)` → damageをそのままmetadataへ。`getUnlocalizedName(ItemStack)` で `super.getUnlocalizedName() + "_" + damage` を返却。
- **アイコン**: `registerIcons(IIconRegister)` で `defeatedcrow:*` を登録（単一（お香、乳香））

## メタデータ / 亜種一覧
- **管理方式**: `setHasSubtypes(true)` / `setMaxDamage(0)` / `getMetadata` / `getUnlocalizedName` パターン（1.7.10メタ駆動）
- **詳細**: 単一（お香、乳香）
- **参照**: `src/main/java/mods/defeatedcrow/api/ItemAPI.java:54-177` のコメント、`src/main/java/mods/defeatedcrow/common/item/magic/ItemIncenseFrankincense.java:1` の `getSubItems` / `registerIcons` / `getUnlocalizedName`

| meta | 説明（incenseFrank） | 備考 |
|---|---|---|
| 単一（お香、乳香） | — | |

> 実際のメタ→icon対応は `src/main/java/mods/defeatedcrow/common/item/magic/ItemIncenseFrankincense.java:1` の `registerIcons` で `iconItemType[meta] = par1IconRegister.registerIcon("defeatedcrow:...")` を参照。

## 継承・インターフェース
- 継承: `ItemIncenseFrankincense extends Item` 
- 実装: `IIncenseEffect`
- 親メソッド: `Item` の `onItemUse`, `onItemRightClick`, `addInformation`, `getSubItems`, `registerIcons` 等をオーバーライド

- **IIncenseEffect** (`src/main/java/mods/defeatedcrow/api/charm/IIncenseEffect.java:1`):
  - `effectAreaRange():int` → 範囲5
  - `getEffectType():EffectType` → `EntityLiving` / `EntityPlayer` 等
  - `formEffect(World,x,y,z,EntityLivingBase, IIncenseEffect):boolean` → Potion付与
  - `particleIcon():String`, `particleColorR/G/B():float` → パーティクル定義
  - 呼出元: `TileIncenseBase.java:1`（お香台Tileがtickで呼び出し）

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 / 1.20 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `registerIcons` | `public void registerIcons(IIconRegister par1IconRegister) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemIncenseFrankincense.java:1` (override `Item.registerIcons`) | 乳香。`IIncenseEffect`  | 削除。`ModelLoader` + `assets/.../models/item/*.json` |
| `effectAreaRange` | ` public int effectAreaRange() {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemIncenseFrankincense.java:1` (implement `IIncenseEffect.effectAreaRange`) / 呼出: `TileIncenseBase.updateEntity()` | 乳香。`IIncenseEffect`  | 同上 |
| `getEffectType` | ` public EffectType getEffectType() {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemIncenseFrankincense.java:1` (implement `IIncenseEffect.getEffectType`) / 呼出: `TileIncenseBase.updateEntity()` | 乳香。`IIncenseEffect`  | 同上 |
| `formEffect` | ` public boolean formEffect(World world, int x, int y, int z, EntityLivingBase entity, IIncenseEffect incense) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemIncenseFrankincense.java:1` (implement `IIncenseEffect.formEffect`) / 呼出: `TileIncenseBase.updateEntity()` | 乳香。`IIncenseEffect`  | `IIncenseEffect` 維持、World→Level, BlockPos 変更 |
| `particleIcon` | ` public String particleIcon() {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemIncenseFrankincense.java:1` (implement `IIncenseEffect.particleIcon`) / 呼出: `TileIncenseBase.updateEntity()` | 乳香。`IIncenseEffect`  | 同上、花火パーティクルは `ParticleTypes` |
| `particleColorR` | ` public float particleColorR() {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemIncenseFrankincense.java:1` (implement `IIncenseEffect.particleColorR`) / 呼出: `TileIncenseBase.updateEntity()` | 乳香。`IIncenseEffect`  | 同上 |
| `particleColorG` | ` public float particleColorG() {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemIncenseFrankincense.java:1` (implement `IIncenseEffect.particleColorG`) / 呼出: `TileIncenseBase.updateEntity()` | 乳香。`IIncenseEffect`  | 要確認。1.7.10→1.12 でシグネチャ変更の可能性、リネーム（MCP→Mojang）や `Level`/`BlockPos` 化を確認。 |
| `particleColorB` | ` public float particleColorB() {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemIncenseFrankincense.java:1` (implement `IIncenseEffect.particleColorB`) / 呼出: `TileIncenseBase.updateEntity()` | 乳香。`IIncenseEffect`  | 要確認。1.7.10→1.12 でシグネチャ変更の可能性、リネーム（MCP→Mojang）や `Level`/`BlockPos` 化を確認。 |

## レシピ / イベント / その他連携
- **レシピ**: `DCsRecipeRegister.java:1`, `RegisterMakerRecipe.java:1`（TeaMaker/IceMaker/Pan/Plate/Processor/Evaporator/Brewing）で素材/生成物として使用。例: `incenseFrank` は該当レシピの入力または出力。
- **イベント**:
  - `MaterialRegister.load():247` / `addFluid():490` で登録管理
- **API参照**: `ItemAPI.java:54` コメント、`ChargeItemManager.java:1`, `IIncenseEffect.java:1`, `IBattery.java:1` 等

## 1.7.10 → 1.12.2 / 1.16.5 移行チェックリスト
- [ ] `setUnlocalizedName("defeatedcrow.incense_frankincense")` → `setTranslationKey` + `setRegistryName("defeatedcrow", "incenseFrank")`（`Item.Properties` で `setRegistryName` 必須）
- [ ] `GameRegistry.registerItem(item, "defeatedcrow.incense_frankincense")` → `RegistryEvent.Register<Item>` / `DeferredRegister<Item>` に移行（`MaterialRegister` → `ModItems` クラスへ分割推奨）
- [ ] `IIcon` / `IIconRegister` / `registerIcons` / `getIconFromDamage` → 削除。`assets/defeatedcrow/models/item/incenseFrank.json` + `textures/item/*.png` + `ModelLoader` へ。メタ分岐は `overrides` predicate または個別Item化（1.13+）
- [ ] `getUnlocalizedName(ItemStack)` → `getTranslationKey(ItemStack)` (1.11) → `getDescriptionId(ItemStack)` (1.16)
- [ ] `getSubItems(Item, CreativeTabs, List)` → `fillItemCategory(CreativeModeTab, NonNullList<ItemStack>)` / `ItemGroup` 変更 (1.14+)
- [ ] メタ管理 (`setHasSubtypes`, `getMetadata`) → 1.13+でメタ廃止。NBT / 個別Item / `Capability` / `DataComponent` (1.20) へ分割
- [ ] `onItemUse` / `onItemRightClick` → `useOn(UseOnContext)` / `use(Level, Player, InteractionHand)` へ（`BlockPos`/`Level`/`InteractionResult` 化）
- [ ] `addInformation` → `appendHoverText(ItemStack, Level, List<Component>, TooltipFlag)` (`ChatFormatting`, `Component`)
- [ ] `ItemFood` コンストラクタ: `super(heal, saturation, isWolfFood)` → `Item.Properties.food(new FoodProperties.Builder().nutrition().saturationMod().build())` (1.14+)
- [ ] `ItemTool` / `ItemArmor` / `ItemBow` 継承は維持だが `Tier` / `ArmorMaterial` / `Item.Properties` 変更（`EnumHelper.addToolMaterial` → `TierSortingRegistry.registerTier`）
- [ ] `OreDictionary` → `TagKey<Item>` (`forge:*`, `c:*`) に移行
- [ ] 言語ファイル: `*.lang` → `assets/defeatedcrow/lang/*.json` (`item.defeatedcrow.incenseFrank`)

## 関連ドキュメント
- [Item 一覧](../items.md) - 全体索引
- [Block 一覧](../blocks.md) - ItemBlock はそちらで詳述（`ItemWoodBox` 等）
- [Fluid 一覧](../fluids.md) - 流体コンテナ連携（該当時）
- [移行ガイド](./migration-guide.md) - Item共通の移行手順
- [カテゴリ別一覧](./README.md) - 同カテゴリの他Itemへ

> 自動生成元: `src/main/java/mods/defeatedcrow/common/item/magic/ItemIncenseFrankincense.java:1` / `DCsAppleMilk.java:313` / `MaterialRegister.java:300`
> 最終更新: 2026-08-23
