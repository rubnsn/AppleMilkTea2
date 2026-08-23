# BlockCPanel

> Category: `chalcedony`  
> Source: `src/main/java/mods/defeatedcrow/common/block/BlockCPanel.java:1`  
> Registry: `defeatedcrow.chalcedonyPanel` (`DCsAppleMilk.chalcenonyPanel`)  
> ItemBlock: `なし`  
> TileEntity: `TileCPanel` (`TileChalcedonyPanel`) (`src/main/java/mods/defeatedcrow/common/tile/TileCPanel.java:1`)  
> CreativeTab: `applemilk`

## 概要
玉髄・鉱石装飾ブロック。透過・発光・特殊レンダーを持つ装飾素材。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:232` `public static Block chalcenonyPanel;`
- **インスタンス生成**: `MaterialRegister.java:967` `new BlockCPanel()` / `setBlockName("defeatedcrow.chalcedonyPanel")`
- **GameRegistry**: `GameRegistry.registerBlock(DCsAppleMilk.chalcenonyPanel, "defeatedcrow.chalcedonyPanel")` (`MaterialRegister.java:381`)
- **Material / Hardness / Resistance**: `Material.glass`, hardness `0.3F`, resistance `3.0F` (`BlockCPanel.java:1`)
- **CreativeTab**: `DCsAppleMilk.applemilk`
- **メタデータ**: メタデータでの種類分岐あり。`getSubBlocks`/`getIcon`で分岐。詳細は`ItemAPI`または`Block`内の`boxType`配列等を参照。

## 継承・インターフェース
- 継承: `BlockContainer`
- 実装: `なし`（または `IShearable` 等は個別クラスで確認）

## プロパティ / 状態
- `getRenderType()` : `public int getRenderType() {`
- `isOpaqueCube()` : `public boolean isOpaqueCube() {`
- `renderAsNormalBlock()` : `public boolean renderAsNormalBlock() {`
- `isSideSolid()` : `isSideSolid(...)`
- BoundingBox: `setBlockBoundsBasedOnState()` / `getCollisionBoundingBoxFromPool()` / `getSelectedBoundingBoxFromPool()` 等でAABB制御。`setBlockBounds`でサイズ指定。

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `breakBlock` | `public void breakBlock(World world, int x, int y, int z, Block block, int meta) {` | 破壊時、TileのDrop処理・`notifyNeighbors` | 破壊時にTile内アイテムをEntityItemで散乱 | 1.12+: `breakBlock(World, BlockPos, IBlockState)`。`world.getTileEntity(pos)`でDrop処理。`world.func_147453_f`→`world.notifyNeighborsOfStateChange`。 |
| `canPlaceBlockAt` | `public boolean canPlaceBlockAt(World world, int x, int y, int z) {` | `Block` オーバーライド / 内部呼出 | `canPlaceBlockAt` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `canProvidePower` | `public boolean canProvidePower() {` | `Block` オーバーライド / 内部呼出 | `canProvidePower` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `createNewTileEntity` | `public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {` | `BlockContainer`生成時 (`World.getTileEntity`→`createNewTileEntity`) | Tile生成 (`TileXxx`インスタンス返却) | 1.12+: `BlockContainer`は非推奨。`Block` + `hasTileEntity(IBlockState)` + `createTileEntity(World, IBlockState)`へ。`CommonProxy.registerTileEntity`→`TileEntityType.Builder` / `DeferredRegister`。1.14+で`BlockEntity`へ。 |
| `getBlocksMovement` | `public boolean getBlocksMovement(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_) {` | `Block` オーバーライド / 内部呼出 | `getBlocksMovement` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `getCollisionBoundingBoxFromPool` | `public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_,` | 衝突判定生成 | `getCollisionBoundingBoxFromPool` の1.7.10実装 | 1.12+: `getCollisionBoundingBox(IBlockState, IBlockAccess, BlockPos)` / `addCollisionBoxToList(IBlockState, World, BlockPos, AxisAlignedBB, List, Entity, boolean)`。1.16+ `VoxelShape`/`VoxelShapes`へ。 |
| `getIcon` | `public IIcon getIcon(int side, int meta) {` | レンダー時、面・メタごとに呼出 | 面・メタごとの`IIcon`返却 | 1.8+で削除。`IBlockState`/`Property*`/`BlockStateContainer`で状態管理。`getActualState`/`getStateFromMeta`/`getMetaFromState`へ分割。面ごとのテクスチャは`blockstate`の`variants`で分岐。 |
| `getMobilityFlag` | `public int getMobilityFlag() {` | `Block` オーバーライド / 内部呼出 | `getMobilityFlag` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `getRenderType` | `public int getRenderType() {` | ISBRHレンダーID取得 | `getRenderType` の1.7.10実装 | 1.12+: `getRenderType(IBlockState): EnumBlockRenderType {MODEL, INVISIBLE, LIQUID}`。`DCsAppleMilk.modelXxx`のISBRH登録は`TileEntityRenderer`/`BER`へ移行。 |
| `isOpaqueCube` | `public boolean isOpaqueCube() {` | 光透過・描画判定 | `isOpaqueCube` の1.7.10実装 | 1.12+: `isOpaqueCube(IBlockState)`。戻り値ロジック維持。`isFullCube`/`isTranslucent`も併せて確認。 |
| `isProvidingStrongPower` | `public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int meta) {` | `Block` オーバーライド / 内部呼出 | `isProvidingStrongPower` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `isProvidingWeakPower` | `public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int p_149709_5_) {` | `Block` オーバーライド / 内部呼出 | `isProvidingWeakPower` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `isSideSolid` | `isSideSolid(...)` | `ForgeDirection`側面が固体か判定 (`isSideSolid`→`canConnect`等で使用) | `isSideSolid` の1.7.10実装 | 1.12+: `isSideSolid(IBlockState, IBlockAccess, BlockPos, EnumFacing)`。`ForgeDirection`→`EnumFacing`。 |
| `onBlockActivated` | `public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,` | プレイヤー右クリック時 (`PlayerInteractEvent` 経由) | GUI表示・アイテム収受・イベント発火 (`AMTBlockRightClickEvent`) | 1.12+: `onBlockActivated(World, BlockPos, IBlockState, EntityPlayer, EnumHand, EnumFacing, float,float,float)`。`world.getBlockMetadata(x,y,z)`→`world.getBlockState(pos)`、`world.getTileEntity(x,y,z)`→`world.getTileEntity(pos)`。`MinecraftForge.EVENT_BUS.post`は維持。`PlayerInteractEvent`との兼用注意。 |
| `onEntityCollidedWithBlock` | `public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {` | エンティティ接触時 | `onEntityCollidedWithBlock` の1.7.10実装 | 1.12+: `onEntityCollision(World, BlockPos, IBlockState, Entity)` (1.16+ `onEntityCollision(BlockState, World, BlockPos, Entity)`)。 |
| `onNeighborBlockChange` | `public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {` | `Block` オーバーライド / 内部呼出 | `onNeighborBlockChange` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `registerBlockIcons` | `public void registerBlockIcons(IIconRegister par1IconRegister) {` | クライアントテクスチャ登録時 (`TextureStitchEvent`) | `IIconRegister`でテクスチャ登録 | 1.8+で削除。`IIconRegister`/`IIcon`廃止。JSONモデル(`assets/defeatedcrow/blockstates/*.json`, `models/block/*.json`) + `ModelLoader`へ。`Util.getTexturePassNoAlt()`は`ResourceLocation`へ。 |
| `renderAsNormalBlock` | `public boolean renderAsNormalBlock() {` | 通常ブロック描画判定 | `renderAsNormalBlock` の1.7.10実装 | 1.12+で`isFullCube`/`isOpaqueCube`に統合。`renderAsNormalBlock()`は削除。`BlockRenderLayer`(`CUTOUT`/`TRANSLUCENT`)で代替。 |
| `setBlockBoundsBasedOnState` | `public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_,` | 状態依存のBounds設定 | `setBlockBoundsBasedOnState` の1.7.10実装 | 1.12+で`getBoundingBox(IBlockState, IBlockAccess, BlockPos)`へ。`setBlockBounds`は削除。AABBは`AxisAlignedBB`→`VoxelShape`へ。 |
| `setBlockBoundsForItemRender` | `public void setBlockBoundsForItemRender() {` | `Block` オーバーライド / 内部呼出 | `setBlockBoundsForItemRender` の1.7.10実装 | 削除。`getBoundingBox`で代替。 |
| `tickRate` | `public int tickRate(World p_149738_1_) {` | ティック間隔定義 | `tickRate` の1.7.10実装 | 維持。1.12+でも`tickRate(World)`。`world.scheduleBlockUpdate(pos, block, tickRate, 0)`は`world.scheduleUpdate(pos, block, tickRate)`へ。 |
| `updateTick` | `public void updateTick(World world, int x, int y, int z, Random rand) {` | ランダムティック時 (`setTickRandomly(true)`) | `updateTick` の1.7.10実装 | 1.12+: `updateTick(World, BlockPos, IBlockState, Random)`。`world.getBlockMetadata`/`setBlockMetadataWithNotify`→`world.setBlockState(pos, state.withProperty(...), 3)`。 |

## TileEntity / ItemBlock 連携
- Tile: `TileCPanel` (`src/main/java/mods/defeatedcrow/common/tile/TileCPanel.java:1`) - `CommonProxy.registerTileEntity()` で `GameRegistry.registerTileEntity(TileCPanel.class, "TileChalcedonyPanel")`
  - GUI: `なし`（Tileのみ。インベントリは`breakBlock`で散乱）
  - Renderer: `ClientProxy.registerRenderers()`でISBRH/TileEntityRenderer登録。`modelCPanel`使用。
- ItemBlock: `なし`（デフォルト`ItemBlock`）

## レシピ / ワールド生成 / その他連携
- Event: `AMTBlockRightClickEvent` / `PlantsClickEvent` / `TeamakerRightClickEvent` 等を `MinecraftForge.EVENT_BUS.post(event)` で発火

## レンダー / モデル
- Render ID: `DCsAppleMilk.modelCPanel` (`DCsAppleMilk.java: - ` 初期化, `CommonProxy.getRenderID()` , `ClientProxy.registerRenderers()` で登録)
- Renderer: `TileEntityCPanelRenderer` / `ISimpleBlockRenderingHandler` 実装 (`src/main/java/mods/defeatedcrow/client/model/tileentity/*`)
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
