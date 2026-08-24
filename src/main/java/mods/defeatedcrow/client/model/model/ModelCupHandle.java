package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelCupHandle {
    private final ModelPart handlea1;
    private final ModelPart handlea3;
    private final ModelPart handlea2;
    private final ModelPart handleb1;
    private final ModelPart handleb3;
    private final ModelPart handleb2;
    private final ModelPart handlec1;
    private final ModelPart handlec3;
    private final ModelPart handlec2;
    private final ModelPart handled1;
    private final ModelPart handled3;
    private final ModelPart handled2;
    private final ModelPart sideF;
    private final ModelPart sideB;
    private final ModelPart sideR;
    private final ModelPart sideL;
    private final ModelPart Bottom;

    public ModelCupHandle(ModelPart root) {
        this.handlea1 = root.getChild("handlea1");
        this.handlea3 = root.getChild("handlea3");
        this.handlea2 = root.getChild("handlea2");
        this.handleb1 = root.getChild("handleb1");
        this.handleb3 = root.getChild("handleb3");
        this.handleb2 = root.getChild("handleb2");
        this.handlec1 = root.getChild("handlec1");
        this.handlec3 = root.getChild("handlec3");
        this.handlec2 = root.getChild("handlec2");
        this.handled1 = root.getChild("handled1");
        this.handled3 = root.getChild("handled3");
        this.handled2 = root.getChild("handled2");
        this.sideF = root.getChild("sideF");
        this.sideB = root.getChild("sideB");
        this.sideR = root.getChild("sideR");
        this.sideL = root.getChild("sideL");
        this.Bottom = root.getChild("Bottom");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("handlea1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-1.0F, 17.0F, -5.0F));
        root.addOrReplaceChild("handlea3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-1.0F, 22.0F, -5.0F));
        root.addOrReplaceChild("handlea2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 6, 1), PartPose.offset(-1.0F, 17.0F, -6.0F));
        root.addOrReplaceChild("handleb1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-1F, 17F, 3F));
        root.addOrReplaceChild("handleb3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-1F, 22F, 3F));
        root.addOrReplaceChild("handleb2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 6, 1), PartPose.offset(-1F, 17F, 5F));
        root.addOrReplaceChild("handlec1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(3F, 17F, -1F));
        root.addOrReplaceChild("handlec3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(3F, 22F, -1F));
        root.addOrReplaceChild("handlec2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 1, 6, 2), PartPose.offset(5F, 17F, -1F));
        root.addOrReplaceChild("handled1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-5F, 17F, -1F));
        root.addOrReplaceChild("handled3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-5F, 22F, -1F));
        root.addOrReplaceChild("handled2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 1, 6, 2), PartPose.offset(-6F, 17F, -1F));
        root.addOrReplaceChild("sideF", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-3F, 0F, -3F, 6, 8, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("sideB", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-3F, 0F, 2F, 6, 8, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("sideR", CubeListBuilder.create().texOffs(15, 10).mirror().addBox(-3F, 0F, -2F, 1, 8, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("sideL", CubeListBuilder.create().texOffs(15, 10).mirror().addBox(2F, 0F, -2F, 1, 8, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("Bottom", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, -2F, 4, 2, 4), PartPose.offset(0F, 22F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        handlea1.render(pose, buf, light, overlay, r, g, b, a);
        handlea3.render(pose, buf, light, overlay, r, g, b, a);
        handlea2.render(pose, buf, light, overlay, r, g, b, a);
        handleb1.render(pose, buf, light, overlay, r, g, b, a);
        handleb3.render(pose, buf, light, overlay, r, g, b, a);
        handleb2.render(pose, buf, light, overlay, r, g, b, a);
        handlec1.render(pose, buf, light, overlay, r, g, b, a);
        handlec3.render(pose, buf, light, overlay, r, g, b, a);
        handlec2.render(pose, buf, light, overlay, r, g, b, a);
        handled1.render(pose, buf, light, overlay, r, g, b, a);
        handled3.render(pose, buf, light, overlay, r, g, b, a);
        handled2.render(pose, buf, light, overlay, r, g, b, a);
        sideF.render(pose, buf, light, overlay, r, g, b, a);
        sideB.render(pose, buf, light, overlay, r, g, b, a);
        sideR.render(pose, buf, light, overlay, r, g, b, a);
        sideL.render(pose, buf, light, overlay, r, g, b, a);
        Bottom.render(pose, buf, light, overlay, r, g, b, a);
    }
}
