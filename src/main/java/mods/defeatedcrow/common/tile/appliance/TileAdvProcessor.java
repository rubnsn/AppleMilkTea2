package mods.defeatedcrow.common.tile.appliance;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.crafting.RecipeType;
import java.util.Optional;
import mods.defeatedcrow.common.registry.ModRecipes;
import mods.defeatedcrow.recipe.AdvProcessorRecipe;

public class TileAdvProcessor extends BlockEntity implements WorldlyContainer {
    public TileAdvProcessor(BlockPos pos, BlockState state){ super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_ADV_PROCESSOR.get(), pos, state); java.util.Arrays.fill(items, ItemStack.EMPTY); }
    public int cookTime; public int chargeAmount;
    public ItemStack[] items = new ItemStack[14];
    @Override public void load(CompoundTag tag) {
        if (tag == null) return;
        super.load(tag);
        NonNullList<ItemStack> list = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, list);
        for (int i=0;i<items.length;i++) items[i]=list.get(i);
        if (tag.contains("CookTime")) cookTime = tag.getInt("CookTime");
        if (tag.contains("Charge")) chargeAmount = tag.getInt("Charge");
    }
    @Override public void saveAdditional(CompoundTag tag){
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, NonNullList.of(ItemStack.EMPTY, items));
        tag.putInt("CookTime", cookTime);
        tag.putInt("Charge", chargeAmount);
    }
    @Override public CompoundTag getUpdateTag(){ CompoundTag tag=super.getUpdateTag(); saveAdditional(tag); return tag; }
    @Override public ClientboundBlockEntityDataPacket getUpdatePacket(){ return ClientboundBlockEntityDataPacket.create(this); }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void tick(Level level, BlockPos pos, BlockState state, TileAdvProcessor be){
        if(level.isClientSide) return;
        SimpleContainer ingCont = new SimpleContainer(9);
        for(int i=0;i<9;i++) ingCont.setItem(i, be.items[2+i]);
        Optional<AdvProcessorRecipe> opt = level.getRecipeManager().getRecipeFor((RecipeType)ModRecipes.ADV_PROCESSOR_TYPE.get(), ingCont, level);
        if (opt.isEmpty()) { if(be.cookTime!=0){be.cookTime=0; be.setChanged();} return; }
        AdvProcessorRecipe recipe = opt.get();
        ItemStack result = recipe.getResultStack();
        ItemStack out11 = be.items[11];
        boolean can11 = result.isEmpty() || out11.isEmpty() || (out11.is(result.getItem()) && out11.getCount()+result.getCount() <= out11.getMaxStackSize());
        if (!can11) { be.cookTime=0; return; }
        be.cookTime++;
        if (be.cookTime >= 100) {
            for (var ing : recipe.getIngredients()) {
                for(int s=2;s<=10;s++){
                    if(!be.items[s].isEmpty() && ing.test(be.items[s])){
                        be.items[s].shrink(1);
                        if(be.items[s].isEmpty()) be.items[s]=ItemStack.EMPTY;
                        break;
                    }
                }
            }
            if (!result.isEmpty()){
                if(out11.isEmpty()) be.items[11]=result.copy();
                else out11.grow(result.getCount());
            }
            be.cookTime=0;
            be.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
        } else { be.setChanged(); }
    }
    public int getChargeAmount(){ return chargeAmount; }
    public void setChargeAmount(int v){ chargeAmount=v; }
    public static boolean isItemFuel(ItemStack s){ return false; }
    @Override public int getContainerSize(){ return items.length; }
    @Override public boolean isEmpty(){ for(ItemStack s:items) if(!s.isEmpty()) return false; return true; }
    @Override public ItemStack getItem(int i){ return items[i]; }
    @Override public ItemStack removeItem(int i,int count){
        if (items[i].isEmpty()) return ItemStack.EMPTY;
        if (items[i].getCount() <= count) { ItemStack s=items[i]; items[i]=ItemStack.EMPTY; setChanged(); return s; }
        else { ItemStack s=items[i].split(count); setChanged(); return s; }
    }
    @Override public ItemStack removeItemNoUpdate(int i){ ItemStack s=items[i]; items[i]=ItemStack.EMPTY; return s; }
    @Override public void setItem(int i, ItemStack s){ items[i]=s; if (!s.isEmpty() && s.getCount() > getMaxStackSize()) s.setCount(getMaxStackSize()); setChanged(); }
    @Override public boolean stillValid(Player p){ return true; }
    @Override public void clearContent(){ for(int i=0;i<items.length;i++) items[i]=ItemStack.EMPTY; }
    @Override public int[] getSlotsForFace(Direction d){ return new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13}; }
    @Override public boolean canPlaceItemThroughFace(int i, ItemStack s, Direction d){ return i>=2 && i<=10; }
    @Override public boolean canTakeItemThroughFace(int i, ItemStack s, Direction d){ return i==11 || i==12; }
}
