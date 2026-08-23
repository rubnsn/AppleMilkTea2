package mods.defeatedcrow.client.model.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import mods.defeatedcrow.common.block.edible.BlockCocktailSP.DecorationType;
import mods.defeatedcrow.common.block.edible.BlockCocktailSP.ModelType;

/**
 * 1.20.1 migration: former ModelBase/ModelRenderer model, now LayerDefinition + ModelPart.
 * Geometry was mechanically preserved from the 1.7.10 original.
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELCOCKTAIL) -> new ModelCocktail(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelCocktail {

    private final ModelPart root;
    private final ModelPart lemon;
    private final ModelPart lime;
    private final ModelPart pine;
    private final ModelPart apple;
    private final ModelPart bubble;
    private final ModelPart bottom;
    private final ModelPart Aleg;
    private final ModelPart Aleg2;
    private final ModelPart Aside1;
    private final ModelPart Aside2;
    private final ModelPart Aside3;
    private final ModelPart Aside4;
    private final ModelPart Bside1;
    private final ModelPart Bside2;
    private final ModelPart Bside3;
    private final ModelPart Bside4;
    private final ModelPart Cside1;
    private final ModelPart Cside2;
    private final ModelPart Cside3;
    private final ModelPart Cside4;
    private final ModelPart icecube;
    private final ModelPart icecube2;
    private final ModelPart inner1;
    private final ModelPart inner2;
    private final ModelPart inner3;

    public ModelCocktail(ModelPart root) {
        this.root = root;
        this.lemon = root.getChild("lemon");
        this.lime = root.getChild("lime");
        this.pine = root.getChild("pine");
        this.apple = root.getChild("apple");
        this.bubble = root.getChild("bubble");
        this.bottom = root.getChild("bottom");
        this.Aleg = root.getChild("Aleg");
        this.Aleg2 = root.getChild("Aleg2");
        this.Aside1 = root.getChild("Aside1");
        this.Aside2 = root.getChild("Aside2");
        this.Aside3 = root.getChild("Aside3");
        this.Aside4 = root.getChild("Aside4");
        this.Bside1 = root.getChild("Bside1");
        this.Bside2 = root.getChild("Bside2");
        this.Bside3 = root.getChild("Bside3");
        this.Bside4 = root.getChild("Bside4");
        this.Cside1 = root.getChild("Cside1");
        this.Cside2 = root.getChild("Cside2");
        this.Cside3 = root.getChild("Cside3");
        this.Cside4 = root.getChild("Cside4");
        this.icecube = root.getChild("icecube");
        this.icecube2 = root.getChild("icecube2");
        this.inner1 = root.getChild("inner1");
        this.inner2 = root.getChild("inner2");
        this.inner3 = root.getChild("inner3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition lemon = partdefinition.addOrReplaceChild("lemon", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(2F, -1F, 0F, 5, 4, 1), PartPose.offsetAndRotation(0F, 14F, 0F, 0F, 0F, -0.2094395F));
        PartDefinition lime = partdefinition.addOrReplaceChild("lime", CubeListBuilder.create().texOffs(32, 5).mirror().addBox(2F, 0F, 0F, 5, 4, 1), PartPose.offsetAndRotation(0F, 14F, 0F, 0F, 0F, -0.2094395F));
        PartDefinition pine = partdefinition.addOrReplaceChild("pine", CubeListBuilder.create().texOffs(32, 10).mirror().addBox(2F, 0F, 0F, 5, 4, 1), PartPose.offsetAndRotation(0F, 13F, 0F, 0F, 0F, -0.2094395F));
        PartDefinition apple = partdefinition.addOrReplaceChild("apple", CubeListBuilder.create().texOffs(32, 15).mirror().addBox(2F, 0F, 0F, 5, 4, 1), PartPose.offsetAndRotation(0F, 13F, 0F, 0F, 0F, -0.2094395F));
        PartDefinition bubble = partdefinition.addOrReplaceChild("bubble", CubeListBuilder.create().texOffs(32, 21).mirror().addBox(-3F, 0F, -3F, 6, 1, 6), PartPose.offset(0F, 15.5F, 0F));
        PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -3F, 6, 1, 6), PartPose.offset(0F, 23F, 0F));
        PartDefinition Aleg = partdefinition.addOrReplaceChild("Aleg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -0.5F, 1, 4, 1), PartPose.offset(0F, 19F, 0F));
        PartDefinition Aleg2 = partdefinition.addOrReplaceChild("Aleg2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -3F, 6, 2, 6), PartPose.offset(0F, 18F, 0F));
        PartDefinition Aside1 = partdefinition.addOrReplaceChild("Aside1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -4F, 8, 3, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition Aside2 = partdefinition.addOrReplaceChild("Aside2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, 3F, 8, 3, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition Aside3 = partdefinition.addOrReplaceChild("Aside3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3F, 0F, -3F, 1, 3, 6), PartPose.offset(0F, 16F, 0F));
        PartDefinition Aside4 = partdefinition.addOrReplaceChild("Aside4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -3F, 1, 3, 6), PartPose.offset(0F, 16F, 0F));
        PartDefinition Bside1 = partdefinition.addOrReplaceChild("Bside1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -4F, 8, 9, 1), PartPose.offset(0F, 14F, 0F));
        PartDefinition Bside2 = partdefinition.addOrReplaceChild("Bside2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, -1F, 3F, 8, 9, 1), PartPose.offset(0F, 15F, 0F));
        PartDefinition Bside3 = partdefinition.addOrReplaceChild("Bside3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -3F, 1, 9, 6), PartPose.offset(0F, 14F, 0F));
        PartDefinition Bside4 = partdefinition.addOrReplaceChild("Bside4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3F, 0F, -3F, 1, 9, 6), PartPose.offset(0F, 14F, 0F));
        PartDefinition Cside1 = partdefinition.addOrReplaceChild("Cside1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -4F, 8, 6, 1), PartPose.offset(0F, 13F, 0F));
        PartDefinition Cside2 = partdefinition.addOrReplaceChild("Cside2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, 3F, 8, 6, 1), PartPose.offset(0F, 13F, 0F));
        PartDefinition Cside3 = partdefinition.addOrReplaceChild("Cside3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3F, 0F, -3F, 1, 6, 6), PartPose.offset(0F, 13F, 0F));
        PartDefinition Cside4 = partdefinition.addOrReplaceChild("Cside4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -3F, 1, 6, 6), PartPose.offset(0F, 13F, 0F));
        PartDefinition icecube = partdefinition.addOrReplaceChild("icecube", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1F, 0F, -1F, 3, 3, 3), PartPose.offsetAndRotation(0F, 20F, 0F, 0.4560576F, 0F, -0.1319841F));
        PartDefinition icecube2 = partdefinition.addOrReplaceChild("icecube2", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-2F, 0F, -2F, 3, 3, 3), PartPose.offsetAndRotation(0F, 18F, 0F, -0.1745329F, 0.2648976F, -0.8922867F));
        PartDefinition inner1 = partdefinition.addOrReplaceChild("inner1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -3F, 6, 7, 6), PartPose.offset(0F, 16F, 0F));
        PartDefinition inner2 = partdefinition.addOrReplaceChild("inner2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -3F, 6, 2, 6), PartPose.offset(0F, 16.5F, 0F));
        PartDefinition inner3 = partdefinition.addOrReplaceChild("inner3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, -2F, 4, 1, 4), PartPose.offset(0F, 15.5F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void renderInner(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0) {
        byte b = (byte) (b0 & 4);

        if (b0 == 0) {
            inner1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else {
            inner2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            if (b == 4)             inner3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
    }

    public void renderInnerSP(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
        ModelType type) {

        if (type == ModelType.LONG) {
            inner1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else {
            inner2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
    }

    public void renderDeco(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0) {

        if (b0 == 1)             lime.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 2)             lemon.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 3)             pine.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 4)             apple.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 5)             bubble.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderDecoSP(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
        DecorationType type) {

        if (type == DecorationType.LIME)             lime.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (type == DecorationType.LEMON)             lemon.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (type == DecorationType.PINE)             pine.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (type == DecorationType.APPLE)             apple.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderGlass(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0) {
        byte b = (byte) (b0 & 3);

            bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        if (b == 0) {
            Bside1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Bside2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Bside3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Bside4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else if (b == 2) {
            Aleg.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Aleg2.render(poseStack, vertexConsumer, packedLight, packedOverlay);

            Cside1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Cside2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Cside3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Cside4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else {
            Aside1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Aside2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Aside3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Aside4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Aleg.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Aleg2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
    }

    public void renderGlassSP(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
        ModelType type) {

            bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        if (type == ModelType.LONG) {
            Bside1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Bside2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Bside3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Bside4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else if (type == ModelType.WINE) {
            Aleg.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Aleg2.render(poseStack, vertexConsumer, packedLight, packedOverlay);

            Cside1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Cside2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Cside3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Cside4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else if (type == ModelType.SHORT) {
            Aside1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Aside2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Aside3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Aside4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Aleg.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Aleg2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        this.inner1.yRot = f3 / (180F / (float) Math.PI);
        this.inner2.yRot = f3 / (180F / (float) Math.PI);
        this.inner3.yRot = f3 / (180F / (float) Math.PI);

        this.bottom.yRot = f3 / (180F / (float) Math.PI);
        this.Aleg.yRot = f3 / (180F / (float) Math.PI);
        this.Aleg2.yRot = f3 / (180F / (float) Math.PI);
        this.Bside1.yRot = f3 / (180F / (float) Math.PI);
        this.Bside2.yRot = f3 / (180F / (float) Math.PI);
        this.Bside3.yRot = f3 / (180F / (float) Math.PI);
        this.Bside4.yRot = f3 / (180F / (float) Math.PI);
        this.Cside1.yRot = f3 / (180F / (float) Math.PI);
        this.Cside2.yRot = f3 / (180F / (float) Math.PI);
        this.Cside3.yRot = f3 / (180F / (float) Math.PI);
        this.Cside4.yRot = f3 / (180F / (float) Math.PI);

        this.apple.yRot = f3 / (180F / (float) Math.PI);
        this.pine.yRot = f3 / (180F / (float) Math.PI);
        this.lemon.yRot = f3 / (180F / (float) Math.PI);
        this.lime.yRot = f3 / (180F / (float) Math.PI);
        this.bubble.yRot = f3 / (180F / (float) Math.PI);
    }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
