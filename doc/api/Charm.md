# Charm API (`api/charm/*`)

> Source: `src/main/java/mods/defeatedcrow/api/charm/*`
> API: `IIncenseEffect/EffectType`
> Category: `Magic`

## 概要
お香/チャームAPI。formEffect()で範囲効果。TileIncenseBaseがtick呼出。

## クラス一覧
- **API**: `IIncenseEffect/EffectType` (`src/main/java/mods/defeatedcrow/api/charm/*`)
- **参照**: `grep -r "Charm" src/` で使用箇所列挙

## 移行 (1.12.2+)
| 1.7.10 API | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `IIncenseEffect` (`World,int x,y,z,EntityLivingBase`) | `Level, BlockPos, LivingEntity` に変更 | `MobEffect` + `Capability` 検討 |

## 関連ドキュメント
- [API 一覧](../api.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
