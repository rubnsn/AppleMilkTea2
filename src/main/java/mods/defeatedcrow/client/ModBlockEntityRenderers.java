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
 * 1.20.1 BER registration - replaces the 38 ClientProxy.bindTESR calls (1.7.10).
 * WT-C owns this file. Field names use TILE_* prefix to match ModBlockEntities.
 */
@Mod.EventBusSubscriber(modid = DCsAppleMilk.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModBlockEntityRenderers {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // common.tile
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_CUP_HANDLE.get(), TileEntityCupHandleRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_BREAD.get(), TileEntityBreadRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_JP_BOWL.get(), TileEntityBowlJPRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_CHOPSTICKS_BOX.get(), TileEntityChopsticksRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_EGGS.get(), TileEntityEggsRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_STEAK.get(), TileEntitySteakRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_MAKER_HANDLE.get(), TileEntityMakerRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_PAN_HANDLE.get(), TileEntityPanHandleRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_MAKER_NEXT.get(), TileEntityMakerNextRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_WIPE_BOX.get(), TileEntityWipeBoxRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_WIPE_BOX2.get(), TileEntityWipe2Renderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_ROTARY_DIAL.get(), TileEntityDialRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_COCKTAIL.get(), TileEntityCocktailRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_COCKTAIL2.get(), TileEntityCocktail2Renderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_LARGE_BOTTLE.get(), TileEntityBottleRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_EMPTY_BOTTLE.get(), TileEntityEmptyBottleRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_C_LAMP.get(), TileEntityCLampRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_CORDIAL.get(), TileEntityCordialRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_ALCOHOL_CUP.get(), TileEntityAlcoholCupRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_VEGI_BAG.get(), TileEntityVegiBagRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_CARD_BOARD.get(), TileEntityCardBoardRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_INCENSE_BASE.get(), TileEntityIncenseBaseRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_FLOWER_POT.get(), TileEntityFlowerPotRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_BOWL_RACK.get(), TileEntityBowlRackRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_CONTAINER_BASE.get(), TileEntityContainerBaseRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_CROW_DOLL.get(), TileEntityCrowdollRenderer::new);
        // common.tile.appliance
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_FILLED_SOUP_PAN.get(), TileEntitySoupPanRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_ICE_MAKER.get(), TileEntityIceMakerRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_ICE_CREAM.get(), TileEntityIceCreamRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_PROCESSOR.get(), TileEntityProcessorRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_ADV_PROCESSOR.get(), TileEntityJawCrusherRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_EVAPORATOR.get(), TileEntityEvaporatorRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_PAN_G.get(), TileEntityPanGRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_TEPPAN_II.get(), TileEntityTeppanIIRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_COCKTAIL_SP.get(), TileEntityCocktailSPRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_BREWING_BARREL.get(), TileEntityBarrelRenderer::new);
        // common.tile.energy
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_CHARGER_DEVICE.get(), TileEntityChargerRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TILE_HANDLE_ENGINE.get(), TileEntityEHandleRenderer::new);
    }
}
