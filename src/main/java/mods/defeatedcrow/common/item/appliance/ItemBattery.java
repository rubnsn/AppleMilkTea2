package mods.defeatedcrow.common.item.appliance;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

/**
 * 1.20.1 Battery - ForgeEnergy (IEnergyStorage) via capability.
 * Stores 32000 FE per stack, displays bar.
 */
public class ItemBattery extends Item {
    public static final int MAX_ENERGY = 32000;

    public ItemBattery(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        var cap = stack.getCapability(ForgeCapabilities.ENERGY).orElse(null);
        int stored = cap != null ? cap.getEnergyStored() : getEnergyStored(stack);
        int max = cap != null ? cap.getMaxEnergyStored() : MAX_ENERGY;
        tooltip.add(Component.literal(stored + " / " + max + " FE").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getEnergyStored(stack) < MAX_ENERGY;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        int stored = getEnergyStored(stack);
        return Math.round(13.0F * stored / MAX_ENERGY);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return 0x00FF00;
    }

    private int getEnergyStored(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null ? tag.getInt("Energy") : 0;
    }

    private void setEnergyStored(ItemStack stack, int energy) {
        stack.getOrCreateTag().putInt("Energy", Math.min(energy, MAX_ENERGY));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ICapabilityProvider() {
            final EnergyStorage storage = new EnergyStorage(MAX_ENERGY, 500, 500, 0) {
                @Override
                public int getEnergyStored() {
                    // sync with NBT
                    return ItemBattery.this.getEnergyStored(stack);
                }
                @Override
                public int getMaxEnergyStored() { return MAX_ENERGY; }
                @Override
                public int receiveEnergy(int maxReceive, boolean simulate) {
                    int stored = ItemBattery.this.getEnergyStored(stack);
                    int ret = Math.min(MAX_ENERGY - stored, Math.min(500, maxReceive));
                    if (!simulate) ItemBattery.this.setEnergyStored(stack, stored + ret);
                    return ret;
                }
                @Override
                public int extractEnergy(int maxExtract, boolean simulate) {
                    int stored = ItemBattery.this.getEnergyStored(stack);
                    int ret = Math.min(stored, Math.min(500, maxExtract));
                    if (!simulate) ItemBattery.this.setEnergyStored(stack, stored - ret);
                    return ret;
                }
                @Override
                public boolean canReceive() { return true; }
                @Override
                public boolean canExtract() { return true; }
            };
            final LazyOptional<IEnergyStorage> lazy = LazyOptional.of(() -> storage);
            @Override
            public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable net.minecraft.core.Direction side) {
                if (cap == ForgeCapabilities.ENERGY) return lazy.cast();
                return LazyOptional.empty();
            }
        };
    }
}
