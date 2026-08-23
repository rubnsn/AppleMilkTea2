# Edibles API (`api/edibles/*`)

> Source: `src/main/java/mods/defeatedcrow/api/edibles/*`
> API: `IEdibleItem/EdibleItem/EdibleItemBlock`
> Category: `Food`

## 概要
可食Block/Entity API。PlaceableFoods連携。

## クラス一覧
- **API**: `IEdibleItem/EdibleItem/EdibleItemBlock` (`src/main/java/mods/defeatedcrow/api/edibles/*`)
- **参照**: `grep -r "Edibles" src/` で使用箇所列挙

## 移行 (1.12.2+)
| 1.7.10 API | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `IEdibleItem/EdibleItem/EdibleItemBlock` | 維持可能だが `BlockPos`/`Level` 化 | `Capability` / `TagKey` 連携 |

## 関連ドキュメント
- [API 一覧](../api.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
