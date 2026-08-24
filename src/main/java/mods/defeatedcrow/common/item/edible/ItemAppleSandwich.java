package mods.defeatedcrow.common.item.edible;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import mods.defeatedcrow.common.entity.edible.PlaceableSandwich;
import mods.defeatedcrow.common.registry.ModEntities;

public class ItemAppleSandwich extends EdibleEntityItem {
    public ItemAppleSandwich(Properties properties) {
        super(properties, true, false);
    }
    @Override
    public int[] hungerOnEaten(int meta) { return new int[]{4, 2}; }
    @Override
    protected boolean spownEntityFoods(Level level, Player player, ItemStack item, double x, double y, double z) {
        var e = new PlaceableSandwich(ModEntities.PLACEABLE_SANDWICH.get(), level);
        e.setPos(x, y, z);
        e.setContainerMeta(0);
        e.setYRot(player.getYRot() - 180.0F);
        if (!level.isClientSide) return level.addFreshEntity(e);
        return false;
    }
}
