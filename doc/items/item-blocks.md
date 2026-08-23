
# ItemBlock 一覧（Blockに付随するItem）

> 対象: `src/main/java/mods/defeatedcrow/common/block/**/Item*.java` + `common/block/**/Item*.java` + `common/fluid/Item*.java`（`ItemBlock` 継承クラス）
> 登録: `MaterialRegister.java:316-410` で `GameRegistry.registerBlock(Block, ItemBlock.class, "defeatedcrow.xxx")` により同時登録
> 参照: [Block 一覧](../blocks.md) / [Item 一覧](../items.md) / [Fluid 一覧](../fluids.md)

## 概要
`ItemBlock` はBlockをインベントリで扱うためのItem表現。1.7.10では `GameRegistry.registerBlock` の第2引数で `ItemBlock` クラスを指定し同時登録、1.12+では分離登録（`RegistryEvent.Register<Block>` と `RegistryEvent.Register<Item>` で別々に `new BlockItem(block, props).setRegistryName(block.getRegistryName())`）。

本MODでは以下の `ItemBlock` 系クラスが存在（全36クラス）。詳細は各Blockページ（`doc/blocks/*.md` 未生成の場合は `blocks.md` 表）を参照。

## 一覧

| ItemBlockクラス | 継承 | 対応Blockフィールド | レジストリ名 | 実装インターフェース | ソース |
|---|---|---|---|---|---|
| `ItemAppleBox` | `ItemBlock` | `appleBox` | `defeatedcrow.AppleBox` | `ICompressedItem` | `src/main/java/mods/defeatedcrow/common/block/container/ItemAppleBox.java:1` |
| `ItemAppliance` | `ItemBlock` | `barrel` | `defeatedcrow.blockBarrel` | `-` | `src/main/java/mods/defeatedcrow/common/block/appliance/ItemAppliance.java:1` |
| `ItemBatBox` | `ItemBlock` | `yuzuBat` | `defeatedcrow.yuzuBatContainer` | `-` | `src/main/java/mods/defeatedcrow/common/block/energy/ItemBatBox.java:1` |
| `ItemBlockBottle` | `ItemBlock` | `largeBottle` | `defeatedcrow.largeBottle` | `-` | `src/main/java/mods/defeatedcrow/common/block/brewing/ItemBlockBottle.java:1` |
| `ItemBlockCordial` | `ItemBlock` | `cordial` | `defeatedcrow.blockCordial` | `-` | `src/main/java/mods/defeatedcrow/common/block/brewing/ItemBlockCordial.java:1` |
| `ItemBowlRack` | `ItemBlock` | `bowlRack` | `defeatedcrow.bowlRack` | `-` | `src/main/java/mods/defeatedcrow/common/block/ItemBowlRack.java:1` |
| `ItemBreadBasket` | `ItemBlock` | `Basket` | `defeatedcrow.basket` | `-` | `src/main/java/mods/defeatedcrow/common/block/ItemBreadBasket.java:1` |
| `ItemCLampOp` | `ItemBlock` | `cLampOpaque` | `defeatedcrow.chalcedonyLampOp` | `-` | `src/main/java/mods/defeatedcrow/common/block/ItemCLampOp.java:1` |
| `ItemCardboard` | `ItemBlock` | `cardboard` | `defeatedcrow.cardboardBox` | `ICompressedItem` | `src/main/java/mods/defeatedcrow/common/block/container/ItemCardboard.java:1` |
| `ItemCassisTree` | `ItemBlock` | `cassisTree` | `defeatedcrow.cassisTree` | `-` | `src/main/java/mods/defeatedcrow/common/block/plants/ItemCassisTree.java:1` |
| `ItemChalcedony` | `ItemBlock` | `chalcedony` | `defeatedcrow.chalcedony` | `-` | `src/main/java/mods/defeatedcrow/common/block/ItemChalcedony.java:1` |
| `ItemChalcedonyLamp` | `ItemBlock` | `cLamp` | `defeatedcrow.chalcedonyLamp` | `-` | `src/main/java/mods/defeatedcrow/common/block/ItemChalcedonyLamp.java:1` |
| `ItemCharcoalBox` | `ItemBlock` | `-` | `-` | `ICompressedItem` | `src/main/java/mods/defeatedcrow/common/block/container/ItemCharcoalBox.java:1` |
| `ItemChocoGift` | `EdibleItemBlock` | `chocoBlock` | `defeatedcrow.chocolateGift` | `-` | `src/main/java/mods/defeatedcrow/common/block/edible/ItemChocoGift.java:1` |
| `ItemChopsticksBox` | `ItemBlock` | `chopsticksBox` | `defeatedcrow.chopsticksBox` | `-` | `src/main/java/mods/defeatedcrow/common/block/ItemChopsticksBox.java:1` |
| `ItemClamSand` | `ItemBlock` | `clamSand` | `defeatedcrow.clamSand` | `-` | `src/main/java/mods/defeatedcrow/common/block/plants/ItemClamSand.java:1` |
| `ItemContainerBase` | `ItemBlock` | `-` | `-` | `-` | `src/main/java/mods/defeatedcrow/common/block/container/ItemContainerBase.java:1` |
| `ItemCrowDoll` | `ItemBlock` | `crowDoll` | `defeatedcrow.crowFigure` | `-` | `src/main/java/mods/defeatedcrow/common/block/ItemCrowDoll.java:1` |
| `ItemDummyFluid` | `ItemBlock` | `-` | `-` | `-` | `src/main/java/mods/defeatedcrow/common/fluid/ItemDummyFluid.java:1` |
| `ItemDummyFluid2` | `ItemBlock` | `-` | `-` | `-` | `src/main/java/mods/defeatedcrow/common/fluid/ItemDummyFluid2.java:1` |
| `ItemEggBasket` | `ItemBlock` | `eggBasket` | `defeatedcrow.eggBasket` | `-` | `src/main/java/mods/defeatedcrow/common/block/container/ItemEggBasket.java:1` |
| `ItemEmptyBottle` | `ItemBlock` | `emptyBottle` | `defeatedcrow.emptyBottle` | `-` | `src/main/java/mods/defeatedcrow/common/block/brewing/ItemEmptyBottle.java:1` |
| `ItemFilledSoupPan` | `ItemBlock` | `filledSoupPan` | `defeatedcrow.filledSoupPan` | `-` | `src/main/java/mods/defeatedcrow/common/block/appliance/ItemFilledSoupPan.java:1` |
| `ItemFlintBlock` | `ItemBlock` | `flintBlock` | `defeatedcrow.flintBlock` | `ICompressedItem` | `src/main/java/mods/defeatedcrow/common/block/ItemFlintBlock.java:1` |
| `ItemFlowerPot` | `ItemBlock` | `flowerPot` | `defeatedcrow.flowerPot` | `ICompressedItem` | `src/main/java/mods/defeatedcrow/common/block/container/ItemFlowerPot.java:1` |
| `ItemFlowerVase` | `ItemBlock` | `flowerBase` | `defeatedcrow.flowerVase` | `ICompressedItem` | `src/main/java/mods/defeatedcrow/common/block/container/ItemFlowerVase.java:1` |
| `ItemGelBat` | `BatteyItemBlockBase` | `gelBat` | `defeatedcrow.gelBatContainer` | `-` | `src/main/java/mods/defeatedcrow/common/block/energy/ItemGelBat.java:1` |
| `ItemGunpowderContainer` | `ItemBlock` | `-` | `-` | `ICompressedItem` | `src/main/java/mods/defeatedcrow/common/block/container/ItemGunpowderContainer.java:1` |
| `ItemHedge` | `ItemBlock` | `hedge` | `defeatedcrow.hedge` | `ICompressedItem` | `src/main/java/mods/defeatedcrow/common/block/container/ItemHedge.java:1` |
| `ItemIceBlock` | `EdibleItemBlock` | `-` | `-` | `-` | `src/main/java/mods/defeatedcrow/common/block/edible/ItemIceBlock.java:1` |
| `ItemMachineBlock` | `ItemBlock` | `processor` | `defeatedcrow.processor` | `-` | `src/main/java/mods/defeatedcrow/common/block/appliance/ItemMachineBlock.java:1` |
| `ItemMelonBomb` | `ItemBlock` | `melonBomb` | `defeatedcrow.melonBomb` | `ICompressedItem` | `src/main/java/mods/defeatedcrow/common/block/container/ItemMelonBomb.java:1` |
| `ItemMobDropBox` | `ItemBlock` | `mobBlock` | `defeatedcrow.mobDropBox` | `ICompressedItem` | `src/main/java/mods/defeatedcrow/common/block/container/ItemMobDropBox.java:1` |
| `ItemMushBox` | `ItemBlock` | `mushroomBox` | `defeatedcrow.mushroomBox` | `ICompressedItem` | `src/main/java/mods/defeatedcrow/common/block/container/ItemMushBox.java:1` |
| `ItemSilkyMelon` | `ItemBlock` | `silkyMelon` | `defeatedcrow.melonSilky` | `ICompressedItem` | `src/main/java/mods/defeatedcrow/common/block/container/ItemSilkyMelon.java:1` |
| `ItemTeaSapling` | `ItemBlock` | `saplingTea` | `defeatedcrow.saplingTea` | `-` | `src/main/java/mods/defeatedcrow/common/block/plants/ItemTeaSapling.java:1` |
| `ItemTeaTree` | `ItemBlock` | `teaTree` | `defeatedcrow.teaTree` | `-` | `src/main/java/mods/defeatedcrow/common/block/plants/ItemTeaTree.java:1` |
| `ItemVegiBag` | `ItemBlock` | `vegiBag` | `defeatedcrow.VegiBag` | `ICompressedItem` | `src/main/java/mods/defeatedcrow/common/block/container/ItemVegiBag.java:1` |
| `ItemWipeBox` | `ItemBlock` | `wipeBox` | `defeatedcrow.wipeBox` | `ICompressedItem` | `src/main/java/mods/defeatedcrow/common/block/container/ItemWipeBox.java:1` |
| `ItemWipeBox2` | `ItemBlock` | `wipeBox2` | `defeatedcrow.wipeBox2` | `-` | `src/main/java/mods/defeatedcrow/common/block/container/ItemWipeBox2.java:1` |
| `ItemWoodBox` | `ItemBlock` | `woodBox` | `defeatedcrow.WoodBox` | `ICompressedItem` | `src/main/java/mods/defeatedcrow/common/block/container/ItemWoodBox.java:1` |
| `ItemWoodPanel` | `ItemBlock` | `woodPanel` | `defeatedcrow.woodPanel` | `-` | `src/main/java/mods/defeatedcrow/common/block/ItemWoodPanel.java:1` |
| `ItemYuzuLeaves` | `ItemBlock` | `leavesYuzu` | `defeatedcrow.leavesYuzu` | `-` | `src/main/java/mods/defeatedcrow/common/block/plants/ItemYuzuLeaves.java:1` |

## 1.7.10 → 1.12+ 移行メモ（ItemBlock）

| 項目 | 1.7.10 | 1.12+ |
|---|---|---|
| 登録 | `GameRegistry.registerBlock(block, ItemBlock.class, "defeatedcrow.xxx")` | `RegistryEvent.Register<Block>` で `block.setRegistryName("defeatedcrow","xxx")` + `RegistryEvent.Register<Item>` で `new BlockItem(block, new Item.Properties().tab(...)).setRegistryName(block.getRegistryName())` |
| クラス | `ItemBlock` | `BlockItem` (`net.minecraft.world.item.BlockItem`) にリネーム (1.12で `ItemBlock` → `BlockItem`) |
| メタ対応 | `getUnlocalizedName(ItemStack)` / `getMetadata` / `getSubBlocks` | `fillItemCategory` / `getDescriptionId` / `BlockState` へ。`ICompressedItem.getDisassembledItem` は維持可だがレシピtype化推奨 |
| モデル | `registerIcons` / `IIcon` はBlock側で管理 | `assets/defeatedcrow/models/block/*.json` + `assets/defeatedcrow/models/item/*.json` (`parent: defeatedcrow:block/xxx`) |
| クリエイティブタブ | `setCreativeTab` (Block側) | `Item.Properties.tab` (BlockItem側) |

> 詳細は `doc/blocks/_template.md` の「移行チェックリスト」および各Block個別ページを参照。

## 関連
- [Item 個別ページ索引](./README.md) - 登録Item 53種
- [Item テンプレート](./_template.md)
- [移行ガイド](./migration-guide.md)
- [Block 一覧](../blocks.md)
