package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelCharger {
    private final ModelPart bottom;
    private final ModelPart top;
    private final ModelPart back;
    private final ModelPart side1;
    private final ModelPart side2;
    private final ModelPart inner;
    private final ModelPart button1;
    private final ModelPart button2;
    private final ModelPart dial;
    private final ModelPart panel1;
    private final ModelPart panel2;

    public ModelCharger(ModelPart root) {
        this.bottom = root.getChild("bottom");
        this.top = root.getChild("top");
        this.back = root.getChild("back");
        this.side1 = root.getChild("side1");
        this.side2 = root.getChild("side2");
        this.inner = root.getChild("inner");
        this.button1 = root.getChild("button1");
        this.button2 = root.getChild("button2");
        this.dial = root.getChild("dial");
        this.panel1 = root.getChild("panel1");
        this.panel2 = root.getChild("panel2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-8F, 7F, -8F, 16, 1, 16), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-8F, -8F, -8F, 16, 1, 16), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("back", CubeListBuilder.create().texOffs(64, 0).mirror().addBox(-8F, -7F, 7F, 16, 14, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("side1", CubeListBuilder.create().texOffs(64, 1).mirror().addBox(-8F, -7F, -8F, 1, 14, 16), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("side2", CubeListBuilder.create().texOffs(64, 1).mirror().addBox(7F, -7F, -8F, 1, 14, 16), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("inner", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-7F, -7F, -6F, 14, 14, 13), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("button1", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(-5F, 1F, -7F, 1, 1, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("button2", CubeListBuilder.create().texOffs(4, 48).mirror().addBox(-3F, 1F, -7F, 1, 1, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("dial", CubeListBuilder.create().texOffs(8, 48).mirror().addBox(3F, 0F, -7F, 2, 2, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("panel1", CubeListBuilder.create().texOffs(0, 51).mirror().addBox(-3F, -5F, -6.5F, 8, 4, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("panel2", CubeListBuilder.create().texOffs(0, 56).mirror().addBox(-6F, 4F, -7F, 12, 2, 1), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(mesh, 128, 64);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        bottom.render(pose, buf, light, overlay, r, g, b, a);
        top.render(pose, buf, light, overlay, r, g, b, a);
        back.render(pose, buf, light, overlay, r, g, b, a);
        side1.render(pose, buf, light, overlay, r, g, b, a);
        side2.render(pose, buf, light, overlay, r, g, b, a);
        inner.render(pose, buf, light, overlay, r, g, b, a);
        button1.render(pose, buf, light, overlay, r, g, b, a);
        button2.render(pose, buf, light, overlay, r, g, b, a);
        dial.render(pose, buf, light, overlay, r, g, b, a);
        panel1.render(pose, buf, light, overlay, r, g, b, a);
        panel2.render(pose, buf, light, overlay, r, g, b, a);
    }
}
