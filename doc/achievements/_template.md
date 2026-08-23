# AchievementName

> Source: `src/main/java/mods/defeatedcrow/common/AchievementRegister.java:1`
> Registry: `new Achievement("defeatedcrow.xxx", "dc.xxx", x, y, new ItemStack(...), parent).registerStat()` (`AchievementRegister.java:58`)
> Page: `AchievementRegister.DCachievementPage = new AchievementPage("Apple&Milk&Tea!", list)` (`AchievementRegister.java:376`)
> Parent: `parentAchievement` (`AchievementList.openInventory` / `buildWorkBench` / etc)

## 概要
[1-2文でAchievementの取得条件・特徴を記述。]

## 登録情報
- **ID**: `defeatedcrow.xxx` (stat) / `dc.xxx` (name)
- **位置**: `(x, y)` グリッド座標
- **アイコン**: `new ItemStack(DCsAppleMilk.xxx, 1, meta)`
- **親**: `parent` (`initIndependentStat()` で独立 / `registerStat()` で依存)
- **特殊**: `setSpecial()` 有無（光沢枠）
- **ページ**: `DCachievementPage`

## 付与タイミング
- **Trigger**: `player.triggerAchievement(AchievementRegister.xxx)` を `CraftingEvent` / `TileXxx.onBlockActivated` / `EntityXxx.onUpdate` 等で呼出
- **検索**: `grep -r "triggerAchievement" src/` で全呼出箇所列挙

## 1.7.10 → 1.12.2 移行チェックリスト
- [ ] `net.minecraft.stats.Achievement` → `Advancement` (JSON) に移行。`Achievement` / `AchievementPage` は 1.12で削除
- [ ] `AchievementList` → `Advancement` の `parent` (`minecraft:story/root` 等)
- [ ] `player.triggerAchievement` → `AdvancementProgress.grantCriterion` / `ServerPlayer` の `Advancement` 管理。`CriterionTrigger` (`InventoryChangeTrigger`, `RecipeUnlockedTrigger` 等) に置換
- [ ] `setSpecial()` → `display.frame_type: "challenge"` (`task`/`goal`/`challenge`)
- [ ] 言語キー: `achievement.dc.xxx.name` / `achievement.dc.xxx.desc` → `advancement.defeatedcrow.xxx.title` / `description` (`lang/*.json`)
- [ ] `AchievementPage` → `Advancement` の `data/defeatedcrow/advancements/*.json` (datapack) に分割。`AdvancementProvider` (datagen) 推奨

## 関連ドキュメント
- [Achievement 一覧](../achievements.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
- `src/main/java/mods/defeatedcrow/common/AchievementRegister.java:1`
