# Item 一覧

> 自動生成元: `src/main/java/mods/defeatedcrow/common/DCsAppleMilk.java:238-351`  
> 登録処理: `src/main/java/mods/defeatedcrow/common/MaterialRegister.java:247-314` + `DCsAppleMilk.java:512-517` (NEIダミー)  
> 総数: **64 Itemフィールド**（`DCsAppleMilk`） + **2 NEIダミー** = 66  
> 実際の `GameRegistry.registerItem` 件数: 61（MaterialRegister）+ 2（DCsAppleMilk preInit）= 63（ fluid bucket/bottle は Fluid側で登録）  
> **個別ページ**: 53フィールド分の詳細ページを [`doc/items/`](./items/) に整備（[索引](./items/README.md) / [移行ガイド](./items/migration-guide.md) / [ItemBlock一覧](./items/item-blocks.md)）。テンプレは [`doc/items/_template.md`](./items/_template.md)、構造化データは [`_inventory.json`](./items/_inventory.json) / [`_full_inventory.json`](./items/_full_inventory.json)。

## 概要
食べ物・素材・ツール・魔法アイテム等を `Item` として登録。多くはメタデータで亜種を管理（例: `leafTea:0=茶葉,1=ミント,2=カシス...`）。詳細マッピングは `src/main/java/mods/defeatedcrow/api/ItemAPI.java:54-177` を参照。

> **バージョン移行**: 1.7.10 → 1.12.2/1.16.5/1.20 への移行手順は [Item移行ガイド](./items/migration-guide.md) に集約。各Item個別ページ（`doc/items/*.md`）には「オーバーライドメソッド一覧」「移行チェックリスト」「メソッド参照先情報」を記載。ItemBlock（36クラス）は [ItemBlock一覧](./items/item-blocks.md) と [Block一覧](./blocks.md) を併読。

## カテゴリ別カウント
| カテゴリ | 数 | 主なフィールド |
|---|---|---|
| 食べ物（完成品） | 6 | bakedApple, appleTart, toffyApple, icyToffyApple, appleSandwich, chocolateFruits, baseSoupBowl |
| 食材・素材（加工前） | 7 | leafTea, gratedApple, mincedFoods, foodTea, EXItems, condensedMIlk, clam |
| 飲料素材・醸造 | 5 | yeast, moromi, itemLargeBottle, itemCordial, bucketYoungAlcohol |
| ツール・武器 | 11 | DCgrater, chopsticks, chalcedonyKnife/Hammer, pruningShears, monocle, onixSword, firestarter, yuzuGatling, fossilCannon, eightEyesArm |
| 装置関連 | 5 | batteryItem, slotPanel, jawPlate, dustWood, essentialOil |
| 魔法・お香 | 12+3 | incense* (11種), princessClam, strangeSlag, fossilScale |
| 素材・鉱物 | 5 | inkStick, icyCrystal, stickCarbon, oreDust, （EXItems内ナゲット等） |
| コンテナアイテム | 2 | containerItemDoorW/I |
| 流体コンテナ | 5 | bucketVegiOil, bottleVegiOil, bucketCamOil, bottleCamOil, bucketYoungAlcohol（再掲） |
| NEIダミー | 2 | dummyItem, dummyTeppan |
| **合計** | **64+2** | |

---

## 1. 食べ物（完成品） (`src/main/java/mods/defeatedcrow/common/item/edible/*`)

| フィールド名 | レジストリ名 | クラス | CreativeTab | 詳細 | 説明 |
|---|---|---|---|---|---|
| `bakedApple` | `defeatedcrow.bakedApple` | `ItemBakedApple:1` | applemilkFood | [個別ページ](./items/bakedApple.md) | 焼きリンゴ |
| `appleTart` | `defeatedcrow.appleTart` | `ItemAppleTart:1` | applemilkFood | [個別ページ](./items/appleTart.md) | アップルタルト（メタ: apple:0, cassis:1, yuzu:2, apricot:3） |
| `toffyApple` | `defeatedcrow.toffyApple` | `ItemToffyApple:1` | applemilkFood | [個別ページ](./items/toffyApple.md) | りんご飴（通常） |
| `icyToffyApple` | `defeatedcrow.icyToffyApple` | `ItemIcyToffyApple:1` | applemilkFood | [個別ページ](./items/icyToffyApple.md) | りんご飴（ポーション効果付き: icy:0, airy:1, golden:2, green:3） |
| `appleSandwich` | `defeatedcrow.appleSandwich` | `ItemAppleSandwich:1` | applemilkFood | [個別ページ](./items/appleSandwich.md) | アップルサンド（apple:0, egg:1, cassis:2, yuzu:3） |
| `chocolateFruits` | `defeatedcrow.chocolateFruits` | `ItemChocoFruits:1` | applemilkFood | [個別ページ](./items/chocolateFruits.md) | フルーツチョコ（アーモンド0～リンゴ12まで14種） |
| `baseSoupBowl` | `defeatedcrow.basesoupitem` | `ItemBaseSoupBowl:1` | applemilkFood | [個別ページ](./items/baseSoupBowl.md) | ベーススープ碗（アイテム型、ブロック設置前） |

## 2. 食材・素材

| フィールド名 | レジストリ名 | クラス | Tab | 詳細 | 説明 / メタ |
|---|---|---|---|---|---|
| `leafTea` | `defeatedcrow.leafTea` | `ItemLeafTea:1` | applemilkMaterial | [個別ページ](./items/leafTea.md) | 生茶葉類（tea:0, mint:1, cassis:2, yuzu:3, camellia:4） |
| `foodTea` | `defeatedcrow.foodTea` | `ItemFoodTea:1` | applemilkMaterial | [個別ページ](./items/foodTea.md) | 加工茶葉（green:0, tea:1, oxidized:2, earlGray:3, appleTea:4） |
| `gratedApple` | `defeatedcrow.gratedApple` | `ItemGratedApple:1` | applemilkMaterial | [個別ページ](./items/gratedApple.md) | すりおろし（apple:0, fruit:1, honeyLemon:2, coffee:3, ganache:4, lime5, tomato6, berry7） |
| `mincedFoods` | `defeatedcrow.mincedFoods` | `ItemMincedFoods:1` | applemilkMaterial | [個別ページ](./items/mincedFoods.md) | ミンチ食材（mushroom:0 ... pumpkin:6, BLT:7, chocolate:8, miso:9, clamSoup:10） |
| `EXItems` | `defeatedcrow.condensedMilk` | `ItemEXItem:1` | applemilkMaterial | [個別ページ](./items/EXItems.md) | 汎用素材（milkCandy:0, animalGlue:1, chalGear:3, crushedIce:4, glassDust:5, clamDust:6, ironNugget:7～bronze:13） |
| `condensedMIlk` | `defeatedcrow.milkCandy` | `ItemCondensedMilk:1` | applemilkMaterial | [個別ページ](./items/condensedMIlk.md) | 練乳系（condensed:0, cassisPreserve:1, mintSauce:2, yuzuMarmalade:3） |
| `clam` | `defeatedcrow.clam` | `ItemClam:1` | applemilkFood | [個別ページ](./items/clam.md) | ハマグリ（raw:0, cooked:1, burnt:2, blackEgg:3） |
| `itemMintSeed` | `defeatedcrow.seedMint` | `ItemMintSeed:1` | applemilkMaterial | [個別ページ](./items/itemMintSeed.md) | ミント種 |
| `oreDust` | `defeatedcrow.oreDust` | `ItemOreDust:1` | applemilkMaterial | [個別ページ](./items/oreDust.md) | 鉱石粉（鉱石辞書対応） |
| `dustWood` | `defeatedcrow.dustWood` | `ItemWoodDust:1` | applemilkMaterial | [個別ページ](./items/dustWood.md) | 木粉（お香素材） |

## 3. 飲料・醸造素材

| フィールド名 | レジストリ名 | クラス | Tab | 詳細 | 説明 |
|---|---|---|---|---|---|
| `yeast` | `defeatedcrow.yeast` | `ItemYeast:1` | applemilk | [個別ページ](./items/yeast.md) | 酵母 |
| `moromi` | `defeatedcrow.moromi` | `ItemMoromi:1` | applemilk | [個別ページ](./items/moromi.md) | もろみ（sake_young:0, beer_young:1, wine_young:2 いずれも FluidContainer 対応） |
| `itemLargeBottle` | `defeatedcrow.itemBottle` | `ItemLargeBottle:1` | applemilk | [個別ページ](./items/itemLargeBottle.md) | 大瓶アイテム（中身と残量をメタで管理, 0-127） |
| `itemCordial` | `defeatedcrow.itemCordial` | `ItemCordial:1` | applemilk | [個別ページ](./items/itemCordial.md) | コーディアル（リキュール）アイテム |
| `youngAlcohol` | （フィールドのみ） | `ItemBucketYoungAlcohol` と共有 | — | [個別ページ](./items/youngAlcohol.md) | 若い酒バケツの内部フィールド名（実体は `bucketYoungAlcohol`） |

### 流体コンテナ（`MaterialRegister.addFluid()` で登録）

| フィールド名 | レジストリ名 | クラス | 対応流体 | 詳細 | 備考 |
|---|---|---|---|---|---|
| `bucketVegiOil` | `defeatedcrow.bucketVegiOil` | `ItemBucketVegiOil:1` | vegitable_oil | [個別ページ](./items/bucketVegiOil.md) | バケツ（1000mB） |
| `bottleVegiOil` | `defeatedcrow.bottleVegiOil` | `ItemBottleVegiOil:1` | vegitable_oil | [個別ページ](./items/bottleVegiOil.md) | ボトル（200mB, 空瓶返却） |
| `bucketCamOil` | `defeatedcrow.bucketCamOil` | `ItemBucketCamOil:1` | camellia_oil | [個別ページ](./items/bucketCamOil.md) | 椿油バケツ |
| `bottleCamOil` | `defeatedcrow.bottleCamOil` | `ItemBottleCamOil:1` | camellia_oil | [個別ページ](./items/bottleCamOil.md) | 椿油ボトル |
| `bucketYoungAlcohol` | `defeatedcrow.bucketYoungAlcohol` | `ItemBucketYoungAlcohol:1` | shothu/whiskey/brandy/rum/vodka_young | [個別ページ](./items/bucketYoungAlcohol.md) | メタ 0-4で5種を兼用 |

## 4. ツール・武器 (`src/main/java/mods/defeatedcrow/common/item/*`)

| フィールド名 | レジストリ名 | クラス | Tab | 詳細 | 説明 |
|---|---|---|---|---|---|
| `DCgrater` | `defeatedcrow.grater` | `ItemGrater:1` | applemilk | [個別ページ](./items/DCgrater.md) | おろし金（耐久あり、クラフトで減耗 `CraftingEvent`） |
| `chopsticks` | `defeatedcrow.chopsticks` | `ItemChopsticks:1` | applemilkContainer | [個別ページ](./items/chopsticks.md) | 箸（chopsticks:0, spoon:1） |
| `chalcedonyKnife` | `defeatedcrow.chalcedonyKnife` | `ItemChalcedonyKnife:1` | applemilk | [個別ページ](./items/chalcedonyKnife.md) | 玉髄ナイフ（ToolMaterial CHALCEDONY） |
| `chalcedonyHammer` | `defeatedcrow.chalcedonyStoneCutter` | `ItemChalcedonyHammer:1` | applemilk | [個別ページ](./items/chalcedonyHammer.md) | 玉髄カッター（stone cutter） |
| `pruningShears` | `defeatedcrow.chalcedonyShears` | `ItemChalcedonyShears:1` | applemilk | [個別ページ](./items/pruningShears.md) | 玉髄剪定バサミ |
| `monocle` | `defeatedcrow.monocle` | `ItemChalcedonyMonocle:1` | applemilk | [個別ページ](./items/monocle.md) | モノクル（ヘルメット防具, Shiftで鉱石辞書名表示 `ShowOreNameEvent`） |
| `onixSword` | `defeatedcrow.onixSword` | `ItemOnixSword:1` | applemilk | [個別ページ](./items/onixSword.md) | オニキスソード |
| `firestarter` | `defeatedcrow.firestarter` | `ItemFireStarter:1` | applemilk | [個別ページ](./items/firestarter.md) | 火打石（着火ツール） |
| `yuzuGatling` | `defeatedcrow.yuzuGatling` | `ItemYuzuGatling:1` | applemilkMagic | [個別ページ](./items/yuzuGatling.md) | 柚子ガトリング（EntityYuzuBullet 発射） |
| `fossilCannon` | `defeatedcrow.fossilCannon` | `ItemFossilCannon:1` | applemilkMagic | [個別ページ](./items/fossilCannon.md) | 化石砲（EntityAnchorMissile 発射） |
| `eightEyesArm` | `defeatedcrow.debugArm` | `ItemDebugArm:1` | — | [個別ページ](./items/eightEyesArm.md) | デバッグ用アイテム（クリエイティブタブ無し） |

- 玉髄ツール属性: `DCsAppleMilk.enumToolMaterialChalcedony` は `DCsAppleMilk.java:504` で `EnumHelper.addToolMaterial("CHALCEDONY",2,128,5.0F,4.0F,18)` として生成、補修素材に `Items.flint`。

## 5. 装置関連・素材 (`src/main/java/mods/defeatedcrow/common/item/appliance/*`)

| フィールド名 | レジストリ名 | クラス | Tab | 詳細 | 説明 |
|---|---|---|---|---|---|
| `batteryItem` | `defeatedcrow.battery` | `ItemBattery:1` | applemilk | [個別ページ](./items/batteryItem.md) | 電池（ChargeItem, 4種メタ） |
| `slotPanel` | `defeatedcrow.slotPanel` | `ItemSlotPanel:1` | applemilk | [個別ページ](./items/slotPanel.md) | スロットパネル（装置アップグレード） |
| `jawPlate` | `defeatedcrow.jawPlate` | `ItemJawplate:1` | applemilk | [個別ページ](./items/jawPlate.md) | ジョープレート（AdvProcessor用） |
| `icyCrystal` | `defeatedcrow.icyCrystal` | `ItemIcyCrystal:1` | applemilkMaterial | [個別ページ](./items/icyCrystal.md) | 氷結晶（冷却材） |
| `inkStick` | `defeatedcrow.inkStick` | `ItemInkStick:1` | applemilkMaterial | [個別ページ](./items/inkStick.md) | 墨スティック |
| `stickCarbon` | `defeatedcrow.stickCarbon` | `ItemCarbonStick:1` | applemilkMaterial | [個別ページ](./items/stickCarbon.md) | 炭素棒 |
| `oreDust` | `defeatedcrow.oreDust` | `ItemOreDust:1` | applemilkMaterial | [個別ページ](./items/oreDust.md) | 鉱石粉（再掲） |
| `strangeSlag` | `defeatedcrow.strangeSlag` | `ItemStrangeSlag:1` | applemilkMagic | [個別ページ](./items/strangeSlag.md) | 謎のスラグ（インセンス素材） |
| `fossilScale` | `defeatedcrow.fossilScale` | `ItemFossilScale:1` | applemilkMagic | [個別ページ](./items/fossilScale.md) | 化石鱗片 |

## 6. 魔法・お香 (`src/main/java/mods/defeatedcrow/common/item/magic/*`)

| フィールド名 | レジストリ名 | クラス | Tab | 詳細 | 効果タイプ |
|---|---|---|---|---|---|
| `princessClam` | `defeatedcrow.princessClam` | `ItemPrincessClam:1` | applemilkMagic | [個別ページ](./items/princessClam.md) | 姫ハマグリ（charm素材, flower/butterfly/wind/moon） |
| `essentialOil` | `defeatedcrow.essentialOil` | `ItemEssentialOil:1` | applemilkMagic | [個別ページ](./items/essentialOil.md) | 精油 |
| `incenseApple` | `defeatedcrow.incense_apple` | `ItemIncenseApple:1` | applemilkMagic | [個別ページ](./items/incenseApple.md) | 林檎お香 |
| `incenseRose` | `defeatedcrow.incense_rose` | `ItemIncenseRose:1` | applemilkMagic | [個別ページ](./items/incenseRose.md) | 薔薇お香 |
| `incenseMint` | `defeatedcrow.incense_mint` | `ItemIncenseMint:1` | applemilkMagic | [個別ページ](./items/incenseMint.md) | ミントお香 |
| `incenseClam` | `defeatedcrow.incense_clam` | `ItemIncenseClam:1` | applemilkMagic | [個別ページ](./items/incenseClam.md) | ハマグリお香 |
| `incenseIce` | `defeatedcrow.incense_ice` | `ItemIncenseIce:1` | applemilkMagic | [個別ページ](./items/incenseIce.md) | 氷お香 |
| `incenseLavender` | `defeatedcrow.incense_lavender` | `ItemIncenseLavender:1` | applemilkMagic | [個別ページ](./items/incenseLavender.md) | ラベンダーお香 |
| `incenseSandalwood` | `defeatedcrow.incense_sandalwood` | `ItemIncenseSandalwood:1` | applemilkMagic | [個別ページ](./items/incenseSandalwood.md) | 白檀お香 |
| `incenseAgar` | `defeatedcrow.incense_aloeswood` | `ItemIncenseAgar:1` | applemilkMagic | [個別ページ](./items/incenseAgar.md) | 沈香 |
| `incenseFrank` | `defeatedcrow.incense_frankincense` | `ItemIncenseFrankincense:1` | applemilkMagic | [個別ページ](./items/incenseFrank.md) | 乳香 |
| `incenseYuzu` | `defeatedcrow.incense_yuzu` | `ItemIncenseYuzu:1` | applemilkMagic | [個別ページ](./items/incenseYuzu.md) | 柚子お香 |
| `incenseVanilla` | `defeatedcrow.incense_vanilla` | `ItemIncenseVanilla:1` | applemilkMagic | [個別ページ](./items/incenseVanilla.md) | バニラお香 |

## 7. コンテナアイテム

| フィールド名 | レジストリ名 | クラス | Tab | 詳細 | 説明 |
|---|---|---|---|---|---|
| `containerItemDoorW` | `defeatedcrow.containeritemDoorW` | `ItemContainerDoor:1` (Blocks.wooden_door) | applemilkContainer | [個別ページ](./items/containerItemDoorW.md) | ドア収納アイテム（木） |
| `containerItemDoorI` | `defeatedcrow.containeritemDoorI` | `ItemContainerDoor:1` (Blocks.iron_door) | applemilkContainer | [個別ページ](./items/containerItemDoorI.md) | ドア収納アイテム（鉄） |
| `milkBottle` | — | — | — | [個別ページ](./items/milkBottle.md) | 未使用フィールド（レジストリ無し、予約） |

## 8. NEI / 表示ダミー (`DCsAppleMilk.java:512-517`)

| フィールド名 | レジストリ名 | クラス | 詳細 | 説明 |
|---|---|---|---|
| `dummyItem` | `defeatedcrow.dummyItem` | `ItemDummyForTooltip:1` | [個別ページ](./items/dummyItem.md) | NEI表示用ダミー |
| `dummyTeppan` | `defeatedcrow.dummyPlate` | `ItemDummyForTeppan:1` | [個別ページ](./items/dummyTeppan.md) | 鉄板レシピ表示用ダミー |

---

## 登録補足
- `MaterialRegister.load()` : `src/main/java/mods/defeatedcrow/common/MaterialRegister.java:247-314`
- `MaterialRegister.addFluid()` : 流体コンテナ（bucket/bottle）を `FluidContainerRegistry.registerFluidContainer` で同時登録
- `DCsAppleMilk.preInit` : NEIダミーを `GameRegistry.registerItem` 直登録

## 個別ページ・移行情報
- **個別ページ索引**: [doc/items/README.md](./items/README.md) - カテゴリ別カウントと全53ページへのリンク
- **Item移行ガイド**: [doc/items/migration-guide.md](./items/migration-guide.md) - 1.7.10→1.12.2/1.16.5/1.20 の共通移行手順、メソッドシグネチャ表、サブクラス別対応
- **テンプレート**: [doc/items/_template.md](./items/_template.md) - 新Item追加時の雛形（登録情報、メタ、継承、メソッド、移行チェックリスト）
- **インベントリ**: [doc/items/_inventory.json](./items/_inventory.json) (53フィールド) / [doc/items/_full_inventory.json](./items/_full_inventory.json) (111クラス) - 構造化データ
- **ItemBlock一覧**: [doc/items/item-blocks.md](./items/item-blocks.md) - 36のItemBlock系クラス（Block付随）の一覧と移行メモ

## 関連ドキュメント
- [Block 一覧](./blocks.md) - ItemBlockはそちらでTileEntity含めて詳述
- [Fluid 一覧](./fluids.md) - バケツ・ボトル連携
- [Potion 一覧](./potions.md) - お香・チャームがポーション付与
- [TileEntity 一覧](./tile-entities.md) - 装置Tile連携
- [CreativeTab](./creative-tabs.md) - `applemilk*` 5タブ
- [概要](./overview.md) - MOD全体像

