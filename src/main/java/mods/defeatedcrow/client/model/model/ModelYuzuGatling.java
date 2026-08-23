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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELYUZUGATLING) -> new ModelYuzuGatling(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelYuzuGatling {

    private final ModelPart root;
    private final ModelPart muz1;
    private final ModelPart muz2;
    private final ModelPart muz3;
    private final ModelPart syl1;
    private final ModelPart syl2;
    private final ModelPart bod1;
    private final ModelPart bod2;
    private final ModelPart bod3;
    private final ModelPart she1;
    private final ModelPart base1;
    private final ModelPart base2;

    public ModelYuzuGatling(ModelPart root) {
        this.root = root;
        this.muz1 = root.getChild("muz1");
        this.muz2 = root.getChild("muz2");
        this.muz3 = root.getChild("muz3");
        this.syl1 = root.getChild("syl1");
        this.syl2 = root.getChild("syl2");
        this.bod1 = root.getChild("bod1");
        this.bod2 = root.getChild("bod2");
        this.bod3 = root.getChild("bod3");
        this.she1 = root.getChild("she1");
        this.base1 = root.getChild("base1");
        this.base2 = root.getChild("base2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition muz1 = partdefinition.addOrReplaceChild("muz1", CubeListBuilder.create().texOffs(0, 4).mirror().addBox(3F, -0.5F, -0.5F, 12, 1, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        PartDefinition muz2 = partdefinition.addOrReplaceChild("muz2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3F, -2F, 0.5F, 17, 2, 2), PartPose.offsetAndRotation(0F, 17F, 0F, 0F, 1.570796F, 0F));
        PartDefinition muz3 = partdefinition.addOrReplaceChild("muz3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(3F, -1F, -2.5F, 17, 2, 2), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 1.570796F, 0F));
        PartDefinition syl1 = partdefinition.addOrReplaceChild("syl1", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-1.5F, -1F, -3F, 3, 2, 2), PartPose.offset(0F, 16F, 0F));
        PartDefinition syl2 = partdefinition.addOrReplaceChild("syl2", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(-2.5F, -1F, -1F, 5, 3, 3), PartPose.offset(0F, 16F, 0F));
        PartDefinition bod1 = partdefinition.addOrReplaceChild("bod1", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-3F, 0F, 2F, 6, 2, 8), PartPose.offset(0F, 16F, 0F));
        PartDefinition bod2 = partdefinition.addOrReplaceChild("bod2", CubeListBuilder.create().texOffs(38, 0).mirror().addBox(-2F, -0.5F, 2F, 4, 1, 5), PartPose.offset(0F, 16F, 0F));
        PartDefinition bod3 = partdefinition.addOrReplaceChild("bod3", CubeListBuilder.create().texOffs(54, 0).mirror().addBox(-1F, -2F, 2F, 2, 1, 2), PartPose.offset(0F, 17F, 0F));
        PartDefinition she1 = partdefinition.addOrReplaceChild("she1", CubeListBuilder.create().texOffs(32, 16).mirror().addBox(-3.5F, 0F, 7F, 7, 1, 8), PartPose.offsetAndRotation(0F, 16F, 0F, -0.2617994F, 0F, 0F));
        PartDefinition base1 = partdefinition.addOrReplaceChild("base1", CubeListBuilder.create().texOffs(0, 21).mirror().addBox(-3.5F, 4F, 1F, 7, 4, 7), PartPose.offset(0F, 16F, 0F));
        PartDefinition base2 = partdefinition.addOrReplaceChild("base2", CubeListBuilder.create().texOffs(32, 25).mirror().addBox(-2F, 3F, 2F, 4, 2, 5), PartPose.offset(0F, 15F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            muz1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            muz2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            muz3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            syl1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            syl2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bod1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bod2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bod3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            she1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
