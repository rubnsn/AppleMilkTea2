# Brewing Recipe (`brewingRecipe`)

> API: `IBrewingRecipe / BrewingRecipe` (`src/main/java/mods/defeatedcrow/api/recipe/IBrewingRecipe.java:1`)
> Register: `RegisterMakerRecipe.registerBrewing()` (`src/main/java/mods/defeatedcrow/recipe/BrewingRecipe.java:1`)
> Manager: `RecipeRegisterManager.brewingRecipe` (`src/main/java/mods/defeatedcrow/api/recipe/RecipeRegisterManager.java:1`)
> Block/Tile: `Barrel (TileBrewingBarrel)`

## 概要
樽醸造。若い酒->完成酒 (7種)。

## 登録情報
- **API**: `IBrewingRecipe / BrewingRecipe` (`src/main/java/mods/defeatedcrow/api/recipe/IBrewingRecipe.java:1`)
- **Register**: `RegisterMakerRecipe.registerBrewing()`
- **Manager**: `RecipeRegisterManager.brewingRecipe.register(...)`
- **例**: `RecipeRegisterManager.brewingRecipe.register(new ItemStack(...), new ItemStack(...))`

## メソッド
- **register**: 素材->生成物の基本登録
- **registerRecipe**: `BrewingRecipe.instance.registerRecipe(Fluid young, Fluid aged)`

## 取得・参照
- **getRecipe**: `RecipeRegisterManager.brewingRecipe.getRecipe(ItemStack input)` -> `IBrewingRecipe `
- **getRecipeList**: `getRecipeList()` で全件列挙 (NEI/CraftGuide handlerが利用)
- **RegisteredRecipeGet**: `src/main/java/mods/defeatedcrow/recipe/RegisteredRecipeGet.java:1` で統合取得

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `RecipeRegisterManager.brewingRecipe.register(...)` (独自Registry) | 維持可能 (`RecipeManager` 前は独自) | `RecipeManager` + `RecipeType` / `RecipeSerializer` / `Recipe<?>` (JSON `data/defeatedcrow/recipes/*.json`) へ |
| `CraftingManager` / `GameRegistry.addRecipe` (バニラ) | `RegistryEvent.Register<IRecipe>` / `IForgeRegistryEntry` | `RecipeType` + `RecipeSerializer` (datapack) |
| `OreDictionary` (`String` 入力) | `OreDictionary` 維持 (1.12) | `TagKey<Item>` (`Tags.Items`, `forge:xxx`) |
| `NEI handler` (`plugin/nei/*`) | `JEI` (`IFocus`, `RecipeCategory`) | `JEI` / `REI` 維持だが `RecipeType` 連携 |
| `FluidStack` (`net.minecraftforge.fluids.FluidStack`) | 維持 | `FluidStack` + `FluidIngredient` (JEI) |

移行例 (1.16+ JSON):
```json
{
  "type": "defeatedcrow:brewing",
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
