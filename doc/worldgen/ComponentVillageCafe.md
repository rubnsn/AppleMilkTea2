# ComponentVillageCafe

> Source: `src/main/java/mods/defeatedcrow/common/world/village/ComponentVillageCafe.java:1`
> Registry: `MapGenStructureIO.func_143031_a(ComponentVillageCafe.class,"ViCafe")` (`DCsAppleMilk.java:719`)
> Config: `villagerRecipeID`
> Class: `ComponentVillageCafe extends IWorldGenerator / StructureVillagePieces.Village`

## 概要
カフェ村生成。VillageCreateHandleCafeと連携。

## 登録情報
- **クラス**: `ComponentVillageCafe` (`src/main/java/mods/defeatedcrow/common/world/village/ComponentVillageCafe.java:1`)
- **登録**: `MapGenStructureIO.func_143031_a(ComponentVillageCafe.class,"ViCafe")` at `DCsAppleMilk.java:719`
- **Config**: `villagerRecipeID`

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
