package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import mods.defeatedcrow.common.block.container.BlockContainerBase;
import mods.defeatedcrow.common.block.container.BlockContainerBase;
import mods.defeatedcrow.common.tile.TileContainerBase;

/**
 * 1.20.1 port of the 1.7.10 TESR (was: extends the legacy 1.7.10 TESR +
 * RenderManager.renderEntityWithPosYaw of a fake EntityItem).
 *
 * <p>The old {@code RenderItem.renderInFrame} hack is replaced with
 * {@link net.minecraft.client.renderer.entity.ItemRenderer#renderStatic}. The old
 * "fancy graphics off" double-render (mirrored copy) is intentionally dropped.</p>
 */
public class TileEntityContainerBaseRenderer implements BlockEntityRenderer<TileContainerBase> {

    public static TileEntityContainerBaseRenderer thisRenderer;

    private final BlockEntityRendererProvider.Context context;

    public TileEntityContainerBaseRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
        thisRenderer = this;
    }

    @Override
    public void render(TileContainerBase tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
            int packedLight, int packedOverlay) {
        if (tile.getLevel() == null) return;

        BlockState state = tile.getBlockState();
        if (!(state.getBlock() instanceof BlockContainerBase)) return;

        ItemStack item = ((BlockContainerBase) state.getBlock()).returnItem();
        if (item == null || item.isEmpty()) return;

        // TODO(WT-A/WT-B): old getBlockMetadata() semantics  Emeta & 7 = item count,
        // meta > 7 = "side" layout flag. Re-derive both from the migrated BlockState
        // properties once container blocks land; until then nothing is rendered.
        int rem = 0;
        boolean side = false;
        if (rem <= 0) return;

        for (int i = 0; i <= rem; i++) {
            float f1 = i * 0.1F;
            float f2 = (i & 1) * 0.4F;

            poseStack.pushPose();
            if (side) {
                poseStack.translate(0.125F + f1, 0.35F, 0.3F + f2);
                poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(90.0F));
            } else {
                poseStack.translate(0.3F + f2, 0.35F, 0.125F + f1);
            }

            float scale = item.getItem() instanceof BlockItem ? 1.2F : 1.2F;
            poseStack.scale(scale, scale, scale);

            this.context.getItemRenderer().renderStatic(item, ItemDisplayContext.GROUND,
                packedLight, packedOverlay, poseStack, bufferSource, tile.getLevel(), 0);

            poseStack.popPose();
        }
    }
}
