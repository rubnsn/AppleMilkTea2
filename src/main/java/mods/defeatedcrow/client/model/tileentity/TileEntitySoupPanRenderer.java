package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.appliance.TileFilledSoupPan;
import mods.defeatedcrow.client.model.model.ModelPanHandle;

public class TileEntitySoupPanRenderer implements BlockEntityRenderer<TileFilledSoupPan> {
    // WT0 fix: soup pan now via JSON (filled_soup_pan.json - terracotta + contents_soup). BER disabled to avoid partial ModelPanHandle duplicate.
    private final ModelPanHandle model;

    public TileEntitySoupPanRenderer(BlockEntityRendererProvider.Context ctx) {
        this.model = new ModelPanHandle(ModelPanHandle.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(TileFilledSoupPan be, float partialTicks, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        // No-op: JSON handles full pan + contents. Dynamic soup level via blockstate if needed in future.
    }
}
