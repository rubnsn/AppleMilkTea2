# BlockYuzuLeaves

> Category: `plants`  
> Source: `src/main/java/mods/defeatedcrow/common/block/plants/BlockYuzuLeaves.java:1`  
> Registry: `defeatedcrow.leavesYuzu` (`DCsAppleMilk.leavesYuzu`)  
> ItemBlock: `ItemYuzuLeaves` (`src/main/java/mods/defeatedcrow/common/block/plants/ItemYuzuLeaves.java:1`)  
> TileEntity: `なし`  
> CreativeTab: `applemilk`

## 概要
植物・自然ブロック。成長・収穫・剪定等のギミック。`IShearable`/`IPlantable`/`IRightClickHarvestable`を実装。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:219` `public static Block leavesYuzu;`
- **インスタンス生成**: `MaterialRegister.java:1000` `new BlockYuzuLeaves()` / `setBlockName("defeatedcrow.leavesYuzu")`
- **GameRegistry**: `GameRegistry.registerBlock(DCsAppleMilk.leavesYuzu, ItemYuzuLeaves.class, "defeatedcrow.leavesYuzu")` (`MaterialRegister.java:362`)
- **Material / Hardness / Resistance**: `Material.leaves`, hardness `0.1F`, resistance `` (`BlockYuzuLeaves.java:1`)
- **CreativeTab**: `DCsAppleMilk.applemilk`
- **メタデータ**: メタデータでの種類分岐あり。`getSubBlocks`/`getIcon`で分岐。詳細は`ItemAPI`または`Block`内の`boxType`配列等を参照。

## 継承・インターフェース
- 継承: `BlockLeavesBase`
- 実装: `IShearable, IRightClickHarvestable`

## プロパティ / 状態
- `isOpaqueCube()` : `public boolean isOpaqueCube() {`
- BoundingBox: `setBlockBoundsBasedOnState()` / `getCollisionBoundingBoxFromPool()` / `getSelectedBoundingBoxFromPool()` 等でAABB制御。`setBlockBounds`でサイズ指定。

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `beginLeavesDecay` | `public void beginLeavesDecay(World world, int x, int y, int z) {` | `Block` オーバーライド / 内部呼出 | `beginLeavesDecay` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `breakBlock` | `public void breakBlock(World world, int x, int y, int z, Block par5Block, int meta) {` | 破壊時、TileのDrop処理・`notifyNeighbors` | 破壊時にTile内アイテムをEntityItemで散乱 | 1.12+: `breakBlock(World, BlockPos, IBlockState)`。`world.getTileEntity(pos)`でDrop処理。`world.func_147453_f`→`world.notifyNeighborsOfStateChange`。 |
| `createStackedBlock` | `protected ItemStack createStackedBlock(int p_149644_1_) {` | `Block` オーバーライド / 内部呼出 | `createStackedBlock` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `damageDropped` | `public int damageDropped(int p_149692_1_) {` | ドロップ時のメタ決定 | `damageDropped` の1.7.10実装 | 1.12+: `damageDropped(IBlockState)`または`getMetaFromState`経由。`&7`等のマスクは`PropertyInteger`へ。 |
| `dropBlockAsItemWithChance` | `public void dropBlockAsItemWithChance(World world, int x, int y, int z, int p_149690_5_, float p_149690_6_,` | `Block` オーバーライド / 内部呼出 | `dropBlockAsItemWithChance` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `getCropItem` | `public ItemStack getCropItem(int blockMeta) {` | `Block` オーバーライド / 内部呼出 | `getCropItem` の1.7.10実装 | 維持。 |
| `getDrops` | `public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {` | 破壊時ドロップ生成 | Fortune考慮のドロップリスト生成 | 1.12+: `getDrops(NonNullList<ItemStack>, IBlockAccess, BlockPos, IBlockState, int fortune)`。`world.getBlockMetadata`→`state.getValue(PROPERTY)`。`Blocks.water.getIcon`等の参照削除。 |
| `getGrownMetadata` | `public int getGrownMetadata(World world, int x, int y, int z) {` | `Block` オーバーライド / 内部呼出 | `getGrownMetadata` の1.7.10実装 | Metada→Stateへ。 |
| `getIcon` | `public IIcon getIcon(int par1, int par2) {` | レンダー時、面・メタごとに呼出 | 面・メタごとの`IIcon`返却 | 1.8+で削除。`IBlockState`/`Property*`/`BlockStateContainer`で状態管理。`getActualState`/`getStateFromMeta`/`getMetaFromState`へ分割。面ごとのテクスチャは`blockstate`の`variants`で分岐。 |
| `getInitialMetadata` | `public int getInitialMetadata(World world, int x, int y, int z) {` | `Block` オーバーライド / 内部呼出 | `getInitialMetadata` の1.7.10実装 | 同上。 |
| `getItemDropped` | `public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {` | ドロップ時のItem決定 | `getItemDropped` の1.7.10実装 | 1.12+: `getItemDropped(IBlockState, Random, int fortune)`。`Item.getItemFromBlock(this)`→`this.asItem()`。 |
| `getSaplingBlock` | `public Block getSaplingBlock(int meta) {` | `Block` オーバーライド / 内部呼出 | `getSaplingBlock` の1.7.10実装 | 維持。`Block`返却はそのまま。 |
| `getSaplingMeta` | `public int getSaplingMeta(int meta) {` | `Block` オーバーライド / 内部呼出 | `getSaplingMeta` の1.7.10実装 | 削除。Stateへ。 |
| `getSubBlocks` | `public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {` | CreativeTab表示時 | CreativeTab用サブアイテム列挙 | シグネチャ変更: `getSubBlocks(CreativeTabs, NonNullList<ItemStack>)` → 1.14+ `fillItemGroup(ItemGroup, NonNullList)`。メタ分岐は`Property`へ。`Item.getItemFromBlock`は`block.asItem()`へ。 |
| `harvestBlock` | `public void harvestBlock(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_,` | `Block` オーバーライド / 内部呼出 | `harvestBlock` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `isHarvestable` | `public boolean isHarvestable(World world, int x, int y, int z) {` | `Block` オーバーライド / 内部呼出 | `isHarvestable` の1.7.10実装 | 同上。 |
| `isLeaves` | `public boolean isLeaves(IBlockAccess world, int x, int y, int z) {` | `Block` オーバーライド / 内部呼出 | `isLeaves` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `isOpaqueCube` | `public boolean isOpaqueCube() {` | 光透過・描画判定 | `isOpaqueCube` の1.7.10実装 | 1.12+: `isOpaqueCube(IBlockState)`。戻り値ロジック維持。`isFullCube`/`isTranslucent`も併せて確認。 |
| `isShearable` | `public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {` | ハサミ収穫可否 | `isShearable` の1.7.10実装 | 維持。`isShearable(ItemStack, IBlockAccess, BlockPos)`。 |
| `onBlockActivated` | `public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,` | プレイヤー右クリック時 (`PlayerInteractEvent` 経由) | GUI表示・アイテム収受・イベント発火 (`AMTBlockRightClickEvent`) | 1.12+: `onBlockActivated(World, BlockPos, IBlockState, EntityPlayer, EnumHand, EnumFacing, float,float,float)`。`world.getBlockMetadata(x,y,z)`→`world.getBlockState(pos)`、`world.getTileEntity(x,y,z)`→`world.getTileEntity(pos)`。`MinecraftForge.EVENT_BUS.post`は維持。`PlayerInteractEvent`との兼用注意。 |
| `onHarvest` | `public boolean onHarvest(World world, int x, int y, int z, IInventory inventory, ItemStack currentItem) {` | `Block` オーバーライド / 内部呼出 | `onHarvest` の1.7.10実装 | API `IRightClickHarvestable.onHarvest(World, int, ...)` は `BlockPos` 版へ移行要。 |
| `onSheared` | `public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {` | ハサミ収穫時ドロップ | `onSheared` の1.7.10実装 | 維持。戻り値`List<ItemStack> onSheared(...)`。 |
| `quantityDropped` | `public int quantityDropped(Random rand) {` | ドロップ数決定 | `quantityDropped` の1.7.10実装 | 維持。`quantityDropped(IBlockState, int fortune, Random)`へ。`Random`引数順変更に注意。 |
| `randomDisplayTick` | `public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_,` | クライアントパーティクル描画 | `randomDisplayTick` の1.7.10実装 | 1.12+: `randomDisplayTick(IBlockState, World, BlockPos, Random)`。`world.getBlockMetadata`→`state.getValue(...)`。クライアント限定。 |
| `registerBlockIcons` | `public void registerBlockIcons(IIconRegister par1IconRegister) {` | クライアントテクスチャ登録時 (`TextureStitchEvent`) | `IIconRegister`でテクスチャ登録 | 1.8+で削除。`IIconRegister`/`IIcon`廃止。JSONモデル(`assets/defeatedcrow/blockstates/*.json`, `models/block/*.json`) + `ModelLoader`へ。`Util.getTexturePassNoAlt()`は`ResourceLocation`へ。 |
| `updateTick` | `public void updateTick(World world, int x, int y, int z, Random rand) {` | ランダムティック時 (`setTickRandomly(true)`) | `updateTick` の1.7.10実装 | 1.12+: `updateTick(World, BlockPos, IBlockState, Random)`。`world.getBlockMetadata`/`setBlockMetadataWithNotify`→`world.setBlockState(pos, state.withProperty(...), 3)`。 |

## TileEntity / ItemBlock 連携
- Tile: `なし`（`Block`単体）
- ItemBlock: `ItemYuzuLeaves` (`src/main/java/mods/defeatedcrow/common/block/plants/ItemYuzuLeaves.java:1`) - `getUnlocalizedName(ItemStack)` / `getMetadata()` / `placeBlockAt()` 等。メタ分岐の表示名対応。

## レシピ / ワールド生成 / その他連携
- WorldGen: `WorldgenTeaTree` / `WorldgenClam` (`DCsAppleMilk.java:739-751` `GameRegistry.registerWorldGenerator`)で生成。
- 骨粉: `DCsBonemealEvent`で対応。
- Event: `AMTBlockRightClickEvent` / `PlantsClickEvent` / `TeamakerRightClickEvent` 等を `MinecraftForge.EVENT_BUS.post(event)` で発火

## レンダー / モデル
- Render ID: `DCsAppleMilk.modelYuzuLeaves` (`DCsAppleMilk.java: - ` 初期化, `CommonProxy.getRenderID()` , `ClientProxy.registerRenderers()` で登録)
- Renderer: ISBRH `SimpleBlockRenderingHandler` または `Block`標準描画。`registerBlockIcons`で`IIcon`登録。
- パーティクル: `randomDisplayTick()` で `EntityDCCloudFX` 等（該当する場合）

## 1.7.10 → 1.12.2 移行チェックリスト
- [ ] `setBlockName` → `setTranslationKey` + `setRegistryName("defeatedcrow", "xxx")`
- [ ] `GameRegistry.registerBlock` → `RegistryEvent.Register<Block>` / `DeferredRegister`
- [ ] `BlockContainer` → `Block` + `hasTileEntity` / `createTileEntity`
- [ ] `IIcon` / `IIconRegister` / `getIcon` / `registerBlockIcons` → `BlockState` / JSONモデル
- [ ] `world.getBlockMetadata / setBlockMetadataWithNotify` → `IBlockState` / `PropertyInteger` / `PropertyBool` 等
- [ ] `onBlockActivated(World, int,int,int, ...)` → `onBlockActivated(World, BlockPos, IBlockState, EntityPlayer, EnumHand, EnumFacing, ...)`
- [ ] `breakBlock(World,int,int,int,Block,int)` → `breakBlock(World, BlockPos, IBlockState)`
- [ ] `getCollisionBoundingBoxFromPool` / `getSelectedBoundingBoxFromPool` / `setBlockBoundsBasedOnState` → `getBoundingBox` / `getCollisionBoundingBox` / `addCollisionBoxToList` (1.12) / `VoxelShape` (1.16)
- [ ] `isOpaqueCube() / renderAsNormalBlock() / getRenderType()` → `isOpaqueCube(IBlockState)` / `isFullCube(IBlockState)` / `getRenderType(IBlockState): EnumBlockRenderType` / `getBlockLayer()`
- [ ] `getDrops(World,int,int,int,int,int)` / `quantityDropped` / `damageDropped` → `getDrops(IBlockAccess, BlockPos, IBlockState, int)` / `getItemDropped(IBlockState, ...)`
- [ ] `onBlockPlacedBy` / `onBlockAdded` → `onBlockPlacedBy(World, BlockPos, IBlockState, EntityLivingBase, ItemStack)`
- [ ] `createNewTileEntity` → `createTileEntity(World, IBlockState)`
- [ ] TileEntity 登録: `GameRegistry.registerTileEntity` → `TileEntityType.Builder` / `DeferredRegister<TileEntityType<?>>`
- [ ] ItemBlock 登録分離: `ItemBlock` を別途 `RegistryEvent.Register<Item>` で `new ItemBlock(block)` + `setRegistryName(block.getRegistryName())`
- [ ] `setCreativeTab` は維持だが `CreativeTabs` → `ItemGroup` (1.14+)

## 関連ドキュメント
- [Block 一覧](../../blocks.md)
- [TileEntity 一覧](../../tile-entities.md)
- [カテゴリ別一覧](../README.md) (plants)
- [移行ガイド](../migration-guide.md)
- [Item 一覧](../../items.md)
