package mods.defeatedcrow.common.entity;

import net.minecraft.world.level.Level;

import java.util.List;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
// Material removed in 1.20.1 - use BlockState properties
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
// net.minecraft.world.entity.boss.enderdragon.EnderDragonPart removed
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
// S2BPacketChangeGameState removed - use ClientboundGameEventPacket
import net.minecraft.world.phys.AABB;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.Level;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.handler.CustomExplosion;

/*
 * このエンティティの動作
 * 射出時は重力落下するだけのエンティティで、頭の向きはプレイヤー方向、適当な方向に飛び出す。
 * 生成時にターゲットは決めておく。
 * 射出後、一定時間でモードが切り替わり、ターゲットに対して定期的に進行方向を修正するゆるい追尾弾になる。
 * 同時に羽を開いたり、尾部の炎が光るためDataWatcherを使用。
 * 着弾後、接触したエンティティには物理ダメージを与え、少し宙に浮かせて、その後爆発する。
 * 爆発はCustomExplosionを使用。対空特効のため浮かせたモブに大ダメージを与える。
 * 水中でも爆発できる。水中減速もなし。シーラカンスの得意技なので水中での挙動は優遇気味に。
 */

/*
 * 発射されるエンティティのクラス。
 */
public class EntityAnchorMissile extends Entity implements Projectile {

    /* 地中判定に使うもの */
    protected int xTile = -1;
    protected int yTile = -1;
    protected int zTile = -1;
    protected Block inTile;
    protected int inData;
    protected boolean inGround;

    /* この弾を撃ったエンティティ */
    public LivingEntity shootingEntity;

    /* ターゲットエンティティ */
    public LivingEntity targetEntity;

    /* 地中・空中にいる時間 */
    protected int ticksInGround;
    protected int ticksInAir;
    protected int livingTimeCount = 0;

    /* 軌道修正のクールタイム */
    protected int coolingtime = 4;

    /* ダメージの大きさ */
    protected double damage = 35.0D;

    /* ノックバックの大きさ */
    protected int knockbackStrength = 1;

    /* 起動しているかどうか */
    protected boolean active = false;

    public EntityAnchorMissile(Level par1World) {
        super(type, level);
        this.renderDistanceWeight = 10.0D;
        this.setSize(0.5F, 0.5F);
        this.damage = 50.0D;
    }

    /**
     * 発射する弾を生成・初期パラメータの定義をする。
     * 
     * @param par1World
     *                             :このワールド
     * @param par2EntityLivingBase
     *                             :弾源となるエンティティ。このModの場合、弾を撃ったプレイヤーがここに入る
     * @param par3EntityLivingBase
     *                             :ターゲットのエンティティ。生成前に、予めAABBなどを使ってターゲットを得ておくこと
     * @param speed
     *                             :弾の速度計算に使われる値
     * @param speed2
     *                             :弾の速度計算に使われる値2
     * @param initialYaw
     *                             :弾の初期射出方向
     */
    public EntityAnchorMissile(Level par1World, LivingEntity par2EntityLivingBase,
        LivingEntity par3EntityLivingBase, float speed, float speed2, float initialYaw, float adjustX,
        float adjustY, float adjustZ) {
        super(type, level);
        this.renderDistanceWeight = 10.0D;
        this.shootingEntity = par2EntityLivingBase;
        this.targetEntity = par3EntityLivingBase;
        this.yOffset = 0.0F;
        this.setSize(0.5F, 0.5F);
        this.damage = 50.0D;

        float initialPitch = 0.0F;

        // 初期状態での向きは射手の向きに依存する
        this.setLocationAndAngles(
            par2EntityLivingBase.getX(),
            par2EntityLivingBase.getY() + par2EntityLivingBase.getEyeHeight(),
            par2EntityLivingBase.getZ(),
            par2EntityLivingBase.yRot,
            par2EntityLivingBase.xRot);

        // 初速度
        this.getX() += -(double) (Mth.sin(this.yRot / 180.0F * (float) Math.PI) * (1.0F + adjustZ))
            - Mth.cos(this.yRot / 180.0F * (float) Math.PI) * adjustX;
        this.getY() += 0.05000000149011612D + adjustY;
        this.getZ() += (double) (Mth.cos(this.yRot / 180.0F * (float) Math.PI) * (1.0F + adjustZ))
            - (double) (Mth.sin(this.yRot / 180.0F * (float) Math.PI) * adjustX);
        this.setPosition(this.getX(), this.getY(), this.getZ());

        // 初速度
        this.motionX = -Mth.sin(this.yRot / 180.0F * (float) Math.PI)
            * Mth.cos(this.xRot / 180.0F * (float) Math.PI);
        this.motionZ = Mth.cos(this.yRot / 180.0F * (float) Math.PI)
            * Mth.cos(this.xRot / 180.0F * (float) Math.PI);
        this.motionY = (-Mth.sin(this.xRot / 180.0F * (float) Math.PI));
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, speed * 1.5F, speed2);
    }

    /* dataWatcherを利用したサーバ・クライアント間の同期処理だと思う */
    @Override
    protected void defineSynchedData() {
        this.entityData.define(16, Byte.valueOf((byte) 0));
        this.entityData.define(DATA_ID_17, Byte.valueOf((byte) 0));
    }

    /*
     * IProjectileで実装が必要なメソッド。
     * ディスペンサーによる発射メソッドなどで使用されている。
     */
    @Override
    public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8) {
        float f2 = Mth.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= f2;
        par3 /= f2;
        par5 /= f2;
        par1 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
        par3 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
        par5 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
        par1 *= par7;
        par3 *= par7;
        par5 *= par7;
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;
        float f3 = Mth.sqrt_double(par1 * par1 + par5 * par5);
        this.prevRotationYaw = this.yRot = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
        this.prevRotationPitch = this.xRot = (float) (Math.atan2(par3, f3) * 180.0D / Math.PI);
        this.ticksInGround = 0;
    }

    @Override
    
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
        this.setPosition(par1, par3, par5);
        this.setRotation(par7, par8);
    }

    @Override
    
    public void setVelocity(double par1, double par3, double par5) {
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = Mth.sqrt_double(par1 * par1 + par5 * par5);
            this.prevRotationYaw = this.yRot = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
            this.prevRotationPitch = this.xRot = (float) (Math.atan2(par3, f) * 180.0D / Math.PI);
            this.prevRotationPitch = this.xRot;
            this.prevRotationYaw = this.yRot;
            this.setLocationAndAngles(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
            this.ticksInGround = 0;
        }
    }

    /*
     * Tick毎に呼ばれる更新処理。
     * 速度の更新、衝突判定などをここで行う。
     */
    @Override
    public void tick() {
        super.tick();

        livingTimeCount++;

        // その1、爆発処理
        byte exp = this.isExploded();
        if (exp == 1) {
            this.discard();
        } else if (exp > 1) {
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
            this.setTimeCount(exp);
        }

        // その2、アクティブか否か
        if (livingTimeCount > 3 && !this.active) {
            this.active = true;
            this.setActive((byte) 1);
            this.playSound("defeatedcrow:knock", 0.5F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
        }

        boolean explode = false;

        // 以降、アクティブか否かで動作が変わる。
        if (this.active) {
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
        }

        // 直前のパラメータと新パラメータを一致させているところ。
        // また、速度に応じてエンティティの向きを調整し、常に進行方向に前面が向くようにしている。
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = Mth.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.yRot = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D
                / Math.PI);
            this.prevRotationPitch = this.xRot = (float) (Math.atan2(this.motionY, f) * 180.0D / Math.PI);
        }

        // 激突したブロックを確認している
        Block i = this.level.getBlock(this.xTile, this.yTile, this.zTile);
        boolean air = this.level.isAirBlock(xTile, yTile, zTile);

        // 空気じゃないブロックに当たった&ブロック貫通エンティティでない時
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

        // 空気じゃないブロックに当たった
        if (this.inGround) {
            Block j = this.level.getBlock(this.xTile, this.yTile, this.zTile);
            int k = this.level.getBlockMetadata(this.xTile, this.yTile, this.zTile);

            /*
             * 前のTickに確認した埋まりブロックのIDとメタを照合している。違ったら埋まり状態を解除、一致したら埋まり状態を継続。
             * /* 埋まり状態2tick継続でこのエンティティを消す
             */
            if (j == this.inTile && k == this.inData) {
                ++this.ticksInGround;
                // ブロック貫通の場合、20tick（1秒間）はブロック中にあっても消えないようになる。
                int limit = 2;

                if (this.ticksInGround > limit) {
                    explode = true;
                }
            } else// 埋まり状態の解除処理
            {
                this.inGround = false;
                this.motionX *= this.rand.nextFloat() * 0.1F;
                this.motionY *= this.rand.nextFloat() * 0.1F;
                this.motionZ *= this.rand.nextFloat() * 0.1F;
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

            // Entityとの衝突判定。
            List list = this.level.getEntitiesWithinAABBExcludingEntity(
                this,
                this.getBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ)
                    .expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            int l;
            float f1;

            BlockHitResult entityTarget = null;

            // 1ブロック分の範囲内にいるエンティティ全てに対して繰り返す
            for (l = 0; l < list.size(); ++l) {
                Entity entity1 = (Entity) list.get(l);
                Entity entity = null;

                // ターゲットの場合
                if (entity1 instanceof LivingEntity || entity1 instanceof net.minecraft.world.entity.boss.enderdragon.EnderDragonPart) {
                    f1 = 0.3F;
                    AABB axisalignedbb1 = entity1.getBoundingBox().expand(f1, f1, f1);
                    BlockHitResult movingobjectposition1 = axisalignedbb1.calculateIntercept(vec3, vec31);

                    if (movingobjectposition1 != null) {
                        double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

                        if (d1 < d0 || d0 == 0.0D) {
                            // arrowと異なり、あたったEntityすべてをリストに入れる
                            entityTarget = new BlockHitResult(entity1);
                            d0 = d1;
                            break;
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
                } else if (target instanceof TamableAnimal || target instanceof Horse) {
                    // 事故防止の為、TamableAnimal（犬や猫などのペット）、馬にも当たらないようにする
                    canAttack = false;
                } else {
                    canAttack = true;
                }
            }

            float f2;
            float f3;

            // 当たったあとの処理
            // まずはリストから
            if (canAttack) {
                Entity target = entityTarget.entityHit;

                int i1 = Mth.ceiling_double_int(1.0D * this.damage);
                // 0~2程度の乱数値を上乗せ
                i1 += this.rand.nextInt(3);

                DamageSource damagesource = null;

                // 別メソッドでダメージソースを確認
                damagesource = this.thisDamageSource(this.shootingEntity);

                // バニラ矢と同様、このエンティティが燃えているなら対象に着火することも出来る
                if (this.isBurning() && !(target instanceof EnderMan)) {
                    target.setFire(5);
                }

                else if (target instanceof Projectile) {
                    // 対象が矢などの飛翔Entityの場合、打ち消すことが出来る
                    target.discard();
                } else {
                    // ダメージを与える処理を呼ぶ
                    if (target.attackEntityFrom(damagesource, i1)) {
                        // ダメージを与えることに成功したら以下の処理を行う
                        if (target instanceof LivingEntity) {
                            LivingEntity entitylivingbase = (LivingEntity) target;

                            // ノックバック
                            if (this.knockbackStrength > 0) {
                                f3 = Mth.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

                                if (f3 > 0.0F) {
                                    // Y方向に大きめに打ち上げる
                                    target.addVelocity(
                                        this.motionX * this.knockbackStrength * 0.2000000238418579D / f3,
                                        0.3D,
                                        this.motionZ * this.knockbackStrength * 0.2000000238418579D / f3);
                                }
                            }

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
                        explode = true;
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
                this.motionX = ((float) (movingobjectposition.hitVec.xCoord - this.getX()));
                this.motionY = ((float) (movingobjectposition.hitVec.yCoord - this.getY()));
                this.motionZ = ((float) (movingobjectposition.hitVec.zCoord - this.getZ()));
                f2 = Mth.sqrt_double(
                    this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                this.getX() -= this.motionX / f2 * 0.05000000074505806D;
                this.getY() -= this.motionY / f2 * 0.05000000074505806D;
                this.getZ() -= this.motionZ / f2 * 0.05000000074505806D;
                this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                this.inGround = true;

                if (this.inTile != null) {
                    // Block側に衝突を伝える
                    this.inTile.onEntityCollidedWithBlock(this.level, this.xTile, this.yTile, this.zTile, this);
                }
            }
        }

        // さいごに爆発処理
        int live = (targetEntity != null && targetEntity.isEntityAlive()) ? 10 : 600;

        if (explode || this.livingTimeCount > live) {
            if (this.isExploded() == 0) {
                AMTLogger.debugInfo("explosion");
                this.setExplosion();
                if (!DCsConfig.disableMissileExplosion && !level.isClientSide) {
                    float f = 5.0F;
                    CustomExplosion explosion = new CustomExplosion(
                        level,
                        this,
                        shootingEntity,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        f,
                        CustomExplosion.Type.Anchor,
                        true);
                    explosion.doExplosion();
                }
            }

        }

        // 追尾
        if (active) {
            if (targetEntity != null && targetEntity.isEntityAlive()) {
                double dx = targetEntity.getX() - this.getX();
                double dy = targetEntity.getBoundingBox().minY + (targetEntity.height / 2.0D) - this.getY();
                double dz = targetEntity.getZ() - this.getZ();
                double d3 = Mth.sqrt_double(dx * dx + dz * dz);

                if (d3 >= 1.0E-7D) {
                    float f4 = (float) d3 * 0.2F;
                    double dy2 = dy + f4;

                    float ff = Mth.sqrt_double(dx * dx + dy2 * dy2 + dz * dz);
                    dx /= ff;
                    dy /= ff;
                    dz /= ff;

                    this.motionX = (this.motionX + dx) / 2;
                    this.motionY = (this.motionY + dy) / 2;
                    this.motionZ = (this.motionZ + dz) / 2;

                    this.motionX *= 1.25D;
                    this.motionY *= 1.25D;
                    this.motionZ *= 1.25D;
                }
            }
        }

        // 直前のパラメータと新パラメータを一致させているところ。
        // また、速度に応じてエンティティの向きを調整し、常に進行方向に前面が向くようにしている。
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = Mth.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.yRot = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D
                / Math.PI);
            this.prevRotationPitch = this.xRot = (float) (Math.atan2(this.motionY, f) * 180.0D / Math.PI);
        }

        // 改めてポジションに速度を加算。向きも更新。
        this.getX() += this.motionX;
        this.getY() += this.motionY;
        this.getZ() += this.motionZ;
        float f2 = Mth.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.yRot = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
        this.xRot = (float) (Math.atan2(this.motionY, f2) * 180.0D / Math.PI);

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
            // 泡パーティクルが出る
            for (int j1 = 0; j1 < 4; ++j1) {
                float f3 = 0.25F;
                this.level.spawnParticle(
                    "bubble",
                    this.getX() - this.motionX * f3,
                    this.getY() - this.motionY * f3,
                    this.getZ() - this.motionZ * f3,
                    this.motionX,
                    this.motionY,
                    this.motionZ);
            }
        }

        this.setPosition(this.getX(), this.getY(), this.getZ());
        this.func_145775_I();
    }

    /*
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void addAdditionalSaveData(CompoundTag par1CompoundTag) {
        par1CompoundTag.setShort("xTile", (short) this.xTile);
        par1CompoundTag.setShort("yTile", (short) this.yTile);
        par1CompoundTag.setShort("zTile", (short) this.zTile);
        par1CompoundTag.setByte("inTile", (byte) Block.getIdFromBlock(this.inTile));
        par1CompoundTag.setByte("inData", (byte) this.inData);
        par1CompoundTag.setByte("inGround", (byte) (this.inGround ? 1 : 0));
        par1CompoundTag.setByte("active", (byte) (this.active ? 1 : 0));
        par1CompoundTag.setDouble("damage", this.damage);
    }

    /*
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditionalSaveData(CompoundTag par1CompoundTag) {
        this.xTile = par1CompoundTag.getShort("xTile");
        this.yTile = par1CompoundTag.getShort("yTile");
        this.zTile = par1CompoundTag.getShort("zTile");
        this.inTile = Block.getBlockById(par1CompoundTag.getByte("inTile") & 255);
        this.inData = par1CompoundTag.getByte("inData") & 255;
        this.inGround = par1CompoundTag.getByte("inGround") == 1;
        this.active = par1CompoundTag.getByte("active") == 1;

        if (par1CompoundTag.hasKey("damage")) {
            this.damage = par1CompoundTag.getDouble("damage");
        }
    }

    /*
     * プレイヤーと衝突した時のメソッド。今回は何もしない
     */
    @Override
    public void onCollideWithPlayer(Player par1EntityPlayer) {

    }

    /*
     * ブロックに対し、上を歩いたかという判定の対象になるか、というEntityクラスのメソッド。
     * 耕地を荒らしたりするのに使う。
     */
    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    
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

    @Override
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
        if (entity instanceof Player) {
            return EntityDamageSource.causePlayerDamage((Player) entity);
        }
        return entity != null ? EntityDamageSource.causeIndirectMagicDamage(this, entity) : DamageSource.magic;
    }

    /* 爆発判定 */
    public byte isExploded() {
        byte b0 = this.entityData.getWatchableObjectByte(16);
        return b0;
    }

    // 爆発処理。パーティクル発生のため、クライアントとの同期が必要。
    public void setExplosion() {
        this.entityData.set(16, Byte.valueOf((byte) 4));
    }

    public void setTimeCount(byte i) {
        this.entityData.set(16, Byte.valueOf(i));
    }

    /* アクティブかどうか */
    public boolean isActive() {
        byte b0 = this.entityData.getWatchableObjectByte(17);
        return b0 > 0;
    }

    public void setActive(byte b) {
        this.entityData.set(17, Byte.valueOf((byte) 1));
    }

}
