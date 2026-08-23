# Event 一覧

> Source: `src/main/java/mods/defeatedcrow/event/*` (12クラス) + `api/events/*` (6クラス) + `api/plants/PlantsClickEvent.java:1`
> Bus: `MinecraftForge.EVENT_BUS` (`cpw.mods.fml.common.eventhandler.EventBus`) / `FMLCommonHandler.instance().bus()`
> 総数: **12 EventHandler + 6 API Event**

## 概要
Forge EventBus を利用。`@SubscribeEvent` で登録し `DCsAppleMilk.java:xxx` の `preInit/init` で `MinecraftForge.EVENT_BUS.register(new Handler())` または `FMLCommonHandler.instance().bus().register`。一部は `api/events/*` に公開APIイベントを定義し `MinecraftForge.EVENT_BUS.post(new AMTBlockRightClickEvent(...))` で自作イベントを発火。

## 一覧

### event/* (12) - Forge 標準イベントのハンドラ

| クラス | ソース | 監視Event | Bus | 個別ページ | 説明 |
|---|---|---|---|---|---|
| `BucketFillEvent` | `event/BucketFillEvent.java:1` | `FillBucketEvent` | `EVENT_BUS` | [→](./events/BucketFillEvent.md) | バケツ汲み (BuildCraft由来) |
| `CraftingEvent` | `event/CraftingEvent.java:1` | `PlayerEvent.ItemCraftedEvent` | `FML bus` | [→](./events/CraftingEvent.md) | クラフト実績 |
| `DCsBonemealEvent` | `event/DCsBonemealEvent.java:1` | `BonemealEvent` | `EVENT_BUS` | [→](./events/DCsBonemealEvent.md) | 骨粉成長 |
| `DCsHurtEvent` | `event/DCsHurtEvent.java:1` | `LivingHurtEvent` | `EVENT_BUS` | [→](./events/DCsHurtEvent.md) | 被ダメ軽減/反射 |
| `DCsLivingEvent` | `event/DCsLivingEvent.java:1` | `LivingUpdateEvent` | `EVENT_BUS` | [→](./events/DCsLivingEvent.md) | Potion tick/チャーム |
| `DispenserEvent` | `event/DispenserEvent.java:1` | (直接 `BlockDispenser` 登録) | - | [→](./events/DispenserEvent.md) | ディスペンサー拡張 |
| `EatFoodEvent` | `event/EatFoodEvent.java:1` | `PlayerUseItemEvent.Finish` | `EVENT_BUS` | [→](./events/EatFoodEvent.md) | 食事デバッグ |
| `EntityMoreDropEvent` | `event/EntityMoreDropEvent.java:1` | `LivingDropsEvent` | `EVENT_BUS` | [→](./events/EntityMoreDropEvent.md) | ドロップ増加 |
| `FluidContainerRegisterEvent` | `event/FluidContainerRegisterEvent.java:1` | `FluidContainerRegistry.FluidContainerRegisterEvent` | `EVENT_BUS` | [→](./events/FluidContainerRegisterEvent.md) | 流体Container記録 |
| `FluidDispenser` | `event/FluidDispenser.java:1` | (Dispenser Behavior) | - | [→](./events/FluidDispenser.md) | 流体ディスペンサー |
| `ShowOreNameEvent` | `event/ShowOreNameEvent.java:1` | `ItemTooltipEvent` | `EVENT_BUS` | [→](./events/ShowOreNameEvent.md) | Monocle鉱石名 |
| `SpawnCancelEvent` | `event/SpawnCancelEvent.java:1` | `LivingSpawnEvent.CheckSpawn` | `EVENT_BUS` | [→](./events/SpawnCancelEvent.md) | スポーン抑制 |

### api/events/* (6) - 自作APIイベント (postされる側)

| クラス | ソース | 発火箇所 | 個別ページ | 説明 |
|---|---|---|---|---|
| `AMTBlockRightClickEvent` | `api/events/AMTBlockRightClickEvent.java:1` | `Block.onBlockActivated` 全Block | [→](./events/AMTBlockRightClickEvent.md) | Block右クリック |
| `TeamakerRightClickEvent` | `api/events/TeamakerRightClickEvent.java:1` | `BlockTeaMakerNext` | [→](./events/TeamakerRightClickEvent.md) | TeaMaker右クリック |
| `EatEdiblesEvent` | `api/events/EatEdiblesEvent.java:1` | `PlaceableFoods` | [→](./events/EatEdiblesEvent.md) | 可食 |
| `KnifeCutEvent` | `api/events/KnifeCutEvent.java:1` | `ItemChalcedonyKnife` | [→](./events/KnifeCutEvent.md) | ナイフ切断 |
| `ShootingGunEvent` | `api/events/ShootingGunEvent.java:1` | `ItemYuzuGatling`/`FossilCannon` | [→](./events/ShootingGunEvent.md) | 銃発射 |
| `UseSlagEvent` | `api/events/UseSlagEvent.java:1` | `TileAdvProcessor` | [→](./events/UseSlagEvent.md) | スラグ使用 |
| `PlantsClickEvent` | `api/plants/PlantsClickEvent.java:1` | `BlockTeaTree` 等 | [→](./events/PlantsClickEvent.md) | 植物クリック |

## 登録例

```java
// 登録 (DCsAppleMilk.java:xxx)
MinecraftForge.EVENT_BUS.register(new BucketFillEvent());
MinecraftForge.EVENT_BUS.register(new DCsLivingEvent());
FMLCommonHandler.instance().bus().register(new CraftingEvent());

// 自作イベント発火 (BlockTeaMakerNext.java:xx)
AMTBlockRightClickEvent event = new AMTBlockRightClickEvent(player, world, x,y,z, block, meta, heldItem);
MinecraftForge.EVENT_BUS.post(event);
if(event.isCanceled()) return false;

// ハンドラ (event/DCsLivingEvent.java:1)
@SubscribeEvent
public void onLivingUpdate(LivingUpdateEvent event){
  EntityLivingBase entity = event.entityLiving;
  // Potion処理
}
```

## 詳細
- [個別ページ索引](./events/README.md) - 12+6個別ページへのリンク
- [移行ガイド](./events/migration-guide.md) - Forge 1.12/1.16 移行

## 関連
- [API 一覧](./api.md) - `api/events/*`
- [Potion 一覧](./potions.md) - `DCsLivingEvent` / `DCsHurtEvent`
- [Block 一覧](./blocks.md) - `AMTBlockRightClickEvent`
