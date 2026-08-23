package mods.defeatedcrow.common.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.potion.Potion;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.Mth;
import net.minecraft.world.Level;
import net.minecraftforge.fluids.LiquidBlock;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.AchievementRegister;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.handler.CustomExplosion;

public class EntitySilkyMelon extends Entity {

    // 何のフラグだか判らん
    protected boolean field_70279_a;
    // 騎乗Entityの速度計算に使用
    protected double speedMultiplier;
    // 加速度増加量
    protected int boatPosRotationIncrements;
    // 位置情報
    protected double boatX;
    protected double boatY;
    protected double boatZ;
    protected double boatYaw;
    protected double boatPitch;
    
    protected double velocityX;
    
    protected double velocityY;
    
    protected double velocityZ;

    public int readyTime;

    public EntitySilkyMelon(Level par1World) {
        super(type, level);
        this.field_70279_a = true;
        this.speedMultiplier = 0.07D;
        this.preventEntitySpawning = true;
        this.setSize(0.6F, 0.6F);
        this.yOffset = this.height / 2.0F;
        this.readyTime = 80;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_ID_17, new Integer(0));
        this.entityData.define(18, new Integer(1));
        this.entityData.define(19, new Float(0.0F));
        this.entityData.define(20, new Integer(0));
    }

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
        return true;
    }

    public EntitySilkyMelon(Level par1World, double par2, double par4, double par6) {
        this(par1World);
        this.setPosition(par2, par4 + this.yOffset, par6);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.xo = par2;
        this.yo = par4;
        this.zo = par6;
    }

    @Override
    public double getMountedYOffset() {
        return this.height * 0.0D + 0.06000001192092896D;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (this.isEntityInvulnerable()) {
            return false;
        } else if (!this.level.isClientSide && !this.isRemoved()) {
            this.setForwardDirection(-this.getForwardDirection());
            // 短時間でのダメージの蓄積計算。連続で叩くと破壊される
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + par2 * 10.0F);
            this.setBeenAttacked();
            // ダメージソースを確認
            boolean flag = par1DamageSource.getEntity() instanceof EntityPlayer
                && ((EntityPlayer) par1DamageSource.getEntity()).capabilities.isCreativeMode;
            // 起爆トリガー
            boolean explode = par1DamageSource.isExplosion() || par1DamageSource.isProjectile()
                || !(par1DamageSource.getEntity() instanceof EntityPlayer);

            EntityLivingBase igniter = null;
            if (par1DamageSource instanceof EntityDamageSourceIndirect) {
                Entity ent = ((EntityDamageSourceIndirect) par1DamageSource).getEntity();
                if (ent instanceof EntityLivingBase) {
                    igniter = (EntityLivingBase) ent;
                }

            }

            // 壊れるときの動作
            if ((flag || this.getDamageTaken() > 40.0F) && !explode) {
                // 起爆条件を満たさずに40ダメージ与えれば平和的にアイテム化する
                if (this.vehicle != null) {
                    this.vehicle.startRiding(this);
                }

                if (!flag) {
                    this.spawnAtLocation(this.dropItem(), 1, 0.0F);
                }

                this.discard();
            } else if (explode && this.getExploded() < 1) {
                // 起爆条件を満たしたので爆発
                this.explode(igniter);
            }

            return true;
        } else {
            return true;
        }
    }

    protected void explode(EntityLivingBase igniter) {
        this.setExploded(4);
        if (DCsConfig.canExplodeMelon) {
            float f = 3.0F;
            CustomExplosion explosion = new CustomExplosion(
                level,
                this,
                igniter,
                this.getX(),
                this.getY(),
                this.getZ(),
                f,
                CustomExplosion.Type.Melon,
                true);
            explosion.doExplosion();
        }

        if (igniter instanceof EntityPlayer) {
            ((EntityPlayer) igniter).triggerAchievement(AchievementRegister.useSilkMelon);
        }

        int X = Mth.floor_double(this.getX());
        int Y = Mth.floor_double(this.getY());
        int Z = Mth.floor_double(this.getZ());

        if (!DCsConfig.melonBreakBlock) {
            for (int i = -2; i < 3; i++) {
                for (int k = -2; k < 3; k++) {
                    for (int j = -2; j < 3; j++) {
                        if (Y - j >= 0 && Y - j < 255 && !this.level.isAirBlock(X + i, Y - j, Z + k)) {

                            Block block = this.level.getBlock(X + i, Y - j, Z + k);
                            int meta = this.level.getBlockMetadata(X + i, Y - j, Z + k);
                            if (block != null) {
                                boolean canBreak = true;
                                if (block.getExplosionResistance(this) > 1000
                                    || block.getBlockHardness(level, X + i, Y - j, Z + k) < 0) canBreak = false;
                                if (DCsConfig.fearMelon && Y - j > 0) canBreak = true;
                                if (DCsConfig.completeFearMelon) canBreak = true;

                                boolean c = block.hasTileEntity(meta) || !block.renderAsNormalBlock();

                                if (canBreak) {
                                    if (block.hasTileEntity(meta)) {
                                        // this.level.setBlockToAir(X + i, Y - j, Z + k);
                                        // 破壊しないようにしてみる
                                    } else if (block.getMaterial() == Material.water
                                        || block.getMaterial() == Material.lava
                                        || block instanceof LiquidBlock) {
                                            // 液体は消去
                                            this.level.setBlockToAir(X + i, Y - j, Z + k);
                                        } else {
                                            // 非固形Blockの破壊はコンフィグで可否を設定
                                            if (!block.renderAsNormalBlock() || DCsConfig.completeFearMelon) {
                                                ItemStack drop = new ItemStack(block, 1, meta);
                                                this.level.setBlockToAir(X + i, Y - j, Z + k);
                                                this.entityDropItem(drop, 1.0F);
                                            } else {
                                                this.level.setBlockToAir(X + i, Y - j, Z + k);
                                            }
                                        }
                                }
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }

    }

    protected Item dropItem() {
        return Item.getItemFromBlock(DCsAppleMilk.silkyMelon);
    }

    
    @Override
    public void performHurtAnimation() {
        this.setForwardDirection(-this.getForwardDirection());
        this.setTimeSinceHit(10);
        this.setDamageTaken(this.getDamageTaken() * 11.0F);
    }

    @Override
    public boolean canBeCollidedWith() {
        return !this.isRemoved();
    }

    
    @Override
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
        if (this.field_70279_a) {
            this.boatPosRotationIncrements = par9 + 5;
        } else {
            double d3 = par1 - this.getX();
            double d4 = par3 - this.getY();
            double d5 = par5 - this.getZ();
            double d6 = d3 * d3 + d4 * d4 + d5 * d5;

            if (d6 <= 1.0D) {
                return;
            }

            this.boatPosRotationIncrements = 3;
        }

        this.boatX = par1;
        this.boatY = par3;
        this.boatZ = par5;
        this.boatYaw = par7;
        this.boatPitch = par8;
        this.motionX = this.velocityX;
        this.motionY = this.velocityY;
        this.motionZ = this.velocityZ;
    }

    
    @Override
    public void setVelocity(double par1, double par3, double par5) {
        this.velocityX = this.motionX = par1;
        this.velocityY = this.motionY = par3;
        this.velocityZ = this.motionZ = par5;
    }

    @Override
    public void tick() {
        super.tick();

        // 爆発の結果
        int exp = this.getExploded();
        if (this.getExploded() == 1) {
            this.discard();
        }
        if (exp > 1) {
            AMTLogger.debugInfo("current explode int :" + exp);
            this.level.spawnParticle(
                "hugeexplosion",
                this.getX(),
                this.getY() + 1.0D,
                this.getZ(),
                this.motionX,
                this.motionY,
                this.motionZ);
            exp--;
            this.setExploded(exp);
        }

        // 被ダメ計算
        if (this.getTimeSinceHit() > 0) {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }

        if (this.getDamageTaken() > 0.0F) {
            this.setDamageTaken(this.getDamageTaken() - 1.0F);
        }

        this.xo = this.getX();
        this.yo = this.getY();
        this.zo = this.getZ();
        byte b0 = 5;
        double d0 = 0.0D;

        // 当たり判定が水中にあるか？
        for (int i = 0; i < b0; ++i) {
            double d1 = this.getBoundingBox().minY + (this.getBoundingBox().maxY - this.getBoundingBox().minY) * (i + 0) / b0 - 0.125D;
            double d2 = this.getBoundingBox().minY + (this.getBoundingBox().maxY - this.getBoundingBox().minY) * (i + 1) / b0 - 0.125D;
            AABB axisalignedbb = AABB.getBoundingBox(
                this.getBoundingBox().minX,
                d1,
                this.getBoundingBox().minZ,
                this.getBoundingBox().maxX,
                d2,
                this.getBoundingBox().maxZ);

            // 浮力
            if (this.level.isAABBInMaterial(axisalignedbb, Material.water)) {
                d0 += 1.0D / b0;
            }
        }

        double d3 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        double d4;
        double d5;

        // 水しぶき生成
        if (d3 > 0.26249999999999996D && this.inWater) {
            d4 = Math.cos(this.yRot * Math.PI / 180.0D);
            d5 = Math.sin(this.yRot * Math.PI / 180.0D);

            for (int j = 0; j < 1.0D + d3 * 60.0D; ++j) {
                double d6 = this.rand.nextFloat() * 2.0F - 1.0F;
                double d7 = (this.rand.nextInt(2) * 2 - 1) * 0.7D;
                double d8;
                double d9;

                if (this.rand.nextBoolean()) {
                    d8 = this.getX() - d4 * d6 * 0.8D + d5 * d7;
                    d9 = this.getZ() - d5 * d6 * 0.8D - d4 * d7;
                    this.level
                        .spawnParticle("splash", d8, this.getY() - 0.125D, d9, this.motionX, this.motionY, this.motionZ);
                } else {
                    d8 = this.getX() + d4 + d5 * d6 * 0.7D;
                    d9 = this.getZ() + d5 - d4 * d6 * 0.7D;
                    this.level
                        .spawnParticle("splash", d8, this.getY() - 0.125D, d9, this.motionX, this.motionY, this.motionZ);
                }
            }
        }

        double d10;
        double d11;

        // 向きと加速度の変化？
        if (this.level.isClientSide && this.field_70279_a) {
            if (this.boatPosRotationIncrements > 0) {
                d4 = this.getX() + (this.boatX - this.getX()) / this.boatPosRotationIncrements;
                d5 = this.getY() + (this.boatY - this.getY()) / this.boatPosRotationIncrements;
                d11 = this.getZ() + (this.boatZ - this.getZ()) / this.boatPosRotationIncrements;
                d10 = Mth.wrapAngleTo180_double(this.boatYaw - this.yRot);
                this.yRot = (float) (this.yRot + d10 / this.boatPosRotationIncrements);
                this.xRot = (float) (this.xRot
                    + (this.boatPitch - this.xRot) / this.boatPosRotationIncrements);
                --this.boatPosRotationIncrements;
                this.setPosition(d4, d5, d11);
                this.setRotation(this.yRot, this.xRot);
            } else {
                d4 = this.getX() + this.motionX;
                d5 = this.getY() + this.motionY;
                d11 = this.getZ() + this.motionZ;
                this.setPosition(d4, d5, d11);

                if (this.onGround) {
                    this.motionX *= 0.5D;
                    this.motionY *= 0.5D;
                    this.motionZ *= 0.5D;
                }

                this.motionX *= 0.9900000095367432D;
                this.motionY *= 0.949999988079071D;
                this.motionZ *= 0.9900000095367432D;
            }
        } else {
            // 浮力によるY速度調整
            if (d0 < 1.0D) {
                d4 = d0 * 2.0D - 1.0D;
                this.motionY += 0.03999999910593033D * d4;
            } else {
                if (this.motionY < 0.0D) {
                    this.motionY /= 2.0D;
                }

                this.motionY += 0.007000000216066837D;
            }

            // 乗っているEntityの前進速度
            if (this.vehicle != null && this.vehicle instanceof EntityLivingBase) {
                d4 = ((EntityLivingBase) this.vehicle).moveForward;

                if (d4 > 0.0D) {
                    d5 = -Math.sin(this.vehicle.yRot * (float) Math.PI / 180.0F);
                    d11 = Math.cos(this.vehicle.yRot * (float) Math.PI / 180.0F);
                    this.motionX += d5 * this.speedMultiplier * 0.05000000074505806D;
                    this.motionZ += d11 * this.speedMultiplier * 0.05000000074505806D;
                }
            }

            d4 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            double speedLimit = 0.35D;
            int speedMag = 0;

            if (this.vehicle != null && this.vehicle instanceof EntityLivingBase) {
                EntityLivingBase living = (EntityLivingBase) this.vehicle;
                if (living.isPotionActive(Potion.moveSpeed)) {
                    speedMag = living.getActivePotionEffect(Potion.moveSpeed)
                        .getAmplifier() + 1;
                }
            }

            if (speedMag > 0) {
                speedLimit = 0.35D + 0.1D * speedMag;
            }

            // 速度制限
            if (d4 > speedLimit) {
                d5 = speedLimit / d4;
                this.motionX *= d5;
                this.motionZ *= d5;
                d4 = speedLimit;
            }

            // Entityの前進速度を加味した速度（speedMultipler）の加算
            if (d4 > d3 && this.speedMultiplier < speedLimit) {
                this.speedMultiplier += (speedLimit - this.speedMultiplier) / (speedLimit * 100);

                if (this.speedMultiplier > speedLimit) {
                    this.speedMultiplier = speedLimit;
                }
            } else {
                this.speedMultiplier -= (this.speedMultiplier - 0.07D) / (speedLimit * 100);

                if (this.speedMultiplier < 0.07D) {
                    this.speedMultiplier = 0.07D;
                }
            }

            // 地上では止まっている
            if (this.onGround) {
                this.motionX *= 0.5D;
                this.motionY *= 0.5D;
                this.motionZ *= 0.5D;
            }

            // 最終的にEntityを動かす
            this.moveEntity(this.motionX, this.motionY, this.motionZ);

            // 衝突時に壊れるか
            if (this.isCollidedHorizontally && d3 > 0.3D) {
                // 速度0.30D以上だとドロップ化・実績解除
                this.onCrash();
            } else {
                this.motionX *= 0.9900000095367432D;
                this.motionY *= 0.949999988079071D;
                this.motionZ *= 0.9900000095367432D;
            }

            // また向きを調整している？
            this.xRot = 0.0F;
            d5 = this.yRot;
            d11 = this.xo - this.getX();
            d10 = this.zo - this.getZ();

            if (d11 * d11 + d10 * d10 > 0.001D) {
                d5 = ((float) (Math.atan2(d10, d11) * 180.0D / Math.PI));
            }

            double d12 = Mth.wrapAngleTo180_double(d5 - this.yRot);

            if (d12 > 20.0D) {
                d12 = 20.0D;
            }

            if (d12 < -20.0D) {
                d12 = -20.0D;
            }

            this.yRot = (float) (this.yRot + d12);
            this.setRotation(this.yRot, this.xRot);

            // 当たり判定が乗り物の分拡大されているぽい
            if (!this.level.isClientSide) {
                List list = this.level.getEntitiesWithinAABBExcludingEntity(
                    this,
                    this.getBoundingBox().expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
                int l;

                if (list != null && !list.isEmpty()) {
                    for (l = 0; l < list.size(); ++l) {
                        Entity entity = (Entity) list.get(l);

                        if (entity != this.vehicle && entity.canBePushed()
                            && entity instanceof EntityMelonBomb) {
                            entity.applyEntityCollision(this);
                        }
                    }
                }

                // 雪と睡蓮を破壊
                for (l = 0; l < 4; ++l) {
                    int i1 = Mth.floor_double(this.getX() + (l % 2 - 0.5D) * 0.8D);
                    int j1 = Mth.floor_double(this.getZ() + (l / 2 - 0.5D) * 0.8D);

                    for (int k1 = 0; k1 < 2; ++k1) {
                        int l1 = Mth.floor_double(this.getY()) + k1;
                        Block i2 = this.level.getBlock(i1, l1, j1);

                        if (i2 == Blocks.snow) {
                            this.level.setBlockToAir(i1, l1, j1);
                        } else if (i2 == Blocks.waterlily) {
                            this.level.func_147480_a(i1, l1, j1, true);
                        }
                    }
                }

                // 乗っているEntityが死んだら騎乗を解除
                if (this.vehicle != null && this.vehicle.isRemoved()) {
                    this.vehicle = null;
                }
            }
        }
    }

    // 乗っているEntityの位置調整
    @Override
    public void updateRiderPosition() {
        if (this.vehicle != null) {
            double d0 = Math.cos(this.yRot * Math.PI / 180.0D) * 0.4D;
            double d1 = Math.sin(this.yRot * Math.PI / 180.0D) * 0.4D;
            this.vehicle.setPosition(
                this.getX() + d0,
                this.getY() + this.getMountedYOffset() + this.vehicle.getYOffset(),
                this.getZ() + d1);
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag par1CompoundTag) {}

    @Override
    protected void readAdditionalSaveData(CompoundTag par1CompoundTag) {}

    
    @Override
    public float getShadowSize() {
        return 0.0F;
    }

    @Override
    public boolean interactFirst(EntityPlayer par1EntityPlayer) {
        // 他人が乗っている場合は乗れない
        ItemStack item = par1EntityPlayer.inventory.getCurrentItem();
        if (item != null && item.getItem() == DCsAppleMilk.chopsticks) {
            if (this.vehicle != null && this.vehicle instanceof EntityPlayer
                && this.vehicle != par1EntityPlayer) {
                return true;
            } else {
                if (!this.level.isClientSide) {
                    par1EntityPlayer.startRiding(this);
                }

                return true;
            }
        } else if (item != null
            && (item.getItem() == DCsAppleMilk.firestarter || item.getItem() instanceof ItemFlintAndSteel)) {
                if (item.attemptDamageItem(1, this.level.rand)) {
                    item.stackSize = 0;
                }

                par1EntityPlayer.triggerAchievement(AchievementRegister.useSilkMelon);
                if (!this.level.isClientSide) this.explode(par1EntityPlayer);
                return true;
            } else if (item != null && item.getItem()
                .getItemUseAction(item) == EnumAction.bow) {
                    return false;
                } else {
                    if (!par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(this.dropItem(), 1))) {
                        if (!level.isClientSide)
                            par1EntityPlayer.entityDropItem(new ItemStack(this.dropItem(), 1), 1.0F);
                    }

                    this.discard();
                    this.level.playSoundAtEntity(par1EntityPlayer, "random.pop", 0.4F, 1.8F);
                    return true;
                }

    }

    public void setDamageTaken(float par1) {
        this.entityData.set(19, Float.valueOf(par1));
    }

    public float getDamageTaken() {
        return this.entityData.get(19);
    }

    public void setTimeSinceHit(int par1) {
        this.entityData.set(17, Integer.valueOf(par1));
    }

    public int getTimeSinceHit() {
        return this.entityData.get(17);
    }

    public void setForwardDirection(int par1) {
        this.entityData.set(18, Integer.valueOf(par1));
    }

    public int getForwardDirection() {
        return this.entityData.get(18);
    }

    public void setExploded(int par1) {
        this.entityData.set(20, Integer.valueOf(par1));
    }

    public int getExploded() {
        return this.entityData.get(20);
    }

    
    public void func_70270_d(boolean par1) {
        this.field_70279_a = par1;
    }

    protected void onCrash() {
        if (!this.level.isClientSide && !this.isRemoved()) {
            EntityLivingBase rider = null;
            if (this.vehicle != null && this.vehicle instanceof EntityLivingBase) {
                rider = (EntityLivingBase) this.vehicle;
            }
            this.discard();
            this.explode(rider);
        }
    }
}
