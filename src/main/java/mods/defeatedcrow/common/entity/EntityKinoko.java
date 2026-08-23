package mods.defeatedcrow.common.entity;

import net.minecraft.world.level.Level;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Level;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.entity.edible.PlaceableFoods;

public class EntityKinoko extends PlaceableFoods {

    public EntityKinoko(Level world) {
        super(world);
    }

    public EntityKinoko(Level world, boolean chops, ItemStack item) {
        super(world, chops, item);
    }

    public EntityKinoko(Level world, boolean chops, ItemStack item, double x, double y, double z) {
        super(world, chops, item, x, y, z);
    }

    @Override
    protected ItemStack returnItem() {
        return new ItemStack(DCsAppleMilk.mushroomBox, 1, this.getItemMetadata());
    }

    @Override
    public void updateRiderPosition() {
        if (this.vehicle != null) {
            double d0 = Math.cos((double) this.yRot * Math.PI / 180.0D) * 0.4D;
            double d1 = Math.sin((double) this.yRot * Math.PI / 180.0D) * 0.4D;
            this.vehicle.setPosition(
                this.getX(),
                this.getY() + this.getMountedYOffset() + this.vehicle.getYOffset(),
                this.getZ());
        }
    }

    @Override
    public double getMountedYOffset() {
        return (double) this.height * 0.75D;
    }

    @Override
    public boolean interactFirst(Player par1EntityPlayer) {
        if (this.vehicle != null && this.vehicle instanceof Player
            && this.vehicle != par1EntityPlayer) {
            return true;
        } else {
            if (!this.level.isClientSide) {
                par1EntityPlayer.startRiding(this);
            }

            return true;
        }

    }

}
