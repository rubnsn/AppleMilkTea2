package mods.defeatedcrow.common.tile.energy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.minecraftforge.fml.ModList;
import mods.defeatedcrow.api.charge.IChargeGenerator;
import mods.defeatedcrow.api.charge.IChargeableMachine;
import mods.defeatedcrow.common.config.PropertyHandler;
import mods.defeatedcrow.plugin.SSector.SS2DeviceHandler;
import mods.defeatedcrow.plugin.cofh.RFDeviceHandler;

public class TileHandleEngine extends BlockEntity implements IChargeGenerator, IEnergyProvider {
    public TileHandleEngine(BlockPos pos, BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_HANDLE_ENGINE.get(), pos, state); }


    private int interval = 0;
    private int round = 0;
    private int click = 0;

    private int chargeAmount = 0;
    private final int MAX_CHARGE = 32;

    private int lastRound = 0;

    @Override
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);

        this.interval = par1CompoundTag.getShort("Interval");
        this.round = par1CompoundTag.getShort("Round");
        this.click = par1CompoundTag.getShort("Click");
        this.chargeAmount = par1CompoundTag.getShort("ChargeAmount");
    }

    @Override
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);

        ListTag nbttaglist = new ListTag();

        par1CompoundTag.putShort("Interval", (short) this.interval);
        par1CompoundTag.putShort("Round", (short) this.round);
        par1CompoundTag.putShort("Click", (short) this.click);
        par1CompoundTag.putShort("ChargeAmount", (short) this.chargeAmount);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
        this.saveAdditional(tag);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    public void setChargeAmount(int par1) {
        this.chargeAmount = par1;
    }

    public int getChargeAmount() {
        return this.chargeAmount;
    }

    public void setClick(int par1) {
        this.click = par1;
    }

    public int getClick() {
        return this.click;
    }

    public void setInterval(int par1) {
        if (par1 > 8) par1 = 8;
        this.interval = par1;
    }

    public float getRound() {
        return (float) this.round;
    }

    private int rateRF() {
        return PropertyHandler.rateRF();
    }

    @Override
    public static void tick(Level level, BlockPos pos, BlockState state, TileHandleEngine be) {
        // 1.20.1 tick (was updateEntity) - see doc/tile-entities/migration-guide.md
        if (level.isClientSide) return;
        be.setChanged();
        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
    }

    public void updateServer() {
        int current = this.round;
        if (current != this.lastRound) {
            this.lastRound = current;
            this.level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
        }
    }

    /* IChargeGenerator */

    @Override
    public boolean canGenerate() {
        return this.chargeAmount > 0;
    }

    @Override
    public int generateCharge(Direction dir, boolean flag) {
        if (dir != Direction.DOWN) return 0;

        int ret = Math.min(chargeAmount, 2);
        if (ret > 0) {
            if (!flag) this.chargeAmount -= ret;
            return ret;
        }
        return 0;
    }

    /* IEnergyProvider */

        @Override
    public boolean canConnectEnergy(Direction paramForgeDirection) {
        return paramForgeDirection == Direction.DOWN && this.chargeAmount > 0;
    }

        @Override
    public int extractEnergy(Direction paramForgeDirection, int paramInt, boolean paramBoolean) {
        if (paramForgeDirection != Direction.DOWN) return 0;

        int ret = Math.min(this.chargeAmount, 2);
        int extract = ret * PropertyHandler.rateRF();

        if (ret > 0) {
            this.chargeAmount -= ret;
            return extract;
        }
        return 0;
    }

        @Override
    public int getEnergyStored(Direction paramForgeDirection) {
        return this.chargeAmount * PropertyHandler.rateRF();
    }

        @Override
    public int getMaxEnergyStored(Direction paramForgeDirection) {
        return this.MAX_CHARGE * PropertyHandler.rateRF();
    }

}
