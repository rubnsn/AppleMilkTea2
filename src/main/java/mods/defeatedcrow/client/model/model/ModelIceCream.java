package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelIceCream {
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
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("dish1", CubeListBuilder.create().texOffs(0, 0).addBox(-2F, 0F, -2F, 4, 1, 4), PartPose.offset(0F, 23F, 0F));
        root.addOrReplaceChild("dish2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0F, -0.5F, 1, 3, 1), PartPose.offset(0F, 20F, 0F));
        root.addOrReplaceChild("dish3", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, 0F, -2.5F, 5, 1, 5), PartPose.offset(0F, 19F, 0F));
        root.addOrReplaceChild("dish4", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, -3F, -3F, 6, 3, 1), PartPose.offsetAndRotation(0F, 19F, 0F, 0.5235988F, 0F, 0F));
        root.addOrReplaceChild("dish5", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, -3F, 2F, 6, 3, 1), PartPose.offsetAndRotation(0F, 19F, 0F, -0.5235988F, 0F, 0F));
        root.addOrReplaceChild("dish6", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, -3F, -3F, 1, 3, 6), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, 0F, -0.5235988F));
        root.addOrReplaceChild("dish7", CubeListBuilder.create().texOffs(0, 0).addBox(2F, -3F, -3F, 1, 3, 6), PartPose.offsetAndRotation(0F, 19F, 0F, 0F, 0F, 0.5235988F));
        root.addOrReplaceChild("white", CubeListBuilder.create().texOffs(0, 9).addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("pink", CubeListBuilder.create().texOffs(0, 16).addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("orange", CubeListBuilder.create().texOffs(0, 23).addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("yellow", CubeListBuilder.create().texOffs(16, 9).addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("brown", CubeListBuilder.create().texOffs(16, 16).addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("cocoa", CubeListBuilder.create().texOffs(16, 23).addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("green", CubeListBuilder.create().texOffs(32, 9).addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("berry", CubeListBuilder.create().texOffs(32, 16).addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("lime", CubeListBuilder.create().texOffs(32, 23).addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("red", CubeListBuilder.create().texOffs(48, 9).addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("grape", CubeListBuilder.create().texOffs(48, 16).addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("mint", CubeListBuilder.create().texOffs(48, 23).addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("orange2", CubeListBuilder.create().texOffs(32, 2).addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        root.addOrReplaceChild("soda", CubeListBuilder.create().texOffs(48, 2).addBox(-2F, 0F, -2F, 4, 3, 4), PartPose.offset(0F, 16F, 0F));
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void renderToBuffer(com.mojang.blaze3d.vertex.PoseStack pose, com.mojang.blaze3d.vertex.VertexConsumer buf, int light, int overlay, float r, float g, float b, float a) {
        dish1.render(pose, buf, light, overlay, r, g, b, a);
        dish2.render(pose, buf, light, overlay, r, g, b, a);
        dish3.render(pose, buf, light, overlay, r, g, b, a);
        dish4.render(pose, buf, light, overlay, r, g, b, a);
        dish5.render(pose, buf, light, overlay, r, g, b, a);
        dish6.render(pose, buf, light, overlay, r, g, b, a);
        dish7.render(pose, buf, light, overlay, r, g, b, a);
        white.render(pose, buf, light, overlay, r, g, b, a);
        pink.render(pose, buf, light, overlay, r, g, b, a);
        orange.render(pose, buf, light, overlay, r, g, b, a);
        yellow.render(pose, buf, light, overlay, r, g, b, a);
        brown.render(pose, buf, light, overlay, r, g, b, a);
        cocoa.render(pose, buf, light, overlay, r, g, b, a);
        green.render(pose, buf, light, overlay, r, g, b, a);
        berry.render(pose, buf, light, overlay, r, g, b, a);
        lime.render(pose, buf, light, overlay, r, g, b, a);
        red.render(pose, buf, light, overlay, r, g, b, a);
        grape.render(pose, buf, light, overlay, r, g, b, a);
        mint.render(pose, buf, light, overlay, r, g, b, a);
        orange2.render(pose, buf, light, overlay, r, g, b, a);
        soda.render(pose, buf, light, overlay, r, g, b, a);
    }
}