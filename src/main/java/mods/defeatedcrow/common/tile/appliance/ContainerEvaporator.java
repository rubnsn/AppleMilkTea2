package mods.defeatedcrow.common.tile.appliance;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import mods.defeatedcrow.common.registry.ModMenuTypes;

public class ContainerEvaporator extends AbstractContainerMenu {

    private final TileEvaporator tile;
    private final ContainerData data;

    public ContainerEvaporator(int id, Inventory inv, TileEvaporator tile) {
        super(ModMenuTypes.EVAPORATOR.get(), id);
        this.tile = tile;
        this.data = new SimpleContainerData(4);
        this.addSlot(new Slot(tile, 0, 9, 9));
        this.addSlot(new Slot(tile, 2, 56, 17));
        this.addSlot(new Slot(tile, 1, 9, 55));
        this.addSlot(new Slot(tile, 3, 110, 21));
        this.addSlot(new Slot(tile, 4, 141, 58));
        this.addSlot(new Slot(tile, 5, 56, 55));
        for (int i = 0; i < 3; ++i) {
            for (int h = 0; h < 9; ++h) {
                this.addSlot(new Slot(inv, h + i * 9 + 9, 8 + h * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inv, i, 8 + i * 18, 142));
        }
        this.addDataSlots(data);
    }

    public ContainerEvaporator(int id, Inventory inv, BlockPos pos) {
        this(id, inv, (TileEvaporator) inv.player.level().getBlockEntity(pos));
    }

    @Override
    public boolean stillValid(Player player) { return tile.stillValid(player); }

    public void updateProgressBar(int id, int val) {
        if (id == 0) tile.cookTime = val;
        if (id == 1) tile.setChargeAmount(val);
        if (id == 2 || id == 3) tile.getGuiFluidUpdate(id, val);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack copy = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            copy = stack.copy();
            if (index == 1 || index == 3 || index == 4 || index == 5) {
                if (!this.moveItemStackTo(stack, 6, 42, true)) return ItemStack.EMPTY;
            } else if (index > 5) {
                if (TileEvaporator.isItemFuel(stack)) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) return ItemStack.EMPTY;
                } else {
                    if (!this.moveItemStackTo(stack, 1, 2, false)) return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack, 6, 42, false)) return ItemStack.EMPTY;
            if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
            if (stack.getCount() == copy.getCount()) return ItemStack.EMPTY;
            slot.onTake(player, stack);
        }
        return copy;
    }
}
