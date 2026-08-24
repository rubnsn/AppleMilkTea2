package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelFossilCannon {
    private final ModelPart muz1;
    private final ModelPart muz2;
    private final ModelPart muz3;
    private final ModelPart muz4;
    private final ModelPart syl1;
    private final ModelPart syl2;
    private final ModelPart bod1;
    private final ModelPart base1;
    private final ModelPart base2;
    private final ModelPart sca1;
    private final ModelPart sca2;
    private final ModelPart sca3;
    private final ModelPart sca4;
    private final ModelPart finbase;
    private final ModelPart fin1;
    private final ModelPart fin2;
    private final ModelPart fin3;
    private final ModelPart fin4;
    private final ModelPart fin5;
    private final ModelPart fin6;
    private final ModelPart fin7;

    public ModelFossilCannon(ModelPart root) {
        this.muz1 = root.getChild("muz1");
        this.muz2 = root.getChild("muz2");
        this.muz3 = root.getChild("muz3");
        this.muz4 = root.getChild("muz4");
        this.syl1 = root.getChild("syl1");
        this.syl2 = root.getChild("syl2");
        this.bod1 = root.getChild("bod1");
        this.base1 = root.getChild("base1");
        this.base2 = root.getChild("base2");
        this.sca1 = root.getChild("sca1");
        this.sca2 = root.getChild("sca2");
        this.sca3 = root.getChild("sca3");
        this.sca4 = root.getChild("sca4");
        this.finbase = root.getChild("finbase");
        this.fin1 = root.getChild("fin1");
        this.fin2 = root.getChild("fin2");
        this.fin3 = root.getChild("fin3");
        this.fin4 = root.getChild("fin4");
        this.fin5 = root.getChild("fin5");
        this.fin6 = root.getChild("fin6");
        this.fin7 = root.getChild("fin7");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("muz1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3F, -1.5F, -1F, 12, 1, 2), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        root.addOrReplaceChild("muz2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3F, -2F, 1F, 12, 3, 1), PartPose.offsetAndRotation(0F, 17F, 0F, 0F, 1.570796F, 0F));
        root.addOrReplaceChild("muz3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3F, -1F, -2F, 12, 3, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        root.addOrReplaceChild("muz4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3F, 1.5F, -1F, 12, 1, 2), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        root.addOrReplaceChild("syl1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, -0.5F, -5F, 2, 2, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("syl2", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-1.5F, -0.5F, -1F, 3, 3, 3), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("bod1", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-2F, 0F, 2F, 4, 2, 6), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("base1", CubeListBuilder.create().texOffs(0, 21).mirror().addBox(-3.5F, 4F, 1F, 7, 4, 7), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("base2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 3F, 2F, 4, 2, 5), PartPose.offset(0F, 15F, 0F));
        root.addOrReplaceChild("sca1", CubeListBuilder.create().texOffs(40, 0).mirror().addBox(-2.5F, 0.5F, 4F, 5, 1, 6), PartPose.offsetAndRotation(0F, 16F, 0F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("sca2", CubeListBuilder.create().texOffs(40, 0).mirror().addBox(-2.5F, -2F, 0F, 5, 1, 6), PartPose.offsetAndRotation(0F, 17F, 0F, 0.2617994F, 0F, 0F));
        root.addOrReplaceChild("sca3", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-1F, -0.5F, 4F, 2, 2, 2), PartPose.offsetAndRotation(0F, 16F, 0F, 0.1745329F, 0F, 0F));
        root.addOrReplaceChild("sca4", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-1F, -2F, -1F, 2, 2, 2), PartPose.offsetAndRotation(0F, 16F, 0F, 0.1745329F, 0F, 0F));
        root.addOrReplaceChild("finbase", CubeListBuilder.create().texOffs(13, 8).mirror().addBox(-1F, 0F, 7F, 3, 2, 3), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 0.1745329F, 0F));
        root.addOrReplaceChild("fin1", CubeListBuilder.create().texOffs(32, 20).mirror().addBox(-2F, -2F, 8F, 2, 2, 10), PartPose.offsetAndRotation(0F, 16F, 0F, -0.2617994F, 0.5235988F, 0F));
        root.addOrReplaceChild("fin2", CubeListBuilder.create().texOffs(32, 26).mirror().addBox(-4F, -2F, 8F, 4, 2, 4), PartPose.offsetAndRotation(0F, 16F, 0F, -0.2617994F, 0.3490659F, 0F));
        root.addOrReplaceChild("fin3", CubeListBuilder.create().texOffs(32, 25).mirror().addBox(8F, -1F, 15F, 2, 2, 5), PartPose.offsetAndRotation(0F, 16F, 0F, -0.2617994F, -0.0872665F, 0F));
        root.addOrReplaceChild("fin4", CubeListBuilder.create().texOffs(32, 9).mirror().addBox(-2F, -2F, 11F, 3, 1, 8), PartPose.offsetAndRotation(0F, 16F, 0F, -0.296706F, 0.3839724F, 0F));
        root.addOrReplaceChild("fin5", CubeListBuilder.create().texOffs(33, 10).mirror().addBox(-1F, -2F, 11F, 3, 1, 7), PartPose.offsetAndRotation(0F, 16F, 0F, -0.3141593F, 0.2094395F, 0F));
        root.addOrReplaceChild("fin6", CubeListBuilder.create().texOffs(34, 11).mirror().addBox(0F, -2F, 11F, 3, 1, 6), PartPose.offsetAndRotation(0F, 16F, 0F, -0.3316126F, 0.0349066F, 0F));
        root.addOrReplaceChild("fin7", CubeListBuilder.create().texOffs(32, 22).mirror().addBox(0F, -3F, 9F, 2, 2, 8), PartPose.offsetAndRotation(0F, 16F, 0F, -0.3665191F, -0.1396263F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        muz1.render(pose, buf, light, overlay, r, g, b, a);
        muz2.render(pose, buf, light, overlay, r, g, b, a);
        muz3.render(pose, buf, light, overlay, r, g, b, a);
        muz4.render(pose, buf, light, overlay, r, g, b, a);
        syl1.render(pose, buf, light, overlay, r, g, b, a);
        syl2.render(pose, buf, light, overlay, r, g, b, a);
        bod1.render(pose, buf, light, overlay, r, g, b, a);
        base1.render(pose, buf, light, overlay, r, g, b, a);
        base2.render(pose, buf, light, overlay, r, g, b, a);
        sca1.render(pose, buf, light, overlay, r, g, b, a);
        sca2.render(pose, buf, light, overlay, r, g, b, a);
        sca3.render(pose, buf, light, overlay, r, g, b, a);
        sca4.render(pose, buf, light, overlay, r, g, b, a);
        finbase.render(pose, buf, light, overlay, r, g, b, a);
        fin1.render(pose, buf, light, overlay, r, g, b, a);
        fin2.render(pose, buf, light, overlay, r, g, b, a);
        fin3.render(pose, buf, light, overlay, r, g, b, a);
        fin4.render(pose, buf, light, overlay, r, g, b, a);
        fin5.render(pose, buf, light, overlay, r, g, b, a);
        fin6.render(pose, buf, light, overlay, r, g, b, a);
        fin7.render(pose, buf, light, overlay, r, g, b, a);
    }
}
