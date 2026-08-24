package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelTart {
    private final ModelPart plate;
    private final ModelPart sideB;
    private final ModelPart sideF;
    private final ModelPart sideL;
    private final ModelPart sideR;
    private final ModelPart main;
    private final ModelPart main2;
    private final ModelPart crop1;
    private final ModelPart crop2;
    private final ModelPart crop3;
    private final ModelPart moussebase;
    private final ModelPart mousse1;
    private final ModelPart mousse2;
    private final ModelPart mousse3;
    private final ModelPart mousse4;
    private final ModelPart moussetop;
    private final ModelPart moussetop2;
    private final ModelPart moussetop3;
    private final ModelPart crop4;
    private final ModelPart crop5;
    private final ModelPart crop6;

    public ModelTart(ModelPart root) {
        this.plate = root.getChild("plate");
        this.sideB = root.getChild("sideB");
        this.sideF = root.getChild("sideF");
        this.sideL = root.getChild("sideL");
        this.sideR = root.getChild("sideR");
        this.main = root.getChild("main");
        this.main2 = root.getChild("main2");
        this.crop1 = root.getChild("crop1");
        this.crop2 = root.getChild("crop2");
        this.crop3 = root.getChild("crop3");
        this.moussebase = root.getChild("moussebase");
        this.mousse1 = root.getChild("mousse1");
        this.mousse2 = root.getChild("mousse2");
        this.mousse3 = root.getChild("mousse3");
        this.mousse4 = root.getChild("mousse4");
        this.moussetop = root.getChild("moussetop");
        this.moussetop2 = root.getChild("moussetop2");
        this.moussetop3 = root.getChild("moussetop3");
        this.crop4 = root.getChild("crop4");
        this.crop5 = root.getChild("crop5");
        this.crop6 = root.getChild("crop6");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(0, 0).addBox(-7F, 0F, -7F, 14, 1, 14), PartPose.offset(0F, 23F, 0F));
        root.addOrReplaceChild("sideB", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 0F, -5F, 1, 5, 10), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("sideF", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 0F, -5F, 1, 5, 10), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("sideL", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 0F, -5F, 1, 5, 10), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("sideR", CubeListBuilder.create().texOffs(0, 0).addBox(-6F, 0F, -5F, 1, 5, 10), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("main", CubeListBuilder.create().texOffs(24, 0).addBox(-5F, 0F, -5F, 10, 3, 10), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("main2", CubeListBuilder.create().texOffs(24, 13).addBox(-5F, 0F, -5F, 10, 3, 10), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("crop1", CubeListBuilder.create().texOffs(0, 0).addBox(0.5F, 0F, 0F, 1, 1, 1), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("crop2", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1.5F, 1, 1, 1), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("crop3", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, 0.5F, 1, 1, 1), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("moussebase", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 21F, 0F));
        root.addOrReplaceChild("mousse1", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 22F, 0F));
        root.addOrReplaceChild("mousse2", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 20F, 0F));
        root.addOrReplaceChild("mousse3", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("mousse4", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("moussetop", CubeListBuilder.create().texOffs(0, 11).addBox(0F, 0F, 0F, 10, 0, 10), PartPose.offset(-5F, 17.9F, -5F));
        root.addOrReplaceChild("moussetop2", CubeListBuilder.create().texOffs(30, 11).addBox(0F, 0F, 0F, 10, 0, 10), PartPose.offset(-5F, 17.9F, -5F));
        root.addOrReplaceChild("moussetop3", CubeListBuilder.create().texOffs(0, 21).addBox(0F, 0F, 0F, 10, 0, 10), PartPose.offset(-5F, 17.9F, -5F));
        root.addOrReplaceChild("crop4", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, 0.5F, 1, 1, 1), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("crop5", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0F, -1F, 1, 1, 1), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("crop6", CubeListBuilder.create().texOffs(0, 0).addBox(0F, 0F, -1.5F, 1, 1, 1), PartPose.offset(0F, 17F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        plate.render(pose, buf, light, overlay, r, g, b, a);
        sideB.render(pose, buf, light, overlay, r, g, b, a);
        sideF.render(pose, buf, light, overlay, r, g, b, a);
        sideL.render(pose, buf, light, overlay, r, g, b, a);
        sideR.render(pose, buf, light, overlay, r, g, b, a);
        main.render(pose, buf, light, overlay, r, g, b, a);
        main2.render(pose, buf, light, overlay, r, g, b, a);
        crop1.render(pose, buf, light, overlay, r, g, b, a);
        crop2.render(pose, buf, light, overlay, r, g, b, a);
        crop3.render(pose, buf, light, overlay, r, g, b, a);
        moussebase.render(pose, buf, light, overlay, r, g, b, a);
        mousse1.render(pose, buf, light, overlay, r, g, b, a);
        mousse2.render(pose, buf, light, overlay, r, g, b, a);
        mousse3.render(pose, buf, light, overlay, r, g, b, a);
        mousse4.render(pose, buf, light, overlay, r, g, b, a);
        moussetop.render(pose, buf, light, overlay, r, g, b, a);
        moussetop2.render(pose, buf, light, overlay, r, g, b, a);
        moussetop3.render(pose, buf, light, overlay, r, g, b, a);
        crop4.render(pose, buf, light, overlay, r, g, b, a);
        crop5.render(pose, buf, light, overlay, r, g, b, a);
        crop6.render(pose, buf, light, overlay, r, g, b, a);
    }
}