package mods.defeatedcrow.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.entity.dummy.EntityStunEffect;

/**
 * 1.20.1 migration: Render -> EntityRenderer. Renders nothing (as in 1.7.10).
 */
public class RenderStunEntity extends EntityRenderer<EntityStunEffect> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("defeatedcrow", "textures/entity/kinoko_red.png");

    public RenderStunEntity(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(EntityStunEffect entity, float yaw, float partialTick, PoseStack poseStack,
        MultiBufferSource buffer, int packedLight) {
        // なにもしない
        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityStunEffect entity) {
        return TEXTURE;
    }
}
