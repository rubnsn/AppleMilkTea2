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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELALTCATORARYS) -> new ModelAltCatorarys(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelAltCatorarys {

    private final ModelPart root;
    private final ModelPart Shape1;
    private final ModelPart Shape2;
    private final ModelPart Shape3;
    private final ModelPart Shape4;
    private final ModelPart Shape5;
    private final ModelPart Shape6;
    private final ModelPart Shape7;
    private final ModelPart Shape8;
    private final ModelPart Shape9;
    private final ModelPart Shape10;

    public ModelAltCatorarys(ModelPart root) {
        this.root = root;
        this.Shape1 = root.getChild("Shape1");
        this.Shape2 = root.getChild("Shape2");
        this.Shape3 = root.getChild("Shape3");
        this.Shape4 = root.getChild("Shape4");
        this.Shape5 = root.getChild("Shape5");
        this.Shape6 = root.getChild("Shape6");
        this.Shape7 = root.getChild("Shape7");
        this.Shape8 = root.getChild("Shape8");
        this.Shape9 = root.getChild("Shape9");
        this.Shape10 = root.getChild("Shape10");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Shape1 = partdefinition.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 0F, -0.5F, 10, 1, 1), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0.0523599F));
        PartDefinition Shape2 = partdefinition.addOrReplaceChild("Shape2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, -1.5F, 4, 1, 3), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0.0523599F));
        PartDefinition Shape3 = partdefinition.addOrReplaceChild("Shape3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, -1F, -2F, 10, 1, 1), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, -0.0698132F, -0.0349066F));
        PartDefinition Shape4 = partdefinition.addOrReplaceChild("Shape4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, -1F, -2.5F, 1, 1, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, -0.0698132F, -0.0349066F));
        PartDefinition Shape5 = partdefinition.addOrReplaceChild("Shape5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(5F, -1F, -2.7F, 3, 1, 1), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, -0.0698132F, -0.0349066F));
        PartDefinition Shape6 = partdefinition.addOrReplaceChild("Shape6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(5F, -1F, -1.2F, 3, 1, 1), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, -0.0698132F, -0.0349066F));
        PartDefinition Shape7 = partdefinition.addOrReplaceChild("Shape7", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(2.5F, 0F, -4F, 4, 1, 3), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0.0872665F, 0F));
        PartDefinition Shape8 = partdefinition.addOrReplaceChild("Shape8", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7.5F, 0F, -3F, 10, 1, 1), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0.0872665F, 0F));
        PartDefinition Shape9 = partdefinition.addOrReplaceChild("Shape9", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, -0.5F, 0F, 8, 1, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, -0.0174533F, -0.0523599F));
        PartDefinition Shape10 = partdefinition.addOrReplaceChild("Shape10", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-8F, -0.5F, 1F, 7, 1, 1), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, -0.0174533F, -0.0523599F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    @Override
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            Shape1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape7.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape8.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape9.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape10.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
