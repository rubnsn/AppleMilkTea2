package mods.defeatedcrow.handler;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraft.world.level.material.Fluid;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.20.1 FluidContMap - fluid container mapping (replaces FluidContainerRegistry).
 * Original 1.7.10 used FluidContainerRegistry.FluidContainerData + OreDictionary, now FluidStack + TagKey.
 */
public class FluidContMap {

    private FluidContMap() {}
    public static final FluidContMap instance = new FluidContMap();
    private static List<BottlePack> bottleList = new ArrayList<>();

    public static void Register(Fluid f, ItemStack filled, ItemStack empty, FluidStack fluidStack) {
        if (f == null || filled == null || filled.isEmpty()) return;
        boolean flag = true;
        BottlePack target = null;
        for (BottlePack b : bottleList) {
            if (b.getFluid() == f) target = b;
        }
        if (target != null) {
            for (ItemStack item : target.getAllContainer()) {
                if (ItemStack.isSameItemSameTags(filled, item)) flag = false;
            }
            if (flag) target.addList(filled, empty, fluidStack);
        } else {
            bottleList.add(instance.new BottlePack(f, filled, empty, fluidStack));
        }
    }

    public static BottlePack getPack(Fluid f) {
        if (bottleList.isEmpty()) return null;
        for (BottlePack b : bottleList) if (b.getFluid() == f) return b;
        return null;
    }

    public class BottlePack {
        public final Fluid fluid;
        public final List<ItemStack> filledList = new ArrayList<>();
        public final List<FluidStack> fluidList = new ArrayList<>();
        public BottlePack(Fluid f, ItemStack filled, ItemStack empty, FluidStack fs) {
            this.fluid = f;
            filledList.add(filled);
            if (fs != null) fluidList.add(fs);
        }
        public Fluid getFluid() { return fluid; }
        public List<ItemStack> getAllContainer() { return filledList; }
        public void addList(ItemStack filled, ItemStack empty, FluidStack fs) {
            filledList.add(filled);
            if (fs != null) fluidList.add(fs);
        }
    }
}
