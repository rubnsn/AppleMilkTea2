package mods.defeatedcrow.client.entity.base;

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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELTEACUP) -> new ModelTeaCup(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelTeaCup {

    private final ModelPart root;
    private final ModelPart handle1;
    private final ModelPart handle3;
    private final ModelPart handle2;
    private final ModelPart bottom;
    private final ModelPart sideF;
    private final ModelPart sideB;
    private final ModelPart sideR;
    private final ModelPart sideL;
    private final ModelPart contents;

    public ModelTeaCup(ModelPart root) {
        this.root = root;
        this.handle1 = root.getChild("handle1");
        this.handle3 = root.getChild("handle3");
        this.handle2 = root.getChild("handle2");
        this.bottom = root.getChild("bottom");
        this.sideF = root.getChild("sideF");
        this.sideB = root.getChild("sideB");
        this.sideR = root.getChild("sideR");
        this.sideL = root.getChild("sideL");
        this.contents = root.getChild("contents");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition handle1 = partdefinition.addOrReplaceChild("handle1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-1F, 17F, -5F));
        PartDefinition handle3 = partdefinition.addOrReplaceChild("handle3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-1F, 22F, -5F));
        PartDefinition handle2 = partdefinition.addOrReplaceChild("handle2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 6, 1), PartPose.offset(-1F, 17F, -6F));
        PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -3F, 6, 1, 6), PartPose.offset(0F, 23F, 0F));
        PartDefinition sideF = partdefinition.addOrReplaceChild("sideF", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -3F, 6, 7, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition sideB = partdefinition.addOrReplaceChild("sideB", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, 2F, 6, 7, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition sideR = partdefinition.addOrReplaceChild("sideR", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -2F, 1, 7, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition sideL = partdefinition.addOrReplaceChild("sideL", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(2F, 0F, -2F, 1, 7, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition contents = partdefinition.addOrReplaceChild("contents", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, -2F, 4, 6, 4), PartPose.offset(0F, 17F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            handle1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handle3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handle2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideF.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideB.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideR.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideL.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderContents(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            contents.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        this.handle1.yRot = f3 / (180F / (float) Math.PI);
        this.handle2.yRot = f3 / (180F / (float) Math.PI);
        this.handle3.yRot = f3 / (180F / (float) Math.PI);

        this.bottom.yRot = f3 / (180F / (float) Math.PI);
        this.sideF.yRot = f3 / (180F / (float) Math.PI);
        this.sideB.yRot = f3 / (180F / (float) Math.PI);
        this.sideR.yRot = f3 / (180F / (float) Math.PI);
        this.sideL.yRot = f3 / (180F / (float) Math.PI);
        this.contents.yRot = f3 / (180F / (float) Math.PI);
    }
}
