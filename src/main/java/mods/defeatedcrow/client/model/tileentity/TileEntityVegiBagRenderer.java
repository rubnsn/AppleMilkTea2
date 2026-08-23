package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

import mods.defeatedcrow.common.tile.TileVegiBag;

/**
 * 1.20.1 port of the 1.7.10 TESR (was: extends the legacy 1.7.10 TESR + Tessellator quads).
 *
 * <p>Original drew quads from the old block icon
 * ({@code DCsAppleMilk.vegiBag.getIcon(1, meta)}) on the atlas-bound TextureMap, oriented by
 * {@code getDirectionByte()} and lifted by {@code getSneaking()} (y 0.5 vs 0.0).</p>
 */
public class TileEntityVegiBagRenderer implements BlockEntityRenderer<TileVegiBag> {

    public static TileEntityVegiBagRenderer bagRenderer;

    private final BlockEntityRendererProvider.Context context;

    public TileEntityVegiBagRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
        bagRenderer = this;
    }

    @Override
    public void render(TileVegiBag tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
            int packedLight, int packedOverlay) {
        // Old: float fY = tile.getSneaking() ? 0.0F : 0.5F; then translate(x, y + fY, z); scale(1,-1,-1).
        // TODO(WT-B): re-expose the sneaking flag on the migrated BlockEntity (e.g. a blockstate
        // property or BE field) before restoring it here.
        poseStack.pushPose();
        poseStack.translate(0.0D, 0.5D, 0.0D);
        poseStack.scale(1.0F, -1.0F, -1.0F);

        // TODO(WT-A): the quad sprite was the vegiBag block icon per metadata. In 1.20.1 fetch it
        // from the block atlas, e.g.
        //   TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
        //       .apply(new ResourceLocation("defeatedcrow:block/vegibag_<meta>")).get();
        //   VertexConsumer vc = bufferSource.getBuffer(Sheets.cutoutBlockSheet());
        // then emit the four direction-dependent quads via vc.vertex(...).

        poseStack.popPose();
    }
}
