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
import mods.defeatedcrow.common.block.edible.BlockCocktailSP;
import mods.defeatedcrow.common.block.edible.BlockCocktailSP.DecorationType;
import mods.defeatedcrow.common.block.edible.BlockCocktailSP.ModelType;
import mods.defeatedcrow.common.entity.edible.PlaceableCocktailSP;
import mods.defeatedcrow.handler.Util;

/**
 * 1.20.1 migration: Render -> EntityRenderer + PoseStack/MultiBufferSource.
 * The former per-meta GL color multiplier (glColor4f) has no direct
 * VertexConsumer equivalent with stock RenderTypes and is dropped.
 * TODO: restore the tint via a custom RenderType or vertex-color-aware model pass.
 */
public class RenderCocktailSPEntity extends EntityRenderer<PlaceableCocktailSP> {

    private static final ResourceLocation COCKTAIL_TEX = new ResourceLocation("defeatedcrow", "textures/entity/cocktail.png");
    private static final ResourceLocation INNER_TEX = new ResourceLocation("defeatedcrow", "textures/blocks/contents_cocktailbase.png");

    private final ModelCocktail model;

    public RenderCocktailSPEntity(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.3F;
        this.model = new ModelCocktail(ctx.bakeLayer(ModEntityRenderers.MODEL_COCKTAIL));
    }

    @Override
    public void render(PlaceableCocktailSP entity, float yaw, float partialTick, PoseStack poseStack,
        MultiBufferSource buffer, int packedLight) {
        byte l = (byte) entity.getItemMetadata();
        float size = Util.getCupScale();

        ModelType type = BlockCocktailSP.getGlassType(l);
        DecorationType deco = BlockCocktailSP.getDecoType(l);

        // inner (translucent)
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.45F * size, 0.0F);
        poseStack.scale(size, size, size);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        VertexConsumer vcInner = buffer.getBuffer(RenderType.entityTranslucent(INNER_TEX));
        this.model.renderInnerSP(poseStack, vcInner, packedLight, OverlayTexture.NO_OVERLAY, type);
        poseStack.popPose();

        // deco
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.45F * size, 0.0F);
        poseStack.scale(size, size, size);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        VertexConsumer vcDeco = buffer.getBuffer(RenderType.entityCutout(COCKTAIL_TEX));
        this.model.renderDecoSP(poseStack, vcDeco, packedLight, OverlayTexture.NO_OVERLAY, deco);
        poseStack.popPose();

        // glass (translucent)
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.45F * size, 0.0F);
        poseStack.scale(size, size, size);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        VertexConsumer vcGlass = buffer.getBuffer(RenderType.entityTranslucent(COCKTAIL_TEX));
        this.model.renderGlassSP(poseStack, vcGlass, packedLight, OverlayTexture.NO_OVERLAY, type);
        poseStack.popPose();
        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(PlaceableCocktailSP entity) {
        return COCKTAIL_TEX;
    }
}
