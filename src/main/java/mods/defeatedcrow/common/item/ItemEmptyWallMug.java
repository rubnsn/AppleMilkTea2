package mods.defeatedcrow.common.item;

import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.item.Item;

public class ItemEmptyWallMug extends Item {

    public ItemEmptyWallMug() {
        super();
        maxStackSize = 8;
    }

    @Override
    
    public void registerIcons(BlockIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:wallmug");
    }
}
