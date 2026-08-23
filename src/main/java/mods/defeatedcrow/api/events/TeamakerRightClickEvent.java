package mods.defeatedcrow.api.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;

import mods.defeatedcrow.api.recipe.ITeaRecipe;

import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * TeaMakerの右クリックで呼ばれるメソッド。
 * 特定のアイテムに右クリック動作を新規に追加したり、または特定アイテムをの動作を削除するのに使える。 <br>
 * 1.20.1: TileEntity → BlockEntity、World,int x,int y,int z → Level, BlockPos。
 */
@Cancelable
public class TeamakerRightClickEvent extends Event {

    public final BlockEntity teaMaker;
    public final int remain;
    public final ITeaRecipe currentRecpe;

    public final Player player;

    public final BlockPos pos;

    public TeamakerRightClickEvent(Player entityplayer, BlockPos pos, BlockEntity tile, int rem, ITeaRecipe recipe) {
        this.player = entityplayer;
        this.pos = pos;
        this.teaMaker = tile;
        this.remain = rem;
        this.currentRecpe = recipe;
    }

    /** legacy accessor for compat. */
    @Deprecated
    public int getX() {
        return pos.getX();
    }

    /** legacy accessor for compat. */
    @Deprecated
    public int getY() {
        return pos.getY();
    }

    /** legacy accessor for compat. */
    @Deprecated
    public int getZ() {
        return pos.getZ();
    }

}
