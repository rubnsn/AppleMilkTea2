package mods.defeatedcrow.handler;

import java.util.Random;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.Vec3;

/**
 * AMT2 Stub for 1.20.1 - original 1.7.10 Explosion logic heavily uses removed APIs
 * (explosionX/Y/Z, isFlaming, Mth.floor_double, AABB.getBoundingBox, Vec3.createVectorHelper,
 *  entity.posX etc). Stub keeps constructor and type enum to allow compile; runtime delegates to vanilla Explosion.
 * TODO: reimplement using 1.20.1 Explosion API (Level.ExplosionInteraction).
 */
public class CustomExplosion {

    private final Level level;
    private final LivingEntity igniter;
    private final Type type;
    private final Entity source;
    private final double x, y, z;
    private final float size;
    private final boolean smoking;

    public CustomExplosion(Level world, Entity source, LivingEntity ign, double posX, double posY, double posZ,
        float size, Type t, boolean smoke) {
        this.level = world;
        this.source = source;
        this.igniter = ign;
        this.x = posX;
        this.y = posY;
        this.z = posZ;
        this.size = size;
        this.type = t;
        this.smoking = smoke;
    }

    public void doExplosion() {
        if (level == null || level.isClientSide) return;
        // 1.20.1: use Level#explode with DamageSource and radius; blockInteraction NONE to keep original no-block-damage behavior
        DamageSource ds;
        if (igniter instanceof Player p) {
            ds = level.damageSources().playerAttack(p);
        } else if (source != null) {
            ds = level.damageSources().explosion(source, igniter);
        } else {
            ds = level.damageSources().explosion(null, null);
        }
        // Use vanilla explosion but suppress block breaking
        level.explode(source, ds, null, x, y, z, size * 2.0F, false, Level.ExplosionInteraction.NONE);
        if (smoking) {
            level.playSound(null, x, y, z, net.minecraft.sounds.SoundEvents.GENERIC_EXPLODE, net.minecraft.sounds.SoundSource.BLOCKS, 4.0F,
                (1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.2F) * 0.7F);
        }
    }

    public static enum Type {
        Melon,
        Anchor,
        Normal;
    }
}
