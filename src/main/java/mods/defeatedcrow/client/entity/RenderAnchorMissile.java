package mods.defeatedcrow.client.entity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
public class RenderAnchorMissile extends EntityRenderer<Entity> {
    public RenderAnchorMissile(EntityRendererProvider.Context c){ super(c); }
    @Override public ResourceLocation getTextureLocation(Entity e){ return new ResourceLocation("defeatedcrow:textures/entity/white.png"); }
}
