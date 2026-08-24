package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.appliance.TileTeppanII;

public class TileEntityTeppanIIRenderer implements BlockEntityRenderer<TileTeppanII> {
    // 1.7.10 used EntityItem rendering (RenderItem) with cookTime rotation, not a static model
    // Stub uses no model until item rendering is reimplemented in other worktree
    public TileEntityTeppanIIRenderer(BlockEntityRendererProvider.Context ctx) {}

    @Override
    public void render(TileTeppanII be, float partialTicks, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        // TODO: restore EntityItem rendering (plateItems[0/1/2] + rotation) – avoid ModelBreads placeholder that caused bread collapse
    }
}
