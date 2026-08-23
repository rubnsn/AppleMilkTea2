# hallucinations (幻覚)

> Source: `src/main/java/mods/defeatedcrow/potion/PotionHallucination.java:1`
> Registry: `Potion.potionTypes[ID]` (`MaterialRegister.java:413` `addPotion()`)
> Config: `DCsConfig.potionIDHallucinations` (`DCsConfig.java:9`, 0-127 EndlessIDs)
> Field: `DCsAppleMilk.hallucinations` (`DCsAppleMilk.java:xxx`)
> Color: `0xFF32FF` / 良悪: `悪`
> Manager: `AMTPotionManager.manager.getPotion("hallucinations")` / `PotionGetter.java:1`

## 概要
幻覚。幻覚Mob出現。悪。EntityIllusionMobs連携。

## 登録情報
- **クラス**: `PotionHallucination extends PotionImmunityBase` (`src/main/java/mods/defeatedcrow/potion/PotionHallucination.java:1`)
- **登録**: `if(Potion.potionTypes[DCsConfig.potionIDHallucinations]==null && DCsConfig.potionIDHallucinations<128){ DCsAppleMilk.hallucinations = new PotionHallucination(DCsConfig.potionIDHallucinations, true, 0xFF32FF, x,y).setPotionName("DCs.potion.hallucinations"); }` (`MaterialRegister.java:414`)
- **失敗時**: `succeedAddPotion=false`
- **色**: `0xFF32FF`

## 継承・インターフェース
- 継承: `PotionHallucination extends PotionImmunityBase`
- 基底: `PotionBaseAMT` / `PotionImmunityBase` / `PotionReflexBase` / `PotionLivingBase`

## プロパティ / 効果
- **isBadEffect**: `true` (悪)
- **liquidColor**: `0xFF32FF`
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
- **SpawnCancelEvent**: 幻覚Mobの自然スポーン抑制とは別に幻覚表示

## 付与手段
- **Item**: `ItemIncense*` / `ItemPrincessClam` / `ItemBucketYoungAlcohol` 等が `addPotionEffect(new PotionEffect(id, duration, amp))`
- **Tile**: `TileIncenseBase` が `formEffect()` 経由で近傍に付与

## 1.7.10 -> 1.12.2 移行チェックリスト
- [ ] `Potion` -> `MobEffect` (1.13+)
- [ ] `Potion.potionTypes[ID]` -> `DeferredRegister<MobEffect>` + `RegistryObject<MobEffect>`
- [ ] `DCsConfig.potionIDHallucinations` 整数ID -> 削除、`ResourceLocation` へ
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

> 自動生成: `src/main/java/mods/defeatedcrow/potion/PotionHallucination.java:1` / `MaterialRegister.java:413`
> 最終更新: 2026-08-24