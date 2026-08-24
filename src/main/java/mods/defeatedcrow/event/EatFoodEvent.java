package mods.defeatedcrow.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * 1.20.1 LivingEntityUseItemEvent.Finish - food finish hook.
 * Original 1.7.10 used PlayerUseItemEvent.Finish + LoadModHandler.getItem("DCsBakedApple"), now LivingEntityUseItemEvent.Finish + TagKey/Registry.
 */
public class EatFoodEvent {

    @SubscribeEvent
    public void eatFinishEvent(LivingEntityUseItemEvent.Finish event) {
        LivingEntity entity = event.getEntity();
        ItemStack food = event.getItem();
        if (!(entity instanceof Player player) || food.isEmpty()) return;
        // TODO: restore original baked apple check when ModItems.BAKED_APPLE is available
        // if (food.is(ModItems.BAKED_APPLE.get())) { ... }
    }
}
