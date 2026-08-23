# Fluid 個別ページ索引

> 総数: **18 Fluid** + 4 FluidBlock + 5 Container
> 登録元: `src/main/java/mods/defeatedcrow/common/MaterialRegister.java:490-718` (`addFluid()`)
> 本ディレクトリは1.7.10→1.12.2移行向けの個別ページ集。

## カテゴリ別一覧

### 食用油 (2) - ブロックあり
`FluidRegistry.registerFluid` → `BlockFluidClassic` + `ItemBucket`/`ItemBottle`

| Fluid名 | フィールド | ブロック | バケツ | ボトル | 個別ページ |
|---|---|---|---|---|---|
| `vegitable_oil` | `vegitableOil` | `blockVegitableOil` (`BlockOilFluid`) | `bucketVegiOil` | `bottleVegiOil` | [→](./vegitableOil.md) |
| `camellia_oil` | `camelliaOil` | `blockCamelliaOil` (`BlockCamOilFluid`) | `bucketCamOil` | `bottleCamOil` | [→](./camelliaOil.md) |

### 醸造酒 若い酒 (8)
`bucketYoungAlcohol` (メタ0-4) + `moromi` (0-2) で共用

| Fluid名 | フィールド | コンテナ | 個別ページ |
|---|---|---|---|
| `shothu_young` | `shothu_young` | `bucketYoungAlcohol:0` | [→](./shothu_young.md) |
| `whiskey_young` | `whiskey_young` | `bucketYoungAlcohol:1` | [→](./whiskey_young.md) |
| `brandy_young` | `brandy_young` | `bucketYoungAlcohol:2` | [→](./brandy_young.md) |
| `rum_young` | `rum_young` | `bucketYoungAlcohol:3` | [→](./rum_young.md) |
| `vodka_young` | `vodka_young` | `bucketYoungAlcohol:4` | [→](./vodka_young.md) |
| `sake_young` | `sake_young` | `moromi:0` | [→](./sake_young.md) |
| `beer_young` | `beer_young` | `moromi:1` | [→](./beer_young.md) |
| `wine_young` | `wine_young` | `moromi:2` | [→](./wine_young.md) |

### 醸造酒 完成品 (8)
`itemLargeBottle` の NBT/メタで管理

| Fluid名 | フィールド | ボトル連携 | 個別ページ |
|---|---|---|---|
| `shothu_dc` | `shothu` | `itemLargeBottle:48` | [→](./shothu.md) |
| `whiskey_dc` | `whiskey` | `itemLargeBottle:55` | [→](./whiskey.md) |
| `brandy_dc` | `brandy` | `itemLargeBottle:56` | [→](./brandy.md) |
| `rum_dc` | `rum` | `itemLargeBottle:53` | [→](./rum.md) |
| `vodka_dc` | `vodka` | `itemLargeBottle:54` | [→](./vodka.md) |
| `sake_dc` | `sake` | `itemLargeBottle:49` | [→](./sake.md) |
| `beer_dc` | `beer` | `itemLargeBottle:50` | [→](./beer.md) |
| `wine_dc` | `wine` | `itemLargeBottle:51` | [→](./wine.md) |

### 流体ブロック (4)
| Block | レジストリ名 | 流体 | 個別ページ |
|---|---|---|---|
| `BlockOilFluid` | `defeatedcrow.blockVegiOil` | `vegitable_oil` | [→](./BlockOilFluid.md) |
| `BlockCamOilFluid` | `defeatedcrow.blockCamOil` | `camellia_oil` | [→](./BlockCamOilFluid.md) |
| `BlockDummyFluid` | `defeatedcrow.blockDummyAlcohol` | ダミー | [→](./BlockDummyFluid.md) |
| `BlockDummyFluid2` | `defeatedcrow.blockDummyAlcohol2` | ダミー | [→](./BlockDummyFluid2.md) |

---

## 生成元
- テンプレ: [`_template.md`](./_template.md)
- 移行ガイド: [`migration-guide.md`](./migration-guide.md)
- 一覧: [`fluids.md`](../fluids.md)
