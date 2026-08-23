package mods.defeatedcrow.common.world.village;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

/**
 * 1.20.1 stub for ComponentVillageWarehouse — see ComponentVillageCafe.java
 */
public class ComponentVillageWarehouse extends StructurePiece {

    public ComponentVillageWarehouse(StructurePieceType type, int genDepth, BoundingBox box) {
        super(type, genDepth, box);
    }

    public ComponentVillageWarehouse(Object start, int type, RandomSource rand, BoundingBox box, int coordBaseMode) {
        super(StructurePieceType.VILLAGE_HOUSE, 0, box);
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext ctx, CompoundTag tag) {}

    @Override
    public void postProcess(WorldGenLevel level, StructureManager manager, ChunkGenerator generator, RandomSource rand, BoundingBox box, ChunkPos chunkPos, BlockPos pos) {}

    @Override
    public void handleDataMarker(String name, BlockPos pos, WorldGenLevel level, RandomSource rand, BoundingBox box) {}
}
