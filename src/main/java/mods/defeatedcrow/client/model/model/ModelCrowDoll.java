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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELCROWDOLL) -> new ModelCrowDoll(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelCrowDoll {

    private final ModelPart root;
    private final ModelPart base;
    private final ModelPart body;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart hip;
    private final ModelPart tail;
    private final ModelPart top;
    private final ModelPart wingL;
    private final ModelPart wingR;
    private final ModelPart mouth;
    private final ModelPart legL;
    private final ModelPart legR;

    public ModelCrowDoll(ModelPart root) {
        this.root = root;
        this.base = root.getChild("base");
        this.body = root.getChild("body");
        this.neck = root.getChild("neck");
        this.head = root.getChild("head");
        this.hip = root.getChild("hip");
        this.tail = root.getChild("tail");
        this.top = root.getChild("top");
        this.wingL = root.getChild("wingL");
        this.wingR = root.getChild("wingR");
        this.mouth = root.getChild("mouth");
        this.legL = root.getChild("legL");
        this.legR = root.getChild("legR");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 7F, -4F, 8, 1, 8), PartPose.offset(0F, 16F, 0F));
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-3.5F, 3F, -2.8F, 7, 3, 6), PartPose.offset(0F, 16F, 0F));
        PartDefinition neck = partdefinition.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-3F, 2F, -2.7F, 6, 1, 5), PartPose.offset(0F, 16F, 0F));
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-2.5F, -1F, -2.5F, 5, 3, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition hip = partdefinition.addOrReplaceChild("hip", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-3F, 3F, 3F, 6, 2, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(20, 20).mirror().addBox(-2F, 4F, 2F, 4, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2268928F, 0F, 0F));
        PartDefinition top = partdefinition.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-2F, 2.5F, -2F, 4, 1, 3), PartPose.offset(0F, 12F, 0F));
        PartDefinition wingL = partdefinition.addOrReplaceChild("wingL", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-2F, 0F, 0F, 4, 1, 3), PartPose.offsetAndRotation(3.5F, 18F, -0.5F, -1.396263F, 1.570796F, 0F));
        PartDefinition wingR = partdefinition.addOrReplaceChild("wingR", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-2F, 0F, 0F, 4, 1, 3), PartPose.offsetAndRotation(-3.5F, 18F, -0.5F, -1.396263F, -1.570796F, 0F));
        PartDefinition mouth = partdefinition.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(34, 0).mirror().addBox(-1F, 1F, -3.5F, 2, 1, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition legL = partdefinition.addOrReplaceChild("legL", CubeListBuilder.create().texOffs(34, 3).mirror().addBox(0.5F, 6F, -1F, 1, 1, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition legR = partdefinition.addOrReplaceChild("legR", CubeListBuilder.create().texOffs(34, 3).mirror().addBox(-1.5F, 6F, -1F, 1, 1, 1), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    @Override
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            body.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            neck.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            head.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            hip.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tail.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            wingL.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            wingR.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            mouth.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            legL.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            legR.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderBase(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            base.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
