package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelBasketL {
    private final ModelPart base;
    private final ModelPart sideF;
    private final ModelPart sideB;
    private final ModelPart sideR;
    private final ModelPart sideL;

    public ModelBasketL(ModelPart root) {
        this.base = root.getChild("base");
        this.sideF = root.getChild("sideF");
        this.sideB = root.getChild("sideB");
        this.sideR = root.getChild("sideR");
        this.sideL = root.getChild("sideL");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-8F, 7F, -8F, 16, 1, 16), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("sideF", CubeListBuilder.create().texOffs(0, 19).addBox(-8F, 3F, -8F, 16, 4, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("sideB", CubeListBuilder.create().texOffs(0, 19).addBox(-8F, 0F, 7F, 16, 4, 1), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("sideR", CubeListBuilder.create().texOffs(0, 26).addBox(-7F, 3F, -8F, 14, 4, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("sideL", CubeListBuilder.create().texOffs(0, 26).addBox(-7F, 3F, 7F, 14, 4, 1), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(mesh, 64, 64);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        base.render(pose, buf, light, overlay, r, g, b, a);
        sideF.render(pose, buf, light, overlay, r, g, b, a);
        sideB.render(pose, buf, light, overlay, r, g, b, a);
        sideR.render(pose, buf, light, overlay, r, g, b, a);
        sideL.render(pose, buf, light, overlay, r, g, b, a);
    }
}