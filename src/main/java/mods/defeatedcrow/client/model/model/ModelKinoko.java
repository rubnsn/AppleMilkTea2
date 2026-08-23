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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELKINOKO) -> new ModelKinoko(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelKinoko {

    private final ModelPart root;
    private final ModelPart base;
    private final ModelPart base2;
    private final ModelPart head1;
    private final ModelPart head2;

    public ModelKinoko(ModelPart root) {
        this.root = root;
        this.base = root.getChild("base");
        this.base2 = root.getChild("base2");
        this.head1 = root.getChild("head1");
        this.head2 = root.getChild("head2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -3F, 6, 2, 6), PartPose.offset(0F, 22F, 0F));
        PartDefinition base2 = partdefinition.addOrReplaceChild("base2", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-2F, 0F, -2F, 4, 5, 4), PartPose.offset(0F, 17F, 0F));
        PartDefinition head1 = partdefinition.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-6F, 0F, -6F, 12, 3, 12), PartPose.offset(0F, 14F, 0F));
        PartDefinition head2 = partdefinition.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(4, 19).mirror().addBox(-5F, 0F, -5F, 10, 1, 10), PartPose.offset(0F, 13F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            base.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            head1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            head2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
