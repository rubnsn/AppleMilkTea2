# BlockClamSand

> Category: `plants`  
> Source: `src/main/java/mods/defeatedcrow/common/block/plants/BlockClamSand.java:1`  
> Registry: `defeatedcrow.clamSand` (`DCsAppleMilk.clamSand`)  
> ItemBlock: `ItemClamSand` (`src/main/java/mods/defeatedcrow/common/block/plants/ItemClamSand.java:1`)  
> TileEntity: `なし`  
> CreativeTab: `applemilk`

## 概要
植物・自然ブロック。成長・収穫・剪定等のギミック。`IShearable`/`IPlantable`/`IRightClickHarvestable`を実装。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:215` `public static Block clamSand;`
- **インスタンス生成**: `MaterialRegister.java:1014` `new BlockClamSand()` / `setBlockName("defeatedcrow.clamSand")`
- **GameRegistry**: `GameRegistry.registerBlock(DCsAppleMilk.clamSand, ItemClamSand.class, "defeatedcrow.clamSand")` (`MaterialRegister.java:363`)
- **Material / Hardness / Resistance**: `Material.ground`, hardness `0.5F`, resistance `1.0F` (`BlockClamSand.java:1`)
- **CreativeTab**: `DCsAppleMilk.applemilk`
- **メタデータ**: メタで内容物種類を分岐。`ItemVegiBag`/`ItemClamSand`でテクスチャ分岐。

## 継承・インターフェース
- 継承: `Block`
- 実装: `IRightClickHarvestable`

## プロパティ / 状態
- `getBlockColor()` : `public int getBlockColor() {`
- `getLightValue()` : `public int getLightValue(IBlockAccess world, int x, int y, int z) {`
- BoundingBox: `setBlockBoundsBasedOnState()` / `getCollisionBoundingBoxFromPool()` / `getSelectedBoundingBoxFromPool()` 等でAABB制御。`setBlockBounds`でサイズ指定。

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `colorMultiplier` | `public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {` | `Block` オーバーライド / 内部呼出 | `colorMultiplier` の1.7.10実装 | 1.12+ `colorMultiplier(IBlockState, IBlockAccess, BlockPos, int)` → 1.14+ `BlockColors` 登録へ。 |
| `dropBlockAsItemWithChance` | `public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6,` | `Block` オーバーライド / 内部呼出 | `dropBlockAsItemWithChance` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `getBlockColor` | `public int getBlockColor() {` | `Block` オーバーライド / 内部呼出 | `getBlockColor` の1.7.10実装 | 同上。 |
| `getCropItem` | `public ItemStack getCropItem(int blockMeta) {` | `Block` オーバーライド / 内部呼出 | `getCropItem` の1.7.10実装 | 維持。 |
| `getDrops` | `public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {` | 破壊時ドロップ生成 | Fortune考慮のドロップリスト生成 | 1.12+: `getDrops(NonNullList<ItemStack>, IBlockAccess, BlockPos, IBlockState, int fortune)`。`world.getBlockMetadata`→`state.getValue(PROPERTY)`。`Blocks.water.getIcon`等の参照削除。 |
| `getGrownMetadata` | `public int getGrownMetadata(World world, int x, int y, int z) {` | `Block` オーバーライド / 内部呼出 | `getGrownMetadata` の1.7.10実装 | Metada→Stateへ。 |
| `getIcon` | `getIcon(...)` | レンダー時、面・メタごとに呼出 | 面・メタごとの`IIcon`返却 | 1.8+で削除。`IBlockState`/`Property*`/`BlockStateContainer`で状態管理。`getActualState`/`getStateFromMeta`/`getMetaFromState`へ分割。面ごとのテクスチャは`blockstate`の`variants`で分岐。 |
| `getInitialMetadata` | `public int getInitialMetadata(World world, int x, int y, int z) {` | `Block` オーバーライド / 内部呼出 | `getInitialMetadata` の1.7.10実装 | 同上。 |
| `getItemDropped` | `public Item getItemDropped(int par1, Random par2Random, int par3) {` | ドロップ時のItem決定 | `getItemDropped` の1.7.10実装 | 1.12+: `getItemDropped(IBlockState, Random, int fortune)`。`Item.getItemFromBlock(this)`→`this.asItem()`。 |
| `getLightValue` | `public int getLightValue(IBlockAccess world, int x, int y, int z) {` | 明るさ取得 (`IBlockAccess`から) | `getLightValue` の1.7.10実装 | 1.12+: `getLightValue(IBlockState, IBlockAccess, BlockPos)`。 |
| `getRenderColor` | `public int getRenderColor(int par1) {` | `Block` オーバーライド / 内部呼出 | `getRenderColor` の1.7.10実装 | 同上。 |
| `getSaplingBlock` | `public Block getSaplingBlock(int meta) {` | `Block` オーバーライド / 内部呼出 | `getSaplingBlock` の1.7.10実装 | 維持。`Block`返却はそのまま。 |
| `getSaplingMeta` | `public int getSaplingMeta(int meta) {` | `Block` オーバーライド / 内部呼出 | `getSaplingMeta` の1.7.10実装 | 削除。Stateへ。 |
| `getSubBlocks` | `public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {` | CreativeTab表示時 | CreativeTab用サブアイテム列挙 | シグネチャ変更: `getSubBlocks(CreativeTabs, NonNullList<ItemStack>)` → 1.14+ `fillItemGroup(ItemGroup, NonNullList)`。メタ分岐は`Property`へ。`Item.getItemFromBlock`は`block.asItem()`へ。 |
| `isHarvestable` | `public boolean isHarvestable(World world, int x, int y, int z) {` | `Block` オーバーライド / 内部呼出 | `isHarvestable` の1.7.10実装 | 同上。 |
| `onBlockActivated` | `public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,` | プレイヤー右クリック時 (`PlayerInteractEvent` 経由) | GUI表示・アイテム収受・イベント発火 (`AMTBlockRightClickEvent`) | 1.12+: `onBlockActivated(World, BlockPos, IBlockState, EntityPlayer, EnumHand, EnumFacing, float,float,float)`。`world.getBlockMetadata(x,y,z)`→`world.getBlockState(pos)`、`world.getTileEntity(x,y,z)`→`world.getTileEntity(pos)`。`MinecraftForge.EVENT_BUS.post`は維持。`PlayerInteractEvent`との兼用注意。 |
| `onHarvest` | `public boolean onHarvest(World world, int x, int y, int z, IInventory inventory, ItemStack currentItem) {` | `Block` オーバーライド / 内部呼出 | `onHarvest` の1.7.10実装 | API `IRightClickHarvestable.onHarvest(World, int, ...)` は `BlockPos` 版へ移行要。 |
| `quantityDropped` | `public int quantityDropped(Random par1Random) {` | ドロップ数決定 | `quantityDropped` の1.7.10実装 | 維持。`quantityDropped(IBlockState, int fortune, Random)`へ。`Random`引数順変更に注意。 |
| `randomDisplayTick` | `public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {` | クライアントパーティクル描画 | `randomDisplayTick` の1.7.10実装 | 1.12+: `randomDisplayTick(IBlockState, World, BlockPos, Random)`。`world.getBlockMetadata`→`state.getValue(...)`。クライアント限定。 |
| `registerBlockIcons` | `public void registerBlockIcons(IIconRegister par1IconRegister) {` | クライアントテクスチャ登録時 (`TextureStitchEvent`) | `IIconRegister`でテクスチャ登録 | 1.8+で削除。`IIconRegister`/`IIcon`廃止。JSONモデル(`assets/defeatedcrow/blockstates/*.json`, `models/block/*.json`) + `ModelLoader`へ。`Util.getTexturePassNoAlt()`は`ResourceLocation`へ。 |
| `tickRate` | `public int tickRate(World par1World) {` | ティック間隔定義 | `tickRate` の1.7.10実装 | 維持。1.12+でも`tickRate(World)`。`world.scheduleBlockUpdate(pos, block, tickRate, 0)`は`world.scheduleUpdate(pos, block, tickRate)`へ。 |
| `updateTick` | `public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {` | ランダムティック時 (`setTickRandomly(true)`) | `updateTick` の1.7.10実装 | 1.12+: `updateTick(World, BlockPos, IBlockState, Random)`。`world.getBlockMetadata`/`setBlockMetadataWithNotify`→`world.setBlockState(pos, state.withProperty(...), 3)`。 |

## TileEntity / ItemBlock 連携
- Tile: `なし`（`Block`単体）
- ItemBlock: `ItemClamSand` (`src/main/java/mods/defeatedcrow/common/block/plants/ItemClamSand.java:1`) - `getUnlocalizedName(ItemStack)` / `getMetadata()` / `placeBlockAt()` 等。メタ分岐の表示名対応。

## レシピ / ワールド生成 / その他連携
- WorldGen: `WorldgenTeaTree` / `WorldgenClam` (`DCsAppleMilk.java:739-751` `GameRegistry.registerWorldGenerator`)で生成。
- 骨粉: `DCsBonemealEvent`で対応。
- Event: `AMTBlockRightClickEvent` / `PlantsClickEvent` / `TeamakerRightClickEvent` 等を `MinecraftForge.EVENT_BUS.post(event)` で発火

## レンダー / モデル
- Render ID: `DCsAppleMilk.modelClamSand` (`DCsAppleMilk.java: - ` 初期化, `CommonProxy.getRenderID()` , `ClientProxy.registerRenderers()` で登録)
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
