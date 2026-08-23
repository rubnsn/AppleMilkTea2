# LoadBCPlugin

> Source: `src/main/java/mods/defeatedcrow/plugin/LoadBCPlugin.java:1`
> Target: `BC`
> Category: `Energy`

## 概要
BuildCraft連携。

## 登録情報
- **クラス**: `LoadBCPlugin` (`src/main/java/mods/defeatedcrow/plugin/LoadBCPlugin.java:1`)
- **呼出**: `LoadModHandler` が `Loader.isModLoaded("modid")` で存在チェック後 `try{ new LoadBCPlugin().load(); }catch{}`
- **Target**: `BC`

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `Loader.isModLoaded` + try/catch | `ModList.get().isLoaded` (1.13+) | 同左 + `IEventBus` で条件登録 |
| `GameRegistry.findItem("mod","item")` | `ForgeRegistries.ITEMS.getValue(RL)` | `BuiltInRegistries.ITEM` |

## 関連ドキュメント
- [Plugin 一覧](../plugins.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
