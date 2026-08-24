package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelYuzuGatling {
    private final ModelPart muz1;
    private final ModelPart muz2;
    private final ModelPart muz3;
    private final ModelPart syl1;
    private final ModelPart syl2;
    private final ModelPart bod1;
    private final ModelPart bod2;
    private final ModelPart bod3;
    private final ModelPart she1;
    private final ModelPart base1;
    private final ModelPart base2;

    public ModelYuzuGatling(ModelPart root) {
        this.muz1 = root.getChild("muz1");
        this.muz2 = root.getChild("muz2");
        this.muz3 = root.getChild("muz3");
        this.syl1 = root.getChild("syl1");
        this.syl2 = root.getChild("syl2");
        this.bod1 = root.getChild("bod1");
        this.bod2 = root.getChild("bod2");
        this.bod3 = root.getChild("bod3");
        this.she1 = root.getChild("she1");
        this.base1 = root.getChild("base1");
        this.base2 = root.getChild("base2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("muz1", CubeListBuilder.create().texOffs(0, 4).mirror().addBox(3F, -0.5F, -0.5F, 12, 1, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        root.addOrReplaceChild("muz2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3F, -2F, 0.5F, 17, 2, 2), PartPose.offsetAndRotation(0F, 17F, 0F, 0F, 1.570796F, 0F));
        root.addOrReplaceChild("muz3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3F, -1F, -2.5F, 17, 2, 2), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        root.addOrReplaceChild("syl1", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-1.5F, -1F, -3F, 3, 2, 2), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("syl2", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(-2.5F, -1F, -1F, 5, 3, 3), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("bod1", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-3F, 0F, 2F, 6, 2, 8), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("bod2", CubeListBuilder.create().texOffs(38, 0).mirror().addBox(-2F, -0.5F, 2F, 4, 1, 5), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("bod3", CubeListBuilder.create().texOffs(54, 0).mirror().addBox(-1F, -2F, 2F, 2, 1, 2), PartPose.offset(0F, 17F, 0F));
        root.addOrReplaceChild("she1", CubeListBuilder.create().texOffs(32, 16).mirror().addBox(-3.5F, 0F, 7F, 7, 1, 8), PartPose.offsetAndRotation(0F, 16F, 0F, -0.2617994F, 0F, 0F));
        root.addOrReplaceChild("base1", CubeListBuilder.create().texOffs(0, 21).mirror().addBox(-3.5F, 4F, 1F, 7, 4, 7), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("base2", CubeListBuilder.create().texOffs(32, 25).mirror().addBox(-2F, 3F, 2F, 4, 2, 5), PartPose.offset(0F, 15F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        muz1.render(pose, buf, light, overlay, r, g, b, a);
        muz2.render(pose, buf, light, overlay, r, g, b, a);
        muz3.render(pose, buf, light, overlay, r, g, b, a);
        syl1.render(pose, buf, light, overlay, r, g, b, a);
        syl2.render(pose, buf, light, overlay, r, g, b, a);
        bod1.render(pose, buf, light, overlay, r, g, b, a);
        bod2.render(pose, buf, light, overlay, r, g, b, a);
        bod3.render(pose, buf, light, overlay, r, g, b, a);
        she1.render(pose, buf, light, overlay, r, g, b, a);
        base1.render(pose, buf, light, overlay, r, g, b, a);
        base2.render(pose, buf, light, overlay, r, g, b, a);
    }
}
