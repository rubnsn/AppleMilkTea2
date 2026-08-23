# WorldGen / Village 移行ガイド - 1.7.10 → 1.12.2 / 1.16.5 / 1.20.1

> 対象: `WorldgenTeaTree` / `WorldgenClam` / `AddChestGen` / `ComponentVillage*` / `VillageCreateHandle*`
> 登録元: `DCsAppleMilk.java:705,739-723`
> 最終更新: 2026-08-24（1.20.1ブラッシュアップ: 2026-08-24）  
> 関連: [ビルド移行ガイド](../build.md)

## 概要
WorldGenとVillageの移行対応。`IWorldGenerator` → `BiomeModifier` / `Feature` への刷新が中心。**1.16で導入された `BiomeLoadingEvent` は 1.19.4+で非推奨、1.20.1では `BiomeModifier`（ForgeのRegistry + Codec）+ `Holder`/`HolderGetter`/`PlacedFeature`/`ConfiguredFeature` の datapack駆動に全面置換。旧 `BiomeDictionary` は `TagKey<Biome>` + `Holder<Biome>` へ。村は `Jigsaw` → `Structure` + `StructureSet` + `TemplatePool` へ。**

> **ギャップ補足**（plan.md監査）: 旧DOCは 1.16 `BiomeLoadingEvent` 止まり。1.20.1では `BiomeModifier`/`DataMap`/`Holder` が必須。`BiomeLoadingEvent` は Forge 47では `net.minecraftforge.eventbus.api.Cancelable` ではなく `net.minecraftforge.common.world.BiomeModifierPhase` で管理される `BiomeModifier` に置換される。

## 登録の大局

| 1.7.10 | 1.12.2+ | 1.16.5+ | 1.20.1 | 参照 |
|---|---|---|---|---|
| `GameRegistry.registerWorldGenerator(new WorldgenTeaTree(), 1)` (`DCsAppleMilk.java:739`) | 維持 (`IWorldGenerator` は 1.12まで存続) | `BiomeLoadingEvent` / `BiomeModifier` + `ConfiguredFeature` (`Feature` + `Placement`) + `Registry.register(Registry.FEATURE)` | **Forge BiomeModifier: `DeferredRegister<BiomeModifier> MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIERS, "defeatedcrow")` + `BIOME_MODIFIERS.register("add_tea_tree", ()-> new AddFeaturesBiomeModifier(HolderSet.direct(Holder.direct(FeatureUtils.placedFeature(...))), GenerationStep.Decoration.VEGETAL_DECORATION))` + datapack `data/defeatedcrow/forge/biome_modifier/add_tea_tree.json`。`IWorldGenerator` は削除** | `world/WorldgenTeaTree.java:1` |
| `world.setBlock(x,y,z, block, meta, 2)` / `world.getBlock(x,y,z)` | `world.setBlockState(pos, state, 2)` / `world.getBlockState(pos)` | `world.setBlock(pos, state, 2)` | **同左 + `LevelAccessor` / `WorldGenLevel` 使用。`state.withProperty` → `state.setValue` (Mojmap)** | `WorldgenTeaTree.java:xx` |
| `BiomeDictionary.isBiomeOfType(biome, Type.FOREST)` (`BiomeGenBase`) | 維持 (`BiomeDictionary` 維持) | `TagKey<Biome>` (`BiomeTags` / `forge:is_forest`) + `Holder<Biome>` | **同左 + `BiomeTags.IS_FOREST` + `Holder<Biome>.is(TagKey)`。`ForgeRegistries.BIOMES.tags().createTagKey("forge", ...)`** | 同上 |
| `AddChestGen.addChestItems()` (`ChestGenHooks`) | `ChestGenHooks` → `LootTableLoadEvent` (`LootPool`追加) | `LootTable` (`LootModifier` / `GlobalLootModifier` + `LootTableLoadEvent`) | **同左 + `GlobalLootModifierProvider` (datagen) + `LootTableIdCondition` + `AddItemModifier`。`ChestGenHooks` は1.19で削除** | `world/AddChestGen.java:1` |
| `VillagerRegistry.registerVillagerId` / `registerVillageTradeHandler` / `registerVillageCreationHandler` | 維持 (1.12 `VillagerRegistry` 拡張) | `VillagerProfession` + `PoiType` + `VillagerTrades` + `StructureFeature` (`Jigsaw`) に全面刷新 | **同左 + `DeferredRegister<VillagerProfession>` + `DeferredRegister<PoiType>` + `Structure` は `Registry<Structure>` + `StructureSet` + `TemplatePool` (datapack `data/defeatedcrow/worldgen/structure/...`)** | `entity/VillagerCafe.java:1` / `world/village/*` |
| `MapGenStructureIO.func_143031_a(Component.class, "ViCafe")` | 維持 | `StructurePieceType` + `StructureFeature` + `JigsawManager` | **同左 + `StructureType` + `Codec` + `MapCodec`。`Structure` は `Holder<Structure>` で `PlacedFeature` と同様に管理** | `world/village/ComponentVillageCafe.java:1` |

## メソッド移行

| メソッド | 1.7.10 | 1.12.2 | 1.16.5 | 1.20.1 |
|---|---|---|---|---|
| `generate` | `void generate(Random, int chunkX,int chunkZ, World, IChunkProvider, IChunkProvider)` | 同左 | 削除 → `Feature.place(FeaturePlaceContext)` / `BiomeModifier.addFeature` | **削除。`ConfiguredFeature` + `PlacedFeature` + `BiomeModifier` に分離。`Feature.place(FeaturePlaceContext<BlockState>` は `LevelAccessor` を受ける** |
| `setBlock` | `world.setBlock(x,y,z, block, meta, 2)` | `world.setBlockState(pos, state.withProperty(...), 2)` | `world.setBlock(pos, state, 2)` | **同左 + `worldGenLevel.setBlock(pos, state, 2)`** |
| `getBlock` | `world.getBlock(x,y,z)` | `world.getBlockState(pos).getBlock()` | 同左 | **同左** |
| `getBiomeGenForCoords` | `world.getBiomeGenForCoords(x,z)` → `BiomeGenBase` | `world.getBiome(pos)` → `Biome` | `world.getBiome(pos)` → `Holder<Biome>` | **同左 (`Holder<Biome>` + `is(BiomeTags.IS_FOREST)` / `is(new ResourceLocation("forge","is_plains"))`)** |
| `ChestGenHooks.getInfo` | `ChestGenHooks.getInfo(String).addItem` | `LootTableLoadEvent` (`event.getTable().addPool`) | `GlobalLootModifierSerializer` | **同左 + `GlobalLootModifierProvider` / `LootModifier` + `LootTable` datapack `data/minecraft/loot_table/chests/simple_dungeon.json` への `AddItemModifier`** |
| `getVillagePieceWeight` (VillageHandle) | `int getVillagePieceWeight(Random,int)` | 維持 | `StructureFeature` の `Codec` + `JigsawPool` の重み | **同左 + `StructureSet` の `placement` + `Structure` の `spawnOverrides`** |

## WorldGen 1.16+ → 1.20.1 新システム例

### 1.16.5 ConfiguredFeature

```java
// 1.16.5 ConfiguredFeature
public static final RegistryObject<ConfiguredFeature<?,?>> TEA_TREE = CONFIGURED_FEATURES.register("tea_tree",
  ()-> Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(...).build()).decorated(Features.Placements.HEIGHTMAP).squared().count(5));

// BiomeModifier
@SubscribeEvent
public void onBiomeLoad(BiomeLoadingEvent e){
  if(e.getCategory()==BiomeCategory.PLAINS && !DCsConfig.notGenTeaTree){
    e.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TEA_TREE);
  }
}
```

### 1.20.1 PlacedFeature + BiomeModifier（Forge 47）

```java
// ConfiguredFeature (Holder方式)
public static final ResourceKey<ConfiguredFeature<?,?>> TEA_TREE_KEY = ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation("defeatedcrow","tea_tree"));
public static final ResourceKey<PlacedFeature> TEA_TREE_PLACED_KEY = ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation("defeatedcrow","tea_tree_placed"));

// Datapack: data/defeatedcrow/worldgen/configured_feature/tea_tree.json
// Datapack: data/defeatedcrow/worldgen/placed_feature/tea_tree_placed.json

// BiomeModifier登録（コード側）
public static final DeferredRegister<BiomeModifier> BIOME_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIERS, "defeatedcrow");
public static final RegistryObject<BiomeModifier> ADD_TEA_TREE = BIOME_MODIFIERS.register("add_tea_tree",
  () -> new AddFeaturesBiomeModifier(
    HolderSet.direct(HolderSetUtil.getOrThrow(registries.lookupOrThrow(Registries.BIOME), BiomeTags.IS_OVERWORLD) /* or TagKey */),
    HolderSet.direct(Holder.direct(placedFeature)), // Holder<PlacedFeature>
    GenerationStep.Decoration.VEGETAL_DECORATION));

// または JSON駆動: data/defeatedcrow/forge/biome_modifier/add_tea_tree.json
{
  "type": "forge:add_features",
  "biomes": "#minecraft:is_overworld",
  "features": "defeatedcrow:tea_tree_placed",
  "step": "vegetal_decoration"
}
```

- `BiomeLoadingEvent` は 1.20.1では **削除**。代わりに `AddFeaturesBiomeModifier` / `AddSpawnsBiomeModifier` / `RemoveFeaturesBiomeModifier` の3種（Forge 47）。
- `BiomeDictionary` → `TagKey<Biome>`。Configの `notGenTeaTree` / `disableClam` は `BiomeModifier` の条件に `Condition` として組込まず、datapack側で `biomes` タグで制御するか、Modifierの `Codec` に `ForgeConfigSpec` を参照する `ConditionalModifier` で対応。

### Clam生成（砂浜）

1.20.1では `WorldgenClam` は `Feature`（`CountOnEveryLayer` + `PlacementUtils.HEIGHTMAP`）に置換するか、`BiomeModifier` で `minecraft:beach` バイオームに `PlacedFeature` を追加。

### ChestGen 1.20.1 GlobalLootModifier

```java
// GlobalLootModifier: data/defeatedcrow/loot_modifiers/add_tea.json
{
  "conditions": [{ "condition": "minecraft:loot_table_id", "loot_table_id": "minecraft:chests/simple_dungeon" }],
  "type": "defeatedcrow:add_item",
  "item": "defeatedcrow:leaf_tea",
  "count": { "min": 1, "max": 3 },
  "chance": 0.05
}
// Provider
public class ModLootModifiers extends GlobalLootModifierProvider {
  public ModLootModifiers(PackOutput output){ super(output, "defeatedcrow"); }
  protected void start(){ add("add_tea", new AddItemModifier(new LootTableIdCondition[]{ new LootTableIdCondition(new ResourceLocation("minecraft","chests/simple_dungeon")) }, ModItems.LEAF_TEA.get(), 5)); }
}
```

## Village 1.20.1 刷新

| 1.7.10 | 1.16.5 | 1.20.1 |
|---|---|---|
| `VillagerRegistry.instance().registerVillagerId(id)` | `DeferredRegister<VillagerProfession> PROFESSIONS` + `VillagerProfession` (`PoiType` + `SoundEvent`) | **同左 + `PoiType` は `PoiTypes` + `Holder<PoiType>` + `VillagerProfession` の `workSound` は `SoundEvent` のHolder。`villagerReborn` 的な再登録は `VillagerTradesEvent` で補足** |
| `VillagerRegistry.instance().registerVillageTradeHandler(id, handler)` | `VillagerTradesEvent` (`VillagerTrades.trades.get(Profession).add(...)`) | **同左** |
| `ComponentVillageCafe extends StructureVillagePieces.Village` | `JigsawManager.addPiece` + `StructurePoolElement` + `TemplatePool` (datapack `data/defeatedcrow/worldgen/template_pool/village/cafe.json`) | **同左 + `Structure` は `StructureType` + `Codec` で登録し、`StructureSet` でバイオームタグ `village/plains` 等に紐付。`processor` に `BlockIgnoreProcessor` 等を使用** |
| `MapGenStructureIO` | `StructureFeature` + `Codec` + `Registry.register(Registry.STRUCTURE_FEATURE, ...)` | **同左 + `Registries.STRUCTURE` + `Registries.STRUCTURE_SET` + `Registries.TEMPLATE_POOL`。`DataPack` の `structure/nbt` は `data/defeatedcrow/structures/village/cafe.nbt`** |

## 検証手順
1. `grep -r "setBlock(" world/` → `setBlockState` 置換確認。
2. `grep -r "IWorldGenerator"` → `Feature` + `BiomeModifier` 置換確認 (1.16+)。
3. `grep -r "ChestGenHooks"` → `LootTableLoadEvent` / `GlobalLootModifier` 置換確認。
4. 1.20.1追加: `grep -r "BiomeLoadingEvent"` → 0件（BiomeModifierに置換）を確認。`grep -r "BiomeModifier"` → 1件以上を確認。
5. 村生成は `Jigsaw` の datapack 検証 (`data/defeatedcrow/worldgen/structure` + `data/defeatedcrow/forge/biome_modifier`).

## 関連
- [WorldGen 一覧](../worldgen.md) / [個別ページ索引](./README.md)
- [Entity 一覧](../entities.md) - Villager
- [Config](../config.md)
- [ビルド移行ガイド](../build.md)
- `src/main/java/mods/defeatedcrow/common/world/WorldgenTeaTree.java:1`
- `src/main/java/mods/defeatedcrow/common/world/WorldgenClam.java:1`
