package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.TileCupHandle;
import mods.defeatedcrow.client.model.model.ModelCupHandle;

public class TileEntityCupHandleRenderer implements BlockEntityRenderer<TileCupHandle> {
    // WT0 fix: cup body+handle now via JSON (empty_cup/filled_cup) - BER disabled to avoid double (was 4 handles + sides, texture jpcup). Keep no-op to prevent duplicate handle over JSON.
    private final ModelCupHandle model;

    public TileEntityCupHandleRenderer(BlockEntityRendererProvider.Context ctx) {
        this.model = new ModelCupHandle(ModelCupHandle.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(TileCupHandle be, float partialTicks, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        // No-op: JSON handles world/inventory. If summer rendering needed, re-enable with correct single-handle + porcelain texture + direction byte.
    }
}
