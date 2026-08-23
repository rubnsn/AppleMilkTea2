# BlockYuzuLog

> Category: `plants`  
> Source: `src/main/java/mods/defeatedcrow/common/block/plants/BlockYuzuLog.java:1`  
> Registry: `defeatedcrow.logYuzu` (`DCsAppleMilk.logYuzu`)  
> ItemBlock: `なし`  
> TileEntity: `なし`  
> CreativeTab: `applemilk`

## 概要
植物・自然ブロック。成長・収穫・剪定等のギミック。`IShearable`/`IPlantable`/`IRightClickHarvestable`を実装。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:218` `public static Block logYuzu;`
- **インスタンス生成**: `MaterialRegister.java:997` `new BlockYuzuLog()` / `setBlockName("defeatedcrow.logYuzu")`
- **GameRegistry**: `GameRegistry.registerBlock(DCsAppleMilk.logYuzu, "defeatedcrow.logYuzu")` (`MaterialRegister.java:361`)
- **Material / Hardness / Resistance**: `Material.wood`, hardness `2.0F`, resistance `` (`BlockYuzuLog.java:1`)
- **CreativeTab**: `DCsAppleMilk.applemilk`
- **メタデータ**: メタデータでの種類分岐あり。`getSubBlocks`/`getIcon`で分岐。詳細は`ItemAPI`または`Block`内の`boxType`配列等を参照。

## 継承・インターフェース
- 継承: `BlockRotatedPillar`
- 実装: `なし`（または `IShearable` 等は個別クラスで確認）

## プロパティ / 状態
- `isOpaqueCube() -> false` / `renderAsNormalBlock() -> false` : 透過・特殊レンダー
- `getRenderType() -> DCsAppleMilk.modelYuzuLog` (`DCsAppleMilk.java: - `, `ClientProxy.registerRenderers()`でISBRH登録)
- BoundingBox: `setBlockBoundsBasedOnState()` / `getCollisionBoundingBoxFromPool()` / `getSelectedBoundingBoxFromPool()` 等でAABB制御。`setBlockBounds`でサイズ指定。

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `breakBlock` | `public void breakBlock(World world, int x, int y, int z, Block par5Block, int par6Meta) {` | 破壊時、TileのDrop処理・`notifyNeighbors` | 破壊時にTile内アイテムをEntityItemで散乱 | 1.12+: `breakBlock(World, BlockPos, IBlockState)`。`world.getTileEntity(pos)`でDrop処理。`world.func_147453_f`→`world.notifyNeighborsOfStateChange`。 |
| `canSustainLeaves` | `public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {` | `Block` オーバーライド / 内部呼出 | `canSustainLeaves` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `getItemDropped` | `public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {` | ドロップ時のItem決定 | `getItemDropped` の1.7.10実装 | 1.12+: `getItemDropped(IBlockState, Random, int fortune)`。`Item.getItemFromBlock(this)`→`this.asItem()`。 |
| `isWood` | `public boolean isWood(IBlockAccess world, int x, int y, int z) {` | `Block` オーバーライド / 内部呼出 | `isWood` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |
| `quantityDropped` | `public int quantityDropped(Random p_149745_1_) {` | ドロップ数決定 | `quantityDropped` の1.7.10実装 | 維持。`quantityDropped(IBlockState, int fortune, Random)`へ。`Random`引数順変更に注意。 |
| `registerBlockIcons` | `public void registerBlockIcons(IIconRegister par1IconRegister) {` | クライアントテクスチャ登録時 (`TextureStitchEvent`) | `IIconRegister`でテクスチャ登録 | 1.8+で削除。`IIconRegister`/`IIcon`廃止。JSONモデル(`assets/defeatedcrow/blockstates/*.json`, `models/block/*.json`) + `ModelLoader`へ。`Util.getTexturePassNoAlt()`は`ResourceLocation`へ。 |

## TileEntity / ItemBlock 連携
- Tile: `なし`（`Block`単体）
- ItemBlock: `なし`（デフォルト`ItemBlock`）

## レシピ / ワールド生成 / その他連携
- WorldGen: `WorldgenTeaTree` / `WorldgenClam` (`DCsAppleMilk.java:739-751` `GameRegistry.registerWorldGenerator`)で生成。
- 骨粉: `DCsBonemealEvent`で対応。
- Event: `AMTBlockRightClickEvent` / `PlantsClickEvent` / `TeamakerRightClickEvent` 等を `MinecraftForge.EVENT_BUS.post(event)` で発火

## レンダー / モデル
- Render ID: `DCsAppleMilk.modelYuzuLog` (`DCsAppleMilk.java: - ` 初期化, `CommonProxy.getRenderID()` , `ClientProxy.registerRenderers()` で登録)
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
