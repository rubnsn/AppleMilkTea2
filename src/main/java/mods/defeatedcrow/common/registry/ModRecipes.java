package mods.defeatedcrow.common.registry;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 1.20.1 Recipe registry - WT-D owns all 11 custom types.
 * See doc/recipes/migration-guide.md:34 and plan-wt-d.md:4-2.
 * Uses DeferredRegister for RecipeSerializer and RecipeType.
 * 1.20.1 Forge 47 still uses fromJson/fromNetwork/toNetwork (not MapCodec/StreamCodec).
 * NBT is kept (CompoundTag), DataComponents are 1.20.5+ so not used here.
 * <p>
 * P0-4 design decision (WT-E): keep fromJson/fromNetwork/toNetwork for all 11 types.
 * MapCodec/StreamCodec is 1.20.5+ (NeoForge) and not available in Forge 47.3.
 * P1 will introduce abstract parent {@code recipe/base/AMTRecipeBase} that implements
 * {@code Recipe<?>} with common Ingredient List + ItemStack output + ResourceLocation id,
 * plus a shared abstract Serializer that handles JSON {@code type} field and
 * FriendlyByteBuf read/write of Ingredient/ItemStack. Each concrete recipe (Tea/Ice/Pan etc.)
 * extends the base and only overrides {@code getType()}/{@code getSerializer()} and
 * recipe-specific fields (e.g. Tea has milk flag, Pan has tex/display, Evaporator has fluid).
 * This unifies 11 DummySerializers into one tested abstract path without MapCodec.
 * See test/IMPLEMENTATION_PLAN_WT-E.md P1-1/P0-4 and plan.md 7.2 T1/T5.
 * </p>
 */
public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
        DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "defeatedcrow");
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
        DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, "defeatedcrow");

    // --- WT-D: RECIPE TYPES (11) ---
    public static final RegistryObject<RecipeType<?>> TEA_TYPE = RECIPE_TYPES.register("tea", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> ICE_TYPE = RECIPE_TYPES.register("ice", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> PAN_TYPE = RECIPE_TYPES.register("pan", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> PLATE_TYPE = RECIPE_TYPES.register("plate", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> PROCESSOR_TYPE = RECIPE_TYPES.register("processor", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> ADV_PROCESSOR_TYPE = RECIPE_TYPES.register("adv_processor", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> EVAPORATOR_TYPE = RECIPE_TYPES.register("evaporator", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> BREWING_TYPE = RECIPE_TYPES.register("brewing", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> FONDUE_TYPE = RECIPE_TYPES.register("fondue", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> CHOCOLATE_TYPE = RECIPE_TYPES.register("chocolate", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> CHARGE_TYPE = RECIPE_TYPES.register("charge", () -> new RecipeType<>() {});

    // Serializers - P1 real implementations (WT-E)
    // 1.20.1 uses fromJson/fromNetwork/toNetwork; MapCodec is 1.20.5+.
    public static final RegistryObject<RecipeSerializer<?>> TEA_SERIALIZER = RECIPE_SERIALIZERS.register("tea", mods.defeatedcrow.recipe.TeaRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> ICE_SERIALIZER = RECIPE_SERIALIZERS.register("ice", mods.defeatedcrow.recipe.IceRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> PAN_SERIALIZER = RECIPE_SERIALIZERS.register("pan", mods.defeatedcrow.recipe.PanRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> PLATE_SERIALIZER = RECIPE_SERIALIZERS.register("plate", mods.defeatedcrow.recipe.PlateRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> PROCESSOR_SERIALIZER = RECIPE_SERIALIZERS.register("processor", mods.defeatedcrow.recipe.ProcessorRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> ADV_PROCESSOR_SERIALIZER = RECIPE_SERIALIZERS.register("adv_processor", mods.defeatedcrow.recipe.AdvProcessorRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> EVAPORATOR_SERIALIZER = RECIPE_SERIALIZERS.register("evaporator", mods.defeatedcrow.recipe.EvaporatorRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> BREWING_SERIALIZER = RECIPE_SERIALIZERS.register("brewing", mods.defeatedcrow.recipe.BrewingRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> FONDUE_SERIALIZER = RECIPE_SERIALIZERS.register("fondue", mods.defeatedcrow.recipe.FondueRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> CHOCOLATE_SERIALIZER = RECIPE_SERIALIZERS.register("chocolate", mods.defeatedcrow.recipe.ChocolateRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<?>> CHARGE_SERIALIZER = RECIPE_SERIALIZERS.register("charge", mods.defeatedcrow.recipe.ChargeRecipe.Serializer::new);

    public static void register(IEventBus bus) {
        RECIPE_TYPES.register(bus);
        RECIPE_SERIALIZERS.register(bus);
    }

    /**
     * Temporary dummy serializer - returns null for now.
     * Will be replaced per-recipe with proper fromJson/fromNetwork logic.
     * Keeping it here makes ModRecipes compile on 1.20.1 Forge 47.
     * P0-4 note: do NOT switch to MapCodec here; Forge 47 RecipeSerializer API is
     * fromJson(JsonObject)/fromNetwork(FriendlyByteBuf)/toNetwork(...).
     * The future AMTRecipeBase.Serializer will implement those three and is the only
     * place that needs to be changed when porting to 1.20.5+.
     */
    @SuppressWarnings("rawtypes")
    public static class DummySerializer implements RecipeSerializer<Recipe<?>> {

        @Override
        public Recipe<?> fromJson(ResourceLocation recipeId, JsonObject json) {
            return null;
        }

        @Override
        public Recipe<?> fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            return null;
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, Recipe<?> recipe) {
        }
    }

    private ModRecipes() {}
}
