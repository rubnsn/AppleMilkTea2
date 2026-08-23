# Plate Recipe (`plateRecipe`)

> API: `IPlateRecipe / PlateRecipeRegister` (`src/main/java/mods/defeatedcrow/api/recipe/IPlateRecipe.java:1`)
> Register: `RegisterMakerRecipe.registerPlate()` (`src/main/java/mods/defeatedcrow/recipe/PlateRecipeRegister.java:1`)
> Manager: `RecipeRegisterManager.plateRecipe` (`src/main/java/mods/defeatedcrow/api/recipe/RecipeRegisterManager.java:1`)
> Block/Tile: `TeppanII (PlateRecipe, lava/fire, 40/100 tick)`

## 概要
ステーキ・魚焼き。HeatSource: lava/flowing_lava/fire。

## 登録情報
- **API**: `IPlateRecipe / PlateRecipeRegister` (`src/main/java/mods/defeatedcrow/api/recipe/IPlateRecipe.java:1`)
- **Register**: `RegisterMakerRecipe.registerPlate()`
- **Manager**: `RecipeRegisterManager.plateRecipe.register(...)`
- **例**: `RecipeRegisterManager.plateRecipe.register(new ItemStack(...), new ItemStack(...))`

## メソッド
- **register**: 素材->生成物の基本登録
- **registerHeatSource**: 熱源登録 (fire, furnace, lava)

## 取得・参照
- **getRecipe**: `RecipeRegisterManager.plateRecipe.getRecipe(ItemStack input)` -> `IPlateRecipe `
- **getRecipeList**: `getRecipeList()` で全件列挙 (NEI/CraftGuide handlerが利用)
- **RegisteredRecipeGet**: `src/main/java/mods/defeatedcrow/recipe/RegisteredRecipeGet.java:1` で統合取得

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `RecipeRegisterManager.plateRecipe.register(...)` (独自Registry) | 維持可能 (`RecipeManager` 前は独自) | `RecipeManager` + `RecipeType` / `RecipeSerializer` / `Recipe<?>` (JSON `data/defeatedcrow/recipes/*.json`) へ |
| `CraftingManager` / `GameRegistry.addRecipe` (バニラ) | `RegistryEvent.Register<IRecipe>` / `IForgeRegistryEntry` | `RecipeType` + `RecipeSerializer` (datapack) |
| `OreDictionary` (`String` 入力) | `OreDictionary` 維持 (1.12) | `TagKey<Item>` (`Tags.Items`, `forge:xxx`) |
| `NEI handler` (`plugin/nei/*`) | `JEI` (`IFocus`, `RecipeCategory`) | `JEI` / `REI` 維持だが `RecipeType` 連携 |

移行例 (1.16+ JSON):
```json
{
  "type": "defeatedcrow:plate",
  "ingredient": { "item": "defeatedcrow:leaf_tea" },
  "result": { "item": "defeatedcrow:filled_cup", "count": 1 }
}
```

## 関連ドキュメント
- [Recipe 一覧](../recipes.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
- [Block 一覧](../blocks.md) - 調理機器
- [API 一覧](../api.md)
