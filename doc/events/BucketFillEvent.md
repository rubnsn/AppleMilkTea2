# BucketFillEvent

> Source: `src/main/java/mods/defeatedcrow/event/BucketFillEvent.java:1`
> Event: `FillBucketEvent` (`net.minecraftforge.event.*` / `cpw.mods.fml.common.gameevent.*`)
> Bus: `MinecraftForge.EVENT_BUS` / `FMLCommonHandler.instance().bus()`
> Category: `Bucket, Fluid`

## 概要
流体バケツ汲み。BuildCraft由来ハンドル。buckets MapでBlock->Item紐付け。

## 登録情報
- **クラス**: `BucketFillEvent` (`src/main/java/mods/defeatedcrow/event/BucketFillEvent.java:1`)
- **Subscribe**: `@SubscribeEvent public void onEvent(FillBucketEvent event)`
- **登録**: `MinecraftForge.EVENT_BUS.register(new BucketFillEvent())` at `DCsAppleMilk.java:xxx` (preInit/init)

## プロパティ / 処理
- **Event**: `FillBucketEvent`
- **処理**: 流体バケツ汲み。BuildCraft由来ハンドル。buckets MapでBlock->Item紐付け。
- **Result**: `event.setResult(Result.ALLOW/DENY)` / `event.setCanceled(true)` 等

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `cpw.mods.fml.common.eventhandler.SubscribeEvent` + `FillBucketEvent` (`MovingObjectPosition`) | `net.minecraftforge.event.entity.player.FillBucketEvent` (`RayTraceResult`) | `FillBucketEvent` + `BlockHitResult` / `InteractionResult` |
| `FMLCommonHandler.instance().bus()` | `MinecraftForge.EVENT_BUS` に統合 (1.12) | 同左 |
| `event.world.getBlock(x,y,z)` | `event.getWorld().getBlockState(pos).getBlock()` | 同左 |

## 関連ドキュメント
- [Event 一覧](../events.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
