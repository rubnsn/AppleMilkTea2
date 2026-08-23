package mods.defeatedcrow.client.entity.base;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * 1.20.1 migration: former ModelBase/ModelRenderer model, now LayerDefinition + ModelPart.
 * Geometry was mechanically preserved from the 1.7.10 original.
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELINNERSOUP) -> new ModelInnerSoup(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelInnerSoup {

    private final ModelPart root;
    private final ModelPart gu1;
    private final ModelPart gu2;
    private final ModelPart gu3;
    private final ModelPart gu4;
    private final ModelPart gu5;

    public ModelInnerSoup(ModelPart root) {
        this.root = root;
        this.gu1 = root.getChild("gu1");
        this.gu2 = root.getChild("gu2");
        this.gu3 = root.getChild("gu3");
        this.gu4 = root.getChild("gu4");
        this.gu5 = root.getChild("gu5");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition gu1 = partdefinition.addOrReplaceChild("gu1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0.5F, 1F, 2, 1, 1), PartPose.offsetAndRotation(0F, 18F, 0F, 0.2094395F, 2.094395F, 0F));
        PartDefinition gu2 = partdefinition.addOrReplaceChild("gu2", CubeListBuilder.create().texOffs(7, 0).mirror().addBox(-2F, -0.5F, 0.5F, 2, 1, 2), PartPose.offsetAndRotation(0F, 18F, 0F, -0.4363323F, -0.2094395F, -0.3490659F));
        PartDefinition gu3 = partdefinition.addOrReplaceChild("gu3", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(0.5F, 1.5F, 0F, 2, 1, 1), PartPose.offsetAndRotation(0F, 18F, 0F, 0F, -0.5235988F, -0.5235988F));
        PartDefinition gu4 = partdefinition.addOrReplaceChild("gu4", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(0F, -1.2F, -2.5F, 1, 1, 1), PartPose.offsetAndRotation(0F, 18F, 0F, 0.7853982F, 0.7853982F, 0F));
        PartDefinition gu5 = partdefinition.addOrReplaceChild("gu5", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(0F, 0F, 0F, 1, 1, 1), PartPose.offsetAndRotation(0F, 19F, 0F, 0.7853982F, 0.7853982F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    @Override
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            gu1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            gu2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            gu3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            gu4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            gu5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
