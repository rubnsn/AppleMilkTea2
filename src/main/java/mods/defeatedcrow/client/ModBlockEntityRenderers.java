package mods.defeatedcrow.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import mods.defeatedcrow.client.model.tileentity.TileEntityAlcoholCupRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityBarrelRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityBottleRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityBowlJPRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityBowlRackRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityBreadRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityCardBoardRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityChargerRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityChopsticksRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityCLampRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityCocktail2Renderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityCocktailRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityCocktailSPRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityContainerBaseRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityCordialRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityCrowdollRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityCupHandleRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityDialRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityEggsRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityEHandleRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityEmptyBottleRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityEvaporatorRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityFlowerPotRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityIceCreamRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityIceMakerRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityIncenseBaseRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityJawCrusherRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityMakerNextRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityMakerRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityPanGRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityPanHandleRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityProcessorRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntitySoupPanRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntitySteakRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityTeppanIIRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityVegiBagRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityWipe2Renderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityWipeBoxRenderer;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.registry.ModBlockEntities;

/**
 * 1.20.1 BER registration  Ereplaces the 38 {@code ClientProxy.bindTESR (��1.7.10)}
 * calls (1.7.10). WT-C owns this file.
 *
 * <p><b>FIELD-NAME SYNC REQUIREMENT (WT-B):</b> every {@code ModBlockEntities.*} field referenced
 * below must exist in the WT-B skeleton {@code common/registry/ModBlockEntities.java} with exactly
 * this UPPER_SNAKE name, and its {@code BlockEntityType<T>} generic must match the renderer's
 * {@code BlockEntityRenderer<T>} type argument. The BE types live in
 * {@code common.tile} / {@code common.tile.appliance} / {@code common.tile.energy}; see the
 * per-entry comment for the expected 1.7.10 tile class origin.</p>
 *
 * <p>Build note: {@code ModBlockEntities} does not exist yet (WT-B skeleton pending), so this file
 * does not compile until that lands  Eintentional per plan.md "修正優允E/ ビルド�E全修正征E.</p>
 */
@Mod.EventBusSubscriber(modid = DCsAppleMilk.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModBlockEntityRenderers {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // --- WT-C: BER 38 (ClientProxy.bindTESR (��1.7.10)) ---
        // common.tile:
        event.registerBlockEntityRenderer(ModBlockEntities.CUP_HANDLE.get(), TileEntityCupHandleRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BREAD.get(), TileEntityBreadRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.JP_BOWL.get(), TileEntityBowlJPRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CHOPSTICKS_BOX.get(), TileEntityChopsticksRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.EGGS.get(), TileEntityEggsRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.STEAK.get(), TileEntitySteakRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MAKER_HANDLE.get(), TileEntityMakerRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.PAN_HANDLE.get(), TileEntityPanHandleRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MAKER_NEXT.get(), TileEntityMakerNextRenderer::new); // appliance.TileMakerNext
        event.registerBlockEntityRenderer(ModBlockEntities.WIPE_BOX.get(), TileEntityWipeBoxRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.WIPE_BOX_2.get(), TileEntityWipe2Renderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ROTARY_DIAL.get(), TileEntityDialRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.COCKTAIL.get(), TileEntityCocktailRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.COCKTAIL_2.get(), TileEntityCocktail2Renderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.LARGE_BOTTLE.get(), TileEntityBottleRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.EMPTY_BOTTLE.get(), TileEntityEmptyBottleRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.C_LAMP.get(), TileEntityCLampRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CORDIAL.get(), TileEntityCordialRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ALCOHOL_CUP.get(), TileEntityAlcoholCupRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.VEGI_BAG.get(), TileEntityVegiBagRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CARD_BOARD.get(), TileEntityCardBoardRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.INCENSE_BASE.get(), TileEntityIncenseBaseRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.FLOWER_POT.get(), TileEntityFlowerPotRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BOWL_RACK.get(), TileEntityBowlRackRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CONTAINER_BASE.get(), TileEntityContainerBaseRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CROW_DOLL.get(), TileEntityCrowdollRenderer::new);
        // common.tile.appliance:
        event.registerBlockEntityRenderer(ModBlockEntities.FILLED_SOUP_PAN.get(), TileEntitySoupPanRenderer::new); // appliance.TileFilledSoupPan
        event.registerBlockEntityRenderer(ModBlockEntities.ICE_MAKER.get(), TileEntityIceMakerRenderer::new); // appliance.TileIceMaker
        event.registerBlockEntityRenderer(ModBlockEntities.ICE_CREAM.get(), TileEntityIceCreamRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.PROCESSOR.get(), TileEntityProcessorRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ADV_PROCESSOR.get(), TileEntityJawCrusherRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.EVAPORATOR.get(), TileEntityEvaporatorRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.PAN_G.get(), TileEntityPanGRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TEPPAN_II.get(), TileEntityTeppanIIRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.COCKTAIL_SP.get(), TileEntityCocktailSPRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BREWING_BARREL.get(), TileEntityBarrelRenderer::new);
        // common.tile.energy:
        event.registerBlockEntityRenderer(ModBlockEntities.CHARGER_DEVICE.get(), TileEntityChargerRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.HANDLE_ENGINE.get(), TileEntityEHandleRenderer::new);
    }
}
