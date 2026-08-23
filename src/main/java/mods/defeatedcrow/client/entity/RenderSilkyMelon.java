package mods.defeatedcrow.client.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import mods.defeatedcrow.client.ModEntityRenderers;
import mods.defeatedcrow.client.model.model.ModelMelonBomb;
import mods.defeatedcrow.common.entity.EntitySilkyMelon;

/**
 * 1.20.1 migration: Render -> EntityRenderer + PoseStack/MultiBufferSource.
 */
public class RenderSilkyMelon extends EntityRenderer<EntitySilkyMelon> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("defeatedcrow", "textures/entity/compressedmelon_silky.png");

    private final ModelMelonBomb modelMelonBomb;

    public RenderSilkyMelon(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.5F;
        this.modelMelonBomb = new ModelMelonBomb(ctx.bakeLayer(ModEntityRenderers.MODEL_SILKY_MELON));
    }

    @Override
    public void render(EntitySilkyMelon entity, float yaw, float partialTick, PoseStack poseStack,
        MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - yaw));
        float f2 = (float) entity.getTimeSinceHit() - partialTick;
        float f3 = entity.getDamageTaken() - partialTick;

        if (f3 < 0.0F) {
            f3 = 0.0F;
        }

        if (f2 > 0.0F) {
            poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f2) * f2 * f3 / 10.0F
                * (float) entity.getForwardDirection()));
        }

        poseStack.scale(-1.0F, -1.0F, 1.0F);
        this.modelMelonBomb.render(poseStack, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(entity))),
            packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySilkyMelon entity) {
        return TEXTURE;
    }
}
