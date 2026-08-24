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
import mods.defeatedcrow.common.registry.ModRecipes;
import mods.defeatedcrow.recipe.base.AMTRecipeBase;

/**
 * TeppanII: ingredient -> result (plate)
 * JSON: { "type":"defeatedcrow:plate", "ingredient":{...}, "result":{...} }
 */
public class PlateRecipe extends AMTRecipeBase {
    private final Ingredient ingredient;
    private final ItemStack result;

    public PlateRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result) {
        super(id);
        this.ingredient = ingredient;
        this.result = result;
    }
    public Ingredient getIngredient() { return ingredient; }
    public ItemStack getResult() { return result; }

    @Override public boolean matches(Container c, Level l) {
        for (int i = 0; i < c.getContainerSize(); i++) if (ingredient.test(c.getItem(i))) return true;
        return false;
    }
    @Override public ItemStack assemble(Container c, RegistryAccess a) { return result.copy(); }
    @Override public ItemStack getResultItem(RegistryAccess a) { return result.copy(); }
    @Override public NonNullList<Ingredient> getIngredients() { NonNullList<Ingredient> ll = NonNullList.create(); ll.add(ingredient); return ll; }
    @Override public RecipeSerializer<?> getSerializer() { return ModRecipes.PLATE_SERIALIZER.get(); }
    @Override public RecipeType<?> getType() { return ModRecipes.PLATE_TYPE.get(); }

    public static class Serializer implements RecipeSerializer<PlateRecipe> {
        @Override public PlateRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient ing = AMTRecipeBase.ingredientFromJson(json, "ingredient");
            ItemStack res = AMTRecipeBase.resultFromJson(json);
            return new PlateRecipe(id, ing, res);
        }
        @Override public PlateRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient ing = AMTRecipeBase.readIngredient(buf);
            ItemStack res = AMTRecipeBase.readItemStack(buf);
            return new PlateRecipe(id, ing, res);
        }
        @Override public void toNetwork(FriendlyByteBuf buf, PlateRecipe r) {
            AMTRecipeBase.writeIngredient(buf, r.ingredient);
            AMTRecipeBase.writeItemStack(buf, r.result);
        }
    }
}
