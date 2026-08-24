package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelJawCrusher {
    private final ModelPart body;
    private final ModelPart body2;
    private final ModelPart body3;
    private final ModelPart body4;
    private final ModelPart body5;
    private final ModelPart blade;
    private final ModelPart lod;
    private final ModelPart gear1;
    private final ModelPart gear2;
    private final ModelPart base2;
    private final ModelPart motor;
    private final ModelPart lod2;
    private final ModelPart gear3;
    private final ModelPart belt1;
    private final ModelPart belt2;
    private final ModelPart base;

    public ModelJawCrusher(ModelPart root) {
        this.body = root.getChild("body");
        this.body2 = root.getChild("body2");
        this.body3 = root.getChild("body3");
        this.body4 = root.getChild("body4");
        this.body5 = root.getChild("body5");
        this.blade = root.getChild("blade");
        this.lod = root.getChild("lod");
        this.gear1 = root.getChild("gear1");
        this.gear2 = root.getChild("gear2");
        this.base2 = root.getChild("base2");
        this.motor = root.getChild("motor");
        this.lod2 = root.getChild("lod2");
        this.gear3 = root.getChild("gear3");
        this.belt1 = root.getChild("belt1");
        this.belt2 = root.getChild("belt2");
        this.base = root.getChild("base");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5F, 0F, -1F, 10, 8, 4), PartPose.offset(0F, 15F, 0F));
        root.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(28, 0).addBox(-5F, 0F, 6F, 10, 8, 1), PartPose.offset(0F, 15F, 0F));
        root.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(32, 21).addBox(4F, 0F, 3F, 1, 8, 3), PartPose.offset(0F, 15F, 0F));
        root.addOrReplaceChild("body4", CubeListBuilder.create().texOffs(32, 21).addBox(-5F, 0F, 3F, 1, 8, 3), PartPose.offset(0F, 15F, 0F));
        root.addOrReplaceChild("body5", CubeListBuilder.create().texOffs(40, 20).addBox(-4F, 0F, -2F, 8, 8, 4), PartPose.offset(0F, 14.5F, 0F));
        root.addOrReplaceChild("blade", CubeListBuilder.create().texOffs(16, 15).addBox(-4F, 0F, 3F, 8, 7, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("lod", CubeListBuilder.create().texOffs(0, 12).addBox(-6F, 0F, 0F, 12, 1, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("gear1", CubeListBuilder.create().texOffs(0, 14).addBox(6F, 0F, -2F, 1, 5, 5), PartPose.offset(0F, 14F, 0F));
        root.addOrReplaceChild("gear2", CubeListBuilder.create().texOffs(0, 14).addBox(-7F, 0F, -2F, 1, 5, 5), PartPose.offset(0F, 14F, 0F));
        root.addOrReplaceChild("base2", CubeListBuilder.create().texOffs(0, 17).addBox(-5F, 0F, -7F, 1, 1, 14), PartPose.offset(0F, 23F, 0F));
        root.addOrReplaceChild("motor", CubeListBuilder.create().texOffs(38, 13).addBox(-5F, 0F, -7F, 10, 3, 3), PartPose.offset(0F, 20F, 0F));
        root.addOrReplaceChild("lod2", CubeListBuilder.create().texOffs(0, 12).addBox(5F, 0F, -6F, 1, 1, 1), PartPose.offset(0F, 21F, 0F));
        root.addOrReplaceChild("gear3", CubeListBuilder.create().texOffs(0, 24).addBox(6F, 0F, -7F, 1, 3, 3), PartPose.offset(0F, 20F, 0F));
        root.addOrReplaceChild("belt1", CubeListBuilder.create().texOffs(43, 4).addBox(6F, 0F, -10F, 1, 0, 8), PartPose.offset(0F, 12.5F, 0F));
        root.addOrReplaceChild("belt2", CubeListBuilder.create().texOffs(43, 4).addBox(6F, 0F, -5.5F, 1, 0, 8), PartPose.offset(0F, 20F, 0F));
        root.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 17).addBox(4F, 0F, -7F, 1, 1, 14), PartPose.offset(0F, 23F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        body.render(pose, buf, light, overlay, r, g, b, a);
        body2.render(pose, buf, light, overlay, r, g, b, a);
        body3.render(pose, buf, light, overlay, r, g, b, a);
        body4.render(pose, buf, light, overlay, r, g, b, a);
        body5.render(pose, buf, light, overlay, r, g, b, a);
        blade.render(pose, buf, light, overlay, r, g, b, a);
        lod.render(pose, buf, light, overlay, r, g, b, a);
        gear1.render(pose, buf, light, overlay, r, g, b, a);
        gear2.render(pose, buf, light, overlay, r, g, b, a);
        base2.render(pose, buf, light, overlay, r, g, b, a);
        motor.render(pose, buf, light, overlay, r, g, b, a);
        lod2.render(pose, buf, light, overlay, r, g, b, a);
        gear3.render(pose, buf, light, overlay, r, g, b, a);
        belt1.render(pose, buf, light, overlay, r, g, b, a);
        belt2.render(pose, buf, light, overlay, r, g, b, a);
        base.render(pose, buf, light, overlay, r, g, b, a);
    }
}