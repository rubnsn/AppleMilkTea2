package mods.defeatedcrow.common.block.container;

import net.minecraft.client.renderer.texture.BlockIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import mods.defeatedcrow.common.DCsAppleMilk;

public class BlockContainerWaterBottle extends BlockContainerBase {

    public BlockContainerWaterBottle() {
        super();
    }

    @Override
    public ItemStack returnItem() {
        return new ItemStack(Items.potionitem, 1, 0);
    }

    @Override
    public int getRenderType() {
        return DCsAppleMilk.modelCWBottle;
    }

    @Override
    
    public void registerBlockTextures(BlockIconRegister par1IconRegister) {
        this.bottomIcon = par1IconRegister.registerIcon("defeatedcrow:x32/basket_B1");
        this.sideIcon = par1IconRegister.registerIcon("defeatedcrow:x32/basket_S1");
        this.topIcon = par1IconRegister.registerIcon("defeatedcrow:x32/basket_T1");
        this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:containeritem_bottleW");
    }

}
