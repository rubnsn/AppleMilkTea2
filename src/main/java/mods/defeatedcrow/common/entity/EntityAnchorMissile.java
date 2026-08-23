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
 * 1.20.1 stub for EntityAnchorMissile - legacy 1.7.10 homing projectile.
 * Original used level.clip (old rayTrace) (rayTrace), motionX/Y/Z, xTile/yTile/zTile, EntityDamageSource, S2BPacket.
 * 1.20.1 migration: level.clip(ClipContext), Vec3 deltaMovement, BlockPos, damageSources().
 * Full homing + CustomExplosion logic is TODO; this stub compiles and retains registry + NBT.
 * See doc/entities/migration-guide.md:56 and plan.md:T5
 */
public class EntityAnchorMissile extends Entity {

    protected int xTile = -1;
    protected int yTile = -1;
    protected int zTile = -1;
    protected Block inTile;
    protected int inData;
    protected boolean inGround;

    public LivingEntity shootingEntity;
    public LivingEntity targetEntity;
    public int ticksInGround;
    public int ticksInAir;
    public float damage = 6.0F;
    public int knockbackStrength = 1;
    public boolean explode = false;

    public EntityAnchorMissile(EntityType<?> type, Level level) {
        super(type, level);
    }

    // Legacy constructor retained for callers (ItemYuzuGatling etc.) - delegates to new type
    public EntityAnchorMissile(Level level, LivingEntity shooter, LivingEntity target, float speed, float speed2, float yaw, float adjX, float adjY, float adjZ) {
        this(mods.defeatedcrow.common.registry.ModEntities.ANCHOR_MISSILE.get(), level);
        this.shootingEntity = shooter;
        this.targetEntity = target;
        this.moveTo(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ(), yaw, 0);
    }

    public EntityAnchorMissile(Level level, LivingEntity shooter, float speed, float speed2, float yaw, float pitch) {
        this(mods.defeatedcrow.common.registry.ModEntities.ANCHOR_MISSILE.get(), level);
        this.shootingEntity = shooter;
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
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return super.getAddEntityPacket();
    }

    @Override
    public void tick() {
        super.tick();
        // TODO: restore homing + explosion logic using level.clip + setDeltaMovement + CustomExplosion
        // 1.7.10 logic used level.clip (old rayTrace)_a, hitVec.x (old), motionX, getBlock(xTile...), EntityDamageSource
        // For now, simple gravity + discard after 100 ticks to avoid lingering
        if (!this.level().isClientSide) {
            if (this.tickCount > 100) this.discard();
            this.setDeltaMovement(this.getDeltaMovement().add(0, -0.03, 0));
            this.move(net.minecraft.world.entity.MoverType.SELF, this.getDeltaMovement());
        }
    }

    // Legacy damage source helper stub
    public net.minecraft.world.damagesource.DamageSource thisDamageSource(Entity attacker) {
        return this.level().damageSources().mobProjectile(this, attacker instanceof LivingEntity le ? le : null);
    }
}
