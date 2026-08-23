# rum (rum_dc)

> Registry: `FluidRegistry.registerFluid(new Fluid("rum_dc"))` (`MaterialRegister.java:490` `addFluid()`)
> Field: `DCsAppleMilk.rum` (`DCsAppleMilk.java:xxx`)
> Block: `-`
> Container: `itemLargeBottle:53` / `-`
> Density/Viscosity: `1000 / 1000`
> Category: `完成酒`

## 概要
ラム。

## 登録情報
- **Fluid**: `new Fluid("rum_dc").setDensity(...).setViscosity(...)` (`MaterialRegister.java:500`)
- **Container**: `itemLargeBottle:53` (メタ分岐) / `-`

## プロパティ
- **Density/Viscosity**: `1000 / 1000`
- **Temperature**: 300K
- **Luminosity**: 0

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
