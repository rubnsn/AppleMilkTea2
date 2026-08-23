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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELCANISTER) -> new ModelCanister(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelCanister {

    private final ModelPart root;
    private final ModelPart bottomC;
    private final ModelPart side1C;
    private final ModelPart side2C;
    private final ModelPart side3C;
    private final ModelPart side4C;
    private final ModelPart top1C;
    private final ModelPart top2C;
    private final ModelPart woodcap;
    private final ModelPart contents;

    public ModelCanister(ModelPart root) {
        this.root = root;
        this.bottomC = root.getChild("bottomC");
        this.side1C = root.getChild("side1C");
        this.side2C = root.getChild("side2C");
        this.side3C = root.getChild("side3C");
        this.side4C = root.getChild("side4C");
        this.top1C = root.getChild("top1C");
        this.top2C = root.getChild("top2C");
        this.woodcap = root.getChild("woodcap");
        this.contents = root.getChild("contents");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bottomC = partdefinition.addOrReplaceChild("bottomC", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -5F, 10, 2, 10), PartPose.offset(0F, 22F, 0F));
        PartDefinition side1C = partdefinition.addOrReplaceChild("side1C", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -5F, 10, 10, 1), PartPose.offset(0F, 12F, 0F));
        PartDefinition side2C = partdefinition.addOrReplaceChild("side2C", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, 4F, 10, 10, 1), PartPose.offset(0F, 12F, 0F));
        PartDefinition side3C = partdefinition.addOrReplaceChild("side3C", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -4F, 1, 10, 8), PartPose.offset(0F, 12F, 0F));
        PartDefinition side4C = partdefinition.addOrReplaceChild("side4C", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, -4F, 1, 10, 8), PartPose.offset(0F, 12F, 0F));
        PartDefinition top1C = partdefinition.addOrReplaceChild("top1C", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -4F, 8, 2, 8), PartPose.offset(0F, 11F, 0F));
        PartDefinition top2C = partdefinition.addOrReplaceChild("top2C", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -3F, 6, 1, 6), PartPose.offset(0F, 10F, 0F));
        PartDefinition woodcap = partdefinition.addOrReplaceChild("woodcap", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -4F, 8, 1, 8), PartPose.offset(0F, 9F, 0F));
        PartDefinition contents = partdefinition.addOrReplaceChild("contents", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-4F, 0F, -4F, 8, 8, 8), PartPose.offset(0F, 14F, 0F));
        return LayerDefinition.create(meshdefinition, 32, 32);
    }


    public void renderCanister(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            bottomC.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side1C.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side2C.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side3C.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side4C.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top1C.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            top2C.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderContents(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            contents.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderCanisterCap(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            woodcap.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
