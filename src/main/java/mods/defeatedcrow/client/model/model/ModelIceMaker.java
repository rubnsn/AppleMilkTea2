package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelIceMaker {
    private final ModelPart bottom;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart boad1;
    private final ModelPart boad2;
    private final ModelPart middle;
    private final ModelPart ice;
    private final ModelPart axle;
    private final ModelPart handle1;
    private final ModelPart handle2;
    private final ModelPart handle3;
    private final ModelPart handle4;
    private final ModelPart handle5;
    private final ModelPart handle6;

    public ModelIceMaker(ModelPart root) {
        this.bottom = root.getChild("bottom");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
        this.boad1 = root.getChild("boad1");
        this.boad2 = root.getChild("boad2");
        this.middle = root.getChild("middle");
        this.ice = root.getChild("ice");
        this.axle = root.getChild("axle");
        this.handle1 = root.getChild("handle1");
        this.handle2 = root.getChild("handle2");
        this.handle3 = root.getChild("handle3");
        this.handle4 = root.getChild("handle4");
        this.handle5 = root.getChild("handle5");
        this.handle6 = root.getChild("handle6");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 23F, 0F));
        root.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -6F, -4.5F, 1, 8, 1), PartPose.offsetAndRotation(0F, 23F, 0F, -0.1396263F, 0F, 0.1396263F));
        root.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 0).addBox(3.5F, -6F, -4.5F, 1, 8, 1), PartPose.offsetAndRotation(0F, 23F, 0F, -0.1396263F, 0F, -0.1396263F));
        root.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -6F, 3.5F, 1, 8, 1), PartPose.offsetAndRotation(0F, 23F, 0F, 0.1396263F, 0F, 0.1396263F));
        root.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 0).addBox(3.5F, -6F, 3.5F, 1, 8, 1), PartPose.offsetAndRotation(0F, 23F, 0F, 0.1396263F, 0F, -0.1396263F));
        root.addOrReplaceChild("boad1", CubeListBuilder.create().texOffs(0, 11).addBox(-4F, 0F, -4F, 8, 1, 8), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("boad2", CubeListBuilder.create().texOffs(0, 11).addBox(-4F, 0F, -4F, 8, 1, 8), PartPose.offset(0F, 11F, 0F));
        root.addOrReplaceChild("middle", CubeListBuilder.create().texOffs(0, 20).addBox(-4F, 0F, -4F, 8, 4, 8), PartPose.offset(0F, 12F, 0F));
        root.addOrReplaceChild("ice", CubeListBuilder.create().texOffs(32, 0).addBox(-2F, 0F, -2F, 4, 4, 4), PartPose.offset(0F, 12F, 0F));
        root.addOrReplaceChild("axle", CubeListBuilder.create().texOffs(32, 12).addBox(-0.5F, 0F, -0.5F, 1, 5, 1), PartPose.offset(0F, 8F, 0F));
        root.addOrReplaceChild("handle1", CubeListBuilder.create().texOffs(36, 12).addBox(-2F, 0F, -3F, 4, 1, 1), PartPose.offset(0F, 8F, 0F));
        root.addOrReplaceChild("handle2", CubeListBuilder.create().texOffs(36, 12).addBox(-2F, 0F, 2F, 4, 1, 1), PartPose.offset(0F, 8F, 0F));
        root.addOrReplaceChild("handle3", CubeListBuilder.create().texOffs(36, 12).addBox(2F, 0F, -2F, 1, 1, 4), PartPose.offset(0F, 8F, 0F));
        root.addOrReplaceChild("handle4", CubeListBuilder.create().texOffs(36, 12).addBox(-3F, 0F, -2F, 1, 1, 4), PartPose.offset(0F, 8F, 0F));
        root.addOrReplaceChild("handle5", CubeListBuilder.create().texOffs(36, 12).addBox(-0.5F, 0F, -2F, 1, 1, 4), PartPose.offset(0F, 8F, 0F));
        root.addOrReplaceChild("handle6", CubeListBuilder.create().texOffs(36, 12).addBox(-2F, 0F, -0.5F, 4, 1, 1), PartPose.offset(0F, 8F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        bottom.render(pose, buf, light, overlay, r, g, b, a);
        leg1.render(pose, buf, light, overlay, r, g, b, a);
        leg2.render(pose, buf, light, overlay, r, g, b, a);
        leg3.render(pose, buf, light, overlay, r, g, b, a);
        leg4.render(pose, buf, light, overlay, r, g, b, a);
        boad1.render(pose, buf, light, overlay, r, g, b, a);
        boad2.render(pose, buf, light, overlay, r, g, b, a);
        middle.render(pose, buf, light, overlay, r, g, b, a);
        ice.render(pose, buf, light, overlay, r, g, b, a);
        axle.render(pose, buf, light, overlay, r, g, b, a);
        handle1.render(pose, buf, light, overlay, r, g, b, a);
        handle2.render(pose, buf, light, overlay, r, g, b, a);
        handle3.render(pose, buf, light, overlay, r, g, b, a);
        handle4.render(pose, buf, light, overlay, r, g, b, a);
        handle5.render(pose, buf, light, overlay, r, g, b, a);
        handle6.render(pose, buf, light, overlay, r, g, b, a);
    }
}