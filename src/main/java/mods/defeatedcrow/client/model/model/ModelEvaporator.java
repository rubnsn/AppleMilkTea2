package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelEvaporator {
    private final ModelPart glass1;
    private final ModelPart Shape1;
    private final ModelPart Shape2;
    private final ModelPart Shape3;
    private final ModelPart Shape4;
    private final ModelPart Shape5;
    private final ModelPart Shape6;
    private final ModelPart Shape7;
    private final ModelPart Shape8;
    private final ModelPart Shape8b;
    private final ModelPart Shape9;
    private final ModelPart Shape9b;
    private final ModelPart Shape10;
    private final ModelPart Shape11;
    private final ModelPart Shape12;
    private final ModelPart Shape13;
    private final ModelPart Shape14;

    public ModelEvaporator(ModelPart root) {
        this.glass1 = root.getChild("glass1");
        this.Shape1 = root.getChild("Shape1");
        this.Shape2 = root.getChild("Shape2");
        this.Shape3 = root.getChild("Shape3");
        this.Shape4 = root.getChild("Shape4");
        this.Shape5 = root.getChild("Shape5");
        this.Shape6 = root.getChild("Shape6");
        this.Shape7 = root.getChild("Shape7");
        this.Shape8 = root.getChild("Shape8");
        this.Shape8b = root.getChild("Shape8b");
        this.Shape9 = root.getChild("Shape9");
        this.Shape9b = root.getChild("Shape9b");
        this.Shape10 = root.getChild("Shape10");
        this.Shape11 = root.getChild("Shape11");
        this.Shape12 = root.getChild("Shape12");
        this.Shape13 = root.getChild("Shape13");
        this.Shape14 = root.getChild("Shape14");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("glass1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 1F, 0F, 4, 3, 4), PartPose.offsetAndRotation(0F, 19F, 0F, 0.7853982F, 1.570796F, 0F));
        root.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.5F, 1F, 0F, 3, 4, 3), PartPose.offsetAndRotation(0F, 18F, 0F, 0.7853982F, 1.570796F, 0F));
        root.addOrReplaceChild("Shape2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(1.5F, -5.5F, -1F, 1, 9, 1), PartPose.offsetAndRotation(0F, 15F, 0F, 0.7853982F, 1.570796F, 0F));
        root.addOrReplaceChild("Shape3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 0F, -4F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("Shape4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5.5F, 0F, -3.5F, 3, 4, 3), PartPose.offset(0F, 15.5F, 0F));
        root.addOrReplaceChild("Shape5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -3F, 2, 1, 2), PartPose.offset(0F, 14.5F, 0F));
        root.addOrReplaceChild("Shape6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.5F, 0F, -2.5F, 1, 5, 1), PartPose.offset(0F, 10F, 0F));
        root.addOrReplaceChild("Shape7", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5.5F, 0F, -3.5F, 3, 6, 3), PartPose.offset(0F, 4F, 0F));
        root.addOrReplaceChild("Shape8", CubeListBuilder.create().texOffs(46, 0).mirror().addBox(-1F, 0F, -6F, 8, 3, 1), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("Shape8b", CubeListBuilder.create().texOffs(46, 0).mirror().addBox(-1F, 0F, 1F, 8, 3, 1), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("Shape9", CubeListBuilder.create().texOffs(50, 0).mirror().addBox(6F, 0F, -5F, 1, 3, 6), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("Shape9b", CubeListBuilder.create().texOffs(50, 0).mirror().addBox(-1F, 0F, -5F, 1, 3, 6), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("Shape10", CubeListBuilder.create().texOffs(24, 8).mirror().addBox(-1F, 0F, -6F, 8, 1, 8), PartPose.offset(0F, 22F, 0F));
        root.addOrReplaceChild("Shape11", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.5F, -3F, 0F, 3, 2, 3), PartPose.offsetAndRotation(0F, 18F, 0F, 0.7853982F, 1.570796F, 0F));
        root.addOrReplaceChild("Shape12", CubeListBuilder.create().texOffs(18, 4).mirror().addBox(-1F, 0F, -1F, 1, 12, 1), PartPose.offset(0F, 11F, 0F));
        root.addOrReplaceChild("Shape13", CubeListBuilder.create().texOffs(18, 0).mirror().addBox(-2F, 0F, -1F, 3, 2, 1), PartPose.offset(0F, 9F, 0F));
        root.addOrReplaceChild("Shape14", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-7F, 0F, -7F, 14, 1, 14), PartPose.offset(0F, 23F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        glass1.render(pose, buf, light, overlay, r, g, b, a);
        Shape1.render(pose, buf, light, overlay, r, g, b, a);
        Shape2.render(pose, buf, light, overlay, r, g, b, a);
        Shape3.render(pose, buf, light, overlay, r, g, b, a);
        Shape4.render(pose, buf, light, overlay, r, g, b, a);
        Shape5.render(pose, buf, light, overlay, r, g, b, a);
        Shape6.render(pose, buf, light, overlay, r, g, b, a);
        Shape7.render(pose, buf, light, overlay, r, g, b, a);
        Shape8.render(pose, buf, light, overlay, r, g, b, a);
        Shape8b.render(pose, buf, light, overlay, r, g, b, a);
        Shape9.render(pose, buf, light, overlay, r, g, b, a);
        Shape9b.render(pose, buf, light, overlay, r, g, b, a);
        Shape10.render(pose, buf, light, overlay, r, g, b, a);
        Shape11.render(pose, buf, light, overlay, r, g, b, a);
        Shape12.render(pose, buf, light, overlay, r, g, b, a);
        Shape13.render(pose, buf, light, overlay, r, g, b, a);
        Shape14.render(pose, buf, light, overlay, r, g, b, a);
    }
}
