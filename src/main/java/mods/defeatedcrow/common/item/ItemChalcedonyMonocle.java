package mods.defeatedcrow.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

/**
 * WT-A 1.20.1 mojmap migration for ItemChalcedonyMonocle.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemChalcedonyMonocle extends Item {
    public ItemChalcedonyMonocle(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (truncated, full in git history):
     * package mods.defeatedcrow.common.item;
     * 
     * import java.util.List;
     * 
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.entity.Entity;
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.item.ItemArmor;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.util.EnumChatFormatting;
     * import mods.defeatedcrow.common.DCsAppleMilk;
     * 
     * public class ItemChalcedonyMonocle extends ItemArmor {
     * 
     *     public ItemChalcedonyMonocle(ArmorMaterial material, int index, int slot) {
     *         super(material, index, slot);
     *     }
     * 
     *     @Override
     *     public String getArmorTexture(ItemStack item, Entity entity, int slot, String type) {
     *         if (item.getItem() == DCsAppleMilk.monocle) {
     *             return "defeatedcrow:textures/armor/monocle.png";
     *         }
     *         return null;
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     *         this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:tools/monocle");
     *     }
     * 
     *     @Override
     *     
     *     public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
     *         super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
     *         if (par1ItemStack != null && DCsAppleMilk.proxy.isShiftKeyDown()) { // shiftキー押下時
     *             par3List.add("While you are wearing this item, ore dictionary names is displayed in it's tooltip.");
     *         } else {
     *             par3List.add(EnumChatFormatting.ITALIC + "LShift: Expand tooltip.");
     *         }
     *     }
     * }
     */
}
