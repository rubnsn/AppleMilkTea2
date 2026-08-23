package mods.defeatedcrow.common.entity.edible;

import net.minecraft.world.level.Level;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Level;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;

public class PlaceableCocktailSP extends PlaceableFoods {

    public PlaceableCocktailSP(Level world) {
        super(world);
    }

    public PlaceableCocktailSP(Level world, ItemStack item) {
        super(world, true, item);
    }

    public PlaceableCocktailSP(Level world, ItemStack item, double x, double y, double z) {
        super(world, true, item, x, y, z);
    }

    @Override
    protected ItemStack returnItem() {
        return new ItemStack(mods.defeatedcrow.common.registry.ModBlocks.COCKTAIL_SP.get().asItem(), 1);
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
