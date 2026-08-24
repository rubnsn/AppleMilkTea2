package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelMelonBomb {
    private final ModelPart Shape1;

    public ModelMelonBomb(ModelPart root) {
        this.Shape1 = root.getChild("Shape1");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 12, 12, 12), PartPose.offset(-6F, -5F, -6F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        Shape1.render(pose, buf, light, overlay, r, g, b, a);
    }
}