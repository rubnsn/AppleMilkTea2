package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

import mods.defeatedcrow.api.appliance.SoupType;
import mods.defeatedcrow.common.tile.appliance.TileFilledSoupPan;

/**
 * 1.20.1 port of the 1.7.10 TESR (was: extends the legacy 1.7.10 TESR + Tessellator quads).
 *
 * <p>Original drew a horizontal contents quad at height y (per SoupType) textured with the old
 * block icon {@code DCsAppleMilk.filledSoupPan.getIcon(1, type.id)} on the atlas-bound
 * TextureMap, plus the ModelPanHandle pan body.</p>
 */
public class TileEntitySoupPanRenderer implements BlockEntityRenderer<TileFilledSoupPan> {

    public static TileEntitySoupPanRenderer panRenderer;

    private final BlockEntityRendererProvider.Context context;

    public TileEntitySoupPanRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
        panRenderer = this;
    }

    @Override
    public void render(TileFilledSoupPan tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
            int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5D, 1.0D, 0.5D);
        poseStack.scale(1.0F, -1.0F, -1.0F);

        // TODO(WT-B): get the current SoupType from the migrated BlockEntity and restore the
        // per-type quad height (old y values: 0.3D / 0.2D / 0.1D by type).
        // TODO(WT-A): the sprite was DCsAppleMilk.filledSoupPan.getIcon(1, type.id); in 1.20.1 fetch
        // the matching atlas sprite, e.g.
        //   TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
        //       .apply(new ResourceLocation("defeatedcrow:block/contents/soup_<type>")).get();
        //   VertexConsumer vc = bufferSource.getBuffer(Sheets.translucentCullBlockSheet());
        // then emit the top-face quad (0.16..0.84 square) via vc.vertex(...).
        // Pan body geometry: see client/model/model.ModelPanHandle (unconverted).

        poseStack.popPose();
    }
}
