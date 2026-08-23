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
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELICECREAM) -> new ModelIceCream(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelIceCream {

    private final ModelPart root;
    private final ModelPart dish1;
    private final ModelPart dish2;
    private final ModelPart dish3;
    private final ModelPart dish4;
    private final ModelPart dish5;
    private final ModelPart dish6;
    private final ModelPart dish7;
    private final ModelPart white;
    private final ModelPart pink;
    private final ModelPart orange;
    private final ModelPart yellow;
    private final ModelPart brown;
    private final ModelPart cocoa;
    private final ModelPart green;
    private final ModelPart berry;
    private final ModelPart lime;
    private final ModelPart red;
    private final ModelPart grape;
    private final ModelPart mint;
    private final ModelPart orange2;
    private final ModelPart soda;

    public ModelIceCream(ModelPart root) {
        this.root = root;
        this.dish1 = root.getChild("dish1");
        this.dish2 = root.getChild("dish2");
        this.dish3 = root.getChild("dish3");
        this.dish4 = root.getChild("dish4");
        this.dish5 = root.getChild("dish5");
        this.dish6 = root.getChild("dish6");
        this.dish7 = root.getChild("dish7");
        this.white = root.getChild("white");
        this.pink = root.getChild("pink");
        this.orange = root.getChild("orange");
        this.yellow = root.getChild("yellow");
        this.brown = root.getChild("brown");
        this.cocoa = root.getChild("cocoa");
        this.green = root.getChild("green");
        this.berry = root.getChild("berry");
        this.lime = root.getChild("lime");
        this.red = root.getChild("red");
        this.grape = root.getChild("grape");
        this.mint = root.getChild("mint");
        this.orange2 = root.getChild("orange2");
        this.soda = root.getChild("soda");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition dish1 = partdefinition.addOrReplaceChild("dish1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, -2F, 4, 1, 4), PartPose.offset(0F, 23F, 0F));
        PartDefinition dish2 = partdefinition.addOrReplaceChild("dish2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0F, -0.5F, 1, 3, 1), PartPose.offset(0F, 20F, 0F));
        PartDefinition dish3 = partdefinition.addOrReplaceChild("dish3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.5F, 0F, -2.5F, 5, 1, 5), PartPose.offset(0F, 19F, 0F));
        PartDefinition dish4 = partdefinition.addOrReplaceChild("dish4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, -3F, -3F, 6, 3, 1), PartPose.offsetAndRotation(0F, 19F, 0F, 0.5235988F, 0F, 0F));
        PartDefinition dish5 = partdefinition.addOrReplaceChild("dish5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, -3F, 2F, 6, 3, 1), PartPose.offsetAndRotation(0F, 19F, 0F, -0.5235988F, 0F, 0F));
        PartDefinition dish6 = partdefinition.addOrReplaceChild("dish6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, -3F, -3F, 1, 3, 6), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, 0F, -0.5235988F));
        PartDefinition dish7 = partdefinition.addOrReplaceChild("dish7", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(2F, -3F, -3F, 1, 3, 6), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, 0F, 0.5235988F));
        PartDefinition white = partdefinition.addOrReplaceChild("white", CubeListBuilder.create().texOffs(0, 9).mirror().addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition pink = partdefinition.addOrReplaceChild("pink", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition orange = partdefinition.addOrReplaceChild("orange", CubeListBuilder.create().texOffs(0, 23).mirror().addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition yellow = partdefinition.addOrReplaceChild("yellow", CubeListBuilder.create().texOffs(16, 9).mirror().addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition brown = partdefinition.addOrReplaceChild("brown", CubeListBuilder.create().texOffs(16, 16).mirror().addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition cocoa = partdefinition.addOrReplaceChild("cocoa", CubeListBuilder.create().texOffs(16, 23).mirror().addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition green = partdefinition.addOrReplaceChild("green", CubeListBuilder.create().texOffs(32, 9).mirror().addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition berry = partdefinition.addOrReplaceChild("berry", CubeListBuilder.create().texOffs(32, 16).mirror().addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition lime = partdefinition.addOrReplaceChild("lime", CubeListBuilder.create().texOffs(32, 23).mirror().addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition red = partdefinition.addOrReplaceChild("red", CubeListBuilder.create().texOffs(48, 9).mirror().addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition grape = partdefinition.addOrReplaceChild("grape", CubeListBuilder.create().texOffs(48, 16).mirror().addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition mint = partdefinition.addOrReplaceChild("mint", CubeListBuilder.create().texOffs(48, 23).mirror().addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition orange2 = partdefinition.addOrReplaceChild("orange2", CubeListBuilder.create().texOffs(32, 2).mirror().addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        PartDefinition soda = partdefinition.addOrReplaceChild("soda", CubeListBuilder.create().texOffs(48, 2).mirror().addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0) {
        if (b0 == 0)             white.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 1)             orange.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 2)             green.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 3)             cocoa.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 4)             brown.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 5)             pink.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 6)             yellow.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 7)             lime.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 8)             red.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 9)             berry.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 10)             grape.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 11)             mint.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 12)             orange2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        else if (b0 == 13)             soda.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void renderClear(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0) {
            dish1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            dish2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            dish3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            dish4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            dish5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            dish6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            dish7.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5) {
        }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
