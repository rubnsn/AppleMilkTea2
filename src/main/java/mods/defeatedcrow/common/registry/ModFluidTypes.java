package mods.defeatedcrow.common.registry;

import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 1.20.1 FluidType registry — separated from Fluid (Forge 1.19.3+).
 * See doc/fluids/migration-guide.md:12
 * WT-B owns all types.
 */
public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, "defeatedcrow");

    // --- WT-B: OILS ---
    // public static final RegistryObject<FluidType> VEG_TYPE = FLUID_TYPES.register("vegitable_oil",
    //     () -> new FluidType(FluidType.Properties.create().density(800).viscosity(1500).canExtinguish(true)
    //         .sound(net.minecraftforge.common.SoundActions.BUCKET_FILL, net.minecraft.sounds.SoundEvents.BUCKET_FILL)) {
    //         @Override public int getColor(net.minecraftforge.fluids.FluidStack s, net.minecraft.world.level.LevelReader l, net.minecraft.core.BlockPos p, net.minecraft.world.level.material.FluidState st){ return 0xFFFFF0A0; }
    //     });

    // --- WT-B: BREWING 16 types (same pattern) ---

    private ModFluidTypes() {}
}
