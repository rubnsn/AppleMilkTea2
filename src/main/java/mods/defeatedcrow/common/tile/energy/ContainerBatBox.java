package mods.defeatedcrow.common.tile.energy;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import mods.defeatedcrow.api.energy.IBattery;
import mods.defeatedcrow.common.registry.ModMenuTypes;

public class ContainerBatBox extends AbstractContainerMenu {

    private final TileChargerBase tile;
    private final ContainerData data;

    public ContainerBatBox(int id, Inventory inv, TileChargerBase tile) {
        super(ModMenuTypes.BAT_BOX.get(), id);
        this.tile = tile;
        this.data = new SimpleContainerData(2);
        this.addSlot(new Slot(tile, 0, 9, 9));
        this.addSlot(new Slot(tile, 1, 9, 55));
        for (int j = 0; j < 2; ++j) {
            for (int k = 0; k < 4; ++k) {
                this.addSlot(new Slot(tile, 2 + k + j * 4, 53 + k * 18, 30 + j * 18));
            }
        }
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

    public ContainerBatBox(int id, Inventory inv, BlockPos pos) {
        this(id, inv, (TileChargerBase) inv.player.level().getBlockEntity(pos));
    }

    @Override
    public boolean stillValid(Player player) { return tile.stillValid(player); }

    public void updateProgressBar(int id, int val) {
        if (id == 0) tile.setUnder(val);
        else if (id == 1) tile.setUpper(val);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack copy = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            copy = stack.copy();
            if (index == 1) {
                if (!this.moveItemStackTo(stack, 10, 46, true)) return ItemStack.EMPTY;
            } else if (index > 9) {
                if (tile.isItemFuel(stack)) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) {
                        if (stack.getItem() instanceof IBattery) {
                            if (!this.moveItemStackTo(stack, 2, 9, false)) return ItemStack.EMPTY;
                        }
                    }
                } else if (stack.getItem() instanceof IBattery) {
                    if (!this.moveItemStackTo(stack, 2, 9, false)) return ItemStack.EMPTY;
                } else {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack, 10, 46, false)) {
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
