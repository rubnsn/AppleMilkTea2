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
import mods.defeatedcrow.client.model.model.ModelTart;
import mods.defeatedcrow.common.entity.edible.PlaceableTart;

/**
 * 1.20.1 migration: Render -> EntityRenderer + PoseStack/MultiBufferSource.
 */
public class RenderTartEntity extends EntityRenderer<PlaceableTart> {

    private static final ResourceLocation TART_TEX = new ResourceLocation("defeatedcrow", "textures/entity/tart.png");
    private static final ResourceLocation PLATE_TEX = new ResourceLocation("defeatedcrow", "textures/entity/tartbase.png");
    private static final ResourceLocation MOUSSE_TEX = new ResourceLocation("defeatedcrow", "textures/entity/moussecake.png");
    private static final ResourceLocation MOUSSE_TEX1 = new ResourceLocation("defeatedcrow", "textures/blocks/contents_cocoa_milk.png");
    private static final ResourceLocation MOUSSE_TEX2 = new ResourceLocation("defeatedcrow", "textures/blocks/contents_milk.png");
    private static final ResourceLocation MOUSSE_TEX3_LEMON = new ResourceLocation("defeatedcrow", "textures/blocks/contents_lemon.png");
    private static final ResourceLocation MOUSSE_TEX3_JUICE = new ResourceLocation("defeatedcrow", "textures/blocks/contents_juice.png");

    private final ModelTart model;

    public RenderTartEntity(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.5F;
        this.model = new ModelTart(ctx.bakeLayer(ModEntityRenderers.MODEL_TART));
    }

    @Override
    public void render(PlaceableTart entity, float yaw, float partialTick, PoseStack poseStack,
        MultiBufferSource buffer, int packedLight) {
        byte l = (byte) entity.getItemMetadata();

        poseStack.pushPose();
        poseStack.translate(0.0F, 1.25F, 0.0F);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));

        if (l < 2) {
            VertexConsumer vc = buffer.getBuffer(RenderType.entityCutout(TART_TEX));
            this.model.render(poseStack, vc, packedLight, OverlayTexture.NO_OVERLAY, l);
            if (l == 1) {
                this.model.renderCrops(poseStack, vc, packedLight, OverlayTexture.NO_OVERLAY, l);
            }
        } else {
            ResourceLocation mousseTex3 = (l == 3) ? MOUSSE_TEX3_JUICE : MOUSSE_TEX3_LEMON;

            this.model.renderMousseBase(poseStack, buffer.getBuffer(RenderType.entityCutout(MOUSSE_TEX)),
                packedLight, OverlayTexture.NO_OVERLAY, l);
            this.model.renderMousse1(poseStack, buffer.getBuffer(RenderType.entityCutout(MOUSSE_TEX1)),
                packedLight, OverlayTexture.NO_OVERLAY, l);
            this.model.renderMousse2(poseStack, buffer.getBuffer(RenderType.entityCutout(MOUSSE_TEX2)),
                packedLight, OverlayTexture.NO_OVERLAY, l);
            this.model.renderMousse3(poseStack, buffer.getBuffer(RenderType.entityCutout(mousseTex3)),
                packedLight, OverlayTexture.NO_OVERLAY, l);
        }

        // plate
        this.model.renderPlate(poseStack, buffer.getBuffer(RenderType.entityCutout(PLATE_TEX)),
            packedLight, OverlayTexture.NO_OVERLAY, l);

        poseStack.popPose();
        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(PlaceableTart entity) {
        return TART_TEX;
    }
}
