# VillageCreateHandleWarehouse

> Source: `src/main/java/mods/defeatedcrow/common/world/village/VillageCreateHandleWarehouse.java:1`
> Registry: `VillagerRegistry.instance().registerVillageCreationHandler(new VillageCreateHandleWarehouse())` (`DCsAppleMilk.java:722`)
> Config: `-`
> Class: `VillageCreateHandleWarehouse extends IWorldGenerator / StructureVillagePieces.Village`

## 概要
倉庫生成ハンドル。

## 登録情報
- **クラス**: `VillageCreateHandleWarehouse` (`src/main/java/mods/defeatedcrow/common/world/village/VillageCreateHandleWarehouse.java:1`)
- **登録**: `VillagerRegistry.instance().registerVillageCreationHandler(new VillageCreateHandleWarehouse())` at `DCsAppleMilk.java:722`
- **Config**: `-`

## 継承・インターフェース
- 継承: `StructureVillagePieces.Village / IVillageCreationHandler`

## プロパティ / 生成ロジック
- **登録**: `VillagerRegistry` + `MapGenStructureIO` で村構造物として登録
- **生成**: `Village` の `getVillagePieceWeight` 等で確率制御、村の道路連結

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `VillagerRegistry.registerVillageCreationHandler` + `MapGenStructureIO.func_143031_a` | 維持 (1.12) | `StructureFeature` / `JigsawManager` + `StructureRegistry` + `StructurePieceType` に全面刷新 |
| `ComponentVillageCafe extends Village` | 維持 | `JigsawPool` / `StructureTemplatePool` (datapack) |

## 関連ドキュメント
- [WorldGen / Village 一覧](../worldgen.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](../worldgen.md)
