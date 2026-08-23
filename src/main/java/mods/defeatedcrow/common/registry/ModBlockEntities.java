package mods.defeatedcrow.common.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 1.20.1 BlockEntity registry - Builder.of + Holder.
 * See doc/tile-entities/migration-guide.md:12
 * WT-B owns all 47 BEs (CommonProxy.registerTileEntity 47件). Bootstrap owns the DeferredRegister shell.
 * 1.20.1 uses BlockBehaviour.Properties + BlockEntityType.Builder.of(Supplier, Block...) -> build(null).
 */
public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "defeatedcrow");

    // --- WT-B: APPLIANCE (TileMakerNext, TileProcessor, TileAdvProcessor, TileEvaporator, TileIceMaker, TilePanG, TileTeppanII, TileFilledSoupPan, TileMakerHandle, TilePanHandle) ---
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.appliance.TileMakerNext>> TILE_MAKER_NEXT = BLOCK_ENTITIES.register("tile_maker_next",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.appliance.TileMakerNext::new, ModBlocks.TEA_MAKER_NEXT.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.appliance.TileProcessor>> TILE_PROCESSOR = BLOCK_ENTITIES.register("tile_processor",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.appliance.TileProcessor::new, ModBlocks.PROCESSOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.appliance.TileAdvProcessor>> TILE_ADV_PROCESSOR = BLOCK_ENTITIES.register("tile_adv_processor",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.appliance.TileAdvProcessor::new, ModBlocks.ADV_PROCESSOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.appliance.TileEvaporator>> TILE_EVAPORATOR = BLOCK_ENTITIES.register("tile_evaporator",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.appliance.TileEvaporator::new, ModBlocks.EVAPORATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.appliance.TileIceMaker>> TILE_ICE_MAKER = BLOCK_ENTITIES.register("tile_ice_maker",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.appliance.TileIceMaker::new, ModBlocks.ICE_MAKER.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.appliance.TilePanG>> TILE_PAN_G = BLOCK_ENTITIES.register("tile_pan_g",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.appliance.TilePanG::new, ModBlocks.EMPTY_PAN_G.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.appliance.TileTeppanII>> TILE_TEPPAN_II = BLOCK_ENTITIES.register("tile_teppan_ii",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.appliance.TileTeppanII::new, ModBlocks.TEPPAN_II.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.appliance.TileFilledSoupPan>> TILE_FILLED_SOUP_PAN = BLOCK_ENTITIES.register("tile_filled_soup_pan",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.appliance.TileFilledSoupPan::new, ModBlocks.FILLED_SOUP_PAN.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileMakerHandle>> TILE_MAKER_HANDLE = BLOCK_ENTITIES.register("tile_maker_handle",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileMakerHandle::new, ModBlocks.TEA_MAKER_NEXT.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TilePanHandle>> TILE_PAN_HANDLE = BLOCK_ENTITIES.register("tile_pan_handle",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TilePanHandle::new, ModBlocks.EMPTY_PAN_G.get()).build(null));

    // --- WT-B: ENERGY (TileChargerBase, TileChargerDevice, TileGelBat, TileHandleEngine) ---
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.energy.TileChargerBase>> TILE_CHARGER_BASE = BLOCK_ENTITIES.register("tile_charger_base",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.energy.TileChargerBase::new, ModBlocks.BAT_BOX.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.energy.TileChargerDevice>> TILE_CHARGER_DEVICE = BLOCK_ENTITIES.register("tile_charger_device",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.energy.TileChargerDevice::new, ModBlocks.BAT_BOX.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.energy.TileGelBat>> TILE_GEL_BAT = BLOCK_ENTITIES.register("tile_gel_bat",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.energy.TileGelBat::new, ModBlocks.GEL_BAT.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.energy.TileHandleEngine>> TILE_HANDLE_ENGINE = BLOCK_ENTITIES.register("tile_handle_engine",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.energy.TileHandleEngine::new, ModBlocks.HANDLE_ENGINE.get()).build(null));

    // --- WT-B: CONTAINER/EDIBLE/BREWING/DECOR (TileCupHandle, TileBread, TileSteak, TileCocktail, TileLargeBottle, TileBowlRack, TileCrowDoll, etc. 47 total) ---
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileCupHandle>> TILE_CUP_HANDLE = BLOCK_ENTITIES.register("tile_cup_handle",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileCupHandle::new, ModBlocks.FILLED_CUP.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileBread>> TILE_BREAD = BLOCK_ENTITIES.register("tile_bread",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileBread::new, ModBlocks.BASKET.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileSteak>> TILE_STEAK = BLOCK_ENTITIES.register("tile_steak",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileSteak::new, ModBlocks.FOOD_PLATE.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileCocktail>> TILE_COCKTAIL = BLOCK_ENTITIES.register("tile_cocktail",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileCocktail::new, ModBlocks.COCKTAIL.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileCocktail2>> TILE_COCKTAIL2 = BLOCK_ENTITIES.register("tile_cocktail2",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileCocktail2::new, ModBlocks.COCKTAIL2.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileCocktailSP>> TILE_COCKTAIL_SP = BLOCK_ENTITIES.register("tile_cocktail_sp",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileCocktailSP::new, ModBlocks.COCKTAIL_SP.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileLargeBottle>> TILE_LARGE_BOTTLE = BLOCK_ENTITIES.register("tile_large_bottle",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileLargeBottle::new, ModBlocks.LARGE_BOTTLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileEmptyBottle>> TILE_EMPTY_BOTTLE = BLOCK_ENTITIES.register("tile_empty_bottle",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileEmptyBottle::new, ModBlocks.EMPTY_BOTTLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileCordial>> TILE_CORDIAL = BLOCK_ENTITIES.register("tile_cordial",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileCordial::new, ModBlocks.CORDIAL.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileBrewingBarrel>> TILE_BREWING_BARREL = BLOCK_ENTITIES.register("tile_brewing_barrel",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileBrewingBarrel::new, ModBlocks.BARREL.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileBowlRack>> TILE_BOWL_RACK = BLOCK_ENTITIES.register("tile_bowl_rack",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileBowlRack::new, ModBlocks.BOWL_RACK.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileChopsticksBox>> TILE_CHOPSTICKS_BOX = BLOCK_ENTITIES.register("tile_chopsticks_box",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileChopsticksBox::new, ModBlocks.CHOPSTICKS_BOX.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileVegiBag>> TILE_VEGI_BAG = BLOCK_ENTITIES.register("tile_vegi_bag",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileVegiBag::new, ModBlocks.VEGI_BAG.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileCardBoard>> TILE_CARD_BOARD = BLOCK_ENTITIES.register("tile_card_board",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileCardBoard::new, ModBlocks.CARDBOARD.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileCPanel>> TILE_C_PANEL = BLOCK_ENTITIES.register("tile_c_panel",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileCPanel::new, ModBlocks.CHALCEDONY_PANEL.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileCLamp>> TILE_C_LAMP = BLOCK_ENTITIES.register("tile_c_lamp",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileCLamp::new, ModBlocks.CHALCEDONY_LAMP.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileCrowDoll>> TILE_CROW_DOLL = BLOCK_ENTITIES.register("tile_crow_doll",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileCrowDoll::new, ModBlocks.CROW_DOLL.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileFlowerPot>> TILE_FLOWER_POT = BLOCK_ENTITIES.register("tile_flower_pot",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileFlowerPot::new, ModBlocks.FLOWER_POT.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileIncenseBase>> TILE_INCENSE_BASE = BLOCK_ENTITIES.register("tile_incense_base",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileIncenseBase::new, ModBlocks.INCENSE_BASE.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileEggs>> TILE_EGGS = BLOCK_ENTITIES.register("tile_eggs",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileEggs::new, ModBlocks.EGG_BASKET.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileJPBowl>> TILE_JP_BOWL = BLOCK_ENTITIES.register("tile_jp_bowl",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileJPBowl::new, ModBlocks.BOWL_JP.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileKinoko>> TILE_KINOKO = BLOCK_ENTITIES.register("tile_kinoko",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileKinoko::new, ModBlocks.MUSH_BOX.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileWipeBox>> TILE_WIPE_BOX = BLOCK_ENTITIES.register("tile_wipe_box",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileWipeBox::new, ModBlocks.WIPE_BOX.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileWipeBox2>> TILE_WIPE_BOX2 = BLOCK_ENTITIES.register("tile_wipe_box2",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileWipeBox2::new, ModBlocks.WIPE_BOX2.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileRotaryDial>> TILE_ROTARY_DIAL = BLOCK_ENTITIES.register("tile_rotary_dial",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileRotaryDial::new, ModBlocks.ROTARY_DIAL.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileAlcoholCup>> TILE_ALCOHOL_CUP = BLOCK_ENTITIES.register("tile_alcohol_cup",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileAlcoholCup::new, ModBlocks.ALCOHOL_CUP.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileIceCream>> TILE_ICE_CREAM = BLOCK_ENTITIES.register("tile_ice_cream",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileIceCream::new, ModBlocks.ICE_CREAM.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileHasDirection>> TILE_HAS_DIRECTION = BLOCK_ENTITIES.register("tile_has_direction",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileHasDirection::new, ModBlocks.BASKET.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileHasRemaining>> TILE_HAS_REMAINING = BLOCK_ENTITIES.register("tile_has_remaining",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileHasRemaining::new, ModBlocks.CHALCEDONY.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileHasRemain2>> TILE_HAS_REMAIN2 = BLOCK_ENTITIES.register("tile_has_remain2",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileHasRemain2::new, ModBlocks.CHALCEDONY_PANEL.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileDummy>> TILE_DUMMY = BLOCK_ENTITIES.register("tile_dummy",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileDummy::new, ModBlocks.WOOD_BOX.get()).build(null));
    public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.TileContainerBase>> TILE_CONTAINER_BASE = BLOCK_ENTITIES.register("tile_container_base",
        () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.TileContainerBase::new, ModBlocks.WOOD_BOX.get()).build(null));

    private ModBlockEntities() {}
}
