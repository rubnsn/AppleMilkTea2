package mods.defeatedcrow.common.tile.appliance;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TileEvaporator extends BlockEntity implements WorldlyContainer {
    public TileEvaporator(BlockPos pos, BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_EVAPORATOR.get(), pos, state); }
    public ItemStack[] items = new ItemStack[3];
    { java.util.Arrays.fill(items, ItemStack.EMPTY); }
    @Override public void load(CompoundTag tag) {
        if (tag == null) return;
        super.load(tag);
        NonNullList<ItemStack> list = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, list);
        for(int i=0;i<items.length;i++) items[i]=list.get(i);
    }
    @Override public void saveAdditional(CompoundTag tag) { super.saveAdditional(tag); ContainerHelper.saveAllItems(tag, NonNullList.of(ItemStack.EMPTY, items)); }
    @Override public CompoundTag getUpdateTag(){ CompoundTag tag=super.getUpdateTag(); saveAdditional(tag); return tag; }
    @Override public ClientboundBlockEntityDataPacket getUpdatePacket() { return ClientboundBlockEntityDataPacket.create(this); }
    public static void tick(Level level, BlockPos pos, BlockState state, TileEvaporator be) { if(level.isClientSide) return; be.setChanged(); }
    @Override public int getContainerSize() { return items.length; }
    @Override public boolean isEmpty(){ for(ItemStack s:items) if(!s.isEmpty()) return false; return true; }
    @Override public ItemStack getItem(int i) { return items[i]; }
    @Override public ItemStack removeItem(int i,int count){
        if (items[i].isEmpty()) return ItemStack.EMPTY;
        if (items[i].getCount() <= count) { ItemStack s=items[i]; items[i]=ItemStack.EMPTY; setChanged(); return s; }
        else { ItemStack s=items[i].split(count); setChanged(); return s; }
    }
    @Override public ItemStack removeItemNoUpdate(int i){ ItemStack s=items[i]; items[i]=ItemStack.EMPTY; return s; }
    @Override public void setItem(int i, ItemStack s){ items[i]=s; setChanged(); }
    @Override public boolean stillValid(Player p){ return true; }
    @Override public void clearContent(){ for(int i=0;i<items.length;i++) items[i]=ItemStack.EMPTY; }
    @Override public int[] getSlotsForFace(Direction d){ return new int[]{0,1,2}; }
    @Override public boolean canPlaceItemThroughFace(int i, ItemStack s, Direction d){ return true; }
    @Override public boolean canTakeItemThroughFace(int i, ItemStack s, Direction d){ return true; }
}
