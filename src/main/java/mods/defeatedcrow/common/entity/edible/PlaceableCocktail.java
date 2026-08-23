package mods.defeatedcrow.common.entity.edible;

import net.minecraft.item.ItemStack;
import net.minecraft.world.Level;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;

public class PlaceableCocktail extends PlaceableFoods {

    public PlaceableCocktail(Level world) {
        super(world);
    }

    public PlaceableCocktail(Level world, ItemStack item) {
        super(world, true, item);
    }

    public PlaceableCocktail(Level world, ItemStack item, double x, double y, double z) {
        super(world, true, item, x, y, z);
    }

    @Override
    protected ItemStack returnItem() {
        return new ItemStack(DCsAppleMilk.cocktail, 1, this.getItemMetadata());
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
