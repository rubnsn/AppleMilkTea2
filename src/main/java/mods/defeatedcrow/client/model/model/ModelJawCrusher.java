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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELJAWCRUSHER) -> new ModelJawCrusher(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelJawCrusher {

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart body2;
    private final ModelPart body3;
    private final ModelPart body4;
    private final ModelPart body5;
    private final ModelPart blade;
    private final ModelPart lod;
    private final ModelPart gear1;
    private final ModelPart gear2;
    private final ModelPart base2;
    private final ModelPart motor;
    private final ModelPart lod2;
    private final ModelPart gear3;
    private final ModelPart belt1;
    private final ModelPart belt2;
    private final ModelPart base;

    public ModelJawCrusher(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.body2 = root.getChild("body2");
        this.body3 = root.getChild("body3");
        this.body4 = root.getChild("body4");
        this.body5 = root.getChild("body5");
        this.blade = root.getChild("blade");
        this.lod = root.getChild("lod");
        this.gear1 = root.getChild("gear1");
        this.gear2 = root.getChild("gear2");
        this.base2 = root.getChild("base2");
        this.motor = root.getChild("motor");
        this.lod2 = root.getChild("lod2");
        this.gear3 = root.getChild("gear3");
        this.belt1 = root.getChild("belt1");
        this.belt2 = root.getChild("belt2");
        this.base = root.getChild("base");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -1F, 10, 8, 4), PartPose.offset(0F, 15F, 0F));
        PartDefinition body2 = partdefinition.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(28, 0).mirror().addBox(-5F, 0F, 6F, 10, 8, 1), PartPose.offset(0F, 15F, 0F));
        PartDefinition body3 = partdefinition.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(32, 21).mirror().addBox(4F, 0F, 3F, 1, 8, 3), PartPose.offset(0F, 15F, 0F));
        PartDefinition body4 = partdefinition.addOrReplaceChild("body4", CubeListBuilder.create().texOffs(32, 21).mirror().addBox(-5F, 0F, 3F, 1, 8, 3), PartPose.offset(0F, 15F, 0F));
        PartDefinition body5 = partdefinition.addOrReplaceChild("body5", CubeListBuilder.create().texOffs(40, 20).mirror().addBox(-4F, 0F, -2F, 8, 8, 4), PartPose.offsetAndRotation(0F, 14.5F, 0F, 0.1396263F, 0F, 0F));
        PartDefinition blade = partdefinition.addOrReplaceChild("blade", CubeListBuilder.create().texOffs(16, 15).mirror().addBox(-4F, 0F, 3F, 8, 7, 1), PartPose.offsetAndRotation(0F, 16F, 0F, 0.1745329F, 0F, 0F));
        PartDefinition lod = partdefinition.addOrReplaceChild("lod", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(-6F, 0F, 0F, 12, 1, 1), PartPose.offset(0F, 16F, 0F));
        PartDefinition gear1 = partdefinition.addOrReplaceChild("gear1", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(6F, 0F, -2F, 1, 5, 5), PartPose.offset(0F, 14F, 0F));
        PartDefinition gear2 = partdefinition.addOrReplaceChild("gear2", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(-7F, 0F, -2F, 1, 5, 5), PartPose.offset(0F, 14F, 0F));
        PartDefinition base2 = partdefinition.addOrReplaceChild("base2", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-5F, 0F, -7F, 1, 1, 14), PartPose.offset(0F, 23F, 0F));
        PartDefinition motor = partdefinition.addOrReplaceChild("motor", CubeListBuilder.create().texOffs(38, 13).mirror().addBox(-5F, 0F, -7F, 10, 3, 3), PartPose.offset(0F, 20F, 0F));
        PartDefinition lod2 = partdefinition.addOrReplaceChild("lod2", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(5F, 0F, -6F, 1, 1, 1), PartPose.offset(0F, 21F, 0F));
        PartDefinition gear3 = partdefinition.addOrReplaceChild("gear3", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(6F, 0F, -7F, 1, 3, 3), PartPose.offset(0F, 20F, 0F));
        PartDefinition belt1 = partdefinition.addOrReplaceChild("belt1", CubeListBuilder.create().texOffs(43, 4).mirror().addBox(6F, 0F, -10F, 1, 0, 8), PartPose.offsetAndRotation(0F, 12.5F, 0F, 0.8726646F, 0F, 0F));
        PartDefinition belt2 = partdefinition.addOrReplaceChild("belt2", CubeListBuilder.create().texOffs(43, 4).mirror().addBox(6F, 0F, -5.5F, 1, 0, 8), PartPose.offsetAndRotation(0F, 20F, 0F, 0.6108652F, 0F, 0F));
        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(4F, 0F, -7F, 1, 1, 14), PartPose.offset(0F, 23F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, boolean active) {
            body.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            body2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            body3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            body4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            body5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            blade.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            lod.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            gear1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            gear2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            motor.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            lod2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            gear3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            belt1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            belt2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            base.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5, boolean b) {
        this.body.yRot = f3 / (180F / (float) Math.PI);
        this.body2.yRot = f3 / (180F / (float) Math.PI);
        this.body3.yRot = f3 / (180F / (float) Math.PI);
        this.body4.yRot = f3 / (180F / (float) Math.PI);
        this.body5.yRot = f3 / (180F / (float) Math.PI);
        this.blade.yRot = f3 / (180F / (float) Math.PI);
        this.lod.yRot = f3 / (180F / (float) Math.PI);
        this.lod2.yRot = f3 / (180F / (float) Math.PI);
        this.gear1.yRot = f3 / (180F / (float) Math.PI);
        this.gear2.yRot = f3 / (180F / (float) Math.PI);
        this.gear3.yRot = f3 / (180F / (float) Math.PI);
        this.base.yRot = f3 / (180F / (float) Math.PI);
        this.base2.yRot = f3 / (180F / (float) Math.PI);
        this.belt2.yRot = f3 / (180F / (float) Math.PI);
        this.belt1.yRot = f3 / (180F / (float) Math.PI);
        this.motor.yRot = f3 / (180F / (float) Math.PI);

        // 莉･荳九・豁ｯ霆翫・蝗櫁ｻ｢驛ｨ蛻・□縺後∬ｻｸ縺ｮ蝠城｡後〒諤昴≧繧医≧縺ｪ隕ｳ轤ｹ繧偵＠縺ｪ縺・◆繧∽ｿ晉蕗縲りｦ∵隼濶ｯ縲・        // if (b)
        // {
        // this.gear1.xRot += 1 / (180F / (float)Math.PI);
        // this.gear2.xRot += 1 / (180F / (float)Math.PI);
        // this.gear3.xRot += 1 / (180F / (float)Math.PI);
        // }
    }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
