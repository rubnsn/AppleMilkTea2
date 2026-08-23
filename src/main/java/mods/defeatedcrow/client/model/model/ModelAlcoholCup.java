package mods.defeatedcrow.client.model.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * 1.20.1 migration: former ModelBase/ModelRenderer model, now LayerDefinition + ModelPart.
 * Geometry was mechanically preserved from the 1.7.10 original.
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELALCOHOLCUP) -> new ModelAlcoholCup(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelAlcoholCup {

    private final ModelPart root;
    private final ModelPart lemon;
    private final ModelPart lime;
    private final ModelPart pine;
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
    private final ModelPart obon1;
    private final ModelPart obon2;
    private final ModelPart obon3;
    private final ModelPart chokobottom;
    private final ModelPart choko1;
    private final ModelPart chokoside1;
    private final ModelPart chokoside2;
    private final ModelPart chokoside3;
    private final ModelPart chokoside4;
    private final ModelPart tokkuri1;
    private final ModelPart tokkuri2;
    private final ModelPart tokkuri3;
    private final ModelPart tokkuri4;

    public ModelAlcoholCup(ModelPart root) {
        this.root = root;
        this.lemon = root.getChild("lemon");
        this.lime = root.getChild("lime");
        this.pine = root.getChild("pine");
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
        this.obon1 = root.getChild("obon1");
        this.obon2 = root.getChild("obon2");
        this.obon3 = root.getChild("obon3");
        this.chokobottom = root.getChild("chokobottom");
        this.choko1 = root.getChild("choko1");
        this.chokoside1 = root.getChild("chokoside1");
        this.chokoside2 = root.getChild("chokoside2");
        this.chokoside3 = root.getChild("chokoside3");
        this.chokoside4 = root.getChild("chokoside4");
        this.tokkuri1 = root.getChild("tokkuri1");
        this.tokkuri2 = root.getChild("tokkuri2");
        this.tokkuri3 = root.getChild("tokkuri3");
        this.tokkuri4 = root.getChild("tokkuri4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition lemon = partdefinition.addOrReplaceChild("lemon", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(2F, -1F, 0F, 5, 4, 1), PartPose.offsetAndRotation(0F, 14F, 0F, 0F, 0F, -0.2094395F));
        PartDefinition lime = partdefinition.addOrReplaceChild("lime", CubeListBuilder.create().texOffs(32, 5).mirror().addBox(2F, 0F, 0F, 5, 4, 1), PartPose.offsetAndRotation(0F, 14F, 0F, 0F, 0F, -0.2094395F));
        PartDefinition pine = partdefinition.addOrReplaceChild("pine", CubeListBuilder.create().texOffs(32, 10).mirror().addBox(2F, 0F, 0F, 5, 4, 1), PartPose.offsetAndRotation(0F, 13F, 0F, 0F, 0F, -0.2094395F));
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
        PartDefinition inner2 = partdefinition.addOrReplaceChild("inner2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -3F, 6, 3, 6), PartPose.offset(0F, 15F, 0F));
        PartDefinition obon1 = partdefinition.addOrReplaceChild("obon1", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(-7F, 0F, -7F, 14, 1, 14), PartPose.offset(0F, 23F, 0F));
        PartDefinition obon2 = partdefinition.addOrReplaceChild("obon2", CubeListBuilder.create().texOffs(0, 29).mirror().addBox(-7F, 0F, -8F, 14, 2, 1), PartPose.offsetAndRotation(0F, 22F, 0F, 0F, -1.570796F, 0F));
        PartDefinition obon3 = partdefinition.addOrReplaceChild("obon3", CubeListBuilder.create().texOffs(0, 29).mirror().addBox(-7F, 0F, -8F, 14, 2, 1), PartPose.offsetAndRotation(0F, 22F, 0F, 0F, 1.570796F, 0F));
        PartDefinition chokobottom = partdefinition.addOrReplaceChild("chokobottom", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(2F, 0F, -3.5F, 2, 1, 2), PartPose.offset(0F, 22F, 0F));
        PartDefinition choko1 = partdefinition.addOrReplaceChild("choko1", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(1.5F, 0F, -4F, 3, 1, 3), PartPose.offset(0F, 21F, 0F));
        PartDefinition chokoside1 = partdefinition.addOrReplaceChild("chokoside1", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(1.5F, 0F, -4F, 3, 1, 1), PartPose.offset(0F, 20F, 0F));
        PartDefinition chokoside2 = partdefinition.addOrReplaceChild("chokoside2", CubeListBuilder.create().texOffs(0, 9).mirror().addBox(1.5F, 0F, -2F, 3, 1, 1), PartPose.offset(0F, 20F, 0F));
        PartDefinition chokoside3 = partdefinition.addOrReplaceChild("chokoside3", CubeListBuilder.create().texOffs(8, 0).mirror().addBox(1.5F, 0F, -3F, 1, 1, 1), PartPose.offset(0F, 20F, 0F));
        PartDefinition chokoside4 = partdefinition.addOrReplaceChild("chokoside4", CubeListBuilder.create().texOffs(12, 0).mirror().addBox(3.5F, 0F, -3F, 1, 1, 1), PartPose.offset(0F, 20F, 0F));
        PartDefinition tokkuri1 = partdefinition.addOrReplaceChild("tokkuri1", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-3F, 0F, 0F, 4, 6, 4), PartPose.offset(0F, 17F, 0F));
        PartDefinition tokkuri2 = partdefinition.addOrReplaceChild("tokkuri2", CubeListBuilder.create().texOffs(34, 1).mirror().addBox(-2.5F, 0F, 0.5F, 3, 1, 3), PartPose.offset(0F, 16F, 0F));
        PartDefinition tokkuri3 = partdefinition.addOrReplaceChild("tokkuri3", CubeListBuilder.create().texOffs(36, 4).mirror().addBox(-2F, 0F, 1F, 2, 2, 2), PartPose.offset(0F, 14F, 0F));
        PartDefinition tokkuri4 = partdefinition.addOrReplaceChild("tokkuri4", CubeListBuilder.create().texOffs(48, 0).mirror().addBox(-2.5F, 0F, 0.5F, 3, 1, 3), PartPose.offset(0F, 13F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void renderIce(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0) {
        if (b0 == 0) {
            icecube.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            icecube2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }

    }

    public void renderInner(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0) {
        if (b0 == 1) {
            inner2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else {
            inner1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }

    }

    public void renderGlass(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0) {
            bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        if (b0 == 1) {
            Aleg.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Aleg2.render(poseStack, vertexConsumer, packedLight, packedOverlay);

            Cside1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Cside2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Cside3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Cside4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else {
            Bside1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Bside2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Bside3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Bside4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
    }

    public void renderAtukan(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            obon1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            obon2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            obon3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            chokobottom.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            choko1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            chokoside1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            chokoside2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            chokoside3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            chokoside4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tokkuri1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tokkuri2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tokkuri3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tokkuri4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    @Override
    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        this.inner1.yRot = f3 / (180F / (float) Math.PI);
        this.inner2.yRot = f3 / (180F / (float) Math.PI);

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

        this.obon1.yRot = f3 / (180F / (float) Math.PI);
        this.obon2.yRot = -1.570796F + f3 / (180F / (float) Math.PI);
        this.obon3.yRot = 1.570796F + f3 / (180F / (float) Math.PI);
        this.choko1.yRot = f3 / (180F / (float) Math.PI);
        this.chokobottom.yRot = f3 / (180F / (float) Math.PI);
        this.chokoside1.yRot = f3 / (180F / (float) Math.PI);
        this.chokoside2.yRot = f3 / (180F / (float) Math.PI);
        this.chokoside3.yRot = f3 / (180F / (float) Math.PI);
        this.chokoside4.yRot = f3 / (180F / (float) Math.PI);
        this.tokkuri1.yRot = f3 / (180F / (float) Math.PI);
        this.tokkuri2.yRot = f3 / (180F / (float) Math.PI);
        this.tokkuri3.yRot = f3 / (180F / (float) Math.PI);
        this.tokkuri4.yRot = f3 / (180F / (float) Math.PI);
    }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
