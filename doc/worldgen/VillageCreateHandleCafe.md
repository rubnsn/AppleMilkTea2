# VillageCreateHandleCafe

> Source: `src/main/java/mods/defeatedcrow/common/world/village/VillageCreateHandleCafe.java:1`
> Registry: `VillagerRegistry.instance().registerVillageCreationHandler(new VillageCreateHandleCafe())` (`DCsAppleMilk.java:719`)
> Config: `-`
> Class: `VillageCreateHandleCafe extends IWorldGenerator / StructureVillagePieces.Village`

## 概要
カフェ生成ハンドル。村生成時に probabilistically 追加。

## 登録情報
- **クラス**: `VillageCreateHandleCafe` (`src/main/java/mods/defeatedcrow/common/world/village/VillageCreateHandleCafe.java:1`)
- **登録**: `VillagerRegistry.instance().registerVillageCreationHandler(new VillageCreateHandleCafe())` at `DCsAppleMilk.java:719`
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
