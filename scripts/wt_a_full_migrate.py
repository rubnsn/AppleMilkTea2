#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
WT-A full 1.20.1 mojmap migration: Blocks + Items
Converts 1.7.10 MCP files to minimal compiling 1.20.1 stubs preserving TODO.
- Reads SJIS (cp932) originals, writes UTF-8.
- Skips already migrated files (contain BlockBehaviour or net.minecraft.world.level)
- For blocks: generic Block with Properties constructor, VoxelShape stub, use() stub, remove icons
- For items: generic Item with Properties constructor, remove icons, stub useOn/use/finishUsingItem
"""
import pathlib, re

ROOT = pathlib.Path(r"E:\AppleMilkTea2")
BLOCK_ROOT = ROOT / "src/main/java/mods/defeatedcrow/common/block"
ITEM_ROOT = ROOT / "src/main/java/mods/defeatedcrow/common/item"
# Also include common/item/edible etc via rglob
BLOCK_FILES = list(BLOCK_ROOT.rglob("*.java"))
ITEM_FILES = list(ITEM_ROOT.rglob("*.java"))
# Also check block Item* files already under block/** (they are block's ItemBlock)
# And also check common/block/**/Item*.java already included in BLOCK_FILES

def is_migrated(text):
    # if already has mojmap 1.20.1 marker
    return ("BlockBehaviour.Properties" in text or "net.minecraft.world.level.block.Block" in text or "net.minecraft.world.item.Item" in text) and "BlockTexture" not in text and "BlockIconRegister" not in text

def migrate_block(path: pathlib.Path):
    raw = path.read_bytes()
    try:
        text = raw.decode('utf-8')
    except:
        try:
            text = raw.decode('cp932')
        except:
            text = raw.decode('utf-8', errors='ignore')
    if is_migrated(text):
        # check if still has old hack BlockTexture - then not fully migrated
        if "BlockTexture" not in text and "BlockIconRegister" not in text and "net.minecraft.world.level.block.state.BlockBehaviour" not in text:
            # Actually BlockMintCrop is migrated (has BlockBehaviour) so skip
            if "BlockBehaviour" in text:
                print(f"[skip] {path.relative_to(ROOT)} already mojmap")
                return False
        # if contains BlockTexture still -> needs migration even if has some mojmap (hybrid)
        if "BlockTexture" in text or "BlockIconRegister" in text:
            pass
        else:
            if "BlockBehaviour" in text:
                print(f"[skip] {path.relative_to(ROOT)} already mojmap")
                return False

    # Extract package
    m_pkg = re.search(r'^\s*package\s+([\w\.]+);', text, re.MULTILINE)
    pkg = m_pkg.group(1) if m_pkg else "mods.defeatedcrow.common.block"
    # Extract class name and extends/implements
    # Find public class declaration
    m_class = re.search(r'public\s+(?:abstract\s+)?class\s+(\w+)\s+extends\s+([\w\.]+)(?:\s+implements\s+([^{]+))?\s*\{', text)
    if not m_class:
        # try without extends (rare)
        m_class = re.search(r'public\s+class\s+(\w+)\s*\{', text)
        if not m_class:
            print(f"[warn] no class found {path}")
            return False
        class_name = m_class.group(1)
        extends_clause = "Block"
        implements_clause = ""
    else:
        class_name = m_class.group(1)
        extends_clause = m_class.group(2)
        implements_clause = m_class.group(3) or ""

    # Determine if originally had TileEntity
    has_tile = "TileEntity" in text or "createNewTileEntity" in text or "createTileEntity" in text
    has_random_tick = "updateTick" in text or "randomDisplayTick" in text

    # Determine original extends type
    orig_extends = extends_clause.strip()
    # Normalize to mojmap base
    # For 1.20.1, most AMT blocks are simple Block or CropBlock etc.
    # Keep CropBlock for mint, keep specific bases if known
    if "CropBlock" in text or path.name == "BlockMintCrop.java":
        base_class = "CropBlock"
        base_import = "import net.minecraft.world.level.block.CropBlock;"
    elif "BlockContainer" in orig_extends:
        base_class = "Block"
        base_import = ""
    elif "Block" in orig_extends:
        base_class = "Block"
        base_import = ""
    else:
        base_class = "Block"
        base_import = ""

    # Build new file content
    # Header imports
    imports = []
    imports.append("import net.minecraft.core.BlockPos;")
    imports.append("import net.minecraft.core.Direction;")
    imports.append("import net.minecraft.network.chat.Component;")
    imports.append("import net.minecraft.world.InteractionHand;")
    imports.append("import net.minecraft.world.InteractionResult;")
    imports.append("import net.minecraft.world.entity.player.Player;")
    imports.append("import net.minecraft.world.item.ItemStack;")
    imports.append("import net.minecraft.world.item.TooltipFlag;")
    imports.append("import net.minecraft.world.level.BlockGetter;")
    imports.append("import net.minecraft.world.level.Level;")
    imports.append("import net.minecraft.world.level.block.Block;")
    imports.append("import net.minecraft.world.level.block.state.BlockBehaviour;")
    imports.append("import net.minecraft.world.level.block.state.BlockState;")
    imports.append("import net.minecraft.world.phys.BlockHitResult;")
    imports.append("import net.minecraft.world.phys.shapes.CollisionContext;")
    imports.append("import net.minecraft.world.phys.shapes.VoxelShape;")
    imports.append("import net.minecraft.world.phys.shapes.Shapes;")
    if has_tile:
        imports.append("import net.minecraft.world.level.block.EntityBlock;")
        imports.append("import net.minecraft.world.level.block.entity.BlockEntity;")
        imports.append("import org.jetbrains.annotations.Nullable;")
    if base_class == "CropBlock":
        imports.append("import net.minecraft.world.level.block.CropBlock;")
        imports.append("import net.minecraft.world.level.block.state.StateDefinition;")
        imports.append("import net.minecraft.world.level.block.state.properties.IntegerProperty;")
        imports.append("import net.minecraft.util.RandomSource;")
        imports.append("import net.minecraft.server.level.ServerLevel;")
    # dedup
    imports = sorted(set(imports))

    # Build class header
    # For blocks with tile, implement EntityBlock
    impl = ""
    if has_tile:
        impl = " implements EntityBlock"

    # Preserve Javadoc comment if exists before class
    # Extract original file header comments (package + imports + javadoc)
    # We'll generate new javadoc
    new_lines = []
    new_lines.append(f"package {pkg};")
    new_lines.append("")
    for imp in imports:
        new_lines.append(imp)
    new_lines.append("")
    new_lines.append("/**")
    new_lines.append(f" * WT-A 1.20.1 mojmap migration for {class_name}.")
    new_lines.append(f" * Original 1.7.10 logic preserved as TODO; stub compiles under Forge 47 + mojmap.")
    new_lines.append(f" * Properties are supplied by ModBlocks (BlockBehaviour.Properties.of()...).")
    if has_tile:
        new_lines.append(f" * Former BlockContainer/TileEntity logic: see Tile* migration (WT-B).")
    new_lines.append(f" * Textures: JSON models under assets/defeatedcrow/models/block/ + blockstates/")
    new_lines.append(" */")
    # Special handling for CropBlock subclass (BlockMintCrop already migrated, skip)
    if path.name == "BlockMintCrop.java":
        # This file is already properly migrated, don't overwrite
        print(f"[skip] {path.relative_to(ROOT)} is reference CropBlock, keep as-is")
        return False

    if base_class == "CropBlock":
        # shouldn't happen for other files, but keep generic
        new_lines.append(f"public class {class_name} extends {base_class}{impl} {{")
    else:
        new_lines.append(f"public class {class_name} extends Block{impl} {{")
    new_lines.append("")
    new_lines.append(f"    public {class_name}(BlockBehaviour.Properties properties) {{")
    new_lines.append(f"        super(properties);")
    new_lines.append(f"    }}")
    new_lines.append("")
    # Common overrides stub
    new_lines.append("    // 1.20.1: VoxelShape replaces AxisAlignedBB / setBlockBounds / getSelectedBoundingBox")
    new_lines.append("    @Override")
    new_lines.append("    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {")
    new_lines.append("        return Shapes.block(); // TODO: restore original bounds via Block.box() per meta/state")
    new_lines.append("    }")
    new_lines.append("")
    new_lines.append("    @Override")
    new_lines.append("    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {")
    new_lines.append("        return getShape(state, level, pos, ctx);")
    new_lines.append("    }")
    new_lines.append("")
    # Use interaction stub (replaces onBlockActivated)
    new_lines.append("    // 1.7.10 onBlockActivated -> 1.20.1 use (BlockPos + BlockHitResult)")
    new_lines.append("    @Override")
    new_lines.append("    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {")
    new_lines.append("        // TODO: restore original onBlockActivated logic")
    new_lines.append("        // Original used: world.getBlockMetadata(x,y,z), player.inventory, MinecraftForge.EVENT_BUS.post(AMTBlockRightClickEvent)")
    new_lines.append("        // Migration: use state, level.getBlockEntity(pos), player.getItemInHand(hand), Component")
    new_lines.append("        return InteractionResult.PASS;")
    new_lines.append("    }")
    new_lines.append("")
    if has_tile:
        new_lines.append("    @Nullable")
        new_lines.append("    @Override")
        new_lines.append("    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {")
        new_lines.append("        // TODO: return new Tile* (pos, state) — requires WT-B BlockEntityType registration")
        new_lines.append("        return null;")
        new_lines.append("    }")
        new_lines.append("")
    # Tooltip stub if original had addInformation / getDescription
    new_lines.append("    @Override")
    new_lines.append("    public void appendHoverText(ItemStack stack, BlockGetter level, java.util.List<Component> tooltip, TooltipFlag flag) {")
    new_lines.append("        // TODO: restore addInformation logic with Component.translatable")
    new_lines.append("        super.appendHoverText(stack, level, tooltip, flag);")
    new_lines.append("    }")
    new_lines.append("")
    # Preserve original file as comment footer for reference
    new_lines.append("    /*")
    new_lines.append("     * Original 1.7.10 source (kept for reference, SJIS -> UTF-8):")
    # add truncated original (first 100 lines) as comment to preserve logic hint
    orig_lines = text.splitlines()[:80]
    for ol in orig_lines:
        # escape comment end
        safe = ol.replace("*/", "* /")
        new_lines.append(f"     * {safe}")
    new_lines.append("     * ... (full original retained in git history: git show HEAD:\""+str(path.relative_to(ROOT)).replace(chr(92),"/")+"\")")
    new_lines.append("     */")
    new_lines.append("}")
    new_content = "\n".join(new_lines) + "\n"
    # Write UTF-8
    path.write_text(new_content, encoding='utf-8')
    print(f"[migrate-block] {path.relative_to(ROOT)} -> {base_class}{impl}")
    return True

def migrate_item(path: pathlib.Path):
    raw = path.read_bytes()
    try:
        text = raw.decode('utf-8')
    except:
        try:
            text = raw.decode('cp932')
        except:
            text = raw.decode('utf-8', errors='ignore')
    if is_migrated(text):
        if "BlockTexture" not in text and "BlockIconRegister" not in text:
            if "net.minecraft.world.item.Item" in text or "net.minecraft.world.item.ItemNameBlockItem" in text:
                print(f"[skip] {path.relative_to(ROOT)} already mojmap")
                return False
    if "BlockTexture" not in text and "BlockIconRegister" not in text and "IIcon" not in text:
        # Check if already mojmap item
        if "net.minecraft.world.item" in text:
            print(f"[skip] {path.relative_to(ROOT)} already mojmap (no texture)")
            return False

    m_pkg = re.search(r'^\s*package\s+([\w\.]+);', text, re.MULTILINE)
    pkg = m_pkg.group(1) if m_pkg else "mods.defeatedcrow.common.item"
    m_class = re.search(r'public\s+(?:abstract\s+)?class\s+(\w+)\s+extends\s+([\w\.]+)(?:\s+implements\s+([^{]+))?\s*\{', text)
    if not m_class:
        m_class = re.search(r'public\s+class\s+(\w+)\s*\{', text)
        if not m_class:
            print(f"[warn] no class {path}")
            return False
        class_name = m_class.group(1)
        extends_clause = "Item"
    else:
        class_name = m_class.group(1)
        extends_clause = m_class.group(2)

    # Detect special item types
    orig_extends = extends_clause.strip()
    base_class = "Item"
    base_import_extra = []
    if "ItemFood" in orig_extends:
        base_class = "Item"
        # FoodProperties will be in ModItems registration, not here
    elif "ItemTool" in orig_extends or "ItemPickaxe" in orig_extends or "ItemSword" in orig_extends:
        base_class = "Item"
    elif "ItemArmor" in orig_extends:
        base_class = "Item"
    elif "ItemSeeds" in orig_extends or "ItemNameBlockItem" in text:
        base_class = "ItemNameBlockItem"
        base_import_extra.append("import net.minecraft.world.level.block.Blocks;")
    elif "ItemBlock" in orig_extends:
        base_class = "BlockItem"
        base_import_extra.append("import net.minecraft.world.item.BlockItem;")
    elif "ItemBucket" in orig_extends:
        base_class = "BucketItem"

    # Skip already migrated ItemMintSeed
    if path.name == "ItemMintSeed.java":
        print(f"[skip] {path.relative_to(ROOT)} already mojmap")
        return False

    imports = []
    imports.append("import net.minecraft.network.chat.Component;")
    imports.append("import net.minecraft.world.InteractionHand;")
    imports.append("import net.minecraft.world.InteractionResultHolder;")
    imports.append("import net.minecraft.world.entity.player.Player;")
    imports.append("import net.minecraft.world.item.Item;")
    imports.append("import net.minecraft.world.item.ItemStack;")
    imports.append("import net.minecraft.world.item.TooltipFlag;")
    imports.append("import net.minecraft.world.level.Level;")
    if base_class == "ItemNameBlockItem":
        imports.append("import net.minecraft.world.item.ItemNameBlockItem;")
        imports.append("import mods.defeatedcrow.common.registry.ModBlocks;")
    if base_class == "BlockItem":
        imports.append("import net.minecraft.world.item.BlockItem;")
        imports.append("import net.minecraft.world.level.block.Block;")
    if base_class == "BucketItem":
        imports.append("import net.minecraft.world.item.BucketItem;")
        imports.append("import net.minecraft.world.level.material.Fluid;")
    imports.extend(base_import_extra)
    imports = sorted(set(imports))

    new_lines = []
    new_lines.append(f"package {pkg};")
    new_lines.append("")
    for imp in imports:
        new_lines.append(imp)
    new_lines.append("")
    new_lines.append("/**")
    new_lines.append(f" * WT-A 1.20.1 mojmap migration for {class_name}.")
    new_lines.append(f" * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).")
    new_lines.append(f" * Textures: JSON models under assets/defeatedcrow/models/item/")
    new_lines.append(" */")
    if base_class == "ItemNameBlockItem":
        new_lines.append(f"public class {class_name} extends ItemNameBlockItem {{")
        new_lines.append(f"    public {class_name}(Properties properties) {{")
        # Generic: need Block and farmland
        new_lines.append(f"        super(ModBlocks.CROP_MINT.get(), properties); // TODO: correct Block for {class_name}")
        new_lines.append(f"    }}")
    elif base_class == "BlockItem":
        new_lines.append(f"public class {class_name} extends BlockItem {{")
        new_lines.append(f"    public {class_name}(Block block, Properties properties) {{")
        new_lines.append(f"        super(block, properties);")
        new_lines.append(f"    }}")
    elif base_class == "BucketItem":
        new_lines.append(f"public class {class_name} extends BucketItem {{")
        new_lines.append(f"    public {class_name}(java.util.function.Supplier<? extends Fluid> fluid, Properties properties) {{")
        new_lines.append(f"        super(fluid, properties);")
        new_lines.append(f"    }}")
    else:
        new_lines.append(f"public class {class_name} extends Item {{")
        new_lines.append(f"    public {class_name}(Properties properties) {{")
        new_lines.append(f"        super(properties);")
        new_lines.append(f"    }}")
    new_lines.append("")
    # Common stubs
    new_lines.append("    @Override")
    new_lines.append("    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {")
    new_lines.append("        // TODO: restore addInformation logic")
    new_lines.append("        super.appendHoverText(stack, level, tooltip, flag);")
    new_lines.append("    }")
    new_lines.append("")
    # If original was food, add food hint
    if "ItemFood" in orig_extends or "hungerOnEaten" in text or "FoodBase" in text:
        new_lines.append("    // 1.7.10 ItemFood -> 1.20.1 FoodProperties in ModItems registration (Item.Properties.food(...))")
        new_lines.append("    // finishUsingItem replaces onEaten/onFoodEaten")
        new_lines.append("    @Override")
        new_lines.append("    public ItemStack finishUsingItem(ItemStack stack, Level level, net.minecraft.world.entity.LivingEntity entity) {")
        new_lines.append("        return super.finishUsingItem(stack, level, entity);")
        new_lines.append("    }")
        new_lines.append("")
    # useOn / use stubs if original had onItemUse
    if "onItemUse" in text or "onItemRightClick" in text or "spownEntityFoods" in text:
        new_lines.append("    @Override")
        new_lines.append("    public net.minecraft.world.InteractionResult useOn(net.minecraft.world.item.context.UseOnContext ctx) {")
        new_lines.append("        // TODO: restore onItemUse logic with BlockPos/Level/Player")
        new_lines.append("        return super.useOn(ctx);")
        new_lines.append("    }")
        new_lines.append("")
        new_lines.append("    @Override")
        new_lines.append("    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {")
        new_lines.append("        return super.use(level, player, hand);")
        new_lines.append("    }")
        new_lines.append("")
    new_lines.append("    /*")
    new_lines.append("     * Original 1.7.10 source (truncated, full in git history):")
    orig_lines = text.splitlines()[:60]
    for ol in orig_lines:
        safe = ol.replace("*/", "* /")
        new_lines.append(f"     * {safe}")
    new_lines.append("     */")
    new_lines.append("}")
    new_content = "\n".join(new_lines) + "\n"
    path.write_text(new_content, encoding='utf-8')
    print(f"[migrate-item] {path.relative_to(ROOT)} -> {base_class}")
    return True

def main():
    migrated_blocks = 0
    migrated_items = 0
    for p in BLOCK_FILES:
        # Skip already mojmap reference
        if p.name in ("BlockMintCrop.java",):
            continue
        try:
            if migrate_block(p):
                migrated_blocks += 1
        except Exception as e:
            print(f"[error] {p}: {e}")
            import traceback; traceback.print_exc()
    for p in ITEM_FILES:
        if p.name in ("ItemMintSeed.java",):
            continue
        try:
            if migrate_item(p):
                migrated_items += 1
        except Exception as e:
            print(f"[error] {p}: {e}")
            import traceback; traceback.print_exc()
    print(f"Done: blocks {migrated_blocks}/{len(BLOCK_FILES)}, items {migrated_items}/{len(ITEM_FILES)}")

if __name__ == "__main__":
    main()
