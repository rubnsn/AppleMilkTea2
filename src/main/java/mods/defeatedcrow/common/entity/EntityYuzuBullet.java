package mods.defeatedcrow.common.entity;

import net.minecraft.world.level.Level;

import java.util.List;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
// Material removed in 1.20.1 - use BlockState properties
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.nbt.CompoundTag;
// S2BPacketChangeGameState removed - use ClientboundGameEventPacket
import net.minecraft.world.phys.AABB;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.Level;

import mods.defeatedcrow.common.config.DCsConfig;

public class EntityYuzuBullet extends Entity implements Projectile {

    /* 地中判定に使うもの */
    protected int xTile = -1;
    protected int yTile = -1;
    protected int zTile = -1;
    protected Block inTile;
    protected int inData;
    protected boolean inGround;

    /* この弾を撃ったエンティティ */
    public LivingEntity shootingEntity;

    /* 地中・空中にいる時間 */
    protected int ticksInGround;
    protected int ticksInAir;
    protected int livingTimeCount = 0;

    /* ダメージの大きさ */
    protected double damage = 5.0D;

    /* ノックバックの大きさ */
    protected int knockbackStrength = 1;

    public EntityYuzuBullet(Level par1World) {
        super(type, level);
        this.renderDistanceWeight = 10.0D;
        this.setSize(0.5F, 0.5F);
        this.damage = 5.0D;
    }

    public EntityYuzuBullet(Level par1World, LivingEntity par2EntityLivingBase, float speed, float speed2,
        float adjustX, float adjustZ, float adjustY) {
        super(type, level);
        this.renderDistanceWeight = 10.0D;
        this.shootingEntity = par2EntityLivingBase;
        this.yOffset = 0.0F;
        this.setSize(0.5F, 0.5F);

        // 初期状態での向きの決定
        this.setLocationAndAngles(
            par2EntityLivingBase.getX(),
            par2EntityLivingBase.getY() + (double) par2EntityLivingBase.getEyeHeight(),
            par2EntityLivingBase.getZ(),
            par2EntityLivingBase.yRot,
            par2EntityLivingBase.xRot);

        // 位置の調整
        this.getX() += -(double) (Mth.sin(this.yRot / 180.0F * (float) Math.PI) * (1.0F + adjustZ))
            - (double) (Mth.cos(this.yRot / 180.0F * (float) Math.PI) * adjustX);
        this.getY() += 0.05000000149011612D + adjustY;
        this.getZ() += (double) (Mth.cos(this.yRot / 180.0F * (float) Math.PI) * (1.0F + adjustZ))
            - (double) (Mth.sin(this.yRot / 180.0F * (float) Math.PI) * adjustX);
        this.setPosition(this.getX(), this.getY(), this.getZ());

        // 初速度
        this.motionX = ((double) (-Mth.sin(this.yRot / 180.0F * (float) Math.PI)
            * Mth.cos(this.xRot / 180.0F * (float) Math.PI)));
        this.motionZ = ((double) (Mth.cos(this.yRot / 180.0F * (float) Math.PI)
            * Mth.cos(this.xRot / 180.0F * (float) Math.PI)));
        this.motionY = ((double) (-Mth.sin(this.xRot / 180.0F * (float) Math.PI)));
        this.setThrowableHeading(this.motionX * speed, this.motionY * speed, this.motionZ * speed, speed, speed2);
    }

    protected void defineSynchedData() {}

    public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8) {
        float f2 = Mth.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= (double) f2;
        par3 /= (double) f2;
        par5 /= (double) f2;
        par1 += this.rand.nextGaussian() * (double) (this.rand.nextBoolean() ? -1 : 1)
            * 0.007499999832361937D
            * (double) par8;
        par3 += this.rand.nextGaussian() * (double) (this.rand.nextBoolean() ? -1 : 1)
            * 0.007499999832361937D
            * (double) par8;
        par5 += this.rand.nextGaussian() * (double) (this.rand.nextBoolean() ? -1 : 1)
            * 0.007499999832361937D
            * (double) par8;
        par1 *= (double) par7;
        par3 *= (double) par7;
        par5 *= (double) par7;
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;
        float f3 = Mth.sqrt_double(par1 * par1 + par5 * par5);
        this.prevRotationYaw = this.yRot = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
        this.prevRotationPitch = this.xRot = (float) (Math.atan2(par3, (double) f3) * 180.0D / Math.PI);
        this.ticksInGround = 0;
    }

    
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
        this.setPosition(par1, par3, par5);
        this.setRotation(par7, par8);
    }

    
    public void setVelocity(double par1, double par3, double par5) {
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = Mth.sqrt_double(par1 * par1 + par5 * par5);
            this.prevRotationYaw = this.yRot = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
            this.prevRotationPitch = this.xRot = (float) (Math.atan2(par3, (double) f) * 180.0D / Math.PI);
            this.prevRotationPitch = this.xRot;
            this.prevRotationYaw = this.yRot;
            this.setLocationAndAngles(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
            this.ticksInGround = 0;
        }
    }

    @Override
    public void tick() {
        super.tick();

        livingTimeCount++;
        if (livingTimeCount > 300) this.discard();

        double dx = -(double) (Mth.sin(this.yRot / 180.0F * (float) Math.PI));
        double dz = -(double) (Mth.cos(this.yRot / 180.0F * (float) Math.PI));
        for (int i = 0; i < 4; ++i) {
            this.level.spawnParticle(
                "crit",
                this.getX() + dx,
                this.getY(),
                this.getZ() + dz,
                -this.motionX,
                -this.motionY + 0.2D,
                -this.motionZ);
        }

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = Mth.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.yRot = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D
                / Math.PI);
            this.prevRotationPitch = this.xRot = (float) (Math.atan2(this.motionY, (double) f) * 180.0D
                / Math.PI);
        }

        Block i = this.level.getBlock(this.xTile, this.yTile, this.zTile);
        boolean air = this.level.isAirBlock(xTile, yTile, zTile);

        if (i != null && i.getMaterial() != /*Material*/ air) {
            i.setBlockBoundsBasedOnState(this.level, this.xTile, this.yTile, this.zTile);
            AABB axisalignedbb = i
                .getCollisionBoundingBoxFromPool(this.level, this.xTile, this.yTile, this.zTile);

            // 当たり判定に接触しているかどうか
            if (axisalignedbb != null
                && axisalignedbb.isVecInside(Vec3.createVectorHelper(this.getX(), this.getY(), this.getZ()))) {
                this.inGround = true;
            }
        }

        if (this.inGround) {
            Block j = this.level.getBlock(this.xTile, this.yTile, this.zTile);
            int k = this.level.getBlockMetadata(this.xTile, this.yTile, this.zTile);

            /*
             * 前のTickに確認した埋まりブロックのIDとメタを照合している。違ったら埋まり状態を解除、一致したら埋まり状態を継続。
             * /* 埋まり状態2tick継続でこのエンティティを消す
             */
            if (j == this.inTile && k == this.inData) {
                ++this.ticksInGround;

                int limit = 2;

                if (this.ticksInGround > limit) {
                    this.discard();
                }
            } else// 埋まり状態の解除処理
            {
                this.inGround = false;
                this.motionX *= (double) (this.rand.nextFloat() * 0.1F);
                this.motionY *= (double) (this.rand.nextFloat() * 0.1F);
                this.motionZ *= (double) (this.rand.nextFloat() * 0.1F);
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        } else// 埋まってない時。速度の更新。
        {
            ++this.ticksInAir;
            // ブロックとの衝突判定
            Vec3 vec3 = Vec3.createVectorHelper(this.getX(), this.getY(), this.getZ());
            Vec3 vec31 = Vec3
                .createVectorHelper(this.getX() + this.motionX, this.getY() + this.motionY, this.getZ() + this.motionZ);
            BlockHitResult movingobjectposition = this.level.func_147447_a(vec3, vec31, false, true, false);
            vec3 = Vec3.createVectorHelper(this.getX(), this.getY(), this.getZ());
            vec31 = Vec3
                .createVectorHelper(this.getX() + this.motionX, this.getY() + this.motionY, this.getZ() + this.motionZ);

            // ブロックに当たった
            if (movingobjectposition != null) {
                vec31 = Vec3.createVectorHelper(
                    movingobjectposition.hitVec.xCoord,
                    movingobjectposition.hitVec.yCoord,
                    movingobjectposition.hitVec.zCoord);
            }

            List list = this.level.getEntitiesWithinAABBExcludingEntity(
                this,
                this.getBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ)
                    .expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            int l;
            float f1;

            BlockHitResult entityTarget = null;

            for (l = 0; l < list.size(); ++l) {
                Entity entity1 = (Entity) list.get(l);
                Entity entity = null;

                // ターゲットの場合
                if (entity1 instanceof LivingEntity) {
                    f1 = 0.3F;
                    AABB axisalignedbb1 = entity1.getBoundingBox().expand((double) f1, (double) f1, (double) f1);
                    BlockHitResult movingobjectposition1 = axisalignedbb1.calculateIntercept(vec3, vec31);

                    if (movingobjectposition1 != null) {
                        double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

                        if (d1 < d0 || d0 == 0.0D) {
                            if (entity1 != null) {
                                entityTarget = new BlockHitResult(entity1);
                                d0 = d1;
                                break;
                            }
                        }
                    }
                }
            }

            /*
             * 当たったエンティティそれそれについての判定部分。
             * ここで特定の種類のエンティティに当たらないようにできる。
             */
            boolean canAttack = false;
            if (entityTarget != null) {
                Entity target = entityTarget.entityHit;

                if (target instanceof Player) {
                    // プレイヤーに当たった時
                    Player entityplayer = (Player) target;

                    if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof Player
                        && !((Player) this.shootingEntity).canAttackPlayer(entityplayer)) {
                        // PvPが許可されていないと当たらない
                        canAttack = false;
                    } else if (entityplayer == this.shootingEntity) {
                        // 対象が撃った本人の場合も当たらない
                        canAttack = false;
                    } else if (DCsConfig.PvPProhibitionMode && entityplayer instanceof Player) {
                        canAttack = false;
                    }
                } else {
                    canAttack = true;
                }
            }

            float f2;
            float f3;

            // 当たったあとの処理
            // まずはリストから
            if (this.livingTimeCount > 1 && canAttack) {
                Entity target = entityTarget.entityHit;

                int i1 = Mth.ceiling_double_int(1.0D * this.damage);
                // 0~2程度の乱数値を上乗せ
                i1 += this.rand.nextInt(3);

                if (target.isImmuneToFire() && this.isBurning()) {
                    i1 = 0;
                }

                DamageSource damagesource = null;

                // 別メソッドでダメージソースを確認
                damagesource = this.thisDamageSource(this.shootingEntity);

                if (target instanceof Projectile) {
                    // 対象が矢などの飛翔Entityの場合、打ち消すことが出来る
                    target.discard();
                } else {
                    // ダメージを与える処理を呼ぶ
                    if (target.attackEntityFrom(damagesource, (float) i1)) {
                        // ダメージを与えることに成功したら以下の処理を行う
                        if (target instanceof LivingEntity) {
                            LivingEntity entitylivingbase = (LivingEntity) target;

                            // 無敵時間はなし
                            target.hurtResistantTime = 0;

                            // マルチプレイ時に、両者がプレイヤーだった時のパケット送信処理
                            if (this.shootingEntity != null && target != this.shootingEntity
                                && target instanceof Player
                                && this.shootingEntity instanceof ServerPlayer) {
                                ((ServerPlayer) this.shootingEntity).playerNetServerHandler
                                    .sendPacket(new S2BPacketChangeGameState(6, 0.0F));
                            }
                        }

                        // ここでヒット時の効果音がなる
                        this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

                        // 当たったあと、弾を消去する。エンティティ貫通がONの弾種はそのまま残す。
                        if (!(target instanceof EnderMan)) {
                            this.discard();
                        }
                    }
                }
            }

            if (movingobjectposition != null)// blockのみ
            {
                this.xTile = movingobjectposition.blockX;
                this.yTile = movingobjectposition.blockY;
                this.zTile = movingobjectposition.blockZ;
                this.inTile = this.level.getBlock(this.xTile, this.yTile, this.zTile);
                this.inData = this.level.getBlockMetadata(this.xTile, this.yTile, this.zTile);
                this.motionX = (double) ((float) (movingobjectposition.hitVec.xCoord - this.getX()));
                this.motionY = (double) ((float) (movingobjectposition.hitVec.yCoord - this.getY()));
                this.motionZ = (double) ((float) (movingobjectposition.hitVec.zCoord - this.getZ()));
                f2 = Mth.sqrt_double(
                    this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                this.getX() -= this.motionX / (double) f2 * 0.05000000074505806D;
                this.getY() -= this.motionY / (double) f2 * 0.05000000074505806D;
                this.getZ() -= this.motionZ / (double) f2 * 0.05000000074505806D;
                this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                this.inGround = true;

                if (this.inTile != null) {
                    // Block側に衝突を伝える
                    this.inTile.onEntityCollidedWithBlock(this.level, this.xTile, this.yTile, this.zTile, this);

                    // 燃えている時
                    if (this.isBurning()) {
                        if (this.inTile == Blocks.ice) this.level.setBlock(xTile, yTile, zTile, Blocks.water);
                        if (this.inTile.getMaterial() == /*Material*/ snow) {
                            this.level.setBlockToAir(xTile, yTile, zTile);
                            this.discard();
                        }
                    }
                }
            }
        }

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = Mth.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.yRot = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D
                / Math.PI);
            this.prevRotationPitch = this.xRot = (float) (Math.atan2(this.motionY, (double) f) * 180.0D
                / Math.PI);
        }

        // 改めてポジションに速度を加算。向きも更新。
        this.getX() += this.motionX;
        this.getY() += this.motionY;
        this.getZ() += this.motionZ;
        float f2 = Mth.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.yRot = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
        this.xRot = (float) (Math.atan2(this.motionY, (double) f2) * 180.0D / Math.PI);

        while (this.xRot - this.prevRotationPitch < -180.0F) {
            this.prevRotationPitch -= 360.0F;
        }

        while (this.xRot - this.prevRotationPitch >= 180.0F) {
            this.prevRotationPitch += 360.0F;
        }

        while (this.yRot - this.prevRotationYaw < -180.0F) {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.yRot - this.prevRotationYaw >= 180.0F) {
            this.prevRotationYaw += 360.0F;
        }

        this.xRot = this.prevRotationPitch + (this.xRot - this.prevRotationPitch) * 0.2F;
        this.yRot = this.prevRotationYaw + (this.yRot - this.prevRotationYaw) * 0.2F;

        // 水中に有る
        if (this.isInWater()) {
            if (this.isBurning()) {
                this.extinguish();
            }

            // 泡パーティクルが出る
            for (int j1 = 0; j1 < 4; ++j1) {
                float f3 = 0.25F;
                this.level.spawnParticle(
                    "bubble",
                    this.getX() - this.motionX * (double) f3,
                    this.getY() - this.motionY * (double) f3,
                    this.getZ() - this.motionZ * (double) f3,
                    this.motionX,
                    this.motionY,
                    this.motionZ);
            }
        }

        this.setPosition(this.getX(), this.getY(), this.getZ());
        this.func_145775_I();
    }

    public void addAdditionalSaveData(CompoundTag par1CompoundTag) {
        par1CompoundTag.setShort("xTile", (short) this.xTile);
        par1CompoundTag.setShort("yTile", (short) this.yTile);
        par1CompoundTag.setShort("zTile", (short) this.zTile);
        par1CompoundTag.setByte("inTile", (byte) (byte) Block.getIdFromBlock(this.inTile));
        par1CompoundTag.setByte("inData", (byte) this.inData);
        par1CompoundTag.setByte("inGround", (byte) (this.inGround ? 1 : 0));
        par1CompoundTag.setDouble("damage", this.damage);
    }

    public void readAdditionalSaveData(CompoundTag par1CompoundTag) {
        this.xTile = par1CompoundTag.getShort("xTile");
        this.yTile = par1CompoundTag.getShort("yTile");
        this.zTile = par1CompoundTag.getShort("zTile");
        this.inTile = Block.getBlockById(par1CompoundTag.getByte("inTile") & 255);
        this.inData = par1CompoundTag.getByte("inData") & 255;
        this.inGround = par1CompoundTag.getByte("inGround") == 1;

        if (par1CompoundTag.hasKey("damage")) {
            this.damage = par1CompoundTag.getDouble("damage");
        }
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    
    public float getShadowSize() {
        return 0.0F;
    }

    public void setDamage(double par1) {
        this.damage = par1;
    }

    public double getDamage() {
        return this.damage;
    }

    public void setKnockbackStrength(int par1) {
        this.knockbackStrength = par1;
    }

    public boolean canAttackWithItem() {
        return false;
    }

    /* 落下速度 */
    public float fallSpeed() {
        return 0.0F;
    }

    /* ダメージソースのタイプ */
    public DamageSource thisDamageSource(Entity entity) {
        // 発射元のEntityがnullだった場合の対策を含む。
        if (this.isBurning()) {
            return DamageSource.lava;
        } else {
            return entity != null ? EntityDamageSource.causeIndirectMagicDamage(entity, this) : DamageSource.magic;
        }
    }

}
