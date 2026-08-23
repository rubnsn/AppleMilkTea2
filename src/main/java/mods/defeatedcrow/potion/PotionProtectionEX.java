package mods.defeatedcrow.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.damagesource.DamageSource;

/**
 * 中身はゲッターとセッターだけ。実際の動作はDamage時のイベントに依存する (WT-B: DCsHurtEvent)。
 * 1.20.1: PotionBaseAMT(api凍結) → MobEffect 直接継承。
 */
public class PotionProtectionEX extends MobEffect {

    private boolean allProtection = false;

    private boolean projectileProtection = false;

    private boolean explodeProtection = false;

    private boolean suffocationProtection = false;

    private DamageSource preventSource;

    public PotionProtectionEX(MobEffectCategory category, int color) {
        super(category, color);
    }

    public PotionProtectionEX setAllProtection() {
        this.allProtection = true;
        return this;
    }

    public PotionProtectionEX setProtectProj() {
        this.projectileProtection = true;
        return this;
    }

    public PotionProtectionEX setProtectExplode() {
        this.explodeProtection = true;
        return this;
    }

    public PotionProtectionEX setProtectSuffocation() {
        this.suffocationProtection = true;
        return this;
    }

    public PotionProtectionEX setPreventSource(DamageSource par1Source) {
        this.preventSource = par1Source;
        return this;
    }

    public boolean getAllProtection() {
        return this.allProtection;
    }

    public boolean getProjProtection() {
        return this.projectileProtection;
    }

    public boolean getExplodeProtection() {
        return this.explodeProtection;
    }

    public boolean getSuffocationProtection() {
        return this.suffocationProtection;
    }

    public DamageSource getPreventSource() {
        return this.preventSource;
    }
}
