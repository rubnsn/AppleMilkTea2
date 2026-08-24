package mods.defeatedcrow.recipe.base;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * 1.20.1 common base for 11 AMT custom recipes.
 * P0-4 design: keep Forge 47 fromJson/fromNetwork/toNetwork (no MapCodec).
 * Provides helpers for Ingredient / ItemStack / FluidStack JSON &amp; network IO
 * and default Recipe boilerplate (id / canCraft / isSpecial).
 * Each concrete recipe only defines its own fields + Serializer.
 */
public abstract class AMTRecipeBase implements Recipe<Container> {

    protected final ResourceLocation id;

    protected AMTRecipeBase(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public boolean canCraftInDimensions(int w, int h) {
        return true;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    // ---- JSON helpers ----

    public static Ingredient ingredientFromJson(JsonObject json, String member) {
        if (!json.has(member)) return Ingredient.EMPTY;
        return Ingredient.fromJson(json.get(member));
    }

    public static NonNullList<Ingredient> ingredientsFromJson(JsonObject json) {
        NonNullList<Ingredient> list = NonNullList.create();
        if (json.has("ingredients")) {
            var arr = GsonHelper.getAsJsonArray(json, "ingredients");
            for (var el : arr) {
                list.add(Ingredient.fromJson(el, false));
            }
        } else if (json.has("ingredient")) {
            list.add(Ingredient.fromJson(json.get("ingredient"), false));
        }
        return list;
    }

    public static ItemStack resultFromJson(JsonObject json) {
        if (!json.has("result")) return ItemStack.EMPTY;
        JsonObject o = GsonHelper.getAsJsonObject(json, "result");
        return ShapedRecipe.itemStackFromJson(o);
    }

    public static ItemStack stackFromJson(JsonObject json, String member) {
        if (!json.has(member)) return ItemStack.EMPTY;
        JsonObject o = GsonHelper.getAsJsonObject(json, member);
        return ShapedRecipe.itemStackFromJson(o);
    }

    public static FluidStack fluidFromJson(JsonObject json, String member) {
        if (!json.has(member)) return FluidStack.EMPTY;
        JsonObject o = GsonHelper.getAsJsonObject(json, member);
        String fluidName = GsonHelper.getAsString(o, "fluid");
        int amount = GsonHelper.getAsInt(o, "amount", 1000);
        var fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidName));
        if (fluid == null) return FluidStack.EMPTY;
        return new FluidStack(fluid, amount);
    }

    public static int intFromJson(JsonObject json, String member, int def) {
        return GsonHelper.getAsInt(json, member, def);
    }

    public static boolean boolFromJson(JsonObject json, String member, boolean def) {
        return GsonHelper.getAsBoolean(json, member, def);
    }

    public static float floatFromJson(JsonObject json, String member, float def) {
        return GsonHelper.getAsFloat(json, member, def);
    }

    // ---- network helpers ----

    public static void writeIngredient(FriendlyByteBuf buf, Ingredient ing) {
        ing.toNetwork(buf);
    }

    public static Ingredient readIngredient(FriendlyByteBuf buf) {
        return Ingredient.fromNetwork(buf);
    }

    public static void writeItemStack(FriendlyByteBuf buf, ItemStack stack) {
        buf.writeItem(stack);
    }

    public static ItemStack readItemStack(FriendlyByteBuf buf) {
        return buf.readItem();
    }

    public static void writeFluidStack(FriendlyByteBuf buf, FluidStack stack) {
        stack.writeToPacket(buf);
    }

    public static FluidStack readFluidStack(FriendlyByteBuf buf) {
        return FluidStack.readFromPacket(buf);
    }

    // abstract Recipe contract
    @Override
    public abstract boolean matches(Container container, Level level);

    @Override
    public abstract ItemStack assemble(Container container, RegistryAccess access);

    @Override
    public abstract ItemStack getResultItem(RegistryAccess access);

    @Override
    public abstract NonNullList<Ingredient> getIngredients();
}
