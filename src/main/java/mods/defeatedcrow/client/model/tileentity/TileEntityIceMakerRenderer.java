package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.appliance.TileIceMaker;

/**
 * 1.20.1 port of the 1.7.10 TESR (was: extends the legacy 1.7.10 TESR + GL11 immediate mode).
 *
 * <p>Original geometry: {@link mods.defeatedcrow.client.model.model.ModelIceMaker}
 * (ModelBase-based, owned by client/model/model - not yet converted to LayerDefinition/ModelPart).</p>
 */
public class TileEntityIceMakerRenderer implements BlockEntityRenderer<TileIceMaker> {

    private static final ResourceLocation ICE_TEX = new ResourceLocation(
        "defeatedcrow:textures/entity/icemaker.png");


    private final BlockEntityRendererProvider.Context context;

    public TileEntityIceMakerRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(TileIceMaker tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
            int packedLight, int packedOverlay) {
        // Old GL11 chain: translate(x + 0.5, y + 1.5, z + 0.5); scale(1, -1, -1);
        // rotate(yaw from direction byte/metadata around Y); bindTexture(...);
        // model.render(null, 0, 0, 0, yaw, 0, 0.0625F);
        poseStack.pushPose();
        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.scale(1.0F, -1.0F, -1.0F);

        // TODO: restore ModelIceMaker rendering via
        // VertexConsumer vc = bufferSource.getBuffer(Sheets.cutoutBlockSheet());
        // (blended/translucent parts: Sheets.translucentCullBlockSheet()).
        poseStack.popPose();
    }
}
