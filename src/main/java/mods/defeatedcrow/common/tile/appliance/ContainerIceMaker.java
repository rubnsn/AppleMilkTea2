package mods.defeatedcrow.common.tile.appliance;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.registry.ModMenuTypes;

public class ContainerIceMaker extends AbstractContainerMenu {

    private final TileIceMaker tile;
    private final ContainerData data;

    public ContainerIceMaker(int id, Inventory inv, TileIceMaker tile) {
        super(ModMenuTypes.ICE_MAKER.get(), id);
        this.tile = tile;
        this.data = new SimpleContainerData(2);
        this.addSlot(new Slot(tile, 0, 56, 17));
        this.addSlot(new Slot(tile, 1, 56, 53));
        this.addSlot(new Slot(tile, 2, 112, 35));
        this.addSlot(new Slot(tile, 3, 140, 35));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inv, i, 8 + i * 18, 142));
        }
        this.addDataSlots(data);
    }

    public ContainerIceMaker(int id, Inventory inv, BlockPos pos) {
        this(id, inv, (TileIceMaker) inv.player.level().getBlockEntity(pos));
    }

    @Override
    public boolean stillValid(Player player) { return tile.stillValid(player); }

    public void updateProgressBar(int id, int val) {
        if (id == 0) tile.cookTime = val;
        if (id == 1) tile.chargeAmount = val;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack copy = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            copy = stack.copy();
            if (index == 2 || index == 3) {
                if (!this.moveItemStackTo(stack, 4, 40, true)) return ItemStack.EMPTY;
            } else if (index != 1 && index != 0) {
                if (RecipeRegisterManager.iceRecipe.getRecipe(stack) != null) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) return ItemStack.EMPTY;
                } else if (TileIceMaker.getItemBurnTime(stack) > 0) {
                    if (!this.moveItemStackTo(stack, 1, 2, false)) return ItemStack.EMPTY;
                } else if (index >= 4 && index < 31) {
                    if (!this.moveItemStackTo(stack, 31, 40, false)) return ItemStack.EMPTY;
                } else if (index >= 31 && index < 40 && !this.moveItemStackTo(stack, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack, 4, 40, false)) {
                return ItemStack.EMPTY;
            }
            if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
            if (stack.getCount() == copy.getCount()) return ItemStack.EMPTY;
            slot.onTake(player, stack);
        }
        return copy;
    }
}
