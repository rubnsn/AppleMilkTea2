package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelCanister {
    private final ModelPart bottomC;
    private final ModelPart side1C;
    private final ModelPart side2C;
    private final ModelPart side3C;
    private final ModelPart side4C;
    private final ModelPart top1C;
    private final ModelPart top2C;
    private final ModelPart woodcap;
    private final ModelPart contents;

    public ModelCanister(ModelPart root) {
        this.bottomC = root.getChild("bottomC");
        this.side1C = root.getChild("side1C");
        this.side2C = root.getChild("side2C");
        this.side3C = root.getChild("side3C");
        this.side4C = root.getChild("side4C");
        this.top1C = root.getChild("top1C");
        this.top2C = root.getChild("top2C");
        this.woodcap = root.getChild("woodcap");
        this.contents = root.getChild("contents");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("bottomC", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -5F, 10, 2, 10), PartPose.offset(0F, 22F, 0F));
        root.addOrReplaceChild("side1C", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -5F, 10, 10, 1), PartPose.offset(0F, 12F, 0F));
        root.addOrReplaceChild("side2C", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, 4F, 10, 10, 1), PartPose.offset(0F, 12F, 0F));
        root.addOrReplaceChild("side3C", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -4F, 1, 10, 8), PartPose.offset(0F, 12F, 0F));
        root.addOrReplaceChild("side4C", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, -4F, 1, 10, 8), PartPose.offset(0F, 12F, 0F));
        root.addOrReplaceChild("top1C", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -4F, 8, 2, 8), PartPose.offset(0F, 11F, 0F));
        root.addOrReplaceChild("top2C", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -3F, 6, 1, 6), PartPose.offset(0F, 10F, 0F));
        root.addOrReplaceChild("woodcap", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -4F, 8, 1, 8), PartPose.offset(0F, 9F, 0F));
        root.addOrReplaceChild("contents", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -4F, 8, 8, 8), PartPose.offset(0F, 14F, 0F));
        return LayerDefinition.create(mesh, 32, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        bottomC.render(pose, buf, light, overlay, r, g, b, a);
        side1C.render(pose, buf, light, overlay, r, g, b, a);
        side2C.render(pose, buf, light, overlay, r, g, b, a);
        side3C.render(pose, buf, light, overlay, r, g, b, a);
        side4C.render(pose, buf, light, overlay, r, g, b, a);
        top1C.render(pose, buf, light, overlay, r, g, b, a);
        top2C.render(pose, buf, light, overlay, r, g, b, a);
        woodcap.render(pose, buf, light, overlay, r, g, b, a);
        contents.render(pose, buf, light, overlay, r, g, b, a);
    }
}
