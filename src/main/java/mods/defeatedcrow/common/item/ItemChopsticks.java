package mods.defeatedcrow.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.BlockTexture;
import net.minecraft.util.MathHelper;
import mods.defeatedcrow.common.DCsAppleMilk;

public class ItemChopsticks extends Item {

    
    private BlockTexture iconType[];

    private static final String[] itemType = new String[] { "", "_2" };

    public ItemChopsticks() {
        super();
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        maxStackSize = 64;

    }

    @Override
    
    public BlockTexture getBlockTextureFromDamage(int par1) {
        int j = MathHelper.clamp_int(par1, 0, 2);
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
        for (int i = 0; i < 2; i++) {
            par3List.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    
    public void registerIcons(BlockIconRegister par1IconRegister) {
        this.iconType = new BlockTexture[2];

        for (int i = 0; i < 2; ++i) {
            this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:chopsticks" + itemType[i]);
        }
    }

    @Override
    
    // マウスオーバー時の表示情報
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        if (par1ItemStack != null && DCsAppleMilk.proxy.isShiftKeyDown()) { // shiftキー押下時
            int m = par1ItemStack.getItemDamage();
            if (m == 0) {
                par3List.add("This item is derived by ");
                par3List.add("right-click to the chopsticks holder.");
            } else {
                par3List.add("This item is derived by right-click");
                par3List.add("to the chopsticks holder with sneaking.");
            }
        } else {
            par3List.add(EnumChatFormatting.ITALIC + "LShift: Expand tooltip.");
        }
    }

}
