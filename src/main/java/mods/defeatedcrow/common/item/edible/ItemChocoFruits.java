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
import mods.defeatedcrow.common.*;

public class ItemChocoFruits extends ItemFood {

    
    private BlockTexture iconType[];

    private static final String[] itemType = new String[] { "almond", "peanut", "crashednut", "strawberry", "cherry",
        "berry", "banana", "rice", "bread", "cookie", "truffle", "candy", "toffy", "plate" };

    public ItemChocoFruits(int reco, int sat, boolean flag) {
        super(reco, sat, flag);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setMaxStackSize(64);

    }

    
    public BlockTexture getBlockTextureFromDamage(int par1) {
        int j = MathHelper.clamp_int(par1, 0, 14);
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
        for (int i = 0; i < 14; i++) {
            par3List.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    
    public void registerIcons(BlockIconRegister par1IconRegister) {
        this.iconType = new BlockTexture[14];

        for (int i = 0; i < 14; ++i) {
            this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:choco_" + itemType[i]);
        }
    }

}
