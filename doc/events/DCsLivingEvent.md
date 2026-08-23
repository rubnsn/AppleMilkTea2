# DCsLivingEvent

> Source: `src/main/java/mods/defeatedcrow/event/DCsLivingEvent.java:1`
> Event: `LivingEvent.LivingUpdateEvent` (`net.minecraftforge.event.*` / `cpw.mods.fml.common.gameevent.*`)
> Bus: `MinecraftForge.EVENT_BUS` / `FMLCommonHandler.instance().bus()`
> Category: `Potion, Charm, Coord`

## 概要
毎tick PotionLivingBase/formPotionEffect, CharmWarp, CoordListRegister。

## 登録情報
- **クラス**: `DCsLivingEvent` (`src/main/java/mods/defeatedcrow/event/DCsLivingEvent.java:1`)
- **Subscribe**: `@SubscribeEvent public void onEvent(LivingEvent.LivingUpdateEvent event)`
- **登録**: `MinecraftForge.EVENT_BUS.register(new DCsLivingEvent())` at `DCsAppleMilk.java:xxx` (preInit/init)

## プロパティ / 処理
- **Event**: `LivingEvent.LivingUpdateEvent`
- **処理**: 毎tick PotionLivingBase/formPotionEffect, CharmWarp, CoordListRegister。
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
