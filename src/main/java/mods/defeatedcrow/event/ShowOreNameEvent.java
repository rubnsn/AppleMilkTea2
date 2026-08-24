package mods.defeatedcrow.event;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

/**
 * 1.20.1 ItemTooltipEvent - monocle shows ore tags / fluid.
 * Original 1.7.10 used player armor slot + ore dict + fluid registry, now TagKey + Capability.
 * See doc/events/migration-guide.md:40
 */
public class ShowOreNameEvent {

    @SubscribeEvent
    public void advancedTooltip(ItemTooltipEvent event) {
        ItemStack target = event.getItemStack();
        Player player = event.getEntity();
        List<Component> tooltip = event.getToolTip();
        if (player == null || target.isEmpty() || tooltip == null) return;
        // TODO: restore monocle check (ModItems.MONOCLE) and tag/fluid display
        // if (player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.MONOCLE.get())) {
        //   var tags = target.getTags().map(t->t.location().toString()).toList();
        //   tooltip.add(Component.literal("Tags: "+tags).withStyle(ChatFormatting.GRAY));
        //   target.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(h-> ...);
        // }
    }
}
