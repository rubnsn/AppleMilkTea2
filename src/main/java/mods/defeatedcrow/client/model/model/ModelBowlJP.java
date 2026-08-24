package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelBowlJP {
    private final ModelPart bottom1;
    private final ModelPart bottom2;
    private final ModelPart sideL;
    private final ModelPart sideR;
    private final ModelPart sideF;
    private final ModelPart sideB;
    private final ModelPart sideL2;
    private final ModelPart sideR2;
    private final ModelPart sideF2;
    private final ModelPart sideB2;

    public ModelBowlJP(ModelPart root) {
        this.bottom1 = root.getChild("bottom1");
        this.bottom2 = root.getChild("bottom2");
        this.sideL = root.getChild("sideL");
        this.sideR = root.getChild("sideR");
        this.sideF = root.getChild("sideF");
        this.sideB = root.getChild("sideB");
        this.sideL2 = root.getChild("sideL2");
        this.sideR2 = root.getChild("sideR2");
        this.sideF2 = root.getChild("sideF2");
        this.sideB2 = root.getChild("sideB2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("bottom1", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 4, 1, 4), PartPose.offset(-2F, 23F, -2F));
        root.addOrReplaceChild("bottom2", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0F, 6, 1, 6), PartPose.offset(-3F, 22F, -3F));
        root.addOrReplaceChild("sideL", CubeListBuilder.create().texOffs(0, 10).addBox(0F, 0F, 0F, 1, 4, 6), PartPose.offset(3F, 18F, -3F));
        root.addOrReplaceChild("sideR", CubeListBuilder.create().texOffs(14, 10).addBox(0F, 0F, 0F, 1, 4, 6), PartPose.offset(-4F, 18F, -3F));
        root.addOrReplaceChild("sideF", CubeListBuilder.create().texOffs(0, 20).addBox(0F, -2F, 0F, 8, 4, 1), PartPose.offset(-4F, 20F, -4F));
        root.addOrReplaceChild("sideB", CubeListBuilder.create().texOffs(0, 25).addBox(0F, 0F, 0F, 8, 4, 1), PartPose.offset(-4F, 18F, 3F));
        root.addOrReplaceChild("sideL2", CubeListBuilder.create().texOffs(33, 10).addBox(0F, 0F, 0F, 1, 3, 6), PartPose.offset(3F, 19F, -3F));
        root.addOrReplaceChild("sideR2", CubeListBuilder.create().texOffs(47, 10).addBox(0F, 0F, 0F, 1, 3, 6), PartPose.offset(-4F, 19F, -3F));
        root.addOrReplaceChild("sideF2", CubeListBuilder.create().texOffs(33, 19).addBox(0F, 0F, 0F, 8, 3, 1), PartPose.offset(-4F, 19F, -4F));
        root.addOrReplaceChild("sideB2", CubeListBuilder.create().texOffs(33, 23).addBox(0F, 0F, 0F, 8, 3, 1), PartPose.offset(-4F, 19F, 3F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        bottom1.render(pose, buf, light, overlay, r, g, b, a);
        bottom2.render(pose, buf, light, overlay, r, g, b, a);
        sideL.render(pose, buf, light, overlay, r, g, b, a);
        sideR.render(pose, buf, light, overlay, r, g, b, a);
        sideF.render(pose, buf, light, overlay, r, g, b, a);
        sideB.render(pose, buf, light, overlay, r, g, b, a);
        sideL2.render(pose, buf, light, overlay, r, g, b, a);
        sideR2.render(pose, buf, light, overlay, r, g, b, a);
        sideF2.render(pose, buf, light, overlay, r, g, b, a);
        sideB2.render(pose, buf, light, overlay, r, g, b, a);
    }
}