# BlockSilkyMelon

> Category: `container`  
> Source: `src/main/java/mods/defeatedcrow/common/block/container/BlockSilkyMelon.java:1`  
> Registry: `defeatedcrow.melonSilky` (`DCsAppleMilk.silkyMelon`)  
> ItemBlock: `ItemSilkyMelon` (`src/main/java/mods/defeatedcrow/common/block/container/ItemSilkyMelon.java:1`)  
> TileEntity: `なし`  
> CreativeTab: `applemilkContainer`

## 概要
圧縮収納コンテナ。大量の素材を1ブロックに圧縮保管。`ItemBlock`のメタで種類分岐。

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:202` `public static Block silkyMelon;`
- **インスタンス生成**: `MaterialRegister.java:902` `new BlockSilkyMelon()` / `setBlockName("defeatedcrow.melonSilky")`
- **GameRegistry**: `GameRegistry.registerBlock(DCsAppleMilk.silkyMelon, ItemSilkyMelon.class, "defeatedcrow.melonSilky")` (`MaterialRegister.java:391`)
- **Material / Hardness / Resistance**: `Material.unknown`, hardness ``, resistance `` (`BlockSilkyMelon.java:1`)
- **CreativeTab**: `DCsAppleMilk.applemilkContainer`
- **メタデータ**: メタデータでの種類分岐あり。`getSubBlocks`/`getIcon`で分岐。詳細は`ItemAPI`または`Block`内の`boxType`配列等を参照。

## 継承・インターフェース
- 継承: `BlockMelonBomb`
- 実装: `なし`（または `IShearable` 等は個別クラスで確認）

## プロパティ / 状態
- `isOpaqueCube() -> false` / `renderAsNormalBlock() -> false` : 透過・特殊レンダー
- `getRenderType() -> DCsAppleMilk.modelSilkyMelon` (`DCsAppleMilk.java: - `, `ClientProxy.registerRenderers()`でISBRH登録)
- BoundingBox: `setBlockBoundsBasedOnState()` / `getCollisionBoundingBoxFromPool()` / `getSelectedBoundingBoxFromPool()` 等でAABB制御。`setBlockBounds`でサイズ指定。

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `registerBlockIcons` | `public void registerBlockIcons(IIconRegister par1IconRegister) {` | クライアントテクスチャ登録時 (`TextureStitchEvent`) | `IIconRegister`でテクスチャ登録 | 1.8+で削除。`IIconRegister`/`IIcon`廃止。JSONモデル(`assets/defeatedcrow/blockstates/*.json`, `models/block/*.json`) + `ModelLoader`へ。`Util.getTexturePassNoAlt()`は`ResourceLocation`へ。 |
| `setEntityMelon` | `protected void setEntityMelon(World par1World, int par2, int par3, int par4) {` | `Block` オーバーライド / 内部呼出 | `setEntityMelon` の1.7.10実装 | 要確認: 1.7.10→1.12.2でシグネチャ変更の可能性あり。`World, int x,y,z`→`World, BlockPos, IBlockState`、`IIcon`→JSONモデル等のパターンに該当するか確認。 |

## TileEntity / ItemBlock 連携
- Tile: `なし`（`Block`単体）
- ItemBlock: `ItemSilkyMelon` (`src/main/java/mods/defeatedcrow/common/block/container/ItemSilkyMelon.java:1`) - `getUnlocalizedName(ItemStack)` / `getMetadata()` / `placeBlockAt()` 等。メタ分岐の表示名対応。

## レシピ / ワールド生成 / その他連携
- OreDictionary: `RegisterOreHandler`で鉱石辞書登録。`ICompressedItem`実装で解凍レシピ対応。
- Event: `AMTBlockRightClickEvent` / `PlantsClickEvent` / `TeamakerRightClickEvent` 等を `MinecraftForge.EVENT_BUS.post(event)` で発火

## レンダー / モデル
- Render ID: `DCsAppleMilk.modelSilkyMelon` (`DCsAppleMilk.java: - ` 初期化, `CommonProxy.getRenderID()` , `ClientProxy.registerRenderers()` で登録)
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
