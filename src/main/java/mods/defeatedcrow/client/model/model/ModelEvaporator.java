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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELEVAPORATOR) -> new ModelEvaporator(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelEvaporator {

    private final ModelPart root;
    private final ModelPart glass1;
    private final ModelPart Shape1;
    private final ModelPart Shape2;
    private final ModelPart Shape3;
    private final ModelPart Shape4;
    private final ModelPart Shape5;
    private final ModelPart Shape6;
    private final ModelPart Shape7;
    private final ModelPart Shape8;
    private final ModelPart Shape8b;
    private final ModelPart Shape9;
    private final ModelPart Shape9b;
    private final ModelPart Shape10;
    private final ModelPart Shape11;
    private final ModelPart Shape12;
    private final ModelPart Shape13;
    private final ModelPart Shape14;

    public ModelEvaporator(ModelPart root) {
        this.root = root;
        this.glass1 = root.getChild("glass1");
        this.Shape1 = root.getChild("Shape1");
        this.Shape2 = root.getChild("Shape2");
        this.Shape3 = root.getChild("Shape3");
        this.Shape4 = root.getChild("Shape4");
        this.Shape5 = root.getChild("Shape5");
        this.Shape6 = root.getChild("Shape6");
        this.Shape7 = root.getChild("Shape7");
        this.Shape8 = root.getChild("Shape8");
        this.Shape8b = root.getChild("Shape8b");
        this.Shape9 = root.getChild("Shape9");
        this.Shape9b = root.getChild("Shape9b");
        this.Shape10 = root.getChild("Shape10");
        this.Shape11 = root.getChild("Shape11");
        this.Shape12 = root.getChild("Shape12");
        this.Shape13 = root.getChild("Shape13");
        this.Shape14 = root.getChild("Shape14");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition glass1 = partdefinition.addOrReplaceChild("glass1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 1F, 0F, 4, 3, 4), PartPose.offsetAndRotation(0F, 19F, 0F, 0.7853982F, 1.570796F, 0F));
        PartDefinition Shape1 = partdefinition.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.5F, 1F, 0F, 3, 4, 3), PartPose.offsetAndRotation(0F, 18F, 0F, 0.7853982F, 1.570796F, 0F));
        PartDefinition Shape2 = partdefinition.addOrReplaceChild("Shape2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(1.5F, -5.5F, -1F, 1, 9, 1), PartPose.offsetAndRotation(0F, 15F, 0F, 0.7853982F, 1.570796F, 0F));
        PartDefinition Shape3 = partdefinition.addOrReplaceChild("Shape3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 0F, -4F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition Shape4 = partdefinition.addOrReplaceChild("Shape4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5.5F, 0F, -3.5F, 3, 4, 3), PartPose.offset(0F, 15.5F, 0F));
        PartDefinition Shape5 = partdefinition.addOrReplaceChild("Shape5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -3F, 2, 1, 2), PartPose.offset(0F, 14.5F, 0F));
        PartDefinition Shape6 = partdefinition.addOrReplaceChild("Shape6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.5F, 0F, -2.5F, 1, 5, 1), PartPose.offset(0F, 10F, 0F));
        PartDefinition Shape7 = partdefinition.addOrReplaceChild("Shape7", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5.5F, 0F, -3.5F, 3, 6, 3), PartPose.offset(0F, 4F, 0F));
        PartDefinition Shape8 = partdefinition.addOrReplaceChild("Shape8", CubeListBuilder.create().texOffs(46, 0).mirror().addBox(-1F, 0F, -6F, 8, 3, 1), PartPose.offset(0F, 19F, 0F));
        PartDefinition Shape8b = partdefinition.addOrReplaceChild("Shape8b", CubeListBuilder.create().texOffs(46, 0).mirror().addBox(-1F, 0F, 1F, 8, 3, 1), PartPose.offset(0F, 19F, 0F));
        PartDefinition Shape9 = partdefinition.addOrReplaceChild("Shape9", CubeListBuilder.create().texOffs(50, 0).mirror().addBox(6F, 0F, -5F, 1, 3, 6), PartPose.offset(0F, 19F, 0F));
        PartDefinition Shape9b = partdefinition.addOrReplaceChild("Shape9b", CubeListBuilder.create().texOffs(50, 0).mirror().addBox(-1F, 0F, -5F, 1, 3, 6), PartPose.offset(0F, 19F, 0F));
        PartDefinition Shape10 = partdefinition.addOrReplaceChild("Shape10", CubeListBuilder.create().texOffs(24, 8).mirror().addBox(-1F, 0F, -6F, 8, 1, 8), PartPose.offset(0F, 22F, 0F));
        PartDefinition Shape11 = partdefinition.addOrReplaceChild("Shape11", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.5F, -3F, 0F, 3, 2, 3), PartPose.offsetAndRotation(0F, 18F, 0F, 0.7853982F, 1.570796F, 0F));
        PartDefinition Shape12 = partdefinition.addOrReplaceChild("Shape12", CubeListBuilder.create().texOffs(18, 4).mirror().addBox(-1F, 0F, -1F, 1, 12, 1), PartPose.offset(0F, 11F, 0F));
        PartDefinition Shape13 = partdefinition.addOrReplaceChild("Shape13", CubeListBuilder.create().texOffs(18, 0).mirror().addBox(-2F, 0F, -1F, 3, 2, 1), PartPose.offset(0F, 9F, 0F));
        PartDefinition Shape14 = partdefinition.addOrReplaceChild("Shape14", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-7F, 0F, -7F, 14, 1, 14), PartPose.offset(0F, 23F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            Shape8.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape8b.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape9.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape9b.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape10.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape11.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape12.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape13.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape14.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderGlass(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            glass1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape7.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
