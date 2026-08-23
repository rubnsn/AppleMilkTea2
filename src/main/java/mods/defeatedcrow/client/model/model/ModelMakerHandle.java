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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELMAKERHANDLE) -> new ModelMakerHandle(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelMakerHandle {

    private final ModelPart root;
    private final ModelPart handle1;
    private final ModelPart handle2;
    private final ModelPart handle3;
    private final ModelPart spout;

    public ModelMakerHandle(ModelPart root) {
        this.root = root;
        this.handle1 = root.getChild("handle1");
        this.handle2 = root.getChild("handle2");
        this.handle3 = root.getChild("handle3");
        this.spout = root.getChild("spout");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition handle1 = partdefinition.addOrReplaceChild("handle1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -7F, 2, 1, 3), PartPose.offset(0F, 10F, 0F));
        PartDefinition handle2 = partdefinition.addOrReplaceChild("handle2", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -7F, 2, 8, 1), PartPose.offset(0F, 11F, 0F));
        PartDefinition handle3 = partdefinition.addOrReplaceChild("handle3", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -7F, 2, 1, 2), PartPose.offset(0F, 19F, 0F));
        PartDefinition spout = partdefinition.addOrReplaceChild("spout", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, 4F, 3, 1, 2), PartPose.offset(0F, 9F, 0F));
        return LayerDefinition.create(meshdefinition, 32, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            handle1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handle2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handle3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            spout.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        this.handle1.yRot = f3 / (180F / (float) Math.PI);
        this.handle2.yRot = f3 / (180F / (float) Math.PI);
        this.handle3.yRot = f3 / (180F / (float) Math.PI);
        this.spout.yRot = f3 / (180F / (float) Math.PI);
    }
}
