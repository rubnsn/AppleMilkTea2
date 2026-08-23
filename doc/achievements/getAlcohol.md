# getAlcohol (dc.getAlcohol)

> Source: `src/main/java/mods/defeatedcrow/common/AchievementRegister.java:1`
> Registry: `new Achievement("defeatedcrow.getAlcohol", "dc.getAlcohol", 4, 0, new ItemStack(DCsAppleMilk.itemLargeBottle:5), parent).registerStat()` (`AchievementRegister.java:58`)
> Parent: `craftBarrel`
> Display: `ItemStack(DCsAppleMilk.itemLargeBottle:5)` at (4,0)
> Type: `通常` / Independent: false (child)

## 概要
酒取得。

## 登録情報
- **クラス**: `net.minecraft.stats.Achievement`
- **ID**: `"defeatedcrow.getAlcohol"` (stat) / `"dc.getAlcohol"` (name)
- **位置**: `(4,0)` グリッド
- **アイコン**: `new ItemStack(DCsAppleMilk.itemLargeBottle:5)`
- **親**: `craftBarrel` (依存 `registerStat()`)
- **ページ**: `AchievementRegister.DCachievementPage = new AchievementPage("Apple&Milk&Tea!", list)` (`AchievementRegister.java:376`)

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `net.minecraft.stats.Achievement` + `AchievementPage` | **削除** → `Advancement` (`Advancement` JSON + `AdvancementProvider`) | 同左 + `AdvancementHolder` |
| `player.triggerAchievement(AchievementRegister.getAlcohol)` | `AdvancementManager` + `ServerPlayer` の `AdvancementProgress.grantCriterion` / `CriterionTrigger` | 同左 |
| `AchievementList.openInventory` 等 | `Advancement` の `parent` に `minecraft:story/root` 等を指定 | 同左 |

移行例 (1.16+ JSON):
```json
{
  "display": { "icon": { "item": "defeatedcrow:teamaker_next" }, "title": { "translate": "advancement.defeatedcrow.getAlcohol.title" }, "frame_type": "task" },
  "parent": "minecraft:story/root",
  "criteria": { "craft": { "trigger": "minecraft:inventory_changed", "conditions": { "items": [{ "items": ["defeatedcrow:teamaker_next"] }] } } }
}
```

## 関連ドキュメント
- [Achievement 一覧](../achievements.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
