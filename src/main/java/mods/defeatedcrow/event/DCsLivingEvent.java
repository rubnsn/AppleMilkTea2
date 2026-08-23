package mods.defeatedcrow.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import mods.defeatedcrow.api.potion.PotionImmunityBase;

/**
 * 1.20.1 stub for DCsLivingEvent — handles PotionImmunityBase prevention + charm warp key.
 * Original 1.7.10 used PotionReg-old (old)[id], getActiveEffects, pID_old (old) -> getEffect, provider.getDimensionName, getX() etc.
 * 1.20.1: MobEffect + MobEffectInstance + LivingEntity.getActiveEffects() + level.dimension().location()
 * See doc/events/migration-guide.md:31
 * Full logic TODO — this stub keeps the class and event subscription so the mod loads.
 */
public class DCsLivingEvent {

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (!(entity instanceof Player player)) return;
        // TODO: restore PotionImmunityBase logic via player.getActiveEffects() + MobEffectInstance
        // for (MobEffectInstance inst : player.getActiveEffects()) {
        //   if (inst.getEffect() instanceof PotionImmunityBase immunity && immunity.preventPotion(...)) { ... }
        // }
        // TODO: restore charm warp key logic via Component + BlockPos + level.dimension()
    }
}
