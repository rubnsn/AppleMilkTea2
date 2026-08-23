# LoadThaumcraftPlugin

> Source: `src/main/java/mods/defeatedcrow/plugin/LoadThaumcraftPlugin.java:1`
> Target: `TC4`
> Category: `Magic`

## 概要
Thaumcraft連携。

## 登録情報
- **クラス**: `LoadThaumcraftPlugin` (`src/main/java/mods/defeatedcrow/plugin/LoadThaumcraftPlugin.java:1`)
- **呼出**: `LoadModHandler` が `Loader.isModLoaded("modid")` で存在チェック後 `try{ new LoadThaumcraftPlugin().load(); }catch{}`
- **Target**: `TC4`

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `Thaumcraft` 4.x API | Thaumcraft 6 (1.12) で `thaumcraft.api.*` 変更 | Thaumcraft 1.16未対応で削除 |
| `GameRegistry.findItem("mod","item")` | `ForgeRegistries.ITEMS.getValue(RL)` | `BuiltInRegistries.ITEM` |

## 関連ドキュメント
- [Plugin 一覧](../plugins.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
