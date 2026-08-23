package mods.defeatedcrow.common.item.appliance;

import java.util.Random;

import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;

public class ItemGrater extends Item {

    public ItemGrater() {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(64);
        this.setNoRepair();
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
        return false;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack item) {
        if (item.getItem() == this) {
            Random rand = Item.itemRand;
            boolean flag = item.attemptDamageItem(1, rand);
            return flag ? null : item;
        }
        return super.getContainerItem(item);
    }

    @Override
    
    public void registerIcons(BlockIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:grater");
    }

}
