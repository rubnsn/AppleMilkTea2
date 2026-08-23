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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELWHITEDISHB) -> new ModelWhiteDishB(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelWhiteDishB {

    private final ModelPart root;
    private final ModelPart base;
    private final ModelPart b;
    private final ModelPart l;
    private final ModelPart ff;
    private final ModelPart r;
    private final ModelPart bl;
    private final ModelPart fl;
    private final ModelPart fr;
    private final ModelPart br;

    public ModelWhiteDishB(ModelPart root) {
        this.root = root;
        this.base = root.getChild("base");
        this.b = root.getChild("b");
        this.l = root.getChild("l");
        this.ff = root.getChild("ff");
        this.r = root.getChild("r");
        this.bl = root.getChild("bl");
        this.fl = root.getChild("fl");
        this.fr = root.getChild("fr");
        this.br = root.getChild("br");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        float f = 3.141593F / 360.0F;

        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 1F, -3F, 6, 1, 6), PartPose.offset(0F, 22F, 0F));
        PartDefinition b = partdefinition.addOrReplaceChild("b", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-3F, 1.3F, 2.5F, 6, 1, 4), PartPose.offsetAndRotation(0F, 22F, 0F, 0.1396263F, 0F, 0F));
        PartDefinition l = partdefinition.addOrReplaceChild("l", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-3F, 1.2F, 2.5F, 6, 1, 4), PartPose.offsetAndRotation(0F, 22F, 0F, 0.1396263F, 1.570796F, 0F));
        PartDefinition ff = partdefinition.addOrReplaceChild("ff", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-3F, 1.2F, 2.5F, 6, 1, 4), PartPose.offsetAndRotation(0F, 22F, 0F, 0.1396263F, 3.141593F, 0F));
        PartDefinition r = partdefinition.addOrReplaceChild("r", CubeListBuilder.create().texOffs(0, 8).mirror().addBox(-3F, 1.3F, 2.5F, 6, 1, 4), PartPose.offsetAndRotation(0F, 22F, 0F, 0.1396263F, 4.712389F, 0F));
        PartDefinition bl = partdefinition.addOrReplaceChild("bl", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(2.5F, 1.5F, 2.5F, 4, 1, 4), PartPose.offsetAndRotation(0F, 22F, 0F, 0.1396263F, 0F, -0.1396263F));
        PartDefinition fl = partdefinition.addOrReplaceChild("fl", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(2.5F, 0.4F, 2.5F, 4, 1, 4), PartPose.offsetAndRotation(0F, 22F, -9F, -12.0F * f, f, -12.0F * f));
        PartDefinition fr = partdefinition.addOrReplaceChild("fr", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(2.5F, 1.5F, 2.5F, 4, 1, 4), PartPose.offsetAndRotation(0F, 22F, 0F, 0.1396263F, 3.141593F, 0.1396263F));
        PartDefinition br = partdefinition.addOrReplaceChild("br", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(2.5F, 0.4F, 2.5F, 4, 1, 4), PartPose.offsetAndRotation(-9F, 22F, 0F, 12.0F * f, f, 12.0F * f));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    @Override
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
            base.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            b.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            l.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            ff.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            r.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            bl.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            fl.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            fr.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            br.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    @Override
    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
}
