package mods.defeatedcrow.common.entity.edible;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import mods.defeatedcrow.common.registry.ModBlocks;

public class PlaceableBowlJP extends PlaceableFoods {
    public PlaceableBowlJP(EntityType<?> t, Level l){ super(t,l); }
    @Override
    protected ItemStack returnItem() {
        return new ItemStack(ModBlocks.BOWL_JP.get());
    }
}
