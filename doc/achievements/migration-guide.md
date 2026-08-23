# Achievement 移行ガイド - 1.7.10 → 1.12.2 / 1.16.5 / 1.20.1

> 対象: `AppleMilkTea2` 全37 Achievement / `AchievementRegister.java:1`
> 前提: 1.7.10 `Achievement` / `AchievementPage` → 1.12 `Advancement` (1.12で Achievement 廃止、Advancement 導入) → **1.20.1は `AdvancementHolder` + `HolderLookup.Provider` + `AdvancementProvider` の完全datapack化**
> 最終更新: 2026-08-24（1.20.1ブラッシュアップ: 2026-08-24）  
> 関連: [ビルド移行ガイド](../build.md)

## 概要
Achievement は 1.12で完全に削除され、Advancement (JSON) に置換。37件全てを `data/defeatedcrow/advancements/*.json` に移行。**1.20.1では `Advancement` は `AdvancementHolder` にラップされ、`AdvancementProvider` は `HolderLookup.Provider` を受け取る `Consumer<AdvancementHolder>` に変更。`DisplayInfo` → `AdvancementDisplay`、`FrameType` → `AdvancementFrameType` にリネーム。旧 `LanguageRegistry` の `achievement.*` 言語キーは `advancements.*` に。**

> **ギャップ補足**（plan.md監査）: 旧DOCは `AdvancementProvider` を 1.16止まりで記載、Holder未記載。1.20.1では `AdvancementHolder`/`PackOutput`/`ExistingFileHelper`→`HolderLookup.Provider`/`CompletableFuture` の変更が必須。

## 登録の大局

| 1.7.10 | 1.12.2+ | 1.16.5+ | 1.20.1 | 参照 |
|---|---|---|---|---|
| `new Achievement("defeatedcrow.getTeaLeaves","dc.getTeaLeaves",0,1,new ItemStack(leafTea), AchievementList.openInventory).initIndependentStat().registerStat()` (`AchievementRegister.java:58`) | `Advancement.Builder.advancement().display(new DisplayInfo(new ItemStack(leafTea), Component.translatable("advancement.defeatedcrow.getTeaLeaves.title"), ..., FrameType.TASK, true, true, false)).parent(RootAdvancement).addCriterion("obtain", InventoryChangeTrigger.TriggerInstance.hasItems(leafTea)).save(consumer, "defeatedcrow:get_tea_leaves")` (AdvancementProvider) | 同左 + `AdvancementHolder` 導入前 | **`AdvancementHolder` + `Advancement.Builder.advancement().display(new AdvancementDisplay(new ItemStack(leafTea), Component.translatable("advancement.defeatedcrow.getTeaLeaves.title"), ..., AdvancementFrameType.TASK, true, true, false)).parent(holder.value()).addCriterion("has_leaf", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItems.LEAF_TEA.get()).build())).save(consumer, new ResourceLocation("defeatedcrow","get_tea_leaves"), existingFileHelper)` (Providerは `PackOutput` + `CompletableFuture<HolderLookup.Provider>`)** | `AchievementRegister.java:58` |
| `AchievementPage` (`new AchievementPage("Apple&Milk&Tea!", list)`) | `Advancement` の `parent` ツリーで代替。`Root` は `minecraft:story/root` | 同左 + `Advancement` は datapack `data/defeatedcrow/advancements/` に JSON | **同左** | `AchievementRegister.java:376` |
| `player.triggerAchievement(AchievementRegister.getTea)` (`CraftingEvent.java:1`) | `ServerPlayer` の `AdvancementProgress` → `Advancements.Advancement.getAdvancement(RL)` → `player.getAdvancements().award(advancement, "criterion")` | 同左だが `CriterionTrigger` 経由で自動付与も可 | **`ServerPlayer` の `getAdvancements().award(holder, "criterion")` + `AdvancementHolder` は `ServerAdvancementManager` から `get(new ResourceLocation("defeatedcrow","get_tea_leaves"))` で取得** | `event/CraftingEvent.java:1` |
| `lang/*.lang` (`achievement.dc.getTeaLeaves.name`) | `lang/*.json` (`"advancement.defeatedcrow.getTeaLeaves.title": "Tea Leaves"`) | 同左 | **同左** | `assets/defeatedcrow/lang/*.lang` |

## Advancement JSON 移行例

```java
// 1.7.10
getTeaLeaves = (new Achievement("defeatedcrow.getTeaLeaves","dc.getTeaLeaves",0,1,new ItemStack(DCsAppleMilk.leafTea,1,0), AchievementList.openInventory)).initIndependentStat().registerStat();

// 1.12+ (AdvancementProvider データ生成)
public class AMTAdvancementProvider extends AdvancementProvider {
  protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper helper){
    Advancement root = Advancement.Builder.advancement()
      .display(new DisplayInfo(new ItemStack(DCsAppleMilk.leafTea), Component.translatable("advancement.defeatedcrow.getTeaLeaves.title"), Component.translatable("advancement.defeatedcrow.getTeaLeaves.description"), new ResourceLocation("defeatedcrow:textures/gui/advancement_bg.png"), FrameType.TASK, true, true, false))
      .addCriterion("has_leaf", InventoryChangeTrigger.TriggerInstance.hasItems(DCsAppleMilk.leafTea))
      .save(consumer, "defeatedcrow:get_tea_leaves");
    Advancement makeTeaLeaves = Advancement.Builder.advancement().parent(root)
      .display(new DisplayInfo(...))
      .addCriterion("has_food_tea", InventoryChangeTrigger.TriggerInstance.hasItems(DCsAppleMilk.foodTea))
      .save(consumer, "defeatedcrow:make_tea_leaves");
  }
}
```

### 1.20.1 Provider（Holder対応）

```java
// 1.20.1
public class AMTAdvancementProvider extends AdvancementProvider {
  public AMTAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper helper){
    super(output, registries, helper, List.of(new AMTAdvancements()));
  }
  public static class AMTAdvancements implements AdvancementProvider.AdvancementGenerator {
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper helper){
      AdvancementHolder root = Advancement.Builder.advancement()
        .display(new AdvancementDisplay(new ItemStack(ModItems.LEAF_TEA.get()),
          Component.translatable("advancement.defeatedcrow.getTeaLeaves.title"),
          Component.translatable("advancement.defeatedcrow.getTeaLeaves.description"),
          new ResourceLocation("defeatedcrow","textures/gui/advancement_bg.png"),
          AdvancementFrameType.TASK, true, true, false))
        .addCriterion("has_leaf", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItems.LEAF_TEA.get()).build()))
        .save(saver, new ResourceLocation("defeatedcrow","get_tea_leaves"), helper);
      AdvancementHolder makeTeaLeaves = Advancement.Builder.advancement().parent(root.value())
        .display(new AdvancementDisplay(new ItemStack(ModItems.FOOD_TEA.get()),
          Component.translatable("advancement.defeatedcrow.makeTeaLeaves.title"),
          Component.translatable("advancement.defeatedcrow.makeTeaLeaves.description"),
          new ResourceLocation("defeatedcrow","textures/gui/advancement_bg.png"),
          AdvancementFrameType.TASK, true, true, false))
        .addCriterion("has_food_tea", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItems.FOOD_TEA.get()).build()))
        .save(saver, new ResourceLocation("defeatedcrow","make_tea_leaves"), helper);
    }
  }
}
// runData で生成: data/defeatedcrow/advancements/get_tea_leaves.json
```

### JSON手書き例 (`data/defeatedcrow/advancements/get_tea_leaves.json`) — 1.20.1でも同型

```json
{
  "display": {
    "icon": { "item": "defeatedcrow:leaf_tea" },
    "title": { "translate": "advancement.defeatedcrow.getTeaLeaves.title" },
    "description": { "translate": "advancement.defeatedcrow.getTeaLeaves.description" },
    "frame": "task",
    "show_toast": true,
    "announce_to_chat": true,
    "hidden": false,
    "background": "defeatedcrow:textures/gui/advancement_bg.png"
  },
  "parent": "minecraft:story/root",
  "criteria": {
    "has_leaf_tea": {
      "trigger": "minecraft:inventory_changed",
      "conditions": { "items": [{ "items": ["defeatedcrow:leaf_tea"] }] }
    }
  }
}
```

| 1.7.10 Achievement位置 | 1.12+ Advancement `parent` | 1.20.1も同左 |
|---|---|---|
| `AchievementList.openInventory` (独立) | `minecraft:story/root` | 同左 |
| `AchievementList.buildWorkBench` | `minecraft:story/root` または `minecraft:story/mine_stone` | 同左 |
| `craftPan` (親が `buildFurnace`) | `defeatedcrow:craft_pan` の親を `defeatedcrow:make_tea_leaves` にする等、ツリー維持 | 同左 |

## 特殊枠移行

| 1.7.10 | 1.12+ | 1.20.1 |
|---|---|---|
| `setSpecial()` (光沢) | `display.frame_type: "challenge"` (`task`/`goal`/`challenge`) | `AdvancementFrameType.CHALLENGE` (`TASK`/`GOAL`/`CHALLENGE`) |
| `initIndependentStat()` | `Advancement` の `parent` なし (`Root` は `minecraft:story/root`) | 同左 |

## 言語移行

| 1.7.10 (`*.lang`) | 1.12+ (`*.json`) | 1.20.1も同左 |
|---|---|---|
| `achievement.dc.getTeaLeaves.name=Tea Leaves` | `"advancement.defeatedcrow.getTeaLeaves.title": "Tea Leaves"` | 同左 |
| `achievement.dc.getTeaLeaves.desc=Get tea leaves` | `"advancement.defeatedcrow.getTeaLeaves.description": "Get tea leaves"` | 同左 |

## Datagen 登録（1.20.1）

```java
// Mod DataGenerators
@SubscribeEvent
public static void gatherData(GatherDataEvent e){
  DataGenerator gen = e.getGenerator();
  PackOutput output = gen.getPackOutput();
  CompletableFuture<HolderLookup.Provider> lookup = e.getLookupProvider();
  ExistingFileHelper helper = e.getExistingFileHelper();
  gen.addProvider(e.includeServer(), new AMTAdvancementProvider(output, lookup, helper));
  gen.addProvider(e.includeServer(), new AdvancementProvider(output, lookup, helper, List.of(new AMTAdvancements()))); // Forgeのヘルパー
}
```

## 検証手順
1. `grep -r "Achievement"` src/ → `Advancement` / `AdvancementHolder` 置換確認。
2. `gradlew runData` で `AdvancementProvider` が `data/defeatedcrow/advancements/*.json` を生成するか確認（`build/generated`）。
3. `lang/*.json` の `advancement.defeatedcrow.*` キー確認。
4. ゲーム内で `/advancement grant @p only defeatedcrow:get_tea_leaves` が動作するか検証（1.20.1は `minecraft:story/root` からの親ツリーが表示されるかも確認）。

## 関連
- [Achievement 一覧](../achievements.md) / [個別ページ索引](./README.md)
- [Event 一覧](../events.md) - `CraftingEvent` の `triggerAchievement` は `AdvancementHolder` の `award` に
- `src/main/java/mods/defeatedcrow/common/AchievementRegister.java:1`
- [Minecraft Wiki - Advancement](https://minecraft.wiki/w/Advancement)
- [ビルド移行ガイド](../build.md) - `runData` タスクで `PackOutput` が生成される前提
