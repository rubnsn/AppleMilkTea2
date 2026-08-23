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
import mods.defeatedcrow.client.entity.base.ModelWoodBowl;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.entity.edible.PlaceableBowl;

/**
 * 1.20.1 migration: Render -> EntityRenderer + PoseStack/MultiBufferSource.
 * The former block-icon (block-icon) flat quads for the bowl contents are replaced by
 * renderSingleBlock of the corresponding block.
 * TODO(WT-A): DCsAppleMilk.bowlBlock is pending DeferredRegister migration; the
 * registry lookup below must be synced with WT-A's final registry name and the
 * per-meta state should come from the entity's synced ItemStack.
 */
public class RenderBowlEntity extends EntityRenderer<PlaceableBowl> {

    private static final ResourceLocation WOOD_TEX = new ResourceLocation("defeatedcrow", "textures/entity/woodbowl.png");

    private final ModelWoodBowl model;

    public RenderBowlEntity(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.5F;
        this.model = new ModelWoodBowl(ctx.bakeLayer(ModEntityRenderers.MODEL_BOWL_WOOD));
    }

    @Override
    public void render(PlaceableBowl entity, float yaw, float partialTick, PoseStack poseStack,
        MultiBufferSource buffer, int packedLight) {
        int l = entity.getItemMetadata();

        // bowl
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.25F, 0.0F);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        this.model.render(poseStack, buffer.getBuffer(RenderType.entityCutout(WOOD_TEX)),
            packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();

        // contents: former block-icon quads of DCsAppleMilk.bowlBlock meta texture
        if (l != 15) {
            // TODO(WT-A): resolve per-meta BlockState from WT-A's bowlBlock registration
            net.minecraft.world.level.block.Block bowl = net.minecraftforge.registries.ForgeRegistries.BLOCKS
                .getValue(new ResourceLocation(DCsAppleMilk.MODID, "bowl"));
            net.minecraft.world.level.block.state.BlockState state = bowl != null
                ? bowl.defaultBlockState()
                : net.minecraft.world.level.block.Blocks.BOWL.defaultBlockState();
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
    public ResourceLocation getTextureLocation(PlaceableBowl entity) {
        return WOOD_TEX;
    }
}
