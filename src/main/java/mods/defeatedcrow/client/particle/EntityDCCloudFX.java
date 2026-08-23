package mods.defeatedcrow.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;

/**
 * 1.20.1 port of the 1.7.10 "cloud" smoke particle (was {@code EntityFX}, FX layer 2).
 *
 * <p>
 * Grows from 0 to its initial size over the lifetime (old behaviour: scale factor
 * {@code age/maxAge * 32} clamped to 1) and damps motion by 0.96/tick. Also reused as the provider for
 * the "flower" sprite variant ({@code ModParticleTypes.FLOWER}) — the 1.7.10 codebase had no dedicated
 * flower FX class, only the icon.
 */
public class EntityDCCloudFX extends TextureSheetParticle {

    private final float initialSize;

    protected EntityDCCloudFX(ClientLevel level, double x, double y, double z, double vx, double vy, double vz,
        SpriteSet sprites) {
        super(level, x, y, z, 0.0D, 0.0D, 0.0D);
        float f = 2.5F;
        // 旧実装: 初速0 → 0.1倍(恒等) → 入力速度を加算 = 入力速度そのまま
        this.xd = vx;
        this.yd = vy;
        this.zd = vz;
        this.initialSize = 0.75F * f * 0.2F; // 旧 particleScale(既定0.2) * 0.75 * f
        this.lifetime = (int) ((8.0D / (this.random.nextDouble() * 0.8D + 0.3D)) * f);
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
        this.xd *= 0.9599999785423279D;
        this.yd *= 0.9599999785423279D;
        this.zd *= 0.9599999785423279D;
    }

    @Override
    public float getQuadSize(float partialTick) {
        float f6 = ((float) this.age + partialTick) / (float) this.lifetime * 32.0F;
        if (f6 > 1.0F) {
            f6 = 1.0F;
        }
        if (f6 < 0.0F) {
            f6 = 0.0F;
        }
        return this.initialSize * f6;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public EntityDCCloudFX createParticle(SimpleParticleType type, ClientLevel level, double x, double y,
            double z, double vx, double vy, double vz) {
            return new EntityDCCloudFX(level, x, y, z, vx, vy, vz, this.sprites);
        }
    }
}
