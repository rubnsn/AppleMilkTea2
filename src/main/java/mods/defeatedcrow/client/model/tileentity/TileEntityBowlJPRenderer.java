package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.TileJPBowl;

/**
 * 1.20.1 port of the 1.7.10 TESR (was: extends the legacy 1.7.10 TESR + GL11 immediate mode).
 *
 * <p>Original geometry: {@link mods.defeatedcrow.client.model.model.ModelBowlJP}
 * (ModelBase-based, owned by client/model/model  Enot yet converted).</p>
 */
public class TileEntityBowlJPRenderer implements BlockEntityRenderer<TileJPBowl> {

    private static final ResourceLocation BOWL_JP_TEX_1 = new ResourceLocation(
        "defeatedcrow:textures/entity/bowlJP_sakura.png");
    private static final ResourceLocation BOWL_JP_TEX_2 = new ResourceLocation(
        "defeatedcrow:textures/entity/bowlJP_bluepattern.png");
    private static final ResourceLocation BOWL_JP_TEX_3 = new ResourceLocation(
        "defeatedcrow:textures/entity/bowlJP_whiteporcelain.png");

    private final BlockEntityRendererProvider.Context context;

    public TileEntityBowlJPRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(TileJPBowl tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
            int packedLight, int packedOverlay) {
        // Old code read tile.getBlockMetadata() to select one of three textures and the yaw.
        // Old GL11 chain: translate(x + 0.5, y + 1.0/1.5, z + 0.5); scale(1,-1,-1);
        // bindTexture(selected); model.render(null, ..., yaw, 0.0F, 0.0625F);
        poseStack.pushPose();
        poseStack.translate(0.5D, 1.0D, 0.5D);
        poseStack.scale(1.0F, -1.0F, -1.0F);

        // TODO: restore ModelBowlJP rendering via
        // bufferSource.getBuffer(Sheets.cutoutBlockSheet()), texture per metadata variant
        // (BOWL_JP_TEX_1 / _2 / _3).
        poseStack.popPose();
    }
}
