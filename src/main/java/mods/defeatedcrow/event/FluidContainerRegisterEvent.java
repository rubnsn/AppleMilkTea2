package mods.defeatedcrow.event;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.handler.FluidContMap;

/**
 * 1.20.1 stub for FluidContainerRegisterEvent — legacy FluidContainerRegistry.
 * 1.20.1 uses ForgeCapabilities.FLUID_HANDLER + FluidUtil. This event is no longer fired.
 * This stub keeps the class for compilation; registration is via FluidContMap directly.
 * See doc/fluids/migration-guide.md
 */
public class FluidContainerRegisterEvent {

    @SubscribeEvent
    public void onRegister(Object event) {
        // No-op in 1.20.1 — FluidStack handling is via capability, not registry event
        AMTLogger.debugInfo("FluidContainerRegisterEvent stub — no action in 1.20.1");
    }
}
