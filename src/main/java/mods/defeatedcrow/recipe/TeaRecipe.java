package mods.defeatedcrow.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import mods.defeatedcrow.common.registry.ModRecipes;
import mods.defeatedcrow.recipe.base.AMTRecipeBase;

/**
 * TeaMaker recipe: leafTea -> filled_cup with milk variant textures.
 * JSON: { "type":"defeatedcrow:tea", "ingredient":{item/tag}, "result":{item,count}, "canMilk":bool, "textures":[s0,s1] }
 */
public class TeaRecipe extends AMTRecipeBase {

    private final Ingredient ingredient;
    private final ItemStack result;
    private final boolean canMilk;
    private final List<String> textures;

    public TeaRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, boolean canMilk, List<String> textures) {
        super(id);
        this.ingredient = ingredient;
        this.result = result;
        this.canMilk = canMilk;
        this.textures = textures;
    }

    public Ingredient getIngredient() { return ingredient; }
    public ItemStack getResult() { return result; }
    public boolean canMilk() { return canMilk; }
    public List<String> getTextures() { return textures; }

    @Override
    public boolean matches(Container container, Level level) {
        if (container.getContainerSize() == 0) return false;
        // check any slot (TeaMaker has single input)
        for (int i = 0; i < container.getContainerSize(); i++) {
            if (ingredient.test(container.getItem(i))) return true;
        }
        return false;
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess access) {
        return result.copy();
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return result.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(ingredient);
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.TEA_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.TEA_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<TeaRecipe> {
        @Override
        public TeaRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient ing = AMTRecipeBase.ingredientFromJson(json, "ingredient");
            ItemStack res = AMTRecipeBase.resultFromJson(json);
            boolean milk = AMTRecipeBase.boolFromJson(json, "canMilk", false);
            List<String> tex = new ArrayList<>();
            if (json.has("textures")) {
                JsonArray arr = GsonHelper.getAsJsonArray(json, "textures");
                for (var el : arr) tex.add(el.getAsString());
            }
            return new TeaRecipe(id, ing, res, milk, tex);
        }

        @Override
        public TeaRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient ing = AMTRecipeBase.readIngredient(buf);
            ItemStack res = AMTRecipeBase.readItemStack(buf);
            boolean milk = buf.readBoolean();
            int n = buf.readVarInt();
            List<String> tex = new ArrayList<>();
            for (int i = 0; i < n; i++) tex.add(buf.readUtf());
            return new TeaRecipe(id, ing, res, milk, tex);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, TeaRecipe recipe) {
            AMTRecipeBase.writeIngredient(buf, recipe.ingredient);
            AMTRecipeBase.writeItemStack(buf, recipe.result);
            buf.writeBoolean(recipe.canMilk);
            buf.writeVarInt(recipe.textures.size());
            for (String s : recipe.textures) buf.writeUtf(s);
        }
    }
}
