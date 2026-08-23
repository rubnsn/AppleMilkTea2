package mods.defeatedcrow.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.client.ModEntityRenderers;
import mods.defeatedcrow.client.model.model.ModelSandwich;
import mods.defeatedcrow.common.entity.edible.PlaceableSandwich;

/**
 * 1.20.1 migration: Render -> EntityRenderer + PoseStack/MultiBufferSource.
 */
public class RenderSandwichEntity extends EntityRenderer<PlaceableSandwich> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("defeatedcrow", "textures/entity/sandwich.png");

    private final ModelSandwich model;

    public RenderSandwichEntity(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.5F;
        this.model = new ModelSandwich(ctx.bakeLayer(ModEntityRenderers.MODEL_SANDWICH));
    }

    @Override
    public void render(PlaceableSandwich entity, float yaw, float partialTick, PoseStack poseStack,
        MultiBufferSource buffer, int packedLight) {
        byte l = (byte) entity.getItemMetadata();

        poseStack.pushPose();
        poseStack.translate(0.0F, 1.2F, 0.0F);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        VertexConsumer vc = buffer.getBuffer(RenderType.entityCutout(getTextureLocation(entity)));
        this.model.render(poseStack, vc, packedLight, OverlayTexture.NO_OVERLAY, l);
        poseStack.popPose();
        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(PlaceableSandwich entity) {
        return TEXTURE;
    }
}
