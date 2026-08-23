package mods.defeatedcrow.event;

import net.minecraftforge.fluids./* FLUID_CONTAINER_REMOVED removed - use ForgeCapabilities.FLUID_HANDLER */.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.handler.FluidContMap;

public class FluidContainerRegisterEvent {

    @SubscribeEvent
    public void onRegister(/* FLUID_CONTAINER_REMOVED removed - use ForgeCapabilities.FLUID_HANDLER */.FluidContainerRegisterEvent event) {
        FluidContainerData data = event.data;
        FluidStack fluid = data.fluid;
        if (data != null && fluid != null && fluid.getFluid() != null) {
            FluidContMap.Register(fluid.getFluid(), data);
            AMTLogger.debugInfo(
                "register fluid cont map : " + fluid.getFluid()
                    .getLocalizedName(fluid));
        }
    }

}
