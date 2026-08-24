package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelAutoMaker {
    private final ModelPart Shape1;
    private final ModelPart Shape2;
    private final ModelPart Shape3;
    private final ModelPart Shape4;

    public ModelAutoMaker(ModelPart root) {
        this.Shape1 = root.getChild("Shape1");
        this.Shape2 = root.getChild("Shape2");
        this.Shape3 = root.getChild("Shape3");
        this.Shape4 = root.getChild("Shape4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(13, 16).addBox(-1F, 0F, -1F, 2, 1, 2), PartPose.offset(0F, 23F, 0F));
        root.addOrReplaceChild("Shape2", CubeListBuilder.create().texOffs(0, 16).addBox(-2F, 0F, -2F, 4, 1, 4), PartPose.offset(0F, 22F, 0F));
        root.addOrReplaceChild("Shape3", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -3F, 6, 3, 6), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("Shape4", CubeListBuilder.create().texOffs(0, 9).addBox(-3F, 0F, -5F, 6, 1, 6), PartPose.offset(0F, 18F, 2F));
        return LayerDefinition.create(mesh, 32, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        Shape1.render(pose, buf, light, overlay, r, g, b, a);
        Shape2.render(pose, buf, light, overlay, r, g, b, a);
        Shape3.render(pose, buf, light, overlay, r, g, b, a);
        Shape4.render(pose, buf, light, overlay, r, g, b, a);
    }
}
