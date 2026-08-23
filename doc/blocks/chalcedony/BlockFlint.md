# BlockFlint

> Category: `chalcedony`  
> Source: `src/main/java/mods/defeatedcrow/common/block/BlockFlint.java:1`  
> Registry: `defeatedcrow.flintBlock` (`DCsAppleMilk.flintBlock`)  
> ItemBlock: `ItemFlintBlock` (`src/main/java/mods/defeatedcrow/common/block/ItemFlintBlock.java:1`)  
> TileEntity: `なし`  
> CreativeTab: `applemilkContainer`

## 概要
玉髄・鉱石装飾ブロック。透過・発光・特殊レンダーを持つ装飾素材。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:228` `public static Block flintBlock;`
- **インスタンス生成**: `MaterialRegister.java:950` `new BlockFlint()` / `setBlockName("defeatedcrow.flintBlock")`
- **GameRegistry**: `GameRegistry.registerBlock(DCsAppleMilk.flintBlock, ItemFlintBlock.class, "defeatedcrow.flintBlock")` (`MaterialRegister.java:377`)
- **Material / Hardness / Resistance**: `Material.unknown`, hardness `1.5F`, resistance `2.0F` (`BlockFlint.java:1`)
- **CreativeTab**: `DCsAppleMilk.applemilkContainer`
- **メタデータ**: メタデータでの種類分岐あり。`getSubBlocks`/`getIcon`で分岐。詳細は`ItemAPI`または`Block`内の`boxType`配列等を参照。

## 継承・インターフェース
- 継承: `BlockBreakable`
- 実装: `なし`（または `IShearable` 等は個別クラスで確認）

## プロパティ / 状態
- `isOpaqueCube()` : `public boolean isOpaqueCube() {`
- `renderAsNormalBlock()` : `public boolean renderAsNormalBlock() {`
- `getBlockColor()` : `public int getBlockColor() {`
- `isSideSolid()` : `public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {`
- `getRenderBlockPass()` : `public int getRenderBlockPass() {`
- BoundingBox: `setBlockBoundsBasedOnState()` / `getCollisionBoundingBoxFromPool()` / `getSelectedBoundingBoxFromPool()` 等でAABB制御。`setBlockBounds`でサイズ指定。

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `canPlaceTorchOnTop` | `public boolean canPlaceTorchOnTop(World par1World, int par2, int par3, int par4) {` | `Block` オーバーライド / 内部呼出 | `canPlaceTorchOnTop` の1.7.10実装 | 1.12+で`isSideSolid(..., UP)`で代替。削除される場合あり。 |
| `colorMultiplier` | `public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {` | `Block` オーバーライド / 内部呼出 | `colorMultiplier` の1.7.10実装 | 1.12+ `colorMultiplier(IBlockState, IBlockAccess, BlockPos, int)` → 1.14+ `BlockColors` 登録へ。 |
| `getBlockColor` | `public int getBlockColor() {` | `Block` オーバーライド / 内部呼出 | `getBlockColor` の1.7.10実装 | 同上。 |
| `getItemDropped` | `public Item getItemDropped(int metadata, Random rand, int fortune) {` | ドロップ時のItem決定 | `getItemDropped` の1.7.10実装 | 1.12+: `getItemDropped(IBlockState, Random, int fortune)`。`Item.getItemFromBlock(this)`→`this.asItem()`。 |
| `getRenderBlockPass` | `public int getRenderBlockPass() {` | `Block` オーバーライド / 内部呼出 | `getRenderBlockPass` の1.7.10実装 | 1.12+ `getBlockLayer(): BlockRenderLayer {SOLID, CUTOUT, TRANSLUCENT}`。 |
| `isOpaqueCube` | `public boolean isOpaqueCube() {` | 光透過・描画判定 | `isOpaqueCube` の1.7.10実装 | 1.12+: `isOpaqueCube(IBlockState)`。戻り値ロジック維持。`isFullCube`/`isTranslucent`も併せて確認。 |
| `isSideSolid` | `public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {` | `ForgeDirection`側面が固体か判定 (`isSideSolid`→`canConnect`等で使用) | `isSideSolid` の1.7.10実装 | 1.12+: `isSideSolid(IBlockState, IBlockAccess, BlockPos, EnumFacing)`。`ForgeDirection`→`EnumFacing`。 |
| `registerBlockIcons` | `public void registerBlockIcons(IIconRegister par1IconRegister) {` | クライアントテクスチャ登録時 (`TextureStitchEvent`) | `IIconRegister`でテクスチャ登録 | 1.8+で削除。`IIconRegister`/`IIcon`廃止。JSONモデル(`assets/defeatedcrow/blockstates/*.json`, `models/block/*.json`) + `ModelLoader`へ。`Util.getTexturePassNoAlt()`は`ResourceLocation`へ。 |
| `renderAsNormalBlock` | `public boolean renderAsNormalBlock() {` | 通常ブロック描画判定 | `renderAsNormalBlock` の1.7.10実装 | 1.12+で`isFullCube`/`isOpaqueCube`に統合。`renderAsNormalBlock()`は削除。`BlockRenderLayer`(`CUTOUT`/`TRANSLUCENT`)で代替。 |

## TileEntity / ItemBlock 連携
- Tile: `なし`（`Block`単体）
- ItemBlock: `ItemFlintBlock` (`src/main/java/mods/defeatedcrow/common/block/ItemFlintBlock.java:1`) - `getUnlocalizedName(ItemStack)` / `getMetadata()` / `placeBlockAt()` 等。メタ分岐の表示名対応。

## レシピ / ワールド生成 / その他連携
- Event: `AMTBlockRightClickEvent` / `PlantsClickEvent` / `TeamakerRightClickEvent` 等を `MinecraftForge.EVENT_BUS.post(event)` で発火

## レンダー / モデル
- Render ID: `DCsAppleMilk.modelFlint` (`DCsAppleMilk.java: - ` 初期化, `CommonProxy.getRenderID()` , `ClientProxy.registerRenderers()` で登録)
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
- [カテゴリ別一覧](../README.md) (chalcedony)
- [移行ガイド](../migration-guide.md)
- [Item 一覧](../../items.md)
