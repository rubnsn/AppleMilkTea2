package mods.defeatedcrow.common.item.appliance;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

/**
 * WT-A 1.20.1 mojmap migration for ItemGrater.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemGrater extends Item {
    public ItemGrater(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (truncated, full in git history):
     * package mods.defeatedcrow.common.item.appliance;
     * 
     * import java.util.Random;
     * 
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.src.*;
     * 
     * public class ItemGrater extends Item {
     * 
     *     public ItemGrater() {
     *         super();
     *         this.setMaxStackSize(1);
     *         this.setMaxDamage(64);
     *         this.setNoRepair();
     *     }
     * 
     *     @Override
     *     public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
     *         return false;
     *     }
     * 
     *     @Override
     *     public boolean hasContainerItem(ItemStack stack) {
     *         return true;
     *     }
     * 
     *     @Override
     *     public ItemStack getContainerItem(ItemStack item) {
     *         if (item.getItem() == this) {
     *             Random rand = Item.itemRand;
     *             boolean flag = item.attemptDamageItem(1, rand);
     *             return flag ? null : item;
     *         }
     *         return super.getContainerItem(item);
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     *         this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:grater");
     *     }
     * 
     * }
     */
}
