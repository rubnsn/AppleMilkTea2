# Recipe 一覧

> 登録元: `src/main/java/mods/defeatedcrow/recipe/*` + `api/recipe/*` + `DCsAppleMilk.java:xxx` (`RegisterMakerRecipe`)
> Manager: `src/main/java/mods/defeatedcrow/api/recipe/RecipeRegisterManager.java:1` (全Manager集約)
> 総数: **11 RecipeType** (Tea / Ice / Pan / Plate / Processor / AdvProcessor / Evaporator / Brewing / Fondue / Chocolate / Charge)

## 概要
独自 `RecipeRegisterManager` に各 `*RecipeRegister` を集約。`RegisterMakerRecipe` が `MaterialRegister.load()` 後の `DCsRecipeRegister` から呼出。OreDictionary 対応 (`String` 入力) と `FluidStack` 対応が特徴。NEI/CraftGuide は `plugin/nei/*` / `plugin/craftguide/*` で表示。

## 一覧

| RecipeType | Managerフィールド | API | Registerクラス | 対応Block/Tile | 件数例 | 個別ページ | 説明 |
|---|---|---|---|---|---|---|---|
| Tea | `teaRecipe` | `ITeaRecipe` / `ITeaRecipeRegister` | `TeaRecipeRegister` | `TeaMakerNext` / `TileMakerNext` | 20 | [→](./recipes/TeaMaker.md) | 茶・ミルクティー。`registerCanMilk` でミルク分岐 |
| Ice | `iceRecipe` | `IIceRecipe` | `IceRecipeRegister` | `IceMaker` (`TileIceMaker`, charge) | 13+4 | [→](./recipes/IceMaker.md) | アイス。`registerCanLeave` で空カップ返却、`registerCharger` でクーラー |
| Pan | `panRecipe` | `IPanRecipe` | `PanRecipeRegister` | `EmptyPanGaiden` (`HeatSource` fire/furnace) | 10 | [→](./recipes/Pan.md) | 鍋料理。`registerHeatSource` |
| Plate | `plateRecipe` | `IPlateRecipe` | `PlateRecipeRegister` | `TeppanII` (`TileTeppanII`, lava/fire) | 7 | [→](./recipes/Plate.md) | 鉄板焼き。`registerHeatSource` |
| Processor | `processorRecipe` | `IProcessorRecipe` | `ProcessorRecipeRegister` | `Processor` (`TileProcessor`) | 50+ | [→](./recipes/Processor.md) | 汎用粉砕。おろし金移植、鉱石→粉 |
| AdvProcessor | `processorRecipe` (tier分岐) | `IProcessorRecipe` + `OreCrushRecipe` | `OreCrushRecipe` | `AdvProcessor` (`TileAdvProcessor`, dustDif/procDif) | 10+ | [→](./recipes/AdvProcessor.md) | 高性能粉砕。JawCrusher |
| Evaporator | `evaporatorRecipe` | `IEvaporatorRecipe` | `EvaporatorRecipeRegister` | `Evaporator` (`TileEvaporator`) | 15 | [→](./recipes/Evaporator.md) | 精油・若い酒・油 |
| Brewing | `brewingRecipe` | `IBrewingRecipe` | `BrewingRecipe` | `Barrel` (`TileBrewingBarrel`) | 7 | [→](./recipes/Brewing.md) | 樽醸造。若い酒→完成酒 |
| Fondue | `fondueRecipe` | `IFondueRecipe` | `FondueRecipeRegister` | `Fondue` / `baseSoupBowl` | 10 | [→](./recipes/Fondue.md) | フォンデュ。`SoupType` EMPTY→WATER/CHOCO/OIL/CHEESE |
| Chocolate | `chocoRecipe` | `IChocoFruitsRecipe` | `ChocolateRecipe` | `ItemChocoFruits` | 14 | [→](./recipes/Chocolate.md) | フルーツチョコ |
| Charge | `chargeItem` | `IChargeItemRegister` | `ChargeItemRegister` | `BatBox`/`Gel`/`HandleEngine` | 5 | [→](./recipes/ChargeItem.md) | 電池チャージ |

## 登録例

### Tea (`RegisterMakerRecipe.registerTea()` : `src/main/java/mods/defeatedcrow/recipe/RegisterMakerRecipe.java:21`)

```java
RecipeRegisterManager.teaRecipe.registerCanMilk(
  new ItemStack(DCsAppleMilk.foodTea,1,0), // 入力 (green tea)
  new ItemStack(DCsAppleMilk.teacupBlock,1,4), // 出力 水
  new ItemStack(DCsAppleMilk.teacupBlock,1,5), // 出力 ミルク
  "defeatedcrow:textures/blocks/contents_greentea.png",
  "defeatedcrow:textures/blocks/contents_greentea_milk.png");
```

### Ice (`registerIce()` : `...:134`)

```java
RecipeRegisterManager.iceRecipe.registerCanLeave(
  new ItemStack(DCsAppleMilk.teacupBlock,1,3),
  new ItemStack(DCsAppleMilk.blockIcecream,1,1),
  new ItemStack(DCsAppleMilk.emptyCup,1,0)); // 空カップ返却
RecipeRegisterManager.iceRecipe.registerCharger(new ItemStack(DCsAppleMilk.icyCrystal,1,0), 64);
```

### Pan/Plate (`registerPan()` / `registerPlate()`)

```java
RecipeRegisterManager.panRecipe.registerHeatSource(Blocks.fire, -1);
RecipeRegisterManager.panRecipe.register(
  new ItemStack(DCsAppleMilk.mincedFoods,1,0),
  new ItemStack(DCsAppleMilk.bowlBlock,1,1),
  new ItemStack(DCsAppleMilk.bowlJP,1,1), "kinoko", "Mushroom Soup");
RecipeRegisterManager.plateRecipe.register(new ItemStack(Items.beef), new ItemStack(DCsAppleMilk.foodPlate,1,0), 100, false);
```

### Processor (`registerProcessor()` : `...:325`)

```java
RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple,1,0), true, null, new Object[]{"cropApple"});
RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(Items.flint), false, 0, new ItemStack(Blocks.sand), new Object[]{new ItemStack(Blocks.gravel)});
```

### Evaporator / Brewing (`registerEvaporator()` / `registerBrewing()`)

```java
RecipeRegisterManager.evaporatorRecipe.addRecipe(new ItemStack(DCsAppleMilk.essentialOil,1,0), null, new ItemStack(Items.apple,8,0));
RecipeRegisterManager.evaporatorRecipe.addRecipe(null, new FluidStack(DCsAppleMilk.whiskey_young,100), new ItemStack(DCsAppleMilk.moromi,1,1), true);
BrewingRecipe.instance.registerRecipe(DCsAppleMilk.whiskey_young, DCsAppleMilk.whiskey);
```

## 取得・NEI連携

- **getRecipe**: `RecipeRegisterManager.teaRecipe.getRecipe(ItemStack input)` → `ITeaRecipe`。全Typeで `getRecipe` / `getRecipeList` が `plugin/nei/*` から呼出。
- **NEI**: `src/main/java/mods/defeatedcrow/plugin/nei/*` に 15 handler (`TeaRecipeHandler`, `IceRecipeHandler`, `PanRecipeHandler`, `ProcessorRecipeHandler`, `EvaporatorRecipeHandler`, `BrewingRecipeHandler`, `ChocoRecipeHandler`, `FondueRecipeHandler` 等)。`LoadNEIPlugin.java:1` で `API.registerRecipeHandler` + `registerUsageHandler`。
- **CraftGuide**: `src/main/java/mods/defeatedcrow/plugin/craftguide/*` に同等の 10 handler。
- **JEI移行**: 1.12+ は `JEIPlugin` で `IRecipeCategory` + `IFocus` に置換。

## 一覧詳細
- [個別ページ索引](./recipes/README.md) - 11個別ページへのリンク
- [移行ガイド](./recipes/migration-guide.md) - JSON レシピ移行

## 関連
- [Block 一覧](./blocks.md) - 調理機器
- [TileEntity 一覧](./tile-entities.md) - 機器Tile
- [API 一覧](./api.md) - `api/recipe/*`
- [Fluid 一覧](./fluids.md) - Evaporator/Brewing の Fluid
