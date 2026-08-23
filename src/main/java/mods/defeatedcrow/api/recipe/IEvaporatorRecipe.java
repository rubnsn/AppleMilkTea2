package mods.defeatedcrow.api.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.FluidStack;

public interface IEvaporatorRecipe {

    ItemStack getInput();

    ItemStack getOutput();

    FluidStack getSecondary();

    boolean returnContainer();

}
