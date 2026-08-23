# fossilCannon (`ItemFossilCannon`)

> Category: `tool (ツール・武器)`
> Source: `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1`
> Registry: `defeatedcrow.fossilCannon` (`DCsAppleMilk.fossilCannon`)
> Field: `DCsAppleMilk.java:295` `public static Item fossilCannon;`
> Registration: `MaterialRegister.java:258` `GameRegistry.registerItem(DCsAppleMilk.fossilCannon, "defeatedcrow.fossilCannon")`
> Class: `ItemFossilCannon extends ItemBow` implements IBattery
> CreativeTab: `DCsAppleMilk.applemilkMagic`
> StackSize: `1` 

## 概要
化石砲（`ItemBow`+`IBattery`）。`EntityAnchorMissile` 発射、`charge/discharge/getDurabilityForDisplay`。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:295` `public static Item fossilCannon;`
- **インスタンス生成**: `MaterialRegister.java` で `new ItemFossilCannon()` → `setUnlocalizedName("defeatedcrow.fossilCannon")`  DCsAppleMilk.applemilkMagic（該当行は `MaterialRegister.java` の `add*()` メソッド内）
- **GameRegistry**: `GameRegistry.registerItem(DCsAppleMilk.fossilCannon, "defeatedcrow.fossilCannon")` (`MaterialRegister.java:258`)
- **メタデータ管理**: `setHasSubtypes(true)` + `getMetadata(int)` → damageをそのままmetadataへ。`getUnlocalizedName(ItemStack)` で `super.getUnlocalizedName() + "_" + damage` を返却。
- **アイコン**: `registerIcons(IIconRegister)` で `defeatedcrow:*` を登録（単一、`IBattery` チャージ式弓）

## メタデータ / 亜種一覧
- **管理方式**: `setHasSubtypes(true)` / `setMaxDamage(0)` / `getMetadata` / `getUnlocalizedName` パターン（1.7.10メタ駆動）
- **詳細**: 単一、`IBattery` チャージ式弓
- **参照**: `src/main/java/mods/defeatedcrow/api/ItemAPI.java:54-177` のコメント、`src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` の `getSubItems` / `registerIcons` / `getUnlocalizedName`

| meta | 説明（fossilCannon） | 備考 |
|---|---|---|
| 単一、`IBattery` チャージ式弓 | — | |

> 実際のメタ→icon対応は `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` の `registerIcons` で `iconItemType[meta] = par1IconRegister.registerIcon("defeatedcrow:...")` を参照。

## 継承・インターフェース
- 継承: `ItemFossilCannon extends ItemBow` 
- 実装: `IBattery`
- 親メソッド: `Item` の `onItemUse`, `onItemRightClick`, `addInformation`, `getSubItems`, `registerIcons` 等をオーバーライド

- **IBattery** (`src/main/java/mods/defeatedcrow/api/charge/IBattery.java:1`):
  - `getChargeAmount(ItemStack):int`, `getMaxAmount`, `isFullCharged`, `charge(ItemStack,int,boolean)`, `discharge`
  - 呼出元: `TileChargerBase`, `TileChargerDevice`, `ChargeItemManager`
  - エネルギー換算: `PropertyHandler.rateRF/EU/GF()` (`common/config/PropertyHandler.java:1`)

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 / 1.20 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `getMaxAmount` | ` public int getMaxAmount(ItemStack item) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (implement `IBattery.getMaxAmount`) / 呼出: `TileChargerBase`, `ChargeItemManager` | 化石砲（`ItemBow`+`IBatt | 同上 |
| `registerIcons` | `public void registerIcons(IIconRegister par1IconRegister) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (override `ItemBow.registerIcons`) | 化石砲（`ItemBow`+`IBatt | 削除。`ModelLoader` + `assets/.../models/item/*.json` |
| `getRarity` | `public EnumRarity getRarity(ItemStack par1ItemStack) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (override `ItemBow.getRarity`) | 化石砲（`ItemBow`+`IBatt | 前述 |
| `onPlayerStoppedUsing` | ` int par4) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (override `ItemBow.onPlayerStoppedUsing`) | 化石砲（`ItemBow`+`IBatt | `releaseUsing` + `LivingEntity` |
| `onEaten` | `public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (override `ItemBow.onEaten`) | 化石砲（`ItemBow`+`IBatt | `finishUsingItem` に統合 |
| `getMaxItemUseDuration` | `public int getMaxItemUseDuration(ItemStack par1ItemStack) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (override `ItemBow.getMaxItemUseDuration`) | 化石砲（`ItemBow`+`IBatt | `getUseDuration` に変更 |
| `getItemUseAction` | `public EnumAction getItemUseAction(ItemStack par1ItemStack) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (override `ItemBow.getItemUseAction`) | 化石砲（`ItemBow`+`IBatt | `UseAnim` に変更 (1.16+) |
| `onItemRightClick` | `public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (override `ItemBow.onItemRightClick`) | 化石砲（`ItemBow`+`IBatt | `use(Level, Player, InteractionHand)` → `InteractionResultHolder` |
| `getItemIconForUseDuration` | `public IIcon getItemIconForUseDuration(int par1) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (override `ItemBow.getItemIconForUseDuration`) | 化石砲（`ItemBow`+`IBatt | 削除。モデル `overrides` で `pull` predicate |
| `getChargeAmount` | ` public int getChargeAmount(ItemStack item) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (implement `IBattery.getChargeAmount`) / 呼出: `TileChargerBase`, `ChargeItemManager` | 化石砲（`ItemBow`+`IBatt | 自作 `IBattery` → `CapabilityEnergy` 移行検討 |
| `isFullCharged` | ` public boolean isFullCharged(ItemStack item) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (implement `IBattery.isFullCharged`) / 呼出: `TileChargerBase`, `ChargeItemManager` | 化石砲（`ItemBow`+`IBatt | 同上 |
| `charge` | ` public int charge(ItemStack item, int amount, boolean flag) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (implement `IBattery.charge`) / 呼出: `TileChargerBase`, `ChargeItemManager` | 化石砲（`ItemBow`+`IBatt | 同上 |
| `discharge` | ` public int discharge(ItemStack item, int amount, boolean flag) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (implement `IBattery.discharge`) / 呼出: `TileChargerBase`, `ChargeItemManager` | 化石砲（`ItemBow`+`IBatt | 同上 |
| `addInformation` | `public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (override `ItemBow.addInformation`) | 化石砲（`ItemBow`+`IBatt | `appendHoverText` + `Component` へ |
| `showDurabilityBar` | ` public boolean showDurabilityBar(ItemStack stack) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (override `ItemBow.showDurabilityBar`) | 化石砲（`ItemBow`+`IBatt | 維持 (`isBarVisible`) |
| `getDurabilityForDisplay` | ` public double getDurabilityForDisplay(ItemStack stack) {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (override `ItemBow.getDurabilityForDisplay`) | 化石砲（`ItemBow`+`IBatt | 維持 (`getBarWidth` / `getBarColor`) |
| `isFull3D` | `public boolean isFull3D() {` | `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` (override `ItemBow.isFull3D`) | 化石砲（`ItemBow`+`IBatt | 維持 |

## レシピ / イベント / その他連携
- **レシピ**: `DCsRecipeRegister.java:1`, `RegisterMakerRecipe.java:1`（TeaMaker/IceMaker/Pan/Plate/Processor/Evaporator/Brewing）で素材/生成物として使用。例: `fossilCannon` は該当レシピの入力または出力。
- **イベント**:
  - `EntityMoreDropEvent.java:1`（チャーム効果）、`DCsLivingEvent` / `DCsHurtEvent`（Potion効果）
  - `MaterialRegister.load():247` / `addFluid():490` で登録管理
- **API参照**: `ItemAPI.java:54` コメント、`ChargeItemManager.java:1`, `IIncenseEffect.java:1`, `IBattery.java:1` 等

## 1.7.10 → 1.12.2 / 1.16.5 移行チェックリスト
- [ ] `setUnlocalizedName("defeatedcrow.fossilCannon")` → `setTranslationKey` + `setRegistryName("defeatedcrow", "fossilCannon")`（`Item.Properties` で `setRegistryName` 必須）
- [ ] `GameRegistry.registerItem(item, "defeatedcrow.fossilCannon")` → `RegistryEvent.Register<Item>` / `DeferredRegister<Item>` に移行（`MaterialRegister` → `ModItems` クラスへ分割推奨）
- [ ] `IIcon` / `IIconRegister` / `registerIcons` / `getIconFromDamage` → 削除。`assets/defeatedcrow/models/item/fossilCannon.json` + `textures/item/*.png` + `ModelLoader` へ。メタ分岐は `overrides` predicate または個別Item化（1.13+）
- [ ] `getUnlocalizedName(ItemStack)` → `getTranslationKey(ItemStack)` (1.11) → `getDescriptionId(ItemStack)` (1.16)
- [ ] `getSubItems(Item, CreativeTabs, List)` → `fillItemCategory(CreativeModeTab, NonNullList<ItemStack>)` / `ItemGroup` 変更 (1.14+)
- [ ] メタ管理 (`setHasSubtypes`, `getMetadata`) → 1.13+でメタ廃止。NBT / 個別Item / `Capability` / `DataComponent` (1.20) へ分割
- [ ] `onItemUse` / `onItemRightClick` → `useOn(UseOnContext)` / `use(Level, Player, InteractionHand)` へ（`BlockPos`/`Level`/`InteractionResult` 化）
- [ ] `addInformation` → `appendHoverText(ItemStack, Level, List<Component>, TooltipFlag)` (`ChatFormatting`, `Component`)
- [ ] `ItemFood` コンストラクタ: `super(heal, saturation, isWolfFood)` → `Item.Properties.food(new FoodProperties.Builder().nutrition().saturationMod().build())` (1.14+)
- [ ] `ItemTool` / `ItemArmor` / `ItemBow` 継承は維持だが `Tier` / `ArmorMaterial` / `Item.Properties` 変更（`EnumHelper.addToolMaterial` → `TierSortingRegistry.registerTier`）
- [ ] `OreDictionary` → `TagKey<Item>` (`forge:*`, `c:*`) に移行
- [ ] 言語ファイル: `*.lang` → `assets/defeatedcrow/lang/*.json` (`item.defeatedcrow.fossilCannon`)

## 関連ドキュメント
- [Item 一覧](../items.md) - 全体索引
- [Block 一覧](../blocks.md) - ItemBlock はそちらで詳述（`ItemWoodBox` 等）
- [Fluid 一覧](../fluids.md) - 流体コンテナ連携（該当時）
- [移行ガイド](./migration-guide.md) - Item共通の移行手順
- [カテゴリ別一覧](./README.md) - 同カテゴリの他Itemへ

> 自動生成元: `src/main/java/mods/defeatedcrow/common/item/magic/ItemFossilCannon.java:1` / `DCsAppleMilk.java:295` / `MaterialRegister.java:258`
> 最終更新: 2026-08-23
