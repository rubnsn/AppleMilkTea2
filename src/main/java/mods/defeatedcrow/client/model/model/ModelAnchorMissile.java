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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELANCHORMISSILE) -> new ModelAnchorMissile(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelAnchorMissile {

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart head2;
    private final ModelPart head3;
    private final ModelPart body;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart tail4;
    private final ModelPart wingL1;
    private final ModelPart wingL2;
    private final ModelPart wingR1;
    private final ModelPart wingR2;
    private final ModelPart wing3;
    private final ModelPart burn;

    public ModelAnchorMissile(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.head2 = root.getChild("head2");
        this.head3 = root.getChild("head3");
        this.body = root.getChild("body");
        this.tail1 = root.getChild("tail1");
        this.tail2 = root.getChild("tail2");
        this.tail3 = root.getChild("tail3");
        this.tail4 = root.getChild("tail4");
        this.wingL1 = root.getChild("wingL1");
        this.wingL2 = root.getChild("wingL2");
        this.wingR1 = root.getChild("wingR1");
        this.wingR2 = root.getChild("wingR2");
        this.wing3 = root.getChild("wing3");
        this.burn = root.getChild("burn");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, -1.5F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, -4F));
        PartDefinition head2 = partdefinition.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, -1F, -4F, 3, 2, 2), PartPose.offset(0F, 16F, -4F));
        PartDefinition head3 = partdefinition.addOrReplaceChild("head3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, -0.5F, -5F, 2, 1, 1), PartPose.offset(0F, 16F, -4F));
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(36, 10).mirror().addBox(-1.5F, -1F, 2F, 3, 2, 10), PartPose.offset(0F, 16F, -4F));
        PartDefinition tail1 = partdefinition.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(28, 0).mirror().addBox(-2F, -1F, 12F, 4, 1, 3), PartPose.offsetAndRotation(0F, 16F, -4F, 0.0523599F, 0F, 0F));
        PartDefinition tail2 = partdefinition.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(28, 0).mirror().addBox(-2F, 0F, 12F, 4, 1, 3), PartPose.offsetAndRotation(0F, 16F, -4F, -0.0523599F, 0F, 0F));
        PartDefinition tail3 = partdefinition.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(43, 0).mirror().addBox(0F, -1F, 12F, 1, 2, 3), PartPose.offsetAndRotation(0F, 16F, -4F, 0F, 0.0872665F, 0F));
        PartDefinition tail4 = partdefinition.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(43, 0).mirror().addBox(-1F, -1F, 12F, 1, 2, 3), PartPose.offsetAndRotation(0F, 16F, -4F, 0F, -0.0872665F, 0F));
        PartDefinition wingL1 = partdefinition.addOrReplaceChild("wingL1", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-1F, 0F, 1F, 3, 1, 10), PartPose.offsetAndRotation(0F, 16F, -4F, 0F, 0.7853982F, 0F));
        PartDefinition wingL2 = partdefinition.addOrReplaceChild("wingL2", CubeListBuilder.create().texOffs(28, 24).mirror().addBox(-2.5F, 0F, 11F, 5, 1, 2), PartPose.offsetAndRotation(0F, 16F, -4F, 0F, 0.7853982F, 0F));
        PartDefinition wingR1 = partdefinition.addOrReplaceChild("wingR1", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-2F, 0F, 1F, 3, 1, 10), PartPose.offsetAndRotation(0F, 16F, -4F, 0F, -0.7853982F, 0F));
        PartDefinition wingR2 = partdefinition.addOrReplaceChild("wingR2", CubeListBuilder.create().texOffs(28, 28).mirror().addBox(-2.5F, 0F, 11F, 5, 1, 2), PartPose.offsetAndRotation(0F, 16F, -4F, 0F, -0.7853982F, 0F));
        PartDefinition wing3 = partdefinition.addOrReplaceChild("wing3", CubeListBuilder.create().texOffs(28, 5).mirror().addBox(-0.5F, 3F, 7F, 1, 2, 4), PartPose.offsetAndRotation(0F, 16F, -4F, 0.5235988F, 0F, 0F));
        PartDefinition burn = partdefinition.addOrReplaceChild("burn", CubeListBuilder.create().texOffs(52, 0).mirror().addBox(-1.5F, -1F, 13F, 3, 2, 1), PartPose.offset(0F, 16F, -4F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, boolean flag) {
            head.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            head2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            head3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            body.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tail1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tail2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tail3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tail4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            wingL1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            wingL2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            wingR1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            wingR2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            wing3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        if (flag)             burn.render(poseStack, vertexConsumer, packedLight, packedOverlay);

    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5, boolean flag) {
        if (flag) {
            this.wingL1.yRot = 0.7853982F;
            this.wingL2.yRot = 0.7853982F;
            this.wingR1.yRot = -0.7853982F;
            this.wingR2.yRot = -0.7853982F;
        } else {
            this.wingL1.yRot = 0F;
            this.wingL2.yRot = 0F;
            this.wingR1.yRot = 0F;
            this.wingR2.yRot = 0F;
        }
    }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
