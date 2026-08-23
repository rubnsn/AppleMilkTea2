package mods.defeatedcrow.common.tile.appliance;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerListener; // ContainerListener -> ContainerListener in 1.20.1
import net.minecraft.world.inventory.Slot;
// SlotFurnace removed in 1.20.1 - use Slot
import net.minecraft.world.item.ItemStack;
// 1.20.1: Container -> AbstractContainerMenu (see doc/tile-entities/migration-guide.md)
public class ContainerEvaporator extends AbstractContainerMenu {

    private TileEvaporator tileentity;

    private TileEvaporator inventory;

    private int lastCookTime;
    private int lastBurnTime;
    private int lastFluidAmount;
    private int lastFluidID;

    public ContainerEvaporator(Player player, TileEvaporator par2TileEntity) {
        this.tileentity = par2TileEntity;
        this.inventory = par2TileEntity;

        // 燃料
        this.addSlot(new Slot(this.inventory, 0, 9, 9));
        // 材料
        this.addSlot(new Slot(this.inventory, 2, 56, 17));

        // 完成品
        this.addSlot(new SlotFurnace(player, this.inventory, 1, 9, 55));
        this.addSlot(new SlotFurnace(player, this.inventory, 3, 110, 21));
        this.addSlot(new Slot(this.inventory, 4, 141, 58));
        this.addSlot(new SlotFurnace(player, this.inventory, 5, 56, 55));

        int i;

        // 1 ～ 3段目のインベントリ
        for (i = 0; i < 3; ++i) {
            for (int h = 0; h < 9; ++h) {
                this.addSlot(new Slot(player.inventory, h + i * 9 + 9, 8 + h * 18, 84 + i * 18));
            }
        }

        // 4段目のインベントリ
        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(player.inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void addCraftingToCrafters(ContainerListener par1ContainerListener) {
        super.addCraftingToCrafters(par1ContainerListener);
        par1ContainerListener.sendProgressBarUpdate(this, 0, this.tileentity.cookTime);
        par1ContainerListener.sendProgressBarUpdate(this, 1, this.tileentity.getChargeAmount());

        if (this.tileentity.productTank.getFluid() != null) {
            par1ContainerListener.sendProgressBarUpdate(
                this,
                2,
                this.tileentity.productTank.getFluid()
                    .getFluid()
                    .getID());
            par1ContainerListener.sendProgressBarUpdate(this, 3, this.tileentity.productTank.getFluidAmount());
        } else {
            par1ContainerListener.sendProgressBarUpdate(this, 2, 0);
            par1ContainerListener.sendProgressBarUpdate(this, 3, 0);
        }

    }

    // 更新を送る
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ContainerListener icrafting = (ContainerListener) this.crafters.get(i);

            if (this.lastCookTime != this.tileentity.cookTime) {
                icrafting.sendProgressBarUpdate(this, 0, this.tileentity.cookTime);
            }

            if (this.lastBurnTime != this.tileentity.getChargeAmount()) {
                icrafting.sendProgressBarUpdate(this, 1, this.tileentity.getChargeAmount());
            }

            if (this.tileentity.productTank.getFluid() != null) {
                if (this.lastFluidID != this.tileentity.productTank.getFluid()
                    .getFluid()
                    .getID()) {
                    icrafting.sendProgressBarUpdate(
                        this,
                        2,
                        this.tileentity.productTank.getFluid()
                            .getFluid()
                            .getID());
                }
                if (this.lastFluidAmount != this.tileentity.productTank.getFluidAmount()) {
                    icrafting.sendProgressBarUpdate(this, 3, this.tileentity.productTank.getFluidAmount());
                }

                this.lastFluidAmount = this.tileentity.productTank.getFluidAmount();
                this.lastFluidID = this.tileentity.productTank.getFluid()
                    .getFluid()
                    .getID();
            } else {
                if (this.lastFluidID != 0) {
                    icrafting.sendProgressBarUpdate(this, 2, 0);
                }
                if (this.lastFluidAmount != 0) {
                    icrafting.sendProgressBarUpdate(this, 3, 0);
                }

                this.lastFluidAmount = 0;
                this.lastFluidID = 0;
            }
        }

        this.lastCookTime = this.tileentity.cookTime;
        this.lastBurnTime = this.tileentity.getChargeAmount();

    }

    // 更新する
    @Override
    
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.tileentity.cookTime = par2;
        }

        if (par1 == 1) {
            this.tileentity.setChargeAmount(par2);
        }

        if (par1 == 2 || par1 == 3) {
            this.tileentity.getGuiFluidUpdate(par1, par2);
        }
    }

    // InventorySample内のstillValidメソッドを参照
    @Override
    public boolean canInteractWith(Player par1EntityPlayer) {
        return this.inventory.stillValid(par1EntityPlayer);
    }

    // Shiftクリック
    @Override
    public ItemStack transferStackInSlot(Player par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            // カーソルを排出スロットにあわせているとき
            if (par2 == 1 || par2 == 3 || par2 == 4 || par2 == 5) {
                // アイテムの移動(スロット6～42へ)
                if (!this.mergeItemStack(itemstack1, 6, 42, true)) return null;

                slot.onSlotChange(itemstack1, itemstack);
            }
            // カーソルをプレイヤーのインベントリにあわせている
            else if (par2 > 5) {
                // 燃料である
                if (TileEvaporator.isItemFuel(itemstack)) {
                    // アイテムの移動(スロット0～1へ)
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) return null;
                } else// それ以外のアイテムはすべて材料欄に飛ばす
                {
                    // アイテムの移動(スロット2へ)
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)) return null;
                }
            }
            // アイテムの移動(スロット6～42へ)
            else if (!this.mergeItemStack(itemstack1, 6, 42, false)) return null;

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) return null;

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }

}
