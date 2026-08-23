package mods.defeatedcrow.common.entity.edible;

import net.minecraft.world.level.Level;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
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
    public ResourceLocation getSoupIcon(int meta) {
        // 1.20.1: former atlas icon (meta+16 innerType) -> ResourceLocation.
        // ItemBaseSoupBowl had iconType[0..8] = foods/basesoupitem_<TYPE> and innerType = contents/basesoup_<TYPE>.
        // Entity soup quad now uses entityCutout with this texture; fallback is bowlJP_inner.png in RenderFoodEntityBase.
        int m = meta & 15;
        String[] names = { "WATER", "CHOCO", "OIL", "DASHI", "SHOYU", "TONKOTU", "BLOOD", "PURPLE", "CHEESE" };
        if (m < 0 || m >= names.length) m = 0;
        return new ResourceLocation("defeatedcrow", "textures/items/contents/basesoup_" + names[m] + ".png");
    }

    @Override
    protected ItemStack returnItem() {
        return new ItemStack(mods.defeatedcrow.common.registry.ModBlocks.BASE_SOUP_BOWL.get().asItem(), 1);
    }

}
