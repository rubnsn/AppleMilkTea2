# BlockCamOilFluid

> Category: `fluid`  
> Source: `src/main/java/mods/defeatedcrow/common/fluid/BlockCamOilFluid.java:1`  
> Registry: `defeatedcrow.blockCamOil` (`DCsAppleMilk.blockCamelliaOil`)  
> ItemBlock: `なし`  
> TileEntity: `なし`  
> CreativeTab: `applemilk`

## 概要
流体ブロック。`Block`継承のダミー/流体表示用。実際の流体登録は`FluidRegistry`で管理。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:338` `public static Block blockCamelliaOil;`
- **インスタンス生成**: `MaterialRegister.java:530` `new BlockCamOilFluid()` / `setBlockName("defeatedcrow.blockCamOil")`
- **GameRegistry**: `GameRegistry.registerBlock(DCsAppleMilk.blockCamelliaOil, "defeatedcrow.blockCamOil")` (`MaterialRegister.java:532`)
- **Material / Hardness / Resistance**: `Material.unknown`, hardness ``, resistance `` (`BlockCamOilFluid.java:1`)
- **CreativeTab**: `DCsAppleMilk.applemilk`
- **メタデータ**: メタデータでの種類分岐あり。`getSubBlocks`/`getIcon`で分岐。詳細は`ItemAPI`または`Block`内の`boxType`配列等を参照。

## 継承・インターフェース
- 継承: `BlockFluidClassic`
- 実装: `なし`（または `IShearable` 等は個別クラスで確認）

## プロパティ / 状態
- `isOpaqueCube() -> false` / `renderAsNormalBlock() -> false` : 透過・特殊レンダー
- `getRenderType() -> DCsAppleMilk.modelCamOilFluid` (`DCsAppleMilk.java: - `, `ClientProxy.registerRenderers()`でISBRH登録)
- BoundingBox: `setBlockBoundsBasedOnState()` / `getCollisionBoundingBoxFromPool()` / `getSelectedBoundingBoxFromPool()` 等でAABB制御。`setBlockBounds`でサイズ指定。

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `canDisplace` | `public boolean canDisplace(IBlockAccess world, int x, int y, int z) {` | `Block` オーバーライド / 内部呼出 | `canDisplace` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `displaceIfPossible` | `public boolean displaceIfPossible(World world, int x, int y, int z) {` | `Block` オーバーライド / 内部呼出 | `displaceIfPossible` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `getIcon` | `public IIcon getIcon(int side, int meta) {` | レンダー時、面・メタごとに呼出 | 面・メタごとの`IIcon`返却 | 1.8+で削除。`IBlockState`/`Property*`/`BlockStateContainer`で状態管理。`getActualState`/`getStateFromMeta`/`getMetaFromState`へ分割。面ごとのテクスチャは`blockstate`の`variants`で分岐。 |
| `onEntityCollidedWithBlock` | `public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {` | エンティティ接触時 | `onEntityCollidedWithBlock` の1.7.10実装 | 1.12+: `onEntityCollision(World, BlockPos, IBlockState, Entity)` (1.16+ `onEntityCollision(BlockState, World, BlockPos, Entity)`)。 |
| `registerBlockIcons` | `public void registerBlockIcons(IIconRegister par1IconRegister) {` | クライアントテクスチャ登録時 (`TextureStitchEvent`) | `IIconRegister`でテクスチャ登録 | 1.8+で削除。`IIconRegister`/`IIcon`廃止。JSONモデル(`assets/defeatedcrow/blockstates/*.json`, `models/block/*.json`) + `ModelLoader`へ。`Util.getTexturePassNoAlt()`は`ResourceLocation`へ。 |

## TileEntity / ItemBlock 連携
- Tile: `なし`（`Block`単体）
- ItemBlock: `なし`（デフォルト`ItemBlock`）

## レシピ / ワールド生成 / その他連携
- Fluid: `MaterialRegister.addFluid()`で`FluidRegistry.registerFluid`→`GameRegistry.registerBlock`→`FluidContainerRegistry.registerFluidContainer`でバケツ/ボトル紐付け。
- Event: `AMTBlockRightClickEvent` / `PlantsClickEvent` / `TeamakerRightClickEvent` 等を `MinecraftForge.EVENT_BUS.post(event)` で発火

## レンダー / モデル
- Render ID: `DCsAppleMilk.modelCamOilFluid` (`DCsAppleMilk.java: - ` 初期化, `CommonProxy.getRenderID()` , `ClientProxy.registerRenderers()` で登録)
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
- [カテゴリ別一覧](../README.md) (fluid)
- [移行ガイド](../migration-guide.md)
- [Fluid 一覧](../../fluids.md)
