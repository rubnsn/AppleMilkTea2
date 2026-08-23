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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELROTARYDIAL) -> new ModelRotaryDial(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelRotaryDial {

    private final ModelPart root;
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
        this.root = root;
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
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.5F, 0F, -5F, 9, 4, 10), PartPose.offset(0F, 20F, 0F));
        PartDefinition middle = partdefinition.addOrReplaceChild("middle", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.5F, 0F, -5F, 9, 4, 9), PartPose.offsetAndRotation(0F, 18F, 0F, 0.418879F, 0F, 0F));
        PartDefinition back = partdefinition.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4.5F, 0F, 3F, 9, 7, 4), PartPose.offset(0F, 17F, 0F));
        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(2F, 0F, 4F, 1, 1, 2), PartPose.offset(0F, 16F, 0F));
        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, 4F, 1, 1, 2), PartPose.offset(0F, 16F, 0F));
        PartDefinition handle = partdefinition.addOrReplaceChild("handle", CubeListBuilder.create().texOffs(0, 15).mirror().addBox(-5F, 0F, 4F, 10, 1, 2), PartPose.offset(0F, 15F, 0F));
        PartDefinition handle2 = partdefinition.addOrReplaceChild("handle2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(5F, 0F, 3F, 3, 4, 4), PartPose.offset(0F, 15F, 0F));
        PartDefinition handle3 = partdefinition.addOrReplaceChild("handle3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-8F, 0F, 3F, 3, 4, 4), PartPose.offset(0F, 15F, 0F));
        PartDefinition plate = partdefinition.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(-4F, 0.5F, -5F, 8, 0, 8), PartPose.offsetAndRotation(0F, 17F, 0F, 0.418879F, 0F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
        byte par8) {
            bottom.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            middle.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            back.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handle.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handle2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            handle3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            plate.render(poseStack, vertexConsumer, packedLight, packedOverlay);

    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        this.bottom.yRot = f3 / (180F / (float) Math.PI);
        this.middle.yRot = f3 / (180F / (float) Math.PI);
        this.back.yRot = f3 / (180F / (float) Math.PI);
        this.leg1.yRot = f3 / (180F / (float) Math.PI);
        this.leg2.yRot = f3 / (180F / (float) Math.PI);
        this.handle.yRot = f3 / (180F / (float) Math.PI);
        this.handle2.yRot = f3 / (180F / (float) Math.PI);
        this.handle3.yRot = f3 / (180F / (float) Math.PI);
        this.plate.yRot = f3 / (180F / (float) Math.PI);
    }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
