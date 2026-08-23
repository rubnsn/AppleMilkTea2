package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

import mods.defeatedcrow.common.tile.appliance.TilePanG;

/**
 * 1.20.1 port of the 1.7.10 TESR (was: extends the legacy 1.7.10 TESR + ModelBase + Tessellator quads).
 *
 * <p>Original rendered ModelPanHandle for the pan body plus up to three stacked contents quads
 * (rice layers at y 0.3 / 0.2 / 0.1) textured from a runtime texPass texture
 * ({@code contents_rice} base).</p>
 */
public class TileEntityPanGRenderer implements BlockEntityRenderer<TilePanG> {

    public static TileEntityPanGRenderer panRenderer;

    private final BlockEntityRendererProvider.Context context;

    public TileEntityPanGRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
        panRenderer = this;
    }

    @Override
    public void render(TilePanG tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
            int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5D, 1.0D, 0.5D);
        poseStack.scale(1.0F, -1.0F, -1.0F);

        // TODO(WT-B): get the fill amount/type from the migrated BlockEntity to pick layer count
        // (old code stacked quads at y 0.3D / 0.2D / 0.1D) and rebuild contentsTex (texPass).
        // TODO(WT-A): fetch the contents sprite from the block atlas:
        //   TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
        //       .apply(new ResourceLocation("defeatedcrow:block/contents_rice")).get();
        //   VertexConsumer vc = bufferSource.getBuffer(Sheets.cutoutBlockSheet());
        // then emit the 0.21..0.79 square top-face quads via vc.vertex(...).
        // Pan body geometry: see client/model/model.ModelPanHandle (unconverted).

        poseStack.popPose();
    }
}
