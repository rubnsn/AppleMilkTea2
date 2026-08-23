package mods.defeatedcrow.common.entity.edible;

import net.minecraft.item.ItemStack;
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
        return new ItemStack(DCsAppleMilk.bowlJP, 1, this.getItemMetadata());
    }

    @Override
    protected byte particleNumber() {
        return 2;
    }

}
