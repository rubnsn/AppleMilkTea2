package mods.defeatedcrow.potion;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import mods.defeatedcrow.common.registry.ModMobEffects;

/**
 * Immunityポーションのクラス。Amplifierごとに除去可能な効果をチェックして除去する。
 * 1.20.1: PotionLivingBase(api凍結) → MobEffect 直接継承、tick処理は applyEffectTick へ。
 */
public class PotionImmunity extends MobEffect {

    public PotionImmunity(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide()) return;

        List<net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect>> check = new ArrayList<>();

        if (amplifier == 0) {
            check.add(MobEffects.HUNGER);
        }

        if (amplifier > 0) {
            check.add(MobEffects.POISON);
            check.add(MobEffects.WITHER);
        }

        if (amplifier > 1) {
            check.add(MobEffects.CONFUSION);
            check.add(MobEffects.BLINDNESS);
            check.add(ModMobEffects.SUFFOCATION.getHolder());
        }

        if (amplifier > 2) {
            check.add(MobEffects.DIG_SLOWDOWN);
            check.add(MobEffects.MOVEMENT_SLOWDOWN);
            check.add(MobEffects.WEAKNESS);
        }

        for (net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect> effect : check) {
            if (entity.hasEffect(effect)) {
                entity.removeEffect(effect);
            }
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}
