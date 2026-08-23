# AppleMilkTea2 概要

> ModID: `DCsAppleMilk`  
> Version: `Tags.VERSION`（`2.9m` 系）  
> Minecraft: `1.7.10` / Forge `10.13.4.1614` / EndlessIDs `1.7.4+` → **移植先: 1.20.1 / Forge 47.3.x / ForgeGradle 6 / Mojang Official Mappings / JDK 17**（[移行プラン](../plan.md) / [ビルド移行ガイド](./build.md)）  
> ライセンス: ソース MMPL-1.0 / テクスチャ CC-BY-NC

## 概要
「AppleMilkTea2」は defeatedcrow 氏による大規模コンテンツMOD。紅茶・料理・醸造・玉髄装飾・お香・エネルギーなどの複合システムを備える。1.7.10 Modded 環境（GTNH）対応版を **1.20.1 Forge 47.x + FG6 + Moj map + JDK17 の標準ビルド体系へ刷新中**。GTNH convention / EndlessIDs / MCPC mapping / `rfg.deobf` 依存は [build.md](./build.md) の通り撤去し、`mods.toml`/`Gradle 8.x`/`mojmap` に移行。

## ビルド移行サマリ（本DOC最大ギャップの解消）

| 旧 (1.7.10 GTNH) | 新 (1.20.1 標準 Forge) | 文書 |
|---|---|---|
| `settings.gradle.kts` + `gtnhsettingsconvention 2.0.29` | `settings.gradle` (Groovy) + `pluginManagement` のみ、GTNH除去 | [build.md](./build.md#settingsgradle) |
| `build.gradle.kts` + `com.gtnewhorizons.gtnhconvention` | `build.gradle` (Groovy) + `net.minecraftforge.gradle:6.x` + `java toolchain 17` + `minecraft { mappings channel:'official', version:'1.20.1' }` | [build.md](./build.md#buildgradle) |
| `gradle.properties` の `minecraftVersion=1.7.10`/`forgeVersion=10.13.4.1614`/MCP `stable 12`/`coreModClass`/`forceEnableMixins` 等 | `minecraftVersion=1.20.1`/`forgeVersion=47.3.x` + FG6用 `official` mapping + 開発者名のみ | [build.md](./build.md#gradleproperties) |
| `dependencies.gradle` の `rfg.deobf(curse.maven)` + ローカルjar (Bamboo/wa) + NEI/CCC 多数 | 通常 `minecraft`/`forge` 依存 + `curse.maven/modrinth/forge.maven` の **1.20.1 対応版のみ**、非対応（Thaum/NEI/CraftGuide/MCE2/Sector2等）は削除、Bambooは保留 | [build.md](./build.md#dependencies) + [plugins/migration-guide](./plugins/migration-guide.md#1201対応分類) |
| `gradle-wrapper.properties` Gradle 9.4 | **Gradle 8.x** (MDK準拠 8.1.1系, FG6はGradle 9非対応) | [build.md](./build.md#wrapper) |
| `.jdk`/daemon JVM JDK25 | JDK17 (`JAVA_HOME` のローカルJDK17) | [build.md](./build.md#jdk) |
| `mcmod.info` | `src/main/resources/META-INF/mods.toml` (`modId=DCsAppleMilk`) + `pack.mcmeta` | [build.md](./build.md#mods-toml) |

> **DOC移行DOCの十分性**（plan.md監査より）: 1.16.5土台としては良質だが 1.20.1には不足。特に `TileEntity(BlockEntityType.create)/WorldGen(BiomeModifier/Holder)/Renderer(BER.Context)/Network(SimpleChannel)/Fluid(FluidType)/Achievement(AdvancementHolder)/DataComponents(NBT≠Components)` の6ギャップと **ビルド移行DOCの皆無**が最大課題 — 本ブラッシュアップで各 `migration-guide.md` に 1.20.1追記と [build.md](./build.md) 新設で補完。

## 主なコンテンツ
- **飲食物**: 茶・ココア・スープ・ステーキ・アイス・カクテル等（メタデータで数百種）
- **調理機器**: TeaMaker / IceMaker / EmptyPan / TeppanII / Processor / AdvProcessor / Evaporator
- **醸造**: Barrel + Cordial + LargeBottle による発酵・蒸留（18種の流体）
- **収納圧縮**: WoodBox / VegiBag / CharcoalBox / MelonBomb 等の大量収納・圧縮
- **植物**: 茶の木・ミント・カシス・柚子・ハマグリ砂浜生成
- **装飾**: Basket, BowlRack, ChopsticksBox, Chalcedony Lamp, CrowDoll
- **魔法**: Incense（お香11種）、Charm（姫ハマグリ）、幻覚・窒息等のPotion 10種
- **エネルギー**: YuzuBat / GelBat / BatBox / HandleEngine（ChargeItem API）

## 統計
| カテゴリ | 数 | 個別ページ | 移行ガイド |
|---|---|---|---|
| Block | 74 | [74件](./blocks/README.md) | [→](./blocks/migration-guide.md) |
| Item | 64 +2 dummy | [53件](./items/README.md) | [→](./items/migration-guide.md) |
| Fluid | 18 (油2 + 醸造16) | [18件](./fluids/README.md) | [→](./fluids/migration-guide.md) |
| Potion | 10 | [10件](./potions/README.md) | [→](./potions/migration-guide.md) |
| TileEntity | 45 (GUI 5) | [45件](./tile-entities/README.md) | [→](./tile-entities/migration-guide.md) |
| Entity (ModEntity) | 20 (Placeable 13 + 投擲3 + 魔法4) | [22件](./entities/README.md) | [→](./entities/migration-guide.md) |
| Villager | 2 (Cafe, Yome) | [2件](./entities/README.md) |同上 |
| Village Component | 2 (Cafe, Warehouse) | [8件](./worldgen/README.md) | [→](./worldgen/migration-guide.md) |
| WorldGen | 2 (TeaTree, Clam) + ChestGen + Yuzu(予備) |同上 |同上 |
| CreativeTab | 5 | [5件](./creative-tabs/README.md) | [→](./creative-tabs/migration-guide.md) |
| Achievement | 37 (Advancement) | [37件](./achievements/README.md) | [→](./achievements/migration-guide.md) |
| Recipe | 11 (Tea/Ice/Pan/Plate/Processor/AdvProcessor/Evaporator/Brewing/Fondue/Choco/Charge) | [11件](./recipes/README.md) | [→](./recipes/migration-guide.md) |
| API | 9パッケージ 40+クラス | [11件](./api/README.md) | [→](./api/migration-guide.md) |
| Event | 12 handler + 7 APIイベント | [19件](./events/README.md) | [→](./events/migration-guide.md) |
| Plugin | 20+ MOD | [12件](./plugins/README.md) | [→](./plugins/migration-guide.md) |
| Handler | 14 | [14件](./handler/README.md) | [→](./handler/migration-guide.md) |
| Network | 5 (AMT2) | [5件](./network/README.md) | [→](./network/migration-guide.md) |
| **Total Docs** | **389+** | **400+ md** | **17移行ガイド** |

## エントリポイント
- `@Mod` クラス: `src/main/java/mods/defeatedcrow/common/DCsAppleMilk.java:127`
- Proxy: `ClientProxy` / `CommonProxy` (`SidedProxy`)
- Material登録: `MaterialRegister` (`src/main/java/mods/defeatedcrow/common/MaterialRegister.java:213`)
- レシピ: `DCsRecipeRegister` + `RegisterMakerRecipe`（Tea/Ice/Pan/Plate/Processor/Evaporator/Brewing）

## 依存・連携（1.7.10 → 1.20.1分類）

- 必須(旧): Forge, EndlessIDs → **1.20.1**: Forge 47.x のみ（EndlessIDs不要、RegistryがHolder/RL管理）
- 任意連携 (postInitで`Loader.isModLoaded` → 1.20.1 `ModList.get().isLoaded`): 詳細は [plugins/migration-guide.md の1.20.1対応分類](./plugins/migration-guide.md#1201対応分類) を正本とする
  - **Omit確定/相当** (1.20.1版なし): Thaumcraft(1.12.2止まり)、NEI/CC/CraftGuide、MCE2/Sector2、PPC/Tofu/wa/Gummi/Growthcraft/MapleTree/SugiForest/DartCraft/ExtraTrees/EnchantChanger/ExBucket、AppleCore(Forgeに内包)。`plugin/*` の大半は削除
  - **Bambooのみ保留**: 1.20.1版あり、`LoadBambooPlugin` は破棄せずAPI差分を後追い検証（`CookingRegistry`/`GrindRegistory` 等）
  - **置換候補** (1.20.1対応あり): IC2(industrialcraft-2継続)、BuildCraft(コミュニティ維持)、BiomesOPlenty(現役)、Thermal Series(TE4後継)、JEI(NEI→JEI置換)
  - **エネルギー系刷新**: CoFH RF(`IEnergyHandler/@Optional`) → `Forge Energy`(`IEnergyStorage`+`Capability`)全面書換、`MachineBase`/`TileChargerDevice`/`TileHandleEngine`が中心

## ビルド・ツール・検証（1.20.1）

- **JDK**: 17（`JAVA_HOME` をローカルJDK17に、`gradle-daemon-jvm.properties` のJDK25撤去）— [build.md#jdk](./build.md#jdk)
- **Gradle**: 8.x (`gradle-wrapper.properties` の `distributionUrl` を 8.1.1系) — FG6はGradle9非対応 — [build.md#wrapper](./build.md#wrapper)
- **Mojmap公式ソース**: `mappings channel:'official'` の難読化解除済みソースは `%USERPROFILE%\.gradle\caches\forge_gradle\minecraft_user_repo\...\forge-1.20.1-47.3.0_mapped_official_1.20.1-sources.jar` に生成 (`Block.java:1` 等が `BlockBehaviour.Properties` で読める)。詳細は [build.md#deobf-sources](./build.md#deobf-sources)
- **検証コマンド**: `$env:JAVA_HOME="<JDK17>"; .\gradlew build` が `BUILD SUCCESSFUL` / `genIntellijRuns`/`genEclipseRuns` 成功 / `runClient` でタイトル画面 도달 / 生成jarがForge47でロード可能 — [build.md#verification](./build.md#verification)

## ディレクトリ構成（抜粋）
```
src/main/java/mods/defeatedcrow/
  api/          # 外部公開API（ItemAPI, Recipe, Potion, Charge, Charm）→ 1.20.1はCapability/RecipeType/TagKeyに移行
  asm/          # CoreMod (PotionTransformer) → 1.20.1ではASM撤去 or Mixin検討、EndlessIDs不要
  client/       # Render, Gui, Proxy, Particle → 1.20ではBlockEntityRenderer/EntityRendererProvider.Context
  common/
    block/      # Block本体 → BlockBehaviour.Properties + VoxelShape
    item/       # Item本体 → Item.Properties + FoodProperties + 個別Item/NBT化
    tile/       # TileEntity → BlockEntity + BlockEntityType (1.20.1はHolder/DeferredRegister)
    entity/     # Entity → EntityType + Placeable分離
    fluid/      # Fluid Block/Item → FluidType + ForgeFlowingFluid + LiquidBlock
    world/      # WorldGen, Village → BiomeModifier/PlacedFeature/Structure(Jigsaw)
    config/     # DCsConfig → ForgeConfigSpec (TOML)
  potion/       # Potion実装 → MobEffect/MobEffectCategory/ Holder
  plugin/       # 他MOD連携 → 大半Omit、Bamboo保留、RF→ForgeEnergy
  recipe/       # レシピ登録API実装 → RecipeType + RecipeSerializer + Ingredient(TagKey)
  event/        # イベントハンドラ → net.minecraftforge.eventbus.api + BlockPos化
  handler/      # OreDict等 → TagKey/BlockPos/SavedData
doc/
  build.md      # ★新設: GTNH→FG6 1.20.1ビルド移行の正本
  blocks/migration-guide.md  # 1.20追記済
  tile-entities/migration-guide.md # 1.20追記済 (BlockEntityType)
  fluids/migration-guide.md # FluidType追記済
  worldgen/migration-guide.md # BiomeModifier追記済
  network/migration-guide.md # SimpleChannel完全版
```

## ドキュメント索引
- [ビルド移行ガイド](./build.md) ★新設 - GTNH 1.7.10→1.20.1 FG6 刷新の正本（settings/build/gradle.properties/wrapper/mods.toml/JDK17/mojmap）
- [Block 一覧](./blocks.md) / [個別ページ索引](./blocks/README.md) / [移行ガイド](./blocks/migration-guide.md) - 74個別ページ（1.20.1追記: BlockBehaviour/DeferredRegister/BER）
- [Item 一覧](./items.md) / [個別ページ索引](./items/README.md) / [移行ガイド](./items/migration-guide.md) / [ItemBlock 一覧](./items/item-blocks.md) - 53個別ページ（1.20.1追記: NBT維持/DataComponentsは1.20.5+）
- [Entity 一覧](./entities.md) / [個別ページ索引](./entities/README.md) / [移行ガイド](./entities/migration-guide.md) - 22個別ページ（1.20.1追記: EntityType/Renderer/Context）
- [Fluid 一覧](./fluids.md) / [個別ページ索引](./fluids/README.md) / [移行ガイド](./fluids/migration-guide.md) - 18個別ページ（1.20.1追記: FluidType分離）
- [Potion 一覧](./potions.md) / [個別ページ索引](./potions/README.md) / [移行ガイド](./potions/migration-guide.md) - 10個別ページ（1.20.1 Holder/MobEffect）
- [TileEntity 一覧](./tile-entities.md) / [個別ページ索引](./tile-entities/README.md) / [移行ガイド](./tile-entities/migration-guide.md) - 45個別ページ（1.20.1追記: BlockEntityType.Builder.of）
- [WorldGen / Village](./worldgen.md) / [個別ページ索引](./worldgen/README.md) / [移行ガイド](./worldgen/migration-guide.md) - 8個別ページ（1.20.1追記: BiomeModifier/Holder）
- [CreativeTab](./creative-tabs.md) / [個別ページ索引](./creative-tabs/README.md) / [移行ガイド](./creative-tabs/migration-guide.md) - 5個別ページ（1.19.3 Registry済）
- [Config](./config.md) / [個別ページ索引](./config/README.md) / [移行ガイド](./config/migration-guide.md) - 27個別ページ（1.20.1はForgeConfigSpec維持）
- [Achievement](./achievements.md) / [個別ページ索引](./achievements/README.md) / [移行ガイド](./achievements/migration-guide.md) - 37個別ページ（1.20.1 AdvancementHolder）
- [Recipe](./recipes.md) / [個別ページ索引](./recipes/README.md) / [移行ガイド](./recipes/migration-guide.md) - 11個別ページ（1.20.1 RecipeType維持）
- [API](./api.md) / [個別ページ索引](./api/README.md) / [移行ガイド](./api/migration-guide.md) - 11個別ページ（1.20.1 TagKey/Holder）
- [Event](./events.md) / [個別ページ索引](./events/README.md) / [移行ガイド](./events/migration-guide.md) - 19個別ページ（1.20追記）
- [Plugin](./plugins.md) / [個別ページ索引](./plugins/README.md) / [移行ガイド](./plugins/migration-guide.md) - 12個別ページ（1.20.1 Omit/Keep分類）
- [Handler](./handler.md) / [個別ページ索引](./handler/README.md) / [移行ガイド](./handler/migration-guide.md) - 14個別ページ（1.20詳細化）
- [Network](./network.md) / [個別ページ索引](./network/README.md) / [移行ガイド](./network/migration-guide.md) - 5個別ページ（1.20 SimpleChannel完全版）

## 開発メモ
- `ItemAPI` は非推奨: `GameRegistry.findItem/findBlock` を推奨 → **1.20.1では `ForgeRegistries` / `DeferredRegister<...>` + `Holder` / `RegistryObject` で参照**
- Potion ID は 128 拡張前提、configで変更可能 → **1.20.1では整数ID廃止、RL + `DeferredRegister<MobEffect>` 管理（config項目削除）**
- 登録順: preInit(Material/Fluid/Potion) → init(Entity/Villager/WorldGen/Recipe) → postInit(他MOD連携, OreDic) → **1.20.1では `RegisterEvent` / `DeferredRegister` + `FMLCommonSetupEvent(enqueueWork)` + `BiomeModifier`/`PlacedFeature` はdatapack駆動**
- エネルギー: 1.7.10 RF(`IEnergyHandler/@Optional`) + EU(`BasicSink/BasicSource`) → **1.20.1 Forge Energy (`IEnergyStorage` capability) に全面書換。中枢は `MachineBase.java` / `TileChargerDevice.java` / `TileHandleEngine.java` の3ファイル**
- データ駆動: 1.20.1はNBT維持（Data Componentsは1.20.5+）。`items/migration-guide.md` の注記を参照
