package mods.defeatedcrow.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

/**
 * 1.20.1 stub for EntityYuzuBullet - legacy 1.7.10 projectile used by Yuzu Gatling.
 * Original used level.clip (old rayTrace), motionX/Y/Z, xTile, getBlock, EntityDamageSource.
 * Migration: level.clip, Vec3, BlockPos, damageSources(). See doc/entities/migration-guide.md
 */
public class EntityYuzuBullet extends Entity {

    protected int xTile = -1;
    protected int yTile = -1;
    protected int zTile = -1;
    protected Block inTile;
    protected int inData;
    protected boolean inGround;

    public LivingEntity shootingEntity;
    protected int ticksInGround;
    protected int ticksInAir;
    protected double damage = 5.0D;
    protected int knockbackStrength = 1;

    public EntityYuzuBullet(EntityType<?> type, Level level) {
        super(type, level);
    }

    public EntityYuzuBullet(Level level, LivingEntity shooter, float speed, float speed2, float adjX, float adjZ, float adjY) {
        this(mods.defeatedcrow.common.registry.ModEntities.YUZU_BULLET.get(), level);
        this.shootingEntity = shooter;
        this.moveTo(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ(), shooter.getYRot(), shooter.getXRot());
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        this.xTile = tag.getInt("xTile");
        this.yTile = tag.getInt("yTile");
        this.zTile = tag.getInt("zTile");
        this.inGround = tag.getBoolean("inGround");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("xTile", xTile);
        tag.putInt("yTile", yTile);
        tag.putInt("zTile", zTile);
        tag.putBoolean("inGround", inGround);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() { return super.getAddEntityPacket(); }

    @Override
    public void tick() {
        super.tick();
        // TODO: restore projectile logic (rayTrace, hitVec, inTile, damage) - stub just falls and discards
        if (!this.level().isClientSide) {
            if (this.tickCount > 80) this.discard();
            this.setDeltaMovement(this.getDeltaMovement().add(0, -0.03, 0));
            this.move(net.minecraft.world.entity.MoverType.SELF, this.getDeltaMovement());
        }
    }
}
