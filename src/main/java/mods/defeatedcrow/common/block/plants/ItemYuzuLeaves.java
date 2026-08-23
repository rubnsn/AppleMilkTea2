package mods.defeatedcrow.common.block.plants;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import java.util.List;

/**
 * WT-A 1.20.1: ItemYuzuLeaves -> BlockItem (formerly ItemBlock).
 * Registration: ModItems + ModBlocks DeferredRegister (see ModItems.java:192)
 */
public class ItemYuzuLeaves extends BlockItem {
    public ItemYuzuLeaves(Block block, Properties properties) {
        super(block, properties);
    }
}