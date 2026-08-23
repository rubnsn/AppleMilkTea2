# FluidContainerRegisterEvent

> Source: `src/main/java/mods/defeatedcrow/event/FluidContainerRegisterEvent.java:1`
> Event: `FluidContainerRegistry.FluidContainerRegisterEvent` (`net.minecraftforge.event.*` / `cpw.mods.fml.common.gameevent.*`)
> Bus: `MinecraftForge.EVENT_BUS` / `FMLCommonHandler.instance().bus()`
> Category: `Fluid`

## 概要
流体コンテナ登録時にFluidContMapへ登録。

## 登録情報
- **クラス**: `FluidContainerRegisterEvent` (`src/main/java/mods/defeatedcrow/event/FluidContainerRegisterEvent.java:1`)
- **Subscribe**: `@SubscribeEvent public void onEvent(FluidContainerRegistry.FluidContainerRegisterEvent event)`
- **登録**: `MinecraftForge.EVENT_BUS.register(new FluidContainerRegisterEvent())` at `DCsAppleMilk.java:xxx` (preInit/init)

## プロパティ / 処理
- **Event**: `FluidContainerRegistry.FluidContainerRegisterEvent`
- **処理**: 流体コンテナ登録時にFluidContMapへ登録。
- **Result**: `event.setResult(Result.ALLOW/DENY)` / `event.setCanceled(true)` 等

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `FluidContainerRegistry.FluidContainerRegisterEvent` | 維持 (`net.minecraftforge.event.*`) | 維持だが `World` → `Level` |
| `FMLCommonHandler.instance().bus()` | `MinecraftForge.EVENT_BUS` に統合 (1.12) | 同左 |
| `event.world.getBlock(x,y,z)` | `event.getWorld().getBlockState(pos).getBlock()` | 同左 |

## 関連ドキュメント
- [Event 一覧](../events.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
