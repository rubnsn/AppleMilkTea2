# Item 移行ガイド - 1.7.10 → 1.12.2 / 1.16.5 / 1.20.1

> 対象: `src/main/java/mods/defeatedcrow/common/item/**/*.java` / `common/block/**/Item*.java` / `common/fluid/Item*.java`
> 前提: Forge 1.7.10 (10.13.4.1614) + EndlessIDs → Forge 1.12.2 (14.23) / 1.16.5 (36.x) / 1.20.1 (47.x) へのバージョン移行

## 目次
- [1. 登録システムの変更](#1-登録システムの変更)
- [2. メタデータ廃止と亜種管理](#2-メタデータ廃止と亜種管理)
- [3. クライアント側レンダリング・モデル](#3-クライアント側レンダリングモデル)
- [4. メソッドシグネチャ移行表](#4-メソッドシグネチャ移行表)
- [5. Itemサブクラス別の移行](#5-itemサブクラス別の移行)
- [6. API / Capability 移行](#6-api--capability-移行)
- [7. 言語・テクスチャ・サウンド](#7-言語テクスチャサウンド)
- [8. チェックリスト（コピペ用）](#8-チェックリストコピペ用)
- [9. 参考: ブロック移行との連携](#9-参考-ブロック移行との連携)

---

## 1. 登録システムの変更

### 1.7.10 (現行)
```java
// DCsAppleMilk.java:240
public static Item bakedApple;
// MaterialRegister.java:762
DCsAppleMilk.bakedApple = (new ItemBakedApple(7,7,false)).setUnlocalizedName("defeatedcrow.bakedApple").setCreativeTab(DCsAppleMilk.applemilkFood);
// MaterialRegister.java:262
GameRegistry.registerItem(DCsAppleMilk.bakedApple, "defeatedcrow.bakedApple");
```

### 1.12.2 / 1.16.5 (移行後)
```java
// ModItems.java (新設推奨)
public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "defeatedcrow");
public static final RegistryObject<Item> BAKED_APPLE = ITEMS.register("bakedApple", () -> new ItemBakedApple(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(7).build()).tab(DCsAppleMilk.TAB_FOOD)));

// または RegistryEvent
@SubscribeEvent
public static void registerItems(RegistryEvent.Register<Item> e) {
    e.getRegistry().register(new ItemBakedApple(...).setRegistryName("defeatedcrow","bakedApple"));
}
```
- **必須**: `setUnlocalizedName` → `setTranslationKey` (1.11) → `setRegistryName(ResourceLocation)` に分離。`Item.Properties` で `tab`, `stacksTo`, `durability`, `food` を指定。
- **推奨**: `DCsAppleMilk` の static フィールドを `DeferredRegister` / `RegistryObject` に置換。`MaterialRegister` の巨大 `load()` を `ModItems`, `ModBlocks`, `ModFluids` に分割。
- **参照**: `MaterialRegister.java:247-314` / `DCsAppleMilk.java:238-351` / `CommonProxy` は `RegistryEvent` に移行。

### 1.20+ (新システム)
- `DeferredRegister` 維持、`ForgeRegistries.ITEMS` → `Registries.ITEM`。
- `Item.Properties` は `FoodProperties` が `FoodProperties.Builder` から `FoodProperties` Record へ。
- `CreativeModeTab` は `DeferredRegister<CreativeModeTab>` で別登録（`CreativeTabs` 廃止）。

---

## 2. メタデータ廃止と亜種管理

**1.7.10**: 多くのItemは `setHasSubtypes(true)` + `damage` (0-15, 一部は0-127) で亜種を表現。例:
- `leafTea:0=tea,1=mint,2=cassis,3=yuzu,4=camellia` (`ItemLeafTea.java:32-64`)
- `itemLargeBottle: type=meta &15, amount=(meta>>4)&7` (`ItemLargeBottle.java:1`)

**1.13+**: メタ廃止（`ItemStack` の `damage` は耐久専用）。対策3パターン:

| 現行パターン | 移行パターン | 例 |
|---|---|---|
| 5-15種の亜種 (`leafTea`, `mincedFoods`) | **個別Item化**: `leaf_tea`, `leaf_mint`, `leaf_cassis` を別 `RegistryObject` として登録。レシピ・辞書は `TagKey` で統合 | `leafTea` → `LEAF_TEA`, `LEAF_MINT`, ... |
| 100+種の流体ボトル (`itemLargeBottle` 0-127) | **NBT / Capability**: `ItemStack` の `tag` に `{Fluid: "sake_dc", Amount:200}` を保持。`BlockLargeBottle` の TileEntity と同期 | `itemLargeBottle` → `LARGE_BOTTLE` 1 Item + NBT |
| 耐久/残量 (`wipeBox2` 0-5000, `DCgrater` 耐久) | **Damage or NBT**: `damage` は耐久に限定、`getBarWidth` / `getBarColor` で表示。残量は `tag` | `wipeBox2` → `ItemStack` damage + `TileWipeBox2` |

**必要作業**:
- [ ] `getMetadata(int)` / `getSubItems` / `getUnlocalizedName(ItemStack)` のメタ分岐を削除 → 個別Item or NBT分岐
- [ ] `ModelLoader` の `overrides` predicate `damage` → `custom_model_data` / `nbt` predicate (1.16+ は `CustomModelData`)
- [ ] レシピは `ItemAPI.getItem("leafTea",1)` → `ModItems.LEAF_MINT.get()` または `TagKey` に置換。`ItemAPI` は `@Deprecated` で既に `GameRegistry.findItem` 推奨 (`ItemAPI.java:12`) だが、さらに `RegistryObject` / `ForgeRegistries` に置換。

---

## 3. クライアント側レンダリング・モデル

**1.7.10**:
```java
@SideOnly(Side.CLIENT)
private IIcon iconItemType[];
public IIcon getIconFromDamage(int par1){ return iconItemType[MathHelper.clamp_int(par1,0,4)]; }
public void registerIcons(IIconRegister reg){ iconItemType[0]=reg.registerIcon("defeatedcrow:leaf_raw"); ... }
```

**移行後**: 削除。`assets/defeatedcrow/models/item/*.json` + `textures/item/*.png` に移行。

```json
// assets/defeatedcrow/models/item/leafTea.json (1.12例、1.16も同様)
{
  "parent": "item/generated",
  "textures": { "layer0": "defeatedcrow:item/leaf_raw" },
  "overrides": [
    { "predicate": {"damage": 1}, "model": "defeatedcrow:item/leaf_mint" },
    { "predicate": {"damage": 2}, "model": "defeatedcrow:item/leaf_cassis" }
  ]
}
```
- `@SideOnly` / `IIcon` / `IIconRegister` / `MathHelper.clamp_int` は全削除。
- `ClientProxy.registerTex()` / `registerRenderers()` の `IIcon` 関連は削除、代わりに `ModelRegistryEvent` / `TextureStitchEvent` は自動化。

---

## 4. メソッドシグネチャ移行表

> 各Itemでオーバーライドされている主要メソッドの移行。`doc/items/*.md` の「オーバーライドメソッド一覧」と併読。

| メソッド (1.7.10) | シグネチャ (1.7.10) | 移行後 (1.12.2) | 移行後 (1.16.5) | 移行後 (1.20) | 備考 |
|---|---|---|---|---|---|
| `getIconFromDamage` | `IIcon getIconFromDamage(int)` | 削除 | 削除 | 削除 | JSON `overrides` |
| `registerIcons` | `void registerIcons(IIconRegister)` | 削除 | 削除 | 削除 | `ModelLoader` |
| `getUnlocalizedName(ItemStack)` | `String getUnlocalizedName(ItemStack)` | `getTranslationKey(ItemStack)` | `getDescriptionId(ItemStack)` | `getDescriptionId` 維持 | `I18n` |
| `getSubItems` | `void getSubItems(Item, CreativeTabs, List)` | `void getSubItems(CreativeTabs, NonNullList<ItemStack>)` | `void fillItemCategory(CreativeModeTab, NonNullList<ItemStack>)` | `fillItemCategory` + `ItemDisplayParameters` | 1.14でリネーム |
| `onItemUse` | `boolean onItemUse(ItemStack, EntityPlayer, World, int x,int y,int z, int side, float hitX,hitY,hitZ)` | `EnumActionResult onItemUse(EntityPlayer, World, BlockPos, EnumHand, EnumFacing, float, float, float)` | `ActionResultType onItemUse(ItemUseContext)` → `useOn(UseOnContext)` | `InteractionResult useOn(UseOnContext)` | `BlockPos`/`Level` |
| `onItemRightClick` | `ItemStack onItemRightClick(ItemStack, World, EntityPlayer)` | `ActionResult<ItemStack> onItemRightClick(World, EntityPlayer, EnumHand)` | `ActionResult<ItemStack> use(Level, Player, InteractionHand)` | `InteractionResultHolder<ItemStack> use` | `EnumHand` |
| `onEaten` / `onFoodEaten` | `ItemStack onEaten(ItemStack, World, EntityPlayer)` | `ItemStack onItemUseFinish(ItemStack, World, EntityLivingBase)` | `ItemStack finishUsingItem(ItemStack, Level, LivingEntity)` | 同左 | `FoodProperties` |
| `addInformation` | `void addInformation(ItemStack, EntityPlayer, List, boolean)` | `void addInformation(ItemStack, World, List<String>, ITooltipFlag)` | `void appendHoverText(ItemStack, Level, List<Component>, TooltipFlag)` | 同左 + `DataComponent` | `Component` |
| `hasEffect` | `boolean hasEffect(ItemStack)` | `boolean hasEffect(ItemStack)` (維持) | `boolean isFoil(ItemStack)` | 同左 | エンチャ光 |
| `getRarity` | `EnumRarity getRarity(ItemStack)` | `EnumRarity getRarity(ItemStack)` | `Rarity getRarity(ItemStack)` | 同左 | レア色 |
| `onBlockDestroyed` | `boolean onBlockDestroyed(ItemStack, World, Block, int x,int y,int z, EntityLivingBase)` | `boolean onBlockDestroyed(ItemStack, World, IBlockState, BlockPos, EntityLivingBase)` | `boolean mineBlock(ItemStack, Level, BlockState, BlockPos, LivingEntity)` | 同左 | `BlockState` |
| `itemInteractionForEntity` | `boolean itemInteractionForEntity(ItemStack, EntityPlayer, EntityLivingBase)` | `boolean itemInteractionForEntity(ItemStack, EntityPlayer, EntityLivingBase, EnumHand)` | `ActionResultType interactLivingEntity(ItemStack, Player, LivingEntity, InteractionHand)` | `InteractionResult` | |
| `doesContainerItemLeaveCraftingGrid` | `boolean doesContainerItemLeaveCraftingGrid(ItemStack)` | `boolean hasContainerItem` → `hasCraftingRemainingItem` | `boolean hasCraftingRemainingItem(ItemStack)` | 同左 | クラフト残留 |
| `getContainerItem` | `ItemStack getContainerItem(ItemStack)` | `ItemStack getContainerItem` → `getCraftingRemainingItem` | `ItemStack getCraftingRemainingItem(ItemStack)` | 同左 | |
| `getMetadata` | `int getMetadata(int)` | 維持だが非推奨 | 削除（メタ廃止） | 削除 | |
| `getArmorTexture` | `String getArmorTexture(ItemStack, Entity, int, String)` | `String getArmorTexture(ItemStack, Entity, EquipmentSlot, String)` | `String getArmorTexture` 維持だが `ArmorMaterial` 変更 | `HumanoidModel` | 1.16で `ArmorItem` |
| `getPlantType` | `EnumPlantType getPlantType(IBlockAccess, int x,int y,int z)` | `EnumPlantType getPlantType(IBlockAccess, BlockPos)` | `PlantType getPlantType(BlockGetter, BlockPos)` | 同左 | `IPlantable` |

**全Item共通の最小移行**: `registerIcons`/`getIconFromDamage` を削除しJSONモデル化すれば、通常の `Item` / `ItemFood` は 1.12 でほぼ動作（メタは `damage` で残る）。1.16+ で本格的な個別Item化/NBT化が必要。

---

## 5. Itemサブクラス別の移行

### 5.1 `Item` / `ItemFood` (`leafTea`, `bakedApple`, `mincedFoods` 等 30種)
- `setHasSubtypes` + `damage` → 1.13+で個別Itemへ分割（例: `leafTea` 5種 → 5 Items）。`Food` は `FoodProperties` に移行。
- `ItemFood` コンストラクタ `super(7,7,false)` → `super(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(7).build()))`。

### 5.2 `ItemTool` / `ItemPickaxe` / `ItemSword` / `ItemBow` (`chalcedonyKnife`, `chalcedonyHammer`, `onixSword`, `yuzuGatling` 等 10種)
- `ToolMaterial` (`EnumHelper.addToolMaterial("CHALCEDONY",2,128,5.0F,4.0F,18)`) (`DCsAppleMilk.java:504`) → `Tier` 登録 (`TierSortingRegistry.registerTier(new ForgeTier(2,128,5.0F,4.0F,18, TagKey.create(...), ()->Ingredient.of(Items.FLINT)))`).
- `func_150897_b` / `func_150893_a` (MCP 1.7) → `isCorrectToolForDrops` / `getDestroySpeed` (MCP 1.12) → `isCorrectToolForDrops` / `getDestroySpeed` 維持 (Mojang 1.16+)。
- `ItemBow` (`yuzuGatling`, `fossilCannon`) は `onPlayerStoppedUsing` → `releaseUsing` + `getUseDuration` / `getUseAnimation` (`UseAnim.BOW`)。`getItemIconForUseDuration` は削除。

### 5.3 `ItemArmor` (`monocle`)
- `ItemArmor(ArmorMaterial.IRON, proxy.addArmor("monocle"), 0)` → `ArmorItem(ArmorMaterials.IRON, Type.HELMET, props)` (1.16: `ArmorMaterial` / `Type`, 1.20: `ArmorItem.Type`).
- `getArmorTexture` → `ArmorMaterial` の `getName()` + `assets/.../models/armor/monocle_layer_1.png`。`proxy.addArmor` は削除。

### 5.4 `ItemSeeds` / `IPlantable` (`itemMintSeed`)
- `ItemSeeds` ( `super(Block, Block)` ) → `BlockItem` + `ItemNameBlockItem` / `BlockItem` で `IPlantable` 維持。`getPlantType`/`getPlant`/`getPlantMetadata` は `BlockPos` 版へ。
- 登録は `Block` と `Item` を同時登録（`DeferredRegister`）。

### 5.5 `ItemBucket` (`bucketVegiOil`, `bottleVegiOil` 等)
- `ItemBucket(Block)` → `BucketItem(Supplier<? extends Fluid>, Properties)` (1.16+ `Fluid` が `Supplier`)。`bottleVegiOil` は `BucketItem` ではなく `Item` + `FluidContainer` だが、維持可能。
- `FluidContainerRegistry.registerFluidContainer` → `CapabilityFluidHandler` / `FluidUtil` / `IFluidHandlerItem` (1.12+) → `FluidUtil.getFluidHandler` / `CraftingHelper`。`BucketFillEvent` は `FillBucketEvent` にリネーム。

### 5.6 `ItemBlock` (`ItemWoodBox`, `ItemVegiBag`, `ItemChocoGift` 等 30種)
- `ItemBlock(Block)` → `BlockItem(Block, Properties)` (1.12+ `ItemBlock` → `BlockItem`)。`GameRegistry.registerBlock` で `ItemBlock` を同時登録していたが、1.12+ は `RegistryEvent.Register<Item>` で `new BlockItem(block, props).setRegistryName(block.getRegistryName())` を別登録。
- `ICompressedItem.getDisassembledItem` / `getSubBlocks` は維持だが、`getSubBlocks` → `fillItemCategory`。

### 5.7 `EdibleEntityItem` / `EdibleEntityItem2` (`appleTart`, `appleSandwich`, `baseSoupBowl`)
- `spownEntityFoods(World, Player, ItemStack, x,y,z)` → `Level.addFreshEntity(new PlaceableTart(...))`。`IEdibleItem` / `IEdible` は `FoodProperties` + `finishUsingItem` に移行。

### 5.8 `IBattery` / `IChargeItem` (`batteryItem` は非実装だが参照、`yuzuGatling`, `fossilCannon`, `eightEyesArm`)
- 自作 `IBattery` (`api/charge/IBattery.java`) は `CapabilityEnergy` (`IEnergyStorage`) に置換推奨。`ChargeItemManager` → `Capability` / `EnergyStorage`。
- `getDurabilityForDisplay` / `showDurabilityBar` → `isBarVisible` / `getBarWidth` / `getBarColor` (1.16+)。

### 5.9 `IIncenseEffect` (`incenseApple` 等 11種)
- 維持可能だが `World, int x,int y,int z, EntityLivingBase` → `Level, BlockPos, LivingEntity` に変更。`Potion` → `MobEffect` / `MobEffectInstance` (1.16+)。

---

## 6. API / Capability 移行

| 1.7.10 API | 移行後 |
|---|---|
| `ItemAPI.getItem("leafTea",1)` (reflection, `@Deprecated`) | `ForgeRegistries.ITEMS.getValue(new ResourceLocation("defeatedcrow","leafTea"))` または `ModItems.LEAF_MINT.get()` |
| `GameRegistry.findItem/findBlock` | `ForgeRegistries.ITEMS.getValue` / `DeferredRegister` |
| `ChargeItemManager` / `IBattery` | `CapabilityEnergy` / `IEnergyStorage` (`capabilities/energy`) |
| `IIncenseEffect` | 維持 or `Capability` + `MobEffect` (`Potion` → `MobEffect`) |
| `ICompressedItem` | 維持 or `RecipeType` (`DecompressionRecipe`) |
| `OreDictionary.registerOre` | `TagKey<Item>` (`Tags.Items`, `forge:xxx`) |
| `FluidContainerRegistry` | `CapabilityFluidHandler` / `IFluidHandlerItem` |
| `EnumHelper.addToolMaterial` | `TierSortingRegistry.registerTier` |

---

## 7. 言語・テクスチャ・サウンド

- **言語**: `assets/defeatedcrow/lang/ja_JP.lang` (`item.defeatedcrow.leafTea_0.name=生茶葉`) → `assets/defeatedcrow/lang/ja_jp.json` (`"item.defeatedcrow.leafTea": "生茶葉"` , メタ分岐は個別キー `item.defeatedcrow.leafMint` に分割)。
- **テクスチャ**: `textures/items/*.png` は維持だが `IIconRegister` 登録は不要。`models/item/*.json` で `parent: item/generated` + `textures: {layer0: defeatedcrow:item/xxx}`。
- **サウンド**: 変更なし（`sounds.json` 維持）。

---

## 8. チェックリスト（コピペ用）

各Itemファイル（`doc/items/*.md` の個別チェックリストと併用）:

```markdown
- [ ] `setUnlocalizedName` → `setTranslationKey` + `setRegistryName` / `Item.Properties`
- [ ] `GameRegistry.registerItem` → `DeferredRegister` / `RegistryEvent`
- [ ] `IIcon`/`IIconRegister`/`registerIcons`/`getIconFromDamage` → JSONモデルへ
- [ ] `getUnlocalizedName(ItemStack)` → `getDescriptionId`
- [ ] `getSubItems` → `fillItemCategory` / `ItemGroup`→`CreativeModeTab`
- [ ] メタ管理 → 1.13+で個別Item/NBTへ分割（`hasSubtypes` 削除）
- [ ] `onItemUse`/`onItemRightClick` → `useOn`/`use` (`BlockPos`/`Level`/`InteractionResult`)
- [ ] `addInformation` → `appendHoverText` (`Component`)
- [ ] `ItemFood` → `FoodProperties`
- [ ] `ToolMaterial` → `Tier` / `ArmorMaterial` → `ArmorItem.Type`
- [ ] `OreDictionary` → `TagKey`
- [ ] `lang/*.lang` → `lang/*.json`
```

---

## 9. 参考: ブロック移行との連携

- `ItemBlock` / `BlockItem` は `doc/blocks/_template.md` / `docs/blocks/migration-guide.md`（存在すれば）と併読。`BlockContainer` → `BlockEntity` (1.14+) の変更は `TileEntity` 側に影響。
- `Fluid` は `doc/fluids.md` + `BlockOilFluid` 等を `FluidType` / `LiquidBlock` に移行。


---

## 10. 1.20.1 追補（Data Components 注記）

> **重要: 1.20.1 はまだNBT、Data Componentsは1.20.5+**
> plan.mdのギャップ「Data Components(1.20.1=NBT vs 1.20.5=Componentsの区別なし)」を補完。

| バージョン | アイテムデータの保持方式 | 本MODでの対応 |
|---|---|---|
| 1.7.10 | `ItemStack` の `stackTagCompound` (`NBTTagCompound`) + `damage` でメタ管理 | `leafTea:0-4` / `largeBottle: meta &15` 等 |
| 1.12.2 / 1.16.5 | `CompoundNBT` (`CompoundTag`) + `damage` は耐久専用、Capability/NBTで亜種 | 個別Item化 or NBT `{Fluid:"sake", Amount:200}` |
| **1.20.1** | **NBT維持** (`CompoundTag` + `ItemStack.getTag()` / `getOrCreateTag()` / `saveAdditional` 等)。`DataComponents` は未導入 | **本MODはNBITのまま**。`largeBottle` の `{Fluid, Amount}` は `CompoundTag` で維持。`FoodProperties` / `BucketItem` 等は `Item.Properties` で管理。`appendHoverText` / `use` / `finishUsingItem` 等もNBT参照のまま |
| 1.20.5+ | `DataComponents` (`DataComponentType` + `DataComponentMap` + `ItemStack.set(DataComponentType, value)`) に全面置換。`getTag()` は非推奨→削除 | **1.20.1移植ではDataComponentsへ移行しない**。将来1.21へ再移植する際に `DataComponents` への置換が必要だが、1.20.1ではNBTで正 |

### 1.20.1 での NBT 実装定型

```java
// 保存
CompoundTag tag = stack.getOrCreateTag();
tag.putString("Fluid", "defeatedcrow:sake");
tag.putInt("Amount", 200);
tag.putByte("Remain", (byte) remain);

// 読出
CompoundTag tag = stack.getTag();
if(tag != null && tag.contains("Fluid")){
  String fluid = tag.getString("Fluid");
  int amount = tag.getInt("Amount");
}

// ToolTip
public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag){
  CompoundTag tag = stack.getTag();
  if(tag != null) tooltip.add(Component.translatable("tooltip.defeatedcrow.fluid", tag.getString("Fluid")));
}
```

### 1.20.1 での個別Item化 vs NBT選択（再掲）

- 5-15種の亜種 (`leafTea`, `mincedFoods`) → 個別Item化（`LEAF_TEA`, `LEAF_MINT`...）を推奨。TagKeyで統合。
- 100+種の流体ボトル (`largeBottle` 0-127) → **NBT**で1Itemに集約（1.20.1でもDataComponents不要）。
- 耐久/残量 (`wipeBox2`, `DCgrater`) → `ItemStack` の `damage`（耐久）+ NBTの `Amount` で併用。

### 検証 1.20.1

- `grep -r "DataComponent"` → 0件（1.20.1では導入しない）を確認
- `grep -r "getTag()"` / `getOrCreateTag()` が `largeBottle` / `wipeBox2` に残存することを確認（1.20.1はNBT）
- `gradlew build` で `Item.Properties` の `food` / `stacksTo` / `durability` が `FoodProperties` / `Item.Properties` の正しい型で登録されているか確認

## 関連ドキュメント
- [Item 一覧](../items.md) / [個別ページ索引](./README.md)
- 各Item個別ページ: `doc/items/*.md`（`_inventory.json` から生成）
- [Block 一覧](../blocks.md) / [Fluid 一覧](../fluids.md)
- [TileEntity 一覧](../tile-entities.md)
- ソース: `MaterialRegister.java:247`, `DCsAppleMilk.java:238`, `ItemAPI.java:54`

> 最終更新: 2026-08-23
