package mods.defeatedcrow.potion;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
public class PotionConfinement extends MobEffect {
    public PotionConfinement(){ super(MobEffectCategory.BENEFICIAL, 0xFFFFFF); }
    protected PotionConfinement(MobEffectCategory c,int col){ super(c,col); }
}
