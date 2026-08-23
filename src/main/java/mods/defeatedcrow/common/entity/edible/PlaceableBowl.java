package mods.defeatedcrow.common.entity.edible;

import net.minecraft.world.level.Level;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Level;

import mods.defeatedcrow.common.DCsAppleMilk;

public class PlaceableBowl extends PlaceableFoods {

    public PlaceableBowl(Level world) {
        super(world);
    }

    public PlaceableBowl(Level world, ItemStack item) {
        super(world, true, item);
    }

    public PlaceableBowl(Level world, ItemStack item, double x, double y, double z) {
        super(world, true, item, x, y, z);
    }

    @Override
    protected ItemStack returnItem() {
        return new ItemStack(mods.defeatedcrow.common.registry.ModBlocks.BOWL_BLOCK.get().asItem(), 1);
    }

    @Override
    protected byte particleNumber() {
        return 2;
    }

}
