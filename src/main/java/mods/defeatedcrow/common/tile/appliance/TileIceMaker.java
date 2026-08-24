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
import net.minecraft.world.item.crafting.RecipeType;
import java.util.Optional;
import mods.defeatedcrow.common.registry.ModRecipes;
import mods.defeatedcrow.recipe.IceRecipe;

public class TileIceMaker extends BlockEntity implements WorldlyContainer {
    public TileIceMaker(BlockPos pos, BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_ICE_MAKER.get(), pos, state); }
    public int chargeAmount; public int currentItemCharge; public int cookTime; private int coolTime=8;
    public ItemStack[] iceItemStacks = new ItemStack[4];
    { java.util.Arrays.fill(iceItemStacks, ItemStack.EMPTY); }
    @Override public void load(CompoundTag tag) {
        if (tag == null) return;
        super.load(tag);
        NonNullList<ItemStack> list = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, list);
        for(int i=0;i<iceItemStacks.length;i++) iceItemStacks[i]=list.get(i);
        if(tag.contains("Charge")) chargeAmount=tag.getInt("Charge");
        if(tag.contains("CurrentCharge")) currentItemCharge=tag.getInt("CurrentCharge");
        if(tag.contains("CookTime")) cookTime=tag.getInt("CookTime");
        if(tag.contains("CoolTime")) coolTime=tag.getInt("CoolTime");
    }
    @Override public void saveAdditional(CompoundTag tag){
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, NonNullList.of(ItemStack.EMPTY, iceItemStacks));
        tag.putInt("Charge", chargeAmount);
        tag.putInt("CurrentCharge", currentItemCharge);
        tag.putInt("CookTime", cookTime);
        tag.putInt("CoolTime", coolTime);
    }
    @Override public CompoundTag getUpdateTag(){ CompoundTag tag=super.getUpdateTag(); saveAdditional(tag); return tag; }
    @Override public ClientboundBlockEntityDataPacket getUpdatePacket(){ return ClientboundBlockEntityDataPacket.create(this); }
    public int getCookProgressScaled(int p){ return cookTime * p /150; }
    public int getBurnTimeRemainingScaled(int p){ return chargeAmount * p /127; }
    public boolean isBurning(){ return cookTime>0; }
    public boolean isCharged(){ return chargeAmount>0; }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void tick(Level level, BlockPos pos, BlockState state, TileIceMaker be){
        if(level.isClientSide) return;
        ItemStack input = be.iceItemStacks[0];
        if (input.isEmpty()) { if(be.cookTime!=0){be.cookTime=0; be.setChanged();} return; }
        Optional<IceRecipe> opt = level.getRecipeManager().getRecipeFor((RecipeType)ModRecipes.ICE_TYPE.get(), be, level);
        if (opt.isEmpty()) { be.cookTime=0; return; }
        IceRecipe recipe = opt.get();
        if (!recipe.getIngredient().test(input)) { be.cookTime=0; return; }
        ItemStack result = recipe.getResult();
        ItemStack container = recipe.getContainer();
        ItemStack out = be.iceItemStacks[1];
        boolean canFit = out.isEmpty() || (out.is(result.getItem()) && out.getCount()+result.getCount() <= out.getMaxStackSize());
        ItemStack contSlot = be.iceItemStacks[2];
        boolean canFitCont = container.isEmpty() || contSlot.isEmpty() || (contSlot.is(container.getItem()) && contSlot.getCount()+container.getCount() <= contSlot.getMaxStackSize());
        if (!canFit || !canFitCont) { be.cookTime=0; return; }
        // simple charge check: require chargeAmount >0 or isHotBiome false
        // for test, ignore charge and just progress
        be.cookTime++;
        if (be.cookTime >= 100) {
            input.shrink(1);
            if (input.isEmpty()) be.iceItemStacks[0]=ItemStack.EMPTY;
            if (out.isEmpty()) be.iceItemStacks[1]=result.copy();
            else out.grow(result.getCount());
            if (!container.isEmpty()){
                if (contSlot.isEmpty()) be.iceItemStacks[2]=container.copy();
                else contSlot.grow(container.getCount());
            }
            be.cookTime=0;
            be.setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
        } else { be.setChanged(); }
    }
    @Override public int getContainerSize(){ return iceItemStacks.length; }
    @Override public boolean isEmpty(){ for(ItemStack s:iceItemStacks) if(!s.isEmpty()) return false; return true; }
    @Override public ItemStack getItem(int i){ return iceItemStacks[i]; }
    @Override public ItemStack removeItem(int i,int count){
        if (iceItemStacks[i].isEmpty()) return ItemStack.EMPTY;
        if (iceItemStacks[i].getCount() <= count) { ItemStack s=iceItemStacks[i]; iceItemStacks[i]=ItemStack.EMPTY; setChanged(); return s; }
        else { ItemStack s=iceItemStacks[i].split(count); setChanged(); return s; }
    }
    @Override public ItemStack removeItemNoUpdate(int i){ ItemStack s=iceItemStacks[i]; iceItemStacks[i]=ItemStack.EMPTY; return s; }
    @Override public void setItem(int i, ItemStack s){ iceItemStacks[i]=s; setChanged(); }
    @Override public boolean stillValid(Player p){ return true; }
    @Override public void clearContent(){ for(int i=0;i<iceItemStacks.length;i++) iceItemStacks[i]=ItemStack.EMPTY; }
    @Override public int[] getSlotsForFace(Direction d){ return new int[]{0,1,2,3}; }
    @Override public boolean canPlaceItemThroughFace(int i, ItemStack s, Direction d){ return i==0; }
    @Override public boolean canTakeItemThroughFace(int i, ItemStack s, Direction d){ return i==1 || i==2; }
    public int isHotBiome(){ return 0; }
    public static int getItemBurnTime(ItemStack s){ return 0; }
    public static boolean isItemFuel(ItemStack s){ return false; }
}
