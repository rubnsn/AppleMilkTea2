package mods.defeatedcrow.api;

import net.minecraft.world.item.ItemStack;

/**
 * 圧縮レシピのためのインターフェイス。
 */
public interface ICompressedItem {

    ItemStack getDisassembledItem(ItemStack container);

}
