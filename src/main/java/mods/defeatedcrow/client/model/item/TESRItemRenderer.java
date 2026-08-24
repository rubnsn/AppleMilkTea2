package mods.defeatedcrow.client.model.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import mods.defeatedcrow.client.model.model.*;
import mods.defeatedcrow.common.registry.ModItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * BEWLR for TESR BlockItems - renders the same Model as BER in inventory/hand.
 * Textures mapped to existing files in textures/entity (see fix for bread/clamp etc).
 */
public class TESRItemRenderer extends BlockEntityWithoutLevelRenderer {
    private static final Logger LOGGER = LogManager.getLogger("dcsapplemilk");

    private final ModelBarrel barrelModel;
    private final ModelProcessor processorModel;
    private final ModelEvaporator evaporatorModel;
    private final ModelIceMaker iceMakerModel;
    private final ModelMakerNext makerNextModel;
    private final ModelWipeBox wipeBoxModel;
    private final ModelWipeBox2 wipeBox2Model;
    private final ModelCupHandle cupHandleModel;
    private final ModelCocktail cocktailModel;
    private final ModelCLamp cLampModel;
    private final ModelCordial cordialModel;
    private final ModelAlcoholCup alcoholCupModel;
    private final ModelBowlJP bowlJPModel;
    private final ModelChopsticks chopsticksModel;
    private final ModelEggs eggsModel;
    private final ModelSteak steakModel;
    private final ModelBreads breadsModel;
    private final ModelTart tartModel;
    private final ModelIncenseBase incenseModel;
    private final ModelFlowerPot flowerPotModel;
    private final ModelCharger chargerModel;
    private final ModelHandleEngine handleEngineModel;
    private final ModelJawCrusher jawCrusherModel;
    private final ModelLargeBottle largeBottleModel;
    private final ModelCrowDoll crowDollModel;
    private final ModelAltBowl altBowlModel;
    private final ModelPanHandle panHandleModel;

    public TESRItemRenderer(Minecraft mc, EntityModelSet modelSet) {
        super(mc.getBlockEntityRenderDispatcher(), modelSet);
        this.barrelModel = new ModelBarrel(ModelBarrel.createBodyLayer().bakeRoot());
        this.processorModel = new ModelProcessor(ModelProcessor.createBodyLayer().bakeRoot());
        this.evaporatorModel = new ModelEvaporator(ModelEvaporator.createBodyLayer().bakeRoot());
        this.iceMakerModel = new ModelIceMaker(ModelIceMaker.createBodyLayer().bakeRoot());
        this.makerNextModel = new ModelMakerNext(ModelMakerNext.createBodyLayer().bakeRoot());
        this.wipeBoxModel = new ModelWipeBox(ModelWipeBox.createBodyLayer().bakeRoot());
        this.wipeBox2Model = new ModelWipeBox2(ModelWipeBox2.createBodyLayer().bakeRoot());
        this.cupHandleModel = new ModelCupHandle(ModelCupHandle.createBodyLayer().bakeRoot());
        this.cocktailModel = new ModelCocktail(ModelCocktail.createBodyLayer().bakeRoot());
        this.cLampModel = new ModelCLamp(ModelCLamp.createBodyLayer().bakeRoot());
        this.cordialModel = new ModelCordial(ModelCordial.createBodyLayer().bakeRoot());
        this.alcoholCupModel = new ModelAlcoholCup(ModelAlcoholCup.createBodyLayer().bakeRoot());
        this.bowlJPModel = new ModelBowlJP(ModelBowlJP.createBodyLayer().bakeRoot());
        this.chopsticksModel = new ModelChopsticks(ModelChopsticks.createBodyLayer().bakeRoot());
        this.eggsModel = new ModelEggs(ModelEggs.createBodyLayer().bakeRoot());
        this.steakModel = new ModelSteak(ModelSteak.createBodyLayer().bakeRoot());
        this.breadsModel = new ModelBreads(ModelBreads.createBodyLayer().bakeRoot());
        this.tartModel = new ModelTart(ModelTart.createBodyLayer().bakeRoot());
        this.incenseModel = new ModelIncenseBase(ModelIncenseBase.createBodyLayer().bakeRoot());
        this.flowerPotModel = new ModelFlowerPot(ModelFlowerPot.createBodyLayer().bakeRoot());
        this.chargerModel = new ModelCharger(ModelCharger.createBodyLayer().bakeRoot());
        this.handleEngineModel = new ModelHandleEngine(ModelHandleEngine.createBodyLayer().bakeRoot());
        this.jawCrusherModel = new ModelJawCrusher(ModelJawCrusher.createBodyLayer().bakeRoot());
        this.largeBottleModel = new ModelLargeBottle(ModelLargeBottle.createBodyLayer().bakeRoot());
        this.crowDollModel = new ModelCrowDoll(ModelCrowDoll.createBodyLayer().bakeRoot());
        this.altBowlModel = new ModelAltBowl(ModelAltBowl.createBodyLayer().bakeRoot());
        this.panHandleModel = new ModelPanHandle(ModelPanHandle.createBodyLayer().bakeRoot());
    }

    @Override
    public void onResourceManagerReload(ResourceManager manager) {}

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        Item item = stack.getItem();
        pose.pushPose();
        // Vanilla reference: ItemRenderer.handleCameraTransforms already applies ItemDisplayContext (GUI/FIRST_PERSON etc.)
        // transform for builtin/entity before calling renderByItem. Here we replicate BER pose exactly:
        // BER does translate(0.5,1.5,0.5) scale(1,-1,-1) (see TileEntityBarrelRenderer.java:26). Same for item
        // so that hand/GUI size is not double-scaled. Previous 0.65/0.8 scale + ItemRenderer's GUI 0.5 made it tiny.
        pose.translate(0.5F, 1.5F, 0.5F);
        pose.scale(1.0F, -1.0F, -1.0F);

        boolean rendered = false;
        if (item == ModItems.BARREL_ITEM.get()) {
            renderBarrel(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.PROCESSOR_ITEM.get()) {
            renderProcessor(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.EVAPORATOR_ITEM.get()) {
            renderEvaporator(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.ICE_MAKER_ITEM.get()) {
            renderIceMaker(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.TEA_MAKER_NEXT_ITEM.get() || item == ModItems.TEA_MAKER_BLACK_ITEM.get()) {
            renderMakerNext(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.WIPE_BOX_ITEM.get()) {
            renderWipeBox(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.WIPE_BOX2_ITEM.get()) {
            renderWipeBox2(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.FILLED_CUP_ITEM.get() || item == ModItems.FILLED_CUP2_ITEM.get()) {
            renderCupHandle(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.COCKTAIL_ITEM.get() || item == ModItems.COCKTAIL2_ITEM.get() || item == ModItems.COCKTAIL_SP_ITEM.get()) {
            renderCocktail(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.CHALCEDONY_LAMP_ITEM.get()) {
            renderCLamp(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.CORDIAL.get()) {
            renderCordial(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.ALCOHOL_CUP_ITEM.get()) {
            renderAlcoholCup(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.BOWL_JP_ITEM.get()) {
            renderBowlJP(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.CHOPSTICKS_BOX_ITEM.get()) {
            renderChopsticks(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.EGG_BASKET_ITEM.get()) {
            renderEggs(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.FOOD_PLATE_ITEM.get()) {
            renderSteak(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.BASKET_ITEM.get() || item == ModItems.VEGI_BAG_ITEM.get()) {
            renderBreads(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.BOWL_RACK_ITEM.get()) {
            renderAltBowl(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.CHOCO_BLOCK_ITEM.get()) {
            renderTart(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.INCENSE_BASE_ITEM.get()) {
            renderIncense(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.FLOWER_POT_ITEM.get()) {
            renderFlowerPot(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.BAT_BOX_ITEM.get() || item == ModItems.GEL_BAT_ITEM.get()) {
            renderCharger(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.HANDLE_ENGINE_ITEM.get()) {
            renderHandleEngine(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.ADV_PROCESSOR_ITEM.get()) {
            renderJawCrusher(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.LARGE_BOTTLE.get() || item == ModItems.EMPTY_BOTTLE_ITEM.get()) {
            renderLargeBottle(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.CROW_DOLL_ITEM.get()) {
            renderCrowDoll(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.FILLED_SOUP_PAN_ITEM.get() || item == ModItems.EMPTY_PAN_G_ITEM.get() || item == ModItems.TEPPAN_II_ITEM.get()) {
            renderPanHandle(pose, buffers, light, overlay);
            rendered = true;
        } else if (item == ModItems.CARDBOARD_ITEM.get() || item == ModItems.CONTAINER_WATER_BOTTLE_ITEM.get() || item == ModItems.FLOWER_VASE_ITEM.get() || item == ModItems.HEDGE_ITEM.get() || item == ModItems.ROTARY_DIAL_ITEM.get() || item == ModItems.CHALCEDONY_PANEL_ITEM.get() || item == ModItems.WOOD_PANEL_ITEM.get() || item == ModItems.YUZU_FENCE_ITEM.get()) {
            renderIncense(pose, buffers, light, overlay);
            rendered = true;
        }
        pose.popPose();
        if (!rendered) {
            // Fallback: vanilla will not render because we set block model to empty, so log for debug
            // System.out.println("[TESRItemRenderer] no model for " + item);
        }
    }

    // --- Direct render helpers with correct existing textures ---
    private void renderBarrel(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/barrel.png")));
        barrelModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderProcessor(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/processor.png")));
        processorModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderEvaporator(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/evaporator.png")));
        evaporatorModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderIceMaker(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/icemaker.png")));
        iceMakerModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderMakerNext(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/automaker.png")));
        makerNextModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderWipeBox(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/wipebox.png")));
        wipeBoxModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderWipeBox2(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/wipebox2.png")));
        wipeBox2Model.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderCupHandle(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/jpcup.png")));
        cupHandleModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderCocktail(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/cocktail.png")));
        cocktailModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderCLamp(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/x32/lamp_r13a.png")));
        cLampModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderCordial(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/largebottle.png")));
        cordialModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderAlcoholCup(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/cocktail.png")));
        alcoholCupModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderBowlJP(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/bowljp_whiteporcelain.png")));
        bowlJPModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderChopsticks(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/chopsticks.png")));
        chopsticksModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderEggs(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/breads.png")));
        eggsModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderSteak(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/steak.png")));
        steakModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderBreads(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/breads.png")));
        breadsModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderTart(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/tart.png")));
        tartModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderIncense(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/incensebase.png")));
        incenseModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderFlowerPot(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/flowerpot_red.png")));
        flowerPotModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderCharger(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/charger.png")));
        chargerModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderHandleEngine(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/handle_engine.png")));
        handleEngineModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderJawCrusher(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/jawcrusher.png")));
        jawCrusherModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderLargeBottle(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/largebottle.png")));
        largeBottleModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
    private void renderCrowDoll(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/crowdoll.png")));
        crowDollModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }

    private void renderAltBowl(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/x32alt/bowlrack_alt.png")));
        altBowlModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }

    private void renderPanHandle(PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(new ResourceLocation("defeatedcrow", "textures/entity/breads.png")));
        panHandleModel.renderToBuffer(pose, vc, light, overlay, 1, 1, 1, 1);
    }
}
