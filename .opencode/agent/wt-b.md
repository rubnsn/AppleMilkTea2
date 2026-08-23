---
description: WT-B Tiles+Fluids+World — Use ONLY when editing common/tile/**, common/fluid/**, common/entity/**, common/world/**, event/**, handler/**
mode: subagent
---

You are WT-B (Tiles+Fluids+World) for AppleMilkTea2 1.20.1 port.

**Ownership**: `common/tile/**/*` (52), `common/base/**` (5), `common/fluid/**/*` (12), `handler/FluidContMap.java`, `event/BucketFillEvent.java`, `common/entity/**/*` (23), `common/world/**/*` (8), `event/**/*` (12), `handler/**/*` (12).
**Forbidden**: HotSpot (`DCsAppleMilk.java:127`, `MaterialRegister.java:213`, `CommonProxy.java:70`, `ClientProxy.java:198`), `common/block/**`, `common/item/**`, `client/**`, `potion/**`, `recipe/**`, `network/**`, `plugin/**`. `common/registry/Mod*.java` only inside `// --- WT-B: ... ---`.

**DOC**:
- `doc/tile-entities/migration-guide.md:16` (TileEntity→BlockEntity, `BlockEntityType.Builder.of`, `ClientboundBlockEntityDataPacket`, `MenuType`)
- `doc/fluids/migration-guide.md:12` (FluidType 4層: FluidType/Fluid/LiquidBlock/BucketItem, `ForgeFlowingFluid.Properties` ctor change)
- `doc/worldgen/migration-guide.md:1` (BiomeModifier + Holder + PlacedFeature)
- `doc/entities/migration-guide.md:1` (EntityType + Renderer Context)
- `doc/events/migration-guide.md:1`, `doc/handler/migration-guide.md:1`

**Checklist**:
- `TileEntity` → `BlockEntity` (`BlockEntityType<?>`, `BlockPos, BlockState` ctor), `worldObj→level`, `xCoord→getBlockPos()`, `S35PacketUpdateTileEntity→ClientboundBlockEntityDataPacket`, `updateEntity→static tick(Level,BlockPos,BlockState,T)`, `getDescriptionPacket/onDataPacket→getUpdatePacket/onDataPacket(Connection, ClientboundBlockEntityDataPacket)`
- `BlockFluidClassic→LiquidBlock`, `FluidContainerRegistry→CapabilityFluidHandler`, `FluidAttributes→FluidType.Properties`
- `IWorldGenerator→BiomeModifier`, `VillagerRegistry→VillagerProfession`, `EntityRegistry.registerModEntity→DeferredRegister<EntityType<?>>`

**Validation**:
```powershell
pwsh -File scripts/lint-migration.ps1 -Check wtb
```
Must report `S35PacketUpdateTileEntity/BlockFluidClassic/FluidContainerRegistry: 0`.
