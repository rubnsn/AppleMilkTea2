package mods.defeatedcrow.client.particle;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.multiplayer.ClientLevel;
public class EntityDCCloudFX extends TextureSheetParticle {
    protected EntityDCCloudFX(ClientLevel l, double x, double y, double z, double xd, double yd, double zd){ super(l,x,y,z,xd,yd,zd); }
    @Override public net.minecraft.client.particle.ParticleRenderType getRenderType(){ return net.minecraft.client.particle.ParticleRenderType.PARTICLE_SHEET_OPAQUE; }
}
