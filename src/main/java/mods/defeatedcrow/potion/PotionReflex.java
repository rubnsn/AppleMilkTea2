package mods.defeatedcrow.potion;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

/**
 * 当て身ポーション。効果時間中にダメージを受けると発動し、ダメージを反射したり、吸収したりする。
 * 発動は WT-B の DCsHurtEvent から effectFormer を経由して呼ばれる。
 * 1.20.1: PotionReflexBase(api凍結) → MobEffect 直接継承、PotionEffect → MobEffectInstance。
 */
public class PotionReflex extends MobEffect {

    public PotionReflex(MobEffectCategory category, int color) {
        super(category, color);
    }

    public boolean effectFormer(LivingEntity target, DamageSource source, Holder<MobEffect> self, MobEffectInstance effect, float amount) {
        boolean succeed = false;

        if (amount < 1.0F) {
            amount = 1.0F;
        }

        int amp = effect.getAmplifier();

        if (self.is(this)) {
            if (amount < 5.0F) {
                amount = 5.0F;
            }

            if (source.getEntity() != null) {
                LivingEntity livingAttacker = source.getEntity() instanceof LivingEntity le ? le : null;

                if (livingAttacker != null && livingAttacker != target) {
                    float range = 360 - livingAttacker.getYRot();
                    float yawX = (float) Math.sin(range / 180.0F * Math.PI);
                    float yawZ = (float) Math.cos(range / 180.0F * Math.PI);
                    livingAttacker.push(1.0D * yawX, 0.3D, 1.0D * yawZ);
                    livingAttacker.hurt(target.damageSources().magic(), amount * amp);
                    playMetalSound(target);
                    succeed = true;
                } else if (livingAttacker == null && amp > 0) {
                    playMetalSound(target);
                    succeed = true;
                }
            }
        }

        if (self.is(mods.defeatedcrow.common.registry.ModMobEffects.ABS_EXP.getKey())) {
            if (target instanceof Player player) {
                int get = Math.round(Math.abs(amount));
                boolean flag = false;

                if (amp > 1) {
                    flag = true;
                } else if (amp > 0 && (source.is(net.minecraft.world.damagesource.DamageTypes.EXPLOSION)
                    || source.is(net.minecraft.world.damagesource.DamageTypes.IN_FIRE))) {
                    flag = true;
                } else {
                    flag = (source.getEntity() != null);
                }

                if (flag) {
                    player.giveExperiencePoints(get);
                    playSuzuSound(player);
                    succeed = true;
                }
            }
        }

        if (self.is(mods.defeatedcrow.common.registry.ModMobEffects.ABS_HEAL.getKey())) {
            boolean flag = false;

            if (amp > 1) {
                flag = true;
            } else if (amp > 0 && (source.is(net.minecraft.world.damagesource.DamageTypes.EXPLOSION)
                || source.is(net.minecraft.world.damagesource.DamageTypes.IN_FIRE))) {
                flag = true;
            } else {
                flag = (source.getEntity() != null);
            }

            if (flag) {
                target.heal(amount * amp);
                playSuzuSound(target);
                succeed = true;
            }
        }

        return succeed;
    }

    private void playMetalSound(LivingEntity target) {
        SoundEvent metal = net.minecraftforge.registries.ForgeRegistries.SOUND_EVENTS
            .getValue(new net.minecraft.resources.ResourceLocation(mods.defeatedcrow.common.DCsAppleMilk.MODID, "metal"));
        SoundEvent use = metal != null ? metal : SoundEvents.NOTE_BLOCK_BELL.value();
        target.level().playSound(null, target.blockPosition(), use, SoundSource.PLAYERS, 1.0F,
            0.5F + target.level().random.nextFloat());
    }

    private void playSuzuSound(LivingEntity target) {
        SoundEvent suzu = net.minecraftforge.registries.ForgeRegistries.SOUND_EVENTS
            .getValue(new net.minecraft.resources.ResourceLocation(mods.defeatedcrow.common.DCsAppleMilk.MODID, "suzu"));
        SoundEvent use = suzu != null ? suzu : SoundEvents.NOTE_BLOCK_CHIME.value();
        target.level().playSound(null, target.blockPosition(), use, SoundSource.PLAYERS, 1.0F,
            0.5F + target.level().random.nextFloat());
    }
}
