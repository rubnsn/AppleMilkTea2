# Handler / Util 一覧

> Source: `src/main/java/mods/defeatedcrow/handler/*` (12クラス) + `common/config/PropertyHandler.java:1` + `common/AMTLogger.java:1`
> 役割: 座標管理、爆発、時間、ネットワーク、鉱石辞書等のユーティリティ

## 概要
Tile以外の共通処理を `handler/*` に集約。`CoordListRegister` の座標DB、`CustomExplosion` の独自爆発、`RegisterOreHandler` の辞書登録が中心的。

## 一覧

| クラス | ソース | 個別ページ | 説明 |
|---|---|---|---|
| `Coord` | `handler/Coord.java:1` | [→](./handler/Coord.md) | 単一座標 (x,y,z, dim) のデータクラス。 |
| `Pos` | `handler/Pos.java:1` | [→](./handler/Pos.md) | 同上、別実装。 |
| `CoordListRegister` | `handler/CoordListRegister.java:1` | [→](./handler/CoordListRegister.md) | `TileCrowDoll` の座標DB。`List<Coord>` を `WorldSavedData` 的に保持。`SpawnCancelEvent` が参照。 |
| `CustomExplosion` | `handler/CustomExplosion.java:1` | [→](./handler/CustomExplosion.md) | 独自爆発。`EntityMelonBomb` / `EntitySilkyMelon` / `EntityAnchorMissile` の `doExplosion` で利用。 |
| `FluidContMap` | `handler/FluidContMap.java:1` | [→](./handler/FluidContMap.md) | `Fluid` → `FluidContainerData` の Map。`FluidContainerRegisterEvent` で登録。 |
| `GenkotuHandler` | `handler/GenkotuHandler.java:1` | [→](./handler/GenkotuHandler.md) | げんこつ関連 (未使用?)。 |
| `KeyConfigHelper` | `handler/KeyConfigHelper.java:1` | [→](./handler/KeyConfigHelper.md) | キーコンフィグ補助。`charmWarpKey` のキーコード管理。 |
| `NetworkUtil` | `handler/NetworkUtil.java:1` | [→](./handler/NetworkUtil.md) | クライアントネットワークUtil。`ClientProxy` から。 |
| `NetworkUtilServer` | `handler/NetworkUtilServer.java:1` | [→](./handler/NetworkUtilServer.md) | サーバーUtil。`CommonProxy.serverStart`。 |
| `RegisterOreHandler` | `handler/RegisterOreHandler.java:1` | [→](./handler/RegisterOreHandler.md) | 鉱石辞書登録。`LoadOreDicHandler` から呼出。`crop*` / `dust*` 等を `OreDictionary.registerOre`。 |
| `TimeHandler` | `handler/TimeHandler.java:1` | [→](./handler/TimeHandler.md) | 時間管理。 |
| `Util` | `handler/Util.java:1` | [→](./handler/Util.md) | 汎用Util。`getCupScale()` / `getCupSize()` / `notEmptyItem()` / `checkDebugModePass()` 等。 |
| `PropertyHandler` | `common/config/PropertyHandler.java:1` | [→](./handler/PropertyHandler.md) | `DCsConfig` の派生値算出。`ChargeGenRate()` / `rateRF/EU/GF()` 等。 |
| `AMTLogger` | `common/AMTLogger.java:1` | [→](./handler/AMTLogger.md) | ロガー。`debugInfo` 等。 |

## 詳細

### `Coord` / `CoordListRegister` (`handler/Coord.java:1` / `CoordListRegister.java:1`)
- `CrowDoll` が設置されたチャンク座標を `Coord(x,y,z)` で `List<Coord>` に保存。
- `SpawnCancelEvent` が `LivingSpawnEvent.CheckSpawn` で `CoordListRegister.contains(x,y,z)` なら `Result.DENY` でスポーン抑制。
- 永続化は `WorldSavedData` ではなく `TileCrowDoll` の `writeToNBT` で保存 + `CommonProxy` の `serverStart` で `NetworkUtilServer` 経由で同期。
- **移行**: `WorldSavedData` / `SavedData` (`Level#saveData`) + `Capability` に置換。`BlockPos` 化。

### `CustomExplosion` (`handler/CustomExplosion.java:1`)
- `net.minecraft.world.Explosion` を継承せず自作。`doExplosionA()` / `doExplosionB(boolean)` で `world.getBlock` の耐性チェックを旧 `Block.getExplosionResistance` で行い、`FML` の `ExplosionEvent` を発火。
- `fearMelon` / `completeFearMelon` で `Blocks.bedrock` も破壊。
- **移行**: `Explosion` (`Level#explode`) の `BlockInteraction` / `ExplosionMode` に移行。`ForgeEventFactory.onExplosionStart` etc.

### `RegisterOreHandler` (`handler/RegisterOreHandler.java:1`)
```java
OreDictionary.registerOre("cropApple", new ItemStack(Items.apple));
OreDictionary.registerOre("dustWood", new ItemStack(DCsAppleMilk.dustWood,1,0));
OreDictionary.registerOre("logYuzuWood", new ItemStack(DCsAppleMilk.logYuzu,1,0)); // testRecipe経由も
```
- 100+エントリを `LoadOreDicHandler` → `RegisterOreHandler.registerOre()` で登録。
- **移行**: `TagKey<Item>` (`data/forge/tags/items/crops/apple.json` etc) + `TagsProvider` (datagen)。

### `PropertyHandler` (`common/config/PropertyHandler.java:1`)
```java
public static int ChargeGenRate(){ return DCsConfig.chargeDif==0? r/2 : r; }
public static int rateRF(){ switch(DCsConfig.exchangeDif){ case 0: return 4; ... } }
```
- `DCsConfig.chargeDif/exchangeDif/dustDif` から派生値を算出。`TileChargerBase` / `Processor` が参照。
- **移行**: `Config.COMMON.chargeDif.get()` 経由に置換。

### `Util` (`handler/Util.java:1`)
```java
public static float getCupScale(){ return (float)DCsConfig.setCupScale; }
public static boolean notEmptyItem(ItemStack stack){ return stack!=null && stack.stackSize>0; }
public static boolean checkDebugModePass(EntityPlayer player){ return player.getDisplayName().equals(DCsConfig.debugPass); }
```

## 移行サマリ

| クラス | 1.7.10 | 1.16+ |
|---|---|---|
| `Coord` (`x,y,z`) | `int x,y,z` | `BlockPos` (`net.minecraft.core.BlockPos`) |
| `CoordListRegister` | `List<Coord>` + `TileCrowDoll` NBT | `SavedData` + `CompoundTag` (`Level#saveData`) |
| `CustomExplosion` | 自作 `Explosion` | `Level.explode` + `Explosion` (`Explosion.BlockInteraction`) |
| `FluidContMap` | `Fluid` → `FluidContainerData` Map | `CapabilityFluidHandler` |
| `RegisterOreHandler` | `OreDictionary.registerOre` | `TagKey` + `TagsProvider` |
| `Util.notEmptyItem` | `stack!=null` | `!stack.isEmpty()` (`ItemStack.EMPTY`) |

## 関連
- [Block 一覧](./blocks.md) - `TileCrowDoll` / `BlockCrowDoll`
- [Item 一覧](./items.md) - `ItemStack` Util
- [Config 一覧](./config.md) - `PropertyHandler`
- [Event 一覧](./events.md) - `SpawnCancelEvent`
- `src/main/java/mods/defeatedcrow/handler/*`
