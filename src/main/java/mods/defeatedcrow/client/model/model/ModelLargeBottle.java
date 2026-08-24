package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelLargeBottle {
    private final ModelPart bottom;
    private final ModelPart middle1;
    private final ModelPart middle2;
    private final ModelPart middle3;
    private final ModelPart top;
    private final ModelPart cap;

    public ModelLargeBottle(ModelPart root) {
        this.bottom = root.getChild("bottom");
        this.middle1 = root.getChild("middle1");
        this.middle2 = root.getChild("middle2");
        this.middle3 = root.getChild("middle3");
        this.top = root.getChild("top");
        this.cap = root.getChild("cap");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 7).addBox(-3F, 0F, -3F, 6, 7, 6), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("middle1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0F, -2.5F, 5, 2, 5), PartPose.offset(0F, 15F, 0F));
        root.addOrReplaceChild("middle2", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, -2F, 4, 1, 4), PartPose.offset(0F, 14F, 0F));
        root.addOrReplaceChild("middle3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1.5F, 3, 1, 3), PartPose.offset(0F, 13F, 0F));
        root.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2, 2, 2), PartPose.offset(0F, 11F, 0F));
        root.addOrReplaceChild("cap", CubeListBuilder.create().texOffs(20, 0).addBox(-1F, 0F, -1F, 2, 2, 2), PartPose.offset(0F, 9F, 0F));
        return LayerDefinition.create(mesh, 32, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        bottom.render(pose, buf, light, overlay, r, g, b, a);
        middle1.render(pose, buf, light, overlay, r, g, b, a);
        middle2.render(pose, buf, light, overlay, r, g, b, a);
        middle3.render(pose, buf, light, overlay, r, g, b, a);
        top.render(pose, buf, light, overlay, r, g, b, a);
        cap.render(pose, buf, light, overlay, r, g, b, a);
    }
}