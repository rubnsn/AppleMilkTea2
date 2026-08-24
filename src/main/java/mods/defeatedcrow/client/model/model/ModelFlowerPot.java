package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelFlowerPot {
    private final ModelPart bagF;
    private final ModelPart bagB;
    private final ModelPart bagL;
    private final ModelPart bagR;
    private final ModelPart dirt;
    private final ModelPart chain;
    private final ModelPart flower1;
    private final ModelPart flower2;
    private final ModelPart flower3;
    private final ModelPart base;

    public ModelFlowerPot(ModelPart root) {
        this.bagF = root.getChild("bagF");
        this.bagB = root.getChild("bagB");
        this.bagL = root.getChild("bagL");
        this.bagR = root.getChild("bagR");
        this.dirt = root.getChild("dirt");
        this.chain = root.getChild("chain");
        this.flower1 = root.getChild("flower1");
        this.flower2 = root.getChild("flower2");
        this.flower3 = root.getChild("flower3");
        this.base = root.getChild("base");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("bagF", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 2F, 0F, 12, 6, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("bagB", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 2F, 5F, 12, 6, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("bagL", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(5F, 2F, 1F, 1, 6, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("bagR", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(-6F, 2F, 1F, 1, 6, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("dirt", CubeListBuilder.create().texOffs(7, 13).mirror().addBox(-5F, 3F, 1F, 10, 4, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("chain", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-6.5F, 3F, -0.5F, 13, 1, 8), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("flower1", CubeListBuilder.create().texOffs(36, 16).mirror().addBox(-7F, -8F, 3F, 14, 10, 0), PartPose.offsetAndRotation(0F, 16F, 0F, -0.2617994F, 0F, 0F));
        root.addOrReplaceChild("flower2", CubeListBuilder.create().texOffs(36, 16).mirror().addBox(-4F, -6F, 1F, 14, 10, 0), PartPose.offsetAndRotation(0F, 16F, 0F, 0.3490659F, -0.3490659F, 0F));
        root.addOrReplaceChild("flower3", CubeListBuilder.create().texOffs(36, 16).mirror().addBox(-10F, -6F, 1F, 14, 10, 0), PartPose.offsetAndRotation(0F, 16F, 0F, 0.3490659F, 0.3490659F, 0F));
        root.addOrReplaceChild("base", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-8F, -8F, 7.9F, 16, 16, 0), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        bagF.render(pose, buf, light, overlay, r, g, b, a);
        bagB.render(pose, buf, light, overlay, r, g, b, a);
        bagL.render(pose, buf, light, overlay, r, g, b, a);
        bagR.render(pose, buf, light, overlay, r, g, b, a);
        dirt.render(pose, buf, light, overlay, r, g, b, a);
        chain.render(pose, buf, light, overlay, r, g, b, a);
        flower1.render(pose, buf, light, overlay, r, g, b, a);
        flower2.render(pose, buf, light, overlay, r, g, b, a);
        flower3.render(pose, buf, light, overlay, r, g, b, a);
        base.render(pose, buf, light, overlay, r, g, b, a);
    }
}
