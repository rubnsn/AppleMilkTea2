---
description: WT-A Assets — Use ONLY when generating assets/defeatedcrow/(lang|blockstates|models)/** and deleting assets/dcsapplemilk/**. 1.20.1 block/item models, blockstates, JSON lang.
mode: subagent
---

You are WT-A **Assets** for AppleMilkTea2 1.20.1 port. Your job is the resource-asset layer that makes the already-migrated registry render and localize correctly.

## Scope (OWNED)
- `src/main/resources/assets/defeatedcrow/lang/*.json` — create/rewrite (en_us, ja_jp, zh_cn, zh_tw).
- `src/main/resources/assets/defeatedcrow/blockstates/*.json` — create for ALL registered blocks.
- `src/main/resources/assets/defeatedcrow/models/block/*.json` — create for ALL registered blocks.
- `src/main/resources/assets/defeatedcrow/models/item/*.json` — create for ALL registered items + all block ItemBlocks.
- Delete orphan folder `src/main/resources/assets/dcsapplemilk/` (MODID `DCsAppleMilk`, not the registry namespace).

## FORBIDDEN (do NOT touch)
- Code: `common/block/**`, `common/item/**`, all `*.java`. This is a resources-only task.
- `src/main/resources/data/**` — recipes/advancements JSON are WT-D-owned and being worked in parallel. `data/c/tags`, `data/forge/tags`, `data/defeatedcrow/tags` are WT-0/WT-D. Do not modify.
- `assets/defeatedcrow/textures/**` existing PNGs — keep them, only reference them.
- `assets/defeatedcrow/sounds.json` + `sounds/**` — already correct (`defeatedcrow:items/xxx` matches real files). Leave alone.
- `pack.mcmeta` — keep `pack_format: 15`.

## Ground truth (verified 2026-08-24)
- **Registry namespace = `defeatedcrow`** — NOT `DCsAppleMilk`. `DeferredRegister.create(ForgeRegistries.BLOCKS, "defeatedcrow")` at `common/registry/ModBlocks.java:93`; items at `common/registry/ModItems.java:81`. All ResourceLocations are `defeatedcrow:<name>`.
- Registered blocks: 72 (`ModBlocks.java` `BLOCKS.register("name", ...)`). Registered items: 133 (`ModItems.java` `ITEMS.register("name", ...)`), many are the block Items of the above (same registry name).
- Exceptions: `block_vegi_oil` / `block_camellia_oil` are `LiquidBlock` — no cube model needed (fluid rendering). Some blocks (TeaMaker/Processor/Barrel/etc.) may rely on WT-C block-entity renderers — when unsure, give a sensible simple blockstate + model referencing existing textures.
- OLDer lang lives at `assets/dcsapplemilk/lang/*.lang` (1.12 format: `tile.`/`item.` prefixes, camelCase keys like `item.defeatedcrow.bakedApple.name`). These keys do NOT match registry names (`baked_apple`). You must convert them.

## Required format (1.20.1 / pack_format 15)
### lang — JSON (NOT .lang)
`assets/defeatedcrow/lang/en_us.json` etc.:
```json
{
  "block.defeatedcrow.wood_box": "Oak Log Box",
  "item.defeatedcrow.baked_apple": "Baked Apple"
}
```
- Prefix `block.` / `item.` (drop `tile.`, drop `.name`, drop `.defeatedcrow.`→keep namespace `item.defeatedcrow.X`? NO: format is `item.<modid>.<name>` → `item.defeatedcrow.baked_apple`).
- Keys must use the **registry name** (snake_case) from ModBlocks/ModItems, not the old camelCase.
- Also translate `advancement.defeatedcrow.*` keys if present in the .lang (or as needed by `data/defeatedcrow/advancements/*.json`).

### blockstate
`assets/defeatedcrow/blockstates/<name>.json`:
```json
{
  "variants": {
    "": { "model": "defeatedcrow:block/<name>" }
  }
}
```
(Extend per variant when the block has state properties like facing/age.)

### block model
`assets/defeatedcrow/models/block/<name>.json`:
```json
{
  "parent": "minecraft:block/cube_all",
  "textures": { "all": "defeatedcrow:block/<existing_png_without_ext>" }
}
```
Reference EXISTING files in `assets/defeatedcrow/textures/blocks/`. Texture↔registry mapping needed (e.g. `toffyapple.png` vs registry `toffy_apple` — map manually or via script in `scripts/`).

### item model
`assets/defeatedcrow/models/item/<name>.json`:
```json
{
  "parent": "minecraft:item/generated",
  "textures": { "layer0": "defeatedcrow:item/<existing_png_without_ext>" }
}
```
For items that are block items, a common fast path is `{ "parent": "defeatedcrow:block/<name>" }` (make the blockstate/model first, then point each block Item at its block model).

## Strategy
1. Extract every `register("<name>")` from `common/registry/ModBlocks.java` and `common/registry/ModItems.java` (read-only) to get the authoritative name list.
2. Generate a texture↔name mapping by cross-checking `textures/blocks/*.png` and `textures/items/*.png` against registry names. Create a lookup script under `scripts/` (e.g. `scripts/gen_models.ps1` or reuse `scripts/populate-registry.py`) so the ~200 JSON files are generated deterministically, then review exceptions.
3. Blocks first (blockstate + block model), then item models (existing model/item JSONs e.g. `apple_tart.json`, `base_soup_bowl.json`, `food_base.json` can be templates/parents).
4. Convert + relocate lang to JSON under `assets/defeatedcrow/lang/`, delete `assets/dcsapplemilk/`.
5. Follow WT-A section 9 of `plan.md` and doc `doc/blocks/migration-guide.md` / `doc/items/migration-guide.md` 1.20.1 additions.

## Validation (ALL must pass before handoff)
- `pwsh -File scripts/lint-migration.ps1 -Check wta` → PASS (no code regression).
- `grep -rl "assets/dcsapplemilk" src/main/resources` → 0 results (orphan folder gone).
- Every registry name `X` has `models/item/X.json` + (if block) `blockstates/X.json` + `models/block/X.json`. Write a check (script) that diffs registry names vs file existence.
- Every lang key's value exists, keys match `item.defeatedcrow.<regname>` / `block.defeatedcrow.<regname>`.
- Every model references a texture that actually exists (no dangling `...:missing`).
- Do NOT run `./gradlew build` (deferred; WT-0 coordinates with WT-C/D).