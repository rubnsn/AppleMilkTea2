package mods.defeatedcrow.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

/**
 * 1.20.1 migration: Render -> EntityRenderer + PoseStack/MultiBufferSource.
 * Uses the vanilla creeper layer (ModelLayers.CREEPER) instead of new ModelCreeper(0.0F).
 */
public class RenderIllusionCreeper extends EntityRenderer<mods.defeatedcrow.common.entity.dummy.EntityIllusionMobs> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/creeper/creeper.png");

    private final ModelCreeper creeperModel;

    public RenderIllusionCreeper(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.5F;
        this.creeperModel = new ModelCreeper(ctx.bakeLayer(ModelLayers.CREEPER));
    }

    @Override
    public void render(mods.defeatedcrow.common.entity.dummy.EntityIllusionMobs entity, float yaw, float partialTick,
        PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.5F, 0.0F);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        VertexConsumer vc = buffer.getBuffer(RenderType.entityCutout(TEXTURE));
        this.creeperModel.render(poseStack, vc, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(mods.defeatedcrow.common.entity.dummy.EntityIllusionMobs entity) {
        return TEXTURE;
    }
}
