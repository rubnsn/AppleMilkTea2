package mods.defeatedcrow.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import mods.defeatedcrow.api.potion.PotionProtectionEX;
import mods.defeatedcrow.api.potion.PotionReflexBase;

/**
 * 1.20.1 stub for DCsHurtEvent - handles PotionProtectionEX / PotionReflexBase damage prevention.
 * Original used PotionReg-old (old), EntityDamageSource, DamageSource.anvil, ammount, getActiveEffects.
 * 1.20.1: MobEffectInstance + level.damageSources(), DamageSource tags, LivingHurtEvent.getSource/getAmount/setAmount.
 * See doc/events/migration-guide.md
 */
public class DCsHurtEvent {

    @SubscribeEvent
    public void onHurtEvent(LivingHurtEvent event) {
        LivingEntity target = event.getEntity();
        if (target == null || target.level().isClientSide) return;
        DamageSource source = event.getSource();
        float damage = event.getAmount();
        // TODO: restore PotionProtectionEX / PotionReflexBase logic via target.getActiveEffects()
        // for (MobEffectInstance inst : target.getActiveEffects()) {
        //   if (inst.getEffect() instanceof PotionProtectionEX ex && ex.getAllProtection()) { event.setAmount(0); event.setCanceled(true); }
        //   if (inst.getEffect() instanceof PotionReflexBase reflex && reflex.effectFormer(...)) { ... }
        // }
    }
}
