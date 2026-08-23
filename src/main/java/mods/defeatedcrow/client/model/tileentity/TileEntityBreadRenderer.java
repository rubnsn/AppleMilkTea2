package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.TileBread;

/**
 * 1.20.1 port of the 1.7.10 TESR (was: extends the legacy 1.7.10 TESR + GL11 immediate mode).
 *
 * <p>Original geometry: {@link mods.defeatedcrow.client.model.model.ModelBasketL}
 * (ModelBase-based, owned by client/model/model - not yet converted to LayerDefinition/ModelPart).</p>
 */
public class TileEntityBreadRenderer implements BlockEntityRenderer<TileBread> {

    private static final ResourceLocation BREAD_TEX = new ResourceLocation(
        "defeatedcrow:textures/entity/breads.png");
    private static final ResourceLocation BREAD_TEX_2 = new ResourceLocation(
        "defeatedcrow:textures/entity/breads2.png");
    private static final ResourceLocation BREAD_TEX_3 = new ResourceLocation(
        "defeatedcrow:textures/entity/breads3.png");
    private static final ResourceLocation BREAD_ALT_TEX = new ResourceLocation(
        "defeatedcrow:textures/entity/breadalt.png");
    private static final ResourceLocation BREAD_ALT_TEX_2 = new ResourceLocation(
        "defeatedcrow:textures/entity/breadalt2.png");
    private static final ResourceLocation BREAD_ALT_TEX_3 = new ResourceLocation(
        "defeatedcrow:textures/entity/breadalt3.png");
    private static final ResourceLocation BASKET_TEX = new ResourceLocation(
        "defeatedcrow:textures/entity/baskets.png");
    private static final ResourceLocation BOTTLE_BASKET_TEX = new ResourceLocation(
        "defeatedcrow:textures/entity/bottlebasket.png");


    private final BlockEntityRendererProvider.Context context;

    public TileEntityBreadRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(TileBread tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
            int packedLight, int packedOverlay) {
        // Original selected among breads/breadalt/baskets/bottlebasket textures by metadata,
        // all under the Util.getEntityTexturePassNoAlt() prefix.
        // Old GL11 chain: translate(x + 0.5, y + 1.5, z + 0.5); scale(1, -1, -1);
        // rotate(yaw from direction byte/metadata around Y); bindTexture(...);
        // model.render(null, 0, 0, 0, yaw, 0, 0.0625F);
        poseStack.pushPose();
        poseStack.translate(0.5D, 1.5D, 0.5D);
        poseStack.scale(1.0F, -1.0F, -1.0F);

        // TODO: restore ModelBasketL rendering via
        // VertexConsumer vc = bufferSource.getBuffer(Sheets.cutoutBlockSheet());
        // (blended/translucent parts: Sheets.translucentCullBlockSheet()).
        poseStack.popPose();
    }
}
