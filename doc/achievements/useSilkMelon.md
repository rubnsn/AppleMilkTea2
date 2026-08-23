# useSilkMelon (dc.explodeSilkMelon)

> Source: `src/main/java/mods/defeatedcrow/common/AchievementRegister.java:1`
> Registry: `new Achievement("defeatedcrow.useSilkMelon", "dc.explodeSilkMelon", -1, 7, new ItemStack(DCsAppleMilk.silkyMelon), parent).registerStat()` (`AchievementRegister.java:58`)
> Parent: `crashMelon`
> Display: `ItemStack(DCsAppleMilk.silkyMelon)` at (-1,7)
> Type: `特殊 (setSpecial)` / Independent: false (child)

## 概要
シルキーメロン。特殊。

## 登録情報
- **クラス**: `net.minecraft.stats.Achievement`
- **ID**: `"defeatedcrow.useSilkMelon"` (stat) / `"dc.explodeSilkMelon"` (name)
- **位置**: `(-1,7)` グリッド
- **アイコン**: `new ItemStack(DCsAppleMilk.silkyMelon)`
- **親**: `crashMelon` (依存 `registerStat()`)
- **特殊**: `setSpecial()` で光沢枠
- **ページ**: `AchievementRegister.DCachievementPage = new AchievementPage("Apple&Milk&Tea!", list)` (`AchievementRegister.java:376`)

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `net.minecraft.stats.Achievement` + `AchievementPage` | **削除** → `Advancement` (`Advancement` JSON + `AdvancementProvider`) | 同左 + `AdvancementHolder` |
| `player.triggerAchievement(AchievementRegister.useSilkMelon)` | `AdvancementManager` + `ServerPlayer` の `AdvancementProgress.grantCriterion` / `CriterionTrigger` | 同左 |
| `AchievementList.openInventory` 等 | `Advancement` の `parent` に `minecraft:story/root` 等を指定 | 同左 |

移行例 (1.16+ JSON):
```json
{
  "display": { "icon": { "item": "defeatedcrow:teamaker_next" }, "title": { "translate": "advancement.defeatedcrow.useSilkMelon.title" }, "frame_type": "challenge" },
  "parent": "minecraft:story/root",
  "criteria": { "craft": { "trigger": "minecraft:inventory_changed", "conditions": { "items": [{ "items": ["defeatedcrow:teamaker_next"] }] } } }
}
```

## 関連ドキュメント
- [Achievement 一覧](../achievements.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
