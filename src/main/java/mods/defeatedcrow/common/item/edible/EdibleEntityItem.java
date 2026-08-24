package mods.defeatedcrow.common.item.edible;

import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

/**
 * WT-A 1.20.1 mojmap migration for EdibleEntityItem.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class EdibleEntityItem extends Item {
    public EdibleEntityItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
    }

    // 1.7.10 ItemFood -> 1.20.1 FoodProperties in ModItems registration (Item.Properties.food(...))
    // finishUsingItem replaces onEaten/onFoodEaten
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, net.minecraft.world.entity.LivingEntity entity) {
        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public net.minecraft.world.InteractionResult useOn(net.minecraft.world.item.context.UseOnContext ctx) {
        return super.useOn(ctx);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return super.use(level, player, hand);
    }

    /*
     * Original 1.7.10 source (truncated, full in git history):
     * package mods.defeatedcrow.common.item.edible;
     * 
     * import java.util.ArrayList;
     * import java.util.List;
     * 
     * import net.minecraft.world.level.block.Block;
     * import net.minecraft.entity.Entity;
     * import net.minecraft.entity.EntityLivingBase;
     * import net.minecraft.world.entity.player.Player;
     * import net/minecraft/init/Blocks;
     * import net.minecraft.item.EnumAction;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.potion.Potion;
     * import net.minecraft.world.effect.MobEffectInstance;
     * import net.minecraft.util.StatCollector;
     * import net.minecraft.world.level.Level;
     * import net.minecraftforge.common.MinecraftForge;
     * import mods.defeatedcrow.api.edibles.IEdibleItem;
     * import mods.defeatedcrow.api.events.EatEdiblesEvent;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * import mods.defeatedcrow.common.config.DCsConfig;
     * import mods.defeatedcrow.plugin.SSector.LoadSSectorPlugin;
     * 
     * public class EdibleEntityItem extends Item implements IEdibleItem {
     * 
     *     public boolean allowChopstacks = true;
     *     public boolean showTooltip = true;
     * 
     *     public EdibleEntityItem(boolean chopsticks, boolean tip) {
     *         super();
     *         this.allowChopstacks = chopsticks;
     *         this.showTooltip = tip;
     *     }
     * 
     *     @Override
     *     public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
     *         int meta = par1ItemStack.getDamageValue();
     *         boolean flag = false;
     *         EatEdiblesEvent event = new EatEdiblesEvent(par2World, par3EntityPlayer, par1ItemStack);
     * 
     *         MinecraftForge.EVENT_BUS.post(event);
     * 
     *         if (event.hasResult() && event.getResult() == Result.ALLOW) {
     *             if (!par3EntityPlayer.capabilities.isCreativeMode) {
     *                 --par1ItemStack.getCount();
     *                 this.returnItemStack(par3EntityPlayer, meta);
     *             }
     *             flag = true;
     *         }
     * 
     *         if (event.isCanceled()) {
     *             return par1ItemStack;
     *         }
     * 
     *         if (!flag && !par3EntityPlayer.capabilities.isCreativeMode) {
     *             --par1ItemStack.getCount();
     *             this.returnItemStack(par3EntityPlayer, meta);
     *         }
     * 
     */
}