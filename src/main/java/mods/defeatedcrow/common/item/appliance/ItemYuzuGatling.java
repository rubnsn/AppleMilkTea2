package mods.defeatedcrow.common.item.appliance;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
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
 * 1.20.1 Yuzu Gatling - ForgeEnergy gun, 6400 FE, shoots YuzuBullet.
 */
public class ItemYuzuGatling extends Item {
    public static final int MAX_ENERGY = 6400;
    public static final int COST_PER_SHOT = 20;

    public ItemYuzuGatling(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, java.util.List<Component> tooltip, TooltipFlag flag) {
        var cap = stack.getCapability(ForgeCapabilities.ENERGY).orElse(null);
        int stored = cap != null ? cap.getEnergyStored() : getEnergyStored(stack);
        tooltip.add(Component.literal(stored + " / " + MAX_ENERGY + " FE").withStyle(ChatFormatting.GRAY));
    }

    @Override public boolean isBarVisible(ItemStack stack) { return getEnergyStored(stack) < MAX_ENERGY; }
    @Override public int getBarWidth(ItemStack stack) { return Math.round(13.0F * getEnergyStored(stack) / MAX_ENERGY); }
    @Override public int getBarColor(ItemStack stack) { return 0xFFDD00; }

    private int getEnergyStored(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null ? tag.getInt("Energy") : 0;
    }
    private void setEnergyStored(ItemStack stack, int energy) {
        stack.getOrCreateTag().putInt("Energy", Math.min(energy, MAX_ENERGY));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            var cap = stack.getCapability(ForgeCapabilities.ENERGY).orElse(null);
            int stored = cap != null ? cap.getEnergyStored() : getEnergyStored(stack);
            if (stored >= COST_PER_SHOT) {
                var type = mods.defeatedcrow.common.registry.ModEntities.YUZU_BULLET.get();
                var bullet = new mods.defeatedcrow.common.entity.EntityYuzuBullet(type, level);
                bullet.moveTo(player.getX(), player.getEyeY() - 0.1, player.getZ(), player.getYRot(), player.getXRot());
                var look = player.getLookAngle();
                bullet.setDeltaMovement(look.scale(1.5));
                level.addFreshEntity(bullet);
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 0.5F, 1.0F);
                if (cap != null) cap.extractEnergy(COST_PER_SHOT, false);
                else setEnergyStored(stack, stored - COST_PER_SHOT);
                player.getCooldowns().addCooldown(this, 4);
            }
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ICapabilityProvider() {
            final EnergyStorage storage = new EnergyStorage(MAX_ENERGY, 500, 500, 0) {
                @Override public int getEnergyStored() { return ItemYuzuGatling.this.getEnergyStored(stack); }
                @Override public int getMaxEnergyStored() { return MAX_ENERGY; }
                @Override public int receiveEnergy(int maxReceive, boolean simulate) {
                    int stored = ItemYuzuGatling.this.getEnergyStored(stack);
                    int ret = Math.min(MAX_ENERGY - stored, Math.min(500, maxReceive));
                    if (!simulate) ItemYuzuGatling.this.setEnergyStored(stack, stored + ret);
                    return ret;
                }
                @Override public int extractEnergy(int maxExtract, boolean simulate) {
                    int stored = ItemYuzuGatling.this.getEnergyStored(stack);
                    int ret = Math.min(stored, Math.min(500, maxExtract));
                    if (!simulate) ItemYuzuGatling.this.setEnergyStored(stack, stored - ret);
                    return ret;
                }
                @Override public boolean canReceive() { return true; }
                @Override public boolean canExtract() { return true; }
            };
            final LazyOptional<IEnergyStorage> lazy = LazyOptional.of(() -> storage);
            @Override public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable net.minecraft.core.Direction side) {
                if (cap == ForgeCapabilities.ENERGY) return lazy.cast();
                return LazyOptional.empty();
            }
        };
    }
}
