package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelBarrelBase {
    private final ModelPart base1;
    private final ModelPart base2;
    private final ModelPart base3;
    private final ModelPart base4;
    private final ModelPart base5;
    private final ModelPart base6;
    private final ModelPart base7;
    private final ModelPart base8;
    private final ModelPart base9;
    private final ModelPart base10;
    private final ModelPart base11;
    private final ModelPart base12;
    private final ModelPart base13;
    private final ModelPart base14;

    public ModelBarrelBase(ModelPart root) {
        this.base1 = root.getChild("base1");
        this.base2 = root.getChild("base2");
        this.base3 = root.getChild("base3");
        this.base4 = root.getChild("base4");
        this.base5 = root.getChild("base5");
        this.base6 = root.getChild("base6");
        this.base7 = root.getChild("base7");
        this.base8 = root.getChild("base8");
        this.base9 = root.getChild("base9");
        this.base10 = root.getChild("base10");
        this.base11 = root.getChild("base11");
        this.base12 = root.getChild("base12");
        this.base13 = root.getChild("base13");
        this.base14 = root.getChild("base14");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("base1", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 8.1F, 6F, 12, 1, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        root.addOrReplaceChild("base2", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 8.1F, -7F, 12, 1, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        root.addOrReplaceChild("base3", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 6F, 6F, 12, 1, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        root.addOrReplaceChild("base4", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 6F, -7F, 12, 1, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        root.addOrReplaceChild("base5", CubeListBuilder.create().texOffs(0, 4).addBox(-8F, 8.1F, -7F, 16, 1, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("base6", CubeListBuilder.create().texOffs(0, 4).addBox(-8F, 8.1F, 6F, 16, 1, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("base7", CubeListBuilder.create().texOffs(0, 4).addBox(-8F, 6F, -7F, 16, 1, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("base8", CubeListBuilder.create().texOffs(0, 4).addBox(-8F, 6F, 6F, 16, 1, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("base9", CubeListBuilder.create().texOffs(36, 0).addBox(6F, 7F, -7F, 1, 1, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("base10", CubeListBuilder.create().texOffs(36, 0).addBox(-7F, 7F, -7F, 1, 1, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("base11", CubeListBuilder.create().texOffs(36, 0).addBox(6F, 7F, 6F, 1, 1, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("base12", CubeListBuilder.create().texOffs(36, 0).addBox(-7F, 7F, 6F, 1, 1, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("base13", CubeListBuilder.create().texOffs(0, 8).addBox(-7F, 5F, 4.5F, 14, 1, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        root.addOrReplaceChild("base14", CubeListBuilder.create().texOffs(0, 8).addBox(-7F, 5F, -5.5F, 14, 1, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        base1.render(pose, buf, light, overlay, r, g, b, a);
        base2.render(pose, buf, light, overlay, r, g, b, a);
        base3.render(pose, buf, light, overlay, r, g, b, a);
        base4.render(pose, buf, light, overlay, r, g, b, a);
        base5.render(pose, buf, light, overlay, r, g, b, a);
        base6.render(pose, buf, light, overlay, r, g, b, a);
        base7.render(pose, buf, light, overlay, r, g, b, a);
        base8.render(pose, buf, light, overlay, r, g, b, a);
        base9.render(pose, buf, light, overlay, r, g, b, a);
        base10.render(pose, buf, light, overlay, r, g, b, a);
        base11.render(pose, buf, light, overlay, r, g, b, a);
        base12.render(pose, buf, light, overlay, r, g, b, a);
        base13.render(pose, buf, light, overlay, r, g, b, a);
        base14.render(pose, buf, light, overlay, r, g, b, a);
    }
}