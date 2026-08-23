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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELCUPHANDLE) -> new ModelCupHandle(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelCupHandle {

    private final ModelPart root;
    private final ModelPart handlea1;
    private final ModelPart handlea3;
    private final ModelPart handlea2;
    private final ModelPart handleb1;
    private final ModelPart handleb3;
    private final ModelPart handleb2;
    private final ModelPart handlec1;
    private final ModelPart handlec3;
    private final ModelPart handlec2;
    private final ModelPart handled1;
    private final ModelPart handled3;
    private final ModelPart handled2;
    private final ModelPart sideF;
    private final ModelPart sideB;
    private final ModelPart sideR;
    private final ModelPart sideL;
    private final ModelPart Bottom;

    public ModelCupHandle(ModelPart root) {
        this.root = root;
        this.handlea1 = root.getChild("handlea1");
        this.handlea3 = root.getChild("handlea3");
        this.handlea2 = root.getChild("handlea2");
        this.handleb1 = root.getChild("handleb1");
        this.handleb3 = root.getChild("handleb3");
        this.handleb2 = root.getChild("handleb2");
        this.handlec1 = root.getChild("handlec1");
        this.handlec3 = root.getChild("handlec3");
        this.handlec2 = root.getChild("handlec2");
        this.handled1 = root.getChild("handled1");
        this.handled3 = root.getChild("handled3");
        this.handled2 = root.getChild("handled2");
        this.sideF = root.getChild("sideF");
        this.sideB = root.getChild("sideB");
        this.sideR = root.getChild("sideR");
        this.sideL = root.getChild("sideL");
        this.Bottom = root.getChild("Bottom");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition handlea1 = partdefinition.addOrReplaceChild("handlea1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-1.0F, 17.0F, -5.0F));
        PartDefinition handlea3 = partdefinition.addOrReplaceChild("handlea3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-1.0F, 22.0F, -5.0F));
        PartDefinition handlea2 = partdefinition.addOrReplaceChild("handlea2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 6, 1), PartPose.offset(-1.0F, 17.0F, -6.0F));
        PartDefinition handleb1 = partdefinition.addOrReplaceChild("handleb1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-1F, 17F, 3F));
        PartDefinition handleb3 = partdefinition.addOrReplaceChild("handleb3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-1F, 22F, 3F));
        PartDefinition handleb2 = partdefinition.addOrReplaceChild("handleb2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 6, 1), PartPose.offset(-1F, 17F, 5F));
        PartDefinition handlec1 = partdefinition.addOrReplaceChild("handlec1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(3F, 17F, -1F));
        PartDefinition handlec3 = partdefinition.addOrReplaceChild("handlec3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(3F, 22F, -1F));
        PartDefinition handlec2 = partdefinition.addOrReplaceChild("handlec2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 1, 6, 2), PartPose.offset(5F, 17F, -1F));
        PartDefinition handled1 = partdefinition.addOrReplaceChild("handled1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-5F, 17F, -1F));
        PartDefinition handled3 = partdefinition.addOrReplaceChild("handled3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-5F, 22F, -1F));
        PartDefinition handled2 = partdefinition.addOrReplaceChild("handled2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 1, 6, 2), PartPose.offset(-6F, 17F, -1F));
        PartDefinition sideF = partdefinition.addOrReplaceChild("sideF", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-3F, 0F, -3F, 6, 8, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition sideB = partdefinition.addOrReplaceChild("sideB", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-3F, 0F, 2F, 6, 8, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition sideR = partdefinition.addOrReplaceChild("sideR", CubeListBuilder.create().texOffs(15, 10).mirror().addBox(-3F, 0F, -2F, 1, 8, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition sideL = partdefinition.addOrReplaceChild("sideL", CubeListBuilder.create().texOffs(15, 10).mirror().addBox(2F, 0F, -2F, 1, 8, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition Bottom = partdefinition.addOrReplaceChild("Bottom", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, -2F, 4, 2, 4), PartPose.offset(0F, 22F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte par5) {
        // if (DCsConfig.useJapaneseCup)
        // {
        //             sideB.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        //             sideF.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        //             sideL.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        //             sideR.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        //             Bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        // }
        // else
        // {
        if (par5 == 0) {
            handleb1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handleb2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handleb3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else if (par5 == 1) {
            handlec1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handlec2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handlec3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else if (par5 == 2) {
            handlea1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handlea2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handlea3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else if (par5 == 4) {
            handled1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handled2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handled3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
        // }
    }

    public void renderSummer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            sideB.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideF.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideL.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideR.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            Bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
