# SpawnCancelEvent

> Source: `src/main/java/mods/defeatedcrow/event/SpawnCancelEvent.java:1`
> Event: `LivingSpawnEvent.CheckSpawn` (`net.minecraftforge.event.*` / `cpw.mods.fml.common.gameevent.*`)
> Bus: `MinecraftForge.EVENT_BUS` / `FMLCommonHandler.instance().bus()`
> Category: `Mob, CrowDoll`

## 概要
カラス人形周辺でMobスポーン抑制。CoordListRegisterで座標管理。

## 登録情報
- **クラス**: `SpawnCancelEvent` (`src/main/java/mods/defeatedcrow/event/SpawnCancelEvent.java:1`)
- **Subscribe**: `@SubscribeEvent public void onEvent(LivingSpawnEvent.CheckSpawn event)`
- **登録**: `MinecraftForge.EVENT_BUS.register(new SpawnCancelEvent())` at `DCsAppleMilk.java:xxx` (preInit/init)

## プロパティ / 処理
- **Event**: `LivingSpawnEvent.CheckSpawn`
- **処理**: カラス人形周辺でMobスポーン抑制。CoordListRegisterで座標管理。
- **Result**: `event.setResult(Result.ALLOW/DENY)` / `event.setCanceled(true)` 等

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `LivingEvent.LivingUpdateEvent` / `LivingHurtEvent` / `LivingDropsEvent` | 維持 (`net.minecraftforge.event.entity.living.*`) | `LivingEvent` → `LivingEvent` 維持だが `EntityLivingBase` → `LivingEntity` |
| `FMLCommonHandler.instance().bus()` | `MinecraftForge.EVENT_BUS` に統合 (1.12) | 同左 |
| `event.world.getBlock(x,y,z)` | `event.getWorld().getBlockState(pos).getBlock()` | 同左 |

## 関連ドキュメント
- [Event 一覧](../events.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
