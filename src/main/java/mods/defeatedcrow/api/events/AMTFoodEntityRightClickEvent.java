package mods.defeatedcrow.api.events;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * AMT2で追加されるEntity状態の食べ物の右クリック効果前に呼ばれるイベント。 <br>
 * Cancelした場合、本来の処理は呼ばれない。 <br>
 * 1.20.1: World → Level, EntityPlayer → Player, Entity → world.entity.Entity。
 */
@Cancelable
@Event.HasResult
public class AMTFoodEntityRightClickEvent extends Event {

    public final Level level;
    public final Player player;
    public final ItemStack heldItem;

    public final Entity target;

    public AMTFoodEntityRightClickEvent(Level level, Player player, ItemStack item, Entity entity) {
        this.level = level;
        this.player = player;
        this.heldItem = item;
        this.target = entity;
    }

}
