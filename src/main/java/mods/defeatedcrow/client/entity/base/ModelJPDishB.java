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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELJPDISHB) -> new ModelJPDishB(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelJPDishB {

    private final ModelPart root;
    private final ModelPart dishJP1;
    private final ModelPart dishJP2;
    private final ModelPart dishJP3;

    public ModelJPDishB(ModelPart root) {
        this.root = root;
        this.dishJP1 = root.getChild("dishJP1");
        this.dishJP2 = root.getChild("dishJP2");
        this.dishJP3 = root.getChild("dishJP3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition dishJP1 = partdefinition.addOrReplaceChild("dishJP1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 0F, -6F, 12, 1, 12), PartPose.offset(0F, 23F, 0F));
        PartDefinition dishJP2 = partdefinition.addOrReplaceChild("dishJP2", CubeListBuilder.create().texOffs(0, 13).mirror().addBox(-6F, 0F, -7F, 12, 2, 1), PartPose.offsetAndRotation(0F, 22F, 0F, 0F, -1.570796F, 0F));
        PartDefinition dishJP3 = partdefinition.addOrReplaceChild("dishJP3", CubeListBuilder.create().texOffs(0, 13).mirror().addBox(-6F, 0F, -7F, 12, 2, 1), PartPose.offsetAndRotation(0F, 22F, 0F, 0F, 1.570796F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    @Override
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            dishJP1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            dishJP2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            dishJP3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    @Override
    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
