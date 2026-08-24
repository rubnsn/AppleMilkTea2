package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.TileEggs;
import mods.defeatedcrow.client.model.model.ModelEggs;

public class TileEntityEggsRenderer implements BlockEntityRenderer<TileEggs> {
    private final ModelEggs model;
    private static final ResourceLocation TEX_WHITE = new ResourceLocation("defeatedcrow", "textures/block/whitepanel.png");
    private static final ResourceLocation TEX_BLACK = new ResourceLocation("defeatedcrow", "textures/block/teppann.png");

    public TileEntityEggsRenderer(BlockEntityRendererProvider.Context ctx) {
        // Use LayerDefinition bakeRoot directly for minimal implementation (no ModModelLayers)
        this.model = new ModelEggs(ModelEggs.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(TileEggs be, float partialTicks, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        // 1.7.10 used whitepanel/teppann based on blockMetadata &1
        ResourceLocation tex = TEX_WHITE;
        if (be.getLevel() != null) {
            var state = be.getLevel().getBlockState(be.getBlockPos());
            // fallback to level check not needed; use BE's remain? For now default white
        }
        pose.pushPose();
        pose.translate(0.5, 1.5, 0.5);
        pose.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(tex));
        this.model.renderToBuffer(pose, vc, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        pose.popPose();
    }
}
