package mods.defeatedcrow.common.item;

import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockTexture;
import net.minecraft.util.MathHelper;

public class ItemDummyForTeppan extends Item {

    
    private BlockTexture iconItemType[];

    public ItemDummyForTeppan() {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    
    public BlockTexture getBlockTextureFromDamage(int par1) {
        int j = MathHelper.clamp_int(par1, 0, 1);
        return this.iconItemType[j];
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        int j = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 1);
        return super.getUnlocalizedName() + "_" + j;
    }

    @Override
    
    public void registerIcons(BlockIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:teppan_dummy");
        this.iconItemType = new BlockTexture[2];
        this.iconItemType[0] = par1IconRegister.registerIcon("defeatedcrow:teppan_dummy");
        this.iconItemType[1] = par1IconRegister.registerIcon("defeatedcrow:teppan_dummy_oven");
    }
}
