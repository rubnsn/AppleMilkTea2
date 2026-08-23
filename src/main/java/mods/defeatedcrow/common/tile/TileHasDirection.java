package mods.defeatedcrow.common.tile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
public class TileHasDirection extends BlockEntity {
    public TileHasDirection(BlockPos pos, BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_HAS_DIRECTION.get(), pos, state); }


    private byte direction = 0;

    // NBT
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);
        this.direction = par1CompoundTag.getByte("Direction");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);
        par1CompoundTag.putByte("Direction", this.direction);
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

    public byte getDirectionByte() {
        return this.direction;
    }

    public void setDirectionByte(byte par1) {
        this.direction = par1;
    }

    public int setDirection() {
        byte l = this.direction;
        if (l == 0) return 0;// south
        if (l == 1) return 180;// west
        if (l == 2) return 90;// north
        if (l == 4) return -90;// east
        else return 0;
    }

    public int getMetadata() { return 0; }
}
