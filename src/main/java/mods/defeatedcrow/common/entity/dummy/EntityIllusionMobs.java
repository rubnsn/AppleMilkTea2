package mods.defeatedcrow.common.entity.dummy;

import net.minecraft.world.level.material.MapColor;
// Material removed in 1.20.1 - use BlockState properties
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Level;

public class EntityIllusionMobs extends Entity {

    private int livingTime = 0;

    public EntityIllusionMobs(Level world) {
        super(world);
        this.preventEntitySpawning = true;
        this.setSize(0.6F, 2.0F);
        this.yOffset = this.height / 2.0F;
        this.livingTime = 0;
    }

    public EntityIllusionMobs(Level par1World, double par2, double par4, double par6, float yaw) {
        this(par1World);
        this.setPosition(par2, par4 + (double) this.yOffset, par6);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.xo = par2;
        this.yo = par4;
        this.zo = par6;
        this.yRot = yaw;
    }

    @Override
    protected boolean canTriggerWalking() {
        return true;
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    public AABB getCollisionBox( par1Entity) {
        return par1Entity.getBoundingBox();
    }

    @Override
    public AABB getBoundingBox() {
        return this.getBoundingBox();
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        // 攻撃されると消えてしまう
        this.discard();
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return !this.isRemoved();
    }

    @Override
    public void tick() {
        super.tick();

        if (this.livingTime > 60) {
            this.discard();
        } else {
            this.livingTime++;
        }

        byte b0 = 5;
        double d0 = 0.0D;

        for (int i = 0; i < b0; ++i) {
            double d1 = this.getBoundingBox().minY
                + (this.getBoundingBox().maxY - this.getBoundingBox().minY) * (double) (i + 0) / (double) b0
                - 0.125D;
            double d2 = this.getBoundingBox().minY
                + (this.getBoundingBox().maxY - this.getBoundingBox().minY) * (double) (i + 1) / (double) b0
                - 0.125D;
            AABB axisalignedbb = AABB.getBoundingBox(
                this.getBoundingBox().minX,
                d1,
                this.getBoundingBox().minZ,
                this.getBoundingBox().maxX,
                d2,
                this.getBoundingBox().maxZ);

            // 浮力
            if (this.level.isAABBInMaterial(axisalignedbb, /*/*Material*/ water*/ net.minecraft.world.level.material.Fluids.WATER)) {
                d0 += 1.0D / (double) b0;
            }
        }

        if (d0 < 1.0D) {
            double d4 = d0 * 2.0D - 1.0D;
            this.motionY += 0.03999999910593033D * d4;
        } else {
            if (this.motionY < 0.0D) {
                this.motionY /= 2.0D;
            }

            this.motionY += 0.007000000216066837D;
        }

        if (this.onGround) {
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
        }
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_70037_1_) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag p_70014_1_) {}

}
