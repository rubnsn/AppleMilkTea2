package mods.defeatedcrow.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;

/**
 * 1.20.1 port of the 1.7.10 "orb" particle (was {@code EntityFX}, FX layer 2).
 *
 * <p>
 * Half-transparent orb that rises straight up, shrinks, and dies once it has climbed more than 1 block
 * from its origin (old {@code orginalPosY} logic). Spawned by BlockChalcedonyLamp (WT-B).
 */
public class EntityOrbFX extends TextureSheetParticle {

    // 発生地点のY座標。消滅条件に利用する
    private final double originalPosY;

    protected EntityOrbFX(ClientLevel level, double x, double y, double z, double vx, double vy, double vz,
        SpriteSet sprites) {
        super(level, x, y, z, vx, vy, vz);
        this.originalPosY = y;
        this.xd = 0.0D;
        this.yd = vy;
        this.zd = 0.0D;
        this.alpha = 0.5F;
        this.quadSize = 0.2F;
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

        // 発生地点から1m以上上昇したら消滅
        if (this.y > this.originalPosY + 1.0D) {
            this.remove();
            return;
        }

        this.move(this.xd, this.yd, this.zd);
    }

    @Override
    public float getQuadSize(float partialTick) {
        // 小さくすめる
        float f = ((float) this.lifetime - (float) this.age - partialTick) / (float) this.lifetime;
        return this.quadSize * Math.max(f, 0.0F);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public EntityOrbFX createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
            double vx, double vy, double vz) {
            return new EntityOrbFX(level, x, y, z, vx, vy, vz, this.sprites);
        }
    }
}
