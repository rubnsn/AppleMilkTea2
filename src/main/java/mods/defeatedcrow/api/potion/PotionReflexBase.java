package mods.defeatedcrow.api.potion;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

/**
 * ただ単に継承して使えるようにするだけのもの。 <br>
 * 継承しているポーション効果は、
 * プレイヤーを含むLivingEntityのダメージ処理時に呼び出される。(amplifier現象機能は廃止済み) <br>
 * 呼び出されたあとの処理はこのクラス内で定義できます。 <br>
 * 1.20.1: PotionEffect → MobEffectInstance、EntityLivingBase → LivingEntity。
 */
public abstract class PotionReflexBase extends PotionBaseAMT {

    public boolean endlessly = false;

    protected PotionReflexBase(MobEffectCategory category, int color, boolean isInfinity, int x, int y) {
        super(category, color, x, y);
        this.endlessly = isInfinity;
    }

    /**
     * @param target
     *               : 攻撃を受けたLivingEntity
     * @param source
     *               : 受けた攻撃のダメージソース
     * @param effect
     *               : このポーション効果
     * @param amount
     *               : 受けたダメージの値 <br>
     *               このメソッド内でダメージを受けた瞬間の処理を行い、成否判定をboolean型で返して下さい。 <br>
     *               trueを返した場合のみ、ダメージの無効化が行われます。
     */
    public abstract boolean effectFormer(LivingEntity target, DamageSource source, MobEffectInstance effect,
        float amount);

}
