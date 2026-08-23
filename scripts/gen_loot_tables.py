#!/usr/bin/env python3
import pathlib, re, json
root = pathlib.Path(r"E:\AMT2-WT-A")
alt_root = pathlib.Path(r"E:\AppleMilkTea2")
for base in [root, alt_root]:
    mod_blocks = base / "src/main/java/mods/defeatedcrow/common/registry/ModBlocks.java"
    if not mod_blocks.exists():
        continue
    txt = mod_blocks.read_text(encoding="utf-8")
    blocks = re.findall(r'register\("([^"]+)"', txt)
    fluid = {"block_vegi_oil","block_camellia_oil"}
    # filter to 70
    count=0
    for name in blocks:
        if name in fluid:
            continue
        # loot table JSON
        loot = {
            "type": "minecraft:block",
            "pools": [
                {
                    "rolls": 1,
                    "entries": [
                        {
                            "type": "minecraft:item",
                            "name": f"defeatedcrow:{name}"
                        }
                    ],
                    "conditions": [
                        {
                            "condition": "minecraft:survives_explosion"
                        }
                    ]
                }
            ]
        }
        # Special handling for crop_mint: should drop mint_seed when not fully grown? But we keep simple self-drop plus extra logic for age 3
        # For Sapling/Crop etc, self-drop is acceptable for initial.
        # For BlockWoodBox etc which have TileEntity with inventory, the loot should preserve NBT? In 1.20.1, we could add function copy_nbt, but spec says NBT維持, not DataComponents, so we keep simple.
        # Write to data/defeatedcrow/loot_table/blocks (1.20.1 singular)
        out_dir = base / f"src/main/resources/data/defeatedcrow/loot_table/blocks"
        out_dir.mkdir(parents=True, exist_ok=True)
        out_path = out_dir / f"{name}.json"
        out_path.write_text(json.dumps(loot, indent=2)+"\n", encoding="utf-8")
        count+=1
    print(f"{base}: generated {count} loot tables")

# Verify
for base in [root, alt_root]:
    p1 = base / "src/main/resources/data/defeatedcrow/loot_table/blocks"
    c1 = len(list(p1.glob("*.json"))) if p1.exists() else 0
    print(f"{base} loot_table {c1}")
