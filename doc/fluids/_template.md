# FluidName

> Source: `src/main/java/mods/defeatedcrow/common/fluid/FluidName.java:1` / `BlockOilFluid.java:1`
> Registry: `FluidRegistry.registerFluid(new Fluid("xxx"))` (`MaterialRegister.java:490` `addFluid()`)
> Block: `DCsAppleMilk.blockXxx` (`BlockOilFluid` / `BlockDummyFluid`) (`DCsAppleMilk.java:xxx`)
> Container: `DCsAppleMilk.bucketXxx` / `bottleXxx` (`ItemBucketXxx` / `ItemBottleXxx`)
> CreativeTab: `applemilk` (バケツ)

## 概要
[1-2文で流体の用途・特徴を記述。食用油/醸造酒(若)/完成酒/ダミー演出の分類]

## 登録情報
- **Fluid**: `new Fluid("xxx").setDensity(800).setViscosity(1500).setLuminosity(0)` (`MaterialRegister.java:500`)
- **Block**: `new BlockOilFluid(fluid, Material.water)` → `setBlockName("defeatedcrow.blockXxx")` → `GameRegistry.registerBlock`
- **Bucket**: `new ItemBucketXxx(Block)` → `FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid,1000), new ItemStack(bucketVegiOil), new ItemStack(Items.bucket))`
- **Bottle**: `new ItemBottleXxx(...)` (200mB) → `registerFluidContainer(new FluidStack(fluid,200), new ItemStack(bottle), new ItemStack(Item.getItemFromBlock(DCsAppleMilk.emptyBottle)))`
- **Density/Viscosity**: `800 / 1500` (油) / `1000 / 1000` (酒) 等

## 継承・インターフェース
- Fluid: `net.minecraftforge.fluids.Fluid`
- Block: `BlockFluidClassic` (`BlockOilFluid`, `BlockCamOilFluid`) / `Block` (`BlockDummyFluid`)
- Item: `ItemBucket` / `Item` + `IFluidContainerItem`

## プロパティ / 状態
- **Still Icon**: `IIcon stillIcon` (`registerBlockIcons` で `defeatedcrow:fluid_xxx_still`)
- **Flow Icon**: `IIcon flowIcon` (`defeatedcrow:fluid_xxx_flow`)
- **CanDisplace**: `false` (油)
- **Temperature**: 300K 固定
- **Gaseous**: `false`

## オーバーライドメソッド一覧
> 移行時の対応要否を `移行` 列に記載。1.7.10 → 1.12.2 / 1.16.5 での変更点を要約。

| メソッド | シグネチャ (1.7.10) | 参照元 / 呼出タイミング | 説明 | 移行 (1.12.2+) |
|---|---|---|---|---|
| `registerBlockIcons` | `void registerBlockIcons(IIconRegister)` | クライアント初期化 | アイコン登録 | 削除 → `TextureStitchEvent` / `FluidAttributes` の `stillTexture/flowTexture` (`ResourceLocation`) |
| `getIcon` | `IIcon getIcon(int side, int meta)` | レンダー時 | 流体テクスチャ | 削除 → `FluidAttributes.Builder(still, flowing)` |
| `canDisplace` | `boolean canDisplace(IBlockAccess, int x,y,z)` | 流体置換判定 | 置換可否 | `canDisplace(FluidState, BlockGetter, BlockPos, Fluid, Direction)` |
| `displaceIfPossible` | `boolean displaceIfPossible(World,int x,y,z)` | 流動時 | 置換実行 | 同上 |
| `onBlockActivated` | `boolean onBlockActivated(World,int x,y,z, EntityPlayer,...)` | バケツ汲み | 汲み取り | `use(BlockState, Level, BlockPos, Player, InteractionHand, BlockHitResult)` |
| `getQuantaValue` | `int getQuantaValue(IBlockAccess,int x,y,z)` | 流動計算 | 量 | `getAmount(FluidState)` |

## コンテナ / ディスペンサー連携
- **Fill**: `BucketFillEvent` (`FillBucketEvent`) で流体ブロック右クリック時にバケツ取得 (`src/main/java/mods/defeatedcrow/event/BucketFillEvent.java:1`)
- **Dispenser**: `FluidDispenser.load()` で `BlockDispenser.dispenseBehaviorRegistry.putObject(bottleVegiOil, Behavior)` (`src/main/java/mods/defeatedcrow/event/FluidDispenser.java:1`)
- **ContainerMap**: `FluidContainerRegisterEvent` → `FluidContMap.Register` (`src/main/java/mods/defeatedcrow/event/FluidContainerRegisterEvent.java:1`)

## レンダー / モデル
- 1.7: `BlockFluidClassic` はバニラ流体レンダー（`IIcon`）
- 1.12+: `Fluid` → `FluidAttributes` + `BlockFluidBase` / `FlowingFluid` (1.13+ `ForgeFlowingFluid` + `LiquidBlock`)
- テクスチャ: `assets/defeatedcrow/textures/blocks/fluid_xxx_still.png` + `fluid_xxx_flow.png`

## 1.7.10 → 1.12.2 移行チェックリスト
- [ ] `FluidRegistry.registerFluid(new Fluid("xxx"))` → `DeferredRegister<Fluid>` / `RegistryEvent.Register<Fluid>` + `new ForgeFlowingFluid.Properties(...)`
- [ ] `Fluid.setDensity/Viscosity/Luminosity` → `FluidAttributes.Builder(density, viscosity, temperature, luminosity).color(...).sound(...)`
- [ ] `BlockFluidClassic` → `FlowingFluidBlock` / `LiquidBlock` (1.13+ `FlowingFluid` + `ForgeFlowingFluid.Flowing/Souce`)
- [ ] `IIcon` / `registerBlockIcons` / `getIcon` → `ResourceLocation` (`stillTexture`/`flowingTexture`/`overlayTexture`)
- [ ] `FluidContainerRegistry.registerFluidContainer(FluidStack, filled, empty)` → `CapabilityFluidHandler` (`IFluidHandlerItem` / `FluidUtil.getFluidHandler`) / `FluidAttributes.bucket`
- [ ] `ItemBucket` → `BucketItem(Supplier<? extends Fluid>, Properties)` (1.16+ コンストラクタ変更)
- [ ] `FillBucketEvent` → `FillBucketEvent` 維持だが `MovingObjectPosition` → `RayTraceResult` / `BlockHitResult`, `Result` → `Event.Result`
- [ ] `BlockDispenser.dispenseBehaviorRegistry` → `DispenserBlock.registerBehavior(ItemLike, DispenseItemBehavior)` (1.14+)
- [ ] 流体の `ResourceLocation` は `forge:fluid` タグや `FluidStack` で統一。`FluidRegistry.getFluidStack("xxx", amount)` → `new FluidStack(ForgeRegistries.FLUIDS.getValue(RL), amount)`

## 関連ドキュメント
- [Fluid 一覧](../fluids.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
- [Block 一覧](../blocks.md) - 流体ブロック
- [Item 一覧](../items.md) - バケツ/ボトル
