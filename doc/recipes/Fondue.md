# Fondue Recipe (`fondueRecipe`)

> API: `IFondueRecipe / FondueRecipeRegister` (`src/main/java/mods/defeatedcrow/api/recipe/IFondueRecipe.java:1`)
> Register: `RegisterMakerRecipe.registerSoupSource()` (`src/main/java/mods/defeatedcrow/recipe/FondueRecipeRegister.java:1`)
> Manager: `RecipeRegisterManager.fondueRecipe` (`src/main/java/mods/defeatedcrow/api/recipe/RecipeRegisterManager.java:1`)
> Block/Tile: `Fondue (Plate) / baseSoupBowl`

## 概要
フォンデュ。SoupType EMPTY->WATER/CHOCO/OIL/CHEESE。

## 登録情報
- **API**: `IFondueRecipe / FondueRecipeRegister` (`src/main/java/mods/defeatedcrow/api/recipe/IFondueRecipe.java:1`)
- **Register**: `RegisterMakerRecipe.registerSoupSource()`
- **Manager**: `RecipeRegisterManager.fondueRecipe.register(...)`
- **例**: `RecipeRegisterManager.fondueRecipe.register(new ItemStack(...), new ItemStack(...))`

## メソッド
- **register**: 素材->生成物の基本登録

## 取得・参照
- **getRecipe**: `RecipeRegisterManager.fondueRecipe.getRecipe(ItemStack input)` -> `IFondueRecipe `
- **getRecipeList**: `getRecipeList()` で全件列挙 (NEI/CraftGuide handlerが利用)
- **RegisteredRecipeGet**: `src/main/java/mods/defeatedcrow/recipe/RegisteredRecipeGet.java:1` で統合取得

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `RecipeRegisterManager.fondueRecipe.register(...)` (独自Registry) | 維持可能 (`RecipeManager` 前は独自) | `RecipeManager` + `RecipeType` / `RecipeSerializer` / `Recipe<?>` (JSON `data/defeatedcrow/recipes/*.json`) へ |
| `CraftingManager` / `GameRegistry.addRecipe` (バニラ) | `RegistryEvent.Register<IRecipe>` / `IForgeRegistryEntry` | `RecipeType` + `RecipeSerializer` (datapack) |
| `OreDictionary` (`String` 入力) | `OreDictionary` 維持 (1.12) | `TagKey<Item>` (`Tags.Items`, `forge:xxx`) |
| `NEI handler` (`plugin/nei/*`) | `JEI` (`IFocus`, `RecipeCategory`) | `JEI` / `REI` 維持だが `RecipeType` 連携 |

移行例 (1.16+ JSON):
```json
{
  "type": "defeatedcrow:fondue",
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
