package mods.defeatedcrow.common.tile.appliance;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerListener; // ContainerListener -> ContainerListener in 1.20.1
import net.minecraft.world.inventory.Slot;
// SlotFurnace removed in 1.20.1 - use Slot
import net.minecraft.world.item.ItemStack;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.recipe.*;

// 1.20.1: Container -> AbstractContainerMenu (see doc/tile-entities/migration-guide.md)
public class ContainerIceMaker extends AbstractContainerMenu {

    private TileIceMaker tileentity;

    private TileIceMaker inventory;

    private int lastCookTime;
    private int lastBurnTime;

    public ContainerIceMaker(Player player, TileIceMaker par2TileEntity) {
        this.tileentity = par2TileEntity;
        this.inventory = par2TileEntity;

        this.addSlot(new Slot(this.inventory, 0, 56, 17));
        this.addSlot(new Slot(this.inventory, 1, 56, 53));
        this.addSlot(new SlotFurnace(player, this.inventory, 2, 112, 35));
        this.addSlot(new SlotFurnace(player, this.inventory, 3, 140, 35));

        int i;

        // 1 ～ 3段目のインベントリ
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // 4段目のインベントリ
        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(player.inventory, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ContainerListener par1ContainerListener) {
        super.addCraftingToCrafters(par1ContainerListener);
        par1ContainerListener.sendProgressBarUpdate(this, 0, this.tileentity.cookTime);
        par1ContainerListener.sendProgressBarUpdate(this, 1, this.tileentity.chargeAmount);
    }

    // 更新を送る
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ContainerListener icrafting = (ContainerListener) this.crafters.get(i);

            if (this.lastCookTime != this.tileentity.cookTime) {
                icrafting.sendProgressBarUpdate(this, 0, this.tileentity.cookTime);
            }

            if (this.lastBurnTime != this.tileentity.chargeAmount) {
                icrafting.sendProgressBarUpdate(this, 1, this.tileentity.chargeAmount);
            }
        }

        this.lastCookTime = this.tileentity.cookTime;
        this.lastBurnTime = this.tileentity.chargeAmount;
    }

    // 更新する
    
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.tileentity.cookTime = par2;
        }

        if (par1 == 1) {
            this.tileentity.chargeAmount = par2;
        }
    }

    // InventorySample内のstillValidメソッドを参照
    @Override
    public boolean canInteractWith(Player par1EntityPlayer) {
        return this.inventory.stillValid(par1EntityPlayer);
    }

    // Shiftクリック
    public ItemStack transferStackInSlot(Player par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            // スロット番号が2の時
            if (par2 == 2 || par2 == 3) {
                // アイテムの移動(スロット3～39へ)
                if (!this.mergeItemStack(itemstack1, 4, 40, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            // スロット番号が0、1でない時
            else if (par2 != 1 && par2 != 0) {
                if (RecipeRegisterManager.iceRecipe.getRecipe(itemstack1) != null) {
                    // アイテムの移動(スロット0～1へ)
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (TileIceMaker.getItemBurnTime(itemstack1) > 0) {
                    // アイテムの移動(スロット1～2へ)
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
                        return null;
                    }
                } else if (par2 >= 4 && par2 < 31) {
                    // アイテムの移動(スロット30～39へ)
                    if (!this.mergeItemStack(itemstack1, 31, 40, false)) {
                        return null;
                    }
                } else if (par2 >= 31 && par2 < 40 && !this.mergeItemStack(itemstack1, 4, 31, false)) {
                    return null;
                }
            }
            // アイテムの移動(スロット3～39へ)
            else if (!this.mergeItemStack(itemstack1, 4, 40, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }

}
