package mods.defeatedcrow.common.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 1.20.1 BlockEntity registry — Builder.of + Holder.
 * See doc/tile-entities/migration-guide.md:12
 * WT-B owns all 45 BEs. Bootstrap owns the DeferredRegister shell.
 */
public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "defeatedcrow");

    // --- WT-B: APPLIANCE (TileMakerNext, TileProcessor, TileAdvProcessor, TileEvaporator, TileIceMaker, TilePanG, TileTeppanII, TileFilledSoupPan, TileMakerHandle, TilePanHandle) ---
    // public static final RegistryObject<BlockEntityType<mods.defeatedcrow.common.tile.appliance.TileMakerNext>> TEA_MAKER_NEXT = BLOCK_ENTITIES.register("tea_maker_next",
    //     () -> BlockEntityType.Builder.of(mods.defeatedcrow.common.tile.appliance.TileMakerNext::new, ModBlocks.TEA_MAKER_NEXT.get()).build(null));

    // --- WT-B: ENERGY (TileChargerBase, TileChargerDevice, TileGelBat, TileHandleEngine) ---

    // --- WT-B: CONTAINER/EDIBLE/BREWING/DECOR (TileCupHandle, TileBread, TileSteak, TileCocktail, TileLargeBottle, TileBowlRack, TileCrowDoll, etc. 45 total) ---

    private ModBlockEntities() {}
}
