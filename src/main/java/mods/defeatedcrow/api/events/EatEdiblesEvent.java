package mods.defeatedcrow.api.events;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * IEdibleを実装しているAMTの飲食アイテムの効果発揮時に差し込めるイベント。 <br>
 * キャンセル可能。 <br>
 * 必ずResultを返す必要がある。Result.ALLOWの場合、アイテムがひとつ消費される。 <br>
 * 1.20.1: World → Level, EntityPlayer → Player。
 */
@Cancelable
@Event.HasResult
public class EatEdiblesEvent extends Event {

    public final Level level;
    public final Player player;
    public final ItemStack edibles;

    public EatEdiblesEvent(Level level, Player player, ItemStack item) {
        this.level = level;
        this.player = player;
        this.edibles = item;
    }

}
