package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.appliance.TileMakerNext;
import mods.defeatedcrow.client.model.model.ModelMakerNext;

public class TileEntityMakerNextRenderer implements BlockEntityRenderer<TileMakerNext> {
    private final ModelMakerNext model;
    // 1.7.10: TileMakerNext.getCurrentTexture() returned blocks/contents_*.png dynamically (milk/water/tea etc.)
    // 1.20.1 stub TileMakerNext has no logic yet (WT-B), so use generic milk contents as fallback until functional migration merges
    private static final ResourceLocation TEX = new ResourceLocation("defeatedcrow", "textures/block/contents_milk.png");

    public TileEntityMakerNextRenderer(BlockEntityRendererProvider.Context ctx) {
        // Use LayerDefinition bakeRoot directly for minimal implementation (no ModModelLayers)
        this.model = new ModelMakerNext(ModelMakerNext.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(TileMakerNext be, float partialTicks, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        pose.pushPose();
        pose.translate(0.5, 1.5, 0.5);
        pose.scale(1.0F, -1.0F, -1.0F);
        // Restore 1.7.10 blend/color (milked ? 2.0 : 1.2 alpha) – keep simple opaque for now, use translucent for water-like
        VertexConsumer vc = buffers.getBuffer(RenderType.entityTranslucent(TEX));
        this.model.renderToBuffer(pose, vc, light, overlay, 1.0F, 1.0F, 1.0F, 0.9F);
        pose.popPose();
    }
}
