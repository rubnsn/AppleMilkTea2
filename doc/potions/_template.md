# PotionName

> Source: `src/main/java/mods/defeatedcrow/potion/PotionName.java:1`
> Registry: `Potion.potionTypes[ID]` (`MaterialRegister.java:413` `addPotion()`)
> Config: `DCsConfig.potionIDXXX` (default `xx`, 0-127 EndlessIDs) (`DCsConfig.java:9`)
> Manager: `AMTPotionManager.manager.getPotion("xxx")` / `DCsAppleMilk.field` (`DCsAppleMilk.java:xxx`)
> Type: 良 (Beneficial) / 悪 (Harmful) / 中立

## 概要
[1-2文でPotionの効果・特徴を記述。保護/反射/窒息/幻覚/束縛/免疫等の分類]

## 登録情報
- **クラス**: `PotionXxx extends PotionBaseAMT / PotionImmunityBase / PotionReflexBase / PotionLivingBase` (`src/main/java/mods/defeatedcrow/potion/PotionXxx.java:1`)
- **コンストラクタ**: `new PotionXxx(ID, isBadEffect, liquidColor, x, y).setPotionName("DCs.potion.xxx")` (`MaterialRegister.java:414`)
- **ID解決**: `if(Potion.potionTypes[DCsConfig.potionIDXXX]==null && ID<128)` のみ登録成功、失敗時 `succeedAddPotion=false`
- **Color**: `0xRRGGBB` (例: `0xFEC0C0` Immunization)
- **Icon**: `x,y` は `textures/gui/potion.png` 上の座標（1.7）→ 1.12+は `MobEffect` アイコン `textures/mob_effect/*.png`

## 継承・インターフェース
- 継承: `Potion` (1.7) → `MobEffect` (1.13+ リネーム) / `PotionBaseAMT` 等の中間基底
- 実装: `IPotionGetter` 経由で `AMTPotionManager` に登録

## プロパティ / 効果
- **isBadEffect**: `true` = 悪、`false` = 良
- **liquidColor**: ポーション液体色（瓶色）
- **isReady**: `isReady(int duration, int amplifier)` で tick実行タイミング制御
- **performEffect**: `performEffect(EntityLivingBase, int amplifier)` で毎tick効果発動
- **formPotionEffect**: AMT独自拡張（`PotionLivingBase` 系で amplifier に応じて付与/除去）
- **Duration/Amplifier**: `PotionEffect(ID, duration, amplifier)` で付与

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `isReady` | `boolean isReady(int duration, int amp)` | `Potion` tick判定 (`EntityLivingBase.onLivingUpdate`) | tick実行するか | 維持。1.12+ `isDurationEffectTick(int duration, int amp)` にリネーム（MCP→Mojang） |
| `performEffect` | `void performEffect(EntityLivingBase, int amp)` | `isReady` が true のtick | 効果本体 | 維持。`EntityLivingBase` → `LivingEntity` (1.14+) |
| `formPotionEffect` | `boolean formPotionEffect(int amp, int id, EntityLivingBase)` | `DCsLivingEvent` から呼出 | AMT独自の効果分岐 | 維持（AMT独自）。1.16+も同様だが `MobEffect` 継承で `applyEffectTick` に統合検討 |
| `hasStatusIcon` | `boolean hasStatusIcon()` | GUI表示 | アイコン有無 | 削除(1.12+は `MobEffect` が自動) |
| `getStatusIconIndex` | `int getStatusIconIndex()` | GUI | テクスチャindex | 削除 → `MobEffect` の `getDisplayName` + アセット |
| `renderInventoryEffect` | `void renderInventoryEffect(int x, int y, PotionEffect, Minecraft)` | インベントリ描画 | カスタム描画 | `renderHUDEffect` / `renderInventoryEffect` 削除、JSONモデルへ |

## イベント連携
- **DCsLivingEvent**: `LivingEvent.LivingUpdateEvent` で `PotionLivingBase` / `PotionImmunityBase` の `formPotionEffect` をtick処理 (`src/main/java/mods/defeatedcrow/event/DCsLivingEvent.java:1`)
- **DCsHurtEvent**: `LivingHurtEvent` で `PotionProtectionEX` / `PotionReflex` のダメージ軽減/反射 (`src/main/java/mods/defeatedcrow/event/DCsHurtEvent.java:1`)
- **EntityMoreDropEvent**: `LivingDropsEvent` で charmのドロップ増加連携

## 付与手段
- **Item**: `ItemIncense*` (IIncenseEffect), `ItemPrincessClam` (charm), `ItemBucketYoungAlcohol` 等が `entity.addPotionEffect(new PotionEffect(id, duration, amp))`
- **Tile**: `TileIncenseBase` が近傍Entityに `formEffect()` 経由で付与
- **Command**: `/effect` 由来はバニラ経由

## 1.7.10 → 1.12.2 移行チェックリスト
- [ ] `Potion` → `MobEffect` (1.13+ リネーム). `net.minecraft.potion.Potion` → `net.minecraft.potion.Effect` (Mojang) → `MobEffect`
- [ ] `Potion.potionTypes[ID]` 固定配列 → `Registry.register(Registry.MOB_EFFECT, new ResourceLocation("defeatedcrow","xxx"), effect)` / `DeferredRegister<MobEffect>` + `RegistryObject<MobEffect>`
- [ ] `DCsConfig.potionIDXXX` 整数ID管理 → 削除。`ResourceLocation` ベースへ（EndlessIDs不要、1.12以前はForgeのPotion拡張で `PotionType` 登録）
- [ ] `setPotionName("DCs.potion.xxx")` → `setRegistryName("defeatedcrow","xxx")` + `I18n` / `lang/*.json` (`effect.defeatedcrow.xxx`)
- [ ] `isReady` → `isDurationEffectTick` (1.12) → `isDurationEffectTick` 維持 (1.16 `isDurationEffectTick`)
- [ ] `performEffect` → `applyEffectTick(LivingEntity, int amplifier)` (1.16+ リネーム、1.12は `performEffect` 維持)
- [ ] `PotionEffect` → `EffectInstance` / `MobEffectInstance` (1.13+ リネーム). コンストラクタ `new PotionEffect(id, duration, amp)` → `new EffectInstance(effect, duration, amp)` / `new MobEffectInstance(holder, duration, amp)`
- [ ] `PotionHelper` / `ItemStack` の `Potion` NBT → `PotionUtils` / `MobEffectInstance` リスト
- [ ] 色: コンストラクタ `liquidColor` → `MobEffectCategory` + `color` (`MobEffectCategory.BENEFICIAL/ HARMFUL`)
- [ ] `AMTPotionManager` / `PotionGetter` / `IPotionGetter` → `RegistryObject<MobEffect>` + `ForgeRegistries.MOB_EFFECTS.getValue(RL)` で置換
- [ ] `AchievementRegister` 等の参照は維持だが `Potion` → `MobEffect` 置換

## 関連ドキュメント
- [Potion 一覧](../potions.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
- [Item 一覧](../items.md) - お香/チャーム付与
- [Event 一覧](../events.md) - `DCsLivingEvent`/`DCsHurtEvent`
