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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELBARRELBASE) -> new ModelBarrelBase(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelBarrelBase {

    private final ModelPart root;
    private final ModelPart base1;
    private final ModelPart base2;
    private final ModelPart base3;
    private final ModelPart base4;
    private final ModelPart base5;
    private final ModelPart base6;
    private final ModelPart base7;
    private final ModelPart base8;
    private final ModelPart base9;
    private final ModelPart base10;
    private final ModelPart base11;
    private final ModelPart base12;
    private final ModelPart base13;
    private final ModelPart base14;

    public ModelBarrelBase(ModelPart root) {
        this.root = root;
        this.base1 = root.getChild("base1");
        this.base2 = root.getChild("base2");
        this.base3 = root.getChild("base3");
        this.base4 = root.getChild("base4");
        this.base5 = root.getChild("base5");
        this.base6 = root.getChild("base6");
        this.base7 = root.getChild("base7");
        this.base8 = root.getChild("base8");
        this.base9 = root.getChild("base9");
        this.base10 = root.getChild("base10");
        this.base11 = root.getChild("base11");
        this.base12 = root.getChild("base12");
        this.base13 = root.getChild("base13");
        this.base14 = root.getChild("base14");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition base1 = partdefinition.addOrReplaceChild("base1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 8.1F, 6F, 12, 1, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        PartDefinition base2 = partdefinition.addOrReplaceChild("base2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 8.1F, -7F, 12, 1, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        PartDefinition base3 = partdefinition.addOrReplaceChild("base3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 6F, 6F, 12, 1, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        PartDefinition base4 = partdefinition.addOrReplaceChild("base4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 6F, -7F, 12, 1, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        PartDefinition base5 = partdefinition.addOrReplaceChild("base5", CubeListBuilder.create().texOffs(0, 4).mirror().addBox(-8F, 8.1F, -7F, 16, 1, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition base6 = partdefinition.addOrReplaceChild("base6", CubeListBuilder.create().texOffs(0, 4).mirror().addBox(-8F, 8.1F, 6F, 16, 1, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition base7 = partdefinition.addOrReplaceChild("base7", CubeListBuilder.create().texOffs(0, 4).mirror().addBox(-8F, 6F, -7F, 16, 1, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition base8 = partdefinition.addOrReplaceChild("base8", CubeListBuilder.create().texOffs(0, 4).mirror().addBox(-8F, 6F, 6F, 16, 1, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition base9 = partdefinition.addOrReplaceChild("base9", CubeListBuilder.create().texOffs(36, 0).mirror().addBox(6F, 7F, -7F, 1, 1, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition base10 = partdefinition.addOrReplaceChild("base10", CubeListBuilder.create().texOffs(36, 0).mirror().addBox(-7F, 7F, -7F, 1, 1, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition base11 = partdefinition.addOrReplaceChild("base11", CubeListBuilder.create().texOffs(36, 0).mirror().addBox(6F, 7F, 6F, 1, 1, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition base12 = partdefinition.addOrReplaceChild("base12", CubeListBuilder.create().texOffs(36, 0).mirror().addBox(-7F, 7F, 6F, 1, 1, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition base13 = partdefinition.addOrReplaceChild("base13", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-7F, 5F, 4.5F, 14, 1, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        PartDefinition base14 = partdefinition.addOrReplaceChild("base14", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-7F, 5F, -5.5F, 14, 1, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            base1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base7.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base8.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base9.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base10.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base11.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base12.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base13.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base14.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
