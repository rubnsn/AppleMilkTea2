package mods.defeatedcrow.api.energy;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

/**
 * 充電可能アイテムのItemBlock版のベースクラス
 * 注意!このクラスを継承したItemBlockは、設置するとチャージ情報がリセットされてしまいます。
 * TileEntityなどを利用して、別途で情報保存用の処理を行う必要あり。 <br>
 * 1.20.1: BlockItem 継承、NBT維持、capability ブリッジ ({@code ForgeCapabilities.ENERGY}) を内蔵。
 */
public abstract class BatteyItemBlockBase extends BlockItem implements IBattery {

    public static final String TAG_CHARGE = "charge";

    public BatteyItemBlockBase(Block block, Item.Properties properties) {
        super(block, properties);
    }

    @Override
    public int getChargeAmount(ItemStack item) {
        CompoundTag nbt = item.getTag();
        if (nbt != null && nbt.contains(TAG_CHARGE)) {
            return nbt.getInt(TAG_CHARGE);
        }
        return 0;
    }

    @Override
    public abstract int getMaxAmount(ItemStack item);

    @Override
    public boolean isFullCharged(ItemStack item) {
        return this.getChargeAmount(item) >= this.getMaxAmount(item);
    }

    @Override
    public int charge(ItemStack item, int amount, boolean flag) {

        if (item == null || item.isEmpty()) return 0;

        int charge = this.getChargeAmount(item);
        int i = Math.max(this.getMaxAmount(item) - charge, 0);
        int increase = Math.min(amount, i);

        if (flag && increase > 0) {
            item.getOrCreateTag().putInt(TAG_CHARGE, charge + increase);
        }
        return increase;
    }

    @Override
    public int discharge(ItemStack item, int amount, boolean flag) {

        if (item == null || item.isEmpty()) return 0;

        int charge = this.getChargeAmount(item);
        int reduce = Math.min(amount, charge);

        if (flag && reduce > 0) {
            item.getOrCreateTag().putInt(TAG_CHARGE, charge - reduce);
        }
        return reduce;
    }

    // マウスオーバー時の表示情報
    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, level, tooltip, flagIn);
        int max = this.getMaxAmount(stack);
        tooltip.add(Component.literal("charge amount : " + this.getChargeAmount(stack) + "/" + max)
            .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        int max = this.getMaxAmount(stack);
        int charge = Mth.clamp(this.getChargeAmount(stack), 0, max);
        return Math.round(13.0F * (float) charge / (float) max);
    }

    /**
     * ForgeEnergy bridge. 旧 IBattery の charge/discharge を IEnergyStorage 経由に中継する。
     */
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
        return new EnergyCapabilityProvider(stack);
    }

    private class EnergyCapabilityProvider implements ICapabilityProvider {

        private final ItemStack container;
        private final LazyOptional<IEnergyStorage> energy;

        EnergyCapabilityProvider(ItemStack stack) {
            this.container = stack;
            this.energy = LazyOptional.of(() -> new EnergyStorageBridge(this.container));
        }

        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
            if (cap == ForgeCapabilities.ENERGY) {
                return energy.cast();
            }
            return LazyOptional.empty();
        }
    }

    private class EnergyStorageBridge implements IEnergyStorage {

        private final ItemStack container;

        EnergyStorageBridge(ItemStack stack) {
            this.container = stack;
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            return BatteyItemBlockBase.this.charge(container, maxReceive, !simulate);
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            return BatteyItemBlockBase.this.discharge(container, maxExtract, !simulate);
        }

        @Override
        public int getEnergyStored() {
            return BatteyItemBlockBase.this.getChargeAmount(container);
        }

        @Override
        public int getMaxEnergyStored() {
            return BatteyItemBlockBase.this.getMaxAmount(container);
        }

        @Override
        public boolean canExtract() {
            return true;
        }

        @Override
        public boolean canReceive() {
            return true;
        }
    }

}
