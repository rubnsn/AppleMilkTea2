# Plugin 移行ガイド - 1.7.10 → 1.12.2 / 1.16.5 / 1.20.1

> 対象: `plugin/**` 全30+クラス / `LoadModHandler.java:1`
> 最終更新: 2026-08-24（1.20.1ブラッシュアップ: 2026-08-24）  
> 関連: [ビルド移行ガイド](../build.md) / [概要](../overview.md)
> 前提: `Loader.isModLoaded` → `ModList.get().isLoaded` / `NEI` → `JEI` / `CoFH RF` → `ForgeEnergy`

## 概要
任意連携の移行。`Loader.isModLoaded` + try/catch パターンは `ModList` に置換。表示系は `NEI`/`CraftGuide` → `JEI` に統一。Energyは `ForgeEnergy` (`CapabilityEnergy`) に統合。

## 登録の大局

| 1.7.10 | 1.12.2+ | 1.16.5+ | 参照 |
|---|---|---|---|
| `Loader.isModLoaded("IC2")` (`cpw.mods.fml.common.Loader`) | `Loader.isModLoaded` 維持 (1.12) | `ModList.get().isLoaded("ic2")` (`net.minecraftforge.fml.ModList`) | `plugin/LoadModHandler.java:1` |
| `try{ new LoadIC2Plugin().load(); }catch(Exception e){}` | 同左 | 同左だが `IEventBus` の `enqueueWork` で登録 | 同上 |
| `codechicken.nei.api.API.registerRecipeHandler(new TeaRecipeHandler())` | **JEI**: `mezz.jei.api.JeiPlugin` → `IRecipeCategory` + `IRecipeRegistration` | 同左 + `RecipeType` 連携 | `plugin/nei/LoadNEIPlugin.java:1` |
| `uristqwerty.CraftGuide.api` | **削除** (CraftGuideは1.7で停止) → `JEI` に統合 | 同左 | `plugin/craftguide/*` |
| `cofh.api.energy.IEnergyHandler` (RF) | `cofh` 維持だが `ForgeEnergy` (`CapabilityEnergy`) に統合 | `CapabilityEnergy` (`IEnergyStorage`) | `plugin/cofh/*` |
| `ic2.api.energy.tile.IEnergySink/IEnergySource` (EU) | 維持 (IC2 Exp APIは 1.12で `ic2.api.energy` 維持) | IC2 Classicは `EnergyNet` 変更、1.16未対応で削除推奨 | `plugin/IC2/*` |
| `forestry.api.farming.IFarmable` (`FarmableAMT`) | 維持 (Forestry 1.12は `IFarmable` 維持) | Forestry 1.16未対応で削除 | `plugin/ffm/*` |
| `thaumcraft.api.ThaumcraftApi` | Thaumcraft 6 (1.12) で `ThaumcraftApi` 変更 | Thaumcraft 1.16未対応 | `plugin/LoadThaumcraftPlugin.java:1` |
| `GameRegistry.findItem("mod","item")` (`ItemAPI`等) | `ForgeRegistries.ITEMS.getValue(RL)` | `BuiltInRegistries.ITEM` | `api/ItemAPI.java:1` |
| `OreDictionary.registerOre` (`LoadOreDicHandler`) | `OreDictionary` 維持 (1.12) | `TagKey<Item>` (`Tags` / `forge:xxx`) | `handler/RegisterOreHandler.java:1` |

## JEI移行詳細

```java
// 1.7.10 NEI (LoadNEIPlugin.java:1)
API.registerRecipeHandler(new TeaRecipeHandler());
API.registerUsageHandler(new TeaRecipeHandler());

// 1.12+ JEI
@JeiPlugin
public class JEIPluginAMT implements IModPlugin {
  public void registerCategories(IRecipeCategoryRegistration reg){
    reg.addRecipeCategories(new TeaCategory(reg.getJeiHelpers().getGuiHelper()));
  }
  public void registerRecipes(IRecipeRegistration reg){
    reg.addRecipes(RecipeRegisterManager.teaRecipe.getRecipeList(), TEA_TYPE);
  }
}
class TeaCategory implements IRecipeCategory<ITeaRecipe> {
  public Component getTitle(){ return Component.translatable("category.defeatedcrow.tea"); }
  public IDrawable getBackground(){ return helper.createDrawable(new ResourceLocation("defeatedcrow","textures/gui/tea.png"),0,0,176,80); }
  public void setRecipe(IRecipeLayout layout, ITeaRecipe recipe, IIngredients ing){ ... }
}
```

## Energy移行 (ForgeEnergy)

```java
// 1.7.10 CoFH RF
RFItemHandler implements IEnergyHandler { int receiveEnergy(...); }

// 1.16+ ForgeEnergy
CapabilityEnergy.ENERGY handler = new EnergyStorage(capacity);
LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> handler);
public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side){
  if(cap==CapabilityEnergy.ENERGY) return energy.cast();
  return super.getCapability(cap, side);
}
```

## Thaumcraft/Forestry 注意

| MOD | 1.7.10 | 1.12 | 1.16+ |
|---|---|---|---|
| Thaumcraft | 4.x | 6.x (API全面変更) | 未移植、削除推奨 |
| Forestry | 4.x | 5.x (維持) | 未移植 (1.16未対応) |
| BuildCraft | 6.x | 7.x (維持) | 8.x (維持) |

`LoadThaumcraftPlugin` / `LoadForestryPlugin` は 1.16+で `ModList.get().isLoaded` が falseを返すため自動スキップされるようにしておくか、該当Plugin自体を `mods.toml` の `dependencies` から除外。


---

## 1.20.1対応分類（plan.md「連携分類」準拠の正本）

> ユーザー予想「大半は無くなってる=ほぼオミット」を概ね裏付け。ソース側は全連携が `postInit` の `Loader.isModLoaded` → 1.20.1 `ModList.get().isLoaded` で条件分岐しておりハード依存ではない（try/catchで抑止）。Gradle側は対応しないものは削除すればビルドが通る。

### Omit確定・或いは相当（1.20.1対応版なし → Gradleから削除、pluginクラス削除）

| 旧連携 | 最終更新 | 1.20.1対応 | 改修後 |
|---|---|---|---|
| Thaumcraft (ThaumcraftApi `4.x`→`6.x`) | CF最終 2018, 1.12.2終了 | **なし** (Thaumcraft6止まり) | `plugin/LoadThaumcraftPlugin` 削除、`ThaumcraftApi` 参照削除 |
| NEI / CodeChickenCore / CodeChickenLib | 1.7.10専用API | **なし** | `plugin/nei/*` を JEI置換 or 削除。1.20.1標準はJEI |
| CraftGuide (`uristqwerty.CraftGuide`) | 非対応 | **なし** | `plugin/craftguide/*` 削除、JEIに統合 |
| MCEconomy2 / SextiarySector2 | 非対応 | **なし** | `plugin/mce/*`, `plugin/SSector/*` 削除 |
| PPC(TofuCraft/Bamboo以外)/Wa/Gummi/Growthcraft/MapleTree/SugiForest/DartCraft/ExtraTrees/EnchantChanger/ExBucket | 日本語ローカルMod中心、1.20.1非対応 or 入手困難 | **なし/入手困難** | `LoadModHandler` の文字列lookupはフェッチ不可になるため大半削除 |
| AppleCore (`func_151686_a` 1.7.10 SRG) | 1.20.1ではForge内包 | **なし** (`AppleCore`名義では存在しない) | `plugin/LoadAppleCorePlugin` 削除 |
| CoFH RF (`cofh.api.energy.IEnergyHandler`, `@Optional.Interface(cpw.mods.fml)`) | 1.7.10専用 | **なし** (Forge Energyへ移行) | RF系は削除、capability `IEnergyStorage` へ全面書換 |

※ **Bambooのみ保留**: 1.20.1版が存在するため `plugin/LoadBambooPlugin` は破棄せず保留。`CookingRegistory`/`GrindRegistory` 等のAPI差分を後追い検証し、座標が判明したら `curse.maven` / `modrinth` で再追加。

### 1.20.1対応あり or 後継あり → 置換候補（Gradleで1.20.1座標へ置換、要検証）

| 連携 | 1.20.1対応 | 置換先 / 注意 |
|---|---|---|
| IC2 (`ic2.api.energy.tile.IEnergySink/IEnergySource`, `BasicSink/BasicSource/IElectricItem`) | **あり** (IC2 classic 最新も更新中) | EU系APIは1.20.1で継続。要検証だが `compileOnly fg.deobf("net.industrial-craft:industrialcraft-2:2.8.xx")` で維持 |
| CoFH / Thermal Series (TE4) | 1.20.1では Forge Energy(`IEnergyStorage`)へ移行 | `cofh.api.energy` は消滅、RFは全面書換。TE4は `imc` のみでクラス参照なし、1.20.1のThermal Seriesへ置換候補 |
| Forestry (`forestry.api.farming.IFarmable`等) | 1.20.1 Forge版で半ば休眠だが **存在** | 要検証。`curse.maven:forestry-...` の1.20 forge版が存在すれば置換、なければ削除 |
| BuildCraft (fuel登録) | **あり** (コミュニティ維持) | 燃料登録のみで影響小、`LoadBCPlugin` は維持候補 |
| Railcraft | 1.7終了後 `Railcraft Reborn`系後継 | 要検証/オミット寄り、RebornのAPI差分要確認 |
| Biomes O' Plenty | **あり** (1.20.1現役) | 置換候補 `curse.maven:biomes-o-plenty-220318:xxxx` |
| JEI (NEI→JEI) | **あり** (1.20.1現役更新中) | NEI表示連携の置換先。`@JeiPlugin` + `IRecipeCategory` + `RecipeType` 連携。必須ではない |

### エネルギー系移行ポイント

- **RF** (`cofh.api.energy.IEnergyHandler` / `IEnergyContainerItem` / `@Optional.Interface` + `cpw.mods.fml`) → **全て Forge Energy capability(`IEnergyStorage`) + capability lookupへ書換必須**。対象: `MachineBase.java` / `TileChargerDevice.java` / `TileHandleEngine.java` 等。中枢は `MachineBase`/`TileChargerDevice`/`TileHandleEngine` の3ファイル
- **EU** (`BasicSink/BasicSource/ElectricItem.manager`) はアダプタ層 `plugin/IC2/*` に隔離済みで影響局所化
- **Gradle側の扱い**: `dependencies.gradle` の `compileOnly` 依存は削除。対応する場合のみ `curse.maven`/`modrinth`/`forge.maven` の1.20.1座標へ置換。ローカルjar(Bamboo/wa)は除去

### 1.20.1依存置換例（build.gradle抜粋）

```groovy
dependencies {
  minecraft 'net.minecraftforge:forge:1.20.1-47.3.0'
  compileOnly fg.deobf("mezz.jei:jei-1.20.1-common-api:15.3.0.4")
  runtimeOnly fg.deobf("mezz.jei:jei-1.20.1-forge:15.3.0.4")
}
```

### 検証 1.20.1

1. `grep -r "Loader.isModLoaded"` → `ModList.get().isLoaded` 置換
2. `grep -r "codechicken.nei"` → `mezz.jei` 置換または削除
3. `grep -r "cofh.api.energy"` → `CapabilityEnergy` / `IEnergyStorage` 置換
4. `grep -r "Thaumcraft"` → 0件（削除）を確認
5. `gradlew build` でビルド成功を確認


## 検証手順
1. `grep -r "Loader.isModLoaded"` → `ModList.get().isLoaded` 置換確認 (1.16+)。
2. `grep -r "codechicken.nei"` → `mezz.jei` 置換確認。
3. `grep -r "OreDictionary"` → `TagKey` 置換確認 (1.16+)。
4. `gradlew build` で `JEI` 未導入時の `ClassNotFound` を `try/catch` で抑止確認。

## 関連
- [Plugin 一覧](../plugins.md) / [個別ページ索引](./README.md)
- [Recipe 一覧](../recipes.md) - NEI/JEI
- [Item 一覧](../items.md) / [Block 一覧](../blocks.md) - OreDictionary
- `src/main/java/mods/defeatedcrow/plugin/LoadModHandler.java:1`
