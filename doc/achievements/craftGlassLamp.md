# craftGlassLamp (dc.craftGlassLamp)

> Source: `src/main/java/mods/defeatedcrow/common/AchievementRegister.java:1`
> Registry: `new Achievement("defeatedcrow.glasslamp", "dc.craftGlassLamp", 3, 4, new ItemStack(DCsAppleMilk.cLamp), parent).registerStat()` (`AchievementRegister.java:58`)
> Parent: `craftChalcedony`
> Display: `ItemStack(DCsAppleMilk.cLamp)` at (3,4)
> Type: `特殊 (setSpecial)` / Independent: false (child)

## 概要
ガラスランプ。

## 登録情報
- **クラス**: `net.minecraft.stats.Achievement`
- **ID**: `"defeatedcrow.glasslamp"` (stat) / `"dc.craftGlassLamp"` (name)
- **位置**: `(3,4)` グリッド
- **アイコン**: `new ItemStack(DCsAppleMilk.cLamp)`
- **親**: `craftChalcedony` (依存 `registerStat()`)
- **特殊**: `setSpecial()` で光沢枠
- **ページ**: `AchievementRegister.DCachievementPage = new AchievementPage("Apple&Milk&Tea!", list)` (`AchievementRegister.java:376`)

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `net.minecraft.stats.Achievement` + `AchievementPage` | **削除** → `Advancement` (`Advancement` JSON + `AdvancementProvider`) | 同左 + `AdvancementHolder` |
| `player.triggerAchievement(AchievementRegister.craftGlassLamp)` | `AdvancementManager` + `ServerPlayer` の `AdvancementProgress.grantCriterion` / `CriterionTrigger` | 同左 |
| `AchievementList.openInventory` 等 | `Advancement` の `parent` に `minecraft:story/root` 等を指定 | 同左 |

移行例 (1.16+ JSON):
```json
{
  "display": { "icon": { "item": "defeatedcrow:teamaker_next" }, "title": { "translate": "advancement.defeatedcrow.craftGlassLamp.title" }, "frame_type": "challenge" },
  "parent": "minecraft:story/root",
  "criteria": { "craft": { "trigger": "minecraft:inventory_changed", "conditions": { "items": [{ "items": ["defeatedcrow:teamaker_next"] }] } } }
}
```

## 関連ドキュメント
- [Achievement 一覧](../achievements.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
