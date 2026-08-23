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
 * WT-A 1.20.1 mojmap migration for ItemIcyCrystal.
 * Former 1.7.10 IItem with subtypes/meta -> NBT or split RegistryObject (see ModItems).
 * Textures: JSON models under assets/defeatedcrow/models/item/
 */
public class ItemIcyCrystal extends Item {
    public ItemIcyCrystal(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        // TODO: restore addInformation logic
        super.appendHoverText(stack, level, tooltip, flag);
    }

    /*
     * Original 1.7.10 source (truncated, full in git history):
     * package mods.defeatedcrow.common.item.appliance;
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
     * public class ItemIcyCrystal extends Item {
     * 
     *     
     *     private BlockTexture iconType[];
     *     private static final String[] itemType = new String[] { "_blink", "_orb", "_cloud", "_flower", "_feather" };
     * 
     *     public ItemIcyCrystal() {
     *         super();
     *         this.setMaxStackSize(64);
     *         this.setMaxDamage(0);
     *         this.setHasSubtypes(true);
     *     }
     * 
     *     @Override
     *     
     *     public BlockTexture getBlockTextureFromDamage(int par1) {
     *         int j = MathHelper.clamp_int(par1, 0, 5);
     *         return j > 0 ? this.iconType[j - 1] : this.itemIcon;
     *     }
     * 
     *     @Override
     *     public int getMetadata(int par1) {
     *         return par1;
     *     }
     * 
     *     @Override
     *     public String getUnlocalizedName(ItemStack par1ItemStack) {
     *         return super.getUnlocalizedName() + "_" + par1ItemStack.getDamageValue();
     *     }
     * 
     *     @Override
     *     
     *     public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
     *         par3List.add(new ItemStack(this, 1, 0));
     *         // par3List.add(new ItemStack(this, 1, 1));
     *         // par3List.add(new ItemStack(this, 1, 2));
     *         // par3List.add(new ItemStack(this, 1, 3));
     *         // par3List.add(new ItemStack(this, 1, 4));
     *         // par3List.add(new ItemStack(this, 1, 5));
     *     }
     * 
     *     @Override
     *     
     *     public void registerIcons(BlockIconRegister par1IconRegister) {
     * 
     *         this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:icycrystal");
     *         this.iconType = new BlockTexture[5];
     *         for (int i = 0; i < 5; ++i) {
     *             this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:particle" + itemType[i]);
     */
}
