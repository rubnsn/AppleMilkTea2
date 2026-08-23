package mods.defeatedcrow.common.block.container;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import java.util.List;

/**
 * WT-A 1.20.1: ItemVegiBag -> BlockItem (formerly ItemBlock).
 * Registration: ModItems + ModBlocks DeferredRegister (see ModItems.java:192)
 */
public class ItemVegiBag extends BlockItem {
    public ItemVegiBag(Block block, Properties properties) {
        super(block, properties);
    }
}