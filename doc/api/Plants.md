# Plants API (`api/plants/*`)

> Source: `src/main/java/mods/defeatedcrow/api/plants/*`
> API: `IRightClickHarvestable/PlantsClickEvent`
> Category: `Plants`

## 概要
植物API。右クリック収穫。TeaTree/Yuzu等。

## クラス一覧
- **API**: `IRightClickHarvestable/PlantsClickEvent` (`src/main/java/mods/defeatedcrow/api/plants/*`)
- **参照**: `grep -r "Plants" src/` で使用箇所列挙

## 移行 (1.12.2+)
| 1.7.10 API | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `IRightClickHarvestable/PlantsClickEvent` | 維持可能だが `BlockPos`/`Level` 化 | `Capability` / `TagKey` 連携 |

## 関連ドキュメント
- [API 一覧](../api.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
