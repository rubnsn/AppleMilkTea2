package mods.defeatedcrow.api.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * 右クリックで何らかの効果を発揮するブロック
 * （右クリック回収動作を含む）の右クリック効果前に呼ばれるイベント。 <br>
 * 現在の対象ブロック： <br>
 * Basket、BowlRack、IncanseBase、RotaryDial、EmptyCup、EmptyBottle <br>
 * BarrelなどTileEntityにNBT情報を持つブロックは基本的にこのイベントで扱わない <br>
 * また、食べ物ブロックはこのイベントを持つが、デフォルトではEntity化しているのでコンフィグでEntity化をOFFにしている場合以外は意味が無い。 <br>
 * Entity化していないChocoGiftへの干渉はこのイベントでのみ行える。 <br>
 * 1.20.1: World,int x,int y,int z → Level, BlockPos。
 */
@Cancelable
@Event.HasResult
public class AMTBlockRightClickEvent extends Event {

    public final Level level;
    public final Player player;
    public final ItemStack heldItem;

    public final BlockPos pos;

    public AMTBlockRightClickEvent(Level level, Player player, ItemStack item, BlockPos pos) {
        this.level = level;
        this.player = player;
        this.heldItem = item;
        this.pos = pos;
    }

    /** legacy accessor for compat. */
    @Deprecated
    public int getPosX() {
        return pos.getX();
    }

    /** legacy accessor for compat. */
    @Deprecated
    public int getPosY() {
        return pos.getY();
    }

    /** legacy accessor for compat. */
    @Deprecated
    public int getPosZ() {
        return pos.getZ();
    }

}
