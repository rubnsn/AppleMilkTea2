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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELSANDWICH) -> new ModelSandwich(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelSandwich {

    private final ModelPart root;
    private final ModelPart basketU;
    private final ModelPart basketF;
    private final ModelPart basketB;
    private final ModelPart basketL;
    private final ModelPart basketR;
    private final ModelPart bread11;
    private final ModelPart bread12;
    private final ModelPart bread21;
    private final ModelPart bread22;
    private final ModelPart bread31;
    private final ModelPart bread32;
    private final ModelPart apple1;
    private final ModelPart apple2;
    private final ModelPart apple3;
    private final ModelPart egg1;
    private final ModelPart egg2;
    private final ModelPart egg3;
    private final ModelPart cassis1;
    private final ModelPart cassis2;
    private final ModelPart cassis3;

    public ModelSandwich(ModelPart root) {
        this.root = root;
        this.basketU = root.getChild("basketU");
        this.basketF = root.getChild("basketF");
        this.basketB = root.getChild("basketB");
        this.basketL = root.getChild("basketL");
        this.basketR = root.getChild("basketR");
        this.bread11 = root.getChild("bread11");
        this.bread12 = root.getChild("bread12");
        this.bread21 = root.getChild("bread21");
        this.bread22 = root.getChild("bread22");
        this.bread31 = root.getChild("bread31");
        this.bread32 = root.getChild("bread32");
        this.apple1 = root.getChild("apple1");
        this.apple2 = root.getChild("apple2");
        this.apple3 = root.getChild("apple3");
        this.egg1 = root.getChild("egg1");
        this.egg2 = root.getChild("egg2");
        this.egg3 = root.getChild("egg3");
        this.cassis1 = root.getChild("cassis1");
        this.cassis2 = root.getChild("cassis2");
        this.cassis3 = root.getChild("cassis3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition basketU = partdefinition.addOrReplaceChild("basketU", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 0F, -4F, 12, 1, 8), PartPose.offset(0F, 23F, 0F));
        PartDefinition basketF = partdefinition.addOrReplaceChild("basketF", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-6F, 0F, -4F, 12, 4, 1), PartPose.offset(0F, 19F, 0F));
        PartDefinition basketB = partdefinition.addOrReplaceChild("basketB", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-6F, 0F, -4F, 12, 4, 1), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, 3.141593F, 0F));
        PartDefinition basketL = partdefinition.addOrReplaceChild("basketL", CubeListBuilder.create().texOffs(40, 0).mirror().addBox(-6F, 0F, -3F, 1, 4, 6), PartPose.offset(0F, 19F, 0F));
        PartDefinition basketR = partdefinition.addOrReplaceChild("basketR", CubeListBuilder.create().texOffs(40, 0).mirror().addBox(-6F, 0F, -3F, 1, 4, 6), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, 3.141593F, 0F));
        PartDefinition bread11 = partdefinition.addOrReplaceChild("bread11", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-4.5F, 0F, -2.5F, 1, 3, 5), PartPose.offsetAndRotation(0F, 18.5F, 0F, 0F, -0.0698132F, -0.0872665F));
        PartDefinition bread12 = partdefinition.addOrReplaceChild("bread12", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-3F, 0F, -2.5F, 1, 3, 5), PartPose.offsetAndRotation(0F, 18.5F, 0F, 0F, -0.0698132F, -0.122173F));
        PartDefinition bread21 = partdefinition.addOrReplaceChild("bread21", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.5F, 0F, -2.5F, 1, 3, 5), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, 0F, -0.122173F));
        PartDefinition bread22 = partdefinition.addOrReplaceChild("bread22", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(0F, 0F, -2.5F, 1, 3, 5), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, 0F, -0.122173F));
        PartDefinition bread31 = partdefinition.addOrReplaceChild("bread31", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(2F, 0F, -2.5F, 1, 3, 5), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, 0F, 0.0349066F));
        PartDefinition bread32 = partdefinition.addOrReplaceChild("bread32", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(3.5F, 0F, -2.5F, 1, 3, 5), PartPose.offset(0F, 19F, 0F));
        PartDefinition apple1 = partdefinition.addOrReplaceChild("apple1", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-3.5F, 0F, -2F, 1, 3, 4), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, -0.0698132F, 0F));
        PartDefinition apple2 = partdefinition.addOrReplaceChild("apple2", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-0.5F, 0F, -2F, 1, 3, 4), PartPose.offset(0F, 19.5F, 0F));
        PartDefinition apple3 = partdefinition.addOrReplaceChild("apple3", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(3F, 0F, -2F, 1, 3, 4), PartPose.offset(0F, 19.5F, 0F));
        PartDefinition egg1 = partdefinition.addOrReplaceChild("egg1", CubeListBuilder.create().texOffs(10, 24).mirror().addBox(-4F, 0F, -2F, 1, 3, 4), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, -0.0698132F, 0F));
        PartDefinition egg2 = partdefinition.addOrReplaceChild("egg2", CubeListBuilder.create().texOffs(10, 24).mirror().addBox(-1F, 0F, -2F, 1, 3, 4), PartPose.offset(0F, 19.5F, 0F));
        PartDefinition egg3 = partdefinition.addOrReplaceChild("egg3", CubeListBuilder.create().texOffs(10, 24).mirror().addBox(2.5F, 0F, -2F, 1, 3, 4), PartPose.offset(0F, 19.5F, 0F));
        PartDefinition cassis1 = partdefinition.addOrReplaceChild("cassis1", CubeListBuilder.create().texOffs(20, 24).mirror().addBox(-4F, 0F, -2F, 1, 3, 4), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, -0.0698132F, 0F));
        PartDefinition cassis2 = partdefinition.addOrReplaceChild("cassis2", CubeListBuilder.create().texOffs(20, 24).mirror().addBox(-1F, 0F, -2F, 1, 3, 4), PartPose.offset(0F, 19.5F, 0F));
        PartDefinition cassis3 = partdefinition.addOrReplaceChild("cassis3", CubeListBuilder.create().texOffs(20, 24).mirror().addBox(2.5F, 0F, -2F, 1, 3, 4), PartPose.offset(0F, 19.5F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0) {
            basketU.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            basketF.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            basketB.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            basketL.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            basketR.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bread11.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bread12.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bread21.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bread22.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bread31.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bread32.render(poseStack, vertexConsumer, packedLight, packedOverlay);

        if (b0 == 1) {
            egg1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            egg2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            egg3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else if (b0 == 2) {
            cassis1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            cassis2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            cassis3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else {
            apple1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            apple2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            apple3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        this.basketU.yRot = f3 / (180F / (float) Math.PI);
        this.basketF.yRot = f3 / (180F / (float) Math.PI);
        this.basketB.yRot = 3.141593F + f3 / (180F / (float) Math.PI);
        this.basketR.yRot = 3.141593F + f3 / (180F / (float) Math.PI);
        this.basketL.yRot = f3 / (180F / (float) Math.PI);
        this.bread11.yRot = -0.0698132F + f3 / (180F / (float) Math.PI);
        this.bread12.yRot = -0.0698132F + f3 / (180F / (float) Math.PI);
        this.bread21.yRot = f3 / (180F / (float) Math.PI);
        this.bread22.yRot = f3 / (180F / (float) Math.PI);
        this.bread31.yRot = f3 / (180F / (float) Math.PI);
        this.bread32.yRot = f3 / (180F / (float) Math.PI);

        this.apple1.yRot = -0.0698132F + f3 / (180F / (float) Math.PI);
        this.apple2.yRot = f3 / (180F / (float) Math.PI);
        this.apple3.yRot = f3 / (180F / (float) Math.PI);

        this.egg1.yRot = -0.0698132F + f3 / (180F / (float) Math.PI);
        this.egg2.yRot = f3 / (180F / (float) Math.PI);
        this.egg3.yRot = f3 / (180F / (float) Math.PI);

        this.cassis1.yRot = -0.0698132F + f3 / (180F / (float) Math.PI);
        this.cassis2.yRot = f3 / (180F / (float) Math.PI);
        this.cassis3.yRot = f3 / (180F / (float) Math.PI);
    }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
