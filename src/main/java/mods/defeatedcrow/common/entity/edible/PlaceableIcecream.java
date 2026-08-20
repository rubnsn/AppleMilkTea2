package mods.defeatedcrow.common.entity.edible;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;

public class PlaceableIcecream extends PlaceableFoods {

    public PlaceableIcecream(World world) {
        super(world);
    }

    public PlaceableIcecream(World world, boolean chops, ItemStack item) {
        super(world, chops, item);
    }

    public PlaceableIcecream(World world, boolean chops, ItemStack item, double x, double y, double z) {
        super(world, chops, item, x, y, z);
    }

    @Override
    protected ItemStack returnItem() {
        return new ItemStack(DCsAppleMilk.blockIcecream, 1, this.getItemMetadata());
    }

    @Override
    protected byte particleNumber() {
        return 1;
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
