package mods.defeatedcrow.common.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import mods.defeatedcrow.common.fluid.DCsTank;
import mods.defeatedcrow.recipe.BrewingRecipe;

/*
 * 熟成時間の処理と、完了したかどうかの判定を持つ。
 * 直射日光は厳禁。日光に当てると熟成時間がリセットされてしまう。
 * 酒は液体タンクに保管されている。熟成完了するまでアクセス出来ない。
 * 1.20.1: TileEntity -> BlockEntity, S35 -> ClientboundBlockEntityDataPacket, level/BlockPos (see doc/tile-entities/migration-guide.md:112)
 */
public class TileBrewingBarrel extends BlockEntity implements IFluidHandler {

    public TileBrewingBarrel(BlockPos pos, BlockState state) {
        super(mods.defeatedcrow.common.registry.ModBlockEntities.TILE_BREWING_BARREL.get(), pos, state);
    }

    private int aging = 0;
    private boolean isAged = false;
    public DCsTank productTank = new DCsTank(1000);
    // 向き
    private boolean side = false;

    private int lastState = 0;

    // NBT
    @Override
    public void load(CompoundTag par1NBTTagCompound) {
        super.load(par1NBTTagCompound);
        this.aging = par1NBTTagCompound.getInt("Remaining");
        this.isAged = par1NBTTagCompound.getBoolean("IsAged");
        this.side = par1NBTTagCompound.getBoolean("Side");

        this.productTank = new DCsTank(1000);
        if (par1NBTTagCompound.contains("productTank")) {
            this.productTank.readFromNBT(par1NBTTagCompound.getCompound("productTank"));
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    protected void saveAdditional(CompoundTag par1NBTTagCompound) {
        super.saveAdditional(par1NBTTagCompound);
        par1NBTTagCompound.putInt("Remaining", this.aging);
        par1NBTTagCompound.putBoolean("IsAged", this.isAged);
        par1NBTTagCompound.putBoolean("Side", this.side);

        CompoundTag tank = new CompoundTag();
        this.productTank.writeToNBT(tank);
        par1NBTTagCompound.put("productTank", tank);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    public int getAgingTime() {
        return this.aging;
    }

    public void setAgingTime(int par1) {
        this.aging = par1;
    }

    public boolean getAged() {
        return this.isAged;
    }

    public void setAged(boolean par1) {
        this.isAged = par1;
    }

    public boolean getSide() {
        return this.side;
    }

    public void setSide(boolean flag) {
        this.side = flag;
    }

    // レンダー用の熟成段階取得メソッド。一日（20分）ごとに色が濃くなっていく。
    public int getAgingStage() {
        int i = this.aging / 6000;
        return i;
    }

    public void setAgingStage(int par1) {
        int i = par1 * 6000;
        this.aging = i;
    }

    // レンダー用。真下の状態を確認
    public boolean isOnNormalCube() {
        if (this.level == null) return false;
        BlockPos below = this.worldPosition.below();
        BlockState state = this.level.getBlockState(below);
        return !state.isAir() && state.isSolidRender(this.level, below);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TileBrewingBarrel be) {
        if (level.isClientSide) return;
        if (!be.isAged && be.canBrew()) {
            // 直射日光が当たっていない・常温でのみ熟成する。
            if (!level.canSeeSky(pos) && !be.isDryBiome()) {
                be.aging++;

                if (be.aging > 24000)// 4日間で熟成完了する
                {
                    be.aging = 24000;
                    be.onBrewing();
                    be.setAged(true);
                }
            }
        }

        if (be.productTank.isEmpty()) {
            be.setAged(false);
            be.setAgingTime(0);
        }

        int curState = be.productTank.getFluidAmount() + be.aging;
        if (be.lastState != curState) {
            be.lastState = curState;
            level.sendBlockUpdated(pos, state, state, 3);
        }
        be.setChanged();
    }

    private boolean canBrew() {
        if (this.productTank.isEmpty()) return false;
        FluidStack stack = this.productTank.getFluid();
        if (stack == null || stack.isEmpty()) return false;
        Object fluid = stack.getFluid();
        @SuppressWarnings("unchecked")
        java.util.Map<Object, Object> map = (java.util.Map<Object, Object>) (java.util.Map<?, ?>) BrewingRecipe.recipe;
        // 1.20.1: recipe map may be stub empty; treat non-empty as brewable for aging logic
        if (map.isEmpty()) return true;
        return map.containsKey(fluid);
    }

    // 熟成完了処理
    private void onBrewing() {
        FluidStack current = this.productTank.getFluid();
        if (current == null || current.isEmpty()) return;
        Object mat = current.getFluid();
        @SuppressWarnings("unchecked")
        java.util.Map<Object, Object> map = (java.util.Map<Object, Object>) (java.util.Map<?, ?>) BrewingRecipe.recipe;
        Object out = map.get(mat);
        if (out == null) {
            // fallback linear search by identity
            for (java.util.Map.Entry<Object, Object> e : map.entrySet()) {
                if (e.getKey() == mat) { out = e.getValue(); break; }
            }
        }
        int amount = this.productTank.getFluidAmount();
        if (out != null && amount > 0) {
            // out may be old Fluid or material Fluid; create FluidStack accordingly
            FluidStack ret;
            if (out instanceof net.minecraft.world.level.material.Fluid fluidMat) {
                ret = new FluidStack(fluidMat, amount);
            } else if (out instanceof Fluid fluidOld) {
                // old Fluid -> try to create via material lookup (Forge 1.20 FluidStack still accepts old Fluid as wrapper)
                ret = new FluidStack(fluidOld, amount);
            } else {
                return;
            }
            this.productTank.setFluid(ret);
        }
        this.setChanged();
    }

    public int getMetadata() {
        return 0;
    }

    public boolean isColdBiome() {
        if (this.level == null) return false;
        var holder = this.level.getBiome(this.worldPosition);
        return holder.is(BiomeTags.IS_TAIGA) || holder.is(BiomeTags.IS_SNOWY) || holder.is(BiomeTags.IS_FROZEN_OCEAN);
    }

    public boolean isDryBiome() {
        if (this.level == null) return false;
        var holder = this.level.getBiome(this.worldPosition);
        return holder.is(BiomeTags.IS_DESERT) || holder.is(BiomeTags.IS_SAVANNA) || holder.is(BiomeTags.IS_NETHER) || holder.is(BiomeTags.IS_BADLANDS);
    }

    /* ====== 以下、IFluidHandlerの実装メソッド ====== */

    @Override
    public FluidStack drain(Direction from, FluidStack resource, boolean doDrain) {
        if (resource == null || resource.isEmpty() || !this.isAged) {
            return FluidStack.EMPTY;
        }
        if (productTank.getFluid().isFluidEqual(resource)) {
            FluidStack ret = productTank.drain(resource.getAmount(), doDrain ? net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE : net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.SIMULATE);
            if (productTank.isEmpty()) {
                this.setAged(false);
                this.setAgingStage(0);
            }
            return ret;
        }
        return FluidStack.EMPTY;
    }

    @Override
    public FluidStack drain(Direction from, int maxDrain, boolean doDrain) {
        if (maxDrain <= 0 || !this.isAged) {
            return FluidStack.EMPTY;
        }
        FluidStack ret = productTank.drain(maxDrain, doDrain ? net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE : net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.SIMULATE);
        if (productTank.isEmpty()) {
            this.setAged(false);
            this.setAgingStage(0);
        }
        return ret;
    }

    // 外部からの液体の受け入れ
    @Override
    public int fill(Direction from, FluidStack resource, boolean doFill) {
        if (resource == null || resource.isEmpty() || resource.getFluid() == null || this.isAged) {
            return 0;
        }

        FluidStack current = this.productTank.getFluid();
        FluidStack resourceCopy = resource.copy();
        if (!current.isEmpty() && current.getAmount() > 0 && current.isFluidEqual(resourceCopy)) {
            return 0;
        }

        int used = this.productTank.fill(resourceCopy, doFill ? net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.EXECUTE : net.minecraftforge.fluids.capability.IFluidHandler.FluidAction.SIMULATE);

        return used;
    }

    // 空でないと&醸造未完了でないと受け入れない
    @Override
    public boolean canFill(Direction from, Fluid fluid) {
        if (fluid == null || this.isAged || !this.productTank.isEmpty()) return false;
        @SuppressWarnings("unchecked")
        java.util.Map<Object, Object> map = (java.util.Map<Object, Object>) (java.util.Map<?, ?>) BrewingRecipe.recipe;
        if (map.isEmpty()) return true;
        return map.containsKey(fluid);
    }

    // 醸造が完了するまで引き抜けないようにする
    @Override
    public boolean canDrain(Direction from, Fluid fluid) {
        return this.isAged;
    }

    @Override
    public FluidTankInfo[] getTankInfo(Direction from) {
        return new FluidTankInfo[] { productTank.getInfo() };
    }

}
