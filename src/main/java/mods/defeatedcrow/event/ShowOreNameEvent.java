package mods.defeatedcrow.event;

import java.util.ArrayList;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

import mods.defeatedcrow.common.registry.ModItems;

/**
 * 1.20.1: OreDict → TagKey migration (see handler/TagHelper).
 * Old OreDict#getOreIDs / getOreName replaced with ItemStack#getTags + TagKey#location.
 * Fluid display: old Fluid Container Registry#getFluidForFilledItem replaced with ForgeCapabilities.FLUID_HANDLER_ITEM.
 * See doc/handler/migration-guide.md and handler/TagHelper.java
 */
public class ShowOreNameEvent {

    private ArrayList<String> ores = new ArrayList<>();
    private ArrayList<String> fluids = new ArrayList<>();

    @SubscribeEvent
    public void advancedTooltip(ItemTooltipEvent event) {
        Player player = event.getEntity();
        ItemStack target = event.getItemStack();
        if (player == null || target == null || target.isEmpty()) return;

        // 1.20.1: EntityPlayerSP → client-side check via player.level().isClientSide()
        // 旧: 頭装備 monocle で判定。1.20.1では ModItems.MONOCLE の RegistryObject
        ItemStack head = player.getInventory().getArmor(3); // 3 = helmet slot (0 boots..3 helmet)
        if (!head.isEmpty() && head.is(ModItems.MONOCLE.get())) {
            this.ores = this.getOre(target);
            this.fluids = this.getFluidName(target);

            for (String s : ores) event.getToolTip().add(Component.literal(s));
            for (String s : fluids) event.getToolTip().add(Component.literal(s));
        }
    }

    private ArrayList<String> getOre(ItemStack item) {
        ArrayList<String> ore = new ArrayList<>();
        if (item == null || item.isEmpty()) {
            ore.add("No tag");
            return ore;
        }
        // 1.20.1: ItemStack#getTags() returns Stream<TagKey<Item>>
        var tags = item.getTags().toList();
        if (!tags.isEmpty()) {
            for (TagKey<Item> key : tags) {
                ore.add(key.location().toString());
            }
        } else {
            ore.add("No tag");
        }
        return ore;
    }

    private ArrayList<String> getFluidName(ItemStack item) {
        ArrayList<String> fluid = new ArrayList<>();
        if (item == null || item.isEmpty()) {
            fluid.add("Not fluid container");
            return fluid;
        }
        IFluidHandlerItem handler = item.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).orElse(null);
        FluidStack f = handler != null ? handler.getFluidInTank(0) : FluidStack.EMPTY;
        if (!f.isEmpty() && f.getFluid() != null) {
            fluid.add("Fluid container : " + f.getDisplayName().getString() + " " + f.getAmount());
            fluid.add("Fluid registry name : " + BuiltInRegistries.FLUID.getKey(f.getFluid()));
        } else {
            fluid.add("Not fluid container");
        }
        return fluid;
    }
}

