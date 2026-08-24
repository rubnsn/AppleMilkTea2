package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelEightEyesArm {
    private final ModelPart hand;
    private final ModelPart fingar1;
    private final ModelPart fingar2;
    private final ModelPart fingar3;
    private final ModelPart fingar4;
    private final ModelPart thumb1;
    private final ModelPart thumb2;
    private final ModelPart wrist;
    private final ModelPart arm1;
    private final ModelPart arm2;
    private final ModelPart armor1;
    private final ModelPart armor2;
    private final ModelPart armor3;
    private final ModelPart armor4;
    private final ModelPart armor5;
    private final ModelPart armor6;
    private final ModelPart armor7;

    public ModelEightEyesArm(ModelPart root) {
        this.hand = root.getChild("hand");
        this.fingar1 = root.getChild("fingar1");
        this.fingar2 = root.getChild("fingar2");
        this.fingar3 = root.getChild("fingar3");
        this.fingar4 = root.getChild("fingar4");
        this.thumb1 = root.getChild("thumb1");
        this.thumb2 = root.getChild("thumb2");
        this.wrist = root.getChild("wrist");
        this.arm1 = root.getChild("arm1");
        this.arm2 = root.getChild("arm2");
        this.armor1 = root.getChild("armor1");
        this.armor2 = root.getChild("armor2");
        this.armor3 = root.getChild("armor3");
        this.armor4 = root.getChild("armor4");
        this.armor5 = root.getChild("armor5");
        this.armor6 = root.getChild("armor6");
        this.armor7 = root.getChild("armor7");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("hand", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 2F, -6F, 3, 4, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, -0.0523599F, 0F));
        root.addOrReplaceChild("fingar1", CubeListBuilder.create().texOffs(0, 9).mirror().addBox(-4.5F, 5F, -6F, 3, 1, 3), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, -0.5235988F, 0F));
        root.addOrReplaceChild("fingar2", CubeListBuilder.create().texOffs(0, 9).mirror().addBox(-4.5F, 4F, -6F, 3, 1, 3), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, -0.5585054F, 0F));
        root.addOrReplaceChild("fingar3", CubeListBuilder.create().texOffs(0, 9).mirror().addBox(-4.5F, 3F, -6F, 3, 1, 3), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, -0.5759587F, 0F));
        root.addOrReplaceChild("fingar4", CubeListBuilder.create().texOffs(0, 9).mirror().addBox(-4.5F, 2F, -6F, 3, 1, 3), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, -0.6108652F, 0F));
        root.addOrReplaceChild("thumb1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 1F, -5.5F, 2, 2, 3), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 0.2443461F, 0F));
        root.addOrReplaceChild("thumb2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.5F, 2F, -5F, 1, 3, 1), PartPose.offsetAndRotation(0F, 16F, 0F, -0.2617994F, 0F, 0F));
        root.addOrReplaceChild("wrist", CubeListBuilder.create().texOffs(0, 15).mirror().addBox(-1.5F, 0F, -3F, 3, 3, 2), PartPose.offsetAndRotation(0F, 18F, 0F, 0F, 0.0872665F, 0F));
        root.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 2F, -1F, 3, 3, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, 2F, 3F, 3, 3, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, -0.1396263F, 0F));
        root.addOrReplaceChild("armor1", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-1F, 1F, -2F, 4, 5, 5), PartPose.offsetAndRotation(0F, 16F, 0F, 0.1396263F, 0.4363323F, 0F));
        root.addOrReplaceChild("armor2", CubeListBuilder.create().texOffs(32, 11).mirror().addBox(-2.5F, 1.5F, 0.5F, 4, 5, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0.1570796F, 0.4363323F, 0F));
        root.addOrReplaceChild("armor3", CubeListBuilder.create().texOffs(32, 11).mirror().addBox(-3.5F, 2F, 2.5F, 4, 5, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0.1745329F, 0.4363323F, 0F));
        root.addOrReplaceChild("armor4", CubeListBuilder.create().texOffs(32, 21).mirror().addBox(-5F, 2.5F, 3F, 5, 5, 5), PartPose.offsetAndRotation(0F, 16F, 0F, 0.1919862F, 0.4363323F, 0.0174533F));
        root.addOrReplaceChild("armor5", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2F, 1.5F, -1F, 2, 4, 2), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, -0.2094395F, 0F));
        root.addOrReplaceChild("armor6", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-1.5F, 1.5F, 1F, 2, 4, 2), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, -0.3141593F, 0F));
        root.addOrReplaceChild("armor7", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-1F, 1.5F, 3F, 2, 4, 2), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, -0.3141593F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        hand.render(pose, buf, light, overlay, r, g, b, a);
        fingar1.render(pose, buf, light, overlay, r, g, b, a);
        fingar2.render(pose, buf, light, overlay, r, g, b, a);
        fingar3.render(pose, buf, light, overlay, r, g, b, a);
        fingar4.render(pose, buf, light, overlay, r, g, b, a);
        thumb1.render(pose, buf, light, overlay, r, g, b, a);
        thumb2.render(pose, buf, light, overlay, r, g, b, a);
        wrist.render(pose, buf, light, overlay, r, g, b, a);
        arm1.render(pose, buf, light, overlay, r, g, b, a);
        arm2.render(pose, buf, light, overlay, r, g, b, a);
        armor1.render(pose, buf, light, overlay, r, g, b, a);
        armor2.render(pose, buf, light, overlay, r, g, b, a);
        armor3.render(pose, buf, light, overlay, r, g, b, a);
        armor4.render(pose, buf, light, overlay, r, g, b, a);
        armor5.render(pose, buf, light, overlay, r, g, b, a);
        armor6.render(pose, buf, light, overlay, r, g, b, a);
        armor7.render(pose, buf, light, overlay, r, g, b, a);
    }
}
