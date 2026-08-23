package mods.defeatedcrow.common.item;

import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.item.Item;

public class ItemCarbonStick extends Item {

    public ItemCarbonStick() {
        super();
        maxStackSize = 64;

    }

    @Override
    
    public void registerIcons(BlockIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:stick_carbon");
    }

}
