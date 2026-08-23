package mods.defeatedcrow.event;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import mods.defeatedcrow.common.DCsAppleMilk;

/**
 * 1.20.1 stub for EntityMoreDropEvent - princessClam bonus drops (flower/butterfly).
 * Original used EntityDamageSource, posX/Y/Z, world.rand, mainInventory, getDamageValue-old, count-old.
 * 1.20.1: DamageSource via level.damageSources(), player.getInventory(), ItemStack.getDamageValue/getCount/setCount, level.random
 * See doc/events/migration-guide.md
 */
public class EntityMoreDropEvent {

    @SubscribeEvent
    public void onEntityDrop(LivingDropsEvent event) {
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();
        // TODO: restore princessClam logic using ItemStack.is + getCount/setCount + level.random
        // if (entity instanceof Player || !(source.getEntity() instanceof Player player)) return;
        // for (ItemEntity drop : event.getDrops()) { ItemStack s = drop.getItem(); if (s.is(ModItems.PRINCESS_CLAM.get())) ... }
    }
}
