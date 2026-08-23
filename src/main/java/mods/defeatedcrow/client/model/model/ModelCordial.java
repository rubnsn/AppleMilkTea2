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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELCORDIAL) -> new ModelCordial(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelCordial {

    private final ModelPart root;
    private final ModelPart bottom;
    private final ModelPart side1;
    private final ModelPart side2;
    private final ModelPart side3;
    private final ModelPart side4;
    private final ModelPart top1;
    private final ModelPart top2;
    private final ModelPart cap;
    private final ModelPart inner;
    private final ModelPart drink1;
    private final ModelPart drink2;
    private final ModelPart drink3;

    public ModelCordial(ModelPart root) {
        this.root = root;
        this.bottom = root.getChild("bottom");
        this.side1 = root.getChild("side1");
        this.side2 = root.getChild("side2");
        this.side3 = root.getChild("side3");
        this.side4 = root.getChild("side4");
        this.top1 = root.getChild("top1");
        this.top2 = root.getChild("top2");
        this.cap = root.getChild("cap");
        this.inner = root.getChild("inner");
        this.drink1 = root.getChild("drink1");
        this.drink2 = root.getChild("drink2");
        this.drink3 = root.getChild("drink3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 23F, 0F));
        PartDefinition side1 = partdefinition.addOrReplaceChild("side1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -5F, 10, 10, 1), PartPose.offset(0F, 13F, 0F));
        PartDefinition side2 = partdefinition.addOrReplaceChild("side2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, 4F, 10, 10, 1), PartPose.offset(0F, 13F, 0F));
        PartDefinition side3 = partdefinition.addOrReplaceChild("side3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, -4F, 1, 10, 8), PartPose.offset(0F, 13F, 0F));
        PartDefinition side4 = partdefinition.addOrReplaceChild("side4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -4F, 1, 10, 8), PartPose.offset(0F, 13F, 0F));
        PartDefinition top1 = partdefinition.addOrReplaceChild("top1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, -1F, -4F, 8, 1, 8), PartPose.offset(0F, 13F, 0F));
        PartDefinition top2 = partdefinition.addOrReplaceChild("top2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -3F, 6, 1, 6), PartPose.offset(0F, 11F, 0F));
        PartDefinition cap = partdefinition.addOrReplaceChild("cap", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -4F, 8, 1, 8), PartPose.offset(0F, 10F, 0F));
        PartDefinition inner = partdefinition.addOrReplaceChild("inner", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.5F, 0F, -3.5F, 7, 3, 7), PartPose.offset(0F, 20F, 0F));
        PartDefinition drink1 = partdefinition.addOrReplaceChild("drink1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -4F, 8, 9, 8), PartPose.offset(0F, 14F, 0F));
        PartDefinition drink2 = partdefinition.addOrReplaceChild("drink2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -4F, 8, 6, 8), PartPose.offset(0F, 17F, 0F));
        PartDefinition drink3 = partdefinition.addOrReplaceChild("drink3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -4F, 8, 4, 8), PartPose.offset(0F, 19F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {

            bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderDrink(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            drink1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderContents(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            inner.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
