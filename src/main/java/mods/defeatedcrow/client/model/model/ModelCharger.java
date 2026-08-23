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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELCHARGER) -> new ModelCharger(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelCharger {

    private final ModelPart root;
    private final ModelPart bottom;
    private final ModelPart top;
    private final ModelPart back;
    private final ModelPart side1;
    private final ModelPart side2;
    private final ModelPart inner;
    private final ModelPart button1;
    private final ModelPart button2;
    private final ModelPart dial;
    private final ModelPart panel1;
    private final ModelPart panel2;

    public ModelCharger(ModelPart root) {
        this.root = root;
        this.bottom = root.getChild("bottom");
        this.top = root.getChild("top");
        this.back = root.getChild("back");
        this.side1 = root.getChild("side1");
        this.side2 = root.getChild("side2");
        this.inner = root.getChild("inner");
        this.button1 = root.getChild("button1");
        this.button2 = root.getChild("button2");
        this.dial = root.getChild("dial");
        this.panel1 = root.getChild("panel1");
        this.panel2 = root.getChild("panel2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-8F, 7F, -8F, 16, 1, 16), PartPose.offset(0F, 16F, 0F));
        PartDefinition top = partdefinition.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-8F, -8F, -8F, 16, 1, 16), PartPose.offset(0F, 16F, 0F));
        PartDefinition back = partdefinition.addOrReplaceChild("back", CubeListBuilder.create().texOffs(64, 0).mirror().addBox(-8F, -7F, 7F, 16, 14, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition side1 = partdefinition.addOrReplaceChild("side1", CubeListBuilder.create().texOffs(64, 1).mirror().addBox(-8F, -7F, -8F, 1, 14, 16), PartPose.offset(0F, 16F, 0F));
        PartDefinition side2 = partdefinition.addOrReplaceChild("side2", CubeListBuilder.create().texOffs(64, 1).mirror().addBox(7F, -7F, -8F, 1, 14, 16), PartPose.offset(0F, 16F, 0F));
        PartDefinition inner = partdefinition.addOrReplaceChild("inner", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-7F, -7F, -6F, 14, 14, 13), PartPose.offset(0F, 16F, 0F));
        PartDefinition button1 = partdefinition.addOrReplaceChild("button1", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(-5F, 1F, -7F, 1, 1, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition button2 = partdefinition.addOrReplaceChild("button2", CubeListBuilder.create().texOffs(4, 48).mirror().addBox(-3F, 1F, -7F, 1, 1, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition dial = partdefinition.addOrReplaceChild("dial", CubeListBuilder.create().texOffs(8, 48).mirror().addBox(3F, 0F, -7F, 2, 2, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition panel1 = partdefinition.addOrReplaceChild("panel1", CubeListBuilder.create().texOffs(0, 51).mirror().addBox(-3F, -5F, -6.5F, 8, 4, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition panel2 = partdefinition.addOrReplaceChild("panel2", CubeListBuilder.create().texOffs(0, 56).mirror().addBox(-6F, 4F, -7F, 12, 2, 1), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(meshdefinition, 128, 64);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            back.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            inner.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            button1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            button2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            dial.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            panel1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            panel2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        this.bottom.yRot = f3 / (180F / (float) Math.PI);
        this.top.yRot = f3 / (180F / (float) Math.PI);
        this.back.yRot = f3 / (180F / (float) Math.PI);
        this.side1.yRot = f3 / (180F / (float) Math.PI);
        this.side2.yRot = f3 / (180F / (float) Math.PI);
        this.inner.yRot = f3 / (180F / (float) Math.PI);
        this.button1.yRot = f3 / (180F / (float) Math.PI);
        this.button2.yRot = f3 / (180F / (float) Math.PI);
        this.dial.yRot = f3 / (180F / (float) Math.PI);
        this.panel1.yRot = f3 / (180F / (float) Math.PI);
        this.panel2.yRot = f3 / (180F / (float) Math.PI);
    }
}
