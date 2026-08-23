package mods.defeatedcrow.potion;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
public class PotionHallucination extends MobEffect {
    protected PotionHallucination(MobEffectCategory cat, int col) { super(cat, col); }
    public PotionHallucination() { super(MobEffectCategory.BENEFICIAL, 0xFFFFFF); }
}
