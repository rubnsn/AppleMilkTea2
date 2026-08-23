package mods.defeatedcrow.recipe;

import net.minecraft.world.item.ItemStack;
import java.util.HashMap;
import java.util.Map;

/**
 * Stub for 1.20.1 - original used old dict and old recipe system
 * Now replaced by TagKey + RecipeType per doc/oredict-to-tagkey.md
 */
public class OreCrushRecipe {
    private static final Map<ItemStack, ItemStack> map = new HashMap<>();
    public static void register(ItemStack in, ItemStack out) { map.put(in, out); }
    public static ItemStack getOutput(ItemStack in) { return map.get(in); }
    public static Map<ItemStack, ItemStack> getMap() { return map; }
}
