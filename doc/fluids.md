# Fluid 一覧

> 登録元: `src/main/java/mods/defeatedcrow/common/MaterialRegister.java:490-718` (`addFluid()`)  
> フィールド元: `src/main/java/mods/defeatedcrow/common/DCsAppleMilk.java:318-340`  
> 総数: **18 Fluid** + 4 Fluidブロック + 5 流体コンテナItem  
> 個別ページ: [`doc/fluids/`](./fluids/README.md) に 18件の個別ページを生成  
> 移行ガイド: [`doc/fluids/migration-guide.md`](./fluids/migration-guide.md)

## 概要
植物油・椿油はブロック＋バケツ＋ボトルの3点セットで登録（`FluidContainerRegistry`）。醸造酒はFluidのみ登録し、ボトル/バケツはメタデータで共用。

## Fluid 一覧

### 1. 食用油（ブロックあり）

| フィールド名 | 流体名 (FluidRegistry) | ブロック | バケツ | ボトル | Density/Viscosity | 用途 |
|---|---|---|---|---|---|---|
| `vegitableOil` | `vegitable_oil` | `blockVegitableOil` (`BlockOilFluid:1`) | `bucketVegiOil` | `bottleVegiOil` | 800 / 1500 | 揚げ物、精製燃料、Teppanレシピ |
| `camelliaOil` | `camellia_oil` | `blockCamelliaOil` (`BlockCamOilFluid:1`) | `bucketCamOil` | `bottleCamOil` | 800 / 1500 | 椿油、食用・燃料 |

登録詳細:
- `FluidRegistry.registerFluid` → `GameRegistry.registerBlock` で流体ブロック登録 → `FluidContainerRegistry.registerFluidContainer` でバケツ(1000mB)・ボトル(200mB)紐付け

### 2. 醸造酒（若い酒 / Young）- 5種

| フィールド名 | 流体名 | ブロック | コンテナ | 備考 |
|---|---|---|---|---|
| `shothu_young` | `shothu_young` | なし（樽内で処理） | `bucketYoungAlcohol:0` | 焼酎（若） |
| `whiskey_young` | `whiskey_young` | なし | `bucketYoungAlcohol:1` | ウイスキー（若） |
| `brandy_young` | `brandy_young` | なし | `bucketYoungAlcohol:2` | ブランデー（若） |
| `rum_young` | `rum_young` | なし | `bucketYoungAlcohol:3` | ラム（若） |
| `vodka_young` | `vodka_young` | なし | `bucketYoungAlcohol:4` | ウォッカ（若） |

### 3. 醸造酒（完成品）- 5種

| フィールド名 | 流体名 | ボトル連携 | 備考 |
|---|---|---|---|
| `shothu` | `shothu_dc` | `itemLargeBottle:48` (200mB) | 焼酎完成品 |
| `whiskey` | `whiskey_dc` | `itemLargeBottle:55` | ウイスキー完成品 |
| `brandy` | `brandy_dc` | `itemLargeBottle:56` | ブランデー |
| `rum` | `rum_dc` | `itemLargeBottle:53` | ラム |
| `vodka` | `vodka_dc` | `itemLargeBottle:54` | ウォッカ |

### 4. 醸造酒（日本酒系 若）- 3種

| フィールド名 | 流体名 | コンテナ | 備考 |
|---|---|---|---|
| `sake_young` | `sake_young` | `moromi:0` | 日本酒もろみ |
| `beer_young` | `beer_young` | `moromi:1` | ビールもろみ |
| `wine_young` | `wine_young` | `moromi:2` | ワインもろみ |

### 5. 醸造酒（日本酒系 完成品）- 3種

| フィールド名 | 流体名 | ボトル連携 | 備考 |
|---|---|---|---|
| `sake` | `sake_dc` | `itemLargeBottle:49` | 日本酒 |
| `beer` | `beer_dc` | `itemLargeBottle:50` | ビール |
| `wine` | `wine_dc` | `itemLargeBottle:51` | ワイン |

## 流体ブロック

| フィールド名 | レジストリ名 | クラス | 流体 |
|---|---|---|---|
| `blockVegitableOil` | `defeatedcrow.blockVegiOil` | `BlockOilFluid:1` | vegitable_oil |
| `blockCamelliaOil` | `defeatedcrow.blockCamOil` | `BlockCamOilFluid:1` | camellia_oil |
| `blockDummyAlcohol` | `defeatedcrow.blockDummyAlcohol` | `BlockDummyFluid:1` | （ダミー、醸造樽演出用） |
| `blockDummyAlcohol2` | `defeatedcrow.blockDummyAlcohol2` | `BlockDummyFluid2:1` | （ダミー） |

## 流体コンテナ登録詳細

```java
// MaterialRegister.java:508-510 例
FluidContainerRegistry.registerFluidContainer(
    FluidRegistry.getFluidStack("vegitable_oil", FluidContainerRegistry.BUCKET_VOLUME),
    new ItemStack(DCsAppleMilk.bucketVegiOil),
    new ItemStack(Items.bucket));
FluidContainerRegistry.registerFluidContainer(
    FluidRegistry.getFluidStack("vegitable_oil", 200),
    new ItemStack(DCsAppleMilk.bottleVegiOil),
    new ItemStack(Item.getItemFromBlock(DCsAppleMilk.emptyBottle)));
```

- バケツ: `BUCKET_VOLUME = 1000`
- ボトル: `200`
- `DispenserEvent` と `FluidDispenser` でディスペンサー対応も登録 (`MaterialRegister.java:715-716`)

## ディスペンサー・バケツイベント
- `BucketFillEvent` : `src/main/java/mods/defeatedcrow/event/BucketFillEvent.java:1`
- `FluidDispenser` : `src/main/java/mods/defeatedcrow/event/FluidDispenser.java:1`

## 移行ドキュメント
- [個別ページ索引](./fluids/README.md) - 18個別ページの一覧
- [移行ガイド](./fluids/migration-guide.md) - 1.7.10→1.12.2 Fluid移行
- [テンプレ](./fluids/_template.md)

## 関連ドキュメント
- [Block 一覧](./blocks.md) - 流体ブロック
- [Block 個別ページ - Fluid](./blocks/fluid/BlockOilFluid.md) / [CamOil](./blocks/fluid/BlockCamOilFluid.md) / [Dummy1](./blocks/fluid/BlockDummyFluid.md) / [Dummy2](./blocks/fluid/BlockDummyFluid2.md)
- [Block 個別ページ索引](./blocks/README.md)
- [Item 一覧](./items.md) - バケツ・ボトル
- [TileEntity 一覧](./tile-entities.md) - Barrel連携
