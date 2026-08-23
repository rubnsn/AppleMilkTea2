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
 * 1.20.1 stub for ComponentVillageCafe — legacy StructureVillagePieces.Village.
 * 1.20.1 uses Jigsaw + TemplatePool + Structure (datapack). This stub keeps the class for registry compat
 * and provides minimal StructurePiece implementation so the project compiles.
 * Full Jigsaw migration is TODO (requires data/defeatedcrow/worldgen/structure/village_cafe.nbt + template_pool).
 * See doc/worldgen/migration-guide.md:105
 */
public class ComponentVillageCafe extends StructurePiece {

    public ComponentVillageCafe(StructurePieceType type, int genDepth, BoundingBox box) {
        super(type, genDepth, box);
    }

    // Legacy constructor retained for VillageCreateHandle compat (not used in 1.20.1)
    public ComponentVillageCafe(Object start, int type, RandomSource rand, BoundingBox box, int coordBaseMode) {
        super(StructurePieceType.VILLAGE_HOUSE, 0, box);
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext ctx, CompoundTag tag) {}

    @Override
    public void postProcess(WorldGenLevel level, StructureManager manager, ChunkGenerator generator, RandomSource rand, BoundingBox box, ChunkPos chunkPos, BlockPos pos) {
        // TODO: place structure via Jigsaw / Template
    }

    @Override
    public void handleDataMarker(String name, BlockPos pos, WorldGenLevel level, RandomSource rand, BoundingBox box) {}
}
