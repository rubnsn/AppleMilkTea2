# Config 移行ガイド - 1.7.10 → 1.12.2 / 1.16.5 / 1.20.1

> 対象: `DCsConfig.java:1`
> 最終更新: 2026-08-24（1.20.1ブラッシュアップ: 2026-08-24）  
> 関連: [ビルド移行ガイド](../build.md) (50+項目) / `DCsConfigCocktail.java:1` / `DCsConfiguration.java:1` (ASM)
> ファイル: `config/DCsAppleMilk.cfg` → `config/defeatedcrow-common.toml` (1.14+)
> 前提: Forge 1.7.10 `Configuration` (INICFG) → Forge 1.12 `Config` アノテーション → Forge 1.14+ `ForgeConfigSpec` (TOML)

## 概要
全Config項目の移行対応。`Configuration` → `ForgeConfigSpec` の全面刷新。`DCsConfig` の static 直管理から `ModConfig` + `ConfigValue` 管理へ。

## 登録の大局

| 1.7.10 | 1.12.2+ | 1.16.5+ | 参照 |
|---|---|---|---|
| `Configuration cfg = new Configuration(file)` (`DCsAppleMilk.java:477`) → `new DCsConfig().config(cfg)` → `cfg.get("category","key",default,"comment")` → `property.getInt()/getBoolean()` → `DCsConfig.field = value` (`DCsConfig.java:99-446`) | `ForgeConfigSpec.Builder` で `BUILDER.comment("comment").define("key", default)` / `defineInRange("key", default, min,max)` → `ForgeConfigSpec spec = BUILDER.build()` → `ModLoadingContext.registerConfig(ModConfig.Type.COMMON, spec, "defeatedcrow-common.toml")` | 同左 + `ModConfigEvent.Loading/Reloading` で値再読込 | `DCsConfig.java:99` / `DCsAppleMilk.java:476` |
| `config/DCsAppleMilk.cfg` (INI) | `config/defeatedcrow-common.toml` は 1.14からだが 1.12では `Config` アノテーション + `Configuration` のハイブリッドも可 | `config/defeatedcrow-common.toml` (TOML) + `config/defeatedcrow-client.toml` (render系分離推奨) |  |
| `DCsConfig.field` static 直参照 (`grep -r "DCsConfig."`) | `ConfigHolder.COMMON.field.get()` / `ConfigValue<Integer>` 経由 | 同左だが `Supplier` キャッシュで `if(Config.COMMON.alowSlime.get())` |  |
| `cfg.load()` / `cfg.save()` in `try{}` | `ForgeConfigSpec` は自動 load/save (`ModConfig` 管理) | 同左 | `DCsConfig.java:100` |
| `Property` (`net.minecraftforge.common.config.Property`) | `ForgeConfigSpec.ConfigValue<T>` / `IntValue` / `BooleanValue` / `DoubleValue` | 同左 |  |

## コード移行例

```java
// 1.7.10 (DCsConfig.java:125)
Property DCpotionID = cfg.get("potionID", "Immunization", potionIDImmunity);
potionIDImmunity = DCpotionID.getInt();

// 1.16.5 (Config.java 新規)
public class Config {
  public static final ForgeConfigSpec COMMON_SPEC;
  public static final Common COMMON;
  static {
    Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
    COMMON_SPEC = specPair.getRight();
    COMMON = specPair.getLeft();
  }
  public static class Common {
    public final ForgeConfigSpec.IntValue potionIDImmunity;
    public final ForgeConfigSpec.IntValue teaTreeGenValue;
    public Common(ForgeConfigSpec.Builder builder){
      builder.comment("Set new potion ID").push("potionID");
      potionIDImmunity = builder.comment("Immunization").defineInRange("Immunization", 60, 0, 127);
      builder.pop();
      builder.push("world setting");
      teaTreeGenValue = builder.comment("Set the generation probability of tea tree.(1-20)").defineInRange("Tea Tree Gen Probability", 5, 1, 20);
      builder.pop();
    }
  }
}
// 登録
ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC, "defeatedcrow-common.toml");

// 参照
int id = Config.COMMON.potionIDImmunity.get(); // 旧 DCsConfig.potionIDImmunity
```

## カテゴリ別移行注意

### Potion ID (10) / Entity ID (20)
- 1.13+ Registry化で整数ID管理自体が不要になるため、`potionID*` / `entityId*` の Config 項目は **削除** 推奨。
- 1.12以前に暫定的に維持する場合は `defineInRange(...,0,127)` で代替。
- `villagerRecipeID` (+1で `villagerRecipe2ID` 自動生成) は `VillagerProfession` の `ResourceLocation` 管理に置換。

### WorldGen (4)
- `teaTreeGenValue` の手動クランプ (`DCsAppleMilk.java:740-744`で `if(value>20)value=20`) → `defineInRange(...,1,20)` で自動。
- `notGenTeaTree` / `disableClam` は `BooleanValue` にそのまま。

### Difficulty / Setting (大量)
- `dustDif`/`chargeDif`/`exchangeDif`/`procDif` は `defineInRange` で範囲指定。
- `enableMobBlock[]` (`boolean[5]`) → `defineList("Enable Mob Drop Container", Arrays.asList(true,true,true,true,true), o-> o instanceof Boolean)`
- `setCupScale` (`double 0.01-10.0`) → `defineInRange("Set Drink Entity Scale", 1.0D, 0.01D, 10.0D)`
- `debugPass` (`String`) → `define("Debug Mode Pass", "Input the password here")`

### Render Setting
- `setCupTexture`/`setAltTexturePass`/`useAltTeppanTex`/`noRenderFoodsSteam` 等は `CLIENT` config に分離推奨（サーバー同期不要）。

### Cocktail Config
- `DCsConfigCocktail` の `DCsAppleMilk-cocktail.cfg` は `common.toml` のサブカテゴリ `cocktail` に統合するか、別TOML `defeatedcrow-cocktail.toml` に分離。

### ASM Config
- `DCsConfiguration` (`asm/config`) は CoreMod 用。1.12+で CoreMod が `coremods.json` / `Mixin` に変わるため合わせて移行。`Configuration` → `ForgeConfigSpec` 同様だが `ModContainer` 前の読込なので `FMLPreInitializationEvent` より前の `FMLConstructionEvent` で処理。


---

## 1.20.1 追補

- **ForgeConfigSpec は1.20.1でも維持**: `ForgeConfigSpec` + `ModConfig.Type.COMMON` + `ModLoadingContext.registerConfig` の定型は 1.16→1.20で変更なし。`TOML` の `defeatedcrow-common.toml` / `defeatedcrow-client.toml` 分離も維持。
- **PotionID / EntityID 削除**: 1.20.1ではRegistryのHolder管理で整数ID不要。`potionID*` / `entityId*` のConfig項目は **削除**（旧 `DCsConfig.java:9` の10項目は `DeferredRegister<MobEffect>` でRL管理）。
- **WorldGen確率**: `teaTreeGenValue` の手動クランプは `defineInRange` で自動（1.20.1でも同じ）。
- **mods.toml との連携**: `mods.toml` の `[[dependencies]]` ではなく `ModList.get().isLoaded` で分岐するため、Configで連携Modの有無を制御する項目は不要。

### 検証 1.20.1

- `grep -r "potionID"` → 0件（削除）確認
- `config/defeatedcrow-common.toml` が `ForgeConfigSpec` の `defineInRange` の範囲コメントを正しく出力するか `runClient` で確認


## 検証手順
1. `grep -r \"Configuration(\" src/` → `ForgeConfigSpec` 置換確認。
2. `grep -r \"cfg.get(\"` → `BUILDER.define` 置換確認。
3. `grep -r \"DCsConfig\\.\"` → `Config.COMMON.xxx.get()` 置換確認（static直参照の残存検出）。
4. `gradlew build` で `ModConfig` 未登録エラー解消確認。
5. 生成される `config/defeatedcrow-common.toml` が TOML形式で正しく `defineInRange` の範囲がコメント出力されるか確認。

## 関連
- [Config 一覧](../config.md) / [個別ページ索引](./README.md)
- [WorldGen](../worldgen.md) / [Entity](../entities.md) / [Potion](../potions.md) - Config参照先
- `src/main/java/mods/defeatedcrow/common/config/DCsConfig.java:1`
- `src/main/java/mods/defeatedcrow/common/DCsAppleMilk.java:476`
