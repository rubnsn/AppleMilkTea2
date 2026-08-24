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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.crafting.RecipeType;
import java.util.Optional;
import mods.defeatedcrow.common.registry.ModRecipes;
import mods.defeatedcrow.recipe.PanRecipe;
public class TileFilledSoupPan extends BlockEntity implements WorldlyContainer {
    public TileFilledSoupPan(BlockPos pos, BlockState state){ super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_FILLED_SOUP_PAN.get(), pos, state); java.util.Arrays.fill(items, ItemStack.EMPTY); }
    public ItemStack[] items = new ItemStack[2];
    public int cookTime=0;
    @Override public void load(CompoundTag tag) {
        if (tag == null) return;
        super.load(tag);
        NonNullList<ItemStack> list = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, list);
        for (int i=0;i<items.length;i++) items[i]=list.get(i);
        if(tag.contains("CookTime")) cookTime=tag.getInt("CookTime");
    }
    @Override public void saveAdditional(CompoundTag tag){
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, NonNullList.of(ItemStack.EMPTY, items));
        tag.putInt("CookTime", cookTime);
    }
    @Override public CompoundTag getUpdateTag(){ CompoundTag tag=super.getUpdateTag(); saveAdditional(tag); return tag; }
    @Override public ClientboundBlockEntityDataPacket getUpdatePacket(){ return ClientboundBlockEntityDataPacket.create(this); }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void tick(Level level, BlockPos pos, BlockState state, TileFilledSoupPan be){
        if(level.isClientSide) return;
        BlockPos below = pos.below();
        var belowState = level.getBlockState(below);
        boolean isHeat = belowState.is(Blocks.FIRE) || belowState.is(Blocks.LAVA) || belowState.is(Blocks.CAMPFIRE) || belowState.is(Blocks.MAGMA_BLOCK);
        if (!isHeat) { if(be.cookTime!=0){be.cookTime=0; be.setChanged();} return; }
        ItemStack input = be.items[0];
        if (input.isEmpty()) { be.cookTime=0; return; }
        Optional<PanRecipe> opt = level.getRecipeManager().getRecipeFor((RecipeType)ModRecipes.PAN_TYPE.get(), be, level);
        if (opt.isEmpty()) { be.cookTime=0; return; }
        PanRecipe recipe = opt.get();
        if (!recipe.getIngredient().test(input)) { be.cookTime=0; return; }
        ItemStack result = recipe.getResult();
        ItemStack out = be.items[1];
        boolean canFit = out.isEmpty() || (out.is(result.getItem()) && out.getCount()+result.getCount() <= out.getMaxStackSize());
        if (!canFit) { be.cookTime=0; return; }
        be.cookTime++;
        if (be.cookTime >= recipe.getCookingTime()) {
            input.shrink(1);
            if (input.isEmpty()) be.items[0]=ItemStack.EMPTY;
            if (out.isEmpty()) be.items[1]=result.copy();
            else out.grow(result.getCount());
            be.cookTime=0;
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
