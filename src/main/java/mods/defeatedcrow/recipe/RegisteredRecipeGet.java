package mods.defeatedcrow.recipe;

import net.minecraft.world.item.ItemStack;
import java.util.HashMap;
import java.util.Map;

/**
 * Stub for 1.20.1 - original used ItemStack(Block,int,int) with damage values; now damage is NBT
 */
public class RegisteredRecipeGet {
    public static final Map<ItemStack, ItemStack> panRecipeList = new HashMap<>();
    public static final Map<ItemStack, ItemStack> teaRecipeList = new HashMap<>();
    static {
        // stub entries removed - use datapack per plan.md WT-D
    }
    public static Map<ItemStack, ItemStack> getPanList() { return panRecipeList; }
}
