package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelChocoPan {
    private final ModelPart contants1;
    private final ModelPart contants2;
    private final ModelPart contants3;

    public ModelChocoPan(ModelPart root) {
        this.contants1 = root.getChild("contants1");
        this.contants2 = root.getChild("contants2");
        this.contants3 = root.getChild("contants3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("contants1", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 21F, 0F));
        root.addOrReplaceChild("contants2", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10, 3, 10), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("contants3", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10, 5, 10), PartPose.offset(0F, 17F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        contants1.render(pose, buf, light, overlay, r, g, b, a);
        contants2.render(pose, buf, light, overlay, r, g, b, a);
        contants3.render(pose, buf, light, overlay, r, g, b, a);
    }
}