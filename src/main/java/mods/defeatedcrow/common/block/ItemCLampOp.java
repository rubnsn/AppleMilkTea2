package mods.defeatedcrow.common.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import java.util.List;

/**
 * WT-A 1.20.1: ItemCLampOp -> BlockItem (formerly ItemBlock).
 * Registration: ModItems + ModBlocks DeferredRegister (see ModItems.java:192)
 */
public class ItemCLampOp extends BlockItem {
    public ItemCLampOp(Block block, Properties properties) {
        super(block, properties);
    }
}