package mods.defeatedcrow.common.tile;

import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
public class TileCPanel extends BlockEntity {
    public TileCPanel(BlockPos pos, BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_C_PANEL.get(), pos, state); }


    private ItemStack holdItem = null;

    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);

        if (par1CompoundTag.contains("HoldItem")) {
            this.setItemstack(ItemStack.of(par1CompoundTag.getCompound("HoldItem")));
        }
    }

    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);

        if (this.getItemstack() != null) {
            par1CompoundTag.put(
                "HoldItem",
                this.getItemstack()
                    .saveAdditional(new CompoundTag()));
        }
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

    public ItemStack getItemstack() {
        return this.holdItem;
    }

    public void setItemstack(ItemStack par1ItemStack) {
        this.holdItem = par1ItemStack;
    }

}
