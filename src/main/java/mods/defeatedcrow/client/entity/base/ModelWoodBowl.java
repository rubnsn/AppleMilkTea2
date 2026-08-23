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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELWOODBOWL) -> new ModelWoodBowl(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelWoodBowl {

    private final ModelPart root;
    private final ModelPart bottom;
    private final ModelPart bottom2;
    private final ModelPart sideF;
    private final ModelPart sideB;
    private final ModelPart sideR;
    private final ModelPart sideL;

    public ModelWoodBowl(ModelPart root) {
        this.root = root;
        this.bottom = root.getChild("bottom");
        this.bottom2 = root.getChild("bottom2");
        this.sideF = root.getChild("sideF");
        this.sideB = root.getChild("sideB");
        this.sideR = root.getChild("sideR");
        this.sideL = root.getChild("sideL");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -3F, 6, 1, 6), PartPose.offset(0F, 23F, 0F));
        PartDefinition bottom2 = partdefinition.addOrReplaceChild("bottom2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -4F, 8, 1, 8), PartPose.offset(0F, 22F, 0F));
        PartDefinition sideF = partdefinition.addOrReplaceChild("sideF", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -5F, 10, 3, 1), PartPose.offset(0F, 19F, 0F));
        PartDefinition sideB = partdefinition.addOrReplaceChild("sideB", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, 4F, 10, 3, 1), PartPose.offset(0F, 19F, 0F));
        PartDefinition sideR = partdefinition.addOrReplaceChild("sideR", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -4F, 1, 3, 8), PartPose.offset(0F, 19F, 0F));
        PartDefinition sideL = partdefinition.addOrReplaceChild("sideL", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, -4F, 1, 3, 8), PartPose.offset(0F, 19F, 0F));
        return LayerDefinition.create(meshdefinition, 32, 16);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bottom2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideF.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideB.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideR.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideL.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        this.bottom.yRot = f3 / (180F / (float) Math.PI);
        this.bottom2.yRot = f3 / (180F / (float) Math.PI);
        this.sideF.yRot = f3 / (180F / (float) Math.PI);
        this.sideB.yRot = f3 / (180F / (float) Math.PI);
        this.sideR.yRot = f3 / (180F / (float) Math.PI);
        this.sideL.yRot = f3 / (180F / (float) Math.PI);
    }
}
