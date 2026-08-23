# Potion API (`api/potion/*`)

> Source: `src/main/java/mods/defeatedcrow/api/potion/*`
> API: `AMTPotionManager/IPotionGetter/PotionBaseAMT/PotionImmunityBase/PotionLivingBase/PotionReflexBase`
> Category: `Potion`

## 概要
ポーションAPI。EndlessIDs対応の取得マネージャ。

## クラス一覧
- **API**: `AMTPotionManager/IPotionGetter/PotionBaseAMT/PotionImmunityBase/PotionLivingBase/PotionReflexBase` (`src/main/java/mods/defeatedcrow/api/potion/*`)
- **参照**: `grep -r "Potion" src/` で使用箇所列挙

## 移行 (1.12.2+)
| 1.7.10 API | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `AMTPotionManager.manager` / `IPotionGetter` | `RegistryObject<MobEffect>` | `Holder<MobEffect>` |

## 関連ドキュメント
- [API 一覧](../api.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
