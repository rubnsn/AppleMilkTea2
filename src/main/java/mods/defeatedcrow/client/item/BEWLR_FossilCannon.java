package mods.defeatedcrow.client.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import mods.defeatedcrow.client.model.model.ModelFossilCannon;

@OnlyIn(Dist.CLIENT)
public class BEWLR_FossilCannon extends BlockEntityWithoutLevelRenderer {
    private final ModelFossilCannon model;
    private static final ResourceLocation TEX = new ResourceLocation("defeatedcrow", "textures/entity/fossilcannon.png");
    public BEWLR_FossilCannon(Minecraft mc, EntityModelSet set) {
        super(mc.getBlockEntityRenderDispatcher(), set);
        this.model = new ModelFossilCannon(ModelFossilCannon.createBodyLayer().bakeRoot());
    }
    @Override public void onResourceManagerReload(ResourceManager m) {}
    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext ctx, PoseStack pose, MultiBufferSource buf, int light, int overlay) {
        pose.pushPose();
        pose.translate(0.5F, 1.5F, 0.5F);
        pose.scale(1.0F, -1.0F, -1.0F);
        VertexConsumer vc = buf.getBuffer(RenderType.entityCutout(TEX));
        model.renderToBuffer(pose, vc, light, overlay, 1,1,1,1);
        pose.popPose();
    }
}
