package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelMakerHandle {
    private final ModelPart handle1;
    private final ModelPart handle2;
    private final ModelPart handle3;
    private final ModelPart spout;

    public ModelMakerHandle(ModelPart root) {
        this.handle1 = root.getChild("handle1");
        this.handle2 = root.getChild("handle2");
        this.handle3 = root.getChild("handle3");
        this.spout = root.getChild("spout");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("handle1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -7F, 2, 1, 3), PartPose.offset(0F, 10F, 0F));
        root.addOrReplaceChild("handle2", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -7F, 2, 8, 1), PartPose.offset(0F, 11F, 0F));
        root.addOrReplaceChild("handle3", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -7F, 2, 1, 2), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("spout", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, 4F, 3, 1, 2), PartPose.offset(0F, 9F, 0F));
        return LayerDefinition.create(mesh, 32, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        handle1.render(pose, buf, light, overlay, r, g, b, a);
        handle2.render(pose, buf, light, overlay, r, g, b, a);
        handle3.render(pose, buf, light, overlay, r, g, b, a);
        spout.render(pose, buf, light, overlay, r, g, b, a);
    }
}