package mods.defeatedcrow.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.src.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.*;

public class ItemInkStick extends Item {

    public ItemInkStick() {
        super();
        maxStackSize = 64;

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:inkstick");
    }

}
