package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelKinoko {
    private final ModelPart base;
    private final ModelPart base2;
    private final ModelPart head1;
    private final ModelPart head2;

    public ModelKinoko(ModelPart root) {
        this.base = root.getChild("base");
        this.base2 = root.getChild("base2");
        this.head1 = root.getChild("head1");
        this.head2 = root.getChild("head2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -3F, 6, 2, 6), PartPose.offset(0F, 22F, 0F));
        root.addOrReplaceChild("base2", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-2F, 0F, -2F, 4, 5, 4), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-6F, 0F, -6F, 12, 3, 12), PartPose.offset(0F, 14F, 0F));
        root.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(4, 19).mirror().addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 13F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        base.render(pose, buf, light, overlay, r, g, b, a);
        base2.render(pose, buf, light, overlay, r, g, b, a);
        head1.render(pose, buf, light, overlay, r, g, b, a);
        head2.render(pose, buf, light, overlay, r, g, b, a);
    }
}
