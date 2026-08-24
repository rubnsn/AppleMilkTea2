package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelAltBowl {
    private final ModelPart plate1;
    private final ModelPart plate2;
    private final ModelPart plate3;
    private final ModelPart plate4;
    private final ModelPart plate5;
    private final ModelPart plate6;
    private final ModelPart plate7;
    private final ModelPart plate8;
    private final ModelPart plate9;

    public ModelAltBowl(ModelPart root) {
        this.plate1 = root.getChild("plate1");
        this.plate2 = root.getChild("plate2");
        this.plate3 = root.getChild("plate3");
        this.plate4 = root.getChild("plate4");
        this.plate5 = root.getChild("plate5");
        this.plate6 = root.getChild("plate6");
        this.plate7 = root.getChild("plate7");
        this.plate8 = root.getChild("plate8");
        this.plate9 = root.getChild("plate9");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("plate1", CubeListBuilder.create().texOffs(16, 8).addBox(-3F, -3F, 0F, 6, 6, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("plate2", CubeListBuilder.create().texOffs(16, 0).addBox(-3F, -6.5F, 0.5F, 6, 4, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("plate3", CubeListBuilder.create().texOffs(16, 18).addBox(-3F, 2.5F, 0.5F, 6, 4, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("plate4", CubeListBuilder.create().texOffs(0, 8).addBox(-6.5F, -3F, 0.5F, 4, 6, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("plate5", CubeListBuilder.create().texOffs(36, 8).addBox(2.5F, -3F, 0.5F, 4, 6, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("plate6", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -6.5F, 1F, 4, 4, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("plate7", CubeListBuilder.create().texOffs(36, 0).addBox(2.5F, -6.5F, 1F, 4, 4, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("plate8", CubeListBuilder.create().texOffs(0, 18).addBox(-6.5F, 2.5F, 1F, 4, 4, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("plate9", CubeListBuilder.create().texOffs(36, 18).addBox(2.5F, 2.5F, 1F, 4, 4, 1), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        plate1.render(pose, buf, light, overlay, r, g, b, a);
        plate2.render(pose, buf, light, overlay, r, g, b, a);
        plate3.render(pose, buf, light, overlay, r, g, b, a);
        plate4.render(pose, buf, light, overlay, r, g, b, a);
        plate5.render(pose, buf, light, overlay, r, g, b, a);
        plate6.render(pose, buf, light, overlay, r, g, b, a);
        plate7.render(pose, buf, light, overlay, r, g, b, a);
        plate8.render(pose, buf, light, overlay, r, g, b, a);
        plate9.render(pose, buf, light, overlay, r, g, b, a);
    }
}