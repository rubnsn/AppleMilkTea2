package mods.defeatedcrow.potion;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
public class PotionReflex extends MobEffect {
    public PotionReflex() { super(MobEffectCategory.BENEFICIAL, 0xFFFFFF); }
    public PotionReflex(MobEffectCategory c, int col) { super(c, col); }
}
