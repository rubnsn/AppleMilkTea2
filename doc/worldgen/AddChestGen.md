# AddChestGen

> Source: `src/main/java/mods/defeatedcrow/common/world/AddChestGen.java:1`
> Registry: `(new AddChestGen()).addChestItems()` (`DCsAppleMilk.java:705`)
> Config: `-`
> Class: `AddChestGen extends IWorldGenerator / StructureVillagePieces.Village`

## 概要
ダンジョン/村チェストへのアイテム追加。ChestGenHooks経由。

## 登録情報
- **クラス**: `AddChestGen` (`src/main/java/mods/defeatedcrow/common/world/AddChestGen.java:1`)
- **登録**: `(new AddChestGen()).addChestItems()` at `DCsAppleMilk.java:705`
- **Config**: `-`

## 継承・インターフェース
- 継承: `IWorldGenerator`

## プロパティ / 生成ロジック
- **Hook**: `ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST)` 等に `WeightedRandomChestContent` 追加
- **Items**: `leafTea`, `princessClam`, `foodTea` 等を低確率追加

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `IWorldGenerator.generate(Random, chunkX,chunkZ, World, IChunkProvider...)` | `IWorldGenerator` 維持 (1.12) | `BiomeLoadingEvent` / `BiomeModifier` / `ConfiguredFeature` / `PlacedFeature` (`Feature` + `Placement`) に刷新 |
| `world.setBlock(x,y,z, block, meta, 2)` | `world.setBlockState(pos, state, 2)` | `world.setBlock(pos, state, 2)` |
| `world.getBlock(x,y,z)` | `world.getBlockState(pos).getBlock()` | 同左 |
| `BiomeDictionary.isBiomeOfType(biome, Type.FOREST)` | 維持 (1.12) | `BiomeTags` / `TagKey<Biome>` + `Holder<Biome>` |
| `GameRegistry.registerWorldGenerator` | 同左 | `Registry.register(Registry.FEATURE, ...)` + `BiomeModifications` |

## 関連ドキュメント
- [WorldGen / Village 一覧](../worldgen.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](../worldgen.md)
