# Potion 移行ガイド - 1.7.10 → 1.12.2 / 1.16.5 / 1.20.1

> 対象: `AppleMilkTea2` 全10 Potion / `MaterialRegister.java:413-488`
> 最終更新: 2026-08-24（1.20.1ブラッシュアップ: 2026-08-24）  
> 関連: [ビルド移行ガイド](../build.md) / `DCsConfig.java:9`
> 前提: Forge 1.7.10 EndlessIDs (0-127) → 1.12.2 Forge (128拡張不要) → 1.16+ Registry

## 概要
10 Potionの 1.7.10→1.12.2/1.16 移行対応。`Potion` → `MobEffect` の全面刷新。

## 登録の大局

| 1.7.10 | 1.12.2+ | 1.16.5+ |
|---|---|---|
| `Potion.potionTypes[DCsConfig.potionIDImmunity] == null && DCsConfig.potionIDImmunity <128` で分岐 (`MaterialRegister.java:414`) | `RegistryEvent.Register<Potion>` / `DeferredRegister<MobEffect>` で `ResourceLocation` ベース登録。ID衝突チェック不要 | 同左 + `MobEffectCategory` |
| `new PotionImmunity(id, false, 0xFEC0C0, x,y).setPotionName("DCs.potion.immunization")` | `new PotionImmunity(MobEffectCategory.BENEFICIAL, 0xFEC0C0).setRegistryName("defeatedcrow","immunization")` | `new MobEffect(MobEffectCategory.BENEFICIAL, 0xFEC0C0)` |
| `DCsConfig.potionIDXXX` (整数) | 削除。`ResourceLocation` で管理 | 同左 |
| `Potion.potionTypes` 固定配列 (EndlessIDsで128拡張) | `ForgeRegistries.POTIONS` / `ForgeRegistries.MOB_EFFECTS` | `Registry.MOB_EFFECT` |
| `AMTPotionManager.manager` (`IPotionGetter`) | `RegistryObject<MobEffect>` / `ForgeRegistries.MOB_EFFECTS.getValue(RL)` | 同左 |
| `PotionEffect` (`new PotionEffect(id, duration, amp)`) | `PotionEffect` 維持 (1.12) | `MobEffectInstance` / `EffectInstance` (`new MobEffectInstance(effect, duration, amp)`) |
| `lang/*.lang` (`potion.DCs.potion.xxx`) | `lang/*.json` (`effect.defeatedcrow.xxx`) | 同左 |

## クラス移行

| 1.7.10 クラス | 1.12.2 | 1.16.5 |
|---|---|---|
| `net.minecraft.potion.Potion` | 維持 (Forge `Potion` → `MobEffect` は1.13から) | `net.minecraft.world.effect.MobEffect` |
| `PotionBaseAMT` / `PotionLivingBase` | 維持だが `Potion` 継承 | `MobEffect` 継承に変更 |
| `Potion.potionTypes` | 維持 (1.12まで) | `Registry` 化で削除 |
| `PotionHelper` | 維持 | `PotionUtils` |
| `ItemStack` の `Potion` NBT (`PotionEffect` リスト) | 維持 | `MobEffectInstance` + `DataComponents` |

## メソッド移行

| メソッド | 1.7.10 | 1.12.2 | 1.16.5 |
|---|---|---|---|
| `isReady` | `boolean isReady(int duration, int amp)` | 同左 (MCP) → `isDurationEffectTick` | `isDurationEffectTick(int duration, int amp)` |
| `performEffect` | `void performEffect(EntityLivingBase, int amp)` | 同左 | `void applyEffectTick(LivingEntity, int amp)` |
| `formPotionEffect` (AMT独自) | `boolean formPotionEffect(int amp, int id, EntityLivingBase)` | 維持 | 維持だが `LivingEntity` + `Holder<MobEffect>` |
| `getStatusIconIndex` | `int getStatusIconIndex()` | 削除 (自動) | 削除 |
| `hasStatusIcon` | `boolean hasStatusIcon()` | 削除 | 削除 |
| `renderInventoryEffect` | `void renderInventoryEffect(...)` | 削除 | 削除 |

```java
// 1.7.10
if(Potion.potionTypes[DCsConfig.potionIDImmunity]==null && DCsConfig.potionIDImmunity <128){
  DCsAppleMilk.Immunization = (new PotionImmunity(DCsConfig.potionIDImmunity,false,0xFEC0C0,0,0)).setPotionName("DCs.potion.immunization");
}

// 1.16.5
public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "defeatedcrow");
public static final RegistryObject<MobEffect> IMMUNIZATION = MOB_EFFECTS.register("immunization", ()-> new PotionImmunity(MobEffectCategory.BENEFICIAL, 0xFEC0C0));
```

## イベント連携移行

| 1.7.10 | 1.12.2+ | 説明 |
|---|---|---|
| `DCsLivingEvent` (`LivingUpdateEvent` → `formPotionEffect`) | 維持。`LivingUpdateEvent` → `LivingEvent.LivingUpdateEvent` | `LivingEntity` |
| `DCsHurtEvent` (`LivingHurtEvent` → 反射/保護) | 維持 | `LivingHurtEvent` |
| `EntityMoreDropEvent` (`LivingDropsEvent` → charm) | 維持 | `LivingDropsEvent` |

発火は `MinecraftForge.EVENT_BUS.register(new DCsLivingEvent())` 維持。


---

## 1.20.1 追補

- **MobEffect は Holder化**: `RegistryObject<MobEffect>` + `Holder<MobEffect>` + `MobEffectCategory` は1.16→1.20で維持だが、1.20.1では `Holder<MobEffect>` で `MobEffectInstance` を生成: `new MobEffectInstance(ModMobEffects.IMMUNIZATION.getHolder().get(), duration, amp)` または `new MobEffectInstance(ModMobEffects.IMMUNIZATION.get(), duration, amp)` の両方が可だが `Holder` 経由が推奨。
- **PotionItem の brewing**: `BrewingRecipeRegistry.addRecipe` は 1.20.1でも維持（Forgeの `BrewingRecipeRegistry`）。`PotionUtils` → `PotionContents` に一部移行したものもあるがForge 47では `PotionUtils` 維持。
- **AttributeModifier**: ポーションの `addAttributeModifier` は 1.20.1では `Holder<Attribute>` + `ResourceLocation` で管理。旧 `UUID` ベースではなく `ResourceLocation` で識別。

### 検証 1.20.1

- `grep -r "potionTypes"` → 0件（Holder化で削除）確認
- `grep -r "MobEffectInstance"` のimportが `net.minecraft.world.effect.MobEffectInstance` であることを確認（Mojmap）


## 検証手順
1. `grep -r "potionTypes\["` → 0件確認 (Registry化で削除)
2. `grep -r "potionID"` → `DCsConfig` の整数管理が削除確認
3. `gradlew build` で `Potion` → `MobEffect` の未置換コンパイルエラー解消確認
4. `lang/*.lang` → `lang/*.json` 移行で `effect.defeatedcrow.xxx` キー確認

## 関連
- [Potion 一覧](../potions.md) / [個別ページ索引](./README.md)
- [Event 一覧](../events.md)
- `src/main/java/mods/defeatedcrow/common/MaterialRegister.java:413`
- `src/main/java/mods/defeatedcrow/common/config/DCsConfig.java:9`
