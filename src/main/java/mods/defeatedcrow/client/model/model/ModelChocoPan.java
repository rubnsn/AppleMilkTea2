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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELCHOCOPAN) -> new ModelChocoPan(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelChocoPan {

    private final ModelPart root;
    private final ModelPart contants1;
    private final ModelPart contants2;
    private final ModelPart contants3;

    public ModelChocoPan(ModelPart root) {
        this.root = root;
        this.contants1 = root.getChild("contants1");
        this.contants2 = root.getChild("contants2");
        this.contants3 = root.getChild("contants3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition contants1 = partdefinition.addOrReplaceChild("contants1", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 21F, 0F));
        PartDefinition contants2 = partdefinition.addOrReplaceChild("contants2", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10, 3, 10), PartPose.offset(0F, 19F, 0F));
        PartDefinition contants3 = partdefinition.addOrReplaceChild("contants3", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10, 5, 10), PartPose.offset(0F, 17F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte par5) {
            contants1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        if (par5 > 3) {
            contants2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            if (par5 > 7) {
            contants3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            }
        }
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
