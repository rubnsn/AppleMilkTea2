package mods.defeatedcrow.common.world.village;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

/**
 * 1.20.1 ComponentVillageWarehouse - warehouse structure (was StructureVillagePieces.Village).
 */
public class ComponentVillageWarehouse {

    private boolean hasMadeChest;

    public ComponentVillageWarehouse() {}

    public boolean addComponentParts(ServerLevelAccessor level, RandomSource random, BoundingBox box) {
        // TODO: place NBT template data/defeatedcrow/structures/village_warehouse.nbt
        return true;
    }

    protected void addAdditionalSaveData(CompoundTag tag) { tag.putBoolean("Chest", hasMadeChest); }
    protected void readAdditionalSaveData(CompoundTag tag) { hasMadeChest = tag.getBoolean("Chest"); }
    public void postProcess(net.minecraft.world.level.WorldGenLevel level, net.minecraft.world.level.StructureManager manager, net.minecraft.world.level.chunk.ChunkGenerator generator, RandomSource random, BoundingBox box, net.minecraft.world.level.ChunkPos chunkPos, BlockPos pos) {
        // TODO: template placement
    }
}
