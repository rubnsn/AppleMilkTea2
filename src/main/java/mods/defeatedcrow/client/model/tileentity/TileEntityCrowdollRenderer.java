package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import mods.defeatedcrow.common.tile.TileCrowDoll;

/**
 * 1.20.1 port of the 1.7.10 TESR (was: extends the legacy 1.7.10 TESR + ModelCrowDoll +
 * RenderManager.renderEntityWithPosYaw of a fake EntityItem for the arrow).
 *
 * <p>Original geometry: {@link mods.defeatedcrow.client.model.model.ModelCrowDoll}
 * (ModelBase-based, owned by client/model/model  Enot yet converted). The doll body
 * ({@code render}) and base ({@code renderBase}) were drawn with a yaw from metadata
 * (0->180F, 1->-90F, 2->0F, 3->90F) and a height offset {@code 0.05F * tile.range}.</p>
 */
public class TileEntityCrowdollRenderer implements BlockEntityRenderer<TileCrowDoll> {

    public static TileEntityCrowdollRenderer dollRenderer;

    private final BlockEntityRendererProvider.Context context;

    public TileEntityCrowdollRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
        dollRenderer = this;
    }

    @Override
    public void render(TileCrowDoll tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource,
            int packedLight, int packedOverlay) {
        if (tile.getLevel() == null) return;

        // TODO: restore ModelCrowDoll#render / #renderBase via
        // VertexConsumer vc = bufferSource.getBuffer(Sheets.cutoutBlockSheet())
        // ("defeatedcrow:textures/entity/crowdoll.png"), with the legacy transform chain:
        //   translate(x + 0.5, y + 1.5 + 0.05*range, z + 0.5); scale(1,-1,-1); rotate(yaw, Y);
        //   render(...); then base at y + 1.5 without height offset.

        // Arrow pointer item (old: fake EntityItem of Items.arrow at y + 0.75 + 0.05*range,
        // scale 1.2, yaw + 180, shifted -0.15 on X):
        poseStack.pushPose();
        poseStack.translate(0.35D, 0.75D + 0.05D * tile.range, 0.5D);
        poseStack.scale(1.2F, -1.2F, -1.2F);

        ItemStack arrow = new ItemStack(Items.ARROW, 1);
        this.context.getItemRenderer().renderStatic(arrow, ItemDisplayContext.GROUND,
            packedLight, packedOverlay, poseStack, bufferSource, tile.getLevel(), 0);

        poseStack.popPose();
    }
}
