package mods.defeatedcrow.client.model.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * 1.20.1 migration: former ModelBase/ModelRenderer model, now LayerDefinition + ModelPart.
 * Geometry was mechanically preserved from the 1.7.10 original.
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELALTDIAL) -> new ModelAltDial(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelAltDial {

    private final ModelPart root;
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
        this.root = root;
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
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition dodai1 = partdefinition.addOrReplaceChild("dodai1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 7F, -4F, 8, 1, 8), PartPose.offset(0F, 16F, 0F));
        PartDefinition dodai2 = partdefinition.addOrReplaceChild("dodai2", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(-3.5F, 6F, -3.5F, 7, 1, 7), PartPose.offset(0F, 16F, 0F));
        PartDefinition tou1 = partdefinition.addOrReplaceChild("tou1", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(-1F, -2F, -1F, 2, 7, 2), PartPose.offset(0F, 16F, 0F));
        PartDefinition tou2 = partdefinition.addOrReplaceChild("tou2", CubeListBuilder.create().texOffs(9, 19).mirror().addBox(-0.5F, -4F, -0.5F, 1, 10, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition tou3 = partdefinition.addOrReplaceChild("tou3", CubeListBuilder.create().texOffs(14, 19).mirror().addBox(-4F, -1.8F, -0.5F, 3, 1, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 0F, 0.0698132F));
        PartDefinition juwa1 = partdefinition.addOrReplaceChild("juwa1", CubeListBuilder.create().texOffs(25, 0).mirror().addBox(-0.5F, -3F, -4.5F, 1, 1, 2), PartPose.offsetAndRotation(0F, 16F, 0F, -0.7853982F, 0F, 0F));
        PartDefinition juwa2 = partdefinition.addOrReplaceChild("juwa2", CubeListBuilder.create().texOffs(33, 0).mirror().addBox(-2F, -5F, -6F, 4, 4, 2), PartPose.offsetAndRotation(0F, 16F, 0F, -0.7853982F, 0F, 0F));
        PartDefinition juwa3 = partdefinition.addOrReplaceChild("juwa3", CubeListBuilder.create().texOffs(46, 0).mirror().addBox(-1.5F, -8F, 3F, 3, 3, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 1.082104F, 0F, 0F));
        PartDefinition juwa4 = partdefinition.addOrReplaceChild("juwa4", CubeListBuilder.create().texOffs(46, 0).mirror().addBox(-1.5F, -9.5F, 1.5F, 3, 3, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0.4886922F, 0F, 0F));
        PartDefinition juwa5 = partdefinition.addOrReplaceChild("juwa5", CubeListBuilder.create().texOffs(46, 0).mirror().addBox(-0.5F, -8.7F, 1.5F, 1, 3, 3), PartPose.offsetAndRotation(0F, 16F, 0F, 0.7853982F, 0.1396263F, -0.1396263F));
        PartDefinition juwa6 = partdefinition.addOrReplaceChild("juwa6", CubeListBuilder.create().texOffs(46, 0).mirror().addBox(-0.5F, -8.7F, 1.5F, 1, 3, 3), PartPose.offsetAndRotation(0F, 16F, 0F, 0.7853982F, -0.1396263F, 0.1396263F));
        PartDefinition dial1 = partdefinition.addOrReplaceChild("dial1", CubeListBuilder.create().texOffs(33, 7).mirror().addBox(-3F, 3F, 1.5F, 6, 6, 1), PartPose.offsetAndRotation(0F, 16F, 0F, -0.8726646F, 0F, 0F));
        PartDefinition dial2 = partdefinition.addOrReplaceChild("dial2", CubeListBuilder.create().texOffs(48, 7).mirror().addBox(-3F, 3F, 1.4F, 6, 6, 0), PartPose.offsetAndRotation(0F, 16F, 0F, -0.8726646F, 0F, 0F));
        PartDefinition tuwa1 = partdefinition.addOrReplaceChild("tuwa1", CubeListBuilder.create().texOffs(33, 16).mirror().addBox(-5F, -3F, -1F, 2, 5, 2), PartPose.offset(0F, 15F, 0F));
        PartDefinition tuwa2 = partdefinition.addOrReplaceChild("tuwa2", CubeListBuilder.create().texOffs(42, 16).mirror().addBox(-5.5F, 1F, -1.5F, 3, 1, 3), PartPose.offset(0F, 16F, 0F));
        PartDefinition tuwa3 = partdefinition.addOrReplaceChild("tuwa3", CubeListBuilder.create().texOffs(42, 21).mirror().addBox(-6F, 2F, -2F, 4, 1, 4), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    @Override
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            dodai1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            dodai2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tou1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tou2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tou3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            juwa1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            juwa2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            juwa3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            juwa4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            juwa5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            juwa6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            dial1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            dial2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tuwa1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tuwa2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tuwa3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
