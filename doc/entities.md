# Entity 一覧

> 自動生成元: `src/main/java/mods/defeatedcrow/common/DCsAppleMilk.java:542-702`  
> 総数: **20 Entity**（ModEntity）+ 2 Villager  
> 個別ページ: [`doc/entities/`](./entities/README.md) に 22件の個別ページを生成（カテゴリ別）  
> 移行ガイド: [`doc/entities/migration-guide.md`](./entities/migration-guide.md) - 1.7.10→1.12.2/1.16.5 対応

## 概要
投擲・設置型の食物エンティティ、爆発ブロックの投射体、魔法弾、ダミー効果エンティティを `EntityRegistry.registerModEntity` で登録。configでID可変（`DCsConfig.entityId*`）、未設定時は `findGlobalUniqueEntityId()` で自動採番。

全エンティティの登録範囲: `trackingRange=250, updateFrequency=5, sendsVelocityUpdates=true`。

村人は `VillagerRegistry` で登録、村構造物は `MapGenStructureIO` で登録。

---

## ModEntity 一覧

### A. 投擲・爆発ブロック

| 登録名 | クラス | ID config | ソース | 説明 |
|---|---|---|---|---|
| `compressedMelon` | `EntityMelonBomb:1` | `entityIdMelon` | `src/main/java/mods/defeatedcrow/common/entity/EntityMelonBomb.java:1` | 圧縮スイカ爆弾（投擲→着弾爆発） |
| `compressedSilkyMelon` | `EntitySilkyMelon:1` | `entityIdSilkMelon` | `src/main/java/mods/defeatedcrow/common/entity/EntitySilkyMelon.java:1` | シルキーメロン（投擲爆発、演出強化） |
| `mushroomBox` | `EntityKinoko:1` | `entityIdKinoko` | `src/main/java/mods/defeatedcrow/common/entity/EntityKinoko.java:1` | キノコ箱投擲体 |

### B. 魔法・弾体

| 登録名 | クラス | ID config | ソース | 説明 |
|---|---|---|---|---|
| `yuzuBullet` | `EntityYuzuBullet:1` | `entityIdBullet` | `src/main/java/mods/defeatedcrow/common/entity/EntityYuzuBullet.java:1` | 柚子ガトリング弾 |
| `anchorMissile` | `EntityAnchorMissile:1` | `entityIdMissile` | `src/main/java/mods/defeatedcrow/common/entity/EntityAnchorMissile.java:1` | アンカーミサイル（化石砲） |
| `stunEntity` | `EntityStunEffect:1` | `entityIdStun` | `src/main/java/mods/defeatedcrow/common/entity/dummy/EntityStunEffect.java:1` | スタン効果ダミー |
| `illusionCreeper` | `EntityIllusionMobs:1` | `entityIdIllusion` | `src/main/java/mods/defeatedcrow/common/entity/dummy/EntityIllusionMobs.java:1` | 幻覚クリーパー（Hallucination ポーション演出） |

### C. 設置型食物エンティティ（Placeable）

右クリックで設置される食物ブロックの実体。`Placeable*` は `FoodBaseEntity` 継承。

| 登録名 | クラス | ID config | 対応ブロック | 説明 |
|---|---|---|---|---|
| `PlaceableIceCream` | `PlaceableIcecream:1` | `entityIdIce` | `blockIcecream` | 設置アイス（回転表示） |
| `PlaceableSteak` | `PlaceableSteak:1` | `entityIdSteak` | `foodPlate` | 設置ステーキ |
| `PlaceableAlcoholCup` | `PlaceableAlcoholCup:1` | `entityIdAlcohol` | `alcoholCup` | 設置酒カップ |
| `PlaceableCocktail` | `PlaceableCocktail:1` | `entityIdCocktail` | `cocktail` | 設置カクテル ① |
| `PlaceableCocktail2` | `PlaceableCocktail2:1` | `entityIdCocktail2` | `cocktail2` | 設置カクテル ② |
| `PlaceableCocktailSP` | `PlaceableCocktailSP:1` | `entityIdCocktailSP` | `cocktailSP` | 設置カクテル SP |
| `PlaceableBowl` | `PlaceableBowl:1` | `entityIdBowl` | `bowlBlock` | 設置スープ碗 |
| `PlaceableBowlJP` | `PlaceableBowlJP:1` | `entityIdBowlJP` | `bowlJP` | 設置和風碗 |
| `PlaceableCup` | `PlaceableCup1:1` | `entityIdCup` | `teacupBlock` | 設置ティーカップ ① |
| `PlaceableCup2` | `PlaceableCup2:1` | `entityIdCup2` | `teaCup2` | 設置ティーカップ ② |
| `PlaceableTart` | `PlaceableTart:1` | `entityIdTart` | （ItemAppleTartの設置体） | 設置タルト |
| `PlaceableSandwich` | `PlaceableSandwich:1` | `entityIdSandwich` | （ItemAppleSandwich） | 設置サンドイッチ |
| `PlaceableBaseSoup` | `PlaceableBaseSoup:1` | `entityIdBaseSoup` | （ItemBaseSoupBowl） | 設置ベーススープ |

> 計 13 Placeable + 3 爆発 + 4 魔法 = 20 Entity

---

## Villager 一覧

| クラス | ID config | 登録箇所 | 説明 |
|---|---|---|---|
| `VillagerCafe` | `villagerRecipeID` | `DCsAppleMilk.java:712` `VillagerRegistry.registerVillagerId` | カフェ村人（取引: 茶・食材等） |
| `VillagerYome` | `villagerRecipe2ID` | `DCsAppleMilk.java:715` | ヨメ村人（追加取引） |

村構造物:
- `ComponentVillageCafe` + `VillageCreateHandleCafe` → `ViCafe` (`DCsAppleMilk.java:719`)
- `ComponentVillageWarehouse` + `VillageCreateHandleWarehouse` → `ViWarehouse` (`DCsAppleMilk.java:722-723`)

ソース:
- `src/main/java/mods/defeatedcrow/common/entity/VillagerCafe.java:1`
- `src/main/java/mods/defeatedcrow/common/entity/VillagerYome.java:1`
- `src/main/java/mods/defeatedcrow/common/world/village/ComponentVillageCafe.java:1`
- `src/main/java/mods/defeatedcrow/common/world/village/ComponentVillageWarehouse.java:1`

---

## レンダー対応

| Entity | Renderクラス |
|---|---|
| EntityMelonBomb / SilkyMelon | `RenderMelonBomb:1`, `RenderSilkyMelon:1` |
| EntityKinoko | `RenderKinokoEntity:1` |
| EntityYuzuBullet | `RenderYuzuBullet:1` |
| EntityAnchorMissile | `RenderAnchorMissile:1` |
| EntityStunEffect | `RenderStunEntity:1` |
| EntityIllusionMobs | `RenderIllusionCreeper:1` |
| Placeable* | `Render*Entity`（`RenderBowlEntity`, `RenderCupEntity` 等） |

レンダー登録は `ClientProxy.registerRenderers()` : `src/main/java/mods/defeatedcrow/client/ClientProxy.java:1`

---

## 設定（DCsConfig）

| configキー | デフォルト | 説明 |
|---|---|---|
| `entityIdMelon` 等 20項目 | 0（自動） | Entity ID。0のとき `findGlobalUniqueEntityId()` で採番 |
| `villagerRecipeID` |  | 村人ID |
| `notGenTeaTree`, `disableClam` |  | ワールド生成ON/OFFだがEntityとは別 |

詳細は `src/main/java/mods/defeatedcrow/common/config/DCsConfig.java:1` 参照。

## 移行ドキュメント
- [個別ページ索引](./entities/README.md) - 22個別ページの一覧
- [移行ガイド](./entities/migration-guide.md) - 1.7.10→1.12.2 メソッド対応表
- [テンプレ](./entities/_template.md) - 個別ページ雛形

## 関連ドキュメント
- [Block 一覧](./blocks.md) - 食物ブロックとの対応
- [TileEntity 一覧](./tile-entities.md)
- [Fluid 一覧](./fluids.md)
- [Potion 一覧](./potions.md)
- [WorldGen / Village](./worldgen.md)
- [Config](./config.md)
