package mods.defeatedcrow.common.tile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
public class TileIncenseBase extends BlockEntity implements WorldlyContainer {
    public TileIncenseBase(BlockPos pos, BlockState state){ super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_INCENSE_BASE.get(), pos, state); java.util.Arrays.fill(items, ItemStack.EMPTY); }
    public ItemStack[] items = new ItemStack[2];
    public int burnTime=0;
    @Override public void load(CompoundTag tag) {
        if (tag == null) return;
        super.load(tag);
        NonNullList<ItemStack> list = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, list);
        for(int i=0;i<items.length;i++) items[i]=list.get(i);
        if(tag.contains("BurnTime")) burnTime=tag.getInt("BurnTime");
    }
    @Override public void saveAdditional(CompoundTag tag){ super.saveAdditional(tag); ContainerHelper.saveAllItems(tag, NonNullList.of(ItemStack.EMPTY, items)); tag.putInt("BurnTime", burnTime); }
    @Override public CompoundTag getUpdateTag(){ CompoundTag tag=super.getUpdateTag(); saveAdditional(tag); return tag; }
    @Override public ClientboundBlockEntityDataPacket getUpdatePacket(){ return ClientboundBlockEntityDataPacket.create(this); }
    public static void tick(Level level, BlockPos pos, BlockState state, TileIncenseBase be){
        if(level.isClientSide) return;
        ItemStack incense = be.items[0];
        if(incense.isEmpty()){ if(be.burnTime!=0){be.burnTime=0; be.setChanged();} return; }
        // simple: if incense present, increment burnTime and apply effect to players in 8 block radius every 40 ticks
        be.burnTime++;
        if(be.burnTime % 40 == 0){
            AABB box = new AABB(pos).inflate(8.0);
            var players = level.getEntitiesOfClass(Player.class, box);
            for(Player p : players){
                // apply regeneration as placeholder for 11 incense types
                // In real, would map incense item to MobEffect via ModMobEffects
                p.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0, false, true));
            }
        }
        // consume incense every 600 ticks (30 sec)
        if(be.burnTime >= 600){
            incense.shrink(1);
            if(incense.isEmpty()) be.items[0]=ItemStack.EMPTY;
            be.burnTime=0;
        }
        be.setChanged();
        if(be.burnTime % 20 == 0) level.sendBlockUpdated(pos, state, state, 3);
    }
    @Override public int getContainerSize(){ return items.length; }
    @Override public boolean isEmpty(){ for(ItemStack s:items) if(!s.isEmpty()) return false; return true; }
    @Override public ItemStack getItem(int i){ return items[i]; }
    @Override public ItemStack removeItem(int i,int count){
        if (items[i].isEmpty()) return ItemStack.EMPTY;
        if (items[i].getCount() <= count) { ItemStack s=items[i]; items[i]=ItemStack.EMPTY; setChanged(); return s; }
        else { ItemStack s=items[i].split(count); setChanged(); return s; }
    }
    @Override public ItemStack removeItemNoUpdate(int i){ ItemStack s=items[i]; items[i]=ItemStack.EMPTY; return s; }
    @Override public void setItem(int i, ItemStack s){ items[i]=s; setChanged(); }
    @Override public boolean stillValid(Player p){ return true; }
    @Override public void clearContent(){ for(int i=0;i<items.length;i++) items[i]=ItemStack.EMPTY; }
    @Override public int[] getSlotsForFace(Direction d){ return new int[]{0,1}; }
    @Override public boolean canPlaceItemThroughFace(int i, ItemStack s, Direction d){ return i==0; }
    @Override public boolean canTakeItemThroughFace(int i, ItemStack s, Direction d){ return i==1; }
}
