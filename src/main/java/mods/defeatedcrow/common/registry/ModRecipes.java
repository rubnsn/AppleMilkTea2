package mods.defeatedcrow.common.registry;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
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
 * Uses DeferredRegister + MapCodec (future-proof for 1.20.5 StreamCodec).
 * NBT is kept (CompoundTag), DataComponents are 1.20.5+ so not used here.
 */
public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
        DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "defeatedcrow");
    public static final DeferredRegister<RecipeType<?>> TYPES =
        DeferredRegister.create(ForgeRegistries.Keys.RECIPE_TYPES, "defeatedcrow");

    // --- WT-D: RECIPE TYPES (11) ---
    public static final RegistryObject<RecipeType<?>> TEA_TYPE = TYPES.register("tea", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> ICE_TYPE = TYPES.register("ice", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> PAN_TYPE = TYPES.register("pan", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> PLATE_TYPE = TYPES.register("plate", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> PROCESSOR_TYPE = TYPES.register("processor", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> ADV_PROCESSOR_TYPE = TYPES.register("adv_processor", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> EVAPORATOR_TYPE = TYPES.register("evaporator", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> BREWING_TYPE = TYPES.register("brewing", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> FONDUE_TYPE = TYPES.register("fondue", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> CHOCOLATE_TYPE = TYPES.register("chocolate", () -> new RecipeType<>() {});
    public static final RegistryObject<RecipeType<?>> CHARGE_TYPE = TYPES.register("charge", () -> new RecipeType<>() {});

    // Serializers - dummy MapCodec/StreamCodec stubs until each recipe class provides its own Serializer.
    // Each concrete recipe (TeaRecipe, ProcessorRecipe, etc.) will replace its dummy with RecordCodecBuilder MapCodec.
    public static final RegistryObject<RecipeSerializer<?>> TEA_SERIALIZER = SERIALIZERS.register("tea", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> ICE_SERIALIZER = SERIALIZERS.register("ice", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> PAN_SERIALIZER = SERIALIZERS.register("pan", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> PLATE_SERIALIZER = SERIALIZERS.register("plate", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> PROCESSOR_SERIALIZER = SERIALIZERS.register("processor", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> ADV_PROCESSOR_SERIALIZER = SERIALIZERS.register("adv_processor", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> EVAPORATOR_SERIALIZER = SERIALIZERS.register("evaporator", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> BREWING_SERIALIZER = SERIALIZERS.register("brewing", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> FONDUE_SERIALIZER = SERIALIZERS.register("fondue", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> CHOCOLATE_SERIALIZER = SERIALIZERS.register("chocolate", DummySerializer::new);
    public static final RegistryObject<RecipeSerializer<?>> CHARGE_SERIALIZER = SERIALIZERS.register("charge", DummySerializer::new);

    public static void register(IEventBus bus) {
        TYPES.register(bus);
        SERIALIZERS.register(bus);
    }

    /**
     * Temporary dummy serializer - returns null codec/streamCodec.
     * Will be replaced per-recipe with MapCodec<Recipe> via RecordCodecBuilder + Ingredient.CODEC + ItemStack.CODEC.
     * Keeping it here makes ModRecipes compile on 1.20.1 (MapCodec) and forward-compatible with 1.20.5 StreamCodec.
     */
    @SuppressWarnings("rawtypes")
    public static class DummySerializer implements RecipeSerializer<Recipe<?>> {

        // 1.20.1 Forge 47 uses MapCodec; 1.19.x used Codec. Provide both signatures without @Override to stay compatible.
        public MapCodec<Recipe<?>> codec() {
            return null;
        }

        public StreamCodec<RegistryFriendlyByteBuf, Recipe<?>> streamCodec() {
            return null;
        }

        // Legacy fallback for older mappings (ignored if MapCodec is used)
        public com.mojang.serialization.Codec<Recipe<?>> codecLegacy() {
            return null;
        }
    }

    private ModRecipes() {}
}
