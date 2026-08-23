package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import mods.defeatedcrow.common.tile.appliance.TileTeppanII;

/**
 * 1.20.1 port of the 1.7.10 TESR (was: extends the legacy 1.7.10 TESR +
 * RenderManager.renderEntityWithPosYaw of a fake EntityItem).
 *
 * <p>The old {@code RenderItem.renderInFrame} hack is replaced with
 * {@link net.minecraft.client.renderer.entity.ItemRenderer#renderStatic}. The old
 * "fancy graphics off" double-render (mirrored copy) is intentionally dropped.</p>
 */
public class TileEntityTeppanIIRenderer implements BlockEntityRenderer<TileTeppanII> {

    public static TileEntityTeppanIIRenderer teppanRenderer;

    private final BlockEntityRendererProvider.Context context;

    public TileEntityTeppanIIRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
        teppanRenderer = this;
    }

    @Override
    public void render(TileTeppanII tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
            int packedLight, int packedOverlay) {
        if (tile.getLevel() == null) return;

        ItemStack item = tile.isFailed() ? tile.plateItems[2]
                : tile.isFinishCooking() ? tile.plateItems[1]
                : tile.plateItems[0];
        if (item == null || item.isEmpty()) return;

        int cookTime = Mth.clamp(tile.getCookTime(), 0, 360);

        poseStack.pushPose();
        poseStack.translate(0.5D, 0.15D, 0.5D);
        poseStack.mulPose(Axis.YP.rotationDegrees(cookTime));

        float scale = item.getItem() instanceof BlockItem ? 1.2F : 0.8F;
        poseStack.scale(scale, scale, scale);

        // Old code rendered a mirrored second copy when fancy graphics was off; omitted.
        this.context.getItemRenderer().renderStatic(item, ItemDisplayContext.GROUND,
            packedLight, packedOverlay, poseStack, bufferSource, tile.getLevel(), 0);

        poseStack.popPose();
    }
}
