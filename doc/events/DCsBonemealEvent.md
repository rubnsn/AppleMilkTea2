# DCsBonemealEvent

> Source: `src/main/java/mods/defeatedcrow/event/DCsBonemealEvent.java:1`
> Event: `BonemealEvent` (`net.minecraftforge.event.*` / `cpw.mods.fml.common.gameevent.*`)
> Bus: `MinecraftForge.EVENT_BUS` / `FMLCommonHandler.instance().bus()`
> Category: `Plants`

## 概要
骨粉で茶樹/カシス/ミント成長。fertilize()が成功でResult.ALLOW。

## 登録情報
- **クラス**: `DCsBonemealEvent` (`src/main/java/mods/defeatedcrow/event/DCsBonemealEvent.java:1`)
- **Subscribe**: `@SubscribeEvent public void onEvent(BonemealEvent event)`
- **登録**: `MinecraftForge.EVENT_BUS.register(new DCsBonemealEvent())` at `DCsAppleMilk.java:xxx` (preInit/init)

## プロパティ / 処理
- **Event**: `BonemealEvent`
- **処理**: 骨粉で茶樹/カシス/ミント成長。fertilize()が成功でResult.ALLOW。
- **Result**: `event.setResult(Result.ALLOW/DENY)` / `event.setCanceled(true)` 等

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `BonemealEvent` (`event.x,y,z`, `world.getBlock`) | `BonemealEvent` (`BlockPos`, `IBlockState`) | `BonemealEvent` (`BlockState`, `Level`) |
| `FMLCommonHandler.instance().bus()` | `MinecraftForge.EVENT_BUS` に統合 (1.12) | 同左 |
| `event.world.getBlock(x,y,z)` | `event.getWorld().getBlockState(pos).getBlock()` | 同左 |

## 関連ドキュメント
- [Event 一覧](../events.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
