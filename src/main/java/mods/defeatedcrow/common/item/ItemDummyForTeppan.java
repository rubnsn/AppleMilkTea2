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
 * WT-A 1.20.1 mojmap migration for ItemDummyForTeppan.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemDummyForTeppan extends Item {
    public ItemDummyForTeppan(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (truncated, full in git history):
     * package mods.defeatedcrow.common.item;
     * 
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * 
     * public class ItemDummyForTeppan extends Item {
     * 
     *     
     *     private BlockTexture iconItemType[];
     * 
     *     public ItemDummyForTeppan() {
     *         super();
     *         this.setMaxStackSize(1);
     *         this.setMaxDamage(0);
     *         this.setHasSubtypes(true);
     *     }
     * 
     *     
     *     public BlockTexture getBlockTextureFromDamage(int par1) {
     *         int j = MathHelper.clamp_int(par1, 0, 1);
     *         return this.iconItemType[j];
     *     }
     * 
     *     @Override
     *     public int getMetadata(int par1) {
     *         return par1;
     *     }
     * 
     *     @Override
     *     public String getUnlocalizedName(ItemStack par1ItemStack) {
     *         int j = MathHelper.clamp_int(par1ItemStack.getDamageValue(), 0, 1);
     *         return super.getUnlocalizedName() + "_" + j;
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     *         this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:teppan_dummy");
     *         this.iconItemType = new BlockTexture[2];
     *         this.iconItemType[0] = par1IconRegister.registerIcon("defeatedcrow:teppan_dummy");
     *         this.iconItemType[1] = par1IconRegister.registerIcon("defeatedcrow:teppan_dummy_oven");
     *     }
     * }
     */
}
