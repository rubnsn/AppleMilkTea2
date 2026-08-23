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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELAUTOMAKER) -> new ModelAutoMaker(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelAutoMaker {

    private final ModelPart root;
    private final ModelPart Shape1;
    private final ModelPart Shape2;
    private final ModelPart Shape3;
    private final ModelPart Shape4;

    public ModelAutoMaker(ModelPart root) {
        this.root = root;
        this.Shape1 = root.getChild("Shape1");
        this.Shape2 = root.getChild("Shape2");
        this.Shape3 = root.getChild("Shape3");
        this.Shape4 = root.getChild("Shape4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Shape1 = partdefinition.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(13, 16).addBox(-1F, 0F, -1F, 2, 1, 2), PartPose.offset(0F, 23F, 0F));
        PartDefinition Shape2 = partdefinition.addOrReplaceChild("Shape2", CubeListBuilder.create().texOffs(0, 16).addBox(-2F, 0F, -2F, 4, 1, 4), PartPose.offset(0F, 22F, 0F));
        PartDefinition Shape3 = partdefinition.addOrReplaceChild("Shape3", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -3F, 6, 3, 6), PartPose.offset(0F, 19F, 0F));
        PartDefinition Shape4 = partdefinition.addOrReplaceChild("Shape4", CubeListBuilder.create().texOffs(0, 9).addBox(-3F, 0F, -5F, 6, 1, 6), PartPose.offset(0F, 18F, 2F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            Shape1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Shape4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        this.Shape4.xRot = f3 / (180F / (float) Math.PI);
    }
}
