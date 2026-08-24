package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelAltDial {
    private final ModelPart dodai1;
    private final ModelPart dodai2;
    private final ModelPart tou1;
    private final ModelPart tou2;
    private final ModelPart tou3;
    private final ModelPart juwa1;
    private final ModelPart juwa2;
    private final ModelPart juwa3;
    private final ModelPart juwa4;
    private final ModelPart juwa5;
    private final ModelPart juwa6;
    private final ModelPart dial1;
    private final ModelPart dial2;
    private final ModelPart tuwa1;
    private final ModelPart tuwa2;
    private final ModelPart tuwa3;

    public ModelAltDial(ModelPart root) {
        this.dodai1 = root.getChild("dodai1");
        this.dodai2 = root.getChild("dodai2");
        this.tou1 = root.getChild("tou1");
        this.tou2 = root.getChild("tou2");
        this.tou3 = root.getChild("tou3");
        this.juwa1 = root.getChild("juwa1");
        this.juwa2 = root.getChild("juwa2");
        this.juwa3 = root.getChild("juwa3");
        this.juwa4 = root.getChild("juwa4");
        this.juwa5 = root.getChild("juwa5");
        this.juwa6 = root.getChild("juwa6");
        this.dial1 = root.getChild("dial1");
        this.dial2 = root.getChild("dial2");
        this.tuwa1 = root.getChild("tuwa1");
        this.tuwa2 = root.getChild("tuwa2");
        this.tuwa3 = root.getChild("tuwa3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("dodai1", CubeListBuilder.create().texOffs(0, 0).addBox(-4F, 7F, -4F, 8, 1, 8), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("dodai2", CubeListBuilder.create().texOffs(0, 10).addBox(-3.5F, 6F, -3.5F, 7, 1, 7), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("tou1", CubeListBuilder.create().texOffs(0, 19).addBox(-1F, -2F, -1F, 2, 7, 2), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("tou2", CubeListBuilder.create().texOffs(9, 19).addBox(-0.5F, -4F, -0.5F, 1, 10, 1), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("tou3", CubeListBuilder.create().texOffs(14, 19).addBox(-4F, -1.8F, -0.5F, 3, 1, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 0F, 0.0698132F));
        root.addOrReplaceChild("juwa1", CubeListBuilder.create().texOffs(25, 0).addBox(-0.5F, -3F, -4.5F, 1, 1, 2), PartPose.offsetAndRotation(0F, 16F, 0F, -0.7853982F, 0F, 0F));
        root.addOrReplaceChild("juwa2", CubeListBuilder.create().texOffs(33, 0).addBox(-2F, -5F, -6F, 4, 4, 2), PartPose.offsetAndRotation(0F, 16F, 0F, -0.7853982F, 0F, 0F));
        root.addOrReplaceChild("juwa3", CubeListBuilder.create().texOffs(46, 0).addBox(-1.5F, -8F, 3F, 3, 3, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 1.082104F, 0F, 0F));
        root.addOrReplaceChild("juwa4", CubeListBuilder.create().texOffs(46, 0).addBox(-1.5F, -9.5F, 1.5F, 3, 3, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0.4886922F, 0F, 0F));
        root.addOrReplaceChild("juwa5", CubeListBuilder.create().texOffs(46, 0).addBox(-0.5F, -8.7F, 1.5F, 1, 3, 3), PartPose.offsetAndRotation(0F, 16F, 0F, 0.7853982F, 0.1396263F, -0.1396263F));
        root.addOrReplaceChild("juwa6", CubeListBuilder.create().texOffs(46, 0).addBox(-0.5F, -8.7F, 1.5F, 1, 3, 3), PartPose.offsetAndRotation(0F, 16F, 0F, 0.7853982F, -0.1396263F, 0.1396263F));
        root.addOrReplaceChild("dial1", CubeListBuilder.create().texOffs(33, 7).addBox(-3F, 3F, 1.5F, 6, 6, 1), PartPose.offsetAndRotation(0F, 16F, 0F, -0.8726646F, 0F, 0F));
        root.addOrReplaceChild("dial2", CubeListBuilder.create().texOffs(48, 7).addBox(-3F, 3F, 1.4F, 6, 6, 0), PartPose.offsetAndRotation(0F, 16F, 0F, -0.8726646F, 0F, 0F));
        root.addOrReplaceChild("tuwa1", CubeListBuilder.create().texOffs(33, 16).addBox(-5F, -3F, -1F, 2, 5, 2), PartPose.offset(0F, 15F, 0F));
        root.addOrReplaceChild("tuwa2", CubeListBuilder.create().texOffs(42, 16).addBox(-5.5F, 1F, -1.5F, 3, 1, 3), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("tuwa3", CubeListBuilder.create().texOffs(42, 21).addBox(-6F, 2F, -2F, 4, 1, 4), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        dodai1.render(pose, buf, light, overlay, r, g, b, a);
        dodai2.render(pose, buf, light, overlay, r, g, b, a);
        tou1.render(pose, buf, light, overlay, r, g, b, a);
        tou2.render(pose, buf, light, overlay, r, g, b, a);
        tou3.render(pose, buf, light, overlay, r, g, b, a);
        juwa1.render(pose, buf, light, overlay, r, g, b, a);
        juwa2.render(pose, buf, light, overlay, r, g, b, a);
        juwa3.render(pose, buf, light, overlay, r, g, b, a);
        juwa4.render(pose, buf, light, overlay, r, g, b, a);
        juwa5.render(pose, buf, light, overlay, r, g, b, a);
        juwa6.render(pose, buf, light, overlay, r, g, b, a);
        dial1.render(pose, buf, light, overlay, r, g, b, a);
        dial2.render(pose, buf, light, overlay, r, g, b, a);
        tuwa1.render(pose, buf, light, overlay, r, g, b, a);
        tuwa2.render(pose, buf, light, overlay, r, g, b, a);
        tuwa3.render(pose, buf, light, overlay, r, g, b, a);
    }
}