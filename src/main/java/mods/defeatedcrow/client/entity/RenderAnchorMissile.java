package mods.defeatedcrow.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.client.ModEntityRenderers;
import mods.defeatedcrow.client.model.model.ModelAnchorMissile;
import mods.defeatedcrow.common.entity.EntityAnchorMissile;

/**
 * 1.20.1 migration: Render -> EntityRenderer + PoseStack/MultiBufferSource.
 */
public class RenderAnchorMissile extends EntityRenderer<EntityAnchorMissile> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("defeatedcrow", "textures/entity/anchormissile.png");

    private final ModelAnchorMissile modelMissile;

    public RenderAnchorMissile(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.5F;
        this.modelMissile = new ModelAnchorMissile(ctx.bakeLayer(ModEntityRenderers.MODEL_ANCHOR_MISSILE));
    }

    @Override
    public void render(EntityAnchorMissile entity, float yaw, float partialTick, PoseStack poseStack,
        MultiBufferSource buffer, int packedLight) {
        boolean flag = entity.isActive();

        poseStack.pushPose();
        poseStack.translate(0.0F, 1.0F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(entity.getViewYRot(partialTick)));
        poseStack.mulPose(Axis.XP.rotationDegrees(-entity.getViewXRot(partialTick)));
        poseStack.scale(1.0F, -1.0F, -1.0F);
        // apply animated wing state, then render
        this.modelMissile.setupAnim(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, flag);
        this.modelMissile.render(poseStack, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(entity))),
            packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityAnchorMissile entity) {
        return TEXTURE;
    }
}
