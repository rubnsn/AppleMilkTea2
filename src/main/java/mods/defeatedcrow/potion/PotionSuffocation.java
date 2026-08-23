package mods.defeatedcrow.potion;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
public class PotionSuffocation extends MobEffect {
    public PotionSuffocation() { super(MobEffectCategory.BENEFICIAL, 0xFFFFFF); }
    public PotionSuffocation(MobEffectCategory c, int col) { super(c, col); }
}
