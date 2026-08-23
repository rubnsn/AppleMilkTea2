package mods.defeatedcrow.client.model.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * 1.20.1 migration: former ModelBase/ModelRenderer model, now LayerDefinition + ModelPart.
 * Geometry was mechanically preserved from the 1.7.10 original.
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELFLOWERPOT) -> new ModelFlowerPot(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelFlowerPot {

    private final ModelPart root;
    private final ModelPart bagF;
    private final ModelPart bagB;
    private final ModelPart bagL;
    private final ModelPart bagR;
    private final ModelPart dirt;
    private final ModelPart chain;
    private final ModelPart flower1;
    private final ModelPart flower2;
    private final ModelPart flower3;
    private final ModelPart base;

    public ModelFlowerPot(ModelPart root) {
        this.root = root;
        this.bagF = root.getChild("bagF");
        this.bagB = root.getChild("bagB");
        this.bagL = root.getChild("bagL");
        this.bagR = root.getChild("bagR");
        this.dirt = root.getChild("dirt");
        this.chain = root.getChild("chain");
        this.flower1 = root.getChild("flower1");
        this.flower2 = root.getChild("flower2");
        this.flower3 = root.getChild("flower3");
        this.base = root.getChild("base");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bagF = partdefinition.addOrReplaceChild("bagF", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 2F, 0F, 12, 6, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2617994F, 0F, 0F));
        PartDefinition bagB = partdefinition.addOrReplaceChild("bagB", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 2F, 5F, 12, 6, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2617994F, 0F, 0F));
        PartDefinition bagL = partdefinition.addOrReplaceChild("bagL", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(5F, 2F, 1F, 1, 6, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2617994F, 0F, 0F));
        PartDefinition bagR = partdefinition.addOrReplaceChild("bagR", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(-6F, 2F, 1F, 1, 6, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2617994F, 0F, 0F));
        PartDefinition dirt = partdefinition.addOrReplaceChild("dirt", CubeListBuilder.create().texOffs(7, 13).mirror().addBox(-5F, 3F, 1F, 10, 4, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2617994F, 0F, 0F));
        PartDefinition chain = partdefinition.addOrReplaceChild("chain", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-6.5F, 3F, -0.5F, 13, 1, 8), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2617994F, 0F, 0F));
        PartDefinition flower1 = partdefinition.addOrReplaceChild("flower1", CubeListBuilder.create().texOffs(36, 16).mirror().addBox(-7F, -8F, 3F, 14, 10, 0), PartPose.offsetAndRotation(0F, 16F, 0F, -0.2617994F, 0F, 0F));
        PartDefinition flower2 = partdefinition.addOrReplaceChild("flower2", CubeListBuilder.create().texOffs(36, 16).mirror().addBox(-4F, -6F, 1F, 14, 10, 0), PartPose.offsetAndRotation(0F, 16F, 0F, 0.3490659F, -0.3490659F, 0F));
        PartDefinition flower3 = partdefinition.addOrReplaceChild("flower3", CubeListBuilder.create().texOffs(36, 16).mirror().addBox(-10F, -6F, 1F, 14, 10, 0), PartPose.offsetAndRotation(0F, 16F, 0F, 0.3490659F, 0.3490659F, 0F));
        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-8F, -8F, 7.9F, 16, 16, 0), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            bagF.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bagB.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bagL.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bagR.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            dirt.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            chain.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            flower1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            flower2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            flower3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        bagF.yRot = f3 / (180F / (float) Math.PI);
        bagB.yRot = f3 / (180F / (float) Math.PI);
        bagL.yRot = f3 / (180F / (float) Math.PI);
        bagR.yRot = f3 / (180F / (float) Math.PI);
        dirt.yRot = f3 / (180F / (float) Math.PI);
        chain.yRot = f3 / (180F / (float) Math.PI);
        flower1.yRot = f3 / (180F / (float) Math.PI);
        flower2.yRot = -0.3490659F + f3 / (180F / (float) Math.PI);
        flower3.yRot = 0.3490659F + f3 / (180F / (float) Math.PI);
        base.yRot = f3 / (180F / (float) Math.PI);
    }
}
