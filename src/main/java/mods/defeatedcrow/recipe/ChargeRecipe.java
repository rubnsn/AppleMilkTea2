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
 * Charge: ingredient -> amount (battery charging)
 * JSON: { "type":"defeatedcrow:charge", "ingredient":{...}, "amount":500 }
 */
public class ChargeRecipe extends AMTRecipeBase {
    private final Ingredient ingredient;
    private final int amount;
    public ChargeRecipe(ResourceLocation id, Ingredient ingredient, int amount) { super(id); this.ingredient=ingredient; this.amount=amount; }
    public Ingredient getIngredient(){return ingredient;}
    public int getAmount(){return amount;}
    @Override public boolean matches(Container c, Level l){ for(int i=0;i<c.getContainerSize();i++) if(ingredient.test(c.getItem(i))) return true; return false; }
    @Override public ItemStack assemble(Container c, RegistryAccess a){ return ItemStack.EMPTY; }
    @Override public ItemStack getResultItem(RegistryAccess a){ return ItemStack.EMPTY; }
    @Override public NonNullList<Ingredient> getIngredients(){ NonNullList<Ingredient> ll=NonNullList.create(); ll.add(ingredient); return ll; }
    @Override public RecipeSerializer<?> getSerializer(){ return ModRecipes.CHARGE_SERIALIZER.get(); }
    @Override public RecipeType<?> getType(){ return ModRecipes.CHARGE_TYPE.get(); }
    public static class Serializer implements RecipeSerializer<ChargeRecipe>{
        @Override public ChargeRecipe fromJson(ResourceLocation id, JsonObject json){
            Ingredient ing=AMTRecipeBase.ingredientFromJson(json, "ingredient");
            int a=AMTRecipeBase.intFromJson(json, "amount", 0);
            return new ChargeRecipe(id, ing, a);
        }
        @Override public ChargeRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf){
            Ingredient ing=AMTRecipeBase.readIngredient(buf);
            int a=buf.readVarInt();
            return new ChargeRecipe(id, ing, a);
        }
        @Override public void toNetwork(FriendlyByteBuf buf, ChargeRecipe r){
            AMTRecipeBase.writeIngredient(buf, r.ingredient);
            buf.writeVarInt(r.amount);
        }
    }
}
