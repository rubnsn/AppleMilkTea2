package mods.defeatedcrow.common.item.edible;

import java.util.List;

import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.BlockTexture;
import net.minecraft.util.MathHelper;
import mods.defeatedcrow.*;

public class ItemCondensedMilk extends ItemFood {

    
    private BlockTexture iconType[];

    private static final String[] icon = new String[] { "condensedmilk", "preserve_cassis", "sauce_mint",
        "marmalade_yuzu" };

    public ItemCondensedMilk(int reco, int sat, boolean flag) {
        super(reco, sat, flag);
        this.setMaxStackSize(64);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
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
        return super.getUnlocalizedName() + "_" + par1ItemStack.getItemDamage();
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
            this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:" + icon[i]);
        }
    }

}
