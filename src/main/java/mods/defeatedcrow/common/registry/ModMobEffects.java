package mods.defeatedcrow.common.registry;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import mods.defeatedcrow.potion.PotionConfinement;
import mods.defeatedcrow.potion.PotionHallucination;
import mods.defeatedcrow.potion.PotionImmunity;
import mods.defeatedcrow.potion.PotionProtectionEX;
import mods.defeatedcrow.potion.PotionReflex;
import mods.defeatedcrow.potion.PotionSuffocation;

/**
 * 1.20.1 MobEffect registry - replaces Potion (int ID 128 extension).
 * See doc/potions/migration-guide.md
 * WT-C owns all 10 effects.
 */
public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "defeatedcrow");

    // --- WT-C: MOB EFFECTS (10) ---
    public static final RegistryObject<MobEffect> IMMUNIZATION = MOB_EFFECTS.register("immunization",
        () -> new PotionImmunity(net.minecraft.world.effect.MobEffectCategory.BENEFICIAL, 0xFEC0C0));

    public static final RegistryObject<MobEffect> PROJECTILE_RESIST = MOB_EFFECTS.register("projectile_resist",
        () -> new PotionProtectionEX(net.minecraft.world.effect.MobEffectCategory.BENEFICIAL, 0xC0C0FE).setProtectProj());

    public static final RegistryObject<MobEffect> EXPLOSION_RESIST = MOB_EFFECTS.register("explosion_resist",
        () -> new PotionProtectionEX(net.minecraft.world.effect.MobEffectCategory.BENEFICIAL, 0xFEBE6A).setProtectExplode());

    public static final RegistryObject<MobEffect> REFLEX = MOB_EFFECTS.register("reflex",
        () -> new PotionReflex(net.minecraft.world.effect.MobEffectCategory.BENEFICIAL, 0xFFE9C8));

    public static final RegistryObject<MobEffect> ABS_EXP = MOB_EFFECTS.register("absorb_exp",
        () -> new PotionReflex(net.minecraft.world.effect.MobEffectCategory.BENEFICIAL, 0xD2FF7F));

    public static final RegistryObject<MobEffect> ABS_HEAL = MOB_EFFECTS.register("absorb_heal",
        () -> new PotionReflex(net.minecraft.world.effect.MobEffectCategory.BENEFICIAL, 0xFEA3A3));

    public static final RegistryObject<MobEffect> SUFFOCATION = MOB_EFFECTS.register("suffocation",
        () -> new PotionSuffocation(net.minecraft.world.effect.MobEffectCategory.HARMFUL, 0x4C4C4C));

    public static final RegistryObject<MobEffect> SUFFOCATION_RESIST = MOB_EFFECTS.register("suffocation_resist",
        () -> new PotionProtectionEX(net.minecraft.world.effect.MobEffectCategory.BENEFICIAL, 0xA0A0A0).setProtectSuffocation());

    public static final RegistryObject<MobEffect> HALLUCINATION = MOB_EFFECTS.register("hallucination",
        () -> new PotionHallucination(net.minecraft.world.effect.MobEffectCategory.HARMFUL, 0xB04CFE));

    public static final RegistryObject<MobEffect> CONFINEMENT = MOB_EFFECTS.register("confinement",
        () -> new PotionConfinement(net.minecraft.world.effect.MobEffectCategory.HARMFUL, 0x808080));

    public static Holder<MobEffect> holder(RegistryObject<MobEffect> ro) {
        return ro.getHolder().orElseThrow();
    }

    private ModMobEffects() {}
}
