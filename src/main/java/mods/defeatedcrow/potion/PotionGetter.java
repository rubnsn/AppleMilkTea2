package mods.defeatedcrow.potion;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
public class PotionGetter extends MobEffect {
    protected PotionGetter(MobEffectCategory cat, int col) { super(cat, col); }
    public PotionGetter() { super(MobEffectCategory.BENEFICIAL, 0xFFFFFF); }
}
