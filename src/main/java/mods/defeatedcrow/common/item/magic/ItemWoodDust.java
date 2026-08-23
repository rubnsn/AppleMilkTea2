package mods.defeatedcrow.common.item.magic;

import java.util.List;

import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockTexture;
import net.minecraft.util.MathHelper;

public class ItemWoodDust extends Item {

    
    private BlockTexture iconType[];

    private static final String[] itemType = new String[] { "wood", "charcoal", "ash", "presscake" };

    public ItemWoodDust() {
        super();
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setMaxStackSize(64);

    }

    
    public BlockTexture getBlockTextureFromDamage(int par1) {
        int j = MathHelper.clamp_int(par1, 0, 3);
        return this.iconType[j];
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        int meta = par1ItemStack.getItemDamage();
        return meta < 4 ? super.getUnlocalizedName() + "_" + this.itemType[meta]
            : super.getUnlocalizedName() + "_" + meta;
    }

    @Override
    
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(this, 1, 0));
        par3List.add(new ItemStack(this, 1, 1));
        par3List.add(new ItemStack(this, 1, 2));
        par3List.add(new ItemStack(this, 1, 3));
    }

    @Override
    
    public void registerIcons(BlockIconRegister par1IconRegister) {
        this.iconType = new BlockTexture[4];

        for (int i = 0; i < 4; ++i) {
            this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:dust_" + itemType[i]);
        }
    }
}
