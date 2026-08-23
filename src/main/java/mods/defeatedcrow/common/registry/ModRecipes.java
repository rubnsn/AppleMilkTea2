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

    // Serializers - dummy implementations until each recipe class provides its own Serializer.
    // 1.20.1 uses fromJson/fromNetwork/toNetwork; MapCodec is 1.20.5+.
    public static final RegistryObject<RecipeSerializer<?>> TEA_SERIALIZER = RECIPE_SERIALIZERS.register("tea", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> ICE_SERIALIZER = RECIPE_SERIALIZERS.register("ice", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> PAN_SERIALIZER = RECIPE_SERIALIZERS.register("pan", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> PLATE_SERIALIZER = RECIPE_SERIALIZERS.register("plate", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> PROCESSOR_SERIALIZER = RECIPE_SERIALIZERS.register("processor", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> ADV_PROCESSOR_SERIALIZER = RECIPE_SERIALIZERS.register("adv_processor", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> EVAPORATOR_SERIALIZER = RECIPE_SERIALIZERS.register("evaporator", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> BREWING_SERIALIZER = RECIPE_SERIALIZERS.register("brewing", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> FONDUE_SERIALIZER = RECIPE_SERIALIZERS.register("fondue", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> CHOCOLATE_SERIALIZER = RECIPE_SERIALIZERS.register("chocolate", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> CHARGE_SERIALIZER = RECIPE_SERIALIZERS.register("charge", DummySerializer::new);

    public static void register(IEventBus bus) {
        RECIPE_TYPES.register(bus);
        RECIPE_SERIALIZERS.register(bus);
    }

    /**
     * Temporary dummy serializer - returns null for now.
     * Will be replaced per-recipe with proper fromJson/fromNetwork logic.
     * Keeping it here makes ModRecipes compile on 1.20.1 Forge 47.
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
