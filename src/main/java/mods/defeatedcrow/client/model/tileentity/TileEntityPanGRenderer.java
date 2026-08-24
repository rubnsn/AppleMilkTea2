package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.appliance.TilePanG;
import mods.defeatedcrow.client.model.model.ModelPanHandle;

public class TileEntityPanGRenderer implements BlockEntityRenderer<TilePanG> {
    // WT0 fix: pan body now via JSON (empty_pan_g.json - hardened_clay/terracotta). BER disabled to avoid double (was ModelPanHandle terracotta partial)
    private final ModelPanHandle model;

    public TileEntityPanGRenderer(BlockEntityRendererProvider.Context ctx) {
        this.model = new ModelPanHandle(ModelPanHandle.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(TilePanG be, float partialTicks, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        // No-op: JSON handles world/inventory full pan. Keep for future contents dynamic if needed.
    }
}
