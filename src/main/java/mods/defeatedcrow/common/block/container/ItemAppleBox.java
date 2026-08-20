package mods.defeatedcrow.common.block.container;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import mods.defeatedcrow.api.ICompressedItem;

public class ItemAppleBox extends ItemBlock implements ICompressedItem {

    public ItemAppleBox(Block block) {
        super(block);
    }

    @Override
    public ItemStack getDisassembledItem(ItemStack container) {
        return new ItemStack(Items.apple, 9, 0);
    }

}
