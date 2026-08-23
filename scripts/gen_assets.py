#!/usr/bin/env python3
import pathlib, re, json, os, sys

root = pathlib.Path(r"E:\AppleMilkTea2")
mod_blocks = root / "src/main/java/mods/defeatedcrow/common/registry/ModBlocks.java"
mod_items = root / "src/main/java/mods/defeatedcrow/common/registry/ModItems.java"
resources = root / "src/main/resources"
assets_defeated = resources / "assets/defeatedcrow"
assets_old = resources / "assets/dcsapplemilk"

# 1. Extract registry names
def extract_register(path, prefix):
    text = path.read_text(encoding="utf-8")
    # pattern BLOCKS.register("name"  or ITEMS.register("name"
    names = re.findall(r'register\("([^"]+)"', text)
    return names

block_names = extract_register(mod_blocks, "BLOCKS")
item_names = extract_register(mod_items, "ITEMS")
print(f"Blocks: {len(block_names)} {block_names}")
print(f"Items: {len(item_names)} {item_names}")

# Deduplicate but preserve order
block_set = set(block_names)
item_set = set(item_names)

# 2. List block textures (top-level only, ignore subdirs x32 etc)
block_tex_dir = assets_defeated / "textures/blocks"
block_textures = []  # list of (stem, full_rel_path_without_ext, normalized)
if block_tex_dir.exists():
    for p in block_tex_dir.iterdir():
        if p.is_file() and p.suffix.lower()==".png":
            stem = p.stem  # keep case
            # normalized for matching: lower + remove underscores
            norm = stem.replace("_","").lower()
            block_textures.append((stem, stem, norm))  # (stem, rel_path, norm)
    # also handle? But spec says reference existing files in blocks/
    # Sorting for deterministic
    block_textures.sort(key=lambda x: x[0].lower())
print(f"Block textures top-level: {len(block_textures)}")
# debug list
# for t in block_textures[:10]:
#     print(t)

# Item textures recursive
item_tex_dir = assets_defeated / "textures/items"
item_textures = []  # (basename_stem, rel_path_without_ext, norm_basename, norm_full)
if item_tex_dir.exists():
    for p in item_tex_dir.rglob("*.png"):
        # relative to item_tex_dir, without extension
        rel = p.relative_to(item_tex_dir).with_suffix("")  # e.g. tools/chalcedonyknife or appletart
        # use posix style for mc reference (forward slash)
        rel_posix = rel.as_posix()  # keep case as filesystem (preserves case)
        basename = p.stem
        norm_base = basename.replace("_","").lower()
        norm_full = rel_posix.replace("_","").replace("/","").lower()  # flattened
        item_textures.append((basename, rel_posix, norm_base, norm_full))
    item_textures.sort(key=lambda x: x[1].lower())
print(f"Item textures recursive: {len(item_textures)}")
# for t in item_textures[:20]:
#     print(t)

# Build lookup for block textures by norm
block_norm_map = {}
for stem, rel, norm in block_textures:
    # keep first occurrence if duplicate norm (unlikely)
    if norm not in block_norm_map:
        block_norm_map[norm] = rel
    # also map lower stem exact?
# Also map lower without removal? but primary is norm

# For items, map norm_base -> rel_posix
item_norm_base_map = {}
for basename, rel, norm_base, norm_full in item_textures:
    if norm_base not in item_norm_base_map:
        item_norm_base_map[norm_base] = rel
    # also if multiple with same base but different folders, first wins
# Also map full flattened for fallback?

# Manual overrides for blocks where no good match
block_manual = {
    "tea_maker_next": "whitepanel",
    "tea_maker_black": "whitepanel",
    "empty_pan_g": "porcelain",
    "filled_soup_pan": "porcelain",
    "processor": "whitepanel",
    "adv_processor": "whitepanel",
    "evaporator": "whitepanel",
    "incense_base": "woodpanel",
    "vegi_bag": "bag_wheat_T",
    "cardboard": "cardboard_T",
    "charcoal_box": "container_charcoal_T",
    "gunpowder_container": "container_gunpowder_T",
    "egg_basket": "basket_T0",
    "mushroom_box": "woodpanel",
    "melon_bomb": "melonbox",
    "wipe_box": "wipes_T",
    "wipe_box2": "wipes_T",
    "mob_block": "mobbox_bone",
    "silky_melon": "melonbox_silky",
    "flower_pot": "flowerpot_red",
    "flower_vase": "blueglass",
    "hedge": "tealeaf",
    "container_water_bottle": "containeritem_bottleW",
    "container_saddle": "containeritem_saddle",
    "filled_cup": "cup_empty",
    "filled_cup2": "cup_empty",
    "ice_cream_block": "whitepanel",
    "cocktail": "contents_cocktailbase",
    "cocktail2": "contents_cocktailbase",
    "cocktail_sp": "contents_cocktailbase",
    "alcohol_cup": "bottle_empty",
    "bowl_block": "whitepanel",
    "bowl_jp": "whitepanel",
    "food_plate": "whitepanel",
    "choco_block": "chocogift",
    "empty_bottle": "bottle_empty",
    "large_bottle": "bottle_empty",
    "cordial": "cordial_drink",
    "barrel": "barrel",
    "sapling_tea": "sapling_tea",
    "tea_tree": "tealeaf",
    "cassis_tree": "cassisleaf_0",
    "clam_sand": "whitepanel",
    "crop_mint": "crop_mint_stage_3",
    "sapling_yuzu": "sapling_yuzu",
    "log_yuzu": "log_yuzu_side",
    "leaves_yuzu": "leaves_yuzu_0",
    "bowl_rack": "basket_T0",
    "basket": "basket_T0",
    "chopsticks_box": "basket_T0",
    "wood_panel": "woodpanel",
    "yuzu_fence": "yuzufence",
    "flint_block": "chalcedony",
    "chalcedony": "chalcedony",
    "chalcedony_lamp": "chalcedony",
    "chalcedony_lamp_op": "chalcedony_opaq",
    "chalcedony_panel": "chalcedony",
    "rotary_dial": "rotarydial_block",
    "crow_doll": "crowdoll",
    "bat_box": "charger_F",
    "red_gel": "redgel",
    "yuzu_light": "lightgel",
    "yuzu_bat": "container_yuzubat_S",
    "gel_bat": "redgel",
    "handle_engine": "whitepanel",
    "wood_box": "WoodBox",
    "apple_box": "AppleBox",
    "empty_cup": "cup_empty",
    "ice_maker": "icemaker_body",
    "teppan_ii": "teppann",
    # fluid blocks excluded but if we generate anyway, fallback
    "block_vegi_oil": None,
    "block_camellia_oil": None,
}

# Also ensure block manual textures exist; validate
existing_block_stems = set(stem for stem,_,_ in block_textures)
# Check manual values exist (case-sensitive check)
for k,v in block_manual.items():
    if v is None:
        continue
    # need to check if v exists as file (case insensitive? but we keep case)
    # Check existence via filesystem case-insensitive on Windows, but we can check lower map
    found = any(stem.lower()==v.lower() for stem in existing_block_stems)
    if not found:
        print(f"WARN block manual texture missing: {k} -> {v}")

# For items, manual overrides where exact basename not found
item_manual = {
    "ex_items": "dummy",
    "essential_oil": "essence_apple",
    "strange_slag": "strange_slag",
    "fossil_scale": "purple_scale",
    "slot_panel": "dummy",
    "jaw_plate": "tools/jawplate_alloy",
    "dust_wood": "dust_wood",
    "chalcedony_knife": "tools/chalcedonyknife",
    "chalcedony_hammer": "tools/chalcedonyhammer",
    "chalcedony_shears": "tools/chalcedonyshears",
    "monocle": "tools/monocle",
    "onix_sword": "tools/onixsword",
    "fire_starter": "tools/firestarter",
    "grater": "grater",
    "yuzu_gatling": "dummy",
    "fossil_cannon": "dummy",
    "battery": "battery_yuzu",
    "container_door_w": "containeritem_doorW",
    "container_door_i": "containeritem_doorI",
    "dummy_tooltip": "dummy",
    "dummy_teppan": "teppan_dummy",
    "choco_fruits": "choco_plate",
    "grated_apple": "gratedapple",
    "minced_foods": "mincedfoods_mushroom",
    "food_tea": "leaf_tea",
    "condensed_milk": "condensedmilk",
    "clam": "clam",
    "yeast": "yeast",
    "moromi": "moromi_rice",
    "base_soup_bowl": "foods/basesoupitem_WATER",
    "wall_mug": "wallmug",
    "leaf_tea": "leaf_tea",
    "leaf_mint": "leaf_mint",
    "leaf_cassis": "cassis",
    "leaf_yuzu": "yuzu",
    "leaf_camellia": "camelliafruits",
    "mint_seed": "seed_mint",
    "ink_stick": "inkstick",
    "ore_dust": "oredust_iron",
    "wood_dust": "dust_wood",
    "empty_wall_mug": "wallmug_dummy",
    "carbon_stick": "stick_carbon",
    "icy_crystal": "icycrystal",
    "baked_apple": "bakedapple",
    "apple_tart": "appletart",
    "toffy_apple": "toffyapple",
    "icy_toffy_apple": "icytoffyapple",
    "apple_sandwich": "sandwich_apple",
    "chopsticks": "chopsticks",
    # for leaf splits fallback if not found exact
    # large_bottle, cordial, empty_bottle are block items but also have special handling below? Actually they are block-related but also items with same name as blocks, but we treat them as block items.
    # However large_bottle etc are block items that have special item class but still block item parent. The spec says for items that are block items, parent is block model. So we will treat them as block items, not generated.
    # But we have also essential_oil etc already
}

# Need to handle block items: set of block names that have corresponding item names
# For item model generation, if item_name in block_set, then it's a block item -> parent block
block_item_names = set(block_names) & set(item_names)
print(f"Block-item overlapping names: {len(block_item_names)} {sorted(block_item_names)[:10]}...")

# However our item_names includes both block items and non-block items, but large_bottle, cordial, empty_bottle are in both, so they will be treated as block items
# That's intended per spec: "For items that are block items, a common fast path is parent defeatedcrow:block/<name>"
# So large_bottle, cordial, empty_bottle will be block parent even though they have special Item class, still okay? But texture for those blocks exists (bottle_empty etc) but block model for empty_bottle etc uses bottle_empty texture, item will point to block model.

# Also some items like leaf_tea etc not block items, will be generated.

# Create directories
(assets_defeated / "blockstates").mkdir(parents=True, exist_ok=True)
(assets_defeated / "models/block").mkdir(parents=True, exist_ok=True)
(assets_defeated / "models/item").mkdir(parents=True, exist_ok=True)
(assets_defeated / "lang").mkdir(parents=True, exist_ok=True)

# 3. Generate blockstates + block models
fluid_blocks = {"block_vegi_oil", "block_camellia_oil"}
generated_blocks = []
for name in block_names:
    if name in fluid_blocks:
        print(f"Skipping fluid block {name}")
        continue
    # blockstate
    bs_path = assets_defeated / "blockstates" / f"{name}.json"
    bs_content = {
        "variants": {
            "": {"model": f"defeatedcrow:block/{name}"}
        }
    }
    bs_path.write_text(json.dumps(bs_content, indent=2) + "\n", encoding="utf-8")
    # block model
    # find texture
    norm = name.replace("_","").lower()
    texture = block_norm_map.get(norm)
    # fallback to manual
    if texture is None:
        manual = block_manual.get(name)
        if manual:
            texture = manual
        else:
            # try substring search: find any block texture where norm is substring of texture norm or vice versa
            candidates = []
            for stem, rel, tnorm in block_textures:
                if norm in tnorm or tnorm in norm:
                    candidates.append(rel)
            if candidates:
                # pick shortest candidate
                candidates.sort(key=len)
                texture = candidates[0]
            else:
                texture = "whitepanel"  # fallback generic exists
    # validate texture exists (case-insensitive)
    # For consistency, ensure texture string matches actual file stem case? We have texture as rel which preserves case.
    # If manual gave lower like whitepanel, that file exists as whitepanel.png (lower), so okay.
    # If we used block_norm_map, we got actual stem case (like WoodBox)
    # Need to ensure texture reference keeps case as file
    # For block model, reference is defeatedcrow:block/<texture>
    bm_path = assets_defeated / "models/block" / f"{name}.json"
    bm_content = {
        "parent": "minecraft:block/cube_all",
        "textures": {
            "all": f"defeatedcrow:block/{texture}"
        }
    }
    bm_path.write_text(json.dumps(bm_content, indent=2) + "\n", encoding="utf-8")
    generated_blocks.append(name)

print(f"Generated {len(generated_blocks)} blockstates + models")

# 4. Generate item models
# Need full list of item textures for existence check: set of rel_posix lower?
existing_item_rel_lower = set(rel.lower() for _, rel, _, _ in item_textures)
# Also keep mapping rel -> exists case-sensitive check via filesystem but we assume lower check is enough for validation

generated_items = []
for name in item_names:
    im_path = assets_defeated / "models/item" / f"{name}.json"
    if name in block_item_names:
        # block item -> parent block
        content = {
            "parent": f"defeatedcrow:block/{name}"
        }
        im_path.write_text(json.dumps(content, indent=2) + "\n", encoding="utf-8")
        generated_items.append((name, "block"))
    else:
        # non-block item -> generated with texture
        norm = name.replace("_","").lower()
        texture_rel = item_norm_base_map.get(norm)
        if texture_rel is None:
            # try manual override
            manual = item_manual.get(name)
            if manual:
                texture_rel = manual
            else:
                # try substring match among item textures
                candidates = []
                for basename, rel, norm_base, norm_full in item_textures:
                    if norm in norm_base or norm_base in norm:
                        candidates.append(rel)
                if candidates:
                    # pick shortest, prefer exact basename length closest to registry length
                    candidates.sort(key=lambda x: abs(len(x)-len(name)))
                    texture_rel = candidates[0]
                else:
                    # fallback to dummy if exists else first texture
                    if "dummy" in existing_item_rel_lower:
                        texture_rel = "dummy"
                    else:
                        texture_rel = item_textures[0][1] if item_textures else "dummy"
        # Validate existence (case-insensitive)
        # Need to find actual rel case that matches lower
        # If manual override like "tools/jawplate_alloy" lower is "tools/jawplate_alloy", check if exists lower. If exists, keep as is.
        # If not exists lower, try to find closest.
        # For now ensure file exists via lower check; if not, fallback to dummy
        if texture_rel.lower() not in existing_item_rel_lower:
            # try to find case-correct version from map where lower matches
            # Search for any item texture where rel.lower() == texture_rel.lower()
            found = None
            for _, rel, _, _ in item_textures:
                if rel.lower() == texture_rel.lower():
                    found = rel
                    break
            if found:
                texture_rel = found
            else:
                # fallback to dummy
                # Find dummy texture
                for _, rel, _, _ in item_textures:
                    if rel.lower() == "dummy":
                        texture_rel = rel
                        break
                else:
                    texture_rel = "dummy"
        content = {
            "parent": "minecraft:item/generated",
            "textures": {
                "layer0": f"defeatedcrow:item/{texture_rel}"
            }
        }
        im_path.write_text(json.dumps(content, indent=2) + "\n", encoding="utf-8")
        generated_items.append((name, texture_rel))

print(f"Generated {len(generated_items)} item models")
# Count block vs generated
print(f" - block parent: {sum(1 for _,t in generated_items if t=='block')}")
print(f" - generated: {sum(1 for _,t in generated_items if t!='block')}")

# 5. Lang handling
# Parse old lang files
def parse_lang(path):
    d={}
    try:
        text = path.read_text(encoding="utf-8")
    except UnicodeDecodeError:
        text = path.read_text(encoding="cp932")
    for line in text.splitlines():
        line=line.strip()
        if not line or line.startswith("#"):
            continue
        if "=" not in line:
            continue
        k,v=line.split("=",1)
        k=k.strip()
        v=v.strip()
        d[k]=v
    return d

old_langs = {}
for lang_code, fname in [("en_us","en_US.lang"),("ja_jp","ja_JP.lang"),("zh_cn","zh_CN.lang"),("zh_tw","zh_TW.lang")]:
    p = assets_old / "lang" / fname
    if p.exists():
        old_langs[lang_code]=parse_lang(p)
        print(f"Parsed {lang_code}: {len(old_langs[lang_code])} entries")
    else:
        print(f"Missing {p}")
        old_langs[lang_code]={}

# Helper to find best translation for registry name in a given old dict
import re

def normalize(s):
    return s.replace("_","").replace(" ","").lower()

# Precompute old entries normalized mapping: separate tile vs item
old_tile_entries = {}
old_item_entries = {}
old_all_entries = {}
for lang_code, d in old_langs.items():
    tile_entries=[]
    item_entries=[]
    all_entries=[]
    for k,v in d.items():
        if "defeatedcrow." not in k:
            continue
        after=k.split("defeatedcrow.")[1]
        if after.endswith(".name"):
            after=after[:-5]
        middle=after.split(".")[0]
        if not middle:
            continue
        norm=normalize(middle)
        entry=(norm, middle, k, v)
        all_entries.append(entry)
        if k.startswith("tile."):
            tile_entries.append(entry)
        elif k.startswith("item."):
            item_entries.append(entry)
        else:
            # fluid, entity etc not used but keep in all
            pass
    old_tile_entries[lang_code]=tile_entries
    old_item_entries[lang_code]=item_entries
    old_all_entries[lang_code]=all_entries
# For backward compat, keep old_norm_entries as all
old_norm_entries = old_all_entries

# Manual overrides for known split mappings for en etc
# For leaf items, we need explicit mapping from old leafTea variants to new leaf items
leaf_manual_en = {
    "leaf_tea": "Raw Tea Leaves",  # from leafTea_0
    "leaf_mint": "Mint Leaves",
    "leaf_cassis": "Cassis Fruits",
    "leaf_yuzu": "Yuzu",
    "leaf_camellia": "Camellia Fruits",
}
leaf_manual_ja = {
    "leaf_tea": "生の茶葉",
    "leaf_mint": "ミントの葉",
    "leaf_cassis": "カシスの実",
    "leaf_yuzu": "柚子の実",
    "leaf_camellia": "椿の実",
}
# Additional manual lang overrides for blocks/items where fuzzy fails or picks generic
manual_lang_en = {
    "empty_pan_g": "Clay Pan",
    "filled_soup_pan": "Clay Pan",
    "bowl_jp": "Japanese Bowl",
    "bowl_block": "Bowl",
    "alcohol_cup": "Alcohol Cup",
    "cocktail_sp": "Special Cocktail",
    "gunpowder_container": "Gunpowder Container",
    "charcoal_box": "Charcoal Box",
    "wood_box": "Wood Box",  # generic fallback if needed
    "chalcedony_hammer": "Chalcedony Hammer",
    "chalcedony_knife": "Chalcedony Knife",
    "chalcedony_shears": "Pruning Shears",
    "onix_sword": "Onyx Sword",
    "fire_starter": "Fire Starter",
    "yuzu_gatling": "Yuzu Gatling",
    "fossil_cannon": "Fossil Cannon",
    "container_door_w": "Wooden Door Bundle",
    "container_door_i": "Iron Door Bundle",
    "dummy_tooltip": "Tooltip Dummy",
    "dummy_teppan": "Teppan Dummy",
    "yuzu_light": "Yuzu Light",
    "yuzu_bat": "Yuzu Battery",
    "gel_bat": "Gel Battery",
    "handle_engine": "Handle Engine",
    "teppan_ii": "Cooking Iron Plate",
    "processor": "Food Processor",
    "adv_processor": "Hyper Jaw Crusher",
    "evaporator": "Evaporator",
    "bat_box": "Bat Box",
    "red_gel": "Red Gel",
    "chalcedony_panel": "Chalcedony Panel",
    "chalcedony_lamp_op": "Chalcedony Lamp",
    "chalcedony_lamp": "Chalcedony Lamp",
    "chopsticks_box": "Chopsticks Box",
    "wood_panel": "Wood Panel",
    "flint_block": "Flint Block",
    "hedge": "Hedge",
    "cargo": "Cargo",
}
manual_lang_ja = {
    "empty_pan_g": "土鍋",
    "filled_soup_pan": "土鍋",
    "bowl_jp": "お椀",
    "bowl_block": "ボウル",
    "alcohol_cup": "酒カップ",
    "cocktail_sp": "スペシャルカクテル",
    "gunpowder_container": "火薬コンテナ",
    "charcoal_box": "木炭箱",
    "wood_box": "木箱",
    "chalcedony_hammer": "玉髄のハンマー",
    "chalcedony_knife": "玉髄のナイフ",
    "chalcedony_shears": "玉髄の剪定鋏",
    "onix_sword": "オニキスソード",
    "fire_starter": "着火具",
    "yuzu_gatling": "柚子ガトリング",
    "fossil_cannon": "化石砲",
    "container_door_w": "木のドア束",
    "container_door_i": "鉄のドア束",
    "dummy_tooltip": "ダミー",
    "dummy_teppan": "鉄板ダミー",
    "yuzu_light": "柚子ライト",
    "yuzu_bat": "柚子電池",
    "gel_bat": "ゲル電池",
    "handle_engine": "ハンドルエンジン",
    "teppan_ii": "調理用鉄板",
    "processor": "フードプロセッサー",
    "adv_processor": "ハイパージョークラッシャー",
    "evaporator": "蒸留器",
    "bat_box": "バッテリーボックス",
    "red_gel": "赤ジェル",
    "chalcedony_panel": "玉髄パネル",
    "chalcedony_lamp_op": "玉髄ランプ",
    "chalcedony_lamp": "玉髄ランプ",
    "chopsticks_box": "箸箱",
    "wood_panel": "木質パネル",
    "flint_block": "フリントブロック",
    "hedge": "生垣",
}
# zh manual could reuse en title case for now
# For other langs, we could fallback to en but we have old files so we can try generic matching for zh as well, but for leaf we will use manual if not found.

def title_case(s):
    return " ".join(word.capitalize() for word in s.split("_"))

def find_best_value(registry, lang_code, is_block=False):
    # registry like baked_apple, wood_box
    norm_reg = normalize(registry)
    # manual leaf handling
    if registry in leaf_manual_en and lang_code=="en_us":
        return leaf_manual_en[registry]
    if registry in leaf_manual_ja and lang_code=="ja_jp":
        return leaf_manual_ja[registry]
    # Choose entry lists by type: blocks prefer tile, pure items prefer item
    # For overlapping block-items, caller will pass is_block appropriately
    if is_block:
        primary_entries = old_tile_entries.get(lang_code, [])
        secondary_entries = old_item_entries.get(lang_code, [])
    else:
        # for pure items, prioritize item entries; for block-items, caller will set is_block True, so here is pure item case
        # Determine if registry is a block-item (exists in both sets) -> prioritize tile
        if registry in block_item_names:
            primary_entries = old_tile_entries.get(lang_code, [])
            secondary_entries = old_item_entries.get(lang_code, [])
        else:
            primary_entries = old_item_entries.get(lang_code, [])
            secondary_entries = old_tile_entries.get(lang_code, [])
    # exact match in primary
    for norm_old, middle, k, v in primary_entries:
        if norm_old == norm_reg:
            return v
    # exact in secondary
    for norm_old, middle, k, v in secondary_entries:
        if norm_old == norm_reg:
            return v
    # substring primary
    for norm_old, middle, k, v in primary_entries:
        if norm_reg in norm_old:
            return v
    # substring secondary
    for norm_old, middle, k, v in secondary_entries:
        if norm_reg in norm_old:
            return v
    # manual fallback
    if lang_code=="en_us" and registry in manual_lang_en:
        return manual_lang_en[registry]
    if lang_code=="ja_jp" and registry in manual_lang_ja:
        return manual_lang_ja[registry]
    if lang_code in ("zh_cn","zh_tw") and registry in leaf_manual_en:
        return leaf_manual_en[registry]
    return title_case(registry)

# Also need to handle wooden box variants: ensure wood_box returns Oak Log Box (first variant) not too generic. Our substring logic will match WoodBox_oak -> norm woodboxoak contains woodbox, so best will be woodboxoak with diff len 3 (oak). Among all wood variants, the shortest extra is oak (3) vs birch etc, but all similar. The first encountered with minimal diff will win, which is oak (good).
# Similarly for mushroomBox, first red vs brown, either is fine.

# Generate lang JSONs
# Need to collect all keys to generate: block prefix for block_names, item prefix for item_names
# Also advancement keys for existing advancements

# Read existing advancements to know ids
adv_dir = resources / "data/defeatedcrow/advancements"
adv_ids=[]
if adv_dir.exists():
    for p in adv_dir.glob("*.json"):
        if p.name.startswith("."):
            continue
        adv_ids.append(p.stem)  # e.g., get_tea_leaves, craft_tea_maker
print(f"Adv ids: {adv_ids}")

# Creative tabs ids from ModCreativeTabs?
# Could hardcode as per spec: applemilk, applemilk_material, applemilk_food, applemilk_container, applemilk_magic
tab_ids = ["applemilk","applemilk_material","applemilk_food","applemilk_container","applemilk_magic"]

for lang_code in ["en_us","ja_jp","zh_cn","zh_tw"]:
    out={}
    old = old_langs[lang_code]
    # blocks
    for name in block_names:
        if name in fluid_blocks:
            continue
        key = f"block.defeatedcrow.{name}"
        val = find_best_value(name, lang_code, is_block=True)
        out[key]=val
    # items (including block items)
    for name in item_names:
        key = f"item.defeatedcrow.{name}"
        # Determine if this item is a block-item (shares registry with block)
        is_block_item = name in block_item_names
        # For block-items, treat as block for lang lookup (tile), else item
        val = find_best_value(name, lang_code, is_block=is_block_item)
        out[key]=val

    # Fluid lang? Old has fluid.* keys. New fluids use item? But we can keep fluid translation as block.fluid? Not needed.

    # Advancements: map from old achievement
    # Old achievement keys: achievement.dc.getTeaLeaves and .desc
    # New: advancement.defeatedcrow.<adv_id>.title / .description
    # adv_id examples: get_tea_leaves -> old getTeaLeaves (camelCase)
    # Map by normalizing: adv_id normalized gettealeaves matches old achievement getTeaLeaves normalized gettealeaves
    for adv_id in adv_ids:
        norm_adv = normalize(adv_id)
        # Find old achievement title and desc
        # Old keys are like achievement.dc.getTeaLeaves and achievement.dc.getTeaLeaves.desc
        # Build dict for achievement lookup per lang
        # We'll search old dict for keys containing adv normalized
        title_val=None
        desc_val=None
        for k,v in old.items():
            if k.startswith("achievement.dc."):
                # extract achievement id part: e.g., achievement.dc.getTeaLeaves -> getTeaLeaves ; achievement.dc.getTeaLeaves.desc -> getTeaLeaves
                # Remove prefix achievement.dc.
                rest=k[len("achievement.dc."):]
                is_desc=False
                if rest.endswith(".desc"):
                    rest=rest[:-5]
                    is_desc=True
                norm_rest=normalize(rest)
                if norm_rest==norm_adv or norm_adv in norm_rest or norm_rest in norm_adv:
                    if is_desc:
                        # choose best match by minimal len diff
                        if desc_val is None or abs(len(norm_rest)-len(norm_adv)) < abs(len(normalize(desc_val[0]))-len(norm_adv)) if isinstance(desc_val, tuple) else True:
                            desc_val=(rest,v)
                    else:
                        if title_val is None:
                            title_val=(rest,v)
                        else:
                            # prefer exact match, keep first
                            pass
        # If not found via substring, fallback to title case from adv_id
        if title_val:
            out[f"advancement.defeatedcrow.{adv_id}.title"]=title_val[1]
        else:
            out[f"advancement.defeatedcrow.{adv_id}.title"]=title_case(adv_id)
        if desc_val:
            out[f"advancement.defeatedcrow.{adv_id}.description"]=desc_val[1]
        else:
            # try old desc pattern else generic
            out[f"advancement.defeatedcrow.{adv_id}.description"]=title_case(adv_id) + " description"
        # Also handle parent advancements that may be referenced but not existing files? Like make_tea_leaves parent in craft_tea_maker.json but file not exists? We skip.

    # ItemGroup (creative tabs)
    for tab in tab_ids:
        key=f"itemGroup.defeatedcrow.{tab}"
        # Try to find old translation? There is no old itemGroup, so fallback
        # For en, generate Title Case with spaces
        if lang_code=="en_us":
            val=title_case(tab).replace("Applemilk","AppleMilk")
            # manual nice names
            mapping_en={
                "applemilk":"Apple&Milk&Tea!",
                "applemilk_material":"Apple&Milk Materials",
                "applemilk_food":"Apple&Milk Foods",
                "applemilk_container":"Apple&Milk Containers",
                "applemilk_magic":"Apple&Milk Magic",
            }
            val=mapping_en.get(tab, title_case(tab))
        elif lang_code=="ja_jp":
            mapping_ja={
                "applemilk":"Apple&Milk&Tea!",
                "applemilk_material":"素材",
                "applemilk_food":"食べ物",
                "applemilk_container":"コンテナ",
                "applemilk_magic":"魔法",
            }
            val=mapping_ja.get(tab, tab)
        else:
            # zh generic fallback to en mapping
            val=title_case(tab)
        out[key]=val

    # Also add some other required keys: maybe keep old dc.panMessage etc if needed? But spec says translate block/item, advancement. We could include those old keys as is? The spec doesn't require them, but we could keep them for completeness.
    # We'll also add a few generic keys for completeness: preserve old keys that are not tile/item but are dc.*, fluid.*, etc, converting to same key? But 1.20 expects same keys? Possibly keep them as is for backward compat, but not needed for lang JSON - they would be extra.
    # We'll not add unless needed.

    # Write JSON
    lang_path = assets_defeated / "lang" / f"{lang_code}.json"
    # sort keys alphabetically for deterministic
    ordered=dict(sorted(out.items()))
    lang_path.write_text(json.dumps(ordered, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(f"Wrote {lang_path} with {len(ordered)} keys")

print("Done generating lang")

# 6. Validation checks
print("\n--- Validation ---")
# Check missing textures for block models
missing_block_textures=[]
for name in block_names:
    if name in fluid_blocks:
        continue
    bp = assets_defeated / "models/block" / f"{name}.json"
    if not bp.exists():
        missing_block_textures.append(name)
    else:
        j=json.loads(bp.read_text(encoding="utf-8"))
        tex=j.get("textures",{}).get("all","")
        # tex is defeatedcrow:block/<texture>
        if tex.startswith("defeatedcrow:block/"):
            tname=tex[len("defeatedcrow:block/"):]
            # check file exists (case-insensitive lower)
            # block textures dir
            exists = any(p.stem.lower()==tname.lower() for p in block_tex_dir.glob("*.png"))
            if not exists:
                missing_block_textures.append(f"{name} -> {tname} missing")
print(f"Missing block models/textures: {missing_block_textures[:10]} total {len(missing_block_textures)}")

missing_item=[]
for name in item_names:
    ip = assets_defeated / "models/item" / f"{name}.json"
    if not ip.exists():
        missing_item.append(name)
    else:
        j=json.loads(ip.read_text(encoding="utf-8"))
        parent=j.get("parent","")
        if parent=="minecraft:item/generated":
            tex=j.get("textures",{}).get("layer0","")
            if tex.startswith("defeatedcrow:item/"):
                trel=tex[len("defeatedcrow:item/"):]
                # Need to check file exists under textures/items with that relative path .png
                # trel may include subfolder
                candidate = item_tex_dir / (trel + ".png")
                if not candidate.exists():
                    # case-insensitive check
                    # check lower
                    found=False
                    for p in item_tex_dir.rglob("*.png"):
                        rel=p.relative_to(item_tex_dir).with_suffix("").as_posix()
                        if rel.lower()==trel.lower():
                            found=True
                            break
                    if not found:
                        missing_item.append(f"{name} -> {trel} missing")
        elif parent.startswith("defeatedcrow:block/"):
            # check block model exists
            block_model = assets_defeated / "models/block" / f"{name}.json"
            if not block_model.exists() and name not in fluid_blocks:
                missing_item.append(f"{name} block parent missing {block_model}")
print(f"Missing item models/textures: {missing_item[:20]} total {len(missing_item)}")

# Lang keys
for lang_code in ["en_us","ja_jp","zh_cn","zh_tw"]:
    p=assets_defeated / "lang" / f"{lang_code}.json"
    if not p.exists():
        print(f"Missing lang {lang_code}")
    else:
        j=json.loads(p.read_text(encoding="utf-8"))
        # avoid printing non-ascii that fails on cp932 console; encode ascii with replacement
        try:
            sample = list(j.items())[:2]
            print(f"Lang {lang_code}: {len(j)} entries, sample {sample}")
        except UnicodeEncodeError:
            print(f"Lang {lang_code}: {len(j)} entries")

# Check orphan folder exists?
print(f"Orphan exists before delete? {assets_old.exists()}")
# We will not delete yet, manual step after validation
