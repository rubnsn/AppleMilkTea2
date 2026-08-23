package mods.defeatedcrow.common.tile.energy;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.minecraftforge.fml.ModList;
import mods.defeatedcrow.common.config.PropertyHandler;
import mods.defeatedcrow.plugin.IC2.EUItemHandler;
import mods.defeatedcrow.plugin.IC2.EUSinkManager;
import mods.defeatedcrow.plugin.IC2.IEUSinkChannel;
import mods.defeatedcrow.plugin.SSector.SS2ItemHandler;
import mods.defeatedcrow.plugin.cofh.RFItemHandler;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceHandler;

/*
 * TileChargerBaseの発展型。
 * 他MODのエネルギー受け入れのために用意したもの。
 */
public class TileChargerDevice extends TileChargerBase implements IEnergyHandler, IEnergyInfo, IGearForceHandler {

    protected IEUSinkChannel EUChannel;

    // このTileにはコンストラクタが要る
    public TileChargerDevice() {
        super();
        if (ModList.get().isLoaded("ic2")) {
            EUChannel = EUSinkManager.getChannel(this, MAX_CHARGE, 3);
        }
    }

    // このへんはオーバーライドしとかないとイマイチ動きが悪い
    @Override
    public void load(CompoundTag par1CompoundTag) {
        if (EUChannel != null) {
            EUChannel.readFromNBT2(par1CompoundTag);
        }
        super.load(par1CompoundTag);
    }

    @Override
    public void saveAdditional(CompoundTag par1CompoundTag) {
        if (EUChannel != null) {
            EUChannel.writeToNBT2(par1CompoundTag);
        }
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

    /* Modごとの変換レート。コンフィグ変更可能にしました。バランスは投げ捨てました。諸事情により問い合わせは拒否します。 */

    private static int exchangeRateRF() {
        // RF -> Charge
        return PropertyHandler.rateRF();
    }

    private static int exchangeRateEU() {
        // EU -> Charge
        return PropertyHandler.rateEU();
    }

    private static int exchangeRateGF() {
        // GF -> Charge
        return PropertyHandler.rateGF();
    }

    /* 充電操作用のメソッド */

    /**
     * 他MODの電池アイテムを対応させるためのメソッド。
     * フル充電でもtrueを返す。
     */
    @Override
    public boolean isChargeableBattery(ItemStack item) {
        boolean flag = false;

        if (ModList.get().isLoaded("sextiarysector")) {
            flag = SS2ItemHandler.isGFItem(item);
        }
        if (ModList.get().isLoaded("cofh_core") && !flag) {
            flag = RFItemHandler.isChargeable(item);
        }
        if (!flag && ModList.get().isLoaded("ic2") && !flag) {
            flag = EUItemHandler.isChargeable(item);
        }

        return flag;
    }

    /**
     * 他MODの電池アイテムを対応させるためのメソッド。
     * ここで充電を増やす。 <br>
     * 減らす方はTileChargerBaseで行っているので不要。 <br>
     * シミュレート可能。
     */
    @Override
    public int chargeAnotherBattery(ItemStack item, int inc, boolean flag) {
        int ret = 0;
        if (ModList.get().isLoaded("sextiarysector")) {
            int i = SS2ItemHandler.chargeAmount(item, inc * this.exchangeRateGF(), flag);
            ret = Math.round(i / this.exchangeRateGF());
        }
        if (ModList.get().isLoaded("cofh_core") && ret == 0) {
            int i = RFItemHandler.chargeAmount(item, inc * this.exchangeRateRF(), flag);
            ret = Math.round(i / this.exchangeRateRF());
        }
        if (ModList.get().isLoaded("ic2") && ret == 0) {
            int i = EUItemHandler.chargeAmount(item, inc * this.exchangeRateEU(), flag);
            ret = Math.round(i / this.exchangeRateEU());
        }
        return ret;
    }

    // こちら側からネットワークへのチェックが要るIC2ケーブルからのEU受け入れはこのメソッドで行う。
    @Override
    public int acceptChargeFromDir(Direction dir) {
        int ret = 0;
        // EU受入量は指定する必要があるので、とりあえず512とする。
        if (EUChannel != null) {
            int i = this.getChargeAmount();
            double eu = Math.min(EUChannel.getEnergyStored2(), 512);
            double get = eu / this.exchangeRateEU();
            if ((MAX_CHARGE - i) < get) return 0;

            if (EUChannel.useEnergy2(eu)) {
                ret = (int) get;
            }
        }

        if (ret == 0) return super.acceptChargeFromDir(dir);

        return ret;

    }

    /* 他MODの電池を燃料スロットで溶かすための操作 */

    // 燃料判定
    @Override
    public int getItemBurnTime(ItemStack item) {
        if (item == null) return 0;
        else {
            int ret = 0;
            int inc = 16; // 速度はチャージバッテリーと同じ

            if (ModList.get().isLoaded("sextiarysector") && ret == 0) {
                int i = SS2ItemHandler.dischargeAmount(item, inc * exchangeRateGF(), true);
                ret = Math.round(i / exchangeRateGF());
            }
            if (ModList.get().isLoaded("cofh_core") && ret == 0) {
                int i = RFItemHandler.dischargeAmount(item, inc * exchangeRateRF(), true);
                ret = Math.round(i / exchangeRateRF());
            }
            if (ModList.get().isLoaded("ic2") && ret == 0) {
                int i = EUItemHandler.dischargeAmount(item, inc * exchangeRateEU(), true);
                ret = Math.round(i / exchangeRateEU());
            }
            if (ret == 0) return super.getItemBurnTime(item);
            return ret;
        }
    }

    @Override
    public int discharge(ItemStack item, int amount, int slot) {
        if (item == null) return 0;
        else {
            int ret = 0;
            int inc = amount;

            if (ModList.get().isLoaded("sextiarysector") && ret == 0) {
                int i = SS2ItemHandler.dischargeAmount(item, inc * exchangeRateGF(), false);
                ret = Math.round(i / exchangeRateGF());

                if (ret > 0 && SS2ItemHandler.getAmount(item) == 0 && this.itemstacks[1] == null) {
                    if (item == null || item.stackSize == 0) {
                        this.setInventorySlotContents(0, null);
                    } else {
                        this.setInventorySlotContents(1, item.copy());
                        this.decrStackSize(slot, 1);
                    }
                }
            }
            if (ModList.get().isLoaded("cofh_core") && ret == 0) {
                int i = RFItemHandler.dischargeAmount(item, inc * exchangeRateRF(), false);
                ret = Math.round(i / exchangeRateRF());

                if (ret > 0 && RFItemHandler.getAmount(item) == 0 && this.itemstacks[1] == null) {
                    if (item == null || item.stackSize == 0) {
                        this.setInventorySlotContents(0, null);
                    } else {
                        this.setInventorySlotContents(1, item.copy());
                        this.decrStackSize(slot, 1);
                    }

                }
            }
            if (ModList.get().isLoaded("ic2") && ret == 0) {
                int i = EUItemHandler.dischargeAmount(item, inc * exchangeRateEU(), false);
                ret = Math.round(i / exchangeRateEU());

                if (ret > 0 && EUItemHandler.getAmount(item) == 0 && this.itemstacks[1] == null) {
                    if (item == null || item.stackSize == 0) {
                        this.setInventorySlotContents(0, null);
                    } else {
                        this.setInventorySlotContents(1, item.copy());
                        this.decrStackSize(slot, 1);
                    }
                }
            }

            if (ret == 0) {
                this.decrStackSize(slot, 1);
                ret = super.getItemBurnTime(item);
            }

            return ret;
        }
    }

    /* for EU */

    @Override
    public void invalidate() {
        if (EUChannel != null) {
            EUChannel.invalidate2();
        }
        super.invalidate();
    }

    // 以下はSinkChannel用のメソッド
    @Override
    public void onChunkUnload() {
        if (EUChannel != null) {
            EUChannel.onChunkUnload2();
        }
        super.onChunkUnload();
    }

    @Override
    public static void tick(Level level, BlockPos pos, BlockState state, TileChargerDevice be) {
        // 1.20.1 tick (was updateEntity) - see doc/tile-entities/migration-guide.md
        if (level.isClientSide) return;
        be.setChanged();
        level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3);
    }

    /* for RF */

        @Override
    public boolean canConnectEnergy(Direction dir) {
        // 向きごとにコネクト可能か見ているっぽい
        BlockEntity tile = level.getBlockEntity(pos.relative(dir));
        boolean flag = (tile instanceof IEnergyConnection);
        return flag;
    }

        @Override
    public int receiveEnergy(Direction dir, int in, boolean flag) {
        // エネルギーの受け入れ
        int eng = this.getChargeAmount();
        int get = in;
        if (this.isFullCharged() || get < this.exchangeRateRF()) return 0;

        int ret = Math.min((this.getMaxChargeAmount() - eng) * this.exchangeRateRF(), get);

        if (!flag) {
            int i = Math.round(ret / this.exchangeRateRF());// 1/10に
            this.setChargeAmount(eng + i);
        }

        return ret;
    }

        @Override
    public int extractEnergy(Direction paramForgeDirection, int paramInt, boolean paramBoolean) {
        // 出力はしない
        return 0;
    }

        @Override
    public int getEnergyStored(Direction paramForgeDirection) {
        // 10倍になる
        return this.getChargeAmount() * this.exchangeRateRF();
    }

        @Override
    public int getMaxEnergyStored(Direction paramForgeDirection) {
        // 10倍
        return this.getMaxChargeAmount() * this.exchangeRateRF();
    }

        @Override
    public int getInfoEnergyPerTick() {
        return 0;
    }

        @Override
    public int getInfoMaxEnergyPerTick() {
        return 0;
    }

        @Override
    public int getInfoEnergyStored() {
        int eng = this.getChargeAmount();
        int get = eng * this.exchangeRateRF();
        return get;
    }

        @Override
    public int getInfoMaxEnergyStored() {
        int eng = this.getMaxChargeAmount();
        int get = eng * this.exchangeRateRF();
        return get;
    }

    /* for GF */

        @Override
    public int addEnergy(Direction from, int power, int speed, boolean simulate) {
        // エネルギーの受け入れ
        int eng = this.getChargeAmount();
        int get = speed;
        if (this.isFullCharged() || power < 3) return 0;

        if (get < this.exchangeRateGF()) {
            get = 3;
        }
        int ret = Math.min((this.getMaxChargeAmount() - eng) * this.exchangeRateGF(), get);

        if (!simulate) {
            int i = Math.round(1.0F * ret / this.exchangeRateGF());// 1/3に
            this.setChargeAmount(eng + i);
        }

        return ret;
    }

        @Override
    public int drawEnergy(Direction from, int power, int speed, boolean simulate) {
        return 0;
    }

        @Override
    public boolean canInterface(Direction from) {
        return true;
    }

        @Override
    public int getSpeedStored(Direction from) {
        int eng = this.getChargeAmount();
        int get = eng * this.exchangeRateGF();
        return get;
    }

        @Override
    public int getPowerStored(Direction from) {
        return 0;
    }

        @Override
    public int getMaxSpeedStored(Direction from) {
        int eng = this.getMaxChargeAmount();
        int get = eng * this.exchangeRateGF();
        return get;
    }

        @Override
    public int getMaxPowerStored(Direction from) {
        return 3;
    }

}
