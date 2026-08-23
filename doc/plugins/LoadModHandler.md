# LoadModHandler

> Source: `src/main/java/mods/defeatedcrow/plugin/LoadModHandler.java:1`
> Target: `IC2/Forestry/BC/Bamboo/Tofu/Thaumcraft/BoP/MCEconomy/SSector/Growthcraft/MapleTree/ExtraTrees/Railcraft/SugiForest/DartCraft/Wa/EnchantChanger/AppleCore/PPC/CraftGuide`
> Category: `Matrix`

## 概要
全連携の親。postInitで各Load*Pluginを呼出。

## 登録情報
- **クラス**: `LoadModHandler` (`src/main/java/mods/defeatedcrow/plugin/LoadModHandler.java:1`)
- **呼出**: `LoadModHandler` が `Loader.isModLoaded("modid")` で存在チェック後 `try{ new LoadModHandler().load(); }catch{}`
- **Target**: `IC2/Forestry/BC/Bamboo/Tofu/Thaumcraft/BoP/MCEconomy/SSector/Growthcraft/MapleTree/ExtraTrees/Railcraft/SugiForest/DartCraft/Wa/EnchantChanger/AppleCore/PPC/CraftGuide`

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `Loader.isModLoaded` + try/catch | `ModList.get().isLoaded` (1.13+) | 同左 + `IEventBus` で条件登録 |
| `GameRegistry.findItem("mod","item")` | `ForgeRegistries.ITEMS.getValue(RL)` | `BuiltInRegistries.ITEM` |

## 関連ドキュメント
- [Plugin 一覧](../plugins.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
