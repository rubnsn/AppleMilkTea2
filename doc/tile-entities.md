# TileEntity 一覧

> 登録元: `src/main/java/mods/defeatedcrow/common/CommonProxy.java:70-117` (`registerTileEntity()`)  
> 総数: **45 TileEntity**  
> 個別ページ: [`doc/tile-entities/`](./tile-entities/README.md) に 45件の個別ページを生成  
> 移行ガイド: [`doc/tile-entities/migration-guide.md`](./tile-entities/migration-guide.md)

## 概要
ブロックのインベントリ・液体・回転・残量管理などを TileEntity で実装。多くは `TileHasDirection` / `TileHasRemaining` を継承。

## 一覧

| 登録名 | クラス | ソース | 対応ブロック | 説明 |
|---|---|---|---|---|
| `TileHasDirection` | `TileHasDirection` | `src/main/java/mods/defeatedcrow/common/tile/TileHasDirection.java:1` | 共通基底 | 方位を持つ基底 |
| `TileHasRemaining` | `TileHasRemaining` | `src/main/java/mods/defeatedcrow/common/tile/TileHasRemaining.java:1` | 共通基底 | 残量1を持つ基底 |
| `TileHasRemaining2` | `TileHasRemain2` | `src/main/java/mods/defeatedcrow/common/tile/TileHasRemain2.java:1` | 共通基底 | 残量2を持つ基底 |
| `TileCupHandle` | `TileCupHandle` | `src/main/java/mods/defeatedcrow/common/tile/TileCupHandle.java:1` | emptyCup | 空カップのハンドル |
| `TileBread` | `TileBread` | `src/main/java/mods/defeatedcrow/common/tile/TileBread.java:1` | Basket | パンかご |
| `TileDummy` | `TileDummy` | `src/main/java/mods/defeatedcrow/common/tile/TileDummy.java:1` | ダミー | ダミー |
| `TileJPBowl` | `TileJPBowl` | `src/main/java/mods/defeatedcrow/common/tile/TileJPBowl.java:1` | bowlJP | 和風碗 |
| `TileChopsticksBox` | `TileChopsticksBox` | `src/main/java/mods/defeatedcrow/common/tile/TileChopsticksBox.java:1` | chopsticksBox | 箸箱 |
| `TileEggs` | `TileEggs` | `src/main/java/mods/defeatedcrow/common/tile/TileEggs.java:1` | eggBasket | 卵カゴ |
| `TileSteak` | `TileSteak` | `src/main/java/mods/defeatedcrow/common/tile/TileSteak.java:1` | foodPlate | ステーキ皿 |
| `TileMakerHandle` | `TileMakerHandle` | `src/main/java/mods/defeatedcrow/common/tile/TileMakerHandle.java:1` | teaMaker* | ティーメーカー内部 |
| `TilePanHandle` | `TilePanHandle` | `src/main/java/mods/defeatedcrow/common/tile/TilePanHandle.java:1` | emptyPanGaiden | 鍋ハンドル |
| `TileFilledSoupPan` | `TileFilledSoupPan` | `src/main/java/mods/defeatedcrow/common/tile/appliance/TileFilledSoupPan.java:1` | filledSoupPan | 中身入り鍋 |
| `TileMakerNext` | `TileMakerNext` | `src/main/java/mods/defeatedcrow/common/tile/appliance/TileMakerNext.java:1` | teaMakerNext | ティーメーカー Tile |
| `TileWipeBox` | `TileWipeBox` | `src/main/java/mods/defeatedcrow/common/tile/TileWipeBox.java:1` | wipeBox | 紙箱 |
| `TileIceMaker` | `TileIceMaker` | `src/main/java/mods/defeatedcrow/common/tile/appliance/TileIceMaker.java:1` | iceMaker | アイスメーカー（Container/Guiあり） |
| `TileIcecream` | `TileIceCream` | `src/main/java/mods/defeatedcrow/common/tile/TileIceCream.java:1` | blockIcecream | アイスクリーム |
| `TileWipeBox2` | `TileWipeBox2` | `src/main/java/mods/defeatedcrow/common/tile/TileWipeBox2.java:1` | wipeBox2 | 大型紙箱 |
| `TileRotaryDial` | `TileRotaryDial` | `src/main/java/mods/defeatedcrow/common/tile/TileRotaryDial.java:1` | rotaryDial | ロータリーダイヤル |
| `TileCocktail` | `TileCocktail` | `src/main/java/mods/defeatedcrow/common/tile/TileCocktail.java:1` | cocktail | カクテル ① |
| `TileCocktail2` | `TileCocktail2` | `src/main/java/mods/defeatedcrow/common/tile/TileCocktail2.java:1` | cocktail2 | カクテル ② |
| `TileLargeBottle` | `TileLargeBottle` | `src/main/java/mods/defeatedcrow/common/tile/TileLargeBottle.java:1` | largeBottle | 大瓶 |
| `TileEmptyBottle` | `TileEmptyBottle` | `src/main/java/mods/defeatedcrow/common/tile/TileEmptyBottle.java:1` | emptyBottle | 空瓶 |
| `TileChalcedonyLamp` | `TileCLamp` | `src/main/java/mods/defeatedcrow/common/tile/TileCLamp.java:1` | cLamp | 玉髄ランプ |
| `TileCordial` | `TileCordial` | `src/main/java/mods/defeatedcrow/common/tile/TileCordial.java:1` | cordial | コーディアル |
| `TileAlcoholCup` | `TileAlcoholCup` | `src/main/java/mods/defeatedcrow/common/tile/TileAlcoholCup.java:1` | alcoholCup | 酒カップ |
| `TileEvaporator` | `TileEvaporator` | `src/main/java/mods/defeatedcrow/common/tile/appliance/TileEvaporator.java:1` | evaporator | エバポレーター（Container/Guiあり） |
| `TileProcessor` | `TileProcessor` | `src/main/java/mods/defeatedcrow/common/tile/appliance/TileProcessor.java:1` | processor | プロセッサー（Container/Guiあり） |
| `TileAdvProcessor` | `TileAdvProcessor` | `src/main/java/mods/defeatedcrow/common/tile/appliance/TileAdvProcessor.java:1` | advProcessor | 高性能プロセッサー |
| `TileVegiBag` | `TileVegiBag` | `src/main/java/mods/defeatedcrow/common/tile/TileVegiBag.java:1` | vegiBag | 野菜袋 |
| `TileCardboard` | `TileCardBoard` | `src/main/java/mods/defeatedcrow/common/tile/TileCardBoard.java:1` | cardboard | 段ボール |
| `TileChalcedonyPanel` | `TileCPanel` | `src/main/java/mods/defeatedcrow/common/tile/TileCPanel.java:1` | chalcenonyPanel | 玉髄パネル |
| `TileIncenseBase` | `TileIncenseBase` | `src/main/java/mods/defeatedcrow/common/tile/TileIncenseBase.java:1` | incenseBase | お香台 |
| `TilePanG` | `TilePanG` | `src/main/java/mods/defeatedcrow/common/tile/appliance/TilePanG.java:1` | emptyPanGaiden | 鍋 G |
| `TileBarrel` | `TileBrewingBarrel` | `src/main/java/mods/defeatedcrow/common/tile/TileBrewingBarrel.java:1` | barrel | 醸造樽 |
| `TileChargerBase` | `TileChargerBase` | `src/main/java/mods/defeatedcrow/common/tile/energy/TileChargerBase.java:1` | batBox | 蓄電基底（ContainerBatBox/GuiBatBox） |
| `TileChargerDevice` | `TileChargerDevice` | `src/main/java/mods/defeatedcrow/common/tile/energy/TileChargerDevice.java:1` | yuzuBat/gelBat | 蓄電デバイス |
| `TileFlowerPot` | `TileFlowerPot` | `src/main/java/mods/defeatedcrow/common/tile/TileFlowerPot.java:1` | flowerPot | 植木鉢 |
| `TileGelBattery` | `TileGelBat` | `src/main/java/mods/defeatedcrow/common/tile/energy/TileGelBat.java:1` | gelBat | ゲル電池 |
| `TileTeppanII` | `TileTeppanII` | `src/main/java/mods/defeatedcrow/common/tile/appliance/TileTeppanII.java:1` | teppanII | 鉄板 II |
| `TileCocktailSP` | `TileCocktailSP` | `src/main/java/mods/defeatedcrow/common/tile/TileCocktailSP.java:1` | cocktailSP | カクテル SP |
| `TileEHandle` | `TileHandleEngine` | `src/main/java/mods/defeatedcrow/common/tile/energy/TileHandleEngine.java:1` | handleEngine | ハンドル発電機 |
| `TileBowlRack` | `TileBowlRack` | `src/main/java/mods/defeatedcrow/common/tile/TileBowlRack.java:1` | bowlRack | 碗ラック |
| `TileDCContainerBase` | `TileContainerBase` | `src/main/java/mods/defeatedcrow/common/tile/TileContainerBase.java:1` | container* | コンテナ基底 |
| `TileDCCrowDoll` | `TileCrowDoll` | `src/main/java/mods/defeatedcrow/common/tile/TileCrowDoll.java:1` | crowDoll | カラス人形 |

## GUI を持つ TileEntity

| Tile | Container | Gui | GUI ID |
|---|---|---|---|
| TileIceMaker | `ContainerIceMaker:1` | `GuiIceMaker:1` | 2 |
| TileProcessor | `ContainerProcessor:1` | `GuiProcessor:1` | 3 |
| TileEvaporator | `ContainerEvaporator:1` | `GuiEvaporator:1` | 4 |
| TileAdvProcessor | `ContainerAdvProcessor:1` | `GuiAdvProcessor:1` | 5 |
| TileChargerBase | `ContainerBatBox:1` | `GuiBatBox:1` | 6 |

登録箇所: `DCsAppleMilk.java:364-370` + `CommonProxy.getServerGuiElement/getClientGuiElement:1`

## レンダー
TileEntityRenderer は `ClientProxy.registerTileEntity` ではなく `ClientProxy.registerRenderers()` でISBRH経由で描画。例:
- `TileEntityWipeBoxRenderer`, `TileEntityVegiBagRenderer`, `TileEntitySoupPanRenderer` 等  
  `src/main/java/mods/defeatedcrow/client/model/tileentity/*` に多数存在（約30種）。

## 移行ドキュメント
- [個別ページ索引](./tile-entities/README.md) - 45個別ページの一覧
- [移行ガイド](./tile-entities/migration-guide.md) - TileEntity移行
- [テンプレ](./tile-entities/_template.md)

## 関連ドキュメント
- [Block 一覧](./blocks.md)
- [Block 個別ページ索引](./blocks/README.md)
- [Entities](./entities.md)
- [Fluid 一覧](./fluids.md)
- [Potion 一覧](./potions.md)
