package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.TileAlcoholCup;

/**
 * 1.20.1 port of the 1.7.10 TESR (was: extends the legacy 1.7.10 TESR + GL11 immediate mode).
 *
 * <p>Original geometry: {@link mods.defeatedcrow.client.model.model.ModelAlcoholCup}
 * (ModelBase-based, owned by client/model/model  Enot yet converted).</p>
 */
public class TileEntityAlcoholCupRenderer implements BlockEntityRenderer<TileAlcoholCup> {

    private static final ResourceLocation COCKTAIL_TEX = new ResourceLocation(
        "defeatedcrow:textures/entity/cocktail.png");

    private final BlockEntityRendererProvider.Context context;

    public TileEntityAlcoholCupRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(TileAlcoholCup tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
            int packedLight, int packedOverlay) {
        // Old code read tile.getBlockMetadata() (& 3) to pick the yaw.
        // Old GL11 chain: translate(x + 0.5, y + 1.5, z + 0.5); scale(1,-1,-1);
        // bindTexture(COCKTAIL_TEX); model.render(null, ..., yaw, 0.0F, 0.0625F);
        poseStack.pushPose();
        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.scale(1.0F, -1.0F, -1.0F);

        // TODO: restore ModelAlcoholCup rendering via
        // bufferSource.getBuffer(Sheets.cutoutBlockSheet()), texture COCKTAIL_TEX.
        poseStack.popPose();
    }
}
