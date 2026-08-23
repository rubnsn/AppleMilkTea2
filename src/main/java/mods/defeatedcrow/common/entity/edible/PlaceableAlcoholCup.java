package mods.defeatedcrow.common.entity.edible;

import net.minecraft.item.ItemStack;
import net.minecraft.world.Level;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;

public class PlaceableAlcoholCup extends PlaceableFoods {

    public PlaceableAlcoholCup(Level world) {
        super(world);
    }

    public PlaceableAlcoholCup(Level world, ItemStack item) {
        super(world, true, item);
    }

    public PlaceableAlcoholCup(Level world, ItemStack item, double x, double y, double z) {
        super(world, true, item, x, y, z);
    }

    @Override
    protected ItemStack returnItem() {
        return new ItemStack(DCsAppleMilk.alcoholCup, 1, this.getItemMetadata());
    }

    @Override
    protected float getScale() {
        return Util.getCupScale();
    }

    @Override
    protected float getSize() {
        return Util.getCupSize();
    }

}
