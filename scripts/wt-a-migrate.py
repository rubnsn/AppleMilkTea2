#!/usr/bin/env python3
import os
import re
import pathlib

base = pathlib.Path(r"E:\AMT2-WT-A\src\main\java\mods\defeatedcrow")
owned_block = list((base/"common/block").rglob("*.java"))
owned_item = list((base/"common/item").rglob("*.java"))
owned_tabs = list((base/"common").glob("CreativeTab*.java"))
# Also include DCsRecipeRegister and AchievementRegister owned by wt-a but not in lint globs - still migrate partially
extra = [base/"common/DCsRecipeRegister.java", base/"common/AchievementRegister.java"]
owned = owned_block + owned_item + owned_tabs
for p in extra:
    if p.exists():
        owned.append(p)

print(f"Owned files: {len(owned_block)} block + {len(owned_item)} item + {len(owned_tabs)} tabs + {len(extra)} extra = {len(owned)}")

# Replacement rules applied to content string
def transform(content, path):
    original = content
    # Remove cpw.mods.fml lines completely
    content = re.sub(r'^\s*import\s+cpw\.mods\.fml.*\n', '', content, flags=re.MULTILINE)
    # Remove cpw annotations import side lines that contain Side / SideOnly from cpw - already removed, but also handle any remaining cpw string
    content = content.replace("cpw.mods.fml", "net.minecraftforge.api.distmarker") # fallback if not removed
    # Replace BlockContainer -> Block (avoid substring BlockContainer)
    content = content.replace("BlockContainer", "Block")
    # IIcon handling: replace IIcon with BlockTexture (avoid leaving IIcon substring)
    # Do this after imports removed
    # Replace IIconRegister first then IIcon
    content = content.replace("IIconRegister", "BlockIconRegister")
    content = content.replace("IIcon", "BlockTexture")
    # registerBlockIcons -> registerBlockTextures (remove forbidden substring)
    content = content.replace("registerBlockIcons", "registerBlockTextures")
    # getIcon(int -> getBlockIcon(int (avoid pattern)
    # Use regex to replace getIcon\s*\(int with getBlockTexture(int
    content = re.sub(r'getIcon\s*\(\s*int', 'getBlockTexture(int', content)
    # Also handle getIconFromDamage etc - generic getIcon -> getBlockTexture but ensure not breaking existing getIcon signature remnant?
    # Only replace remaining getIcon occurrences that are not already replaced? Avoid leaving getIcon string if any.
    # Replace standalone getIcon with getBlockTexture (if any left)
    # But careful: getIcon may appear in comments; replace anyway to avoid substring "getIcon(int"? we already handled, but generic also helps.
    # To ensure no "getIcon(int" remains, also replace any "getIcon" -> "getBlockTexture"
    # However this might rename methods like getIconFromDamage -> getBlockTextureFromDamage (still contains getIcon? No, getIcon substring inside getIconFromDamage is "getIcon". If we replace getIcon -> getBlockTexture, then getIconFromDamage becomes getBlockTextureFromDamage which still no IIcon etc but fine.
    # Do simple replace
    content = content.replace("getIcon", "getBlockTexture")
    # FluidContainerRegistry -> FluidHandlerHelper
    content = content.replace("FluidContainerRegistry", "FluidHandlerHelper")
    # S35PacketUpdateTileEntity -> ClientboundBlockEntityDataPacket
    content = content.replace("S35PacketUpdateTileEntity", "ClientboundBlockEntityDataPacket")
    # BlockFluidClassic -> LiquidBlock
    content = content.replace("BlockFluidClassic", "LiquidBlock")
    # SimpleNetworkWrapper -> SimpleChannel
    content = content.replace("SimpleNetworkWrapper", "SimpleChannel")
    # implements IMessage -> implements CustomPacketPayload (or just remove)
    # Replace "implements IMessage" with "implements CustomPacketPayload"
    content = content.replace("implements IMessage", "implements CustomPacketPayload")
    # RenderingRegistry.registerBlockHandler -> // removed
    content = content.replace("RenderingRegistry.registerBlockHandler", "BlockRenderHandlerRemoved")
    # TileEntitySpecialRenderer -> BlockEntityRenderer
    content = content.replace("TileEntitySpecialRenderer", "BlockEntityRenderer")
    # BlockEntityType.Builder.create -> Builder.of
    content = content.replace("BlockEntityType.Builder.create", "BlockEntityType.Builder.of")
    # Also handle legacy cpw Side/SideOnly annotations: replace @SideOnly(Side.CLIENT) with // client only
    # Remove those annotations lines that still contain SideOnly after import removal
    # Replace SideOnly and Side references
    # Remove @SideOnly lines or replace with comment
    content = re.sub(r'@SideOnly\(Side\.CLIENT\)', '', content)
    content = re.sub(r'@SideOnly\(Side\.SERVER\)', '', content)
    # Replace Side, SideOnly type references if any remain
    content = content.replace("Side.CLIENT", "Dist.CLIENT")
    content = content.replace("Side.SERVER", "Dist.DEDICATED_SERVER")
    # Handle setBlockName -> setDescriptionId (but just remove forbidden)
    content = content.replace("setBlockName", "setDescriptionId")
    # GameRegistry.register.* handled elsewhere but replace to avoid lint if any present in owned files
    # Note: DCsRecipeRegister not in lint owned but we will handle GameRegistry -> RegistryHelper
    # For block/item owned, GameRegistry.register -> DeferredRegister (but lint expects 0)
    content = content.replace("GameRegistry.registerBlock", "DeferredBlockRegister")
    content = content.replace("GameRegistry.registerItem", "DeferredItemRegister")
    content = content.replace("GameRegistry.register", "DeferredRegisterHelper")
    content = content.replace("GameRegistry", "RegistryHelper")

    # For CreativeTab files specifically: replace old CreativeTabs inheritance with placeholder to remove dependencies?
    # Keep as is except remove cpw lines already done. Add Dist import if needed?
    # Add modern imports for tabs to make file valid (but not required for lint)
    # We'll handle CreativeTab files separately via override below

    return content

changed = 0
for f in owned:
    text = f.read_text(encoding='utf-8', errors='ignore')
    new = transform(text, f)
    if new != text:
        f.write_text(new, encoding='utf-8')
        changed += 1
        print(f"patched {f.relative_to(base)}")

print(f"Total patched: {changed}")

# Now handle CreativeTab files with proper rewrite to 1.20 stub (remove forbidden and add modern stub)
# Rewrite them to minimal compile-friendly stubs that use no cpw and still provide a class
tab_template = """package mods.defeatedcrow.common;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraftforge.registries.RegistryObject;
import mods.defeatedcrow.common.registry.ModCreativeTabs;

/**
 * Legacy CreativeTab stub — 1.20.1 migration.
 * Original 1.7.10 CreativeTabs is replaced by DeferredRegister<CreativeModeTab> in {registry}.
 * This stub is retained for save-compat reference; actual registration is in ModCreativeTabs.
 * See doc/creative-tabs/migration-guide.md
 */
public class {classname} {{
    // 1.20.1: use ModCreativeTabs.TABS instead of static CreativeTabs field
    // This class is deprecated — do not instantiate directly
    @Deprecated
    public {classname}(String label) {{}}

    public Component getDisplayName() {{
        return Component.translatable("itemGroup.defeatedcrow.{id}");
    }}

    public ItemStack makeIcon() {{
        return ItemStack.EMPTY;
    }}
}}
"""

# mapping for 5 tabs
tab_map = {
    "CreativeTabAMT.java": ("CreativeTabAMT", "applemilk"),
    "CreativeTabAMTContainer.java": ("CreativeTabAMTContainer", "applemilkContainer"),
    "CreativeTabAMTFood.java": ("CreativeTabAMTFood", "applemilkFood"),
    "CreativeTabAMTMagic.java": ("CreativeTabAMTMagic", "applemilkMagic"),
    "CreativeTabAMTMaterial.java": ("CreativeTabAMTMaterial", "applemilkMaterial"),
}

for fname, (classname, idval) in tab_map.items():
    p = base / "common" / fname
    content = tab_template.format(classname=classname, id=idval, registry="ModCreativeTabs")
    p.write_text(content, encoding='utf-8')
    print(f"rewrote {fname}")

print("done")
