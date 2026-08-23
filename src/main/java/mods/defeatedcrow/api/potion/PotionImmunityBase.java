package mods.defeatedcrow.api.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.player.Player;

/**
 * ただ単に継承して使えるようにするだけのもの。 <br>
 * Immunityとは名ばかりで、実際はかかっている間はTick毎に一回ずつ処理が呼び出される。 <br>
 * したがって実情はプレイヤーの常時監視用システムである。 <br>
 * 1.20.1: EntityPlayer → Player、Potion ID 引数廃止 → MobEffectCategory。
 */
public abstract class PotionImmunityBase extends PotionBaseAMT {

    protected PotionImmunityBase(MobEffectCategory category, int color, int x, int y) {
        super(category, color, x, y);
    }

    /**
     * @param amp
     *               : このポーション効果のAmplifier
     * @param id
     *               : 旧PotionID (1.20.1では互換のため維持、未使用)
     * @param player
     *               : 監視対象のプレイヤー <br>
     *               このメソッド内でTick毎の処理を行い、成否判定をboolean型で返して下さい。 <br>
     *               返されるbooleanは成功時のログ出力にのみ影響し、どちらでもプレイは続行されます。
     */
    public abstract boolean preventPotion(int amp, int id, Player player);

}
