package mods.defeatedcrow.client.entity.base;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import mods.defeatedcrow.client.ModEntityRenderers;
import mods.defeatedcrow.common.base.FoodBaseEntity;
import mods.defeatedcrow.common.base.FoodModelType.Deco;
import mods.defeatedcrow.common.base.FoodModelType.Dish;
import mods.defeatedcrow.common.base.FoodModelType.Soup;
import mods.defeatedcrow.common.config.DCsConfig;

/**
 * 1.20.1 migration: Render -> EntityRenderer + PoseStack/MultiBufferSource.
 * Former Tessellator immediate-mode soup quads are ported to VertexConsumer
 * flat quads on {@link RenderType#entityCutout}.
 * TODO(WT-B): the former per-item soup icon (entity.getSoupIcon(meta), item atlas)
 * is gone; restore by syncing an ItemStack via SynchedEntityData and rendering it,
 * or by resolving the sprite from WT-A's block/item registration.
 */
public class RenderFoodEntityBase extends EntityRenderer<FoodBaseEntity> {

    protected static final String[] PASS = { "foods", "foods/x32" };

    protected static final ResourceLocation BOWL_RICE_TEX = tex("bowlJP_rice.png");
    protected static final ResourceLocation BOWL_SOUP_TEX = tex("bowlJP_soup.png");
    protected static final ResourceLocation BOWL_WOOD_TEX = new ResourceLocation("defeatedcrow", "textures/entity/woodbowl.png");
    protected static final ResourceLocation DISH_GLASS_TEX = tex("dish_glass.png");
    protected static final ResourceLocation DISH_JP_TEX = tex("dish_jp.png");
    protected static final ResourceLocation DISH_SQUARE_TEX = tex("dish_square.png");
    protected static final ResourceLocation DISH_WHITE_TEX = tex("dish_white.png");
    protected static final ResourceLocation MAG_WHITE_TEX = new ResourceLocation("defeatedcrow", "textures/blocks/whitepanel.png");

    /** fallback texture for the former soup/deco icon quads */
    protected static final ResourceLocation INNER_TEX_DEFAULT = tex("bowlJP_inner.png");

    private static ResourceLocation tex(String name) {
        return new ResourceLocation("defeatedcrow", "textures/entity/" + getPass() + "/" + name);
    }

    protected final ModelRiceBowlB modelBowlR;
    protected final ModelSoupBowlB modelBowlS;
    protected final ModelWoodBowl modelBowlW;
    protected final ModelGlassDishB modelDishG;
    protected final ModelJPDishB modelDishJ;
    protected final ModelWhiteDishB modelDishW;
    protected final ModelTeaCup modelCup;

    protected final ModelInnerKobati modelKobati;
    protected final ModelInnerSoup modelSoup;

    public RenderFoodEntityBase(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowRadius = 0.3F;
        this.modelBowlR = new ModelRiceBowlB(ctx.bakeLayer(ModEntityRenderers.MODEL_RICE_BOWL_B));
        this.modelBowlS = new ModelSoupBowlB(ctx.bakeLayer(ModEntityRenderers.MODEL_SOUP_BOWL_B));
        this.modelBowlW = new ModelWoodBowl(ctx.bakeLayer(ModEntityRenderers.MODEL_BOWL_WOOD));
        this.modelDishG = new ModelGlassDishB(ctx.bakeLayer(ModEntityRenderers.MODEL_GLASS_DISH_B));
        this.modelDishJ = new ModelJPDishB(ctx.bakeLayer(ModEntityRenderers.MODEL_JP_DISH_B));
        this.modelDishW = new ModelWhiteDishB(ctx.bakeLayer(ModEntityRenderers.MODEL_WHITE_DISH_B));
        this.modelCup = new ModelTeaCup(ctx.bakeLayer(ModEntityRenderers.MODEL_TEA_CUP));
        this.modelKobati = new ModelInnerKobati(ctx.bakeLayer(ModEntityRenderers.MODEL_INNER_KOBATI));
        this.modelSoup = new ModelInnerSoup(ctx.bakeLayer(ModEntityRenderers.MODEL_INNER_SOUP));
    }

    @Override
    public void render(FoodBaseEntity entity, float yaw, float partialTick, PoseStack poseStack,
        MultiBufferSource buffer, int packedLight) {
        byte l = (byte) entity.getItemMetadata();
        Dish dish = entity.getDishType();
        Soup soup = entity.getSoupType();
        Deco deco = entity.getDecoType();

        this.renderDeco(entity, l, deco, yaw, poseStack, buffer, packedLight);
        this.renderDish(entity, l, dish, yaw, poseStack, buffer, packedLight);
        this.renderSoup(entity, l, soup, yaw, poseStack, buffer, packedLight);
        super.render(entity, yaw, partialTick, poseStack, buffer, packedLight);
    }

    protected void renderDish(FoodBaseEntity entity, byte meta, Dish dish, float yaw,
        PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 1.25F, 0.0F);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));

        if (dish == Dish.RiceBowl) {
            this.modelBowlR.render(poseStack, buffer.getBuffer(RenderType.entityCutout(BOWL_RICE_TEX)),
                packedLight, OverlayTexture.NO_OVERLAY);
        } else if (dish == Dish.SoupBowl) {
            this.modelBowlS.render(poseStack, buffer.getBuffer(RenderType.entityCutout(BOWL_SOUP_TEX)),
                packedLight, OverlayTexture.NO_OVERLAY);
        } else if (dish == Dish.WoodBowl) {
            this.modelBowlW.render(poseStack, buffer.getBuffer(RenderType.entityCutout(BOWL_WOOD_TEX)),
                packedLight, OverlayTexture.NO_OVERLAY);
        } else if (dish == Dish.Obon) {
            this.modelDishJ.render(poseStack, buffer.getBuffer(RenderType.entityCutout(DISH_JP_TEX)),
                packedLight, OverlayTexture.NO_OVERLAY);
        } else if (dish == Dish.SquarePlate) {
            this.modelDishJ.render(poseStack, buffer.getBuffer(RenderType.entityCutout(DISH_SQUARE_TEX)),
                packedLight, OverlayTexture.NO_OVERLAY);
        } else if (dish == Dish.Glass) {
            this.modelDishG.render(poseStack, buffer.getBuffer(RenderType.entityTranslucent(DISH_GLASS_TEX)),
                packedLight, OverlayTexture.NO_OVERLAY);
        } else if (dish == Dish.White) {
            this.modelDishW.render(poseStack, buffer.getBuffer(RenderType.entityCutout(DISH_WHITE_TEX)),
                packedLight, OverlayTexture.NO_OVERLAY);
        } else if (dish == Dish.Mug) {
            this.modelCup.render(poseStack, buffer.getBuffer(RenderType.entityCutout(MAG_WHITE_TEX)),
                packedLight, OverlayTexture.NO_OVERLAY);
        }

        poseStack.popPose();
    }

    protected void renderSoup(FoodBaseEntity entity, byte meta, Soup soup, float yaw,
        PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (soup == Soup.None) return;

        // TODO(WT-B): restore per-item soup texture/icon (former TextureMap items atlas)
        ResourceLocation soupTex = INNER_TEX_DEFAULT;

        poseStack.pushPose();
        poseStack.translate(0.0F, 0.4F, 0.0F);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));

        VertexConsumer vc = buffer.getBuffer(soup == Soup.Drink
            ? RenderType.entityTranslucent(soupTex)
            : RenderType.entityCutout(soupTex));

        double x;
        double y;
        double z;

        if (soup != Soup.Rice && soup != Soup.WoodRice) {
            if (soup == Soup.Soup) {
                x = 0.2D;
                y = 0.33D;
                z = 0.2D;
            } else if (soup == Soup.WoodSoup) {
                x = 0.25D;
                y = 0.43D;
                z = 0.25D;
            } else if (soup == Soup.Drink) {
                x = 0.15D;
                y = 0.3D;
                z = 0.15D;
            } else {
                x = 0.25D;
                y = 0.5D;
                z = 0.25D;
            }
            flatQuad(poseStack, vc, (float) x, (float) y, (float) z, packedLight);
        } else if (soup == Soup.WoodRice) {
            flatQuad(poseStack, vc, 0.1F, 0.3F, 0.1F, packedLight);

            slopeQuadA(poseStack, vc, 0.25F, 0.5F, 0.1F, 0.3F, packedLight);
            slopeQuadB(poseStack, vc, 0.25F, 0.5F, 0.1F, 0.3F, packedLight);
            slopeQuadC(poseStack, vc, 0.25F, 0.5F, 0.1F, 0.3F, packedLight);
        } else if (soup == Soup.Rice) {
            flatQuad(poseStack, vc, 0.1F, 0.3F, 0.1F, packedLight);

            slopeQuadA(poseStack, vc, 0.2F, 0.45F, 0.1F, 0.3F, packedLight);
            slopeQuadB(poseStack, vc, 0.2F, 0.45F, 0.1F, 0.3F, packedLight);
            slopeQuadC(poseStack, vc, 0.2F, 0.45F, 0.1F, 0.3F, packedLight);
        }

        poseStack.popPose();
    }

    protected void renderDeco(FoodBaseEntity entity, byte meta, Deco deco, float yaw,
        PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (deco == Deco.None) {
            return;
        }

        poseStack.pushPose();
        poseStack.translate(0.0F, 1.25F, 0.0F);
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));

        VertexConsumer vc = buffer.getBuffer(RenderType.entityCutout(getDecoTexture(entity, meta, deco)));

        if (deco == Deco.Kobathi) {
            this.modelKobati.render(poseStack, vc, packedLight, OverlayTexture.NO_OVERLAY);
        } else if (deco == Deco.SoupInner) {
            this.modelSoup.render(poseStack, vc, packedLight, OverlayTexture.NO_OVERLAY);
        }

        poseStack.popPose();
    }

    /**
     * Horizontal quad (top face normal +Y), half extents hx/hz at height y.
     * Geometry preserved from the former Tessellator draw.
     */
    protected static void flatQuad(PoseStack poseStack, VertexConsumer vc, float hx, float y, float hz, int packedLight) {
        PoseStack.Pose pose = poseStack.last();
        vertex(vc, pose, -hx, y, -hz, 0.0F, 1.0F, packedLight);
        vertex(vc, pose, hx, y, -hz, 1.0F, 1.0F, packedLight);
        vertex(vc, pose, hx, y, hz, 1.0F, 0.0F, packedLight);
        vertex(vc, pose, -hx, y, hz, 0.0F, 0.0F, packedLight);
    }

    /** one slanted side quad of the rice mound (+Z/-Z/-X variants collapsed into offsets by caller order) */
    protected static void slopeQuadA(PoseStack poseStack, VertexConsumer vc, float hx, float hy, float ix, float iy, int packedLight) {
        PoseStack.Pose pose = poseStack.last();
        vertex(vc, pose, -hx, hy, -hx, 0.0F, 1.0F, packedLight);
        vertex(vc, pose, hx, hy, -hx, 1.0F, 1.0F, packedLight);
        vertex(vc, pose, ix, iy, -ix, 1.0F, 0.0F, packedLight);
        vertex(vc, pose, -ix, iy, -ix, 0.0F, 0.0F, packedLight);
    }

    protected static void slopeQuadB(PoseStack poseStack, VertexConsumer vc, float hx, float hy, float ix, float iy, int packedLight) {
        PoseStack.Pose pose = poseStack.last();
        vertex(vc, pose, hx, hy, hx, 0.0F, 1.0F, packedLight);
        vertex(vc, pose, -hx, hy, hx, 1.0F, 1.0F, packedLight);
        vertex(vc, pose, -ix, iy, ix, 1.0F, 0.0F, packedLight);
        vertex(vc, pose, ix, iy, ix, 0.0F, 0.0F, packedLight);
    }

    protected static void slopeQuadC(PoseStack poseStack, VertexConsumer vc, float hx, float hy, float ix, float iy, int packedLight) {
        PoseStack.Pose pose = poseStack.last();
        vertex(vc, pose, -hx, hy, -hx, 0.0F, 1.0F, packedLight);
        vertex(vc, pose, -ix, iy, -ix, 1.0F, 1.0F, packedLight);
        vertex(vc, pose, -ix, iy, ix, 1.0F, 0.0F, packedLight);
        vertex(vc, pose, -hx, hy, hx, 0.0F, 0.0F, packedLight);
    }

    private static void vertex(VertexConsumer vc, PoseStack.Pose pose, float x, float y, float z,
        float u, float v, int packedLight) {
        vc.vertex(pose.pose(), x, y, z)
            .color(1.0F, 1.0F, 1.0F, 1.0F)
            .uv(u, v)
            .overlayCoords(OverlayTexture.NO_OVERLAY)
            .uv2(packedLight)
            .normal(pose.normal(), 0.0F, 1.0F, 0.0F)
            .endVertex();
    }

    protected ResourceLocation getBowlTextures(FoodBaseEntity entity) {
        return BOWL_RICE_TEX;
    }

    protected ResourceLocation getDecoTexture(FoodBaseEntity entity, byte meta, Deco deco) {
        return INNER_TEX_DEFAULT;
    }

    @Override
    public ResourceLocation getTextureLocation(FoodBaseEntity entity) {
        return this.getBowlTextures(entity);
    }

    private static String getPass() {
        int i = DCsConfig.setAltTexturePass == 0 ? 0 : 1;
        return PASS[i];
    }
}
