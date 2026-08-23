package mods.defeatedcrow.api.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

/**
 * ただ単に継承して使えるようにするだけのもの。 <br>
 * ImmunityBaseと違い、LivingEntityに影響がある。 <br>
 * 1.20.1: EntityLivingBase → LivingEntity、Potion ID 引数廃止 → MobEffectCategory。
 */
public abstract class PotionLivingBase extends PotionBaseAMT {

    protected PotionLivingBase(MobEffectCategory category, int color, int x, int y) {
        super(category, color, x, y);
    }

    /**
     * @param amp
     *               : このポーション効果のAmplifier
     * @param id
     *               : 旧PotionID (1.20.1では互換のため維持、未使用)
     * @param living
     *               : 監視対象のEntity <br>
     *               このメソッド内でTick毎の処理を行い、成否判定をboolean型で返して下さい。 <br>
     *               返されるbooleanは成功時のログ出力にのみ影響し、どちらでもプレイは続行されます。
     */
    public abstract boolean formPotionEffect(int amp, int id, LivingEntity living);

}
