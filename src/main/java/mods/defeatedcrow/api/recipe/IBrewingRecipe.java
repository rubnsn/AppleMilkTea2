package mods.defeatedcrow.api.recipe;

import java.util.Map;

import net.minecraftforge.fluids.FluidStack;

/**
 * Brewing Barrel にレシピを追加するAPI。
 * input amount : output amount = 1 : 1
 * 1.20.1: Fluid -> FluidStack (amount in stack)
 */
public interface IBrewingRecipe {

    void registerRecipe(FluidStack input, FluidStack output);

    Map<FluidStack, FluidStack> recipeMap();

}
