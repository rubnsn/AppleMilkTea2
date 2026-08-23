package mods.defeatedcrow.common.entity;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import mods.defeatedcrow.common.registry.ModItems;

/**
 * 1.20.1 stub for VillagerYome — trades for storage boxes, chalcedony, batteries.
 * See VillagerCafe.java for migration notes.
 */
public class VillagerYome {

    @SubscribeEvent
    public static void onTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.ARMORER) {
            var list = event.getTrades().get(2);
            if (list == null) return;
            list.add((trader, rand) -> new net.minecraft.world.entity.npc.VillagerTrades.ItemListing() {
                @Override
                public net.minecraft.world.item.trading.MerchantOffer getOffer(net.minecraft.world.entity.Entity trader, net.minecraft.util.RandomSource rand) {
                    return new net.minecraft.world.item.trading.MerchantOffer(new ItemStack(Items.EMERALD, 3), new ItemStack(ModItems.BATTERY.get(), 1), 8, 5, 0.2F);
                }
            });
        }
    }
}
