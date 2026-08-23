# AppleMilkTea2 ドキュメント

> 最終更新: 2026-08-24（1.20.1ブラッシュアップ: 2026-08-24）  
> 対象バージョン: `DCsAppleMilk 2.9m`（Minecraft 1.7.10, Forge 10.13.4.1614, EndlessIDs 1.7.4+） → **Minecraft 1.20.1 移植先: Forge 47.x + ForgeGradle 6 + Mojang Official Mappings + JDK 17**（[移行プラン](../plan.md) / [ビルド移行ガイド](./build.md)）  
> 解析元: `src/main/java/mods/defeatedcrow/**` / `DCsAppleMilk.java` / `MaterialRegister.java`
> 移行対応: **Minecraft 1.7.10 → 1.12.2 / 1.16.5 完全対応、1.20.1 ブラッシュアップ済**（全カテゴリで個別ページ+移行ガイド整備）。旧READMEの「1.19+完全対応」表記は 1.20.1 要件（FluidType/BiomeModifier/BlockEntityRenderer/SimpleChannel/Holder など）未記載だったため本改訂で是正。

この `doc/` フォルダは AppleMilkTea2 のコードベースを静的解析し、主要なゲーム要素を一覧化したドキュメント集です。
Block/Item以外の全カテゴリについても、1.12.2以降への移行を見据えた個別ページ群と移行ガイドを整備。**ビルド枠組み自体は [build.md](./build.md) で 1.20.1 (ForgeGradle 6 / Gradle 8.x / mods.toml / Mojang mappings) へ全面刷新**し、ソース移行は各 `migration-guide.md` の 1.20.1 追記を参照。

## 索引

| ドキュメント | 内容 | 件数 | 個別ページ | 移行ガイド | 主要ソース |
|---|---|---|---|---|---|
| [概要](./overview.md) | MOD全体像、統計、連携MOD、1.20.1目標像 | — | — | — | `DCsAppleMilk.java:127` |
| [ビルド / Gradle](./build.md) | **1.7.10 GTNH → 1.20.1 FG6 移行: Gradle 8.x / JDK17 / mappings / mods.toml / 難読化解除済み公式ソース場所** | — | — | **本ガイドがビルド移行の正本** ([公式ソース場所](./build.md#deobf-sources)) | `build.gradle` / `gradle.properties` / `settings.gradle` |
| [Block 一覧](./blocks.md) | 全ブロックを9カテゴリで表解 | **74** | [74件](./blocks/README.md) | [→](./blocks/migration-guide.md)（1.20.1追記） | `MaterialRegister.java:316-411` |
| [Item 一覧](./items.md) | 全アイテムを8カテゴリで表解 | **64+2** | [53件](./items/README.md) | [→](./items/migration-guide.md)（1.20.1 NBT≠DataComponents明記） | `MaterialRegister.java:247-314` |
| [ItemBlock 一覧](./items/item-blocks.md) | ItemBlock 36種 | **36** | — | — | `MaterialRegister.java:316` |
| [Entity 一覧](./entities.md) | ModEntity 20 + Villager 2 | **20+2** | [22件](./entities/README.md) | [→](./entities/migration-guide.md)（1.20 Renderer/Context追記） | `DCsAppleMilk.java:542-702` |
| [Fluid 一覧](./fluids.md) | 流体18種 + ブロック4 + コンテナ5 | **18** | [18件](./fluids/README.md) | [→](./fluids/migration-guide.md)（FluidType分離追記） | `MaterialRegister.java:490-718` |
| [Potion 一覧](./potions.md) | 追加ポーション効果 | **10** | [10件](./potions/README.md) | [→](./potions/migration-guide.md) | `MaterialRegister.java:413-488` |
| [TileEntity 一覧](./tile-entities.md) | TileEntityとGUI対応（1.20.1ではBlockEntity） | **45** | [45件](./tile-entities/README.md) | [→](./tile-entities/migration-guide.md)（1.20.1 BlockEntityType追記） | `CommonProxy.java:70-117` |
| [WorldGen / Village](./worldgen.md) | 生成と村構造物 | **4+4** | [8件](./worldgen/README.md) | [→](./worldgen/migration-guide.md)（BiomeModifier/Holder追記） | `DCsAppleMilk.java:739-723` |
| [CreativeTab](./creative-tabs.md) | クリエイティブタブ | **5** | [5件](./creative-tabs/README.md) | [→](./creative-tabs/migration-guide.md)（1.19.3 Registry済） | `DCsAppleMilk.java:144-149` |
| [Config](./config.md) | コンフィグ項目 | **50+** | [27件](./config/README.md) | [→](./config/migration-guide.md) | `DCsConfig.java:1` |
| [Achievement](./achievements.md) | 実績 | **37** | [37件](./achievements/README.md) | [→](./achievements/migration-guide.md)（AdvancementHolder追記） | `AchievementRegister.java:1` |
| [Recipe](./recipes.md) | レシピ11種 | **11** | [11件](./recipes/README.md) | [→](./recipes/migration-guide.md) | `recipe/*` / `api/recipe/*` |
| [API](./api.md) | 公開API 9パッケージ | **40+** | [11件](./api/README.md) | [→](./api/migration-guide.md) | `api/**` |
| [Event](./events.md) | イベント12+7 | **19** | [19件](./events/README.md) | [→](./events/migration-guide.md)（1.16止まり→1.20追記） | `event/*` / `api/events/*` |
| [Plugin](./plugins.md) | 他MOD連携20+ | **20+** | [12件](./plugins/README.md) | [→](./plugins/migration-guide.md)（1.20.1 Omit/Keep分類追記） | `plugin/*` |
| [Handler](./handler.md) | ハンドラ/Util 14 | **14** | [14件](./handler/README.md) | [→](./handler/migration-guide.md)（1.20.1詳細化） | `handler/*` |
| [Network](./network.md) | ネットワーク 5 | **5** | [5件](./network/README.md) | [→](./network/migration-guide.md)（SimpleChannel完全版） | `network/*` |

## クイック統計

```
Block        74  (9カテゴリ) + 個別ページ74 + 移行ガイド（1.20.1 BlockBehaviour/VoxelShape/BER追記）
Item         64  (+2 NEI dummy) + 個別ページ53 + 移行ガイド + ItemBlock36（1.20.1はNBT維持/DataComponentsは1.20.5+）
Fluid        18  (油2 + 醸造16) + 個別ページ18 + 移行ガイド（1.20.1 FluidType分離追記）
FluidBlock    4  → LiquidBlock + FluidType
Potion       10  + 個別ページ10 + 移行ガイド（MobEffect/Holder対応）
TileEntity   45  (GUI 5) + 個別ページ45 + 移行ガイド（1.20.1 BlockEntityType.create→Builder.of）
Entity       20  (Placeable 13 + 投擲3 + 魔法4) + 個別ページ22 + 移行ガイド（1.20 Renderer/Context追記）
Villager      2  (Cafe, Yome)
Village       2  (Cafe, Warehouse) + WorldGen個別ページ8 + 移行ガイド（1.20.1 BiomeModifier/PlacedFeature）
WorldGen      2  (TeaTree, Clam) + ChestGen1 (1.20.1ではBiomeModifier+Datapackへ)
CreativeTab   5  + 個別ページ5 + 移行ガイド（1.19.3 CreativeModeTab Registry化済）
Achievement  37  + 個別ページ37 + 移行ガイド (Advancement/Holder, 1.20.1 AdvancementProvider)
Recipe       11  (Tea/Ice/Pan/Plate/Processor/AdvProcessor/Evaporator/Brewing/Fondue/Choco/Charge) + 個別ページ11 + 移行ガイド
API           9パッケージ 40+クラス + 個別ページ11 + 移行ガイド
Event        12 handler + 7 APIイベント =19 + 個別ページ19 + 移行ガイド（1.20 Bus/BlockPos化追記）
Plugin       20+ MOD連携 + 個別ページ12 + 移行ガイド + マトリクス（1.20.1 Omit/Keep分類追記）
Handler      14  (Coord/Explosion/OreDict/Util等) + 個別ページ14（1.20.1詳細化）
Network       5  (AMT2チャンネル) SimpleChannel 1.20完全版
Build         1  ビルド移行ガイド新設: Forge 47.x + FG6 + Moj map + JDK17 + mods.toml + Gradle8
Total Docs  400+ (doc/**/*.md 389件 at 2026-08-24) + build.md新設
```

## 生成方法
- `DCsAppleMilk.java` の static フィールドを Field parsing
- `MaterialRegister` の `GameRegistry.register*` 呼び出しを正規表現で抽出
- `CommonProxy.registerTileEntity` / `EntityRegistry.registerModEntity` を抽出
- 手動でカテゴリ分類・説明付与（`ItemAPI.java` のメタマッピングコメントを参照）

## 整備状況 (2026-08-24 時点 / 1.20.1ブラッシュアップ反映)

- [x] **ビルド枠組み移行**（1.7.10 GTNH → 1.20.1 ForgeGradle 6）- [`build.md`](./build.md) **新設**（[移行プラン](../plan.md) のフェーズA-CをDOC化: settings.gradle/build.gradle/gradle.properties/wrapper/mods.toml/JDK17/mojmap）
- [x] レシピ一覧（11 RecipeType / Tea/Ice/Processor/Evaporator/Barrel/Pan/Plate/Fondue/Choco/Charge）- [`recipes.md`](./recipes.md) + [`recipes/`](./recipes/README.md) + [移行ガイド](./recipes/migration-guide.md) - `recipe/*.java:1` / `api/recipe/*:1` から抽出済（`RegisterMakerRecipe.java:21` / `RecipeRegisterManager.java:1`）— 1.20.1はRecipeType/Serializer維持
- [x] 実績（Achievement）一覧 37件 - [`achievements.md`](./achievements.md) + [`achievements/`](./achievements/README.md) + [移行ガイド](./achievements/migration-guide.md) - `AchievementRegister.java:1` から抽出済（`DCachievementsList` 37）— 1.20.1はAdvancementHolder/Provider対応を追記
- [x] API リファレンス（9パッケージ 40+クラス）- [`api.md`](./api.md) + [`api/`](./api/README.md) + [移行ガイド](./api/migration-guide.md) - `api/recipe/*:1`, `api/potion/*:1`, `api/charge/*:1`, `api/charm/*:1` 等 — 1.20.1はHolder/TagKey注記を追記
- [x] イベント一覧（12 handler + 7 APIイベント =19）- [`events.md`](./events.md) + [`events/`](./events/README.md) + [移行ガイド](./events/migration-guide.md) - `event/*.java:1` / `api/events/*:1` — 1.20.1のBlockPos/EventBus完全版を追記
- [x] 他MOD連携マトリクス（20+ MOD）- [`plugins.md`](./plugins.md) + [`plugins/`](./plugins/README.md) + [移行ガイド](./plugins/migration-guide.md) - `plugin/*:1` / `LoadModHandler.java:1` — 1.20.1 Omit/Keep/Bamboo保留の分類を追記
- [x] Entity 詳細（20+2 +Village2）- [`entities.md`](./entities.md) + [`entities/`](./entities/README.md) + [移行ガイド](./entities/migration-guide.md) - 22個別ページ — 1.20.1のEntityRenderer/Context/AttributeSupplier追記
- [x] TileEntity 詳細（45）- [`tile-entities.md`](./tile-entities.md) + [`tile-entities/`](./tile-entities/README.md) + [移行ガイド](./tile-entities/migration-guide.md) — 1.20.1 BlockEntityType.Builder.of / DeferredRegister / 同期パケット追記
- [x] Potion 詳細（10）- [`potions.md`](./potions.md) + [`potions/`](./potions/README.md) + [移行ガイド](./potions/migration-guide.md) — 1.20.1 MobEffect/Holder追記
- [x] Fluid 詳細（18 + 4 Block）- [`fluids.md`](./fluids.md) + [`fluids/`](./fluids/README.md) + [移行ガイド](./fluids/migration-guide.md) — 1.20.1 FluidType分離を追記
- [x] WorldGen/Village 詳細（4+4）- [`worldgen.md`](./worldgen.md) + [`worldgen/`](./worldgen/README.md) + [移行ガイド](./worldgen/migration-guide.md) — 1.20.1 BiomeModifier/Holder/ConfiguredFeature追記
- [x] Config 詳細（50+項目）- [`config.md`](./config.md) + [`config/`](./config/README.md) + [移行ガイド](./config/migration-guide.md) - `DCsConfig.java:1` — 1.20.1はForgeConfigSpec維持（mods.toml依存）
- [x] CreativeTab 詳細（5）- [`creative-tabs.md`](./creative-tabs.md) + [`creative-tabs/`](./creative-tabs/README.md) + [移行ガイド](./creative-tabs/migration-guide.md) — 1.19.3でCreativeModeTab Registry化済、1.20.1も同型
- [x] Handler / Network - [`handler.md`](./handler.md) / [`network.md`](./network.md) + 個別ページ群 — Networkは1.20 SimpleChannel完全版に拡張、Handlerは1.20詳細化
- [x] ビルド移行DOCの欠落は本改訂で解消: 旧`doc/`にはFG6/mods.toml/Gradle8/Mojmapの記載が1回（plugins guideのmods.toml言及）のみだったため、[build.md](./build.md) を正本として新設
- [ ] テクスチャ・モデル対応表（`assets/defeatedcrow/*`）- 未着手（今後の拡張余地。1.20ではblockstates/modelsは維持だがRenderType透過分離に注意）

## 規約
- ファイル参照は `file_path:line_number` 形式で記述（例: `src/main/java/mods/defeatedcrow/common/DCsAppleMilk.java:127`）
- 日本語を主言語とし、必要に応じて英語のレジストリ名を併記
- 新要素追加時は本フォルダ内の該当mdを更新すること

## ライセンス
- ソースコード: MMPL-1.0 (`License(MMPL-1.0).txt:1`)
- テクスチャ・効果音: CC-BY-NC
