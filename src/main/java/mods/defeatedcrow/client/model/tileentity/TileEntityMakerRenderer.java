package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.TileMakerHandle;
import mods.defeatedcrow.client.model.model.ModelMakerHandle;

public class TileEntityMakerRenderer implements BlockEntityRenderer<TileMakerHandle> {
    private final ModelMakerHandle model;
    private static final ResourceLocation TEX = new ResourceLocation("defeatedcrow", "textures/block/porcelain.png");

    public TileEntityMakerRenderer(BlockEntityRendererProvider.Context ctx) {
        // Use LayerDefinition bakeRoot directly for minimal implementation (no ModModelLayers)
        this.model = new ModelMakerHandle(ModelMakerHandle.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(TileMakerHandle be, float partialTicks, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        byte dir = be.getDirectionByte();
        float j = 0;
        if (dir == 0) j = 180.0F;
        else if (dir == 1) j = -90.0F;
        else if (dir == 2) j = 0.0F;
        else if (dir == 4) j = 90.0F;
        pose.pushPose();
        // 1.7.10: translate(par1, par2+1, par3+1) -> 0.5,1.5,0.5 with extra 0.5,0.5,0.5 and -1 on Y
        pose.translate(0.5, 1.5, 0.5);
        pose.mulPose(com.mojang.math.Axis.YP.rotationDegrees(j));
        pose.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(TEX));
        this.model.renderToBuffer(pose, vc, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        pose.popPose();
    }
}
