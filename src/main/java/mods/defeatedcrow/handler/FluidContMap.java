package mods.defeatedcrow.handler;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.oredict.OreDictionary;

/**
 * 1.20.1: Fluid Container Registry removed -> CapabilityFluidHandler (IFluidHandlerItem)
 * See doc/fluids/migration-guide.md
 * This map now uses FluidStack + ItemStack via ForgeCapabilities.FLUID_HANDLER_ITEM
 */
public class FluidContMap {

    private FluidContMap() {}

    public static final FluidContMap instance = new FluidContMap();

    private static List<BottlePack> bottleList = new ArrayList<BottlePack>();

    public static class FluidContData {
        public final FluidStack fluid;
        public final ItemStack filledContainer;
        public final ItemStack emptyContainer;
        public FluidContData(FluidStack fluid, ItemStack filled, ItemStack empty) {
            this.fluid = fluid;
            this.filledContainer = filled;
            this.emptyContainer = empty;
        }
    }

    public static void Register(FluidType type, FluidContData data) {
        if (type == null || data == null || data.filledContainer == null) return;
        // find pack by fluid type
        BottlePack target = null;
        for (BottlePack b : bottleList) {
            if (b.getType() == type) target = b;
        }
        if (target != null) {
            boolean exists = false;
            for (ItemStack item : target.getAllContainer()) {
                if (isSameItem(data.filledContainer, item)) exists = true;
            }
            if (!exists) target.addList(data);
        } else {
            BottlePack pack = instance.new BottlePack(type, data);
            bottleList.add(pack);
        }
    }

    @Deprecated
    public static void Register(net.minecraftforge.fluids.Fluid fluid, FluidContData data) {
        // legacy Fluid -> FluidType bridge
        if (fluid != null) Register(fluid.getFluidType(), data);
    }

    private static boolean isSameItem(ItemStack in, ItemStack tar) {
        if (in == null || in.isEmpty() || tar == null || tar.isEmpty()) return false;
        if (in.is(tar.getItem())) {
            int a = in.getDamageValue();
            int b = tar.getDamageValue();
            return a == b || b == OreDictionary.WILDCARD_VALUE;
        }
        return false;
    }

    public static BottlePack getPack(FluidType type) {
        if (bottleList.isEmpty()) return null;
        for (BottlePack b : bottleList) if (b.getType() == type) return b;
        return null;
    }

    @Deprecated
    public static BottlePack getPack(net.minecraftforge.fluids.Fluid fluid) {
        if (fluid == null) return null;
        return getPack(fluid.getFluidType());
    }

    public class BottlePack {
        public final FluidType type;
        public final List<FluidContData> dataList = new ArrayList<FluidContData>();
        public BottlePack(FluidType t, FluidContData d) { this.type = t; dataList.add(d); }
        public FluidType getType() { return type; }
        public List<FluidContData> getList() { return dataList; }
        public boolean addList(FluidContData data) {
            if (data != null && data.filledContainer != null) return dataList.add(data);
            return false;
        }
        public boolean isFilledContainer(ItemStack cont) {
            if (cont == null || cont.isEmpty() || dataList.isEmpty()) return false;
            for (FluidContData d : dataList) if (match(cont, d.filledContainer)) return true;
            return false;
        }
        public List<ItemStack> getAllContainer() {
            List<ItemStack> ret = new ArrayList<ItemStack>();
            for (FluidContData d : dataList) if (d.filledContainer != null) ret.add(d.filledContainer);
            return ret;
        }
        private boolean match(ItemStack a, ItemStack b) {
            if (a != null && !a.isEmpty() && b != null && !b.isEmpty()) {
                if (a.is(b.getItem())) return a.getDamageValue() == b.getDamageValue() || b.getDamageValue() == OreDictionary.WILDCARD_VALUE;
            }
            return false;
        }
        // 1.20.1 capability helper
        public FluidStack getFluidContained(ItemStack stack) {
            IFluidHandlerItem handler = stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).orElse(null);
            if (handler != null) return handler.getFluidInTank(0);
            return FluidStack.EMPTY;
        }
    }
}
