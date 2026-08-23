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
| **WT-C Client+Cross** | `feature/client-cross` | `client/**/*` 180 (`model:86`, `renderblocks:46` etc.) + `potion/*`7 + `recipe/*`15 + `network/*`3 + `plugin/*`66(大半削除) | HotSpot / `common/block/*` / `common/tile/*` | `doc/network/migration-guide.md:14`, `doc/recipes/migration-guide.md`, `doc/potions/migration-guide.md`, `doc/plugins/migration-guide.md:78` |

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

### 7.1 現状

* `9fdb3b4`/`9b62fb3` で `BlockEntityType.Builder.of` 47件 / `FluidType` 19件 / `FlowingFluid` 39件 / `EntityType` 21件 / `MenuType` 5件の雛形登録は完了。`S35PacketUpdateTileEntity`→`ClientboundBlockEntityDataPacket` 等表層lintはPASS。
* だが `ItemStack`/`Level`/`Potion`/`WorldGen`/`Village` の旧APIが200+件残存し `javac` 不通過。最大は `WorldGenYuzuTrees.java:54`（1.7.10 `WorldGenAbstractTree` のまま）、`ComponentVillageCafe.java:47`（`StructureVillagePieces.Village`）、`DCsLivingEvent.java:44`（`Potion.potionTypes`）。
* 追加検証で0件にすべき拡張grep: `rg -n "xCoord|stackSize|isItemEqual|getItemDamage|Potion\.potionTypes|func_147447|IWorldGenerator"` → 現WT-Bで80+件ヒット（本文7.2参照）。

### 7.2 残存タスク — 7群 / P0=ビルドブロッカー

| 群 | 件数 | P | 対象ファイル (例) | 移行内容 | 正本 |
|---|---|---|---|---|---|
| **T1 Tile Container/Inventory** | 80 | P0 | `TileEvaporator.java:112` `stackSize/isItemEqual/getItemDamage` 12件 / `TileProcessor.java:77` 10件 / `TileIceMaker.java:160` `decrStackSize` / `TileIncenseBase.java:120` `ash.stackSize++` / `Container* 5件` `sendProgressBarUpdate` / `TileFilledSoupPan.java:111` `String getDisplayName()` | `stackSize`→`getCount/setCount`/`isItemEqual`→`isSameItemSameTags`/`getItemDamage`→`getDamageValue`/`decrStackSize`→`removeItem`+`setChanged`/`getDisplayName():Component`/`Container→AbstractContainerMenu+ContainerData` | `tile-entities:57` `handler:15` |
| **T2 TileDummy 型** | 1 | P0 | `TileDummy.java:8` `super(null,pos,state)` + `TileAlcoholCup.java:3` 等6stub | `super(ModBlockEntities.TILE_XXX.get(), pos, state)` に。`TileDummy` 自体は `ModBlockEntities.TILE_DUMMY.get()` | `tile-entities:17` |
| **T3 Fluid** | 0 | P2 | `BlockOilFluid.java:1`/`DCsTank.java:1` は置換済 | 残は `ItemDummyFluid` stub登録不要確認のみ | `fluids:73` |
| **T4A WorldGenYuzu** | 1 | P0 | `WorldGenYuzuTrees.java:54` 全面1.7.10 | `WorldGenAbstractTree`→`Feature` / `world.getBlock(int)`→`level.getBlockState(pos)` / `func_150523_a`→`BlockState.isAir/isLeaves` / `WorldgenTeaTree.java:17` 型に縮退 | `worldgen:54` |
| **T4B datapack BiomeModifier** | 5 | P0 | `data/defeatedcrow/worldgen/*` 0件 / `registry/ModBiomeModifiers.java` 未作成 | `configured_feature/tea_tree.json` / `placed_feature` / `forge/biome_modifier/add_tea_tree.json` / `global_loot_modifiers.json` + `DeferredRegister<BiomeModifier>` を `DCsAppleMilk.java:39` に登録 (WT0へエスカレ ※WT-Bで雛形作成) | `worldgen:70` |
| **T4C Village** | 2 | P1 | `ComponentVillageCafe.java:47` `fillWithBlocks/placeBlockAtCurrentPosition/getMetadataWithOffset/Blocks.brick_block/WeightedRandomChestContent` / `ComponentVillageWarehouse.java:37` 同 | `StructureVillagePieces.Village`→`StructurePiece` (`BoundingBox`/`BlockState`) or 一時スタブ化→将来Jigsaw (`TemplatePool`+`nbt`) | `worldgen:105` |
| **T5 Entity** | 6 | P0 | `EntityAnchorMissile.java:310` `func_147447/xCoord/blockX/motionX/getBlock` / `EntityMelonBomb.java:339` `getActivePotionEffect` / `PlaceableFoods.java:64` `getItemDamage` / `VillagerCafe.java:75` `ItemStack(Blocks.pumpkin,3,0)` | `func_147447`→`level.clip(ClipContext)`/`hitVec.xCoord`→`hitResult.getLocation().x`/`motionX`→`setDeltaMovement(Vec3)`/`getActivePotionEffect`→`getEffect`/`Villager`は`ModVillagers`新設 | `entities:56` |
| **T6 Event** | 5 | P0 | `DCsLivingEvent.java:44` `Potion.potionTypes/getPotionID/getActivePotionEffects/ammount/EntityDamageSource/posX/provider` / `EntityMoreDropEvent.java:25` 同 / `SpawnCancelEvent.java:16` `LivingSpawnEvent.CheckSpawn` / `CraftingEvent.java:38` `getItemDamage` | `Potion.potionTypes`→`BuiltInRegistries.MOB_EFFECT`+`getActiveEffects`/`EntityDamageSource`→`damageSources()`/`posX`→`getX()`/`LivingSpawnEvent.CheckSpawn`→`MobSpawnEvent.FinalizeSpawn` | `events:38` |
| **T7 Handler/lint** | 1 | P2 | `Coord.java:1` 等軽微 / `scripts/lint-migration.ps1` 14ルールのみ | `lint` に `stackSize/isItemEqual/getItemDamage/func_147447` 拡張ルールをWT-B用に追加 | `handler:62` |

### 7.3 優先順位（依存順）

**Phase 1 — ビルド止血 (2-3日)**: T4A(Yuzu縮退) → T5-Projectile(`func_147447`) → T6-Living/Hurt(`Potion`) → T2(`super(null)`) → T1(`stackSize`一括) → `SpawnCancelEvent` リネーム  
**Phase 2 — 機能復旧 (1週)**: T4C(村最小移行) → T5-Melon/Placeable → T6-MoreDrop/Crafting → T4B(datapack+ModBiomeModifiers)  
**Phase 3 — 磨き**: 村Jigsaw化 / `AddChestGen` の `GlobalLootModifierProvider` / `lint`拡張

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

### 7.5 次の3手

1. T1 Container一括 — `rg stackSize` 80件を `TileEvaporator/Processor/ChargerBase` から現代化
2. T4A Yuzu縮退 — `WorldGenYuzuTrees.java:1` を `WorldgenTeaTree.java:17` 倣いに縮退
3. T6 Living/Hurt — `DCsHurtEvent.java:31`/`DCsLivingEvent.java:44` の `Potion` → `MobEffectInstance` 置換
