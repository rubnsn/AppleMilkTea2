# makeTeaLeaves (dc.makeTeaLeaves)

> Source: `src/main/java/mods/defeatedcrow/common/AchievementRegister.java:1`
> Registry: `new Achievement("defeatedcrow.makeTealeaves", "dc.makeTeaLeaves", -2, 0, new ItemStack(DCsAppleMilk.foodTea:0), parent).registerStat()` (`AchievementRegister.java:58`)
> Parent: `getTeaLeaves`
> Display: `ItemStack(DCsAppleMilk.foodTea:0)` at (-2,0)
> Type: `通常` / Independent: true

## 概要
製茶。

## 登録情報
- **クラス**: `net.minecraft.stats.Achievement`
- **ID**: `"defeatedcrow.makeTealeaves"` (stat) / `"dc.makeTeaLeaves"` (name)
- **位置**: `(-2,0)` グリッド
- **アイコン**: `new ItemStack(DCsAppleMilk.foodTea:0)`
- **親**: `getTeaLeaves` (依存 `registerStat()`)
- **ページ**: `AchievementRegister.DCachievementPage = new AchievementPage("Apple&Milk&Tea!", list)` (`AchievementRegister.java:376`)

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `net.minecraft.stats.Achievement` + `AchievementPage` | **削除** → `Advancement` (`Advancement` JSON + `AdvancementProvider`) | 同左 + `AdvancementHolder` |
| `player.triggerAchievement(AchievementRegister.makeTeaLeaves)` | `AdvancementManager` + `ServerPlayer` の `AdvancementProgress.grantCriterion` / `CriterionTrigger` | 同左 |
| `AchievementList.openInventory` 等 | `Advancement` の `parent` に `minecraft:story/root` 等を指定 | 同左 |

移行例 (1.16+ JSON):
```json
{
  "display": { "icon": { "item": "defeatedcrow:teamaker_next" }, "title": { "translate": "advancement.defeatedcrow.makeTeaLeaves.title" }, "frame_type": "task" },
  "parent": "minecraft:story/root",
  "criteria": { "craft": { "trigger": "minecraft:inventory_changed", "conditions": { "items": [{ "items": ["defeatedcrow:teamaker_next"] }] } } }
}
```

## 関連ドキュメント
- [Achievement 一覧](../achievements.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
