package mods.defeatedcrow.common.item.appliance;

import java.util.List;

import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockTexture;
import net.minecraft.util.MathHelper;

public class ItemIcyCrystal extends Item {

    
    private BlockTexture iconType[];
    private static final String[] itemType = new String[] { "_blink", "_orb", "_cloud", "_flower", "_feather" };

    public ItemIcyCrystal() {
        super();
        this.setMaxStackSize(64);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    
    public BlockTexture getBlockTextureFromDamage(int par1) {
        int j = MathHelper.clamp_int(par1, 0, 5);
        return j > 0 ? this.iconType[j - 1] : this.itemIcon;
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
        // par3List.add(new ItemStack(this, 1, 1));
        // par3List.add(new ItemStack(this, 1, 2));
        // par3List.add(new ItemStack(this, 1, 3));
        // par3List.add(new ItemStack(this, 1, 4));
        // par3List.add(new ItemStack(this, 1, 5));
    }

    @Override
    
    public void registerIcons(BlockIconRegister par1IconRegister) {

        this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:icycrystal");
        this.iconType = new BlockTexture[5];
        for (int i = 0; i < 5; ++i) {
            this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:particle" + itemType[i]);
        }

    }

}
