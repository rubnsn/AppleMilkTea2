# Block/Item モデル 1.20.1 JSON変換 対応表（1.7.10コードベース → JSON）

> 生成: 2026-08-24 WT0 横断対応 / 正本: `doc/blocks/migration-guide.md:206` / `plan.md:3.2`
> 対象: `ModBlocks 72` + `ModItems 133` / masterは `src/main/resources/assets/models` 不在・全て `registerBlockIcons`+`ISBRH/TESR` コードレンダー
> 現状 `dev` は全72が `minecraft:block/cube_all` スタブ (`assets/defeatedcrow/models/block/*.json:2`)、BER 38+44cutoutは `client/ModClientEvents.java:22` コメントアウト

## 凡例

- **旧Render**: `ISBRH`=`RenderingRegistry.registerBlockHandler` 44 (`ClientProxy.java:198`) / `TESR`=`bindTileEntitySpecialRenderer` 38 (`ClientProxy.java:120`) / `plain` = `isOpaqueCube:true` でコードレンダー無し
- **Target parent**: 1.20.1 JSON `models/block/*.json` の `parent`
  - `cube_all` = 単純立方体 / `cube_column` = 柱 / `cross` = 十字植物 / `crop` = 作物stage / `leaves` = 葉 / `fence`/`panel` = 柵/板
  - `ber_cube` = 最小 `cube` (cutout) + `BlockEntityRenderer` で形状再現（旧 TESR/ISBRH の置換）
- **BER**: `○` = `BlockEntityRenderer` 要 (`client/model/tileentity/TileEntityXxxRenderer.java:1` → 1.20.1 `BlockEntityRendererProvider`) / `×` = JSONのみ
- **RenderType**: `cutout`/`translucent`/`cutout_mipped` は `ModClientEvents.onClientSetup` の `ItemBlockRenderTypes.setRenderLayer`

## Block 72 対応表

| # | registry (`ModBlocks`) | カテゴリ | 旧クラス (master) | 旧Render | 旧テクスチャ (`registerBlockIcons` 主) | 現 `models/block` parent | Target parent | BER | RenderType | 状態 |
|---|---|---|---|---|---|---|---|---|---|---|
| 1 | `apple_box` | container | `BlockAppleBox.java:27` | plain | `AppleBox.png` | `cube_all` ok | `cube_all` | × | solid | 要テクスチャ紐付け再確認（現 `applebox` 正） |
| 2 | `wood_box` | container | `BlockWoodBox.java:27` 13variant | plain | `WoodBox_*.png` / `WoodBoxside_*.png` 26枚 | `cube_all` (whitepanel誤) | `cube_all` (13variantはPropertyEnum化せず一旦oak代表) | × | solid | **要修正**: `all: woodbox_oak` に |
| 3 | `charcoal_box` | container | `BlockCharcoalBox.java:27` | plain | `container_charcoal_t.png` 等 | `cube_all` | `cube_all` | × | solid | ok |
| 4 | `gunpowder_container` | container | `BlockGunpowderContainer.java:27` | plain | `container_gunpowder_t.png` | `cube_all` | `cube_all` | × | solid | ok |
| 5 | `melon_bomb` | container | `BlockMelonBomb.java:27` | plain | `melonbox.png`/`melonbox_top.png` | `cube_all` | `cube_bottom_top` | × | solid | **要修正**: `cube_bottom_top` |
| 6 | `silky_melon` | container | `BlockSilkyMelon.java:27` | plain | `melonbox_silky.png` | `cube_all` | `cube_bottom_top` | × | solid | **要修正** |
| 7 | `mushroom_box` | container | `BlockMushBox.java:31` `EntityBlock` | TESR `TileVegiBag`? | `bag_*`? 実際は `mushbox` テクスチャ無し | `cube_all` | `ber_cube` | ○ `TileVegiBag` | cutout | **要BER** |
| 8 | `vegi_bag` | container | `BlockVegiBag.java:31` `EntityBlock` | TESR `TileVegiBag` + ISBRH `RenderWipeBox`? | `bag_*_t.png` 11種 | `cube_all` | `ber_cube` | ○ `TileVegiBag` | cutout | **要BER** |
| 9 | `wipe_box` | container | `BlockWipeBox.java:31` | TESR `TileWipeBox` + ISBRH `RenderWipeBox` | `wipes_t.png`/`wipes_s1.png` 等 | `cube_all` | `ber_cube` | ○ `TileWipeBox` | cutout | **要BER** |
| 10 | `wipe_box2` | container | `BlockWipeBox2.java:31` | TESR `TileWipeBox2` | `wipe2.png` | `cube_all` | `ber_cube` | ○ `TileWipeBox2` | cutout | **要BER** |
| 11 | `egg_basket` | container | `BlockEggBasket.java:31` | TESR `TileEggs` + ISBRH `RenderEggsBasket` | `basket_t1.png`/`basket_b0.png` | `cube_all` | `ber_cube` | ○ `TileEggs` | cutout | **要BER** |
| 12 | `cardboard` | container | `BlockCardboard.java:31` | TESR `TileCardBoard` | `cardboard_t.png`/`cardboard_b.png`/`cardboard_s_*.png` 8種 | `cube_all` | `ber_cube` | ○ `TileCardBoard` | cutout | **要BER** |
| 13 | `apple_box` 以外の `container` 系 | | | | | | | | | |
| 14 | `container_saddle` | container | `BlockContainerSaddle.java:27` | plain | `containeritem_saddle.png` | `cube_all` | `cube_all` | × | solid | ok |
| 15 | `container_water_bottle` | container | `BlockContainerWaterBottle.java:27`  ISBRH `RenderContainerWBottle` | ISBRH | `containeritem_bottlew.png` | `cube_all` | `ber_cube`? 実際はTESR無しだがISBRHあり | △ | cutout | **要cutout** |
| 16 | `flower_pot` | container | `BlockFlowerPot.java:31` | TESR `TileFlowerPot` + ISBRH `RenderFlowerPot` | `flowerpot_red.png` | `cube_all` | `ber_cube` | ○ `TileFlowerPot` | cutout | **要BER** |
| 17 | `flower_vase` | container | `BlockFlowerVase.java:27` | ISBRH `RenderFlowerVase` | `basket_*`? | `cube_all` | `ber_cube`? | △ | cutout | **要BER/cutout** |
| 18 | `hedge` | container | `BlockHedge.java:27` | ISBRH `RenderHedge` | `hedge` テクスチャ無し(葉) | `cube_all` | `fence`相当の `cube` + VoxelShape | × | cutout | **要fence化** |
| 19 | `mob_block` | container | `BlockMobDrop.java:27` | plain | `mobbox_*.png` 5種 | `cube_all` | `cube_all` | × | solid | ok |
| 20 | `basket` | decorative | `BlockBasket.java:27` | TESR `TileBread` + ISBRH `RenderBreadBasket` | `basket_t0.png` | `cube_all` | `ber_cube` | ○ `TileBread` | cutout | **要BER** |
| 21 | `bowl_rack` | decorative | `BlockBowlRack.java:27` | TESR `TileBowlRack` + ISBRH `RenderBowlRack` | `woodpanel.png` | `cube_all` | `ber_cube` | ○ `TileBowlRack` | cutout | **要BER** |
| 22 | `chopsticks_box` | decorative | `BlockChopsticksBox.java:27` | TESR `TileChopsticksBox` + ISBRH `RenderChopsticksBox` | `chopsticks` テクスチャ | `cube_all` | `ber_cube` | ○ `TileChopsticksBox` | cutout | **要BER** |
| 23 | `chalcedony` | chalcedony | `BlockChalcedony.java:27` | plain `BlockBreakable` | `chalcedony*.png` 8種 | `cube_all` | `cube_all` (`translucent`) | × | translucent | **要renderType translucent** |
| 24 | `chalcedony_lamp` | chalcedony | `BlockChalcedonyLamp.java:27` `TileCLamp` | TESR `TileCLamp` + ISBRH `RenderChalcedonyLamp` | `lampside_*.png` | `cube_all` (chalcedony誤) | `ber_cube` | ○ `TileCLamp` | cutout | **要BER** |
| 25 | `chalcedony_lamp_op` | chalcedony | `BlockChalcedonyLampOp.java:27` | ISBRH `RenderCLampOp` | `chalcedony_opaq*.png` | `cube_all` | `cube_all` | × | translucent | **要修正**: `cube_all` + translucent |
| 26 | `chalcedony_panel` | chalcedony | `BlockCPanel.java:27` | TESR `TileCPanel` + ISBRH `RenderCPanel` | `chalcedony*.png` | `cube_all` | `ber_cube`/`panel` | ○ `TileCPanel` | cutout | **要BER/panel** |
| 27 | `crow_doll` | chalcedony | `BlockCrowDoll.java:27` | TESR `TileCrowDoll` + ISBRH? | `crowdoll.png` | `cube_all` | `ber_cube` | ○ `TileCrowDoll` | cutout | **要BER** |
| 28 | `flint_block` | chalcedony | `BlockFlint.java:27` | plain | `chalcedony_opaq`? | `cube_all` | `cube_all` | × | solid | ok |
| 29 | `incense_base` | decorative | `BlockIncenseBase.java:27` | TESR `TileIncenseBase` + ISBRH `RenderIncenseBase` | `wipes`? | `cube_all` | `ber_cube` | ○ `TileIncenseBase` | cutout | **要BER** |
| 30 | `rotary_dial` | decorative | `BlockRotaryDial.java:27` | TESR `TileRotaryDial` + ISBRH `RenderDial` | `rotarydial_block.png` | `cube_all` | `ber_cube` | ○ `TileRotaryDial` | cutout | **要BER** |
| 31 | `wood_panel` | decorative | `BlockWoodPanel.java:27` | ISBRH `RenderWoodPanel` | `woodpanel*.png` 4種 | `cube_all` | `panel`/`cube` | × | cutout | **要panel化** |
| 32 | `yuzu_fence` | decorative | `BlockYuzuFence.java:27` | ISBRH `RenderYuzuFence` | `yuzufence.png` | `cube_all` | `fence` | × | cutout | **要fence化** |
| 33 | `barrel` | brewing | `BlockBarrel.java:31` | TESR `TileBrewingBarrel` | `barrel.png` | `cube_all` | `ber_cube` | ○ `TileBrewingBarrel` | cutout | **要BER** |
| 34 | `cordial` | brewing | `BlockCordial.java:31` | TESR `TileCordial` + ISBRH `RenderCordial` | `cordial_inner_*.png`/`cordial_drink*.png` | `cube_all` | `ber_cube` | ○ `TileCordial` | cutout/translucent | **要BER** |
| 35 | `empty_bottle` | brewing | `BlockEmptyBottle.java:31` | TESR `TileEmptyBottle` | `bottle_empty.png` | `cube_all` | `ber_cube` | ○ `TileEmptyBottle` | cutout | **要BER** |
| 36 | `large_bottle` | brewing | `BlockLargeBottle.java:31` | TESR `TileLargeBottle` + ISBRH `RenderLargeBottle` | `bottle_*` | `cube_all` | `ber_cube` | ○ `TileLargeBottle` | cutout | **要BER** |
| 37 | `alcohol_cup` | edible | `BlockAlcoholCup.java:31` | TESR `TileAlcoholCup` + ISBRH `RenderAlcoholCup` | `summercup_side.png` | `cube_all` | `ber_cube` | ○ `TileAlcoholCup` | cutout | **要BER** |
| 38 | `bowl_block` | edible | `BlockBowl.java:27` | ISBRH `RenderFilledBowl`? | `porcelain.png` | `cube_all` | `ber_cube`? | △ | cutout | **要BER/cutout** |
| 39 | `bowl_jp` | edible | `BlockBowlJP.java:31` | TESR `TileJPBowl` + ISBRH `RenderFilledBowlJP` | `porcelain.png` | `cube_all` | `ber_cube` | ○ `TileJPBowl` | cutout | **要BER** |
| 40 | `choco_block` | edible | `BlockChocoGift.java:27` | ISBRH `RenderChocoPan` | `chocogift.png` | `cube_all` | `ber_cube` | △ | cutout | **要cutout** |
| 41 | `cocktail` | edible | `BlockCocktail.java:31` | TESR `TileCocktail` + ISBRH `RenderCocktail` | `contents_cocktailbase.png` | `cube_all` | `ber_cube` | ○ `TileCocktail` | cutout | **要BER** |
| 42 | `cocktail2` | edible | `BlockCocktail2.java:31` | TESR `TileCocktail2` | `contents` | `cube_all` | `ber_cube` | ○ `TileCocktail2` | cutout | **要BER** |
| 43 | `cocktail_sp` | edible | `BlockCocktailSP.java:31` | TESR `TileCocktailSP` | `contents` | `cube_all` | `ber_cube` | ○ `TileCocktailSP` | cutout | **要BER** |
| 44 | `filled_cup` | edible | `BlockFilledCup.java:31` | TESR `TileCupHandle`+ ISBRH `RenderFilledCup` | `cup_*.png` | `cube_all` | `ber_cube` | ○ `TileCupHandle` | cutout | **要BER** |
| 45 | `filled_cup2` | edible | `BlockFilledCup2.java:31` | TESR `TileHasRemain2`? | `cup` | `cube_all` | `ber_cube` | ○ | cutout | **要BER** |
| 46 | `food_plate` | edible | `BlockFoodPlate.java:31` | TESR `TileSteak` + ISBRH `RenderFoodPlate` | `porcelain.png` | `cube_all` | `ber_cube` | ○ `TileSteak` | cutout | **要BER** |
| 47 | `ice_cream_block` | edible | `BlockIceCream.java:31` | TESR `TileIceCream` + ISBRH `RenderIceCream` | `contents` | `cube_all` | `ber_cube` | ○ `TileIceCream` | cutout | **要BER** |
| 48 | `adv_processor` | appliance | `BlockAdvProcessor.java:31` | TESR `TileAdvProcessor` + ISBRH `RenderJawCrusher` | `teppann.png` 等 | `cube_all` | `ber_cube` | ○ `TileAdvProcessor` | cutout | **要BER** |
| 49 | `empty_cup` | appliance | `BlockEmptyCup.java:31` | ISBRH `RenderEmptyCup` | `cup_empty.png` | `cube_all` | `ber_cube` | △ | cutout | **要cutout/BER** |
| 50 | `empty_pan_g` | appliance | `BlockEmptyPanG.java:31` | TESR `TilePanG` | `teppann.png`/`porcelain` | `cube_all` | `ber_cube` | ○ `TilePanG` | cutout | **要BER** |
| 51 | `evaporator` | appliance | `BlockEvaporator.java:31` | TESR `TileEvaporator` + ISBRH `RenderEvaporator` | `icemaker_body.png` | `cube_all` | `ber_cube` | ○ `TileEvaporator` | cutout | **要BER** |
| 52 | `filled_soup_pan` | appliance | `BlockFilledSoupPan.java:31` | TESR `TileFilledSoupPan` + ISBRH `RenderSoupPanFilled` | `contents_soup.png` | `cube_all` | `ber_cube` | ○ `TileFilledSoupPan` | cutout | **要BER** |
| 53 | `ice_maker` | appliance | `BlockIceMaker.java:31` | TESR `TileIceMaker` + ISBRH `RenderIceMaker` | `icemaker_body.png` | `cube_all` | `ber_cube` | ○ `TileIceMaker` | cutout | **要BER** |
| 54 | `processor` | appliance | `BlockProcessor.java:31` | TESR `TileProcessor` + ISBRH `RenderProcessor` | `teppann.png` | `cube_all` | `ber_cube` | ○ `TileProcessor` | cutout | **要BER** |
| 55 | `tea_maker_black` | appliance | `BlockTeaMakerBlack.java:27` (extends Next) | ISBRH `RenderTeaMakerNext` | `whitepanel.png` | `cube_all` (whitepanel誤) | `ber_cube` | ○ `TileMakerNext` | cutout | **要BER** |
| 56 | `tea_maker_next` | appliance | `BlockTeaMakerNext.java:31` | TESR `TileMakerNext` + ISBRH `RenderTeaMakerNext` + `RenderAutoMaker` | `whitepanel.png` + `cordial` | `cube_all` (whitepanel) | `ber_cube` | ○ `TileMakerNext` | cutout | **要BER** |
| 57 | `teppan_ii` | appliance | `BlockTeppanII.java:31` | TESR `TileTeppanII` + ISBRH `RenderTeppann` | `teppann.png` | `cube_all` | `ber_cube` | ○ `TileTeppanII` | cutout | **要BER** |
| 58 | `bat_box` | energy | `BlockBatBox.java:31` | TESR `TileChargerDevice`? | `charger_f.png` | `cube_all` | `ber_cube` | ○ `TileChargerDevice` | cutout | **要BER** |
| 59 | `gel_bat` | energy | `BlockGelBat.java:31` | TESR `TileGelBat` + ISBRH `RenderGelBat` | `lightgel.png` | `cube_all` | `ber_cube` | ○ `TileGelBat` | cutout/translucent | **要BER** |
| 60 | `handle_engine` | energy | `BlockHandleEngine.java:31` | TESR `TileHandleEngine` + ISBRH `RenderEHandle` | `charger_f.png` | `cube_all` | `ber_cube` | ○ `TileHandleEngine` | cutout | **要BER** |
| 61 | `red_gel` | energy | `BlockRedGel.java:27` | ISBRH `RenderGelBat`? plain | `redgel.png` | `cube_all` | `cube_all` translucent | × | translucent | **要translucent** |
| 62 | `yuzu_bat` | energy | `BlockYuzuBat.java:27` | ISBRH `RenderYuzuBat` | `container_yuzubat_s.png` | `cube_all` | `cube_all` | × | solid | ok? |
| 63 | `yuzu_light` | energy | `BlockYuzuLight.java:27` | plain | `yuzufence.png`? | `cube_all` | `cube_all` | × | solid/light | ok |
| 64 | `cassis_tree` | plants | `BlockCassisTree.java:27` | ISBRH `RenderCassisTree` + `IShearable` | `cassisleaf_*.png` 4枚 | `cube_all` (cassisleaf_0) | `cross` | × | cutout | **要cross** |
| 65 | `tea_tree` | plants | `BlockTeaTree.java:27` | ISBRH `RenderTeaTree` + `IShearable` | `tealeaf.png`/`tealog.png` | `cube_all` (tea_tree) | `cross` + `cube_column` (log混在) | × | cutout | **要cross** |
| 66 | `sapling_tea` | plants | `BlockSaplingTea.java:27` | plain `IPlantable` | `sapling_tea.png` | `cube_all` | `cross` | × | cutout | **要cross** |
| 67 | `sapling_yuzu` | plants | `BlockYuzuSapling.java:27` | plain | `sapling_yuzu.png` | `cube_all` | `cross` | × | cutout | **要cross** |
| 68 | `crop_mint` | plants | `BlockMintCrop.java:5` `CropBlock` | ISBRH `RenderKinoko`? | `crop_mint_stage_0..3.png` | `cube_all` (stage3) | `crop` stage 0-3 | × | cutout | **要crop** |
| 69 | `leaves_yuzu` | plants | `BlockYuzuLeaves.java:27` | plain | `leaves_yuzu_*.png` 3種 | `cube_all` | `cube_all`/`leaves` | × | cutout_mipped | **要cutout_mipped** |
| 70 | `log_yuzu` | plants | `BlockYuzuLog.java:27` | plain | `log_yuzu_side.png`/`log_yuzu_top.png` | `cube_all` | `cube_column` | × | solid | **要cube_column** |
| 71 | `clam_sand` | plants | `BlockClamSand.java:27` | plain | `clam_sand` テクスチャ? | `cube_all` | `cube_all` | × | solid | ok |
| 72 | `block_vegi_oil` / `block_camellia_oil` | fluid | `BlockOilFluid`等 | Fluid `FlowingFluid` | `fluid/oil_still` | `cube_all` 正 | `cube_all` 正 | × | translucent | **完了** |

> **集計**: `cube_all`単純 12 / `cross/crop/column/fence/panel` 15 / `ber_cube`(BER要) 38 / 流体2 =72。現状72全て `ber_cube` にすべき 38が `cube_all` のまま。

## Item 133 対応

- `models/item/*.json:133` は全 `minecraft:item/generated` 1層でテクスチャ `defeatedcrow:item/xxx`。小文字化では `appletart.png` 等のズレを今回修正済だが `toffyapple.png` vs `toffy_apple.json` 等 15件は要注意（実体 `toffyapple.png` に合わせる）。
- BEWLR 5件 (`yuzu_gatling`/`fossil_cannon`/`eightEyesArm`/`cocktail_sp`/`handle_engine`) は `client/item/RenderItemXxx.java:1` の `IItemRenderer` → 1.20.1 `BlockEntityWithoutLevelRenderer` へ。現状 `generated` で暫定表示だが本来は `parent: "minecraft:item/handheld"` + BEWLR。
- `ModItems.TART` 等の `layer0` は `textures/item/appletart.png` 等と一致確認済み (`Select-String` で `layer0` と実PNG照合で 0 mismatch は前回達成)。

## 次ステップ（WT0横断）

1. `models/block` 27件の緊急修正: 植物8 (`cross`/`crop`/`column`) + フェンス/パネル7 + 流体以外の `ber_cube` 38のうち `craft` 的に `cube_bottom_top` 2件 (`melon_bomb`/`silky_melon`) を先に JSON 化。残りBER 38は最小 `cube` (cutout) で形状はBER側で補うため JSONは切替だけでよい。
2. `ModClientEvents.java:22` の 38 BER + 44 cutout をアンコメントし `ItemBlockRenderTypes.setRenderLayer` と `BlockEntityRenderers.register` を復活。
3. `assets/defeatedcrow/textures/block` の `hedge` 等サブフォルダのテクスチャ参照を `cross` 等に正す。

