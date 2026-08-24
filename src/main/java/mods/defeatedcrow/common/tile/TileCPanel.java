package mods.defeatedcrow.common.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TileCPanel extends BlockEntity {
    private ItemStack holdItem = ItemStack.EMPTY;

    public TileCPanel(BlockPos pos, BlockState state) {
        super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_C_PANEL.get(), pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("HoldItem")) {
            this.holdItem = ItemStack.of(tag.getCompound("HoldItem"));
        } else {
            this.holdItem = ItemStack.EMPTY;
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (!holdItem.isEmpty()) {
            tag.put("HoldItem", holdItem.save(new CompoundTag()));
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public ItemStack getItemstack() {
        return holdItem.isEmpty() ? null : holdItem;
    }

    public void setItemstack(ItemStack stack) {
        this.holdItem = stack == null ? ItemStack.EMPTY : stack;
        setChanged();
        if (level != null) level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }
}
