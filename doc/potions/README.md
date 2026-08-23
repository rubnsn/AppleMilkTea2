# Potion 個別ページ索引

> 総数: **10 Potion** (EndlessIDs 0-127)
> 登録元: `src/main/java/mods/defeatedcrow/common/MaterialRegister.java:413-488` (`addPotion()`)
> 本ディレクトリは1.7.10→1.12.2移行向けの個別ページ集。

## 一覧

| フィールド名 | configキー | 色 | 良/悪 | クラス | 個別ページ | 説明 |
|---|---|---|---|---|---|---|
| `Immunization` | `potionIDImmunity` | `0xFEC0C0` | 良 | `PotionImmunity` | [→](./Immunization.md) | 免疫 |
| `prvExplode` | `potionIDPrvExplode` | `0x646464` | 良 | `PotionProtectionEX` | [→](./prvExplode.md) | 爆発耐性 |
| `prvProjectile` | `potionIDPrvProjectile` | `0x996420` | 良 | `PotionProtectionEX` | [→](./prvProjectile.md) | 飛び道具耐性 |
| `reflex` | `potionIDReflex` | `0x0064FF` | 良 | `PotionReflex` | [→](./reflex.md) | 反射 |
| `absEXP` | `potionIDAbsEXP` | `0x00FF64` | 良 | `PotionReflex` | [→](./absEXP.md) | 経験値吸収 |
| `absHeal` | `potionIDAbsHeal` | `0xFF32C0` | 良 | `PotionReflex` | [→](./absHeal.md) | 回復吸収 |
| `suffocation` | `potionIDSuffocation` | `0x161616` | 悪 | `PotionSuffocation` | [→](./suffocation.md) | 窒息 |
| `prvSuffocation` | `potionIDPrvSuffocation` | `0xC064FF` | 良 | `PotionProtectionEX` | [→](./prvSuffocation.md) | 窒息耐性 |
| `hallucinations` | `potionIDHallucinations` | `0xFF32FF` | 悪 | `PotionHallucination` | [→](./hallucinations.md) | 幻覚 |
| `confinement` | `potionIDConfinement` | `0x646464` | 悪 | `PotionConfinement` | [→](./confinement.md) | 束縛 |

## 分類

### 良性 (6)
Immunization, prvExplode, prvProjectile, reflex, absEXP, absHeal, prvSuffocation

### 悪性 (3)
suffocation, hallucinations, confinement

---

## 生成元
- テンプレ: [`_template.md`](./_template.md)
- 移行ガイド: [`migration-guide.md`](./migration-guide.md)
- 一覧: [`potions.md`](../potions.md)
