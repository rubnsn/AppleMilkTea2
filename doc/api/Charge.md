# Charge API (`api/charge/*`)

> Source: `src/main/java/mods/defeatedcrow/api/charge/*`
> API: `IChargeItem/IChargeableMachine/IChargeGenerator/ChargeItemManager`
> Category: `Energy`

## 概要
チャージAPI。電池/機器の充放電。batteryUpdate/chargeDif連携。

## クラス一覧
- **API**: `IChargeItem/IChargeableMachine/IChargeGenerator/ChargeItemManager` (`src/main/java/mods/defeatedcrow/api/charge/*`)
- **参照**: `grep -r "Charge" src/` で使用箇所列挙

## 移行 (1.12.2+)
| 1.7.10 API | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `ChargeItemManager` / `IChargeItem` (自作Energy) | `CapabilityEnergy` (`IEnergyStorage`) に置換推奨 | `EnergyStorage` / `Capability` |

## 関連ドキュメント
- [API 一覧](../api.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
