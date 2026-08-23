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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELBARREL) -> new ModelBarrel(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelBarrel {

    private final ModelPart root;
    private final ModelPart side1;
    private final ModelPart side2;
    private final ModelPart side3;
    private final ModelPart side4;
    private final ModelPart side5;
    private final ModelPart side6;
    private final ModelPart side7;
    private final ModelPart side8;
    private final ModelPart side9;
    private final ModelPart side10;
    private final ModelPart r1;
    private final ModelPart r2;
    private final ModelPart r3;
    private final ModelPart r4;
    private final ModelPart r5;
    private final ModelPart l1;
    private final ModelPart l2;
    private final ModelPart l3;
    private final ModelPart l4;
    private final ModelPart l5;
    private final ModelPart base1;
    private final ModelPart base2;

    public ModelBarrel(ModelPart root) {
        this.root = root;
        this.side1 = root.getChild("side1");
        this.side2 = root.getChild("side2");
        this.side3 = root.getChild("side3");
        this.side4 = root.getChild("side4");
        this.side5 = root.getChild("side5");
        this.side6 = root.getChild("side6");
        this.side7 = root.getChild("side7");
        this.side8 = root.getChild("side8");
        this.side9 = root.getChild("side9");
        this.side10 = root.getChild("side10");
        this.r1 = root.getChild("r1");
        this.r2 = root.getChild("r2");
        this.r3 = root.getChild("r3");
        this.r4 = root.getChild("r4");
        this.r5 = root.getChild("r5");
        this.l1 = root.getChild("l1");
        this.l2 = root.getChild("l2");
        this.l3 = root.getChild("l3");
        this.l4 = root.getChild("l4");
        this.l5 = root.getChild("l5");
        this.base1 = root.getChild("base1");
        this.base2 = root.getChild("base2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition side1 = partdefinition.addOrReplaceChild("side1", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, 6F, -2F, 16, 1, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition side2 = partdefinition.addOrReplaceChild("side2", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, 6F, -2F, 16, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0.6283185F, 0F, 0F));
        PartDefinition side3 = partdefinition.addOrReplaceChild("side3", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, 6F, -2F, 16, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 1.256637F, 0F, 0F));
        PartDefinition side4 = partdefinition.addOrReplaceChild("side4", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, 6F, -2F, 16, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, -0.6283185F, 0F, 0F));
        PartDefinition side5 = partdefinition.addOrReplaceChild("side5", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, 6F, -2F, 16, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, -1.256637F, 0F, 0F));
        PartDefinition side6 = partdefinition.addOrReplaceChild("side6", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, -7F, -2F, 16, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 1.256637F, 0F, 0F));
        PartDefinition side7 = partdefinition.addOrReplaceChild("side7", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, -7F, -2F, 16, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, -1.256637F, 0F, 0F));
        PartDefinition side8 = partdefinition.addOrReplaceChild("side8", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, -7F, -2F, 16, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0.6283185F, 0F, 0F));
        PartDefinition side9 = partdefinition.addOrReplaceChild("side9", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, -7F, -2F, 16, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, -0.6283185F, 0F, 0F));
        PartDefinition side10 = partdefinition.addOrReplaceChild("side10", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, -7F, -2F, 16, 1, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition r1 = partdefinition.addOrReplaceChild("r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7F, -5F, -5F, 1, 10, 10), PartPose.offset(0F, 16F, 0F));
        PartDefinition r2 = partdefinition.addOrReplaceChild("r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7F, -4F, -6.5F, 1, 8, 2), PartPose.offset(0F, 16F, 0F));
        PartDefinition r3 = partdefinition.addOrReplaceChild("r3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7F, -4F, 4.5F, 1, 8, 2), PartPose.offset(0F, 16F, 0F));
        PartDefinition r4 = partdefinition.addOrReplaceChild("r4", CubeListBuilder.create().texOffs(14, 0).mirror().addBox(-7F, -6F, -3.5F, 1, 1, 7), PartPose.offset(0F, 16F, 0F));
        PartDefinition r5 = partdefinition.addOrReplaceChild("r5", CubeListBuilder.create().texOffs(14, 0).mirror().addBox(-7F, 5F, -3.5F, 1, 1, 7), PartPose.offset(0F, 16F, 0F));
        PartDefinition l1 = partdefinition.addOrReplaceChild("l1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(6F, -5F, -5F, 1, 10, 10), PartPose.offset(0F, 16F, 0F));
        PartDefinition l2 = partdefinition.addOrReplaceChild("l2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(6F, -4F, 4.5F, 1, 8, 2), PartPose.offset(0F, 16F, 0F));
        PartDefinition l3 = partdefinition.addOrReplaceChild("l3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(6F, -4F, -6.5F, 1, 8, 2), PartPose.offset(0F, 16F, 0F));
        PartDefinition l4 = partdefinition.addOrReplaceChild("l4", CubeListBuilder.create().texOffs(14, 0).mirror().addBox(6F, -6F, -3.5F, 1, 1, 7), PartPose.offset(0F, 16F, 0F));
        PartDefinition l5 = partdefinition.addOrReplaceChild("l5", CubeListBuilder.create().texOffs(14, 0).mirror().addBox(6F, 5F, -3.5F, 1, 1, 7), PartPose.offset(0F, 16F, 0F));
        PartDefinition base1 = partdefinition.addOrReplaceChild("base1", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(5F, 6F, -5F, 1, 2, 10), PartPose.offset(0F, 16F, 0F));
        PartDefinition base2 = partdefinition.addOrReplaceChild("base2", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-6F, 6F, -5F, 1, 2, 10), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            side1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side7.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side8.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side9.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side10.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            r2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            r3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            r4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            r5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            l2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            l3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            l4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            l5.render(poseStack, vertexConsumer, packedLight, packedOverlay);

    }

    public void renderSide(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            r1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            l1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderBase(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            base1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        side1.yRot = f3 / (180F / (float) Math.PI);
        side2.yRot = f3 / (180F / (float) Math.PI);
        side3.yRot = f3 / (180F / (float) Math.PI);
        side4.yRot = f3 / (180F / (float) Math.PI);
        side5.yRot = f3 / (180F / (float) Math.PI);
        side6.yRot = f3 / (180F / (float) Math.PI);
        side7.yRot = f3 / (180F / (float) Math.PI);
        side8.yRot = f3 / (180F / (float) Math.PI);
        side9.yRot = f3 / (180F / (float) Math.PI);
        side10.yRot = f3 / (180F / (float) Math.PI);
        r1.yRot = f3 / (180F / (float) Math.PI);
        r2.yRot = f3 / (180F / (float) Math.PI);
        r3.yRot = f3 / (180F / (float) Math.PI);
        r4.yRot = f3 / (180F / (float) Math.PI);
        r5.yRot = f3 / (180F / (float) Math.PI);
        l1.yRot = f3 / (180F / (float) Math.PI);
        l2.yRot = f3 / (180F / (float) Math.PI);
        l3.yRot = f3 / (180F / (float) Math.PI);
        l4.yRot = f3 / (180F / (float) Math.PI);
        l5.yRot = f3 / (180F / (float) Math.PI);
        base1.yRot = f3 / (180F / (float) Math.PI);
        base2.yRot = f3 / (180F / (float) Math.PI);
    }
}
