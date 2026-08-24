package mods.defeatedcrow.common.item.edible;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import mods.defeatedcrow.common.entity.edible.PlaceableTart;
import mods.defeatedcrow.common.registry.ModEntities;

public class ItemAppleTart extends EdibleEntityItem {
    public ItemAppleTart(Properties properties) {
        super(properties, true, false);
    }
    @Override
    public int[] hungerOnEaten(int meta) { return new int[]{8, 5}; }
    @Override
    protected boolean spownEntityFoods(Level level, Player player, ItemStack item, double x, double y, double z) {
        var e = new PlaceableTart(ModEntities.PLACEABLE_TART.get(), level);
        e.setPos(x, y, z);
        e.setContainerMeta(item.getDamageValue());
        e.setYRot(player.getYRot() - 180.0F);
        if (!level.isClientSide) return level.addFreshEntity(e);
        return false;
    }
}
