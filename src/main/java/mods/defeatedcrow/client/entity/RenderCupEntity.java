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
import mods.defeatedcrow.client.entity.base.ModelTeaCup;
import mods.defeatedcrow.common.block.edible.BlockFilledCup;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.entity.edible.PlaceableCup1;
import mods.defeatedcrow.handler.Util;

/**
 * 1.20.1 migration: Render -> EntityRenderer + PoseStack/MultiBufferSource.
 */
public class RenderCupEntity extends EntityRenderer<PlaceableCup1> {

    private static final ResourceLocation CUP_TEX = new ResourceLocation("defeatedcrow", "textures/blocks/porcelain.png");
    private static final ResourceLocation CUP_SUMMER_TEX = new ResourceLocation("defeatedcrow", "textures/blocks/blueglass.png");

    private final ModelTeaCup model;

    public RenderCupEntity(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.3F * Util.getCupSize();
        this.model = new ModelTeaCup(ctx.bakeLayer(ModEntityRenderers.MODEL_TEA_CUP));
    }

    @Override
    public void render(PlaceableCup1 entity, float yaw, float partialTick, PoseStack poseStack,
        MultiBufferSource buffer, int packedLight) {
        byte l = (byte) entity.getItemMetadata();
        float size = Util.getCupScale();

        // contents
        ResourceLocation innerTex = new ResourceLocation("defeatedcrow",
            "textures/blocks/contents" + BlockFilledCup.contents[l] + ".png");
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.45F * size, 0.0F);
        poseStack.scale(size, size, size);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        VertexConsumer vc = buffer.getBuffer(RenderType.entityTranslucent(innerTex));
        this.model.renderContents(poseStack, vc, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(0.0F, 1.45F * size, 0.0F);
        if (DCsConfig.useSummerRender) {
            poseStack.scale(1.0F, -1.0F, -1.0F);
            poseStack.scale(size, size, size);
            poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
            VertexConsumer vcGlass = buffer.getBuffer(RenderType.entityTranslucent(CUP_SUMMER_TEX));
            this.model.render(poseStack, vcGlass, packedLight, OverlayTexture.NO_OVERLAY);
        } else {
            poseStack.scale(size, size, size);
            poseStack.scale(1.0F, -1.0F, -1.0F);
            poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
            VertexConsumer vcCup = buffer.getBuffer(RenderType.entityCutout(CUP_TEX));
            this.model.render(poseStack, vcCup, packedLight, OverlayTexture.NO_OVERLAY);
        }
        poseStack.popPose();
        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(PlaceableCup1 entity) {
        return CUP_TEX;
    }
}
