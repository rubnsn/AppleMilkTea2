package mods.defeatedcrow.common.item.edible;

import java.util.List;

import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.BlockTexture;
import net.minecraft.util.MathHelper;
import mods.defeatedcrow.common.*;

public class ItemYeast extends Item {

    
    private BlockTexture iconType[];

    private static final String[] itemType = new String[] { "yeast", "CO2_cylinder" };

    public ItemYeast() {
        super();
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setMaxStackSize(64);

    }

    
    public BlockTexture getBlockTextureFromDamage(int par1) {
        int j = MathHelper.clamp_int(par1, 0, 1);
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
    }

    @Override
    
    public void registerIcons(BlockIconRegister par1IconRegister) {
        this.iconType = new BlockTexture[2];

        for (int i = 0; i < 2; ++i) {
            this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:" + itemType[i]);
        }
    }

}
