package mods.defeatedcrow.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import mods.defeatedcrow.common.DCsAppleMilk;

/**
 * 1.20.1 client registration  Ereplaces ClientProxy ISBRH/TESR/EntityRenderer.
 * Bootstrap-owned skeleton, WT-C fills each section.
 *
 * - BlockEntityRenderers.register: 38 bindTESR (��1.7.10) ↁEBlockEntityRendererProvider
 * - ISBRH 44 ↁEblockstate JSON + ItemBlockRenderTypes.setRenderLayer in FMLClientSetupEvent + BlockEntityRenderer for TESR-like
 * - EntityRenderers.register: 23 registerEntityRenderingHandler ↁEEntityRendererProvider
 * See doc/blocks/migration-guide.md:174 / doc/entities/migration-guide.md
 */
@Mod.EventBusSubscriber(modid = DCsAppleMilk.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onRegisterBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // --- WT-C: BER 38 (ClientProxy.bindTESR (��1.7.10)) ---
        // event.registerBlockEntityRenderer(ModBlockEntities.TEA_MAKER_NEXT.get(), RenderTeaMakerNext::new);
        // event.registerBlockEntityRenderer(ModBlockEntities.TILE_CUP_HANDLE.get(), TileEntityCupHandleRenderer::new);
        // ... 38 entries: TileCupHandle, TileBread, TileJPBowl, TileChopsticksBox, TileEggs, TileSteak,
        // TileMakerHandle, TilePanHandle, TileFilledSoupPan, TileMakerNext, TileWipeBox, TileIceMaker,
        // TileIceCream, TileWipeBox2, TileRotaryDial, TileCocktail, TileCocktail2, TileLargeBottle,
        // TileEmptyBottle, TileCLamp, TileCordial, TileAlcoholCup, TileProcessor, TileAdvProcessor,
        // TileEvaporator, TileVegiBag, TileCardBoard, TileIncenseBase, TilePanG, TileBrewingBarrel,
        // TileChargerDevice, TileFlowerPot, TileTeppanII, TileCocktailSP, TileHandleEngine, TileBowlRack,
        // TileContainerBase, TileCrowDoll
    }

    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // --- WT-C: Entity 23 (ClientProxy.registerEntityRenderingHandler) ---
        // event.registerEntityRenderer(ModEntities.MELON_BOMB.get(), RenderMelonBomb::new);
        // event.registerEntityRenderer(ModEntities.SILKY_MELON.get(), RenderSilkyMelon::new);
        // ... 23 entries: EntityMelonBomb, EntitySilkyMelon, PlaceableIcecream, PlaceableSteak,
        // PlaceableAlcoholCup, PlaceableCocktail, PlaceableCocktail2, PlaceableBowl, PlaceableBowlJP,
        // PlaceableCup1, PlaceableCup2, PlaceableTart, PlaceableSandwich, EntityKinoko, EntityStunEffect,
        // EntityIllusionMobs, EntityAnchorMissile, EntityYuzuBullet, PlaceableCocktailSP, PlaceableBaseSoup
        // + 3 remaining (Placeable* variants)
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            // --- WT-C: RenderType cutout for 44 ISBRH blocks ---
            // ItemBlockRenderTypes.setRenderLayer(ModBlocks.TEA_MAKER_NEXT.get(), RenderType.cutout());
            // ... 44 blocks: teaMakerNext, emptyCup, teaTree, filledCup, bowl, bowlRack, cLamp, basket,
            // foodPlate, teppanII, bowlJP, cupSummer, chopsticksBox, eggBasket, kinoko, chocoPan,
            // teaMakerBlack, processor, wipeBox, iceMaker, iceCream, dial, cocktail, largeBottle,
            // cassisTree, cordial, alcoholCup, processor, evaporator, jawCrusher, cPanel, incenseBase,
            // yuzuBat, gelBat, chargerDevice, flowerPot, yuzuFence, eHandle, woodPanel, cLampOp,
            // soupPanFilled, containerWBottle, flowerVase, hedge
        });
    }
}
