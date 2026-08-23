package mods.defeatedcrow.client.entity.base;

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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELINNERKOBATI) -> new ModelInnerKobati(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelInnerKobati {

    private final ModelPart root;
    private final ModelPart kobathi1;
    private final ModelPart kobathi2;
    private final ModelPart kobathi3;
    private final ModelPart kobathi4;
    private final ModelPart kobathi5;
    private final ModelPart kobathi6;
    private final ModelPart kobathi7;
    private final ModelPart top1;
    private final ModelPart top2;
    private final ModelPart top3;

    public ModelInnerKobati(ModelPart root) {
        this.root = root;
        this.kobathi1 = root.getChild("kobathi1");
        this.kobathi2 = root.getChild("kobathi2");
        this.kobathi3 = root.getChild("kobathi3");
        this.kobathi4 = root.getChild("kobathi4");
        this.kobathi5 = root.getChild("kobathi5");
        this.kobathi6 = root.getChild("kobathi6");
        this.kobathi7 = root.getChild("kobathi7");
        this.top1 = root.getChild("top1");
        this.top2 = root.getChild("top2");
        this.top3 = root.getChild("top3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition kobathi1 = partdefinition.addOrReplaceChild("kobathi1", CubeListBuilder.create().texOffs(5, 0).mirror().addBox(0F, -1F, 0F, 1, 3, 1), PartPose.offsetAndRotation(0F, 20F, 0F, 0.7853982F, 0.5061455F, 0F));
        PartDefinition kobathi2 = partdefinition.addOrReplaceChild("kobathi2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, -2F, -5F, 1, 3, 1), PartPose.offsetAndRotation(0F, 17F, 0F, 1.570796F, 1.919862F, 0F));
        PartDefinition kobathi3 = partdefinition.addOrReplaceChild("kobathi3", CubeListBuilder.create().texOffs(5, 0).mirror().addBox(0.5F, -1F, -2F, 1, 3, 1), PartPose.offsetAndRotation(0F, 20F, 0F, 1.570796F, 1.832596F, 0F));
        PartDefinition kobathi4 = partdefinition.addOrReplaceChild("kobathi4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, -1F, -1F, 1, 2, 1), PartPose.offsetAndRotation(0F, 20F, 0F, 1.570796F, 0.9948377F, 0F));
        PartDefinition kobathi5 = partdefinition.addOrReplaceChild("kobathi5", CubeListBuilder.create().texOffs(5, 0).mirror().addBox(0F, -0.5F, 0F, 1, 3, 1), PartPose.offsetAndRotation(0F, 20F, 0F, 0.9773844F, -2.321288F, 0F));
        PartDefinition kobathi6 = partdefinition.addOrReplaceChild("kobathi6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.5F, -1F, -1F, 1, 3, 1), PartPose.offsetAndRotation(0F, 20F, 0F, 1.134464F, 1.396263F, -0.418879F));
        PartDefinition kobathi7 = partdefinition.addOrReplaceChild("kobathi7", CubeListBuilder.create().texOffs(5, 0).mirror().addBox(-1.5F, 0F, 0.5F, 1, 2, 1), PartPose.offsetAndRotation(0F, 20F, 0F, 1.047198F, 0.7853982F, -0.3839724F));
        PartDefinition top1 = partdefinition.addOrReplaceChild("top1", CubeListBuilder.create().texOffs(0, 5).mirror().addBox(-0.5F, -1.5F, -0.2F, 1, 1, 1), PartPose.offsetAndRotation(0F, 20F, 0F, -0.3839724F, 0.1047198F, 0F));
        PartDefinition top2 = partdefinition.addOrReplaceChild("top2", CubeListBuilder.create().texOffs(0, 5).mirror().addBox(0F, -1.5F, -1F, 1, 1, 1), PartPose.offsetAndRotation(0F, 20F, 0F, 0.6283185F, -0.1919862F, 0F));
        PartDefinition top3 = partdefinition.addOrReplaceChild("top3", CubeListBuilder.create().texOffs(0, 5).mirror().addBox(-1F, -1.3F, -1.2F, 1, 1, 1), PartPose.offsetAndRotation(0F, 20F, 0F, -0.2443461F, -0.122173F, -0.2094395F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    @Override
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            kobathi1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            kobathi2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            kobathi3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            kobathi4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            kobathi5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            kobathi6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            kobathi7.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
