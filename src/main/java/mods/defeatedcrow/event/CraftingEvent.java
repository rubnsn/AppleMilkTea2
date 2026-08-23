package mods.defeatedcrow.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * 1.20.1 stub for CraftingEvent — handles container returns for largeBottle/cordial and achievement triggers.
 * Original used getStackInSlot, getDamageValue-old, triggerAchievement, potionitem, emptyBottle etc.
 * 1.20.1: Achievements -> Advancements (AdvancementHolder), ItemStack NBT, Inventory, getDamageValue.
 * See doc/events/migration-guide.md and doc/achievements/migration-guide.md
 */
public class CraftingEvent {

    @SubscribeEvent
    public void onCraftingEvent(PlayerEvent.ItemCraftedEvent event) {
        Player player = event.getEntity();
        ItemStack crafting = event.getCrafting();
        if (crafting.isEmpty() || player == null) return;
        // TODO: restore container return logic for largeBottle/cordial using getDamageValue + ModItems
        // for (int i = 0; i < event.getInventory().getContainerSize(); i++) { ItemStack m = event.getInventory().getItem(i); ... }
        // TODO: restore achievements via AdvancementHolder / trigger
    }

    @SubscribeEvent
    public void onSmelting(PlayerEvent.ItemSmeltedEvent event) {
        Player player = event.getEntity();
        ItemStack item = event.getSmelting();
        if (item.isEmpty() || player == null) return;
        // TODO: restore smelting achievements
    }
}
