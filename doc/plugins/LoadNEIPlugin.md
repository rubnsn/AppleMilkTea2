# LoadNEIPlugin

> Source: `src/main/java/mods/defeatedcrow/plugin/nei/LoadNEIPlugin.java:1`
> Target: `NEI`
> Category: `JEI`

## 概要
NEI連携。15 handler (Tea/Ice/Pan/Plate/Processor/Evaporator/Brewing/Choco/Fondue等)。

## 登録情報
- **クラス**: `LoadNEIPlugin` (`src/main/java/mods/defeatedcrow/plugin/nei/LoadNEIPlugin.java:1`)
- **呼出**: `LoadModHandler` が `Loader.isModLoaded("modid")` で存在チェック後 `try{ new LoadNEIPlugin().load(); }catch{}`
- **Target**: `NEI`

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `codechicken.nei.api.API` / `uristqwerty.CraftGuide.api` | `JEI` (`mezz.jei.api.*` / `JEIPlugin`) に置換。`IRecipeCategory` + `IRecipeHandler` | 同左 + `RecipeType` 連携 |
| `GameRegistry.findItem("mod","item")` | `ForgeRegistries.ITEMS.getValue(RL)` | `BuiltInRegistries.ITEM` |

## 関連ドキュメント
- [Plugin 一覧](../plugins.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
