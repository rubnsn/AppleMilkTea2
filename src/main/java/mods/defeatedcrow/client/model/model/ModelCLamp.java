package mods.defeatedcrow.client.model.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * 1.20.1 migration: former ModelBase/ModelRenderer model, now LayerDefinition + ModelPart.
 * Geometry was mechanically preserved from the 1.7.10 original.
 * Usage: bakeLayer(ModEntityRenderers.MODEL_MODELCLAMP) -> new ModelCLamp(modelPart).
 * If this model has a setupAnim(...) method, call it before render() to apply part rotations.
 */
public class ModelCLamp {

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart ear1;
    private final ModelPart ear2;
    private final ModelPart wing1;
    private final ModelPart wing2;
    private final ModelPart tail1;
    private final ModelPart tail2;
    private final ModelPart core1;
    private final ModelPart core2;
    private final ModelPart sphere;
    private final ModelPart sphere2;
    private final ModelPart arm1;
    private final ModelPart arm2;
    private final ModelPart arm3;
    private final ModelPart blade1;
    private final ModelPart blade2;
    private final ModelPart blade3;
    private final ModelPart blade21;
    private final ModelPart blade22;
    private final ModelPart blade23;
    private final ModelPart side1;
    private final ModelPart side2;
    private final ModelPart side3;
    private final ModelPart side4;
    private final ModelPart inner1;
    private final ModelPart inner2;
    private final ModelPart barB1;
    private final ModelPart barB2;
    private final ModelPart barB3;
    private final ModelPart barB4;
    private final ModelPart barB5;
    private final ModelPart barB6;
    private final ModelPart barF1;
    private final ModelPart barF2;
    private final ModelPart barF3;
    private final ModelPart barF4;
    private final ModelPart barF5;
    private final ModelPart barF6;
    private final ModelPart barR1;
    private final ModelPart barR2;
    private final ModelPart barR3;
    private final ModelPart barR4;
    private final ModelPart barL1;
    private final ModelPart barL2;
    private final ModelPart barL3;
    private final ModelPart barL4;
    private final ModelPart head1;
    private final ModelPart head2;
    private final ModelPart head3;
    private final ModelPart head4;
    private final ModelPart cover1;
    private final ModelPart head6;
    private final ModelPart head7;
    private final ModelPart head8;
    private final ModelPart head9;
    private final ModelPart cover2;
    private final ModelPart glow1;
    private final ModelPart glow2;
    private final ModelPart top;
    private final ModelPart lod1a;
    private final ModelPart lod1b;
    private final ModelPart lod1c;
    private final ModelPart lod2a;
    private final ModelPart lod2b;
    private final ModelPart lod2c;
    private final ModelPart lod3a;
    private final ModelPart lod3b;
    private final ModelPart lod3c;
    private final ModelPart lod4a;
    private final ModelPart lod4b;
    private final ModelPart lod4c;
    private final ModelPart wingb1;
    private final ModelPart wingb2;

    public ModelCLamp(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.ear1 = root.getChild("ear1");
        this.ear2 = root.getChild("ear2");
        this.wing1 = root.getChild("wing1");
        this.wing2 = root.getChild("wing2");
        this.tail1 = root.getChild("tail1");
        this.tail2 = root.getChild("tail2");
        this.core1 = root.getChild("core1");
        this.core2 = root.getChild("core2");
        this.sphere = root.getChild("sphere");
        this.sphere2 = root.getChild("sphere2");
        this.arm1 = root.getChild("arm1");
        this.arm2 = root.getChild("arm2");
        this.arm3 = root.getChild("arm3");
        this.blade1 = root.getChild("blade1");
        this.blade2 = root.getChild("blade2");
        this.blade3 = root.getChild("blade3");
        this.blade21 = root.getChild("blade21");
        this.blade22 = root.getChild("blade22");
        this.blade23 = root.getChild("blade23");
        this.side1 = root.getChild("side1");
        this.side2 = root.getChild("side2");
        this.side3 = root.getChild("side3");
        this.side4 = root.getChild("side4");
        this.inner1 = root.getChild("inner1");
        this.inner2 = root.getChild("inner2");
        this.barB1 = root.getChild("barB1");
        this.barB2 = root.getChild("barB2");
        this.barB3 = root.getChild("barB3");
        this.barB4 = root.getChild("barB4");
        this.barB5 = root.getChild("barB5");
        this.barB6 = root.getChild("barB6");
        this.barF1 = root.getChild("barF1");
        this.barF2 = root.getChild("barF2");
        this.barF3 = root.getChild("barF3");
        this.barF4 = root.getChild("barF4");
        this.barF5 = root.getChild("barF5");
        this.barF6 = root.getChild("barF6");
        this.barR1 = root.getChild("barR1");
        this.barR2 = root.getChild("barR2");
        this.barR3 = root.getChild("barR3");
        this.barR4 = root.getChild("barR4");
        this.barL1 = root.getChild("barL1");
        this.barL2 = root.getChild("barL2");
        this.barL3 = root.getChild("barL3");
        this.barL4 = root.getChild("barL4");
        this.head1 = root.getChild("head1");
        this.head2 = root.getChild("head2");
        this.head3 = root.getChild("head3");
        this.head4 = root.getChild("head4");
        this.cover1 = root.getChild("cover1");
        this.head6 = root.getChild("head6");
        this.head7 = root.getChild("head7");
        this.head8 = root.getChild("head8");
        this.head9 = root.getChild("head9");
        this.cover2 = root.getChild("cover2");
        this.glow1 = root.getChild("glow1");
        this.glow2 = root.getChild("glow2");
        this.top = root.getChild("top");
        this.lod1a = root.getChild("lod1a");
        this.lod1b = root.getChild("lod1b");
        this.lod1c = root.getChild("lod1c");
        this.lod2a = root.getChild("lod2a");
        this.lod2b = root.getChild("lod2b");
        this.lod2c = root.getChild("lod2c");
        this.lod3a = root.getChild("lod3a");
        this.lod3b = root.getChild("lod3b");
        this.lod3c = root.getChild("lod3c");
        this.lod4a = root.getChild("lod4a");
        this.lod4b = root.getChild("lod4b");
        this.lod4c = root.getChild("lod4c");
        this.wingb1 = root.getChild("wingb1");
        this.wingb2 = root.getChild("wingb2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.5F, -3F, -2F, 5, 6, 5), PartPose.offset(0F, 14F, 0F));
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, -2F, -1.5F, 4, 3, 4), PartPose.offset(0F, 10F, 0F));
        PartDefinition ear1 = partdefinition.addOrReplaceChild("ear1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1F, 0F, 0.5F, 1, 1, 1), PartPose.offsetAndRotation(0F, 8.3F, 0F, 0.7853982F, -1.570796F, 0F));
        PartDefinition ear2 = partdefinition.addOrReplaceChild("ear2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, 0F, 0.5F, 1, 1, 1), PartPose.offsetAndRotation(0F, 8.3F, 0F, 0.7853982F, 1.570796F, 0F));
        PartDefinition wing1 = partdefinition.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, -1.5F, 2.5F, 1, 3, 6), PartPose.offsetAndRotation(0F, 15F, 0F, 0.4363323F, 1.570796F, 0F));
        PartDefinition wing2 = partdefinition.addOrReplaceChild("wing2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0F, -1.5F, 2.5F, 1, 3, 6), PartPose.offsetAndRotation(0F, 15F, 0F, 0.4363323F, -1.570796F, 0F));
        PartDefinition tail1 = partdefinition.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, -1.5F, 4, 2, 4), PartPose.offset(0F, 17F, 0F));
        PartDefinition tail2 = partdefinition.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.5F, 0F, -1F, 3, 2, 2), PartPose.offset(0F, 19F, 0F));
        PartDefinition core1 = partdefinition.addOrReplaceChild("core1", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-1F, -1F, -1F, 2, 2, 2), PartPose.offsetAndRotation(0F, 14F, 0F, 0F, 0F, -0.7853982F));
        PartDefinition core2 = partdefinition.addOrReplaceChild("core2", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(-1.5F, -0.5F, -1F, 3, 2, 2), PartPose.offset(0F, 9F, 0F));
        PartDefinition sphere = partdefinition.addOrReplaceChild("sphere", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, -3F, -3F, 6, 6, 6), PartPose.offset(0F, 14F, 0F));
        PartDefinition sphere2 = partdefinition.addOrReplaceChild("sphere2", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-2F, -2F, -2F, 4, 4, 4), PartPose.offset(0F, 14F, 0F));
        PartDefinition arm1 = partdefinition.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(0, 13).mirror().addBox(-5F, -1F, -1F, 2, 2, 2), PartPose.offsetAndRotation(0F, 13F, -0.5F, 0F, 0.6981317F, 0.2617994F));
        PartDefinition arm2 = partdefinition.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(0, 13).mirror().addBox(3F, -1F, -1F, 2, 2, 2), PartPose.offsetAndRotation(0F, 13F, -0.5F, 0F, -0.6981317F, -0.2617994F));
        PartDefinition arm3 = partdefinition.addOrReplaceChild("arm3", CubeListBuilder.create().texOffs(0, 13).mirror().addBox(-1F, -1F, -4F, 2, 2, 2), PartPose.offsetAndRotation(0F, 12.5F, 0F, -0.2617994F, 0F, 0F));
        PartDefinition blade1 = partdefinition.addOrReplaceChild("blade1", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-0.5F, 0F, -4.5F, 1, 10, 2), PartPose.offsetAndRotation(0F, 11F, 0F, -0.3490659F, 0F, 0F));
        PartDefinition blade2 = partdefinition.addOrReplaceChild("blade2", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-0.5F, 0F, -5.5F, 1, 10, 2), PartPose.offsetAndRotation(0F, 11.5F, -0.2F, -0.3490659F, 2.234021F, 0F));
        PartDefinition blade3 = partdefinition.addOrReplaceChild("blade3", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-0.5F, 0F, -5.5F, 1, 10, 2), PartPose.offsetAndRotation(0F, 11.5F, -0.2F, -0.3490659F, -2.234021F, 0F));
        PartDefinition blade21 = partdefinition.addOrReplaceChild("blade21", CubeListBuilder.create().texOffs(14, 13).mirror().addBox(-0.5F, 0F, -8.7F, 1, 1, 3), PartPose.offsetAndRotation(0F, 23F, 0F, -0.5410521F, 0F, 0F));
        PartDefinition blade22 = partdefinition.addOrReplaceChild("blade22", CubeListBuilder.create().texOffs(14, 13).mirror().addBox(-0.5F, 0F, -9.5F, 1, 1, 3), PartPose.offsetAndRotation(0F, 23.5F, -0.2F, -0.5410521F, 2.234021F, 0F));
        PartDefinition blade23 = partdefinition.addOrReplaceChild("blade23", CubeListBuilder.create().texOffs(14, 13).mirror().addBox(-0.5F, 0F, -9.5F, 1, 1, 3), PartPose.offsetAndRotation(0F, 23.5F, -0.2F, -0.5410521F, -2.234021F, 0F));
        PartDefinition side1 = partdefinition.addOrReplaceChild("side1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, -2F, -6F, 2, 14, 2), PartPose.offset(0F, 10F, 0F));
        PartDefinition side2 = partdefinition.addOrReplaceChild("side2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, -2F, -6F, 2, 14, 2), PartPose.offset(0F, 10F, 0F));
        PartDefinition side3 = partdefinition.addOrReplaceChild("side3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6F, -2F, 4F, 2, 14, 2), PartPose.offset(0F, 10F, 0F));
        PartDefinition side4 = partdefinition.addOrReplaceChild("side4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, -2F, 4F, 2, 14, 2), PartPose.offset(0F, 10F, 0F));
        PartDefinition inner1 = partdefinition.addOrReplaceChild("inner1", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-4F, -2F, -4F, 8, 14, 8), PartPose.offset(0F, 10F, 0F));
        PartDefinition inner2 = partdefinition.addOrReplaceChild("inner2", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-3.5F, 0F, -3.5F, 7, 13, 7), PartPose.offset(0F, 8.5F, 0F));
        PartDefinition barB1 = partdefinition.addOrReplaceChild("barB1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -1F, 1, 6, 1), PartPose.offsetAndRotation(0F, 8F, 0F, 1.047198F, 1.570796F, 0F));
        PartDefinition barB2 = partdefinition.addOrReplaceChild("barB2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, 0F, 1, 6, 1), PartPose.offsetAndRotation(0F, 8F, 0F, -1.047198F, 1.570796F, 0F));
        PartDefinition barB3 = partdefinition.addOrReplaceChild("barB3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, 8F, 1, 10, 1), PartPose.offsetAndRotation(0F, 5F, 0F, -1.047198F, 1.570796F, 0F));
        PartDefinition barB4 = partdefinition.addOrReplaceChild("barB4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -9F, 1, 10, 1), PartPose.offsetAndRotation(0F, 5F, 0F, 1.047198F, 1.570796F, 0F));
        PartDefinition barB5 = partdefinition.addOrReplaceChild("barB5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, 9F, 1, 6, 1), PartPose.offsetAndRotation(0F, 10F, 0F, -1.047198F, 1.570796F, 0F));
        PartDefinition barB6 = partdefinition.addOrReplaceChild("barB6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -10F, 1, 6, 1), PartPose.offsetAndRotation(0F, 10F, 0F, 1.047198F, 1.570796F, 0F));
        PartDefinition barF1 = partdefinition.addOrReplaceChild("barF1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, 0F, 1, 6, 1), PartPose.offsetAndRotation(0F, 8F, 0F, -1.047198F, 1.570796F, 0F));
        PartDefinition barF2 = partdefinition.addOrReplaceChild("barF2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, -1F, 1, 6, 1), PartPose.offsetAndRotation(0F, 8F, 0F, 1.047198F, 1.570796F, 0F));
        PartDefinition barF3 = partdefinition.addOrReplaceChild("barF3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, -9F, 1, 10, 1), PartPose.offsetAndRotation(0F, 5F, 0F, 1.047198F, 1.570796F, 0F));
        PartDefinition barF4 = partdefinition.addOrReplaceChild("barF4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, 8F, 1, 10, 1), PartPose.offsetAndRotation(0F, 5F, 0F, -1.047198F, 1.570796F, 0F));
        PartDefinition barF5 = partdefinition.addOrReplaceChild("barF5", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, -9F, 1, 5, 1), PartPose.offsetAndRotation(0F, 11F, 0F, 1.047198F, 1.570796F, 0F));
        PartDefinition barF6 = partdefinition.addOrReplaceChild("barF6", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, 9F, 1, 6, 1), PartPose.offsetAndRotation(0F, 10F, 0F, -1.047198F, 1.570796F, 0F));
        PartDefinition barR1 = partdefinition.addOrReplaceChild("barR1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, 8F, 1, 10, 1), PartPose.offsetAndRotation(0F, 2F, 0F, -1.047198F, 0F, 0F));
        PartDefinition barR2 = partdefinition.addOrReplaceChild("barR2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -9F, 1, 10, 1), PartPose.offsetAndRotation(0F, 2F, 0F, 1.047198F, 0F, 0F));
        PartDefinition barR3 = partdefinition.addOrReplaceChild("barR3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, 8F, 1, 10, 1), PartPose.offsetAndRotation(0F, 8F, 0F, -1.047198F, 0F, 0F));
        PartDefinition barR4 = partdefinition.addOrReplaceChild("barR4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5F, 0F, -9F, 1, 10, 1), PartPose.offsetAndRotation(0F, 8F, 0F, 1.047198F, 0F, 0F));
        PartDefinition barL1 = partdefinition.addOrReplaceChild("barL1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, -9F, 1, 10, 1), PartPose.offsetAndRotation(0F, 2F, 0F, 1.047198F, 0F, 0F));
        PartDefinition barL2 = partdefinition.addOrReplaceChild("barL2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, 8F, 1, 10, 1), PartPose.offsetAndRotation(0F, 2F, 0F, -1.047198F, 0F, 0F));
        PartDefinition barL3 = partdefinition.addOrReplaceChild("barL3", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, -9F, 1, 10, 1), PartPose.offsetAndRotation(0F, 8F, 0F, 1.047198F, 0F, 0F));
        PartDefinition barL4 = partdefinition.addOrReplaceChild("barL4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4F, 0F, 8F, 1, 10, 1), PartPose.offsetAndRotation(0F, 8F, 0F, -1.047198F, 0F, 0F));
        PartDefinition head1 = partdefinition.addOrReplaceChild("head1", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-1F, 0F, 2F, 2, 1, 2), PartPose.offsetAndRotation(0F, 11F, 0F, -0.5235988F, 1.570796F, 0F));
        PartDefinition head2 = partdefinition.addOrReplaceChild("head2", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-2F, 0F, 4F, 4, 1, 1), PartPose.offsetAndRotation(0F, 11F, 0F, -0.5235988F, 1.570796F, 0F));
        PartDefinition head3 = partdefinition.addOrReplaceChild("head3", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-3F, 0F, 5F, 6, 1, 1), PartPose.offsetAndRotation(0F, 11F, 0F, -0.5235988F, 1.570796F, 0F));
        PartDefinition head4 = partdefinition.addOrReplaceChild("head4", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-4F, 0F, 6F, 8, 1, 1), PartPose.offsetAndRotation(0F, 11F, 0F, -0.5235988F, 1.570796F, 0F));
        PartDefinition cover1 = partdefinition.addOrReplaceChild("cover1", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(5F, 0F, -4F, 1, 10, 8), PartPose.offset(0F, 14.5F, 0F));
        PartDefinition head6 = partdefinition.addOrReplaceChild("head6", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-1F, 0F, 2F, 2, 1, 2), PartPose.offsetAndRotation(0F, 11F, 0F, -0.5235988F, -1.570796F, 0F));
        PartDefinition head7 = partdefinition.addOrReplaceChild("head7", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-2F, 0F, 4F, 4, 1, 1), PartPose.offsetAndRotation(0F, 11F, 0F, -0.5235988F, -1.570796F, 0F));
        PartDefinition head8 = partdefinition.addOrReplaceChild("head8", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-3F, 0F, 5F, 6, 1, 1), PartPose.offsetAndRotation(0F, 11F, 0F, -0.5235988F, -1.570796F, 0F));
        PartDefinition head9 = partdefinition.addOrReplaceChild("head9", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-4F, 0F, 6F, 8, 1, 1), PartPose.offsetAndRotation(0F, 11F, 0F, -0.5235988F, -1.570796F, 0F));
        PartDefinition cover2 = partdefinition.addOrReplaceChild("cover2", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-6F, 0F, -4F, 1, 10, 8), PartPose.offset(0F, 14.5F, 0F));
        PartDefinition glow1 = partdefinition.addOrReplaceChild("glow1", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-2F, 0F, -2F, 4, 4, 4), PartPose.offsetAndRotation(0F, 16F, 0F, 0F, 0.7853982F, 0F));
        PartDefinition glow2 = partdefinition.addOrReplaceChild("glow2", CubeListBuilder.create().texOffs(24, 8).mirror().addBox(-3F, 0F, -3F, 6, 6, 6), PartPose.offsetAndRotation(0F, 15F, 0F, 0F, 0.7853982F, 0F));
        PartDefinition top = partdefinition.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(-1F, 0F, -1F, 2, 2, 2), PartPose.offsetAndRotation(0F, 13F, 0F, 0F, 0.7853982F, 0F));
        PartDefinition lod1a = partdefinition.addOrReplaceChild("lod1a", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, 6F, 6, 1, 1), PartPose.offset(0F, 17F, 0F));
        PartDefinition lod1b = partdefinition.addOrReplaceChild("lod1b", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, 6F, 4, 1, 1), PartPose.offsetAndRotation(0F, 17F, 0F, 0F, 0.6981317F, 0F));
        PartDefinition lod1c = partdefinition.addOrReplaceChild("lod1c", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, 6F, 4, 1, 1), PartPose.offsetAndRotation(0F, 17F, 0F, 0F, -0.6981317F, 0F));
        PartDefinition lod2a = partdefinition.addOrReplaceChild("lod2a", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, 6F, 6, 1, 1), PartPose.offset(0F, 21F, 0F));
        PartDefinition lod2b = partdefinition.addOrReplaceChild("lod2b", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, 6F, 4, 1, 1), PartPose.offsetAndRotation(0F, 21F, 0F, 0F, 0.6981317F, 0F));
        PartDefinition lod2c = partdefinition.addOrReplaceChild("lod2c", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, 6F, 4, 1, 1), PartPose.offsetAndRotation(0F, 21F, 0F, 0F, -0.6981317F, 0F));
        PartDefinition lod3a = partdefinition.addOrReplaceChild("lod3a", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -7F, 6, 1, 1), PartPose.offset(0F, 17F, 0F));
        PartDefinition lod3b = partdefinition.addOrReplaceChild("lod3b", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, -7F, 4, 1, 1), PartPose.offsetAndRotation(0F, 17F, 0F, 0F, 0.6981317F, 0F));
        PartDefinition lod3c = partdefinition.addOrReplaceChild("lod3c", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, -7F, 4, 1, 1), PartPose.offsetAndRotation(0F, 17F, 0F, 0F, -0.6981317F, 0F));
        PartDefinition lod4a = partdefinition.addOrReplaceChild("lod4a", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3F, 0F, -7F, 6, 1, 1), PartPose.offset(0F, 21F, 0F));
        PartDefinition lod4b = partdefinition.addOrReplaceChild("lod4b", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, -7F, 4, 1, 1), PartPose.offsetAndRotation(0F, 21F, 0F, 0F, 0.6981317F, 0F));
        PartDefinition lod4c = partdefinition.addOrReplaceChild("lod4c", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2F, 0F, -7F, 4, 1, 1), PartPose.offsetAndRotation(0F, 21F, 0F, 0F, -0.6981317F, 0F));
        PartDefinition wingb1 = partdefinition.addOrReplaceChild("wingb1", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(1F, 0F, -1F, 20, 0, 5), PartPose.offsetAndRotation(0F, 11.5F, 0F, -0.1047198F, 0F, 0F));
        PartDefinition wingb2 = partdefinition.addOrReplaceChild("wingb2", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(1F, 0F, -1F, 20, 0, 5), PartPose.offsetAndRotation(0F, 11.5F, 0F, -0.1047198F, 3.141593F, 0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }


    // normal-color parts
public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, byte b0, float rad) {

        if (b0 == 9) {
            arm1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            arm2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            arm3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            blade1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            blade2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            blade3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            blade21.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            blade22.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            blade23.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else if (b0 == 11) {
            top.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            head1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            head2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            head3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            head4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            head6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            head7.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            head8.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            head9.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            cover1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            cover2.render(poseStack, vertexConsumer, packedLight, packedOverlay);

            lod1a.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            lod1b.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            lod1c.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            lod2a.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            lod2b.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            lod2c.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            lod3a.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            lod3b.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            lod3c.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            lod4a.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            lod4b.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            lod4c.render(poseStack, vertexConsumer, packedLight, packedOverlay);

            wingb1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            wingb2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
    }

    // 蜊企乗・縺九▽縲∵・繧九＞濶ｲ
    public void renderLucent(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
        byte b0, float rad) {

        if (b0 == 8) {
            body.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            head.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            ear1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            ear2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            wing1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            wing2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tail1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            tail2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else if (b0 == 9) {
            sphere.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else if (b0 == 10) {
            inner1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else if (b0 == 11) {
            glow2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
    }

    // 荳埼乗・縲∵・繧九＞濶ｲ
    public void renderGlow(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
        byte b0) {

        if (b0 == 8) {
            core1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            core2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else if (b0 == 9) {
            sphere2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else if (b0 == 10) {
            inner2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            side4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barB1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barB2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barB3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barB4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barF1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barF2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barF3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barF4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barL1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barL2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barL3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barL4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barB5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barB6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barR1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barR2.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barR3.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barR4.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barF5.render(poseStack, vertexConsumer, packedLight, packedOverlay);
            barF6.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        } else if (b0 == 11) {
            glow1.render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }
    }

    public void setupAnim(float f, float f1, float f2, float f3, float f4, float f5, byte b0, float rad) {
        this.body.yRot = f3 / (180F / (float) Math.PI);
        this.head.yRot = f3 / (180F / (float) Math.PI);
        this.ear1.yRot = -1.570796F + (f3 / (180F / (float) Math.PI));
        this.ear2.yRot = 1.570796F + (f3 / (180F / (float) Math.PI));
        this.tail1.yRot = f3 / (180F / (float) Math.PI);
        this.tail2.yRot = f3 / (180F / (float) Math.PI);
        this.wing1.yRot = 1.570796F + f3 / (180F / (float) Math.PI);
        this.wing2.yRot = -1.570796F + f3 / (180F / (float) Math.PI);
        this.core2.yRot = f3 / (180F / (float) Math.PI);

        this.core1.yRot += rad / 3 / (360F / (float) Math.PI);
        this.core1.xRot += rad / 3 / (360F / (float) Math.PI);

        this.wingb1.yRot = rad / (180F / (float) Math.PI);
        this.wingb2.yRot = -3.141593F + rad / (180F / (float) Math.PI);
    }
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay);
    }
}
