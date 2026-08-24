package mods.defeatedcrow.event;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * 1.20.1 LivingHurtEvent - potion protection/reflex.
 * Original 1.7.10 used EntityLivingBase+DamageSource+event.ammount, now LivingEntity+getSource+getAmount.
 * See doc/events/migration-guide.md:38
 */
public class DCsHurtEvent {

    @SubscribeEvent
    public void onHurtEvent(LivingHurtEvent event) {
        LivingEntity target = event.getEntity();
        DamageSource source = event.getSource();
        float damage = event.getAmount();
        if (target == null || target.level().isClientSide) return;
        // TODO: restore PotionProtectionEX / PotionReflexBase logic
        // for (MobEffectInstance inst : target.getActiveEffects()) {
        //   if (inst.getEffect() instanceof PotionProtectionEX ex) { ... }
        //   if (inst.getEffect() instanceof PotionReflexBase reflex && reflex.effectFormer(target, source, inst, damage)) { ... }
        // }
        // if (canPrevent) { event.setAmount(0); event.setCanceled(true); }
    }
}
