# Entity 個別ページ索引

> 総数: **20 Entity** (ModEntity) + 2 Villager + 2 VillageHandle
> 登録元: `src/main/java/mods/defeatedcrow/common/DCsAppleMilk.java:542-723`
> Config: `src/main/java/mods/defeatedcrow/common/config/DCsConfig.java:20`
> 本ディレクトリは1.7.10→1.12.2移行向けの個別ページ集。`_template.md`をベースに生成。

## カテゴリ別一覧

### projectile / 爆発 (3)
`src/main/java/mods/defeatedcrow/common/entity/*`

| Entityクラス | 登録名 | configキー | 個別ページ | 説明 |
|---|---|---|---|---|
| `EntityMelonBomb` | `compressedMelon` | `entityIdMelon` | [→](./EntityMelonBomb.md) | 圧縮スイカ爆弾 |
| `EntitySilkyMelon` | `compressedSilkyMelon` | `entityIdSilkMelon` | [→](./EntitySilkyMelon.md) | シルキーメロン |
| `EntityKinoko` | `mushroomBox` | `entityIdKinoko` | [→](./EntityKinoko.md) | キノコ箱投擲 |

### 魔法・弾体 / dummy (4)
`src/main/java/mods/defeatedcrow/common/entity/*` + `dummy/*`

| Entityクラス | 登録名 | configキー | 個別ページ | 説明 |
|---|---|---|---|---|
| `EntityYuzuBullet` | `yuzuBullet` | `entityIdBullet` | [→](./EntityYuzuBullet.md) | 柚子ガトリング弾 |
| `EntityAnchorMissile` | `anchorMissile` | `entityIdMissile` | [→](./EntityAnchorMissile.md) | アンカーミサイル |
| `EntityStunEffect` | `stunEntity` | `entityIdStun` | [→](./EntityStunEffect.md) | スタン演出ダミー |
| `EntityIllusionMobs` | `illusionCreeper` | `entityIdIllusion` | [→](./EntityIllusionMobs.md) | 幻覚クリーパー |

### 設置型食物 Entity (Placeable 13)
`src/main/java/mods/defeatedcrow/common/entity/edible/*` (`PlaceableFoods` 継承)

| Entityクラス | 登録名 | configキー | 対応Block | 個別ページ |
|---|---|---|---|---|
| `PlaceableIcecream` | `PlaceableIceCream` | `entityIdIce` | `blockIcecream` | [→](./PlaceableIcecream.md) |
| `PlaceableSteak` | `PlaceableSteak` | `entityIdSteak` | `foodPlate` | [→](./PlaceableSteak.md) |
| `PlaceableAlcoholCup` | `PlaceableAlcoholCup` | `entityIdAlcohol` | `alcoholCup` | [→](./PlaceableAlcoholCup.md) |
| `PlaceableCocktail` | `PlaceableCocktail` | `entityIdCocktail` | `cocktail` | [→](./PlaceableCocktail.md) |
| `PlaceableCocktail2` | `PlaceableCocktail2` | `entityIdCocktail2` | `cocktail2` | [→](./PlaceableCocktail2.md) |
| `PlaceableCocktailSP` | `PlaceableCocktailSP` | `entityIdCocktailSP` | `cocktailSP` | [→](./PlaceableCocktailSP.md) |
| `PlaceableBowl` | `PlaceableBowl` | `entityIdBowl` | `bowlBlock` | [→](./PlaceableBowl.md) |
| `PlaceableBowlJP` | `PlaceableBowlJP` | `entityIdBowlJP` | `bowlJP` | [→](./PlaceableBowlJP.md) |
| `PlaceableCup1` | `PlaceableCup` | `entityIdCup` | `teacupBlock` | [→](./PlaceableCup1.md) |
| `PlaceableCup2` | `PlaceableCup2` | `entityIdCup2` | `teaCup2` | [→](./PlaceableCup2.md) |
| `PlaceableTart` | `PlaceableTart` | `entityIdTart` | `ItemAppleTart` | [→](./PlaceableTart.md) |
| `PlaceableSandwich` | `PlaceableSandwich` | `entityIdSandwich` | `ItemAppleSandwich` | [→](./PlaceableSandwich.md) |
| `PlaceableBaseSoup` | `PlaceableBaseSoup` | `entityIdBaseSoup` | `ItemBaseSoupBowl` | [→](./PlaceableBaseSoup.md) |

### Villager / Village (2+2)

| クラス | ID config | 登録箇所 | 個別ページ | 説明 |
|---|---|---|---|---|
| `VillagerCafe` | `villagerRecipeID` | `VillagerRegistry.registerVillagerId` `DCsAppleMilk.java:712` | [→](./VillagerCafe.md) | カフェ村人 |
| `VillagerYome` | `villagerRecipe2ID` | `VillagerRegistry.registerVillagerId` `DCsAppleMilk.java:715` | [→](./VillagerYome.md) | ヨメ村人 |
| `ComponentVillageCafe` + `VillageCreateHandleCafe` | `ViCafe` | `MapGenStructureIO.func_143031_a` `DCsAppleMilk.java:719` | [→](../worldgen/ComponentVillageCafe.md) | カフェ生成 |
| `ComponentVillageWarehouse` + `VillageCreateHandleWarehouse` | `ViWarehouse` | `MapGenStructureIO` `DCsAppleMilk.java:722` | [→](../worldgen/ComponentVillageWarehouse.md) | 倉庫生成 |

---

## 生成元
- テンプレ: [`_template.md`](./_template.md)
- 移行ガイド: [`migration-guide.md`](./migration-guide.md)
- 一覧: [`entities.md`](../entities.md)
