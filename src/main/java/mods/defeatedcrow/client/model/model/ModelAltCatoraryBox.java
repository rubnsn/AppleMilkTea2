package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelAltCatoraryBox {
    private final ModelPart bottom;
    private final ModelPart sideL;
    private final ModelPart sideR;
    private final ModelPart sideF;
    private final ModelPart sideB;

    public ModelAltCatoraryBox(ModelPart root) {
        this.bottom = root.getChild("bottom");
        this.sideL = root.getChild("sideL");
        this.sideR = root.getChild("sideR");
        this.sideF = root.getChild("sideF");
        this.sideB = root.getChild("sideB");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 21).addBox(-6F, 7F, -2F, 12, 1, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("sideL", CubeListBuilder.create().texOffs(12, 12).addBox(6F, 4F, -2F, 1, 4, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("sideR", CubeListBuilder.create().texOffs(0, 12).addBox(-7F, 4F, -2F, 1, 4, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("sideF", CubeListBuilder.create().texOffs(0, 0).addBox(-7F, 4F, -3F, 14, 4, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("sideB", CubeListBuilder.create().texOffs(0, 6).addBox(-7F, 4F, 2F, 14, 4, 1), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        bottom.render(pose, buf, light, overlay, r, g, b, a);
        sideL.render(pose, buf, light, overlay, r, g, b, a);
        sideR.render(pose, buf, light, overlay, r, g, b, a);
        sideF.render(pose, buf, light, overlay, r, g, b, a);
        sideB.render(pose, buf, light, overlay, r, g, b, a);
    }
}