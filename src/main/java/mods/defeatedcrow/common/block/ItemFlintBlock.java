package mods.defeatedcrow.common.block;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import mods.defeatedcrow.api.ICompressedItem;

public class ItemFlintBlock extends ItemBlock implements ICompressedItem {

    public ItemFlintBlock(Block block) {
        super(block);
    }

    @Override
    public ItemStack getDisassembledItem(ItemStack cont) {
        return new ItemStack(Items.flint, 4, 0);
    }

}
