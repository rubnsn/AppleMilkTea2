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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELBREADALT) -> new ModelBreadAlt(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelBreadAlt {

    private final ModelPart root;
    private final ModelPart bread1;
    private final ModelPart bread2;
    private final ModelPart bread3;
    private final ModelPart bread4;
    private final ModelPart bread5;

    public ModelBreadAlt(ModelPart root) {
        this.root = root;
        this.bread1 = root.getChild("bread1");
        this.bread2 = root.getChild("bread2");
        this.bread3 = root.getChild("bread3");
        this.bread4 = root.getChild("bread4");
        this.bread5 = root.getChild("bread5");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bread1 = partdefinition.addOrReplaceChild("bread1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, -10F, 1.5F, 4, 16, 3), PartPose.offsetAndRotation(0F, 16F, 0F, -0.2617994F, 0.3490659F, 0F));
        PartDefinition bread2 = partdefinition.addOrReplaceChild("bread2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, -10F, 1.5F, 4, 16, 3), PartPose.offsetAndRotation(0F, 16F, 0F, -0.3396263F, -1.308997F, 0.2094395F));
        PartDefinition bread3 = partdefinition.addOrReplaceChild("bread3", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(-2F, -8F, -2F, 3, 14, 3), PartPose.offsetAndRotation(0F, 16F, 0F, -0.0872665F, -0.122173F, 0F));
        PartDefinition bread4 = partdefinition.addOrReplaceChild("bread4", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(0F, -8F, 1.5F, 3, 14, 3), PartPose.offsetAndRotation(0F, 16F, 0F, -0.2792527F, 1.919862F, 0.0872665F));
        PartDefinition bread5 = partdefinition.addOrReplaceChild("bread5", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(-0.5F, -7F, -5F, 3, 14, 3), PartPose.offsetAndRotation(0F, 16F, 0F, 0.0698132F, 1.047198F, 0F));
        return LayerDefinition.create(meshdefinition, 32, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        if (f3 > 0)// 0-5
        {
            bread1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            if (f3 > 1) {
            bread2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                if (f3 > 2) {
            bread3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                    if (f3 > 3) {
            bread5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                        if (f3 > 4) {
            bread4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
                        }
                    }
                }
            }
        }
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
