package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelPanHandle {
    private final ModelPart handlea1;
    private final ModelPart handlea2;
    private final ModelPart handlea3;
    private final ModelPart handlea4;

    public ModelPanHandle(ModelPart root) {
        this.handlea1 = root.getChild("handlea1");
        this.handlea2 = root.getChild("handlea2");
        this.handlea3 = root.getChild("handlea3");
        this.handlea4 = root.getChild("handlea4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("handlea1", CubeListBuilder.create().texOffs(0, 0).addBox(6F, 0F, -1.5F, 2, 1, 3), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("handlea2", CubeListBuilder.create().texOffs(0, 0).addBox(-8F, 0F, -1.5F, 2, 1, 3), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("handlea3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, 6F, 3, 1, 2), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("handlea4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -8F, 3, 1, 2), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(mesh, 32, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        handlea1.render(pose, buf, light, overlay, r, g, b, a);
        handlea2.render(pose, buf, light, overlay, r, g, b, a);
        handlea3.render(pose, buf, light, overlay, r, g, b, a);
        handlea4.render(pose, buf, light, overlay, r, g, b, a);
    }
}
