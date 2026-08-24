package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelAltCatorarys {
    private final ModelPart Shape1;
    private final ModelPart Shape2;
    private final ModelPart Shape3;
    private final ModelPart Shape4;
    private final ModelPart Shape5;
    private final ModelPart Shape6;
    private final ModelPart Shape7;
    private final ModelPart Shape8;
    private final ModelPart Shape9;
    private final ModelPart Shape10;

    public ModelAltCatorarys(ModelPart root) {
        this.Shape1 = root.getChild("Shape1");
        this.Shape2 = root.getChild("Shape2");
        this.Shape3 = root.getChild("Shape3");
        this.Shape4 = root.getChild("Shape4");
        this.Shape5 = root.getChild("Shape5");
        this.Shape6 = root.getChild("Shape6");
        this.Shape7 = root.getChild("Shape7");
        this.Shape8 = root.getChild("Shape8");
        this.Shape9 = root.getChild("Shape9");
        this.Shape10 = root.getChild("Shape10");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 0F, -0.5F, 10, 1, 1), PartPose.offset(0F, 0F, 0F));
        root.addOrReplaceChild("Shape2", CubeListBuilder.create().texOffs(0, 0).addBox(4F, 0F, -1.5F, 4, 1, 3), PartPose.offset(0F, 0F, 0F));
        root.addOrReplaceChild("Shape3", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, -1F, -2F, 10, 1, 1), PartPose.offset(0F, 0F, 0F));
        root.addOrReplaceChild("Shape4", CubeListBuilder.create().texOffs(0, 0).addBox(4F, -1F, -2.5F, 1, 1, 2), PartPose.offset(0F, 0F, 0F));
        root.addOrReplaceChild("Shape5", CubeListBuilder.create().texOffs(0, 0).addBox(5F, -1F, -2.7F, 3, 1, 1), PartPose.offset(0F, 0F, 0F));
        root.addOrReplaceChild("Shape6", CubeListBuilder.create().texOffs(0, 0).addBox(5F, -1F, -1.2F, 3, 1, 1), PartPose.offset(0F, 0F, 0F));
        root.addOrReplaceChild("Shape7", CubeListBuilder.create().texOffs(0, 0).addBox(2.5F, 0F, -4F, 4, 1, 3), PartPose.offset(0F, 0F, 0F));
        root.addOrReplaceChild("Shape8", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, 0F, -3F, 10, 1, 1), PartPose.offset(0F, 0F, 0F));
        root.addOrReplaceChild("Shape9", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -0.5F, 0F, 8, 1, 2), PartPose.offset(0F, 0F, 0F));
        root.addOrReplaceChild("Shape10", CubeListBuilder.create().texOffs(0, 0).addBox(-8F, -0.5F, 1F, 7, 1, 1), PartPose.offset(0F, 0F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        Shape1.render(pose, buf, light, overlay, r, g, b, a);
        Shape2.render(pose, buf, light, overlay, r, g, b, a);
        Shape3.render(pose, buf, light, overlay, r, g, b, a);
        Shape4.render(pose, buf, light, overlay, r, g, b, a);
        Shape5.render(pose, buf, light, overlay, r, g, b, a);
        Shape6.render(pose, buf, light, overlay, r, g, b, a);
        Shape7.render(pose, buf, light, overlay, r, g, b, a);
        Shape8.render(pose, buf, light, overlay, r, g, b, a);
        Shape9.render(pose, buf, light, overlay, r, g, b, a);
        Shape10.render(pose, buf, light, overlay, r, g, b, a);
    }
}