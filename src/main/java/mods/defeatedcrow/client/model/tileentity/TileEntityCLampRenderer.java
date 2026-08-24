package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.TileCLamp;
import mods.defeatedcrow.client.model.model.ModelCLamp;

public class TileEntityCLampRenderer implements BlockEntityRenderer<TileCLamp> {
    private final ModelCLamp model;
    private static final ResourceLocation DTex = new ResourceLocation("defeatedcrow", "textures/entity/x32/lamp_embrion.png");
    private static final ResourceLocation RTex = new ResourceLocation("defeatedcrow", "textures/entity/x32/lamp_r13a.png");
    private static final ResourceLocation GTex = new ResourceLocation("defeatedcrow", "textures/entity/x32/lamp_markiii.png");
    private static final ResourceLocation ITex = new ResourceLocation("defeatedcrow", "textures/entity/x32/lamp_sword.png");

    public TileEntityCLampRenderer(BlockEntityRendererProvider.Context ctx) {
        this.model = new ModelCLamp(ModelCLamp.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(TileCLamp be, float partialTicks, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        // Determine lamp type: 1.7.10 used block metadata (0-15). 1.20.1 block has no color property, default to 8 (blue embrion) for now.
        // TODO: store color in BlockState / TileCLamp NBT to support all 4 variants. For now blue (8) fixes the reported issue.
        byte b0 = 8;
        // Try to infer from block state if possible (if block has variant property in future)
        // For now, if the block is chalcedony_lamp, use 8 (blue). Other lamps (opaque etc) would be different blocks, but TileCLamp only for chalcedony_lamp.
        float k = 0; // angle rad
        try {
            // If TileCLamp had rad, use it; currently stub has no method, keep 0
            // k = be.getAngle();
        } catch (Exception e) {}
        byte direction = 0; // TODO: from BlockState HORIZONTAL_FACING if added
        float j = 0;
        if (direction == 0) j = 180.0F;
        else if (direction == 1) j = -90.0F;
        else if (direction == 2) j = 0.0F;
        else if (direction == 4) j = 90.0F;

        ResourceLocation tex;
        if (b0 == 8) tex = DTex;
        else if (b0 == 9) tex = RTex;
        else if (b0 == 10) tex = GTex;
        else tex = ITex;

        // Pass 1: opaque parts (blades etc for 9/11) - original used same tex
        pose.pushPose();
        pose.translate(0.5, 1.5, 0.5);
        pose.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(tex));
        model.render(pose, vc, light, overlay, 1, 1, 1, 1, b0, k);
        pose.popPose();

        // Pass 2: glow (additive)
        pose.pushPose();
        pose.translate(0.5, 1.5, 0.5);
        pose.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer vcGlow = buffers.getBuffer(RenderType.entityCutout(tex));
        model.renderGlow(pose, vcGlow, light, overlay, 1, 1, 1, 1, b0);
        pose.popPose();

        // Pass 3: lucent / translucent (body etc for 8) with blend
        pose.pushPose();
        pose.translate(0.5, 1.5, 0.5);
        pose.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer vcLucent = buffers.getBuffer(RenderType.entityTranslucent(tex));
        model.renderLucent(pose, vcLucent, light, overlay, 1, 1, 1, 0.6F, b0, k);
        pose.popPose();
    }
}
