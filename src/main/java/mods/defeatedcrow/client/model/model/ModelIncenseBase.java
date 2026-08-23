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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELINCENSEBASE) -> new ModelIncenseBase(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelIncenseBase {

    private final ModelPart root;
    private final ModelPart base;
    private final ModelPart base2;
    private final ModelPart body;
    private final ModelPart side1;
    private final ModelPart side2;
    private final ModelPart side3;
    private final ModelPart side4;
    private final ModelPart middle1;
    private final ModelPart middle2;
    private final ModelPart middle3;
    private final ModelPart middle4;
    private final ModelPart top1;
    private final ModelPart top2;
    private final ModelPart top3;
    private final ModelPart top4;
    private final ModelPart top5;
    private final ModelPart top6;
    private final ModelPart top7;
    private final ModelPart top8;
    private final ModelPart top9;
    private final ModelPart cone1;
    private final ModelPart cone2;
    private final ModelPart glow;

    public ModelIncenseBase(ModelPart root) {
        this.root = root;
        this.base = root.getChild("base");
        this.base2 = root.getChild("base2");
        this.body = root.getChild("body");
        this.side1 = root.getChild("side1");
        this.side2 = root.getChild("side2");
        this.side3 = root.getChild("side3");
        this.side4 = root.getChild("side4");
        this.middle1 = root.getChild("middle1");
        this.middle2 = root.getChild("middle2");
        this.middle3 = root.getChild("middle3");
        this.middle4 = root.getChild("middle4");
        this.top1 = root.getChild("top1");
        this.top2 = root.getChild("top2");
        this.top3 = root.getChild("top3");
        this.top4 = root.getChild("top4");
        this.top5 = root.getChild("top5");
        this.top6 = root.getChild("top6");
        this.top7 = root.getChild("top7");
        this.top8 = root.getChild("top8");
        this.top9 = root.getChild("top9");
        this.cone1 = root.getChild("cone1");
        this.cone2 = root.getChild("cone2");
        this.glow = root.getChild("glow");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 23).mirror().addBox(-3F, 0F, -3F, 6, 1, 6), PartPose.offset(0F, 23F, 0F));
        PartDefinition base2 = partdefinition.addOrReplaceChild("base2", CubeListBuilder.create().texOffs(0, 23).mirror().addBox(-4F, 0F, -4F, 8, 1, 8), PartPose.offset(0F, 22F, 0F));
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 0).mirror().addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 21F, 0F));
        PartDefinition side1 = partdefinition.addOrReplaceChild("side1", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(-5F, 0F, -5F, 10, 3, 1), PartPose.offset(0F, 18F, 0F));
        PartDefinition side2 = partdefinition.addOrReplaceChild("side2", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(-5F, 0F, 4F, 10, 3, 1), PartPose.offset(0F, 18F, 0F));
        PartDefinition side3 = partdefinition.addOrReplaceChild("side3", CubeListBuilder.create().texOffs(16, 11).mirror().addBox(-5F, 0F, -4F, 1, 3, 8), PartPose.offset(0F, 18F, 0F));
        PartDefinition side4 = partdefinition.addOrReplaceChild("side4", CubeListBuilder.create().texOffs(16, 11).mirror().addBox(4F, 0F, -4F, 1, 3, 8), PartPose.offset(0F, 18F, 0F));
        PartDefinition middle1 = partdefinition.addOrReplaceChild("middle1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -4F, 8, 1, 1), PartPose.offset(0F, 17F, 0F));
        PartDefinition middle2 = partdefinition.addOrReplaceChild("middle2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, 3F, 8, 1, 1), PartPose.offset(0F, 17F, 0F));
        PartDefinition middle3 = partdefinition.addOrReplaceChild("middle3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -3F, 1, 1, 6), PartPose.offset(0F, 17F, 0F));
        PartDefinition middle4 = partdefinition.addOrReplaceChild("middle4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3F, 0F, -3F, 1, 1, 6), PartPose.offset(0F, 17F, 0F));
        PartDefinition top1 = partdefinition.addOrReplaceChild("top1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -4F, 1, 1, 4), PartPose.offsetAndRotation(0F, 15F, 0F, 0.5235988F, 0F, 0F));
        PartDefinition top2 = partdefinition.addOrReplaceChild("top2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -4F, 1, 1, 4), PartPose.offsetAndRotation(0F, 15F, 0F, 0.5235988F, 1.570796F, 0F));
        PartDefinition top3 = partdefinition.addOrReplaceChild("top3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -4F, 1, 1, 4), PartPose.offsetAndRotation(0F, 15F, 0F, 0.5235988F, 3.141593F, 0F));
        PartDefinition top4 = partdefinition.addOrReplaceChild("top4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -4F, 1, 1, 4), PartPose.offsetAndRotation(0F, 15F, 0F, 0.5235988F, -1.570796F, 0F));
        PartDefinition top5 = partdefinition.addOrReplaceChild("top5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -5F, 1, 1, 5), PartPose.offsetAndRotation(0F, 15F, 0F, 0.5235988F, 0.7853982F, 0F));
        PartDefinition top6 = partdefinition.addOrReplaceChild("top6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -5F, 1, 1, 5), PartPose.offsetAndRotation(0F, 15F, 0F, 0.5235988F, 2.356194F, 0F));
        PartDefinition top7 = partdefinition.addOrReplaceChild("top7", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -5F, 1, 1, 5), PartPose.offsetAndRotation(0F, 15F, 0F, 0.5235988F, -2.356194F, 0F));
        PartDefinition top8 = partdefinition.addOrReplaceChild("top8", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -5F, 1, 1, 5), PartPose.offsetAndRotation(0F, 15F, 0F, 0.5235988F, -0.7853982F, 0F));
        PartDefinition top9 = partdefinition.addOrReplaceChild("top9", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, 0F, -1F, 2, 1, 2), PartPose.offset(0F, 15F, 0F));
        PartDefinition cone1 = partdefinition.addOrReplaceChild("cone1", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-1F, 0F, -1F, 2, 1, 2), PartPose.offset(0F, 20F, 0F));
        PartDefinition cone2 = partdefinition.addOrReplaceChild("cone2", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-0.5F, 0F, -0.5F, 1, 1, 1), PartPose.offset(0F, 19F, 0F));
        PartDefinition glow = partdefinition.addOrReplaceChild("glow", CubeListBuilder.create().texOffs(9, 18).mirror().addBox(-0.5F, 0F, -0.5F, 1, 1, 1), PartPose.offset(0F, 18F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            base.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            body.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top7.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top8.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top9.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderCone(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            cone1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            cone2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderGlow(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            glow.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {

        }
}
