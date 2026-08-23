package mods.defeatedcrow.common.tile;

import net.minecraft.world.level.block.Block;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
// BiomeDictionary removed - use TagKey<Biome> + Holder<Biome>
import net.minecraft.core.Direction;
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
 */
public class TileBrewingBarrel extends BlockEntity implements IFluidHandler {
    public TileBrewingBarrel(BlockPos pos, BlockState state) { super(null, pos, state); }


    private int aging = 0;
    private boolean isAged = false;
    public DCsTank productTank = new DCsTank(1000);
    // 向き
    private boolean side = false;

    private int lastState = 0;

    // NBT
    @Override
    public void load(CompoundTag par1CompoundTag) {
        super.load(par1CompoundTag);
        this.aging = par1CompoundTag.getInt("Remaining");
        this.isAged = par1CompoundTag.getBoolean("IsAged");
        this.side = par1CompoundTag.getBoolean("Side");

        this.productTank = new DCsTank(1000);
        if (par1CompoundTag.contains("productTank")) {
            this.productTank.load(par1CompoundTag.getCompound("productTank"));
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void saveAdditional(CompoundTag par1CompoundTag) {
        super.saveAdditional(par1CompoundTag);
        par1CompoundTag.putInt("Remaining", this.aging);
        par1CompoundTag.putBoolean("IsAged", this.isAged);
        par1CompoundTag.putBoolean("Side", this.side);

        CompoundTag tank = new CompoundTag();
        this.productTank.saveAdditional(tank);
        par1CompoundTag.put("productTank", tank);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag nbtTagCompound = new CompoundTag();
        this.saveAdditional(nbtTagCompound);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
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
    public boolean isOnNormalCube() { return level != null && level.getBlockState(getBlockPos().below()).isSolid(); }

    @Override
    public static void tick(Level level, BlockPos pos, BlockState state, TileBrewingBarrel be) {
        // 1.20.1 tick (was updateEntity) - see doc/tile-entities/migration-guide.md
        if (level.isClientSide) return;
        be.setChanged();
        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
    }

    private boolean canBrew() {
        boolean flag = false;
        Fluid input = be.productTank.getFluidType();

        if (input != null && BrewingRecipe.recipe.containsKey(input)) {
            flag = true;
        }

        return flag;
    }

    // 熟成完了処理
    private void onBrewing() {
        Fluid input = be.productTank.getFluidType();
        int amount = be.productTank.getFluidAmount();

        if (input != null && amount > 0) {
            if (BrewingRecipe.recipe.containsKey(input)) {
                Fluid output = BrewingRecipe.recipe.get(input);
                FluidStack ret = new FluidStack(output, amount);
                be.productTank.setFluid(ret);
            }
        }
        be.setChanged();
    }

    public int getMetadata() { return 0; }

        return flag;
    }

    public boolean isDryBiome() { return level != null && level.getBiome(getBlockPos()).is(net.minecraft.tags.BiomeTags.IS_DESERT); }

    /* ====== 以下、IFluidHandlerの実装メソッド ====== */

    @Override
    public FluidStack drain(Direction from, FluidStack resource, boolean doDrain) {
        if (resource == null || !this.isAged) {
            return null;
        }
        if (productTank.getFluidType() == resource.getFluid()) {
            FluidStack ret = productTank.drain(resource.amount, doDrain);
            if (productTank.isEmpty()) {
                this.setAged(false);
                this.setAgingStage(0);
            }
            return ret;
        }
        return null;
    }

    @Override
    public FluidStack drain(Direction from, int maxDrain, boolean doDrain) {
        if (maxDrain <= 0 || !this.isAged) {
            return null;
        }
        FluidStack ret = productTank.drain(maxDrain, doDrain);
        if (productTank.isEmpty()) {
            this.setAged(false);
            this.setAgingStage(0);
        }
        return ret;
    }

    // 外部からの液体の受け入れ
    @Override
    public int fill(Direction from, FluidStack resource, boolean doFill) {
        if (resource == null || resource.getFluid() == null || this.isAged) {
            return 0;
        }

        FluidStack current = this.productTank.getFluid();
        FluidStack resourceCopy = resource.copy();
        if (current != null && current.amount > 0 && current.isFluidEqual(resourceCopy)) {
            return 0;
        }

        int i = 0;
        int used = this.productTank.fill(resourceCopy, doFill);
        resourceCopy.amount -= used;
        i += used;

        return i;
    }

    // 空でないと&醸造未完了でないと受け入れない
    @Override
    public boolean canFill(Direction from, Fluid fluid) {
        return !this.isAged && fluid != null && BrewingRecipe.recipe.containsKey(fluid) && this.productTank.isEmpty();
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
