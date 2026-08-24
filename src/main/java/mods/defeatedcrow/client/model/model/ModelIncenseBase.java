package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelIncenseBase {
    private final ModelPart base;
    private final ModelPart base2;
    private final ModelPart body;
    private final ModelPart side1;
    private final ModelPart side2;
    private final ModelPart side3;
    private final ModelPart side4;
    private final ModelPart middle1;
    private final ModelPart middle2;
    private final ModelPart middle3;
    private final ModelPart middle4;
    private final ModelPart top1;
    private final ModelPart top2;
    private final ModelPart top3;
    private final ModelPart top4;
    private final ModelPart top5;
    private final ModelPart top6;
    private final ModelPart top7;
    private final ModelPart top8;
    private final ModelPart top9;
    private final ModelPart cone1;
    private final ModelPart cone2;
    private final ModelPart glow;

    public ModelIncenseBase(ModelPart root) {
        this.base = root.getChild("base");
        this.base2 = root.getChild("base2");
        this.body = root.getChild("body");
        this.side1 = root.getChild("side1");
        this.side2 = root.getChild("side2");
        this.side3 = root.getChild("side3");
        this.side4 = root.getChild("side4");
        this.middle1 = root.getChild("middle1");
        this.middle2 = root.getChild("middle2");
        this.middle3 = root.getChild("middle3");
        this.middle4 = root.getChild("middle4");
        this.top1 = root.getChild("top1");
        this.top2 = root.getChild("top2");
        this.top3 = root.getChild("top3");
        this.top4 = root.getChild("top4");
        this.top5 = root.getChild("top5");
        this.top6 = root.getChild("top6");
        this.top7 = root.getChild("top7");
        this.top8 = root.getChild("top8");
        this.top9 = root.getChild("top9");
        this.cone1 = root.getChild("cone1");
        this.cone2 = root.getChild("cone2");
        this.glow = root.getChild("glow");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 23).mirror().addBox(-3F, 0F, -3F, 6, 1, 6), PartPose.offset(0F, 23F, 0F));
        root.addOrReplaceChild("base2", CubeListBuilder.create().texOffs(0, 23).mirror().addBox(-4F, 0F, -4F, 8, 1, 8), PartPose.offset(0F, 22F, 0F));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 0).mirror().addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 21F, 0F));
        root.addOrReplaceChild("side1", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(-5F, 0F, -5F, 10, 3, 1), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("side2", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(-5F, 0F, 4F, 10, 3, 1), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("side3", CubeListBuilder.create().texOffs(16, 11).mirror().addBox(-5F, 0F, -4F, 1, 3, 8), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("side4", CubeListBuilder.create().texOffs(16, 11).mirror().addBox(4F, 0F, -4F, 1, 3, 8), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("middle1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -4F, 8, 1, 1), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("middle2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, 3F, 8, 1, 1), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("middle3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -3F, 1, 1, 6), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("middle4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3F, 0F, -3F, 1, 1, 6), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("top1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -4F, 1, 1, 4), PartPose.offsetAndRotation(0F, 15F, 0F, 0.5235988F, 0F, 0F));
        root.addOrReplaceChild("top2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -4F, 1, 1, 4), PartPose.offsetAndRotation(0F, 15F, 0F, 0.5235988F, 1.570796F, 0F));
        root.addOrReplaceChild("top3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -4F, 1, 1, 4), PartPose.offsetAndRotation(0F, 15F, 0F, 0.5235988F, 3.141593F, 0F));
        root.addOrReplaceChild("top4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -4F, 1, 1, 4), PartPose.offsetAndRotation(0F, 15F, 0F, 0.5235988F, -1.570796F, 0F));
        root.addOrReplaceChild("top5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -5F, 1, 1, 5), PartPose.offsetAndRotation(0F, 15F, 0F, 0.5235988F, 0.7853982F, 0F));
        root.addOrReplaceChild("top6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -5F, 1, 1, 5), PartPose.offsetAndRotation(0F, 15F, 0F, 0.5235988F, 2.356194F, 0F));
        root.addOrReplaceChild("top7", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -5F, 1, 1, 5), PartPose.offsetAndRotation(0F, 15F, 0F, 0.5235988F, -2.356194F, 0F));
        root.addOrReplaceChild("top8", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -5F, 1, 1, 5), PartPose.offsetAndRotation(0F, 15F, 0F, 0.5235988F, -0.7853982F, 0F));
        root.addOrReplaceChild("top9", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, 0F, -1F, 2, 1, 2), PartPose.offset(0F, 15F, 0F));
        root.addOrReplaceChild("cone1", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-1F, 0F, -1F, 2, 1, 2), PartPose.offset(0F, 20F, 0F));
        root.addOrReplaceChild("cone2", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-0.5F, 0F, -0.5F, 1, 1, 1), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("glow", CubeListBuilder.create().texOffs(9, 18).mirror().addBox(-0.5F, 0F, -0.5F, 1, 1, 1), PartPose.offset(0F, 18F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        base.render(pose, buf, light, overlay, r, g, b, a);
        base2.render(pose, buf, light, overlay, r, g, b, a);
        body.render(pose, buf, light, overlay, r, g, b, a);
        side1.render(pose, buf, light, overlay, r, g, b, a);
        side2.render(pose, buf, light, overlay, r, g, b, a);
        side3.render(pose, buf, light, overlay, r, g, b, a);
        side4.render(pose, buf, light, overlay, r, g, b, a);
        middle1.render(pose, buf, light, overlay, r, g, b, a);
        middle2.render(pose, buf, light, overlay, r, g, b, a);
        middle3.render(pose, buf, light, overlay, r, g, b, a);
        middle4.render(pose, buf, light, overlay, r, g, b, a);
        top1.render(pose, buf, light, overlay, r, g, b, a);
        top2.render(pose, buf, light, overlay, r, g, b, a);
        top3.render(pose, buf, light, overlay, r, g, b, a);
        top4.render(pose, buf, light, overlay, r, g, b, a);
        top5.render(pose, buf, light, overlay, r, g, b, a);
        top6.render(pose, buf, light, overlay, r, g, b, a);
        top7.render(pose, buf, light, overlay, r, g, b, a);
        top8.render(pose, buf, light, overlay, r, g, b, a);
        top9.render(pose, buf, light, overlay, r, g, b, a);
        cone1.render(pose, buf, light, overlay, r, g, b, a);
        cone2.render(pose, buf, light, overlay, r, g, b, a);
        glow.render(pose, buf, light, overlay, r, g, b, a);
    }
}
