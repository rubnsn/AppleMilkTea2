# Potion 一覧

> 定義元: `src/main/java/mods/defeatedcrow/potion/*`  
> 登録元: `src/main/java/mods/defeatedcrow/common/MaterialRegister.java:413-488` (`addPotion()`)  
> 初期化: `src/main/java/mods/defeatedcrow/potion/PotionGetter.java:1` + `src/main/java/mods/defeatedcrow/api/potion/AMTPotionManager.java:1`  
> 総数: **10 Potion**（IDは EndlessIDs で 0-127 範囲可変）  
> 個別ページ: [`doc/potions/`](./potions/README.md) に 10件の個別ページを生成  
> 移行ガイド: [`doc/potions/migration-guide.md`](./potions/migration-guide.md)

## 概要
EndlessIDs により拡張された Potion ID（0-127）を利用。config の IDが未使用かつ範囲内の場合のみ登録。失敗時は `succeedAddPotion=false`。

全Potionは `Potion.potionTypes[ID]` に登録、色と効果は各クラスで定義。

## 一覧

| フィールド名 | configキー | デフォルトID | クラス | 色 (hex) | 良/悪 | 説明 |
|---|---|---|---|---|---|---|
| `Immunization` | `potionIDImmunity` |  | `PotionImmunity:1` | `0xFEC0C0` | 良 | 免疫（Immunization）：デバフ無効化 |
| `prvExplode` | `potionIDPrvExplode` |  | `PotionProtectionEX:1` | `0x646464` | 良 | 爆発耐性（anvil由来, 保護EX） |
| `prvProjectile` | `potionIDPrvProjectile` |  | `PotionProtectionEX:1` | `0x996420` | 良 | 飛び道具耐性（magic由来） |
| `reflex` | `potionIDReflex` |  | `PotionReflex:1` | `0x0064FF` | 良 | 反射（ダメージ反射 type 3） |
| `absEXP` | `potionIDAbsEXP` |  | `PotionReflex:1` | `0x00FF64` | 良 | 経験値吸収（type 4） |
| `absHeal` | `potionIDAbsHeal` |  | `PotionReflex:1` | `0xFF32C0` | 良 | 回復吸収（type 5） |
| `suffocation` | `potionIDSuffocation` |  | `PotionSuffocation:1` | `0x161616` | 悪 | 窒息（Suffocation, 持続ダメージ） |
| `prvSuffocation` | `potionIDPrvSuffocation` |  | `PotionProtectionEX:1` | `0xC064FF` | 良 | 窒息耐性（inWall由来, setProtectSuffocation()） |
| `hallucinations` | `potionIDHallucinations` |  | `PotionHallucination:1` | `0xFF32FF` | 悪 | 幻覚（幻覚Mob出現） |
| `confinement` | `potionIDConfinement` |  | `PotionConfinement:1` | `0x646464` | 悪 | 束縛（移動制限） |

### クラス詳細
- `PotionImmunity` : `src/main/java/mods/defeatedcrow/potion/PotionImmunity.java:1` - `PotionImmunityBase` 継承
- `PotionProtectionEX` : `src/main/java/mods/defeatedcrow/potion/PotionProtectionEX.java:1` - `DamageSource` ごとに軽減、引数で詳細設定
- `PotionReflex` : `src/main/java/mods/defeatedcrow/potion/PotionReflex.java:1` - 反射・吸収系、type 3-5
- `PotionSuffocation` : `src/main/java/mods/defeatedcrow/potion/PotionSuffocation.java:1`
- `PotionHallucination` : `src/main/java/mods/defeatedcrow/potion/PotionHallucination.java:1`
- `PotionConfinement` : `src/main/java/mods/defeatedcrow/potion/PotionConfinement.java:1`

### 登録ロジック抜粋 (`MaterialRegister.java:414`)

```java
if (Potion.potionTypes[DCsConfig.potionIDImmunity] == null && DCsConfig.potionIDImmunity < 128) {
    DCsAppleMilk.Immunization = (new PotionImmunity(DCsConfig.potionIDImmunity, false, 0xFEC0C0, 0, 0))
        .setPotionName("DCs.potion.immunization");
}
```

- EndlessIDs なしでは 32 までだが、GTNH環境では 128 に拡張前提。
- `PotionGetter` で `AMTPotionManager.manager` 経由で取得可能。

## イベント連携
- `DCsLivingEvent` : `src/main/java/mods/defeatedcrow/event/DCsLivingEvent.java:1` - ポーション効果の毎tick処理
- `DCsHurtEvent` : `src/main/java/mods/defeatedcrow/event/DCsHurtEvent.java:1` - 被ダメージ時の反射/保護処理
- `EntityMoreDropEvent` : 螺鈿チャーム等のドロップ増加

## 移行ドキュメント
- [個別ページ索引](./potions/README.md) - 10個別ページの一覧
- [移行ガイド](./potions/migration-guide.md) - Potion→MobEffect移行
- [テンプレ](./potions/_template.md)

## 関連ドキュメント
- [Item 一覧](./items.md) - お香・チャームがポーション付与
- [Fluid 一覧](./fluids.md)
- [Event 一覧](./events.md) - `DCsLivingEvent` / `DCsHurtEvent`
- [API 一覧](./api.md) - `api/potion/*`
