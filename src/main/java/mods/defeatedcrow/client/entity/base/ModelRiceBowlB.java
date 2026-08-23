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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELRICEBOWLB) -> new ModelRiceBowlB(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelRiceBowlB {

    private final ModelPart root;
    private final ModelPart bottom1;
    private final ModelPart bottom2;
    private final ModelPart sideL2;
    private final ModelPart sideR2;
    private final ModelPart sideF2;
    private final ModelPart sideB2;

    public ModelRiceBowlB(ModelPart root) {
        this.root = root;
        this.bottom1 = root.getChild("bottom1");
        this.bottom2 = root.getChild("bottom2");
        this.sideL2 = root.getChild("sideL2");
        this.sideR2 = root.getChild("sideR2");
        this.sideF2 = root.getChild("sideF2");
        this.sideB2 = root.getChild("sideB2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bottom1 = partdefinition.addOrReplaceChild("bottom1", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(0F, 0F, 0F, 4, 1, 4), PartPose.offset(-2F, 23F, -2F));
        PartDefinition bottom2 = partdefinition.addOrReplaceChild("bottom2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0F, 8, 1, 8), PartPose.offset(-4F, 22F, -4F));
        PartDefinition sideL2 = partdefinition.addOrReplaceChild("sideL2", CubeListBuilder.create().texOffs(0, 10).mirror().addBox(0F, 0F, 0F, 1, 3, 6), PartPose.offset(3F, 19F, -3F));
        PartDefinition sideR2 = partdefinition.addOrReplaceChild("sideR2", CubeListBuilder.create().texOffs(14, 10).mirror().addBox(0F, 0F, 0F, 1, 3, 6), PartPose.offset(-4F, 19F, -3F));
        PartDefinition sideF2 = partdefinition.addOrReplaceChild("sideF2", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(0F, 0F, 0F, 8, 3, 1), PartPose.offset(-4F, 19F, -4F));
        PartDefinition sideB2 = partdefinition.addOrReplaceChild("sideB2", CubeListBuilder.create().texOffs(0, 23).mirror().addBox(0F, 0F, 0F, 8, 3, 1), PartPose.offset(-4F, 19F, 3F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            bottom1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bottom2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideL2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideR2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideF2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            sideB2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
