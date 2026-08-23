# Event 個別ページ索引

> 総数: **12 Handler** (`event/*`) + **7 API Event** (`api/events/*` + `api/plants/*`)
> 本ディレクトリは 1.7.10→1.12.2 移行向けの個別ページ集。

## 一覧

### event/* (12)

| クラス | 監視Event | 個別ページ |
|---|---|---|
| `BucketFillEvent` | `FillBucketEvent` | [→](./BucketFillEvent.md) |
| `CraftingEvent` | `ItemCraftedEvent` | [→](./CraftingEvent.md) |
| `DCsBonemealEvent` | `BonemealEvent` | [→](./DCsBonemealEvent.md) |
| `DCsHurtEvent` | `LivingHurtEvent` | [→](./DCsHurtEvent.md) |
| `DCsLivingEvent` | `LivingUpdateEvent` | [→](./DCsLivingEvent.md) |
| `DispenserEvent` | `BlockDispenser` | [→](./DispenserEvent.md) |
| `EatFoodEvent` | `PlayerUseItemEvent.Finish` | [→](./EatFoodEvent.md) |
| `EntityMoreDropEvent` | `LivingDropsEvent` | [→](./EntityMoreDropEvent.md) |
| `FluidContainerRegisterEvent` | `FluidContainerRegisterEvent` | [→](./FluidContainerRegisterEvent.md) |
| `FluidDispenser` | Dispenser | [→](./FluidDispenser.md) |
| `ShowOreNameEvent` | `ItemTooltipEvent` | [→](./ShowOreNameEvent.md) |
| `SpawnCancelEvent` | `CheckSpawn` | [→](./SpawnCancelEvent.md) |

### api/events/* (7)

| クラス | 発火元 | 個別ページ |
|---|---|---|
| `AMTBlockRightClickEvent` | Block右クリック | [→](./AMTBlockRightClickEvent.md) |
| `TeamakerRightClickEvent` | TeaMaker | [→](./TeamakerRightClickEvent.md) |
| `EatEdiblesEvent` | Placeable | [→](./EatEdiblesEvent.md) |
| `KnifeCutEvent` | Knife | [→](./KnifeCutEvent.md) |
| `ShootingGunEvent` | Gatling/Cannon | [→](./ShootingGunEvent.md) |
| `UseSlagEvent` | AdvProcessor | [→](./UseSlagEvent.md) |
| `PlantsClickEvent` | TeaTree等 | [→](./PlantsClickEvent.md) |

---

## 生成元
- テンプレ: 各個別ページはEventテンプレを兼ねる
- 移行ガイド: [`migration-guide.md`](./migration-guide.md)
- 一覧: [`events.md`](../events.md)
