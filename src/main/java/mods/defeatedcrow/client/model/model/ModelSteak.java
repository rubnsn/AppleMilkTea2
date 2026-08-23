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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELSTEAK) -> new ModelSteak(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelSteak {

    private final ModelPart root;
    private final ModelPart pork;
    private final ModelPart carrot1;
    private final ModelPart carrot2;
    private final ModelPart carrot3;
    private final ModelPart potato1;
    private final ModelPart potato2;
    private final ModelPart beef;
    private final ModelPart butter;
    private final ModelPart body1;
    private final ModelPart body2;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart leg5;
    private final ModelPart leg6;
    private final ModelPart leg7;
    private final ModelPart leg8;
    private final ModelPart bottom;
    private final ModelPart bottom2;
    private final ModelPart top1;
    private final ModelPart top2;
    private final ModelPart Shape1;
    private final ModelPart wood;
    private final ModelPart plate;

    public ModelSteak(ModelPart root) {
        this.root = root;
        this.pork = root.getChild("pork");
        this.carrot1 = root.getChild("carrot1");
        this.carrot2 = root.getChild("carrot2");
        this.carrot3 = root.getChild("carrot3");
        this.potato1 = root.getChild("potato1");
        this.potato2 = root.getChild("potato2");
        this.beef = root.getChild("beef");
        this.butter = root.getChild("butter");
        this.body1 = root.getChild("body1");
        this.body2 = root.getChild("body2");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
        this.leg5 = root.getChild("leg5");
        this.leg6 = root.getChild("leg6");
        this.leg7 = root.getChild("leg7");
        this.leg8 = root.getChild("leg8");
        this.bottom = root.getChild("bottom");
        this.bottom2 = root.getChild("bottom2");
        this.top1 = root.getChild("top1");
        this.top2 = root.getChild("top2");
        this.Shape1 = root.getChild("Shape1");
        this.wood = root.getChild("wood");
        this.plate = root.getChild("plate");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition pork = partdefinition.addOrReplaceChild("pork", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-4F, 0F, -5F, 8, 3, 5), PartPose.offset(0F, 18F, 0F));
        PartDefinition carrot1 = partdefinition.addOrReplaceChild("carrot1", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(5F, 0F, 0F, 1, 1, 3), PartPose.offsetAndRotation(0F, 20F, 0F, 0F, -0.4363323F, 0F));
        PartDefinition carrot2 = partdefinition.addOrReplaceChild("carrot2", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(4F, 0F, 1F, 1, 1, 3), PartPose.offsetAndRotation(0F, 20F, 0F, 0F, -0.4363323F, 0F));
        PartDefinition carrot3 = partdefinition.addOrReplaceChild("carrot3", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-1F, 0F, 3F, 1, 1, 3), PartPose.offsetAndRotation(0F, 21F, 0F, 0.4363323F, 0.7853982F, 0F));
        PartDefinition potato1 = partdefinition.addOrReplaceChild("potato1", CubeListBuilder.create().texOffs(0, 23).mirror().addBox(-5F, 0F, 2F, 2, 2, 2), PartPose.offset(0F, 19F, 0F));
        PartDefinition potato2 = partdefinition.addOrReplaceChild("potato2", CubeListBuilder.create().texOffs(0, 23).mirror().addBox(-3F, 0F, 3F, 2, 2, 2), PartPose.offset(0F, 19F, 0F));
        PartDefinition beef = partdefinition.addOrReplaceChild("beef", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -5F, 8, 3, 5), PartPose.offset(0F, 18F, 0F));
        PartDefinition butter = partdefinition.addOrReplaceChild("butter", CubeListBuilder.create().texOffs(9, 18).mirror().addBox(-0.5F, 0F, -3F, 1, 1, 1), PartPose.offset(0F, 17F, 0F));
        PartDefinition body1 = partdefinition.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -6F, 6, 5, 10), PartPose.offset(0F, 17F, 0F));
        PartDefinition body2 = partdefinition.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-4F, 0F, -5F, 8, 4, 8), PartPose.offset(0F, 18F, 0F));
        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(3F, 0F, -2F, 2, 5, 7), PartPose.offset(0F, 16F, 0F));
        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-5F, 0F, -2F, 2, 5, 7), PartPose.offset(0F, 16F, 0F));
        PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(3, 18).mirror().addBox(5F, 0F, -1F, 1, 4, 5), PartPose.offset(0F, 16.5F, 0F));
        PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(3, 18).mirror().addBox(-6F, 0F, -1F, 1, 4, 5), PartPose.offset(0F, 16.5F, 0F));
        PartDefinition leg5 = partdefinition.addOrReplaceChild("leg5", CubeListBuilder.create().texOffs(18, 16).mirror().addBox(3F, -1F, 2F, 2, 2, 4), PartPose.offsetAndRotation(0F, 18F, 0F, 0.3141593F, 0F, 0F));
        PartDefinition leg6 = partdefinition.addOrReplaceChild("leg6", CubeListBuilder.create().texOffs(18, 16).mirror().addBox(-5F, -1F, 1F, 2, 2, 4), PartPose.offsetAndRotation(0F, 18F, 0F, 0.3141593F, 0F, 0F));
        PartDefinition leg7 = partdefinition.addOrReplaceChild("leg7", CubeListBuilder.create().texOffs(18, 24).mirror().addBox(3F, 0F, 4F, 2, 1, 4), PartPose.offsetAndRotation(0F, 17F, 0F, 0.3141593F, 0F, 0F));
        PartDefinition leg8 = partdefinition.addOrReplaceChild("leg8", CubeListBuilder.create().texOffs(18, 24).mirror().addBox(-5F, 0F, 4F, 2, 1, 4), PartPose.offsetAndRotation(0F, 17F, 0F, 0.3141593F, 0F, 0F));
        PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2F, 0F, -2F, 4, 1, 4), PartPose.offset(0F, 21F, 0F));
        PartDefinition bottom2 = partdefinition.addOrReplaceChild("bottom2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -3F, 6, 1, 6), PartPose.offset(0F, 20F, 0F));
        PartDefinition top1 = partdefinition.addOrReplaceChild("top1", CubeListBuilder.create().texOffs(14, 8).mirror().addBox(-2F, 0F, 4F, 4, 4, 1), PartPose.offset(0F, 14F, 0F));
        PartDefinition top2 = partdefinition.addOrReplaceChild("top2", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-3F, 0F, 3F, 6, 6, 1), PartPose.offset(0F, 14F, 0F));
        PartDefinition Shape1 = partdefinition.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2F, 0F, -2F, 4, 2, 4), PartPose.offset(0F, 18F, 0F));
        PartDefinition wood = partdefinition.addOrReplaceChild("wood", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-8F, 0F, -8F, 16, 1, 16), PartPose.offset(0F, 23F, 0F));
        PartDefinition plate = partdefinition.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-7F, 0F, -7F, 14, 1, 14), PartPose.offset(0F, 22F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
        byte par8) {

        if (par8 == 0 || par8 == 1) {
            butter.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            carrot1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            carrot2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            carrot3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            potato1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            potato2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            if (par8 == 0)             beef.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            if (par8 == 1)             pork.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else if (par8 == 2) {
            body1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            body2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg7.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg8.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else {
            bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bottom2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
    }

    public void renderPlate(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
        byte par8) {
            wood.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            plate.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        this.beef.yRot = f3 / (180F / (float) Math.PI);
        this.pork.yRot = f3 / (180F / (float) Math.PI);
        this.butter.yRot = f3 / (180F / (float) Math.PI);
        this.carrot1.yRot = -0.4363323F + f3 / (180F / (float) Math.PI);
        this.carrot2.yRot = -0.4363323F + f3 / (180F / (float) Math.PI);
        this.carrot3.yRot = 0.7853982F + f3 / (180F / (float) Math.PI);
        this.potato1.yRot = f3 / (180F / (float) Math.PI);
        this.potato2.yRot = f3 / (180F / (float) Math.PI);

        this.body1.yRot = f3 / (180F / (float) Math.PI);
        this.body2.yRot = f3 / (180F / (float) Math.PI);
        this.leg1.yRot = f3 / (180F / (float) Math.PI);
        this.leg2.yRot = f3 / (180F / (float) Math.PI);
        this.leg3.yRot = f3 / (180F / (float) Math.PI);
        this.leg4.yRot = f3 / (180F / (float) Math.PI);
        this.leg5.yRot = f3 / (180F / (float) Math.PI);
        this.leg6.yRot = f3 / (180F / (float) Math.PI);
        this.leg7.yRot = f3 / (180F / (float) Math.PI);
        this.leg8.yRot = f3 / (180F / (float) Math.PI);

        this.top1.yRot = f3 / (180F / (float) Math.PI);
        this.top2.yRot = f3 / (180F / (float) Math.PI);

        this.wood.yRot = f3 / (180F / (float) Math.PI);
        this.plate.yRot = f3 / (180F / (float) Math.PI);

    }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
