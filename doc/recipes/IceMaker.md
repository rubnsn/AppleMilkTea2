# IceMaker Recipe (`iceRecipe`)

> API: `IIceRecipe / IceRecipeRegister` (`src/main/java/mods/defeatedcrow/api/recipe/IIceRecipe.java:1`)
> Register: `RegisterMakerRecipe.registerIce()` (`src/main/java/mods/defeatedcrow/recipe/IceRecipeRegister.java:1`)
> Manager: `RecipeRegisterManager.iceRecipe` (`src/main/java/mods/defeatedcrow/api/recipe/RecipeRegisterManager.java:1`)
> Block/Tile: `IceMaker (BlockIceMaker, charge 64/8/16/4/1)`

## 概要
アイス13種+クーラー。registerCanLeaveで空カップ返却。

## 登録情報
- **API**: `IIceRecipe / IceRecipeRegister` (`src/main/java/mods/defeatedcrow/api/recipe/IIceRecipe.java:1`)
- **Register**: `RegisterMakerRecipe.registerIce()`
- **Manager**: `RecipeRegisterManager.iceRecipe.register(...)`
- **例**: `RecipeRegisterManager.iceRecipe.register(new ItemStack(...), new ItemStack(...))`

## メソッド
- **register**: 素材->生成物の基本登録
- **registerCanLeave**: 空容器返却あり登録
- **registerCharger**: クーラー登録 (crystal 64, ice 8, packed_ice 16, snow 4, snowball 1)

## 取得・参照
- **getRecipe**: `RecipeRegisterManager.iceRecipe.getRecipe(ItemStack input)` -> `IIceRecipe `
- **getRecipeList**: `getRecipeList()` で全件列挙 (NEI/CraftGuide handlerが利用)
- **RegisteredRecipeGet**: `src/main/java/mods/defeatedcrow/recipe/RegisteredRecipeGet.java:1` で統合取得

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `RecipeRegisterManager.iceRecipe.register(...)` (独自Registry) | 維持可能 (`RecipeManager` 前は独自) | `RecipeManager` + `RecipeType` / `RecipeSerializer` / `Recipe<?>` (JSON `data/defeatedcrow/recipes/*.json`) へ |
| `CraftingManager` / `GameRegistry.addRecipe` (バニラ) | `RegistryEvent.Register<IRecipe>` / `IForgeRegistryEntry` | `RecipeType` + `RecipeSerializer` (datapack) |
| `OreDictionary` (`String` 入力) | `OreDictionary` 維持 (1.12) | `TagKey<Item>` (`Tags.Items`, `forge:xxx`) |
| `NEI handler` (`plugin/nei/*`) | `JEI` (`IFocus`, `RecipeCategory`) | `JEI` / `REI` 維持だが `RecipeType` 連携 |

移行例 (1.16+ JSON):
```json
{
  "type": "defeatedcrow:icemaker",
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
