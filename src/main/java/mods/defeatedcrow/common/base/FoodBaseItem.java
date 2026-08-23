package mods.defeatedcrow.common.base;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import java.util.ArrayList;
import java.util.List;
public class FoodBaseItem extends Item {
    public FoodBaseItem(boolean b){ super(new Item.Properties()); }
    public int[] hungerOnEaten(int meta){ return new int[]{4,2}; }
    public List<MobEffectInstance> effectOnEaten(Player p,int m){ return new ArrayList<>(); }
}
