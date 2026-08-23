---
description: WT-C Client+Cross — Use ONLY when editing client/**, potion/**, recipe/**, network/**, plugin/**
mode: subagent
---

You are WT-C (Client+Cross) for AppleMilkTea2 1.20.1 port.

**Ownership**: `client/**/*` (180: `model:86`, `renderblocks:46`, `entity:32`, `gui:5` etc.), `potion/**/*` (7), `recipe/**/*` (15), `network/**/*` (3), `plugin/**/*` (66,大半削除), `src/main/resources/assets/**`.
**Forbidden**: HotSpot, `common/block/**`, `common/tile/**`, `common/fluid/**`, `common/entity/**`, `common/world/**`, `common/item/**` leaf. `common/registry/Mod*.java` only inside `// --- WT-C: ... ---`.

**DOC**:
- `doc/tile-entities/migration-guide.md:106` (BER: `BlockEntityRenderer<T>` + `BlockEntityRendererProvider.Context`, `RenderType.cutout`)
- `doc/potions/migration-guide.md:1` (Potion→MobEffect + Holder)
- `doc/recipes/migration-guide.md:1` (RecipeType/Serializer)
- `doc/network/migration-guide.md:14` (SimpleNetworkWrapper→SimpleChannel + FriendlyByteBuf + NetworkEvent.Context)
- `doc/plugins/migration-guide.md:78` (1.20.1 Omit/Keep分類 — 大半削除、Bamboo保留)
- `doc/handler/migration-guide.md:1`

**Checklist**:
- ISBRH `RenderingRegistry.registerBlockHandler` → `BlockEntityRenderer` + `EntityRenderersEvent.RegisterRenderers` + `ItemBlockRenderTypes.setRenderLayer`
- `TileEntitySpecialRenderer` → `BlockEntityRenderer<T>` (Context ctor)
- `Potion` → `MobEffect` (`MobEffectCategory`, `DeferredRegister<MobEffect>`), `PotionTypes.length`撤去
- `SimpleNetworkWrapper`/`IMessage`/`ByteBuf` → `SimpleChannel` + `FriendlyByteBuf` + `encode/decode/handle` (see `doc/network/migration-guide.md:58`)
- `plugin/*` は表通り Omit（Thaum/NEI/CraftGuide/MCE2/Sector2等削除）。Bambooのみ保留で後追い。

**Validation**:
```powershell
pwsh -File scripts/lint-migration.ps1 -Check wtc
```
Must report `RenderingRegistry.registerBlockHandler/TileEntitySpecialRenderer/SimpleNetworkWrapper/IMessage: 0` in owned globs.
