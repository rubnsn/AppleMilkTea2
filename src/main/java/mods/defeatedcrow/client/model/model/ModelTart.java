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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELTART) -> new ModelTart(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelTart {

    private final ModelPart root;
    private final ModelPart plate;
    private final ModelPart sideB;
    private final ModelPart sideF;
    private final ModelPart sideL;
    private final ModelPart sideR;
    private final ModelPart main;
    private final ModelPart main2;
    private final ModelPart crop1;
    private final ModelPart crop2;
    private final ModelPart crop3;
    private final ModelPart moussebase;
    private final ModelPart mousse1;
    private final ModelPart mousse2;
    private final ModelPart mousse3;
    private final ModelPart mousse4;
    private final ModelPart moussetop;
    private final ModelPart moussetop2;
    private final ModelPart moussetop3;
    private final ModelPart crop4;
    private final ModelPart crop5;
    private final ModelPart crop6;

    public ModelTart(ModelPart root) {
        this.root = root;
        this.plate = root.getChild("plate");
        this.sideB = root.getChild("sideB");
        this.sideF = root.getChild("sideF");
        this.sideL = root.getChild("sideL");
        this.sideR = root.getChild("sideR");
        this.main = root.getChild("main");
        this.main2 = root.getChild("main2");
        this.crop1 = root.getChild("crop1");
        this.crop2 = root.getChild("crop2");
        this.crop3 = root.getChild("crop3");
        this.moussebase = root.getChild("moussebase");
        this.mousse1 = root.getChild("mousse1");
        this.mousse2 = root.getChild("mousse2");
        this.mousse3 = root.getChild("mousse3");
        this.mousse4 = root.getChild("mousse4");
        this.moussetop = root.getChild("moussetop");
        this.moussetop2 = root.getChild("moussetop2");
        this.moussetop3 = root.getChild("moussetop3");
        this.crop4 = root.getChild("crop4");
        this.crop5 = root.getChild("crop5");
        this.crop6 = root.getChild("crop6");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition plate = partdefinition.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7F, 0F, -7F, 14, 1, 14), PartPose.offset(0F, 23F, 0F));
        PartDefinition sideB = partdefinition.addOrReplaceChild("sideB", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 0F, -5F, 1, 5, 10), PartPose.offsetAndRotation(0F, 18F, 0F, 0F, 1.570796F, -0.0F));
        PartDefinition sideF = partdefinition.addOrReplaceChild("sideF", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 0F, -5F, 1, 5, 10), PartPose.offsetAndRotation(0F, 18F, 0F, 0F, -1.570796F, -0.0F));
        PartDefinition sideL = partdefinition.addOrReplaceChild("sideL", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 0F, -5F, 1, 5, 10), PartPose.offsetAndRotation(0F, 18F, 0F, 0F, 0F, -0.0F));
        PartDefinition sideR = partdefinition.addOrReplaceChild("sideR", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 0F, -5F, 1, 5, 10), PartPose.offsetAndRotation(0F, 18F, 0F, 0F, 3.141593F, -0.0F));
        PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-5F, 0F, -5F, 10, 3, 10), PartPose.offset(0F, 19F, 0F));
        PartDefinition main2 = partdefinition.addOrReplaceChild("main2", CubeListBuilder.create().texOffs(24, 13).mirror().addBox(-5F, 0F, -5F, 10, 3, 10), PartPose.offset(0F, 19F, 0F));
        PartDefinition crop1 = partdefinition.addOrReplaceChild("crop1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.5F, 0F, 0F, 1, 1, 1), PartPose.offset(0F, 18F, 0F));
        PartDefinition crop2 = partdefinition.addOrReplaceChild("crop2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, 0F, -1.5F, 1, 1, 1), PartPose.offsetAndRotation(0F, 18F, 0F, 0F, 0.5205006F, 0F));
        PartDefinition crop3 = partdefinition.addOrReplaceChild("crop3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, 0.5F, 1, 1, 1), PartPose.offsetAndRotation(0F, 18F, 0F, 0F, 0.2602503F, 0F));
        PartDefinition moussebase = partdefinition.addOrReplaceChild("moussebase", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 21F, 0F));
        PartDefinition mousse1 = partdefinition.addOrReplaceChild("mousse1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 22F, 0F));
        PartDefinition mousse2 = partdefinition.addOrReplaceChild("mousse2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 20F, 0F));
        PartDefinition mousse3 = partdefinition.addOrReplaceChild("mousse3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 19F, 0F));
        PartDefinition mousse4 = partdefinition.addOrReplaceChild("mousse4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 18F, 0F));
        PartDefinition moussetop = partdefinition.addOrReplaceChild("moussetop", CubeListBuilder.create().texOffs(0, 11).mirror().addBox(0F, 0F, 0F, 10, 0, 10), PartPose.offset(-5F, 17.9F, -5F));
        PartDefinition moussetop2 = partdefinition.addOrReplaceChild("moussetop2", CubeListBuilder.create().texOffs(30, 11).mirror().addBox(0F, 0F, 0F, 10, 0, 10), PartPose.offset(-5F, 17.9F, -5F));
        PartDefinition moussetop3 = partdefinition.addOrReplaceChild("moussetop3", CubeListBuilder.create().texOffs(0, 21).mirror().addBox(0F, 0F, 0F, 10, 0, 10), PartPose.offset(-5F, 17.9F, -5F));
        PartDefinition crop4 = partdefinition.addOrReplaceChild("crop4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0.5F, 1, 1, 1), PartPose.offset(0F, 17F, 0F));
        PartDefinition crop5 = partdefinition.addOrReplaceChild("crop5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -1F, 1, 1, 1), PartPose.offsetAndRotation(0F, 17F, 0F, 0F, 0.2792527F, 0F));
        PartDefinition crop6 = partdefinition.addOrReplaceChild("crop6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, -1.5F, 1, 1, 1), PartPose.offsetAndRotation(0F, 17F, 0F, 0F, -0.1745329F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0) {
            sideB.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideF.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideL.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideR.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        if (b0 == 0) {
            main.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else {
            main2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
    }

    public void renderCrops(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0) {
        if (b0 != 0) {
            crop1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            crop2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            crop3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
    }

    public void renderMousseBase(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0) {
            moussebase.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            crop4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            crop5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            crop6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        if (b0 == 3) {
            moussetop2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else {
            moussetop.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
    }

    public void renderMousse1(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0) {
            mousse1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderMousse2(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0) {
            mousse2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            mousse4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderMousse3(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0) {
            mousse3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderPlate(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0) {
            plate.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        this.plate.yRot = f3 / (180F / (float) Math.PI);
        this.sideF.yRot = -1.570796F + f3 / (180F / (float) Math.PI);
        this.sideB.yRot = 1.570796F + f3 / (180F / (float) Math.PI);
        this.sideR.yRot = 3.141593F + f3 / (180F / (float) Math.PI);
        this.sideL.yRot = f3 / (180F / (float) Math.PI);
        this.main.yRot = f3 / (180F / (float) Math.PI);
        this.main2.yRot = f3 / (180F / (float) Math.PI);
        this.crop1.yRot = f3 / (180F / (float) Math.PI);
        this.crop2.yRot = f3 / (180F / (float) Math.PI);
        this.crop3.yRot = f3 / (180F / (float) Math.PI);

        this.moussebase.yRot = f3 / (180F / (float) Math.PI);
        this.moussetop.yRot = f3 / (180F / (float) Math.PI);
        this.moussetop2.yRot = f3 / (180F / (float) Math.PI);
        this.moussetop3.yRot = f3 / (180F / (float) Math.PI);
        this.mousse1.yRot = f3 / (180F / (float) Math.PI);
        this.mousse2.yRot = f3 / (180F / (float) Math.PI);
        this.mousse3.yRot = f3 / (180F / (float) Math.PI);
        this.mousse4.yRot = f3 / (180F / (float) Math.PI);
        this.crop4.yRot = f3 / (180F / (float) Math.PI);
        this.crop5.yRot = f3 / (180F / (float) Math.PI);
        this.crop6.yRot = f3 / (180F / (float) Math.PI);
    }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
