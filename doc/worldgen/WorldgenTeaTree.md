# WorldgenTeaTree

> Source: `src/main/java/mods/defeatedcrow/common/world/WorldgenTeaTree.java:1`
> Registry: `GameRegistry.registerWorldGenerator(new WorldgenTeaTree(),1)` (`DCsAppleMilk.java:739`)
> Config: `notGenTeaTree / teaTreeGenValue (1-20)`
> Class: `WorldgenTeaTree extends IWorldGenerator / StructureVillagePieces.Village`

## 概要
茶の木生成。地上 (y70-100) で草上にランダム生成。Forestバイオームでカシス/柚子分岐。

## 登録情報
- **クラス**: `WorldgenTeaTree` (`src/main/java/mods/defeatedcrow/common/world/WorldgenTeaTree.java:1`)
- **登録**: `GameRegistry.registerWorldGenerator(new WorldgenTeaTree(),1)` at `DCsAppleMilk.java:739`
- **Config**: `notGenTeaTree / teaTreeGenValue (1-20)`

## 継承・インターフェース
- 継承: `IWorldGenerator`

## プロパティ / 生成ロジック
- **Dim**: `dimensionId !=1 && !=-1` (Overworldのみ)
- **Pos**: `chunkX<<4 + rand(16), 70+rand(30), chunkZ<<4+rand(16)`
- **Check**: `getBlockLightValue>11 && isAirBlock && getBlock(y-1)==Blocks.grass`
- **Forest分岐**: `BiomeDictionary.Type.FOREST` で `saplingTea` (寒冷は2: yuzu?), `FOREST.COLD`でcassis分岐
- **GenValue**: `DCsConfig.teaTreeGenValue` (1-20, クランプ済み)

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
