# BlockMelonBomb

> Category: `container`  
> Source: `src/main/java/mods/defeatedcrow/common/block/container/BlockMelonBomb.java:1`  
> Registry: `defeatedcrow.melonBomb` (`DCsAppleMilk.melonBomb`)  
> ItemBlock: `ItemMelonBomb` (`src/main/java/mods/defeatedcrow/common/block/container/ItemMelonBomb.java:1`)  
> TileEntity: `なし`  
> CreativeTab: `applemilkContainer`

## 概要
圧縮収納コンテナ。大量の素材を1ブロックに圧縮保管。`ItemBlock`のメタで種類分岐。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:198` `public static Block melonBomb;`
- **インスタンス生成**: `MaterialRegister.java:899` `new BlockMelonBomb()` / `setBlockName("defeatedcrow.melonBomb")`
- **GameRegistry**: `GameRegistry.registerBlock(DCsAppleMilk.melonBomb, ItemMelonBomb.class, "defeatedcrow.melonBomb")` (`MaterialRegister.java:390`)
- **Material / Hardness / Resistance**: `Material.wood`, hardness `0.1F`, resistance `` (`BlockMelonBomb.java:1`)
- **CreativeTab**: `DCsAppleMilk.applemilkContainer`
- **メタデータ**: メタデータでの種類分岐あり。`getSubBlocks`/`getIcon`で分岐。詳細は`ItemAPI`または`Block`内の`boxType`配列等を参照。

## 継承・インターフェース
- 継承: `Block`
- 実装: `なし`（または `IShearable` 等は個別クラスで確認）

## プロパティ / 状態
- `isOpaqueCube()` : `public boolean isOpaqueCube() {`
- `renderAsNormalBlock()` : `public boolean renderAsNormalBlock() {`
- BoundingBox: `setBlockBoundsBasedOnState()` / `getCollisionBoundingBoxFromPool()` / `getSelectedBoundingBoxFromPool()` 等でAABB制御。`setBlockBounds`でサイズ指定。

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `getCollisionBoundingBoxFromPool` | `public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {` | 衝突判定生成 | `getCollisionBoundingBoxFromPool` の1.7.10実装 | 1.12+: `getCollisionBoundingBox(IBlockState, IBlockAccess, BlockPos)` / `addCollisionBoxToList(IBlockState, World, BlockPos, AxisAlignedBB, List, Entity, boolean)`。1.16+ `VoxelShape`/`VoxelShapes`へ。 |
| `getIcon` | `public IIcon getIcon(int par1, int par2) {` | レンダー時、面・メタごとに呼出 | 面・メタごとの`IIcon`返却 | 1.8+で削除。`IBlockState`/`Property*`/`BlockStateContainer`で状態管理。`getActualState`/`getStateFromMeta`/`getMetaFromState`へ分割。面ごとのテクスチャは`blockstate`の`variants`で分岐。 |
| `getItemDropped` | `public Item getItemDropped(int metadata, Random rand, int fortune) {` | ドロップ時のItem決定 | `getItemDropped` の1.7.10実装 | 1.12+: `getItemDropped(IBlockState, Random, int fortune)`。`Item.getItemFromBlock(this)`→`this.asItem()`。 |
| `getSelectedBoundingBoxFromPool` | `public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {` | 選択ボックス生成 | `getSelectedBoundingBoxFromPool` の1.7.10実装 | 1.12+: `getSelectedBoundingBox(IBlockState, World, BlockPos)`。 |
| `isOpaqueCube` | `public boolean isOpaqueCube() {` | 光透過・描画判定 | `isOpaqueCube` の1.7.10実装 | 1.12+: `isOpaqueCube(IBlockState)`。戻り値ロジック維持。`isFullCube`/`isTranslucent`も併せて確認。 |
| `onBlockActivated` | `public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,` | プレイヤー右クリック時 (`PlayerInteractEvent` 経由) | GUI表示・アイテム収受・イベント発火 (`AMTBlockRightClickEvent`) | 1.12+: `onBlockActivated(World, BlockPos, IBlockState, EntityPlayer, EnumHand, EnumFacing, float,float,float)`。`world.getBlockMetadata(x,y,z)`→`world.getBlockState(pos)`、`world.getTileEntity(x,y,z)`→`world.getTileEntity(pos)`。`MinecraftForge.EVENT_BUS.post`は維持。`PlayerInteractEvent`との兼用注意。 |
| `onBlockPlacedBy` | `public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,` | 設置時、プレイヤー向きでメタ決定 (`ItemBlock.placeBlockAt`) | `onBlockPlacedBy` の1.7.10実装 | 1.12+: `onBlockPlacedBy(World, BlockPos, IBlockState, EntityLivingBase, ItemStack)`。`MathHelper.floor_double(rotationYaw*4/360+0.5)&3`は`EnumFacing.fromAngle`/`HorizontalFacing`へ。`setBlockMetadataWithNotify`→`setBlockState`。 |
| `onNeighborBlockChange` | `public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {` | `Block` オーバーライド / 内部呼出 | `onNeighborBlockChange` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `registerBlockIcons` | `public void registerBlockIcons(IIconRegister par1IconRegister) {` | クライアントテクスチャ登録時 (`TextureStitchEvent`) | `IIconRegister`でテクスチャ登録 | 1.8+で削除。`IIconRegister`/`IIcon`廃止。JSONモデル(`assets/defeatedcrow/blockstates/*.json`, `models/block/*.json`) + `ModelLoader`へ。`Util.getTexturePassNoAlt()`は`ResourceLocation`へ。 |
| `renderAsNormalBlock` | `public boolean renderAsNormalBlock() {` | 通常ブロック描画判定 | `renderAsNormalBlock` の1.7.10実装 | 1.12+で`isFullCube`/`isOpaqueCube`に統合。`renderAsNormalBlock()`は削除。`BlockRenderLayer`(`CUTOUT`/`TRANSLUCENT`)で代替。 |
| `setBlockBoundsBasedOnState` | `public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {` | 状態依存のBounds設定 | `setBlockBoundsBasedOnState` の1.7.10実装 | 1.12+で`getBoundingBox(IBlockState, IBlockAccess, BlockPos)`へ。`setBlockBounds`は削除。AABBは`AxisAlignedBB`→`VoxelShape`へ。 |

## TileEntity / ItemBlock 連携
- Tile: `なし`（`Block`単体）
- ItemBlock: `ItemMelonBomb` (`src/main/java/mods/defeatedcrow/common/block/container/ItemMelonBomb.java:1`) - `getUnlocalizedName(ItemStack)` / `getMetadata()` / `placeBlockAt()` 等。メタ分岐の表示名対応。

## レシピ / ワールド生成 / その他連携
- OreDictionary: `RegisterOreHandler`で鉱石辞書登録。`ICompressedItem`実装で解凍レシピ対応。
- Event: `AMTBlockRightClickEvent` / `PlantsClickEvent` / `TeamakerRightClickEvent` 等を `MinecraftForge.EVENT_BUS.post(event)` で発火

## レンダー / モデル
- Render ID: `DCsAppleMilk.modelMelonBomb` (`DCsAppleMilk.java: - ` 初期化, `CommonProxy.getRenderID()` , `ClientProxy.registerRenderers()` で登録)
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
- [カテゴリ別一覧](../README.md) (container)
- [移行ガイド](../migration-guide.md)
- [Item 一覧](../../items.md)
