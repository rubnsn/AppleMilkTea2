package mods.defeatedcrow.common.fluid;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

/**
 * 1.20.1: FluidTank migrated, FluidRegistry removed. Use FluidStack with FluidType.
 * See doc/fluids/migration-guide.md
 */
public class DCsTank extends FluidTank {

    public DCsTank(int capacity) {
        super(capacity);
    }

    public DCsTank(FluidStack stack, int capacity) {
        super(capacity);
        if (stack != null && !stack.isEmpty()) setFluid(stack);
    }

    public DCsTank(net.minecraft.world.level.material.Fluid fluid, int amount, int capacity) {
        super(capacity);
        if (fluid != null) setFluid(new FluidStack(fluid, amount));
    }

    public boolean isEmpty() {
        return getFluid().isEmpty() || getFluidAmount() <= 0;
    }

    public boolean isFull() {
        return !getFluid().isEmpty() && getFluidAmount() == getCapacity();
    }

    public net.minecraft.world.level.material.Fluid getFluidType() {
        return getFluid().isEmpty() ? net.minecraft.world.level.material.Fluids.EMPTY : getFluid().getFluid();
    }

    public String getFluidName() {
        return getFluid().isEmpty() ? "Empty" : net.minecraftforge.registries.ForgeRegistries.FLUIDS.getKey(getFluid().getFluid()).toString();
    }

    public void setAmount(int amount) {
        if (!getFluid().isEmpty()) {
            getFluid().setAmount(amount);
        }
    }

    // 1.20.1: FluidRegistry.getFluid(int) removed - use ResourceLocation lookup
    @Deprecated
    public void setFluidById(int id) {
        // No-op: fluid IDs removed in 1.20.1 (use Registry lookup)
        this.setFluid(FluidStack.EMPTY);
    }

    // 1.20.1 bridge for BlockEntity NBT (TileEvaporator/Barrel use load/saveAdditional wrappers)
    public void load(net.minecraft.nbt.CompoundTag tag) {
        this.readFromNBT(tag);
    }

    public void saveAdditional(net.minecraft.nbt.CompoundTag tag) {
        this.writeToNBT(tag);
    }
}
