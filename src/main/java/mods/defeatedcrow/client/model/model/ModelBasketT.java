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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELBASKETT) -> new ModelBasketT(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelBasketT {

    private final ModelPart root;
    private final ModelPart base;
    private final ModelPart sideF;
    private final ModelPart sideB;
    private final ModelPart sideR;
    private final ModelPart sideL;

    public ModelBasketT(ModelPart root) {
        this.root = root;
        this.base = root.getChild("base");
        this.sideF = root.getChild("sideF");
        this.sideB = root.getChild("sideB");
        this.sideR = root.getChild("sideR");
        this.sideL = root.getChild("sideL");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 36).mirror().addBox(-5F, 7F, -5F, 10, 1, 10), PartPose.offset(0F, 16F, 0F));
        PartDefinition sideF = partdefinition.addOrReplaceChild("sideF", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(-5F, -1F, -5F, 10, 8, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition sideB = partdefinition.addOrReplaceChild("sideB", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(-5F, -1F, 4F, 10, 8, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition sideR = partdefinition.addOrReplaceChild("sideR", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-4F, -1F, -5F, 8, 8, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        PartDefinition sideL = partdefinition.addOrReplaceChild("sideL", CubeListBuilder.create().texOffs(32, 48).mirror().addBox(-4F, -1F, 4F, 8, 8, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            base.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideF.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideB.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideR.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideL.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
