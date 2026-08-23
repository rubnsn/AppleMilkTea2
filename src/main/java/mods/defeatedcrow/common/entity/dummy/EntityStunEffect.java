package mods.defeatedcrow.common.entity.dummy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Level;

import mods.defeatedcrow.common.DCsAppleMilk;

/*
 * Original code was made by A.K.
 * AdvancedToolsの氷結用エンティティが元になっている
 */
public class EntityStunEffect extends Entity {

    private EntityLiving target;
    private EntityPlayer player;
    private EntityAITasks entityTasks;
    private int remain;

    public EntityStunEffect(Level world) {
        super(world);
    }

    public EntityStunEffect(Level world, EntityLiving targetEntity, EntityPlayer playerEntity, int remainTime) {
        super(world);
        if (targetEntity == null) {
            this.discard();
            this.target = null;
        } else {
            this.target = targetEntity;
            this.player = playerEntity;
            this.remain = remainTime;
            this.setSize(2.0F, 2.0F);
            this.target.ridingEntity = null;
            this.setPosition(this.target.getX(), this.target.getY(), this.target.getZ());
            this.yRot = this.target.yRot;

            if (this.target instanceof EntityMob) {
                this.originalMethodForAI();
            }
        }
    }

    private void originalMethodForAI() {
        this.entityTasks = this.target.tasks;
        ObfuscationReflectionHelper
            .setPrivateValue(EntityLiving.class, this.target, new EntityAITasks(this.level.theProfiler), 7);
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        this.remain = nbt.getInteger("RemainTime");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        nbt.setInteger("RemainTime", this.remain);
    }

    @Override
    public void tick() {
        if (!this.level.isClientSide) {
            --this.remain;

            if (this.target == null) {
                this.discard();
            } else {
                if (this.remain > 0 && this.target.isEntityAlive() && !(this.target instanceof EntityEnderman)) {
                    this.target.setPosition(this.getX(), this.target.getY(), this.getZ());
                    this.yRot = this.target.yRot;
                    this.target.onGround = false;
                    if (this.target instanceof EntityMob) {
                        ((EntityMob) this.target).attackTime = 20;
                        this.target.setAttackTarget(this.target);
                        this.target.setLastAttacker(this.target);
                    }

                    if (DCsAppleMilk.debugMode) {
                        for (int var1 = 0; var1 < 2; ++var1) {
                            double var2 = this.rand.nextDouble() * (double) this.width * 2.0D;
                            double var4 = this.rand.nextDouble() * Math.PI * 1.0D;
                            double var6 = this.getX() + var2 * Math.sin(var4);
                            double var8 = this.getY() + (double) this.height * this.rand.nextDouble();
                            double var10 = this.getZ() + var2 * Math.cos(var4);
                            this.level.spawnParticle("explode", var6, var8, var10, 0.0D, 0.0D, 0.0D);
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
        if (this.entityTasks != null) {
            ObfuscationReflectionHelper.setPrivateValue(EntityLiving.class, this.target, this.entityTasks, 7);
            this.target.setAttackTarget(null);
            this.target.setLastAttacker(null);
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
