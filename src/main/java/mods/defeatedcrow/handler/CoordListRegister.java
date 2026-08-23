package mods.defeatedcrow.handler;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import mods.defeatedcrow.common.AMTLogger;

/**
 * 1.20.1: World,int x,y,z -> Level, BlockPos
 * CoordListRegister now uses BlockPos + Level.dimension()
 */
public class CoordListRegister {

    private static CoordListRegister instance;
    protected static final ArrayList<Coord> coodList = new ArrayList<Coord>();
    protected static final HashMap<Coord, ArrayList<Pos>> coodCounter = new HashMap<Coord, ArrayList<Pos>>();

    private CoordListRegister() {}

    public static CoordListRegister getInstance() {
        if (instance == null) {
            instance = new CoordListRegister();
        }
        return instance;
    }

    /** 指定した座標のブロックをChunkLoaderとして起動する */
    public static boolean setCood(Level level, BlockPos pos, int chunkX, int chunkZ) {
        if (level.isClientSide) return false;
        Coord cood = new Coord(chunkX, chunkZ, level.dimension().location().toString().hashCode());
        Pos p = new Pos(pos);

        if (!isCoodIncluded(cood)) {
            coodList.add(cood);
            ArrayList<Pos> posList = new ArrayList<Pos>();
            posList.add(p);
            coodCounter.put(cood, posList);
            return true;
        } else {
            boolean f = false;
            for (Pos existing : coodCounter.get(cood)) {
                if (existing.equals(p)) f = true;
            }
            if (!f) {
                coodCounter.get(cood).add(p);
                AMTLogger.debugInfo("already added coord");
                return false;
            }
            return false;
        }
    }

    /** 指定した座標のChunkLoaderを停止する */
    public static boolean deleteCood(Level level, BlockPos pos, int chunkX, int chunkZ) {
        if (level.isClientSide) return false;
        Coord cood = new Coord(chunkX, chunkZ, level.dimension().location().toString().hashCode());
        Pos p = new Pos(pos);

        if (isCoodIncluded(cood)) {
            boolean f = false;
            for (Pos existing : coodCounter.get(cood)) {
                if (existing.equals(p)) f = true;
            }
            if (f) {
                coodCounter.get(cood).remove(p);
                AMTLogger.debugInfo("remove pos");
                if (coodCounter.get(cood).isEmpty()) {
                    coodList.remove(cood);
                    return true;
                }
            }
        }
        return false;
    }

    // legacy overloads for compat (deprecated)
    @Deprecated
    public static boolean setCood(Level level, int x, int y, int z, int i, int j) {
        return setCood(level, new BlockPos(x, y, z), i, j);
    }

    @Deprecated
    public static boolean deleteCood(Level level, int x, int y, int z, int i, int j) {
        return deleteCood(level, new BlockPos(x, y, z), i, j);
    }

    public static boolean isCoodIncluded(Coord cood) {
        if (coodList.isEmpty()) return false;
        for (Coord c : coodList) {
            if (c.equals(cood)) {
                return true;
            }
        }
        return false;
    }
}
