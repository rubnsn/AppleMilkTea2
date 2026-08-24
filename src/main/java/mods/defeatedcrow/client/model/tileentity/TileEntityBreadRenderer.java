package mods.defeatedcrow.client.model.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.common.tile.TileBread;
import mods.defeatedcrow.client.model.model.ModelBreads;
import mods.defeatedcrow.client.model.model.ModelBasketL;
import mods.defeatedcrow.client.model.model.ModelBasketT;

public class TileEntityBreadRenderer implements BlockEntityRenderer<TileBread> {
    private final ModelBreads breadModel;
    private final ModelBasketL basketL;
    private final ModelBasketT basketT;
    private static final ResourceLocation BREAD_TEX = new ResourceLocation("defeatedcrow", "textures/entity/breads.png");
    private static final ResourceLocation BASKET_TEX = new ResourceLocation("defeatedcrow", "textures/entity/baskets.png");

    public TileEntityBreadRenderer(BlockEntityRendererProvider.Context ctx) {
        this.breadModel = new ModelBreads(ModelBreads.createBodyLayer().bakeRoot());
        this.basketL = new ModelBasketL(ModelBasketL.createBodyLayer().bakeRoot());
        this.basketT = new ModelBasketT(ModelBasketT.createBodyLayer().bakeRoot());
    }

    @Override
    public void render(TileBread be, float partialTicks, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        pose.pushPose();
        pose.translate(0.5, 1.5, 0.5);
        pose.scale(1.0F, -1.0F, -1.0F);
        // Bread loaves - simple dish + few breads as per original (ModelBreads + basket)
        VertexConsumer vcBread = buffers.getBuffer(RenderType.entityCutout(BREAD_TEX));
        this.breadModel.renderToBuffer(pose, vcBread, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        VertexConsumer vcBasket = buffers.getBuffer(RenderType.entityCutout(BASKET_TEX));
        // Render both basket variants for simple representation - dish shape
        this.basketL.renderToBuffer(pose, vcBasket, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        pose.popPose();
    }
}
