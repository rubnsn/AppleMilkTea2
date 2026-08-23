package mods.defeatedcrow.common.entity.dummy;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;

import mods.defeatedcrow.common.DCsAppleMilk;

/*
 * Original code was made by A.K.
 * AdvancedToolsの氷結用エンティティが元になっている
 */
public class EntityStunEffect extends Entity {

    private LivingEntity target;
    private Player player;
    private GoalSelector entityTasks;
    private int remain;

    // 1.20.1: Entity constructor now requires EntityType + Level
    public EntityStunEffect(net.minecraft.world.entity.EntityType<?> type, Level world) {
        super(type, world);
    }

    public EntityStunEffect(net.minecraft.world.entity.EntityType<?> type, Level world, LivingEntity targetEntity, Player playerEntity, int remainTime) {
        super(type, world);
        if (targetEntity == null) {
            this.discard();
            this.target = null;
        } else {
            this.target = targetEntity;
            this.player = playerEntity;
            this.remain = remainTime;
            // 1.20.1: setSize removed, use EntityDimensions via Builder
            // this.setSize(2.0F, 2.0F);
            if (this.target.getVehicle() != null) this.target.stopRiding();
            this.setPos(this.target.getX(), this.target.getY(), this.target.getZ());
            this.setYRot(this.target.getYRot());

            if (this.target instanceof Mob) {
                this.originalMethodForAI();
            }
        }
    }

    private void originalMethodForAI() {
        // 1.20.1: GoalSelector access via Mob#goalSelector, reflection removed
        // this.entityTasks = ((Mob)this.target).goalSelector;
        // ObfuscationReflectionHelper removed
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        this.remain = nbt.getInt("RemainTime");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        nbt.putInt("RemainTime", this.remain);
    }

    @Override
    public void tick() {
        if (!this.level.isClientSide) {
            --this.remain;

            if (this.target == null) {
                this.discard();
            } else {
                if (this.remain > 0 && this.target.isAlive() && !(this.target instanceof EnderMan)) {
                    this.target.setPos(this.getX(), this.target.getY(), this.getZ());
                    this.setYRot(this.target.getYRot());
                    this.target.setOnGround(false);
                    if (this.target instanceof Mob) {
                        // 1.20.1: attackTime removed, use tick counting
                        // ((Mob) this.target).attackTime = 20;
                        if (this.target instanceof Mob mob) {
                            mob.setTarget(mob);
                            mob.setLastHurtByMob(mob);
                        }
                    }

                    if (DCsAppleMilk.debugMode) {
                        for (int var1 = 0; var1 < 2; ++var1) {
                            double var2 = this.level.random.nextDouble() * (double) this.getBbWidth() * 2.0D;
                            double var4 = this.level.random.nextDouble() * Math.PI * 1.0D;
                            double var6 = this.getX() + var2 * Math.sin(var4);
                            double var8 = this.getY() + (double) this.getBbHeight() * this.level.random.nextDouble();
                            double var10 = this.getZ() + var2 * Math.cos(var4);
                            this.level.addParticle(net.minecraft.core.particles.ParticleTypes.EXPLOSION, var6, var8, var10, 0.0D, 0.0D, 0.0D);
                        }
                    }
                } else {
                    this.discard();
                }
            }
        }
    }

    @Override
    public void discard() {
        if (this.entityTasks != null && this.target instanceof Mob mob) {
            // 1.20.1: GoalSelector restore removed
            // ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, this.target, this.entityTasks, 7);
            mob.setTarget(null);
            mob.setLastHurtByMob(null);
        }
        super.discard();
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public float getBrightness(float var1) {
        return 1.0f;
    }

}
