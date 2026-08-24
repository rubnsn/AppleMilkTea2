package mods.defeatedcrow.common.tile;

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
import net.minecraft.world.item.crafting.RecipeType;
import java.util.Optional;
import mods.defeatedcrow.common.registry.ModRecipes;
import mods.defeatedcrow.recipe.BrewingRecipe;
import net.minecraftforge.fluids.FluidStack;

public class TileBrewingBarrel extends BlockEntity implements WorldlyContainer {
    public TileBrewingBarrel(BlockPos pos, BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_BREWING_BARREL.get(), pos, state); }
    public ItemStack[] items = new ItemStack[2];
    { java.util.Arrays.fill(items, ItemStack.EMPTY); }
    public int brewTime=0;
    @Override public void load(CompoundTag tag) {
        if (tag == null) return;
        super.load(tag);
        NonNullList<ItemStack> list = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, list);
        for(int i=0;i<items.length;i++) items[i]=list.get(i);
        if(tag.contains("BrewTime")) brewTime=tag.getInt("BrewTime");
    }
    @Override public void saveAdditional(CompoundTag tag){ super.saveAdditional(tag); ContainerHelper.saveAllItems(tag, NonNullList.of(ItemStack.EMPTY, items)); tag.putInt("BrewTime", brewTime); }
    @Override public CompoundTag getUpdateTag(){ CompoundTag tag=super.getUpdateTag(); saveAdditional(tag); return tag; }
    @Override public ClientboundBlockEntityDataPacket getUpdatePacket(){ return ClientboundBlockEntityDataPacket.create(this); }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void tick(Level level, BlockPos pos, BlockState state, TileBrewingBarrel be){
        if(level.isClientSide) return;
        // Brewing is fluid based; for P2 we implement simple item aging: if slot0 has item, after 200 ticks move to slot1
        // Real fluid tank will be TileEvaporator/Barrel with ForgeCap, but for now use BrewingRecipe via empty check
        // Try to find brewing recipe via fluid: if items[0] is young alcohol bottle, treat its fluid tag as input
        // For test, just use time-based conversion: if items[0] not empty and items[1] can accept, brew
        if(be.items[0].isEmpty()){ if(be.brewTime!=0){be.brewTime=0; be.setChanged();} return; }
        // check if output slot can accept (if empty)
        ItemStack out = be.items[1];
        // For P2, we don't have fluid to item mapping, so just check if brewing recipe exists for any fluid
        // Use a dummy check: if level has any brewing recipe, consider barrel can brew
        var mgr = level.getRecipeManager();
        var recipes = mgr.getAllRecipesFor((RecipeType)ModRecipes.BREWING_TYPE.get());
        if(recipes.isEmpty()){ be.brewTime=0; return; }
        // simple: after 200 ticks, copy input to output (simulate aging)
        boolean canFit = out.isEmpty() || out.getCount() < out.getMaxStackSize();
        if(!canFit){ be.brewTime=0; return; }
        be.brewTime++;
        if(be.brewTime>=200){
            ItemStack input = be.items[0];
            // simulate: consume 1 from input, produce 1 aged in output (same item for now)
            // In real, would convert fluid young->aged via FluidStack
            ItemStack result = input.copy(); result.setCount(1);
            // try to find matching recipe to get output fluid name, but for now just copy
            // If recipe found, we could produce largeBottle with aged fluid NBT, but simplified
            input.shrink(1);
            if(input.isEmpty()) be.items[0]=ItemStack.EMPTY;
            if(out.isEmpty()) be.items[1]=result;
            else out.grow(1);
            be.brewTime=0;
            be.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
        } else { be.setChanged(); }
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
