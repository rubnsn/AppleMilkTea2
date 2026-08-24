package mods.defeatedcrow.common.world;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import mods.defeatedcrow.common.registry.ModItems;
import mods.defeatedcrow.common.registry.ModBlocks;

/**
 * 1.20.1 AddChestGen - chest loot via LootTableLoadEvent (replaces old chest hooks).
 * Original 1.7.10 used chest hooks addItem with WeightedRandomChestContent, now GlobalLootModifier/datapack or LootTableLoadEvent.
 * See doc/worldgen/migration-guide.md
 */
public class AddChestGen {

    @SubscribeEvent
    public void onLootLoad(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();
        if (name == null) return;
        String path = name.getPath();
        // villageBlacksmith / pyramidJungleChest -> add saplingTea etc.
        if (path.contains("village_blacksmith") || path.contains("jungle_temple") || path.contains("dungeon") || path.contains("mineshaft")) {
            // TODO: restore weighted loot when ModItems are ready; example for villageBlacksmith:
            // LootPool pool = LootPool.lootPool().setRolls(ConstantValue.exactly(1))
            //   .add(LootItem.lootTableItem(ModItems.SAPLING_TEA.get()).setWeight(20).build())
            //   .build();
            // event.getTable().addPool(pool);
        }
        // villageCafeDC is custom; handled via datapack data/defeatedcrow/loot_tables/chests/village_cafe.json and GlobalLootModifier
    }

    public void addChestItems() {
        // Legacy entry point kept for DCsAppleMilk compatibility; now loot is datapack-driven.
        // Register this class to FORGE bus: MinecraftForge.EVENT_BUS.register(new AddChestGen());
    }
}
