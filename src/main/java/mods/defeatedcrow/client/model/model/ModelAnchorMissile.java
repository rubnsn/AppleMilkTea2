package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelAnchorMissile {
    private final ModelPart head;
    private final ModelPart head2;
    private final ModelPart head3;
    private final ModelPart body;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart tail3;
    private final ModelPart tail4;
    private final ModelPart wingL1;
    private final ModelPart wingL2;
    private final ModelPart wingR1;
    private final ModelPart wingR2;
    private final ModelPart wing3;
    private final ModelPart burn;

    public ModelAnchorMissile(ModelPart root) {
        this.head = root.getChild("head");
        this.head2 = root.getChild("head2");
        this.head3 = root.getChild("head3");
        this.body = root.getChild("body");
        this.tail1 = root.getChild("tail1");
        this.tail2 = root.getChild("tail2");
        this.tail3 = root.getChild("tail3");
        this.tail4 = root.getChild("tail4");
        this.wingL1 = root.getChild("wingL1");
        this.wingL2 = root.getChild("wingL2");
        this.wingR1 = root.getChild("wingR1");
        this.wingR2 = root.getChild("wingR2");
        this.wing3 = root.getChild("wing3");
        this.burn = root.getChild("burn");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, -1.5F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, -4F));
        root.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1F, -4F, 3, 2, 2), PartPose.offset(0F, 16F, -4F));
        root.addOrReplaceChild("head3", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -0.5F, -5F, 2, 1, 1), PartPose.offset(0F, 16F, -4F));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(36, 10).addBox(-1.5F, -1F, 2F, 3, 2, 10), PartPose.offset(0F, 16F, -4F));
        root.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(28, 0).addBox(-2F, -1F, 12F, 4, 1, 3), PartPose.offsetAndRotation(0F, 16F, -4F, 0.0523599F, 0F, 0F));
        root.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(28, 0).addBox(-2F, 0F, 12F, 4, 1, 3), PartPose.offsetAndRotation(0F, 16F, -4F, -0.0523599F, 0F, 0F));
        root.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(43, 0).addBox(0F, -1F, 12F, 1, 2, 3), PartPose.offsetAndRotation(0F, 16F, -4F, 0F, 0.0872665F, 0F));
        root.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(43, 0).addBox(-1F, -1F, 12F, 1, 2, 3), PartPose.offsetAndRotation(0F, 16F, -4F, 0F, -0.0872665F, 0F));
        root.addOrReplaceChild("wingL1", CubeListBuilder.create().texOffs(0, 8).addBox(-1F, 0F, 1F, 3, 1, 10), PartPose.offset(0F, 16F, -4F));
        root.addOrReplaceChild("wingL2", CubeListBuilder.create().texOffs(28, 24).addBox(-2.5F, 0F, 11F, 5, 1, 2), PartPose.offset(0F, 16F, -4F));
        root.addOrReplaceChild("wingR1", CubeListBuilder.create().texOffs(0, 20).addBox(-2F, 0F, 1F, 3, 1, 10), PartPose.offset(0F, 16F, -4F));
        root.addOrReplaceChild("wingR2", CubeListBuilder.create().texOffs(28, 28).addBox(-2.5F, 0F, 11F, 5, 1, 2), PartPose.offset(0F, 16F, -4F));
        root.addOrReplaceChild("wing3", CubeListBuilder.create().texOffs(28, 5).addBox(-0.5F, 3F, 7F, 1, 2, 4), PartPose.offsetAndRotation(0F, 16F, -4F, 0.5235988F, 0F, 0F));
        root.addOrReplaceChild("burn", CubeListBuilder.create().texOffs(52, 0).addBox(-1.5F, -1F, 13F, 3, 2, 1), PartPose.offset(0F, 16F, -4F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        head.render(pose, buf, light, overlay, r, g, b, a);
        head2.render(pose, buf, light, overlay, r, g, b, a);
        head3.render(pose, buf, light, overlay, r, g, b, a);
        body.render(pose, buf, light, overlay, r, g, b, a);
        tail1.render(pose, buf, light, overlay, r, g, b, a);
        tail2.render(pose, buf, light, overlay, r, g, b, a);
        tail3.render(pose, buf, light, overlay, r, g, b, a);
        tail4.render(pose, buf, light, overlay, r, g, b, a);
        wingL1.render(pose, buf, light, overlay, r, g, b, a);
        wingL2.render(pose, buf, light, overlay, r, g, b, a);
        wingR1.render(pose, buf, light, overlay, r, g, b, a);
        wingR2.render(pose, buf, light, overlay, r, g, b, a);
        wing3.render(pose, buf, light, overlay, r, g, b, a);
        burn.render(pose, buf, light, overlay, r, g, b, a);
    }
}