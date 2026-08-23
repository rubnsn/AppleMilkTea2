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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELCHOPSTICKS) -> new ModelChopsticks(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelChopsticks {

    private final ModelPart root;
    private final ModelPart cup;
    private final ModelPart stick1;
    private final ModelPart stick2;
    private final ModelPart stick3;
    private final ModelPart stick4;
    private final ModelPart stick5;
    private final ModelPart stick6;

    public ModelChopsticks(ModelPart root) {
        this.root = root;
        this.cup = root.getChild("cup");
        this.stick1 = root.getChild("stick1");
        this.stick2 = root.getChild("stick2");
        this.stick3 = root.getChild("stick3");
        this.stick4 = root.getChild("stick4");
        this.stick5 = root.getChild("stick5");
        this.stick6 = root.getChild("stick6");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition cup = partdefinition.addOrReplaceChild("cup", CubeListBuilder.create().texOffs(5, 0).mirror().addBox(0F, 0F, 0F, 4, 5, 4), PartPose.offset(-2F, 19F, -2F));
        PartDefinition stick1 = partdefinition.addOrReplaceChild("stick1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 1, 8, 1), PartPose.offsetAndRotation(1F, 14F, 0F, 0F, 0F, 0.2094395F));
        PartDefinition stick2 = partdefinition.addOrReplaceChild("stick2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 1, 8, 1), PartPose.offsetAndRotation(1F, 14F, -2F, 0.1745329F, 0F, 0.0872665F));
        PartDefinition stick3 = partdefinition.addOrReplaceChild("stick3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 1, 8, 1), PartPose.offsetAndRotation(0F, 14F, 1.5F, -0.2617994F, 0F, 0F));
        PartDefinition stick4 = partdefinition.addOrReplaceChild("stick4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 1, 8, 1), PartPose.offsetAndRotation(-2F, 14F, 2F, -0.2617994F, 0F, -0.1745329F));
        PartDefinition stick5 = partdefinition.addOrReplaceChild("stick5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 1, 8, 1), PartPose.offsetAndRotation(-2.5F, 15F, -2.5F, 0.2617994F, 0F, -0.2617994F));
        PartDefinition stick6 = partdefinition.addOrReplaceChild("stick6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 1, 8, 1), PartPose.offsetAndRotation(-1F, 14F, -1F, 0.0523599F, 0F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte par5) {
            cup.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        if (par5 > 0) {
            stick1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            stick2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            stick3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            if (par5 > 1) {
            stick4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                if (par5 > 2) {
            stick5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                    if (par5 > 3) {
            stick6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                    }
                }
            }
        }
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
