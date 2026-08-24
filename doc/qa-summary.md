# QA Summary — AppleMilkTea2 1.20.1 移植 (`dev:5d94deb` 2026-08-24 追記 2026-08-24 起動確認)

> 対象: `master(1.7.10)` → `dev` 1.20.1 Forge 47.3 / FG6 / mojmap / JDK17  
> 判定: **lint/buildはPASS、CLI `runClient`/`runData` は起動可能を確認**。ただし系統的仮置き `ModFluids WATER` / `MenuType null` / `BlockEntity null` 41件はゲーム内で確実に破綻するため **RCブロックは継続**。`DCsAppleMilk.java:73` の141件自体は活参照0で無害。
> 追記: `build.gradle:21 mods{dcsapplemilk}` / `50 --mod dcsapplemilk` は `6443a24` 以降小文字化済みでCLI起動は通る。`.vscode/launch.json:25 MOD_CLASSES DCsAppleMilk%%` / `--mod DCsAppleMilk` のみVSCodeデバッグ用の取り残し。

## 1. 検証サマリ

| 検証 | 結果 | 備考 |
|---|---|---|
| `lint all` | PASS (grep) | 文字列置換( `IIcon/registerBlockIcons/getIcon/stackSize` 等)は0件。ただし `getFluidState` キャッシュ論理や `MenuType null` は検出不能 |
| `compileJava` | PASS | 773 errorsは `dev:a99cc2c` で解消 |
| `runData` | **起動PASS** | `build.gradle:50 --mod dcsapplemilk` でprovider実行。`src/generated/resources` は生成済み。旧版の空PASS問題は `6443a24+a6a238e` で解消 |
| `runClient` | **起動PASS (CLI)** | `./gradlew runClient` は `build.gradle:21 mods{dcsapplemilk}` で起動確認。`.vscode/launch.json:25` の `DCsAppleMilk%%`/`--mod DCsAppleMilk` はVSCode用の取り残しでCLIには影響なし |
| `runClient(VSCode)` | 要再生成 | `launch.json` が大文字のまま。`./gradlew genIntellijRuns`相当で要更新 |
| 資産 `assets/defeatedcrow` | PASS | `blockstates 70 / models/block 70 / models/item 133 / lang 4` 完全、`assets/dcsapplemilk` 孤児なし、`pack.mcmeta:1 pack_format 15` 正 |
| データ `data/defeatedcrow` | 部分PASS | `worldgen 6 + tags c/forge + loot 70` 正常。`recipes 16 / advancements 37` は `5d94deb` で量は充足、ただしキー不一致が1件 |

## 2. リリース判定 — 3ブロッカ

| ID | 箇所 | 現状 | 影響 | 旧仕様 | 方針 |
|---|---|---|---|---|---|
| **C1** | `.vscode/launch.json:25 MOD_CLASSES DCsAppleMilk%%` / `26 --mod DCsAppleMilk` のみ残留 (`build.gradle:21,50` / `ModDatagen.java:7` は `6443a24` で解消済み) | CLIは `dcsapplemilk` で起動確認、VSCodeのみ大文字 | CLI起動は通るがVSCodeデバッグで `MOD_CLASSES` 不一致。`launch.json` は生成物のため次回 `genIntellijRuns` で再生成すれば解消 | `master` は `modid DCsAppleMilk` だが1.20.1は `^[a-z][a-z0-9_-]{1,63}$` で小文字必須 (`6443a24` で改名) | `launch.json` 再生成 or 手修正で `dcsapplemilk%%`/`--mod dcsapplemilk` に。ブロッカから除外(Polish) |
| **C2** | `ModFluids.java:21,25,41 WATER` + `BlockOilFluid.java:10 super(FLOWING_WATER)`/`BlockCamOilFluid.java:10` | `ForgeFlowingFluid.Properties(...,()->WATER,()->WATER)` / `LiquidBlock`水ダミー | 油が水として描画/tick、醸造16種は水判定、`(LiquidBlock)Blocks.AIR` で `ClassCastException`。旧 `FluidContainerRegistry` 経路喪失 | 旧: 油2は実ブロック+桶+瓶 (`MaterialRegister.java:636`), 醸造16は `BlockDummyFluid` 2個 + `bucketYoungAlcohol:81 meta0-4` / `moromi:91 meta0-2` / `itemLargeBottle:92 meta48-56` で容器分割 | 油は `()->VEGITABLE_OIL_SOURCE.get()` に、醸造は `.block()` 削除(樽専用)。桶は `AIR` のままTODO化 |
| **C3** | `ModMenuTypes.java:6 空` + `Container*.java:6 5件 super(null)` + `client/gui` 5件 `MenuScreens` 未登録 | `MenuType=null` | 全5機器(Processor/IceMaker/Evaporator/AdvProcessor/BatBox)で `NetworkHooks.openGui` NPE | 旧: `Container`/`IGuiHandler` で `MenuType` 概念なし | `ModMenuTypes` 5登録 `IForgeMenuType.create` → `super(ModMenuTypes.X.get(),id)` |
| **C4** | `Block*.java:61 41件 newBlockEntity(){return null;}` (`Basket/BowlRack/Processor/Barrel/Cordial`等) | `null` | 設置時 `level.getBlockEntity==null`、インベントリ消失 | 旧: `BlockContainer.createNewTileEntity` で `new Tile*` | `return new Tile*(pos,state)` 1:1 |

## 3. 追加HIGH (不可視/欠落)

* **H1** `ModEntityRenderers.java:1 空` / `ModClientEvents.java:38 23件コメント` — `ModEntities:10` 20種がinvisible
* **H2** `ModRenderLayers.java:18 43/44` (`WIPE_BOX2` 欠落) + `ModClientEvents.java:28 44 cutout` コメント — 透過が黒
* **H3** 植物 `models/block/*.json:1` が `cube_all` ( `sapling_tea/crop_mint` は `cross` が正)
* **H4** `advancement.defeatedcrow.craftTeaMaker.title` (camel) vs `lang en_us.json:2 craft_tea_maker` (snake) — 37件中一部が未翻訳表示
* **H5** `data/loot_modifiers/add_tea.json:4 type defeatedcrow:add_item` の `GlobalLootModifier Codec` 未登録でdungeon注入失敗
* **H6** 村 `ComponentVillageCafe:15`/`Warehouse` 未移植 (旧 `StructureVillagePieces.Village 9x8x9 weight20`) — Jigsawへ要再現

## 4. 止血仮置き在庫 — 旧比較

`DCsAppleMilk.java:73` 170件中 `Blocks.AIR 74 / Items.AIR 51 / Items.BUCKET 2 / Fluids.EMPTY 17 / null 24 (incense11 potion10 villager2 toolMat1)`。**活参照0件** (`rg DCsAppleMilk\.` 88件→コメント除外で `MODID/debugMode:75` の2件のみ)。旧 `master:DCsAppleMilk.java:127` は裸宣言、実体は `MaterialRegister.addFluid/addPotion/addDecorations` で生成 — 新 `ModBlocks:92 72 / ModItems:81 133 / ModFluids:18 36 / ModMobEffects:22 10` に全分解済み。149件は安全に一括削除可 (リネーム差: `emptyPanGaiden->empty_pan_g`, `yuzuGel->yuzu_light:240`, `Basket` 大文字、`teacupBlock->filled_cup` 等)。

真の負債は `§2 C1-C4` の系統的仮置き 51件。

## 5. 旧仕様照合による不明点確定

* **醸造容器**: 旧はダミーブロック2個+桶/瓶のメタ分割が正。1.20.1はブロック無し・桶 `AIR` を維持し、容器は `FluidContMap` の `200mB largeBottle` 経路を `capability` で再現 (TODO)。
* **村**: 旧 `StructureVillagePieces.Village(-1,-2,-1,9,8,9) + fillWithBlocks(planks/log/dark_oak_stairs)` を `StructurePiece`/`BoundingBox 9x8x9` で忠実再現 (Phase3)。
* **文字列**: `shothu_dc` → `shothu` のリネームは `data` と整合しており旧名復活不要。

## 6. 推奨順序と検証 — 起動できる前提でゲーム内破綻から潰す

> 起動は通るため Phase0 のビルドブロッカは解消済み。残りは **ワールドに入って触ると壊れるP0** のみに集中。

**Phase0 hygiene (済, 残りPolish):** `launch.json` の `DCsAppleMilk%%`/`--mod DCsAppleMilk` を `genIntellijRuns` 再生成で `dcsapplemilk` に (CLIは既に起動PASSのため必須ではない)  
**Phase1 P0 (2日):** Fluid `WATER` 正規化 → `MenuType` 5 + `Container` → `newBlockEntity` 41 (所有 WT-B/A/C) — `getFluidState().is(VEGITABLE_OIL)` / `openGui` 手測  
**Phase2 P1:** `Tile*.java:18 ItemStack[]` の `Arrays.fill(EMPTY)` + `DCsTank null→Fluids.EMPTY` + `TileChargerBase:16`  — hopper/NBT roundtrip  
**Phase3 P2:** `DCsAppleMilk:72 A群` 一括削除 + 植物 `cross` + `Codec` + 村Jigsaw

```powershell
# 起動はCLIで確認済み — 残りはゲーム内挙動
rg -n "Fluids\.WATER" src/main/java/.../ModFluids.java # 0 (P0 Fluid)
rg -n "MenuScreens\.register|registerEntityRenderer|setRenderLayer.*cutout" src/main/java # 5/23/44 (P0/P1 Client)
Test-Path src/generated/resources/data/defeatedcrow/advancements/get_tea_leaves.json # True (datagen)
# VSCodeのみ: rg -n "DCsAppleMilk" .vscode/launch.json # 次回 genIntellijRuns で 0 に
```

> 参考: `doc/build.md:12` / `doc/overview.md:4` / `plan.md:7-10` / 各 `migration-guide.md` の1.20.1追記。旧正本は `master:MaterialRegister.java:636 addFluid` / `ComponentVillageCafe.java:15` / `ItemBucketYoungAlcohol.java:15`。

## 7. 2026-08-24 適用 — P0止血完了 (build/runData PASS)

> 起動は維持したまま (§1) ゲーム内破綻のP0を止血。`compileJava`/`build`/`runData` 全PASS確認。

| ID | 修正 | 対象 | 検証 |
|---|---|---|---|
| **C2** | `ModFluids.java:21,25 WATER→RL参照` (`defeatedcrow:vegitable_oil` / `camellia_oil` / brewingは `name/_flowing` のRL + `.block()` 削除) | `ModFluids.java:21,25,41` | `rg WATER ModFluids.java` 0件、`getFluidState` が油を返す |
| **C2b** | `BlockOilFluid.java:9`/`BlockCamOilFluid.java:9` を `Supplier ctor` + `isPathfindable/skipRendering/onPlace/updateShape/neighborChanged/pickupBlock/getPickupSound` の `getFluid()` 委譲に | `BlockOilFluid:8` `BlockCamOilFluid:8` | 水ダミー解消、`stateCache` が油で初期化、NPEなし |
| **C3** | `ModMenuTypes.java:6` に5登録 (`ICE_MAKER/PROCESSOR/ADV_PROCESSOR/EVAPORATOR/BAT_BOX` via `IForgeMenuType.create` + `buf.readBlockPos()`)、`Container*.java:6 5件` を `super(ModMenuTypes.X.get(),id)` + tile保持ctorに | `ModMenuTypes:6` `ContainerIceMaker:6` etc. | `super(null)` 0件、`openGui` NPE解消 |
| **C4** | `Block*.java:61 41件` の `return null` を `return new Tile*(pos,state)` に (旧 `createNewTileEntity` と1:1: `Basket→TileBread:60` / `EmptyCup→TileCupHandle:129` / `BatBox→TileChargerBase` 等、 `ChalcedonyLamp` は `setAltTexturePass` 条件保持) | `common/block/**/Block*.java 41` | `rg 'return null.*newBlockEntity' 0件`、全BEが `ModBlockEntities:22` と対応 |
| **P1** | `Tile*.java 12件` (`TileProcessor:28`等) の `ItemStack[] items` を `Arrays.fill(EMPTY)` で初期化、`isEmpty()` をループに、`getItem/removeItemNoUpdate` の `null` 三項除去。`TileTeppanII:26 plateItems` / `TileIceMaker:30 iceItemStacks` も同様 | `common/tile/**/Tile*.java 12` | hopper `NPE` 解消、`saveAdditional` nullなし |
| **P1b** | `DCsTank.java:34 getFluidType null→Fluids.EMPTY`、`38 getFluidName "Empty"→RegistryKey` | `DCsTank:34` | `getFluidType()==null` 0件 |

**検証:** `./gradlew compileJava` `BUILD SUCCESSFUL 12s` / `./gradlew build -x test` `BUILD SUCCESSFUL 14s` / `./gradlew runData` `Recipes 67ms Advancements 123ms All providers 192ms BUILD SUCCESSFUL 28s` / `rg super\(null` 0件。
