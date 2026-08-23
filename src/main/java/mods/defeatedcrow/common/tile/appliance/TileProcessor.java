package mods.defeatedcrow.common.tile.appliance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;

import mods.defeatedcrow.api.appliance.IProcessorPanel;
import mods.defeatedcrow.api.appliance.IProcessorRecipeTool;
import mods.defeatedcrow.api.recipe.IProcessorRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.handler.TagHelper;

public class TileProcessor extends MachineBase {
    public TileProcessor(net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.state.BlockState state) { super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_PROCESSOR.get(), pos, state); }


    @Override
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);
    }

    @Override
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return super.getUpdatePacket();
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
    }

    @Override
    public boolean canSmelt() {
        boolean flag1 = false;
        boolean flag2 = false;

        List<ItemStack> items = new ArrayList<ItemStack>(this.getCurrentContains());
        List<IProcessorRecipe> recipes = new ArrayList<IProcessorRecipe>(
            RecipeRegisterManager.processorRecipe.getRecipes());
        if (recipes == null || recipes.isEmpty()) return false;

        ItemStack output = null;
        ItemStack sec = null;
        ItemStack cont = null;
        float chance = 1.0F;

        for (IProcessorRecipe recipe : recipes) {
            if (recipe.isFoodRecipe() == this.acceptFoodRecipe() && this.ismatchTier(recipe) && recipe.matches(items)) {
                output = recipe.getOutput();
                sec = recipe.getSecondary();
                chance = recipe.getChance();
                cont = recipe.getCraftingRemainingItem(items);
                break;
            }
        }

        if (output == null && sec == null) return false;

        if (output == null) flag1 = true;
        else {
            if (this.itemstacks[11] == null) {
                flag1 = true;
            } else {
                if (this.net.minecraft.world.item.ItemStack.isSameItemSameTags(itemstacks[11], output)) {
                    int result = this.itemstacks[11].getCount() + output.getCount();
                    flag1 = (result <= this.getMaxStackSize() && result <= output.getMaxStackSize());
                }
            }
        }

        if (flag1) {
            if (sec != null) {
                if (this.itemstacks[12] == null) {
                    flag2 = true;
                } else {
                    if (this.net.minecraft.world.item.ItemStack.isSameItemSameTags(itemstacks[12], sec)) {
                        int result = this.itemstacks[12].getCount() + sec.getCount();
                        flag2 = (result <= this.getMaxStackSize() && result <= sec.getMaxStackSize());
                    }
                }
            } else if (cont != null) {
                if (this.itemstacks[12] == null) {
                    flag2 = true;
                } else {
                    if (this.net.minecraft.world.item.ItemStack.isSameItemSameTags(itemstacks[12], cont)) {
                        int result = this.itemstacks[12].getCount() + cont.getCount();
                        flag2 = (result <= this.getMaxStackSize() && result <= cont.getMaxStackSize());
                    }
                }
            } else {
                flag2 = true;
            }
        }

        return flag1 && flag2;
    }

    protected List<ItemStack> getCurrentContains() {
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        for (int i = 2; i < 11; i++) {
            if (this.itemstacks[i] != null) {
                items.add(this.itemstacks[i].copy());
            }
        }
        return items;
    }

    public boolean acceptFoodRecipe() {
        return true;
    }

    public boolean ismatchTier(IProcessorRecipe recipe) {
        return true;
    }

    public void onRecipeOutput() {

    }

    @Override
    public void onProgress() {
        // 結局canSmelt()と同じことをしていて無駄な感じはする
        List<ItemStack> items = new ArrayList<ItemStack>(this.getCurrentContains());
        List<IProcessorRecipe> recipes = new ArrayList<IProcessorRecipe>(
            RecipeRegisterManager.processorRecipe.getRecipes());
        if (recipes == null || recipes.isEmpty()) return;

        IProcessorRecipe activeRecipe = null;
        boolean flag = false;

        for (IProcessorRecipe recipe : recipes) {
            if (recipe.isFoodRecipe() == this.acceptFoodRecipe() && this.ismatchTier(recipe) && recipe.matches(items)) {
                activeRecipe = recipe;
                flag = true;
            }
        }

        if (flag && activeRecipe != null) {
            // まずは材料を減らす
            List<Object> required = new ArrayList<Object>(activeRecipe.getProcessedInput());
            ItemStack output = activeRecipe.getOutput();
            ItemStack sec = activeRecipe.getSecondary();
            ItemStack cont = activeRecipe.getCraftingRemainingItem(items);
            float chance = activeRecipe.getChance();
            boolean getSec = level.rand.nextFloat() <= chance;

            for (int i = 2; i < 11; i++) {
                ItemStack slot = this.itemstacks[i];

                if (slot != null) {
                    boolean inRecipe = false;
                    Iterator<Object> req = required.iterator();

                    if (slot.getItem() instanceof IProcessorPanel) {
                        inRecipe = true;
                        continue;
                    }

                    // 9スロットについて、要求材料の数だけ回す
                    while (req.hasNext()) {
                        boolean match = false;
                        Object next = req.next();
                        int count = 1;

                        if (next instanceof ItemStack) {
                            count = ((ItemStack) next).getCount();
                            match = TagHelper.itemMatches((ItemStack) next, slot, false) && slot.getCount() >= count;
                        } else if (next instanceof ArrayList) {
                            ArrayList<ItemStack> list = new ArrayList<ItemStack>((ArrayList<ItemStack>) next);
                            count = 1;
                            if (list != null && !list.isEmpty()) {
                                for (ItemStack item : list) {
                                    boolean f = TagHelper.itemMatches(item, slot, false) && !slot.isEmpty() && slot.getCount() > 0;
                                    if (f) match = true;
                                }
                            }
                        }

                        if (match) {
                            inRecipe = true;
                            required.remove(next);
                            if (slot.getItem() instanceof IProcessorRecipeTool) {
                                ItemStack ret = ((IProcessorRecipeTool) slot.getItem()).returnItem(slot);
                                this.setItem(i, ret);
                            } else {
                                this.decrStackSize(i, 1);
                            }
                            this.setChanged();
                            break;
                        }
                    }

                    if (!inRecipe) {
                        return;// 中断
                    }
                }
            }

            if (output != null) {
                AMTLogger.debugInfo("current recipe : " + output.toString());

                // 次に完成品を完成品スロットへ
                if (this.itemstacks[11] == null) {
                    this.itemstacks[11] = output.copy();
                } else if (this.net.minecraft.world.item.ItemStack.isSameItemSameTags(itemstacks[11], output)) {
                    this.itemstacks[11].grow(output.getCount());
                }
            }

            if (sec != null && getSec) {
                if (this.itemstacks[12] == null) {
                    this.itemstacks[12] = sec.copy();
                } else if (this.net.minecraft.world.item.ItemStack.isSameItemSameTags(itemstacks[12], sec)) {
                    this.itemstacks[12].grow(sec.getCount());
                }
            } else if (cont != null) {
                if (this.itemstacks[12] == null) {
                    this.itemstacks[12] = cont.copy();
                } else if (this.net.minecraft.world.item.ItemStack.isSameItemSameTags(itemstacks[12], cont)) {
                    this.itemstacks[12].grow(cont.getCount());
                }
            }

            this.onRecipeOutput();

            this.setChanged();
        }
    }

    /* ====== 以下、インベントリ関係 ====== */

    /*
     * フードプロセッサーの場合
     * 燃料スロット：0
     * 燃料空容器の排出スロット：1
     * 材料スロット：2～10
     * 完成品スロット：11,12
     */

    @Override
    public int getContainerSize() {
        return 13;
    }

    @Override
    protected int[] slotsTop() {
        return new int[] { 0, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    }

    @Override
    protected int[] slotsBottom() {
        return new int[] { 1, 11, 12 };
    }

    @Override
    protected int[] slotsSides() {
        return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
    }

    @Override
    public String getContainerName() {
        return "Food Processor";
    }

}
