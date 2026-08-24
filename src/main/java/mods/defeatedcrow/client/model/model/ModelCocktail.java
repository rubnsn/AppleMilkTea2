package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelCocktail {
    private final ModelPart lemon;
    private final ModelPart lime;
    private final ModelPart pine;
    private final ModelPart apple;
    private final ModelPart bubble;
    private final ModelPart bottom;
    private final ModelPart Aleg;
    private final ModelPart Aleg2;
    private final ModelPart Aside1;
    private final ModelPart Aside2;
    private final ModelPart Aside3;
    private final ModelPart Aside4;
    private final ModelPart Bside1;
    private final ModelPart Bside2;
    private final ModelPart Bside3;
    private final ModelPart Bside4;
    private final ModelPart Cside1;
    private final ModelPart Cside2;
    private final ModelPart Cside3;
    private final ModelPart Cside4;
    private final ModelPart icecube;
    private final ModelPart icecube2;
    private final ModelPart inner1;
    private final ModelPart inner2;
    private final ModelPart inner3;

    public ModelCocktail(ModelPart root) {
        this.lemon = root.getChild("lemon");
        this.lime = root.getChild("lime");
        this.pine = root.getChild("pine");
        this.apple = root.getChild("apple");
        this.bubble = root.getChild("bubble");
        this.bottom = root.getChild("bottom");
        this.Aleg = root.getChild("Aleg");
        this.Aleg2 = root.getChild("Aleg2");
        this.Aside1 = root.getChild("Aside1");
        this.Aside2 = root.getChild("Aside2");
        this.Aside3 = root.getChild("Aside3");
        this.Aside4 = root.getChild("Aside4");
        this.Bside1 = root.getChild("Bside1");
        this.Bside2 = root.getChild("Bside2");
        this.Bside3 = root.getChild("Bside3");
        this.Bside4 = root.getChild("Bside4");
        this.Cside1 = root.getChild("Cside1");
        this.Cside2 = root.getChild("Cside2");
        this.Cside3 = root.getChild("Cside3");
        this.Cside4 = root.getChild("Cside4");
        this.icecube = root.getChild("icecube");
        this.icecube2 = root.getChild("icecube2");
        this.inner1 = root.getChild("inner1");
        this.inner2 = root.getChild("inner2");
        this.inner3 = root.getChild("inner3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("lemon", CubeListBuilder.create().texOffs(32, 0).addBox(2F, -1F, 0F, 5, 4, 1), PartPose.offset(0F, 14F, 0F));
        root.addOrReplaceChild("lime", CubeListBuilder.create().texOffs(32, 5).addBox(2F, 0F, 0F, 5, 4, 1), PartPose.offset(0F, 14F, 0F));
        root.addOrReplaceChild("pine", CubeListBuilder.create().texOffs(32, 10).addBox(2F, 0F, 0F, 5, 4, 1), PartPose.offset(0F, 13F, 0F));
        root.addOrReplaceChild("apple", CubeListBuilder.create().texOffs(32, 15).addBox(2F, 0F, 0F, 5, 4, 1), PartPose.offset(0F, 13F, 0F));
        root.addOrReplaceChild("bubble", CubeListBuilder.create().texOffs(32, 21).addBox(-3F, 0F, -3F, 6, 1, 6), PartPose.offset(0F, 15.5F, 0F));
        root.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -3F, 6, 1, 6), PartPose.offset(0F, 23F, 0F));
        root.addOrReplaceChild("Aleg", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0F, -0.5F, 1, 4, 1), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("Aleg2", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -3F, 6, 2, 6), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("Aside1", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, -4F, 8, 3, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("Aside2", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, 3F, 8, 3, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("Aside3", CubeListBuilder.create().texOffs(0, 0).addBox(3F, 0F, -3F, 1, 3, 6), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("Aside4", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, -3F, 1, 3, 6), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("Bside1", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, -4F, 8, 9, 1), PartPose.offset(0F, 14F, 0F));
        root.addOrReplaceChild("Bside2", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, -1F, 3F, 8, 9, 1), PartPose.offset(0F, 15F, 0F));
        root.addOrReplaceChild("Bside3", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, -3F, 1, 9, 6), PartPose.offset(0F, 14F, 0F));
        root.addOrReplaceChild("Bside4", CubeListBuilder.create().texOffs(0, 0).addBox(3F, 0F, -3F, 1, 9, 6), PartPose.offset(0F, 14F, 0F));
        root.addOrReplaceChild("Cside1", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, -4F, 8, 6, 1), PartPose.offset(0F, 13F, 0F));
        root.addOrReplaceChild("Cside2", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, 3F, 8, 6, 1), PartPose.offset(0F, 13F, 0F));
        root.addOrReplaceChild("Cside3", CubeListBuilder.create().texOffs(0, 0).addBox(3F, 0F, -3F, 1, 6, 6), PartPose.offset(0F, 13F, 0F));
        root.addOrReplaceChild("Cside4", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, -3F, 1, 6, 6), PartPose.offset(0F, 13F, 0F));
        root.addOrReplaceChild("icecube", CubeListBuilder.create().texOffs(0, 16).addBox(-1F, 0F, -1F, 3, 3, 3), PartPose.offsetAndRotation(0F, 20F, 0F, 0.4560576F, 0F, -0.1319841F));
        root.addOrReplaceChild("icecube2", CubeListBuilder.create().texOffs(0, 17).addBox(-2F, 0F, -2F, 3, 3, 3), PartPose.offsetAndRotation(0F, 18F, 0F, -0.1745329F, 0.2648976F, -0.8922867F));
        root.addOrReplaceChild("inner1", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -3F, 6, 7, 6), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("inner2", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -3F, 6, 2, 6), PartPose.offset(0F, 16.5F, 0F));
        root.addOrReplaceChild("inner3", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, -2F, 4, 1, 4), PartPose.offset(0F, 15.5F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        lemon.render(pose, buf, light, overlay, r, g, b, a);
        lime.render(pose, buf, light, overlay, r, g, b, a);
        pine.render(pose, buf, light, overlay, r, g, b, a);
        apple.render(pose, buf, light, overlay, r, g, b, a);
        bubble.render(pose, buf, light, overlay, r, g, b, a);
        bottom.render(pose, buf, light, overlay, r, g, b, a);
        Aleg.render(pose, buf, light, overlay, r, g, b, a);
        Aleg2.render(pose, buf, light, overlay, r, g, b, a);
        Aside1.render(pose, buf, light, overlay, r, g, b, a);
        Aside2.render(pose, buf, light, overlay, r, g, b, a);
        Aside3.render(pose, buf, light, overlay, r, g, b, a);
        Aside4.render(pose, buf, light, overlay, r, g, b, a);
        Bside1.render(pose, buf, light, overlay, r, g, b, a);
        Bside2.render(pose, buf, light, overlay, r, g, b, a);
        Bside3.render(pose, buf, light, overlay, r, g, b, a);
        Bside4.render(pose, buf, light, overlay, r, g, b, a);
        Cside1.render(pose, buf, light, overlay, r, g, b, a);
        Cside2.render(pose, buf, light, overlay, r, g, b, a);
        Cside3.render(pose, buf, light, overlay, r, g, b, a);
        Cside4.render(pose, buf, light, overlay, r, g, b, a);
        icecube.render(pose, buf, light, overlay, r, g, b, a);
        icecube2.render(pose, buf, light, overlay, r, g, b, a);
        inner1.render(pose, buf, light, overlay, r, g, b, a);
        inner2.render(pose, buf, light, overlay, r, g, b, a);
        inner3.render(pose, buf, light, overlay, r, g, b, a);
    }
}