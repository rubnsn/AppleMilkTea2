package mods.defeatedcrow.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

/**
 * 移動速度を強制ゼロにして足止めする効果 (Deprecated維持)。
 * 1.20.1: PotionLivingBase(api凍結) → MobEffect 直接継承。
 */
@Deprecated
public class PotionConfinement extends MobEffect {

    public PotionConfinement(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (!living.level().isClientSide()) {
            living.setDeltaMovement(0.0D, living.getDeltaMovement().y(), 0.0D);
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
