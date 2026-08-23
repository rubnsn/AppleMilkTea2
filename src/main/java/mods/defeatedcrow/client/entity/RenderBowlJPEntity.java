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
import mods.defeatedcrow.client.model.model.ModelBowlJP;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.entity.edible.PlaceableBowlJP;
import mods.defeatedcrow.handler.Util;

/**
 * 1.20.1 migration: Render -> EntityRenderer + PoseStack/MultiBufferSource.
 * The former block-icon (block-icon) flat quads for the bowl contents are replaced by
 * renderSingleBlock of the corresponding block.
 * TODO(WT-A): DCsAppleMilk.bowlJP is pending DeferredRegister migration; the
 * registry lookup below must be synced with WT-A's final registry name.
 */
public class RenderBowlJPEntity extends EntityRenderer<PlaceableBowlJP> {

    private static final ResourceLocation BOWL_JP_TEX1 = new ResourceLocation(
        Util.getEntityTexturePassNoAlt() + "bowlJP_sakura.png");
    private static final ResourceLocation BOWL_JP_TEX2 = new ResourceLocation(
        Util.getEntityTexturePassNoAlt() + "bowlJP_bluepattern.png");
    private static final ResourceLocation BOWL_JP_TEX3 = new ResourceLocation(
        Util.getEntityTexturePassNoAlt() + "bowlJP_whiteporcelain.png");

    private final ModelBowlJP model;

    public RenderBowlJPEntity(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.5F;
        this.model = new ModelBowlJP(ctx.bakeLayer(ModEntityRenderers.MODEL_BOWL_JP));
    }

    @Override
    public void render(PlaceableBowlJP entity, float yaw, float partialTick, PoseStack poseStack,
        MultiBufferSource buffer, int packedLight) {
        int l = entity.getItemMetadata();

        // bowl
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.25F, 0.0F);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        this.model.render(poseStack, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(entity))),
            packedLight, OverlayTexture.NO_OVERLAY, (byte) l);
        poseStack.popPose();

        // contents: former block-icon quads of DCsAppleMilk.bowlJP meta texture
        if (l != 15) {
            // TODO(WT-A): resolve per-meta BlockState from WT-A's bowlJP registration
            net.minecraft.world.level.block.Block bowlJP = net.minecraftforge.registries.ForgeRegistries.BLOCKS
                .getValue(new ResourceLocation(DCsAppleMilk.MODID, "bowljp"));
            net.minecraft.world.level.block.state.BlockState state = bowlJP != null
                ? bowlJP.defaultBlockState()
                : net.minecraft.world.level.block.Blocks.SNOW.defaultBlockState();
            poseStack.pushPose();
            poseStack.translate(0.0F, 0.5F, 0.0F);
            poseStack.scale(1.0F, -1.0F, -1.0F);
            poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
            net.minecraft.client.Minecraft.getInstance().getBlockRenderer()
                .renderSingleBlock(state, poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
            poseStack.popPose();
        }
        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(PlaceableBowlJP entity) {
        if (Util.getCupRender() == 1) return BOWL_JP_TEX1;
        else if (Util.getCupRender() == 2) return BOWL_JP_TEX2;
        return BOWL_JP_TEX3;
    }
}
