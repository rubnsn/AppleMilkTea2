# Block 一覧

> 自動生成元: `src/main/java/mods/defeatedcrow/common/DCsAppleMilk.java:152-340`  
> 登録処理: `src/main/java/mods/defeatedcrow/common/MaterialRegister.java:316-410`  
> 総数: **74 ブロック**（フィールド数） / 登録件数 74（流体ブロック含む）  
> 個別ページ: [`doc/blocks/`](./blocks/README.md) に 74件の個別ページを生成（カテゴリ別）  
> 移行ガイド: [`doc/blocks/migration-guide.md`](./blocks/migration-guide.md)

## 概要
AppleMilkTea2 が追加するブロックをカテゴリ別に整理。全ブロックは `DCsAppleMilk` の static フィールドとして保持され、`MaterialRegister.load()` で `GameRegistry.registerBlock` される。
1.7.10→1.12.2移行に向けて、各Blockのオーバーライドメソッドと移行注記を個別ページに分離した。

## カテゴリ別カウント
| カテゴリ | 数 | 備考 | 個別ページ |
|---|---|---|---|
| 調理機器・装置 | 10 | TeaMaker, IceMaker, Processor など | [→](./blocks/appliance/) |
| エネルギー・蓄電 | 6 | BatBox, Gel, YuzuBat, HandleEngine | [→](./blocks/energy/) |
| 飲食物ブロック（設置型） | 11 | Bowl, Plate, Cup, Cocktail, IceCream | [→](./blocks/edible/) |
| 醸造・瓶・樽 | 4 | EmptyBottle, LargeBottle, Cordial, Barrel | [→](./blocks/brewing/) |
| コンテナ・圧縮収納 | 18 | WoodBox, VegiBag, MelonBomb など | [→](./blocks/container/) |
| 植物・自然 | 8 | TeaTree, Yuzu, ClamSand, Cassis, MintCrop | [→](./blocks/plants/) |
| インテリア・装飾 | 6 | Basket, BowlRack, ChopsticksBox, WoodPanel, YuzuFence, IncenseBase | [→](./blocks/decorative/) |
| 鉱石装飾（玉髄） | 7 | FlintBlock, Chalcedony, Lamp, RotaryDial, CPanel, Doll | [→](./blocks/chalcedony/) |
| 流体ブロック | 4 | VegOil, CamOil, DummyAlcohol x2 | [→](./blocks/fluid/) |
| **合計** | **74** | | |

---

## 1. 調理機器・装置 (`src/main/java/mods/defeatedcrow/common/block/appliance/*`)

| フィールド名 | レジストリ名 | クラス | ItemBlock | 個別ページ | 説明 |
|---|---|---|---|---|---|
| `teaMakerNext` | `defeatedcrow.teaMakerNext` | `BlockTeaMakerNext:1` | `ItemAppliance` | [→](./blocks/appliance/BlockTeaMakerNext.md) | TeaMaker / ティーメーカー（新型） |
| `teaMakerBlack` | `defeatedcrow.teaMakerBlack` | `BlockTeaMakerBlack:1` | `ItemAppliance` | [→](./blocks/appliance/BlockTeaMakerBlack.md) | TeaMaker 黒モデル |
| `emptyCup` | `defeatedcrow.emptyCup` | `BlockEmptyCup:1` | `なし` | [→](./blocks/appliance/BlockEmptyCup.md) | 空カップ（TeaMaker用） |
| `emptyPanGaiden` | `defeatedcrow.emptyPanG` | `BlockEmptyPanG:1` | `ItemPanG` | [→](./blocks/appliance/BlockEmptyPanG.md) | 空鍋（スープ鍋） |
| `filledSoupPan` | `defeatedcrow.filledSoupPan` | `BlockFilledSoupPan:1` | `ItemFilledSoupPan` | [→](./blocks/appliance/BlockFilledSoupPan.md) | 中身入りスープ鍋 |
| `iceMaker` | `defeatedcrow.iceMaker` | `BlockIceMaker:1` | `なし` | [→](./blocks/appliance/BlockIceMaker.md) | アイスメーカー / IceMaker |
| `teppanII` | `defeatedcrow.teppanII` | `BlockTeppanII:1` | `ItemAppliance` | [→](./blocks/appliance/BlockTeppanII.md) | 調理鉄板 II |
| `processor` | `defeatedcrow.processor` | `BlockProcessor:1` | `ItemMachineBlock` | [→](./blocks/appliance/BlockProcessor.md) | フードプロセッサー |
| `advProcessor` | `defeatedcrow.advProcessor` | `BlockAdvProcessor:1` | `ItemMachineBlock` | [→](./blocks/appliance/BlockAdvProcessor.md) | 高性能プロセッサー（AdvProcessor / Jaw Crusher） |
| `evaporator` | `defeatedcrow.evaporator` | `BlockEvaporator:1` | `ItemMachineBlock` | [→](./blocks/appliance/BlockEvaporator.md) | エバポレーター（蒸発器） |

## 2. エネルギー・蓄電 (`src/main/java/mods/defeatedcrow/common/block/energy/*`)

| フィールド名 | レジストリ名 | クラス | ItemBlock | 個別ページ | 説明 |
|---|---|---|---|---|---|
| `batBox` | `defeatedcrow.batBox` | `BlockBatBox:1` | `ItemBatBox` | [→](./blocks/energy/BlockBatBox.md) | 蓄電箱 BatBox (TileChargerBase) |
| `redGel` | `defeatedcrow.redGel` | `BlockRedGel:1` | `なし` | [→](./blocks/energy/BlockRedGel.md) | 赤色ゲル（燃料） |
| `yuzuGel` | `defeatedcrow.lightGel` | `BlockYuzuLight:1` | `なし` | [→](./blocks/energy/BlockYuzuLight.md) | 柚子ゲル / 発光ゲル |
| `yuzuBat` | `defeatedcrow.yuzuBatContainer` | `BlockYuzuBat:1` | `ItemBatBox` | [→](./blocks/energy/BlockYuzuBat.md) | 柚子電池ボックス |
| `gelBat` | `defeatedcrow.gelBatContainer` | `BlockGelBat:1` | `ItemGelBat` | [→](./blocks/energy/BlockGelBat.md) | ゲル電池ボックス |
| `handleEngine` | `defeatedcrow.EHandle` | `BlockHandleEngine:1` | `なし` | [→](./blocks/energy/BlockHandleEngine.md) | ハンドル式発電機 |

## 3. 飲食物ブロック（設置・可食） (`src/main/java/mods/defeatedcrow/common/block/edible/*`)

設置して右クリックで食べられるタイプ。エンティティ化してドロップする。

| フィールド名 | レジストリ名 | クラス | ItemBlock（EntityItem） | 個別ページ | 説明 |
|---|---|---|---|---|---|
| `teacupBlock` | `defeatedcrow.filledCup` | `BlockFilledCup:1` | `EntityItemTeaCup` | [→](./blocks/edible/BlockFilledCup.md) | ティーカップ ①（ミルクティー等 14種） |
| `teaCup2` | `defeatedcrow.filledCup2` | `BlockFilledCup2:1` | `EntityItemTeaCup2` | [→](./blocks/edible/BlockFilledCup2.md) | ティーカップ ②（アールグレイ等 9種） |
| `blockIcecream` | `defeatedcrow.iceCreamBlock` | `BlockIceCream:1` | `EntityItemIceCream` | [→](./blocks/edible/BlockIceCream.md) | アイスクリーム（ミルク, 茶, ココア等 10種） |
| `cocktail` | `defeatedcrow.cocktail` | `BlockCocktail:1` | `EntityItemCocktail` | [→](./blocks/edible/BlockCocktail.md) | カクテル ①（ダイキリ等 10種） |
| `cocktail2` | `defeatedcrow.cocktail2` | `BlockCocktail2:1` | `EntityItemCocktail2` | [→](./blocks/edible/BlockCocktail2.md) | カクテル ② |
| `cocktailSP` | `defeatedcrow.cocktailSP` | `BlockCocktailSP:1` | `EntityItemCocktailSP` | [→](./blocks/edible/BlockCocktailSP.md) | カクテル SP（特殊） |
| `alcoholCup` | `defeatedcrow.alcoholCup` | `BlockAlcoholCup:1` | `EntityItemAlcoholCup` | [→](./blocks/edible/BlockAlcoholCup.md) | 酒カップ（日本酒等） |
| `bowlBlock` | `defeatedcrow.bowlBlock` | `BlockBowl:1` | `EntityItemBowl` | [→](./blocks/edible/BlockBowl.md) | スープ碗（ライス, シチュー等 8種） |
| `bowlJP` | `defeatedcrow.bowlJP` | `BlockBowlJP:1` | `EntityItemBowlJP` | [→](./blocks/edible/BlockBowlJP.md) | 和風碗（同上 JPレンダリング） |
| `foodPlate` | `defeatedcrow.foodPlate` | `BlockFoodPlate:1` | `EntityItemSteak` | [→](./blocks/edible/BlockFoodPlate.md) | ステーキプレート（牛豚鶏ハマグリ） |
| `chocoBlock` | `defeatedcrow.chocolateGift` | `BlockChocoGift:1` | `ItemChocoGift` | [→](./blocks/edible/BlockChocoGift.md) | チョコギフト箱 |

## 4. 醸造・瓶・樽 (`src/main/java/mods/defeatedcrow/common/block/brewing/*`)

| フィールド名 | レジストリ名 | クラス | ItemBlock | 個別ページ | 説明 |
|---|---|---|---|---|---|
| `emptyBottle` | `defeatedcrow.emptyBottle` | `BlockEmptyBottle:1` | `ItemEmptyBottle` | [→](./blocks/brewing/BlockEmptyBottle.md) | 空き瓶（ガラス瓶） |
| `largeBottle` | `defeatedcrow.largeBottle` | `BlockLargeBottle:1` | `ItemBlockBottle` | [→](./blocks/brewing/BlockLargeBottle.md) | 大瓶（Large Bottle, 容量管理はメタデータ） |
| `cordial` | `defeatedcrow.blockCordial` | `BlockCordial:1` | `ItemBlockCordial` | [→](./blocks/brewing/BlockCordial.md) | コーディアル瓶 |
| `barrel` | `defeatedcrow.blockBarrel` | `BlockBarrel:1` | `ItemAppliance` | [→](./blocks/brewing/BlockBarrel.md) | 醸造樽（Barrel, 液体醸造） |

## 5. コンテナ・圧縮収納 (`src/main/java/mods/defeatedcrow/common/block/container/*`)

| フィールド名 | レジストリ名 | クラス | ItemBlock | 個別ページ | 説明 |
|---|---|---|---|---|---|
| `woodBox` | `defeatedcrow.WoodBox` | `BlockWoodBox:1` | `ItemWoodBox` | [→](./blocks/container/BlockWoodBox.md) | 原木箱（12種: オーク～アカシア＋他MOD連携） |
| `appleBox` | `defeatedcrow.AppleBox` | `BlockAppleBox:1` | `ItemAppleBox` | [→](./blocks/container/BlockAppleBox.md) | リンゴ箱 |
| `vegiBag` | `defeatedcrow.VegiBag` | `BlockVegiBag:1` | `ItemVegiBag` | [→](./blocks/container/BlockVegiBag.md) | 野菜袋（茶葉, じゃが, にんじん等 10種） |
| `cardboard` | `defeatedcrow.cardboardBox` | `BlockCardboard:1` | `ItemCardboard` | [→](./blocks/container/BlockCardboard.md) | 段ボール箱 |
| `charcoalBox` | `defeatedcrow.Charcoalcontainer` | `BlockCharcoalBox:1` | `ItemCharcoalBox` | [→](./blocks/container/BlockCharcoalBox.md) | 木炭コンテナ（燃料ハンドラ登録 `DCsAppleMilk.java:347`） |
| `gunpowderContainer` | `defeatedcrow.GunpowderContainer` | `BlockGunpowderContainer:1` | `ItemGunpowderContainer` | [→](./blocks/container/BlockGunpowderContainer.md) | 火薬/粘土/ハマグリ粉 コンテナ |
| `eggBasket` | `defeatedcrow.eggBasket` | `BlockEggBasket:1` | `ItemEggBasket` | [→](./blocks/container/BlockEggBasket.md) | 卵カゴ（通常/黒卵） |
| `mushroomBox` | `defeatedcrow.mushroomBox` | `BlockMushBox:1` | `ItemMushBox` | [→](./blocks/container/BlockMushBox.md) | キノコ箱（赤/茶） |
| `melonBomb` | `defeatedcrow.melonBomb` | `BlockMelonBomb:1` | `ItemMelonBomb` | [→](./blocks/container/BlockMelonBomb.md) | 圧縮スイカ（爆発エンティティ化） |
| `wipeBox` | `defeatedcrow.wipeBox` | `BlockWipeBox:1` | `ItemWipeBox` | [→](./blocks/container/BlockWipeBox.md) | 紙箱（wipe/kixWipe） |
| `wipeBox2` | `defeatedcrow.wipeBox2` | `BlockWipeBox2:1` | `ItemWipeBox2` | [→](./blocks/container/BlockWipeBox2.md) | 大型紙箱（耐久 0-5000） |
| `mobBlock` | `defeatedcrow.mobDropBox` | `BlockMobDrop:1` | `ItemMobDropBox` | [→](./blocks/container/BlockMobDrop.md) | Mobドロップ圧縮箱 |
| `silkyMelon` | `defeatedcrow.melonSilky` | `BlockSilkyMelon:1` | `ItemSilkyMelon` | [→](./blocks/container/BlockSilkyMelon.md) | シルキーメロン（投擲爆発） |
| `flowerPot` | `defeatedcrow.flowerPot` | `BlockFlowerPot:1` | `ItemFlowerPot` | [→](./blocks/container/BlockFlowerPot.md) | 植木鉢（AMT独自） |
| `flowerBase` | `defeatedcrow.flowerVase` | `BlockFlowerVase:1` | `ItemFlowerVase` | [→](./blocks/container/BlockFlowerVase.md) | 花瓶 |
| `hedge` | `defeatedcrow.hedge` | `BlockHedge:1` | `ItemHedge` | [→](./blocks/container/BlockHedge.md) | 生け垣 |
| `containerWBottle` | `defeatedcrow.containerBottleW` | `BlockContainerWaterBottle:1` | `ItemContainerBase` | [→](./blocks/container/BlockContainerWaterBottle.md) | 水瓶コンテナ（壁掛けボトル） |
| `containerSaddle` | `defeatedcrow.containerSaddle` | `BlockContainerSaddle:1` | `ItemContainerBase` | [→](./blocks/container/BlockContainerSaddle.md) | サドルコンテナ |

## 6. 植物・自然 (`src/main/java/mods/defeatedcrow/common/block/plants/*`)

| フィールド名 | レジストリ名 | クラス | ItemBlock | 個別ページ | 説明 |
|---|---|---|---|---|---|
| `saplingTea` | `defeatedcrow.saplingTea` | `BlockSaplingTea:1` | `ItemTeaSapling` | [→](./blocks/plants/BlockSaplingTea.md) | 茶の苗木 |
| `teaTree` | `defeatedcrow.teaTree` | `BlockTeaTree:1` | `ItemTeaTree` | [→](./blocks/plants/BlockTeaTree.md) | 茶の木（成長段階 0:通常/1:成長） |
| `cassisTree` | `defeatedcrow.cassisTree` | `BlockCassisTree:1` | `ItemCassisTree` | [→](./blocks/plants/BlockCassisTree.md) | カシスの木 |
| `saplingYuzu` | `defeatedcrow.saplingYuzu` | `BlockYuzuSapling:1` | `なし` | [→](./blocks/plants/BlockYuzuSapling.md) | 柚子の苗木 |
| `logYuzu` | `defeatedcrow.logYuzu` | `BlockYuzuLog:1` | `なし` | [→](./blocks/plants/BlockYuzuLog.md) | 柚子の原木 |
| `leavesYuzu` | `defeatedcrow.leavesYuzu` | `BlockYuzuLeaves:1` | `ItemYuzuLeaves` | [→](./blocks/plants/BlockYuzuLeaves.md) | 柚子の葉 |
| `clamSand` | `defeatedcrow.clamSand` | `BlockClamSand:1` | `ItemClamSand` | [→](./blocks/plants/BlockClamSand.md) | ハマグリ砂（通常/減少/姫） |
| `cropMint` | `defeatedcrow.cropMint` | `BlockMintCrop:1` | `なし` | [→](./blocks/plants/BlockMintCrop.md) | ミント作物（作物ブロック） |

## 7. インテリア・装飾 (`src/main/java/mods/defeatedcrow/common/block/*`)

| フィールド名 | レジストリ名 | クラス | ItemBlock | 個別ページ | 説明 |
|---|---|---|---|---|---|
| `bowlRack` | `defeatedcrow.bowlRack` | `BlockBowlRack:1` | `ItemBowlRack` | [→](./blocks/decorative/BlockBowlRack.md) | 茶碗ラック |
| `Basket` | `defeatedcrow.basket` | `BlockBasket:1` | `ItemBreadBasket` | [→](./blocks/decorative/BlockBasket.md) | パンかご / ボトルケース兼用（メタ 0-5:パン, 6-14:瓶） |
| `chopsticksBox` | `defeatedcrow.chopsticksBox` | `BlockChopsticksBox:1` | `ItemChopsticksBox` | [→](./blocks/decorative/BlockChopsticksBox.md) | 箸箱 |
| `woodPanel` | `defeatedcrow.woodPanel` | `BlockWoodPanel:1` | `ItemWoodPanel` | [→](./blocks/decorative/BlockWoodPanel.md) | 木製パネル |
| `yuzuFence` | `defeatedcrow.yuzuFence` | `BlockYuzuFence:1` | `なし` | [→](./blocks/decorative/BlockYuzuFence.md) | 柚子フェンス |
| `incenseBase` | `defeatedcrow.incenseBase` | `BlockIncenseBase:1` | `なし` | [→](./blocks/decorative/BlockIncenseBase.md) | お香台（Incense Base） |

## 8. 鉱石装飾（玉髄 / Chalcedony） (`src/main/java/mods/defeatedcrow/common/block/*`)

| フィールド名 | レジストリ名 | クラス | ItemBlock | 個別ページ | 説明 |
|---|---|---|---|---|---|
| `flintBlock` | `defeatedcrow.flintBlock` | `BlockFlint:1` | `ItemFlintBlock` | [→](./blocks/chalcedony/BlockFlint.md) | フリントブロック |
| `chalcedony` | `defeatedcrow.chalcedony` | `BlockChalcedony:1` | `ItemChalcedony` | [→](./blocks/chalcedony/BlockChalcedony.md) | 玉髄ブロック（青/橙/白） |
| `cLamp` | `defeatedcrow.chalcedonyLamp` | `BlockChalcedonyLamp:1` | `ItemChalcedonyLamp` | [→](./blocks/chalcedony/BlockChalcedonyLamp.md) | 玉髄ランプ（6+ バリエーション） |
| `cLampOpaque` | `defeatedcrow.chalcedonyLampOp` | `BlockChalcedonyLampOp:1` | `ItemCLampOp` | [→](./blocks/chalcedony/BlockChalcedonyLampOp.md) | 不透明玉髄ランプ |
| `chalcenonyPanel` | `defeatedcrow.chalcedonyPanel` | `BlockCPanel:1` | `なし` | [→](./blocks/chalcedony/BlockCPanel.md) | カルセドニーパネル（CPanel） |
| `rotaryDial` | `defeatedcrow.rotaryDial` | `BlockRotaryDial:1` | `なし` | [→](./blocks/chalcedony/BlockRotaryDial.md) | ロータリーダイヤル |
| `crowDoll` | `defeatedcrow.crowFigure` | `BlockCrowDoll:1` | `ItemCrowDoll` | [→](./blocks/chalcedony/BlockCrowDoll.md) | カラス人形（defeatedcrow） |

## 9. 流体ブロック (`src/main/java/mods/defeatedcrow/common/fluid/*`)

| フィールド名 | レジストリ名 | クラス | 流体 | 個別ページ | 説明 |
|---|---|---|---|---|---|
| `blockVegitableOil` | `defeatedcrow.blockVegiOil` | `BlockOilFluid:1` | `vegitable_oil` | [→](./blocks/fluid/BlockOilFluid.md) | 植物油ブロック |
| `blockCamelliaOil` | `defeatedcrow.blockCamOil` | `BlockCamOilFluid:1` | `camellia_oil` | [→](./blocks/fluid/BlockCamOilFluid.md) | 椿油ブロック |
| `blockDummyAlcohol` | `defeatedcrow.blockDummyAlcohol` | `BlockDummyFluid:1` | `（ダミー）` | [→](./blocks/fluid/BlockDummyFluid.md) | 醸造ダミー流体 ① |
| `blockDummyAlcohol2` | `defeatedcrow.blockDummyAlcohol2` | `BlockDummyFluid2:1` | `（ダミー）` | [→](./blocks/fluid/BlockDummyFluid2.md) | 醸造ダミー流体 ② |

---

## 登録順序・関連コード
- `MaterialRegister.load()` : `src/main/java/mods/defeatedcrow/common/MaterialRegister.java:238-411` で全ブロックを登録
- `MaterialRegister.addFluid()` : `src/main/java/mods/defeatedcrow/common/MaterialRegister.java:490-718` で流体ブロックと流体コンテナを登録
- TileEntity は `CommonProxy.registerTileEntity()` : `src/main/java/mods/defeatedcrow/common/CommonProxy.java:70-117` で紐付け

## メタデータ管理補足
- `ItemChocoGift`, `BlockWoodBox`, `BlockVegiBag` などはメタデータで種類を分岐。詳細は `src/main/java/mods/defeatedcrow/api/ItemAPI.java:54-177` のコメントに全マッピング記載。
- `BlockLargeBottle` は `metadata & 15 = 種類`, `(metadata >>4) &7 = 残量` で管理。

## 移行ドキュメント
- [個別ページ索引](./blocks/README.md) - 74個別ページの一覧
- [移行ガイド](./blocks/migration-guide.md) - 1.7.10→1.12.2 メソッド対応表
- [テンプレ](./blocks/_template.md) - 個別ページ雛形

## 関連ドキュメント
- [Item 一覧](./items.md)
- [TileEntity 一覧](./tile-entities.md)
- [Fluid 一覧](./fluids.md)