# Block 移行ガイド - Minecraft 1.7.10 → 1.12.2 / 1.16.5 / 1.20.1

> 対象: `AppleMilkTea2 (DCsAppleMilk 2.9m)` / Minecraft `1.7.10` (Forge `10.13.4.1614`) → `1.12.2` (Forge `14.23.5.2860`) → `1.16.5` / `1.18.2`  
> 最終更新: 2026-08-24（1.20.1ブラッシュアップ: 2026-08-24）  
> 関連: [ビルド移行ガイド](../build.md)  
> 関連: [Block 一覧](../blocks.md) / [個別ページ索引](./README.md) / [TileEntity 一覧](../tile-entities.md)

## 概要
AppleMilkTea2 の全 `74 Block` （`DCsAppleMilk.java:152-340` の static フィールド）を 1.7.10 から 1.12.2 以降へ移行する際の **メソッド参照・シグネチャ変更・登録処理** を整理する。個別ページ（`doc/blocks/<category>/Block*.md` 74件）の `移行 (1.12.2+)` 列の詳細版。

## 移行の大局（3段階）

### 1. 登録処理の分離
| 1.7.10 | 1.12.2+ | 参照先 |
|---|---|---|
| `DCsAppleMilk.field = new BlockXxx().setBlockName("defeatedcrow.xxx").setCreativeTab(...)` (`MaterialRegister.java:1039`) | `block = new BlockXxx(Block.Properties.create(Material.XXX).hardnessAndResistance(x,y).sound(...))` + `block.setRegistryName("defeatedcrow","xxx")` + `block.setTranslationKey("defeatedcrow.xxx")` | `DCsAppleMilk.java:152` / `MaterialRegister.java:238` |
| `GameRegistry.registerBlock(DCsAppleMilk.xxx, ItemXxx.class, "defeatedcrow.xxx")` (`MaterialRegister.java:316-411`) | `RegistryEvent.Register<Block> e` で `e.getRegistry().register(block)` / `DeferredRegister<Block>` | `MaterialRegister.java:316` |
| `GameRegistry.registerBlock(DCsAppleMilk.xxx, "defeatedcrow.xxx")` (ItemBlockなし) | 同上、ItemBlockは別イベントで `new ItemBlock(block, ...).setRegistryName(block.getRegistryName())` を `Register<Item>` で登録 | 同上 |
| `GameRegistry.findBlock("mod","name")` / `ItemAPI` | `ForgeRegistries.BLOCKS.getValue(new ResourceLocation("defeatedcrow","xxx"))` | `api/ItemAPI.java:54` |
| `setCreativeTab(DCsAppleMilk.applemilk)` | 維持だが `CreativeTabs` → 1.14+ `ItemGroup` | `DCsAppleMilk.java:144` |

### 2. BlockState / Property 化（メタデータ廃止）
| 1.7.10 | 1.12.2+ | 参照先 |
|---|---|---|
| `int meta = world.getBlockMetadata(x,y,z)` / `world.setBlockMetadataWithNotify(x,y,z, meta, 3)` | `IBlockState state = world.getBlockState(pos)` / `state.getValue(PROP)` / `world.setBlockState(pos, state.withProperty(PROP, value), 3)` | 全Blockの `onBlockActivated`, `updateTick` 等 |
| `damageDropped(int)` / `getSubBlocks(Item, CreativeTabs, List)` で `new ItemStack(block,1,meta)` 列挙 | `getStateFromMeta(int)` / `getMetaFromState(IBlockState)` / `createBlockState()` で `PropertyInteger/PropertyEnum/PropertyBool` 定義。`getSubBlocks` → `NonNullList` + `fillItemGroup` (1.14+) | `BlockWoodBox.java:40` / `BlockTeaTree.java:140` |
| `IIcon getIcon(int side, int meta)` / `registerBlockIcons(IIconRegister)` | 削除。`assets/defeatedcrow/blockstates/*.json` + `models/block/*.json` + `models/item/*.json` で JSON モデル化。`ModelLoader` 登録 | `BlockWoodBox.java:76` |
| `Blocks.water.getBlockTextureFromSide(0)` 等の `IIcon` 参照 | `ResourceLocation("minecraft","blocks/water_still")` 等のテクスチャ参照へ | `BlockTeaMakerNext.java:424` |

### 3. World/BlockPos/IBlockState 化（座標・状態の集約）
| 1.7.10 | 1.12.2+ |
|---|---|
| `World, int x, int y, int z` | `World, BlockPos, IBlockState` |
| `IBlockAccess, int x,y,z` | `IBlockAccess, BlockPos, IBlockState` |

---

## メソッド別移行対応表（参照先付き）

> 各メソッドの **定義元**、**呼出元**、**移行後のシグネチャ**、**対応例** をまとめる。`file_path:line_number` は 1.7.10 時点。

| メソッド | 1.7.10 シグネチャ / 定義例 | 呼出元 / タイミング | 移行後 (1.12.2) | 移行後 (1.16.5) | 対応例 / 参照 |
|---|---|---|---|---|---|
| `onBlockActivated` | `boolean onBlockActivated(World, int x,y,z, EntityPlayer, int side, float hitX,float hitY,float hitZ)` (`BlockTeaMakerNext.java:45`) | プレイヤー右クリック (`NetHandlerPlayServer.processPlayerBlockPlacement` → `PlayerInteractEvent`) | `boolean onBlockActivated(World, BlockPos, IBlockState, EntityPlayer, EnumHand, EnumFacing, float,float,float)` | `ActionResultType onUse(BlockState, World, BlockPos, PlayerEntity, Hand, BlockRayTraceResult)` | `world.getTileEntity(x,y,z)`→`world.getTileEntity(pos)` / `world.getBlockMetadata`→`state.getValue(PROP)` / `MinecraftForge.EVENT_BUS.post(new AMTBlockRightClickEvent(...))` は維持 |
| `createNewTileEntity` | `TileEntity createNewTileEntity(World, int meta)` (`BlockTeaMakerNext.java:332`) | `BlockContainer.createTileEntity` 経由、チャンクロード時 | `TileEntity createTileEntity(World, IBlockState)` + `boolean hasTileEntity(IBlockState)` | `TileEntity createTileEntity(BlockState, IBlockReader)` | `BlockContainer` 廃止。`extends Block implements ITileEntityProvider` へ。`CommonProxy.registerTileEntity`→`TileEntityType.Builder.create(Supplier, Block...)` → `DeferredRegister<TileEntityType>` |
| `registerBlockIcons` | `void registerBlockIcons(IIconRegister)` (`BlockWoodBox.java:76`) | クライアント `TextureStitchEvent.Pre` | **削除** | **削除** | `assets/defeatedcrow/textures/blocks/*.png` + `blockstates/*.json` + `models/block/*.json` で置換。`Util.getTexturePassNoAlt()` は `ResourceLocation` へ |
| `getIcon` | `IIcon getIcon(int side, int meta)` (`BlockWoodBox.java:40`) | レンダー時、面・メタで | **削除** | **削除** | `BlockState` の `Property` で `getStateFromMeta` / `getActualState` に分離。`getQuads` / `IBakedModel` へ |
| `getSubBlocks` | `void getSubBlocks(Item, CreativeTabs, List)` (`BlockWoodBox.java:52`) | CreativeTab描画時 | `void getSubBlocks(CreativeTabs, NonNullList<ItemStack>)` | `void fillItemGroup(ItemGroup, NonNullList)` | `par3List.add(new ItemStack(this,1,meta))` → `items.add(new ItemStack(this,1,meta))`。1.14+は `fillItemGroup` |
| `getDrops` | `ArrayList<ItemStack> getDrops(World, int x,y,z, int meta, int fortune)` (`BlockTeaTree.java:156`) | 破壊時 (`Block.getDrops` → `ForgeHooks`) | `void getDrops(NonNullList<ItemStack>, IBlockAccess, BlockPos, IBlockState, int fortune)` | `List<ItemStack> getDrops(BlockState, LootContext.Builder)` | `world.getBlockMetadata`→`state.getValue(...)`。`getItemDropped`/`quantityDropped`も `IBlockState` 版へ |
| `damageDropped` | `int damageDropped(int meta)` (`BlockTeaTree.java: - `) | ドロップ時のメタ決定 | `int damageDropped(IBlockState)` | `int damageDropped(BlockState)` は削除、単に `getStateFromMeta` 経由 | `return par1 & 7` 等のマスクは `PropertyInteger` に置換 |
| `getItemDropped` | `Item getItemDropped(int meta, Random, int fortune)` (`BlockTeaMakerNext.java:429`) | ドロップ時のItem決定 | `Item getItemDropped(IBlockState, Random, int fortune)` | `Item getItemDropped(BlockState, ...)` | `Item.getItemFromBlock(this)` → `this.asItem()` (1.12) / `this.asItem()` 維持 |
| `quantityDropped` | `int quantityDropped(Random)` (`BlockTeaTree.java:152`) | ドロップ数 | `int quantityDropped(IBlockState, int fortune, Random)` | 同上 | `random.nextInt(2)==0?1:2` 等は維持だがシグネチャ変更 |
| `isOpaqueCube` | `boolean isOpaqueCube()` (`BlockTeaMakerNext.java:317`) | 光伝播・描画判定 (`World.isBlockNormalCube`) | `boolean isOpaqueCube(IBlockState)` | `boolean isOpaqueCube(BlockState, IBlockReader, BlockPos)` / `isSolid` | `return false` 維持。`renderAsNormalBlock` と統合 |
| `renderAsNormalBlock` | `boolean renderAsNormalBlock()` (`BlockTeaMakerNext.java:322`) | 描画判定 | **削除** → `isFullCube(IBlockState)` / `isOpaqueCube` で代替 | `boolean isSolid` / `VoxelShape` | `return false` → `isFullCube` も `false` へ |
| `getRenderType` | `int getRenderType()` (`BlockTeaMakerNext.java:327`) 返り値 `DCsAppleMilk.modelXxx` (`DCsAppleMilk.java:408`) | `RenderBlocks` → ISBRH | `EnumBlockRenderType getRenderType(IBlockState) { return MODEL/INVISIBLE }` | `BlockRenderType getRenderType(BlockState)` | `DCsAppleMilk.modelXxx` の `getRenderID()` / `ClientProxy.registerRenderers()` (ISBRH) は `TileEntityRenderer` / `BlockEntityRenderer` + `EntityRendererRegistry` へ移行 |
| `isSideSolid` | `boolean isSideSolid(IBlockAccess, int x,y,z, ForgeDirection side)` (`BlockCrowDoll.java: - `) | 隣接ブロックの接続判定 (`BlockYuzuFence.canConnectBlock`) | `boolean isSideSolid(IBlockState, IBlockAccess, BlockPos, EnumFacing)` | `boolean isSideSolid(BlockState, IBlockReader, BlockPos, Direction)` | `ForgeDirection` → `EnumFacing` (1.12) → `Direction` (1.16) |
| `addCollisionBoxesToList` | `void addCollisionBoxesToList(World,int x,y,z, AxisAlignedBB, List, Entity)` (`BlockYuzuFence.java:1`) | 衝突判定構築 | `void addCollisionBoxToList(IBlockState, World, BlockPos, AxisAlignedBB, List, Entity, boolean)` | `VoxelShape getCollisionShape(BlockState, IBlockReader, BlockPos)` | `AxisAlignedBB.getBoundingBox` → `new AxisAlignedBB` / 1.16+ `VoxelShapes.create` / `Block.makeCuboidShape` |
| `getCollisionBoundingBoxFromPool` | `AxisAlignedBB getCollisionBoundingBoxFromPool(World,int x,y,z)` (`BlockTeaMakerNext.java:396`) | 衝突AABB取得 | `AxisAlignedBB getCollisionBoundingBox(IBlockState, IBlockAccess, BlockPos)` | `VoxelShape getCollisionShape` | `setBlockBoundsBasedOnState` 内で `setBlockBounds` → `getBoundingBox` へ |
| `getSelectedBoundingBoxFromPool` | `AxisAlignedBB getSelectedBoundingBoxFromPool(World,int x,y,z)` (`BlockTeaMakerNext.java:403`) | 選択AABB取得 | `AxisAlignedBB getSelectedBoundingBox(IBlockState, World, BlockPos)` | `VoxelShape getShape` / `getRenderShape` | 同上 |
| `setBlockBoundsBasedOnState` | `void setBlockBoundsBasedOnState(IBlockAccess,int x,y,z)` (`BlockTeaMakerNext.java:409`) | Bounds設定、AABB生成前に呼出 | `AxisAlignedBB getBoundingBox(IBlockState, IBlockAccess, BlockPos)` | `VoxelShape getShape` | `setBlockBounds(minX,minY,minZ,maxX,maxY,maxZ)` → `createCuboidShape` / `VoxelShape` |
| `onBlockAdded` | `void onBlockAdded(World,int x,y,z)` (`BlockTeaMakerNext.java:338`) | 設置直後、隣接更新前 | `void onBlockAdded(World, BlockPos, IBlockState)` | `void onBlockAdded(BlockState, World, BlockPos, BlockState, boolean)` | `world.setBlockMetadataWithNotify` → `world.setBlockState(pos, state.withProperty(...), 3)` |
| `onBlockPlacedBy` | `void onBlockPlacedBy(World,int x,y,z, EntityLivingBase, ItemStack)` (`BlockTeaMakerNext.java:373`) | 設置時、プレイヤー向きでメタ決定 | `void onBlockPlacedBy(World, BlockPos, IBlockState, EntityLivingBase, ItemStack)` | `void onBlockPlacedBy(World, BlockPos, BlockState, LivingEntity, ItemStack)` | `MathHelper.floor_double(yaw*4/360+0.5)&3` → `EnumFacing.fromAngle(yaw)` / `state.withProperty(FACING, ...)` |
| `breakBlock` | `void breakBlock(World,int x,y,z, Block, int meta)` (`BlockBatBox.java:113`) | 破壊時、TileのDrop散乱 + `notifyNeighbors` | `void breakBlock(World, BlockPos, IBlockState)` | `void onReplaced(BlockState, World, BlockPos, BlockState, boolean)` | `world.getTileEntity(x,y,z)` → `world.getTileEntity(pos)` / `world.func_147453_f` → `world.notifyNeighborsOfStateChange` / `world.updateComparatorOutputLevel` |
| `updateTick` | `void updateTick(World,int x,y,z, Random)` (`BlockTeaTree.java:53`) | ランダムティック (`setTickRandomly(true)`) | `void updateTick(World, BlockPos, IBlockState, Random)` | `void tick(BlockState, ServerWorld, BlockPos, Random)` / `randomTick` | `world.getBlockMetadata` → `state.getValue(...)` / `world.setBlockMetadataWithNotify` → `world.setBlockState` |
| `tickRate` | `int tickRate(World)` (`BlockIncenseBase.java: - `) | ティック間隔 | `int tickRate(World)` 維持 | `int getTickRate` → `scheduledTick` に統合 | `world.scheduleBlockUpdate(x,y,z, this, tickRate)` → `world.scheduleUpdate(pos, this, tickRate)` |
| `randomDisplayTick` | `void randomDisplayTick(World,int x,y,z, Random)` (`BlockIncenseBase.java:1`) | クライアントパーティクル | `void randomDisplayTick(IBlockState, World, BlockPos, Random)` | `void animateTick(BlockState, World, BlockPos, Random)` | `world.getBlockMetadata` → `state.getValue` / `FMLClientHandler.instance().getClient().effectRenderer.addEffect` → `Minecraft.getInstance().particles.addParticle` |
| `getLightValue` | `int getLightValue(IBlockAccess,int x,y,z)` (`BlockIncenseBase.java:1`) | 明るさ取得 | `int getLightValue(IBlockState, IBlockAccess, BlockPos)` | `int getLightValue(BlockState, IBlockReader, BlockPos)` | `world.getBlockMetadata` → `state.getValue` |
| `hasComparatorInputOverride` | `boolean hasComparatorInputOverride()` (`BlockTeaMakerNext.java:441`) | コンパレーター入力有無 | `boolean hasComparatorInputOverride(IBlockState)` | `boolean hasAnalogOutputSignal(BlockState)` | `return true` 維持 |
| `getComparatorInputOverride` | `int getComparatorInputOverride(World,int x,y,z, int side)` (`BlockTeaMakerNext.java:445`) | コンパレーター出力 (`Tile.remain`等) | `int getComparatorInputOverride(IBlockState, World, BlockPos)` | `int getAnalogOutputSignal(BlockState, World, BlockPos)` | `world.getTileEntity(pos)` 維持。`TileMakerNext.getRemain()` 等 |
| `onBlockClicked` | `void onBlockClicked(World,int x,y,z, EntityPlayer)` (`BlockCrowDoll.java:1`) | 左クリック | `void onBlockClicked(World, BlockPos, EntityPlayer)` | `void onBlockClicked(BlockState, World, BlockPos, PlayerEntity)` | `world.getTileEntity` → `pos` 版 |
| `onEntityCollidedWithBlock` | `void onEntityCollidedWithBlock(World,int x,y,z, Entity)` (`BlockYuzuFence.java:1`) | エンティティ接触 | `void onEntityCollision(World, BlockPos, IBlockState, Entity)` | `void onEntityCollision(BlockState, World, BlockPos, Entity)` | `DamageSource.cactus` 等は維持。`EntityLiving`→`LivingEntity` |
| `getEnchantPowerBonus` | `float getEnchantPowerBonus(World,int x,y,z)` (`BlockCrowDoll.java:1`) | エンチャント台のボーナス | `float getEnchantPowerBonus(World, BlockPos)` | 同上 | `return 5;` 維持 |
| `isShearable` | `boolean isShearable(ItemStack, IBlockAccess,int x,y,z)` (`BlockTeaTree.java:334`) | ハサミ収穫可否 (`IShearable`) | 維持 | 維持 | `return true` 維持 |
| `onSheared` | `ArrayList<ItemStack> onSheared(ItemStack, IBlockAccess,int x,y,z, int fortune)` (`BlockTeaTree.java:339`) | ハサミ収穫ドロップ | 維持 | 維持 | `new ItemStack(this,1,0)` → `new ItemStack(this.asItem(),1)` |
| `getPlantType` | `EnumPlantType getPlantType(IBlockAccess,int x,y,z)` (`BlockTeaTree.java:201`) | `IPlantable` 植物タイプ | 維持 | 維持 | `return Plains;` 維持 |
| `canSilkHarvest` | `boolean canSilkHarvest()` (`BlockTeaTree.java:187`) | シルクタッチ可否 | `boolean canSilkHarvest(World, BlockPos, IBlockState, EntityPlayer)` | 同上 | `return true` 維持 |
| `setBlockBounds` / `thisBoundingBox` | `void setBlockBounds(float, ...)` / `void thisBoundingBox(int meta)` (`BlockWoodPanel.java:1`) | AABBヘルパー | **削除** | **削除** | `VoxelShape` / `VoxelShapes` / `Block.makeCuboidShape` へ。`ForgeDirection` → `Direction` |

---

## 登録・TileEntity・レンダー移行

### Block/Item/TileEntity 登録の分離
```java
// 1.7.10 (MaterialRegister.java:316)
GameRegistry.registerBlock(DCsAppleMilk.teaMakerNext, ItemAppliance.class, "defeatedcrow.teaMakerNext");
GameRegistry.registerTileEntity(TileMakerNext.class, "TileMakerNext"); // CommonProxy.java:85

// 1.12.2
@SubscribeEvent
public void onBlocksRegistry(RegistryEvent.Register<Block> e){
  e.getRegistry().register(new BlockTeaMakerNext().setRegistryName("defeatedcrow","teamaker_next"));
}
@SubscribeEvent
public void onItemsRegistry(RegistryEvent.Register<Item> e){
  Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("defeatedcrow","teamaker_next"));
  e.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
}
@SubscribeEvent
public void onTileRegistry(RegistryEvent.Register<TileEntityType<?>> e){
  e.getRegistry().register(TileEntityType.Builder.create(TileMakerNext::new, block).build(null).setRegistryName("defeatedcrow","tile_maker_next"));
}
```

### ISBRH → TileEntityRenderer (BER)
- 1.7.10: `DCsAppleMilk.modelTeaMaker = proxy.getRenderID()` (`DCsAppleMilk.java:785`) → `ClientProxy.registerRenderers()` で `RenderingRegistry.registerBlockHandler(ISimpleBlockRenderingHandler)`
- 1.12.2: `ClientRegistry.bindTileEntitySpecialRenderer(TileMakerNext.class, new RenderTileMakerNext())` (TESR)
- 1.16.5: `EntityRendererRegistry` / `BlockEntityRenderer` + `RenderType` (`CUTOUT`, `TRANSLUCENT`)

---

## カテゴリ別の移行注意点

### Appliance (10) - `block/appliance/*`
- `BlockContainer` + `TileAppliance` 系。`onBlockActivated`でGUI (`player.openGui`) は `NetworkHooks.openGui` + `MenuProvider` へ。
- `hasComparatorInputOverride`/`getComparatorInputOverride` は `getAnalogOutputSignal` へ。
- `getRenderType` の ISBRH は TESR へ。`modelTeaMaker` 等のIDは削除。

### Energy (6) - `block/energy/*`
- `TileChargerBase`/`TileGelBat` 等の蓄電。`breakBlock`でNBT `charge` を `ItemStack` に保存する処理は `getDrops`/`onReplaced` へ移行。
- `TileHandleEngine` の発電ロジックは `CapabilityEnergy` (Forge Energy) へ置換検討。

### Edible (11) - `block/edible/*`
- `BlockFilledCup` 等は `TileCupHandle` で `directionByte` を保持。`onBlockPlacedBy`の `MathHelper.floor_double(yaw*4/360+0.5)&3` → `Horizontal FACING` Propertyへ。
- `EntityItem` 系 `ItemBlock` (`EntityItemTeaCup`等) は `PlaceableEntity` に投げて設置する現行ロジックを `BlockItem` + `UseOnBlock` へ。

### Brewing (4) - `block/brewing/*`
- `BlockLargeBottle` の `metadata &15 / >>4` 管理は `PropertyInteger` 2つに分割。
- `BlockDummyFluid` 2種は流体ブロックのダミー表示用。1.12+では `Fluid` の `BlockFluidBase` 継承か削除。

### Container (18) - `block/container/*`
- `BlockWoodBox` の13種、`BlockVegiBag`の10種等はメタ分岐。1.12+で `PropertyEnum` へ。
- `BlockGunpowderContainer` の `tickRate`/`updateTick` の雨・乾燥バイオーム判定は `World.isRaining` + `BiomeDictionary` → `World.isRainingAt` + `Biome` タグへ。

### Plants (8) - `block/plants/*`
- `BlockTeaTree` の `IShearable`/`IPlantable`/`IRightClickHarvestable` は維持。`updateTick` の成長は `randomTick` + `IGrowable` へ。
- `BlockClamSand` のハマグリ生成は `WorldGen` 維持。

### Decorative/Chalcedony (13) - `block/*`
- `BlockYuzuFence` の `addCollisionBoxesToList` は `VoxelShape` へ。`canConnectBlock` の `isSideSolid` は `BlockState.isFaceSturdy` へ。
- `BlockChalcedony` の `BlockBreakable` 継承は `AbstractGlassBlock` 等へ。
- `BlockCrowDoll` の `CoordListRegister` によるチャンク座標登録は `WorldSavedData` / `Capability` へ。

### Fluid (4) - `fluid/*`
- `BlockOilFluid`/`BlockCamOilFluid` は `BlockFluidClassic` → `FlowingFluid` + `FluidBlock` へ。
- `FluidContainerRegistry.registerFluidContainer` (バケツ/ボトル) は `FluidAttributes` + `BucketItem` へ。

---

## 移行チェックリスト（Block共通）

- [ ] `setBlockName` → `setTranslationKey` + `setRegistryName`
- [ ] `GameRegistry.registerBlock` → `RegistryEvent.Register<Block>` / `DeferredRegister`
- [ ] `BlockContainer` → `Block` + `hasTileEntity` / `createTileEntity`
- [ ] `IIcon`/`IIconRegister`/`getIcon`/`registerBlockIcons` → `BlockState` + JSONモデル
- [ ] `getBlockMetadata`/`setBlockMetadataWithNotify` → `IBlockState` + `Property*`
- [ ] `World, int x,y,z` → `World, BlockPos, IBlockState`
- [ ] `ForgeDirection` → `EnumFacing` (1.12) → `Direction` (1.16)
- [ ] `isOpaqueCube()`/`renderAsNormalBlock()`/`getRenderType()` → `isOpaqueCube(IBlockState)`/`isFullCube`/`getRenderType`/`getBlockLayer`
- [ ] `getDrops`/`quantityDropped`/`damageDropped` → `IBlockState` 版
- [ ] `breakBlock`/`onBlockAdded`/`onBlockPlacedBy` → `BlockPos` 版
- [ ] `getCollisionBoundingBoxFromPool`/`getSelectedBoundingBoxFromPool`/`setBlockBoundsBasedOnState` → `getBoundingBox`/`getCollisionBoundingBox`/`VoxelShape`
- [ ] `TileEntity` 登録: `GameRegistry.registerTileEntity` → `TileEntityType`
- [ ] `ItemBlock` 分離登録
- [ ] `setCreativeTab` → `ItemGroup` (1.14+)
- [ ] `setTickRandomly(true)` + `updateTick` → `randomTick` / `scheduledTick`
- [ ] レンダー: ISBRH → `TileEntityRenderer` / `BlockEntityRenderer`

---


---

## 1.20.1 追補（plan.md ギャップ対応）

### 登録の1.20.1定型（DeferredRegister + Holder）

```java
// ModBlocks.java (1.20.1)
public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "defeatedcrow");
public static final RegistryObject<Block> TEA_MAKER_NEXT = BLOCKS.register("tea_maker_next",
  () -> new BlockTeaMakerNext(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(2.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));
public static final RegistryObject<Item> TEA_MAKER_NEXT_ITEM = ModItems.ITEMS.register("tea_maker_next",
  () -> new BlockItem(TEA_MAKER_NEXT.get(), new Item.Properties()));

// BlockEntity
public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "defeatedcrow");
public static final RegistryObject<BlockEntityType<TileMakerNext>> TEA_MAKER_NEXT_BE = BLOCK_ENTITIES.register("tea_maker_next",
  () -> BlockEntityType.Builder.of(TileMakerNext::new, TEA_MAKER_NEXT.get()).build(null));

// CreativeModeTab への登録は displayItems で: see creative-tabs/migration-guide.md
```

- `Block.Properties.create(Material.XXX)` → `BlockBehaviour.Properties.of().mapColor(MapColor.XXX).sound(SoundType.XXX)`。
- `hardnessAndResistance` → `strength(hardness, resistance)`。
- `setRegistryName` / `setTranslationKey` は削除、DeferredRegisterのkeyで管理。
- `setCreativeTab` は削除、CreativeModeTabの `displayItems` で `output.accept`。

### BlockState / Property の1.20.1

- `IBlockState` → `BlockState`、`PropertyInteger/Enum/Bool` → `IntegerProperty/EnumProperty/BooleanProperty`。
- `createBlockState` → `createBlockStateDefinition(StateDefinition.Builder<Block, BlockState>)`。
- `getStateFromMeta` / `getMetaFromState` は削除（1.13でメタ廃止）。既存セーブ互換は `BlockState` の `getValue`/`setValue` で直接管理。

### レンダー / モデル 1.20.1

- `IIcon`/`getIcon`/`registerBlockIcons` は削除済（1.12でJSON化）。1.20.1も `assets/defeatedcrow/blockstates/*.json` + `models/block/*.json` + `models/item/*.json` のJSONモデルを維持。
- `getRenderType()` の `ISBRH` → `BlockEntityRenderer` (BER) + `EntityBlock` (`BlockEntityWithoutLevelRenderer` 対応) に完全移行。旧 `ClientProxy.registerRenderers()` の `RenderingRegistry.registerBlockHandler` は `EntityRenderersEvent.RegisterRenderers` + `BlockEntityRenderers.register` に。
- `registerRenderType` / `getBlockLayer` は `ItemBlock` の `RenderType` (`CUTOUT`, `TRANSLUCENT`) で `ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout())` を `FMLClientSetupEvent` で実行。
- `BER.Context` は `BlockEntityRendererProvider.Context` にリネーム（`BlockEntityRenderer<T extends BlockEntity>` のコンストラクタ引数）。

```java
// 1.20.1 BER 登録
@SubscribeEvent
public static void onRegisterBER(EntityRenderersEvent.RegisterRenderers e){
  e.registerBlockEntityRenderer(ModBlockEntities.TEA_MAKER_NEXT.get(), RenderTeaMakerNext::new);
}
public class RenderTeaMakerNext implements BlockEntityRenderer<TileMakerNext> {
  public RenderTeaMakerNext(BlockEntityRendererProvider.Context ctx){ this.model = new TeaMakerModel(ctx.bakeLayer(ModModelLayers.TEA_MAKER)); }
  public void render(TileMakerNext be, float partial, PoseStack pose, MultiBufferSource buf, int light, int overlay){ /* ... */ }
}
```

### 衝突・VoxelShape 1.20.1

- `getCollisionBoundingBoxFromPool` / `getSelectedBoundingBoxFromPool` / `setBlockBoundsBasedOnState` / `addCollisionBoxesToList` は全て `VoxelShape` に置換済。1.20.1では `getShape` / `getCollisionShape` / `getOcclusionShape` / `getVisualShape` に細分化。
- `AxisAlignedBB` → `AABB` + `VoxelShape` + `Shapes.create` / `Shapes.or` / `Block.box(minX, minY, minZ, maxX, maxY, maxZ)`。

### TileEntity連携 1.20.1

- `BlockContainer` は削除、`Block` + `EntityBlock`（`newBlockEntity` + `getTicker`）に。`createNewTileEntity` は `newBlockEntity(BlockPos, BlockState)` に。
- `TileEntity` → `BlockEntity`、登録は上記 `BlockEntityType.Builder.of`。

### 検証 1.20.1 追加

- `grep -r "setBlockName"` → 0件（`BlockBehaviour.Properties` に置換）を確認
- `grep -r "IIcon"` → 0件（JSONモデルに置換）を確認
- `grep -r "getRenderType"` → `BlockEntityRenderer` に置換（旧ISBRH参照が残存しないか）
- `grep -r "BlockContainer"` → 0件（`EntityBlock` に置換）を確認
- `grep -r "BlockEntityType.Builder.create"` → `Builder.of` に置換（1.20.1は `create` 削除）を確認

## 検証手順

1. **静的解析**: `doc/blocks/_inventory2.json` で 74 Block の `methods` を網羅。`grep -r "world.getBlockMetadata"` で残存箇所を検出。
2. **ビルド検証**: `gradlew build` で 1.12.2 環境でコンパイルエラーになるメソッド（`getIcon`/`registerBlockIcons` 等）が正しく削除されているか確認。
3. **JEI/NEI**: `ItemDummyForTooltip` 等のダミー表示が 1.12+ `Ingredient` で代替できているか。
4. **ワールド互換**: 旧セーブの `meta → state` 変換は `getStateFromMeta` / `getMetaFromState` で互換維持。

---

## 関連ドキュメント

- [Block 一覧](../blocks.md) - 9カテゴリ表
- [個別ページ索引](./README.md) - 74個別ページへのリンク
- [テンプレ](./_template.md) - 個別ページの雛形
- [TileEntity 一覧](../tile-entities.md) - 45 TileEntity と `CommonProxy.registerTileEntity`
- [Fluid 一覧](../fluids.md) - 流体18種と `MaterialRegister.addFluid`
- [Item 一覧](../items.md) - 64 Item と `ItemBlock` 連携
- [WorldGen](../worldgen.md) - `WorldgenTeaTree`/`WorldgenClam`
- `src/main/java/mods/defeatedcrow/common/MaterialRegister.java:238-411` - Block登録本体
- `src/main/java/mods/defeatedcrow/common/DCsAppleMilk.java:152-340` - フィールド定義
- `src/main/java/mods/defeatedcrow/common/CommonProxy.java:70-117` - TileEntity登録
