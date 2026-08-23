package mods.defeatedcrow.common.fluid;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Level;

import mods.defeatedcrow.common.DCsAppleMilk;

public class ItemBottleVegiOil extends ItemBucket {

    public ItemBottleVegiOil(Block block) {
        super(block);
        this.setContainerItem(Item.getItemFromBlock(DCsAppleMilk.emptyBottle));
    }

    @Override
        public void registerIcons(/*migrated*/Register par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:bottle_oil");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, Level p_77659_2_, EntityPlayer p_77659_3_) {
        return p_77659_1_;
    }

}
