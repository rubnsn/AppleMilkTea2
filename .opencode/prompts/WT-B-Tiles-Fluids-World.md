# WT-B Tiles+Fluids+World — 人間用opencodeプロンプト（コピペで起動）

> worktree: `../AMT2-WT-B` (branch `feature/tiles-fluids-world`, base `dev`)  
> 所有: `common/tile/**` 52 + `common/fluid/**` 12 + `common/entity/**` 23 + `common/world/**` 8 + `event/**` + `handler/**`  
> 禁止: HotSpot 4ファイル、common/block/**, common/item/**, client/**

## 起動コマンド

```powershell
git worktree add ../AMT2-WT-B -b feature/tiles-fluids-world dev
cd ../AMT2-WT-B
opencode --agent wt-b --port 4097
```

## コピペプロンプト

```
あなたは WT-B (Tiles+Fluids+World) 担当。所有: common/tile/**, common/fluid/**, common/entity/**, common/world/**, event/**, handler/** のみ。
禁止: common/DCsAppleMilk.java, common/MaterialRegister.java, common/CommonProxy.java, client/ClientProxy.java は読取専用。common/registry/Mod*.java は // --- WT-B: ... --- 内のみ追記。

DOC:
- doc/tile-entities/migration-guide.md (BlockEntity + Builder.of + ClientboundBlockEntityDataPacket + getTicker)
- doc/fluids/migration-guide.md (FluidType 4層, ForgeFlowingFluid.Properties ctor: FluidType, Source, Flowing)
- doc/worldgen/migration-guide.md (BiomeModifier/Holder/PlacedFeature)
- doc/entities/migration-guide.md

タスク:
1. common/tile/appliance/TileProcessor.java から開始。TileEntity→BlockEntity へ。writeToNBT→saveAdditional, readFromNBT→load, getDescriptionPacket→getUpdatePacket(ClientboundBlockEntityDataPacket.create), xCoord/worldObj→getBlockPos()/level, updateEntity→static tick(Level,BlockPos,BlockState,T)。
2. CommonProxy.registerTileEntity の代替として common/registry/ModBlockEntities.java の // --- WT-B: APPLIANCE --- に BlockEntityType.Builder.of(...) を追記。MenuTypeは ModMenuTypes.java へ。
3. common/fluid/BlockOilFluid.java → LiquidBlock, BlockDummyFluidは FlowingFluidで代替または削除。ModFluidTypes/ModFluids に8+16 Fluidを登録。
4. common/entity/** を EntityType DeferredRegisterへ、common/world/** を BiomeModifierへ、event/handlerの World,int x,y,z を BlockPosへ。
5. 終了条件: grepで S35PacketUpdateTileEntity/BlockFluidClassic/FluidContainerRegistry/GameRegistry.registerTileEntity が0件。

pwsh -File scripts/lint-migration.ps1 -Check wtb
```

## 検証

```powershell
pwsh -File scripts/lint-migration.ps1 -Check wtb
# 期待: wtb: PASS
```
