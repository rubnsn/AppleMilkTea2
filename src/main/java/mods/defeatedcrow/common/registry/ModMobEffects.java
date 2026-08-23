package mods.defeatedcrow.common.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 1.20.1 MobEffect registry — replaces Potion (int ID 128 확장).
 * See doc/potions/migration-guide.md:1
 * WT-C owns all 10 effects (Immunization, prvExplode, prvProjectile, reflex, absEXP, absHeal, suffocation, prvSuffocation, hallucinations, confinement).
 */
public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "defeatedcrow");

    // --- WT-C: MOB EFFECTS (10) ---
    // public static final RegistryObject<MobEffect> IMMUNIZATION = MOB_EFFECTS.register("immunization",
    //     () -> new mods.defeatedcrow.potion.PotionImmunity(net.minecraft.world.effect.MobEffectCategory.BENEFICIAL, 0xFEC0C0));

    private ModMobEffects() {}
}
