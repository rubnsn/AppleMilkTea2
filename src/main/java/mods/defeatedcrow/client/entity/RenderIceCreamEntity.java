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
import mods.defeatedcrow.client.model.model.ModelIceCream;
import mods.defeatedcrow.common.entity.edible.PlaceableIcecream;
import mods.defeatedcrow.handler.Util;

/**
 * 1.20.1 migration: Render -> EntityRenderer + PoseStack/MultiBufferSource.
 * The former GL11 stencil/polygon-offset trick for the "clear" pass is replaced by
 * a translucent second pass of the clear geometry (TODO: revisit if depth artifacts appear).
 */
public class RenderIceCreamEntity extends EntityRenderer<PlaceableIcecream> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("defeatedcrow", "textures/entity/icecream.png");

    private final ModelIceCream model;

    public RenderIceCreamEntity(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.3F;
        this.model = new ModelIceCream(ctx.bakeLayer(ModEntityRenderers.MODEL_ICECREAM));
    }

    @Override
    public void render(PlaceableIcecream entity, float yaw, float partialTick, PoseStack poseStack,
        MultiBufferSource buffer, int packedLight) {
        byte l = (byte) entity.getItemMetadata();
        float size = Util.getCupScale();

        // main
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.45F * size, 0.0F);
        poseStack.scale(size, size, size);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - yaw));
        VertexConsumer vcMain = buffer.getBuffer(RenderType.entityCutout(TEXTURE));
        this.model.render(poseStack, vcMain, packedLight, OverlayTexture.NO_OVERLAY, l);
        poseStack.popPose();

        // clear pass (former stencil pass)
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.45F * size, 0.0F);
        poseStack.scale(size, size, size);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - yaw));
        VertexConsumer vcClear = buffer.getBuffer(RenderType.entityTranslucent(TEXTURE));
        this.model.renderClear(poseStack, vcClear, packedLight, OverlayTexture.NO_OVERLAY, l);
        poseStack.popPose();
        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(PlaceableIcecream entity) {
        return TEXTURE;
    }
}
