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
 * Food Processor: ingredients[] -> result (+ secondary chance)
 * JSON: { "type":"defeatedcrow:processor", "ingredients":[{...}], "result":{...}, "secondary":{...}, "secondaryChance":0.1, "tier":0, "isFood":false }
 */
public class ProcessorRecipe extends AMTRecipeBase {
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack result;
    private final ItemStack secondary;
    private final float secondaryChance;
    private final int tier;
    private final boolean isFood;

    public ProcessorRecipe(ResourceLocation id, NonNullList<Ingredient> ingredients, ItemStack result, ItemStack secondary, float secondaryChance, int tier, boolean isFood) {
        super(id);
        this.ingredients = ingredients;
        this.result = result;
        this.secondary = secondary;
        this.secondaryChance = secondaryChance;
        this.tier = tier;
        this.isFood = isFood;
    }
    public NonNullList<Ingredient> getIngredientsList() { return ingredients; }
    public ItemStack getResultStack() { return result; }
    public ItemStack getSecondary() { return secondary; }
    public float getSecondaryChance() { return secondaryChance; }
    public int getTier() { return tier; }
    public boolean isFood() { return isFood; }

    @Override public boolean matches(Container c, Level l) {
        // simple: all ingredients must be found in container (unordered)
        // if single ingredient, check any slot
        if (ingredients.isEmpty()) return false;
        // copy needed counts
        java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
        for (int i = 0; i < c.getContainerSize(); i++) {
            ItemStack s = c.getItem(i);
            if (!s.isEmpty()) inputs.add(s);
        }
        // For each ingredient, need at least one input that matches and not reused?
        // Simplified: check each ingredient finds a distinct input slot
        boolean[] used = new boolean[inputs.size()];
        for (Ingredient ing : ingredients) {
            boolean found = false;
            for (int i = 0; i < inputs.size(); i++) if (!used[i] && ing.test(inputs.get(i))) { used[i]=true; found=true; break; }
            if (!found) return false;
        }
        return true;
    }
    @Override public ItemStack assemble(Container c, RegistryAccess a) { return result.copy(); }
    @Override public ItemStack getResultItem(RegistryAccess a) { return result.copy(); }
    @Override public NonNullList<Ingredient> getIngredients() { return ingredients; }
    @Override public RecipeSerializer<?> getSerializer() { return ModRecipes.PROCESSOR_SERIALIZER.get(); }
    @Override public RecipeType<?> getType() { return ModRecipes.PROCESSOR_TYPE.get(); }

    public static class Serializer implements RecipeSerializer<ProcessorRecipe> {
        @Override public ProcessorRecipe fromJson(ResourceLocation id, JsonObject json) {
            NonNullList<Ingredient> ings = AMTRecipeBase.ingredientsFromJson(json);
            ItemStack res = AMTRecipeBase.resultFromJson(json);
            ItemStack sec = AMTRecipeBase.stackFromJson(json, "secondary");
            float chance = AMTRecipeBase.floatFromJson(json, "secondaryChance", 0.0f);
            int tier = AMTRecipeBase.intFromJson(json, "tier", 0);
            boolean food = AMTRecipeBase.boolFromJson(json, "isFood", false);
            return new ProcessorRecipe(id, ings, res, sec, chance, tier, food);
        }
        @Override public ProcessorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            int n = buf.readVarInt();
            NonNullList<Ingredient> ings = NonNullList.withSize(n, Ingredient.EMPTY);
            for (int i=0;i<n;i++) ings.set(i, AMTRecipeBase.readIngredient(buf));
            ItemStack res = AMTRecipeBase.readItemStack(buf);
            ItemStack sec = AMTRecipeBase.readItemStack(buf);
            float chance = buf.readFloat();
            int tier = buf.readVarInt();
            boolean food = buf.readBoolean();
            return new ProcessorRecipe(id, ings, res, sec, chance, tier, food);
        }
        @Override public void toNetwork(FriendlyByteBuf buf, ProcessorRecipe r) {
            buf.writeVarInt(r.ingredients.size());
            for (Ingredient ing : r.ingredients) AMTRecipeBase.writeIngredient(buf, ing);
            AMTRecipeBase.writeItemStack(buf, r.result);
            AMTRecipeBase.writeItemStack(buf, r.secondary);
            buf.writeFloat(r.secondaryChance);
            buf.writeVarInt(r.tier);
            buf.writeBoolean(r.isFood);
        }
    }
}
