package mods.defeatedcrow.handler;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

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

    // 1.20.1: WILDCARD_VALUE (32767) は廃止。メタ差分は個別 Item 化済みのため
    // 単純な「同一 Item か」を判定する。旧 isSameItem は damage 比較＋WILDCARD を含んでいたが
    // 1.20.1では damage/wildcard 概念がなく、is(Item) だけで十分。
    // NBT 厳密一致が必要な場合は ItemStack.isSameItemSameTags を別途使う。
    private static boolean isSameItem(ItemStack in, ItemStack tar) {
        if (in == null || in.isEmpty() || tar == null || tar.isEmpty()) return false;
        return in.is(tar.getItem());
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
        // 1.20.1: WILDCARD_VALUE 撤去に伴い、同一Item判定に簡略化 (TagHelper.itemMatches 準拠)
        private boolean match(ItemStack a, ItemStack b) {
            if (a != null && !a.isEmpty() && b != null && !b.isEmpty()) {
                return a.is(b.getItem());
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
