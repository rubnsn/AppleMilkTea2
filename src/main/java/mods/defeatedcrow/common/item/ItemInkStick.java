package mods.defeatedcrow.common.item;

import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.item.Item;
import net.minecraft.src.*;
import mods.defeatedcrow.*;

public class ItemInkStick extends Item {

    public ItemInkStick() {
        super();
        maxStackSize = 64;

    }

    @Override
    
    public void registerIcons(BlockIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:inkstick");
    }

}
