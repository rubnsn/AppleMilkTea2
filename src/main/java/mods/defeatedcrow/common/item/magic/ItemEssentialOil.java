package mods.defeatedcrow.common.item.magic;

import java.util.List;

import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockTexture;
import net.minecraft.util.MathHelper;

public class ItemEssentialOil extends Item {

    
    private BlockTexture iconType[];

    private static final String[] itemType = new String[] { "apple", "rose", "mint", "yuzu", "clam", "ice", "lavender",
        "vanilla", "sandalwood", "aloeswood", "frankincense" };

    public ItemEssentialOil() {
        super();
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setMaxStackSize(64);

    }

    
    public BlockTexture getBlockTextureFromDamage(int par1) {
        int j = MathHelper.clamp_int(par1, 0, 10);
        return this.iconType[j];
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        int meta = par1ItemStack.getItemDamage();
        return meta < 11 ? super.getUnlocalizedName() + "_" + this.itemType[meta]
            : super.getUnlocalizedName() + "_" + meta;
    }

    @Override
    
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(this, 1, 0));
        par3List.add(new ItemStack(this, 1, 1));
        par3List.add(new ItemStack(this, 1, 2));
        par3List.add(new ItemStack(this, 1, 3));
        par3List.add(new ItemStack(this, 1, 4));
        par3List.add(new ItemStack(this, 1, 5));
        par3List.add(new ItemStack(this, 1, 6));
        par3List.add(new ItemStack(this, 1, 7));
        par3List.add(new ItemStack(this, 1, 8));
        par3List.add(new ItemStack(this, 1, 9));
        par3List.add(new ItemStack(this, 1, 10));
    }

    @Override
    
    public void registerIcons(BlockIconRegister par1IconRegister) {
        this.iconType = new BlockTexture[11];

        for (int i = 0; i < 11; ++i) {
            this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:essence_" + itemType[i]);
        }
    }
}
