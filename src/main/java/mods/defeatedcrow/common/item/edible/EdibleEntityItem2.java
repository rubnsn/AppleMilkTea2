package mods.defeatedcrow.common.item.edible;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

/**
 * WT-A 1.20.1 mojmap migration for EdibleEntityItem2.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class EdibleEntityItem2 extends Item {
    public EdibleEntityItem2(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic
        super.appendHoverText(stack, level, tooltip, flag);
    }

    // 1.7.10 ItemFood -> 1.20.1 FoodProperties in ModItems registration (Item.Properties.food(...))
    // finishUsingItem replaces onEaten/onFoodEaten
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, net.minecraft.world.entity.LivingEntity entity) {
        return super.finishUsingItem(stack, level, entity);
    }

    /*
     * Original 1.7.10 source (truncated, full in git history):
     * package mods.defeatedcrow.common.item.edible;
     * 
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.item.ItemStack;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.plugin.LoadAppleCorePlugin;
     * import squeek.applecore.api.food.FoodValues;
     * import squeek.applecore.api.food.IEdible;
     * 
     * /* クラッシュ回避用の中継クラス * /
     * @Optional.Interface(iface = "squeek.applecore.api.food.IEdible", modid = "AppleCore")
     * public class EdibleEntityItem2 extends EdibleEntityItem implements IEdible {
     * 
     *     public EdibleEntityItem2(boolean chopsticks, boolean tip) {
     *         super(chopsticks, tip);
     *     }
     * 
     *     /**
     *      * AppleCore連携用、IEdibleの実装メソッド。
     *      * /
     *     @Override
     *     @Optional.Method(modid = "AppleCore")
     *     public FoodValues getFoodValues(ItemStack itemStack) {
     *         int[] h = this.hungerOnEaten(itemStack.getItemDamage());
     *         return new FoodValues(h[0], h[1] * 0.1F);
     *     }
     * 
     *     @Override
     *     protected void addStatus(EntityPlayer player, int[] heal, ItemStack food) {
     *         if (DCsAppleMilk.SuccessLoadACore) {
     *             LoadAppleCorePlugin.addFoodStatus(player, food);
     *         } else {
     *             player.getFoodStats()
     *                 .addStats(heal[0], heal[1] * 0.1F);
     *         }
     *     }
     * }
     */
}
