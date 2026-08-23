package mods.defeatedcrow.common.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

/**
 * 1.20.1 stub for EntityYuzuBullet - projectile.
 * Original 1.7.10 used motionX, blockX, etc.
 * See doc/entities/migration-guide.md
 */
public class EntityYuzuBullet extends Entity {

    public EntityYuzuBullet(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {}

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return super.getAddEntityPacket();
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, -0.05, 0));
            this.move(net.minecraft.world.entity.MoverType.SELF, this.getDeltaMovement());
            if (this.tickCount > 100) this.discard();
        }
    }
}
