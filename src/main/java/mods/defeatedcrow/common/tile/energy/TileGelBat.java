package mods.defeatedcrow.common.tile.energy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
// chargeの一時保管用
public class TileGelBat extends BlockEntity {
    public TileGelBat(BlockPos pos, BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_GEL_BAT.get(), pos, state); }


    private int chargeAmount = 0;

    @Override
    public void load(CompoundTag tag) {
        if (tag == null) return;
        super.load(tag);

        this.chargeAmount = tag.getShort("ChargeAmount");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        ListTag nbttaglist = new ListTag();

        tag.putShort("ChargeAmount", (short) this.chargeAmount);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
        this.saveAdditional(tag);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
        if (tag != null) this.load(tag);
    }

    public void setChargeAmount(int chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public int getChargeAmount() {
        return this.chargeAmount;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TileGelBat be) {
        if (level.isClientSide) return;
        be.setChanged();
    }

}
