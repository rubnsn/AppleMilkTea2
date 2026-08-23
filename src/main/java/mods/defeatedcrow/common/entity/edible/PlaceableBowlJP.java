package mods.defeatedcrow.common.entity.edible;

import net.minecraft.world.level.Level;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Level;

import mods.defeatedcrow.common.DCsAppleMilk;

public class PlaceableBowlJP extends PlaceableFoods {

    public PlaceableBowlJP(Level world) {
        super(world);
        this.setSize(0.5F, 0.3F);
    }

    public PlaceableBowlJP(Level world, ItemStack item) {
        super(world, true, item);
    }

    public PlaceableBowlJP(Level world, ItemStack item, double x, double y, double z) {
        super(world, true, item, x, y, z);
    }

    @Override
    protected ItemStack returnItem() {
        return new ItemStack(mods.defeatedcrow.common.registry.ModBlocks.BOWL_JP.get().asItem(), 1);
    }

    @Override
    protected byte particleNumber() {
        return 2;
    }

}
