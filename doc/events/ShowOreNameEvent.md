# ShowOreNameEvent

> Source: `src/main/java/mods/defeatedcrow/event/ShowOreNameEvent.java:1`
> Event: `ItemTooltipEvent` (`net.minecraftforge.event.*` / `cpw.mods.fml.common.gameevent.*`)
> Bus: `MinecraftForge.EVENT_BUS` / `FMLCommonHandler.instance().bus()`
> Category: `Tool, OreDict`

## 概要
Monocle装備でShift時にOreDictionary名を表示。

## 登録情報
- **クラス**: `ShowOreNameEvent` (`src/main/java/mods/defeatedcrow/event/ShowOreNameEvent.java:1`)
- **Subscribe**: `@SubscribeEvent public void onEvent(ItemTooltipEvent event)`
- **登録**: `MinecraftForge.EVENT_BUS.register(new ShowOreNameEvent())` at `DCsAppleMilk.java:xxx` (preInit/init)

## プロパティ / 処理
- **Event**: `ItemTooltipEvent`
- **処理**: Monocle装備でShift時にOreDictionary名を表示。
- **Result**: `event.setResult(Result.ALLOW/DENY)` / `event.setCanceled(true)` 等

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `ItemTooltipEvent` (`net.minecraftforge.event.entity.player.ItemTooltipEvent`) | 維持 | `ItemTooltipEvent` + `Component` (`List<Component>`) |
| `FMLCommonHandler.instance().bus()` | `MinecraftForge.EVENT_BUS` に統合 (1.12) | 同左 |
| `event.world.getBlock(x,y,z)` | `event.getWorld().getBlockState(pos).getBlock()` | 同左 |

## 関連ドキュメント
- [Event 一覧](../events.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
