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

public class TileIceMaker extends BlockEntity implements WorldlyContainer {
    public TileIceMaker(BlockPos pos, BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_ICE_MAKER.get(), pos, state); }
    public int chargeAmount; public int currentItemCharge; public int cookTime; private int coolTime=8;
    public ItemStack[] iceItemStacks = new ItemStack[4];
    { java.util.Arrays.fill(iceItemStacks, ItemStack.EMPTY); }
    @Override public void load(CompoundTag tag) {
        if (tag == null) return;
        super.load(tag); }
    @Override public void saveAdditional(CompoundTag tag){ super.saveAdditional(tag); }
    @Override public ClientboundBlockEntityDataPacket getUpdatePacket(){ return ClientboundBlockEntityDataPacket.create(this); }
    
    public int getCookProgressScaled(int p){ return cookTime * p /150; }
    public int getBurnTimeRemainingScaled(int p){ return chargeAmount * p /127; }
    public boolean isBurning(){ return cookTime>0; }
    public boolean isCharged(){ return chargeAmount>0; }
    public static void tick(Level level, BlockPos pos, BlockState state, TileIceMaker be){ if(level.isClientSide) return; be.setChanged(); }
    @Override public int getContainerSize(){ return iceItemStacks.length; }
    @Override public boolean isEmpty(){ for(ItemStack s:iceItemStacks) if(!s.isEmpty()) return false; return true; }
    @Override public ItemStack getItem(int i){ return iceItemStacks[i]; }
    @Override public ItemStack removeItem(int i,int j){ return ItemStack.EMPTY; }
    @Override public ItemStack removeItemNoUpdate(int i){ ItemStack s=iceItemStacks[i]; iceItemStacks[i]=ItemStack.EMPTY; return s; }
    @Override public void setItem(int i, ItemStack s){ iceItemStacks[i]=s; }
    @Override public boolean stillValid(Player p){ return true; }
    @Override public void clearContent(){ for(int i=0;i<iceItemStacks.length;i++) iceItemStacks[i]=ItemStack.EMPTY; }
    @Override public int[] getSlotsForFace(Direction d){ return new int[]{0}; }
    @Override public boolean canPlaceItemThroughFace(int i, ItemStack s, Direction d){ return true; }
    @Override public boolean canTakeItemThroughFace(int i, ItemStack s, Direction d){ return true; }
    public int isHotBiome(){ return 0; }
    public static int getItemBurnTime(ItemStack s){ return 0; }
    public static boolean isItemFuel(ItemStack s){ return false; }
}
