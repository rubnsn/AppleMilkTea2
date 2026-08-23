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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELICEMAKER) -> new ModelIceMaker(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelIceMaker {

    private final ModelPart root;
    private final ModelPart bottom;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart boad1;
    private final ModelPart boad2;
    private final ModelPart middle;
    private final ModelPart ice;
    private final ModelPart axle;
    private final ModelPart handle1;
    private final ModelPart handle2;
    private final ModelPart handle3;
    private final ModelPart handle4;
    private final ModelPart handle5;
    private final ModelPart handle6;

    public ModelIceMaker(ModelPart root) {
        this.root = root;
        this.bottom = root.getChild("bottom");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
        this.boad1 = root.getChild("boad1");
        this.boad2 = root.getChild("boad2");
        this.middle = root.getChild("middle");
        this.ice = root.getChild("ice");
        this.axle = root.getChild("axle");
        this.handle1 = root.getChild("handle1");
        this.handle2 = root.getChild("handle2");
        this.handle3 = root.getChild("handle3");
        this.handle4 = root.getChild("handle4");
        this.handle5 = root.getChild("handle5");
        this.handle6 = root.getChild("handle6");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 23F, 0F));
        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.5F, -6F, -4.5F, 1, 8, 1), PartPose.offsetAndRotation(0F, 23F, 0F, -0.1396263F, 0F, 0.1396263F));
        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3.5F, -6F, -4.5F, 1, 8, 1), PartPose.offsetAndRotation(0F, 23F, 0F, -0.1396263F, 0F, -0.1396263F));
        PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.5F, -6F, 3.5F, 1, 8, 1), PartPose.offsetAndRotation(0F, 23F, 0F, 0.1396263F, 0F, 0.1396263F));
        PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3.5F, -6F, 3.5F, 1, 8, 1), PartPose.offsetAndRotation(0F, 23F, 0F, 0.1396263F, 0F, -0.1396263F));
        PartDefinition boad1 = partdefinition.addOrReplaceChild("boad1", CubeListBuilder.create().texOffs(0, 11).mirror().addBox(-4F, 0F, -4F, 8, 1, 8), PartPose.offset(0F, 16F, 0F));
        PartDefinition boad2 = partdefinition.addOrReplaceChild("boad2", CubeListBuilder.create().texOffs(0, 11).mirror().addBox(-4F, 0F, -4F, 8, 1, 8), PartPose.offset(0F, 11F, 0F));
        PartDefinition middle = partdefinition.addOrReplaceChild("middle", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-4F, 0F, -4F, 8, 4, 8), PartPose.offset(0F, 12F, 0F));
        PartDefinition ice = partdefinition.addOrReplaceChild("ice", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-2F, 0F, -2F, 4, 4, 4), PartPose.offset(0F, 12F, 0F));
        PartDefinition axle = partdefinition.addOrReplaceChild("axle", CubeListBuilder.create().texOffs(32, 12).mirror().addBox(-0.5F, 0F, -0.5F, 1, 5, 1), PartPose.offset(0F, 8F, 0F));
        PartDefinition handle1 = partdefinition.addOrReplaceChild("handle1", CubeListBuilder.create().texOffs(36, 12).mirror().addBox(-2F, 0F, -3F, 4, 1, 1), PartPose.offset(0F, 8F, 0F));
        PartDefinition handle2 = partdefinition.addOrReplaceChild("handle2", CubeListBuilder.create().texOffs(36, 12).mirror().addBox(-2F, 0F, 2F, 4, 1, 1), PartPose.offset(0F, 8F, 0F));
        PartDefinition handle3 = partdefinition.addOrReplaceChild("handle3", CubeListBuilder.create().texOffs(36, 12).mirror().addBox(2F, 0F, -2F, 1, 1, 4), PartPose.offset(0F, 8F, 0F));
        PartDefinition handle4 = partdefinition.addOrReplaceChild("handle4", CubeListBuilder.create().texOffs(36, 12).mirror().addBox(-3F, 0F, -2F, 1, 1, 4), PartPose.offset(0F, 8F, 0F));
        PartDefinition handle5 = partdefinition.addOrReplaceChild("handle5", CubeListBuilder.create().texOffs(36, 12).mirror().addBox(-0.5F, 0F, -2F, 1, 1, 4), PartPose.offset(0F, 8F, 0F));
        PartDefinition handle6 = partdefinition.addOrReplaceChild("handle6", CubeListBuilder.create().texOffs(36, 12).mirror().addBox(-2F, 0F, -0.5F, 4, 1, 1), PartPose.offset(0F, 8F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
        boolean par8, boolean par9) {

            bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            boad1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            boad2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            axle.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handle1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handle2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handle3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handle4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handle5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handle6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        if (par8) {
            ice.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }

    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5, boolean flag) {
        if (flag) {
            this.ice.yRot += 1 / (180F / (float) Math.PI);
            this.axle.yRot += 1 / (180F / (float) Math.PI);
            this.handle1.yRot += 1 / (180F / (float) Math.PI);
            this.handle2.yRot += 1 / (180F / (float) Math.PI);
            this.handle3.yRot += 1 / (180F / (float) Math.PI);
            this.handle4.yRot += 1 / (180F / (float) Math.PI);
            this.handle5.yRot += 1 / (180F / (float) Math.PI);
            this.handle6.yRot += 1 / (180F / (float) Math.PI);
        }
    }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
