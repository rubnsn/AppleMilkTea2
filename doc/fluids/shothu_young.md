# shothu_young (shothu_young)

> Registry: `FluidRegistry.registerFluid(new Fluid("shothu_young"))` (`MaterialRegister.java:490` `addFluid()`)
> Field: `DCsAppleMilk.shothu_young` (`DCsAppleMilk.java:xxx`)
> Block: `-`
> Container: `bucketYoungAlcohol:0` / `-`
> Density/Viscosity: `1000 / 1000`
> Category: `若い酒`

## 概要
焼酎若。樽醸造。bucketYoungAlcoholメタ0。

## 登録情報
- **Fluid**: `new Fluid("shothu_young").setDensity(...).setViscosity(...)` (`MaterialRegister.java:500`)
- **Container**: `bucketYoungAlcohol:0` (メタ分岐) / `-`
- **Barrel**: `TileBrewingBarrel` 内で `BrewingRecipe` により完成品 `shothu_dc` へ変換

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
