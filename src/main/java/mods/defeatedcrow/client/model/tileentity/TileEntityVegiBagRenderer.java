package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.TileVegiBag;

public class TileEntityVegiBagRenderer implements BlockEntityRenderer<TileVegiBag> {
    // 1.7.10 used Tessellator flat quad with block icon (vegiBag 16x16) and sneaking offset, not a 3D model
    public TileEntityVegiBagRenderer(BlockEntityRendererProvider.Context ctx) {}

    @Override
    public void render(TileVegiBag be, float partialTicks, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        // TODO: restore flat icon rendering – avoid ModelBreads/baskets placeholder
    }
}
