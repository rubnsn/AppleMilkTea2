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
 * IceMaker: ingredient -> result + container returned.
 * JSON: { "type":"defeatedcrow:ice", "ingredient":{...}, "result":{...}, "container":{...} }
 */
public class IceRecipe extends AMTRecipeBase {

    private final Ingredient ingredient;
    private final ItemStack result;
    private final ItemStack container;

    public IceRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, ItemStack container) {
        super(id);
        this.ingredient = ingredient;
        this.result = result;
        this.container = container;
    }

    public Ingredient getIngredient() { return ingredient; }
    public ItemStack getResult() { return result; }
    public ItemStack getContainer() { return container; }

    @Override
    public boolean matches(Container c, Level level) {
        for (int i = 0; i < c.getContainerSize(); i++) if (ingredient.test(c.getItem(i))) return true;
        return false;
    }

    @Override
    public ItemStack assemble(Container c, RegistryAccess access) { return result.copy(); }

    @Override
    public ItemStack getResultItem(RegistryAccess access) { return result.copy(); }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> l = NonNullList.create(); l.add(ingredient); return l;
    }

    @Override
    public RecipeSerializer<?> getSerializer() { return ModRecipes.ICE_SERIALIZER.get(); }
    @Override
    public RecipeType<?> getType() { return ModRecipes.ICE_TYPE.get(); }

    public static class Serializer implements RecipeSerializer<IceRecipe> {
        @Override
        public IceRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient ing = AMTRecipeBase.ingredientFromJson(json, "ingredient");
            ItemStack res = AMTRecipeBase.resultFromJson(json);
            ItemStack cont = AMTRecipeBase.stackFromJson(json, "container");
            return new IceRecipe(id, ing, res, cont);
        }
        @Override
        public IceRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient ing = AMTRecipeBase.readIngredient(buf);
            ItemStack res = AMTRecipeBase.readItemStack(buf);
            ItemStack cont = AMTRecipeBase.readItemStack(buf);
            return new IceRecipe(id, ing, res, cont);
        }
        @Override
        public void toNetwork(FriendlyByteBuf buf, IceRecipe r) {
            AMTRecipeBase.writeIngredient(buf, r.ingredient);
            AMTRecipeBase.writeItemStack(buf, r.result);
            AMTRecipeBase.writeItemStack(buf, r.container);
        }
    }
}
