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
public class TilePanG extends BlockEntity implements WorldlyContainer {
    public TilePanG(BlockPos pos, BlockState state){ super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_PAN_G.get(), pos, state); }
    public ItemStack[] items = new ItemStack[2];
    @Override public void load(CompoundTag t){ super.load(t); }
    @Override public void saveAdditional(CompoundTag t){ super.saveAdditional(t); }
    @Override public ClientboundBlockEntityDataPacket getUpdatePacket(){ return ClientboundBlockEntityDataPacket.create(this); }
    
    public static void tick(Level level, BlockPos pos, BlockState state, TilePanG be){ if(level.isClientSide) return; be.setChanged(); }
    @Override public int getContainerSize(){ return items.length; }
    @Override public boolean isEmpty(){ return true; }
    @Override public ItemStack getItem(int i){ return items[i]==null?ItemStack.EMPTY:items[i]; }
    @Override public ItemStack removeItem(int i,int j){ return ItemStack.EMPTY; }
    @Override public ItemStack removeItemNoUpdate(int i){ ItemStack s=items[i]; items[i]=ItemStack.EMPTY; return s==null?ItemStack.EMPTY:s; }
    @Override public void setItem(int i, ItemStack s){ items[i]=s; }
    @Override public boolean stillValid(Player p){ return true; }
    @Override public void clearContent(){ for(int i=0;i<items.length;i++) items[i]=ItemStack.EMPTY; }
    @Override public int[] getSlotsForFace(Direction d){ return new int[]{0}; }
    @Override public boolean canPlaceItemThroughFace(int i, ItemStack s, Direction d){ return true; }
    @Override public boolean canTakeItemThroughFace(int i, ItemStack s, Direction d){ return true; }
}
