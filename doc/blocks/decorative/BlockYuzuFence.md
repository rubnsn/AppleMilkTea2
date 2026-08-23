# BlockYuzuFence

> Category: `decorative`  
> Source: `src/main/java/mods/defeatedcrow/common/block/BlockYuzuFence.java:1`  
> Registry: `defeatedcrow.yuzuFence` (`DCsAppleMilk.yuzuFence`)  
> ItemBlock: `なし`  
> TileEntity: `なし`  
> CreativeTab: `applemilk`

## 概要
インテリア・装飾ブロック。向きやBoundingBoxを細かく制御。多くはTileで方向保持。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:204` `public static Block yuzuFence;`
- **インスタンス生成**: `MaterialRegister.java:970` `new BlockYuzuFence()` / `setBlockName("defeatedcrow.yuzuFence")`
- **GameRegistry**: `GameRegistry.registerBlock(DCsAppleMilk.yuzuFence, "defeatedcrow.yuzuFence")` (`MaterialRegister.java:395`)
- **Material / Hardness / Resistance**: `Material.wood`, hardness `0.2F`, resistance `3.0F` (`BlockYuzuFence.java:1`)
- **CreativeTab**: `DCsAppleMilk.applemilk`
- **メタデータ**: メタデータでの種類分岐あり。`getSubBlocks`/`getIcon`で分岐。詳細は`ItemAPI`または`Block`内の`boxType`配列等を参照。

## 継承・インターフェース
- 継承: `Block`
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
| `addCollisionBoxesToList` | `public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list,
        Entity entity) {` | 衝突ボックスリスト構築 (Fence等) | `addCollisionBoxesToList` の1.7.10実装 | 1.12+維持。`addCollisionBoxToList`へリネーム。`ForgeDirection`→`EnumFacing`。 |
| `damageDropped` | `public int damageDropped(int par1) {` | ドロップ時のメタ決定 | `damageDropped` の1.7.10実装 | 1.12+: `damageDropped(IBlockState)`または`getMetaFromState`経由。`&7`等のマスクは`PropertyInteger`へ。 |
| `getRenderType` | `public int getRenderType() {` | ISBRHレンダーID取得 | `getRenderType` の1.7.10実装 | 1.12+: `getRenderType(IBlockState): EnumBlockRenderType {MODEL, INVISIBLE, LIQUID}`。`DCsAppleMilk.modelXxx`のISBRH登録は`TileEntityRenderer`/`BER`へ移行。 |
| `isOpaqueCube` | `public boolean isOpaqueCube() {` | 光透過・描画判定 | `isOpaqueCube` の1.7.10実装 | 1.12+: `isOpaqueCube(IBlockState)`。戻り値ロジック維持。`isFullCube`/`isTranslucent`も併せて確認。 |
| `isSideSolid` | `isSideSolid(...)` | `ForgeDirection`側面が固体か判定 (`isSideSolid`→`canConnect`等で使用) | `isSideSolid` の1.7.10実装 | 1.12+: `isSideSolid(IBlockState, IBlockAccess, BlockPos, EnumFacing)`。`ForgeDirection`→`EnumFacing`。 |
| `onEntityCollidedWithBlock` | `public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {` | エンティティ接触時 | `onEntityCollidedWithBlock` の1.7.10実装 | 1.12+: `onEntityCollision(World, BlockPos, IBlockState, Entity)` (1.16+ `onEntityCollision(BlockState, World, BlockPos, Entity)`)。 |
| `registerBlockIcons` | `public void registerBlockIcons(IIconRegister par1IconRegister) {` | クライアントテクスチャ登録時 (`TextureStitchEvent`) | `IIconRegister`でテクスチャ登録 | 1.8+で削除。`IIconRegister`/`IIcon`廃止。JSONモデル(`assets/defeatedcrow/blockstates/*.json`, `models/block/*.json`) + `ModelLoader`へ。`Util.getTexturePassNoAlt()`は`ResourceLocation`へ。 |
| `renderAsNormalBlock` | `public boolean renderAsNormalBlock() {` | 通常ブロック描画判定 | `renderAsNormalBlock` の1.7.10実装 | 1.12+で`isFullCube`/`isOpaqueCube`に統合。`renderAsNormalBlock()`は削除。`BlockRenderLayer`(`CUTOUT`/`TRANSLUCENT`)で代替。 |
| `setBlockBoundsBasedOnState` | `public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {` | 状態依存のBounds設定 | `setBlockBoundsBasedOnState` の1.7.10実装 | 1.12+で`getBoundingBox(IBlockState, IBlockAccess, BlockPos)`へ。`setBlockBounds`は削除。AABBは`AxisAlignedBB`→`VoxelShape`へ。 |

## TileEntity / ItemBlock 連携
- Tile: `なし`（`Block`単体）
- ItemBlock: `なし`（デフォルト`ItemBlock`）

## レシピ / ワールド生成 / その他連携
- Event: `AMTBlockRightClickEvent` / `PlantsClickEvent` / `TeamakerRightClickEvent` 等を `MinecraftForge.EVENT_BUS.post(event)` で発火

## レンダー / モデル
- Render ID: `DCsAppleMilk.modelYuzuFence` (`DCsAppleMilk.java: - ` 初期化, `CommonProxy.getRenderID()` , `ClientProxy.registerRenderers()` で登録)
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
- [カテゴリ別一覧](../README.md) (decorative)
- [移行ガイド](../migration-guide.md)
- [Item 一覧](../../items.md)
