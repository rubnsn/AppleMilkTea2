# Events API (`api/events/*`)

> Source: `src/main/java/mods/defeatedcrow/api/events/*`
> API: `AMTBlockRightClickEvent/TeamakerRightClickEvent/EatEdiblesEvent/KnifeCutEvent/ShootingGunEvent/UseSlagEvent`
> Category: `Event`

## 概要
APIイベント。Block右クリック/切断/射撃等をForge BUSでpost。

## クラス一覧
- **API**: `AMTBlockRightClickEvent/TeamakerRightClickEvent/EatEdiblesEvent/KnifeCutEvent/ShootingGunEvent/UseSlagEvent` (`src/main/java/mods/defeatedcrow/api/events/*`)
- **参照**: `grep -r "Events" src/` で使用箇所列挙

## 移行 (1.12.2+)
| 1.7.10 API | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `MinecraftForge.EVENT_BUS.post(new AMTBlockRightClickEvent(...))` | 維持 (`net.minecraftforge.eventbus.api.Event`) | 同左 (1.16 `EventBus` は `IEventBus`) |

## 関連ドキュメント
- [API 一覧](../api.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
