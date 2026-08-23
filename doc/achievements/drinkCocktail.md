# drinkCocktail (dc.drinkCocktail)

> Source: `src/main/java/mods/defeatedcrow/common/AchievementRegister.java:1`
> Registry: `new Achievement("defeatedcrow.drinkCocktail", "dc.drinkCocktail", 7, -3, new ItemStack(DCsAppleMilk.cocktail), parent).registerStat()` (`AchievementRegister.java:58`)
> Parent: `getAlcohol`
> Display: `ItemStack(DCsAppleMilk.cocktail)` at (7,-3)
> Type: `特殊 (setSpecial)` / Independent: false (child)

## 概要
カクテル。特殊。

## 登録情報
- **クラス**: `net.minecraft.stats.Achievement`
- **ID**: `"defeatedcrow.drinkCocktail"` (stat) / `"dc.drinkCocktail"` (name)
- **位置**: `(7,-3)` グリッド
- **アイコン**: `new ItemStack(DCsAppleMilk.cocktail)`
- **親**: `getAlcohol` (依存 `registerStat()`)
- **特殊**: `setSpecial()` で光沢枠
- **ページ**: `AchievementRegister.DCachievementPage = new AchievementPage("Apple&Milk&Tea!", list)` (`AchievementRegister.java:376`)

## 移行 (1.12.2+)
| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `net.minecraft.stats.Achievement` + `AchievementPage` | **削除** → `Advancement` (`Advancement` JSON + `AdvancementProvider`) | 同左 + `AdvancementHolder` |
| `player.triggerAchievement(AchievementRegister.drinkCocktail)` | `AdvancementManager` + `ServerPlayer` の `AdvancementProgress.grantCriterion` / `CriterionTrigger` | 同左 |
| `AchievementList.openInventory` 等 | `Advancement` の `parent` に `minecraft:story/root` 等を指定 | 同左 |

移行例 (1.16+ JSON):
```json
{
  "display": { "icon": { "item": "defeatedcrow:teamaker_next" }, "title": { "translate": "advancement.defeatedcrow.drinkCocktail.title" }, "frame_type": "challenge" },
  "parent": "minecraft:story/root",
  "criteria": { "craft": { "trigger": "minecraft:inventory_changed", "conditions": { "items": [{ "items": ["defeatedcrow:teamaker_next"] }] } } }
}
```

## 関連ドキュメント
- [Achievement 一覧](../achievements.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
