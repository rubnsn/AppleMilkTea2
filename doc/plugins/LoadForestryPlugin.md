# LoadForestryPlugin

> Source: `src/main/java/mods/defeatedcrow/plugin/ffm/LoadForestryPlugin.java:1`
> Target: `FFM`
> Category: `Farm`

## 概要
Forestry連携。FarmableAMT/CropAMTPlants/FarmLogicAMTでFarmable登録。

## 登録情報
- **クラス**: `LoadForestryPlugin` (`src/main/java/mods/defeatedcrow/plugin/ffm/LoadForestryPlugin.java:1`)
- **呼出**: `LoadModHandler` が `Loader.isModLoaded("modid")` で存在チェック後 `try{ new LoadForestryPlugin().load(); }catch{}`
- **Target**: `FFM`

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `Forestry` (`forestry.api.farming.*` `IFarmable`) | `Farmable` APIは 1.12まで維持 | 1.16+ Forestry無しで削除 or `TagKey` |
| `GameRegistry.findItem("mod","item")` | `ForgeRegistries.ITEMS.getValue(RL)` | `BuiltInRegistries.ITEM` |

## 関連ドキュメント
- [Plugin 一覧](../plugins.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
