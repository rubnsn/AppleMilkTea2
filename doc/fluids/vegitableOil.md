# vegitableOil (vegitable_oil)

> Registry: `FluidRegistry.registerFluid(new Fluid("vegitable_oil"))` (`MaterialRegister.java:490` `addFluid()`)
> Field: `DCsAppleMilk.vegitableOil` (`DCsAppleMilk.java:xxx`)
> Block: `blockVegitableOil`
> Container: `bucketVegiOil` / `bottleVegiOil`
> Density/Viscosity: `800 / 1500`
> Category: `油`

## 概要
食用油。Teppan/Processor燃料。ブロック+バケツ(1000mB)+ボトル(200mB)。

## 登録情報
- **Fluid**: `new Fluid("vegitable_oil").setDensity(...).setViscosity(...)` (`MaterialRegister.java:500`)
- **Block**: `new BlockOilFluid(fluid, Material.water)` -> `GameRegistry.registerBlock`
- **Bucket**: `FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid,1000), new ItemStack(bucketVegiOil), new ItemStack(Items.bucket))`
- **Bottle**: `registerFluidContainer(new FluidStack(fluid,200), new ItemStack(bottleVegiOil), new ItemStack(emptyBottle))`

## プロパティ
- **Density/Viscosity**: `800 / 1500`
- **Temperature**: 300K
- **Luminosity**: 0
- **Block**: `BlockFluidClassic` 継承、Still/Flow IIcon

## 移行 (1.12.2+)
- [ ] `FluidRegistry.registerFluid` -> `DeferredRegister<Fluid>` + `ForgeFlowingFluid.Properties`
- [ ] `Fluid.setDensity`等 -> `FluidAttributes.Builder`
- [ ] `BlockFluidClassic` -> `FlowingFluidBlock` / `LiquidBlock`
- [ ] `FluidContainerRegistry.registerFluidContainer` -> `CapabilityFluidHandler` / `BucketItem`
- [ ] `IIcon` -> `ResourceLocation` still/flow

## 関連ドキュメント
- [Fluid 一覧](../fluids.md)
- [カテゴリ別一覧](./README.md)
- [移行ガイド](./migration-guide.md)
