package mods.defeatedcrow.common.tile.appliance;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.core.Direction;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import mods.defeatedcrow.api.edibles.IEdibleItem;
import mods.defeatedcrow.api.recipe.IEvaporatorRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.fluid.DCsTank;

public class TileEvaporator extends MachineBase implements IFluidHandler, IPipeConnection {
    public TileEvaporator(net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.state.BlockState state) { super(pos, state); }


    public DCsTank productTank = new DCsTank(4000);

    @Override
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);
        this.productTank = new DCsTank(4000);
        if (par1CompoundTag.contains("productTank")) {
            this.productTank.load(par1CompoundTag.getCompound("productTank"));
        }
    }

    @Override
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);
        CompoundTag tank = new CompoundTag();
        this.productTank.saveAdditional(tank);
        par1CompoundTag.put("productTank", tank);
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
    public static void tick(Level level, BlockPos pos, BlockState state, TileEvaporator be) {
        // 1.20.1 tick (was updateEntity) - see doc/tile-entities/migration-guide.md
        if (level.isClientSide) return;
        be.setChanged();
        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
    }

    // 調理中の矢印の描画
    
    public int getFluidAmountScaled(int par1) {
        return this.productTank.getFluidAmount() * par1 / 4000;
    }

    // GUI用にコンテナからの更新を受け取るメソッド。
    public void getGuiFluidUpdate(int id, int val) {
        if (id == 2)// ID
        {
            if (productTank.getFluid() == null) {
                productTank.setFluidById(val);
            } else {
                int amo = productTank.getFluidAmount();
                productTank.setFluidById(val);
            }
        } else if (id == 3)// amount
        {
            if (productTank.getFluid() == null) {
                productTank.setFluid((FluidStack) null);
            } else {
                productTank.getFluid().amount = val;
            }
        }
    }

    @Override
    public boolean canSmelt() {
        boolean flag1 = false;// レシピ
        boolean flag2 = false;// メイン完成スロット
        boolean flag3 = false;// 液体
        boolean flag4 = false;// 空容器

        ItemStack items = null;
        if (this.itemstacks[2] == null) return false;
        else {
            items = this.itemstacks[2].copy();
        }

        IEvaporatorRecipe recipe = RecipeRegisterManager.evaporatorRecipe.getRecipe(items);
        if (recipe == null) return false;

        ItemStack output = recipe.getOutput();
        FluidStack second = recipe.getSecondary();

        // 両方がnullのレシピはレシピとみなさない
        if ((output == null && second == null) || (items.stackSize < recipe.getInput().stackSize)) return false;
        else {
            flag1 = true;
        }

        ItemStack container = null;
        if (items.getItem() instanceof IEdibleItem) {
            IEdibleItem edible = (IEdibleItem) items.getItem();
            container = edible.getReturnContainer(items.getItemDamage());
        } else if (items.getItem() == DCsAppleMilk.moromi) {

        } else if (items.getItem()
            .hasContainerItem(items)) {
                container = items.getItem()
                    .getContainerItem(items);
            } else if (items.getItem() == DCsAppleMilk.itemLargeBottle)// 特殊条件
        {
            if (items.getItemDamage() > 16) {
                container = new ItemStack(DCsAppleMilk.itemLargeBottle, 1, items.getItemDamage() - 16);
            } else {
                container = new ItemStack(DCsAppleMilk.emptyBottle, 1, 0);
            }
        }

        if (this.itemstacks[3] == null || output == null) {
            flag2 = true;
        } else {
            if (this.itemstacks[3].isItemEqual(output)) {
                int result = this.itemstacks[3].stackSize + output.stackSize;
                flag2 = (result <= this.getMaxStackSize() && result <= output.getMaxStackSize());
            }
        }

        if (container != null && recipe.returnContainer()) {
            if (this.itemstacks[5] == null) {
                flag4 = true;
            } else {
                if (this.itemstacks[5].isItemEqual(container)) {
                    int result = this.itemstacks[5].stackSize + container.stackSize;
                    flag4 = (result <= this.getMaxStackSize() && result <= container.getMaxStackSize());
                }
            }
        } else {
            flag4 = true;
        }

        // 液体ありのレシピの場合
        if (second == null) {
            flag3 = true;
        } else {
            if (this.productTank.isEmpty()) {
                flag3 = true;
            } else {
                int fillAmount = this.productTank.fill(second, false);
                flag3 = fillAmount >= second.amount;
            }
        }

        // AMTLogger.debugInfo("Evaporator update : " + flag1 + ", " + flag2 +
        // ", " + flag3 + ", " + flag4);

        return flag1 && flag2 && flag3 && flag4;
    }

    @Override
    public void onProgress() {
        // 結局canSmelt()と同じことをしていて無駄な感じはする
        // すでに判定は済んでるし、二重にやる必要無いような…
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;

        ItemStack items = this.itemstacks[2];
        if (items == null) return;

        IEvaporatorRecipe recipe = RecipeRegisterManager.evaporatorRecipe.getRecipe(items);
        if (recipe == null) return;

        ItemStack output = recipe.getOutput();
        FluidStack second = recipe.getSecondary();
        if (output == null && second == null) return;
        else {
            flag1 = true;
        }

        ItemStack container = null;
        if (items.getItem() instanceof IEdibleItem) {
            IEdibleItem edible = (IEdibleItem) items.getItem();
            container = edible.getReturnContainer(items.getItemDamage());
        } else if (items.getItem()
            .hasContainerItem(items)) {
                container = items.getItem()
                    .getContainerItem(items);
            } else if (items.getItem() == DCsAppleMilk.itemLargeBottle)// 特殊条件
        {
            if (items.getItemDamage() > 16) {
                container = new ItemStack(DCsAppleMilk.itemLargeBottle, 1, items.getItemDamage() - 16);
            } else {
                container = new ItemStack(DCsAppleMilk.emptyBottle, 1, 0);
            }
        }

        // 材料を減らし、返却アイテムが有る場合は返却スロットへ。
        if (this.itemstacks[2] != null) {
            this.itemstacks[2].stackSize -= recipe.getInput().stackSize;

            if (this.itemstacks[2].stackSize <= 0) {
                this.itemstacks[2] = null;
            }

            if (container != null && recipe.returnContainer()) {
                if (this.itemstacks[5] == null) {
                    this.itemstacks[5] = container.copy();
                } else if (this.itemstacks[5].isItemEqual(container)) {
                    this.itemstacks[5].stackSize += container.stackSize;
                }
            }

            flag2 = true;
        }

        if (flag1 && flag2) {
            String out = output == null ? "Empty" : output.toString();
            String sec = second == null ? "Empty"
                : second.getFluid()
                    .getLocalizedName(second);
            AMTLogger.debugInfo("current recipe : " + out + ", " + sec);

            // 次に完成品を完成品スロットへ
            if (output != null) {
                if (this.itemstacks[3] == null) {
                    this.itemstacks[3] = output.copy();
                } else if (this.itemstacks[3].isItemEqual(output)) {
                    this.itemstacks[3].stackSize += output.stackSize;
                }
            }

            if (this.productTank.isEmpty()) {
                this.productTank.setFluid(second);
            } else if (this.productTank.getFluid()
                .isFluidEqual(second))// secondのnull判定も兼ねてる
            {
                this.productTank.fill(second, true);
            }

            this.setChanged();
        }
    }

    /* ====== 以下、インベントリ関係 ====== */

    /*
     * フードプロセッサーの場合
     * 燃料スロット：0
     * 燃料空容器の排出スロット：1
     * 材料スロット：2
     * 完成品スロット：3,4
     * 材料のから容器排出：5
     * 上記にプラスして排出用液体スロットひとつ
     */

    @Override
    public int getContainerSize() {
        return 6;
    }

    @Override
    protected int[] slotsTop() {
        return new int[] { 0, 2 };
    }

    @Override
    protected int[] slotsBottom() {
        return new int[] { 1, 3, 4, 5 };
    }

    @Override
    protected int[] slotsSides() {
        return new int[] { 0, 1, 2, 3, 4, 5 };
    }

    @Override
    public String getContainerName() {
        return "Evaporator";
    }

    /* ====== 以下、IFluidHandlerの実装メソッド ====== */

    @Override
    public FluidStack drain(Direction from, FluidStack resource, boolean doDrain) {
        if (resource == null) return null;
        if (productTank.getFluidType() == resource.getFluid()) return productTank.drain(resource.amount, doDrain);
        return null;
    }

    @Override
    public FluidStack drain(Direction from, int maxDrain, boolean doDrain) {
        return this.productTank.drain(maxDrain, doDrain);
    }

    // 外部からの液体の受け入れはなし
    @Override
    public int fill(Direction from, FluidStack resource, boolean doFill) {
        return 0;
    }

    @Override
    public boolean canFill(Direction from, Fluid fluid) {
        return false;
    }

    @Override
    public boolean canDrain(Direction from, Fluid fluid) {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(Direction from) {
        return new FluidTankInfo[] { productTank.getInfo() };
    }

    // BuildCraft対応
        @Override
    public ConnectOverride overridePipeConnection(PipeType type, Direction with) {
        return type == PipeType.FLUID ? ConnectOverride.CONNECT : ConnectOverride.DISCONNECT;
    }

}
