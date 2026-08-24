package mods.defeatedcrow.common.entity.edible;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import mods.defeatedcrow.common.registry.ModItems;

public class PlaceableBaseSoup extends PlaceableFoods {
    public PlaceableBaseSoup(EntityType<?> t, Level l){ super(t,l); }
    @Override
    protected ItemStack returnItem() {
        return new ItemStack(ModItems.BASE_SOUP_BOWL.get());
    }
}
