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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELYUZUBULLET) -> new ModelYuzuBullet(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelYuzuBullet {

    private final ModelPart root;
    private final ModelPart Shape1;

    public ModelYuzuBullet(ModelPart root) {
        this.root = root;
        this.Shape1 = root.getChild("Shape1");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Shape1 = partdefinition.addOrReplaceChild("Shape1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, -1F, -1.5F, 3, 3, 3), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(meshdefinition, 32, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            Shape1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
