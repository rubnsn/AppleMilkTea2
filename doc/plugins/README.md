# Plugin 個別ページ索引

> Source: `src/main/java/mods/defeatedcrow/plugin/**`
> 総数: 30+クラス (Energy 5, Farm 1, Magic 1, Display 2, Core 2, 他 10+)
> Entry: `LoadModHandler.java:1`
> 本ディレクトリは 1.7.10→1.12.2 移行向けの個別ページ集。

## 一覧

| クラス | Target MOD | 個別ページ |
|---|---|---|
| `LoadModHandler` | 全体統括 | [→](./LoadModHandler.md) |
| `LoadOreDicHandler` | 鉱石辞書 | [→](./LoadOreDicHandler.md) |
| `AddonIntegration` | 補助 | [→](./AddonIntegration.md) |
| `LoadIC2Plugin` | IC2 | [→](./LoadIC2Plugin.md) |
| `LoadTE4Plugin` | TE4 | [→](./LoadTE4Plugin.md) |
| `LoadBCPlugin` | BC | [→](./LoadBCPlugin.md) |
| `LoadForestryPlugin` | Forestry | [→](./LoadForestryPlugin.md) |
| `LoadBambooPlugin` | BambooMod | [→](./LoadBambooPlugin.md) |
| `LoadThaumcraftPlugin` | Thaumcraft | [→](./LoadThaumcraftPlugin.md) |
| `LoadBoPPlugin` | BoP | [→](./LoadBoPPlugin.md) |
| `LoadNEIPlugin` | NEI | [→](./LoadNEIPlugin.md) |
| `LoadCraftGuidePlugin` | CraftGuide | [→](./LoadCraftGuidePlugin.md) |

### 個別Handler

| カテゴリ | クラス | 個別ページ |
|---|---|---|
| IC2 | `EUItemHandler` / `EUSinkChannel` / `EUSourceChannel` | [→](./IC2Handlers.md) |
| CoFH | `RFItemHandler` / `RFDeviceHandler` | [→](./CofH.md) |
| SSector | `SS2ItemHandler` / `SS2DeviceHandler` | [→](./SSector.md) |
| Forestry | `FarmableAMT` / `CropAMTPlants` / `FarmLogicAMT` | [→](./Forestry.md) |
| NEI | `TeaRecipeHandler` / `ProcessorRecipeHandler` 等15種 | [→](./NEIHandlers.md) |
| CraftGuide | `TeaRecipeHandlerCG` 等10種 | [→](./CraftGuideHandlers.md) |
| MCEconomy | `MCEconomyPlugin` / `RegisterSellItem` | [→](./MCEconomyPlugin.md) |

---

## 生成元
- 移行ガイド: [`migration-guide.md`](./migration-guide.md)
- 一覧: [`plugins.md`](../plugins.md)
