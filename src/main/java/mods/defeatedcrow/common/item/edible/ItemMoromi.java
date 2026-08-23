package mods.defeatedcrow.common.item.edible;

import java.util.List;

import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockTexture;
import net.minecraft.util.MathHelper;

public class ItemMoromi extends Item {

    private static final String[] type = new String[] { "rice", "wort", "grape", "syrup", "potato" };
    
    private BlockTexture iconItemType[];

    public ItemMoromi() {
        super();
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setContainerItem(Items.bucket);
        maxStackSize = 64;

    }

    
    public BlockTexture getBlockTextureFromDamage(int par1) {
        int j = MathHelper.clamp_int(par1, 0, 4);
        return this.iconItemType[j];
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
        par3List.add(new ItemStack(this, 1, 4));
    }

    @Override
    
    public void registerIcons(BlockIconRegister par1IconRegister) {
        this.iconItemType = new BlockTexture[5];

        for (int i = 0; i < 5; ++i) {
            this.iconItemType[i] = par1IconRegister.registerIcon("defeatedcrow:moromi_" + type[i]);
        }
    }
}
