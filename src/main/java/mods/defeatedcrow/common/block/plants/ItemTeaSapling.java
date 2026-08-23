package mods.defeatedcrow.common.block.plants;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import java.util.List;

/**
 * WT-A 1.20.1: ItemTeaSapling -> BlockItem (formerly ItemBlock).
 * Registration: ModItems + ModBlocks DeferredRegister (see ModItems.java:192)
 */
public class ItemTeaSapling extends BlockItem {
    public ItemTeaSapling(Block block, Properties properties) {
        super(block, properties);
    }
}