package mods.defeatedcrow.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

/**
 * 1.20.1 stub for EntityMelonBomb - compressed melon bomb (storage + explosion).
 * Original used isAABBInMaterial, getBlock, getActivePotionEffect, stackSize, etc.
 * This stub retains registry and basic NBT; full water/lily/potion/break logic TODO.
 */
public class EntityMelonBomb extends Entity {

    private int readyTime = 80;
    private int ticksInAir = 0;

    public EntityMelonBomb(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) { this.readyTime = tag.getInt("ReadyTime"); }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) { tag.putInt("ReadyTime", readyTime); }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() { return super.getAddEntityPacket(); }

    public AABB getCollisionBox(Entity other) { return other.getBoundingBox(); }

    @Override
    public boolean canBeCollidedWith() { return !this.isRemoved(); }

    @Override
    public void tick() {
        super.tick();
        // TODO: restore water/lily/potion/break/placement logic
        if (!this.level().isClientSide) {
            if (this.tickCount > 200) this.discard();
            this.setDeltaMovement(this.getDeltaMovement().add(0, -0.04, 0));
            this.move(net.minecraft.world.entity.MoverType.SELF, this.getDeltaMovement());
        }
    }

    @Override
    public boolean hurt(net.minecraft.world.damagesource.DamageSource source, float amount) {
        if (!this.level().isClientSide) this.discard();
        return true;
    }
}
