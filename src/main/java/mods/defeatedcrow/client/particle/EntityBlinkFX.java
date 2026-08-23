package mods.defeatedcrow.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;

/**
 * 1.20.1 port of the 1.7.10 "blink" particle (was {@code EntityFX}, FX layer 2).
 *
 * <p>
 * FX layer 2 (custom icon textures) no longer exists: 1.20.1 particles always sample the block/particle
 * atlas via {@code SpriteSet} ({@code textures/particle/blink.png}). Spawned from WT-B code as
 * {@code level.addParticle(ModParticleTypes.BLINK, x, y, z, vx, vy, vz)}.
 */
public class EntityBlinkFX extends TextureSheetParticle {

    protected EntityBlinkFX(ClientLevel level, double x, double y, double z, double vx, double vy, double vz,
        SpriteSet sprites) {
        super(level, x, y, z, vx, vy, vz);
        this.xd = vx;
        this.yd = vy;
        this.zd = vz;
        this.quadSize = 0.25F;
        this.lifetime = 20 + this.random.nextInt(12);
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
        this.yd *= 0.90D;
        this.zd *= 0.90D;
    }

    @Override
    public float getQuadSize(float partialTick) {
        // 旧実装相当: 縮小しながら消滅する
        float f = ((float) this.lifetime - (float) this.age - partialTick) / (float) this.lifetime;
        return this.quadSize * Math.max(f, 0.0F);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public EntityBlinkFX createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
            double vx, double vy, double vz) {
            return new EntityBlinkFX(level, x, y, z, vx, vy, vz, this.sprites);
        }
    }
}
