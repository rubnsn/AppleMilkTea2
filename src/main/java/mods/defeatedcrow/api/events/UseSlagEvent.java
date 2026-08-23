package mods.defeatedcrow.api.events;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * StrangeSlagの右クリック時に呼ばれる。 <br>
 * Cancelした場合、本来の処理は呼ばれない。 <br>
 * 1.20.1: World → Level, EntityPlayer → Player。
 */
@Cancelable
@Event.HasResult
public class UseSlagEvent extends Event {

    public final Level level;
    public final Player player;
    public ItemStack returnItem;

    public UseSlagEvent(Level level, Player player, ItemStack item) {
        this.level = level;
        this.player = player;
        this.returnItem = item;
    }
}
