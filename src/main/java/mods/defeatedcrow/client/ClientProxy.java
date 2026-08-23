package mods.defeatedcrow.client;

import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import mods.defeatedcrow.common.CommonProxy;

/**
 * 1.20.1 stub - ClientProxy は ISBRH/TESR/EntityRenderer の旧登録を廃止。
 * SidedProxy は削除、DistExecutor / IEventBus の clientSetup に置換。
 *
 * 移行メモ（詳細は client/ModClientEvents.java を参照）:
 *
 * bind TESR 38件 -> BlockEntityRenderers register (FMLClientSetupEvent / EntityRenderersEvent RegisterRenderers):
 *  TileCupHandle -> TileEntityCupHandleRenderer, TileBread -> TileEntityBreadRenderer,
 *  TileJPBowl -> TileEntityBowlJPRenderer, TileChopsticksBox -> TileEntityChopsticksRenderer,
 *  TileEggs -> TileEntityEggsRenderer, TileSteak -> TileEntitySteakRenderer,
 *  TileMakerHandle -> TileEntityMakerRenderer, TilePanHandle -> TileEntityPanHandleRenderer,
 *  TileFilledSoupPan -> TileEntitySoupPanRenderer, TileMakerNext -> TileEntityMakerNextRenderer,
 *  TileWipeBox -> TileEntityWipeBoxRenderer, TileIceMaker -> TileEntityIceMakerRenderer,
 *  TileIceCream -> TileEntityIceCreamRenderer, TileWipeBox2 -> TileEntityWipe2Renderer,
 *  TileRotaryDial -> TileEntityDialRenderer, TileCocktail -> TileEntityCocktailRenderer,
 *  TileCocktail2 -> TileEntityCocktail2Renderer, TileLargeBottle -> TileEntityBottleRenderer,
 *  TileEmptyBottle -> TileEntityEmptyBottleRenderer, TileCLamp -> TileEntityCLampRenderer,
 *  TileCordial -> TileEntityCordialRenderer, TileAlcoholCup -> TileEntityAlcoholCupRenderer,
 *  TileProcessor -> TileEntityProcessorRenderer, TileAdvProcessor -> TileEntityJawCrusherRenderer,
 *  TileEvaporator -> TileEntityEvaporatorRenderer, TileVegiBag -> TileEntityVegiBagRenderer,
 *  TileCardBoard -> TileEntityCardBoardRenderer, TileIncenseBase -> TileEntityIncenseBaseRenderer,
 *  TilePanG -> TileEntityPanGRenderer, TileBrewingBarrel -> TileEntityBarrelRenderer,
 *  TileChargerDevice -> TileEntityChargerRenderer, TileFlowerPot -> TileEntityFlowerPotRenderer,
 *  TileTeppanII -> TileEntityTeppanIIRenderer, TileCocktailSP -> TileEntityCocktailSPRenderer,
 *  TileHandleEngine -> TileEntityEHandleRenderer, TileBowlRack -> TileEntityBowlRackRenderer,
 *  TileContainerBase -> TileEntityContainerBaseRenderer, TileCrowDoll -> TileEntityCrowdollRenderer
 *  -> 1.20.1: BlockEntityRenderers.register(ModBlockEntities.X.get(), Ctx::new)
 *
 * registerBlockHandler 44 ISBRH -> BlockEntityRenderer + blockstate JSON + RenderType cutout (旧 RenderingRegistry handler はModClientEventsへ):
 *  RenderEmptyCup, RenderSoupPan, RenderTeaTree, RenderFilledCup, RenderFilledBowl,
 *  RenderBowlRack, RenderChalcedonyLamp, RenderBreadBasket, RenderFoodPlate, RenderTeppann,
 *  RenderFilledBowlJP, RenderCupSummer, RenderChopsticksBox, RenderEggsBasket, RenderKinoko,
 *  RenderChocoPan, RenderTeaMakerNext, RenderAutoMaker, RenderWipeBox, RenderIceMaker,
 *  RenderIceCream, RenderDial, RenderCocktail, RenderLargeBottle, RenderCassisTree,
 *  RenderCordial, RenderAlcoholCup, RenderProcessor, RenderEvaporator, RenderJawCrusher,
 *  RenderCPanel, RenderIncenseBase, RenderYuzuBat, RenderGelBat, RenderChargerDevice,
 *  RenderFlowerPot, RenderYuzuFence, RenderEHandle, RenderWoodPanel, RenderCLampOp,
 *  RenderSoupPanFilled, RenderContainerWBottle, RenderFlowerVase, RenderHedge
 *  -> 1.20.1: models/block/*.json + ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout()) in FMLClientSetupEvent
 *
 * registerEntityRenderingHandler 23 -> EntityRenderers.register (EntityRenderersEvent.RegisterRenderers):
 *  EntityMelonBomb -> RenderMelonBomb, EntitySilkyMelon -> RenderSilkyMelon,
 *  PlaceableIcecream -> RenderIceCreamEntity, PlaceableSteak -> RenderSteakEntity,
 *  PlaceableAlcoholCup -> RenderAlcoholCupEntity, PlaceableCocktail -> RenderCocktailEntity,
 *  PlaceableCocktail2 -> RenderCocktail2Entity, PlaceableBowl -> RenderBowlEntity,
 *  PlaceableBowlJP -> RenderBowlJPEntity, PlaceableCup1 -> RenderCupEntity,
 *  PlaceableCup2 -> RenderCup2Entity, PlaceableTart -> RenderTartEntity,
 *  PlaceableSandwich -> RenderSandwichEntity, EntityKinoko -> RenderKinokoEntity,
 *  EntityStunEffect -> RenderStunEntity, EntityIllusionMobs -> RenderIllusionCreeper,
 *  EntityAnchorMissile -> RenderAnchorMissile, EntityYuzuBullet -> RenderYuzuBullet,
 *  PlaceableCocktailSP -> RenderCocktailSPEntity, PlaceableBaseSoup -> RenderFoodEntityBase
 *  -> 1.20.1: EntityRenderers.register(ModEntities.X.get(), Ctx::new) + ModelLayerLocation
 *
 * ItemRenderer 5 -> BlockEntityWithoutLevelRenderer / ItemProperties:
 *  yuzuGatling, fossilCannon, eightEyesArm, cocktailSP, handleEngine
 * Villager skin 2 -> VillagerRenderer via EntityRenderersEvent
 */
@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public Level getClientWorld() {
        return net.minecraft.client.Minecraft.getInstance().level;
    }

    @Override
    public int addArmor(String armor) {
        return 0;
    }

    @Override
    public void registerTileEntity() {}

    @Override
    public int getRenderID() {
        return -1;
    }

    @Override
    public void registerRenderers() {}

    @Override
    public void registerTex() {}

    @Override
    public void registerFluidTex() {}

    @Override
    public boolean isShiftKeyDown() { return false; }
    @Override
    public boolean isJumpKeyDown() { return false; }
    @Override
    public boolean isSneakKeyDown() { return false; }
    @Override
    public boolean isFowardKeyDown() { return false; }
    @Override
    public boolean isBackKeyDown() { return false; }
    @Override
    public boolean isLeftKeyDown() { return false; }
    @Override
    public boolean isRightKeyDown() { return false; }
    @Override
    public boolean isWarpKeyDown() { return false; }
}
