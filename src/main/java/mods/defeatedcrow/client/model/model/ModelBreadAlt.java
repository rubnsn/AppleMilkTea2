package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelBreadAlt {
    private final ModelPart bread1;
    private final ModelPart bread2;
    private final ModelPart bread3;
    private final ModelPart bread4;
    private final ModelPart bread5;

    public ModelBreadAlt(ModelPart root) {
        this.bread1 = root.getChild("bread1");
        this.bread2 = root.getChild("bread2");
        this.bread3 = root.getChild("bread3");
        this.bread4 = root.getChild("bread4");
        this.bread5 = root.getChild("bread5");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("bread1", CubeListBuilder.create().texOffs(0, 0).addBox(-1F, -10F, 1.5F, 4, 16, 3), PartPose.offsetAndRotation(0F, 16F, 0F, -0.2617994F, 0.3490659F, 0F));
        root.addOrReplaceChild("bread2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -10F, 1.5F, 4, 16, 3), PartPose.offsetAndRotation(0F, 16F, 0F, -0.3396263F, -1.308997F, 0.2094395F));
        root.addOrReplaceChild("bread3", CubeListBuilder.create().texOffs(16, 0).addBox(-2F, -8F, -2F, 3, 14, 3), PartPose.offsetAndRotation(0F, 16F, 0F, -0.0872665F, -0.122173F, 0F));
        root.addOrReplaceChild("bread4", CubeListBuilder.create().texOffs(16, 0).addBox(0F, -8F, 1.5F, 3, 14, 3), PartPose.offsetAndRotation(0F, 16F, 0F, -0.2792527F, 1.919862F, 0.0872665F));
        root.addOrReplaceChild("bread5", CubeListBuilder.create().texOffs(16, 0).addBox(-0.5F, -7F, -5F, 3, 14, 3), PartPose.offsetAndRotation(0F, 16F, 0F, 0.0698132F, 1.047198F, 0F));
        return LayerDefinition.create(mesh, 32, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        bread1.render(pose, buf, light, overlay, r, g, b, a);
        bread2.render(pose, buf, light, overlay, r, g, b, a);
        bread3.render(pose, buf, light, overlay, r, g, b, a);
        bread4.render(pose, buf, light, overlay, r, g, b, a);
        bread5.render(pose, buf, light, overlay, r, g, b, a);
    }
}