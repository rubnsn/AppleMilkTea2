package mods.defeatedcrow.common.entity.edible;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import mods.defeatedcrow.common.registry.ModItems;

public class PlaceableTart extends PlaceableFoods {
    public PlaceableTart(EntityType<?> t, Level l){ super(t,l); }
    @Override
    protected ItemStack returnItem() {
        return new ItemStack(ModItems.APPLE_TART.get());
    }
}
