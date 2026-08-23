# WorldGenYuzuTrees

> Source: `src/main/java/mods/defeatedcrow/common/world/WorldGenYuzuTrees.java:1`
> Registry: `（未登録、予備）` (`-`)
> Config: `-`
> Class: `WorldGenYuzuTrees extends IWorldGenerator / StructureVillagePieces.Village`

## 概要
柚子木生成。予備/内部利用。WorldgenTeaTree内で saplingYuzu 生成として実動作。

## 登録情報
- **クラス**: `WorldGenYuzuTrees` (`src/main/java/mods/defeatedcrow/common/world/WorldGenYuzuTrees.java:1`)
- **登録**: `（未登録、予備）` at `-`
- **Config**: `-`

## 継承・インターフェース
- 継承: `StructureVillagePieces.Village / IVillageCreationHandler`

## プロパティ / 生成ロジック

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `VillagerRegistry.registerVillageCreationHandler` + `MapGenStructureIO.func_143031_a` | 維持 (1.12) | `StructureFeature` / `JigsawManager` + `StructureRegistry` + `StructurePieceType` に全面刷新 |
| `ComponentVillageCafe extends Village` | 維持 | `JigsawPool` / `StructureTemplatePool` (datapack) |

## 関連ドキュメント
- [WorldGen / Village 一覧](../worldgen.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](../worldgen.md)
