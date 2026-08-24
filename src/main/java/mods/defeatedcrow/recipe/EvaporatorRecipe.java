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
 * Evaporator: ingredient -> result with time. Fluid input is also supported via ingredient as item placeholder for now.
 * JSON: { "type":"defeatedcrow:evaporator", "ingredient":{...}, "result":{...}, "time":100 }
 */
public class EvaporatorRecipe extends AMTRecipeBase {
    private final Ingredient ingredient;
    private final ItemStack result;
    private final int time;

    public EvaporatorRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, int time) {
        super(id);
        this.ingredient = ingredient;
        this.result = result;
        this.time = time;
    }
    public Ingredient getIngredient() { return ingredient; }
    public ItemStack getResult() { return result; }
    public int getTime() { return time; }

    @Override public boolean matches(Container c, Level l) {
        for(int i=0;i<c.getContainerSize();i++) if(ingredient.test(c.getItem(i))) return true;
        return false;
    }
    @Override public ItemStack assemble(Container c, RegistryAccess a) { return result.copy(); }
    @Override public ItemStack getResultItem(RegistryAccess a) { return result.copy(); }
    @Override public NonNullList<Ingredient> getIngredients() { NonNullList<Ingredient> ll=NonNullList.create(); ll.add(ingredient); return ll; }
    @Override public RecipeSerializer<?> getSerializer() { return ModRecipes.EVAPORATOR_SERIALIZER.get(); }
    @Override public RecipeType<?> getType() { return ModRecipes.EVAPORATOR_TYPE.get(); }

    public static class Serializer implements RecipeSerializer<EvaporatorRecipe> {
        @Override public EvaporatorRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient ing=AMTRecipeBase.ingredientFromJson(json, "ingredient");
            ItemStack res=AMTRecipeBase.resultFromJson(json);
            int t=AMTRecipeBase.intFromJson(json, "time", 100);
            if(json.has("cookingTime")) t=AMTRecipeBase.intFromJson(json, "cookingTime", t);
            return new EvaporatorRecipe(id, ing, res, t);
        }
        @Override public EvaporatorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient ing=AMTRecipeBase.readIngredient(buf);
            ItemStack res=AMTRecipeBase.readItemStack(buf);
            int t=buf.readVarInt();
            return new EvaporatorRecipe(id, ing, res, t);
        }
        @Override public void toNetwork(FriendlyByteBuf buf, EvaporatorRecipe r) {
            AMTRecipeBase.writeIngredient(buf, r.ingredient);
            AMTRecipeBase.writeItemStack(buf, r.result);
            buf.writeVarInt(r.time);
        }
    }
}
