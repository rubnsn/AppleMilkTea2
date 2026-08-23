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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELHANDLEENGINE) -> new ModelHandleEngine(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelHandleEngine {

    private final ModelPart root;
    private final ModelPart base;
    private final ModelPart shaft1;
    private final ModelPart shaft2;
    private final ModelPart shaft3;
    private final ModelPart handle;

    public ModelHandleEngine(ModelPart root) {
        this.root = root;
        this.base = root.getChild("base");
        this.shaft1 = root.getChild("shaft1");
        this.shaft2 = root.getChild("shaft2");
        this.shaft3 = root.getChild("shaft3");
        this.handle = root.getChild("handle");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(9, 0).mirror().addBox(-2F, 7F, -2F, 4, 1, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition shaft1 = partdefinition.addOrReplaceChild("shaft1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 1F, -0.5F, 1, 6, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition shaft2 = partdefinition.addOrReplaceChild("shaft2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 1F, 0.5F, 1, 1, 3), PartPose.offset(0F, 16F, 0F));
        PartDefinition shaft3 = partdefinition.addOrReplaceChild("shaft3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, 3.5F, 1, 2, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition handle = partdefinition.addOrReplaceChild("handle", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-1F, -4F, 3F, 2, 4, 2), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            handle.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            shaft1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            shaft2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            shaft3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderBase(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            base.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        this.handle.yRot = f3 / (180F / (float) Math.PI);
        this.shaft1.yRot = f3 / (180F / (float) Math.PI);
        this.shaft2.yRot = f3 / (180F / (float) Math.PI);
        this.shaft3.yRot = f3 / (180F / (float) Math.PI);
    }
}
