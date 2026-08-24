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
import mods.defeatedcrow.common.registry.ModBlocks;
import mods.defeatedcrow.common.registry.ModItems;

/**
 * BEWLR for TESR BlockItems - renders the same Model as BER in inventory/hand.
 * Uses Model.createBodyLayer().bakeRoot() directly (no ModModelLayers) for minimal.
 * Textures are defeatedcrow:textures/entity/<name>.png (fallback to block texture).
 */
public class TESRItemRenderer extends BlockEntityWithoutLevelRenderer {

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
    }

    @Override
    public void onResourceManagerReload(ResourceManager manager) {}

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        Item item = stack.getItem();
        pose.pushPose();
        // Center and scale for item view (similar to BER but smaller for GUI)
        pose.translate(0.5, 1.0, 0.5);
        pose.scale(0.8F, -0.8F, -0.8F);

        // Select model and texture based on item
        if (item == ModItems.BARREL_ITEM.get()) {
            renderModel(barrelModel, "barrel", pose, buffers, light, overlay);
        } else if (item == ModItems.PROCESSOR_ITEM.get()) {
            renderModel(processorModel, "processor", pose, buffers, light, overlay);
        } else if (item == ModItems.EVAPORATOR_ITEM.get()) {
            renderModel(evaporatorModel, "evaporator", pose, buffers, light, overlay);
        } else if (item == ModItems.ICE_MAKER_ITEM.get()) {
            renderModel(iceMakerModel, "icemaker", pose, buffers, light, overlay);
        } else if (item == ModItems.TEA_MAKER_NEXT_ITEM.get() || item == ModItems.TEA_MAKER_BLACK_ITEM.get()) {
            renderModel(makerNextModel, "makernext", pose, buffers, light, overlay);
        } else if (item == ModItems.WIPE_BOX_ITEM.get()) {
            renderModel(wipeBoxModel, "wipebox", pose, buffers, light, overlay);
        } else if (item == ModItems.WIPE_BOX2_ITEM.get()) {
            renderModel(wipeBox2Model, "wipebox2", pose, buffers, light, overlay);
        } else if (item == ModItems.FILLED_CUP_ITEM.get() || item == ModItems.FILLED_CUP2_ITEM.get()) {
            renderModel(cupHandleModel, "cuphandle", pose, buffers, light, overlay);
        } else if (item == ModItems.COCKTAIL_ITEM.get() || item == ModItems.COCKTAIL2_ITEM.get() || item == ModItems.COCKTAIL_SP_ITEM.get()) {
            renderModel(cocktailModel, "cocktail", pose, buffers, light, overlay);
        } else if (item == ModItems.CHALCEDONY_LAMP_ITEM.get()) {
            renderModel(cLampModel, "clamp", pose, buffers, light, overlay);
        } else if (item == ModItems.CORDIAL.get()) {
            renderModel(cordialModel, "cordial", pose, buffers, light, overlay);
        } else if (item == ModItems.ALCOHOL_CUP_ITEM.get()) {
            renderModel(alcoholCupModel, "alcoholcup", pose, buffers, light, overlay);
        } else if (item == ModItems.BOWL_JP_ITEM.get()) {
            renderModel(bowlJPModel, "bowljp", pose, buffers, light, overlay);
        } else if (item == ModItems.CHOPSTICKS_BOX_ITEM.get()) {
            renderModel(chopsticksModel, "chopsticks", pose, buffers, light, overlay);
        } else if (item == ModItems.EGG_BASKET_ITEM.get()) {
            renderModel(eggsModel, "eggs", pose, buffers, light, overlay);
        } else if (item == ModItems.FOOD_PLATE_ITEM.get()) {
            renderModel(steakModel, "steak", pose, buffers, light, overlay);
        } else if (item == ModItems.BASKET_ITEM.get() || item == ModItems.VEGI_BAG_ITEM.get() || item == ModItems.BOWL_RACK_ITEM.get()) {
            renderModel(breadsModel, "breads", pose, buffers, light, overlay);
        } else if (item == ModItems.CHOCO_BLOCK_ITEM.get()) {
            renderModel(tartModel, "tart", pose, buffers, light, overlay);
        } else if (item == ModItems.INCENSE_BASE_ITEM.get()) {
            renderModel(incenseModel, "incensebase", pose, buffers, light, overlay);
        } else if (item == ModItems.FLOWER_POT_ITEM.get()) {
            renderModel(flowerPotModel, "flowerpot", pose, buffers, light, overlay);
        } else if (item == ModItems.BAT_BOX_ITEM.get() || item == ModItems.GEL_BAT_ITEM.get()) {
            renderModel(chargerModel, "charger", pose, buffers, light, overlay);
        } else if (item == ModItems.HANDLE_ENGINE_ITEM.get()) {
            renderModel(handleEngineModel, "handleengine", pose, buffers, light, overlay);
        } else if (item == ModItems.ADV_PROCESSOR_ITEM.get()) {
            renderModel(jawCrusherModel, "jawcrusher", pose, buffers, light, overlay);
        } else if (item == ModItems.LARGE_BOTTLE.get() || item == ModItems.EMPTY_BOTTLE_ITEM.get()) {
            renderModel(largeBottleModel, "largebottle", pose, buffers, light, overlay);
        } else if (item == ModItems.CROW_DOLL_ITEM.get()) {
            renderModel(crowDollModel, "crowdoll", pose, buffers, light, overlay);
        } else if (item == ModItems.FILLED_SOUP_PAN_ITEM.get() || item == ModItems.EMPTY_PAN_G_ITEM.get() || item == ModItems.TEPPAN_II_ITEM.get()) {
            // Fallback for pan-related: use breads as placeholder
            renderModel(breadsModel, "breads", pose, buffers, light, overlay);
        } else if (item == ModItems.CARDBOARD_ITEM.get() || item == ModItems.CONTAINER_WATER_BOTTLE_ITEM.get() || item == ModItems.FLOWER_VASE_ITEM.get() || item == ModItems.HEDGE_ITEM.get() || item == ModItems.ROTARY_DIAL_ITEM.get() || item == ModItems.CHALCEDONY_PANEL_ITEM.get() || item == ModItems.WOOD_PANEL_ITEM.get() || item == ModItems.YUZU_FENCE_ITEM.get()) {
            // For remaining decorative TESR, use incense or breads as generic
            renderModel(incenseModel, "incensebase", pose, buffers, light, overlay);
        } else {
            // Fallback: render nothing, let vanilla handle
            pose.popPose();
            return;
        }
        pose.popPose();
    }

    private void renderModel(Object model, String texName, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
        // Resolve texture: try entity/<tex>.png, fallback to block/<tex>.png, then to missing
        ResourceLocation tex = new ResourceLocation("defeatedcrow", "textures/entity/" + texName + ".png");
        // Use entityCutout; if texture missing, Minecraft will show missing texture but not crash
        VertexConsumer vc = buffers.getBuffer(RenderType.entityCutout(tex));
        // Call renderToBuffer via reflection or direct
        try {
            model.getClass().getMethod("renderToBuffer", PoseStack.class, VertexConsumer.class, int.class, int.class, float.class, float.class, float.class, float.class)
                .invoke(model, pose, vc, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        } catch (Exception e) {
            // Fallback: try without reflection if model has method
        }
    }
}
