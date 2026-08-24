package mods.defeatedcrow.common.tile.appliance;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

// Stub for 1.20.1 - original had many 1.7.10 APIs (IPipeConnection, saveAdditional misuse, pos var etc)
// Minimal compile stub; runtime functionality TODO per doc/tile-entities/migration-guide.md
public class TileTeppanII extends BlockEntity implements WorldlyContainer {
    public TileTeppanII(BlockPos pos, BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_TEPPAN_II.get(), pos, state); }
    public ItemStack[] plateItems = new ItemStack[4];
    { java.util.Arrays.fill(plateItems, ItemStack.EMPTY); }
    @Override public void load(CompoundTag tag) {
        if (tag == null) return;
        super.load(tag); }
    @Override public void saveAdditional(CompoundTag tag) { super.saveAdditional(tag); }
    @Override public ClientboundBlockEntityDataPacket getUpdatePacket() { return ClientboundBlockEntityDataPacket.create(this); }
    public static void tick(Level level, BlockPos pos, BlockState state, TileTeppanII be) { if (level.isClientSide) return; be.setChanged(); }
    @Override public int getContainerSize() { return plateItems.length; }
    @Override public boolean isEmpty(){ for(ItemStack s:plateItems) if(!s.isEmpty()) return false; return true; }
    @Override public ItemStack getItem(int i) { return plateItems[i]; }
    @Override public ItemStack removeItem(int i, int j) { return ItemStack.EMPTY; }
    @Override public ItemStack removeItemNoUpdate(int i) { ItemStack s = plateItems[i]; plateItems[i]=ItemStack.EMPTY; return s; }
    @Override public void setItem(int i, ItemStack s) { plateItems[i]=s; }
    @Override public boolean stillValid(Player p) { return true; }
    @Override public void clearContent() { for(int i=0;i<plateItems.length;i++) plateItems[i]=ItemStack.EMPTY; }
    @Override public int[] getSlotsForFace(Direction d) { return new int[]{0}; }
    @Override public boolean canPlaceItemThroughFace(int i, ItemStack s, Direction d) { return true; }
    @Override public boolean canTakeItemThroughFace(int i, ItemStack s, Direction d) { return true; }
}
