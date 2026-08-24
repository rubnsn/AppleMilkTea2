package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelRotaryDial {
    private final ModelPart bottom;
    private final ModelPart middle;
    private final ModelPart back;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart handle;
    private final ModelPart handle2;
    private final ModelPart handle3;
    private final ModelPart plate;

    public ModelRotaryDial(ModelPart root) {
        this.bottom = root.getChild("bottom");
        this.middle = root.getChild("middle");
        this.back = root.getChild("back");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.handle = root.getChild("handle");
        this.handle2 = root.getChild("handle2");
        this.handle3 = root.getChild("handle3");
        this.plate = root.getChild("plate");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.5F, 0F, -5F, 9, 4, 10), PartPose.offset(0F, 20F, 0F));
        root.addOrReplaceChild("middle", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.5F, 0F, -5F, 9, 4, 9), PartPose.offsetAndRotation(0F, 18F, 0F, 0.418879F, 0F, 0F));
        root.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.5F, 0F, 3F, 9, 7, 4), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(2F, 0F, 4F, 1, 1, 2), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, 4F, 1, 1, 2), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("handle", CubeListBuilder.create().texOffs(0, 15).mirror().addBox(-5F, 0F, 4F, 10, 1, 2), PartPose.offset(0F, 15F, 0F));
        root.addOrReplaceChild("handle2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(5F, 0F, 3F, 3, 4, 4), PartPose.offset(0F, 15F, 0F));
        root.addOrReplaceChild("handle3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-8F, 0F, 3F, 3, 4, 4), PartPose.offset(0F, 15F, 0F));
        root.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(-4F, 0.5F, -5F, 8, 0, 8), PartPose.offsetAndRotation(0F, 17F, 0F, 0.418879F, 0F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        bottom.render(pose, buf, light, overlay, r, g, b, a);
        middle.render(pose, buf, light, overlay, r, g, b, a);
        back.render(pose, buf, light, overlay, r, g, b, a);
        leg1.render(pose, buf, light, overlay, r, g, b, a);
        leg2.render(pose, buf, light, overlay, r, g, b, a);
        handle.render(pose, buf, light, overlay, r, g, b, a);
        handle2.render(pose, buf, light, overlay, r, g, b, a);
        handle3.render(pose, buf, light, overlay, r, g, b, a);
        plate.render(pose, buf, light, overlay, r, g, b, a);
    }
}
