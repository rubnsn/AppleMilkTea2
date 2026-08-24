package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelCordial {
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
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 23F, 0F));
        root.addOrReplaceChild("side1", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10, 10, 1), PartPose.offset(0F, 13F, 0F));
        root.addOrReplaceChild("side2", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, 4F, 10, 10, 1), PartPose.offset(0F, 13F, 0F));
        root.addOrReplaceChild("side3", CubeListBuilder.create().texOffs(0, 0).addBox(4F, 0F, -4F, 1, 10, 8), PartPose.offset(0F, 13F, 0F));
        root.addOrReplaceChild("side4", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -4F, 1, 10, 8), PartPose.offset(0F, 13F, 0F));
        root.addOrReplaceChild("top1", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, -1F, -4F, 8, 1, 8), PartPose.offset(0F, 13F, 0F));
        root.addOrReplaceChild("top2", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -3F, 6, 1, 6), PartPose.offset(0F, 11F, 0F));
        root.addOrReplaceChild("cap", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, -4F, 8, 1, 8), PartPose.offset(0F, 10F, 0F));
        root.addOrReplaceChild("inner", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0F, -3.5F, 7, 3, 7), PartPose.offset(0F, 20F, 0F));
        root.addOrReplaceChild("drink1", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, -4F, 8, 9, 8), PartPose.offset(0F, 14F, 0F));
        root.addOrReplaceChild("drink2", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, -4F, 8, 6, 8), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("drink3", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, -4F, 8, 4, 8), PartPose.offset(0F, 19F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        bottom.render(pose, buf, light, overlay, r, g, b, a);
        side1.render(pose, buf, light, overlay, r, g, b, a);
        side2.render(pose, buf, light, overlay, r, g, b, a);
        side3.render(pose, buf, light, overlay, r, g, b, a);
        side4.render(pose, buf, light, overlay, r, g, b, a);
        top1.render(pose, buf, light, overlay, r, g, b, a);
        top2.render(pose, buf, light, overlay, r, g, b, a);
        cap.render(pose, buf, light, overlay, r, g, b, a);
        inner.render(pose, buf, light, overlay, r, g, b, a);
        drink1.render(pose, buf, light, overlay, r, g, b, a);
        drink2.render(pose, buf, light, overlay, r, g, b, a);
        drink3.render(pose, buf, light, overlay, r, g, b, a);
    }
}