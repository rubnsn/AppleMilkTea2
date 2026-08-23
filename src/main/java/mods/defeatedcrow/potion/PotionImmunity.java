package mods.defeatedcrow.potion;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
public class PotionImmunity extends MobEffect {
    protected PotionImmunity(MobEffectCategory cat, int col) { super(cat, col); }
    public PotionImmunity() { super(MobEffectCategory.BENEFICIAL, 0xFFFFFF); }
}
