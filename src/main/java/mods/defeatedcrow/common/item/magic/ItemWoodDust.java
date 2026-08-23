package mods.defeatedcrow.common.item.magic;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

/**
 * WT-A 1.20.1 mojmap migration for ItemWoodDust.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemWoodDust extends Item {
    public ItemWoodDust(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (truncated, full in git history):
     * package mods.defeatedcrow.common.item.magic;
     * 
     * import java.util.List;
     * 
     * import net.minecraft.client.renderer.texture.BlockIconRegister;
     * import net.minecraft.creativetab.CreativeTabs;
     * import net.minecraft.item.Item;
     * import net.minecraft.item.ItemStack;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * 
     * public class ItemWoodDust extends Item {
     * 
     *     
     *     private BlockTexture iconType[];
     * 
     *     private static final String[] itemType = new String[] { "wood", "charcoal", "ash", "presscake" };
     * 
     *     public ItemWoodDust() {
     *         super();
     *         this.setMaxDamage(0);
     *         this.setHasSubtypes(true);
     *         this.setMaxStackSize(64);
     * 
     *     }
     * 
     *     
     *     public BlockTexture getBlockTextureFromDamage(int par1) {
     *         int j = MathHelper.clamp_int(par1, 0, 3);
     *         return this.iconType[j];
     *     }
     * 
     *     @Override
     *     public int getMetadata(int par1) {
     *         return par1;
     *     }
     * 
     *     @Override
     *     public String getUnlocalizedName(ItemStack par1ItemStack) {
     *         int meta = par1ItemStack.getItemDamage();
     *         return meta < 4 ? super.getUnlocalizedName() + "_" + this.itemType[meta]
     *             : super.getUnlocalizedName() + "_" + meta;
     *     }
     * 
     *     @Override
     *     
     *     public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
     *         par3List.add(new ItemStack(this, 1, 0));
     *         par3List.add(new ItemStack(this, 1, 1));
     *         par3List.add(new ItemStack(this, 1, 2));
     *         par3List.add(new ItemStack(this, 1, 3));
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     *         this.iconType = new BlockTexture[4];
     * 
     *         for (int i = 0; i < 4; ++i) {
     *             this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:dust_" + itemType[i]);
     */
}
