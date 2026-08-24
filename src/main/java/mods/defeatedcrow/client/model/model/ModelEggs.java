package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelEggs {
    private final ModelPart bottom1;
    private final ModelPart bottom2;
    private final ModelPart bottom3;
    private final ModelPart bottom4;
    private final ModelPart bottom5;
    private final ModelPart bottom6;
    private final ModelPart bottom7;
    private final ModelPart bottom8;
    private final ModelPart top1;
    private final ModelPart top2;
    private final ModelPart top3;
    private final ModelPart top4;
    private final ModelPart top5;
    private final ModelPart top6;
    private final ModelPart top7;
    private final ModelPart top8;

    public ModelEggs(ModelPart root) {
        this.bottom1 = root.getChild("bottom1");
        this.bottom2 = root.getChild("bottom2");
        this.bottom3 = root.getChild("bottom3");
        this.bottom4 = root.getChild("bottom4");
        this.bottom5 = root.getChild("bottom5");
        this.bottom6 = root.getChild("bottom6");
        this.bottom7 = root.getChild("bottom7");
        this.bottom8 = root.getChild("bottom8");
        this.top1 = root.getChild("top1");
        this.top2 = root.getChild("top2");
        this.top3 = root.getChild("top3");
        this.top4 = root.getChild("top4");
        this.top5 = root.getChild("top5");
        this.top6 = root.getChild("top6");
        this.top7 = root.getChild("top7");
        this.top8 = root.getChild("top8");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("bottom1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 3, 3, 3), PartPose.offset(-6.5F, 21F, -5F));
        root.addOrReplaceChild("bottom2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 3, 3, 3), PartPose.offset(-1.5F, 21F, -5F));
        root.addOrReplaceChild("bottom3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 3, 3, 3), PartPose.offset(3.5F, 21F, -5F));
        root.addOrReplaceChild("bottom4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 3, 3, 3), PartPose.offset(-4F, 21F, -1F));
        root.addOrReplaceChild("bottom5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 3, 3, 3), PartPose.offset(1F, 21F, -1F));
        root.addOrReplaceChild("bottom6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 3, 3, 3), PartPose.offset(-1.5F, 21F, 3F));
        root.addOrReplaceChild("bottom7", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 3, 3, 3), PartPose.offset(3.5F, 21F, 3F));
        root.addOrReplaceChild("bottom8", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 3, 3, 3), PartPose.offset(-6.5F, 21F, 3F));
        root.addOrReplaceChild("top1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-6F, 20F, -4.5F));
        root.addOrReplaceChild("top2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-1F, 20F, -4.5F));
        root.addOrReplaceChild("top3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(4F, 20F, -4.5F));
        root.addOrReplaceChild("top4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-3.5F, 20F, -0.5F));
        root.addOrReplaceChild("top5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(1.5F, 20F, -0.5F));
        root.addOrReplaceChild("top6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-6F, 20F, 3.5F));
        root.addOrReplaceChild("top7", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(-1F, 20F, 3.5F));
        root.addOrReplaceChild("top8", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 2, 1, 2), PartPose.offset(4F, 20F, 3.5F));
        return LayerDefinition.create(mesh, 16, 16);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        bottom1.render(pose, buf, light, overlay, r, g, b, a);
        bottom2.render(pose, buf, light, overlay, r, g, b, a);
        bottom3.render(pose, buf, light, overlay, r, g, b, a);
        bottom4.render(pose, buf, light, overlay, r, g, b, a);
        bottom5.render(pose, buf, light, overlay, r, g, b, a);
        bottom6.render(pose, buf, light, overlay, r, g, b, a);
        bottom7.render(pose, buf, light, overlay, r, g, b, a);
        bottom8.render(pose, buf, light, overlay, r, g, b, a);
        top1.render(pose, buf, light, overlay, r, g, b, a);
        top2.render(pose, buf, light, overlay, r, g, b, a);
        top3.render(pose, buf, light, overlay, r, g, b, a);
        top4.render(pose, buf, light, overlay, r, g, b, a);
        top5.render(pose, buf, light, overlay, r, g, b, a);
        top6.render(pose, buf, light, overlay, r, g, b, a);
        top7.render(pose, buf, light, overlay, r, g, b, a);
        top8.render(pose, buf, light, overlay, r, g, b, a);
    }
}
