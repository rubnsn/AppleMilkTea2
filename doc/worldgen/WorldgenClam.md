# WorldgenClam

> Source: `src/main/java/mods/defeatedcrow/common/world/WorldgenClam.java:1`
> Registry: `GameRegistry.registerWorldGenerator(new WorldgenClam(),2)` (`DCsAppleMilk.java:740`)
> Config: `disableClam / clamChanceValue`
> Class: `WorldgenClam extends IWorldGenerator / StructureVillagePieces.Village`

## 概要
ハマグリ砂浜生成。海岸 (水際) でclamSandを生成。disableClamで無効化。

## 登録情報
- **クラス**: `WorldgenClam` (`src/main/java/mods/defeatedcrow/common/world/WorldgenClam.java:1`)
- **登録**: `GameRegistry.registerWorldGenerator(new WorldgenClam(),2)` at `DCsAppleMilk.java:740`
- **Config**: `disableClam / clamChanceValue`

## 継承・インターフェース
- 継承: `IWorldGenerator`

## プロパティ / 生成ロジック
- **海岸生成**: 砂上に `clamSand` を水際で配置
- **Chance**: `clamChanceValue` で確率制御

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
