package mods.defeatedcrow.test;

import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

import mods.defeatedcrow.common.registry.ModBlocks;
import mods.defeatedcrow.common.registry.ModItems;
import mods.defeatedcrow.common.tile.appliance.TilePanG;
import mods.defeatedcrow.common.tile.appliance.TileProcessor;
import mods.defeatedcrow.common.tile.appliance.TileEvaporator;
import mods.defeatedcrow.common.tile.appliance.TileIceMaker;
import mods.defeatedcrow.common.tile.appliance.TileTeppanII;
import mods.defeatedcrow.common.tile.appliance.TileMakerNext;

/**
 * P2 verification: appliance TileEntity tick + RecipeManager integration.
 * Covers TEST_PLAN C-1..C-6 (TeaMaker/Pan/Teppan/Processor/Evaporator/IceMaker).
 */
@PrefixGameTestTemplate(false)
@GameTestHolder("dcsapplemilk")
public class ApplianceGameTests {

    private static final String EMPTY = "empty";

    // Pan: apple + heat -> baked_apple (cookingTime 200, heat required)
    @GameTest(template = EMPTY)
    public void panAppleToBaked(GameTestHelper helper) {
        var level = helper.getLevel();
        BlockPos panPos = new BlockPos(1, 2, 1);
        BlockPos below = panPos.below();
        level.setBlock(below, Blocks.FIRE.defaultBlockState(), 3);
        level.setBlock(panPos, ModBlocks.EMPTY_PAN_G.get().defaultBlockState(), 3);
        var be = (TilePanG) level.getBlockEntity(panPos);
        helper.assertTrue(be != null, "TilePanG not found");
        be.setItem(0, new ItemStack(Items.APPLE));
        // tick 250 times (recipe needs 200)
        var state = level.getBlockState(panPos);
        for (int i = 0; i < 250; i++) TilePanG.tick(level, panPos, state, be);
        ItemStack out = be.getItem(1);
        helper.assertTrue(!out.isEmpty(), "pan output empty");
        helper.assertTrue(out.is(ModItems.BAKED_APPLE.get()), "pan output not baked_apple: " + out);
        helper.succeed();
    }

    // Processor: gravel -> sand + flint secondary (tier 0, 9 slots 2-10)
    @GameTest(template = EMPTY)
    public void processorGravelToSand(GameTestHelper helper) {
        var level = helper.getLevel();
        BlockPos pos = new BlockPos(1, 2, 1);
        level.setBlock(pos, ModBlocks.PROCESSOR.get().defaultBlockState(), 3);
        var be = (TileProcessor) level.getBlockEntity(pos);
        helper.assertTrue(be != null, "TileProcessor not found");
        be.setItem(2, new ItemStack(Items.GRAVEL));
        var state = level.getBlockState(pos);
        for (int i = 0; i < 150; i++) TileProcessor.tick(level, pos, state, be);
        ItemStack out = be.getItem(11);
        helper.assertTrue(!out.isEmpty() && out.is(Items.SAND), "processor output not sand: " + out);
        helper.succeed();
    }

    // Evaporator: sugar -> gunpowder (time 100)
    @GameTest(template = EMPTY)
    public void evaporatorSugarToGunpowder(GameTestHelper helper) {
        var level = helper.getLevel();
        BlockPos pos = new BlockPos(1, 2, 1);
        level.setBlock(pos, ModBlocks.EVAPORATOR.get().defaultBlockState(), 3);
        var be = (TileEvaporator) level.getBlockEntity(pos);
        helper.assertTrue(be != null, "TileEvaporator not found");
        be.setItem(0, new ItemStack(Items.SUGAR));
        var state = level.getBlockState(pos);
        for (int i = 0; i < 150; i++) TileEvaporator.tick(level, pos, state, be);
        ItemStack out = be.getItem(1);
        helper.assertTrue(!out.isEmpty() && out.is(Items.GUNPOWDER), "evaporator output not gunpowder: " + out);
        helper.succeed();
    }

    // IceMaker: condensed_milk -> ice_cream_block (container bowl)
    @GameTest(template = EMPTY)
    public void iceMakerCondensedMilk(GameTestHelper helper) {
        var level = helper.getLevel();
        BlockPos pos = new BlockPos(1, 2, 1);
        level.setBlock(pos, ModBlocks.ICE_MAKER.get().defaultBlockState(), 3);
        var be = (TileIceMaker) level.getBlockEntity(pos);
        helper.assertTrue(be != null, "TileIceMaker not found");
        be.setItem(0, new ItemStack(ModItems.CONDENSED_MILK.get()));
        var state = level.getBlockState(pos);
        for (int i = 0; i < 150; i++) TileIceMaker.tick(level, pos, state, be);
        ItemStack out = be.getItem(1);
        helper.assertTrue(!out.isEmpty() && out.is(ModItems.ICE_CREAM_ITEM.get()), "iceMaker output not ice_cream_block: " + out);
        helper.succeed();
    }

    // TeppanII: beef -> food_plate (plate recipe)
    @GameTest(template = EMPTY)
    public void teppanBeefToPlate(GameTestHelper helper) {
        var level = helper.getLevel();
        BlockPos pos = new BlockPos(1, 2, 1);
        BlockPos below = pos.below();
        level.setBlock(below, Blocks.FIRE.defaultBlockState(), 3);
        level.setBlock(pos, ModBlocks.TEPPAN_II.get().defaultBlockState(), 3);
        var be = (TileTeppanII) level.getBlockEntity(pos);
        helper.assertTrue(be != null, "TileTeppanII not found");
        be.setItem(0, new ItemStack(Items.BEEF));
        var state = level.getBlockState(pos);
        for (int i = 0; i < 150; i++) TileTeppanII.tick(level, pos, state, be);
        // output could be in any of 0-3 except input, check any slot has food_plate
        boolean found = false;
        for (int i = 0; i < be.getContainerSize(); i++) {
            var s = be.getItem(i);
            if (!s.isEmpty() && s.is(ModItems.FOOD_PLATE_ITEM.get())) { found = true; break; }
        }
        helper.assertTrue(found, "teppan output not food_plate");
        helper.succeed();
    }

    // TeaMakerNext: leaf_tea -> filled_cup (tea)
    @GameTest(template = EMPTY)
    public void teaMakerLeafTea(GameTestHelper helper) {
        var level = helper.getLevel();
        BlockPos pos = new BlockPos(1, 2, 1);
        level.setBlock(pos, ModBlocks.TEA_MAKER_NEXT.get().defaultBlockState(), 3);
        var be = (TileMakerNext) level.getBlockEntity(pos);
        helper.assertTrue(be != null, "TileMakerNext not found");
        be.setItem(0, new ItemStack(ModItems.LEAF_TEA.get()));
        var state = level.getBlockState(pos);
        for (int i = 0; i < 150; i++) TileMakerNext.tick(level, pos, state, be);
        ItemStack out = be.getItem(1);
        helper.assertTrue(!out.isEmpty() && out.is(ModItems.FILLED_CUP_ITEM.get()), "teaMaker output not filled_cup: " + out);
        helper.succeed();
    }
}
