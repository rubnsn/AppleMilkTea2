package mods.defeatedcrow.common.tile.energy;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 1.20.1: TileChargerDevice - legacy EU/RF/GF bridge removed.
 * Now delegates to TileChargerBase's Forge Energy (IEnergyStorage) capability.
 * Original RF (CoFH IEnergyHandler), EU (IC2), GF (SextiarySector) are removed in 1.20.1.
 * This stub keeps the class for registry compatibility; all energy logic is via ForgeCapabilities.ENERGY.
 * See doc/tile-entities/migration-guide.md and doc/handler/migration-guide.md (RF->Forge Energy)
 */
public class TileChargerDevice extends TileChargerBase {

    public TileChargerDevice(BlockPos pos, BlockState state) {
        super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_CHARGER_DEVICE.get(), pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return super.getUpdatePacket();
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
    }

    // 1.20.1: charge helpers now delegate to super (Forge Energy)
    @Override
    public boolean isChargeableBattery(ItemStack item) {
        return super.isChargeableBattery(item);
    }

    @Override
    public int chargeAnotherBattery(ItemStack item, int inc, boolean simulate) {
        return super.chargeAnotherBattery(item, inc, simulate);
    }

    @Override
    public int getItemBurnTime(ItemStack item) {
        return super.getItemBurnTime(item);
    }

    @Override
    public int discharge(ItemStack item, int amount, int slot) {
        return super.discharge(item, amount, slot);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
    }

    @Override
    public void onChunkUnloaded() {
        super.onChunkUnloaded();
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TileChargerDevice be) {
        // 1.20.1 ticker via Block#getTicker
        if (level.isClientSide) return;
        TileChargerBase.tick(level, pos, state, be);
    }

    // 1.20.1: All legacy RF/EU/GF methods (canConnectEnergy, receiveEnergy, addEnergy etc.) removed.
    // Forge Energy capability is provided via TileChargerBase#getCapability(CapabilityEnergy.ENERGY)
}
