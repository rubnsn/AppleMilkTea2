package mods.defeatedcrow.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;

/**
 * 1.20.1: Chunk coord helper migrated to ChunkPos + ResourceKey<Level>
 */
public class Coord {

    public final int x;
    public final int z;
    public final int dim;

    public Coord(int i, int j, int d) {
        x = i;
        z = j;
        dim = d;
    }

    public Coord(ChunkPos pos, int d) {
        this(pos.x, pos.z, d);
    }

    public ChunkAccess getChunk(Level level) {
        return level.getChunk(x, z);
    }

    public boolean sameCood(int i, int j, int d) {
        return i == x && j == z && dim == d;
    }

    public boolean sameCood(ChunkPos pos, int d) {
        return pos.x == x && pos.z == z && dim == d;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Coord p) {
            return p.x == x && p.z == z && p.dim == dim;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int i = x + z * 953 + dim * 13;
        return i;
    }
}
