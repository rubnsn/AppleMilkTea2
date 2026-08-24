package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.TileCordial;
import mods.defeatedcrow.client.model.model.ModelCordial;

public class TileEntityCordialRenderer implements BlockEntityRenderer<TileCordial> {
    private final ModelCordial model;
    private static final ResourceLocation TEX = new ResourceLocation("defeatedcrow", "textures/entity/cordial.png");

    public TileEntityCordialRenderer(BlockEntityRendererProvider.Context ctx) {
        // Use LayerDefinition bakeRoot directly for minimal implementation (no ModModelLayers)
        this.model = new ModelCordial(ModelCordial.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(TileCordial be, float partialTicks, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        pose.pushPose();
        pose.translate(0.5, 1.5, 0.5);
        pose.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(TEX));
        // Fallback to entityCutout if texture missing, will show missing but compile
        this.model.renderToBuffer(pose, vc, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        pose.popPose();
    }
}
