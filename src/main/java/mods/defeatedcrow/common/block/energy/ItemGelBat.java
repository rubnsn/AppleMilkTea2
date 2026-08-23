package mods.defeatedcrow.common.block.energy;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import java.util.List;

/**
 * WT-A 1.20.1: ItemGelBat -> BlockItem (formerly ItemBlock).
 * Registration: ModItems + ModBlocks DeferredRegister (see ModItems.java:192)
 */
public class ItemGelBat extends BlockItem {
    public ItemGelBat(Block block, Properties properties) {
        super(block, properties);
    }
}