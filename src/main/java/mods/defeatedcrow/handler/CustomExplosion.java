package mods.defeatedcrow.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
// net.minecraft.world.entity.boss.enderdragon.EnderDragonPart removed
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.Level;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.config.DCsConfig;

/**
 * AMT2用のカスタムエクスプロージョン。
 * 目的は2つあり、 <br>
 * ・シルクメロン用の着火者、ドロップアイテムを消さない爆発 <br>
 * ・アンカーミサイル用飛行特効 <br>
 * すべての場合でブロックを破壊しない。
 */
public class CustomExplosion extends Explosion {

    private final Random rand = new Random();
    private final Level level;
    private final LivingEntity igniter;
    private final Type type;
    private Map playerMap = new HashMap();

    public CustomExplosion(Level world, Entity source, LivingEntity ign, double posX, double posY, double posZ,
        float size, Type t, boolean smoke) {
        super(world, source, posX, posY, posZ, size);
        this.level = world;
        this.type = t;
        this.igniter = ign;
        this.isFlaming = false;
        this.isSmoking = smoke;
    }

    public void doExplosion() {
        float f = this.explosionSize;
        int i = 0;
        int j = 0;
        int k = 0;
        double d5 = this.explosionX;
        double d6 = this.explosionY;
        double d7 = this.explosionZ;

        // エンティティへのダメージ
        this.explosionSize *= 2.0F;
        i = Mth.floor_double(this.explosionX - this.explosionSize - 1.0D);
        int i2 = Mth.floor_double(this.explosionX + this.explosionSize + 1.0D);
        j = Mth.floor_double(this.explosionY - this.explosionSize - 1.0D);
        int j2 = Mth.floor_double(this.explosionY + this.explosionSize + 1.0D);
        k = Mth.floor_double(this.explosionZ - this.explosionSize - 1.0D);
        int k2 = Mth.floor_double(this.explosionZ + this.explosionSize + 1.0D);
        List list = this.level
            .getEntitiesWithinAABBExcludingEntity(this.exploder, AABB.getBoundingBox(i, j, k, i2, j2, k2));
        Vec3 vec3 = Vec3.createVectorHelper(this.explosionX, this.explosionY, this.explosionZ);

        for (int i1 = 0; i1 < list.size(); ++i1) {
            Entity entity = (Entity) list.get(i1);
            double d4 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / this.explosionSize;
            AMTLogger.debugInfo("d4 :" + d4);

            if (d4 <= 1.0D && d4 > 0.0D) {
                double d11 = (1.0D - d4);
                AMTLogger.debugInfo("d11 :" + d11);
                d5 = entity.posX - this.explosionX;
                d6 = entity.posY - this.explosionY;
                d7 = entity.posZ - this.explosionZ;
                d5 *= d11;
                d6 *= d11;
                d7 *= d11;

                boolean flag = true;
                float damage = (int) ((this.explosionSize * this.explosionSize * 2) * (d11 * d11));
                AMTLogger.debugInfo("explosion prev damage :" + damage);
                damage = Math.max(damage, 3.0F);

                if (this.type == Type.Melon) {
                    if (entity instanceof ItemEntity || entity instanceof Projectile || entity == this.exploder) {
                        flag = false;
                    } else if (entity instanceof LivingEntity) {
                        if (this.igniter != null) {
                            LivingEntity living = (LivingEntity) entity;
                            flag = !(living == this.igniter);
                        }
                    } else if (entity instanceof Boat || entity instanceof AbstractMinecart) {
                        flag = false;
                    } else if (DCsConfig.PvPProhibitionMode && entity instanceof Player) {
                        flag = false;
                    }
                } else if (this.type == Type.Anchor) {
                    if (entity instanceof LivingEntity) {
                        if (this.igniter != null) {
                            LivingEntity living = (LivingEntity) entity;
                            flag = !(living == this.igniter);
                        }
                    } else if (entity instanceof Boat || entity instanceof AbstractMinecart) {
                        flag = false;
                    } else if (DCsConfig.PvPProhibitionMode && entity instanceof Player) {
                        flag = false;
                    } else if (entity instanceof net.minecraft.world.entity.boss.enderdragon.EnderDragonPart) {
                        damage *= 2.0F;
                    } else if (!entity.onGround) {
                        damage *= 10.0F;
                    }
                } else {
                    if (entity instanceof LivingEntity) {
                        if (this.igniter != null) {
                            LivingEntity living = (LivingEntity) entity;
                            flag = !(living == this.igniter);
                        }
                    }
                }

                if (flag) {
                    AMTLogger.debugInfo("explosion type :" + this.type);
                    AMTLogger.debugInfo("explosion deal damage :" + damage);
                    if (entity instanceof Projectile) {
                        entity.discard();
                    } else {
                        if (this.igniter instanceof Player) {
                            entity
                                .attackEntityFrom(DamageSource.causePlayerDamage((Player) this.igniter), damage);
                        } else if (this.igniter != null) {
                            entity.attackEntityFrom(DamageSource.setExplosionSource(this), damage);
                        } else {
                            entity.attackEntityFrom(DamageSource.setExplosionSource(this), damage);
                        }

                    }
                    double d8 = EnchantmentProtection.func_92092_a(entity, d11);
                    entity.motionX += d5 * d8;
                    entity.motionY += d6 * d8;
                    entity.motionZ += d7 * d8;
                }
            }
        }

        this.explosionSize = f;
        this.level.playSoundEffect(
            this.explosionX,
            this.explosionY,
            this.explosionZ,
            "random.explode",
            4.0F,
            (1.0F + (this.level.rand.nextFloat() - this.level.rand.nextFloat()) * 0.2F) * 0.7F);
    }

    public static enum Type {
        Melon,
        Anchor,
        Normal;
    }

}
