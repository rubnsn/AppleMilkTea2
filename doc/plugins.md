# 他MOD連携 (Plugin) 一覧

> Source: `src/main/java/mods/defeatedcrow/plugin/*` (30+クラス)
> Entry: `src/main/java/mods/defeatedcrow/plugin/LoadModHandler.java:1` (postInitで呼出)
> 連携MOD数: **20+** (任意、存在チェック `Loader.isModLoaded("modid")` で分岐)
> NEI/CraftGuide: `plugin/nei/*` (15 handler) / `plugin/craftguide/*` (10 handler)

## 概要
任意連携は `LoadModHandler` が `Loader.isModLoaded` で存在チェックし `try{ new LoadXPlugin().load(); }catch(Exception e){}` で安全にロード。失敗時は無視。

大別: **Energy** (IC2/TE4/BC/SSector/CofH) / **Farm** (Forestry) / **Magic** (Thaumcraft) / **WorldGen** (BoP) / **Display** (NEI/CraftGuide) / **Economy** (MCEconomy) / **Core** (OreDict, AppleCore)。

## 一覧

### コアハンドラ (3)

| クラス | ソース | 個別ページ | 説明 |
|---|---|---|---|
| `LoadModHandler` | `plugin/LoadModHandler.java:1` | [→](./plugins/LoadModHandler.md) | 全連携の親。postInitで各Plugin呼出。 |
| `LoadOreDicHandler` | `plugin/LoadOreDicHandler.java:1` | [→](./plugins/LoadOreDicHandler.md) | 鉱石辞書登録統括。`RegisterOreHandler` 呼出。 |
| `AddonIntegration` | `plugin/AddonIntegration.java:1` | [→](./plugins/AddonIntegration.md) | 他MODアイテムの辞書追加補助。 |

### Energy連携 (5)

| クラス | Target MOD | 個別ページ | 説明 |
|---|---|---|---|
| `LoadIC2Plugin` | IC2 (`ic2`) | [→](./plugins/LoadIC2Plugin.md) | EU連携。`EUItemHandler`/`EUSink`/`EUSource` で Charge↔EU 交換。`PropertyHandler.exchangeDif` でレート可変。 |
| `LoadTE4Plugin` | ThermalExpansion 4 (`ThermalExpansion`) | [→](./plugins/LoadTE4Plugin.md) | RF連携。`RFItemHandler`/`RFDeviceHandler`。CofH経由。 |
| `LoadBCPlugin` | BuildCraft (`BuildCraft|Energy`) | [→](./plugins/LoadBCPlugin.md) | MJ連携。 |
| `SSector` (`LoadSSectorPlugin`) | SextiarySector (`SextiarySector`) | [→](./plugins/LoadSSectorPlugin.md) | GF(ギアForce)連携。`SS2ItemHandler`/`SS2DeviceHandler`。 |
| `CofH` (`RF*Handler`) | CoFH (`CoFHAPI\|energy`) | [→](./plugins/CofH.md) | RF API抽象層。 |

### Forestry (1)

| クラス | Target | 個別ページ | 説明 |
|---|---|---|---|
| `LoadForestryPlugin` | Forestry (`Forestry`) | [→](./plugins/LoadForestryPlugin.md) | `FarmableAMT` / `CropAMTPlants` / `FarmLogicAMT` / `ItemChalcedonyCircuit` で `IFarmable` 登録。 |

### Magic/WorldGen (2)

| クラス | Target | 個別ページ | 説明 |
|---|---|---|---|
| `LoadThaumcraftPlugin` | Thaumcraft 4 (`Thaumcraft`) | [→](./plugins/LoadThaumcraftPlugin.md) | Aspect登録等。 |
| `LoadBoPPlugin` | BiomesOPlenty (`BiomesOPlenty`) | [→](./plugins/LoadBoPPlugin.md) | バイオーム連携。 |

### 素材MOD (5)

| クラス | Target | 個別ページ | 説明 |
|---|---|---|---|
| `LoadBambooPlugin` | BambooMod (`BambooMod`) | [→](./plugins/LoadBambooPlugin.md) | タケ連携。 |
| `LoadTofuPlugin` | TofuCraft | [→](./plugins/LoadTofuPlugin.md) | 豆腐連携。 |
| `HandleDryingRack` | (汎用) | [→](./plugins/HandleDryingRack.md) | 乾燥棚連携。 |
| `LoadAppleCorePlugin` | AppleCore | [→](./plugins/LoadAppleCorePlugin.md) | `IAppleCore` で食品効果拡張。 |
| `LoadPPCPlugin` | PPC (`PPC`) | [→](./plugins/LoadPPCPlugin.md) | パン連携。 |

### 汎用 (他)

| クラス | Target | 個別ページ | 説明 |
|---|---|---|---|
| `LoadExBucketPlugin` | ExBucket | [→](./plugins/LoadExBucketPlugin.md) | バケツ拡張連携。 |
| `LoadRailCraftPlugin` | Railcraft | [→](./plugins/LoadRailCraftPlugin.md) |  |
| `LoadMCEPlugin` (`MCEconomyPlugin`/`MCOldPlugin`/`OpenShopGui`/`RegisterSellItem`) | MCEconomy2 (`MCEconomy2`) | [→](./plugins/MCEconomyPlugin.md) | 経済連携。村取引の金額化。 |
| `DartCraft`, `Wa`, `EnchantChanger`, `Growthcraft`, `MapleTree`, `ExtraTrees`, `SugiForest` | 各MOD | [→](./plugins/OtherPlugins.md) | 包丁連携等は `plugin/LoadModHandler` 内で `Item` 取得分岐。 |

### 表示連携 (2)

| クラス | Target | 個別ページ | 説明 |
|---|---|---|---|
| `LoadNEIPlugin` | NEI (`NotEnoughItems`) | [→](./plugins/LoadNEIPlugin.md) | 15 handler: `Tea/Ice/Pan/Processor/Evaporator/Brewing/Choco/Fondue/Plate/Teppan/AdvProcessor` 等。`NEIPluginAMTCore` + `ClientProxyNPA`。 |
| `LoadCraftGuidePlugin` | CraftGuide (`craftguide`) | [→](./plugins/LoadCraftGuidePlugin.md) | 10 handler: 同上。 |

## 連携マトリクス (抜粋)

| MOD ID | 検出キー | 連携内容 | 主なクラス | 1.12+対応 |
|---|---|---|---|---|
| `IC2` | `Loader.isModLoaded("IC2")` | EU ↔ Charge 交換 (Sink/Source Channel) | `LoadIC2Plugin` + `IC2/EU*.java:1` (4種) | IC2 Classic API変更、維持可能 |
| `Forestry` | `Forestry` | 茶樹のFarmable化 | `plugin/ffm/*` (5種) | Forestry API維持 (1.12) |
| `Thaumcraft` | `Thaumcraft` | Aspect (4.x) | `LoadThaumcraftPlugin` | 6.xでAPI全面変更、1.16未対応 |
| `BuildCraft` | `BuildCraft|Energy` | MJ | `LoadBCPlugin` | BC 7.xで維持 |
| `MCEconomy2` | `MCEconomy2` | 経済 | `plugin/mce/*` (4種) | 維持 |
| `NotEnoughItems` | `NotEnoughItems` | NEI表示 | `plugin/nei/*` (15種) | JEIに置換 |
| `craftguide` | `craftguide` | 表示 | `plugin/craftguide/*` (10種) | JEIに置換 |

## 詳細
- [個別ページ索引](./plugins/README.md) - 12+個別ページへのリンク
- [移行ガイド](./plugins/migration-guide.md) - 1.12/1.16 移行

## 関連
- [Recipe 一覧](./recipes.md) - NEI表示
- [Block 一覧](./blocks.md) / [Item 一覧](./items.md)
- `src/main/java/mods/defeatedcrow/plugin/LoadModHandler.java:1`
