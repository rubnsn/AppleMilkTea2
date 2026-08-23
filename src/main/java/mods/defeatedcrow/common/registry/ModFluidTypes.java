package mods.defeatedcrow.common.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, "defeatedcrow");
    public static final RegistryObject<FluidType> VEGITABLE_OIL = FLUID_TYPES.register("vegitable_oil",
        () -> new FluidType(FluidType.Properties.create().density(800).viscosity(1500).canExtinguish(true)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL).sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)));
    public static final RegistryObject<FluidType> CAMELLIA_OIL = FLUID_TYPES.register("camellia_oil",
        () -> new FluidType(FluidType.Properties.create().density(800).viscosity(1500).canExtinguish(true).canConvertToSource(false)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)));
    private static FluidType brewingType() {
        return new FluidType(FluidType.Properties.create().density(1000).viscosity(1000).canExtinguish(false)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL));
    }
    public static final RegistryObject<FluidType> SAKE_YOUNG = FLUID_TYPES.register("sake_young", () -> brewingType());
    public static final RegistryObject<FluidType> SAKE = FLUID_TYPES.register("sake", () -> brewingType());
    public static final RegistryObject<FluidType> BEER_YOUNG = FLUID_TYPES.register("beer_young", () -> brewingType());
    public static final RegistryObject<FluidType> BEER = FLUID_TYPES.register("beer", () -> brewingType());
    public static final RegistryObject<FluidType> WINE_YOUNG = FLUID_TYPES.register("wine_young", () -> brewingType());
    public static final RegistryObject<FluidType> WINE = FLUID_TYPES.register("wine", () -> brewingType());
    public static final RegistryObject<FluidType> SHOTHU_YOUNG = FLUID_TYPES.register("shothu_young", () -> brewingType());
    public static final RegistryObject<FluidType> SHOTHU = FLUID_TYPES.register("shothu", () -> brewingType());
    public static final RegistryObject<FluidType> WHISKEY_YOUNG = FLUID_TYPES.register("whiskey_young", () -> brewingType());
    public static final RegistryObject<FluidType> WHISKEY = FLUID_TYPES.register("whiskey", () -> brewingType());
    public static final RegistryObject<FluidType> BRANDY_YOUNG = FLUID_TYPES.register("brandy_young", () -> brewingType());
    public static final RegistryObject<FluidType> BRANDY = FLUID_TYPES.register("brandy", () -> brewingType());
    public static final RegistryObject<FluidType> RUM_YOUNG = FLUID_TYPES.register("rum_young", () -> brewingType());
    public static final RegistryObject<FluidType> RUM = FLUID_TYPES.register("rum", () -> brewingType());
    public static final RegistryObject<FluidType> VODKA_YOUNG = FLUID_TYPES.register("vodka_young", () -> brewingType());
    public static final RegistryObject<FluidType> VODKA = FLUID_TYPES.register("vodka", () -> brewingType());
    private ModFluidTypes() {}
}
