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
 * WT-A 1.20.1 mojmap migration for ItemIcyToffyApple.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemIcyToffyApple extends Item {
    public ItemIcyToffyApple(Properties properties) {
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
     * import java.util.Iterator;
     * import java.util.List;
     * 
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.item.CreativeModeTab;
     * import net.minecraft.world.entity.player.Player;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemFood;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.potion.Potion;
     * import net.minecraft.world.effect.MobEffectInstance;
     * import net.minecraft.src.*;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * import net.minecraft.world.level.Level;
     * import mods.defeatedcrow.*;
     * import mods.defeatedcrow.api.potion.AMTPotionManager;
     * 
     * public class ItemIcyToffyApple extends ItemFood {
     * 
     *     
     *     private BlockTexture iconToffyType[];
     * 
     *     public ItemIcyToffyApple(int reco, int sat, boolean flag) {
     *         super(reco, sat, flag);
     *         this.setMaxDamage(0);
     *         this.setHasSubtypes(true);
     *         this.setMaxStackSize(64);
     *         this.setAlwaysEdible();
     * 
     *     }
     * 
     *     
     *     public BlockTexture getBlockTextureFromDamage(int par1) {
     *         int j = MathHelper.clamp_int(par1, 0, 6);
     *         return this.iconToffyType[j];
     *     }
     * 
     *     @Override
     *     public int getMetadata(int par1) {
     *         return par1;
     *     }
     * 
     *     @Override
     *     protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
     *         boolean alt = false;
     *         boolean alt2 = false;
     *         switch (par1ItemStack.getDamageValue()) {
     *             case 0:
     *                 par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 600, 0));
     *                 break;
     *             case 1:
     *                 par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.jump.id, 600, 1));
     *                 break;
     *             case 2:
     *                 par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 600, 0));
     *                 break;
     *             case 3:
     */
}
