package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelYuzuBullet {
    private final ModelPart Shape1;

    public ModelYuzuBullet(ModelPart root) {
        this.Shape1 = root.getChild("Shape1");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1F, -1.5F, 3, 3, 3), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(mesh, 32, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        Shape1.render(pose, buf, light, overlay, r, g, b, a);
    }
}