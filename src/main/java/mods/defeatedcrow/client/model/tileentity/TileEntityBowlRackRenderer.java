package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.TileBowlRack;
import mods.defeatedcrow.client.model.model.ModelAltBowl;

public class TileEntityBowlRackRenderer implements BlockEntityRenderer<TileBowlRack> {
    private final ModelAltBowl model;
    private static final ResourceLocation TEX = new ResourceLocation("defeatedcrow", "textures/entity/x32alt/bowlrack_alt.png");

    public TileEntityBowlRackRenderer(BlockEntityRendererProvider.Context ctx) {
        // Use LayerDefinition bakeRoot directly for minimal implementation (no ModModelLayers)
        this.model = new ModelAltBowl(ModelAltBowl.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(TileBowlRack be, float partialTicks, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        int amo = be.getRemainByte() & 0xFF;
        // Clamp to 0..4 (BlockBowlRack max 4) and ensure at least 0 renders nothing for empty
        if (amo < 0) amo = 0;
        if (amo > 4) amo = 4;
        if (amo == 0) return;

        // Determine facing from block state if available; fallback to l=0 (south)
        int l = 0;
        if (be.getLevel() != null) {
            var state = be.getLevel().getBlockState(be.getBlockPos());
            // BlockBowlRack currently has no FACING property, future may add; try to read if present
            if (state.hasProperty(net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING)) {
                var dir = state.getValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING);
                l = switch (dir) {
                    case NORTH -> 2;
                    case SOUTH -> 0;
                    case WEST -> 1;
                    case EAST -> 3;
                    default -> 0;
                };
            }
        }
        float j = 0;
        if (l == 0) j = 180.0F;
        else if (l == 1) j = -90.0F;
        else if (l == 2) j = 0.0F;
        else if (l == 3) j = 90.0F;

        pose.pushPose();
        for (int i = 0; i < amo; i++) {
            float ajx = 0.5F;
            float ajz = 0.5F;
            if (l == 0) ajz = 0.15F + 0.25F * i;
            else if (l == 1) ajx = 0.85F - 0.25F * i;
            else if (l == 2) ajz = 0.10F + 0.25F * i;
            else if (l == 3) ajx = 0.9F - 0.25F * i;

            pose.pushPose();
            // translate to bowl position (ajx, 1.5, ajz) relative to block origin, then adjust for model center
            pose.translate(ajx, 1.5, ajz);
            pose.scale(1.0F, -1.0F, -1.0F);
            pose.mulPose(com.mojang.math.Axis.YP.rotationDegrees(j));
            VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(TEX));
            this.model.renderToBuffer(pose, vc, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
            pose.popPose();
        }
        pose.popPose();
    }
}
