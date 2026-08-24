package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelHandleEngine {
    private final ModelPart base;
    private final ModelPart shaft1;
    private final ModelPart shaft2;
    private final ModelPart shaft3;
    private final ModelPart handle;

    public ModelHandleEngine(ModelPart root) {
        this.base = root.getChild("base");
        this.shaft1 = root.getChild("shaft1");
        this.shaft2 = root.getChild("shaft2");
        this.shaft3 = root.getChild("shaft3");
        this.handle = root.getChild("handle");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("base", CubeListBuilder.create().texOffs(9, 0).addBox(-2F, 7F, -2F, 4, 1, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("shaft1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 1F, -0.5F, 1, 6, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("shaft2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 1F, 0.5F, 1, 1, 3), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("shaft3", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0F, 3.5F, 1, 2, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("handle", CubeListBuilder.create().texOffs(0, 8).addBox(-1F, -4F, 3F, 2, 4, 2), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        base.render(pose, buf, light, overlay, r, g, b, a);
        shaft1.render(pose, buf, light, overlay, r, g, b, a);
        shaft2.render(pose, buf, light, overlay, r, g, b, a);
        shaft3.render(pose, buf, light, overlay, r, g, b, a);
        handle.render(pose, buf, light, overlay, r, g, b, a);
    }
}