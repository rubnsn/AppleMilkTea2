package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.TileLargeBottle;

/**
 * 1.20.1 port of the 1.7.10 TESR (was: extends the legacy 1.7.10 TESR + GL11 immediate mode).
 *
 * <p>Original geometry: {@link mods.defeatedcrow.client.model.model.ModelLargeBottle}
 * (ModelBase-based, owned by client/model/model  Enot yet converted). The bottle texture was
 * swapped at runtime depending on the distilled content (old {@code texPass} string build).</p>
 */
public class TileEntityBottleRenderer implements BlockEntityRenderer<TileLargeBottle> {

    private static ResourceLocation bottleTex = new ResourceLocation(
        "defeatedcrow:textures/entity/largebottle.png");

    private final BlockEntityRendererProvider.Context context;

    public TileEntityBottleRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(TileLargeBottle tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
            int packedLight, int packedOverlay) {
        // Old code read tile.getBlockMetadata() & 15 to select contents/yaw and rebuilt bottleTex
        // dynamically (texPass). Old GL11 chain: translate(x + 0.5, y + 1.5, z + 0.5); scale(1,-1,-1);
        // bindTexture(bottleTex); model.render(null, ..., yaw, 0.0F, 0.0625F);
        poseStack.pushPose();
        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.scale(1.0F, -1.0F, -1.0F);

        // TODO: restore ModelLargeBottle rendering via
        // bufferSource.getBuffer(Sheets.translucentCullBlockSheet()) for the glass body
        // (old glass was blended) + cutout sheet for labels; texture selected per content.
        // Re-wire texture selection with the WT-B BlockEntity API.
        poseStack.popPose();
    }
}
