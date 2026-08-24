package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelSandwich {
    private final ModelPart basketU;
    private final ModelPart basketF;
    private final ModelPart basketB;
    private final ModelPart basketL;
    private final ModelPart basketR;
    private final ModelPart bread11;
    private final ModelPart bread12;
    private final ModelPart bread21;
    private final ModelPart bread22;
    private final ModelPart bread31;
    private final ModelPart bread32;
    private final ModelPart apple1;
    private final ModelPart apple2;
    private final ModelPart apple3;
    private final ModelPart egg1;
    private final ModelPart egg2;
    private final ModelPart egg3;
    private final ModelPart cassis1;
    private final ModelPart cassis2;
    private final ModelPart cassis3;

    public ModelSandwich(ModelPart root) {
        this.basketU = root.getChild("basketU");
        this.basketF = root.getChild("basketF");
        this.basketB = root.getChild("basketB");
        this.basketL = root.getChild("basketL");
        this.basketR = root.getChild("basketR");
        this.bread11 = root.getChild("bread11");
        this.bread12 = root.getChild("bread12");
        this.bread21 = root.getChild("bread21");
        this.bread22 = root.getChild("bread22");
        this.bread31 = root.getChild("bread31");
        this.bread32 = root.getChild("bread32");
        this.apple1 = root.getChild("apple1");
        this.apple2 = root.getChild("apple2");
        this.apple3 = root.getChild("apple3");
        this.egg1 = root.getChild("egg1");
        this.egg2 = root.getChild("egg2");
        this.egg3 = root.getChild("egg3");
        this.cassis1 = root.getChild("cassis1");
        this.cassis2 = root.getChild("cassis2");
        this.cassis3 = root.getChild("cassis3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("basketU", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 0F, -4F, 12, 1, 8), PartPose.offset(0F, 23F, 0F));
        root.addOrReplaceChild("basketF", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-6F, 0F, -4F, 12, 4, 1), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("basketB", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-6F, 0F, -4F, 12, 4, 1), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, 3.141593F, 0F));
        root.addOrReplaceChild("basketL", CubeListBuilder.create().texOffs(40, 0).mirror().addBox(-6F, 0F, -3F, 1, 4, 6), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("basketR", CubeListBuilder.create().texOffs(40, 0).mirror().addBox(-6F, 0F, -3F, 1, 4, 6), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, 3.141593F, 0F));
        root.addOrReplaceChild("bread11", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-4.5F, 0F, -2.5F, 1, 3, 5), PartPose.offsetAndRotation(0F, 18.5F, 0F, 0F, -0.0698132F, -0.0872665F));
        root.addOrReplaceChild("bread12", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-3F, 0F, -2.5F, 1, 3, 5), PartPose.offsetAndRotation(0F, 18.5F, 0F, 0F, -0.0698132F, -0.122173F));
        root.addOrReplaceChild("bread21", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.5F, 0F, -2.5F, 1, 3, 5), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, 0F, -0.122173F));
        root.addOrReplaceChild("bread22", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(0F, 0F, -2.5F, 1, 3, 5), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, 0F, -0.122173F));
        root.addOrReplaceChild("bread31", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(2F, 0F, -2.5F, 1, 3, 5), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, 0F, 0.0349066F));
        root.addOrReplaceChild("bread32", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(3.5F, 0F, -2.5F, 1, 3, 5), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("apple1", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-3.5F, 0F, -2F, 1, 3, 4), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, -0.0698132F, 0F));
        root.addOrReplaceChild("apple2", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-0.5F, 0F, -2F, 1, 3, 4), PartPose.offset(0F, 19.5F, 0F));
        root.addOrReplaceChild("apple3", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(3F, 0F, -2F, 1, 3, 4), PartPose.offset(0F, 19.5F, 0F));
        root.addOrReplaceChild("egg1", CubeListBuilder.create().texOffs(10, 24).mirror().addBox(-4F, 0F, -2F, 1, 3, 4), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, -0.0698132F, 0F));
        root.addOrReplaceChild("egg2", CubeListBuilder.create().texOffs(10, 24).mirror().addBox(-1F, 0F, -2F, 1, 3, 4), PartPose.offset(0F, 19.5F, 0F));
        root.addOrReplaceChild("egg3", CubeListBuilder.create().texOffs(10, 24).mirror().addBox(2.5F, 0F, -2F, 1, 3, 4), PartPose.offset(0F, 19.5F, 0F));
        root.addOrReplaceChild("cassis1", CubeListBuilder.create().texOffs(20, 24).mirror().addBox(-4F, 0F, -2F, 1, 3, 4), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, -0.0698132F, 0F));
        root.addOrReplaceChild("cassis2", CubeListBuilder.create().texOffs(20, 24).mirror().addBox(-1F, 0F, -2F, 1, 3, 4), PartPose.offset(0F, 19.5F, 0F));
        root.addOrReplaceChild("cassis3", CubeListBuilder.create().texOffs(20, 24).mirror().addBox(2.5F, 0F, -2F, 1, 3, 4), PartPose.offset(0F, 19.5F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        basketU.render(pose, buf, light, overlay, r, g, b, a);
        basketF.render(pose, buf, light, overlay, r, g, b, a);
        basketB.render(pose, buf, light, overlay, r, g, b, a);
        basketL.render(pose, buf, light, overlay, r, g, b, a);
        basketR.render(pose, buf, light, overlay, r, g, b, a);
        bread11.render(pose, buf, light, overlay, r, g, b, a);
        bread12.render(pose, buf, light, overlay, r, g, b, a);
        bread21.render(pose, buf, light, overlay, r, g, b, a);
        bread22.render(pose, buf, light, overlay, r, g, b, a);
        bread31.render(pose, buf, light, overlay, r, g, b, a);
        bread32.render(pose, buf, light, overlay, r, g, b, a);
        apple1.render(pose, buf, light, overlay, r, g, b, a);
        apple2.render(pose, buf, light, overlay, r, g, b, a);
        apple3.render(pose, buf, light, overlay, r, g, b, a);
        egg1.render(pose, buf, light, overlay, r, g, b, a);
        egg2.render(pose, buf, light, overlay, r, g, b, a);
        egg3.render(pose, buf, light, overlay, r, g, b, a);
        cassis1.render(pose, buf, light, overlay, r, g, b, a);
        cassis2.render(pose, buf, light, overlay, r, g, b, a);
        cassis3.render(pose, buf, light, overlay, r, g, b, a);
    }
}
