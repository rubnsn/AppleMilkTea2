package mods.defeatedcrow.common.block.appliance;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import java.util.List;

/**
 * WT-A 1.20.1: ItemMachineBlock -> BlockItem (formerly ItemBlock).
 * Registration: ModItems + ModBlocks DeferredRegister (see ModItems.java:192)
 */
public class ItemMachineBlock extends BlockItem {
    public ItemMachineBlock(Block block, Properties properties) {
        super(block, properties);
    }
}