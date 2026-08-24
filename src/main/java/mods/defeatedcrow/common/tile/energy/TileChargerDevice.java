package mods.defeatedcrow.common.tile.energy;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TileChargerDevice extends BlockEntity {
    public TileChargerDevice(BlockPos pos, BlockState state){ super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_CHARGER_DEVICE.get(), pos, state); }
    @Override public void load(CompoundTag tag){ super.load(tag); }
    @Override public void saveAdditional(CompoundTag tag){ super.saveAdditional(tag); }
    @Override public ClientboundBlockEntityDataPacket getUpdatePacket(){ return ClientboundBlockEntityDataPacket.create(this); }
    
    public static void tick(Level level, BlockPos pos, BlockState state, TileChargerDevice be){ if(level.isClientSide) return; be.setChanged(); }
}
