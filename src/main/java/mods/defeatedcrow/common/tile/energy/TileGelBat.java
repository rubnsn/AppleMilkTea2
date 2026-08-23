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
    public TileGelBat(BlockPos pos, BlockState state) { super(null, pos, state); }


    private int chargeAmount = 0;

    @Override
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);

        this.chargeAmount = par1CompoundTag.getShort("ChargeAmount");
    }

    @Override
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);

        ListTag nbttaglist = new ListTag();

        par1CompoundTag.putShort("ChargeAmount", (short) this.chargeAmount);
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

    public void setChargeAmount(int par1) {
        this.chargeAmount = par1;
    }

    public int getChargeAmount() {
        return this.chargeAmount;
    }

}
