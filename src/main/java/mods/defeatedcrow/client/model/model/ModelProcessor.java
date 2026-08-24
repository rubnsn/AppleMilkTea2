package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelProcessor {
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart base;
    private final ModelPart body;
    private final ModelPart brade1;
    private final ModelPart brade2;
    private final ModelPart glass;
    private final ModelPart top;

    public ModelProcessor(ModelPart root) {
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
        this.base = root.getChild("base");
        this.body = root.getChild("body");
        this.brade1 = root.getChild("brade1");
        this.brade2 = root.getChild("brade2");
        this.glass = root.getChild("glass");
        this.top = root.getChild("top");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 0F, -6F, 2, 1, 2), PartPose.offset(0F, 23F, 0F));
        root.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 0).addBox(4F, 0F, -6F, 2, 1, 2), PartPose.offset(0F, 23F, 0F));
        root.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 0F, 4F, 2, 1, 2), PartPose.offset(0F, 23F, 0F));
        root.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 0).addBox(4F, 0F, 4F, 2, 1, 2), PartPose.offset(0F, 23F, 0F));
        root.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 0F, -6F, 12, 2, 12), PartPose.offset(0F, 21F, 0F));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 14).addBox(-6.5F, 0F, -6.5F, 13, 5, 13), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("brade1", CubeListBuilder.create().texOffs(0, 3).addBox(-1F, 0F, -1F, 2, 3, 2), PartPose.offset(0F, 13F, 0F));
        root.addOrReplaceChild("brade2", CubeListBuilder.create().texOffs(40, 0).addBox(-3F, 0F, -3F, 6, 0, 6), PartPose.offset(0F, 14.5F, 0F));
        root.addOrReplaceChild("glass", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10, 6, 10), PartPose.offset(0F, 10F, 0F));
        root.addOrReplaceChild("top", CubeListBuilder.create().texOffs(10, 23).addBox(-4F, 0F, -4F, 8, 1, 8), PartPose.offset(0F, 9F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        leg1.render(pose, buf, light, overlay, r, g, b, a);
        leg2.render(pose, buf, light, overlay, r, g, b, a);
        leg3.render(pose, buf, light, overlay, r, g, b, a);
        leg4.render(pose, buf, light, overlay, r, g, b, a);
        base.render(pose, buf, light, overlay, r, g, b, a);
        body.render(pose, buf, light, overlay, r, g, b, a);
        brade1.render(pose, buf, light, overlay, r, g, b, a);
        brade2.render(pose, buf, light, overlay, r, g, b, a);
        glass.render(pose, buf, light, overlay, r, g, b, a);
        top.render(pose, buf, light, overlay, r, g, b, a);
    }
}