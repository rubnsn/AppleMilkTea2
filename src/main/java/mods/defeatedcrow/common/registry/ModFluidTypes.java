package mods.defeatedcrow.common.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, "defeatedcrow");

    private static FluidType createOilType(FluidType.Properties props, String stillName) {
        ResourceLocation still = new ResourceLocation("defeatedcrow", "block/fluid/" + stillName + "_still");
        ResourceLocation flowing = new ResourceLocation("defeatedcrow", "block/fluid/" + stillName + "_still");
        return new FluidType(props) {
            @Override
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    @Override public ResourceLocation getStillTexture() { return still; }
                    @Override public ResourceLocation getFlowingTexture() { return flowing; }
                });
            }
        };
    }

    private static FluidType createBrewingType(String stillName) {
        FluidType.Properties props = FluidType.Properties.create().density(1000).viscosity(1000).canExtinguish(false)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL);
        ResourceLocation still = new ResourceLocation("defeatedcrow", "block/fluid/" + stillName + "_still");
        ResourceLocation flowing = new ResourceLocation("defeatedcrow", "block/fluid/" + stillName + "_still");
        return new FluidType(props) {
            @Override
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    @Override public ResourceLocation getStillTexture() { return still; }
                    @Override public ResourceLocation getFlowingTexture() { return flowing; }
                });
            }
        };
    }

    public static final RegistryObject<FluidType> VEGITABLE_OIL = FLUID_TYPES.register("vegitable_oil",
        () -> createOilType(FluidType.Properties.create().density(800).viscosity(1500).canExtinguish(true)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL).sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY), "oil"));
    public static final RegistryObject<FluidType> CAMELLIA_OIL = FLUID_TYPES.register("camellia_oil",
        () -> createOilType(FluidType.Properties.create().density(800).viscosity(1500).canExtinguish(true).canConvertToSource(false)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL), "camelliaoil"));

    // Brewing 16: young + aged share same still per base type.
    public static final RegistryObject<FluidType> SAKE_YOUNG = FLUID_TYPES.register("sake_young", () -> createBrewingType("sake"));
    public static final RegistryObject<FluidType> SAKE = FLUID_TYPES.register("sake", () -> createBrewingType("sake"));
    public static final RegistryObject<FluidType> BEER_YOUNG = FLUID_TYPES.register("beer_young", () -> createBrewingType("beer"));
    public static final RegistryObject<FluidType> BEER = FLUID_TYPES.register("beer", () -> createBrewingType("beer"));
    public static final RegistryObject<FluidType> WINE_YOUNG = FLUID_TYPES.register("wine_young", () -> createBrewingType("wine"));
    public static final RegistryObject<FluidType> WINE = FLUID_TYPES.register("wine", () -> createBrewingType("wine"));
    public static final RegistryObject<FluidType> SHOTHU_YOUNG = FLUID_TYPES.register("shothu_young", () -> createBrewingType("shothu"));
    public static final RegistryObject<FluidType> SHOTHU = FLUID_TYPES.register("shothu", () -> createBrewingType("shothu"));
    public static final RegistryObject<FluidType> WHISKEY_YOUNG = FLUID_TYPES.register("whiskey_young", () -> createBrewingType("whiskey"));
    public static final RegistryObject<FluidType> WHISKEY = FLUID_TYPES.register("whiskey", () -> createBrewingType("whiskey"));
    public static final RegistryObject<FluidType> BRANDY_YOUNG = FLUID_TYPES.register("brandy_young", () -> createBrewingType("brandy"));
    public static final RegistryObject<FluidType> BRANDY = FLUID_TYPES.register("brandy", () -> createBrewingType("brandy"));
    public static final RegistryObject<FluidType> RUM_YOUNG = FLUID_TYPES.register("rum_young", () -> createBrewingType("rum"));
    public static final RegistryObject<FluidType> RUM = FLUID_TYPES.register("rum", () -> createBrewingType("rum"));
    public static final RegistryObject<FluidType> VODKA_YOUNG = FLUID_TYPES.register("vodka_young", () -> createBrewingType("vodka"));
    public static final RegistryObject<FluidType> VODKA = FLUID_TYPES.register("vodka", () -> createBrewingType("vodka"));
    private ModFluidTypes() {}
}
