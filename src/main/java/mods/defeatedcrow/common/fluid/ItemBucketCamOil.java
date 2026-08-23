package mods.defeatedcrow.common.fluid;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

public class ItemBucketCamOil extends ItemBucket {

    public ItemBucketCamOil(Block block) {
        super(block);
        this.setContainerItem(Items.bucket);
    }

    @Override
        public void registerIcons(/*migrated*/Register par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:bucket_camOil");
    }

}
