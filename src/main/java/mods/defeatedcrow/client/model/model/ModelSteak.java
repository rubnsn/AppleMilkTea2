package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelSteak {
    private final ModelPart pork;
    private final ModelPart carrot1;
    private final ModelPart carrot2;
    private final ModelPart carrot3;
    private final ModelPart potato1;
    private final ModelPart potato2;
    private final ModelPart beef;
    private final ModelPart butter;
    private final ModelPart body1;
    private final ModelPart body2;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart leg5;
    private final ModelPart leg6;
    private final ModelPart leg7;
    private final ModelPart leg8;
    private final ModelPart bottom;
    private final ModelPart bottom2;
    private final ModelPart top1;
    private final ModelPart top2;
    private final ModelPart Shape1;
    private final ModelPart wood;
    private final ModelPart plate;

    public ModelSteak(ModelPart root) {
        this.pork = root.getChild("pork");
        this.carrot1 = root.getChild("carrot1");
        this.carrot2 = root.getChild("carrot2");
        this.carrot3 = root.getChild("carrot3");
        this.potato1 = root.getChild("potato1");
        this.potato2 = root.getChild("potato2");
        this.beef = root.getChild("beef");
        this.butter = root.getChild("butter");
        this.body1 = root.getChild("body1");
        this.body2 = root.getChild("body2");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
        this.leg5 = root.getChild("leg5");
        this.leg6 = root.getChild("leg6");
        this.leg7 = root.getChild("leg7");
        this.leg8 = root.getChild("leg8");
        this.bottom = root.getChild("bottom");
        this.bottom2 = root.getChild("bottom2");
        this.top1 = root.getChild("top1");
        this.top2 = root.getChild("top2");
        this.Shape1 = root.getChild("Shape1");
        this.wood = root.getChild("wood");
        this.plate = root.getChild("plate");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("pork", CubeListBuilder.create().texOffs(0, 8).addBox(-4F, 0F, -5F, 8, 3, 5), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("carrot1", CubeListBuilder.create().texOffs(0, 18).addBox(5F, 0F, 0F, 1, 1, 3), PartPose.offset(0F, 20F, 0F));
        root.addOrReplaceChild("carrot2", CubeListBuilder.create().texOffs(0, 18).addBox(4F, 0F, 1F, 1, 1, 3), PartPose.offset(0F, 20F, 0F));
        root.addOrReplaceChild("carrot3", CubeListBuilder.create().texOffs(0, 18).addBox(-1F, 0F, 3F, 1, 1, 3), PartPose.offset(0F, 21F, 0F));
        root.addOrReplaceChild("potato1", CubeListBuilder.create().texOffs(0, 23).addBox(-5F, 0F, 2F, 2, 2, 2), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("potato2", CubeListBuilder.create().texOffs(0, 23).addBox(-3F, 0F, 3F, 2, 2, 2), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("beef", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 0F, -5F, 8, 3, 5), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("butter", CubeListBuilder.create().texOffs(9, 18).addBox(-0.5F, 0F, -3F, 1, 1, 1), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -6F, 6, 5, 10), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 3).addBox(-4F, 0F, -5F, 8, 4, 8), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 16).addBox(3F, 0F, -2F, 2, 5, 7), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 16).addBox(-5F, 0F, -2F, 2, 5, 7), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(3, 18).addBox(5F, 0F, -1F, 1, 4, 5), PartPose.offset(0F, 16.5F, 0F));
        root.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(3, 18).addBox(-6F, 0F, -1F, 1, 4, 5), PartPose.offset(0F, 16.5F, 0F));
        root.addOrReplaceChild("leg5", CubeListBuilder.create().texOffs(18, 16).addBox(3F, -1F, 2F, 2, 2, 4), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("leg6", CubeListBuilder.create().texOffs(18, 16).addBox(-5F, -1F, 1F, 2, 2, 4), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("leg7", CubeListBuilder.create().texOffs(18, 24).addBox(3F, 0F, 4F, 2, 1, 4), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("leg8", CubeListBuilder.create().texOffs(18, 24).addBox(-5F, 0F, 4F, 2, 1, 4), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 16).addBox(-2F, 0F, -2F, 4, 1, 4), PartPose.offset(0F, 21F, 0F));
        root.addOrReplaceChild("bottom2", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, 0F, -3F, 6, 1, 6), PartPose.offset(0F, 20F, 0F));
        root.addOrReplaceChild("top1", CubeListBuilder.create().texOffs(14, 8).addBox(-2F, 0F, 4F, 4, 4, 1), PartPose.offset(0F, 14F, 0F));
        root.addOrReplaceChild("top2", CubeListBuilder.create().texOffs(0, 8).addBox(-3F, 0F, 3F, 6, 6, 1), PartPose.offset(0F, 14F, 0F));
        root.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 22).addBox(-2F, 0F, -2F, 4, 2, 4), PartPose.offset(0F, 18F, 0F));
        root.addOrReplaceChild("wood", CubeListBuilder.create().texOffs(0, 0).addBox(-8F, 0F, -8F, 16, 1, 16), PartPose.offset(0F, 23F, 0F));
        root.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(0, 17).addBox(-7F, 0F, -7F, 14, 1, 14), PartPose.offset(0F, 22F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        pork.render(pose, buf, light, overlay, r, g, b, a);
        carrot1.render(pose, buf, light, overlay, r, g, b, a);
        carrot2.render(pose, buf, light, overlay, r, g, b, a);
        carrot3.render(pose, buf, light, overlay, r, g, b, a);
        potato1.render(pose, buf, light, overlay, r, g, b, a);
        potato2.render(pose, buf, light, overlay, r, g, b, a);
        beef.render(pose, buf, light, overlay, r, g, b, a);
        butter.render(pose, buf, light, overlay, r, g, b, a);
        body1.render(pose, buf, light, overlay, r, g, b, a);
        body2.render(pose, buf, light, overlay, r, g, b, a);
        leg1.render(pose, buf, light, overlay, r, g, b, a);
        leg2.render(pose, buf, light, overlay, r, g, b, a);
        leg3.render(pose, buf, light, overlay, r, g, b, a);
        leg4.render(pose, buf, light, overlay, r, g, b, a);
        leg5.render(pose, buf, light, overlay, r, g, b, a);
        leg6.render(pose, buf, light, overlay, r, g, b, a);
        leg7.render(pose, buf, light, overlay, r, g, b, a);
        leg8.render(pose, buf, light, overlay, r, g, b, a);
        bottom.render(pose, buf, light, overlay, r, g, b, a);
        bottom2.render(pose, buf, light, overlay, r, g, b, a);
        top1.render(pose, buf, light, overlay, r, g, b, a);
        top2.render(pose, buf, light, overlay, r, g, b, a);
        Shape1.render(pose, buf, light, overlay, r, g, b, a);
        wood.render(pose, buf, light, overlay, r, g, b, a);
        plate.render(pose, buf, light, overlay, r, g, b, a);
    }
}