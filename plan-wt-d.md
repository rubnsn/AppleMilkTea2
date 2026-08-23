# WT-D 計画書 — Recipe + Advancement 現代化 / 保守コスト最小

> 派生: `plan.md` 3-worktree計画の追補。WT-A/B/C merge後 (`dev` `20e8b6c` マージ時点) の残存 `recipe/*`15 + `AchievementRegister`37 を **WT-D (`feature/recipe-advancement`)** として分離。方針は `なるべく新コードに対応させ今後の保守コストを減らす`。
> 正本: `doc/build.md` / `doc/recipes/migration-guide.md` / `doc/achievements/migration-guide.md` / `doc/oredict-to-tagkey.md` / `doc/overview.md`

## 1. 背景と分離理由

* `plan.md:54` WT-C所有 `recipe/*` は旧 `OreDictionary` + `List<Recipe>` in-memoryのまま残存。`lint-migration.ps1 -Check wtc` は `IIcon/SimpleNetworkWrapper` 等14ルールはPASSだが `net.minecraft.init/OreDictionary/NBTTagCompound` は未検査で `recipe/OreCrushRecipe.java:5` 等が残存。
* `common/AchievementRegister.java:1` は `plan.md:54` で WT-A所有だったが 1.7.10 `Achievement` のまま、`WT-C` に寄せても `WT-A` に残しても漏れる。`doc/achievements/migration-guide.md:11` の `AdvancementHolder` 移行が未着手。
* **WT-D分離で保守コストを削る**: 旧APIのshimを残さず 1.20.1 Forge47正規 (`RecipeType`+`MapCodec` + `AdvancementHolder` + `TagKey`+`Holder` + `Datagen`) へ一本化。1.20.5 `DataComponents` は導入しない (NBT維持 `doc/items/migration-guide.md:220`) で将来差分を隔離。

## 2. 前提・決定事項（WT-D）

| 項目 | 決定 | 根拠 |
|---|---|---|
| ブランチ | `feature/recipe-advancement` / worktree `../AMT2-WT-D` | `dev` から派生 |
| 並列度 | 4-worktree (A/B/C/D) まで | 既存WT-A/B/Cは温存、WT-Dは独立 |
| JDK/Gradle/Mapping | 17 / 8.8 / mojmap `official` | `doc/build.md:12` |
| Recipe方式 | `RecipeType<T extends Recipe<?>>` + `RecipeSerializer<T> (MapCodec)` + `DeferredRegister` + `Ingredient(TagKey)` + datapack JSON | `doc/recipes/migration-guide.md:34,78` 1.20.1は `MapCodec` が将来 `StreamCodec` 移行時も差分最小 (`doc/recipes/migration-guide.md:84`) |
| Achievement方式 | `AdvancementHolder` + `AdvancementProvider(PackOutput, CompletableFuture<HolderLookup.Provider>, ExistingFileHelper)` + `ItemPredicate.Builder.item().of(...)` | `doc/achievements/migration-guide.md:43,52` |
| OreDict | `TagKey<Item>` (`forge`/`c` 両対応) `doc/oredict-to-tagkey.md` を正本 | WT-A生成 `data/c/tags` 354件を流用 |
| NBT | 1.20.1はNBT維持、DataComponents導入せず | `doc/items/migration-guide.md:220` |
| 検証 | 修正優先、各WTで `lint` + 最後に `dev` で `runData`+`build` | `plan.md:30` 踏襲 |

## 3. 所有とホットスポット

### 3.1 WT-D排他所有

| パッケージ | ファイル数 | 所有ファイル | 禁止編集 |
|---|---|---|---|
| **WT-D Recipes** | 15 + 新設 | `recipe/**/*` (Tea/Ice/Pan/Plate/Processor/AdvProcessor/Evaporator/Brewing/Fondue/Chocolate/Charge/Slag/OreCrush/RegisterManager/RegisteredRecipeGet/RegisterMaker) + `common/registry/ModRecipes.java`新設 + `common/datagen/AMTRecipeProvider.java`新設 + `common/DCsRecipeRegister.java` + `common/ReceivingIMCEvent.java` | HotSpot / `common/block/*` / `common/tile/*` / `client/*` |
| **WT-D Advancements** | 1 + 新設37 | `common/AchievementRegister.java` (削除→datagen) + `common/datagen/AMTAdvancementProvider.java`新設 + `data/defeatedcrow/advancements/*.json`37 + `assets/defeatedcrow/lang/*.json` advancementキー | 同上 |
| **WT-D Shared** | — | `common/registry/ModRecipes.java` は `// --- WT-D: RECIPES ---` セクションのみ追記。`common/registry/Mod*.java` 他は読取専用 | `api/*` は凍結 (WT0経由) |

> `CODEOWNERS` は `/src/main/java/mods/defeatedcrow/recipe/ @wt-d`, `/common/AchievementRegister.java @wt-d`, `/common/datagen/ @wt-d` を追加。`api/recipe/*` は `api/* @bootstrap` 凍結のため `ModRecipes` 側で委譲。

### 3.2 ホットスポット対策

* `common/registry/ModRecipes.java` は WT-D新設のため競合なし。`DCsAppleMilk.java:40` の `ModRecipes` 登録行は `Bootstrap` と調整 (1行追加のみ)。
* `data/defeatedcrow/recipes` / `advancements` は WT-D専用。WT-A/B/Cは `data/c/tags` 等と衝突しない。
* `api/recipe/ITeaRecipe` 等は `@Deprecated` 委譲に留め、実体は `recipe/TeaRecipe.java` 新クラスへ — `TileProcessor` 等の旧呼出 `RecipeRegisterManager.teaRecipe.getRecipe(stack)` を壊さない。

## 4. 新コード対応設計 — 保守コスト最小の選択

### 4-1 Recipe: 旧→新 対応表

| 1.7.10 (削除) | 1.20.1 (WT-D採用) | 保守性 |
|---|---|---|
| `OreDictionary.getOres("dustIron")` + `Object[]`混在 `recipe/ProcessorRecipeRegister.java:109` | `TagKey<Item> = TagKey.create(Registries.ITEM, RL("forge","dusts/iron"))` + `Ingredient.of(TagKey)` | Tag追加のみでコード不変 `doc/oredict-to-tagkey.md` |
| `List<ITeaRecipe>` + `isItemEqual(getItemDamage)` `recipe/TeaRecipeRegister.java:42` | `Recipe<Container>` + `DeferredRegister<RecipeSerializer<?>>` + `DeferredRegister<RecipeType<?>>` + `MapCodec` + JSON `data/defeatedcrow/recipes/tea/*.json` | 1.16→1.21破壊なし。`MapCodec`は1.19.3+ `StreamCodec` 移行時も差分最小 `doc/recipes/migration-guide.md:84` |
| `new Achievement(...AchievementList.openInventory)` | `AdvancementHolder` + `AdvancementDisplay` + `AdvancementFrameType` + `InventoryChangeTrigger.hasItems(ItemPredicate.Builder...)` | 1.12削除APIを完全除去 `doc/achievements/migration-guide.md:11` |
| `DCsRecipeRegister` `OreDictionary.registerOre` + `ShapedOreRecipe` `Recipe/OreCrushRecipe.java:211` | `RecipeProvider` datagen (`PackOutput`+`HolderLookup.Provider`) | 手動JSON重複排除、Forge `SimpleCookingRecipeBuilder` も利用可 |
| `GameRegistry.addSmelting` コメント (`OreCrushRecipe.java:209` TODO datapack) | `data/.../recipes/smelting/*.json` | 実行時 `RecipeManager` 一本 |

### 4-2 具体クラス設計

**`common/registry/ModRecipes.java` 新設**
```java
public class ModRecipes {
  public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "defeatedcrow");
  public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(ForgeRegistries.Keys.RECIPE_TYPES, "defeatedcrow");
  public static final RegistryObject<RecipeType<TeaRecipe>> TEA_TYPE = TYPES.register("tea", ()-> new RecipeType<>(){});
  public static final RegistryObject<RecipeSerializer<TeaRecipe>> TEA_SERIALIZER = SERIALIZERS.register("tea", ()-> new TeaRecipe.Serializer());
  // 計11種: tea/ice/pan/plate/processor/adv_processor/evaporator/brewing/fondue/chocolate/charge (+slagはprocessor内包)
}
```
`DCsAppleMilk.java:37` で `ModRecipes.TYPES.register(modBus); ModRecipes.SERIALIZERS.register(modBus);`

**`recipe/TeaRecipe.java` 等 (新)**
* `implements Recipe<Container>` (`matches`→`Ingredient.test`, `assemble`→`output.copy()`, `getSerializer/getType`, `canCraftInDimensions`)
* `Serializer implements RecipeSerializer<TeaRecipe>` は `MapCodec<TeaRecipe>` (`RecordCodecBuilder` + `Ingredient.CODEC` + `ItemStack.CODEC`) — 旧 `SimpleRecipeSerializer(ByteBuf)` は使わない
* `ProcessorRecipe` の `tier/secondaryChance` は `Codec.INT/FLOAT` で保持、旧 `Object[] processedInput` + `OreDictionary.itemMatches` は削除 (`recipe/ProcessorRecipeRegister.java:222` 旧ロジック)

**既存 `*RecipeRegister` の扱い**
* 削除せず **Deprecated委譲shim**化: 内部 `List` を廃し `level.getRecipeManager().getAllRecipesFor(ModRecipes.TEA_TYPE.get())` に委譲。`register()` は `AMTLogger.warn("use datapack")` に。既存 `Tile*` の `getRecipe` 呼出互換を維持。

**`recipe/OreCrushRecipe.java:34` `searchOreName()` は削除** — `tier1..5` + `OreDictionary` 6ループは `TagKey` を直接JSONに埋込み。不足は `SlagResultLoot` の `GlobalLootModifier` に移管 or 削除 (WT-D推奨: 削除、世界生成と結合低)。

**Datagen `common/datagen/AMTRecipeProvider.java`**
* `extends RecipeProvider` (`PackOutput`) で11種の `FinishedRecipe` を生成。`GatherDataEvent` で `gen.addProvider(includeServer, new AMTRecipeProvider(output))`。出力 `src/generated/resources/data/defeatedcrow/recipes/**/*.json` をcommit。

### 4-3 Advancement

* **削除** `common/AchievementRegister.java:1` 全体382行
* **新設 `common/datagen/AMTAdvancementProvider.java`**
  ```java
  public class AMTAdvancementProvider extends AdvancementProvider {
    public AMTAdvancementProvider(PackOutput out, CompletableFuture<HolderLookup.Provider> lookup, ExistingFileHelper h){
      super(out, lookup, h, List.of(new AMTAdvancements()));
    }
    public static class AMTAdvancements implements AdvancementProvider.AdvancementGenerator {
      public void generate(HolderLookup.Provider r, Consumer<AdvancementHolder> saver, ExistingFileHelper h){
        AdvancementHolder root = Advancement.Builder.advancement()
          .display(new AdvancementDisplay(new ItemStack(ModItems.LEAF_TEA.get()), Component.translatable("advancement.defeatedcrow.getTeaLeaves.title"), ..., AdvancementFrameType.TASK, true,true,false))
          .addCriterion("has_leaf", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItems.LEAF_TEA.get()).build()))
          .save(saver, RL("defeatedcrow","get_tea_leaves"), h);
        // ...37件、親は minecraft:story/root へ再配置 doc/achievements/migration-guide.md:99
      }
    }
  }
  ```
* 親ツリー再配置: `AchievementList.openInventory`→`minecraft:story/root`, `setSpecial()`→`AdvancementFrameType.CHALLENGE` `doc/achievements/migration-guide.md:109`
* `lang` は `assets/defeatedcrow/lang/en_us.json` に `"advancement.defeatedcrow.getTeaLeaves.title"` 移行 `doc/achievements/migration-guide.md:115`
* `CraftingEvent` の `player.triggerAchievement` は `ServerPlayer.getAdvancements().award(holder, "criterion")` に置換 `doc/achievements/migration-guide.md:19`

### 4-4 付帯クリーンアップ

* `common/ReceivingIMCEvent.java:6` `cpw.mods.fml` + `NBTTagCompound` → `InterModComms` + `CompoundTag` or 削除 (未使用)
* `scripts/lint-migration.ps1` に `wtd` チェック追加: `net\.minecraft\.init\.(Blocks|Items)` / `NBTTagCompound` / `Potion.potionTypes` / `OreDictionary` / 旧 `net\.minecraft\.item\.ItemStack` パス をmust-be-zero。
* `common/base/package-info.java:11` `cpw.mods.fml.common.API` 実import削除 (WT-Bと調整)。

## 5. 実行フロー（ワークツリー運用）

```powershell
# dev派生（済）からWT-D作成
git worktree add ../AMT2-WT-D feature/recipe-advancement -b feature/recipe-advancement
cd ../AMT2-WT-D; opencode --port 4099  # WT-D専用

# WT-Dでのopencodeプロンプト（コピペ用 — 詳細は .opencode/agent/wt-d.md）
あなたは WT-D (Recipe+Advancement) 担当。所有: recipe/**/*, AchievementRegister.java, common/registry/ModRecipes.java, common/datagen/**, data/defeatedcrow/recipes/**, data/defeatedcrow/advancements/** のみ。
禁止: DCsAppleMilk.java / MaterialRegister.java / CommonProxy / ClientProxy / common/block/** / common/tile/** / client/** / common/registry/ModBlocks.java 等Bootstrap/他WT所有。ModRecipes.javaは自分のセクションのみ。
DOC: doc/recipes/migration-guide.md の1.20.1追補 (RecipeType/MapCodec/TagKey/datapack) と doc/achievements/migration-guide.md の1.20.1追補 (AdvancementHolder/PackOutput/HolderLookup) に従い、OreDictionary/Achievement/S35Packet等を削除しTagKey/Holder/DeferredRegisterへ。1.20.1ではNBT維持、DataComponentsは1.20.5+なので導入しない。
検証: pwsh -File scripts/lint-migration.ps1 -Check wtd がPASS、grep -r "OreDictionary\|Achievement\|net.minecraft.init" が所有内で0になるまで。
```

**マージ順**
1. WT-Dは `dev` を `git rebase dev` で追従
2. 週1で `dev` に `git merge --no-ff feature/recipe-advancement` (共有 `ModRecipes` 等のみコンフリクト小)
3. 全修正後 `dev` で `pwsh -File scripts/lint-migration.ps1 -Check all` + `./gradlew runData` + `./gradlew build` 1回

## 6. 競合を起こさない物理的仕組み

* `CODEOWNERS` でWT-D所有外編集をCI拒否
* `common/registry/ModRecipes.java` はWT-D専用のため共有セクション競合なし。`DCsAppleMilk.java` への1行追加はBootstrapと事前合意。
* `api/*` 凍結 — 変更要ならWT-Dは `ModRecipes` 側で委譲、WT0にエスカレーション。
* `data/defeatedcrow/recipes` / `advancements` はWT-D専用、WT-A/B/Cの `data/c/tags` と衝突しない。

## 7. 成果物

* 本 `plan-wt-d.md` が正本（`plan.md` 追補、`doc/overview.md:4` の参照先に追加）
* `.opencode/agent/wt-d.md` + `opencode.json` wtdエントリ + `CODEOWNERS` wtd行
* `common/registry/ModRecipes.java` 雛形 + `common/datagen/AMTRecipeProvider.java`/`AMTAdvancementProvider.java` 雛形 + `scripts/lint-migration.ps1` wtd拡張
* `data/defeatedcrow/recipes/*.json` 11種 + `data/defeatedcrow/advancements/*.json` 37件 (生成物をcommit)

## 8. 検証

* `pwsh -File scripts/lint-migration.ps1 -Check wtd` PASS (新WT-Dチェック)、`all`でも `Achievement/OreDictionary/net.minecraft.init` 0
* `gradlew runData` で `data/defeatedcrow/recipes` + `advancements` 生成、`jar tf build/libs/*.jar | Select-String mods.toml|defeatedcrow` で含む確認 `doc/build.md:341`
* ゲーム内 JEIでTea/Ice等表示、`/advancement grant @p only defeatedcrow:get_tea_leaves` 動作、`minecraft:story/root` からのツリー表示 `doc/achievements/migration-guide.md:138`

---

## 9. 完了記録 — 2026-08-24 `dev` 統合

> `feature/recipe-advancement:df8734f` → `dev:74016c2` Merge → `b99feb3` BOM/連結修正 → `3fda022` WT-B統合時の `lint-migration.ps1` 競合解消 → `7569016` windows-31j修正 で `dev:7569016` に統合完了。

* **成果**: `ModRecipes.java:1` 11種 `DeferredRegister` / `AMTRecipeProvider.java:1` `AMTAdvancementProvider.java:1` / `AchievementRegister.java:1` 382行削除 / `DCsRecipeRegister.java:1` `ReceivingIMCEvent.java:1` 現代化 (`TagHelper`+`CompoundTag`) / `data/defeatedcrow/recipes`3件 + `advancements`2件 + `.gitkeep` 雛形。
* **検証**: `E:/AMT2-WT-D` で `lint wtd` PASS（`OreDictionary:0`/`net.minecraft.init:0`/`NBTTagCompound:0`/`Achievement:0`）。`dev:7569016` でも `wtd` PASS。`wta/wtc/bootstrap` PASS。`wtb` は `6b31c34` 由来の13件残存で `dev` では `wtd` には影響なし。
* **不具合対応**: `df8734f` 由来の `BOM (EF BB BF)` 13件 / `// ...        RecipeRegisterManager` 連結5件 / `OreCrushRecipe.java:211` `} else` / `AMTRecipeProvider.java:21` Javadoc `**/*.json` 内 `*/` / `—` (windows-31j不正) 92件を `b99feb3`/`7569016` で修正。`plan.md:8` にWT-D完了を反映。
* **残**: `lint all` では `WT-C` 未移行が支配的で `cpw:13`/`OreDictionary:5`/`NBTTagCompound:96` 等残存、`compileJava` は `WT-C` 180ファイル起因で `3311` エラー。WT-D単独では `runData`/`build` の完全成功は次フェーズ（WT-C統合後）。
