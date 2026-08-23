package mods.defeatedcrow.api.edibles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

/**
 * 食べられるアイテムの作成を補助するためのクラス。 <br>
 * 同じ処理（容器返却処理）を複数のアイテムに重複して追加するのは面倒だし見た目も良くないので作成。 <br>
 * デフォルト動作では、返却容器はなし、飲食時の効果は空腹度回復のポーション効果である。 <br>
 * 1.20.1: onEaten/onItemRightClick → finishUsingItem/use, EnumAction → UseAnim.
 */
public class EdibleItem extends Item implements IEdibleItem {

    public EdibleItem(Properties properties) {
        super(properties);
    }

    /**
     * 食べる動作
     */
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        int meta = stack.getDamageValue();

        if (entity instanceof Player player) {
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            this.returnItemStack(player, meta);

            if (!level.isClientSide) {
                List<MobEffectInstance> potion = this.effectOnEaten(player, meta);
                if (potion != null && !potion.isEmpty()) {
                    for (MobEffectInstance ret : potion) {
                        player.addEffect(ret);
                    }
                }

                int[] h = this.hungerOnEaten(meta);
                if (h != null && h.length >= 2) {
                    player.getFoodData().eat(h[0], h[1]);
                }
            }
        }

        return stack;
    }

    /**
     * ガリガリ咀嚼する時間
     */
    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    /**
     * 飲食時のエフェクト。
     */
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }

    /**
     * 右クリック動作時に飲食効果を呼び出すメソッド。
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    /**
     * 空容器の返却を行うメソッド。
     */
    protected boolean returnItemStack(Player player, int meta) {
        ItemStack ret = this.getReturnContainer(meta);
        if (ret != null && !ret.isEmpty()) {
            if (!player.getInventory().add(ret)) {
                player.drop(ret, false);
                return true;
            }
        }
        return false;
    }

    /**
     * 返却される空容器をメタデータ毎に定義する。デフォルトでは返却なし。
     */
    @Override
    public ItemStack getReturnContainer(int meta) {
        return ItemStack.EMPTY;
    }

    /**
     * 飲食時のポーション効果をメタデータ毎に定義する。 <br>
     * 注意点として、ItemFoodのような空腹度回復効果ではなく、
     * ポーション効果のSaturationを利用して空腹度回復を行っている。
     */
    @Override
    public List<MobEffectInstance> effectOnEaten(Player player, int meta) {

        List<MobEffectInstance> ret = new ArrayList<MobEffectInstance>();
        // 1.7.10: Potion.field_76443_y == Saturation
        ret.add(new MobEffectInstance(MobEffects.SATURATION, 2, 2));
        return ret;
    }

    @Override
    public int[] hungerOnEaten(int meta) {
        return new int[] { 4, 2 };
    }

}
