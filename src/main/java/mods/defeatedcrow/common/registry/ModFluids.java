package mods.defeatedcrow.common.registry;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 1.20.1 Fluid registry - FlowingFluid Source/Flowing + FluidType separation.
 * See doc/fluids/migration-guide.md:12
 * WT-B owns all fluids (18 species: vegitable_oil, camellia_oil + 16 brewing). Bootstrap owns the DeferredRegister shells.
 * Fix: avoid self-reference in lambda (Java definite assignment) by using helper that does not capture still/flowing RegistryObject.
 */
public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, "defeatedcrow");

    private static ForgeFlowingFluid.Properties vegOilProps() {
        return new ForgeFlowingFluid.Properties(ModFluidTypes.VEGITABLE_OIL, () -> net.minecraft.world.level.material.Fluids.WATER, () -> net.minecraft.world.level.material.Fluids.WATER)
            .block(() -> (LiquidBlock) Blocks.AIR).bucket(() -> net.minecraft.world.item.Items.AIR).slopeFindDistance(2).levelDecreasePerBlock(1);
    }
    private static ForgeFlowingFluid.Properties camOilProps() {
        return new ForgeFlowingFluid.Properties(ModFluidTypes.CAMELLIA_OIL, () -> net.minecraft.world.level.material.Fluids.WATER, () -> net.minecraft.world.level.material.Fluids.WATER)
            .block(() -> (LiquidBlock) Blocks.AIR).bucket(() -> net.minecraft.world.item.Items.AIR).slopeFindDistance(2).levelDecreasePerBlock(1);
    }

    // --- WT-B: OILS (vegitable_oil, camellia_oil) ---
    public static final RegistryObject<ForgeFlowingFluid> VEGITABLE_OIL_SOURCE = FLUIDS.register("vegitable_oil",
        () -> new ForgeFlowingFluid.Source(vegOilProps()));
    public static final RegistryObject<ForgeFlowingFluid> VEGITABLE_OIL_FLOWING = FLUIDS.register("vegitable_oil_flowing",
        () -> new ForgeFlowingFluid.Flowing(vegOilProps()));
    public static final RegistryObject<ForgeFlowingFluid> CAMELLIA_OIL_SOURCE = FLUIDS.register("camellia_oil",
        () -> new ForgeFlowingFluid.Source(camOilProps()));
    public static final RegistryObject<ForgeFlowingFluid> CAMELLIA_OIL_FLOWING = FLUIDS.register("camellia_oil_flowing",
        () -> new ForgeFlowingFluid.Flowing(camOilProps()));

    // --- WT-B: BREWING 16 (use dummy block/bucket + WATER placeholder to avoid self-reference compile error) ---
    private static ForgeFlowingFluid.Properties brewingProps(RegistryObject<net.minecraftforge.fluids.FluidType> type) {
        return new ForgeFlowingFluid.Properties(type, () -> net.minecraft.world.level.material.Fluids.WATER, () -> net.minecraft.world.level.material.Fluids.WATER)
            .block(() -> (LiquidBlock) Blocks.AIR).bucket(() -> net.minecraft.world.item.Items.AIR).slopeFindDistance(2).levelDecreasePerBlock(1);
    }
    public static final RegistryObject<ForgeFlowingFluid> SAKE_YOUNG_SOURCE = FLUIDS.register("sake_young", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.SAKE_YOUNG)));
    public static final RegistryObject<ForgeFlowingFluid> SAKE_YOUNG_FLOWING = FLUIDS.register("sake_young_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.SAKE_YOUNG)));
    public static final RegistryObject<ForgeFlowingFluid> SAKE_SOURCE = FLUIDS.register("sake", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.SAKE)));
    public static final RegistryObject<ForgeFlowingFluid> SAKE_FLOWING = FLUIDS.register("sake_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.SAKE)));
    public static final RegistryObject<ForgeFlowingFluid> BEER_YOUNG_SOURCE = FLUIDS.register("beer_young", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.BEER_YOUNG)));
    public static final RegistryObject<ForgeFlowingFluid> BEER_YOUNG_FLOWING = FLUIDS.register("beer_young_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.BEER_YOUNG)));
    public static final RegistryObject<ForgeFlowingFluid> BEER_SOURCE = FLUIDS.register("beer", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.BEER)));
    public static final RegistryObject<ForgeFlowingFluid> BEER_FLOWING = FLUIDS.register("beer_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.BEER)));
    public static final RegistryObject<ForgeFlowingFluid> WINE_YOUNG_SOURCE = FLUIDS.register("wine_young", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.WINE_YOUNG)));
    public static final RegistryObject<ForgeFlowingFluid> WINE_YOUNG_FLOWING = FLUIDS.register("wine_young_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.WINE_YOUNG)));
    public static final RegistryObject<ForgeFlowingFluid> WINE_SOURCE = FLUIDS.register("wine", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.WINE)));
    public static final RegistryObject<ForgeFlowingFluid> WINE_FLOWING = FLUIDS.register("wine_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.WINE)));
    public static final RegistryObject<ForgeFlowingFluid> SHOTHU_YOUNG_SOURCE = FLUIDS.register("shothu_young", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.SHOTHU_YOUNG)));
    public static final RegistryObject<ForgeFlowingFluid> SHOTHU_YOUNG_FLOWING = FLUIDS.register("shothu_young_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.SHOTHU_YOUNG)));
    public static final RegistryObject<ForgeFlowingFluid> SHOTHU_SOURCE = FLUIDS.register("shothu", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.SHOTHU)));
    public static final RegistryObject<ForgeFlowingFluid> SHOTHU_FLOWING = FLUIDS.register("shothu_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.SHOTHU)));
    public static final RegistryObject<ForgeFlowingFluid> WHISKEY_YOUNG_SOURCE = FLUIDS.register("whiskey_young", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.WHISKEY_YOUNG)));
    public static final RegistryObject<ForgeFlowingFluid> WHISKEY_YOUNG_FLOWING = FLUIDS.register("whiskey_young_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.WHISKEY_YOUNG)));
    public static final RegistryObject<ForgeFlowingFluid> WHISKEY_SOURCE = FLUIDS.register("whiskey", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.WHISKEY)));
    public static final RegistryObject<ForgeFlowingFluid> WHISKEY_FLOWING = FLUIDS.register("whiskey_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.WHISKEY)));
    public static final RegistryObject<ForgeFlowingFluid> BRANDY_YOUNG_SOURCE = FLUIDS.register("brandy_young", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.BRANDY_YOUNG)));
    public static final RegistryObject<ForgeFlowingFluid> BRANDY_YOUNG_FLOWING = FLUIDS.register("brandy_young_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.BRANDY_YOUNG)));
    public static final RegistryObject<ForgeFlowingFluid> BRANDY_SOURCE = FLUIDS.register("brandy", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.BRANDY)));
    public static final RegistryObject<ForgeFlowingFluid> BRANDY_FLOWING = FLUIDS.register("brandy_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.BRANDY)));
    public static final RegistryObject<ForgeFlowingFluid> RUM_YOUNG_SOURCE = FLUIDS.register("rum_young", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.RUM_YOUNG)));
    public static final RegistryObject<ForgeFlowingFluid> RUM_YOUNG_FLOWING = FLUIDS.register("rum_young_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.RUM_YOUNG)));
    public static final RegistryObject<ForgeFlowingFluid> RUM_SOURCE = FLUIDS.register("rum", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.RUM)));
    public static final RegistryObject<ForgeFlowingFluid> RUM_FLOWING = FLUIDS.register("rum_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.RUM)));
    public static final RegistryObject<ForgeFlowingFluid> VODKA_YOUNG_SOURCE = FLUIDS.register("vodka_young", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.VODKA_YOUNG)));
    public static final RegistryObject<ForgeFlowingFluid> VODKA_YOUNG_FLOWING = FLUIDS.register("vodka_young_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.VODKA_YOUNG)));
    public static final RegistryObject<ForgeFlowingFluid> VODKA_SOURCE = FLUIDS.register("vodka", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.VODKA)));
    public static final RegistryObject<ForgeFlowingFluid> VODKA_FLOWING = FLUIDS.register("vodka_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.VODKA)));

    private ModFluids() {}
}
