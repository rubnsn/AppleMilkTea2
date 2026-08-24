package mods.defeatedcrow.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import mods.defeatedcrow.common.registry.ModRecipes;
import mods.defeatedcrow.recipe.base.AMTRecipeBase;

/**
 * Brewing barrel: fluid -> fluid (large bottle aging)
 * JSON: { "type":"defeatedcrow:brewing", "input":{"fluid":"defeatedcrow:shothu_young","amount":1000}, "output":{"fluid":"defeatedcrow:shothu","amount":1000} }
 */
public class BrewingRecipe extends AMTRecipeBase {
    private final FluidStack input;
    private final FluidStack output;

    public BrewingRecipe(ResourceLocation id, FluidStack input, FluidStack output) {
        super(id);
        this.input = input;
        this.output = output;
    }
    public FluidStack getInput() { return input; }
    public FluidStack getOutput() { return output; }

    @Override public boolean matches(Container c, Level l) {
        // not used via Container; tile will query via getAllRecipes and compare FluidStack directly
        return false;
    }
    @Override public ItemStack assemble(Container c, RegistryAccess a) { return ItemStack.EMPTY; }
    @Override public ItemStack getResultItem(RegistryAccess a) { return ItemStack.EMPTY; }
    @Override public NonNullList<Ingredient> getIngredients() { return NonNullList.create(); }
    @Override public RecipeSerializer<?> getSerializer() { return ModRecipes.BREWING_SERIALIZER.get(); }
    @Override public RecipeType<?> getType() { return ModRecipes.BREWING_TYPE.get(); }

    public boolean matchesFluid(FluidStack stack) {
        if (stack == null || stack.isEmpty() || input.isEmpty()) return false;
        return stack.getFluid() == input.getFluid() && stack.getAmount() >= input.getAmount();
    }

    public static class Serializer implements RecipeSerializer<BrewingRecipe> {
        @Override public BrewingRecipe fromJson(ResourceLocation id, JsonObject json) {
            FluidStack in = AMTRecipeBase.fluidFromJson(json, "input");
            FluidStack out = AMTRecipeBase.fluidFromJson(json, "output");
            return new BrewingRecipe(id, in, out);
        }
        @Override public BrewingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            FluidStack in = AMTRecipeBase.readFluidStack(buf);
            FluidStack out = AMTRecipeBase.readFluidStack(buf);
            return new BrewingRecipe(id, in, out);
        }
        @Override public void toNetwork(FriendlyByteBuf buf, BrewingRecipe r) {
            AMTRecipeBase.writeFluidStack(buf, r.input);
            AMTRecipeBase.writeFluidStack(buf, r.output);
        }
    }
}
