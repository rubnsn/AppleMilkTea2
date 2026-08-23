package mods.defeatedcrow.common.item.edible;

import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.item.ItemFood;
import net.minecraft.src.*;
import mods.defeatedcrow.*;

public class ItemBakedApple extends ItemFood {

    public ItemBakedApple(int reco, int sat, boolean flag) {
        super(reco, sat, flag);
        maxStackSize = 64;

    }

    @Override
    
    public void registerIcons(BlockIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:bakedapple");
    }

}
