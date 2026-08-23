package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

import mods.defeatedcrow.common.tile.TileCardBoard;

/**
 * 1.20.1 port of the 1.7.10 TESR (was: extends the legacy 1.7.10 TESR + Tessellator quads).
 *
 * <p>Original drew a single textured quad from the old block icon
 * ({@code DCsAppleMilk.cardboard.getIcon(1, meta)}) on the atlas-bound TextureMap, oriented by
 * {@code getDirectionByte()} (dir 0/2 vs others), at y offset +0.5 with mirrored Y/Z scale.</p>
 */
public class TileEntityCardBoardRenderer implements BlockEntityRenderer<TileCardBoard> {

    public static TileEntityCardBoardRenderer boxRenderer;

    private final BlockEntityRendererProvider.Context context;

    public TileEntityCardBoardRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
        boxRenderer = this;
    }

    @Override
    public void render(TileCardBoard tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
            int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.0D, 0.5D, 0.0D);
        poseStack.scale(1.0F, -1.0F, -1.0F);

        // TODO(WT-A): the quad sprite was the cardboard block icon per metadata. In 1.20.1 fetch it
        // from the block atlas, e.g.
        //   TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
        //       .apply(new ResourceLocation("defeatedcrow:block/cardboard_<meta>")).get();
        //   VertexConsumer vc = bufferSource.getBuffer(Sheets.cutoutBlockSheet());
        // then emit the quad via vc.vertex(pose, x, y, z).color(u, v, overlay, light, normal).
        // Orientation (dir 0/2 vs 1/3 UV flip) must be restored together with the blockstate.

        poseStack.popPose();
    }
}
