package mods.defeatedcrow.recipe;

import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.fluids.FluidStack;

import mods.defeatedcrow.api.recipe.IBrewingRecipe;

public class BrewingRecipe implements IBrewingRecipe {

    public final static BrewingRecipe instance = new BrewingRecipe();

    BrewingRecipe() {}

    public static Map<FluidStack, FluidStack> recipe = new HashMap<FluidStack, FluidStack>();

    @Override
    public void registerRecipe(FluidStack input, FluidStack output) {
        if (input != null && !input.isEmpty()) {
            if (output != null && !output.isEmpty()) {
                recipe.put(input, output);
            }
        }
    }

    @Override
    public Map<FluidStack, FluidStack> recipeMap() {
        return this.recipe;
    }

}
