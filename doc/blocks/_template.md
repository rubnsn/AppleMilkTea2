# BlockName

> Category: `appliance | energy | edible | brewing | container | plants | decorative | chalcedony | fluid`
> Source: `src/main/java/mods/defeatedcrow/common/block/.../BlockName.java:1`
> Registry: `defeatedcrow.xxx` (`DCsAppleMilk.fieldName`)
> ItemBlock: `ItemXxx` (`src/main/java/.../ItemXxx.java:1`)
> TileEntity: `TileXxx` (`CommonProxy.java:xx`, `src/main/java/mods/defeatedcrow/common/tile/...:1`)
> CreativeTab: `applemilk / applemilkContainer ...`

## 概要
[1-2文でBlockの役割・特徴を記述。設置型/装置型/収納型などの分類]

## 登録情報
- **フィールド定義**: `DCsAppleMilk.java:xxx` `public static Block fieldName;`
- **インスタンス生成**: `MaterialRegister.java:xxx` `new BlockXxx()` / `setBlockName("defeatedcrow.xxx")`
- **GameRegistry**: `GameRegistry.registerBlock(DCsAppleMilk.fieldName, ItemXxx.class, "defeatedcrow.xxx")` (`MaterialRegister.java:xxx`)
- **Material / Hardness / Resistance**: `Material.xxx`, hardness `x.xF`, resistance `x.xF`, `setStepSound`, `setLightLevel` 等 (`BlockXxx.java:xx`)
- **CreativeTab**: `DCsAppleMilk.applemilk` 等
- **メタデータ**: [メタ管理の説明。例: `0:xxx, 1:yyy` または `metadata & 7 = type, >>3 = flag` ]

## 継承・インターフェース
- 継承: `Block` / `BlockContainer` / `BlockBreakable` 等
- 実装: `IShearable`, `IPlantable`, `IRightClickHarvestable` 等

## プロパティ / 状態
- `isOpaqueCube() -> false` : 透過ブロック
- `renderAsNormalBlock() -> false` : 特殊レンダラー
- `getRenderType() -> DCsAppleMilk.modelXxx` (`DCsAppleMilk.java:xxx` で割り当て、 `ClientProxy.registerRenderers()` で ISBRH 登録)
- `getLightValue()` / `setLightLevel()` 等
- BoundingBox: `setBlockBoundsBasedOnState()` / `getCollisionBoundingBoxFromPool()` / `getSelectedBoundingBoxFromPool()` 等

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `onBlockActivated` | `boolean onBlockActivated(World, int x, int y, int z, EntityPlayer, int side, float hitX, float hitY, float hitZ)` | プレイヤー右クリック時 (`PlayerInteractEvent`) | GUI表示・アイテム収受等 | `IBlockState, BlockPos, World, EntityPlayer, EnumHand, EnumFacing` に変更。`world.getTileEntity(pos)` / `world.getBlockState(pos)` に置換 |
| `createNewTileEntity` | `TileEntity createNewTileEntity(World, int meta)` | `BlockContainer` 生成時 | Tile生成 | `Block` + `ITileEntityProvider` に変更。`createTileEntity(World, IBlockState)` 等。`BlockContainer` 廃止 (1.14+) |
| `registerBlockIcons` | `void registerBlockIcons(IIconRegister)` | クライアントテクスチャ登録 | `IIcon` 登録 | 削除。`ModelRegistry` + JSON `blockstates/*.json` / `models/block/*.json` へ |
| `getIcon` | `IIcon getIcon(int side, int meta)` | レンダー時 | 面ごとのアイコン | 削除。`getStateFromMeta` / `getActualState` / `BlockStateContainer` へ |
| ... | ... | ... | ... | ... |

## TileEntity / ItemBlock 連携
- Tile: `TileXxx` (`src/main/java/mods/defeatedcrow/common/tile/TileXxx.java:1`) - `CommonProxy.registerTileEntity()` で `GameRegistry.registerTileEntity(TileXxx.class, "TileXxx")`
- GUI: `ContainerXxx` / `GuiXxx` (`CommonProxy.getServerGuiElement/getClientGuiElement`) - GUI ID `x`
- ItemBlock: `ItemXxx` - `getUnlocalizedName(ItemStack)` / `getMetadata()` / `placeBlockAt()` 等。メタ分岐の表示名対応。

## レシピ / ワールド生成 / その他連携
- レシピ: `RegisterMakerRecipe.registerXxx()` / `DCsRecipeRegister` 等
- WorldGen: `WorldgenXxx` 等 (該当する場合)
- Event: `AMTBlockRightClickEvent` / `PlantsClickEvent` 等を `MinecraftForge.EVENT_BUS.post(event)` で発火
- Achievement: `AchievementRegister.xxx` を `triggerAchievement` で付与

## レンダー / モデル
- Render ID: `DCsAppleMilk.modelXxx` (`DCsAppleMilk.java:xxx`初期化, `CommonProxy.getRenderID()` , `ClientProxy.registerRenderers()` で登録)
- Renderer: `TileEntityXxxRenderer` / `ISimpleBlockRenderingHandler` 実装 (`src/main/java/mods/defeatedcrow/client/model/tileentity/*`)
- パーティクル: `randomDisplayTick()` で `EntityDCCloudFX` 等

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
- [Block 一覧](../blocks.md)
- [TileEntity 一覧](../tile-entities.md)
- [カテゴリ別一覧](./README.md) (該当カテゴリへ)
- [移行ガイド](./migration-guide.md)
