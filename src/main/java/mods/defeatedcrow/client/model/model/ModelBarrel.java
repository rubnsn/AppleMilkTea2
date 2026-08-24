package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelBarrel {
    private final ModelPart side1;
    private final ModelPart side2;
    private final ModelPart side3;
    private final ModelPart side4;
    private final ModelPart side5;
    private final ModelPart side6;
    private final ModelPart side7;
    private final ModelPart side8;
    private final ModelPart side9;
    private final ModelPart side10;
    private final ModelPart r1;
    private final ModelPart r2;
    private final ModelPart r3;
    private final ModelPart r4;
    private final ModelPart r5;
    private final ModelPart l1;
    private final ModelPart l2;
    private final ModelPart l3;
    private final ModelPart l4;
    private final ModelPart l5;
    private final ModelPart base1;
    private final ModelPart base2;

    public ModelBarrel(ModelPart root) {
        this.side1 = root.getChild("side1");
        this.side2 = root.getChild("side2");
        this.side3 = root.getChild("side3");
        this.side4 = root.getChild("side4");
        this.side5 = root.getChild("side5");
        this.side6 = root.getChild("side6");
        this.side7 = root.getChild("side7");
        this.side8 = root.getChild("side8");
        this.side9 = root.getChild("side9");
        this.side10 = root.getChild("side10");
        this.r1 = root.getChild("r1");
        this.r2 = root.getChild("r2");
        this.r3 = root.getChild("r3");
        this.r4 = root.getChild("r4");
        this.r5 = root.getChild("r5");
        this.l1 = root.getChild("l1");
        this.l2 = root.getChild("l2");
        this.l3 = root.getChild("l3");
        this.l4 = root.getChild("l4");
        this.l5 = root.getChild("l5");
        this.base1 = root.getChild("base1");
        this.base2 = root.getChild("base2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("side1", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, 6F, -2F, 16, 1, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("side2", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, 6F, -2F, 16, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0.6283185F, 0F, 0F));
        root.addOrReplaceChild("side3", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, 6F, -2F, 16, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 1.256637F, 0F, 0F));
        root.addOrReplaceChild("side4", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, 6F, -2F, 16, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, -0.6283185F, 0F, 0F));
        root.addOrReplaceChild("side5", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, 6F, -2F, 16, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, -1.256637F, 0F, 0F));
        root.addOrReplaceChild("side6", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, -7F, -2F, 16, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 1.256637F, 0F, 0F));
        root.addOrReplaceChild("side7", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, -7F, -2F, 16, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, -1.256637F, 0F, 0F));
        root.addOrReplaceChild("side8", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, -7F, -2F, 16, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0.6283185F, 0F, 0F));
        root.addOrReplaceChild("side9", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, -7F, -2F, 16, 1, 4), PartPose.offsetAndRotation(0F, 16F, 0F, -0.6283185F, 0F, 0F));
        root.addOrReplaceChild("side10", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-8F, -7F, -2F, 16, 1, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7F, -5F, -5F, 1, 10, 10), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7F, -4F, -6.5F, 1, 8, 2), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("r3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7F, -4F, 4.5F, 1, 8, 2), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("r4", CubeListBuilder.create().texOffs(14, 0).mirror().addBox(-7F, -6F, -3.5F, 1, 1, 7), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("r5", CubeListBuilder.create().texOffs(14, 0).mirror().addBox(-7F, 5F, -3.5F, 1, 1, 7), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("l1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(6F, -5F, -5F, 1, 10, 10), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("l2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(6F, -4F, 4.5F, 1, 8, 2), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("l3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(6F, -4F, -6.5F, 1, 8, 2), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("l4", CubeListBuilder.create().texOffs(14, 0).mirror().addBox(6F, -6F, -3.5F, 1, 1, 7), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("l5", CubeListBuilder.create().texOffs(14, 0).mirror().addBox(6F, 5F, -3.5F, 1, 1, 7), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("base1", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(5F, 6F, -5F, 1, 2, 10), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("base2", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-6F, 6F, -5F, 1, 2, 10), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        side1.render(pose, buf, light, overlay, r, g, b, a);
        side2.render(pose, buf, light, overlay, r, g, b, a);
        side3.render(pose, buf, light, overlay, r, g, b, a);
        side4.render(pose, buf, light, overlay, r, g, b, a);
        side5.render(pose, buf, light, overlay, r, g, b, a);
        side6.render(pose, buf, light, overlay, r, g, b, a);
        side7.render(pose, buf, light, overlay, r, g, b, a);
        side8.render(pose, buf, light, overlay, r, g, b, a);
        side9.render(pose, buf, light, overlay, r, g, b, a);
        side10.render(pose, buf, light, overlay, r, g, b, a);
        r1.render(pose, buf, light, overlay, r, g, b, a);
        r2.render(pose, buf, light, overlay, r, g, b, a);
        r3.render(pose, buf, light, overlay, r, g, b, a);
        r4.render(pose, buf, light, overlay, r, g, b, a);
        r5.render(pose, buf, light, overlay, r, g, b, a);
        l1.render(pose, buf, light, overlay, r, g, b, a);
        l2.render(pose, buf, light, overlay, r, g, b, a);
        l3.render(pose, buf, light, overlay, r, g, b, a);
        l4.render(pose, buf, light, overlay, r, g, b, a);
        l5.render(pose, buf, light, overlay, r, g, b, a);
        base1.render(pose, buf, light, overlay, r, g, b, a);
        base2.render(pose, buf, light, overlay, r, g, b, a);
    }
}
