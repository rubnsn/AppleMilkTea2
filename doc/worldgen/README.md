# WorldGen / Village 個別ページ索引

> 総数: WorldGen 3 + ChestGen 1 + Village 4 (Component×2 + Handle×2)
> 登録元: `src/main/java/mods/defeatedcrow/common/DCsAppleMilk.java:705,739-723`
> Config: `DCsConfig.java:37` (`notGenTeaTree`, `teaTreeGenValue`, `disableClam`, `villagerRecipeID`)

## 一覧

| クラス | ソース | 登録箇所 | config | 個別ページ | 説明 |
|---|---|---|---|---|---|
| `WorldgenTeaTree` | `world/WorldgenTeaTree.java:1` | `GameRegistry.registerWorldGenerator(new WorldgenTeaTree(),1)` | `notGenTeaTree`, `teaTreeGenValue` | [→](./WorldgenTeaTree.md) | 茶の木生成 |
| `WorldgenClam` | `world/WorldgenClam.java:1` | `registerWorldGenerator(new WorldgenClam(),2)` | `disableClam` | [→](./WorldgenClam.md) | ハマグリ砂浜 |
| `WorldGenYuzuTrees` | `world/WorldGenYuzuTrees.java:1` | （予備、未直接登録） | — | [→](./WorldGenYuzuTrees.md) | 柚子木（予備） |
| `AddChestGen` | `world/AddChestGen.java:1` | `(new AddChestGen()).addChestItems()` | — | [→](./AddChestGen.md) | チェスト追加 |
| `ComponentVillageCafe` | `world/village/ComponentVillageCafe.java:1` | `MapGenStructureIO.func_143031_a` `ViCafe` | `villagerRecipeID` | [→](./ComponentVillageCafe.md) | カフェ建物 |
| `ComponentVillageWarehouse` | `world/village/ComponentVillageWarehouse.java:1` | `MapGenStructureIO` `ViWarehouse` | `villagerRecipe2ID` | [→](./ComponentVillageWarehouse.md) | 倉庫建物 |
| `VillageCreateHandleCafe` | `world/village/VillageCreateHandleCafe.java:1` | `VillagerRegistry.registerVillageCreationHandler` | — | [→](./VillageCreateHandleCafe.md) | カフェ生成ハンドル |
| `VillageCreateHandleWarehouse` | `world/village/VillageCreateHandleWarehouse.java:1` | `registerVillageCreationHandler` | — | [→](./VillageCreateHandleWarehouse.md) | 倉庫生成ハンドル |

---

## 生成元
- テンプレ: WorldGenは本READMEをテンプレとして利用
- 移行ガイド: [`migration-guide.md`](./migration-guide.md)
- 一覧: [`worldgen.md`](../worldgen.md)
