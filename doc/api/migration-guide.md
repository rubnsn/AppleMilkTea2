# API 移行ガイド - 1.7.10 → 1.12.2 / 1.16.5 / 1.20.1

> 対象: `src/main/java/mods/defeatedcrow/api/**`
> 最終更新: 2026-08-24（1.20.1ブラッシュアップ: 2026-08-24）  
> 関連: [ビルド移行ガイド](../build.md) 全40+クラス
> 前提: Forge 1.7.10 → 1.12/1.16 で `Capability` / `RecipeType` / `TagKey` への移行が推奨

## 概要
APIの移行は `BlockPos`/`Level` 化が中心。独自Energy/Recipe APIは `Capability`/`RecipeType` への置換が推奨されるが、1.12までは独自維持でも動作。

## パッケージ別移行表

| パッケージ | 1.7.10維持 | 1.12.2+ | 1.16.5+ 推奨 | 参照 |
|---|---|---|---|---|
| `api.ItemAPI` | `@Deprecated` で `GameRegistry.findItem` 推奨 (`ItemAPI.java:12`) | `ForgeRegistries.ITEMS.getValue(RL)` | `RegistryObject<Item>` / `BuiltInRegistries.ITEM` | `api/ItemAPI.java:54` |
| `api.ICompressedItem` | `getDisassembledItem` で圧縮展開 | 維持 | `RecipeType<DecompressionRecipe>` + `Ingredient` に移行検討 | `api/ICompressedItem.java:1` |
| `api.appliance` (`ITeaMaker`/`IJawPlate`/`IProcessorPanel`/`SoupType`) | `ITeaMaker` は `World,int x,y,z` を含む | `Level,BlockPos` に変更 | `BlockEntity` + `RecipeType` に分離 | `api/appliance/ITeaMaker.java:1` |
| `api.charge` (`IChargeItem`/`IChargeableMachine`/`IChargeGenerator`/`ChargeItemManager`) | 独自Energy (`ChargeItemManager.chargeItem.registerCharger`) | 維持だが `CapabilityEnergy` (`IEnergyStorage`) 併用推奨 | `CapabilityEnergy` (`EnergyStorage`) / `ForgeEnergy` に完全置換 | `api/charge/ChargeItemManager.java:1` |
| `api.charm` (`IIncenseEffect`/`EffectType`) | `formEffect(World,int x,y,z, EntityLivingBase, ItemStack)` | `Level,BlockPos,LivingEntity` に変更 | `MobEffect` + `Capability` + `Potion` → `MobEffectInstance` | `api/charm/IIncenseEffect.java:1` |
| `api.edibles` (`IEdibleItem`/`EdibleItem`) | `PlaceableFoods` 連携 | `FoodProperties` に統合 | `FoodProperties` + `MobEffectInstance` | `api/edibles/IEdibleItem.java:1` |
| `api.energy` (`IBattery`) | 旧API、既に `Charge` に統合済み | `CapabilityEnergy` に置換 | 削除 | `api/energy/IBattery.java:1` |
| `api.events` (`AMTBlockRightClickEvent`等6種) | `MinecraftForge.EVENT_BUS.post(new AMTBlockRightClickEvent(...))` | `net.minecraftforge.eventbus.api.Event` + `IEventBus` | 同左 (`@SubscribeEvent` → `IEventBus.addListener`) | `api/events/AMTBlockRightClickEvent.java:1` |
| `api.plants` (`IRightClickHarvestable`) | `IPlantable` + `onBlockActivated` で `PlantsClickEvent` | 維持だが `BlockState` 化 | `TagKey<Block>` + `LootTable` に移行 | `api/plants/IRightClickHarvestable.java:1` |
| `api.potion` (`AMTPotionManager`/`IPotionGetter`/`Potion*Base`) | `Potion.potionTypes[ID]` + `IPotionGetter` | `RegistryObject<MobEffect>` / `ForgeRegistries.MOB_EFFECTS` | `Holder<MobEffect>` / `Registry.MOB_EFFECT` | `api/potion/AMTPotionManager.java:1` |
| `api.recipe` (`ITeaRecipe`等 + `RecipeRegisterManager`) | 独自Registry (`List<IRecipe>`) | 維持 (1.12まで) | `RecipeType` / `RecipeSerializer` (JSON) + `TagKey` | `api/recipe/RecipeRegisterManager.java:1` |
| `handler.CoordListRegister` | `WorldSavedData` 的な自作 | 維持 | `Capability` / `WorldCapabilities` + `SavedData` | `handler/CoordListRegister.java:1` |

## 共通移行: World/BlockPos

```java
// 1.7.10
boolean formEffect(World world, int x,int y,int z, EntityLivingBase entity, ItemStack stack)

// 1.16.5
boolean formEffect(Level level, BlockPos pos, LivingEntity entity, ItemStack stack)
```

全APIイベントの `World,int x,y,z` は `Level,BlockPos` に置換。

## Capability 移行例 (Charge)

```java
// 1.7.10
ChargeItemManager.chargeItem.registerCharger(new ItemStack(DCsAppleMilk.batteryItem,1,0), null, 16);
((IChargeItem)stack.getItem()).charge(stack, amount);

// 1.16.5 (CapabilityEnergy)
stack.getCapability(CapabilityEnergy.ENERGY).ifPresent(energy -> energy.receiveEnergy(amount, false));
// 登録は CapabilityProvider で
public class BatteryItem extends Item implements ICapabilityProvider {
  public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side){
    if(cap==CapabilityEnergy.ENERGY) return energyStorage.cast();
    return super.getCapability(cap, side);
  }
}
```


---

## 1.20.1 追補（Holder / TagKey / BlockPos）

| パッケージ | 1.20.1変更点 |
|---|---|
| `api.ItemAPI` | `RegistryObject<Item>` + `Holder<Item>` + `BuiltInRegistries.ITEM`。`getItem("leafTea",1)` → `ModItems.LEAF_TEA.get()` |
| `api.charge` | `CapabilityEnergy` → `ForgeCapabilities.ENERGY` (`IEnergyStorage`) に完全置換。旧 `ChargeItemManager` は削除 |
| `api.potion` | `Holder<MobEffect>`。`AMTPotionManager` は `DeferredRegister<MobEffect>` のラッパーに |
| `api.recipe` | `RecipeType` + `RecipeSerializer` + `TagKey<Item>`。`Ingredient.of(TagKey)` |
| `handler.CoordListRegister` | `SavedData` + `Holder` + `LevelAccessor` |
| `api.events` | `Level` + `BlockPos` + `BlockState` + `Event.Result`。自作イベントは `Level`/`BlockPos` に |

- **Data Componentsとの区別**: 1.20.1はNBT維持、1.20.5+で `DataComponent` へ移行するため、APIで `ItemStack` の `tag` を直接触るコードは1.20.1ではそのまま正。

### 検証 1.20.1

- `grep -r "World, int x, int y, int z"` → `Level, BlockPos` 置換確認
- `grep -r "Holder<"` が `MobEffect` / `Biome` / `Item` で使用されているか確認


## 検証手順
1. `grep -r "World, int x, int y, int z"` → `BlockPos` 置換確認。
2. `grep -r "IChargeItem"` → `CapabilityEnergy` 置換確認 (推奨)。
3. `gradlew build` で `Capability` 未実装のコンパイルエラー解消確認。

## 関連
- [API 一覧](../api.md) / [個別ページ索引](./README.md)
- [Recipe 一覧](../recipes.md)
- [Event 一覧](../events.md)
- `src/main/java/mods/defeatedcrow/api/**`
