package mods.defeatedcrow.common.entity.edible;

import net.minecraft.item.ItemStack;
import net.minecraft.util./*IconREMOVED migrated*/;
import net.minecraft.world.Level;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.base.FoodBaseEntity;
import mods.defeatedcrow.common.base.FoodModelType.Deco;
import mods.defeatedcrow.common.base.FoodModelType.Dish;
import mods.defeatedcrow.common.base.FoodModelType.Soup;

public class PlaceableBaseSoup extends FoodBaseEntity {

    public PlaceableBaseSoup(Level world) {
        super(world);
    }

    public PlaceableBaseSoup(Level world, ItemStack item) {
        super(world, item);
    }

    public PlaceableBaseSoup(Level world, ItemStack item, double x, double y, double z) {
        super(world, item, x, y, z);
    }

    @Override
    public Soup getSoupType() {
        return Soup.WoodSoup;
    }

    @Override
    public Deco getDecoType() {
        return Deco.None;
    }

    @Override
    public Dish getDishType() {
        return Dish.WoodBowl;
    }

    @Override
    public /*IconREMOVED migrated*/ getSoupIcon(int meta) {
        return DCsAppleMilk.baseSoupBowl.getIconFromDamage(meta + 16);
    }

    @Override
    protected ItemStack returnItem() {
        return new ItemStack(DCsAppleMilk.baseSoupBowl, 1, this.getItemMetadata());
    }

}
