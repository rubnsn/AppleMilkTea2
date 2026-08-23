# ItemAPI API (`api/ItemAPI.java:1`)

> Source: `src/main/java/mods/defeatedcrow/api/ItemAPI.java:1`
> API: `ItemAPI`
> Category: `Utility`

## 概要
Reflectionで `GameRegistry.findItem/findBlock` をラップ。getItem("leafTea",damage)等。@Deprecatedで非推奨。

## クラス一覧
- **API**: `ItemAPI` (`src/main/java/mods/defeatedcrow/api/ItemAPI.java:1`)
- **参照**: `grep -r "ItemAPI" src/` で使用箇所列挙

## 移行 (1.12.2+)
| 1.7.10 API | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `ItemAPI.getItem("leafTea",1)` (reflection, @Deprecated) | `ForgeRegistries.ITEMS.getValue(new ResourceLocation("defeatedcrow","leafTea"))` / `RegistryObject` | `ForgeRegistries.ITEMS` → `BuiltInRegistries.ITEM` |

## 関連ドキュメント
- [API 一覧](../api.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
