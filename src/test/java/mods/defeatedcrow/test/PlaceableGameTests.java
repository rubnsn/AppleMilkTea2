package mods.defeatedcrow.test;

import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

import mods.defeatedcrow.common.entity.edible.PlaceableBowl;
import mods.defeatedcrow.common.entity.edible.PlaceableTart;
import mods.defeatedcrow.common.registry.ModEntities;
import mods.defeatedcrow.common.registry.ModItems;

@PrefixGameTestTemplate(false)
@GameTestHolder("dcsapplemilk")
public class PlaceableGameTests {

    private static final String EMPTY = "empty";

    @GameTest(template = EMPTY)
    public void placeableBowlRegistered(GameTestHelper helper) {
        helper.assertTrue(ModEntities.PLACEABLE_BOWL.isPresent(), "PLACEABLE_BOWL not registered");
        helper.assertTrue(ModEntities.PLACEABLE_TART.isPresent(), "PLACEABLE_TART not registered");
        helper.assertTrue(ModEntities.PLACEABLE_BOWL.get() != null, "bowl type null");
        helper.succeed();
    }

    @GameTest(template = EMPTY)
    public void placeableBowlSpawnAndHurt(GameTestHelper helper) {
        var level = helper.getLevel();
        BlockPos pos = new BlockPos(2, 2, 2);
        var type = ModEntities.PLACEABLE_BOWL.get();
        var entity = new PlaceableBowl((EntityType<?>) type, level);
        entity.setPos(2.5, 2, 2.5);
        level.addFreshEntity(entity);
        helper.assertTrue(!entity.isRemoved(), "bowl not added");
        // hurt should discard and drop
        var before = level.getEntitiesOfClass(PlaceableBowl.class, entity.getBoundingBox().inflate(1));
        helper.assertTrue(!before.isEmpty(), "bowl not found in world");
        entity.hurt(level.damageSources().generic(), 1.0F);
        helper.assertTrue(entity.isRemoved(), "bowl not removed after hurt");
        helper.succeed();
    }

    @GameTest(template = EMPTY)
    public void placeableTartSpawn(GameTestHelper helper) {
        var level = helper.getLevel();
        var type = ModEntities.PLACEABLE_TART.get();
        var entity = new PlaceableTart((EntityType<?>) type, level);
        entity.setPos(3.5, 2, 3.5);
        level.addFreshEntity(entity);
        helper.assertTrue(!entity.isRemoved(), "tart not added");
        helper.succeed();
    }

    @GameTest(template = EMPTY)
    public void edibleEntityItemRegistered(GameTestHelper helper) {
        helper.assertTrue(ModItems.APPLE_TART.isPresent(), "apple_tart not registered");
        helper.assertTrue(ModItems.APPLE_SANDWICH.isPresent(), "apple_sandwich not registered");
        helper.assertTrue(ModItems.BASE_SOUP_BOWL.isPresent(), "base_soup_bowl not registered");
        var stack = new ItemStack(ModItems.APPLE_TART.get());
        helper.assertTrue(!stack.isEmpty(), "apple_tart stack empty");
        helper.succeed();
    }
}
