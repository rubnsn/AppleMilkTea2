# LoadIC2Plugin

> Source: `src/main/java/mods/defeatedcrow/plugin/IC2/LoadIC2Plugin.java:1`
> Target: `EU`
> Category: `Energy`

## 概要
IC2連携。EUItemHandler/EUSink/SourceでCharge交換。

## 登録情報
- **クラス**: `LoadIC2Plugin` (`src/main/java/mods/defeatedcrow/plugin/IC2/LoadIC2Plugin.java:1`)
- **呼出**: `LoadModHandler` が `Loader.isModLoaded("modid")` で存在チェック後 `try{ new LoadIC2Plugin().load(); }catch{}`
- **Target**: `EU`

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `IC2` (`ic2.api.energy.*`) | 維持だが IC2Classic/Exp でAPI変更。`CapabilityEnergy` 併用 | `EnergyStorage` に統合 |
| `GameRegistry.findItem("mod","item")` | `ForgeRegistries.ITEMS.getValue(RL)` | `BuiltInRegistries.ITEM` |

## 関連ドキュメント
- [Plugin 一覧](../plugins.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
