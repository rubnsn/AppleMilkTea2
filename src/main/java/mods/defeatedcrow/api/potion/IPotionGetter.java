package mods.defeatedcrow.api.potion;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

/**
 * 1.20.1: Potion(int ID) 廃止 → Holder&lt;MobEffect&gt;。
 * PotionGetter (本体側) がこのインターフェイスを実装して HashMap を参照する。
 */
public interface IPotionGetter {

    /**
     * 未登録のStringを入れた場合はnullが返る。 <br>
     * また、前提MOD不足などで登録失敗していた場合は、代替として MobEffects.REGENERATION が返ることがある。 <br>
     * AMTで追加されているPotionのStringリストは、IPotionGetter.javaのコメントを参照のこと。
     */
    Holder<MobEffect> AMTgetPotion(String name);

    /*
     * AMT2 Potion String Table list
     * immunization
     * projectile_resist
     * explosion_resist
     * reflex
     * absorb_heal
     * absorb_exp
     * suffocation
     * suffocation_resist
     * hallucination
     */

}
