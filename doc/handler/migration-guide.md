# Handler 移行ガイド - 1.7.10 → 1.12.2 / 1.16.5 / 1.20.1

> 対象: `handler/*` 全12 + `PropertyHandler` / `AMTLogger`
> 最終更新: 2026-08-24（1.20.1ブラッシュアップ: 2026-08-24）  
> 関連: [ビルド移行ガイド](../build.md)

## 概要
Coord→BlockPos, OreDictionary→TagKey, CustomExplosion→Explosion, ItemStack null→EMPTY, `WorldSavedData`→`SavedData` 等の汎用ハンドラ移行。1.20.1では `Capability` → `ForgeCapabilities`、`BlockPos` の `Level` 化、`TagKey` の `Holder` 化が確定。

| 1.7.10 | 1.16+ | 1.20.1 |
|---|---|---|
| `Coord(int x,y,z)` / `Pos` | `BlockPos` (`net.minecraft.core.BlockPos`) | **同左 + `BlockPos` は `Vec3i` 継承、 `Level` / `BlockGetter` と併用** |
| `CoordListRegister` (chunk座標登録) | `WorldSavedData` / `Capability` | **`SavedData` + `DimensionDataStorage` + `SavedData.Factory` + `Holder`。チャンクロード時の座標保持は `SavedData` + `LevelAccessor` で** |
| `OreDictionary.registerOre("cropApple", stack)` | `TagKey<Item>` (`Tags.Items`, `forge:xxx`) + `TagsProvider` | **同左 + `TagKey.create(Registries.ITEM, new ResourceLocation("forge","crops/apple"))` + `Ingredient.of(tag)`** |
| `stack!=null` | `!stack.isEmpty()` + `ItemStack.EMPTY` | **同左** |
| `CustomExplosion` (`handler/CustomExplosion.java`) | `Level.explode(Entity, double x,double y,double z, float power, Level.ExplosionInteraction)` | **同左 + `ExplosionInteraction.TNT` / `BLOCK` / `NONE`。`MelonBomb` の爆発は `level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 3.0F, Level.ExplosionInteraction.TNT)`** |
| `RegisterOreHandler` | `TagKey` へ移行、OreDictionary廃止 | **同左 + `data/forge/tags/items` のJSONで代替** |
| `TimeHandler` / `AMTLogger` | 維持だが `FMLLog` → `LogManager.getLogger` | **同左** |
| `FluidContMap` | `CapabilityFluidHandler` | **同左 (`ForgeCapabilities.FLUID_HANDLER`)** |
| `GenkotuHandler` | 維持 | **同左** |

## 詳細

### Coord → BlockPos

```java
// 1.7.10
Coord c = new Coord(x,y,z);
world.getBlock(c.x, c.y, c.z);

// 1.20.1
BlockPos pos = new BlockPos(x,y,z);
level.getBlockState(pos).getBlock();
level.getBlockEntity(pos);
```

### OreDictionary → TagKey

```java
// 1.7.10
OreDictionary.registerOre("cropApple", new ItemStack(Items.apple));
Object[] recipe = new Object[]{"AAA", 'A', "cropApple"};

// 1.20.1
TagKey<Item> CROP_APPLE = TagKey.create(Registries.ITEM, new ResourceLocation("forge","crops/apple"));
Ingredient.of(CROP_APPLE);
// data/forge/tags/items/crops/apple.json
{ "replace": false, "values": ["minecraft:apple"] }
```

### CustomExplosion → Level.explode

```java
// 1.7.10
new CustomExplosion(world, entity, posX, posY, posZ, 3.0F).doExplosionA();

// 1.20.1
level.explode(null, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, 3.0F, Level.ExplosionInteraction.TNT);
```

## 検証
1. `grep -r "OreDictionary"` → 0件（TagKeyに置換）確認 — **WT-Bでは `handler/` + `common/tile` + `event/ShowOreNameEvent.java` で 0件を達成。残存は `recipe/` 5件 + `plugin/LoadOreDicHandler` 1件 + `common/DCsRecipeRegister.java` 1件で WT-A/C所有のため除外**（`doc/oredict-to-tagkey.md:39` の実装状態参照）
2. `grep -r "Coord("` → `BlockPos` 置換確認
3. `grep -r "CustomExplosion"` → `Level.explode` 置換確認
4. `gradlew build` で `SavedData` の `Factory` 型エラー解消確認

### WT-B固有検証（OreDictionary→TagKey）

```powershell
# WT-B所有内のみ 0件であることを確認（plan.md 3.2 の所有境界）
rg -n "OreDictionary" src\main\java\mods\defeatedcrow\handler src\main\java\mods\defeatedcrow\common\tile src\main\java\mods\defeatedcrow\event\ShowOreNameEvent.java
# → 0件が正。recipe/plugin/common/DCsRecipeRegister は WT-C/A所有のため対象外

# TagHelper の存在確認
Test-Path src\main\java\mods\defeatedcrow\handler\TagHelper.java  # True
# datapack タグ生成確認
(Get-ChildItem -Recurse src\main\resources\data\forge\tags\items).Count  # 156
(Get-ChildItem -Recurse src\main\resources\data\c\tags\items).Count     # 156
```

### 残存 OreDictionary 箇所の扱い（WT-B禁止編集のため触らない）

| ファイル | 所有WT | 対応方針 |
|---|---|---|
| `recipe/ProcessorRecipeRegister.java:109,222,226` | WT-C | `OreDictionary.getOres` → `TagHelper.getTagItems` + `Ingredient.of(TagKey)`, `itemMatches` → `TagHelper.itemMatches` に置換予定（WT-Cで実施） |
| `recipe/FondueRecipeRegister.java:103,142,164,203` | WT-C | 同上 |
| `recipe/OreCrushRecipe.java:49,51,53,55,...` | WT-C | `getOres("nugget*"/"dust*"/"ore*")` → `TagKey` 分岐に置換 |
| `recipe/ChocolateRecipe.java:43,69` | WT-C | `getOres(s)` + `WILDCARD_VALUE` → TagKey/ItemStack.is へ |
| `recipe/RegisterMakerRecipe.java:884` | WT-C | `registerOre("logYuzuWood")` → `data/forge/tags/items/logs.json` へ（既生成） |
| `plugin/LoadOreDicHandler.java:20,23` | WT-C | 既に `TagKey` 化済みだが `isEmpty()` 判定バグあり（WT-Cで修正） |
| `common/DCsRecipeRegister.java:25-33,2027,...` | WT-A/C混在 | `registerOre` / `getOres` の残存は WT-A の BlockItem 登録後に Tag 化（WT-A/Cで実施） |

## 関連
- [Handler 一覧](../handler.md) / [個別ページ索引](./README.md)
- [API 一覧](../api.md)
- `src/main/java/mods/defeatedcrow/handler/*`
