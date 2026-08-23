"""api/ directory migration script: 1.7.10 -> 1.20.1 (Forge 47.x, mojmap).

Mechanical conversions only (see doc/api/migration-guide.md):
  1. delete deprecated typo event classes (unused anywhere)
  2. regenerate package-info.java files without cpw.mods.fml.common.API (@API)
  3. plain import rewrites in simple packages (recipe / appliance / charge / energy)

Complex files are migrated manually by the agent afterwards.
"""

from pathlib import Path

BASE = Path(r"E:\AppleMilkTea2\src\main\java\mods\defeatedcrow\api")

# ---------------------------------------------------------------- 1. deletions
DELETE = [
    BASE / "events" / "AMTBlockRightCrickEvent.java",
    BASE / "events" / "AMTFoodEntityRightCrickEvent.java",
]

# ------------------------------------------------------- 2. package-info.java
PKG_INFO = """\
/**
 * Copyright (c) defeatedcrow, 2013
 * URL:http://forum.minecraftuser.jp/viewtopic.php?f=13&t=17657
 * Apple&Milk&Tea! is distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL.
 * Please check the License(MMPL_1.0).txt included in the package file of this Mod.
 *
 * 1.20.1 migration: cpw.mods.fml.common.API (@API annotation) has been removed.
 */

package {pkg};
"""

PKGS = [
    ("package-info.java", "mods.defeatedcrow.api"),
    ("appliance/package-info.java", "mods.defeatedcrow.api.appliance"),
    ("charge/package-info.java", "mods.defeatedcrow.api.charge"),
    ("charm/package-info.java", "mods.defeatedcrow.api.charm"),
    ("edibles/package-info.java", "mods.defeatedcrow.api.edibles"),
    ("energy/package-info.java", "mods.defeatedcrow.api.energy"),
    ("events/package-info.java", "mods.defeatedcrow.api.events"),
    ("plants/package-info.java", "mods.defeatedcrow.api.plants"),
    ("potion/package-info.java", "mods.defeatedcrow.api.potion"),
    ("recipe/package-info.java", "mods.defeatedcrow.api.recipe"),
]

# --------------------------------------------------------- 3. import rewrites
REWRITES = [
    ("import net.minecraft.item.ItemStack;", "import net.minecraft.world.item.ItemStack;"),
    ("import net.minecraft.block.Block;", "import net.minecraft.world.level.block.Block;"),
]

SIMPLE_DIRS = ["recipe", "appliance", "charge", "energy"]


def main() -> None:
    for p in DELETE:
        if p.exists():
            p.unlink()
            print(f"[delete] {p.name}")

    for rel, pkg in PKGS:
        f = BASE / rel
        f.write_text(PKG_INFO.format(pkg=pkg), encoding="utf-8")
        print(f"[pkginf] {rel}")

    for d in SIMPLE_DIRS:
        for f in sorted((BASE / d).glob("*.java")):
            if f.name == "package-info.java":
                continue
            src = f.read_text(encoding="utf-8")
            out = src
            for old, new in REWRITES:
                out = out.replace(old, new)
            if out != src:
                with open(f, "w", encoding="utf-8", newline="") as fh:
                    fh.write(out)
                print(f"[rewrite] {d}/{f.name}")
            else:
                print(f"[skip   ] {d}/{f.name}")


if __name__ == "__main__":
    main()
