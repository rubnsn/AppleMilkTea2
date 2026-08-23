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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELLARGEBOTTLE) -> new ModelLargeBottle(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelLargeBottle {

    private final ModelPart root;
    private final ModelPart bottom;
    private final ModelPart middle1;
    private final ModelPart middle2;
    private final ModelPart middle3;
    private final ModelPart top;
    private final ModelPart cap;

    public ModelLargeBottle(ModelPart root) {
        this.root = root;
        this.bottom = root.getChild("bottom");
        this.middle1 = root.getChild("middle1");
        this.middle2 = root.getChild("middle2");
        this.middle3 = root.getChild("middle3");
        this.top = root.getChild("top");
        this.cap = root.getChild("cap");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(-3F, 0F, -3F, 6, 7, 6), PartPose.offset(0F, 17F, 0F));
        PartDefinition middle1 = partdefinition.addOrReplaceChild("middle1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.5F, 0F, -2.5F, 5, 2, 5), PartPose.offset(0F, 15F, 0F));
        PartDefinition middle2 = partdefinition.addOrReplaceChild("middle2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, -2F, 4, 1, 4), PartPose.offset(0F, 14F, 0F));
        PartDefinition middle3 = partdefinition.addOrReplaceChild("middle3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -1.5F, 3, 1, 3), PartPose.offset(0F, 13F, 0F));
        PartDefinition top = partdefinition.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, 0F, -1F, 2, 2, 2), PartPose.offset(0F, 11F, 0F));
        PartDefinition cap = partdefinition.addOrReplaceChild("cap", CubeListBuilder.create().texOffs(20, 0).mirror().addBox(-1F, 0F, -1F, 2, 2, 2), PartPose.offset(0F, 9F, 0F));
        return LayerDefinition.create(meshdefinition, 32, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {

            bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            cap.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        this.bottom.yRot = f3 / (180F / (float) Math.PI);
    }
}
