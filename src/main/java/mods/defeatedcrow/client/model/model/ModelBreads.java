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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELBREADS) -> new ModelBreads(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelBreads {

    private final ModelPart root;
    private final ModelPart bread1;
    private final ModelPart bread2;
    private final ModelPart bread3;
    private final ModelPart bread4;
    private final ModelPart bread5;
    private final ModelPart bottom1;
    private final ModelPart bottom2;
    private final ModelPart bottom3;
    private final ModelPart bottom4;
    private final ModelPart bottom5;
    private final ModelPart bottom6;
    private final ModelPart bottom7;
    private final ModelPart bottom8;
    private final ModelPart bottom9;
    private final ModelPart middle1;
    private final ModelPart middle2;
    private final ModelPart middle3;
    private final ModelPart middle4;
    private final ModelPart middle5;
    private final ModelPart middle6;
    private final ModelPart middle7;
    private final ModelPart middle8;
    private final ModelPart middle9;
    private final ModelPart top1;
    private final ModelPart top2;
    private final ModelPart top3;
    private final ModelPart top4;
    private final ModelPart top5;
    private final ModelPart top6;
    private final ModelPart top7;
    private final ModelPart top8;
    private final ModelPart top9;

    public ModelBreads(ModelPart root) {
        this.root = root;
        this.bread1 = root.getChild("bread1");
        this.bread2 = root.getChild("bread2");
        this.bread3 = root.getChild("bread3");
        this.bread4 = root.getChild("bread4");
        this.bread5 = root.getChild("bread5");
        this.bottom1 = root.getChild("bottom1");
        this.bottom2 = root.getChild("bottom2");
        this.bottom3 = root.getChild("bottom3");
        this.bottom4 = root.getChild("bottom4");
        this.bottom5 = root.getChild("bottom5");
        this.bottom6 = root.getChild("bottom6");
        this.bottom7 = root.getChild("bottom7");
        this.bottom8 = root.getChild("bottom8");
        this.bottom9 = root.getChild("bottom9");
        this.middle1 = root.getChild("middle1");
        this.middle2 = root.getChild("middle2");
        this.middle3 = root.getChild("middle3");
        this.middle4 = root.getChild("middle4");
        this.middle5 = root.getChild("middle5");
        this.middle6 = root.getChild("middle6");
        this.middle7 = root.getChild("middle7");
        this.middle8 = root.getChild("middle8");
        this.middle9 = root.getChild("middle9");
        this.top1 = root.getChild("top1");
        this.top2 = root.getChild("top2");
        this.top3 = root.getChild("top3");
        this.top4 = root.getChild("top4");
        this.top5 = root.getChild("top5");
        this.top6 = root.getChild("top6");
        this.top7 = root.getChild("top7");
        this.top8 = root.getChild("top8");
        this.top9 = root.getChild("top9");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bread1 = partdefinition.addOrReplaceChild("bread1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 10, 3, 4), PartPose.offsetAndRotation(-4F, 20F, 3F, 0.3490659F, 0.418879F, 0F));
        PartDefinition bread2 = partdefinition.addOrReplaceChild("bread2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 10, 3, 4), PartPose.offsetAndRotation(4F, 20F, -7F, 1.3174533F, -1.02173F, -1.3759587F));
        PartDefinition bread3 = partdefinition.addOrReplaceChild("bread3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 10, 3, 4), PartPose.offsetAndRotation(-3F, 20F, -4F, 0.6174533F, -0.7726646F, -1.0457718F));
        PartDefinition bread4 = partdefinition.addOrReplaceChild("bread4", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(0F, 0F, 0F, 5, 3, 5), PartPose.offsetAndRotation(-6F, 19F, -6F, 0.5235988F, 0.2094395F, -0.5585054F));
        PartDefinition bread5 = partdefinition.addOrReplaceChild("bread5", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(0F, 0F, 0F, 5, 3, 5), PartPose.offsetAndRotation(0F, 17F, -6F, 0.5061455F, 0.1745329F, -0.2268928F));
        PartDefinition bottom1 = partdefinition.addOrReplaceChild("bottom1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7F, 0F, -7F, 4, 7, 4), PartPose.offset(0F, 15F, 0F));
        PartDefinition bottom2 = partdefinition.addOrReplaceChild("bottom2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, -7F, 4, 7, 4), PartPose.offset(0F, 15F, 0F));
        PartDefinition bottom3 = partdefinition.addOrReplaceChild("bottom3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3F, 0F, -7F, 4, 7, 4), PartPose.offset(0F, 15F, 0F));
        PartDefinition bottom4 = partdefinition.addOrReplaceChild("bottom4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7F, 0F, -2F, 4, 7, 4), PartPose.offset(0F, 15F, 0F));
        PartDefinition bottom5 = partdefinition.addOrReplaceChild("bottom5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, -2F, 4, 7, 4), PartPose.offset(0F, 15F, 0F));
        PartDefinition bottom6 = partdefinition.addOrReplaceChild("bottom6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3F, 0F, -2F, 4, 7, 4), PartPose.offset(0F, 15F, 0F));
        PartDefinition bottom7 = partdefinition.addOrReplaceChild("bottom7", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7F, 0F, 3F, 4, 7, 4), PartPose.offset(0F, 15F, 0F));
        PartDefinition bottom8 = partdefinition.addOrReplaceChild("bottom8", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, 3F, 4, 7, 4), PartPose.offset(0F, 15F, 0F));
        PartDefinition bottom9 = partdefinition.addOrReplaceChild("bottom9", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3F, 0F, 3F, 4, 7, 4), PartPose.offset(0F, 15F, 0F));
        PartDefinition middle1 = partdefinition.addOrReplaceChild("middle1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6.5F, 0F, -6.5F, 3, 2, 3), PartPose.offset(0F, 13F, 0F));
        PartDefinition middle2 = partdefinition.addOrReplaceChild("middle2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, -6.5F, 3, 2, 3), PartPose.offset(-1.5F, 13F, 0F));
        PartDefinition middle3 = partdefinition.addOrReplaceChild("middle3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3.5F, 0F, -6.5F, 3, 2, 3), PartPose.offset(0F, 13F, 0F));
        PartDefinition middle4 = partdefinition.addOrReplaceChild("middle4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6.5F, 0F, -1.5F, 3, 2, 3), PartPose.offset(0F, 13F, 0F));
        PartDefinition middle5 = partdefinition.addOrReplaceChild("middle5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -1.5F, 3, 2, 3), PartPose.offset(0F, 13F, 0F));
        PartDefinition middle6 = partdefinition.addOrReplaceChild("middle6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3.5F, 0F, -1.5F, 3, 2, 3), PartPose.offset(0F, 13F, 0F));
        PartDefinition middle7 = partdefinition.addOrReplaceChild("middle7", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6.5F, 0F, 3.5F, 3, 2, 3), PartPose.offset(0F, 13F, 0F));
        PartDefinition middle8 = partdefinition.addOrReplaceChild("middle8", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, 3.5F, 3, 2, 3), PartPose.offset(0F, 13F, 0F));
        PartDefinition middle9 = partdefinition.addOrReplaceChild("middle9", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3.5F, 0F, 3.5F, 3, 2, 3), PartPose.offset(0F, 13F, 0F));
        PartDefinition top1 = partdefinition.addOrReplaceChild("top1", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(-6F, 0F, -6F, 2, 4, 2), PartPose.offset(0F, 9F, 0F));
        PartDefinition top2 = partdefinition.addOrReplaceChild("top2", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(-1F, 0F, -6F, 2, 4, 2), PartPose.offset(0F, 9F, 0F));
        PartDefinition top3 = partdefinition.addOrReplaceChild("top3", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(4F, 0F, -6F, 2, 4, 2), PartPose.offset(0F, 9F, 0F));
        PartDefinition top4 = partdefinition.addOrReplaceChild("top4", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(-6F, 0F, -1F, 2, 4, 2), PartPose.offset(0F, 9F, 0F));
        PartDefinition top5 = partdefinition.addOrReplaceChild("top5", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(-1F, 0F, -1F, 2, 4, 2), PartPose.offset(0F, 9F, 0F));
        PartDefinition top6 = partdefinition.addOrReplaceChild("top6", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(4F, 0F, -1F, 2, 4, 2), PartPose.offset(0F, 9F, 0F));
        PartDefinition top7 = partdefinition.addOrReplaceChild("top7", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(-6F, 0F, 4F, 2, 4, 2), PartPose.offset(0F, 9F, 0F));
        PartDefinition top8 = partdefinition.addOrReplaceChild("top8", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(-1F, 0F, 4F, 2, 4, 2), PartPose.offset(0F, 9F, 0F));
        PartDefinition top9 = partdefinition.addOrReplaceChild("top9", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(4F, 0F, 4F, 2, 4, 2), PartPose.offset(0F, 9F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte par5) {
        if (par5 > 0)// 0-5
        {
            bread1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            if (par5 > 1) {
            bread2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                if (par5 > 2) {
            bread3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                    if (par5 > 3) {
            bread5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                        if (par5 > 4) {
            bread4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                        }
                    }
                }
            }
        }
    }

    public void renderBottle(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte par5) {
        if (par5 > 5)// 6-14
        {
            bottom1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            if (par5 > 6) {
            bottom2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                if (par5 > 7) {
            bottom3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                    if (par5 > 8) {
            bottom4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                        if (par5 > 9) {
            bottom5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                            if (par5 > 10) {
            bottom6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                                if (par5 > 11) {
            bottom7.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle7.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top7.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                                    if (par5 > 12) {
            bottom8.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle8.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top8.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                                        if (par5 > 13) {
            bottom9.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle9.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top9.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
