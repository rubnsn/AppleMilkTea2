package mods.defeatedcrow.common.tile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.handler.CoordListRegister;

public class TileCrowDoll extends BlockEntity {
    public TileCrowDoll(BlockPos pos, BlockState state) { super(null, pos, state); }


    private boolean active = false;

    public double range = 0.0D;

    @Override
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);
        this.range = par1CompoundTag.getDouble("Shake");
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);
        par1CompoundTag.putDouble("Shake", this.range);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag nbtTagCompound = new CompoundTag();
        this.saveAdditional(nbtTagCompound);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    @Override
    public static void tick(Level level, BlockPos pos, BlockState state, TileCrowDoll be) {
        // 1.20.1 tick (was updateEntity) - see doc/tile-entities/migration-guide.md
        if (level.isClientSide) return;
        be.setChanged();
        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
    }

}
