package mods.defeatedcrow.common.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.sounds.SoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 1.20.1 Block registry — FG6 + mojmap + DeferredRegister.
 * Bootstrap-owned skeleton. WT-A/B/C append inside their commented sections only.
 * Registry namespace is "defeatedcrow" for legacy world compat (1.7.10 GameRegistry used "defeatedcrow.*").
 * ModID remains "DCsAppleMilk" (mods.toml). DeferredRegister namespace != modId is intentional for save compat.
 * See doc/blocks/migration-guide.md:174
 */
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "defeatedcrow");

    // --- WT-A: APPLIANCE (teaMakerNext, teaMakerBlack, emptyCup, iceMaker, emptyPanGaiden, filledSoupPan, teppanII, processor, evaporator, advProcessor, incenseBase) ---
    // Example (WT-A fills):
    // public static final RegistryObject<Block> TEA_MAKER_NEXT = BLOCKS.register("tea_maker_next",
    //     () -> new BlockTeaMakerNext(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(2.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));

    // --- WT-A: CONTAINER (woodBox, appleBox, vegiBag, cardboard, charcoalBox, gunpowderContainer, eggBasket, mushroomBox, melonBomb, wipeBox, wipeBox2, mobBlock, silkyMelon, flowerPot, yuzuFence, flowerBase, hedge, containerWBottle, containerSaddle) ---

    // --- WT-A: EDIBLE (teacupBlock, teaCup2, blockIcecream, cocktail, cocktail2, alcoholCup, bowlBlock, bowlJP, foodPlate, chocoBlock, cocktailSP) ---

    // --- WT-A: BREWING (emptyBottle, largeBottle, cordial, barrel, blockDummyAlcohol, blockDummyAlcohol2) ---

    // --- WT-A: PLANTS (saplingTea, teaTree, cassisTree, clamSand, cropMint, saplingYuzu, logYuzu, leavesYuzu) ---

    // --- WT-A: DECORATIVE (bowlRack, Basket, chopsticksBox, woodPanel, flintBlock, chalcedony, cLamp, rotaryDial, chalcenonyPanel, cLampOpaque, crowDoll) ---

    // --- WT-A: ENERGY (batBox, redGel, yuzuGel, yuzuBat, gelBat, handleEngine) ---

    // --- WT-B: FLUID BLOCKS (blockVegitableOil, blockCamelliaOil) — LiquidBlock, see ModFluids ---
    public static final RegistryObject<net.minecraft.world.level.block.LiquidBlock> BLOCK_VEGI_OIL = BLOCKS.register("block_vegi_oil",
        () -> new mods.defeatedcrow.common.fluid.BlockOilFluid(ModFluids.VEGITABLE_OIL_SOURCE, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().strength(100.0F).noLootTable().liquid()));
    public static final RegistryObject<net.minecraft.world.level.block.LiquidBlock> BLOCK_CAMELLIA_OIL = BLOCKS.register("block_camellia_oil",
        () -> new mods.defeatedcrow.common.fluid.BlockCamOilFluid(ModFluids.CAMELLIA_OIL_SOURCE, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().strength(100.0F).noLootTable().liquid()));

    private ModBlocks() {}
}
