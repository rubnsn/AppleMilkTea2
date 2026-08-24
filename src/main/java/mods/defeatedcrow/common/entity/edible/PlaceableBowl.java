package mods.defeatedcrow.common.entity.edible;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import mods.defeatedcrow.common.registry.ModBlocks;

public class PlaceableBowl extends PlaceableFoods {
    public PlaceableBowl(EntityType<?> t, Level l){ super(t,l); }
    @Override
    protected ItemStack returnItem() {
        return new ItemStack(ModBlocks.BOWL_BLOCK.get());
    }
}
