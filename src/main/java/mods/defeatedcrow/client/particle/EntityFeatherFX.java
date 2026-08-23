package mods.defeatedcrow.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;

/**
 * 1.20.1 port of the 1.7.10 "feather" particle (was {@code EntityFX}, FX layer 2).
 *
 * <p>
 * Lifetime is passed by the spawner plus random 0-2 ticks; motion damps at 0.90 (X/Z) and 0.95 (Y) per
 * tick — the feather-like slow fall. Spawned from WT-B code via {@code ModParticleTypes.FEATHER}.
 */
public class EntityFeatherFX extends TextureSheetParticle {

    protected EntityFeatherFX(ClientLevel level, double x, double y, double z, double vx, double vy, double vz,
        int time, SpriteSet sprites) {
        super(level, x, y, z, vx, vy, vz);
        this.xd = vx;
        this.yd = vy;
        this.zd = vz;
        this.lifetime = time + this.random.nextInt(3);
        this.quadSize = 0.3F;
        this.setSprite(sprites.get(this.random));
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.age++ >= this.lifetime) {
            this.remove();
            return;
        }

        this.move(this.xd, this.yd, this.zd);
        this.xd *= 0.90D;
        this.yd *= 0.95D;
        this.zd *= 0.90D;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public EntityFeatherFX createParticle(SimpleParticleType type, ClientLevel level, double x, double y,
            double z, double vx, double vy, double vz) {
            // 旧実装の time 引数相当: 固定値 30 + ランダムで代替（スポナー側の引数はSimpleParticleTypeに載らない）
            return new EntityFeatherFX(level, x, y, z, vx, vy, vz, 30, this.sprites);
        }
    }
}
