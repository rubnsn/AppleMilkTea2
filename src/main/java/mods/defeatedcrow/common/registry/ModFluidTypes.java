package mods.defeatedcrow.common.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 1.20.1 FluidType registry - separated from Fluid (Forge 1.19.3+).
 * See doc/fluids/migration-guide.md:12
 * WT-B owns all types. ForgeFlowingFluid.Properties now takes (FluidType, Supplier<Fluid> still, Supplier<Fluid> flowing).
 */
public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, "defeatedcrow");

    // --- WT-B: OILS ---
    public static final RegistryObject<FluidType> VEGITABLE_OIL = FLUID_TYPES.register("vegitable_oil",
        () -> new FluidType(FluidType.Properties.create().density(800).viscosity(1500).canExtinguish(true)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL).sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
            .lightLevel(0)) {
            @Override public int getColor(FluidStack stack, LevelReader level, BlockPos pos, FluidState state) { return 0xFFFFF0A0; }
            @Override public ResourceLocation getStillTexture() { return new ResourceLocation("defeatedcrow", "block/fluid/vegitable_oil_still"); }
            @Override public ResourceLocation getFlowingTexture() { return new ResourceLocation("defeatedcrow", "block/fluid/vegitable_oil_flow"); }
        });
    public static final RegistryObject<FluidType> CAMELLIA_OIL = FLUID_TYPES.register("camellia_oil",
        () -> new FluidType(FluidType.Properties.create().density(800).viscosity(1500).canExtinguish(true).canConvertToSource(false)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL).lightLevel(0)) {
            @Override public int getColor(FluidStack stack, LevelReader level, BlockPos pos, FluidState state) { return 0xFFFFC070; }
            @Override public ResourceLocation getStillTexture() { return new ResourceLocation("defeatedcrow", "block/fluid/camelliaoil_still"); }
            @Override public ResourceLocation getFlowingTexture() { return new ResourceLocation("defeatedcrow", "block/fluid/camelliaoil_flow"); }
        });

    // --- WT-B: BREWING 16 types (same pattern) ---
    private static FluidType brewingType(int color, String still) {
        return new FluidType(FluidType.Properties.create().density(1000).viscosity(1000).canExtinguish(false)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL).lightLevel(0)) {
            @Override public int getColor(FluidStack stack, LevelReader level, BlockPos pos, FluidState state) { return color; }
            @Override public ResourceLocation getStillTexture() { return new ResourceLocation("defeatedcrow", "block/fluid/" + still); }
            @Override public ResourceLocation getFlowingTexture() { return new ResourceLocation("defeatedcrow", "block/fluid/" + still); }
        };
    }
    public static final RegistryObject<FluidType> SAKE_YOUNG = FLUID_TYPES.register("sake_young", () -> brewingType(0xFFECE8AA, "sake_still"));
    public static final RegistryObject<FluidType> SAKE = FLUID_TYPES.register("sake", () -> brewingType(0xFFECE8AA, "sake_still"));
    public static final RegistryObject<FluidType> BEER_YOUNG = FLUID_TYPES.register("beer_young", () -> brewingType(0xFFE8D070, "beer_still"));
    public static final RegistryObject<FluidType> BEER = FLUID_TYPES.register("beer", () -> brewingType(0xFFE8D070, "beer_still"));
    public static final RegistryObject<FluidType> WINE_YOUNG = FLUID_TYPES.register("wine_young", () -> brewingType(0xFF800040, "wine_still"));
    public static final RegistryObject<FluidType> WINE = FLUID_TYPES.register("wine", () -> brewingType(0xFF800040, "wine_still"));
    public static final RegistryObject<FluidType> SHOTHU_YOUNG = FLUID_TYPES.register("shothu_young", () -> brewingType(0xFFFFFFFF, "shothu_still"));
    public static final RegistryObject<FluidType> SHOTHU = FLUID_TYPES.register("shothu", () -> brewingType(0xFFFFFFFF, "shothu_still"));
    public static final RegistryObject<FluidType> WHISKEY_YOUNG = FLUID_TYPES.register("whiskey_young", () -> brewingType(0xFFD0A040, "whiskey_still"));
    public static final RegistryObject<FluidType> WHISKEY = FLUID_TYPES.register("whiskey", () -> brewingType(0xFFD0A040, "whiskey_still"));
    public static final RegistryObject<FluidType> BRANDY_YOUNG = FLUID_TYPES.register("brandy_young", () -> brewingType(0xFFB06020, "brandy_still"));
    public static final RegistryObject<FluidType> BRANDY = FLUID_TYPES.register("brandy", () -> brewingType(0xFFB06020, "brandy_still"));
    public static final RegistryObject<FluidType> RUM_YOUNG = FLUID_TYPES.register("rum_young", () -> brewingType(0xFFA03020, "rum_still"));
    public static final RegistryObject<FluidType> RUM = FLUID_TYPES.register("rum", () -> brewingType(0xFFA03020, "rum_still"));
    public static final RegistryObject<FluidType> VODKA_YOUNG = FLUID_TYPES.register("vodka_young", () -> brewingType(0xFFFFFFFF, "vodka_still"));
    public static final RegistryObject<FluidType> VODKA = FLUID_TYPES.register("vodka", () -> brewingType(0xFFFFFFFF, "vodka_still"));

    private ModFluidTypes() {}
}
