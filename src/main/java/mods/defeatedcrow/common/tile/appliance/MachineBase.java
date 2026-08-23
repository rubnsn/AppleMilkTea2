package mods.defeatedcrow.common.tile.appliance;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
public class MachineBase extends BlockEntity {
    public MachineBase(BlockPos pos, BlockState state){ super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_PROCESSOR.get(), pos, state); }
    @Override public void load(CompoundTag t){ super.load(t); }
    @Override public void saveAdditional(CompoundTag t){ super.saveAdditional(t); }
}
