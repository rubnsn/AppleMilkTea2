package mods.defeatedcrow.common.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class DCsTank extends FluidTank {

    public DCsTank(int capacity) {
        super(capacity);
    }

    public DCsTank(FluidStack stack, int capacity) {
        super(stack, capacity);
    }

    public DCsTank(Fluid fluid, int amount, int capacity) {
        super(fluid, amount, capacity);
    }

    public boolean isEmpty() {
        return (getFluid() == null) || (getFluid().amount <= 0);
    }

    public boolean isFull() {
        return (getFluid() != null) && (getFluid().amount == getCapacity());
    }

    public Fluid getFluidType() {
        return getFluid() != null ? getFluid().getFluid() : null;
    }

    public String getFluidName() {
        return (this.fluid != null) && (this.fluid.getFluid() != null) ? this.fluid.getFluid()
            .getLocalizedName(this.fluid) : "Empty";
    }

        public void setAmount(int par1) {
        if (this.fluid != null && this.fluid.getFluid() != null) {
            this.fluid.amount = par1;
        }
    }

        public void setFluidById(int par1) {
        Fluid f = FluidRegistry.getFluid(par1);
        if (f != null) {
            this.fluid = new FluidStack(f, this.getFluidAmount());
        } else {
            this.fluid = (FluidStack) null;
        }
    }

}
