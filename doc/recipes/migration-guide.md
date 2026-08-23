# Recipe 移行ガイド - 1.7.10 → 1.12.2 / 1.16.5 / 1.20.1

> 対象: 全11 RecipeType / `RecipeRegisterManager.java:1` / `RegisterMakerRecipe.java:1`
> 最終更新: 2026-08-24（1.20.1ブラッシュアップ: 2026-08-24）  
> 関連: [ビルド移行ガイド](../build.md)
> 前提: 1.7.10 独自Registry → 1.12 `IForgeRegistryEntry` → 1.16 `RecipeType` + `RecipeSerializer` (datapack)

## 概要
独自レシピシステムの datapack 化。`RecipeManager` + `RecipeType` + `RecipeSerializer<I>` への移行が中心。OreDictionary は `TagKey` に置換。

## 登録の大局

| 1.7.10 | 1.12.2+ | 1.16.5+ | 参照 |
|---|---|---|---|
| `RecipeRegisterManager.teaRecipe.register(new ItemStack(input), new ItemStack(output))` (`RegisterMakerRecipe.java:21`) | `RegistryEvent.Register<IRecipe>` / `IForgeRegistryEntry` で `GameRegistry.register(new RecipeTea(...).setRegistryName(RL))` | `RecipeType<TeaRecipe> TEA = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation("defeatedcrow","tea"), new RecipeType<>(){})` + `DeferredRegister<RecipeSerializer<?>> SERIALIZERS` + `Recipe<Container>` (`getIngredients`, `getResultItem`, `matches`, `assemble`) + データパック `data/defeatedcrow/recipes/tea/*.json` | `recipe/TeaRecipeRegister.java:1` / `api/recipe/ITeaRecipe.java:1` |
| `Object[]` で `String` (oreDict) / `ItemStack` / `Block` を混在 (`Processor`) | 維持 (`OreDictionary` は 1.12まで) | `Ingredient` (`Ingredient.of(TagKey, ItemStack)`) / `TagKey<Item>` (`Tags.Items`, `forge:xxx`) | `recipe/ProcessorRecipeRegister.java:1` |
| `FluidStack` (`Evaporator/Brewing`) | `FluidStack` 維持 | `FluidStack` + `FluidIngredient` (JEI表示用) | `recipe/EvaporatorRecipeRegister.java:1` |
| `BrewingRecipe.instance.registerRecipe(young, aged)` | 維持 | `RecipeType<BrewingRecipe>` | `recipe/BrewingRecipe.java:1` |
| `NEI handler` (`plugin/nei/TeaRecipeHandler.java:1` → `API.registerRecipeHandler`) | `JEI` (`mezz.jei.api.JEIPlugin` → `IRecipeCategory` + `IRecipeTransferHandler`) | `JEI` (`RecipeType` 連携) / `REI` | `plugin/nei/*` |

## クラス移行詳細

### 1.7.10 独自クラス
```java
public class TeaRecipeRegister implements ITeaRecipeRegister {
  List<ITeaRecipe> recipes = new ArrayList<>();
  public void register(ItemStack input, ItemStack output, String tex){ recipes.add(new TeaRecipe(input, output, tex)); }
  public ITeaRecipe getRecipe(ItemStack input){ for(ITeaRecipe r: recipes) if(r.matches(input)) return r; return null; }
}
```

### 1.16.5 RecipeType
```java
public class TeaRecipe implements Recipe<Container> {
  final Ingredient input; final ItemStack output; final ResourceLocation tex;
  public boolean matches(Container inv, Level level){ return input.test(inv.getItem(0)); }
  public ItemStack assemble(Container inv){ return output.copy(); }
  public ResourceLocation getId(){ return id; }
  public RecipeSerializer<?> getSerializer(){ return ModRecipes.TEA_SERIALIZER.get(); }
  public RecipeType<?> getType(){ return ModRecipes.TEA_TYPE.get(); }
}
public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "defeatedcrow");
public static final RegistryObject<RecipeSerializer<TeaRecipe>> TEA_SERIALIZER = SERIALIZERS.register("tea", ()-> new SimpleRecipeSerializer<>(TeaRecipe::new));
public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, "defeatedcrow");
public static final RegistryObject<RecipeType<TeaRecipe>> TEA_TYPE = TYPES.register("tea", ()-> new RecipeType<>(){});
```

### JSON (`data/defeatedcrow/recipes/tea/green_tea.json`)
```json
{
  "type": "defeatedcrow:tea",
  "ingredient": { "item": "defeatedcrow:food_tea", "nbt": "{Damage:0}" },
  "result": { "item": "defeatedcrow:filled_cup", "count": 1, "nbt": "{Damage:4}" },
  "textures": ["defeatedcrow:textures/blocks/contents_greentea.png", "defeatedcrow:textures/blocks/contents_greentea_milk.png"],
  "canMilk": true
}
```

## OreDictionary → TagKey

| 1.7.10 | 1.16.5+ |
|---|---|
| `"cropApple"` / `"dustWood"` (`String` in `Object[]`) | `TagKey<Item> CROP_APPLE = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge","crops/apple"))` / `Ingredient.of(CROP_APPLE)` |
| `OreDictionary.registerOre("cropApple", new ItemStack(Items.apple))` (`RegisterOreHandler.java:1`) | `TagsProvider` (datagen) + `data/forge/tags/items/crops/apple.json` |
| `LoadOreDicHandler` | 削除、Tagで代替 |

## NEI → JEI

| 1.7.10 | 1.12.2+ |
|---|---|
| `codechicken.nei.api.API.registerRecipeHandler(new TeaRecipeHandler())` (`plugin/nei/LoadNEIPlugin.java:1`) | `JEIPlugin` (`@JeiPlugin` → `registerCategories` → `IRecipeCategory<TeaRecipe>` + `registerRecipes` → `recipeManager.getAllRecipesFor(TEA_TYPE.get())`) |
| `TemplateRecipeHandler` + `CachedRecipe` | `IRecipeCategory` + `IDrawable` + `IRecipeLayout` |
| `CraftGuide` (`uristqwerty.CraftGuide.api`) | `JEI` に統一 (CraftGuideは1.7で停止) |


---

## 1.20.1 追補

- **RecipeType / RecipeSerializer は1.20.1でも維持**（1.16→1.20で大きな変更なし）。`RecipeType<TeaRecipe>` + `RecipeSerializer<TeaRecipe>` + `Ingredient` + `TagKey` の構成は `1.16.5` と同一。
- **OreDictionary → TagKey** は 1.20.1で完全化。`RecipeProvider` の `Ingredient.of(TagKey)` + `TagKey.create(Registries.ITEM, new ResourceLocation("forge","crops/apple"))` で記述。`LoadOreDicHandler` は削除。
- **Datapack**: `data/defeatedcrow/recipes/tea/*.json` の `type: "defeatedcrow:tea"` は維持。`Result` の `ItemStack` のNBITは 1.20.1ではまだ `nbt` フィールドで有効（1.20.5+は `components` に移行するが1.20.1はNBIT）。
- **JEI連携**: `IRecipeCategory` + `RecipeType` は1.20.1のJEI 15.xでも同一。`RecipeManager.getAllRecipesFor(TYPE.get())` で取得。
- **Builder**: `SimpleRecipeSerializer` → `RecipeSerializer` の `Codec` 対応が1.19.3で追加されたが、Forge 47では `SimpleRecipeSerializer` も `Codec`/`StreamCodec` 両対応の `RecipeSerializer` 実装で動作。`MapCodec` への移行は任意。

### 検証 1.20.1

- `grep -r "OreDictionary"` → `TagKey` 置換確認
- `gradlew runData` で `RecipeProvider` が `data/defeatedcrow/recipes/*.json` を生成するか確認（`PackOutput` + `HolderLookup.Provider`）


## 検証手順
1. `gradlew runData` で `RecipeProvider` が `data/defeatedcrow/recipes/*.json` を生成するか確認。
2. `grep -r "OreDictionary"` → `TagKey` 置換確認。
3. `grep -r "NEI"` → `JEI` 置換確認。
4. ゲーム内で `/reload` + JEIでレシピ表示確認。

## 関連
- [Recipe 一覧](../recipes.md) / [個別ページ索引](./README.md)
- [Block 一覧](../blocks.md) - 機器Block
- [API 一覧](../api.md) - `api/recipe/*`
- `src/main/java/mods/defeatedcrow/recipe/RegisterMakerRecipe.java:1`
- `src/main/java/mods/defeatedcrow/api/recipe/RecipeRegisterManager.java:1`
