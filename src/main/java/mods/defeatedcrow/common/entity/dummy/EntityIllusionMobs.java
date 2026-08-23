package mods.defeatedcrow.common.entity.dummy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

/**
 * 1.20.1 stub for EntityIllusionMobs — legacy 1.7.10 illusion mob.
 * Original used isAABBInMaterial, motionX, yOffset, getCollisionBox with missing type.
 * This stub compiles via ModEntities. Full AI/particle logic TODO.
 */
public class EntityIllusionMobs extends Entity {

    private int livingTime = 0;

    public EntityIllusionMobs(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) { this.livingTime = tag.getInt("LivingTime"); }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) { tag.putInt("LivingTime", livingTime); }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() { return super.getAddEntityPacket(); }

    public AABB getCollisionBox(Entity other) { return other.getBoundingBox(); }

    @Override
    public boolean canBeCollidedWith() { return !this.isRemoved(); }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            if (this.livingTime++ > 60) this.discard();
        }
    }
}
