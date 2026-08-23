# BlockMintCrop

> Category: `plants`  
> Source: `src/main/java/mods/defeatedcrow/common/block/plants/BlockMintCrop.java:1`  
> Registry: `defeatedcrow.cropMint` (`DCsAppleMilk.cropMint`)  
> ItemBlock: `なし`  
> TileEntity: `なし`  
> CreativeTab: `applemilkFood`

## 概要
植物・自然ブロック。成長・収穫・剪定等のギミック。`IShearable`/`IPlantable`/`IRightClickHarvestable`を実装。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:216` `public static Block cropMint;`
- **インスタンス生成**: `MaterialRegister.java:1006` `new BlockMintCrop()` / `setBlockName("defeatedcrow.cropMint")`
- **GameRegistry**: `GameRegistry.registerBlock(DCsAppleMilk.cropMint, "defeatedcrow.cropMint")` (`MaterialRegister.java:364`)
- **Material / Hardness / Resistance**: `Material.unknown`, hardness `0.0F`, resistance `` (`BlockMintCrop.java:1`)
- **CreativeTab**: `DCsAppleMilk.applemilkFood`
- **メタデータ**: メタデータでの種類分岐あり。`getSubBlocks`/`getIcon`で分岐。詳細は`ItemAPI`または`Block`内の`boxType`配列等を参照。

## 継承・インターフェース
- 継承: `BlockBush`
- 実装: `IGrowable`

## プロパティ / 状態
- `getRenderType()` : `public int getRenderType() {`
- BoundingBox: `setBlockBoundsBasedOnState()` / `getCollisionBoundingBoxFromPool()` / `getSelectedBoundingBoxFromPool()` 等でAABB制御。`setBlockBounds`でサイズ指定。

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `damageDropped` | `public int damageDropped(int par1) {` | ドロップ時のメタ決定 | `damageDropped` の1.7.10実装 | 1.12+: `damageDropped(IBlockState)`または`getMetaFromState`経由。`&7`等のマスクは`PropertyInteger`へ。 |
| `fertilize` | `public boolean fertilize(World par1World, int par2, int par3, int par4) {` | `Block` オーバーライド / 内部呼出 | `fertilize` の1.7.10実装 | 独自メソッド。`World.setBlockMetadataWithNotify`→`setBlockState`へ。 |
| `func_149851_a` | `public boolean func_149851_a(World world, int x, int y, int z, boolean var5) {` | `Block` オーバーライド / 内部呼出 | `func_149851_a` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `func_149852_a` | `public boolean func_149852_a(World world, Random rand, int x, int y, int z) {` | `Block` オーバーライド / 内部呼出 | `func_149852_a` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `func_149853_b` | `public void func_149853_b(World world, Random rand, int x, int y, int z) {` | `Block` オーバーライド / 内部呼出 | `func_149853_b` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `getCropItem` | `getCropItem(...)` | `Block` オーバーライド / 内部呼出 | `getCropItem` の1.7.10実装 | 維持。 |
| `getDrops` | `public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {` | 破壊時ドロップ生成 | Fortune考慮のドロップリスト生成 | 1.12+: `getDrops(NonNullList<ItemStack>, IBlockAccess, BlockPos, IBlockState, int fortune)`。`world.getBlockMetadata`→`state.getValue(PROPERTY)`。`Blocks.water.getIcon`等の参照削除。 |
| `getIcon` | `public IIcon getIcon(int par1, int par2) {` | レンダー時、面・メタごとに呼出 | 面・メタごとの`IIcon`返却 | 1.8+で削除。`IBlockState`/`Property*`/`BlockStateContainer`で状態管理。`getActualState`/`getStateFromMeta`/`getMetaFromState`へ分割。面ごとのテクスチャは`blockstate`の`variants`で分岐。 |
| `getItemDropped` | `public Item getItemDropped(int metadata, Random rand, int fortune) {` | ドロップ時のItem決定 | `getItemDropped` の1.7.10実装 | 1.12+: `getItemDropped(IBlockState, Random, int fortune)`。`Item.getItemFromBlock(this)`→`this.asItem()`。 |
| `getRenderType` | `public int getRenderType() {` | ISBRHレンダーID取得 | `getRenderType` の1.7.10実装 | 1.12+: `getRenderType(IBlockState): EnumBlockRenderType {MODEL, INVISIBLE, LIQUID}`。`DCsAppleMilk.modelXxx`のISBRH登録は`TileEntityRenderer`/`BER`へ移行。 |
| `quantityDropped` | `public int quantityDropped(Random par1Random) {` | ドロップ数決定 | `quantityDropped` の1.7.10実装 | 維持。`quantityDropped(IBlockState, int fortune, Random)`へ。`Random`引数順変更に注意。 |
| `registerBlockIcons` | `public void registerBlockIcons(IIconRegister par1IconRegister) {` | クライアントテクスチャ登録時 (`TextureStitchEvent`) | `IIconRegister`でテクスチャ登録 | 1.8+で削除。`IIconRegister`/`IIcon`廃止。JSONモデル(`assets/defeatedcrow/blockstates/*.json`, `models/block/*.json`) + `ModelLoader`へ。`Util.getTexturePassNoAlt()`は`ResourceLocation`へ。 |
| `updateTick` | `public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {` | ランダムティック時 (`setTickRandomly(true)`) | `updateTick` の1.7.10実装 | 1.12+: `updateTick(World, BlockPos, IBlockState, Random)`。`world.getBlockMetadata`/`setBlockMetadataWithNotify`→`world.setBlockState(pos, state.withProperty(...), 3)`。 |

## TileEntity / ItemBlock 連携
- Tile: `なし`（`Block`単体）
- ItemBlock: `なし`（デフォルト`ItemBlock`）

## レシピ / ワールド生成 / その他連携
- WorldGen: `WorldgenTeaTree` / `WorldgenClam` (`DCsAppleMilk.java:739-751` `GameRegistry.registerWorldGenerator`)で生成。
- 骨粉: `DCsBonemealEvent`で対応。
- Event: `AMTBlockRightClickEvent` / `PlantsClickEvent` / `TeamakerRightClickEvent` 等を `MinecraftForge.EVENT_BUS.post(event)` で発火

## レンダー / モデル
- Render ID: `DCsAppleMilk.modelMintCrop` (`DCsAppleMilk.java: - ` 初期化, `CommonProxy.getRenderID()` , `ClientProxy.registerRenderers()` で登録)
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
