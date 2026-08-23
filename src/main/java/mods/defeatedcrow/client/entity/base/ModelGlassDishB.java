package mods.defeatedcrow.client.entity.base;

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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELGLASSDISHB) -> new ModelGlassDishB(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelGlassDishB {

    private final ModelPart root;
    private final ModelPart bottom1;
    private final ModelPart sideL;
    private final ModelPart sideR;
    private final ModelPart sideF;
    private final ModelPart sideB;

    public ModelGlassDishB(ModelPart root) {
        this.root = root;
        this.bottom1 = root.getChild("bottom1");
        this.sideL = root.getChild("sideL");
        this.sideR = root.getChild("sideR");
        this.sideF = root.getChild("sideF");
        this.sideB = root.getChild("sideB");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bottom1 = partdefinition.addOrReplaceChild("bottom1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 4, 2, 4), PartPose.offset(-2F, 22F, -2F));
        PartDefinition sideL = partdefinition.addOrReplaceChild("sideL", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(3F, 0F, -3F, 1, 4, 6), PartPose.offsetAndRotation(0F, 18F, 0F, 0F, 0F, 0.2617994F));
        PartDefinition sideR = partdefinition.addOrReplaceChild("sideR", CubeListBuilder.create().texOffs(14, 10).mirror().addBox(-4F, 0F, -3F, 1, 4, 6), PartPose.offsetAndRotation(0F, 18F, 0F, 0F, 0F, -0.2617994F));
        PartDefinition sideF = partdefinition.addOrReplaceChild("sideF", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-3F, 0F, -4F, 6, 4, 1), PartPose.offsetAndRotation(0F, 18F, 0F, 0.2617994F, 0F, 0F));
        PartDefinition sideB = partdefinition.addOrReplaceChild("sideB", CubeListBuilder.create().texOffs(0, 25).mirror().addBox(-3F, 0F, 3F, 6, 4, 1), PartPose.offsetAndRotation(0F, 18F, 0F, -0.2617994F, 0F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    @Override
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            bottom1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideL.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideR.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideF.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideB.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    @Override
    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
