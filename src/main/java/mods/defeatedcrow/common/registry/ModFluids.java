package mods.defeatedcrow.common.registry;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 1.20.1 Fluid registry - FlowingFluid Source/Flowing + FluidType separation.
 * See doc/fluids/migration-guide.md:12
 * WT-B owns all fluids (18種: vegitable_oil, camellia_oil + 16 brewing). Bootstrap owns the DeferredRegister shells.
 */
public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, "defeatedcrow");

    // helper
    private static ForgeFlowingFluid.Properties props(net.minecraftforge.registries.RegistryObject<net.minecraftforge.fluids.FluidType> type, RegistryObject<Fluid> still, RegistryObject<Fluid> flowing, RegistryObject<net.minecraft.world.level.block.Block> block, RegistryObject<net.minecraft.world.item.Item> bucket) {
        return new ForgeFlowingFluid.Properties(type, still, flowing).block(block).bucket(bucket).slopeFindDistance(2).levelDecreasePerBlock(1);
    }

    // --- WT-B: OILS (vegitable_oil, camellia_oil) ---
    public static final RegistryObject<ForgeFlowingFluid> VEGITABLE_OIL_SOURCE = FLUIDS.register("vegitable_oil",
        () -> new ForgeFlowingFluid.Source(vegOilProps()));
    public static final RegistryObject<ForgeFlowingFluid> VEGITABLE_OIL_FLOWING = FLUIDS.register("vegitable_oil_flowing",
        () -> new ForgeFlowingFluid.Flowing(vegOilProps()));
    private static ForgeFlowingFluid.Properties vegOilProps() {
        return new ForgeFlowingFluid.Properties(ModFluidTypes.VEGITABLE_OIL, VEGITABLE_OIL_SOURCE, VEGITABLE_OIL_FLOWING)
            .slopeFindDistance(2).levelDecreasePerBlock(1);
    }
    public static final RegistryObject<ForgeFlowingFluid> CAMELLIA_OIL_SOURCE = FLUIDS.register("camellia_oil",
        () -> new ForgeFlowingFluid.Source(camOilProps()));
    public static final RegistryObject<ForgeFlowingFluid> CAMELLIA_OIL_FLOWING = FLUIDS.register("camellia_oil_flowing",
        () -> new ForgeFlowingFluid.Flowing(camOilProps()));
    private static ForgeFlowingFluid.Properties camOilProps() {
        return new ForgeFlowingFluid.Properties(ModFluidTypes.CAMELLIA_OIL, CAMELLIA_OIL_SOURCE, CAMELLIA_OIL_FLOWING)
            .slopeFindDistance(2).levelDecreasePerBlock(1);
    }

    // --- WT-B: BREWING 16 (shothu_young/whiskey_young/brandy_young/rum_young/vodka_young + aged + sake_young/beer_young/wine_young + aged) ---
    // All 16 share same pattern: we create generic brewing props with dummy block/bucket to satisfy registry; actual LiquidBlock is dummy (barrel handles brewing)
    // For 1.20.1, brewing fluids are not placed as LiquidBlocks; they are stored in TileBrewingBarrel DCsTank. So block is dummy AIR and bucket is generic bottle.
    public static final RegistryObject<ForgeFlowingFluid> SAKE_YOUNG_SOURCE = FLUIDS.register("sake_young", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.SAKE_YOUNG, SAKE_YOUNG_SOURCE, SAKE_YOUNG_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> SAKE_YOUNG_FLOWING = FLUIDS.register("sake_young_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.SAKE_YOUNG, SAKE_YOUNG_SOURCE, SAKE_YOUNG_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> SAKE_SOURCE = FLUIDS.register("sake", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.SAKE, SAKE_SOURCE, SAKE_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> SAKE_FLOWING = FLUIDS.register("sake_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.SAKE, SAKE_SOURCE, SAKE_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> BEER_YOUNG_SOURCE = FLUIDS.register("beer_young", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.BEER_YOUNG, BEER_YOUNG_SOURCE, BEER_YOUNG_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> BEER_YOUNG_FLOWING = FLUIDS.register("beer_young_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.BEER_YOUNG, BEER_YOUNG_SOURCE, BEER_YOUNG_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> BEER_SOURCE = FLUIDS.register("beer", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.BEER, BEER_SOURCE, BEER_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> BEER_FLOWING = FLUIDS.register("beer_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.BEER, BEER_SOURCE, BEER_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> WINE_YOUNG_SOURCE = FLUIDS.register("wine_young", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.WINE_YOUNG, WINE_YOUNG_SOURCE, WINE_YOUNG_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> WINE_YOUNG_FLOWING = FLUIDS.register("wine_young_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.WINE_YOUNG, WINE_YOUNG_SOURCE, WINE_YOUNG_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> WINE_SOURCE = FLUIDS.register("wine", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.WINE, WINE_SOURCE, WINE_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> WINE_FLOWING = FLUIDS.register("wine_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.WINE, WINE_SOURCE, WINE_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> SHOTHU_YOUNG_SOURCE = FLUIDS.register("shothu_young", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.SHOTHU_YOUNG, SHOTHU_YOUNG_SOURCE, SHOTHU_YOUNG_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> SHOTHU_YOUNG_FLOWING = FLUIDS.register("shothu_young_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.SHOTHU_YOUNG, SHOTHU_YOUNG_SOURCE, SHOTHU_YOUNG_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> SHOTHU_SOURCE = FLUIDS.register("shothu", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.SHOTHU, SHOTHU_SOURCE, SHOTHU_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> SHOTHU_FLOWING = FLUIDS.register("shothu_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.SHOTHU, SHOTHU_SOURCE, SHOTHU_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> WHISKEY_YOUNG_SOURCE = FLUIDS.register("whiskey_young", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.WHISKEY_YOUNG, WHISKEY_YOUNG_SOURCE, WHISKEY_YOUNG_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> WHISKEY_YOUNG_FLOWING = FLUIDS.register("whiskey_young_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.WHISKEY_YOUNG, WHISKEY_YOUNG_SOURCE, WHISKEY_YOUNG_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> WHISKEY_SOURCE = FLUIDS.register("whiskey", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.WHISKEY, WHISKEY_SOURCE, WHISKEY_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> WHISKEY_FLOWING = FLUIDS.register("whiskey_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.WHISKEY, WHISKEY_SOURCE, WHISKEY_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> BRANDY_YOUNG_SOURCE = FLUIDS.register("brandy_young", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.BRANDY_YOUNG, BRANDY_YOUNG_SOURCE, BRANDY_YOUNG_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> BRANDY_YOUNG_FLOWING = FLUIDS.register("brandy_young_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.BRANDY_YOUNG, BRANDY_YOUNG_SOURCE, BRANDY_YOUNG_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> BRANDY_SOURCE = FLUIDS.register("brandy", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.BRANDY, BRANDY_SOURCE, BRANDY_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> BRANDY_FLOWING = FLUIDS.register("brandy_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.BRANDY, BRANDY_SOURCE, BRANDY_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> RUM_YOUNG_SOURCE = FLUIDS.register("rum_young", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.RUM_YOUNG, RUM_YOUNG_SOURCE, RUM_YOUNG_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> RUM_YOUNG_FLOWING = FLUIDS.register("rum_young_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.RUM_YOUNG, RUM_YOUNG_SOURCE, RUM_YOUNG_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> RUM_SOURCE = FLUIDS.register("rum", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.RUM, RUM_SOURCE, RUM_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> RUM_FLOWING = FLUIDS.register("rum_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.RUM, RUM_SOURCE, RUM_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> VODKA_YOUNG_SOURCE = FLUIDS.register("vodka_young", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.VODKA_YOUNG, VODKA_YOUNG_SOURCE, VODKA_YOUNG_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> VODKA_YOUNG_FLOWING = FLUIDS.register("vodka_young_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.VODKA_YOUNG, VODKA_YOUNG_SOURCE, VODKA_YOUNG_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> VODKA_SOURCE = FLUIDS.register("vodka", () -> new ForgeFlowingFluid.Source(brewingProps(ModFluidTypes.VODKA, VODKA_SOURCE, VODKA_FLOWING)));
    public static final RegistryObject<ForgeFlowingFluid> VODKA_FLOWING = FLUIDS.register("vodka_flowing", () -> new ForgeFlowingFluid.Flowing(brewingProps(ModFluidTypes.VODKA, VODKA_SOURCE, VODKA_FLOWING)));

    private static ForgeFlowingFluid.Properties brewingProps(RegistryObject<net.minecraftforge.fluids.FluidType> type, RegistryObject<Fluid> still, RegistryObject<Fluid> flowing) {
        return new ForgeFlowingFluid.Properties(type, still, flowing).slopeFindDistance(2).levelDecreasePerBlock(1);
    }

    private ModFluids() {}
}
