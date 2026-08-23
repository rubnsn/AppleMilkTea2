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
import mods.defeatedcrow.client.model.model.ModelSteak;
import mods.defeatedcrow.common.entity.edible.PlaceableSteak;

/**
 * 1.20.1 migration: Render -> EntityRenderer + PoseStack/MultiBufferSource.
 */
public class RenderSteakEntity extends EntityRenderer<PlaceableSteak> {

    private static final ResourceLocation STEAK_TEX = new ResourceLocation("defeatedcrow", "textures/entity/steak.png");
    private static final ResourceLocation CHICKEN_TEX = new ResourceLocation("defeatedcrow", "textures/entity/roastedchicken.png");
    private static final ResourceLocation HAMAGURI_TEX = new ResourceLocation("defeatedcrow", "textures/entity/hamaguri.png");
    private static final ResourceLocation PLATE_TEX = new ResourceLocation("defeatedcrow", "textures/entity/steakplate.png");

    private final ModelSteak model;

    public RenderSteakEntity(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.5F;
        this.model = new ModelSteak(ctx.bakeLayer(ModEntityRenderers.MODEL_STEAK));
    }

    @Override
    public void render(PlaceableSteak entity, float yaw, float partialTick, PoseStack poseStack,
        MultiBufferSource buffer, int packedLight) {
        byte l = (byte) entity.getItemMetadata();

        poseStack.pushPose();
        poseStack.translate(0.0F, 1.20F, 0.0F);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        VertexConsumer vc = buffer.getBuffer(RenderType.entityCutout(getTextureLocation(entity)));
        this.model.render(poseStack, vc, packedLight, OverlayTexture.NO_OVERLAY, l);
        poseStack.popPose();

        // plate
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.25F, 0.0F);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        this.model.renderPlate(poseStack, buffer.getBuffer(RenderType.entityCutout(PLATE_TEX)),
            packedLight, OverlayTexture.NO_OVERLAY, l);
        poseStack.popPose();
        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(PlaceableSteak entity) {
        byte l = (byte) entity.getItemMetadata();
        if (l == 0 || l == 1) {
            return STEAK_TEX;
        } else if (l == 2) {
            return CHICKEN_TEX;
        }
        return HAMAGURI_TEX;
    }
}
