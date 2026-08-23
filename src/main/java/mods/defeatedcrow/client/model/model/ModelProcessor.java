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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELPROCESSOR) -> new ModelProcessor(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelProcessor {

    private final ModelPart root;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart base;
    private final ModelPart body;
    private final ModelPart brade1;
    private final ModelPart brade2;
    private final ModelPart glass;
    private final ModelPart top;

    public ModelProcessor(ModelPart root) {
        this.root = root;
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
        this.base = root.getChild("base");
        this.body = root.getChild("body");
        this.brade1 = root.getChild("brade1");
        this.brade2 = root.getChild("brade2");
        this.glass = root.getChild("glass");
        this.top = root.getChild("top");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 0F, -6F, 2, 1, 2), PartPose.offset(0F, 23F, 0F));
        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, -6F, 2, 1, 2), PartPose.offset(0F, 23F, 0F));
        PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 0F, 4F, 2, 1, 2), PartPose.offset(0F, 23F, 0F));
        PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, 4F, 2, 1, 2), PartPose.offset(0F, 23F, 0F));
        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, 0F, -6F, 12, 2, 12), PartPose.offset(0F, 21F, 0F));
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(-6.5F, 0F, -6.5F, 13, 5, 13), PartPose.offset(0F, 16F, 0F));
        PartDefinition brade1 = partdefinition.addOrReplaceChild("brade1", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-1F, 0F, -1F, 2, 3, 2), PartPose.offset(0F, 13F, 0F));
        PartDefinition brade2 = partdefinition.addOrReplaceChild("brade2", CubeListBuilder.create().texOffs(40, 0).mirror().addBox(-3F, 0F, -3F, 6, 0, 6), PartPose.offset(0F, 14.5F, 0F));
        PartDefinition glass = partdefinition.addOrReplaceChild("glass", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -5F, 10, 6, 10), PartPose.offset(0F, 10F, 0F));
        PartDefinition top = partdefinition.addOrReplaceChild("top", CubeListBuilder.create().texOffs(10, 23).mirror().addBox(-4F, 0F, -4F, 8, 1, 8), PartPose.offset(0F, 9F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            leg4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            body.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            brade1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            brade2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderGlass(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            glass.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
