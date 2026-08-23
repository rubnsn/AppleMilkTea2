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
import mods.defeatedcrow.client.model.model.ModelKinoko;
import mods.defeatedcrow.common.entity.EntityKinoko;

/**
 * 1.20.1 migration: Render -> EntityRenderer + PoseStack/MultiBufferSource.
 */
public class RenderKinokoEntity extends EntityRenderer<EntityKinoko> {

    private static final ResourceLocation RED_TEX = new ResourceLocation("defeatedcrow", "textures/entity/kinoko_red.png");
    private static final ResourceLocation BROWN_TEX = new ResourceLocation("defeatedcrow", "textures/entity/kinoko_brown.png");

    private final ModelKinoko model;

    public RenderKinokoEntity(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.5F;
        this.model = new ModelKinoko(ctx.bakeLayer(ModEntityRenderers.MODEL_KINOKO));
    }

    @Override
    public void render(EntityKinoko entity, float yaw, float partialTick, PoseStack poseStack,
        MultiBufferSource buffer, int packedLight) {
        byte l = (byte) entity.getItemMetadata();

        poseStack.pushPose();
        poseStack.translate(0.0F, 1.25F, 0.0F);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        this.model.render(poseStack,
            buffer.getBuffer(RenderType.entityCutout(getTextureLocation(entity))),
            packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityKinoko entity) {
        return ((byte) entity.getItemMetadata()) == 0 ? RED_TEX : BROWN_TEX;
    }
}
