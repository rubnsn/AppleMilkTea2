package mods.defeatedcrow.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.model.ModelYuzuBullet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.client.ModEntityRenderers;
import mods.defeatedcrow.common.entity.EntityYuzuBullet;

/**
 * 1.20.1 migration: Render -> EntityRenderer + PoseStack/MultiBufferSource.
 * The former GL color tint for the burning state has no direct VertexConsumer
 * equivalent here and is dropped (TODO: tint via custom RenderType if needed).
 */
public class RenderYuzuBullet extends EntityRenderer<EntityYuzuBullet> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("defeatedcrow", "textures/entity/yuzubullet.png");

    private final ModelYuzuBullet modelMissile;

    public RenderYuzuBullet(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.5F;
        this.modelMissile = new ModelYuzuBullet(ctx.bakeLayer(ModEntityRenderers.MODEL_YUZU_BULLET));
    }

    @Override
    public void render(EntityYuzuBullet entity, float yaw, float partialTick, PoseStack poseStack,
        MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.0F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(entity.getViewYRot(partialTick)));
        poseStack.mulPose(Axis.XP.rotationDegrees(-entity.getViewXRot(partialTick)));
        poseStack.scale(1.0F, -1.0F, -1.0F);
        this.modelMissile.render(poseStack, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(entity))),
            packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityYuzuBullet entity) {
        return TEXTURE;
    }
}
