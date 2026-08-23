# API リファレンス

> 公開API: `src/main/java/mods/defeatedcrow/api/**` (9パッケージ, 40+クラス)
> 内部API: `common/tile` / `common/block` / `recipe` の `implements` 側は `api` を実装
> 参照: `src/main/java/mods/defeatedcrow/api/package-info.java:1` 各パッケージ

## 概要
外部MODが AppleMilkTea2 の機器・レシピ・チャージ・チャーム・植物・ポーションに連携するための公開API。`RecipeRegisterManager` / `ChargeItemManager` / `AMTPotionManager` が中心。`ICompressedItem` / `IPlantable` 等の汎用インターフェースも提供。

1.7.10→1.16移行では `Capability` / `RecipeType` / `TagKey` への置換が推奨だが、API自体は維持可能（`BlockPos`/`Level` 化のみ）。

## パッケージ一覧

| パッケージ | 主なAPI | 実装クラス例 | 個別ページ | 説明 |
|---|---|---|---|---|
| `api` | `ItemAPI` / `ICompressedItem` | `BlockWoodBox`, `VegiBag` | [→](./api/ItemAPI.md) / [→](./api/ICompressedItem.md) | 汎用。Item検索/圧縮 |
| `api.appliance` | `ITeaMaker` / `IProcessorRecipeTool` / `IJawPlate` / `IProcessorPanel` / `SoupType` | `TileMakerNext`, `TileProcessor` | [→](./api/Appliance.md) | 調理機器 |
| `api.charge` | `IChargeItem` / `IChargeableMachine` / `IChargeGenerator` / `IChargeItemRegister` / `ChargeItemManager` | `ItemBattery`, `TileChargerBase` | [→](./api/Charge.md) | チャージ |
| `api.charm` | `IIncenseEffect` / `EffectType` | `ItemIncense*` 11種 | [→](./api/Charm.md) | お香・チャーム |
| `api.edibles` | `IEdibleItem` / `EdibleItem` / `EdibleItemBlock` | `PlaceableFoods` | [→](./api/Edibles.md) | 可食Entity |
| `api.energy` | `IBattery` / `BatteryItemBase` | `ItemBattery` (旧) | [→](./api/Energy.md) | 旧電池 (非推奨) |
| `api.events` | `AMTBlockRightClickEvent` / `TeamakerRightClickEvent` / `EatEdiblesEvent` / `KnifeCutEvent` / `ShootingGunEvent` / `UseSlagEvent` | 各Block/Item | [→](./api/Events.md) | APIイベント |
| `api.plants` | `IRightClickHarvestable` / `PlantsClickEvent` | `BlockTeaTree`, `Yuzu` | [→](./api/Plants.md) | 植物 |
| `api.potion` | `AMTPotionManager` / `IPotionGetter` / `PotionBaseAMT` / `PotionImmunityBase` / `PotionLivingBase` / `PotionReflexBase` | `Potion*` 10種 | [→](./api/Potion.md) | ポーション |
| `api.recipe` | `ITeaRecipe` / `IIceRecipe` / `IPanRecipe` / `IPlateRecipe` / `IProcessorRecipe` / `IEvaporatorRecipe` / `IBrewingRecipe` / `IFondueRecipe` / `IChargeIce` / `RecipeRegisterManager` | `*RecipeRegister` | [→](./api/Recipe.md) | レシピ |

## 詳細

### `ItemAPI` (`api/ItemAPI.java:54`)
```java
@Deprecated
public static ItemStack getItem(String fieldName, int damage){ // reflectionで DCsAppleMilk.field を検索
  Field f = DCsAppleMilk.class.getField(fieldName);
  Item item = (Item)f.get(null);
  return new ItemStack(item,1,damage);
}
```
- **用途**: レシピ・他MOD連携で `leafTea` 等を文字列で取得。`@Deprecated` で `GameRegistry.findItem` 推奨。
- **移行**: `ForgeRegistries.ITEMS.getValue(new ResourceLocation("defeatedcrow","leaf_tea"))` / `RegistryObject` へ。

### `ICompressedItem` (`api/ICompressedItem.java:1`)
```java
public interface ICompressedItem {
  ItemStack getDisassembledItem(ItemStack compressed); // 圧縮→展開
}
```
- **実装**: `BlockWoodBox` / `ItemVegiBag` 等の圧縮収納。右クリックで展開。

### `ITeaMaker` (`api/appliance/ITeaMaker.java:1`)
```java
public interface ITeaMaker {
  ITeaRecipe getRecipe(); void setRecipe(ITeaRecipe recipe);
  ItemStack getItemStack(); void setItemStack(ItemStack stack);
  String getTexture(); String getMilkTexture();
}
```
- **実装**: `TileMakerNext` が `ITeaMaker` で `RecipeRegisterManager.teaRecipe` と連携。

### `IIncenseEffect` (`api/charm/IIncenseEffect.java:1`)
```java
public interface IIncenseEffect {
  EffectType getEffectType(ItemStack incense, EntityLivingBase entity);
  boolean formEffect(World world, int x,int y,int z, EntityLivingBase entity, ItemStack incense);
  int effectAreaRange(); int effectTime();
  IIcon particleIcon(); int particleColor(int meta);
}
```
- **実装**: `ItemIncenseApple` 等11種。`TileIncenseBase` が `formEffect()` を tick呼出。
- **移行**: `World,int x,y,z,EntityLivingBase` → `Level,BlockPos,LivingEntity`。

### `ChargeItemManager` (`api/charge/ChargeItemManager.java:1`)
```java
public class ChargeItemManager {
  public static final IChargeItemRegister chargeItem = new ChargeItemRegister();
  public static IChargeItemRegister getInstance(){ return chargeItem; }
}
public interface IChargeItem { int getChargeAmount(ItemStack stack); void charge(ItemStack stack, int amount); }
```
- **実装**: `ItemBattery` / `ItemYuzuGatling` 等。`TileChargerBase` が `charge`/`discharge`。

### `RecipeRegisterManager` (`api/recipe/RecipeRegisterManager.java:1`)
```java
public class RecipeRegisterManager {
  public static final ITeaRecipeRegister teaRecipe = new TeaRecipeRegister();
  public static final IIceRecipeRegister iceRecipe = new IceRecipeRegister();
  // + pan, plate, processor, evaporator, brewing, fondue, choco
}
```
- **用途**: 全レシピの登録・取得の中央集約。

## 移行サマリ

| API | 1.7.10維持 | 1.16+推奨 |
|---|---|---|
| `ItemAPI` | `@Deprecated` で維持 | `RegistryObject` / `ForgeRegistries` |
| `ICompressedItem` | 維持 | `RecipeType` (`DecompressionRecipe`) |
| `ITeaMaker` 等 | 維持だが `BlockPos` 化 | 維持 + `BlockEntity` 化 |
| `ICharge*` | 維持 | `CapabilityEnergy` (`IEnergyStorage`) |
| `IIncenseEffect` | 維持 | `Capability` + `MobEffect` |
| `IEdibleItem` | 維持 | `FoodProperties` + `MobEffectInstance` |
| `IBattery` | 非推奨 | `CapabilityEnergy` |
| `api.events` | 維持 | `IEventBus` (`net.minecraftforge.eventbus.api.Event`) |
| `Potion` API | `Potion` | `MobEffect` |
| `Recipe` API | 独自Registry | `RecipeType` / `RecipeSerializer` |

## 関連
- [API 個別ページ索引](./api/README.md) - 11個別ページ
- [移行ガイド](./api/migration-guide.md)
- [Recipe 一覧](./recipes.md)
- [Event 一覧](./events.md)
- `src/main/java/mods/defeatedcrow/api/**`
