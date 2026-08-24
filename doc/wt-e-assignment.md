# WT-E 機能実装割当 — GUI垂直スライスまとめ

> 作成: 2026-08-25 / 割当先: WT-E (`E:/AMT2-WT-E` / `feature/stub-impl` or `feature/gui-functional`)  
> 前提決定: GUIテクスチャ流用 / 村は今後に回す / 醸造16種は LiquidBlock不要（樽+瓶で封じる）  
> 正本: `plan.md:11` / `test/IMPLEMENTATION_PLAN_WT-E.md` / `doc/qa-summary.md:6`

## 1. 結論

`dev:a99cc2c` で `lint all PASS`/`build PASS` だが `Container 5`/`Gui 5`/`Tile 13` の中身がスタブで機能チェック不能。`WT-E` に `G6 Recipe → G3 Tile → G1 GUI` の垂直スライスを割当て、`G2 Block use` 41件/`G4 Item` 15件を横展開する。村 `G8` と醸造 `LiquidBlock` は非スコープとし、GUIは `src/main/resources/assets/defeatedcrow/textures/gui/icemakergui.png` 等5枚を `GuiGraphics.blit` で流用する。

## 2. 割当スコープ（約90 files）

| 群 | 対象 `file:line` | 件数 | 所有 |
|---|---|---|---|
| **G6 Recipe** | `common/registry/ModRecipes.java:41` 11 `RecipeType` / `recipe/*Serializer.java:1` / `common/datagen/AMTRecipeProvider.java:75` | 12 | WT-E（`AMTRecipeProvider` の `Tea/Ice` 追記のみ、WT-D は `advancements`） |
| **G3 Tile** | `common/tile/TileBread.java:5` / `appliance/TilePanG.java:19` `TileTeppanII.java:21` `TileBrewingBarrel.java:21` / `energy/TileChargerBase.java:28` | 13 | WT-E |
| **G1 Container/Gui** | `common/tile/appliance/ContainerIceMaker.java:6` + `client/gui/GuiIceMaker.java:6` /  `Processor` / `AdvProcessor` / `Evaporator` / `BatBox` | 10 | WT-E |
| **G2 Block use** | `common/block/BlockBasket.java:40` `BlockBowlRack.java:50` `appliance/BlockTeaMakerNext.java:56` `plants/BlockCassisTree.java:46` | 41 | WT-E（`WT-A` 所有のうち `Block use` 41件に限定） |
| **G4 Item** | `common/item/edible/ItemClam.java:24` `EdibleEntityItem.java:97` / `appliance/ItemYuzuGatling.java:59` | 15 | WT-E |
| **G7 Fluid** | `common/registry/ModFluidTypes.java:41` / `common/fluid/ItemBucketVegiOil.java:1` | 0 | 追加なし（`f61ec2d` で油2種配線済） |

**非スコープ**: 村 `ComponentVillageCafe.java:19` / `Warehouse.java:19` / `VillagerCafe.java:16`、`LiquidBlock` 醸造16種 `ModFluids.java:41` の `block()`、`Gui` 新規描画。

## 3. フェーズ

### Phase E1: Recipe基盤（2d, WT-Dと排他は `AMTRecipeProvider` のみ）
* `recipe/base/AMTRecipeBase.java` 新設 — `Ingredient+ItemStack` の `fromJson/fromNetwork/toNetwork` 共通
* `Tea 20/Ice 17/Pan 10/Plate 7` の `Serializer` 4件を置換し `AMTRecipeProvider` で JSON 10件生成
* 検証: `src/generated/resources/data/defeatedcrow/recipes/*.json` 20+件

### Phase E2: Tile+Container+Gui 垂直スライス（3d, IceMakerで先行）
* Tile: `TilePanG/TeppanII/BrewingBarrel` の `load/saveAdditional` を `TileIceMaker.java:25` 型 (`ContainerHelper`) に統一
* Container: `ContainerIceMaker.java:6` 雛形 — `Slot(0:56,17) Slot(1:56,53) SlotFurnace(2:112,35) SlotFurnace(3:140,35)` 4件 + `SimpleContainerData(2)` (`cookTime,chargeAmount`) + `stillValid` + `quickMoveStack`（`moveItemStackTo`）。残4種は座標差替のみ（`Processor 0(9,9) 2-10(33+ k*18) 11(118,35)12(145,35)` / `AdvProcessor +13(91,16)` / `Evaporator 3-slot` / `BatBox 10-slot`）
* Gui: `GuiIceMaker.java:6` 雛形 — `ResourceLocation("defeatedcrow","textures/gui/icemakergui.png")` を `g.blit(TEX,k,l,0,0,176,166)`、進捗 `charge*16/127` `cook*24/150` を原典座標で再現。`ModClientEvents.java:58` の `MenuScreens.register` 5件は済

### Phase E3: Block use + Item 横展開（2d）
* Block: `BlockCPanel.java:194` 雛形 — `BlockBasket/BowlRack/ChopsticksBox` の `remain` は `TileHasRemaining.java:16` で代用、`TeaMaker/Barrel` 系は `WorldlyContainer` 挿抜 + `ITEM_PICKUP` 音
* Item: `ItemClam.java:79` `sand→clamSand` は `BlockPos` 版で済、残15件は `appendHoverText` のみ

## 4. 実装メモ

* **GUI流用**: `GL11→GuiGraphics`、`drawTexturedModalRect→g.blit(TEX,k,l,u,v,w,h,256,256)`、`I18n.format→Component.translatable`。`evaporatorgui.png` の `drawFluid` は簡易 `blit` で代替
* **Fluid**: `ModFluidTypes.java:41` の `IClientFluidTypeExtensions` は `still=defeatedcrow:block/fluid/<name>_still` で配線済、醸造16種は `source/flowing` のみで `block` 参照なし
* **所有**: `WT-E` は `dev:a99cc2c` 統合後で全ファイル編集可。`recipe` は `AMTRecipeProvider.java:75` に `// --- WT-E: Tea/Ice ---` コメントで排他

## 5. 検証

* `powershell -File scripts/lint-migration.ps1 -Check all` PASS
* `.\gradlew build` PASS / `.\gradlew runGameTestServer` 16→17 GREEN
* `runClient` 手動: `test/checklist.html` C群 `IceMaker/Processor/BatBox` 3項目を `doc/qa-summary.md:7` に追記

## 6. 成果物

* `ModRecipes:41` `DummySerializer` 解消 + `Container/Gui` 10件 + `Block use` 41件 + `doc/qa-summary.md:7` 1行
* ブランチ: `feature/stub-impl` 継続 or `feature/gui-functional` 新設、コミット `[E1-1]` 単位、7d
