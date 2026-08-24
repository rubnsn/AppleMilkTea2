package mods.defeatedcrow.common.item.edible;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import mods.defeatedcrow.common.entity.edible.PlaceableBaseSoup;
import mods.defeatedcrow.common.registry.ModEntities;

public class ItemBaseSoupBowl extends EdibleEntityItem {
    public ItemBaseSoupBowl(Properties properties) {
        super(properties, true, false);
    }
    @Override
    public ItemStack getReturnContainer(int meta) { return new ItemStack(Items.BOWL); }
    @Override
    public int[] hungerOnEaten(int meta) { return meta==0 ? new int[]{0,0} : new int[]{2,2}; }
    @Override
    protected boolean spownEntityFoods(Level level, Player player, ItemStack item, double x, double y, double z) {
        var e = new PlaceableBaseSoup(ModEntities.PLACEABLE_BASE_SOUP.get(), level);
        e.setPos(x, y, z);
        e.setContainerMeta(item.getDamageValue() & 15);
        e.setYRot(player.getYRot() - 180.0F);
        if (!level.isClientSide) return level.addFreshEntity(e);
        return false;
    }
}
