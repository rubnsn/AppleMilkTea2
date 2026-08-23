package mods.defeatedcrow.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

/**
 * 窒息ポーション。1.20.1 MobEffect化 (旧 PotionBaseAMT は api 凍結のため直接継承に切替)。
 * See doc/potions/migration-guide.md
 */
public class PotionSuffocation extends MobEffect {

    public PotionSuffocation(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (amplifier > 0 && living.getHealth() > 1.0F) {
            float damage = amplifier;
            living.hurt(living.damageSources().inWall(), damage);
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int k = 25 >> amplifier;
        return k > 0 ? duration % k == 0 : true;
    }
}
