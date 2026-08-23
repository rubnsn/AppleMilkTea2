package mods.defeatedcrow.common.world;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.loot.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.data.event.GatherDataEvent;

/**
 * 1.20.1: ChestGen_old -> GlobalLootModifier + LootTableLoadEvent
 * See doc/worldgen/migration-guide.md: ChestGen_old removed in 1.19, replaced by LootModifier datapack.
 * This class now provides LootTableLoadEvent hook and documents GlobalLootModifierProvider.
 * Datapack: data/defeatedcrow/loot_modifiers/add_tea.json etc.
 */
public class AddChestGen {

    @SubscribeEvent
    public void onLootLoad(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();
        // village / jungle / stronghold / dungeon / mineshaft injection via LootPool
        // Example legacy injection; in 1.20.1 prefer GlobalLootModifierProvider (datagen)
        // if (name.equals(new ResourceLocation("minecraft", "chests/village_blacksmith"))) {
        //    event.getTable().addPool(LootPool.lootPool().name("defeatedcrow:tea").setRolls(ConstantValue.exactly(1))
        //        .add(LootItem.lootTableItem(() -> ModItems.LEAF_TEA.get()).setWeight(20)).build());
        // }
    }

    /**
     * 1.20.1 GlobalLootModifierProvider example (datagen):
     * public class ModLootModifiers extends GlobalLootModifierProvider {
     *   protected void start() { add("add_tea", new AddItemModifier(new LootTableIdCondition[]{ new LootTableIdCondition(new ResourceLocation("minecraft","chests/village_blacksmith")) }, ModItems.LEAF_TEA.get(), 5)); }
     * }
     * datapack: data/defeatedcrow/forge/loot_modifiers/global_loot_modifiers.json
     */

    @Deprecated
    public void addChestItems() {
        // ChestGen_old removed; use GlobalLootModifier datapack instead.
    }
}
