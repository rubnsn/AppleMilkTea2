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

/**
 * 1.20.1: Container -> AbstractContainerMenu + MenuType + ContainerData
 * See doc/tile-entities/migration-guide.md:40
 * Legacy IGuiHandler / Container decoupled: BlockEntity now uses MenuProvider, Container uses BlockPos via FriendlyByteBuf.
 */
public class ContainerProcessor extends AbstractContainerMenu {

    protected final TileProcessor tile;
    private final ContainerData data;

    public ContainerProcessor(int id, Inventory inv, TileProcessor tile) {
        super(ModMenuTypes.PROCESSOR.get(), id);
        this.tile = tile;
        this.data = new SimpleContainerData(2);
        // fuel
        this.addSlot(new Slot(tile, 0, 9, 9));
        // material 3x3 (slots 2-10)
        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 3; ++k) {
                this.addSlot(new Slot(tile, 2 + k + j * 3, 33 + k * 18, 16 + j * 18));
            }
        }
        // output slots
        this.addSlot(new Slot(tile, 1, 9, 55));
        this.addSlot(new Slot(tile, 11, 118, 35));
        this.addSlot(new Slot(tile, 12, 145, 35));

        // player inventory 3 rows
        for (int i = 0; i < 3; ++i) {
            for (int h = 0; h < 9; ++h) {
                this.addSlot(new Slot(inv, h + i * 9 + 9, 8 + h * 18, 84 + i * 18));
            }
        }
        // hotbar
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inv, i, 8 + i * 18, 142));
        }
        this.addDataSlots(data);
    }

    public ContainerProcessor(int id, Inventory inv, BlockPos pos) {
        this(id, inv, (TileProcessor) inv.player.level().getBlockEntity(pos));
    }

    @Override
    public boolean stillValid(Player player) {
        return tile.stillValid(player);
    }

    // Legacy progress bar replaced by ContainerData; keep stub for compat
    public void updateProgressBar(int id, int val) {
        if (id == 0) tile.cookTime = val;
        if (id == 1) tile.setChargeAmount(val);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack copy = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            copy = stack.copy();
            int lim = tile.getContainerSize();
            if (index == 1 || index == 11 || index == 12) {
                if (!this.moveItemStackTo(stack, lim, lim + 36, true)) return ItemStack.EMPTY;
            } else if (index >= lim) {
                if (TileProcessor.isItemFuel(stack)) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) return ItemStack.EMPTY;
                } else {
                    if (!this.moveItemStackTo(stack, 1, 10, false)) return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(stack, lim, lim + 36, false)) return ItemStack.EMPTY;
            }
            if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();
            if (stack.getCount() == copy.getCount()) return ItemStack.EMPTY;
            slot.onTake(player, stack);
        }
        return copy;
    }
}
