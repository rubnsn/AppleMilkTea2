# craftTeaMaker (dc.craftTeaMaker)

> Source: `src/main/java/mods/defeatedcrow/common/AchievementRegister.java:1`
> Registry: `new Achievement("defeatedcrow.teamaker", "dc.craftTeaMaker", -2, -2, new ItemStack(DCsAppleMilk.teaMakerNext), parent).registerStat()` (`AchievementRegister.java:58`)
> Parent: `makeTeaLeaves`
> Display: `ItemStack(DCsAppleMilk.teaMakerNext)` at (-2,-2)
> Type: `通常` / Independent: true

## 概要
ティーメーカー製作。TeaMaker自体のクラフト。

## 登録情報
- **クラス**: `net.minecraft.stats.Achievement`
- **ID**: `"defeatedcrow.teamaker"` (stat) / `"dc.craftTeaMaker"` (name)
- **位置**: `(-2,-2)` グリッド
- **アイコン**: `new ItemStack(DCsAppleMilk.teaMakerNext)`
- **親**: `makeTeaLeaves` (依存 `registerStat()`)
- **ページ**: `AchievementRegister.DCachievementPage = new AchievementPage("Apple&Milk&Tea!", list)` (`AchievementRegister.java:376`)

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `net.minecraft.stats.Achievement` + `AchievementPage` | **削除** → `Advancement` (`Advancement` JSON + `AdvancementProvider`) | 同左 + `AdvancementHolder` |
| `player.triggerAchievement(AchievementRegister.craftTeaMaker)` | `AdvancementManager` + `ServerPlayer` の `AdvancementProgress.grantCriterion` / `CriterionTrigger` | 同左 |
| `AchievementList.openInventory` 等 | `Advancement` の `parent` に `minecraft:story/root` 等を指定 | 同左 |

移行例 (1.16+ JSON):
```json
{
  "display": { "icon": { "item": "defeatedcrow:teamaker_next" }, "title": { "translate": "advancement.defeatedcrow.craftTeaMaker.title" }, "frame_type": "task" },
  "parent": "minecraft:story/root",
  "criteria": { "craft": { "trigger": "minecraft:inventory_changed", "conditions": { "items": [{ "items": ["defeatedcrow:teamaker_next"] }] } } }
}
```

## 関連ドキュメント
- [Achievement 一覧](../achievements.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
