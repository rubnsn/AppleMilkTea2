#!/usr/bin/env python3
"""Generate Tag JSONs for WT-B OreDictionary -> TagKey migration.
Uses mapping from RegisterOreHandler ores -> registry values (approximate).
Creates data/forge/tags/items, data/c/tags/items, data/defeatedcrow/tags/items.
"""
import json, pathlib, re

ROOT = pathlib.Path(r"E:\AppleMilkTea2")
DATA_ROOT = ROOT / "src/main/resources/data"

# oreName -> tag path mapping uses TagHelper logic (re-implemented)
def to_snake(s):
    out = ""
    for i,ch in enumerate(s):
        if ch.isupper():
            if out: out += "_"
            out += ch.lower()
        else:
            out += ch
    return out.lower()

def ore_to_tag_path(ore):
    if ore.startswith("tool"): return "tools/" + to_snake(ore[4:])
    if ore.startswith("gear"): return "gears/" + to_snake(ore[4:])
    if ore.startswith("plate"): return "plates/" + to_snake(ore[5:])
    if ore.startswith("stick"): return "rods/" + to_snake(ore[5:])
    if ore.startswith("dust"): return "dusts/" + to_snake(ore[4:])
    if ore.startswith("nugget"): return "nuggets/" + to_snake(ore[6:])
    if ore.startswith("gem"): return "gems/" + to_snake(ore[3:])
    if ore.startswith("ingot"): return "ingots/" + to_snake(ore[5:])
    if ore.startswith("ore") and len(ore)>3 and ore[3].isupper(): return "ores/" + to_snake(ore[3:])
    if ore.startswith("block"): return "storage_blocks/" + to_snake(ore[5:])
    if ore.startswith("dye"): return "dyes/" + to_snake(ore[3:])
    if ore == "treeSapling": return "saplings"
    if ore.startswith("sapling"):
        rest = ore[7:]
        return "saplings" if not rest else "saplings/" + to_snake(rest)
    if ore == "logWood": return "logs"
    if ore == "treeLeaves": return "leaves"
    if ore == "slimeball": return "slimeballs"
    if ore.startswith("bucket"): return "buckets/" + to_snake(ore[6:])
    if ore.startswith("bottle"): return "bottles/" + to_snake(ore[6:])
    if ore.startswith("foodBlock"): return "foods/blocks/" + to_snake(ore[9:])
    if ore.startswith("food"): return "foods/" + to_snake(ore[4:])
    if ore.startswith("crop"): return "crops/" + to_snake(ore[4:])
    if ore.startswith("cooking"): return "crops/" + to_snake(ore[7:])
    if ore.startswith("item"): return "items/" + to_snake(ore[4:])
    return to_snake(ore)

# unique ores from RegisterOreHandler (pre-stub)
ores = [
 "dyeBlack","dyeOrange","dyeWhite","treeSapling","logWood","treeLeaves","saplingTea","saplingCassis","saplingCamellia","saplingYuzu",
 "foodClam","cookingClam","cropTea","cropSpiceleaf","cropCassis","cropYuzu","cropCitron","cropCamellia",
 "foodCondencedMilk","foodCassisPreserve","foodMintSauce","foodYuzuMarmalade","foodGreenTea","foodTea","foodEarlGray","foodAppletea","foodCoffee","cropRice","cookingRice","foodYeast",
 "bucketCamelliaOil","bucketVegitableOil","bottleCamelliaOil","bottleVegitableOil",
 "foodGratedApple","foodGratedFruit","foodHoneyLemon","foodGratedLime","foodGratedTomato","foodGratedBerry","foodGratedGrape","foodGratedOrange","foodGratedChocolate",
 "foodBlockMilk","foodBlockGreentea","foodBlockMilkGrenntea","foodBlockTea","foodBlockMilkTea","foodBlockCocoa","foodBlockMilkCocoa","foodBlockJuice","foodBlockMilkJuice","foodBlockLemonade","foodBlockMilkLamonade","foodBlockCoffee","foodBlockMilkCoffee","foodBlockEarlGray","foodBlockMilkEarlGray","foodBlockAppletea","foodBlockMilkAppletea","foodBlockLimejuice","foodBlockTomatojuice","foodBlockMilkBerry","foodBlockGrapejuice","foodBlockMintTea","foodBlockYuzuDrink","foodBlockOrangejuice","foodBlockSoda",
 "foodBlockRice","foodBlockMushroomstew","foodBlockSalmonstew","foodBlockZousui","foodBlockKayakumeshi","foodBlockTofunabe","foodBlockPupmkinsoup","foodBlockBLTsoup",
 "foodBlockSteak","foodBlockTonteki","foodBlockChicken","foodBlockClam",
 "foodToffyApple","foodAppleTart","foodCassisTart","foodYuzuCake","foodApricotCake","foodBakedApple","foodAppleSandwich","foodEggSandwich","foodCassisSandwich","foodYuzuSandwich","foodCookedClam",
 "blockChalcedony","toolGrater","toolChaicedonyHammer","toolChaicedonyKnife","toolChaicedonyShears","toolFirestarter","blockTeaMaker","blockIceMaker","blockEmptyCup","blockEmptyPan","blockEvaporator","blockProsessor",
 "gearIron","gearChalcedony","foodBlackEgg","gemIce","foodCrushedIce","cookingIce","dustGlass","nuggetIron","nuggetTin","nuggetCopper","nuggetSilver","nuggetSteel","nuggetLead","nuggetBronze","nuggetFlint",
 "dustWood","dustCharcoal","dustCoal","dustAsh","dustOilCake","stickCarbon","plateChocolate",
 "dustIron","dustTin","dustCopper","dustSilver","dustLead","dustGold","dustNickel","dustPlatinum",
 "blockClam","gemClam","dustClam","blockClamSand","slimeball",
 "foodFruitsChocolate","foodBlockIcecream",
 "bottleAppleliqueur","bottleTealiqueur","bottleCassisliqueur","bottlePlumliqueur","bottleAmarettoliqueur",
 "bottleShothu","bottleSake","bottleBeer","bottleWine","bottleGin","bottleRum","bottleVodka","bottleWhiskey","bottleBrandy",
 "itemIncense","itemEssentialOil",
]

# ore -> registry values heuristic
ore_values = {
 "dyeBlack": ["defeatedcrow:ink_stick"],
 "dyeOrange": ["defeatedcrow:food_tea"],
 "dyeWhite": ["defeatedcrow:ex_items"],
 "treeSapling": ["defeatedcrow:sapling_tea","defeatedcrow:sapling_yuzu"],
 "logWood": ["defeatedcrow:log_yuzu"],
 "treeLeaves": ["defeatedcrow:leaves_yuzu"],
 "saplingTea": ["defeatedcrow:sapling_tea"],
 "saplingCassis": ["defeatedcrow:sapling_tea"],
 "saplingCamellia": ["defeatedcrow:sapling_tea"],
 "saplingYuzu": ["defeatedcrow:sapling_yuzu"],
 "foodClam": ["defeatedcrow:clam"],
 "cookingClam": ["defeatedcrow:clam"],
 "cropTea": ["defeatedcrow:leaf_tea"],
 "cropSpiceleaf": ["defeatedcrow:leaf_mint"],
 "cropCassis": ["defeatedcrow:leaf_cassis"],
 "cropYuzu": ["defeatedcrow:leaf_yuzu"],
 "cropCitron": ["defeatedcrow:leaf_yuzu"],
 "cropCamellia": ["defeatedcrow:leaf_camellia"],
 "foodCondencedMilk": ["defeatedcrow:condensed_milk"],
 "foodCassisPreserve": ["defeatedcrow:condensed_milk"],
 "foodMintSauce": ["defeatedcrow:condensed_milk"],
 "foodYuzuMarmalade": ["defeatedcrow:condensed_milk"],
 "foodGreenTea": ["defeatedcrow:food_tea"],
 "foodTea": ["defeatedcrow:food_tea"],
 "foodEarlGray": ["defeatedcrow:food_tea"],
 "foodAppletea": ["defeatedcrow:food_tea"],
 "foodCoffee": ["defeatedcrow:grated_apple"],
 "cropRice": ["defeatedcrow:minced_foods"],
 "cookingRice": ["defeatedcrow:bowl_block","defeatedcrow:bowl_jp"],
 "foodYeast": ["defeatedcrow:yeast"],
 "bucketCamelliaOil": ["defeatedcrow:camellia_oil_bucket"],
 "bucketVegitableOil": ["defeatedcrow:vegetable_oil_bucket"],
 "bottleCamelliaOil": ["defeatedcrow:camellia_oil_bottle"],
 "bottleVegitableOil": ["defeatedcrow:vegetable_oil_bottle"],
 "foodGratedApple": ["defeatedcrow:grated_apple"],
 "foodGratedFruit": ["defeatedcrow:grated_apple"],
 "foodHoneyLemon": ["defeatedcrow:grated_apple"],
 "foodGratedLime": ["defeatedcrow:grated_apple"],
 "foodGratedTomato": ["defeatedcrow:grated_apple"],
 "foodGratedBerry": ["defeatedcrow:grated_apple"],
 "foodGratedGrape": ["defeatedcrow:grated_apple"],
 "foodGratedOrange": ["defeatedcrow:grated_apple"],
 "foodGratedChocolate": ["defeatedcrow:minced_foods"],
 "foodBlockMilk": ["defeatedcrow:filled_cup"],
 "foodBlockGreentea": ["defeatedcrow:filled_cup"],
 "foodBlockMilkGrenntea": ["defeatedcrow:filled_cup"],
 "foodBlockTea": ["defeatedcrow:filled_cup"],
 "foodBlockMilkTea": ["defeatedcrow:filled_cup"],
 "foodBlockCocoa": ["defeatedcrow:filled_cup"],
 "foodBlockMilkCocoa": ["defeatedcrow:filled_cup"],
 "foodBlockJuice": ["defeatedcrow:filled_cup"],
 "foodBlockMilkJuice": ["defeatedcrow:filled_cup"],
 "foodBlockLemonade": ["defeatedcrow:filled_cup"],
 "foodBlockMilkLamonade": ["defeatedcrow:filled_cup"],
 "foodBlockCoffee": ["defeatedcrow:filled_cup"],
 "foodBlockMilkCoffee": ["defeatedcrow:filled_cup"],
 "foodBlockEarlGray": ["defeatedcrow:filled_cup2"],
 "foodBlockMilkEarlGray": ["defeatedcrow:filled_cup2"],
 "foodBlockAppletea": ["defeatedcrow:filled_cup2"],
 "foodBlockMilkAppletea": ["defeatedcrow:filled_cup2"],
 "foodBlockLimejuice": ["defeatedcrow:filled_cup2"],
 "foodBlockTomatojuice": ["defeatedcrow:filled_cup2"],
 "foodBlockMilkBerry": ["defeatedcrow:filled_cup2"],
 "foodBlockGrapejuice": ["defeatedcrow:filled_cup2"],
 "foodBlockMintTea": ["defeatedcrow:filled_cup2"],
 "foodBlockYuzuDrink": ["defeatedcrow:filled_cup2"],
 "foodBlockOrangejuice": ["defeatedcrow:filled_cup2"],
 "foodBlockSoda": ["defeatedcrow:filled_cup2"],
 "foodBlockRice": ["defeatedcrow:bowl_block"],
 "foodBlockMushroomstew": ["defeatedcrow:bowl_block"],
 "foodBlockSalmonstew": ["defeatedcrow:bowl_block"],
 "foodBlockZousui": ["defeatedcrow:bowl_block"],
 "foodBlockKayakumeshi": ["defeatedcrow:bowl_block"],
 "foodBlockTofunabe": ["defeatedcrow:bowl_block"],
 "foodBlockPupmkinsoup": ["defeatedcrow:bowl_block"],
 "foodBlockBLTsoup": ["defeatedcrow:bowl_block"],
 "foodBlockSteak": ["defeatedcrow:food_plate"],
 "foodBlockTonteki": ["defeatedcrow:food_plate"],
 "foodBlockChicken": ["defeatedcrow:food_plate"],
 "foodBlockClam": ["defeatedcrow:food_plate"],
 "foodToffyApple": ["defeatedcrow:toffy_apple"],
 "foodAppleTart": ["defeatedcrow:apple_tart"],
 "foodCassisTart": ["defeatedcrow:apple_tart"],
 "foodYuzuCake": ["defeatedcrow:apple_tart"],
 "foodApricotCake": ["defeatedcrow:apple_tart"],
 "foodBakedApple": ["defeatedcrow:baked_apple"],
 "foodAppleSandwich": ["defeatedcrow:apple_sandwich"],
 "foodEggSandwich": ["defeatedcrow:apple_sandwich"],
 "foodCassisSandwich": ["defeatedcrow:apple_sandwich"],
 "foodYuzuSandwich": ["defeatedcrow:apple_sandwich"],
 "foodCookedClam": ["defeatedcrow:clam"],
 "blockChalcedony": ["defeatedcrow:chalcedony"],
 "toolGrater": ["defeatedcrow:grater"],
 "toolChaicedonyHammer": ["defeatedcrow:chalcedony_hammer"],
 "toolChaicedonyKnife": ["defeatedcrow:chalcedony_knife"],
 "toolChaicedonyShears": ["defeatedcrow:chalcedony_shears"],
 "toolFirestarter": ["defeatedcrow:fire_starter"],
 "blockTeaMaker": ["defeatedcrow:tea_maker_next"],
 "blockIceMaker": ["defeatedcrow:ice_maker"],
 "blockEmptyCup": ["defeatedcrow:empty_cup"],
 "blockEmptyPan": ["defeatedcrow:empty_pan_g"],
 "blockEvaporator": ["defeatedcrow:evaporator"],
 "blockProsessor": ["defeatedcrow:processor"],
 "gearIron": ["defeatedcrow:ex_items"],
 "gearChalcedony": ["defeatedcrow:ex_items"],
 "foodBlackEgg": ["defeatedcrow:clam"],
 "gemIce": ["defeatedcrow:icy_crystal"],
 "foodCrushedIce": ["defeatedcrow:ex_items"],
 "cookingIce": ["defeatedcrow:ex_items"],
 "dustGlass": ["defeatedcrow:ex_items"],
 "nuggetIron": ["defeatedcrow:ex_items"],
 "nuggetTin": ["defeatedcrow:ex_items"],
 "nuggetCopper": ["defeatedcrow:ex_items"],
 "nuggetSilver": ["defeatedcrow:ex_items"],
 "nuggetSteel": ["defeatedcrow:ex_items"],
 "nuggetLead": ["defeatedcrow:ex_items"],
 "nuggetBronze": ["defeatedcrow:ex_items"],
 "nuggetFlint": ["defeatedcrow:ex_items"],
 "dustWood": ["defeatedcrow:wood_dust"],
 "dustCharcoal": ["defeatedcrow:dust_wood"],
 "dustCoal": ["defeatedcrow:dust_wood"],
 "dustAsh": ["defeatedcrow:dust_wood"],
 "dustOilCake": ["defeatedcrow:dust_wood"],
 "stickCarbon": ["defeatedcrow:carbon_stick"],
 "plateChocolate": ["defeatedcrow:choco_fruits"],
 "dustIron": ["defeatedcrow:ore_dust"],
 "dustTin": ["defeatedcrow:ore_dust"],
 "dustCopper": ["defeatedcrow:ore_dust"],
 "dustSilver": ["defeatedcrow:ore_dust"],
 "dustLead": ["defeatedcrow:ore_dust"],
 "dustGold": ["defeatedcrow:ore_dust"],
 "dustNickel": ["defeatedcrow:ore_dust"],
 "dustPlatinum": ["defeatedcrow:ore_dust"],
 "blockClam": ["defeatedcrow:gunpowder_container"],
 "gemClam": ["defeatedcrow:princess_clam"],
 "dustClam": ["defeatedcrow:ex_items"],
 "blockClamSand": ["defeatedcrow:clam_sand"],
 "slimeball": ["defeatedcrow:ex_items"],
 "foodFruitsChocolate": ["defeatedcrow:choco_fruits"],
 "foodBlockIcecream": ["defeatedcrow:ice_cream_block"],
 "bottleAppleliqueur": ["defeatedcrow:cordial"],
 "bottleTealiqueur": ["defeatedcrow:cordial"],
 "bottleCassisliqueur": ["defeatedcrow:cordial"],
 "bottlePlumliqueur": ["defeatedcrow:cordial"],
 "bottleAmarettoliqueur": ["defeatedcrow:cordial"],
 "bottleShothu": ["defeatedcrow:large_bottle"],
 "bottleSake": ["defeatedcrow:large_bottle"],
 "bottleBeer": ["defeatedcrow:large_bottle"],
 "bottleWine": ["defeatedcrow:large_bottle"],
 "bottleGin": ["defeatedcrow:large_bottle"],
 "bottleRum": ["defeatedcrow:large_bottle"],
 "bottleVodka": ["defeatedcrow:large_bottle"],
 "bottleWhiskey": ["defeatedcrow:large_bottle"],
 "bottleBrandy": ["defeatedcrow:large_bottle"],
 "itemIncense": ["defeatedcrow:incense_apple","defeatedcrow:incense_rose","defeatedcrow:incense_mint","defeatedcrow:incense_clam","defeatedcrow:incense_ice","defeatedcrow:incense_lavender","defeatedcrow:incense_sandalwood","defeatedcrow:incense_agar","defeatedcrow:incense_frankincense","defeatedcrow:incense_yuzu","defeatedcrow:incense_vanilla"],
 "itemEssentialOil": ["defeatedcrow:essential_oil"],
}

def write_tag(namespace, ore, values):
    path = ore_to_tag_path(ore)
    # special: for foodBlock etc, forge path is under foods/blocks, c likewise, defeatedcrow likewise
    # determine file location
    file_path = DATA_ROOT / namespace / "tags" / "items" / (path + ".json")
    file_path.parent.mkdir(parents=True, exist_ok=True)
    # if exists, merge? For now overwrite if not already correct type
    data = {"replace": False, "values": values}
    # only write if not exists or different
    if file_path.exists():
        try:
            existing = json.loads(file_path.read_text(encoding="utf-8"))
            if existing == data:
                return False
        except: pass
    file_path.write_text(json.dumps(data, indent=2) + "\n", encoding="utf-8")
    return True

created = 0
for ore in ores:
    vals = ore_values.get(ore, [f"defeatedcrow:{to_snake(ore)}"])
    # forge and c
    for ns in ["forge","c"]:
        if write_tag(ns, ore, vals):
            print(f"[create] {ns}:tags/items/{ore_to_tag_path(ore)}.json -> {vals}")
            created+=1
    # for defeatedcrow-specific (itemIncense etc) also put under defeatedcrow
    if ore.startswith("item") or ore.startswith("foodBlock") or ore in ["blockChalcedony","gearIron"]:
        if write_tag("defeatedcrow", ore, vals):
            print(f"[create] defeatedcrow:tags/items/{ore_to_tag_path(ore)}.json")
            created+=1
    # special: also ensure forge/c have entry even if duplicate ore name collapses (bottleShothu variants with same ore name but same path)
    # handle deduplication: bottleShothu etc all same ore name but we already did one per ore name

# Also generate generic tags for yeasts etc missing?
print(f"Done, created/updated {created} tag files")
# list
import subprocess, sys
# count total tags
total = len(list((DATA_ROOT/"forge"/"tags"/"items").rglob("*.json")))
print(f"Total forge tags: {total}")
total2 = len(list((DATA_ROOT/"c"/"tags"/"items").rglob("*.json")))
print(f"Total c tags: {total2}")
