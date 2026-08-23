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
 * WT-A 1.20.1 mojmap migration for ItemOreDust.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemOreDust extends Item {
    public ItemOreDust(Properties properties) {
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
     * import net.minecraft.world.item.CreativeModeTab;
     * import net.minecraft.world.item.Item;
     * import net.minecraft.world.item.ItemStack;
     * import net.minecraft.util.BlockTexture;
     * import net.minecraft.util.MathHelper;
     * 
     * public class ItemOreDust extends Item {
     * 
     *     
     *     private BlockTexture iconType[];
     * 
     *     private static final String[] itemType = new String[] { "iron", "tin", "copper", "silver", "lead", "gold", "nickel",
     *         "platinum" };
     * 
     *     public ItemOreDust() {
     *         super();
     *         this.setMaxDamage(0);
     *         this.setHasSubtypes(true);
     *         this.setMaxStackSize(64);
     * 
     *     }
     * 
     *     
     *     public BlockTexture getBlockTextureFromDamage(int par1) {
     *         int j = MathHelper.clamp_int(par1, 0, 7);
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
     *         int meta = par1ItemStack.getDamageValue();
     *         return meta < 8 ? super.getUnlocalizedName() + "_" + this.itemType[meta]
     *             : super.getUnlocalizedName() + "_" + meta;
     *     }
     * 
     *     @Override
     *     
     *     public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
     *         for (int i = 0; i < 8; i++) {
     *             par3List.add(new ItemStack(this, 1, i));
     *         }
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     *         this.iconType = new BlockTexture[8];
     * 
     *         for (int i = 0; i < 8; ++i) {
     *             this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:oredust_" + itemType[i]);
     */
}
