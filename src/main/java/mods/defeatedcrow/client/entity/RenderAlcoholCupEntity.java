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
import mods.defeatedcrow.client.model.model.ModelAlcoholCup;
import mods.defeatedcrow.common.block.edible.BlockAlcoholCup;
import mods.defeatedcrow.common.entity.edible.PlaceableAlcoholCup;
import mods.defeatedcrow.handler.Util;

/**
 * 1.20.1 migration: Render -> EntityRenderer + PoseStack/MultiBufferSource.
 */
public class RenderAlcoholCupEntity extends EntityRenderer<PlaceableAlcoholCup> {

    private static final ResourceLocation COCKTAIL_TEX = new ResourceLocation("defeatedcrow", "textures/entity/cocktail.png");
    private static final ResourceLocation ATUKAN_TEX = new ResourceLocation("defeatedcrow", "textures/entity/atukan.png");

    private final ModelAlcoholCup model;

    public RenderAlcoholCupEntity(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.3F * Util.getCupSize();
        this.model = new ModelAlcoholCup(ctx.bakeLayer(ModEntityRenderers.MODEL_ALCOHOL_CUP));
    }

    @Override
    public void render(PlaceableAlcoholCup entity, float yaw, float partialTick, PoseStack poseStack,
        MultiBufferSource buffer, int packedLight) {
        byte l = (byte) entity.getItemMetadata();
        byte type = 0;
        if (l == 2 || l == 12 || l == 13) {
            type = 1;
        }
        float size = Util.getCupScale();

        if (l == 0) {
            // atukan (solid)
            poseStack.pushPose();
            poseStack.translate(0.0F, 1.4F * size - 0.1F, 0.0F);
            poseStack.scale(size, size, size);
            poseStack.scale(1.0F, -1.0F, -1.0F);
            poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
            VertexConsumer vcAtukan = buffer.getBuffer(RenderType.entityCutout(ATUKAN_TEX));
            this.model.renderAtukan(poseStack, vcAtukan, packedLight, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
        } else {
            // inner
            ResourceLocation innerTex = new ResourceLocation("defeatedcrow",
                "textures/blocks/contents" + BlockAlcoholCup.contents[l] + ".png");
            poseStack.pushPose();
            poseStack.translate(0.0F, 1.45F * size, 0.0F);
            poseStack.scale(size, size, size);
            poseStack.scale(1.0F, -1.0F, -1.0F);
            poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
            VertexConsumer vcInner = buffer.getBuffer(RenderType.entityTranslucent(innerTex));
            this.model.renderInner(poseStack, vcInner, packedLight, OverlayTexture.NO_OVERLAY, type);
            poseStack.popPose();

            // glass + ice (translucent)
            poseStack.pushPose();
            poseStack.translate(0.0F, 1.45F * size, 0.0F);
            poseStack.scale(size, size, size);
            poseStack.scale(1.0F, -1.0F, -1.0F);
            poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
            VertexConsumer vcGlass = buffer.getBuffer(RenderType.entityTranslucent(COCKTAIL_TEX));
            this.model.renderGlass(poseStack, vcGlass, packedLight, OverlayTexture.NO_OVERLAY, type);
            this.model.renderIce(poseStack, vcGlass, packedLight, OverlayTexture.NO_OVERLAY, type);
            poseStack.popPose();
        }
        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(PlaceableAlcoholCup entity) {
        return COCKTAIL_TEX;
    }
}
