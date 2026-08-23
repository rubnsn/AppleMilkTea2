# RecipeType

> API: `api/recipe/IRecipe.java:1` / `IRecipeRegister.java:1`
> Register: `src/main/java/mods/defeatedcrow/recipe/RecipeRegister.java:1`
> Manager: `RecipeRegisterManager.field` (`api/recipe/RecipeRegisterManager.java:1`)
> Block/Tile: `DCsAppleMilk.xxx` / `TileXxx`

## 概要
[1-2文でレシピの用途を記述。入力→出力、熱源/チャージ等の条件]

## 登録情報
- **API**: `IRecipe` / `IRecipeRegister` (`api/recipe/*`)
- **Register**: `RegisterMakerRecipe.registerXxx()` (`recipe/RegisterMakerRecipe.java:1`)
- **Manager**: `RecipeRegisterManager.xxx.register(...)`
- **例**: `RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(input), new ItemStack(output), "texture")`

## メソッド
- `register` / `registerCanMilk` / `registerCanLeave` / `registerCharger` / `registerHeatSource` / `addRecipe` 等

## 取得
- `getRecipe(ItemStack)` / `getRecipeList()` (`plugin/nei/*` が利用)

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `RecipeRegisterManager.xxx.register` (独自) | 維持 | `RecipeType` / `RecipeSerializer` (JSON) |
| `OreDictionary` (`String`) | 維持 | `TagKey` |
| `NEI` | `JEI` | `JEI` + `RecipeType` |

## 関連
- [Recipe 一覧](../recipes.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
