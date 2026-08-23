package mods.defeatedcrow.common.item;

import net.minecraft.world.item.ItemNameBlockItem;

import mods.defeatedcrow.common.registry.ModBlocks;

/**
 * WT-A: ItemMintSeed — 1.7.10 → mojmap 1.20.1 移行。
 * 1.7.10 の ItemSeeds + IPlantable を mojmap の {@link ItemNameBlockItem} に統合。
 * 植え付け可能ブロック種は ModBlocks.CROP_MINT (mojmap CropBlock)、
 * 植えられる地はバニラ耕地 (Blocks.FARMLAND)。
 */
public class ItemMintSeed extends ItemNameBlockItem {

    public ItemMintSeed(Properties properties) {
        super(ModBlocks.CROP_MINT.get(), net.minecraft.world.level.block.Blocks.FARMLAND, properties);
    }
}
