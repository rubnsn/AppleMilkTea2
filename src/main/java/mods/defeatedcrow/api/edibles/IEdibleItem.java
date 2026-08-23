package mods.defeatedcrow.api.edibles;

import java.util.List;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * 1.20.1: PotionEffect → MobEffectInstance, ArrayList → List, EntityPlayer → Player.
 * 返却容器がない場合は ItemStack.EMPTY を返すこと (null 不可)。
 */
public interface IEdibleItem {

    /**
     * 飲食後に返ってくる空容器。返却なしの場合は ItemStack.EMPTY。
     */
    ItemStack getReturnContainer(int meta);

    /**
     * 飲食時のポーション効果。効果なしの場合は空リスト。
     */
    List<MobEffectInstance> effectOnEaten(Player player, int meta);

    /**
     * 飲食時の空腹度回復。int[]{nutrition, saturationModifier}。
     */
    int[] hungerOnEaten(int meta);

}
