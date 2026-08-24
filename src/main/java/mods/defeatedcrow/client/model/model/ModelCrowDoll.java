package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelCrowDoll {
    private final ModelPart base;
    private final ModelPart body;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart hip;
    private final ModelPart tail;
    private final ModelPart top;
    private final ModelPart wingL;
    private final ModelPart wingR;
    private final ModelPart mouth;
    private final ModelPart legL;
    private final ModelPart legR;

    public ModelCrowDoll(ModelPart root) {
        this.base = root.getChild("base");
        this.body = root.getChild("body");
        this.neck = root.getChild("neck");
        this.head = root.getChild("head");
        this.hip = root.getChild("hip");
        this.tail = root.getChild("tail");
        this.top = root.getChild("top");
        this.wingL = root.getChild("wingL");
        this.wingR = root.getChild("wingR");
        this.mouth = root.getChild("mouth");
        this.legL = root.getChild("legL");
        this.legR = root.getChild("legR");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 7F, -4F, 8, 1, 8), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 10).addBox(-3.5F, 3F, -2.8F, 7, 3, 6), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 10).addBox(-3F, 2F, -2.7F, 6, 1, 5), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 20).addBox(-2.5F, -1F, -2.5F, 5, 3, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("hip", CubeListBuilder.create().texOffs(0, 10).addBox(-3F, 3F, 3F, 6, 2, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(20, 20).addBox(-2F, 4F, 2F, 4, 1, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 10).addBox(-2F, 2.5F, -2F, 4, 1, 3), PartPose.offset(0F, 12F, 0F));
        root.addOrReplaceChild("wingL", CubeListBuilder.create().texOffs(0, 10).addBox(-2F, 0F, 0F, 4, 1, 3), PartPose.offset(3.5F, 18F, -0.5F));
        root.addOrReplaceChild("wingR", CubeListBuilder.create().texOffs(0, 10).addBox(-2F, 0F, 0F, 4, 1, 3), PartPose.offset(-3.5F, 18F, -0.5F));
        root.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(34, 0).addBox(-1F, 1F, -3.5F, 2, 1, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("legL", CubeListBuilder.create().texOffs(34, 3).addBox(0.5F, 6F, -1F, 1, 1, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("legR", CubeListBuilder.create().texOffs(34, 3).addBox(-1.5F, 6F, -1F, 1, 1, 1), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        base.render(pose, buf, light, overlay, r, g, b, a);
        body.render(pose, buf, light, overlay, r, g, b, a);
        neck.render(pose, buf, light, overlay, r, g, b, a);
        head.render(pose, buf, light, overlay, r, g, b, a);
        hip.render(pose, buf, light, overlay, r, g, b, a);
        tail.render(pose, buf, light, overlay, r, g, b, a);
        top.render(pose, buf, light, overlay, r, g, b, a);
        wingL.render(pose, buf, light, overlay, r, g, b, a);
        wingR.render(pose, buf, light, overlay, r, g, b, a);
        mouth.render(pose, buf, light, overlay, r, g, b, a);
        legL.render(pose, buf, light, overlay, r, g, b, a);
        legR.render(pose, buf, light, overlay, r, g, b, a);
    }
}