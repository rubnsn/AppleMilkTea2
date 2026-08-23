# reflex (反射)

> Source: `src/main/java/mods/defeatedcrow/potion/PotionReflex.java:1`
> Registry: `Potion.potionTypes[ID]` (`MaterialRegister.java:413` `addPotion()`)
> Config: `DCsConfig.potionIDReflex` (`DCsConfig.java:9`, 0-127 EndlessIDs)
> Field: `DCsAppleMilk.reflex` (`DCsAppleMilk.java:xxx`)
> Color: `0x0064FF` / 良悪: `良`
> Manager: `AMTPotionManager.manager.getPotion("reflex")` / `PotionGetter.java:1`

## 概要
反射type3。被ダメを反射。DCsHurtEvent。

## 登録情報
- **クラス**: `PotionReflex extends PotionReflexBase` (`src/main/java/mods/defeatedcrow/potion/PotionReflex.java:1`)
- **登録**: `if(Potion.potionTypes[DCsConfig.potionIDReflex]==null && DCsConfig.potionIDReflex<128){ DCsAppleMilk.reflex = new PotionReflex(DCsConfig.potionIDReflex, false, 0x0064FF, x,y).setPotionName("DCs.potion.reflex"); }` (`MaterialRegister.java:414`)
- **失敗時**: `succeedAddPotion=false`
- **色**: `0x0064FF`

## 継承・インターフェース
- 継承: `PotionReflex extends PotionReflexBase`
- 基底: `PotionBaseAMT` / `PotionImmunityBase` / `PotionReflexBase` / `PotionLivingBase`

## プロパティ / 効果
- **isBadEffect**: `false` (良)
- **liquidColor**: `0x0064FF`
- **isReady**: `duration % X ==0` で tick制御
- **performEffect**: 効果本体
- **formPotionEffect**: AMT独自、ampに応じた分岐

## オーバーライドメソッド一覧
| メソッド | シグネチャ (1.7.10) | 説明 | 移行 (1.12.2+) |
|---|---|---|---|
| `isReady` | `boolean isReady(int duration, int amp)` | tick判定 | `isDurationEffectTick` にリネーム |
| `performEffect` | `void performEffect(EntityLivingBase, int amp)` | 効果 | `applyEffectTick(LivingEntity, int)` (1.16) |
| `formPotionEffect` | `boolean formPotionEffect(int amp, int id, EntityLivingBase)` | AMT独自 | 維持 |

## イベント連携
- **DCsLivingEvent**: `LivingUpdateEvent` で `formPotionEffect` tick (`src/main/java/mods/defeatedcrow/event/DCsLivingEvent.java:1`)
- **DCsHurtEvent**: `LivingHurtEvent` で軽減/反射 (`src/main/java/mods/defeatedcrow/event/DCsHurtEvent.java:1`)

## 付与手段
- **Item**: `ItemIncense*` / `ItemPrincessClam` / `ItemBucketYoungAlcohol` 等が `addPotionEffect(new PotionEffect(id, duration, amp))`
- **Tile**: `TileIncenseBase` が `formEffect()` 経由で近傍に付与

## 1.7.10 -> 1.12.2 移行チェックリスト
- [ ] `Potion` -> `MobEffect` (1.13+)
- [ ] `Potion.potionTypes[ID]` -> `DeferredRegister<MobEffect>` + `RegistryObject<MobEffect>`
- [ ] `DCsConfig.potionIDReflex` 整数ID -> 削除、`ResourceLocation` へ
- [ ] `setPotionName` -> `setRegistryName` + `lang/*.json`
- [ ] `isReady` -> `isDurationEffectTick`
- [ ] `performEffect` -> `applyEffectTick`
- [ ] `PotionEffect` -> `MobEffectInstance` / `EffectInstance`
- [ ] `AMTPotionManager` -> `ForgeRegistries.MOB_EFFECTS`

## 関連ドキュメント
- [Potion 一覧](../potions.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
- [Event 一覧](../events.md)

> 自動生成: `src/main/java/mods/defeatedcrow/potion/PotionReflex.java:1` / `MaterialRegister.java:413`
> 最終更新: 2026-08-24