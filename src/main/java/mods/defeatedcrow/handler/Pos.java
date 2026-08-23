package mods.defeatedcrow.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * 1.20.1: World,int x,y,z -> BlockPos
 * Legacy Pos wrapper retained for compat, now delegates to BlockPos.
 */
public class Pos {

    public final BlockPos pos;

    public Pos(int i, int j, int k) {
        this.pos = new BlockPos(i, j, k);
    }

    public Pos(BlockPos p) {
        this.pos = p;
    }

    public int getX() { return pos.getX(); }
    public int getY() { return pos.getY(); }
    public int getZ() { return pos.getZ(); }

    public Block getBlock(BlockGetter world) {
        return world.getBlockState(pos).getBlock();
    }

    public BlockEntity getTile(BlockGetter world) {
        return world.getBlockEntity(pos);
    }

    public int getMeta(BlockGetter world) {
        return 0; // metadata removed in 1.20.1, property based
    }

    public boolean isSamePos(int i, int j, int k) {
        return pos.getX() == i && pos.getY() == j && pos.getZ() == k;
    }

    public boolean isSamePos(BlockPos other) {
        return pos.equals(other);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Pos p) {
            return p.pos.equals(pos);
        }
        if (obj instanceof BlockPos bp) {
            return bp.equals(pos);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return pos.hashCode();
    }
}
