# EatFoodEvent

> Source: `src/main/java/mods/defeatedcrow/event/EatFoodEvent.java:1`
> Event: `PlayerUseItemEvent.Finish` (`net.minecraftforge.event.*` / `cpw.mods.fml.common.gameevent.*`)
> Bus: `MinecraftForge.EVENT_BUS` / `FMLCommonHandler.instance().bus()`
> Category: `Debug`

## 概要
食事完了デバッグ。LoadModHandler.getItem("DCsBakedApple")比較。

## 登録情報
- **クラス**: `EatFoodEvent` (`src/main/java/mods/defeatedcrow/event/EatFoodEvent.java:1`)
- **Subscribe**: `@SubscribeEvent public void onEvent(PlayerUseItemEvent.Finish event)`
- **登録**: `MinecraftForge.EVENT_BUS.register(new EatFoodEvent())` at `DCsAppleMilk.java:xxx` (preInit/init)

## プロパティ / 処理
- **Event**: `PlayerUseItemEvent.Finish`
- **処理**: 食事完了デバッグ。LoadModHandler.getItem("DCsBakedApple")比較。
- **Result**: `event.setResult(Result.ALLOW/DENY)` / `event.setCanceled(true)` 等

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `PlayerEvent.ItemCraftedEvent` (`cpw.mods.fml.common.gameevent.PlayerEvent`) | `PlayerEvent.ItemCraftedEvent` (`net.minecraftforge.event.entity.player.PlayerEvent`) | 同左 + `ServerPlayer` |
| `FMLCommonHandler.instance().bus()` | `MinecraftForge.EVENT_BUS` に統合 (1.12) | 同左 |
| `event.world.getBlock(x,y,z)` | `event.getWorld().getBlockState(pos).getBlock()` | 同左 |

## 関連ドキュメント
- [Event 一覧](../events.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
