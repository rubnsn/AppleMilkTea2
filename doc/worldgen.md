# WorldGen / Village 一覧

> 登録元: `src/main/java/mods/defeatedcrow/common/DCsAppleMilk.java:739-723`  
> 総数: WorldGen 2 + ChestGen 1 + Village 2系統  
> 個別ページ: [`doc/worldgen/`](./worldgen/README.md) に 8件の個別ページを生成  
> 移行ガイド: [`doc/worldgen/migration-guide.md`](./worldgen/migration-guide.md)

## WorldGen

| クラス | ソース | 登録箇所 | config | 説明 |
|---|---|---|---|---|
| `WorldgenTeaTree` | `src/main/java/mods/defeatedcrow/common/world/WorldgenTeaTree.java:1` | `GameRegistry.registerWorldGenerator(new WorldgenTeaTree(),1)` | `notGenTeaTree`, `teaTreeGenValue`(1-20) | 茶の木をワールド生成（頻度可変） |
| `WorldgenClam` | `src/main/java/mods/defeatedcrow/common/world/WorldgenClam.java:1` | `registerWorldGenerator(new WorldgenClam(),2)` | `disableClam` | ハマグリ砂浜を海岸に生成 |
| `WorldGenYuzuTrees` | `src/main/java/mods/defeatedcrow/common/world/WorldGenYuzuTrees.java:1` | （直接登録なし、Yuzu関連だが現在は未使用/予備） | — | 柚子木生成（予備/内部利用） |

- 茶の木生成は `DCsConfig.teaTreeGenValue` (1-20) で頻度制御、範囲外はクランプ (`DCsAppleMilk.java:740-744`)
- 無効時は生成スキップ。

## ChestGen

| クラス | ソース | 登録箇所 | 説明 |
|---|---|---|---|
| `AddChestGen` | `src/main/java/mods/defeatedcrow/common/world/AddChestGen.java:1` | `(new AddChestGen()).addChestItems()` at `DCsAppleMilk.java:705` | ダンジョン/村チェストへのアイテム追加 |

## Village

| 要素 | クラス | ソース | 登録名 | 説明 |
|---|---|---|---|---|
| Villager職業 | `VillagerCafe` | `src/main/java/mods/defeatedcrow/common/entity/VillagerCafe.java:1` | `villagerRecipeID` | カフェ店員（取引） |
| Villager職業 | `VillagerYome` | `src/main/java/mods/defeatedcrow/common/entity/VillagerYome.java:1` | `villagerRecipe2ID` | ヨメ（追加取引） |
| CreationHandle | `VillageCreateHandleCafe` | `src/main/java/mods/defeatedcrow/common/world/village/VillageCreateHandleCafe.java:1` | `ViCafe` | 村にカフェを生成 |
| Component | `ComponentVillageCafe` | `src/main/java/mods/defeatedcrow/common/world/village/ComponentVillageCafe.java:1` | `ViCafe` | カフェ建物構造 |
| CreationHandle | `VillageCreateHandleWarehouse` | `src/main/java/mods/defeatedcrow/common/world/village/VillageCreateHandleWarehouse.java:1` | `ViWarehouse` | 倉庫を生成 |
| Component | `ComponentVillageWarehouse` | `src/main/java/mods/defeatedcrow/common/world/village/ComponentVillageWarehouse.java:1` | `ViWarehouse` | 倉庫建物構造 |

登録コード:
```java
VillagerRegistry.instance().registerVillagerId(DCsConfig.villagerRecipeID);
VillagerRegistry.instance().registerVillageTradeHandler(DCsConfig.villagerRecipeID, villager);
VillagerRegistry.instance().registerVillageCreationHandler(new VillageCreateHandleCafe());
MapGenStructureIO.func_143031_a(ComponentVillageCafe.class, "ViCafe");
```

## 移行ドキュメント
- [個別ページ索引](./worldgen/README.md) - 8個別ページの一覧
- [移行ガイド](./worldgen/migration-guide.md) - IWorldGenerator→Feature移行
- [テンプレ](./worldgen/_template.md)

## 関連
- [Entity 一覧](./entities.md) - Villager詳細
- [Block 一覧](./blocks.md) - 生成される植物ブロック
- [Config](./config.md) - `notGenTeaTree` 等
