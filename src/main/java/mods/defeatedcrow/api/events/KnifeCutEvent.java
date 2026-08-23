package mods.defeatedcrow.api.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * ChalcedonyKnifeがブロックを破壊する直前に呼ばれるイベント。 <br>
 * キャンセル可能。 <br>
 * 1.20.1: World,int x,int y,int z → Level, BlockPos。metaはdamage/legacy値として維持。
 */
@Cancelable
@Event.HasResult
public class KnifeCutEvent extends Event {

    public final Level level;
    public final LivingEntity entity;
    public final Block target;
    public final int targetMeta;
    public final BlockPos pos;

    public KnifeCutEvent(Level level, LivingEntity entity, Block block, int meta, BlockPos pos) {
        this.level = level;
        this.entity = entity;
        this.target = block;
        this.targetMeta = meta;
        this.pos = pos;
    }

    /**
     * 破壊対象の現在の BlockState を取得する (1.20.1)。
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
