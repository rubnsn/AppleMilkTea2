package mods.defeatedcrow.common.tile.appliance;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import mods.defeatedcrow.api.appliance.SoupType;

public class TileFilledSoupPan extends BlockEntity {
    public TileFilledSoupPan(BlockPos pos, BlockState state) { super(null, pos, state); }


    private byte type = 0;
    private byte remain = 0;
    private String tex = "defeatedcrow:textures/blocks/contents_rice.png";
    private byte coolTime = 0;
    private boolean direction = false;

    private int last = 0;

    // NBT
    @Override
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);

        this.type = par1CompoundTag.getByte("Type");
        this.remain = par1CompoundTag.getByte("Remaining");
        this.direction = par1CompoundTag.getBoolean("Direction");
        this.tex = par1CompoundTag.getString("Tex");
        this.coolTime = par1CompoundTag.getByte("CoolTime");
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);

        par1CompoundTag.putByte("Type", this.type);
        par1CompoundTag.putByte("Remaining", this.remain);
        par1CompoundTag.putBoolean("Direction", this.direction);
        par1CompoundTag.setString("Tex", tex);
        par1CompoundTag.putByte("CoolTime", this.coolTime);
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

    /* --- update --- */

    @Override
    public static void tick(Level level, BlockPos pos, BlockState state, TileFilledSoupPan be) {
        // 1.20.1 tick (was updateEntity) - see doc/tile-entities/migration-guide.md
        if (level.isClientSide) return;
        be.setChanged();
        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
    }

    /* --- getter, setter --- */

    public byte getTypeByte() {
        return this.type;
    }

    public void setTypeByte(byte par1) {
        this.type = par1;
    }

    public SoupType getType() {
        return SoupType.getType(type);
    }

    public void setType(SoupType i) {
        byte b = (byte) i.id;
        this.type = b;
    }

    public byte getRemainByte() {
        return this.remain;
    }

    public void setRemainByte(byte i) {
        this.remain = i;
    }

    public boolean getDirection() {
        return this.direction;
    }

    public void setDirection(boolean par1) {
        this.direction = par1;
    }

    public String getCurrentTexture() {
        return this.getType().texture;
    }

    public String getDisplayName() {
        return this.getType().display;
    }

    private byte getCoolTime() {
        return this.coolTime;
    }

    private void setCoolTime(byte t) {
        this.coolTime = t;
    }

    public int getMetadata() { return 0; }

}
