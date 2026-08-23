package mods.defeatedcrow.api.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * 右クリック収穫系の植物ブロックの右クリック処理中に呼ばれるイベント。 <br>
 * キャンセル可能。 <br>
 * キャンセルした場合、onHarvestなどの本来の処理はキャンセルされる。 <br>
 * Result.ALLOWの場合、メタデータが初期値に戻る。 <br>
 * 1.20.1: World,int x,int y,int z → Level, BlockPos。targetState で BlockState を取得可能。
 */
@Cancelable
@Event.HasResult
public class PlantsClickEvent extends Event {

    public final Level level;
    public final Player player;
    public final ItemStack holdItem;
    public final Block target;
    public final IRightClickHarvestable targetPlant;
    public final int targetMeta;
    public final BlockPos pos;

    public PlantsClickEvent(Level level, Player player, ItemStack hold, Block block,
        IRightClickHarvestable thisPlant, int meta, BlockPos pos) {
        this.level = level;
        this.player = player;
        this.holdItem = hold;
        this.target = block;
        this.targetPlant = thisPlant;
        this.targetMeta = meta;
        this.pos = pos;
    }

    /**
     * 対象ブロックの現在の BlockState を取得する (1.20.1)。
     */
    public BlockState getTargetState() {
        return level.getBlockState(pos);
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
