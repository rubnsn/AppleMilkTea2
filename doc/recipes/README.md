# Recipe 個別ページ索引

> 総数: **11 RecipeType** (`RecipeRegisterManager.java:1`)
> 本ディレクトリは 1.7.10→1.12.2 移行向けの個別ページ集。

## 一覧

| RecipeType | Manager | 対応Block | 件数 | 個別ページ |
|---|---|---|---|---|
| TeaMaker | `teaRecipe` | `TeaMakerNext` | 20 | [→](./TeaMaker.md) |
| IceMaker | `iceRecipe` | `IceMaker` | 13+4 | [→](./IceMaker.md) |
| Pan | `panRecipe` | `EmptyPanGaiden` | 10 | [→](./Pan.md) |
| Plate (Teppan) | `plateRecipe` | `TeppanII` | 7 | [→](./Plate.md) |
| Processor | `processorRecipe` | `Processor` | 50+ | [→](./Processor.md) |
| AdvProcessor | `processorRecipe` (tier) | `AdvProcessor` | 10+ | [→](./AdvProcessor.md) |
| Evaporator | `evaporatorRecipe` | `Evaporator` | 15 | [→](./Evaporator.md) |
| Brewing | `brewingRecipe` | `Barrel` | 7 | [→](./Brewing.md) |
| Fondue | `fondueRecipe` | `Fondue`/`baseSoupBowl` | 10 | [→](./Fondue.md) |
| Chocolate | `chocoRecipe` | `ItemChocoFruits` | 14 | [→](./Chocolate.md) |
| Charge | `chargeItem` | `BatBox`/`HandleEngine` | 5 | [→](./ChargeItem.md) |

---

## 生成元
- テンプレ: 各個別ページは `Recipe` テンプレを兼ねる
- 移行ガイド: [`migration-guide.md`](./migration-guide.md)
- 一覧: [`recipes.md`](../recipes.md)
