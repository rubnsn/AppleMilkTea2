# beer_young (beer_young)

> Registry: `FluidRegistry.registerFluid(new Fluid("beer_young"))` (`MaterialRegister.java:490` `addFluid()`)
> Field: `DCsAppleMilk.beer_young` (`DCsAppleMilk.java:xxx`)
> Block: `-`
> Container: `moromi:1` / `-`
> Density/Viscosity: `1000 / 1000`
> Category: `若い酒`

## 概要
ビールもろみ。

## 登録情報
- **Fluid**: `new Fluid("beer_young").setDensity(...).setViscosity(...)` (`MaterialRegister.java:500`)
- **Container**: `moromi:1` (メタ分岐) / `-`
- **Barrel**: `TileBrewingBarrel` 内で `BrewingRecipe` により完成品 `beer_dc` へ変換

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
