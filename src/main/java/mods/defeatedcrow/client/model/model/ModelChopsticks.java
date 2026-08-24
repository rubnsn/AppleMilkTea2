package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelChopsticks {
    private final ModelPart cup;
    private final ModelPart stick1;
    private final ModelPart stick2;
    private final ModelPart stick3;
    private final ModelPart stick4;
    private final ModelPart stick5;
    private final ModelPart stick6;

    public ModelChopsticks(ModelPart root) {
        this.cup = root.getChild("cup");
        this.stick1 = root.getChild("stick1");
        this.stick2 = root.getChild("stick2");
        this.stick3 = root.getChild("stick3");
        this.stick4 = root.getChild("stick4");
        this.stick5 = root.getChild("stick5");
        this.stick6 = root.getChild("stick6");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("cup", CubeListBuilder.create().texOffs(5, 0).addBox(0F, 0F, 0F, 4, 5, 4), PartPose.offset(-2F, 19F, -2F));
        root.addOrReplaceChild("stick1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1, 8, 1), PartPose.offsetAndRotation(1F, 14F, 0F, 0F, 0F, 0.2094395F));
        root.addOrReplaceChild("stick2", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1, 8, 1), PartPose.offsetAndRotation(1F, 14F, -2F, 0.1745329F, 0F, 0.0872665F));
        root.addOrReplaceChild("stick3", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1, 8, 1), PartPose.offsetAndRotation(0F, 14F, 1.5F, -0.2617994F, 0F, 0F));
        root.addOrReplaceChild("stick4", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1, 8, 1), PartPose.offsetAndRotation(-2F, 14F, 2F, -0.2617994F, 0F, -0.1745329F));
        root.addOrReplaceChild("stick5", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1, 8, 1), PartPose.offsetAndRotation(-2.5F, 15F, -2.5F, 0.2617994F, 0F, -0.2617994F));
        root.addOrReplaceChild("stick6", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 1, 8, 1), PartPose.offsetAndRotation(-1F, 14F, -1F, 0.0523599F, 0F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        cup.render(pose, buf, light, overlay, r, g, b, a);
        stick1.render(pose, buf, light, overlay, r, g, b, a);
        stick2.render(pose, buf, light, overlay, r, g, b, a);
        stick3.render(pose, buf, light, overlay, r, g, b, a);
        stick4.render(pose, buf, light, overlay, r, g, b, a);
        stick5.render(pose, buf, light, overlay, r, g, b, a);
        stick6.render(pose, buf, light, overlay, r, g, b, a);
    }
}