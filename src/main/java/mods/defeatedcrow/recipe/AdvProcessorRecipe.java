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
 * AdvProcessor (JawCrusher): ingredients[] -> result with tier
 * JSON: { "type":"defeatedcrow:adv_processor", "ingredients":[{...}], "result":{...}, "tier":1 }
 */
public class AdvProcessorRecipe extends AMTRecipeBase {
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack result;
    private final int tier;

    public AdvProcessorRecipe(ResourceLocation id, NonNullList<Ingredient> ingredients, ItemStack result, int tier) {
        super(id);
        this.ingredients = ingredients;
        this.result = result;
        this.tier = tier;
    }
    public NonNullList<Ingredient> getIngredientsList() { return ingredients; }
    public ItemStack getResultStack() { return result; }
    public int getTier() { return tier; }

    @Override public boolean matches(Container c, Level l) {
        if (ingredients.isEmpty()) return false;
        java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
        for (int i=0;i<c.getContainerSize();i++) { var s=c.getItem(i); if(!s.isEmpty()) inputs.add(s); }
        boolean[] used=new boolean[inputs.size()];
        for (Ingredient ing:ingredients){
            boolean found=false;
            for(int i=0;i<inputs.size();i++) if(!used[i] && ing.test(inputs.get(i))) {used[i]=true; found=true; break;}
            if(!found) return false;
        }
        return true;
    }
    @Override public ItemStack assemble(Container c, RegistryAccess a) { return result.copy(); }
    @Override public ItemStack getResultItem(RegistryAccess a) { return result.copy(); }
    @Override public NonNullList<Ingredient> getIngredients() { return ingredients; }
    @Override public RecipeSerializer<?> getSerializer() { return ModRecipes.ADV_PROCESSOR_SERIALIZER.get(); }
    @Override public RecipeType<?> getType() { return ModRecipes.ADV_PROCESSOR_TYPE.get(); }

    public static class Serializer implements RecipeSerializer<AdvProcessorRecipe> {
        @Override public AdvProcessorRecipe fromJson(ResourceLocation id, JsonObject json) {
            NonNullList<Ingredient> ings = AMTRecipeBase.ingredientsFromJson(json);
            ItemStack res = AMTRecipeBase.resultFromJson(json);
            int tier = AMTRecipeBase.intFromJson(json, "tier", 0);
            return new AdvProcessorRecipe(id, ings, res, tier);
        }
        @Override public AdvProcessorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            int n=buf.readVarInt();
            NonNullList<Ingredient> ings= NonNullList.withSize(n, Ingredient.EMPTY);
            for(int i=0;i<n;i++) ings.set(i, AMTRecipeBase.readIngredient(buf));
            ItemStack res=AMTRecipeBase.readItemStack(buf);
            int tier=buf.readVarInt();
            return new AdvProcessorRecipe(id, ings, res, tier);
        }
        @Override public void toNetwork(FriendlyByteBuf buf, AdvProcessorRecipe r) {
            buf.writeVarInt(r.ingredients.size());
            for(Ingredient ing:r.ingredients) AMTRecipeBase.writeIngredient(buf, ing);
            AMTRecipeBase.writeItemStack(buf, r.result);
            buf.writeVarInt(r.tier);
        }
    }
}
