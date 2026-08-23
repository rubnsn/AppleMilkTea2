package mods.defeatedcrow.potion;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.player.Player;

/**
 * 幻覚ポーション。Tick毎に呼び出される。Amplifierごとに効果が悪化する。
 * 1.20.1: PotionImmunityBase(api凍結) → MobEffect 直接継承、旧文字列サウンドは SoundEvents 相当へ置換。
 */
public class PotionHallucination extends MobEffect {

    private final SoundEvent[] overworldMobs = new SoundEvent[] { SoundEvents.ZOMBIE_AMBIENT, SoundEvents.CREEPER_PRIMED,
        SoundEvents.ENDERMAN_STARE, SoundEvents.CREEPER_PRIMED, SoundEvents.SKELETON_AMBIENT, SoundEvents.SPIDER_AMBIENT,
        SoundEvents.ENDERMAN_AMBIENT };
    private final SoundEvent[] netherMobs = new SoundEvent[] { SoundEvents.BLAZE_AMBIENT, SoundEvents.GHAST_AMBIENT };

    public PotionHallucination(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        if (!(living instanceof Player player) || player.level().isClientSide()) return;

        Random rand = player.level().random;
        double x = player.getX() + rand.nextInt(7) - 3.0D;
        double y = player.getY();
        double z = player.getZ() + rand.nextInt(7) - 3.0D;
        int chance = rand.nextInt(200);
        float f = rand.nextFloat();

        SoundEvent[] voices = player.level().dimension() == net.minecraft.world.level.Level.NETHER ? netherMobs
            : overworldMobs;
        SoundEvent voice = voices[rand.nextInt(voices.length)];

        if (chance <= amplifier) {
            player.level().playSound(null, x, y, z, voice, SoundSource.HOSTILE, 1.0F, 0.8F + f);
        }

        if (amplifier > 0 && chance < amplifier) {
            BlockPos pos = BlockPos.containing(x, y, z);
            boolean a = player.level().getBlockState(pos.below()).isSolidRender(player.level(), pos.below());
            boolean b = player.level().getBlockState(pos).isAir();
            boolean c = player.level().getBlockState(pos.above()).isAir();

            if (a && b && c) {
                mods.defeatedcrow.common.entity.dummy.EntityIllusionMobs illusion = new mods.defeatedcrow.common.entity.dummy.EntityIllusionMobs(
                    player.level(), x, y, z, player.getYRot());
                illusion.moveTo(x, y, z, player.getYRot(), 0.0F);
                player.level().addFreshEntity(illusion);
                player.level().playSound(null, x, y, z, SoundEvents.CREEPER_PRIMED, SoundSource.HOSTILE, 1.0F,
                    0.8F + f);
            }
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 40 == 0;
    }
}
