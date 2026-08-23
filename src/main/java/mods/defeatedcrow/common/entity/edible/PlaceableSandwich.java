package mods.defeatedcrow.common.entity.edible;

import net.minecraft.item.ItemStack;
import net.minecraft.world.Level;

import mods.defeatedcrow.common.DCsAppleMilk;

public class PlaceableSandwich extends PlaceableFoods {

    public PlaceableSandwich(Level world) {
        super(world);
    }

    public PlaceableSandwich(Level world, ItemStack item) {
        super(world, true, item);
    }

    public PlaceableSandwich(Level world, ItemStack item, double x, double y, double z) {
        super(world, true, item, x, y, z);
    }

    @Override
    protected ItemStack returnItem() {
        return new ItemStack(DCsAppleMilk.appleSandwich, 1, this.getItemMetadata());
    }

}
