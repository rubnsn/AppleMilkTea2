# Recipe API (`api/recipe/*`)

> Source: `src/main/java/mods/defeatedcrow/api/recipe/*`
> API: `ITeaRecipe/IIceRecipe/IPanRecipe/IPlateRecipe/IProcessorRecipe/IEvaporatorRecipe/IBrewingRecipe/IFondueRecipe/IChargeIce/RecipeRegisterManager`
> Category: `Recipe`

## 概要
レシピAPI。全調理機器の登録・取得。

## クラス一覧
- **API**: `ITeaRecipe/IIceRecipe/IPanRecipe/IPlateRecipe/IProcessorRecipe/IEvaporatorRecipe/IBrewingRecipe/IFondueRecipe/IChargeIce/RecipeRegisterManager` (`src/main/java/mods/defeatedcrow/api/recipe/*`)
- **参照**: `grep -r "Recipe" src/` で使用箇所列挙

## 移行 (1.12.2+)
| 1.7.10 API | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `RecipeRegisterManager.*.register` (独自) | 維持 | `RecipeType` / `RecipeSerializer` (JSON datapack) |
| `OreDictionary` 入力 (`String`) | `OreDictionary` 維持 | `TagKey<Item>` |

## 関連ドキュメント
- [API 一覧](../api.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
