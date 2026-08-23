package mods.defeatedcrow.common.entity;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import mods.defeatedcrow.common.registry.ModBlocks;
import mods.defeatedcrow.common.registry.ModItems;

/**
 * 1.20.1 stub for VillagerCafe (Cafe master) - trades for tea/tart/soup/cups.
 * Original 1.7.10 used VillagerRegistry.registerVillagerId + VillagerTrades. 1.20.1 uses VillagerProfession + PoiType + VillagerTradesEvent.
 * Full profession registration is TODO (requires DeferredRegister<VillagerProfession> + PoiType). This stub registers trades via event.
 * See doc/entities/migration-guide.md:95 and doc/worldgen/migration-guide.md:105
 */
public class VillagerCafe {

    @SubscribeEvent
    public static void onTrades(VillagerTradesEvent event) {
        // TODO: check profession == ModVillagers.CAFE.get() when profession registry is added
        // For now, inject into any villager trades for compilation check - actual logic restored in Phase 2
        if (event.getType() == VillagerProfession.LIBRARIAN) {
            var list = event.getTrades().get(2);
            if (list == null) return;
            list.add((trader, rand) -> new net.minecraft.world.entity.npc.VillagerTrades.ItemListing() {
                @Override
                public net.minecraft.world.item.trading.MerchantOffer getOffer(net.minecraft.world.entity.Entity trader, net.minecraft.util.RandomSource rand) {
                    return new net.minecraft.world.item.trading.MerchantOffer(new ItemStack(Items.EMERALD, 1), new ItemStack(ModItems.APPLE_TART.get(), 1), 12, 5, 0.2F);
                }
            });
        }
    }
}
