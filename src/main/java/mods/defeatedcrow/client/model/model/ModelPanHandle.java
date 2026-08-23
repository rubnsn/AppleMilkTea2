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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELPANHANDLE) -> new ModelPanHandle(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelPanHandle {

    private final ModelPart root;
    private final ModelPart handlea1;
    private final ModelPart handlea2;
    private final ModelPart handlea3;
    private final ModelPart handlea4;

    public ModelPanHandle(ModelPart root) {
        this.root = root;
        this.handlea1 = root.getChild("handlea1");
        this.handlea2 = root.getChild("handlea2");
        this.handlea3 = root.getChild("handlea3");
        this.handlea4 = root.getChild("handlea4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition handlea1 = partdefinition.addOrReplaceChild("handlea1", CubeListBuilder.create().texOffs(0, 0).addBox(6F, 0F, -1.5F, 2, 1, 3), PartPose.offset(0F, 16F, 0F));
        PartDefinition handlea2 = partdefinition.addOrReplaceChild("handlea2", CubeListBuilder.create().texOffs(0, 0).addBox(-8F, 0F, -1.5F, 2, 1, 3), PartPose.offset(0F, 16F, 0F));
        PartDefinition handlea3 = partdefinition.addOrReplaceChild("handlea3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, 6F, 3, 1, 2), PartPose.offset(0F, 16F, 0F));
        PartDefinition handlea4 = partdefinition.addOrReplaceChild("handlea4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -8F, 3, 1, 2), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte par5) {
        if (par5 == 0 || par5 == 2) {
            handlea1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handlea2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else {
            handlea3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handlea4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
    }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
