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
 * WT-A 1.20.1 mojmap migration for ItemDummyForTooltip.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemDummyForTooltip extends Item {
    public ItemDummyForTooltip(Properties properties) {
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
     * import net.minecraft.entity.player.EntityPlayer;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.nbt.CompoundTag;
     * 
     * public class ItemDummyForTooltip extends Item {
     * 
     *     public ItemDummyForTooltip() {
     *         super();
     *         this.setMaxStackSize(1);
     *     }
     * 
     *     
     *     // マウスオーバー時の表示情報
     *     public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
     *         super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
     *         CompoundTag nbt = par1ItemStack.getTagCompound();
     *         String name = "Empty";
     *         short s = 0;
     *         if (nbt != null && nbt.hasKey("fluid")) {
     *             name = nbt.getString("fluid");
     *         }
     *         if (nbt != null && nbt.hasKey("amount")) {
     *             s = nbt.getShort("amount");
     *         }
     *         par3List.add(new String(name + " " + s + "mB"));
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     *         this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:dummy");
     *     }
     * }
     */
}
