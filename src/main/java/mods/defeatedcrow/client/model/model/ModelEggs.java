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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELEGGS) -> new ModelEggs(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelEggs {

    private final ModelPart root;
    private final ModelPart bottom1;
    private final ModelPart bottom2;
    private final ModelPart bottom3;
    private final ModelPart bottom4;
    private final ModelPart bottom5;
    private final ModelPart bottom6;
    private final ModelPart bottom7;
    private final ModelPart bottom8;
    private final ModelPart top1;
    private final ModelPart top2;
    private final ModelPart top3;
    private final ModelPart top4;
    private final ModelPart top5;
    private final ModelPart top6;
    private final ModelPart top7;
    private final ModelPart top8;

    public ModelEggs(ModelPart root) {
        this.root = root;
        this.bottom1 = root.getChild("bottom1");
        this.bottom2 = root.getChild("bottom2");
        this.bottom3 = root.getChild("bottom3");
        this.bottom4 = root.getChild("bottom4");
        this.bottom5 = root.getChild("bottom5");
        this.bottom6 = root.getChild("bottom6");
        this.bottom7 = root.getChild("bottom7");
        this.bottom8 = root.getChild("bottom8");
        this.top1 = root.getChild("top1");
        this.top2 = root.getChild("top2");
        this.top3 = root.getChild("top3");
        this.top4 = root.getChild("top4");
        this.top5 = root.getChild("top5");
        this.top6 = root.getChild("top6");
        this.top7 = root.getChild("top7");
        this.top8 = root.getChild("top8");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bottom1 = partdefinition.addOrReplaceChild("bottom1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 3, 3, 3), PartPose.offset(-6.5F, 21F, -5F));
        PartDefinition bottom2 = partdefinition.addOrReplaceChild("bottom2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 3, 3, 3), PartPose.offset(-1.5F, 21F, -5F));
        PartDefinition bottom3 = partdefinition.addOrReplaceChild("bottom3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 3, 3, 3), PartPose.offset(3.5F, 21F, -5F));
        PartDefinition bottom4 = partdefinition.addOrReplaceChild("bottom4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 3, 3, 3), PartPose.offset(-4F, 21F, -1F));
        PartDefinition bottom5 = partdefinition.addOrReplaceChild("bottom5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 3, 3, 3), PartPose.offset(1F, 21F, -1F));
        PartDefinition bottom6 = partdefinition.addOrReplaceChild("bottom6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 3, 3, 3), PartPose.offset(-1.5F, 21F, 3F));
        PartDefinition bottom7 = partdefinition.addOrReplaceChild("bottom7", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 3, 3, 3), PartPose.offset(3.5F, 21F, 3F));
        PartDefinition bottom8 = partdefinition.addOrReplaceChild("bottom8", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 3, 3, 3), PartPose.offset(-6.5F, 21F, 3F));
        PartDefinition top1 = partdefinition.addOrReplaceChild("top1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-6F, 20F, -4.5F));
        PartDefinition top2 = partdefinition.addOrReplaceChild("top2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-1F, 20F, -4.5F));
        PartDefinition top3 = partdefinition.addOrReplaceChild("top3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(4F, 20F, -4.5F));
        PartDefinition top4 = partdefinition.addOrReplaceChild("top4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-3.5F, 20F, -0.5F));
        PartDefinition top5 = partdefinition.addOrReplaceChild("top5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(1.5F, 20F, -0.5F));
        PartDefinition top6 = partdefinition.addOrReplaceChild("top6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-6F, 20F, 3.5F));
        PartDefinition top7 = partdefinition.addOrReplaceChild("top7", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-1F, 20F, 3.5F));
        PartDefinition top8 = partdefinition.addOrReplaceChild("top8", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(4F, 20F, 3.5F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte par5) {
            bottom1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bottom2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bottom3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bottom4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bottom5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bottom6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bottom7.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bottom8.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top7.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top8.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
