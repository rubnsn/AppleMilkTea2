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

public class TileAdvProcessor extends BlockEntity implements WorldlyContainer {
    public TileAdvProcessor(BlockPos pos, BlockState state){ super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_ADV_PROCESSOR.get(), pos, state); java.util.Arrays.fill(items, ItemStack.EMPTY); }
    public int cookTime; public int chargeAmount;
    public ItemStack[] items = new ItemStack[14];
    @Override public void load(CompoundTag tag){ super.load(tag); }
    @Override public void saveAdditional(CompoundTag tag){ super.saveAdditional(tag); }
    @Override public ClientboundBlockEntityDataPacket getUpdatePacket(){ return ClientboundBlockEntityDataPacket.create(this); }
    
    public static void tick(Level level, BlockPos pos, BlockState state, TileAdvProcessor be){ if(level.isClientSide) return; be.setChanged(); }
    public int getChargeAmount(){ return chargeAmount; }
    public void setChargeAmount(int v){ chargeAmount=v; }
    public static boolean isItemFuel(ItemStack s){ return false; }
    @Override public int getContainerSize(){ return items.length; }
    @Override public boolean isEmpty(){ for(ItemStack s:items) if(!s.isEmpty()) return false; return true; }
    @Override public ItemStack getItem(int i){ return items[i]; }
    @Override public ItemStack removeItem(int i,int j){ return ItemStack.EMPTY; }
    @Override public ItemStack removeItemNoUpdate(int i){ ItemStack s=items[i]; items[i]=ItemStack.EMPTY; return s; }
    @Override public void setItem(int i, ItemStack s){ items[i]=s; }
    @Override public boolean stillValid(Player p){ return true; }
    @Override public void clearContent(){ for(int i=0;i<items.length;i++) items[i]=ItemStack.EMPTY; }
    @Override public int[] getSlotsForFace(Direction d){ return new int[]{0}; }
    @Override public boolean canPlaceItemThroughFace(int i, ItemStack s, Direction d){ return true; }
    @Override public boolean canTakeItemThroughFace(int i, ItemStack s, Direction d){ return true; }
}
