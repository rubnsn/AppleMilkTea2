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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELALTBOWL) -> new ModelAltBowl(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelAltBowl {

    private final ModelPart root;
    private final ModelPart plate1;
    private final ModelPart plate2;
    private final ModelPart plate3;
    private final ModelPart plate4;
    private final ModelPart plate5;
    private final ModelPart plate6;
    private final ModelPart plate7;
    private final ModelPart plate8;
    private final ModelPart plate9;

    public ModelAltBowl(ModelPart root) {
        this.root = root;
        this.plate1 = root.getChild("plate1");
        this.plate2 = root.getChild("plate2");
        this.plate3 = root.getChild("plate3");
        this.plate4 = root.getChild("plate4");
        this.plate5 = root.getChild("plate5");
        this.plate6 = root.getChild("plate6");
        this.plate7 = root.getChild("plate7");
        this.plate8 = root.getChild("plate8");
        this.plate9 = root.getChild("plate9");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition plate1 = partdefinition.addOrReplaceChild("plate1", CubeListBuilder.create().texOffs(16, 8).mirror().addBox(-3F, -3F, 0F, 6, 6, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition plate2 = partdefinition.addOrReplaceChild("plate2", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(-3F, -6.5F, 0.5F, 6, 4, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2617994F, 0F, 0F));
        PartDefinition plate3 = partdefinition.addOrReplaceChild("plate3", CubeListBuilder.create().texOffs(16, 18).mirror().addBox(-3F, 2.5F, 0.5F, 6, 4, 1), PartPose.offsetAndRotation(0F, 16F, 0F, -0.2617994F, 0F, 0F));
        PartDefinition plate4 = partdefinition.addOrReplaceChild("plate4", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-6.5F, -3F, 0.5F, 4, 6, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, -0.2617994F, 0F));
        PartDefinition plate5 = partdefinition.addOrReplaceChild("plate5", CubeListBuilder.create().texOffs(36, 8).mirror().addBox(2.5F, -3F, 0.5F, 4, 6, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 0.2617994F, 0F));
        PartDefinition plate6 = partdefinition.addOrReplaceChild("plate6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6.5F, -6.5F, 1F, 4, 4, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2617994F, -0.2617994F, 0F));
        PartDefinition plate7 = partdefinition.addOrReplaceChild("plate7", CubeListBuilder.create().texOffs(36, 0).mirror().addBox(2.5F, -6.5F, 1F, 4, 4, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2617994F, 0.2617994F, 0F));
        PartDefinition plate8 = partdefinition.addOrReplaceChild("plate8", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-6.5F, 2.5F, 1F, 4, 4, 1), PartPose.offsetAndRotation(0F, 16F, 0F, -0.2617994F, -0.2617994F, 0F));
        PartDefinition plate9 = partdefinition.addOrReplaceChild("plate9", CubeListBuilder.create().texOffs(36, 18).mirror().addBox(2.5F, 2.5F, 1F, 4, 4, 1), PartPose.offsetAndRotation(0F, 16F, 0F, -0.2617994F, 0.2617994F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    @Override
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            plate1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            plate2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            plate3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            plate4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            plate5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            plate6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            plate7.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            plate8.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            plate9.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
