# craftJaw (dc.craftHyperJaw)

> Source: `src/main/java/mods/defeatedcrow/common/AchievementRegister.java:1`
> Registry: `new Achievement("defeatedcrow.craftJaw", "dc.craftHyperJaw", 6, 1, new ItemStack(DCsAppleMilk.advProcessor), parent).registerStat()` (`AchievementRegister.java:58`)
> Parent: `craftChalGear`
> Display: `ItemStack(DCsAppleMilk.advProcessor)` at (6,1)
> Type: `通常` / Independent: false (child)

## 概要
ジョークラッシャー。

## 登録情報
- **クラス**: `net.minecraft.stats.Achievement`
- **ID**: `"defeatedcrow.craftJaw"` (stat) / `"dc.craftHyperJaw"` (name)
- **位置**: `(6,1)` グリッド
- **アイコン**: `new ItemStack(DCsAppleMilk.advProcessor)`
- **親**: `craftChalGear` (依存 `registerStat()`)
- **ページ**: `AchievementRegister.DCachievementPage = new AchievementPage("Apple&Milk&Tea!", list)` (`AchievementRegister.java:376`)

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `net.minecraft.stats.Achievement` + `AchievementPage` | **削除** → `Advancement` (`Advancement` JSON + `AdvancementProvider`) | 同左 + `AdvancementHolder` |
| `player.triggerAchievement(AchievementRegister.craftJaw)` | `AdvancementManager` + `ServerPlayer` の `AdvancementProgress.grantCriterion` / `CriterionTrigger` | 同左 |
| `AchievementList.openInventory` 等 | `Advancement` の `parent` に `minecraft:story/root` 等を指定 | 同左 |

移行例 (1.16+ JSON):
```json
{
  "display": { "icon": { "item": "defeatedcrow:teamaker_next" }, "title": { "translate": "advancement.defeatedcrow.craftJaw.title" }, "frame_type": "task" },
  "parent": "minecraft:story/root",
  "criteria": { "craft": { "trigger": "minecraft:inventory_changed", "conditions": { "items": [{ "items": ["defeatedcrow:teamaker_next"] }] } } }
}
```

## 関連ドキュメント
- [Achievement 一覧](../achievements.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
