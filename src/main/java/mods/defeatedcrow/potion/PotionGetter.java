package mods.defeatedcrow.potion;

import java.util.HashMap;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;

import mods.defeatedcrow.common.registry.ModMobEffects;

/**
 * 1.20.1: Potion(int ID) 廃止 → Holder&lt;MobEffect&gt;。IPotionGetter(api凍結) は外した (WT0へエスカレーション済み)。
 */
public class PotionGetter {

    private static HashMap<String, Holder<MobEffect>> potionMap = new HashMap<String, Holder<MobEffect>>();

    public Holder<MobEffect> AMTgetPotion(String name) {
        if (potionMap.containsKey(name)) {
            return potionMap.get(name);
        }
        return null;
    }

    public static void initialize() {
        potionMap.put("immunization", ModMobEffects.IMMUNIZATION);
        potionMap.put("projectile_resist", ModMobEffects.PROJECTILE_RESIST);
        potionMap.put("explosion_resist", ModMobEffects.EXPLOSION_RESIST);
        potionMap.put("reflex", ModMobEffects.REFLEX);
        potionMap.put("absorb_heal", ModMobEffects.ABS_HEAL);
        potionMap.put("absorb_exp", ModMobEffects.ABS_EXP);
        potionMap.put("suffocation", ModMobEffects.SUFFOCATION);
        potionMap.put("suffocation_resist", ModMobEffects.SUFFOCATION_RESIST);
        potionMap.put("hallucination", ModMobEffects.HALLUCINATION);
    }

    private static Holder<MobEffect> checkIsAdded(Holder<MobEffect> effect) {
        return effect == null ? BuiltInRegistries.MOB_EFFECT.wrapAsHolder(net.minecraft.world.effect.MobEffects.REGENERATION.value()) : effect;
    }

}
