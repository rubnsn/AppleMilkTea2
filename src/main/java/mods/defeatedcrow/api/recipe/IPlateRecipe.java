package mods.defeatedcrow.api.recipe;

import net.minecraft.world.item.ItemStack;

public interface IPlateRecipe {

    ItemStack getInput();

    ItemStack getOutput();

    int cookingTime();

    boolean useOvenRecipe();

}
