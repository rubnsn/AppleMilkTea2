#!/usr/bin/env python3
# Fix Item*.java under common/block that were migrated as Block -> should be BlockItem
import pathlib, re

ROOT = pathlib.Path(r"E:\AppleMilkTea2")
# list
item_files = list((ROOT/"src/main/java/mods/defeatedcrow/common/block").rglob("Item*.java"))
for p in item_files:
    text = p.read_text(encoding='utf-8')
    if "extends Block " in text or "extends Block implements" in text:
        # restore from git HEAD then migrate as item
        import subprocess
        # git show HEAD: path
        rel = p.relative_to(ROOT).as_posix()
        try:
            orig = subprocess.check_output(["git", "show", f"HEAD:{rel}"], cwd=str(ROOT))
            # try decode cp932 first then utf-8
            try:
                orig_text = orig.decode('cp932')
            except:
                orig_text = orig.decode('utf-8', errors='ignore')
        except subprocess.CalledProcessError:
            print(f"[skip] no HEAD for {rel}")
            continue
        # Now migrate as item (BlockItem)
        # Find class name
        m = re.search(r'public\s+class\s+(\w+)', orig_text)
        if not m:
            continue
        class_name = m.group(1)
        # Determine package
        m_pkg = re.search(r'package\s+([\w\.]+);', orig_text)
        pkg = m_pkg.group(1) if m_pkg else "mods.defeatedcrow.common.block"
        # Build new BlockItem
        new_lines = []
        new_lines.append(f"package {pkg};")
        new_lines.append("")
        new_lines.append("import net.minecraft.world.item.BlockItem;")
        new_lines.append("import net.minecraft.world.item.ItemStack;")
        new_lines.append("import net.minecraft.world.level.block.Block;")
        new_lines.append("import net.minecraft.network.chat.Component;")
        new_lines.append("import net.minecraft.world.level.Level;")
        new_lines.append("import java.util.List;")
        new_lines.append("")
        new_lines.append("/**")
        new_lines.append(f" * WT-A 1.20.1: {class_name} -> BlockItem (formerly ItemBlock).")
        new_lines.append(f" * Registration: ModItems + ModBlocks DeferredRegister (see ModItems.java:192)")
        new_lines.append(" */")
        new_lines.append(f"public class {class_name} extends BlockItem {{")
        new_lines.append(f"    public {class_name}(Block block, Properties properties) {{")
        new_lines.append(f"        super(block, properties);")
        new_lines.append(f"    }}")
        new_lines.append("}")
        p.write_text("\n".join(new_lines), encoding='utf-8')
        print(f"[fix] {rel} -> BlockItem")

print("done")
