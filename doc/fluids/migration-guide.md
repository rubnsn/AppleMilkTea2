# Fluid 移行ガイド - 1.7.10 → 1.12.2 / 1.16.5 / 1.20.1

> 対象: `AppleMilkTea2` 全18 Fluid + 4 BlockFluid + 5 Container / `MaterialRegister.java:490-718`
> 最終更新: 2026-08-24（1.20.1ブラッシュアップ: 2026-08-24）  
> 関連: [ビルド移行ガイド](../build.md)

## 概要
流体の 1.7.10→1.12.2/1.16 移行対応。`FluidRegistry` → `DeferredRegister<Fluid>` + `FlowingFluid` への刷新が中心。**1.20.1では `FluidAttributes` が `FluidType` に分離（Forge 1.19.3+）。`Fluid`（液体本体）と `FluidType`（流体の振る舞い: density/viscosity/sound/color/lightLevel）を別DeferredRegisterで管理し、`ForgeFlowingFluid.Properties` の引数が `FluidType` + `Fluid` に変化。旧 `FluidAttributes.Builder` は 1.20.1で削除。**

> **ギャップ補足**（plan.md監査）: 旧DOCは `FluidAttributes` レガシーで止まり、1.20 `FluidType` 未適用。1.20.1 Forge 47では `FluidType(PROPERTIES)` → `Fluid` → `LiquidBlock` → `BucketItem` の4層が正。

## 登録の大局

| 1.7.10 | 1.12.2+ | 1.16.5+ | 1.20.1 | 参照 |
|---|---|---|---|---|
| `FluidRegistry.registerFluid(new Fluid("vegitable_oil").setDensity(800).setViscosity(1500))` (`MaterialRegister.java:500`) | `DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, "defeatedcrow")` + `FLUIDS.register("vegitable_oil", ()-> new ForgeFlowingFluid.Source(props))` + `ForgeFlowingFluid.Properties` | 同左 + `FlowingFluid` (`Source`/`Flowing`) + `FluidAttributes.builder(STILL,FLOWING).density(800).viscosity(1500)` | **`DeferredRegister<FluidType>` + `DeferredRegister<Fluid>` + `DeferredRegister<Block>` + `DeferredRegister<Item>` の4登録。`FluidType` は `new FluidType(FluidType.Properties.create().density(800).viscosity(1500).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL).lightLevel(0))` + `FLUID_TYPES.register("vegitable_oil", ()-> fluidType)`。`ForgeFlowingFluid.Properties` は `new ForgeFlowingFluid.Properties(TYPE, STILL, FLOWING).block(BLOCK).bucket(BUCKET)`** | `MaterialRegister.java:490` |
| `GameRegistry.registerBlock(new BlockOilFluid(fluid, Material.water))` | `DeferredRegister<Block> BLOCKS` → `BLOCKS.register("block_vegi_oil", ()-> new FlowingFluidBlock(supplier, Block.Properties.of(Material.WATER).noCollission()...))` | `LiquidBlock` (`FlowingFluid` supplier) | **`LiquidBlock` 維持だが `Block.Properties` は `BlockBehaviour.Properties.of().noCollission().strength(100F).noLootTable().liquid()` + `Fluid` supplier は `Holder<Fluid>` に** | 同上 |
| `FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid,1000), new ItemStack(bucketVegiOil), new ItemStack(Items.bucket))` | `CapabilityFluidHandler` (`FluidUtil.getFluidHandler` / `IFluidHandlerItem`) + `BucketItem` | `BucketItem(Supplier<? extends Fluid>, Item.Properties)` | **同左 + `ForgeCapabilities.FLUID_HANDLER_ITEM` + `IFluidHandlerItem` は `FluidType` の `getSound`/`getLightLevel` を参照** | `MaterialRegister.java:508` |
| `IIcon stillIcon/flowIcon` (`registerBlockIcons`) | `ResourceLocation` (`stillTexture`/`flowingTexture` in `FluidAttributes`) | `FluidAttributes.Builder(still, flowing).color(...).overlay(...).sound(...)` | **`FluidType` の `Properties` で `stillTexture/flowingTexture/overlayTexture` を `ResourceLocation` で、色は `FluidType` の `getColor()` override、`lightLevel` は `FluidType.Properties.lightLevel(int)`** | `BlockOilFluid.java:1` |
| `BucketFillEvent` (`FillBucketEvent` → `MovingObjectPosition`) | 維持だが `RayTraceResult` / `BlockHitResult` | `FillBucketEvent` 維持 | **同左** | `event/BucketFillEvent.java:1` |
| `FluidDispenser.load()` (`BlockDispenser.dispenseBehaviorRegistry.putObject`) | `DispenserBlock.registerBehavior(ItemLike, DispenseItemBehavior)` | 同左 | **同左** | `event/FluidDispenser.java:1` |

## クラス移行

| 1.7.10 クラス | 1.12.2 | 1.16.5 | 1.20.1 |
|---|---|---|---|
| `net.minecraftforge.fluids.Fluid` | 維持 | 維持だが `ForgeFlowingFluid` に分化 | **分化 + `FluidType` 分離。`Fluid` は still/flow の液体本体、`FluidType` は振る舞い** |
| `BlockFluidClassic` (`BlockOilFluid`) | `BlockFluidClassic` 維持 (1.12) | `FlowingFluidBlock` / `LiquidBlock` | **同左 (`LiquidBlock`)** |
| `ItemBucket` (`ItemBucketVegiOil`) | `ItemBucket` 維持 | `BucketItem` | **同左 (`BucketItem(Supplier<? extends Fluid>, Properties)`)** |
| `FluidContainerRegistry` | **非推奨** → `FluidUtil` + `CapabilityFluidHandler` | 削除 (`CapabilityFluidHandler` のみ) | **同左 (`ForgeCapabilities.FLUID_HANDLER` + `FluidUtil`)** |
| `FluidStack` | 維持だが `ForgeRegistries.FLUIDS` 参照 | 維持 | **同左 (`FluidStack(Holder<Fluid>, int)`)** |
| `FluidAttributes` | `FluidAttributes.Builder` | `FluidAttributes` | **削除 → `FluidType.Properties` + `FluidType` の override (`getStillTexture`, `getFlowingTexture`, `getColor`, `getLightLevel`)** |

```java
// 1.7.10
Fluid vegitableOil = new Fluid("vegitable_oil").setDensity(800).setViscosity(1500);
FluidRegistry.registerFluid(vegitableOil);
GameRegistry.registerBlock(new BlockOilFluid(vegitableOil, Material.water), "defeatedcrow.blockVegiOil");
FluidContainerRegistry.registerFluidContainer(new FluidStack(vegitableOil, 1000), new ItemStack(bucketVegiOil), new ItemStack(Items.bucket));

// 1.16.5
public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, "defeatedcrow");
public static final RegistryObject<FlowingFluid> VEG_OIL = FLUIDS.register("vegitable_oil", ()-> new ForgeFlowingFluid.Source(VEG_PROPS));
public static final RegistryObject<FlowingFluid> VEG_OIL_FLOWING = FLUIDS.register("vegitable_oil_flowing", ()-> new ForgeFlowingFluid.Flowing(VEG_PROPS));
static final ForgeFlowingFluid.Properties VEG_PROPS = new ForgeFlowingFluid.Properties(VEG_OIL, VEG_OIL_FLOWING,
  FluidAttributes.builder(STILL, FLOWING).overlay(OVERLAY).color(0xFFFFFFFF).density(800).viscosity(1500)).block(BLOCK).bucket(BUCKET);
public static final RegistryObject<LiquidBlock> VEG_BLOCK = BLOCKS.register("block_vegi_oil", ()-> new LiquidBlock(VEG_OIL, Block.Properties.of(Material.WATER).noCollission().strength(100F).noDrops()));
public static final RegistryObject<Item> VEG_BUCKET = ITEMS.register("bucket_vegi_oil", ()-> new BucketItem(VEG_OIL, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(TAB)));

// 1.20.1 (FluidType分離)
public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, "defeatedcrow");
public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, "defeatedcrow");
public static final RegistryObject<FluidType> VEG_TYPE = FLUID_TYPES.register("vegitable_oil", ()-> new FluidType(FluidType.Properties.create()
  .density(800).viscosity(1500).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL).lightLevel(0).canExtinguish(true)){
    @Override public int getColor(FluidStack stack, LevelReader level, BlockPos pos, FluidState state){ return 0xFFFFF0A0; }
    @Override public ResourceLocation getStillTexture(){ return new ResourceLocation("defeatedcrow","block/fluid/vegitable_oil_still"); }
    @Override public ResourceLocation getFlowingTexture(){ return new ResourceLocation("defeatedcrow","block/fluid/vegitable_oil_flow"); }
});
public static final RegistryObject<FlowingFluid> VEG_OIL = FLUIDS.register("vegitable_oil", ()-> new ForgeFlowingFluid.Source(VEG_PROPS));
public static final RegistryObject<FlowingFluid> VEG_OIL_FLOWING = FLUIDS.register("vegitable_oil_flowing", ()-> new ForgeFlowingFluid.Flowing(VEG_PROPS));
static final ForgeFlowingFluid.Properties VEG_PROPS = new ForgeFlowingFluid.Properties(VEG_TYPE, VEG_OIL, VEG_OIL_FLOWING).block(VEG_BLOCK).bucket(VEG_BUCKET).slopeFindDistance(2).levelDecreasePerBlock(1);
// 注: ForgeFlowingFluid.Properties のコンストラクタは (FluidType, Supplier<Fluid> still, Supplier<Fluid> flowing)
public static final RegistryObject<LiquidBlock> VEG_BLOCK = BLOCKS.register("block_vegi_oil",
  ()-> new LiquidBlock(VEG_OIL, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().strength(100F).noLootTable().liquid()));
public static final RegistryObject<Item> VEG_BUCKET = ITEMS.register("bucket_vegi_oil",
  ()-> new BucketItem(VEG_OIL, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
```

### 16種の醸造流体 + 2種の油

`vegitable_oil` / `camellia_oil` + `sake`/`wine`/`whiskey`/`brandy`/`vodka`/`rum`/`shothu`/`beer` × `young/aged` の16種は全て同型で `FluidType` を個別に作成。醸造ダミーブロック2種（`BlockDummyFluid`）は `LiquidBlock` のダミー表示用だったが 1.20.1では `FlowingFluid` の `Source` をそのまま使うか、不要なら削除。

## 検証手順
1. `grep -r "FluidContainerRegistry"` → 0件確認 (Capability化)
2. `grep -r "BlockFluidClassic"` → `LiquidBlock` に置換確認
3. `grep -r "FluidAttributes"` → 0件（1.20.1は`FluidType`に置換）確認
4. `gradlew build` で `ForgeFlowingFluid` の未登録エラー解消確認（FluidType→Fluid→Block→Bucketの順で登録）
5. `FillBucketEvent` の `MovingObjectPosition` → `BlockHitResult` 置換確認

## 関連
- [Fluid 一覧](../fluids.md) / [個別ページ索引](./README.md)
- [Block 一覧](../blocks.md) - 流体ブロック
- [Item 一覧](../items.md) - バケツ/ボトル
- [ItemBlocks でのTagKey](../items/migration-guide.md) - 醸造レシピのFluidStackのIngredient化
- `src/main/java/mods/defeatedcrow/common/MaterialRegister.java:490`
- [ビルド移行ガイド](../build.md) - Mojang mappingsでFluidTypeのMCP名が `m_...` でなく `getStillTexture` 等のMojmap名になる
