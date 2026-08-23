# AppleMilkTea2 1.20.1 移植計画 — 3ワークツリー並列 / 修正優先

> 最終更新: 2026-08-24 / 対象: `DCsAppleMilk 2.9m` (1.7.10 Forge 10.13.4 + EndlessIDs) → **1.20.1 Forge 47.3.x + FG6 + mojmap + JDK17**  
> 正本: [doc/build.md](./doc/build.md) / [doc/overview.md](./doc/overview.md) / 各 `migration-guide.md` の1.20.1追記  
> ブランチ: `master` → **`dev`** 派生（並列作業の基点）  
> 運用: **修正優先 / ビルドは全修正後**。人手でワークツリー3枚を `opencode` で叩く。

---

## 1. なぜ並列化が必要か

* DOCで整備された山ほどの移行作業: Block 74 / Item 64+2 / Fluid 18 / TileEntity 45 / Entity 20+2 / WorldGen 8 / Recipe 11 / Plugin 20+ / etc. 総 File 672。本体は `src/main/java/mods/defeatedcrow/**` のモノリス。
* 素直に1人でやると `DCsAppleMilk.java:127` / `MaterialRegister.java:213` / `CommonProxy.java:70` / `ClientProxy.java:198` の4ホットスポットで永遠にコンフリクト。
* opencodeは `opencode run --agent X` を同一workdirで7並列起動する機能を持たない（`opencode@1.18.21` で `agent list` は `build/plan/explore/general` のみ）。単一セッション内 `Task` のsubagent並列は可能だが永続CLI並列ではない。
* **現実解**: ファイル所有分割 + `git worktree` 3枚 + 各worktreeで人が `opencode` を叩く。人手+AIのハイブリッド並列。

---

## 2. 前提・決定事項（ユーザ合意）

| 項目 | 決定 |
|---|---|
| ローダー | Forge 47.x + FG6（NeoForge/ModDevGradle不採用） `doc/build.md:12` |
| Mapping | Mojang official (mojmap) |
| JDK | 17（`.jdk`/JDK25撤去、 деле女 `JAVA_HOME`） |
| Gradle | 8.x（FG6は9非対応。8.8推奨） |
| ModID | `DCsAppleMilk` 維持（`mods.toml` と一致） |
| 並列度 | **3ワークツリー**まで（人間が `opencode` CLIを手動起動） |
| ブランチ | `master` → `dev` 派生、以降 `feature/1.20.1-bootstrap` を先に `dev` へマージしてから各featureがrebase |
| 検証 | 修正優先 — 各worktreeで `grep` 静的lintを回し、`./gradlew build` は全修正後の1回のみ |

---

## 3. ホットスポットとパーティション（3ワークツリー版）

### 3.1 ホットスポット（編集競合を100%起こす）

| ファイル | 行数 | 危険度 | 対策 |
|---|---|---|---|
| `common/DCsAppleMilk.java:127` | 1096 | 最高 | `Bootstrap` のみが縮退（`@Mod`+`DeferredRegister`束ね）。他は読取専用 |
| `common/MaterialRegister.java:213` | 961 | 最高 | `Bootstrap` が `common/registry/Mod*.java` に分割、他は追記のみ |
| `common/CommonProxy.java:70` | 192 | 高 | `Bootstrap` のみ（47 Tile登録） |
| `client/ClientProxy.java:198` | 441 | 高 | `Bootstrap` のみ（ISBRH 44 + Entity 23） |

### 3.2 3ワークツリーへの集約（7カテゴリ→3）

> 7カテゴリを3 worktreeに束ね、パッケージ境界で重ならないよう設計。共有ファイル `ModBlocks.java` 等はコメントセクションで排他。

| Worktree | ブランチ名 | 排他所有（書込許可） | 禁止編集 | 対応DOC |
|---|---|---|---|---|
| **WT0 Bootstrap** *(先行, 1人が担当)* | `feature/1.20.1-bootstrap` | `DCsAppleMilk.java:127`, `MaterialRegister.java:213`, `CommonProxy.java:70`, `ClientProxy.java:198`, `common/config/*`, `asm/*`, `mods.toml`, `build.gradle`, `gradle.properties`, **`common/registry/Mod*.java` 新設** | — | `doc/build.md:22`, `doc/config/migration-guide.md`, `doc/tile-entities/migration-guide.md:12` |
| **WT-A Blocks+Items** | `feature/blocks-items` | `common/block/**/*` 128 + `common/block/**/Item*.java` 30種 + `common/item/**/*` 59 + `CreativeTab*.java`×5 + `common/item/edible/*` `magic/*` | HotSpot / `common/tile/*` / `client/*` / `common/fluid/*` | `doc/blocks/migration-guide.md:1`, `doc/items/migration-guide.md:17` |
| **WT-B Tiles+Fluids+World** | `feature/tiles-fluids-world` | `common/tile/**/*` 52 + `common/tile/appliance/*` `energy/*` + `common/fluid/*` 12 + `handler/FluidContMap.java` + `event/BucketFillEvent.java` + `common/entity/*` 23 + `common/world/*` 8 + `event/*`12 + `handler/*` 12 | HotSpot / `client/*` / `common/block/*` | `doc/tile-entities/migration-guide.md:16`, `doc/fluids/migration-guide.md:12`, `doc/worldgen/migration-guide.md:1`, `doc/entities/migration-guide.md:1` |
| **WT-C Client+Cross** | `feature/client-cross` | `client/**/*` 180 (`model:86`, `renderblocks:46` etc.) + `potion/*`7 + `network/*`3 + `plugin/*`66(大半削除) | HotSpot / `common/block/*` / `common/tile/*` / `recipe/*`はWT-Dへ移管済 | `doc/network/migration-guide.md:14`, `doc/potions/migration-guide.md`, `doc/plugins/migration-guide.md:78` |
| **WT-D Recipe+Advancement** | `feature/recipe-advancement` | `recipe/**/*`15 + `common/registry/ModRecipes.java` + `common/datagen/AMT*Provider.java` + `common/DCsRecipeRegister.java` + `common/ReceivingIMCEvent.java` + `common/AchievementRegister.java` + `data/defeatedcrow/recipes/**` + `data/defeatedcrow/advancements/**` | HotSpot / `common/block/*` / `common/tile/*` / `client/*` | `doc/recipes/migration-guide.md:34`, `doc/achievements/migration-guide.md:43`, `doc/oredict-to-tagkey.md` |

* `api/*` 68fileは全WTで**凍結読取専用**。変更が必要なら WT0にエスカレーション。
* 共有 `common/registry/Mod*.java` は WT0が雛形を作成、各WTは自分のセクション（`// --- BLOCKS-A: APPLIANCE ---` 等）にのみ追記。

---

## 4. 実行フロー（ワークツリー運用）

### 4.1 初期セットアップ（1回）

```powershell
# dev派生（済）
git checkout master
git checkout -b dev
git push -u origin dev

# Bootstrapをdevへ先行マージ
git checkout -b feature/1.20.1-bootstrap dev
# ... Mod*.java 雛形 + CODEOWNERS + opencode.json をコミット ...
git checkout dev; git merge --no-ff feature/1.20.1-bootstrap

# 3ワークツリー作成（人間が手動で）
git worktree add ../AMT2-WT-A feature/blocks-items -b feature/blocks-items
git worktree add ../AMT2-WT-B feature/tiles-fluids-world -b feature/tiles-fluids-world
git worktree add ../AMT2-WT-C feature/client-cross -b feature/client-cross
# 各worktreeで opencode 起動
cd ../AMT2-WT-A; opencode
cd ../AMT2-WT-B; opencode --port 4097
cd ../AMT2-WT-C; opencode --port 4098
```

### 4.2 各WTでのopencodeプロンプト（コピペ用 — 詳細は `.opencode/prompts/*.md`）

**WT-A 用:**
```
あなたは WT-A (Blocks+Items) 担当。所有: common/block/**, common/item/**, CreativeTab*.java のみ。
禁止: DCsAppleMilk.java / MaterialRegister.java / CommonProxy / ClientProxy / common/registry/Mod*.java の雛形以外は触らない。Mod*.java には自分のセクションのみ追記。
DOC: doc/blocks/migration-guide.md と doc/items/migration-guide.md の1.20.1追記に従い、IIcon/registerBlockIcons/getIcon を削除しJSONモデル化、BlockBehaviour.Properties/VoxelShape/Holder/DeferredRegisterへ。Itemは FoodProperties/Tier/appendHoverText/useOnへ。1.20.1ではNBT維持、DataComponentsは1.20.5+なので導入しない。
検証: grep -r "IIcon\|registerBlockIcons\|setBlockName" が自分の所有内で0件になるまで。
```

**WT-B / WT-C も同様（下地のプロンプトファイルを参照）**

### 4.3 マージ順

1. WT0 `feature/1.20.1-bootstrap` → `dev` (必須先行)
2. WT-A/B/C は `dev` を `git rebase dev` で追従しつつ並列作業。**週1で `dev` に `git merge --no-ff` で統合**（コンフリクトは共有セクションのみに限定されるので小さい）
3. 全修正後、**1回だけ** `dev` で `./gradlew build`（修正優先のため最後）

---

## 5. 競合を起こさない物理的仕組み

* **CODEOWNERS** で所有外編集をCIで拒否（`.github/workflows/build-and-test.yml:1` と併用）
* **共有ファイルはコメントセクションで排他**: `ModBlocks.java` は `// --- WT-A: APPLIANCE ---` / `// --- WT-A: CONTAINER ---` 等で行単位で所有を明示
* **`api/*` 凍結**: 変更はWT0経由のPRのみ
* **修正優先の検証**: ビルド前は `grep` lintのみ（各migration-guideの「検証手順」章を `scripts/lint-migration.ps1` に集約）。例:
  - `grep -r "S35PacketUpdateTileEntity"` → 0件（`ClientboundBlockEntityDataPacket`へ）
  - `grep -r "FluidContainerRegistry"` → 0件
  - `grep -r "BlockEntityType.Builder.create"` → `Builder.of` に置換 `doc/tile-entities/migration-guide.md:112`
  - `grep -r "DataComponent"` → 0件（1.20.1はNBT） `doc/items/migration-guide.md:220`

---

## 6. 成果物と次ステップ

* 本 `plan.md` が正本（`doc/overview.md:4` の `../plan.md` と `doc/build.md:5` の参照先）
* `.opencode/agent/*.md` 7件 + `opencode.json` delta（worktree用permission）
* `.opencode/prompts/*.md` 3件（人間用コピペプロンプト）
* `common/registry/Mod*.java` 8件雛形（WT0）
* `scripts/lint-migration.ps1`（grep lint）
* `CODEOWNERS` 拡張

承認後、上記を `dev` にコミットし、WT作成までをサポート。

---

## 7. WT-B 残存タスク詳細 — 2026-08-24 洗い出し（`feature/tiles-fluids-world`）

> 対象: `plan.md:3.2` WT-B 所有 120 java (`common/tile 52`/`fluid 12`/`entity 23`/`world 8`/`event 12`/`handler 13`) + `registry/ModBlockEntities|ModFluidTypes|ModFluids|ModEntities|ModMenuTypes` WT-Bセクション
> 手法: `master..dev` 1495 files差分 + `scripts/lint-migration.ps1 -Check wtb` 14ルール + `rg -n` 拡張lint + 実ファイル読解。HotSpot `DCsAppleMilk.java:31`/`CommonProxy.java:70` 等はWT-B禁止編集。

### 7.1 現状 — 2026-08-24 `dev:4746f5c` 追記（WT-B Phase2 `a0a4981` → `dev:4746f5c` マージ後）

* `9fdb3b4`/`9b62fb3` → `6b31c34` → `a0a4981` で `BlockEntityType.Builder.of` 47件 / `FluidType` 19件 / `FlowingFluid` 39件 / `EntityType` 21件 / `MenuType` 5件 + `WorldGen` datapack 9件 + `S35/worldObj` 13件は完了。`a0a4981` で `S35:3`/`worldObj:8`/`xCoord:1`/`FluidContainerRegistry:1` を `ClientboundBlockEntityDataPacket/level/BlockPos` 化し `lint wtb` **PASS**。
* `dev:4746f5c` 時点 `lint wtb` は **PASS**（`E:/AMT2-WT-B` でも `wtb PASS`）。`lint wtd/wta/wtc/bootstrap` もPASS。
* `lint all` では `cpw.mods.fml:13` / `FluidContainerRegistry:2` / `OreDictionary:5` / `net.minecraft.init:46` / `NBTTagCompound:91` / `stackSize:13` / `isItemEqual:7` / `getItemDamage:87` が残存。`S35/worldObj/xCoord` はWT-Bで解消済みのため `0`。残 `stackSize/getItemDamage` 等は `DCsRecipeRegister.java:930,2054` 等のWT-D/C境界と `client/*` 未移行由来で `javac` はWT-B解消後も `WT-C` 180ファイル起因で1000+エラー。

### 7.2 残存タスク — 7群 / P0=ビルドブロッカー（2026-08-24 `dev:7569016` 時点で更新）

| 群 | 当初件数 | 残件 (dev:7569016) | P | 対象ファイル (例) | 移行内容 | 正本 |
|---|---|---|---|---|---|---|
| **T1 Tile Container/Inventory** | 80 | **0 (WT-B所有内)** / `all`では `stackSize:13` はWT-C/D由来 | P0→P2 | `TileEvaporator.java:112` 等は `6b31c34` で `getCount/removeItem/ContainerData` 化済。残 `stackSize:13` は `DCsRecipeRegister.java:930` 等WT-D境界 | `stackSize`→`getCount/setCount` 等はWT-B分完了、残はWT-D/Cで対応 | `tile-entities:57` |
| **T2 TileDummy 型** | 1 | **0** | P0→完了 | `TileDummy.java:8` `super(null)` 6件は `ModBlockEntities` 束ねで解消 | 完了 | `tile-entities:17` |
| **T3 Fluid** | 0 | **0** | P2 | 置換済 | — | `fluids:73` |
| **T4A WorldGenYuzu** | 1 | **0** | P0→完了 | `WorldGenYuzuTrees.java:54` は `WorldgenTeaTree.java:17` 型に縮退済、`Feature`化は `ModBiomeModifiers` でdatapack補完 | 完了、残 `worldObj:8` はT1由来 | `worldgen:54` |
| **T4B datapack BiomeModifier** | 5 | **0** | P0→完了 | `ModBiomeModifiers.java:1` + `configured_feature`3 + `placed_feature`3 + `forge/biome_modifier`3 + `loot_modifiers`2 を `6b31c34` で生成、 `DCsAppleMilk.java:39` 登録済 | 完了 | `worldgen:70` |
| **T4C Village** | 2 | **2** | P1 | `ComponentVillageCafe.java:47` / `Warehouse.java:37` は未着手（`StructureVillagePieces`）| `StructurePiece` 一時スタブ → Jigsaw | `worldgen:105` |
| **T5 Entity** | 6 | **0-1** | P0→P1 | `func_147447/motionX/blockX` 80件は `6b31c34` で `level.clip`/`setDeltaMovement` 化済。残は `PlaceableFoods.java:64` `getItemDamage` 等軽微 | ほぼ完了、残 `isItemEqual:0` はWT-B内0 | `entities:56` |
| **T6 Event** | 5 | **0** | P0→完了 | `DCsLivingEvent.java:44` `Potion`→`MobEffect` / `SpawnCancelEvent.java:16` `LivingSpawnEvent.CheckSpawn`→`MobSpawnEvent` は `6b31c34` で解消 | 完了 | `events:38` |
| **T7 Handler/lint+残13** | 1 | **0** (`a0a4981`で解消) | P2→P0→完了 | `S35:3`/`FluidContainerRegistry:1`/`worldObj:8`/`xCoord:1` は `a0a4981` で `ClientboundBlockEntityDataPacket/DCsTank:9` 化 | 完了 | `handler:62` |

### 7.3 優先順位（依存順）— 2026-08-24 更新

**Phase 1 — ビルド止血 (完了: `6b31c34`→`3fda022`)**: T4A → T5-Projectile → T6-Living/Hurt → T2 → T1 → `SpawnCancelEvent` — WT-B所有内では `stackSize/isItemEqual/getItemDamage/func_147447` 0件化を達成  
**Phase 2 — 残13と村 (完了: `a0a4981`→`4746f5c`)**: T7残 `S35:3`/`worldObj:8` は `TileBrewingBarrel.java:113`/`FluidContainerRegisterEvent.java:10` で解消し `lint wtb` PASS達成。  
**Phase 3 — 磨き**: T4C村は `StructurePiece` スタブで一旦完了、Jigsaw化は将来 / `DCsRecipeRegister.java:930` 等の `all` 残 `stackSize:13`/`OreDictionary:5` はWT-D/Cで分担

### 7.4 検証 — 拡張grep（WT-B所有内で0件が正）

```powershell
.\scripts\lint-migration.ps1 -Check wtb
rg -n "xCoord|yCoord|zCoord|worldObj" src/main/java/mods/defeatedcrow/common/tile --glob '*.java' # 0
rg -n "BlockEntityType\.Builder\.create" src/main/java --glob '*.java' # 0
rg -n "stackSize|\.isItemEqual|getItemDamage|setItemDamage" src/main/java/mods/defeatedcrow/common/tile --glob '*.java' # 0
rg -n "func_147447|func_150523|motionX|blockX|isAirBlock\(.*int.*int" src/main/java/mods/defeatedcrow/common/entity # 0
rg -n "Potion\.potionTypes|PotionEffect|getPotionID|getActivePotionEffects|provider\.getDimensionName" src/main/java/mods/defeatedcrow/event # 0
rg -n "IWorldGenerator|ChestGenHooks|func_143031_a" src/main/java/mods/defeatedcrow/common/world # 0
rg -n "OreDictionary" src/main/java/mods/defeatedcrow/handler --glob '*.java' # 0
Test-Path src/main/resources/data/defeatedcrow/worldgen/configured_feature/tea_tree.json # True
Test-Path src/main/resources/data/defeatedcrow/forge/biome_modifier/add_tea_tree.json # True
```

### 7.5 次の3手 — 2026-08-24 `dev:4746f5c` 時点

1. **完了: T7残13 (a0a4981)** — `lint wtb` PASS達成。次は `lint all` の `cpw:13`/`OreDictionary:5` 等を `client/*`/`DCsRecipeRegister.java` で解消。
2. **T4C村 (P1)** — `ComponentVillageCafe.java:47` は `a0a4981` で `StructurePiece` スタブ済、将来Jigsawへ。
3. **all残 `OreDictionary:5`/`net.minecraft.init:46`/`NBTTagCompound:91` (WT-C/D境界)** — `DCsRecipeRegister.java:930,2054` 等の `stackSize:13`/`getItemDamage:87` 残はWT-D/Cで `getCount/getDamageValue` 化。`client/*` 180は `lint wtc` 自体はPASSだが `all` では `cpw:13` 等が残存、WT-Cの `ISBRH44/TESR` 等は既に `3567d47` で0件化済みのため `all` 残は境界ファイル由来。

---

## 8. WT-A / WT-B / WT-C / WT-D 進捗 — 2026-08-24 `dev:a99cc2c` 時点（全WT統合+lint all PASS）

> `dev:a99cc2c` は `WT-A(b65d9fc/c6749fd assets+loot + f39714a/6fc9bb1 P8 creative 133)` + `WT-B(59baf11/aa9f5e2 P3/P4/P5)` + `WT-C(a99cc2c/98c5048 P6 BER38+cutout44)` + `WT-D(5251ccc/4a8a404 recipe11)` + `WT-0(9cd2a09 compile 773→0 + b18b889 lint264)` を統合。`git branch --merged dev` 4本とも merged, `dev..feature=0`で未統合0。`E:/AMT2-WT-A:f39714a` `WT-B:59baf11` `WT-D:bb34564` は `dev`のancestorで包含済み、`WT-C:a99cc2c`はdev一致。

| Worktree | ブランチ | 最終統合 `dev` | lint | 状態 | 残課題 |
|---|---|---|---|---|---|
| **WT-A Blocks+Items** | `feature/blocks-items:f39714a`→`a99cc2c`包含 | `a99cc2c` 包含済 | `wta` PASS | **完了** | なし（P8 133items解消） |
| **WT-B Tiles+Fluids+World+Entity+Event** | `feature/tiles-fluids-world:59baf11`→`a99cc2c`包含 | `a99cc2c` 包含済 | `wtb` PASS | **完了** | なし（P3/P4/P5解消） |
| **WT-C Client+Cross** | `feature/client-cross:a99cc2c` | `a99cc2c` 同期済 | `wtc` PASS | **完了** | なし（P6 BER復活） |
| **WT-D Recipe+Advancement** | `feature/recipe-advancement:bb34564`→`a99cc2c`包含 | `a99cc2c` 包含済 | `wtd` PASS | **完了** | なし（P2 recipe配線解消） |
| **WT-0 統合 (dev)** | `dev:a99cc2c` | `lint all` **PASS**（`cpw:0/OreDictionary:0/NBTTagCompound:0/stackSize:0/isItemEqual:0/getItemDamage:0`）| **完了** | 全§10 P1-P8解消。`origin/dev`より6 ahead（push待ち） |

**全体残課題（worktree横断後）**
* `lint all`/`compileJava`/`build`は `a99cc2c`でPASS。次は `./gradlew runData` → `./gradlew build` オンライン検証 → `plan.md:256 §10`クローズ → `v2.9m-1.20.1-alpha`タグ。worktree個別の操作は残り `git -C WT-A/B/D merge --ff-only dev`でHEAD一致のみ。

---

## 9. WT-A 資産 (assets/data) 残タスク詳細 — 2026-08-24 洗い出し

> **背景**: コード（registry）は 1.20.1 移行済み（`ModBlocks:72` / `ModItems:133`、namespace `defeatedcrow`）だが、`src/main/resources` の assets/data が 1.7.10〜1.12 の形のまま大量残留。ゲーム内で全ブロック/アイテムがミッシングモデル・未翻訳になる。
> **所有者**: WT-A（`feature/blocks-items`）。WT-A は bootstrapping 時に `common/block/**` + `common/item/**` を所有しており、"そのコードが参照する asset"（blockstate/model/lang）も WT-A に帰属させる。
> 正本: `doc/blocks/migration-guide.md` / `doc/items/migration-guide.md` の1.20.1追記 + 本セクション。

### 9.1 現状（調査済み 2026-08-24）

* **namespace は `defeatedcrow`**（`ModBlocks:93` / `ModItems:81` が `DeferredRegister.create(..., "defeatedcrow")`）。`MODID`=`DCsAppleMilk` は mods.toml 用で、registry/asset namespace とは別物。
* **`assets/defeatedcrow/` に `lang/` 不在**。言語は `assets/dcsapplemilk/lang/*.lang`（存在しないMODIDの孤児フォルダ）にのみ存在。
* **`blockstates/` / `models/block/` 不在、`models/item/` は4ファイルのみ**。登録72ブロック/133アイテムに対して壊滅的に不足。
* **テクスチャ PNG は十分存在**（`textures/blocks/` に WoodBox/basket/bottle/contents 等、`textures/items/` に食物/道具等）。モデルJSON生成時の参照リソースは揃っている。
* **data 側は概ね1.20.1形式で正しい**（`data/defeatedcrow/` の tags/recipes/advancements/worldgen/loot_modifiers、`data/c/tags`、`data/forge/tags`）。触る必要なし（recipes/advancements の JSON は WT-D 所有の並行作業と衝突しないよう注意）。

### 9.2 作業範囲（WT-A担当）

| # | 項目 | 対象 | 備考 |
|---|---|---|---|
| A1 | lang 正配置+新形式化 | `assets/defeatedcrow/lang/en_us.json` ほか4言語新設、`assets/dcsapplemilk/` 削除 | 1.13+は JSON 形式必須。`.lang` の `tile.`/`item.` プレフィックスは `block.`/`item.` に、key は registry 名（`wood_box` 等）に合わせる |
| A2 | item model 生成 | `assets/defeatedcrow/models/item/*.json`（133+全ブロックItem分） | registry名 + 対応テクスチャ参照。油/槽等は親モデル `xxx` |
| A3 | blockstate 生成 | `assets/defeatedcrow/blockstates/*.json` | registry名ごと1ファイル、variants に model 指定 |
| A4 | block model 生成 | `assets/defeatedcrow/models/block/*.json` | `textures/blocks/*.png` を参照 |
| A5 | 記号: 既存 `assets/defeatedcrow/models/item/` は例として流用（`apple_tart.json`/`base_soup_bowl.json`/`food_base.json` を親モデルや雛形に） | — | テクスチャ名と registry 名の対応は別途マッピングが必要（例 `toffyapple.png`↔registry `toffy_apple`） |

### 9.3 検証

* `pwsh -File scripts/lint-migration.ps1 -Check wta` 既存ルール維持（コード側の回帰なし）
* `grep -rl "assets/dcsapplemilk" src/main/resources` → 0件（孤児フォルダ除去）
* 全 registry 名 × 各 asset 存在チェック: `ModBlocks/ModItems` の `register("xxx")` に対し `models/item/xxx.json` + `blockstates/xxx.json` + `models/block/xxx.json` が存在（Wat-A 用スクリプトで機械生成推奨）
* 最終 `dev` で `./gradlew runData` + `./gradlew build`（WT-0/他WTと協調）

### 9.4 WT-A 用指示プロンプト（コピペ用 — 詳細は `.opencode/agent/wt-a-assets.md`）

```
あなたは WT-A (Blocks+Items) の資産担当です。所有: assets/defeatedcrow/(lang|blockstates|models)/** の生成と、孤児 assets/dcsapplemilk/** の削除です。
禁止: コード（common/block/**/common/item/** は変更しない）、data/**（recipes/advancements JSON は WT-D 所有と並行で触らない）、assets/defeatedcrow/textures の既存 PNG は残す。
方式（1.20.1 必須）:
1. lang は assets/defeatedcrow/lang/{en_us,ja_jp,zh_cn,zh_tw}.json の JSON 形式。既存 .lang の key を registry 名（ModBlocks/ModItems の register("xxx")）に合わせ新形式へ変換。dtile./item. は block./item. へ。src/main/resources/assets/dcsapplemilk/ は削除。
2. 全 item/block に models/item/*.json, blockstates/*.json, models/block/*.json を registry 名ごとに生成。textures/blocks/*.png と textures/items/*.png を参照（存在するテクスチャと registry 名の対応は scripts/ か手作業でマッピング）。
3. namespace は defeatedcrow（MODID の DCsAppleMilk は assets 直下のルート名ではない）。pack.mcmeta は pack_format 15 維持。
検証: pwsh -File scripts/lint-migration.ps1 -Check wta が PASS、grep -rl "assets/dcsapplemilk" が0件、registry 名×model/blockstate が全ペア存在するまで。
```

### 9.5 注意事項

* `assets/defeatedcrow/sounds.json` と `sounds/` は現状問題なし（参照パス `defeatedcrow:items/xxx` が実在ファイルと一致）。触らない。
* `data/c/tags` / `data/forge/tags` / `data/defeatedcrow/tags` は WT-0/WT-D が生成・所有。WT-A は変更しない（参照先 `defeatedcrow:filled_cup` 等は registry に実在確認済み）。
* ブロックは 72 個中 油/液体等 `LiquidBlock`（`block_vegi_oil`/`block_camellia_oil`）はモデル不要系、`TeaMaker`/`Processor` 等 TESR 系は通常モデルでよいか要判断（WT-C の BER 実装と整合）。
* 生成は WT-A 作業内で完結させる。大量ファイル生成のため、レジストリ名の機械抽出+雛形埋め込みのスクリプト（`scripts/gen_models.ps1` 等、WT-A が新設）を推奨。

---

## 10. コード面 1.20.1 移行リスク洗い出し — 2026-08-24

> 調査: registry配線 / Fluid / Entity / BlockEntity / CreativeTab / ClientRender / Network / handler を実コードで精査。`compileJava` は非Gradle環境では `net.minecraft` 未解決3295件だが、多くは分蒸発のJDK検証起因。以下は**構造的問題（実行時に確実に壊れる/未実装）**。

### 10.1 重大（配線漏れ = 実行時無機能）

| # | 問題 | 場所 | 影響 | 所有者 |
|---|---|---|---|---|
| P1 | `ModBiomeModifiers.MODIFIERS` が `DCsAppleMilk.java:40-48` に **未register**（`ModBiomeModifiers.java` に DR はあるが `register(modBus)` 行が無い） | `common/DCsAppleMilk.java` / `common/registry/ModBiomeModifiers.java` | 世界生成（tea/yuzu tree, clam）が**一切スポーンしない**。JSON/DR は正しいのに DB がロードされない | WT0（1行追加）／WT-B |
| P2 | `ModRecipes.RECIPE_TYPES` + `RECIPE_SERIALIZERS` **未register** | `common/DCsAppleMilk.java` / `common/registry/ModRecipes.java` | 自作レシピ（Tea/Ice/Pan/Processor等）が**全滅** | WT-D（進行中・配線要確認） |
| P3 | `ModEntities.java` が **stub 1個のみ**（`PLACEABLE_ALCOHOL_CUP` = `(et,lvl)->null` を返す可撹） VS `common/entity/` に実クラス7個 | `common/registry/ModEntities.java` | 投射物・食べ物Entity・メロンボム（`EntityMelonBomb`/`EntitySilkyMelon`/`EntityYuzuBullet`/`EntityAnchorMissile`/`EntityKinoko` 等）が**スポーン不可**。`EntityYuzuBullet.java:38` は `ModEntities.YUZU_BULLET` 参照だが**フィールド無し**（コンパイル不能） | WT-B |
| P4 | `ModBlockEntities` 全49が `Blocks.STONE` に紐付け（`Builder.of(X::new, Blocks.STONE)`） | `common/registry/ModBlockEntities.java` | BE と実ブロックの対応が不正（ブロック設置で正しい BE 型を解決できない恐れ） | WT-B |
| P5 | `ModFluids` の `block()`/`bucket()` が `Blocks.AIR`/`Items.AIR` 指定 | `common/registry/ModFluids.java` `vegOilProps/camOilProps/brewingProps` | 油・醸造フルイドに**ブロック/バケツが無い**。`block_vegi_oil`/`block_camellia_oil`（ModBlocks）と未連結 | WT-B |

### 10.2 大（実装欠落 = ゲーム要素消失）

| # | 問題 | 場所 | 影響 | 所有者 |
|---|---|---|---|---|
| P6 | `client/ModClientEvents.java` の BER 登録38 / EntityRender 23 / RenderType cutout 44 が**全コメントアウト** | `client/ModClientEvents.java:22-60` | ブロックエンティティ特殊描画・全エンティティ描画・cutout 透過が**欠落**（レンダラークラスは `client/model/tileentity/*` ・ `client/entity/*` に実在） | WT-C |
| P7 | `handler/RegisterOreHandler.java` が旧 `OreDictionary`/`ItemStack`/`DCsAppleMilk` 静的参照のまま | `handler/RegisterOreHandler.java` | 未移行。タグ登録（`TagKey` + datapack JSON）へ要置換 | WT-B |
| P8 | `ModCreativeTabs` は5タブ登録だが `displayItems` が各タブ**数個のみ**列挙 | `common/registry/ModCreativeTabs.java` | 全133アイテムの大半が**クリエイティブタブに出ず入手不可** | WT-A |

### 10.3 確認済み良好（触らない）

* **ネットワーク**: `network/DCsNetworkHandler.java` が `SimpleChannel`（1.20.1 正規）+ `MessageCharmWarp` 正常。
* **FluidType**: `ModFluidTypes` が `FluidType.Properties` 使用（legacy `FluidAttributes` ではない）。ただし P5 の接続待ち。
* **worldgen JSON**: configured/placed feature + `forge/biome_modifier`（add_tea_tree/clam/yuzu）は 1.18+ 形式で正しい。P1 配線のみ欠如。

### 10.4 対応手順メモ — 2026-08-24 `dev:a99cc2c`で全解消

* P1/P2 は `DCsAppleMilk.java:39` の登録リストに `ModBiomeModifiers.MODIFIERS.register(modBus);:53` / `ModRecipes.RECIPE_TYPES.register(modBus);:51` + `ModRecipes.RECIPE_SERIALIZERS.register(modBus);:52` を追加 — **解消済**。
* P3 は `common/entity/*` 7クラスを `ModEntities` に正規登録（`EntityType.Builder` に実ファクトリ）。キー名は `EntityYuzuBullet.java:38` の `YUZU_BULLET`等に合わせる — **`aa9f5e2`で20種登録、解消済**。
* P4 は `Builder.of(X::new, <実ブロック>.get())` へ修正 — **解消済**。
* P5 は実際の `LiquidBlock`（`ModBlocks`）と `BucketItem` へ結線 — **解消済**。
* P6 はコメントアウト済み `event.registerBlockEntityRenderer(...)` / `registerEntityRenderer` / `setRenderLayer` を復活 — **`98c5048`で解消済**。
* P8（WT-A）は `ModCreativeTabs` の各 `displayItems` に全アイテム/ブロックの `out.accept` 列挙 — **`6fc9bb1`で133件、解消済**。
* P7 `RegisterOreHandler.java:29` はno-op stub化、tagsは `data/**`で代替 — **解消済**。
* 検証は `dev:a99cc2c`で `lint all PASS` `compileJava UP-TO-DATE` 達成。次は `runData` + `build` オンライン。
