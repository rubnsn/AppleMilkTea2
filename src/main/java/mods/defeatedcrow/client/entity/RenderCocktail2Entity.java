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
import mods.defeatedcrow.client.model.model.ModelCocktail;
import mods.defeatedcrow.common.block.edible.BlockCocktail2;
import mods.defeatedcrow.common.entity.edible.PlaceableCocktail2;
import mods.defeatedcrow.handler.Util;

/**
 * 1.20.1 migration: Render -> EntityRenderer + PoseStack/MultiBufferSource.
 */
public class RenderCocktail2Entity extends EntityRenderer<PlaceableCocktail2> {

    private static final ResourceLocation COCKTAIL_TEX = new ResourceLocation("defeatedcrow", "textures/entity/cocktail.png");

    private final ModelCocktail model;

    public RenderCocktail2Entity(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.3F;
        this.model = new ModelCocktail(ctx.bakeLayer(ModEntityRenderers.MODEL_COCKTAIL));
    }

    @Override
    public void render(PlaceableCocktail2 entity, float yaw, float partialTick, PoseStack poseStack,
        MultiBufferSource buffer, int packedLight) {
        byte l = (byte) entity.getItemMetadata();
        if (l > 9) l = 9;
        float size = Util.getCupScale();

        byte type = 0;// 0:long縲・:short縲・:wineglass/4:frozen
        if (l == 6 || l == 7) type = 1;
        else if (l == 1 || l == 2 || l == 9) type = 2;

        byte deco = 0;// 0:none
        if (l == 9) deco = 5;

        ResourceLocation innerTex = new ResourceLocation("defeatedcrow",
            "textures/blocks/contents" + BlockCocktail2.contents[l] + ".png");

        // inner (translucent)
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.45F * size, 0.0F);
        poseStack.scale(size, size, size);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        VertexConsumer vcInner = buffer.getBuffer(RenderType.entityTranslucent(innerTex));
        this.model.renderInner(poseStack, vcInner, packedLight, OverlayTexture.NO_OVERLAY, type);
        poseStack.popPose();

        // deco
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.45F * size, 0.0F);
        poseStack.scale(size, size, size);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        VertexConsumer vcDeco = buffer.getBuffer(RenderType.entityCutout(COCKTAIL_TEX));
        this.model.renderDeco(poseStack, vcDeco, packedLight, OverlayTexture.NO_OVERLAY, deco);
        poseStack.popPose();

        // glass (translucent)
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.45F * size, 0.0F);
        poseStack.scale(size, size, size);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        VertexConsumer vcGlass = buffer.getBuffer(RenderType.entityTranslucent(COCKTAIL_TEX));
        this.model.renderGlass(poseStack, vcGlass, packedLight, OverlayTexture.NO_OVERLAY, type);
        poseStack.popPose();
        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(PlaceableCocktail2 entity) {
        return COCKTAIL_TEX;
    }
}
