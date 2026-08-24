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
import mods.defeatedcrow.recipe.PlateRecipe;

public class TileTeppanII extends BlockEntity implements WorldlyContainer {
    public TileTeppanII(BlockPos pos, BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_TEPPAN_II.get(), pos, state); }
    public ItemStack[] plateItems = new ItemStack[4];
    { java.util.Arrays.fill(plateItems, ItemStack.EMPTY); }
    public int cookTime=0;
    @Override public void load(CompoundTag tag) {
        if (tag == null) return;
        super.load(tag);
        NonNullList<ItemStack> list = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, list);
        for (int i=0;i<plateItems.length;i++) plateItems[i]=list.get(i);
        if(tag.contains("CookTime")) cookTime=tag.getInt("CookTime");
    }
    @Override public void saveAdditional(CompoundTag tag) { super.saveAdditional(tag); ContainerHelper.saveAllItems(tag, NonNullList.of(ItemStack.EMPTY, plateItems)); tag.putInt("CookTime", cookTime); }
    @Override public CompoundTag getUpdateTag(){ CompoundTag tag=super.getUpdateTag(); saveAdditional(tag); return tag; }
    @Override public ClientboundBlockEntityDataPacket getUpdatePacket() { return ClientboundBlockEntityDataPacket.create(this); }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void tick(Level level, BlockPos pos, BlockState state, TileTeppanII be) {
        if(level.isClientSide) return;
        // heat check like Pan
        BlockPos below = pos.below();
        var belowState = level.getBlockState(below);
        boolean isHeat = belowState.is(Blocks.FIRE) || belowState.is(Blocks.LAVA) || belowState.is(Blocks.CAMPFIRE) || belowState.is(Blocks.MAGMA_BLOCK);
        if (!isHeat) { if(be.cookTime!=0){be.cookTime=0; be.setChanged();} return; }
        // use first occupied slot as input
        int inSlot=-1; for(int i=0;i<be.plateItems.length;i++) if(!be.plateItems[i].isEmpty()){inSlot=i; break;}
        if(inSlot==-1) { be.cookTime=0; return; }
        // create temporary container with that stack for recipe lookup
        // For Plate, ingredient is single, so we can query with be itself (matches checks any slot)
        Optional<PlateRecipe> opt = level.getRecipeManager().getRecipeFor((RecipeType)ModRecipes.PLATE_TYPE.get(), be, level);
        if(opt.isEmpty()){ be.cookTime=0; return; }
        PlateRecipe recipe = opt.get();
        ItemStack input = be.plateItems[inSlot];
        if(!recipe.getIngredient().test(input)){ be.cookTime=0; return; }
        ItemStack result = recipe.getResult();
        // find output slot: first empty or same item
        int outSlot=-1;
        for(int i=0;i<be.plateItems.length;i++) if(i!=inSlot && be.plateItems[i].isEmpty()){outSlot=i; break;}
        if(outSlot==-1){
            for(int i=0;i<be.plateItems.length;i++) if(i!=inSlot && be.plateItems[i].is(result.getItem())){outSlot=i; break;}
        }
        // if no empty and no matching, check if we can still handle (need empty)
        if(outSlot==-1){ be.cookTime=0; return; }
        ItemStack out = be.plateItems[outSlot];
        boolean canFit = out.isEmpty() || (out.is(result.getItem()) && out.getCount()+result.getCount() <= out.getMaxStackSize());
        if(!canFit){ be.cookTime=0; return; }
        be.cookTime++;
        if(be.cookTime>=100){
            input.shrink(1);
            if(input.isEmpty()) be.plateItems[inSlot]=ItemStack.EMPTY;
            if(out.isEmpty()) be.plateItems[outSlot]=result.copy();
            else out.grow(result.getCount());
            be.cookTime=0;
            be.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
        } else { be.setChanged(); }
    }
    @Override public int getContainerSize() { return plateItems.length; }
    @Override public boolean isEmpty(){ for(ItemStack s:plateItems) if(!s.isEmpty()) return false; return true; }
    @Override public ItemStack getItem(int i) { return plateItems[i]; }
    @Override public ItemStack removeItem(int i, int count){
        if (plateItems[i].isEmpty()) return ItemStack.EMPTY;
        if (plateItems[i].getCount() <= count) { ItemStack s=plateItems[i]; plateItems[i]=ItemStack.EMPTY; setChanged(); return s; }
        else { ItemStack s=plateItems[i].split(count); setChanged(); return s; }
    }
    @Override public ItemStack removeItemNoUpdate(int i) { ItemStack s = plateItems[i]; plateItems[i]=ItemStack.EMPTY; return s; }
    @Override public void setItem(int i, ItemStack s) { plateItems[i]=s; setChanged(); }
    @Override public boolean stillValid(Player p) { return true; }
    @Override public void clearContent() { for(int i=0;i<plateItems.length;i++) plateItems[i]=ItemStack.EMPTY; }
    @Override public int[] getSlotsForFace(Direction d) { return new int[]{0,1,2,3}; }
    @Override public boolean canPlaceItemThroughFace(int i, ItemStack s, Direction d) { return true; }
    @Override public boolean canTakeItemThroughFace(int i, ItemStack s, Direction d) { return true; }
}
