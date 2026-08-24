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
public class TilePanG extends BlockEntity implements WorldlyContainer {
    public TilePanG(BlockPos pos, BlockState state){ super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_PAN_G.get(), pos, state); java.util.Arrays.fill(items, ItemStack.EMPTY); }
    public ItemStack[] items = new ItemStack[2];
    public int cookTime = 0;
    @Override public void load(CompoundTag tag) {
        if (tag == null) return;
        super.load(tag);
        NonNullList<ItemStack> list = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, list);
        for (int i=0;i<items.length;i++) items[i]=list.get(i);
        if (tag.contains("CookTime")) cookTime = tag.getInt("CookTime");
    }
    @Override public void saveAdditional(CompoundTag tag){
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, NonNullList.of(ItemStack.EMPTY, items));
        tag.putInt("CookTime", cookTime);
    }
    @Override public CompoundTag getUpdateTag(){ CompoundTag tag=super.getUpdateTag(); saveAdditional(tag); return tag; }
    @Override public ClientboundBlockEntityDataPacket getUpdatePacket(){ return ClientboundBlockEntityDataPacket.create(this); }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void tick(Level level, BlockPos pos, BlockState state, TilePanG be){
        if(level.isClientSide) return;
        // heat source check: block below is fire/lava/campfire/magma/furnace lit
        BlockPos below = pos.below();
        var belowState = level.getBlockState(below);
        boolean isHeat = belowState.is(Blocks.FIRE) || belowState.is(Blocks.LAVA) || belowState.is(Blocks.CAMPFIRE) || belowState.is(Blocks.MAGMA_BLOCK) || belowState.is(Blocks.FURNACE) || belowState.is(Blocks.SMOKER) || belowState.is(Blocks.BLAST_FURNACE);
        // also allow soul fire etc.
        if (!isHeat) { if(be.cookTime!=0){be.cookTime=0; be.setChanged();} return; }
        ItemStack input = be.items[0];
        if (input.isEmpty()) { if(be.cookTime!=0){be.cookTime=0; be.setChanged();} return; }
        Optional<PanRecipe> opt = level.getRecipeManager().getRecipeFor((RecipeType)ModRecipes.PAN_TYPE.get(), be, level);
        if (opt.isEmpty()) { if(be.cookTime!=0){be.cookTime=0; be.setChanged();} return; }
        PanRecipe recipe = opt.get();
        // verify ingredient matches slot 0 explicitly (getRecipeFor already checks matches via any slot, but ensure slot0)
        if (!recipe.getIngredient().test(input)) { be.cookTime=0; return; }
        ItemStack result = recipe.getResult();
        ItemStack output = be.items[1];
        boolean canFit = output.isEmpty() || (output.is(result.getItem()) && output.getCount() + result.getCount() <= output.getMaxStackSize() && output.getCount() + result.getCount() <= be.getMaxStackSize());
        if (!canFit) { be.cookTime=0; return; }
        be.cookTime++;
        int total = recipe.getCookingTime();
        if (be.cookTime >= total) {
            // consume input
            input.shrink(1);
            if (input.isEmpty()) be.items[0]=ItemStack.EMPTY;
            // produce output
            if (output.isEmpty()) be.items[1]=result.copy();
            else output.grow(result.getCount());
            be.cookTime=0;
            be.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
        } else {
            be.setChanged();
        }
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
    @Override public boolean canPlaceItemThroughFace(int i, ItemStack s, Direction d){ return true; }
    @Override public boolean canTakeItemThroughFace(int i, ItemStack s, Direction d){ return i==1; }
}
